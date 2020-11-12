package by.epamtc.jwd2020.dziadkouskaya.dao;

import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;


/**
 * interface work with table magichotel.room_price
 * @author Yana Dziadkouskaya
 */

public interface RoomCategoryPriceDao {
	/**
	 * create List of all  RoomCategoryPrice objects from magichotel.room_price
	 * @return List of all  RoomCategoryPrice objects
	 * @throws DaoException {@link DaoException}
	 */
	
	public List<RoomCategoryPrice> findAllRoomPrices() throws DaoException;
	/**
	 * delete note from magichotel.room_price by giving id
	 * @param idPrice - idPrice
	 * @throws DaoException {@link DaoException}
	 */
	
	public void deletePrice(int idPrice) throws DaoException;
	/**
	 *add new room_price note to magichotel.room_price
	 * @param price
	 * @throws DaoException {@link DaoException}
	 */
	
	public void addNewRoomCategoryPrice(RoomCategoryPrice price) throws DaoException;
	

}
