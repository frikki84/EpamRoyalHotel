package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.ParametrName;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class AdminFindUserDetails implements Command {
	public static final String ADMIN_CLIENT_PAGE_WITHOUT_CHECK_IN = "/WEB-INF/jspPages/admin_personal_page_only.jsp";	
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command=error_page";

	private static final Logger logger = LogManager.getLogger(AdminFindUserDetails.class);

	ServiceProvider seviceProvider = ServiceProvider.getInstance();

	private UserdetailService userdetailService = seviceProvider.getUserDetailService();
	private UserService userService = seviceProvider.getUserService();
	private CountryService countryService = seviceProvider.getCountryService();


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientCode = (int) request.getSession().getAttribute("client_code");

		
		try {
			UserDetail userDetailFromDB = userdetailService.findUserDetails(clientCode);
			request.setAttribute("userDetail", userDetailFromDB);
			
			User userToJsp = userService.takePhoneEmailFromDb(clientCode);
			request.setAttribute("userPhoneTmail", userToJsp);
			
			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);
			
			
			String adress = ParametrName.ADMIN_FIND_CLIENT_DETAILS.toString();

			request.setAttribute("address", adress);

			request.getRequestDispatcher(ADMIN_CLIENT_PAGE_WITHOUT_CHECK_IN).forward(request, response);
		
			
			
		} catch (ServiceException e) {
			logger.error("AdminFindUserDetails ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);

		} catch (Exception e) {
			logger.error("AdminFindUserDetails Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}


	}

}
