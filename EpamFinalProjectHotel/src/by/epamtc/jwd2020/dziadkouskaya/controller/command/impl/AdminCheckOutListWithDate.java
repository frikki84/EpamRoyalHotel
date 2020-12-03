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
import by.epamtc.jwd2020.dziadkouskaya.service.validation.UserValidation;

public class AdminCheckOutListWithDate implements Command {
	public static final String PATH_TO_ADMIN_CHECK_OUT_LIST = "/WEB-INF/jspPages/admin_check_out_list.jsp";
	public static final String PARAMETR_CLIENT_CHECK_OUT_DATE = "date_check_out";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminCheckOutListWithDate.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateString = request.getParameter(PARAMETR_CLIENT_CHECK_OUT_DATE);
		
		try {
			
		boolean dateCheck = UserValidation.isDate(dateString);
		
		Date date = null;	

		if (!dateCheck) {
			date = new Date(System.currentTimeMillis());

		} else {
			date = Date.valueOf(dateString);
		}
	
		request.setAttribute("date", date);
		
		String adress = ParametrName.ADMIN_CHECK_OUT_LIST_WITH_DATE.toString() + "&" + PARAMETR_CLIENT_CHECK_OUT_DATE
				+ "=" + date;
		request.setAttribute("address", adress);

		List<CheckOutTransferObject> list = null;

		
			list = bookingService.findInfoForCheckingOut(date);
			request.setAttribute("check_out_list", list);

			request.getRequestDispatcher(PATH_TO_ADMIN_CHECK_OUT_LIST).forward(request, response);

		} catch (ServiceException e) {
			logger.error("AdminCheckOutListWithDate ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminCheckOutListWithDate Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
