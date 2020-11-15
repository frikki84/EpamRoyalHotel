package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminGoToPrices implements Command {
	public static final String PATH_TO_ADMIN_PRICES_PAGE = "/WEB-INF/jspPages/admin_prices_main_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminGoToPrices.class);

	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = serviceProvider.getPriceService();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adress = ParametrName.ADMIN_GO_TO_PRICES.toString();
		request.setAttribute("address", adress);

		List<RoomCategoryPrice> priceList = null;
		
		try {
			priceList = priceService.findAllRoomPrices();
			request.setAttribute("price_list", priceList);		

			request.getRequestDispatcher(PATH_TO_ADMIN_PRICES_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("AdminGoToPrices ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminGoToPrices Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
