package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminCheckInPayment implements Command {
	public static final String ADMIN_PAGE_FINAL_PAY = "/WEB-INF/jspPages/admin_final_check_in.jsp";
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = (int)request.getSession().getAttribute("client_booking");
		double sumToPay = 0;
		
		  try {
			sumToPay = bookingService.findFinalBookingSum(bookingId);
			request.setAttribute("sum_to_pay", sumToPay);
			
			request.getRequestDispatcher(ADMIN_PAGE_FINAL_PAY).forward(request, response);
			
			
			
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		  
		 

	}

}
