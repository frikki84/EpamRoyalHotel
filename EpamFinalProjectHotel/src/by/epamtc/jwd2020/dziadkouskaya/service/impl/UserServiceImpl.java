package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.UserDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;

public class UserServiceImpl implements UserService {
	public static final String DEFAULT_MESSAGE_VALUE = "";
	public static final String MESSAGE_LOGIN_EXIST_IN_DB = "This login already exists. Please change your login.";
	public static final String MESSAGE_EMAIL_EXIST_IN_DB = "This email has already been used for registration. Please use another email.";
	public static final String MESSAGE_PHONE_EXIST_IN_DB = "This phone number has already been used for registration. Please use another phone number.";;
	public static final String MESSAGE_USER_LOGIN_INFO_NOT_IN_DB = "This username / email / phone number is not registered in the system";
	public static final String MESSAGE_WRONG_PASSWORD = "Wrong password. Please, repeat";

	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	private UserDao userDao = daoProvider.getUserDao();

	@Override
	public String checkInfoFromDB(User user) throws ServiceException {
		String login = user.getLogin();
		String email = user.getEmail();
		String phone = user.getPhone();

		String result = DEFAULT_MESSAGE_VALUE;

		try {
			boolean loginCheck = userDao.checkLogin(login);

			boolean emailCheck = userDao.checkEmail(email);
			boolean phoneCheck = userDao.checkPhone(phone);

			if (loginCheck) {
				result = MESSAGE_LOGIN_EXIST_IN_DB;

			} else if (emailCheck) {

				result = MESSAGE_EMAIL_EXIST_IN_DB;

			} else if (phoneCheck) {
				result = MESSAGE_PHONE_EXIST_IN_DB;
			}

		} catch (DaoException e) {
			logger.error("Error in checking user login/email/phone during registration", e);
			throw new ServiceException("Error in checking user login/email/phone during registration", e);
		}

		return result;
	}

	@Override
	public String checkUserForLogination(String login, String password) throws ServiceException {

		String result = DEFAULT_MESSAGE_VALUE;

		try {
			boolean loginCheck = userDao.checkLogin(login);
			boolean emailCheck = userDao.checkEmail(login);
			boolean phoneCheck = userDao.checkPhone(login);

			if (!(loginCheck || emailCheck || phoneCheck)) {
				result = MESSAGE_USER_LOGIN_INFO_NOT_IN_DB;

			} else {

				boolean passwordCheck = userDao.checkPassword(login, password);

				if (!passwordCheck) {
					result = MESSAGE_WRONG_PASSWORD;
				}
			}

		} catch (DaoException e) {
			logger.error("Error in checking user for logination", e);
			throw new ServiceException("Error in checking user for logination", e);
		}

		return result;
	}

	@Override
	public boolean checkNewUserPassword(String password, String repeatPassword) {
		boolean result = true;

		if (!password.equals(repeatPassword)) {
			result = false;
		}

		return result;
	}

	@Override
	public void addNewUser(User user) throws ServiceException {
		try {
			userDao.addUser(user);

		} catch (DaoException e) {
			logger.error("Error in adding new user to DB", e);
			throw new ServiceException("Error in adding new user to DB", e);
		}

	}

	@Override
	public int findUserCode(String login) throws ServiceException {
		int result = 0;

		try {
			result = userDao.findUserIdCode(login);
		} catch (DaoException e) {
			logger.error("Error in finding user code", e);
			throw new ServiceException("Error in finding user code", e);
		}

		return result;
	}

	public String findUserRole(String login) throws ServiceException {
		String result = DEFAULT_MESSAGE_VALUE;

		try {
			result = userDao.findUserRole(login);

		} catch (DaoException e) {
			logger.error("Error in finding user role", e);
			throw new ServiceException("Error in finding user role", e);
		}

		return result;

	}

	public String findUserLoginById(int id) throws ServiceException {

		String result = DEFAULT_MESSAGE_VALUE;

		try {
			result = userDao.findUserLogin(id);

		} catch (DaoException e) {
			logger.error("Error in finding user login", e);
			throw new ServiceException("Error in finding user login", e);
		}

		return result;

	}

	@Override
	public User takePhoneEmailFromDb(int id) throws ServiceException {
		User user = null;
		try {
			user = userDao.takePhoneEmailFromDb(id);

		} catch (DaoException e) {
			logger.error("Error in finding user email/phone", e);
			throw new ServiceException("Error in finding user email/phone", e);
		}
		return user;
	}

	@Override
	public void updateEmailPhone(User user) throws ServiceException {

		try {
			userDao.updateEmailPhone(user);
		} catch (DaoException e) {
			logger.error("Error in updating user email/phone", e);
			throw new ServiceException("Error in updating user email/phone", e);
		}

	}

	@Override
	public void deleteUser(int idUser) throws ServiceException {

		try {
			userDao.deleteUser(idUser);

		} catch (DaoException e) {
			logger.error("Error in user delete", e);
			throw new ServiceException("Error in user delete", e);
		}

	}

	@Override
	public String checkClient(String login) throws ServiceException {

		String result = DEFAULT_MESSAGE_VALUE;

		try {
			boolean loginCheck = userDao.checkLogin(login);
			boolean emailCheck = userDao.checkEmail(login);
			boolean phoneCheck = userDao.checkPhone(login);

			if (!loginCheck || !emailCheck || !phoneCheck) {
				result = MESSAGE_USER_LOGIN_INFO_NOT_IN_DB;
			}

		} catch (DaoException e) {
			logger.error("Error in checking user login/email/phone during registration", e);
			throw new ServiceException("Error in check-in clients", e);
		}

		return result;
	}

	@Override
	public String findPasswordByUserId(int idUser) throws ServiceException {
		String password = DEFAULT_MESSAGE_VALUE;

		try {
			password = userDao.findPasswordByUserId(idUser);

		} catch (DaoException e) {
			logger.error("Error in finding password", e);
			throw new ServiceException("Error in finding password", e);
		}
		return password;
	}

	@Override
	public void updatePassword(int idUser, String newPassword) throws ServiceException {
		try {
			userDao.updatePassword(idUser, newPassword);
		} catch (DaoException e) {
			logger.error("Error in updating password", e);
			throw new ServiceException("Error in updating password", e);
		}

	}
}
