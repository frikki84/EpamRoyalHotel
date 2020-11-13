package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
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

	ServiceProvider serviceProvider = ServiceProvider.getInstance();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String email;
		String phone;
		String password;
		String passwordRepeat;

		login = request.getParameter("login");

		email = request.getParameter("email");
		phone = request.getParameter("phone");
		password = request.getParameter("password");
		passwordRepeat = request.getParameter("passwordRepeat");

		UserService userService = serviceProvider.getUserService();
		UserdetailService userdetailService = new UserDetailServiceImpl();

		if (!userService.checkNewUserPassword(password, passwordRepeat)) {

			request.setAttribute("resultAnswer", MSG_ABOUT_WRONG_PASSWORD);
			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

		} else {

			User user = new User(login, passwordRepeat, email, phone, DEFAULTE_USER_ROLE);

			try {

				String checkingResult = userService.checkInfoFromDB(user);
				System.out.println("checking " + checkingResult);

				if (checkingResult == null || !checkingResult.equals(STRING_FROM_DAO_IF_USER_WAS_NOT_FOUND_IN_DB)) {

					request.setAttribute("resultAnswer", checkingResult);
					request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

				} else {
					userService.addNewUser(user);

					int id = userService.findUserCode(login);

					userdetailService.addNewUserDetails(id);

					request.getSession(true).setAttribute("user_code", id);

					response.sendRedirect("mainPage?command=welcome_new_user");

				}

			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}

	}

}
