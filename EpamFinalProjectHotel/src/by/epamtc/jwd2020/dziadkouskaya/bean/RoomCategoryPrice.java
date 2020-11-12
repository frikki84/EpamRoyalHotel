package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class RoomCategoryPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private Date startDate;
	private double pricePerDay;
	private RoomCategory roomCategory;
	
	public RoomCategoryPrice() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomCategoryPrice(Date startDate, double pricePerDay, RoomCategory roomCategory) {
		super();
		this.startDate = startDate;
		this.pricePerDay = pricePerDay;
		this.roomCategory = roomCategory;
	}

	public RoomCategoryPrice(int id, Date startDate, double pricePerDay, RoomCategory roomCategory) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.pricePerDay = pricePerDay;
		this.roomCategory = roomCategory;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
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
		temp = Double.doubleToLongBits(pricePerDay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((roomCategory == null) ? 0 : roomCategory.hashCode());
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
		RoomCategoryPrice other = (RoomCategoryPrice) obj;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(pricePerDay) != Double.doubleToLongBits(other.pricePerDay))
			return false;
		if (roomCategory == null) {
			if (other.roomCategory != null)
				return false;
		} else if (!roomCategory.equals(other.roomCategory))
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
		return "RoomCategoryPrice [id=" + id + ", startDate=" + startDate + ", pricePerDay=" + pricePerDay
				+ ", roomCategory=" + roomCategory + "]";
	}
	
	

}
