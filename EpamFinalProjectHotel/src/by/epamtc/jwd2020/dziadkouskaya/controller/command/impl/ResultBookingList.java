package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.validation.UserValidation;

public class ResultBookingList implements Command {
	public static final String DEFAULT_VALIDATION_STRING = "";
	public static final String PATH_TO_THIS_PAGE_WITH_INFO_ABOUT_WRONG_DATES = "/WEB-INF/jspPages/client_booking_page.jsp";
	public static final String PATH_TO_USER_RESULT_BOOKING_LIST = "/WEB-INF/jspPages/client_booking_result_list.jsp";
	public static final String PATAMETR_START_DATE = "startDate";
	public static final String PATAMETR_END_DATE = "endDate";
	public static final String PATAMETR_PEOPLE_NUMBER = "peopleNumber";
	public static final String PATAMETR_CHILDREN = "childrenNumber";
	public static final String MSG_WRONG_DATE_FORMAT = "Wrong date format. Please, repeat";

	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = error_page";

	private static final Logger logger = LogManager.getLogger(ResultBookingList.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();
	BookingService bookingService = serviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = (int) request.getSession().getAttribute("user_code");

		String firstDate = request.getParameter(PATAMETR_START_DATE);

		String lastDate = request.getParameter(PATAMETR_END_DATE);

		try {

			boolean firstDateCheck = UserValidation.isDate(firstDate);
			boolean lastDateCheck = UserValidation.isDate(lastDate);

			if (!firstDateCheck || !lastDateCheck) {

				request.setAttribute("wrong_date", MSG_WRONG_DATE_FORMAT);
				List<Integer> roomCapasityList = roomCategoryService.createRoomCategoryList();
				request.setAttribute("room_capacity", roomCapasityList);

				request.getRequestDispatcher(PATH_TO_THIS_PAGE_WITH_INFO_ABOUT_WRONG_DATES).forward(request, response);

			}

			Date startDate = Date.valueOf(firstDate);

			Date endDate = Date.valueOf(lastDate);

			String validationString;

			validationString = UserValidation.checkDates(startDate, endDate);

			if (!validationString.equals(DEFAULT_VALIDATION_STRING)) {

				request.setAttribute("wrong_date", validationString);
				List<Integer> roomCapasityList = roomCategoryService.createRoomCategoryList();
				request.setAttribute("room_capacity", roomCapasityList);

				request.getRequestDispatcher(PATH_TO_THIS_PAGE_WITH_INFO_ABOUT_WRONG_DATES).forward(request, response);

			} else {
				String peopleNumber = request.getParameter(PATAMETR_PEOPLE_NUMBER);
				String children = request.getParameter(PATAMETR_CHILDREN);

				int adultPeopleNumber = Integer.parseInt(peopleNumber);
				int childrenNumber = Integer.parseInt(children);

				List<RoomBooking> bookingInfo = bookingService.findFreeRooms(userId, adultPeopleNumber, childrenNumber,
						startDate, endDate);

				request.setAttribute("booking_info", bookingInfo);

				String address = ParametrName.RESULT_BOOKING_LIST.toString() + "&" + PATAMETR_START_DATE + "="
						+ firstDate + "&" + PATAMETR_END_DATE + "=" + endDate + "&" + PATAMETR_PEOPLE_NUMBER + "="
						+ peopleNumber + "&" + PATAMETR_CHILDREN + "=" + children;

				request.setAttribute("address", address);

				request.getRequestDispatcher(PATH_TO_USER_RESULT_BOOKING_LIST).forward(request, response);

			}
		} catch (ServiceException e) {
			logger.error("ResultBookingList ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("ResultBookingList Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}
}
