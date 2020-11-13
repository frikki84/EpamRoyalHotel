package by.epamtc.jwd2020.dziadkouskaya.dao;

import by.epamtc.jwd2020.dziadkouskaya.dao.impl.BookingDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.CountryDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.RoomCategoryDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.RoomCategoryPriceDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.UserDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.UserDetailDaoImpl;

public class DaoProvider {
	private static final DaoProvider instance = new DaoProvider();
	
	private final UserDao userDao = new UserDaoImpl();
	private final CountryDao countryDao = new CountryDaoImpl();
	private final UserDetailDao userDetailDao = new UserDetailDaoImpl();
	private final RoomCategoryDao roomCategoryDao = new RoomCategoryDaoImpl();
	private final BookingDao bookingDao = new BookingDaoImpl();
	private final RoomCategoryPriceDao priceDao= new RoomCategoryPriceDaoImpl();
	

	private DaoProvider() {
		
	}


	public UserDao getUserDao() {
		return userDao;
	}


	public CountryDao getCountryDao() {
		return countryDao;
	}


	
	public UserDetailDao getUserDetailDao() {
		return userDetailDao;
	}


	public static DaoProvider getInstance() {
		return instance;
	}


	public RoomCategoryDao getRoomCategoryDao() {
		return roomCategoryDao;
	}


	public BookingDao getBookingDao() {
		return bookingDao;
	}


	public RoomCategoryPriceDao getPriceDao() {
		return priceDao;
	}
	
	

}
