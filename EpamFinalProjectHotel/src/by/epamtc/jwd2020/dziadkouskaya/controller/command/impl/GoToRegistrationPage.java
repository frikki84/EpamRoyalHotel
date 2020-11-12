package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import sun.print.resources.serviceui;

public class GoToRegistrationPage implements Command{
	public static final String PATH_TO_REGISTRSTION_PAGE = "/WEB-INF/jspPages/registration_repeat.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(PATH_TO_REGISTRSTION_PAGE).forward(request, response);
		
	}

}
