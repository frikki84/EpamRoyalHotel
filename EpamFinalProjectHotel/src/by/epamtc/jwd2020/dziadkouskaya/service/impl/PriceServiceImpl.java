package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.RoomCategoryPriceDao;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class PriceServiceImpl implements PriceService {
	public static final Date DEFAULT_DATE_TO_COMPARE_FOR_MAIN_PAGE = Date.valueOf("2020-01-01");

	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private RoomCategoryPriceDao priceDao = daoProvider.getPriceDao();

	@Override
	public List<RoomCategoryPrice> findAllRoomPrices() throws ServiceException {
		List<RoomCategoryPrice> resultList = null;
		try {
			resultList = priceDao.findAllRoomPrices();
		} catch (DaoException e) {
			throw new ServiceException("Error in finding prices", e);
		}

		return resultList;
	}

	@Override
	public void deletePrice(int idPrice) throws ServiceException {
		try {
			priceDao.deletePrice(idPrice);
		} catch (DaoException e) {
			throw new ServiceException("Error in deleting prices", e);
		}

	}

	@Override
	public void addNewRoomCategoryPrice(RoomCategoryPrice price) throws ServiceException {
		try {
			priceDao.addNewRoomCategoryPrice(price);
		} catch (DaoException e) {
			throw new ServiceException("Error in adding prices", e);
		}

	}

	public Map<String, Double> findPriceForGivenCategoryes(List<RoomCategory> categoryList) throws ServiceException {
		Map<String, Double> resultMap = new HashMap<String, Double>();

		List<RoomCategoryPrice> priceList = null;
		Date now = Date.valueOf(LocalDate.now());
		try {
			priceList = priceDao.findAllRoomPrices();

			double tax = 0;

			for (RoomCategory roomCategory : categoryList) {
				for (RoomCategoryPrice price : priceList) {
					if (roomCategory.getRoomCategoryName()
							.equalsIgnoreCase(price.getRoomCategory().getRoomCategoryName())) {
						if (now.compareTo(price.getStartDate()) >= 0) {
							tax = price.getPricePerDay();
						}
					}
				}
				resultMap.put(roomCategory.getRoomCategoryName(), tax);
			}

		} catch (DaoException e) {
			throw new ServiceException("Error finding info for main page", e);
		}

		return resultMap;
	}

}
