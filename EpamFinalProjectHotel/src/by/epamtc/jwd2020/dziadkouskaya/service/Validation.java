package by.epamtc.jwd2020.dziadkouskaya.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Validation {

	public static boolean isDate(String date) throws ServiceException {
		boolean result = true;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

		try {
			dateFormat.parse(date);
		} catch (ParseException e) {

			return false;
		}

		return result;
	}

	public static String checkDates(Date startDate, Date endDate) throws ServiceException {
		String result = new String("");

		if (startDate.compareTo(endDate) >= 0) {
			result = "Wrong dates";
		} else if (startDate.compareTo(new java.util.Date()) < 0) {
			result = "Your date too old";
		}

		return result;
	}

}
