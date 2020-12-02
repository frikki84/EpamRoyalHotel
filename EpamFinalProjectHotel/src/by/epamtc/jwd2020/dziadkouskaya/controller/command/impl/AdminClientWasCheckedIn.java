package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminClientWasCheckedIn implements Command {
	public static final String PATH_TO_CLIENT_BOOKING_PAGE = "/WEB-INF/jspPages/admin_client_booking.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminClientWasCheckedIn.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* int bookingId = Integer.parseInt(request.getParameter("client_booking")); */
		
		
		  int bookingId = (int) request.getSession().getAttribute("client_booking");
		 
		int clientId = (int) request.getSession().getAttribute("client_code");

		try {
			bookingService.checkInClientWithPayment(bookingId);
			
			
			String userInfoForGreetings = userDetailService.findClientDetails(clientId);
			request.setAttribute("userInfoForGreetings", userInfoForGreetings);
			
			List<BookingTransferObject> clientBookings = bookingService.findUserBookings(clientId);
			request.setAttribute("booking_list", clientBookings);
			
			String adress = ParametrName.ADMIN_CLIENT_WAS_CHECKED_IN.toString();
			request.setAttribute("address", adress);


			request.getRequestDispatcher(PATH_TO_CLIENT_BOOKING_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("AdminClientWasCheckedIn ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminClientWasCheckedIn Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
