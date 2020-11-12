package by.epamtc.jwd2020.dziadkouskaya.controller.localization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ChangeLocale extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ChangeLocale() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		String local = request.getParameter("local");
		System.out.println(local);
		
		request.getSession(true).setAttribute("local", local);

		response.sendRedirect("mainPage?command=go_to_first_page");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
