package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;
import by.epamtc.jwd2020.dziadkouskaya.service.validation.UserValidation;

public class AdminGoToClientPersonalPage implements Command {
	public static final int DEFAULT_ID_FOR_USER_DETAILS = 0;
	public static final String PARAMETR_CLIENT_NAME = "firstName";
	public static final String PARAMETR_CLIENT_SURNAME = "secondName";
	public static final String PARAMETR_CLIENT_THIRD_NAME = "thirdName";
	public static final String PARAMETR_CLIENT_ENGLISH_NAME = "firstNameEnglish";
	public static final String PARAMETR_CLIENT_ENGLISH_SURNAME = "secondNameEnglish";
	public static final String PARAMETR_CLIENT_PASSPORT = "passportNumber";
	public static final String PARAMETR_CLIENT_PASSPORT_ID = "passportId";
	public static final String PARAMETR_CLIENT_OTER_INFO = "passportOtherInfo";
	public static final String PARAMETR_CLIENT_COUNTRY = "country";
	public static final String PARAMETR_CLIENT_BIRTHDATE = "birthDate";
	public static final String PARAMETR_CLIENT_EMAIL = "email";
	public static final String PARAMETR_CLIENT_PHONE = "phone";
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/admin_client_only_personal_data.jsp";
	
	public static final ClientCategory DEFAULT_CLIENT_CATEGORY = new ClientCategory(1, "Клиент-заказчик");
	public static Role DEFAULT_ROLE_VALUE = new Role(4);
	public static String DEFAULT_BIRTH_DATE_VALUE = "1971-01-01";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(UpdateClientDetails.class);

	ServiceProvider seviceProvider = ServiceProvider.getInstance();

	private UserdetailService userdetailService = seviceProvider.getUserDetailService();
	private CountryService countryService = seviceProvider.getCountryService();
	private UserService userService = seviceProvider.getUserService();
	private BookingService bookingService = seviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int clientCode = (int) request.getSession().getAttribute("client_code");
		
		String firstName = request.getParameter(PARAMETR_CLIENT_NAME);
		String secondName = request.getParameter(PARAMETR_CLIENT_SURNAME);
		String thirdName = request.getParameter(PARAMETR_CLIENT_THIRD_NAME);
		String firstNameEnglish = request.getParameter(PARAMETR_CLIENT_ENGLISH_NAME);
		String secondNameEnglish = request.getParameter(PARAMETR_CLIENT_ENGLISH_SURNAME);
		String passportNumber = request.getParameter(PARAMETR_CLIENT_PASSPORT);
		String passportId = request.getParameter(PARAMETR_CLIENT_PASSPORT_ID);
		String passportOtherInfo = request.getParameter(PARAMETR_CLIENT_OTER_INFO);
		String countryName = request.getParameter(PARAMETR_CLIENT_COUNTRY);
		Country country = new Country(countryName);
		String date = request.getParameter(PARAMETR_CLIENT_BIRTHDATE);
		
		try {
			boolean dateCheck = UserValidation.isDate(date);
			
			Date birthDate = null;
			
			if (!dateCheck) {
				birthDate = Date.valueOf(DEFAULT_BIRTH_DATE_VALUE);
				
			} else {
				birthDate = Date.valueOf(date);
			}

			UserDetail userDetail = new UserDetail(clientCode, firstName, secondName, thirdName, firstNameEnglish,
					secondNameEnglish, birthDate, passportNumber, passportId, passportOtherInfo, country,
					DEFAULT_CLIENT_CATEGORY);

			userdetailService.updateUserDetails(userDetail);

			List<Country> countryList = countryService.findCountryList();

			request.setAttribute("countryList", countryList);

			UserDetail userDetailFromDB = userdetailService.findUserDetails(clientCode);

			if (userDetailFromDB != null && userDetailFromDB.getCountry() != null) {
				Country countryM = userDetailFromDB.getCountry();

				for (Country c : countryList) {
					if (countryM.getId() == c.getId()) {
						countryM.setName(c.getName());
					}
				}
				userDetailFromDB.setCountry(country);
			}

			request.setAttribute("userDetail", userDetailFromDB);

			String email = request.getParameter(PARAMETR_CLIENT_EMAIL);
			String phone = request.getParameter(PARAMETR_CLIENT_PHONE);

			User user = new User(clientCode, email, phone, DEFAULT_ROLE_VALUE);
			userService.updateEmailPhone(user);

			User userToJsp = userService.takePhoneEmailFromDb(clientCode);

			request.setAttribute("userPhoneTmail", userToJsp);

			
			String adress = ParametrName.UPDATE_CLIENT_DETAILS.toString() + "&" + PARAMETR_CLIENT_NAME + "=" + firstName + "&"
					+ PARAMETR_CLIENT_SURNAME + "=" + secondName + "&" + PARAMETR_CLIENT_THIRD_NAME + "=" + thirdName
					+ "&" + PARAMETR_CLIENT_ENGLISH_NAME + "=" + firstNameEnglish + "&"
					+ PARAMETR_CLIENT_ENGLISH_SURNAME + "=" + secondNameEnglish + "&" + PARAMETR_CLIENT_BIRTHDATE + "="
					+ date + "&" + PARAMETR_CLIENT_PASSPORT + "=" + passportNumber + "&"
					+ PARAMETR_CLIENT_PASSPORT_ID + "=" + passportId + "&" + PARAMETR_CLIENT_OTER_INFO + "="
					+ passportOtherInfo + "&" + PARAMETR_CLIENT_COUNTRY + "=" + countryName + "&"
					+ PARAMETR_CLIENT_EMAIL + "=" + email + "&" + PARAMETR_CLIENT_PHONE + "=" + phone;
			
			request.setAttribute("address", adress);

			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("UpdateClientDetails ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UpdateClientDetails Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}
}