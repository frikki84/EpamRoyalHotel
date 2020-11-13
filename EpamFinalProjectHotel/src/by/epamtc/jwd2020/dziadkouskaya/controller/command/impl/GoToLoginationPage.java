package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;

public class GoToLoginationPage implements Command{
	public static final String PATH_TO_LOGINATION = "/WEB-INF/jspPages/logination_page_repeat.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = ParametrName.GO_TO_LOGINATION_PAGE.toString();
		request.setAttribute("address", address);
		
		request.getRequestDispatcher(PATH_TO_LOGINATION).forward(request, response);

	}

}
