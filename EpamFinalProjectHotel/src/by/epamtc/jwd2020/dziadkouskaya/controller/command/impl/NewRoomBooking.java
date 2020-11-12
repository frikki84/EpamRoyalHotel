package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class NewRoomBooking implements Command {
	private static ServiceProvider serviseProvider = ServiceProvider.getInstance();
	private BookingService bookingService = serviseProvider.getBookingService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		/*
		 * List<RoomBooking> roomBooking = (List<RoomBooking>)
		 * request.getSession().getAttribute("booking_info");
		 */
		/*
		 * for (RoomBooking booking : roomBooking) { System.out.println(booking); }
		 */
		
		/* int bookingIndex = Integer.parseInt(request.getParameter("booking")); */
		
		/* RoomBooking finalBooking = roomBooking.get(bookingIndex); */
		RoomBooking finalBooking = (RoomBooking) request.getSession().getAttribute("booking_info");
		
		
		finalBooking.setBookingConfirmed(true);
		
		try {
			bookingService.addNewBooking(finalBooking);
			
			response.sendRedirect("mainPage?command=booking_history");
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		
		

	}

}
