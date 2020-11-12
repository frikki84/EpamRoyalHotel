package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class GoToBookingHistory implements Command {
	public static final String STRING_GO_TO_USER_BOOKING_HISTORY = "/WEB-INF/jspPages/booking_history.jsp";
	
	private BookingService bookingService = ServiceProvider.getInstance().getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userCode = (int) request.getSession().getAttribute("user_code");

		List<BookingTransferObject> userHistoryList = null;
		
		try {
			
		userHistoryList = bookingService.findUserBookings(userCode);
		
		request.setAttribute("booking_history", userHistoryList);
		
		request.getRequestDispatcher(STRING_GO_TO_USER_BOOKING_HISTORY).forward(request, response);
		
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
