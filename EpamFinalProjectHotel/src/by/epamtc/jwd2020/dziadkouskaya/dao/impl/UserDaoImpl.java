package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

/**
 * class UserDaoImpl is implementation of interface UserDao, 
 * make all operations with magichotel.users table
 * @author Yana Dziadkouskaya
 *
 */
public class UserDaoImpl implements UserDao {

	public static final String STRING_FOR_INSERT_INFO_TO_USERS = "Insert into USERS(idUser, userLogin, userPassword, userEmail, userPhone, UserCategory_idUserCategory, UserActivity) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String LAST_PART_FOR_SELECT_STATEMENT = "\";";
	public static final String STRING_CHECK_USER_LOGIN = "select * from users where UserLogin like \"";
	public static final String STRING_CHECK_USER_LOGIN2 = "\" and UserActivity = 1;";
	public static final String STRING_CHECK_USER_EMAIL = "select * from users where UserEmail like \"";
	public static final String STRING_CHECK_USER_PHONE = "select * from users where UserPhone like \"";
	public static final String STRING_FIND_USER_CATEGORY = "select r.UserCategoryName\r\n"
			+ "from users u join roles r on u.UserCategory_idUserCategory = r.idusercategory\r\n"
			+ "where u.UserLogin like\"";
	public static final String STRING_CHECK_USER_PASSWORD_LOGIN = "select * from users where UserLogin like \"";
	public static final String STRING_CHECK_USER_PASSWORD_EMAIL = "\" or UserEmail like \"";
	public static final String STRING_CHECK_USER_PASSWORD_PHONE = "\" or UserPhone like \"";
	public static final String STRING_FIND_LOGIN_BY_ID = "select UserLogin from users where idUser like ";
	public static final String STRING_FIND_PHONE_EMAIL_BY_ID = "select UserEmail, UserPhone from users where idUser = ";
	public static final String STRING_FIND_PHONE_EMAIL_BY_ID_LAST_PART = ";";
	public static final String STRING_TO_UPDATE_EMAIL_PHONE = "update USERS set userEmail = ?, userPhone = ?  where idUser = ";
	public static final String STRING_DELETE_USER = "Update users set UserActivity = 2 where idUser = ";
	public static final String STRING_DELETE_USER2 =";";
	public static final String STRING_FIND_PASSWORD = "	select UserPassword from users where idUser =";
	public static final String STRING_UPDATE_PASWORD = "update users set UserPassword = ? where idUser=";
	
	/**
	 * {@link ConnectionPool}
	 */
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	private final ConnectionPool connectionPool = ConnectionPool.getInstance();


	@Override
	public void addUser(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(STRING_FOR_INSERT_INFO_TO_USERS);

			ps.setInt(1, user.getUserId());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setInt(6, user.getRole().getRoleId());
			ps.setInt(7,  user.getActivity().getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error in adding info to USERS", e);
			throw new DaoException("Error in adding info to USERS", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
	}

	@Override
	public String checkUser(User user) throws DaoException {
		String login = user.getLogin();
		String email = user.getEmail();
		String phone = user.getPhone();

		String result = "";

		boolean loginCheck = checkLogin(login);
		boolean emailCheck = checkEmail(email);
		boolean phoneCheck = checkPhone(phone);

		if (loginCheck) {
			result = "This login already exists. Please change your login.";

		} else if (emailCheck) {

			result = "This email has already been used for registration. Please use another email.";

		} else if (phoneCheck) {
			result = "This phone number has already been used for registration. Please use another phone number.";
		}

		return result;

	}

	@Override
	public String checkUserInLogination(String login, String password) throws DaoException {

		boolean loginCheck = checkLogin(login);
		boolean emailCheck = checkEmail(login);
		boolean phoneCheck = checkPhone(login);

		String result = "This username / email / phone number is not registered in the system";

		if (loginCheck || emailCheck || phoneCheck) {

			boolean passwordCheck = checkPassword(login, password);

			if (!passwordCheck) {
				result = "Wrong password. Please, repeat";
			} else {
				result = "";
			}

		}

		return result;
	}
	
	@Override
	public String checkClient(String login) throws DaoException {

		boolean loginCheck = checkLogin(login);
		boolean emailCheck = checkEmail(login);
		boolean phoneCheck = checkPhone(login);

		String result = "This username / email / phone number is not registered in the system";

		if (loginCheck || emailCheck || phoneCheck) {		
				result = "";
		}

		return result;
	}
	
	@Override
	public String findPasswordByUserId(int idUser) throws DaoException {
		String password = "";
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_PASSWORD + idUser + STRING_DELETE_USER2;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				password = set.getString(1);
			}

		} catch (SQLException e) {
			logger.error("Error in finding password", e);
			throw new DaoException("Error in finding password " + e);
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		
		return password;
	}
	
	
	@Override
	public void updatePassword(int idUser, String newPassword) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
 		try {
			connection = connectionPool.takeConnection();
			
			String sql = STRING_UPDATE_PASWORD + idUser+ STRING_DELETE_USER2;
					
			ps = connection.prepareStatement(sql);

			ps.setString(1, newPassword);
			

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error in updating password", e);
			throw new DaoException("Error in updating password", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
		
	}
	/**
	 * checks if the password was entered by the user with the given login, or email, or phone number
	 * @param login user's login or email or phone
	 * @param password user's password
	 * @return is password right or not
	 * @@throws DaoException {@link DaoException}
	 */
	private boolean checkPassword(String login, String password) throws DaoException {
		boolean result = false;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_CHECK_USER_PASSWORD_LOGIN + login + STRING_CHECK_USER_PASSWORD_EMAIL + login
				+ STRING_CHECK_USER_PASSWORD_PHONE + login + LAST_PART_FOR_SELECT_STATEMENT;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				String givingPassword = set.getString(3);

				if (password.equals(givingPassword)) {

					result = true;
				}
			}

		} catch (SQLException e) {
			logger.error("Error in checkin password ", e);
			throw new DaoException("Error in checkin password " + e);
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return result;
	}

	@Override
	public int findUserIdCode(String login) throws DaoException {

		int userId = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_CHECK_USER_PASSWORD_LOGIN + login + STRING_CHECK_USER_PASSWORD_EMAIL + login + STRING_CHECK_USER_PASSWORD_PHONE + login + LAST_PART_FOR_SELECT_STATEMENT;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				userId = set.getInt(1);
			}

		} catch (SQLException e) {
			logger.error("Error in finding userCode ", e);
			throw new DaoException("Error in finding userCode " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return userId;

	}

	@Override
	public String findUserRole(String login) throws DaoException {
		String roleName = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_USER_CATEGORY + login + LAST_PART_FOR_SELECT_STATEMENT;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				roleName = set.getNString(1);

			}

		} catch (SQLException e) {
			logger.error("Error in finding userRole ", e);
			throw new DaoException("Error in finding userRole ",  e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		return roleName;
	}

	@Override
	public String findUserLogin(int id) throws DaoException {
		String login = "";

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_LOGIN_BY_ID + id;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				login = set.getNString(1);

			}

		} catch (SQLException e) {
			logger.error("Error in finding userLogin", e);
			throw new DaoException("Error in finding userLogin " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		return login;
	}
	
	
	@Override
	public User takePhoneEmailFromDb(int id) throws DaoException {
		User user = new User();
		
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_PHONE_EMAIL_BY_ID+ id + STRING_FIND_PHONE_EMAIL_BY_ID_LAST_PART;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			String email = "";
			String phone = "";
			
			if (set.next()) {
				email = set.getString(1);
				phone = set.getString(2);

			}
			user.setUserId(id);
			user.setEmail(email);
			user.setPhone(phone);
			

		} catch (SQLException e) {
			logger.error("Error in finding email/phone", e);
			throw new DaoException("Error in finding email/phone " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}
		return user;
	}
	
	@Override
	public void updateEmailPhone(User user) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
 

		try {
			connection = connectionPool.takeConnection();
			int id = user.getUserId();

			String sql = STRING_TO_UPDATE_EMAIL_PHONE + id + STRING_FIND_PHONE_EMAIL_BY_ID_LAST_PART;
					
			ps = connection.prepareStatement(sql);

			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPhone());
			

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.error("Error in updating user phone/email", e);
			throw new DaoException("Error in updating user phone/email", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
		
	}
	
	

	@Override
	public void deleteUser(int userId) throws DaoException {
		Connection connection = null;
		Statement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.createStatement();

			String sql = STRING_DELETE_USER + userId + STRING_DELETE_USER2;
	

			ps.executeUpdate(sql);

		} catch (SQLException e) {
			logger.error("Error in deleting user", e);
			throw new DaoException("Error in deleting user", e);
		}

		finally {

			connectionPool.closeConnection(connection, ps);

		}
	}
/**
 * checks during logination if a user with such login exists in the magichotel.users table
 * @param login user's login
 * @return does such user's login exist
 * @throws DaoException {@link DaoException}
 */
	private boolean checkLogin(String login) throws DaoException {
		boolean result = false;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_CHECK_USER_LOGIN + login + STRING_CHECK_USER_LOGIN2;
		
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error("Error in checkin login ", e);
			throw new DaoException("Error in checkin login " + e);
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return result;
	}
	
	/**
	 * checks during logination if a user with such email exists in the magichotel.users table
	 * @param login user's login
	 * @return does such user's login exist
	 * @throws DaoException {@link DaoException}
	 */

	private boolean checkEmail(String email) throws DaoException {
		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_CHECK_USER_EMAIL + email + LAST_PART_FOR_SELECT_STATEMENT;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);
			if (email != "" && set.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error("Error in checkin email ", e);
			throw new DaoException("Error in checkin email " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return result;
	}

	/**
	 * checks during logination if a user with such phone exists in the magichotel.users table
	 * @param login user's login
	 * @return does such user's login exist
	 * @throws DaoException {@link DaoException}
	 */
	private boolean checkPhone(String phone) throws DaoException {
		boolean result = false;
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_CHECK_USER_PHONE + phone + LAST_PART_FOR_SELECT_STATEMENT;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);
			if (phone != "" && set.next()) {
				result = true;
			}

		} catch (SQLException e) {
			logger.error("Error in checkin phone", e);
			throw new DaoException("Error in checkin phone " + e);
		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return result;
	}

	

}
