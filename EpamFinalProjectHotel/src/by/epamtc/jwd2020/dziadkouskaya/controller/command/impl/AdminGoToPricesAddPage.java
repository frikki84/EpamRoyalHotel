package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminGoToPricesAddPage implements Command {
	public static final String PATH_TO_ADMIN_CHECK_OUT_LIST = "/WEB-INF/jspPages/admin_prices_add_new_price.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminGoToPricesAddPage.class);
	
	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<RoomCategory> roomCategoryList = null;
		try {
			roomCategoryList = roomCategoryService.createFullRoomCategoryList();
			request.setAttribute("room_category_names", roomCategoryList);			
			
			String adress = ParametrName.ADMIN_PRICE_GO_TO_ADDING_PAGE.toString();
			request.setAttribute("address", adress);

			request.getRequestDispatcher(PATH_TO_ADMIN_CHECK_OUT_LIST).forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("AdminGoToPricesAddPage ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminGoToPricesAddPage Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}
	}

}
