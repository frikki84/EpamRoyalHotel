package by.epamtc.jwd2020.dziadkouskaya.dao;

import java.util.List;

/**
 * interface work with coutries from magichotel.countries
 * @author Yana Dziadkouskaya
 *
 */

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;

public interface CountryDao {

	/**
	 * create List of all notes from magichotel.countries
	 * 
	 * @return List of Country objects
	 * @throws DaoException {@link DaoException}
	 */
	public List<Country> findCountryList() throws DaoException;
	
	/**
	 * find Country id by its name
	 * @param countryName
	 * @return int countryId
	 * @throws DaoException  {@link DaoException}
	 */
	public int findCountryId(String countryName) throws DaoException;

}
