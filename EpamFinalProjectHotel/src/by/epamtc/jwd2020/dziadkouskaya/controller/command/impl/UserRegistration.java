package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.UserDetailServiceImpl;

public class UserRegistration implements Command {
	public static final String MSG_ABOUT_WRONG_PASSWORD = "Passwords don\'t match. Please repeat registration";
	public static final Role DEFAULTE_USER_ROLE = new Role(4, "client");
	public static final String STRING_FROM_DAO_IF_USER_WAS_NOT_FOUND_IN_DB = "";
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/registration_repeat.jsp";
	public static final String PATH_TO_WELCOME_PAGE = "mainPage?command=welcome_new_user";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = go_to_error_page";

	private static final Logger logger = LogManager.getLogger(UserRegistration.class);

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	private UserService userService = serviceProvider.getUserService();
	private UserdetailService userDetailService = serviceProvider.getUserDetailService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login;
		String email;
		String phone;
		String password;
		String passwordRepeat;
		
		try {
			login = request.getParameter("login");

			email = request.getParameter("email");
			phone = request.getParameter("phone");
			password = request.getParameter("password");
			passwordRepeat = request.getParameter("passwordRepeat");
			
			System.out.println(login + "  " + email + "  " + password + "  " + passwordRepeat);

			if (!userService.checkNewUserPassword(password, passwordRepeat)) {

				request.setAttribute("resultAnswer", MSG_ABOUT_WRONG_PASSWORD);

				request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

			} else {

				User user = new User(login, passwordRepeat, email, phone, DEFAULTE_USER_ROLE);
				
				String checkingResult = userService.checkInfoFromDB(user);
				
				System.out.println("checking " + checkingResult);

				if (checkingResult == null || !checkingResult.equals(STRING_FROM_DAO_IF_USER_WAS_NOT_FOUND_IN_DB)) {

					request.setAttribute("resultAnswer", checkingResult);

					request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

				} else {
					userService.addNewUser(user);

					int id = userService.findUserCode(login);

					userDetailService.addNewUserDetails(id);

					request.getSession(true).setAttribute("user_code", id);

					response.sendRedirect("mainPage?command=welcome_new_user");

				}
			}

		} catch (ServiceException e) {
			logger.error("UserRegistration ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UserRegistration Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}
	}

}
