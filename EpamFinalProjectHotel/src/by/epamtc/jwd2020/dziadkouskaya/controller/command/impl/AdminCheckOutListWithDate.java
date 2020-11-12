package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminCheckOutListWithDate implements Command {
public static final String PATH_TO_ADMIN_CHECK_OUT_LIST = "/WEB-INF/jspPages/admin_check_out_list.jsp";
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateString = request.getParameter("date_check_out");	
		Date date = Date.valueOf(dateString);
		request.setAttribute("date", date);
		
		List<CheckOutTransferObject> list = null;
		
		try {
			list = bookingService.findInfoForCheckingOut(date);
			request.setAttribute("check_out_list", list);
			
			
			request.getRequestDispatcher(PATH_TO_ADMIN_CHECK_OUT_LIST).forward(request, response);
			
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}

	}

}
