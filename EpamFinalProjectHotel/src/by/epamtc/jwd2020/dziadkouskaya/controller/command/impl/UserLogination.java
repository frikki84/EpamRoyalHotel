package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import by.epamtc.jwd2020.dziadkouskaya.dao.CountryDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.RoomCategoryPriceDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.RoomCategoryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class UserLogination implements Command {
	public static final String LOGIN_PASSWORD_WAS_FOUND_IN_DB = "";
	public static final String PARAMETR_USER_LOGIN = "login";
	public static final String PARAMETR_USER_PASSWORD = "password";
	public static final String PATH_TO_LOGINATION_FORM_TO_CORRECT_INFO = "/WEB-INF/jspPages/logination_page_repeat.jsp";
	public static final String PATH_TO_BOOKING_PAGE = "/WEB-INF/jspPages/client_booking_page.jsp";
	public static final String PATH_TO_ADMIN_PAGE = "/WEB-INF/jspPages/admin_client_check_in.jsp";
	public static final String ROLE_ADMINISTRATOR = "Администратор";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command =error_page";

	private static final Logger logger = LogManager.getLogger(UserLogination.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private UserService userService = serviceProvider.getUserService();
	private RoomCategoryService roomCategoryService = serviceProvider.getRoomCategoryService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(PARAMETR_USER_LOGIN);
		String password = request.getParameter(PARAMETR_USER_PASSWORD);

		String resultString = null;

		try {
			resultString = userService.checkUserForLogination(login, password);

			if (!resultString.equals(LOGIN_PASSWORD_WAS_FOUND_IN_DB)) {
				request.setAttribute("answerLogination", resultString);

				request.getRequestDispatcher(PATH_TO_LOGINATION_FORM_TO_CORRECT_INFO).forward(request, response);

			} else {

				int id = userService.findUserCode(login);

				request.getSession(true).setAttribute("user_code", id);

				request.getSession().setAttribute("name", login);

				String userRole = userService.findUserRole(login);
				
				String adress = "";

				if (userRole.trim().equalsIgnoreCase(ROLE_ADMINISTRATOR)) {		
					adress = ParametrName.ADMIN_FIND_USER_TO_CHECK_IN.toString(); 
					request.setAttribute("address", adress);
					
					request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);

				} else {

					List<Integer> roomCapasityList = roomCategoryService.createRoomCategoryList();
					request.setAttribute("room_capacity", roomCapasityList);

					adress = ParametrName.BOOKING_PAGE.toString(); 
					request.setAttribute("address", adress);
					
					request.getRequestDispatcher(PATH_TO_BOOKING_PAGE).forward(request, response);
				}

			}

		} catch (ServiceException e) {
			logger.error("UserLogination ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UserLogination Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
