package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.InfoForCleanersTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminListForCleaners implements Command {
	public static final String PATH_TO_CLEANER_LIST = "/WEB-INF/jspPages/admin_check_in_out_table.jsp";
	public static final String PARAMETR_DATE = "date";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminListForCleaners.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dateString = request.getParameter(PARAMETR_DATE);
		System.out.println("Date clean " + dateString);

		Date date = null;
		
		if (dateString == null || dateString.equals("")) {
			date = new Date(System.currentTimeMillis());

		} else {
			date = Date.valueOf(dateString);
		}

		String adress = ParametrName.ADMIN_CLEANER_LIST.toString() + "&" + PARAMETR_DATE + "=" + date;
		request.setAttribute("address", adress);
		try {
			List<InfoForCleanersTransferObject> list = bookingService.prepareListForCleaners(date);
			request.setAttribute("cleaner_list", list);

			request.setAttribute("date", date);

			request.getRequestDispatcher(PATH_TO_CLEANER_LIST).forward(request, response);

		} catch (ServiceException e) {

			logger.error("AdminListForCleaners ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {

			logger.error("AdminListForCleaners Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
