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
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.BookingDaoImpl;

public class ConnectionPool {
	private static final ConnectionPool instance = new ConnectionPool();
	
	private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
	
	// очередь свободных коннекшенов
	private BlockingQueue<Connection> connectionQueue;
	// очередь занятых коннекшенов
	private BlockingQueue<Connection> givenAwayConQueuue;

	private String driverName;
	private String url;
	private String user;
	private String password;
	private int poolSize;

	public ConnectionPool()  {
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
			this.initPoolData();
			
		} catch (DaoException e) {
			logger.error("Error in creating new Connection Pool", e);
			e.printStackTrace();
		}
		
		

	}
	
	public static ConnectionPool getInstance() {
		return instance;
	}

	public void initPoolData() throws DaoException {

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
			throw new DaoException("SQLConnections in ConnectionPool", e);

		} catch (ClassNotFoundException e) {
			logger.error("Can't find database driver class", e);
			throw new DaoException("Can't find database driver class", e);

		}
	}

	private void closeConnectionQueue(BlockingQueue<Connection> queue) throws SQLException {
		Connection connection;
		while ((connection = queue.poll()) != null) {
			if (!connection.getAutoCommit()) {
				connection.commit();
			}
			((PooledConnection) connection).reallyClose();
		}
	}

	// уничтожение пула коннекшенов
	private void clearConnectionQueue() throws DaoException {
		try {
			
			closeConnectionQueue(connectionQueue);
			closeConnectionQueue(givenAwayConQueuue);

		} catch (SQLException exception) {
			logger.error("Error closing the connection", exception);
			throw new DaoException("Error closing the connection", exception);

		}
	}

	public void dispose() throws DaoException {
		clearConnectionQueue();
	}

	public Connection takeConnection() throws DaoException {
		Connection connection = null;

		try {
			connection = connectionQueue.take();
			givenAwayConQueuue.add(connection);
		} catch (InterruptedException e) {
			logger.error("Error connection to data sourse", e);
			throw new DaoException("Error connection to data sourse", e);
		}
		return connection;

	}

	public void closeConnection(Connection con, Statement st, ResultSet rs) throws DaoException {
		try {
			rs.close();
		} catch (SQLException e) {
			logger.error("ResultSet is not closed", e);
			throw new DaoException("ResultSet is not closed", e);
		}

		try {
			st.close();
		} catch (SQLException e) {
			logger.error("Statement is not closed", e);
			throw new DaoException("Statement is not closed", e);
		}
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("Connection is not closed", e);
			throw new DaoException("Connection is not closed", e);
		}

	}

	public void closeConnection(Connection con, Statement st) throws DaoException {
		try {
			st.close();
		} catch (SQLException e) {
			logger.error("Statement is not closed", e);
			throw new DaoException("Statement is not closed", e);
		}
		try {
			con.close();
		} catch (SQLException e) {
			logger.error("Connection is not closed", e);
			throw new DaoException("Connection is not closed", e);
		}

	}

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
			// TODO Auto-generated method stub
			return connection.createClob();
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return connection.createNClob();
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return connection.createSQLXML();
		}

		@Override
		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return connection.createStatement();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return connection.createStruct(typeName, attributes);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getAutoCommit();
		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getCatalog();
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getClientInfo();
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return connection.getClientInfo(name);
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getHoldability();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getMetaData();
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getNetworkTimeout();
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getSchema();
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getTransactionIsolation();
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getTypeMap();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return connection.getWarnings();
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return connection.isClosed();
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return connection.isReadOnly();
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return connection.isValid(timeout);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return connection.nativeSQL(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareCall(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareStatement(sql);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareStatement(sql, autoGeneratedKeys);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareStatement(sql, columnIndexes);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareStatement(sql, columnNames);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			connection.setAutoCommit(autoCommit);

		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub
			connection.setCatalog(catalog);

		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			connection.setClientInfo(properties);

		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			connection.setClientInfo(name, value);

		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			connection.setHoldability(holdability);

		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			connection.setNetworkTimeout(executor, milliseconds);

		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub
			connection.setReadOnly(readOnly);

		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return connection.setSavepoint();
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
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
