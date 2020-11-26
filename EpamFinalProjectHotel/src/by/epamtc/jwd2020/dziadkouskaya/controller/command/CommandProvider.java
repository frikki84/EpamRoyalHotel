package by.epamtc.jwd2020.dziadkouskaya.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminAddGuestInfo;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminAddNewClient;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminCheckInClient;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminCheckInPayment;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminCheckOutClient;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminCheckOutList;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminCheckOutListWithDate;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminClientBookingList;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminClientNewBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminClientWasCheckedIn;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminFindUserToCheckIn;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminGoToAddCientGuests;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminGoToClientAdding;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminGoToClientPersonalPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminGoToPrices;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminGoToPricesAddPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminListForCleaners;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminPriceAdd;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminDeletePrice;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminUserBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.AdminWelcomeNewClient;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.ChangeLocale;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.Contacts;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.DeleteUser;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToBookingHistory;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToBookingPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToErrorPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToFirstPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToLoginationPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.GoToRegistrationPage;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.LocaleChange;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.NewRoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.PaidPrepayment;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.ResultBookingList;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UpdateClientDetailWithoutBooking;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UpdateClientDetails;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UpdateSecurity;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UpdateUser;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UpdateUserDetails;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UserExit;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UserLogination;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UserRegistration;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.UserWasDeleted;
import by.epamtc.jwd2020.dziadkouskaya.controller.command.impl.WelcomeNewUser;


public class CommandProvider {
	
	private static final CommandProvider instance = new CommandProvider();
	
	private Map<ParametrName, Command> commands = new HashMap<>();
	ParametrName parametrName;
	
	public CommandProvider() {
		//первый +/- адрес, второй - логгер
		commands.put(ParametrName.GO_TO_FIRST_PAGE, new GoToFirstPage());//+ +
		commands.put(ParametrName.GO_TO_LOGINATION_PAGE, new GoToLoginationPage());//+-
		commands.put(ParametrName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());//+ -
		commands.put(ParametrName.USER_REGISTRATION, new UserRegistration());//- +
		commands.put(ParametrName.WELCOME_NEW_USER, new WelcomeNewUser());//+ +
		commands.put(ParametrName.USER_LOGINATION, new UserLogination()); //+ + 
		commands.put(ParametrName.UPDATE_USER_DETAILS, new UpdateUserDetails());//+ +
		commands.put(ParametrName.UPDATE_USER, new UpdateUser());//+ +
		commands.put(ParametrName.UPDATE_SECURITY, new UpdateSecurity()); //-+
		commands.put(ParametrName.DELETE_USER, new DeleteUser());//- - 
		commands.put(ParametrName.USER_WAS_DELETED, new UserWasDeleted());//- + 
		commands.put(ParametrName.BOOKING_PAGE, new GoToBookingPage());//- + 
		commands.put(ParametrName.RESULT_BOOKING_LIST, new ResultBookingList());
		commands.put(ParametrName.BOOKING_HISTORY, new GoToBookingHistory());//+ + 
		commands.put(ParametrName.EXIT_TO_MAIN_PAGE, new UserExit());//--
		commands.put(ParametrName.NEW_ROOM_BOOKING,  new NewRoomBooking());// + + 
		commands.put(ParametrName.PREPAIMENT_PAID, new PaidPrepayment());//++
		commands.put(ParametrName.ADMIN_USER_BOOKING, new AdminUserBooking());//++
		commands.put(ParametrName.ADMIN_CLIENT_NEW_BOOKING, new AdminClientNewBooking());
		commands.put(ParametrName.ADMIN_FIND_USER_TO_CHECK_IN, new AdminFindUserToCheckIn());//+ - 
		commands.put(ParametrName.ADMIN_GO_TO_ADDING_NEW_CLIENT, new AdminGoToClientAdding());
		commands.put(ParametrName.ADMIN_WELCOME_NEW_CLIENT, new AdminWelcomeNewClient());
		commands.put(ParametrName.ADMIN_ADD_NEW_CLIENT, new AdminAddNewClient());
		commands.put(ParametrName.ADMIN_CLIENT_BOOKING_LIST, new AdminClientBookingList());
		commands.put(ParametrName.ADMIN_CHECK_IN_CLIENT, new AdminCheckInClient());// + +
		commands.put(ParametrName.ADMIN_GO_TO_CLIENT_PERSONAL_PAGE, new AdminGoToClientPersonalPage());
		commands.put(ParametrName.UPDATE_CLIENT_DETAILS, new UpdateClientDetails());//+ +
		commands.put(ParametrName.UPDATE_CLIENT_DETAIL_WITHOUT_BOOKING, new UpdateClientDetailWithoutBooking());
		commands.put(ParametrName.ADMIN_CHECK_IN_PAYMENT, new AdminCheckInPayment());
		commands.put(ParametrName.ADMIN_GO_TO_ADDING_NEW_GUESTS, new AdminGoToAddCientGuests());
		commands.put(ParametrName.ADMIN_ADD_NEW_GUEST, new AdminAddGuestInfo());//- + 
		commands.put(ParametrName.ADMIN_CLIENT_WAS_CHECKED_IN, new AdminClientWasCheckedIn());//++
		commands.put(ParametrName.ADMIN_CHECK_OUT_LIST, new AdminCheckOutList());//++
		commands.put(ParametrName.ADMIN_CHECK_OUT_LIST_WITH_DATE, new AdminCheckOutListWithDate());//++
		commands.put(ParametrName.ADMIN_CHECK_OUT_CLIENT_FINAL, new AdminCheckOutClient());//++
		commands.put(ParametrName.ADMIN_CLEANER_LIST, new AdminListForCleaners());//++
		commands.put(ParametrName.ADMIN_GO_TO_PRICES, new AdminGoToPrices());//++
		commands.put(ParametrName.ADMIN_PRICES_DELETE, new AdminDeletePrice());//++
		commands.put(ParametrName.ADMIN_PRICE_GO_TO_ADDING_PAGE, new AdminGoToPricesAddPage());//++
		commands.put(ParametrName.ADMIN_PRICES_ADD, new AdminPriceAdd());//++
		commands.put(ParametrName.Go_TO_CONTACTS, new Contacts());
		commands.put(ParametrName.LOCALE_CHANGE, new LocaleChange());//-+
		commands.put(ParametrName.ERROR_PAGE, new GoToErrorPage());//- + 
		
	}
	
	public Command getCommand(String commandName) {
		Command command;
		ParametrName parametrName;
		
		commandName = commandName.toUpperCase();
		
		parametrName = ParametrName.valueOf(commandName);
		
		command = commands.get(parametrName);
		
		return command;
	}
	
	public static CommandProvider getInstance() {
		return instance;
	}
	
	
	
	

}
