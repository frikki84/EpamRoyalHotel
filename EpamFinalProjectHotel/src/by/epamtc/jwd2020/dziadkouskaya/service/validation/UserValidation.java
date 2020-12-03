package by.epamtc.jwd2020.dziadkouskaya.service.validation;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class UserValidation {

	public static final String PHONE_REG_EX = "^((8|\\+)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
	public static final String EMAIL_REG_EX = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String DATE_REGEX = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
		      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String MSG_WRONG_DATES = "Wrong dates";
	public static final String MSG_OLD_DATE = "Your date too old";
	public static final String REGEX_EMPTY_FIELD = "\\s*";
	public static final String EMPTY_FIELD = "";

	public static boolean checkUserPhoneNumber(String phone) {

		boolean resulst = false;

		Pattern pattern = Pattern.compile(PHONE_REG_EX);
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			resulst = true;
		}

		return resulst;
	}

	public static boolean checkUserEmail(String email) {
		boolean result = false;

		Pattern pattern = Pattern.compile(EMAIL_REG_EX);
		Matcher matcher = pattern.matcher(email);

		if (matcher.matches()) {
			result = true;
		}

		return result;
	}
	
	public static boolean isDate(String date) throws ServiceException {
		boolean result = true;

		if (date == null || !date.matches(DATE_REGEX)) {
			result = false;
			
		} else {			
			SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
			df.setLenient(false);			
			try {
				df.parse(date);
			} catch (ParseException ex) {
				return false;
			}
		}
		return result;
	}
	
	
	public static boolean checkWrittenLogin(String login) {
		boolean result = true;
		
		Pattern pattern = Pattern.compile(REGEX_EMPTY_FIELD);
		Matcher matcher = pattern.matcher(login);
		
		if (login == null || login.equals(EMPTY_FIELD) || matcher.matches()) {
			result = false;
		}
		return result;
	}

	

	public static String checkDates(Date startDate, Date endDate) throws ServiceException {
		String result = "";

		if (startDate.compareTo(endDate) >= 0) {
			result = MSG_WRONG_DATES;
		} else if (startDate.compareTo(new java.util.Date()) < 0) {
			result = MSG_OLD_DATE;
		}

		return result;
	}

}
