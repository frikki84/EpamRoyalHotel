package by.epamtc.jwd2020.dziadkouskaya.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.ClientCategory;
import by.epamtc.jwd2020.dziadkouskaya.bean.Country;
import by.epamtc.jwd2020.dziadkouskaya.bean.User;
import by.epamtc.jwd2020.dziadkouskaya.bean.UserDetail;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.Command;
import by.epamtc.jwd2020.dziadkouskaya.service.CountryService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceProvider;
import by.epamtc.jwd2020.dziadkouskaya.service.UserService;
import by.epamtc.jwd2020.dziadkouskaya.service.UserdetailService;

public class UpdateSecurity implements Command {
	public static final String WRONG_OLD_PASSWORD = "Wrong password. Please, repeat it.";
	public static final String PASSWORDS_NOT_MATCH = "New passwords don't match";
	public static final ClientCategory DEFAULT_CLIENT_CATEGORY = new ClientCategory(1, "Клиент-заказчик");

	public static final String PATH_TO_PERSONAL_DATA_PAGE = "/WEB-INF/jspPages/client_personal_data_page.jsp";
	public static final String PATH_TO_ERROR_PAGE = "mainPage?command = go_to_error_page";

	ServiceProvider seviceProvider = ServiceProvider.getInstance();
	UserService userService = seviceProvider.getUserService();
	UserdetailService userdetailService = seviceProvider.getUserDetailService();
	CountryService countryService = seviceProvider.getCountryService();
	
	
	private static final Logger logger = LogManager.getLogger(UpdateSecurity.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int) request.getSession().getAttribute("user_code");

		String oldPAssword = request.getParameter("oldPassword");

		String newPassword = "";
		String newPasswordRepeat = "";

		String wrongPassword = "";

		try {
			String passwordDb = userService.findPasswordByUserId(userId);
			
			boolean checkOldPasswordWithDb = userService.checkNewUserPassword(oldPAssword, passwordDb);

			if (!checkOldPasswordWithDb) {
				wrongPassword = WRONG_OLD_PASSWORD;
				request.setAttribute("wrong_password", wrongPassword);
			} else {
				
				newPassword = request.getParameter("newPassword");
				newPasswordRepeat = request.getParameter("newPasswordRepeat");
				boolean checkRepeatedPasswords = userService.checkNewUserPassword(newPassword, newPasswordRepeat);
				
				if (!checkRepeatedPasswords) {
					wrongPassword = PASSWORDS_NOT_MATCH;
					request.setAttribute("wrong_password", wrongPassword);
					
				} else {
					userService.updatePassword(userId, newPassword);
				}
			}
			
			List<Country> countryList = countryService.findCountryList();
			request.setAttribute("countryList", countryList);
			
			UserDetail userDetailFromDB = userdetailService.findUserDetails(userId);

			if (userDetailFromDB != null && userDetailFromDB.getCountry() != null) {
				Country countryM = userDetailFromDB.getCountry();

				for (Country c : countryList) {
					if (countryM.getId() == c.getId()) {
						countryM.setName(c.getName());
					}
				}
				userDetailFromDB.setCountry(countryM);
			}

			request.setAttribute("userDetail", userDetailFromDB);

			User user = userService.takePhoneEmailFromDb(userId);

			request.setAttribute("userPhoneTmail", user);

			request.getRequestDispatcher(PATH_TO_PERSONAL_DATA_PAGE).forward(request, response);

		} catch (ServiceException e) {
			logger.error("UpdateSecurity ServiceException", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
			
		} catch (Exception e) {
			logger.error("UpdateSecurity Exception", e);
			response.sendRedirect(PATH_TO_ERROR_PAGE);
		}

	}
}
