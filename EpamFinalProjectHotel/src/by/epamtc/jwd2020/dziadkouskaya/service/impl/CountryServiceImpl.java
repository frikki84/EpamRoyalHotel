package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.dao.CountryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.CountryDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class CountryServiceImpl implements CountryService {

	private final DaoProvider daoProvider = DaoProvider.getInstance();

	@Override
	public List<Country> findCountryList() throws ServiceException {
		CountryDao countryDao = daoProvider.getCountryDao();
		
		List<Country> resultList = null;

		try {
			resultList = countryDao.findCountryList();
			
		} catch (DaoException e) {
			throw new ServiceException("Error in finding CountryList", e);
		}

		return resultList;
	}
	
	@Override
	public int findCountryId(String countryName) throws ServiceException {
		CountryDao countryDao = daoProvider.getCountryDao();
		
		int resultId = 0;

		try {
			resultId = countryDao.findCountryId(countryName);
			
		} catch (DaoException e) {
			throw new ServiceException("Error in finding CountryId from CountryName", e);
		}

		return resultId;
	}

}
