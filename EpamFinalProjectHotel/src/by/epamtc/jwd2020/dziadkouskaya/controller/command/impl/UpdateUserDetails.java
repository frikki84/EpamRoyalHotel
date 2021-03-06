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
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;
import by.epamtc.jwd2020.dziadkouskaya.service.validation.UserValidation;

public class UpdateUserDetails implements Command {
	public static final int DEFAULT_ID_FOR_USER_DETAILS = 0;
	public static final ClientCategory DEFAULT_CLIENT_CATEGORY = new ClientCategory(1, "Клиент-заказчик");
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/client_personal_data_page.jsp";
	public static Role DEFAULT_ROLE_VALUE = new Role(4);
	public static String DEFAULT_BIRTH_DATE_VALUE = "1971-01-01";
	public static String MSG_WRONG_EMAIL_PHONE = "Wrong email or phone format. Please, change it and repeat";
	public static String DEFAULT_EMAIL_PHONE_VALUE = "";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(UpdateUserDetails.class);

	ServiceProvider seviceProvider = ServiceProvider.getInstance();

	UserdetailService userdetailService = seviceProvider.getUserDetailService();
	CountryService countryService = seviceProvider.getCountryService();
	UserService userService = seviceProvider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userCode = (int) request.getSession().getAttribute("user_code");

		String adress = ParametrName.WELCOME_NEW_USER.toString();
		request.setAttribute("address", adress);

		try {

			String firstName = request.getParameter("firstName");
			String secondName = request.getParameter("secondName");
			String thirdName = request.getParameter("thirdName");
			String firstNameEnglish = request.getParameter("firstNameEnglish");
			String secondNameEnglish = request.getParameter("secondNameEnglish");
			String passportNumber = request.getParameter("passportNumber");
			String passportId = request.getParameter("passportId");
			String passportOtherInfo = request.getParameter("passportOtherInfo");

			String countryName = request.getParameter("country");
			Country country = new Country(countryName);

			String date = request.getParameter("birthDate");
			Date birthDate = null;

			boolean dateCheck = UserValidation.isDate(date);

			if (!dateCheck) {
				birthDate = Date.valueOf(DEFAULT_BIRTH_DATE_VALUE);

			} else {

				birthDate = Date.valueOf(date);

			}

			UserDetail userDetail = new UserDetail(userCode, firstName, secondName, thirdName, firstNameEnglish,
					secondNameEnglish, birthDate, passportNumber, passportId, passportOtherInfo, country,
					DEFAULT_CLIENT_CATEGORY);

			userdetailService.updateUserDetails(userDetail);

			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);

			UserDetail userDetailFromDB = userdetailService.findUserDetails(userCode);

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

			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			boolean emailCheck = UserValidation.checkUserEmail(email);
			boolean phoneCheck = UserValidation.checkUserPhoneNumber(phone);

			if (email != DEFAULT_EMAIL_PHONE_VALUE && !emailCheck) {

				request.setAttribute("wrong_email_phone", MSG_WRONG_EMAIL_PHONE);
				email = DEFAULT_EMAIL_PHONE_VALUE;
			}

			if (phone != DEFAULT_EMAIL_PHONE_VALUE && !phoneCheck) {
				request.setAttribute("wrong_email_phone", MSG_WRONG_EMAIL_PHONE);
				phone = DEFAULT_EMAIL_PHONE_VALUE;
			}

			User user = new User(userCode, email, phone, DEFAULT_ROLE_VALUE);
			userService.updateEmailPhone(user);

			User userToJsp = userService.takePhoneEmailFromDb(userCode);

			request.setAttribute("userPhoneTmail", userToJsp);

			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("UpdateUserDetails ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UpdateUserDetails Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
