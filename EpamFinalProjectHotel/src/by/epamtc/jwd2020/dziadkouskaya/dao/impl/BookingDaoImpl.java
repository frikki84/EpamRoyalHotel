package by.epamtc.jwd2020.dziadkouskaya.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import by.epamtc.jwd2020.dziadkouskaya.bean.BabyExpense;

import by.epamtc.jwd2020.dziadkouskaya.bean.BookingTransferObject;
import by.epamtc.jwd2020.dziadkouskaya.bean.CheckOutTransferObject;

import by.epamtc.jwd2020.dziadkouskaya.bean.FinalCheckBeforeClientLeaving;
import by.epamtc.jwd2020.dziadkouskaya.bean.Prepayment;
import by.epamtc.jwd2020.dziadkouskaya.bean.Room;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomBooking;
import by.epamtc.jwd2020.dziadkouskaya.bean.RoomCategory;
import by.epamtc.jwd2020.dziadkouskaya.dao.BookingDao;
import by.epamtc.jwd2020.dziadkouskaya.dao.DaoException;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPool;
import by.epamtc.jwd2020.dziadkouskaya.dao.connection_pool.ConnectionPoolError;

public class BookingDaoImpl implements BookingDao {
	public static final String STRING_FIND_FREE_ROOMS_FIRST_PART = "select r.idHotelRoom, r.HotelRoomNumber, r. HotelRoomFloor, r.idRoomCategory,  rc.HotelRoomCategoryName, rc.ParentCategory, rc.PeopleNumberInRoom"
			+ " from  rooms r join  room_category rc on r.idRoomCategory=rc.idHotelRoomCategory \r\n"
			+ "where rc.PeopleNumberInRoom =";
	public static final String STRING_FIND_FREE_ROOMS_SECOND_PART = "  AND r.idHotelRoom not in\r\n"
			+ "(select HotelRoom_idHotelRoom\r\n" + "from room_booking\r\n"
			+ "where IsBookingConfirmed is true AND (BookingStartDate <= \"";
	public static final String STRING_FIND_FREE_ROOMS_THIRD_PART = "\" AND BookingEndDate >= \"";
	public static final String STRING_FIND_FREE_ROOMS_FOURTH_PART = "\"));";

	public static final String STRING_TO_FIND_ROOM_PRICE = "select rp.RoomPriceStartDate, rp.`RoomPrice, rub/day` "
			+ "from rooms r join room_category rc on rc.idHotelRoomCategory = r.idRoomCategory "
			+ "join room_price rp on rp.idRoomCategory = rc.idHotelRoomCategory " + "where r.idHotelRoom = ";

	public static final String STRING_TO_FIND_ROOM_PRICE_LAST_PART = " order by rp.RoomPriceStartDate;";

	public static final String STRING_FOR_INSERT_NEW_BOOKING = "insert into room_booking (BookingStartDate, BookingEndDate, "
			+ "BookingPeopleNumber, BabyCotNumber, User_idUser, HotelRoom_idHotelRoom, IsBookingConfirmed, BookingDate, "
			+ "idBabyExpense, idPrepayment, CheckBasicPayment, BabyExpenseSum, PrepaymentSum, isPrepaymentPaid, isBookingPaid) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	public static final String STRING_FIND_ROOM_BOOKING_ID_1 = "select idRoomBooking\r\n"
			+ "from room_booking where BookingStartDate=\"";
	public static final String STRING_FIND_ROOM_BOOKING_ID_2 = "\" and BookingEndDate=\"";
	public static final String STRING_FIND_ROOM_BOOKING_ID_3 = "\" and BookingPeopleNumber=";
	public static final String STRING_FIND_ROOM_BOOKING_ID_4 = " and BabyCotNumber=";
	public static final String STRING_FIND_ROOM_BOOKING_ID_5 = " and User_idUser = ";
	public static final String STRING_FIND_ROOM_BOOKING_ID_6 = " and HotelRoom_idHotelRoom=";
	public static final String STRING_FIND_ROOM_BOOKING_ID_7 = " and IsBookingConfirmed= ";
	public static final String STRING_FIND_ROOM_BOOKING_ID_8 = " and BookingDate=\"";
	public static final String STRING_FIND_ROOM_BOOKING_ID_9 = "\";";
	public static final String STRING_FIND_NECESSARY_CHILD_EXPENSE = "select * from baby_expence where BabyExpenseName like \"Кроватка для ребенка до 4 лет\" and BabyExpenseStartDate = \"2020-01-01\";";
	public static final String STRING_FIND_NECESSARY_PREPAYMENT = "select * from prepayment where PrepaymentName like \"Стандартная предоплата за номер\";";
	public static final String STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED = "update room_booking set IsBookingConfirmed = ? where idRoomBooking =";
	public static final String STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2 = ";";
	public static final String STRING_FIND_BOOKING_HISTORY = "select rb.idRoomBooking, rb.BookingStartDate, rb.BookingEndDate, r.HotelRoomNumber, rc.HotelRoomCategoryName, rb.BookingPeopleNumber, rb.BabyCotNumber, rb.CheckBasicPayment+rb.BabyExpenseSum, rb.PrepaymentSum, rb.isPrepaymentPaid, rb.isBookingPaid from room_booking rb join rooms r on rb.HotelRoom_idHotelRoom = r.idHotelRoom  join room_category rc on rc.idHotelRoomCategory=r.idRoomCategory where IsBookingConfirmed is true and rb.User_idUser =";
	public static final String STRING_MAKE_PREPAYMENT_PAID = "update room_booking set isPrepaymentPaid = true where idRoomBooking =";
	public static final String STRING_FIND_ROOMBOOKING_BY_ID = "select * from room_booking where idRoomBooking =";
	public static final String STRING_FIND_PEOPLE_NUMBER_BY_BOOKING_ID = "select BookingPeopleNumber from room_booking where idRoomBooking=";
	public static final String STRING_MAKE_LIVING_SUM_PAID = "update room_booking set isBookingPaid = true where idRoomBooking =";
	public static final String STRING_MAKE_CLIENT_LIVING_IN_HOTEL = "insert into finalcheckbeforleaving (FinalCheckDate, idRoomBooking, isClientinHotel) values (?, ?, ?);";
	public static final String STRING_FIND_BOOKINGS_FOR_CHECK_OUT = "select rb.idRoomBooking, r.HotelRoomNumber, rb.BookingStartDate, rb.BookingEndDate, ud.UserName, ud.UserThirdName, ud.UserSurname, fc.isClientinHotel from room_booking rb join rooms r on rb.HotelRoom_idHotelRoom = r.idHotelRoom join users u on rb.User_idUser = u.idUser join user_details ud on ud.User_idUser=u.idUser join finalcheckbeforleaving fc on fc.idRoomBooking=rb.idRoomBooking where ud.userCategory = 1 AND rb.BookingEndDate = \"";
	public static final String STRING_CLIENT_FINAL_CHECK_OUT = "update finalcheckbeforleaving set isClientinHotel=false where idRoomBooking =";

	public static final String STRING_FIND_ROOMS_FREE_AFTER_DATE = "select rb.idRoomBooking, rb.BookingStartDate, rb.BookingEndDate, r.HotelRoomNumber, rc.HotelRoomCategoryName, r.idRoomCategory, rc.ParentCategory from room_booking rb join rooms r on  rb.HotelRoom_idHotelRoom = r.idHotelRoom join room_category rc on rc.idHotelRoomCategory=r.idRoomCategory where (rb.isPrepaymentPaid is true or rb.isBookingPaid is true) and BookingEndDate = \"";
	public static final String STRING_FIND_ROOMS_BUSY_AFTER_DATE = "select rb.idRoomBooking, rb.BookingStartDate, rb.BookingEndDate, r.HotelRoomNumber, rc.HotelRoomCategoryName, r.idRoomCategory, rc.ParentCategory from room_booking rb join rooms r on  rb.HotelRoom_idHotelRoom = r.idHotelRoom join room_category rc on rc.idHotelRoomCategory=r.idRoomCategory where (rb.isPrepaymentPaid is true or rb.isBookingPaid is true) and BookingStartDate = \"";
	public static final String STRING_FIND_ROOMS_FREE_BUSY_AFTER_DATE_LAST_PART = "\" order by r.HotelRoomNumber;";
	public static final String String_WITH_QUOTE_AND_SEMICOLON = "\";";

	private static final Logger logger = LogManager.getLogger(BookingDaoImpl.class);

	private final ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void addNewBooking(RoomBooking roomBooking) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(STRING_FOR_INSERT_NEW_BOOKING);

			ps.setDate(1, roomBooking.getStartDate());
			ps.setDate(2, roomBooking.getEndDate());
			ps.setInt(3, roomBooking.getAdultPeopleNumber());
			ps.setInt(4, roomBooking.getChildrenNumber());
			ps.setInt(5, roomBooking.getUserId());
			ps.setInt(6, roomBooking.getRoom().getId());
			ps.setBoolean(7, roomBooking.isBookingConfirmed());
			ps.setDate(8, roomBooking.getBookingDate());
			ps.setInt(9, roomBooking.getBabyExpense().getId());
			ps.setInt(10, roomBooking.getPrepayment().getId());
			ps.setDouble(11, roomBooking.getBasicPayment());
			ps.setDouble(12, roomBooking.getBabyExpenceSum());
			ps.setDouble(13, roomBooking.getPrepaymentSum());
			ps.setBoolean(14, roomBooking.isPrepaymentPaid());
			ps.setBoolean(15, roomBooking.isBookingPaid());

			ps.executeUpdate();

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in adding new booking", e);

			throw new DaoException("Error in adding new booking", e);
		}

		finally {

				connectionPool.closeConnection(connection, ps);


		}
	}

	@Override
	public int findBookingIdByBookingInfo(RoomBooking roomBooking) throws DaoException {
		int resultId = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;
		
		try {

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_ROOM_BOOKING_ID_1 + roomBooking.getStartDate() + STRING_FIND_ROOM_BOOKING_ID_2
				+ roomBooking.getEndDate() + STRING_FIND_ROOM_BOOKING_ID_3 + roomBooking.getAdultPeopleNumber()
				+ STRING_FIND_ROOM_BOOKING_ID_4 + roomBooking.getChildrenNumber() + STRING_FIND_ROOM_BOOKING_ID_5
				+ roomBooking.getUserId() + STRING_FIND_ROOM_BOOKING_ID_6 + roomBooking.getRoom().getId()
				+ STRING_FIND_ROOM_BOOKING_ID_7 + roomBooking.isBookingConfirmed() + STRING_FIND_ROOM_BOOKING_ID_8
				+ roomBooking.getBookingDate() + STRING_FIND_ROOM_BOOKING_ID_9;
		
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				resultId = set.getInt(1);
			}
		} catch (SQLException e) {
			logger.error("Error in finding idRoomBooking", e);
			throw new DaoException("Error in finding idRoomBooking  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return resultId;
	}

	@Override
	public BabyExpense findBabyExpense() throws DaoException {
		BabyExpense babyExpense = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;
		ResultSetMetaData data;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_NECESSARY_CHILD_EXPENSE;
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				double price = set.getDouble(3);
				Date date = set.getDate(4);

				babyExpense = new BabyExpense(id, name, price, date);

			}
		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding BabyExpense ", e);
			throw new DaoException("Error in finding BabyExpense  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return babyExpense;

	}

	@Override
	public List<RoomBooking> findFreeRooms(int userId, int peopleNumber, int childrenNumber, Date startDate,
			Date endDate) throws DaoException {
		List<RoomBooking> bookingList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_FREE_ROOMS_FIRST_PART + peopleNumber + STRING_FIND_FREE_ROOMS_SECOND_PART + startDate
				+ STRING_FIND_FREE_ROOMS_THIRD_PART + endDate + STRING_FIND_FREE_ROOMS_FOURTH_PART;

		try {

			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int idRoom = set.getInt(1);
				String roomNumber = set.getString(2);
				int roomFloor = set.getInt(3);
				int idRoomCategory = set.getInt(4);
				String roomCategoryName = set.getString(5);
				int idRoomCategoryParent = set.getInt(6);
				int peopleNumberInRoom = set.getInt(7);

				RoomBooking booking = new RoomBooking();
				Room room = new Room();
				RoomCategory roomCategory = new RoomCategory();

				roomCategory.setId(idRoomCategory);
				roomCategory.setRoomCategoryName(roomCategoryName);
				roomCategory.setIdParentRoomCategory(idRoomCategoryParent);
				roomCategory.setPeopleNumberInRoom(peopleNumberInRoom);

				room.setId(idRoom);
				room.setHotelRoomNumber(roomNumber);
				room.setFloorNumber(roomFloor);
				room.setRoomCategory(roomCategory);

				booking.setStartDate(startDate);
				booking.setEndDate(endDate);
				booking.setAdultPeopleNumber(peopleNumber);
				booking.setChildrenNumber(childrenNumber);
				booking.setUserId(userId);
				booking.setRoom(room);
				booking.setBookingDate(new Date(System.currentTimeMillis()));

				bookingList.add(booking);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding Free Rooms  ", e);
			throw new DaoException("Error in finding Free Rooms  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return bookingList;

	}

	@Override
	public Map<Date, Double> findRoomPrice(RoomBooking roomBooking) throws DaoException {
		Map<Date, Double> resultMap = new HashMap<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		int roomId = roomBooking.getRoom().getId();

		String sql = STRING_TO_FIND_ROOM_PRICE + roomId + STRING_TO_FIND_ROOM_PRICE_LAST_PART;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				resultMap.put(set.getDate(1), set.getDouble(2));
			}


		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding Free Rooms", e);
			throw new DaoException("Error in finding Free Rooms  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return resultMap;

	}

	@Override
	public Prepayment findPrepayment() throws DaoException {
		Prepayment prepayment = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_NECESSARY_PREPAYMENT;
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				int id = set.getInt(1);
				String name = set.getString(2);
				Date startDate = set.getDate(3);
				double coeff = set.getDouble(5);

				prepayment = new Prepayment(id, name, coeff, startDate);

			}
		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding BabyExpense", e);
			throw new DaoException("Error in finding BabyExpense  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return prepayment;

	}

	@Override
	public List<BookingTransferObject> findUserBookings(int userId) throws DaoException {
		List<BookingTransferObject> userBookingList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();
		String sql = STRING_FIND_BOOKING_HISTORY + userId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int id = set.getInt(1);
				Date startDate = set.getDate(2);
				Date endDate = set.getDate(3);
				String room = set.getString(4);
				String roomCategoryName = set.getString(5);
				int adults = set.getInt(6);
				int children = set.getInt(7);
				double payment = set.getDouble(8);
				double prepayment = set.getDouble(9);
				boolean isPrepaiment = set.getBoolean(10);
				boolean isBookingPaid = set.getBoolean(11);

				BookingTransferObject transferObject = new BookingTransferObject(id, startDate, endDate, room,
						roomCategoryName, adults, children, payment, prepayment, isPrepaiment, isBookingPaid);
				userBookingList.add(transferObject);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding user history ", e);
			throw new DaoException("Error in finding user history  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return userBookingList;
	}

	@Override
	public void makePrepaymentPaid(int bookingId) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;
		connection = connectionPool.takeConnection();

		try {
			

			String sql = STRING_MAKE_PREPAYMENT_PAID + bookingId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;
			ps = connection.prepareStatement(sql);

			ps.executeUpdate();

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in making prepayment paid", e);
			throw new DaoException("Error in making prepayment paid", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}

	}

	@Override
	public RoomBooking findRoomBookingById(int bookingId) throws DaoException {
		RoomBooking booking = null;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		try {
			connection = connectionPool.takeConnection();
			String sql = STRING_FIND_ROOMBOOKING_BY_ID + bookingId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				Date startDate = set.getDate(2);
				Date endDate = set.getDate(3);
				int adultPeopleNumber = set.getInt(4);
				int childrenNumber = set.getInt(5);
				int userId = set.getInt(6);
				int roomId = set.getInt(7);
				boolean isBookingConfirmed = set.getBoolean(8);
				Date bookingDate = set.getDate(9);
				int babyExpenseId = set.getInt(10);
				int prepaymentId = set.getInt(11);
				double basicPayment = set.getDouble(12);

				double babyExpenceSum = set.getDouble(13);

				double prepaymentSum = set.getDouble(14);
				boolean isPrepaymentPaid = set.getBoolean(15);
				boolean isBookingPaid = set.getBoolean(16);

				booking = new RoomBooking(bookingId, startDate, endDate, adultPeopleNumber, childrenNumber, userId,
						new Room(roomId), bookingDate, isBookingConfirmed, basicPayment, new BabyExpense(babyExpenseId),
						babyExpenceSum, new Prepayment(prepaymentId), prepaymentSum, isPrepaymentPaid, isBookingPaid);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding roomBooking ", e);
			throw new DaoException("Error in finding roomBooking  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return booking;

	}

	@Override
	public int findPeopleNumberByBookingId(int bookingId) throws DaoException {
		int peopleNumber = 0;

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_PEOPLE_NUMBER_BY_BOOKING_ID + bookingId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;
		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			if (set.next()) {
				peopleNumber = set.getInt(1);
			}
		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding peopleNumber ", e);
			throw new DaoException("Error in finding peopleNumber  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return peopleNumber;
	}

	@Override
	public void checkInClientWithPayment(FinalCheckBeforeClientLeaving leaving) throws DaoException {
		makeSumForLivingPaid(leaving);
		checkInCkient(leaving);

	}

	@Override
	public void checkInClientWithoutPayment(FinalCheckBeforeClientLeaving leaving) throws DaoException {
		checkInCkient(leaving);

	}

	public void makeSumForLivingPaid(FinalCheckBeforeClientLeaving leaving) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		int bookingId = leaving.getBooking().getIdBooking();

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_MAKE_LIVING_SUM_PAID + bookingId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;
			ps = connection.prepareStatement(sql);

			ps.executeUpdate();

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in making living sum paid", e);
			throw new DaoException("Error in making living sum paid", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}

	}

	public void checkInCkient(FinalCheckBeforeClientLeaving leaving) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(STRING_MAKE_CLIENT_LIVING_IN_HOTEL);

			ps.setDate(1, leaving.getLeavingDate());
			ps.setInt(2, leaving.getBooking().getIdBooking());
			ps.setBoolean(3, leaving.isPersonLiving());

			ps.executeUpdate();

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in final check", e);
			throw new DaoException("Error in final check", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
	}

	@Override
	public List<CheckOutTransferObject> findInfoForCheckingOut(Date date) throws DaoException {
		List<CheckOutTransferObject> bookingList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_BOOKINGS_FOR_CHECK_OUT + date + STRING_FIND_ROOM_BOOKING_ID_9;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int idBooking = set.getInt(1);
				String roomNumber = set.getString(2);
				Date startDate = set.getDate(3);
				Date endDate = set.getDate(4);
				String name = set.getString(5);
				String thirdName = set.getString(6);
				String secondName = set.getString(7);
				boolean clientStatus = set.getBoolean(8);

				CheckOutTransferObject object = new CheckOutTransferObject(idBooking, roomNumber, startDate, endDate,
						name, thirdName, secondName, clientStatus);
				bookingList.add(object);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in check out user", e);
			throw new DaoException("Error in check out user  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return bookingList;
	}

	@Override
	public void checkOutClient(int bookingId) throws DaoException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();

			String sql = STRING_CLIENT_FINAL_CHECK_OUT + bookingId + STRING_UPDATE_BOOKING_MAKE_IT_CONFIRMED2;
			ps = connection.prepareStatement(sql);

			ps.executeUpdate();

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in final check", e);
			throw new DaoException("Error in final check", e);
		}

		finally {
			connectionPool.closeConnection(connection, ps);
		}
	}

	@Override
	public List<RoomBooking> findFreeRoomsAfterNessecaryDate(Date date) throws DaoException {
		List<RoomBooking> bookingList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_ROOMS_FREE_AFTER_DATE + date + STRING_FIND_ROOMS_FREE_BUSY_AFTER_DATE_LAST_PART;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int idBooking = set.getInt(1);
				Date startDate = set.getDate(2);
				Date endDate = set.getDate(3);
				String roomName = set.getString(4);
				String roomCategory = set.getString(5);
				int roomCategoryId = set.getInt(6);
				int roomCategoryParentId = set.getInt(7);

				RoomCategory category = new RoomCategory(roomCategoryId, roomCategory, roomCategoryParentId);

				Room room = new Room(roomName, category);

				RoomBooking booking = new RoomBooking();
				booking.setIdBooking(idBooking);
				booking.setStartDate(startDate);
				booking.setEndDate(endDate);
				booking.setRoom(room);

				bookingList.add(booking);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding free rooms after date", e);
			throw new DaoException("Error in finding free rooms after date  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return bookingList;
	}

	@Override
	public List<RoomBooking> findBusyRoomsAfterNessecaryDate(Date date) throws DaoException {

		List<RoomBooking> bookingList = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;

		connection = connectionPool.takeConnection();

		String sql = STRING_FIND_ROOMS_BUSY_AFTER_DATE + date + STRING_FIND_ROOMS_FREE_BUSY_AFTER_DATE_LAST_PART;

		try {
			statement = connection.createStatement();
			set = statement.executeQuery(sql);

			while (set.next()) {
				int idBooking = set.getInt(1);
				Date startDate = set.getDate(2);
				Date endDate = set.getDate(3);
				String roomName = set.getString(4);
				String roomCategory = set.getString(5);
				int roomCategoryId = set.getInt(6);
				int roomCategoryParentId = set.getInt(7);

				RoomCategory category = new RoomCategory(roomCategoryId, roomCategory, roomCategoryParentId);

				Room room = new Room(roomName, category);

				RoomBooking booking = new RoomBooking();
				booking.setIdBooking(idBooking);
				booking.setStartDate(startDate);
				booking.setEndDate(endDate);
				booking.setRoom(room);

				bookingList.add(booking);

			}

		} catch (ConnectionPoolError | SQLException e) {
			logger.error("Error in finding free rooms after date", e);
			throw new DaoException("Error in finding free rooms after date  " + e);

		} finally {

			connectionPool.closeConnection(connection, statement, set);

		}

		return bookingList;
	}

}
