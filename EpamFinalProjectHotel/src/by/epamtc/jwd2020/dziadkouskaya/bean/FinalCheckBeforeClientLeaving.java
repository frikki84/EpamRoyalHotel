package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class FinalCheckBeforeClientLeaving implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	private double finalMainSum;
	private double additionalPSum;
	private RoomBooking booking;
	private boolean personLiving;
	private Date leavingDate;
	
	public FinalCheckBeforeClientLeaving() {

	}

	public FinalCheckBeforeClientLeaving(double finalMainSum, double additionalPSum, RoomBooking booking,
			boolean personLiving, Date leavingDate) {
		
		this.finalMainSum = finalMainSum;
		this.additionalPSum = additionalPSum;
		this.booking = booking;
		this.personLiving = personLiving;
		this.leavingDate = leavingDate;
	}

	public FinalCheckBeforeClientLeaving(int id, double finalMainSum, double additionalPSum, RoomBooking booking,
			boolean personLiving, Date leavingDate) {
		
		this.id = id;
		this.finalMainSum = finalMainSum;
		this.additionalPSum = additionalPSum;
		this.booking = booking;
		this.personLiving = personLiving;
		this.leavingDate = leavingDate;
	}

	public FinalCheckBeforeClientLeaving(RoomBooking booking, boolean personLiving) {
		
		this.booking = booking;
		this.personLiving = personLiving;
	}

	public FinalCheckBeforeClientLeaving(RoomBooking booking, boolean personLiving, Date leavingDate) {
		
		this.booking = booking;
		this.personLiving = personLiving;
		this.leavingDate = leavingDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getFinalMainSum() {
		return finalMainSum;
	}

	public void setFinalMainSum(double finalMainSum) {
		this.finalMainSum = finalMainSum;
	}

	public double getAdditionalPSum() {
		return additionalPSum;
	}

	public void setAdditionalPSum(double additionalPSum) {
		this.additionalPSum = additionalPSum;
	}

	public RoomBooking getBooking() {
		return booking;
	}

	public void setBooking(RoomBooking booking) {
		this.booking = booking;
	}

	public boolean isPersonLiving() {
		return personLiving;
	}

	public void setPersonLiving(boolean personLiving) {
		this.personLiving = personLiving;
	}

	public Date getLeavingDate() {
		return leavingDate;
	}

	public void setLeavingDate(Date leavingDate) {
		this.leavingDate = leavingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(additionalPSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((booking == null) ? 0 : booking.hashCode());
		temp = Double.doubleToLongBits(finalMainSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + ((leavingDate == null) ? 0 : leavingDate.hashCode());
		result = prime * result + (personLiving ? 1231 : 1237);
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
		FinalCheckBeforeClientLeaving other = (FinalCheckBeforeClientLeaving) obj;
		if (Double.doubleToLongBits(additionalPSum) != Double.doubleToLongBits(other.additionalPSum))
			return false;
		if (booking == null) {
			if (other.booking != null)
				return false;
		} else if (!booking.equals(other.booking))
			return false;
		if (Double.doubleToLongBits(finalMainSum) != Double.doubleToLongBits(other.finalMainSum))
			return false;
		if (id != other.id)
			return false;
		if (leavingDate == null) {
			if (other.leavingDate != null)
				return false;
		} else if (!leavingDate.equals(other.leavingDate))
			return false;
		if (personLiving != other.personLiving)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FinalCheckBeforeClientLeaving [id=" + id + ", finalMainSum=" + finalMainSum + ", additionalPSum="
				+ additionalPSum + ", booking=" + booking + ", personLiving=" + personLiving + ", leavingDate="
				+ leavingDate + "]";
	}
	
	

}
