package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class Prepayment implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String prepaymentName;
	private double prepaymentCoefficient;
	private Date startDateOfValue;
	private int daysBetweenBookingAndComeIn;

	public Prepayment() {

	}

	public Prepayment(int id) {

		this.id = id;
	}

	public Prepayment(String prepaymentName) {

		this.prepaymentName = prepaymentName;
	}

	public Prepayment(String prepaymentName, double prepaymentCoefficient, Date startDateOfValue) {

		this.prepaymentName = prepaymentName;
		this.prepaymentCoefficient = prepaymentCoefficient;
		this.startDateOfValue = startDateOfValue;
	}

	public Prepayment(String prepaymentName, double prepaymentCoefficient, Date startDateOfValue,
			int daysBetweenBookingAndComeIn) {

		this.prepaymentName = prepaymentName;
		this.prepaymentCoefficient = prepaymentCoefficient;
		this.startDateOfValue = startDateOfValue;
		this.daysBetweenBookingAndComeIn = daysBetweenBookingAndComeIn;
	}

	public Prepayment(int id, String prepaymentName, double prepaymentCoefficient, Date startDateOfValue,
			int daysBetweenBookingAndComeIn) {

		this.id = id;
		this.prepaymentName = prepaymentName;
		this.prepaymentCoefficient = prepaymentCoefficient;
		this.startDateOfValue = startDateOfValue;
		this.daysBetweenBookingAndComeIn = daysBetweenBookingAndComeIn;
	}

	public Prepayment(int id, String prepaymentName, double prepaymentCoefficient, Date startDateOfValue) {

		this.id = id;
		this.prepaymentName = prepaymentName;
		this.prepaymentCoefficient = prepaymentCoefficient;
		this.startDateOfValue = startDateOfValue;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrepaymentName() {
		return prepaymentName;
	}

	public void setPrepaymentName(String prepaymentName) {
		this.prepaymentName = prepaymentName;
	}

	public double getPrepaymentCoefficient() {
		return prepaymentCoefficient;
	}

	public void setPrepaymentCoefficient(double prepaymentCoefficient) {
		this.prepaymentCoefficient = prepaymentCoefficient;
	}

	public Date getStartDateOfValue() {
		return startDateOfValue;
	}

	public void setStartDateOfValue(Date startDateOfValue) {
		this.startDateOfValue = startDateOfValue;
	}

	public int getDaysBetweenBookingAndComeIn() {
		return daysBetweenBookingAndComeIn;
	}

	public void setDaysBetweenBookingAndComeIn(int daysBetweenBookingAndComeIn) {
		this.daysBetweenBookingAndComeIn = daysBetweenBookingAndComeIn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + daysBetweenBookingAndComeIn;
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(prepaymentCoefficient);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((prepaymentName == null) ? 0 : prepaymentName.hashCode());
		result = prime * result + ((startDateOfValue == null) ? 0 : startDateOfValue.hashCode());
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
		Prepayment other = (Prepayment) obj;
		if (daysBetweenBookingAndComeIn != other.daysBetweenBookingAndComeIn)
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(prepaymentCoefficient) != Double.doubleToLongBits(other.prepaymentCoefficient))
			return false;
		if (prepaymentName == null) {
			if (other.prepaymentName != null)
				return false;
		} else if (!prepaymentName.equals(other.prepaymentName))
			return false;
		if (startDateOfValue == null) {
			if (other.startDateOfValue != null)
				return false;
		} else if (!startDateOfValue.equals(other.startDateOfValue))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Prepayment [id=" + id + ", prepaymentName=" + prepaymentName + ", prepaymentCoefficient="
				+ prepaymentCoefficient + ", startDateOfValue=" + startDateOfValue + ", daysBetweenBookingAndComeIn="
				+ daysBetweenBookingAndComeIn + "]";
	}

}
