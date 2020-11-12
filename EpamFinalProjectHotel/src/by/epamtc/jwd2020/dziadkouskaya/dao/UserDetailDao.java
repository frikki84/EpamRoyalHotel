package by.epamtc.jwd2020.dziadkouskaya.dao;

import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;

/**
 * interface work with table magichotel.user_details
 * 
 * @author Yana Dziadkouskaya
 */

public interface UserDetailDao {
	/**
	 * find note in magichotel.user_datails by given user id (from table
	 * magichotel.users)
	 * 
	 * @param userId user id
	 * @return object od UserDetail class
	 * @throws DaoException {@link DaoException}
	 */
	public UserDetail findUserDetails(int userId) throws DaoException;

	/**
	 * update userdetails by givin userDetailsID
	 * 
	 * @param userDetail UserDetail object with new information
	 * @throws DaoException {@link DaoException}
	 */
	public void updateUserDetails(UserDetail userDetail) throws DaoException;

	/**
	 * add new user detail to magichotel.user_details
	 * 
	 * @param userId - userDetailId
	 * @throws DaoException {@link DaoException}
	 */
	public void addNewUserDetails(int userId) throws DaoException;

	/**
	 * administrator searches for client's details by userdetailId
	 * 
	 * @param clientId
	 * @return String does client exist or not
	 * @throws DaoException {@link DaoException}
	 */
	public String findClientDetails(int clientId) throws DaoException;

	/**
	 * the administrator adds new user_details about a new client to the database, but this is
	 * not a customer, but a guest with whom the customer arrived
	 * 
	 * @param userDetail - object UserDetails with necessary guest information
	 * @throws DaoException {@link DaoException}
	 */
	public void addNewGuest(UserDetail userDetail) throws DaoException;

}
