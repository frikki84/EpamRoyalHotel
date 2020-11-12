package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.InfoForCleanersTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminListForCleaners implements Command {
	public static final String PATH_TO_CLEANER_LIST = "/WEB-INF/jspPages/admin_check_in_out_table.jsp";
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateString = request.getParameter("date");
		Date date = null;
		if (dateString == null || dateString.equals("")) {
			date = new Date(System.currentTimeMillis());
			
		} else {
			date = Date.valueOf(dateString);
		}
		
		try {
			List<InfoForCleanersTransferObject> list = bookingService.prepareListForCleaners(date);
			request.setAttribute("cleaner_list", list);
			
			request.setAttribute("date", date);
			
			request.getRequestDispatcher(PATH_TO_CLEANER_LIST).forward(request, response);

			
		} catch (ServiceException e) {
		
			e.printStackTrace();
		}
		
	}

}
