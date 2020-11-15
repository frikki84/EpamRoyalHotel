package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

public class AdminCheckInClient implements Command {
	public static final String ADMIN_CLIENT_PAGE = "/WEB-INF/jspPages/admin_client_personal_page.jsp";
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();
	private CountryService countryService = serviceProvider.getCountryService();
	private UserService userService = serviceProvider.getUserService();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int clientId = (int) request.getSession().getAttribute("client_code");

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

			int bookingId = Integer.parseInt(request.getParameter("client_booking"));
			request.getSession().setAttribute("client_booking", bookingId);
			
			int guestNumber = bookingService.findGuestNumberWithoutUser(bookingId);
			request.setAttribute("guest_number", guestNumber);
			
			double sumToPay = bookingService.findFinalBookingSum(bookingId);
			request.setAttribute("sum_to_pay", sumToPay);

			request.getRequestDispatcher(ADMIN_CLIENT_PAGE).forward(request, response);

		} catch (ServiceException e) {

			e.printStackTrace();
		}

	}

}
