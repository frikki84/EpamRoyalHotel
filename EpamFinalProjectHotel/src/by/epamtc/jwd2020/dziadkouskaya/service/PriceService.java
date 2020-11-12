package by.epamtc.jwd2020.dziadkouskaya.service;

import java.util.List;
import java.util.Map;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;

public interface PriceService {
	public List<RoomCategoryPrice> findAllRoomPrices() throws ServiceException;
	public void deletePrice(int idPrice) throws ServiceException;
	public void addNewRoomCategoryPrice(RoomCategoryPrice price) throws ServiceException;
	public Map<String, Double> findPriceForGivenCategoryes(List<RoomCategory> categoryList) throws ServiceException;

}
