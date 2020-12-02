package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class RoomCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String roomCategoryName;
	private int idParentRoomCategory;
	private int peopleNumberInRoom;

	public RoomCategory() {

	}

	public RoomCategory(int id) {

		this.id = id;
	}

	public RoomCategory(int id, int idParentRoomCategory) {

		this.id = id;
		this.idParentRoomCategory = idParentRoomCategory;
	}

	public RoomCategory(int id, String roomCategoryName, int idParentRoomCategory, int peopleNumberInRoom) {

		this.id = id;
		this.roomCategoryName = roomCategoryName;
		this.idParentRoomCategory = idParentRoomCategory;
		this.peopleNumberInRoom = peopleNumberInRoom;
	}

	public RoomCategory(int id, String roomCategoryName) {

		this.id = id;
		this.roomCategoryName = roomCategoryName;
	}

	public RoomCategory(int id, String roomCategoryName, int idParentRoomCategory) {

		this.id = id;
		this.roomCategoryName = roomCategoryName;
		this.idParentRoomCategory = idParentRoomCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoomCategoryName() {
		return roomCategoryName;
	}

	public void setRoomCategoryName(String roomCategoryName) {
		this.roomCategoryName = roomCategoryName;
	}

	public int getIdParentRoomCategory() {
		return idParentRoomCategory;
	}

	public void setIdParentRoomCategory(int idParentRoomCategory) {
		this.idParentRoomCategory = idParentRoomCategory;
	}

	public int getPeopleNumberInRoom() {
		return peopleNumberInRoom;
	}

	public void setPeopleNumberInRoom(int peopleNumberInRoom) {
		this.peopleNumberInRoom = peopleNumberInRoom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + idParentRoomCategory;
		result = prime * result + peopleNumberInRoom;
		result = prime * result + ((roomCategoryName == null) ? 0 : roomCategoryName.hashCode());
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
		RoomCategory other = (RoomCategory) obj;
		if (id != other.id)
			return false;
		if (idParentRoomCategory != other.idParentRoomCategory)
			return false;
		if (peopleNumberInRoom != other.peopleNumberInRoom)
			return false;
		if (roomCategoryName == null) {
			if (other.roomCategoryName != null)
				return false;
		} else if (!roomCategoryName.equals(other.roomCategoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomCategory [id=" + id + ", roomCategoryName=" + roomCategoryName + ", idParentRoomCategory="
				+ idParentRoomCategory + ", peopleNumberInRoom=" + peopleNumberInRoom + "]";
	}

}
