package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.dao.CountryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

/**
 * class CountryDaoImpl is implementation of interface CountryDao, 
 * make all operations with magichotel.countries table
 * @author Yana Dziadkouskaya
 *
 */
public class CountryDaoImpl implements CountryDao{
	public static final String STRING_FIND_ALL_COUNTRIES = "select * from countries;";
	public static final String STRING_FIND_COUNTRY_ID_BY_NAME = "select idCountry from countries where CountryName like \"";
	public static final String STRING_FIND_COUNTRY_ID_BY_NAME_LAST_PART = "\";"; 

	private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);
	
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	@Override
	public List<Country> findCountryList() throws DaoException {
		List<Country> resultlist = new ArrayList<Country>();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		
		String sql = STRING_FIND_ALL_COUNTRIES;


		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				Country country = new Country(set.getInt(1), set.getString(2));
				resultlist.add(country);
			}

		} catch (SQLException e) {
			logger.error("Error in finding userCode", e);
			throw new DaoException("Error in finding userCode ",  e);
			
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		return resultlist;
	}

	@Override
	public int findCountryId(String countryName) throws DaoException{
		int result = 0;
		
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		
		String sql = STRING_FIND_COUNTRY_ID_BY_NAME + countryName + STRING_FIND_COUNTRY_ID_BY_NAME_LAST_PART;


		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				result = set.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("Error in finding userCode", e);
			throw new DaoException("Error in finding userCode " + e);
			
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		
		
		return result;
	}
}
