package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;



public class AdminGoToPrices implements Command {
	public static final String PATH_TO_ADMIN_PRICES_PAGE = "/WEB-INF/jspPages/admin_prices_main_page.jsp";

	
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = serviceProvider.getPriceService();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomCategoryPrice>priceList  = null;
		List<RoomCategory> roomCategoryList = null;
		try {
			priceList = priceService.findAllRoomPrices();
			request.setAttribute("price_list", priceList);
			
			roomCategoryList = roomCategoryService.createFullRoomCategoryList();
			request.setAttribute("room_category_names", roomCategoryList);
			
			request.getRequestDispatcher(PATH_TO_ADMIN_PRICES_PAGE).forward(request, response);
			
		} catch (ServiceException e) {
			
			e.printStackTrace();
		}
		
		
	}

}
