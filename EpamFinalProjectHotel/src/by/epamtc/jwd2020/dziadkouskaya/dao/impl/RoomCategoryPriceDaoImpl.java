package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryPriceDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;
/**
 * class RoomCategoryPriceDaoImpl is implementation of interface RoomCategoryPriceDao, 
 * make all operations with magichotel.room_prices table
 * @author Yana Dziadkouskaya
 *
 */
public class RoomCategoryPriceDaoImpl implements RoomCategoryPriceDao {
	public static final String STRING_ALL_ROOM_CATEGORY_PRICES = "select rc.idHotelRoomCategory, rc.HotelRoomCategoryName, rp.idRoomPrice, rp.RoomPriceStartDate, rp.`RoomPrice, rub/day`  from room_category rc  join room_price rp on rc.idHotelRoomCategory=rp.idRoomCategory order by rc.HotelRoomCategoryName, rp.RoomPriceStartDate;";
	public static final String STRING_DELETE_PRICE= "delete from room_price where idRoomPrice=";
	public static final String SEMICOLON = ";";
	public static final String STRING_INSERT_NEW_PRICE = "insert into room_price (RoomPriceStartDate, `RoomPrice, rub/day`, idRoomCategory) values(?, ?, ?);";
	
	
	private static final Logger logger = LogManager.getLogger(RoomCategoryPriceDaoImpl.class);
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();
	
	
	@Override
	public List<RoomCategoryPrice> findAllRoomPrices() throws DaoException {
		List<RoomCategoryPrice> resultlist = new ArrayList();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_ALL_ROOM_CATEGORY_PRICES;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int idCategory = set.getInt(1);
				String categoryName = set.getString(2);
				int idPrice = set.getInt(3);
				Date priceStartdate = set.getDate(4);
				double price = set.getDouble(5);
				
				RoomCategory roomCategory = new RoomCategory(idCategory, categoryName);
				RoomCategoryPrice categoryPrice = new RoomCategoryPrice(idPrice, priceStartdate, price, roomCategory);
				
				resultlist.add(categoryPrice);
				
			}

		} catch (SQLException e) {
			logger.error("Error in finding roomCategoryCapacity ", e);
			throw new DaoException("Error in finding roomCategoryCapacity ",  e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return resultlist;
		
	}


	@Override
	public void deletePrice(int idPrice) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			String sql = STRING_DELETE_PRICE + idPrice + SEMICOLON;
			ps = connection.prepareStatement(sql);
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error deleting prices ", e);
			throw new DaoException("Error deleting prices", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
		
	}


	@Override
	public void addNewRoomCategoryPrice(RoomCategoryPrice price) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(STRING_INSERT_NEW_PRICE);

			ps.setDate(1, price.getStartDate());
			ps.setDouble(2, price.getPricePerDay());
			ps.setInt(3, price.getRoomCategory().getId());
			
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error in adding new RoomPrice ", e);
			throw new DaoException("Error in adding new RoomPrice ", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
	}
	
	
	


}
