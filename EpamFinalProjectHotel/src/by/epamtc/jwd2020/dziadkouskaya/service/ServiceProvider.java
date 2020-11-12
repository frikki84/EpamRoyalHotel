package by.epamtc.jwd2020.dziadkouskaya.service;

import by.epamtc.jwd2020.dziadkouskaya.service.impl.BookingServiceimpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.CountryServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.PriceServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.RoleServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.RoomCategoryServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.UserDetailServiceImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.impl.UserServiceImpl;

public class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	
	private final RoleService roleService = new RoleServiceImpl();
	private final UserService userService = new UserServiceImpl();
	private final CountryService countryService = new CountryServiceImpl();
	private final UserDetailServiceImpl userDetailService = new UserDetailServiceImpl();
	private final RoomCategoryService roomCategoryService = new RoomCategoryServiceImpl();
	private final BookingService bookingService = new BookingServiceimpl();
	private final PriceService priceService = new PriceServiceImpl();
	
	public ServiceProvider() {
		
	}

	public static ServiceProvider getInstance() {
		return instance;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public UserService getUserService() {
		return userService;
	}
	
	

	public CountryService getCountryService() {
		return countryService;
	}

	public UserDetailServiceImpl getUserDetailService() {
		return userDetailService;
	}

	public RoomCategoryService getRoomCategoryService() {
		return roomCategoryService;
	}

	public BookingService getBookingService() {
		return bookingService;
	}

	public PriceService getPriceService() {
		return priceService;
	}
	
	

	
}
