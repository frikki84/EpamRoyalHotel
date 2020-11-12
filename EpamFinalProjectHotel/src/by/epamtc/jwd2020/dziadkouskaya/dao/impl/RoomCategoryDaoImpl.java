package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

/**
 * class RoomCategoryDaoImpl is implementation of interface RoomCategoryDao, 
 * make all operations with magichotel.room_category table
 * @author Yana Dziadkouskaya
 *
 */
public class RoomCategoryDaoImpl implements RoomCategoryDao {
	public static final String STRING_ALL_ROOM_CATEGORY_CAPACITY = "select distinct PeopleNumberInRoom from room_category where PeopleNumberInRoom is not null;";
	public static final String STRING_ALL_ROOM_CATEGORY_NAMES = "select distinct * from room_category where ParentCategory is not null order by idHotelRoomCategory;";
	public static final String STRING_FIND_ROOM_BY_NAME = "select * from room_category where ParentCategory is not null and HotelRoomCategoryName like \"";
	public static final String QUOTES_AND_SEMICOLONS = "\";";
	
	private static final Logger logger = LogManager.getLogger(RoomCategoryDaoImpl.class);
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public List<Integer> createRoomCategoryList() throws DaoException {
		
		List<Integer> resultlist = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		
		String sql = STRING_ALL_ROOM_CATEGORY_CAPACITY;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				
				resultlist.add(set.getInt(1));
			}

		} catch (SQLException e) {
			logger.error("Error in finding roomCategoryCapacity ", e);
			throw new DaoException("Error in finding roomCategoryCapacity ", e);
			
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		
		
		return resultlist;
	}
	
	
	@Override
	public List<RoomCategory> createFullRoomCategoryList() throws DaoException {
		
		List<RoomCategory> resultlist = new ArrayList();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		
		String sql = STRING_ALL_ROOM_CATEGORY_NAMES;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int id = set.getInt(1);
				String roomCategoryName = set.getString(2);
				int idParentRoomCategory = set.getInt(3);
				int peopleNumberInRoom = set.getInt(4);
				
				RoomCategory category = new RoomCategory(id, roomCategoryName, idParentRoomCategory, peopleNumberInRoom);
				resultlist.add(category);
			}

		} catch (SQLException e) {
			logger.error("Error in finding roomCategoryList ", e);
			throw new DaoException("Error in finding roomCategoryList ",  e);
			
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		
		
		return resultlist;
	}

	public RoomCategory findRoomCategoryByName(String categoryName) throws DaoException {
		RoomCategory category  = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		
		String sql = STRING_FIND_ROOM_BY_NAME + categoryName + QUOTES_AND_SEMICOLONS;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				int id = set.getInt(1);
				String roomCategoryName = set.getString(2);
				int idParentRoomCategory = set.getInt(3);
				int peopleNumberInRoom = set.getInt(4);
				
				category = new RoomCategory(id, roomCategoryName, idParentRoomCategory, peopleNumberInRoom);
				
			}

		} catch (SQLException e) {
			logger.error("Error in finding roomCategoryList ", e);
			throw new DaoException("Error in finding roomCategory by name ",  e);
			
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
			
		return category;
	}

}
