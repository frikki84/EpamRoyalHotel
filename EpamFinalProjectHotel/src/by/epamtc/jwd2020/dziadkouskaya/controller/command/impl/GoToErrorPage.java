package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;

public class GoToErrorPage implements Command {
	public static final String PATH_TO_ERROR_PAGE = "/errorPage.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(PATH_TO_ERROR_PAGE).forward(request, response);

	}

}
