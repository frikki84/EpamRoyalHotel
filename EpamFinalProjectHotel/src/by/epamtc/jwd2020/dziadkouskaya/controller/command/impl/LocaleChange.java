package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;

public class LocaleChange implements Command {
	public static final String COMMAND_NAME_FOR_LOCALIZATION = "mainPage?command=";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = error_page";

	private static final Logger logger = LogManager.getLogger(LocaleChange.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String local = request.getParameter("local");

			request.getSession(true).setAttribute("local", local);

			String address = request.getParameter("address");

			response.sendRedirect(COMMAND_NAME_FOR_LOCALIZATION + address);

		} catch (Exception e) {
			logger.error("LocaleChange Exception", e);
			
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}

}
