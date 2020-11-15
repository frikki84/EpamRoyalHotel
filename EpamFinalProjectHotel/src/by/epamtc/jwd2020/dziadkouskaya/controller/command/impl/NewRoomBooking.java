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

public class NewRoomBooking implements Command {
	public static final String PATH_TO_BOOKING_HISTORY = "mainPage?command=booking_history";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = error_page";

	private static final Logger logger = LogManager.getLogger(NewRoomBooking.class);

	private static ServiceProvider serviseProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviseProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RoomBooking finalBooking = (RoomBooking) request.getSession().getAttribute("booking_info");

		finalBooking.setBookingConfirmed(true);
		
		String address = ParametrName.BOOKING_HISTORY.toString();
		request.setAttribute("address", address);

		try {
			bookingService.addNewBooking(finalBooking);

			response.sendRedirect(PATH_TO_BOOKING_HISTORY);

		} catch (ServiceException e) {
			logger.error("NewRoomBooking ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
			
		} catch (Exception e) {
			logger.error("NewRoomBooking Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
