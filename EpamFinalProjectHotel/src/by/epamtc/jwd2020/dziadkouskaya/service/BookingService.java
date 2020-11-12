package by.epamtc.jwd2020.dziadkouskaya.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingCheck;
import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.InfoForCleanersTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;

public interface BookingService {
	public List<RoomBooking> findFreeRooms(int userId, int peopleNumber, int childrenNumber, Date startDate,
			Date endDate) throws ServiceException;

	public double findPricePerDay(Map<Date, Double> map, Date date) throws ServiceException;

	public void addNewBooking(RoomBooking roomBooking) throws ServiceException;

	public List<BookingTransferObject> findUserBookings(int userId) throws ServiceException;
	
	public void makePrepaymentPaid(int bookingId) throws ServiceException;
	
	public double findFinalBookingSum(int bookingId) throws ServiceException;
	
	public RoomBooking findRoomBookingById(int bookingId) throws ServiceException;
	public int findGuestNumberWithoutUser(int bookingId) throws ServiceException;
	
	public void checkInClientWithPayment(int bookingId) throws ServiceException;
	
	public void checkInClientWithoutPayment(int bookingId) throws ServiceException;
	
	public List<CheckOutTransferObject> findInfoForCheckingOut(Date date) throws ServiceException;
	
	public void checkOutClient(int bookingId) throws ServiceException;
	
	public List<InfoForCleanersTransferObject> prepareListForCleaners(Date date)  throws ServiceException;;
	
 
	
}
