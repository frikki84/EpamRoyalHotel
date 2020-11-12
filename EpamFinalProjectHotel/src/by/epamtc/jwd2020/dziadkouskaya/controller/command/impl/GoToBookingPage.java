package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class GoToBookingPage implements Command {
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Integer>roomCapasityList = roomCategoryService.createRoomCategoryList();
			request.setAttribute("room_capacity", roomCapasityList);
			
			request.getRequestDispatcher("/WEB-INF/jspPages/client_booking_page.jsp").forward(request, response);
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		
		
				
	}

}
