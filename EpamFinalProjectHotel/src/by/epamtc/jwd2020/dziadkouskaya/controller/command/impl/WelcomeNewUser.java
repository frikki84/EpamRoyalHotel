package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class WelcomeNewUser implements Command {
	public static final ClientCategory DEFAULT_CLIENT_CATEGORY = new ClientCategory(1, "Клиент-заказчик");
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/client_personal_data_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";


	private ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private CountryService countryService = serviceProvider.getCountryService();
	private UserdetailService userdetailService = serviceProvider.getUserDetailService();
	private UserService userService = serviceProvider.getUserService();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();
	
	private static final Logger logger = LogManager.getLogger(WelcomeNewUser.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = ParametrName.WELCOME_NEW_USER.toString();
		request.setAttribute("address", address);
		
		try {
			int id = (int)request.getSession().getAttribute("user_code");
			
			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);
			
			UserDetail detail = userdetailService.findUserDetails(id);
			
			if (detail != null && detail.getCountry() != null) {
				Country country = detail.getCountry();

				for (Country c : countryList) {
					if (country.getId() == c.getId()) {
						country.setName(c.getName());
					}
				}
				detail.setCountry(country);

			} else {
				detail = new UserDetail(id, DEFAULT_CLIENT_CATEGORY);
			}
			
			request.setAttribute("userDetail", detail);
			
			User user = userService.takePhoneEmailFromDb(id);
			
			request.setAttribute("userPhoneTmail", user);
			
			List<Integer>roomCapasityList = roomCategoryService.createRoomCategoryList();
			
			request.setAttribute("room_capacity", roomCapasityList);
			
			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("UserRegistration ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UserRegistration Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}
}
