package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.dao.CountryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

public class CountryDaoImplTest {
	public static final String STRING_FIND_ALL_COUNTRIES = "select * from countries;";
	public static final String STRING_FIND_COUNTRY_ID_BY_NAME = "select idCountry from countries where CountryName like \"";
	public static final String STRING_FIND_COUNTRY_ID_BY_NAME_LAST_PART = "\";";

	public static final ConnectionPoolTest connectionPoolTest = ConnectionPoolTest.getInstance();
	public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private CountryDao countryDao = daoProvider.getCountryDao();

	@After
	public void disconnectDB() {
		connectionPool.dropAllConnections();
		connectionPoolTest.dropAllConnections();
	}

	@Test
	public void findCountryList() throws DaoException, SQLException {
		List<Country> testList = new ArrayList<Country>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_FIND_ALL_COUNTRIES;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		while (set.next()) {
			Country country = new Country(set.getInt(1), set.getString(2));
			testList.add(country);
		}

		List<Country> expectedList = countryDao.findCountryList();

		assertEquals(expectedList, testList);

	}

	@Test
	public void findCountryId() throws DaoException, SQLException {
		int testValue = 0;

		String countryName = "Республика Беларусь";

		Connection connection = null;

		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_FIND_COUNTRY_ID_BY_NAME + countryName + STRING_FIND_COUNTRY_ID_BY_NAME_LAST_PART;

		statement = connection.createStatement();

		set = statement.executeQuery(sql);

		if (set.next()) {
			testValue = set.getInt(1);
		}

		int expectedValue = countryDao.findCountryId(countryName);

		assertEquals(testValue, expectedValue);

	}
}
