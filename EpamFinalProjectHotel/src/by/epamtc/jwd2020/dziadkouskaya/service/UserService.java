package by.epamtc.jwd2020.dziadkouskaya.service;

import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;


public interface UserService {
	public boolean checkNewUserPassword(String password, String repeatPassword);
	public String checkInfoFromDB(User user) throws ServiceException;
	public void addNewUser(User user) throws ServiceException;
	public String checkUserForLogination(String login, String password) throws ServiceException;
	public int findUserCode(String login) throws ServiceException;
	public String findUserRole(String login) throws ServiceException;
	public String findUserLoginById(int id) throws ServiceException;
	public User takePhoneEmailFromDb(int id) throws ServiceException;
	public void updateEmailPhone(User user) throws ServiceException;
	public void deleteUser(int idUser) throws ServiceException;	
	public String checkClient(String login) throws ServiceException;
	public String findPasswordByUserId(int idUser) throws ServiceException;
	public void updatePassword(int idUser, String newPassword) throws ServiceException;
	
	
	
	

	
}
