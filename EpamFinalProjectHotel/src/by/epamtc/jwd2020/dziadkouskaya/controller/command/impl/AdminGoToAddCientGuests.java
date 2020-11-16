package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminGoToAddCientGuests implements Command {
	public static final String DEFAULT_STRING_VALUE = "";
	public static final String PATH_TO_ADD_GUEST = "/WEB-INF/jspPages/admin_add_client_guests.jsp";
	
	public static final String PATH_TO_PAY = "/WEB-INF/jspPages/admin_client_suum_to_pay.jsp";
	
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminGoToAddCientGuests.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	private CountryService countryService = serviceProvider.getCountryService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = (int) request.getSession().getAttribute("client_booking");
				
		int guestNumber = 0;;
		double sumToPay = 0;
		try {
			guestNumber = bookingService.findGuestNumberWithoutUser(bookingId);
			request.setAttribute("guest_number", guestNumber);
			
			if (guestNumber > 0) {
				List<Country> countryList = countryService.findCountryList();
				request.setAttribute("countryList", countryList);
				
				request.getRequestDispatcher(PATH_TO_ADD_GUEST).forward(request, response);
				
			} else {
				sumToPay = bookingService.findFinalBookingSum(bookingId);
				request.setAttribute("sum_to_pay", sumToPay);
				request.getRequestDispatcher(PATH_TO_PAY).forward(request, response);
			}
			
		} catch (ServiceException e) {
			logger.error("AdminGoToAddCientGuests ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminGoToAddCientGuests Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}
		

	}

}
