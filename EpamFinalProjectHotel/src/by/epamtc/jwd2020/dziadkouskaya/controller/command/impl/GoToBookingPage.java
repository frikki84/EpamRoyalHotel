package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class GoToBookingPage implements Command {
	public static final String STRING_TO_USER_BOOKING_PAGE = "/WEB-INF/jspPages/client_booking_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(GoToBookingPage.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Integer> roomCapasityList = roomCategoryService.createRoomCategoryList();
			request.setAttribute("room_capacity", roomCapasityList);
			
			String adress = ParametrName.RESULT_BOOKING_LIST.toString(); // booking_page
			request.setAttribute("address from GoToBookingPage", adress);

			request.getRequestDispatcher(STRING_TO_USER_BOOKING_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("GoToBookingPage ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("GoToBookingPage Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
