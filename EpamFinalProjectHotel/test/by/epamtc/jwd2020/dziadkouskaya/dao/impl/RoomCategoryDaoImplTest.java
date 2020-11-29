package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;


import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

public class RoomCategoryDaoImplTest {
	public static final String STRING_FIND_ALL_CATEGORIES = "select * from room_category;";
	public static final String STRING_FIND_CATEGORIES_ID = "select idHotelRoomCategory from room_category;";
	public static final String STRING_FIND_CATEGORY_BY_NAME = "select * from room_category where HotelRoomCategoryName like \"";
	public static final String STRING_FIND_CATEGORY_BY_NAME_LAST_PART = "\";";
	

	public static final ConnectionPoolTest connectionPoolTest = ConnectionPoolTest.getInstance();
	public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private RoomCategoryDao roomcategoryDao = daoProvider.getRoomCategoryDao();


	@After
	public void disconnectDB() {
		connectionPool.dropAllConnections();
		connectionPoolTest.dropAllConnections();
	}

	@Test
	public void createFullRoomCategoryList() throws DaoException, SQLException {
		List<RoomCategory> testList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_FIND_ALL_CATEGORIES;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		while (set.next()) {
			RoomCategory category = new RoomCategory(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4));
			testList.add(category);
		}
		
		
		List<RoomCategory> expectedList = roomcategoryDao.createFullRoomCategoryList();

		assertEquals(expectedList, testList);

	}
	

	@Test
	public void createRoomCategoryList() throws DaoException, SQLException {
		List<Integer> testList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_FIND_CATEGORIES_ID;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		while (set.next()) {
			int categoryId = set.getInt(1);
			testList.add(categoryId);
		}
		
		
		List<Integer> expectedList = roomcategoryDao.createRoomCategoryList();

		assertEquals(expectedList, testList);

	}
	
	@Test
	public void findRoomCategoryByName() throws DaoException, SQLException {
		String categoryName = "Двухместный люкс";

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_FIND_CATEGORY_BY_NAME + categoryName + STRING_FIND_CATEGORY_BY_NAME_LAST_PART;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);
		
		RoomCategory category = new RoomCategory();

		while (set.next()) {
			category.setId(set.getInt(1));
			category.setRoomCategoryName(set.getString(2));
			category.setIdParentRoomCategory(set.getInt(3));
			category.setPeopleNumberInRoom(set.getInt(4));
		}
		
		
		RoomCategory expectedCategory = roomcategoryDao.findRoomCategoryByName(categoryName);

		assertEquals(expectedCategory, category);

	}



}
