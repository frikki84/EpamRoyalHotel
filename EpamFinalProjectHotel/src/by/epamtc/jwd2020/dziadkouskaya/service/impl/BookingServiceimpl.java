
package by.epamtc.jwd2020.dziadkouskaya.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epamtc.jwd2020.dziadkouskaya.bean.BabyExpense;
import by.epamtc.jwd2020.dziadkouskaya.bean.BookingCheck;
import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.FinalCheckBeforeClientLeaving;
import by.epamtc.jwd2020.dziadkouskaya.bean.InfoForCleanersTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.Prepayment;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.dao.BookingDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoProvider;
import by.epamtc.jwd2020.dziadkouskaya.dao.impl.CountryDaoImpl;
import by.epamtc.jwd2020.dziadkouskaya.service.BookingService;
import by.epamtc.jwd2020.dziadkouskaya.service.ServiceException;
import by.epamtc.jwd2020.dziadkouskaya.service.validation.DateOperations;

public class BookingServiceimpl implements BookingService {

	private static DaoProvider daoProvider = DaoProvider.getInstance();
	private BookingDao bookingDao = daoProvider.getBookingDao();
	
	private static final Logger logger = LogManager.getLogger(BookingServiceimpl.class);

	@Override
	public List<RoomBooking> findFreeRooms(int userId, int peopleNumber, int childrenNumber, Date startDate,
			Date endDate) throws ServiceException {
		List<RoomBooking> resultList = null;

		BabyExpense babyExpense = null;
		Prepayment prepayment = null;
		double sumForLiving = 0;
		double childrenSum = 0;
		double prepaymentSum = 0;

		try {
			resultList = bookingDao.findFreeRooms(userId, peopleNumber, childrenNumber, startDate, endDate);
			
			List<Date> livingInHotelDatesList = createDateListBetweenTwoDates(startDate, endDate);
	

			for (RoomBooking booking : resultList) {	
				Map<Date, Double> priceMap = bookingDao.findRoomPrice(booking);

				for (Date date : livingInHotelDatesList) {
					
					double pricePerDay = findPricePerDay(priceMap, date);
					sumForLiving += pricePerDay;
				}

				babyExpense = bookingDao.findBabyExpense();
				prepayment = bookingDao.findPrepayment();
				childrenSum = findCheckValueBabyExpense(booking, babyExpense);
				prepaymentSum = findCheckValuePrepayment(sumForLiving, childrenSum, prepayment);

				booking.setBasicPayment(sumForLiving);
				booking.setBabyExpense(babyExpense);
				booking.setBabyExpenceSum(childrenSum);
				booking.setPrepayment(prepayment);
				booking.setPrepaymentSum(prepaymentSum);

				sumForLiving = 0;
				childrenNumber = 0;
				prepaymentSum = 0;

			}

		} catch (DaoException e) {
			logger.error("Error in finding free rooms", e);
			throw new ServiceException("Error in finding free rooms", e);
		}
		return resultList;
	}

	@Override
	public double findPricePerDay(Map<Date, Double> map, Date date) {
	
		double taxPerDay = 0.0;
		TreeMap<Date, Double> sortedPricesByDates = new TreeMap<>(map);

		Date finalDate = sortedPricesByDates.firstKey();

		for (Date item : sortedPricesByDates.keySet()) {
			long middleSubtract = DateOperations.subtractDays(item, date);
			
			if (taxPerDay < 1.0 & middleSubtract < 0) {
				taxPerDay = sortedPricesByDates.get(finalDate);

			}

			finalDate = item;

		}
		if (taxPerDay < 1.0) {
			finalDate = sortedPricesByDates.lastKey();
			taxPerDay = sortedPricesByDates.get(finalDate);

		}

		return taxPerDay;
	}

	public List<Date> createDateListBetweenTwoDates(Date startDate, Date endDate) {
		List<Date> datesInRange = new ArrayList<>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startDate);

		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDate);

		while (calendar.before(endCalendar)) {
			Date result = new Date((calendar.getTime().getTime()));
			datesInRange.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return datesInRange;
	}

	public void addNewBooking(RoomBooking roomBooking) throws ServiceException {
		try {
			bookingDao.addNewBooking(roomBooking);
		} catch (DaoException e) {
			logger.error("Error in adding new booking", e);
			throw new ServiceException("Error in adding new roonm booking", e);
		}
	}

	private double findCheckValueBabyExpense(RoomBooking booking, BabyExpense babyExpense) {
		long livingPeriod = DateOperations.subtractDays(booking.getStartDate(), booking.getEndDate());
		double resultValue = booking.getChildrenNumber() * babyExpense.getBabyExpenseValuePerDay() * livingPeriod;

		return resultValue;
	}

	private double findCheckValuePrepayment(double basicPayment, double babyExpenseValue, Prepayment prepayment) {

		double resultValue = (basicPayment + babyExpenseValue) * prepayment.getPrepaymentCoefficient();

		return resultValue;
	}

	@Override
	public List<BookingTransferObject> findUserBookings(int userId) throws ServiceException {
		List<BookingTransferObject> finalList = null;
		try {
			finalList = bookingDao.findUserBookings(userId);
		} catch (DaoException e) {
			logger.error("Error in finding user's bookings", e);
			throw new ServiceException("Error in finding user's bookings", e);
		}
		return finalList;
	}

	@Override
	public void makePrepaymentPaid(int bookingId) throws ServiceException {
		try {
			bookingDao.makePrepaymentPaid(bookingId);
			
		} catch (DaoException e) {
			logger.error("Error in making prepayment paid", e);
			throw new ServiceException("Error in making prepayment paid", e);
		}

	}

	@Override
	public double findFinalBookingSum(int bookingId) throws ServiceException {
		double finalSum = 0;

		RoomBooking roomBooking = findRoomBookingById(bookingId);

		if (roomBooking.isPrepaymentPaid()) {
			finalSum = roomBooking.getBasicPayment() + roomBooking.getBabyExpenceSum() - roomBooking.getPrepaymentSum();

		} else {
			finalSum = roomBooking.getBasicPayment() + roomBooking.getBabyExpenceSum();
		}

		return finalSum;
	}

	@Override
	public RoomBooking findRoomBookingById(int bookingId) throws ServiceException {
		RoomBooking roomBooking = null;
		try {
			roomBooking = bookingDao.findRoomBookingById(bookingId);
		} catch (DaoException e) {
			logger.error("Error in finding room booking", e);
			throw new ServiceException("Error in finding room booking", e);
		}

		return roomBooking;
	}

	@Override
	public int findGuestNumberWithoutUser(int bookingId) throws ServiceException {
		int people = 0;
		try {
			people = bookingDao.findPeopleNumberByBookingId(bookingId) - 1;

		} catch (DaoException e) {
			logger.error("Error in finding people number", e);
			throw new ServiceException("Error in finding people number", e);
		}
		return people;
	}

	@Override
	public void checkInClientWithPayment(int bookingId) throws ServiceException {
		FinalCheckBeforeClientLeaving leaving = new FinalCheckBeforeClientLeaving();

		RoomBooking booking = null;

		try {
			booking = bookingDao.findRoomBookingById(bookingId);
			Date finalDate = booking.getEndDate();

			leaving.setLeavingDate(finalDate);
			leaving.setBooking(booking);
			leaving.setPersonLiving(true);

			bookingDao.checkInClientWithPayment(leaving);
		} catch (DaoException e) {
			logger.error("Error in checking client", e);
			throw new ServiceException("Error in checking client", e);
		}

	}

	@Override
	public void checkInClientWithoutPayment(int bookingId) throws ServiceException {
		FinalCheckBeforeClientLeaving leaving = new FinalCheckBeforeClientLeaving();

		RoomBooking booking = null;

		try {
			booking = bookingDao.findRoomBookingById(bookingId);
			Date finalDate = booking.getEndDate();

			leaving.setLeavingDate(finalDate);
			leaving.setBooking(booking);
			leaving.setPersonLiving(true);
			bookingDao.checkInClientWithoutPayment(leaving);

		} catch (DaoException e) {
			logger.error("Error in checking client", e);
			throw new ServiceException("Error in checking client", e);
		}

	}

	@Override
	public List<CheckOutTransferObject> findInfoForCheckingOut(Date date) throws ServiceException {
		List<CheckOutTransferObject> finalList = null;

		try {
			finalList = bookingDao.findInfoForCheckingOut(date);
		} catch (DaoException e) {
			throw new ServiceException("Error in check out client", e);
		}
		return finalList;
	}

	@Override
	public void checkOutClient(int bookingId) throws ServiceException {
		try {
			bookingDao.checkOutClient(bookingId);
		} catch (DaoException e) {
			logger.error("Error in check out client", e);
			throw new ServiceException("Error in check out client", e);
		}
	}

	@Override
	public List<InfoForCleanersTransferObject> prepareListForCleaners(Date date) throws ServiceException {
		List<InfoForCleanersTransferObject> cleanersList = new ArrayList<>();
		
		try {
			List<RoomBooking> arrivalClientsList = bookingDao.findFreeRoomsAfterNessecaryDate(date);

			List<RoomBooking> depatchureClientList = bookingDao.findBusyRoomsAfterNessecaryDate(date);

			ArrayList<String> roomNumberList = new ArrayList<>();
			
			for (RoomBooking booking : arrivalClientsList) {
				cleanersList.add(new InfoForCleanersTransferObject(booking.getRoom().getHotelRoomNumber(), booking.getRoom().getRoomCategory().getRoomCategoryName(), true));
				roomNumberList.add(booking.getRoom().getHotelRoomNumber());
			}

			for (InfoForCleanersTransferObject object : cleanersList) {
				for (RoomBooking booking : depatchureClientList) {
					if (object.getRoomNumber().equalsIgnoreCase(booking.getRoom().getHotelRoomNumber())) {
						object.setCleanBeforeDepatchure(true);
					}
				}
			}
			
			for (RoomBooking booking : depatchureClientList) {
				if (!roomNumberList.contains(booking.getRoom().getHotelRoomNumber())) {
					cleanersList.add(new InfoForCleanersTransferObject(booking.getRoom().getHotelRoomNumber(), booking.getRoom().getRoomCategory().getRoomCategoryName(), false, true));
				}
			}			
			

		} catch (DaoException e) {
			logger.error("Error in preparing list for cleaners", e);
			throw new ServiceException("Error in preparing list for cleaners", e);
		}

		return cleanersList;
	}
}
