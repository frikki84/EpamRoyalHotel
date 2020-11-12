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

import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.PriceService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;

public class GoToFirstPage implements Command {
	private static ServiceProvider seviceProvider = ServiceProvider.getInstance();
	private PriceService priceService = seviceProvider.getPriceService();
	private RoomCategoryService roomCategoryService = seviceProvider.getRoomCategoryService();

	public static final String PATH_TO_FIRST_PAGE = "/WEB-INF/jspPages/first_page.jsp";

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

			request.getRequestDispatcher(PATH_TO_FIRST_PAGE).forward(request, response);

		} catch (ServiceException e) {

			e.printStackTrace();
		}

	}
}
