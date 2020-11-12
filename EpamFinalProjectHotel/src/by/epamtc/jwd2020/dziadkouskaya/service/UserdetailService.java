package by.epamtc.jwd2020.dziadkouskaya.service;

import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;

public interface UserdetailService {
	public UserDetail findUserDetails(int userId) throws ServiceException;
	public void updateUserDetails(UserDetail userDetail) throws ServiceException;
	public void addNewUserDetails(int userId) throws ServiceException;
	public String findClientDetails(int clientId) throws ServiceException;
	public void addNewGuest(UserDetail userDetail) throws ServiceException;
	

}
