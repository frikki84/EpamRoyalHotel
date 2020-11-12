package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.UserDetailDao;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class UserDetailServiceImpl implements UserdetailService {
	private static DaoProvider daoProvider = DaoProvider.getInstance();
	UserDetailDao dao = daoProvider.getUserDetailDao();

	@Override
	public UserDetail findUserDetails(int userId) throws ServiceException {
		UserDetail userDetail = null;

		try {
			userDetail = dao.findUserDetails(userId);

		} catch (DaoException e) {
			throw new ServiceException("Error in finding userDetails " + e);
		}

		return userDetail;
	}
	
	
	@Override
	public void updateUserDetails(UserDetail userDetail) throws ServiceException {
		
		try {
			dao.updateUserDetails(userDetail);
			//System.out.println("service update user detail");

		} catch (DaoException e) {
			new ServiceException("Error in updating userdetails ", e);
		}

	}

	@Override
	public void addNewUserDetails(int userId) throws ServiceException {
		try {
			dao.addNewUserDetails(userId);

		} catch (DaoException e) {
			
			new ServiceException("Error in adding new  userdetails ", e);
		}
		
	}

	@Override
	public String findClientDetails(int clientId) throws ServiceException {
		String clientInfo = "";
		try {
			clientInfo = dao.findClientDetails(clientId);
		} catch (DaoException e) {
			throw new ServiceException("Error in info about client", e);
		}
		return clientInfo;
	}

	@Override
	public void addNewGuest(UserDetail userDetail) throws ServiceException {
		ClientCategory clientCategory = new ClientCategory(2, "Клиент с заказчиком");
		userDetail.setCategory(clientCategory);
		
		try {
			dao.addNewGuest(userDetail);
		} catch (DaoException e) {
			throw new ServiceException("Error in adding new guest", e);
		}
		
	}
}
