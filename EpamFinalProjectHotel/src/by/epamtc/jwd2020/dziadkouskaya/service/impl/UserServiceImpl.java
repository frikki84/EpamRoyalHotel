package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDao;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;

public class UserServiceImpl implements UserService {
	private static final DaoProvider daoProvider = DaoProvider.getInstance();
	UserDao userDao = daoProvider.getUserDao();

	@Override
	public String checkInfoFromDB(User user) throws ServiceException {
		String result = "";

		//UserDao userDao = daoProvider.getUserDao();

		try {
			result = userDao.checkUser(user);

		} catch (DaoException e) {
			throw new ServiceException(e);
		}

		return result;
	}

	@Override
	public String checkUserForLogination(String login, String password) throws ServiceException {
		String result = "";

		try {
			result = userDao.checkUserInLogination(login, password);

		} catch (DaoException e) {
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
			throw new ServiceException("Error in adding new user to DB", e);
		}

	}

	@Override
	public int findUserCode(String login) throws ServiceException {
		int result = 0;

		try {
			result = userDao.findUserIdCode(login);

		} catch (DaoException e) {
			throw new ServiceException("Error in finding user code", e);
		}

		return result;
	}

	public String findUserRole(String login) throws ServiceException {
		String result = "";

		try {
			result = userDao.findUserRole(login);

		} catch (DaoException e) {
			throw new ServiceException("Error in finding user role", e);
		}

		return result;

	}

	public String findUserLoginById(int id) throws ServiceException {
		
		String result = "";

		try {
			result = userDao.findUserLogin(id);

		} catch (DaoException e) {
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
			throw new ServiceException("Error in finding user email/phone", e);
		}
		return user;
	}

	@Override
	public void updateEmailPhone(User user) throws ServiceException {
		
		try {
			userDao.updateEmailPhone(user);
		} catch (DaoException e) {
			throw new ServiceException("Error in updating user email/phone", e);
		}
		
	}

	@Override
	public void deleteUser(int idUser) throws ServiceException {

		try {
			userDao.deleteUser(idUser);;
		} catch (DaoException e) {
			throw new ServiceException("Error in user delete", e);
		}
		
	}

	@Override
	public String checkClient(String login) throws ServiceException {
		String resultString = null;
		
		try {
			resultString = userDao.checkClient(login);
		} catch (DaoException e) {
		throw new ServiceException("Error in check-in clients", e);
		}
		
		return resultString;
	}

	@Override
	public String findPasswordByUserId(int idUser) throws ServiceException {
		String password = "";
		
		try {
			password = userDao.findPasswordByUserId(idUser);
		} catch (DaoException e) {
			throw new ServiceException("Error in finding password", e);
		}
		return password;
	}

	@Override
	public void updatePassword(int idUser, String newPassword) throws ServiceException {
		try {
			userDao.updatePassword(idUser, newPassword);
		} catch (DaoException e) {
			throw new ServiceException("Error in updating password", e);
		}
		
	}
}
