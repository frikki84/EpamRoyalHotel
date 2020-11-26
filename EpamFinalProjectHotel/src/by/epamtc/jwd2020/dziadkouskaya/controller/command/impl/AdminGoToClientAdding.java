package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;

public class AdminGoToClientAdding implements Command {
	public static final String PATH_TO_REGISTRSTION_PAGE = "/WEB-INF/jspPages/admin_new_client_adding.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String address = ParametrName.ADMIN_GO_TO_ADDING_NEW_CLIENT.toString();
		request.setAttribute("address", address);
		
		request.getRequestDispatcher(PATH_TO_REGISTRSTION_PAGE).forward(request, response);
	}
}
