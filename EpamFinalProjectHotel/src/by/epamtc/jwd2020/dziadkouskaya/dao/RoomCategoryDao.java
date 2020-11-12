package by.epamtc.jwd2020.dziadkouskaya.dao;

import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;

/**
 * interface work with table magichotel.room_category
 * 
 * @author Yana Dziadkouskaya
 *
 */
public interface RoomCategoryDao {
	/**
	 * create List of all id from magichotel.room_category
	 * 
	 * @return List of Integers
	 * @throws DaoException {@link DaoException}
	 */
	public List<Integer> createRoomCategoryList() throws DaoException;

	/**
	 * create List of all notes from magichotel.room_category
	 * 
	 * @return List of RoomCategory objects
	 * @throws DaoException {@link DaoException}
	 */
	public List<RoomCategory> createFullRoomCategoryList() throws DaoException;
	
	public RoomCategory findRoomCategoryByName(String categoryName) throws DaoException;

}
