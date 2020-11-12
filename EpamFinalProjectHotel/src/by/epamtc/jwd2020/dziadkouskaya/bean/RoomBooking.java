package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class RoomBooking implements Serializable {
	private static final boolean DEFAULT_BOOKING_CONFIRMED = false;

	private static final long serialVersionUID = 1L;

	private int idBooking;
	private Date startDate;
	private Date endDate;
	private int adultPeopleNumber;
	private int childrenNumber;
	private int userId;
	private Room room;
	private Date bookingDate;
	private boolean isBookingConfirmed;
	private double basicPayment;
	private BabyExpense babyExpense;
	private double babyExpenceSum;
	private Prepayment prepayment;
	private double prepaymentSum;
	private boolean isPrepaymentPaid;
	private boolean isBookingPaid;

	public RoomBooking() {
		this.isBookingConfirmed = DEFAULT_BOOKING_CONFIRMED;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(int idBooking) {
		super();
		this.idBooking = idBooking;
		this.isBookingConfirmed = DEFAULT_BOOKING_CONFIRMED;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(Date startDate, Date endDate, int adultPeopleNumber, int userId, Room room) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.userId = userId;
		this.room = room;
		this.isBookingConfirmed = DEFAULT_BOOKING_CONFIRMED;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber, int userId, Room room,
			Date bookingDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = DEFAULT_BOOKING_CONFIRMED;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(int idBooking, Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber,
			int userId, Room room, Date bookingDate) {
		super();
		this.idBooking = idBooking;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = DEFAULT_BOOKING_CONFIRMED;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(int idBooking, Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber,
			int userId, Room room, Date bookingDate, boolean isBookingConfirmed) {
		super();
		this.idBooking = idBooking;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = isBookingConfirmed;
		this.isBookingPaid = DEFAULT_BOOKING_CONFIRMED;
		this.isPrepaymentPaid = DEFAULT_BOOKING_CONFIRMED;
	}

	public RoomBooking(int idBooking, Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber,
			int userId, Room room, Date bookingDate, boolean isBookingConfirmed, double basicPayment,
			double babyExpenceSum, double prepaymentSum, boolean isPrepaymentPaid, boolean isBookingPaid) {
		super();
		this.idBooking = idBooking;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = isBookingConfirmed;
		this.basicPayment = basicPayment;
		this.babyExpenceSum = babyExpenceSum;
		this.prepaymentSum = prepaymentSum;
		this.isPrepaymentPaid = isPrepaymentPaid;
		this.isBookingPaid = isBookingPaid;
	}

	public RoomBooking(Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber, int userId, Room room,
			Date bookingDate, boolean isBookingConfirmed, double basicPayment, BabyExpense babyExpense,
			double babyExpenceSum, Prepayment prepayment, double prepaymentSum, boolean isPrepaymentPaid,
			boolean isBookingPaid) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = isBookingConfirmed;
		this.basicPayment = basicPayment;
		this.babyExpense = babyExpense;
		this.babyExpenceSum = babyExpenceSum;
		this.prepayment = prepayment;
		this.prepaymentSum = prepaymentSum;
		this.isPrepaymentPaid = isPrepaymentPaid;
		this.isBookingPaid = isBookingPaid;
	}

	public RoomBooking(Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber, int userId, Room room,
			Date bookingDate, boolean isBookingConfirmed, double basicPayment, double babyExpenceSum,
			double prepaymentSum, boolean isPrepaymentPaid, boolean isBookingPaid) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = isBookingConfirmed;
		this.basicPayment = basicPayment;
		this.babyExpenceSum = babyExpenceSum;
		this.prepaymentSum = prepaymentSum;
		this.isPrepaymentPaid = isPrepaymentPaid;
		this.isBookingPaid = isBookingPaid;
	}

	public RoomBooking(int idBooking, Date startDate, Date endDate, int adultPeopleNumber, int childrenNumber,
			int userId, Room room, Date bookingDate, boolean isBookingConfirmed, double basicPayment,
			BabyExpense babyExpense, double babyExpenceSum, Prepayment prepayment, double prepaymentSum,
			boolean isPrepaymentPaid, boolean isBookingPaid) {
		super();
		this.idBooking = idBooking;
		this.startDate = startDate;
		this.endDate = endDate;
		this.adultPeopleNumber = adultPeopleNumber;
		this.childrenNumber = childrenNumber;
		this.userId = userId;
		this.room = room;
		this.bookingDate = bookingDate;
		this.isBookingConfirmed = isBookingConfirmed;
		this.basicPayment = basicPayment;
		this.babyExpense = babyExpense;
		this.babyExpenceSum = babyExpenceSum;
		this.prepayment = prepayment;
		this.prepaymentSum = prepaymentSum;
		this.isPrepaymentPaid = isPrepaymentPaid;
		this.isBookingPaid = isBookingPaid;
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getAdultPeopleNumber() {
		return adultPeopleNumber;
	}

	public void setAdultPeopleNumber(int adultPeopleNumber) {
		this.adultPeopleNumber = adultPeopleNumber;
	}

	public int getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Room getRoom() {
		return room;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public boolean isBookingConfirmed() {
		return isBookingConfirmed;
	}

	public void setBookingConfirmed(boolean isBookingConfirmed) {
		this.isBookingConfirmed = isBookingConfirmed;
	}

	public double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(double basicPayment) {
		this.basicPayment = basicPayment;
	}

	public double getBabyExpenceSum() {
		return babyExpenceSum;
	}

	public void setBabyExpenceSum(double babyExpenceSum) {
		this.babyExpenceSum = babyExpenceSum;
	}

	public double getPrepaymentSum() {
		return prepaymentSum;
	}

	public void setPrepaymentSum(double prepaymentSum) {
		this.prepaymentSum = prepaymentSum;
	}

	public boolean isPrepaymentPaid() {
		return isPrepaymentPaid;
	}

	public void setPrepaymentPaid(boolean isPrepaymentPaid) {
		this.isPrepaymentPaid = isPrepaymentPaid;
	}

	public boolean isBookingPaid() {
		return isBookingPaid;
	}

	public void setBookingPaid(boolean isBookingPaid) {
		this.isBookingPaid = isBookingPaid;
	}

	public BabyExpense getBabyExpense() {
		return babyExpense;
	}

	public void setBabyExpense(BabyExpense babyExpense) {
		this.babyExpense = babyExpense;
	}

	public Prepayment getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(Prepayment prepayment) {
		this.prepayment = prepayment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + adultPeopleNumber;
		long temp;
		temp = Double.doubleToLongBits(babyExpenceSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((babyExpense == null) ? 0 : babyExpense.hashCode());
		temp = Double.doubleToLongBits(basicPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((bookingDate == null) ? 0 : bookingDate.hashCode());
		result = prime * result + childrenNumber;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + idBooking;
		result = prime * result + (isBookingConfirmed ? 1231 : 1237);
		result = prime * result + (isBookingPaid ? 1231 : 1237);
		result = prime * result + (isPrepaymentPaid ? 1231 : 1237);
		result = prime * result + ((prepayment == null) ? 0 : prepayment.hashCode());
		temp = Double.doubleToLongBits(prepaymentSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomBooking other = (RoomBooking) obj;
		if (adultPeopleNumber != other.adultPeopleNumber)
			return false;
		if (Double.doubleToLongBits(babyExpenceSum) != Double.doubleToLongBits(other.babyExpenceSum))
			return false;
		if (babyExpense == null) {
			if (other.babyExpense != null)
				return false;
		} else if (!babyExpense.equals(other.babyExpense))
			return false;
		if (Double.doubleToLongBits(basicPayment) != Double.doubleToLongBits(other.basicPayment))
			return false;
		if (bookingDate == null) {
			if (other.bookingDate != null)
				return false;
		} else if (!bookingDate.equals(other.bookingDate))
			return false;
		if (childrenNumber != other.childrenNumber)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (idBooking != other.idBooking)
			return false;
		if (isBookingConfirmed != other.isBookingConfirmed)
			return false;
		if (isBookingPaid != other.isBookingPaid)
			return false;
		if (isPrepaymentPaid != other.isPrepaymentPaid)
			return false;
		if (prepayment == null) {
			if (other.prepayment != null)
				return false;
		} else if (!prepayment.equals(other.prepayment))
			return false;
		if (Double.doubleToLongBits(prepaymentSum) != Double.doubleToLongBits(other.prepaymentSum))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomBooking [idBooking=" + idBooking + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", adultPeopleNumber=" + adultPeopleNumber + ", childrenNumber=" + childrenNumber + ", userId="
				+ userId + ", room=" + room + ", bookingDate=" + bookingDate + ", isBookingConfirmed="
				+ isBookingConfirmed + ", basicPayment=" + basicPayment + ", babyExpense=" + babyExpense
				+ ", babyExpenceSum=" + babyExpenceSum + ", prepayment=" + prepayment + ", prepaymentSum="
				+ prepaymentSum + ", isPrepaymentPaid=" + isPrepaymentPaid + ", isBookingPaid=" + isBookingPaid + "]";
	}

}
