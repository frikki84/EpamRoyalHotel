package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserActivity;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;

public class UserDaoImplTest {
	public static final String STRING_FOR_INSERT_INFO_TO_USERS = "Insert into USERS(idUser, userLogin, userPassword, userEmail, userPhone, UserCategory_idUserCategory, UserActivity) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String STRING_CHECK_USER_ID = "select * from users where UserLogin like \"";
	public static final String STRING_CHECK_USER_ID2 = "\";";
	public static final String STRING_DELETE_USER = "Update users set UserActivity = 2 where idUser = ";
	public static final String STRING_DELETE_USER2 = ";";
	public static final String STRING_CHECK_DELETED_USER_ID = "select * from users where UserActivity = 2 and UserLogin like \"";
	public static final String STRING_CHECK_DELETED_USER_ID2 = "\";";
	public static final String STRING_CHECK_USER_LOGIN = "select * from users where UserLogin like \"";
	public static final String STRING_CHECK_USER_LOGIN2 = "\" and UserActivity = 1;";
	public static final String STRING_CHECK_USER_PASSWORD_LOGIN = "select * from users where UserLogin like \"";
	public static final String STRING_CHECK_USER_PASSWORD_EMAIL = "\" or UserEmail like \"";
	public static final String STRING_CHECK_USER_PASSWORD_PHONE = "\" or UserPhone like \"";

	public static final ConnectionPoolTest connectionPoolTest = ConnectionPoolTest.getInstance();
	public static final ConnectionPool connectionPool = ConnectionPool.getInstance();

	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private UserDao userDao = daoProvider.getUserDao();

	@After
	public void disconnectDB() {
		connectionPool.dropAllConnections();
		connectionPoolTest.dropAllConnections();
	}

	@Test
	public void addUser() throws DaoException, SQLException {
		User user = new User("ioio", "159753", "ioio@ya.ru", new Role(4), new UserActivity(1));

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet set = null;
		Statement statement = null;

		connection = connectionPoolTest.takeConnection();

		ps = connection.prepareStatement(STRING_FOR_INSERT_INFO_TO_USERS);
		ps.setInt(1, user.getUserId());
		ps.setString(2, user.getLogin());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getEmail());
		ps.setString(5, user.getPhone());
		ps.setInt(6, user.getRole().getRoleId());
		ps.setInt(7, user.getActivity().getId());
		ps.executeUpdate();

		String sql = STRING_CHECK_USER_ID + user.getLogin() + STRING_CHECK_USER_ID2;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		int testID = 0;

		if (set.next()) {
			testID = set.getInt(1);
		}

		userDao.addUser(user);
		int expectedId = userDao.findUserIdCode(user.getLogin());

		assertEquals(expectedId, testID);

	}

	@Test
	public void deleteUser() throws DaoException, SQLException {
		int userId = 91;
		String userName = "ioio";

		Connection connection = null;
		Statement ps = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		ps = connection.createStatement();
		String sql = STRING_DELETE_USER + userId + STRING_DELETE_USER2;
		ps.executeUpdate(sql);

		String sqlNext = STRING_CHECK_USER_ID + userId + STRING_CHECK_USER_ID2;
		set = ps.executeQuery(sqlNext);

		int testID = 0;

		if (set.next()) {
			testID = set.getInt(1);
		}

		userDao.deleteUser(userId);

		int expectedId = userDao.findUserIdCode(userName);

		assertEquals(expectedId, testID);

	}

	@Test
	public void checkLogin() throws DaoException, SQLException {
		String login = "hyhy";
		boolean result = false;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_CHECK_USER_LOGIN + login + STRING_CHECK_USER_LOGIN2;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		if (set.next()) {
			result = true;
		}

		boolean expectedResult = userDao.checkLogin(login);

		assertEquals(expectedResult, result);

	}
	
	@Test
	public void checkPassword() throws DaoException, SQLException {
		String email = "rety@gmail.com";
		String password = "159";
		boolean result = false;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPoolTest.takeConnection();

		String sql = STRING_CHECK_USER_PASSWORD_LOGIN + email + STRING_CHECK_USER_PASSWORD_EMAIL + email + STRING_CHECK_USER_PASSWORD_PHONE + email + STRING_CHECK_USER_ID2;

		statement = connection.createStatement();
		set = statement.executeQuery(sql);

		if (set.next()) {
			String givingPassword = set.getString(3);

			if (password.equals(givingPassword)) {
				result = true;
			}
		}

		boolean expectedResult = userDao.checkPassword(email, password);

		assertEquals(expectedResult, result);

	}

}
