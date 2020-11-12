package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class Room implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String hotelRoomNumber;
	private int floorNumber;
	private RoomCategory roomCategory;
	
	public Room() {
		super();
	}

	public Room(int id) {
		super();
		this.id = id;
	}

	public Room(String hotelRoomNumber, int floorNumber, RoomCategory roomCategory) {
		super();
		this.hotelRoomNumber = hotelRoomNumber;
		this.floorNumber = floorNumber;
		this.roomCategory = roomCategory;
	}

	public Room(String hotelRoomNumber, RoomCategory roomCategory) {
		super();
		this.hotelRoomNumber = hotelRoomNumber;
		this.roomCategory = roomCategory;
	}

	public Room(int id, String hotelRoomNumber, int floorNumber, RoomCategory roomCategory) {
		super();
		this.id = id;
		this.hotelRoomNumber = hotelRoomNumber;
		this.floorNumber = floorNumber;
		this.roomCategory = roomCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHotelRoomNumber() {
		return hotelRoomNumber;
	}

	public void setHotelRoomNumber(String hotelRoomNumber) {
		this.hotelRoomNumber = hotelRoomNumber;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public RoomCategory getRoomCategory() {
		return roomCategory;
	}

	public void setRoomCategory(RoomCategory roomCategory) {
		this.roomCategory = roomCategory;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + floorNumber;
		result = prime * result + ((hotelRoomNumber == null) ? 0 : hotelRoomNumber.hashCode());
		result = prime * result + id;
		result = prime * result + ((roomCategory == null) ? 0 : roomCategory.hashCode());
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
		Room other = (Room) obj;
		if (floorNumber != other.floorNumber)
			return false;
		if (hotelRoomNumber == null) {
			if (other.hotelRoomNumber != null)
				return false;
		} else if (!hotelRoomNumber.equals(other.hotelRoomNumber))
			return false;
		if (id != other.id)
			return false;
		if (roomCategory == null) {
			if (other.roomCategory != null)
				return false;
		} else if (!roomCategory.equals(other.roomCategory))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", hotelRoomNumber=" + hotelRoomNumber + ", floorNumber=" + floorNumber
				+ ", roomCategory=" + roomCategory + "]";
	}
	
	

	
	
	
	


}
