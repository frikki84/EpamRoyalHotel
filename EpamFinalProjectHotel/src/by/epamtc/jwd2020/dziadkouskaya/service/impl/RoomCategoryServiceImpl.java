package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryDao;

import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class RoomCategoryServiceImpl implements RoomCategoryService {
	
	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private RoomCategoryDao dao = daoProvider.getRoomCategoryDao();
	
	@Override
	public List<Integer> createRoomCategoryList() throws ServiceException {
		List<Integer> roomCategoriCapasity = null;
		
		try {
			roomCategoriCapasity = dao.createRoomCategoryList();
		} catch (DaoException e) {
			throw new ServiceException("Error in finding roomCategoryCapacity", e);
			
		}
				
		return roomCategoriCapasity;
	}

	@Override
	public List<RoomCategory> createFullRoomCategoryList() throws ServiceException {
		List<RoomCategory> resultList  = null;
		try {
			resultList = dao.createFullRoomCategoryList();
		} catch (DaoException e) {
			throw new ServiceException("Error in finding roomCategoryNames", e);
		}
		return resultList;
	}

	@Override
	public RoomCategory findRoomCategoryByName(String categoryName) throws ServiceException {
		RoomCategory category = null;
				try {
			category = dao.findRoomCategoryByName(categoryName);
		} catch (DaoException e) {
			throw new ServiceException("Error infinding roomcategory by name", e);
		}
				
		return category;
	}

}
