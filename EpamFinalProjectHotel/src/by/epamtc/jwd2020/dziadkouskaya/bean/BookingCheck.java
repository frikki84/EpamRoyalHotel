package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class BookingCheck implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final boolean DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT = false;	
	
	private int idCheck;
	private RoomBooking roomBooking;
	private BabyExpense babyExpense;
	private Prepayment prepayment;
	private double basicPayment;
	private boolean isPrepayment;
	private boolean isCheckPayed;
	private double childPaySum;
	private double prepaymentSum;
	
	
	public BookingCheck() {
		super();
		this.isCheckPayed = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
		this.isPrepayment = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
	}
	
	public BookingCheck(int idCheck) {
		super();
		this.idCheck = idCheck;
		this.isCheckPayed = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
		this.isPrepayment = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
	}

	public BookingCheck(int idCheck, RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment,
			double basicPayment, boolean isPrepayment, boolean isCheckPayed) {
		super();
		this.idCheck = idCheck;
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.isPrepayment = isPrepayment;
		this.isCheckPayed = isCheckPayed;
		
	}

	public BookingCheck(RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment, double basicPayment) {
		super();
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.isCheckPayed = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
		this.isPrepayment = DEFAULT_VALUE_OF_PAYMENT_AND_PREPAYMENT;
	}

	public BookingCheck(RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment, double basicPayment,
			boolean isPrepayment, boolean isCheckPayed) {
		super();
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.isPrepayment = isPrepayment;
		this.isCheckPayed = isCheckPayed;
	}
	
	

	public BookingCheck(RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment, double basicPayment,
			double childPaySum, double prepaymentSum) {
		super();
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.childPaySum = childPaySum;
		this.prepaymentSum = prepaymentSum;
	}

	public BookingCheck(RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment, double basicPayment,
			boolean isPrepayment, boolean isCheckPayed, double childPaySum, double prepaymentSum) {
		super();
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.isPrepayment = isPrepayment;
		this.isCheckPayed = isCheckPayed;
		this.childPaySum = childPaySum;
		this.prepaymentSum = prepaymentSum;
	}

	
	
	public BookingCheck(int idCheck, RoomBooking roomBooking, BabyExpense babyExpense, Prepayment prepayment,
			double basicPayment, boolean isPrepayment, boolean isCheckPayed, double childPaySum, double prepaymentSum) {
		super();
		this.idCheck = idCheck;
		this.roomBooking = roomBooking;
		this.babyExpense = babyExpense;
		this.prepayment = prepayment;
		this.basicPayment = basicPayment;
		this.isPrepayment = isPrepayment;
		this.isCheckPayed = isCheckPayed;
		this.childPaySum = childPaySum;
		this.prepaymentSum = prepaymentSum;
	}

	public int getIdCheck() {
		return idCheck;
	}

	public void setIdCheck(int idCheck) {
		this.idCheck = idCheck;
	}

	public RoomBooking getRoomBooking() {
		return roomBooking;
	}

	public void setRoomBooking(RoomBooking roomBooking) {
		this.roomBooking = roomBooking;
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

	public double getBasicPayment() {
		return basicPayment;
	}

	public void setBasicPayment(double basicPayment) {
		this.basicPayment = basicPayment;
	}

	public boolean isPrepayment() {
		return isPrepayment;
	}

	public void setPrepayment(boolean isPrepayment) {
		this.isPrepayment = isPrepayment;
	}

	public boolean isCheckPayed() {
		return isCheckPayed;
	}

	public void setCheckPayed(boolean isCheckPayed) {
		this.isCheckPayed = isCheckPayed;
	}
	
	

	public double getChildPaySum() {
		return childPaySum;
	}

	public void setChildPaySum(double childPaySum) {
		this.childPaySum = childPaySum;
	}

	public double getPrepaymentSum() {
		return prepaymentSum;
	}

	public void setPrepaymentSum(double prepaymentSum) {
		this.prepaymentSum = prepaymentSum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((babyExpense == null) ? 0 : babyExpense.hashCode());
		long temp;
		temp = Double.doubleToLongBits(basicPayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(childPaySum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + idCheck;
		result = prime * result + (isCheckPayed ? 1231 : 1237);
		result = prime * result + (isPrepayment ? 1231 : 1237);
		result = prime * result + ((prepayment == null) ? 0 : prepayment.hashCode());
		temp = Double.doubleToLongBits(prepaymentSum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((roomBooking == null) ? 0 : roomBooking.hashCode());
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
		BookingCheck other = (BookingCheck) obj;
		if (babyExpense == null) {
			if (other.babyExpense != null)
				return false;
		} else if (!babyExpense.equals(other.babyExpense))
			return false;
		if (Double.doubleToLongBits(basicPayment) != Double.doubleToLongBits(other.basicPayment))
			return false;
		if (Double.doubleToLongBits(childPaySum) != Double.doubleToLongBits(other.childPaySum))
			return false;
		if (idCheck != other.idCheck)
			return false;
		if (isCheckPayed != other.isCheckPayed)
			return false;
		if (isPrepayment != other.isPrepayment)
			return false;
		if (prepayment == null) {
			if (other.prepayment != null)
				return false;
		} else if (!prepayment.equals(other.prepayment))
			return false;
		if (Double.doubleToLongBits(prepaymentSum) != Double.doubleToLongBits(other.prepaymentSum))
			return false;
		if (roomBooking == null) {
			if (other.roomBooking != null)
				return false;
		} else if (!roomBooking.equals(other.roomBooking))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookingCheck [idCheck=" + idCheck + ", roomBooking=" + roomBooking + ", babyExpense=" + babyExpense
				+ ", prepayment=" + prepayment + ", basicPayment=" + basicPayment + ", isPrepayment=" + isPrepayment
				+ ", isCheckPayed=" + isCheckPayed + ", childPaySum=" + childPaySum + ", prepaymentSum=" + prepaymentSum
				+ "]";
	}

	
	
	
	

}
