package by.epamtc.jwd2020.dziadkouskaya.dao;

/**
 * interface work with table magichotel.users
 * @author Yana Dziadkouskaya
 */

import by.epamtc.jwd2020.dziadkouskaya.bean.User;

public interface UserDao {
	/**
	 * add new note to magichotel.users with information about new user
	 * 
	 * @param user
	 * @throws DaoException {@link DaoException}
	 */

	public void addUser(User user) throws DaoException;

	public boolean checkLogin(String login) throws DaoException;
	
	public boolean checkEmail(String email) throws DaoException;
	
	public boolean checkPhone(String phone) throws DaoException;
	
	public boolean checkPassword(String login, String password) throws DaoException;
	
	/**
	 * find user id by given user's login
	 * @param login String with login
	 * @return int of user's id
	 * @throws DaoException  {@link DaoException}
	 */
	public int findUserIdCode(String login) throws DaoException;
	/**
	 * find user's role name from magichotel.roles
	 * @param login  String with user's login from magichotel.users
	 * @return String name of user's role
	 * @throws DaoException {@link DaoException}
	 */

	public String findUserRole(String login) throws DaoException;
	/**
	 * find user login by given user's id
	 * @param int id 
	 * @return String user's login
	 * @throws DaoException  {@link DaoException}
	 */

	public String findUserLogin(int id) throws DaoException;
	/**
	 * finds the user's email and phone number from the magichotel.users by id
	 * @param id int user's id
	 * @return object of class User with id, phone, email
	 * @throws DaoException {@link DaoException}
	 */

	public User takePhoneEmailFromDb(int id) throws DaoException;
	/**
	 * update email and phone number in the magichotel.users in nessecary user
	 * @param user object of class User with right email and phone
	 * @throws DaoException {@link DaoException}
	 */

	public void updateEmailPhone(User user) throws DaoException;
	/**
	 * delete user with given id from magichotel.users
	 * @param userId value of user's id 
	 * @throws DaoException{@link DaoException}
	 */

	public void deleteUser(int userId) throws DaoException;

	
	/**
	 * find user's password from magichotel.users by given user's id 
	 * @param idUser int user's id
	 * @return String with user's password
	 * @throws DaoException {@link DaoException}
	 */

	public String findPasswordByUserId(int idUser) throws DaoException;
	/**
	 * update user's password 
	 * @param idUser given user's id
	 * @param newPassword String with new password
	 * @throws DaoException {@link DaoException}
	 */

	public void updatePassword(int idUser, String newPassword) throws DaoException;

}
