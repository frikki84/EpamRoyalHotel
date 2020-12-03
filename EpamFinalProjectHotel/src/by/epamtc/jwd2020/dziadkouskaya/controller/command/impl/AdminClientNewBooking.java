package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminClientNewBooking implements Command {
	public static final String PATH_TO_BOOKING_HISTORY = "mainPage?command=admin_user_booking";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = error_page";

	private static final Logger logger = LogManager.getLogger(AdminClientNewBooking.class);

	private static ServiceProvider serviseProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviseProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RoomBooking finalBooking = (RoomBooking) request.getSession().getAttribute("booking_info");

		int clientId = (int) request.getSession().getAttribute("client_code");
		
		finalBooking.setBookingConfirmed(true);
		finalBooking.setUserId(clientId);

		String address = ParametrName.ADMIN_USER_BOOKING.toString();
		request.setAttribute("address", address);

		try {
			bookingService.addNewBooking(finalBooking);

			response.sendRedirect(PATH_TO_BOOKING_HISTORY);

		} catch (ServiceException e) {
			logger.error("AdminClientNewBooking ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminClientNewBooking Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
