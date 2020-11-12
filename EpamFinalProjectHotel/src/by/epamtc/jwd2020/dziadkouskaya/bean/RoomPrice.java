package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class RoomPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private double price;
	private Date startDateForRoomPrice;
	private RoomCategory roomCategory;
	
	
	public RoomPrice() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RoomPrice(int id) {
		super();
		this.id = id;
	}


	public RoomPrice(double price, Date startDateForRoomPrice) {
		super();
		this.price = price;
		this.startDateForRoomPrice = startDateForRoomPrice;
	}


	public RoomPrice(int id, double price, Date startDateForRoomPrice) {
		super();
		this.id = id;
		this.price = price;
		this.startDateForRoomPrice = startDateForRoomPrice;
	}
	
	
	public RoomPrice(int id, double price, Date startDateForRoomPrice, RoomCategory roomCategory) {
		super();
		this.id = id;
		this.price = price;
		this.startDateForRoomPrice = startDateForRoomPrice;
		this.roomCategory = roomCategory;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Date getStartDateForRoomPrice() {
		return startDateForRoomPrice;
	}


	public void setStartDateForRoomPrice(Date startDateForRoomPrice) {
		this.startDateForRoomPrice = startDateForRoomPrice;
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
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((roomCategory == null) ? 0 : roomCategory.hashCode());
		result = prime * result + ((startDateForRoomPrice == null) ? 0 : startDateForRoomPrice.hashCode());
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
		RoomPrice other = (RoomPrice) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (roomCategory == null) {
			if (other.roomCategory != null)
				return false;
		} else if (!roomCategory.equals(other.roomCategory))
			return false;
		if (startDateForRoomPrice == null) {
			if (other.startDateForRoomPrice != null)
				return false;
		} else if (!startDateForRoomPrice.equals(other.startDateForRoomPrice))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RoomPrice [id=" + id + ", price=" + price + ", startDateForRoomPrice=" + startDateForRoomPrice
				+ ", roomCategory=" + roomCategory + "]";
	}


	
	
	

}
