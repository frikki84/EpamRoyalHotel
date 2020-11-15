package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.PUBLIC_MEMBER;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Role;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.CountryServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.UserDetailServiceImpl;

public class UpdateUser implements Command {
	public static Role DEFAULT_ROLE_VALUE = new Role(4);
	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/client_personal_data_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = go_to_error_page";

	
	ServiceProvider seviceProvider = ServiceProvider.getInstance();
	UserService userService = seviceProvider.getUserService();
	
	private static final Logger logger = LogManager.getLogger(UpdateUser.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id = (int)request.getSession().getAttribute("user_code");
		
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		
		User user = new User(id, email, phone, DEFAULT_ROLE_VALUE);
		
		String adress = ParametrName.UPDATE_USER.toString();
		request.setAttribute("address", adress);
		
		try {
			userService.updateEmailPhone(user);
			
			User userToJsp = userService.takePhoneEmailFromDb(id);
			
			request.setAttribute("userPhoneTmail", userToJsp);
			
			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);
			
		} catch (ServiceException e) {
			logger.error("UpdateUser ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
			
		} catch (Exception e) {
			logger.error("UpdateUser Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}
	}

}
