package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
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
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminCheckInClient implements Command {
	public static final String DEFAULT_STRING_VALUE = "";
	public static final String ADMIN_CLIENT_PAGE = "/WEB-INF/jspPages/admin_client_personal_page.jsp";
	public static final String ADMIN_CLIENT_PAGE_WITHOUT_CHECK_IN = "/WEB-INF/jspPages/admin_personal_page_only.jsp";

	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminCheckInClient.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();
	private CountryService countryService = serviceProvider.getCountryService();
	private UserService userService = serviceProvider.getUserService();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int clientId = (int) request.getSession().getAttribute("client_code");

		String adress = ParametrName.UPDATE_CLIENT_DETAILS.toString();
		request.setAttribute("address", adress);

		try {

			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);

			UserDetail detail = userDetailService.findUserDetails(clientId);

			if (detail != null && detail.getCountry() != null) {
				Country country = detail.getCountry();

				for (Country c : countryList) {
					if (country.getId() == c.getId()) {
						country.setName(c.getName());
					}
				}
				detail.setCountry(country);
			}

			request.setAttribute("userDetail", detail);

			User user = userService.takePhoneEmailFromDb(clientId);
			request.setAttribute("userPhoneTmail", user);

			String bookingNumber = request.getParameter("client_booking");

			if (bookingNumber == null || bookingNumber.equals(DEFAULT_STRING_VALUE)) {

				request.getRequestDispatcher(ADMIN_CLIENT_PAGE_WITHOUT_CHECK_IN).forward(request, response);

			} else {
				int bookingId = Integer.parseInt(bookingNumber);
				request.getSession().setAttribute("client_booking", bookingId);
				request.getRequestDispatcher(ADMIN_CLIENT_PAGE).forward(request, response);

			}

		} catch (ServiceException e) {
			logger.error("AdminCheckInClient ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminCheckInClient Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
