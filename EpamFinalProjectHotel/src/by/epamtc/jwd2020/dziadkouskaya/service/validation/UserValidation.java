package by.epamtc.jwd2020.dziadkouskaya.service.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {
	
	public static final String PHONE_REG_EX = "^((8|\\+)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
	public static final String EMAIL_REG_EX = " ^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	

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

}
