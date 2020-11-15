package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminCheckInPayment implements Command {
	public static final String ADMIN_PAGE_FINAL_PAY = "/WEB-INF/jspPages/admin_client_personal_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminCheckInPayment.class);
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bookingId = (int)request.getSession(true).getAttribute("client_booking");
		double sumToPay = 0;
		
		  try {
			sumToPay = bookingService.findFinalBookingSum(bookingId);
			request.setAttribute("sum_to_pay", sumToPay);
			
			String adress = ParametrName.ADMIN_CHECK_IN_PAYMENT.toString();
			request.setAttribute("address", adress);
			
			request.getRequestDispatcher(ADMIN_PAGE_FINAL_PAY).forward(request, response);
			
		  } catch (ServiceException e) {
				logger.error("AdminCheckInPayment ServiceException", e);
				response.sendRedirect(PATH_TO_ERROR_PAGE);

			} catch (Exception e) {
				logger.error("AdminCheckInPayment Exception", e);
				response.sendRedirect(PATH_TO_ERROR_PAGE);
			}
		  
		 

	}

}
