
package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminAddGuestInfo implements Command {
	public static final String DEFAULT_BIRTH_DATE_VALUE = "1971-01-01";
	public static final String UPDATE_CLIENT_PAGE = "/WEB-INF/jspPages/admin_client_personal_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminAddGuestInfo.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();
	private UserService userService = serviceProvider.getUserService();
	private CountryService countryService = serviceProvider.getCountryService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientId = (int) request.getSession().getAttribute("client_code");
		int bookingId = (int) request.getSession().getAttribute("client_booking");
		double sumToPay = 0;

		List<UserDetail> detailList = new ArrayList<UserDetail>();

		String[] firstName = request.getParameterValues("firstName");
		String[] secondName = request.getParameterValues("secondName");
		String[] thirdName = request.getParameterValues("thirdName");
		String[] firstNameEnglish = request.getParameterValues("firstNameEnglish");
		String[] secondNameEnglish = request.getParameterValues("secondNameEnglish");
		String[] passportNumber = request.getParameterValues("passportNumber");
		String[] passportId = request.getParameterValues("passportId");
		String[] passportOtherInfo = request.getParameterValues("passportOtherInfo");
		String[] countryName = request.getParameterValues("country");
		String[] date = request.getParameterValues("birthDate");

		int guestNumber = firstName.length;

		for (int i = 0; i < guestNumber; i++) {
			Date birthDate = null;
			if (date[i].equals("")) {
				birthDate = Date.valueOf(DEFAULT_BIRTH_DATE_VALUE);
			} else {
				birthDate = Date.valueOf(date[i]);
			}
			Country country = new Country(countryName[i]);

			UserDetail userDetail = new UserDetail(clientId, firstName[i], secondName[i], thirdName[i],
					firstNameEnglish[i], secondNameEnglish[i], birthDate, passportNumber[i], passportId[i],
					passportOtherInfo[i], country);

			detailList.add(userDetail);
		}
		
		try {

			sumToPay = bookingService.findFinalBookingSum(bookingId);
			request.setAttribute("sum_to_pay", sumToPay);

			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);

			UserDetail detail = userDetailService.findUserDetails(clientId);

			if (detail != null && detail.getCountry() != null) {
				Country countr = detail.getCountry();

				for (Country c : countryList) {
					if (countr.getId() == c.getId()) {
						countr.setName(c.getName());
					}
				}
				detail.setCountry(countr);
			}

			request.setAttribute("userDetail", detail);

			User user = userService.takePhoneEmailFromDb(clientId);
			request.setAttribute("userPhoneTmail", user);

			for (UserDetail guest : detailList) {
				userDetailService.addNewGuest(guest);
			}

			request.setAttribute("details_list", detailList);

			request.getRequestDispatcher(UPDATE_CLIENT_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("AdminAddGuestInfo ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminAddGuestInfo Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
