package by.epamtc.jwd2020.dziadkouskaya.service;

import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;

public interface RoomCategoryService {
	public List<Integer> createRoomCategoryList() throws ServiceException;
	public List<RoomCategory> createFullRoomCategoryList() throws ServiceException;
	public RoomCategory findRoomCategoryByName(String categoryName) throws ServiceException;

}
