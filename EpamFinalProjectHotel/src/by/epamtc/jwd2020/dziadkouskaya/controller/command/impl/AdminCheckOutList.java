package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminCheckOutList implements Command {
	public static final String PATH_TO_ADMIN_CHECK_OUT_LIST = "/WEB-INF/jspPages/admin_check_out_list.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminCheckOutList.class);
	
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();
	
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Date date = new Date(System.currentTimeMillis());
		request.setAttribute("date", date);
		
		String adress = ParametrName.ADMIN_CHECK_OUT_LIST.toString();
		request.setAttribute("address", adress);
		
		List<CheckOutTransferObject> list = null;
		
		try {
			list = bookingService.findInfoForCheckingOut(date);
			request.setAttribute("check_out_list", list);
			
			
			request.getRequestDispatcher(PATH_TO_ADMIN_CHECK_OUT_LIST).forward(request, response);
			

		} catch (ServiceException e) {
			logger.error("AdminCheckOutList ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminCheckOutList Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
