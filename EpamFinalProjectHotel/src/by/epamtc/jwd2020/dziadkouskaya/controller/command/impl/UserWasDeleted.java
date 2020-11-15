package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;

public class UserWasDeleted implements Command {
	public static final String STRING_GO_TO_MAIN_PAGE = "mainPage?command=go_to_first_page";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = go_to_error_page";

	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	UserService userService = serviceProvider.getUserService();

	private static final Logger logger = LogManager.getLogger(UserWasDeleted.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = (Integer) request.getSession().getAttribute("user_code");

		try {
			userService.deleteUser(idUser);

			response.sendRedirect(STRING_GO_TO_MAIN_PAGE);

		} catch (ServiceException e) {
			logger.error("UserWasDeleted ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("UserWasDeleted Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
