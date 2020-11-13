package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserActivity;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDetailDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

/**
 * class UserDetailDaoImpl is implementation of interface UserDetailDao, 
 * make all operations with magichotel.user_details table
 * @author Yana Dziadkouskaya
 *
 */
public class UserDetailDaoImpl implements UserDetailDao {
	public static final String STRING_FIND_USER_DETAILS_BY_USER_ID = "select * from user_details where User_idUser= ";
	public static final String STRING_FIND_USER_DETAILS_BY_USER_ID_LAST_PART = " AND (userCategory = 1 OR userCategory = null)";
	public static final String STRING_FOR_INSERT_INFO_TO_USER_DETAILS = "UPDATE USER_DETAILS SET UserName = ?, UserSurname = ?, UserThirdName = ?, EnglishUserName = ?, EnglishUserSurname = ?, UserPersonBirthDate = ?, PasportNumber = ?, UserPasportID = ?, UserPasportAnotherInfo = ?, Country_idCountry = ? where userCategory = 1 and User_idUser like ";
	public static final String STRING_FOR_INSERT_ONE_POINT_TO_USER_DETAILS = "UPDATE USER_DETAILS SET ";
	public static final String STRING_FOR_INSERT_INFO_TO_USER_DETAILS_LAST_PART = ";";
	public static final String STRING_FIND_COUNTRY_ID_BY_NAME = "select idCountry from countries where CountryName like \"";
	public static final String STRING_FOR_FIND_COUNTRY_LAST_PART = "\";";
	public static final int DEFAULT_VALUE_FOR_CLENT_CATEGORY = 1;
	public static final String STRING_CHECK_USER_ID_USER_CATEGORY = "select * from user_details where userCategory like 1 and User_idUser like ";
	public static final String STRING_CHECK_USER_ID_USER_CATEGORY_LAST_PART = ";";
	public static final String STRING_ADD_NEW_USER_DETAIL_IN_DB = "insert into user_details (User_idUser, userCategory) values (?, ?);";
	public static final String STRING_TAKE_INFO_FOR_ADMIN_GREATINGS = "select  ud.UserName, ud.UserThirdName, ud.UserSurname, ud.EnglishUserName, ud.EnglishUserSurname, u.userlogin, u.UserEmail, u.UserPhone from users u join user_details ud on ud.User_idUser=u.idUser where User_idUser =";
	public static final String STRING_ADD_NEW_GUEST = "insert into user_details (User_idUser, UserName, UserSurname, UserThirdName, EnglishUserName, EnglishUserSurname, UserPersonBirthDate, PasportNumber, UserPasportID, UserPasportAnotherInfo, Country_idCountry, userCategory) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

	
	private static final Logger logger = LogManager.getLogger(UserDetailDaoImpl.class);
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public UserDetail findUserDetails(int userId) throws DaoException {
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_USER_DETAILS_BY_USER_ID + userId + STRING_FIND_USER_DETAILS_BY_USER_ID_LAST_PART;

		UserDetail userDetail = new UserDetail();

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				userDetail.setId(set.getInt(1));
				userDetail.setFirstName(set.getString(3));
				userDetail.setSecondName(set.getString(4));
				userDetail.setThirdName(set.getString(5));
				userDetail.setFirstNameEnglish(set.getString(6));
				userDetail.setSecondNameEnglish(set.getString(7));
				userDetail.setBirthDate(set.getDate(8));
				userDetail.setPassportNumber(set.getString(9));
				userDetail.setPassportId(set.getString(10));
				userDetail.setPassportOtherInfo(set.getString(11));
				Country country = new Country(set.getInt(12));
				userDetail.setCountry(country);

				ClientCategory category = new ClientCategory(set.getInt(13));
				userDetail.setCategory(category);

			}

		} catch (SQLException e) {
			logger.error("Error in finding userDetails", e);
			throw new DaoException("Error in finding userDetails ", e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return userDetail;
	}

	@Override
	public void updateUserDetails(UserDetail userDetail) throws DaoException {
		int userId = userDetail.getUserId();
		
		UserDetail detail = findUserDetails(userId);
		
		if (detail != null) {
			updateDetails(userDetail);
		} else {
			addNewUserDetails(userId);
			updateDetails(userDetail);
		}
	}

	/**
	 * add new user detail to magichotel.user_details
	 * 
	 * @param userId - userDetailId
	 * @throws DaoException {@link DaoException}
	 */
	private void updateDetails(UserDetail userDetail) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		
		int userId = userDetail.getUserId();
		// find country id to add this info to DB
		Country country = userDetail.getCountry();
		int countryId = findCountryId(country);
		country.setId(countryId);
		userDetail.setCountry(country);
	
		try {	
			
			connection = connectionPool.takeConnection();

			String sql = STRING_FOR_INSERT_INFO_TO_USER_DETAILS + userId
					+ STRING_FOR_INSERT_INFO_TO_USER_DETAILS_LAST_PART;

			ps = connection.prepareStatement(sql);

			ps.setString(1, userDetail.getFirstName());
			ps.setString(2, userDetail.getSecondName());
			ps.setString(3, userDetail.getThirdName());
			ps.setString(4, userDetail.getFirstNameEnglish());
			ps.setString(5, userDetail.getSecondNameEnglish());
			ps.setObject(6, userDetail.getBirthDate());
			ps.setString(7, userDetail.getPassportNumber());
			ps.setString(8, userDetail.getPassportId());
			ps.setString(9, userDetail.getPassportOtherInfo());
			ps.setInt(10, userDetail.getCountry().getId());

			ps.executeUpdate();


		} catch (SQLException e) {
			logger.error("Error in updating user_details", e);
			throw new DaoException("Error in updating user_details", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
	}
	/**
	 * find id of object class Country which usesed for creating user_details
	 * @param country object class Country
	 * @return int Ciuntry Id
	 * @throws DaoException
	 */
	private int findCountryId(Country country) throws DaoException {
		int countryId = 0;
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_FIND_COUNTRY_ID_BY_NAME + country.getName() + STRING_FOR_FIND_COUNTRY_LAST_PART;

			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				countryId = set.getInt(1);

			}

		} catch (SQLException e) {
			logger.error("Error in finding countryId by countryName ", e);
			throw new DaoException("Error in finding countryId by countryName ", e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return countryId;
	}

	@Override
	public void addNewUserDetails(int userId) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		Statement statement = null;
		ResultSet set = null;

		boolean resultOfChechingExistingUserDetail = true;

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_CHECK_USER_ID_USER_CATEGORY + userId + STRING_CHECK_USER_ID_USER_CATEGORY_LAST_PART;

			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				resultOfChechingExistingUserDetail = false;
			}

			if (resultOfChechingExistingUserDetail) {

				sql = STRING_ADD_NEW_USER_DETAIL_IN_DB;

				ps = connection.prepareStatement(sql);

				ps.setInt(1, userId);
				ps.setInt(2, DEFAULT_VALUE_FOR_CLENT_CATEGORY);

				ps.executeUpdate();
			}

		} catch (SQLException e) {
			logger.error("Error in adding new user_details", e);
			throw new DaoException("Error in adding new user_details", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}

	}

	@Override
	public String findClientDetails(int clientId) throws DaoException {
		String clientInfo = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_TAKE_INFO_FOR_ADMIN_GREATINGS + clientId + STRING_CHECK_USER_ID_USER_CATEGORY_LAST_PART;

			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				String name = set.getString(1);
				String thirdName = set.getString(2);
				String secondName = set.getString(3);
				String englishName = set.getString(4);
				String englishSurname = set.getString(5);
				String login = set.getString(6);
				String email = set.getString(7);
				String phone = set.getString(8);

				clientInfo += name + " " + thirdName + " " + secondName + " " + englishName + " " + englishSurname + " "
						+ login + " " + email + " " + phone;

			}

		} catch (SQLException e) {
			logger.error("Error in finding countryId by countryName", e);
			throw new DaoException("Error in finding countryId by countryName ", e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return clientInfo;
	}

	@Override
	public void addNewGuest(UserDetail userDetail) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		// find country id to add this info to DB
		Country country = userDetail.getCountry();
		int countryId = findCountryId(country);
		country.setId(countryId);
		userDetail.setCountry(country);

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_ADD_NEW_GUEST;

			ps = connection.prepareStatement(sql);

			ps.setInt(1, userDetail.getUserId());
			ps.setString(2, userDetail.getFirstName());
			ps.setString(3, userDetail.getSecondName());
			ps.setString(4, userDetail.getThirdName());
			ps.setString(5, userDetail.getFirstNameEnglish());
			ps.setString(6, userDetail.getSecondNameEnglish());
			ps.setObject(7, userDetail.getBirthDate());
			ps.setString(8, userDetail.getPassportNumber());
			ps.setString(9, userDetail.getPassportId());
			ps.setString(10, userDetail.getPassportOtherInfo());
			ps.setInt(11, userDetail.getCountry().getId());
			ps.setInt(12, userDetail.getCategory().getClientCategoryId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error in adding new  guest", e);
			throw new DaoException("Error in adding new  guest", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
		
	}

}
