package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminUserBooking implements Command {
	public static final String DEFAULT_STRING_IF_USER_LOGIN_WAS_FIND_IN_DB = "";
	public static final String PATH_TO_CLIENT_BOOKING_PAGE = "/WEB-INF/jspPages/admin_client_booking.jsp";
	public static final String PATH_TO_PAGE_REPEAT_LOGIN = "/WEB-INF/jspPages/admin_client_check_in.jsp";

	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private UserService userService = serviceProvider.getUserService();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String clientName = request.getParameter("user_info");
		String checkingResult = "";
		
		int clientId = 0;
			
		try {
			checkingResult = userService.checkClient(clientName);
			
			if (checkingResult.equals(DEFAULT_STRING_IF_USER_LOGIN_WAS_FIND_IN_DB)) {
				
				clientId = userService.findUserCode(clientName);
				request.getSession(true).setAttribute("client_code", clientId);	
				
				String userInfoForGreetings = userDetailService.findClientDetails(clientId);
				request.setAttribute("userInfoForGreetings", userInfoForGreetings);
				
				List<BookingTransferObject> clientBookings = bookingService.findUserBookings(clientId);
				request.setAttribute("booking_list", clientBookings);
				
				
				request.getRequestDispatcher(PATH_TO_CLIENT_BOOKING_PAGE).forward(request, response);
				
			} else {
				request.setAttribute("wrong_login", checkingResult);
				request.getRequestDispatcher(PATH_TO_PAGE_REPEAT_LOGIN).forward(request, response);
			}

			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
