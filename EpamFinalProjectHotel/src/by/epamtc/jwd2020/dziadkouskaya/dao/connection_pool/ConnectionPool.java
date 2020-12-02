package by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;

/**
 * 
 * @author Mi
 *
 */
public class ConnectionPool {

	private static final ConnectionPool instance = new ConnectionPool();

	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

	/**
	 * queue of free connections
	 */
	private BlockingQueue<Connection> connectionQueue;
	/**
	 * queue of busy connections
	 */
	private BlockingQueue<Connection> givenAwayConQueuue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	public ConnectionPool() throws ConnectionPoolException {	
	
			initPoolData();



	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	/**
	 * 
	 * @throws ConnectionPoolException
	 */
	public void initPoolData() throws ConnectionPoolException {
		DBResourceManager dbResourceManager = DBResourceManager.getInstance();
		this.driverName = dbResourceManager.getValue(DBParametr.DB_DRIVER);
		this.url = dbResourceManager.getValue(DBParametr.DB_URL);
		this.user = dbResourceManager.getValue(DBParametr.DB_USER);
		this.password = dbResourceManager.getValue(DBParametr.DB_PASSWORD);

		try {
			this.poolSize = Integer.parseInt(dbResourceManager.getValue(DBParametr.DB_POOL_SIZE));

		} catch (Exception e) {
			poolSize = 5;
		}

		try {

			Class.forName(driverName);
			givenAwayConQueuue = new ArrayBlockingQueue<Connection>(poolSize);
			connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);

			for (int i = 0; i < poolSize; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);

				PooledConnection pooledConnection = new PooledConnection(connection);

				connectionQueue.add(pooledConnection);

			}

		} catch (SQLException e) {
			logger.error("SQLConnections in ConnectionPool", e);
			throw new ConnectionPoolException("SQLConnections in ConnectionPool", e);

		} catch (ClassNotFoundException e) {
			logger.error("Can't find database driver class", e);
			throw new ConnectionPoolException("Can't find database driver class", e);

		}
	}

	/**
	 * close of any connection queue
	 * 
	 * @param queue
	 * @throws ConnectionPoolException
	 * @throws SQLException
	 */
	private void closeConnectionQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			try {
				if (!connection.getAutoCommit()) {
					connection.commit();
				}
				((PooledConnection) connection).reallyClose();
			} catch (SQLException e) {
				logger.error("Can't close connection queue", e);
				throw new ConnectionPoolException("Can't close connection queue", e);
			}

		}
	}

	/**
	 * destruction of the connection pool
	 * 
	 * @throws ConnectionPoolException
	 */

	private void clearConnectionQueue() throws ConnectionPoolException, SQLException {
		closeConnectionQueue(connectionQueue);
		closeConnectionQueue(givenAwayConQueuue);
	}

	public void dispose() throws ConnectionPoolException {
		try {
			clearConnectionQueue();
		} catch (SQLException e) {
			logger.error("Can't dispose", e);
			throw new ConnectionPoolException("Can't dispose", e);
		}
	}

	/**
	 * take connection from head of connectionQueue and move it to
	 * givenAwayConQueuue
	 * 
	 * @return connection
	 * @throws ConnectionPoolException
	 */
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;

		try {
			connection = connectionQueue.take();
			givenAwayConQueuue.add(connection);
		} catch (InterruptedException e) {
			logger.error("Error connection to data sourse", e);
			throw new ConnectionPoolException("Error connection to data sourse", e);
		}
		return connection;

	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
		try {
			rs.close();
		} catch (SQLException e) {
			logger.error("ResultSet is not closed", e);
			throw new ConnectionPoolException("ResultSet is not closed", e);
		}

		try {
			st.close();
		} catch (SQLException e) {
			logger.error("Statement is not closed", e);
			throw new ConnectionPoolException("Statement is not closed", e);
		}
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("Connection is not closed", e);
			throw new ConnectionPoolException("Connection is not closed", e);
		}

	}

	public void closeConnection(Connection con, Statement st) throws ConnectionPoolException {
		try {
			st.close();
		} catch (SQLException e) {
			logger.error("Statement is not closed", e);
			throw new ConnectionPoolException("Statement is not closed", e);
		}
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("Connection is not closed", e);
			throw new ConnectionPoolException("Connection is not closed", e);
		}

	}

	public void dropAllConnections() {
		for (int i = 0; i < poolSize; i++) {
			try {
				((PooledConnection) connectionQueue.take()).reallyClose();
			} catch (SQLException e) {
				logger.error("Could not close connection", e);
			} catch (InterruptedException e) {
				logger.error("Could not take connection", e);
			}
		}
	}

	/**
	 * logic connection
	 * 
	 * @author Mi
	 *
	 */

	class PooledConnection implements Connection {
		private Connection connection;

		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			this.connection.setAutoCommit(true);
		}

		public void reallyClose() throws SQLException {
			connection.close();
		}

		@Override
		public boolean isWrapperFor(Class<?> arg0) throws SQLException {
			return connection.isWrapperFor(arg0);
		}

		@Override
		public <T> T unwrap(Class<T> arg0) throws SQLException {
			return connection.unwrap(arg0);
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			connection.abort(executor);

		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		/*
		 * расширена реализация - connection перемещается из очереди свободных в очередь
		 * занятых
		 */
		@Override
		public void close() throws SQLException {
			if (connection.isClosed()) {
				throw new SQLException("Attemping to close closed connection");
			}
			if (connection.isReadOnly()) {
				connection.setReadOnly(false);
			}
			if (!givenAwayConQueuue.remove(this)) {
				throw new SQLException("Error deleting connection from given away connection pool");
			}
			if (!connectionQueue.offer(this)) {
				throw new SQLException("Error allocating connection in this pool");

			}

		}

		@Override
		public void commit() throws SQLException {
			connection.commit();

		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			return connection.createArrayOf(typeName, elements);
		}

		@Override
		public Blob createBlob() throws SQLException {
			return connection.createBlob();
		}

		@Override
		public Clob createClob() throws SQLException {

			return connection.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {

			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {

			return connection.createSQLXML();
		}

		@Override
		public Statement createStatement() throws SQLException {

			return connection.createStatement();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {

			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {

			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {

			return connection.createStruct(typeName, attributes);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {

			return connection.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {

			return connection.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {

			return connection.getClientInfo();
		}

		@Override
		public String getClientInfo(String name) throws SQLException {

			return connection.getClientInfo(name);
		}

		@Override
		public int getHoldability() throws SQLException {

			return connection.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {

			return connection.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {

			return connection.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {

			return connection.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {

			return connection.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {

			return connection.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {

			return connection.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {

			return connection.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {

			return connection.isReadOnly();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {

			return connection.isValid(timeout);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {

			return connection.nativeSQL(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {

			return connection.prepareCall(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {

			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {

			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {

			return connection.prepareStatement(sql);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {

			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {

			return connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {

			return connection.prepareStatement(sql, columnNames);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {

			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {

			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			connection.releaseSavepoint(savepoint);

		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();

		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			connection.rollback(savepoint);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {

			connection.setAutoCommit(autoCommit);

		}

		@Override
		public void setCatalog(String catalog) throws SQLException {

			connection.setCatalog(catalog);

		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {

			connection.setClientInfo(properties);

		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {

			connection.setClientInfo(name, value);

		}

		@Override
		public void setHoldability(int holdability) throws SQLException {

			connection.setHoldability(holdability);

		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {

			connection.setNetworkTimeout(executor, milliseconds);

		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {

			connection.setReadOnly(readOnly);

		}

		@Override
		public Savepoint setSavepoint() throws SQLException {

			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {

			return connection.setSavepoint(name);
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			connection.setSchema(schema);
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);

		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			connection.setTypeMap(map);

		}

	}
}
