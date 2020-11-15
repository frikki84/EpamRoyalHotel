package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategoryPrice;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class AdminDeletePrice implements Command {
	public static final String PATH_TO_ADMIN_PRICES_PAGE = "/WEB-INF/jspPages/admin_prices_main_page.jsp";
	public static final String PARAMETR_PRICE_CODE = "price_code";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminCheckOutClient.class);

	private final ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = serviceProvider.getPriceService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String priceCode = request.getParameter(PARAMETR_PRICE_CODE);
		int priceId = Integer.parseInt(priceCode);
		
		String adress = ParametrName.ADMIN_CHECK_OUT_CLIENT_FINAL.toString() + "&" + PARAMETR_PRICE_CODE + "=" + priceCode;
		request.setAttribute("address", adress);
		
		try {
			priceService.deletePrice(priceId);			

			List<RoomCategoryPrice> list = priceService.findAllRoomPrices();
			request.setAttribute("price_list", list);

			request.getRequestDispatcher(PATH_TO_ADMIN_PRICES_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("AdminCheckOutClient ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminCheckOutClient Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
