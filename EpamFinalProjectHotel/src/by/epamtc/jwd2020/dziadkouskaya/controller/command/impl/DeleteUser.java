package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;

public class DeleteUser implements Command {
	public static final String PATH_TO_DELETE_PAGE = "/WEB-INF/jspPages/delete_user_page.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(PATH_TO_DELETE_PAGE).forward(request, response);

	}

}
