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

	/**
	 * check before registration user's login, email or phone
	 * 
	 * @param user 
	 * @return a string with information about whether a user with such a login,
	 * email or phone number exists in the database or not
	 * @throws DaoException {@link DaoException}
	 */
	public String checkUser(User user) throws DaoException;
	/**
	 * checks during logination if a user exists in the database table with such a login, 
	 * email or phone number, then checks the correctness of the entered password
	 * @param login - String with login or email or phone
	 * @param password - String with password
	 * @return a string with information about whether a user with such a login,
	 * email or phone number exists in the database and is entered password right
	 * @throws DaoException  {@link DaoException}
	 */

	public String checkUserInLogination(String login, String password) throws DaoException;

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
	 * the administrator checks if the client exists with a login, email or phone numbers in the database
	 * @param login String client's login, email, phone
	 * @return String with information about existing user
	 * @throws DaoException{@link DaoException}
	 */
	public String checkClient(String login) throws DaoException;
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
