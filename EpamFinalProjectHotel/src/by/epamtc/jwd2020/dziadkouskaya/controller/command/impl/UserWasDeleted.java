package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;

public class UserWasDeleted implements Command {
	public static final String STRING_GO_TO_MAIN_PAGE = "/WEB-INF/jspPages/first_page";
	private static ServiceProvider serviceProvider = ServiceProvider.getInstance();
	UserService userService = serviceProvider.getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser = (Integer) request.getSession().getAttribute("user_code");
		
		try {
			userService.deleteUser(idUser);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	
		
		
		response.sendRedirect("mainPage?command=go_to_first_page");
		
		

	}

}
