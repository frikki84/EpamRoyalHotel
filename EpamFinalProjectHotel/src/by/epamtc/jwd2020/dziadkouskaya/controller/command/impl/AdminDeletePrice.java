package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminDeletePrice implements Command {
	public static final String PATH_TO_ADMIN_PRICES_PAGE = "/WEB-INF/jspPages/admin_prices_main_page.jsp";

	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = serviceProvider.getPriceService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int priceId = Integer.parseInt(request.getParameter("price_code"));
		
		try {
			priceService.deletePrice(priceId);
			

			List<RoomCategoryPrice> list = priceService.findAllRoomPrices();
			request.setAttribute("price_list", list);

			request.getRequestDispatcher(PATH_TO_ADMIN_PRICES_PAGE).forward(request, response);

		} catch (ServiceException e) {

			e.printStackTrace();
		}

	}

}
