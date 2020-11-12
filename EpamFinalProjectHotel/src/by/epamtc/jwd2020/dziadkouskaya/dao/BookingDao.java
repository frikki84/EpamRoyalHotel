package by.epamtc.jwd2020.dziadkouskaya.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import by.epamtc.jwd2020.dziadkouskaya.bean.BabyExpense;
import by.epamtc.jwd2020.dziadkouskaya.bean.BookingCheck;
import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.FinalCheckBeforeClientLeaving;
import by.epamtc.jwd2020.dziadkouskaya.bean.Prepayment;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;

/**
 * interface work with table magichotel.room_booking work with users bookings
 * 
 * @author Yana Dziadkouskaya
 */

public interface BookingDao {
	/**
	 * add new booking to magichotel.room_booking
	 * 
	 * @param roomBooking - RoomBooking object with necessary information
	 * @throws DaoException {@link DaoException}
	 */
	public void addNewBooking(RoomBooking roomBooking) throws DaoException;

	/**
	 * find all rooms that are free for the entire time the user wants to stay at
	 * the hotel	  
	 * @param userId - id of User who find free rooms
	 * @param peopleNumber - capacity of room
	 * @param childrenNumber - number of children before years 
	 * @param startDate - starting date of stay at the hotel
	 * @param endDate - ending date of stay at the hotel
	 * @return - List of free rooms
	 * @throws DaoException {@link DaoException}
	 */

	public List<RoomBooking> findFreeRooms(int userId, int peopleNumber, int childrenNumber, Date startDate,
			Date endDate) throws DaoException;

	/**
	 * 
	 * @param roomBooking
	 * @return
	 * @throws DaoException
	 */
	public Map<Date, Double> findRoomPrice(RoomBooking roomBooking) throws DaoException;

	public int findBookingIdByBookingInfo(RoomBooking roomBooking) throws DaoException;

	public BabyExpense findBabyExpense() throws DaoException;

	public Prepayment findPrepayment() throws DaoException;

	public List<BookingTransferObject> findUserBookings(int userId) throws DaoException;

	public void makePrepaymentPaid(int bookingId) throws DaoException;

	public RoomBooking findRoomBookingById(int bookingId) throws DaoException;

	public int findPeopleNumberByBookingId(int bookingId) throws DaoException;

	public void checkInClientWithPayment(FinalCheckBeforeClientLeaving leaving) throws DaoException;

	public void checkInClientWithoutPayment(FinalCheckBeforeClientLeaving leaving) throws DaoException;

	public List<CheckOutTransferObject> findInfoForCheckingOut(Date date) throws DaoException;

	public void checkOutClient(int bookingId) throws DaoException;

	public List<RoomBooking> findFreeRoomsAfterNessecaryDate(Date date) throws DaoException;

	public List<RoomBooking> findBusyRoomsAfterNessecaryDate(Date date) throws DaoException;

}
