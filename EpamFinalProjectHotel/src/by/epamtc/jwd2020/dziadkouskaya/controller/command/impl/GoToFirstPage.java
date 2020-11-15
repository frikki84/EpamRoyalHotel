package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.CommandProvider;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class GoToFirstPage implements Command {
	public static final String PATH_TO_FIRST_PAGE = "/WEB-INF/jspPages/first_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";
	
	
	private static ServiceProvider seviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = seviceProvider.getPriceService();
	private RoomCategoryService roomCategoryService = seviceProvider.getRoomCategoryService();
	
	private static final Logger logger = LogManager.getLogger(GoToFirstPage.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> stringList = new ArrayList<>();
		String oneCategory = "Двухместный люкс";
		String twoCategory = "Одноместный люкс";
		String treeCategory = "Одноместный стандарт";
		String fourCategory = "Двухместный стандарт";

		stringList.add(oneCategory);
		stringList.add(twoCategory);
		stringList.add(treeCategory);
		stringList.add(fourCategory);

		Map<String, Double> categoryPriceMap = null;
		List<RoomCategory> categoryList = new ArrayList<>();

		try {
			for (String name : stringList) {
				RoomCategory category = roomCategoryService.findRoomCategoryByName(name);
				categoryList.add(category);
			}

			categoryPriceMap = priceService.findPriceForGivenCategoryes(categoryList);

			request.setAttribute("map", categoryPriceMap);
			
			String adress = ParametrName.GO_TO_FIRST_PAGE.toString();
			request.setAttribute("address", adress);
						
			request.getRequestDispatcher(PATH_TO_FIRST_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("UserRegistration ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UserRegistration Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}
}
