package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;

public class ChangeLocale implements Command {
	public static final String LOCAL = "local";
	public static final String COMMAND_FOR_REDIRECT = "mainPage?command=CHANGE_LOCALE";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * String url = request.getLocalName(); String url2 = request.getPathInfo();
		 * String url3 = request.getRequestURI(); String url4 =
		 * request.getRequestURL().toString(); System.out.println(url + "\n" + url2 +
		 * "\n" + url3);
		 */
		/*
		 * 0:0:0:0:0:0:0:1 null /MagicHotel/mainPage
		 * http://localhost:8080/MagicHotel/mainPage mainPage?command=CHANGE_LOCALE
		 */

		String local = request.getParameter("local");

		request.getSession(true).setAttribute("local", local);

		response.sendRedirect("mainPage?command=go_to_first_page");

	}

}
