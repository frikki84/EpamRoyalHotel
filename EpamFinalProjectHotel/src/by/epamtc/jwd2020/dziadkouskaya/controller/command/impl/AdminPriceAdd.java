package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;

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

public class AdminPriceAdd implements Command {
	public static final String PATH_TO_NEW_PRICE = "mainPage?command=ADMIN_GO_TO_PRICES";
	public static final String PATH_TO_ADMIN_PRICES_PAGE = "/WEB-INF/jspPages/admin_prices_main_page.jsp";
	
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = serviceProvider.getPriceService();
	


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String categoryId = request.getParameter("roomCategoryId");
		String date = request.getParameter("priceStartDate");
		String priseSt = request.getParameter("price");
		
		
		String msg = "";
		if (categoryId==null || categoryId.equals("") || date == null || date.equals("") || priseSt == null || priseSt.equals("") ) {
			msg = "Enter all parameters";
			request.setAttribute("price_adding_error", msg);
			request.getRequestDispatcher(PATH_TO_ADMIN_PRICES_PAGE).forward(request, response);;
		}
		
		RoomCategory category = new RoomCategory(Integer.parseInt(categoryId));
		Date priceStartDate = Date.valueOf(date);
		double price = Double.valueOf(priseSt);
		
		RoomCategoryPrice newPrice = new RoomCategoryPrice(priceStartDate, price, category);
		
		try {
			priceService.addNewRoomCategoryPrice(newPrice);
			
			response.sendRedirect(PATH_TO_NEW_PRICE);
			
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
