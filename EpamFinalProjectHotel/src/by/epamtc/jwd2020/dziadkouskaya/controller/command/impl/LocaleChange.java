package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;

public class LocaleChange implements Command {
	public static final String COMMAND_NAME_FOR_LOCALIZATION = "mainPage?command=";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String local = request.getParameter("local");
				
		request.getSession(true).setAttribute("local", local);
			
		String address = request.getParameter("address");
		System.out.println("Address " + address);

		response.sendRedirect(COMMAND_NAME_FOR_LOCALIZATION + address);
		
	}


}
