package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class CheckOutTransferObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idRoomBooking;
	private String roomNumber;
	private Date startDate;
	private Date endDate;
	private String userName;
	private String userThirdName;
	private String userSecondName;
	private boolean clientLivingInHotelState;
	
	
	public CheckOutTransferObject() {
		super();
		
	}


	public CheckOutTransferObject(int idRoomBooking, String roomNumber, Date startDate, Date endDate, String userName,
			String userThirdName, String userSecondName, boolean clientLivingInHotelState) {
		super();
		this.idRoomBooking = idRoomBooking;
		this.roomNumber = roomNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.userName = userName;
		this.userThirdName = userThirdName;
		this.userSecondName = userSecondName;
		this.clientLivingInHotelState = clientLivingInHotelState;
	}


	public int getIdRoomBooking() {
		return idRoomBooking;
	}


	public void setIdRoomBooking(int idRoomBooking) {
		this.idRoomBooking = idRoomBooking;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
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


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserThirdName() {
		return userThirdName;
	}


	public void setUserThirdName(String userThirdName) {
		this.userThirdName = userThirdName;
	}


	public String getUserSecondName() {
		return userSecondName;
	}


	public void setUserSecondName(String userSecondName) {
		this.userSecondName = userSecondName;
	}


	public boolean getClientLivingInHotelState() {
		return clientLivingInHotelState;
	}


	public void setClientLivingInHotelState(boolean clientLivingInHotelState) {
		this.clientLivingInHotelState = clientLivingInHotelState;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (clientLivingInHotelState ? 1231 : 1237);
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + idRoomBooking;
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((userSecondName == null) ? 0 : userSecondName.hashCode());
		result = prime * result + ((userThirdName == null) ? 0 : userThirdName.hashCode());
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
		CheckOutTransferObject other = (CheckOutTransferObject) obj;
		if (clientLivingInHotelState != other.clientLivingInHotelState)
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (idRoomBooking != other.idRoomBooking)
			return false;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userSecondName == null) {
			if (other.userSecondName != null)
				return false;
		} else if (!userSecondName.equals(other.userSecondName))
			return false;
		if (userThirdName == null) {
			if (other.userThirdName != null)
				return false;
		} else if (!userThirdName.equals(other.userThirdName))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "CheckOutTransferObject [idRoomBooking=" + idRoomBooking + ", roomNumber=" + roomNumber + ", startDate="
				+ startDate + ", endDate=" + endDate + ", userName=" + userName + ", userThirdName=" + userThirdName
				+ ", userSecondName=" + userSecondName + ", clientLivingInHotelState=" + clientLivingInHotelState + "]";
	}


	
	
	
	
	

}
