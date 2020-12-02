package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class BookingTransferObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private int bookingId;
	private Date startDate;
	private Date endDate;
	private String hotelRoomNumber;
	private String roomCategoryName;
	private int peopleNumberInRoom;
	private int childrenNumber;
	private double fullPrice;
	private double prepayment;
	private boolean isPrepayment;
	private boolean isBookingPaid;
	

	public BookingTransferObject() {

	}

	public BookingTransferObject(Date startDate, Date endDate, String hotelRoomNumber, int peopleNumberInRoom,
			int childrenNumber, double fullPrice, double prepayment) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelRoomNumber = hotelRoomNumber;
		this.peopleNumberInRoom = peopleNumberInRoom;
		this.childrenNumber = childrenNumber;
		this.fullPrice = fullPrice;
		this.prepayment = prepayment;
	}

	public BookingTransferObject(int bookingId, Date startDate, Date endDate, String hotelRoomNumber,
			int peopleNumberInRoom, int childrenNumber, double fullPrice, double prepayment) {
		
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelRoomNumber = hotelRoomNumber;
		this.peopleNumberInRoom = peopleNumberInRoom;
		this.childrenNumber = childrenNumber;
		this.fullPrice = fullPrice;
		this.prepayment = prepayment;
	}

	public BookingTransferObject(int bookingId, Date startDate, Date endDate, String hotelRoomNumber,
			int peopleNumberInRoom, int childrenNumber, double fullPrice, double prepayment, boolean isPrepayment) {
		
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelRoomNumber = hotelRoomNumber;
		this.peopleNumberInRoom = peopleNumberInRoom;
		this.childrenNumber = childrenNumber;
		this.fullPrice = fullPrice;
		this.prepayment = prepayment;
		this.isPrepayment = isPrepayment;
	}

	
	public BookingTransferObject(Date startDate, Date endDate, String hotelRoomNumber, String roomCategoryName,
			int peopleNumberInRoom, int childrenNumber, double fullPrice, double prepayment, boolean isPrepayment,
			boolean isBookingPaid) {
		
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelRoomNumber = hotelRoomNumber;
		this.roomCategoryName = roomCategoryName;
		this.peopleNumberInRoom = peopleNumberInRoom;
		this.childrenNumber = childrenNumber;
		this.fullPrice = fullPrice;
		this.prepayment = prepayment;
		this.isPrepayment = isPrepayment;
		this.isBookingPaid = isBookingPaid;
	}
	
	

	public BookingTransferObject(int bookingId, Date startDate, Date endDate, String hotelRoomNumber,
			String roomCategoryName, int peopleNumberInRoom, int childrenNumber, double fullPrice, double prepayment,
			boolean isPrepayment, boolean isBookingPaid) {
		
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hotelRoomNumber = hotelRoomNumber;
		this.roomCategoryName = roomCategoryName;
		this.peopleNumberInRoom = peopleNumberInRoom;
		this.childrenNumber = childrenNumber;
		this.fullPrice = fullPrice;
		this.prepayment = prepayment;
		this.isPrepayment = isPrepayment;
		this.isBookingPaid = isBookingPaid;
	}

	public boolean getIsBookingPaid() {
		return isBookingPaid;
	}

	public void setIsBookingPaid(boolean isBookingPaid) {
		this.isBookingPaid = isBookingPaid;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
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

	public String getHotelRoomNumber() {
		return hotelRoomNumber;
	}

	public void setHotelRoomNumber(String hotelRoomNumber) {
		this.hotelRoomNumber = hotelRoomNumber;
	}

	public int getPeopleNumberInRoom() {
		return peopleNumberInRoom;
	}

	public void setPeopleNumberInRoom(int peopleNumberInRoom) {
		this.peopleNumberInRoom = peopleNumberInRoom;
	}

	public int getChildrenNumber() {
		return childrenNumber;
	}

	public void setChildrenNumber(int childrenNumber) {
		this.childrenNumber = childrenNumber;
	}

	public double getFullPrice() {
		return fullPrice;
	}

	public void setFullPrice(double fullPrice) {
		this.fullPrice = fullPrice;
	}

	public double getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(double prepayment) {
		this.prepayment = prepayment;
	}

	public boolean getIsPrepayment() {
		return isPrepayment;
	}

	public void setIsPrepayment(boolean isPrepayment) {
		this.isPrepayment = isPrepayment;
	}

	
	
	public String getRoomCategoryName() {
		return roomCategoryName;
	}

	public void setRoomCategoryName(String roomCategoryName) {
		this.roomCategoryName = roomCategoryName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookingId;
		result = prime * result + childrenNumber;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(fullPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((hotelRoomNumber == null) ? 0 : hotelRoomNumber.hashCode());
		result = prime * result + (isBookingPaid ? 1231 : 1237);
		result = prime * result + (isPrepayment ? 1231 : 1237);
		result = prime * result + peopleNumberInRoom;
		temp = Double.doubleToLongBits(prepayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((roomCategoryName == null) ? 0 : roomCategoryName.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		BookingTransferObject other = (BookingTransferObject) obj;
		if (bookingId != other.bookingId)
			return false;
		if (childrenNumber != other.childrenNumber)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(fullPrice) != Double.doubleToLongBits(other.fullPrice))
			return false;
		if (hotelRoomNumber == null) {
			if (other.hotelRoomNumber != null)
				return false;
		} else if (!hotelRoomNumber.equals(other.hotelRoomNumber))
			return false;
		if (isBookingPaid != other.isBookingPaid)
			return false;
		if (isPrepayment != other.isPrepayment)
			return false;
		if (peopleNumberInRoom != other.peopleNumberInRoom)
			return false;
		if (Double.doubleToLongBits(prepayment) != Double.doubleToLongBits(other.prepayment))
			return false;
		if (roomCategoryName == null) {
			if (other.roomCategoryName != null)
				return false;
		} else if (!roomCategoryName.equals(other.roomCategoryName))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingTransferObject [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", hotelRoomNumber=" + hotelRoomNumber + ", roomCategoryName=" + roomCategoryName
				+ ", peopleNumberInRoom=" + peopleNumberInRoom + ", childrenNumber=" + childrenNumber + ", fullPrice="
				+ fullPrice + ", prepayment=" + prepayment + ", isPrepayment=" + isPrepayment + ", isBookingPaid="
				+ isBookingPaid + "]";
	}

	

	

}
