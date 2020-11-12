package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class InfoForCleanersTransferObject implements Serializable {
	public static final boolean DEFAULT_INFO_FOR_CLEANERS = false;

	private static final long serialVersionUID = 1L;
	
	private String roomNumber;
	private String roomCategory;
	private boolean cleanAfterArrival;
	private boolean cleanBeforeDepatchure;
	
	public InfoForCleanersTransferObject() {
		super();
		this.cleanAfterArrival = DEFAULT_INFO_FOR_CLEANERS;
		this.cleanBeforeDepatchure = DEFAULT_INFO_FOR_CLEANERS;
	}

	public InfoForCleanersTransferObject(String roomNumber, boolean cleanAfterArrival, boolean cleanBeforeDepatchure) {
		super();
		this.roomNumber = roomNumber;
		this.cleanAfterArrival = cleanAfterArrival;
		this.cleanBeforeDepatchure = cleanBeforeDepatchure;
	}

	public InfoForCleanersTransferObject(String roomNumber) {
		super();
		this.roomNumber = roomNumber;
		this.cleanAfterArrival = DEFAULT_INFO_FOR_CLEANERS;
		this.cleanBeforeDepatchure = DEFAULT_INFO_FOR_CLEANERS;
	}

		
	public InfoForCleanersTransferObject(String roomNumber, boolean cleanAfterArrival) {
		super();
		this.roomNumber = roomNumber;
		this.cleanAfterArrival = cleanAfterArrival;
		this.cleanBeforeDepatchure = DEFAULT_INFO_FOR_CLEANERS;
	}
	
	

	public InfoForCleanersTransferObject(String roomNumber, String roomCategory, boolean cleanAfterArrival) {
		super();
		this.roomNumber = roomNumber;
		this.roomCategory = roomCategory;
		this.cleanAfterArrival = cleanAfterArrival;
	}

	public InfoForCleanersTransferObject(String roomNumber, String roomCategory) {
		super();
		this.roomNumber = roomNumber;
		this.roomCategory = roomCategory;
	}

	public InfoForCleanersTransferObject(String roomNumber, String roomCategory, boolean cleanAfterArrival,
			boolean cleanBeforeDepatchure) {
		super();
		this.roomNumber = roomNumber;
		this.roomCategory = roomCategory;
		this.cleanAfterArrival = cleanAfterArrival;
		this.cleanBeforeDepatchure = cleanBeforeDepatchure;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public boolean isCleanAfterArrival() {
		return cleanAfterArrival;
	}

	public void setCleanAfterArrival(boolean cleanAfterArrival) {
		this.cleanAfterArrival = cleanAfterArrival;
	}

	public boolean isCleanBeforeDepatchure() {
		return cleanBeforeDepatchure;
	}

	public void setCleanBeforeDepatchure(boolean cleanBeforeDepatchure) {
		this.cleanBeforeDepatchure = cleanBeforeDepatchure;
	}

	
	public String getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(String roomCategory) {
		this.roomCategory = roomCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (cleanAfterArrival ? 1231 : 1237);
		result = prime * result + (cleanBeforeDepatchure ? 1231 : 1237);
		result = prime * result + ((roomNumber == null) ? 0 : roomNumber.hashCode());
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
		InfoForCleanersTransferObject other = (InfoForCleanersTransferObject) obj;
		if (cleanAfterArrival != other.cleanAfterArrival)
			return false;
		if (cleanBeforeDepatchure != other.cleanBeforeDepatchure)
			return false;
		if (roomNumber == null) {
			if (other.roomNumber != null)
				return false;
		} else if (!roomNumber.equals(other.roomNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InfoForCleanersTransferObject [roomNumber=" + roomNumber + ", cleanAfterArrival=" + cleanAfterArrival
				+ ", cleanBeforeDepatchure=" + cleanBeforeDepatchure + "]";
	}


	
	
	
	

}
