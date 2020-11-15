package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.Validation;

public class ResultBookingList implements Command {
	public static final String DEFAULT_VALIDATION_STRING = "";

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();
	BookingService bookingService = serviceProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int userId = (int) request.getSession().getAttribute("user_code");

		String firstDate = request.getParameter("startDate");

		String lastDate = request.getParameter("endDate");

		Date startDate = Date.valueOf(firstDate);

		Date endDate = Date.valueOf(lastDate);

		String validationString;
		try {
			validationString = Validation.checkDates(startDate, endDate);
			
			if (!validationString.equals(DEFAULT_VALIDATION_STRING)) {

				request.setAttribute("wrong_date", validationString);
				List<Integer> roomCapasityList = roomCategoryService.createRoomCategoryList();
				request.setAttribute("room_capacity", roomCapasityList);

				request.getRequestDispatcher("/WEB-INF/jspPages/client_booking_page.jsp").forward(request,
						response);

			} else {
				String peopleNumber = request.getParameter("peopleNumber");
				String children = request.getParameter("childrenNumber");

				int adultPeopleNumber = Integer.parseInt(peopleNumber);
				int childrenNumber = Integer.parseInt(children);

				List<RoomBooking> bookingInfo= bookingService.findFreeRooms(userId, adultPeopleNumber, childrenNumber, startDate, endDate);

				request.setAttribute("booking_info", bookingInfo);

				request.getRequestDispatcher("/WEB-INF/jspPages/client_booking_result_list.jsp").forward(request,
						response);

			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
