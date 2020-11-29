package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class GoToContacts implements Command {
	public static final String PATH_TO_CONTACTS = "/contacts.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(GoToContacts.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.getRequestDispatcher(PATH_TO_CONTACTS).forward(request, response);
		} catch (Exception e) {
			logger.error("AdminGoToPricesAddPage Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
