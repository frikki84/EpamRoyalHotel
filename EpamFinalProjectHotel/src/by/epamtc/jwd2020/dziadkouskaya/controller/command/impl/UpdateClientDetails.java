package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class UpdateClientDetails implements Command {
	public static final int DEFAULT_ID_FOR_USER_DETAILS = 0;
	public static final ClientCategory DEFAULT_CLIENT_CATEGORY = new ClientCategory(1, "Клиент-заказчик");
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/admin_client_personal_page.jsp";
	public static Role DEFAULT_ROLE_VALUE = new Role(4);
	public static String DEFAULT_BIRTH_DATE_VALUE = "1971-01-01";

	ServiceProvider seviceProvider = ServiceProvider.getInstance();

	private UserdetailService userdetailService = seviceProvider.getUserDetailService();
	private CountryService countryService = seviceProvider.getCountryService();
	private UserService userService = seviceProvider.getUserService();
	private BookingService bookingService = seviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// take id from Users (from session)
		int clientCode = (int) request.getSession().getAttribute("client_code");
		int bookingId = (int)request.getSession().getAttribute("client_booking");

		try {
			// take all parameters for creating UserDetail
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
			if (date.equals("")) {
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
					if (country.getId() == c.getId()) {
						country.setName(c.getName());
					}
				}
				userDetailFromDB.setCountry(country);
			}

			request.setAttribute("userDetail", userDetailFromDB);

			String email = request.getParameter("email");
			String phone = request.getParameter("phone");

			User user = new User(clientCode, email, phone, DEFAULT_ROLE_VALUE);
			userService.updateEmailPhone(user);

			User userToJsp = userService.takePhoneEmailFromDb(clientCode);

			request.setAttribute("userPhoneTmail", userToJsp);
			
			int guestNumber = bookingService.findGuestNumberWithoutUser(bookingId);
			request.setAttribute("guest_number", guestNumber);

			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
