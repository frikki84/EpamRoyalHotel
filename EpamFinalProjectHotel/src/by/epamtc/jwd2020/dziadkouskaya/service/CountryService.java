package by.epamtc.jwd2020.dziadkouskaya.service;

import java.util.List;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;


public interface CountryService {
	public List<Country> findCountryList() throws ServiceException;
	public int findCountryId(String countryName) throws ServiceException;
	

}
