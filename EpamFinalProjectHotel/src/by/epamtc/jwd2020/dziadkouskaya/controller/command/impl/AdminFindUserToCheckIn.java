package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;

public class AdminFindUserToCheckIn implements Command {
	public static final String PATH_TO_ADMIN_PAGE = "/WEB-INF/jspPages/admin_client_check_in.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String adress = ParametrName.ADMIN_FIND_USER_TO_CHECK_IN.toString();
		request.setAttribute("address", adress);
		
		request.getRequestDispatcher(PATH_TO_ADMIN_PAGE).forward(request, response);

	}

}
