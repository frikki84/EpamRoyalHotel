package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;

public class BabyExpense implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String babyExpenseName;
	private double babyExpenseValuePerDay;
	private Date babyexpenseStartDate;
	
	public BabyExpense() {

	}
	
	
	public BabyExpense(int id) {
		
		this.id = id;
	}




	public BabyExpense(int id, String babyExpenseName, double babyExpenseValuePerDay) {
		
		this.id = id;
		this.babyExpenseName = babyExpenseName;
		this.babyExpenseValuePerDay = babyExpenseValuePerDay;
	}

	public BabyExpense(int id, String babyExpenseName, double babyExpenseValuePerDay, Date babyexpenseStartDate) {
		
		this.id = id;
		this.babyExpenseName = babyExpenseName;
		this.babyExpenseValuePerDay = babyExpenseValuePerDay;
		this.babyexpenseStartDate = babyexpenseStartDate;
	}

	public BabyExpense(String babyExpenseName, double babyExpenseValuePerDay, Date babyexpenseStartDate) {
		
		this.babyExpenseName = babyExpenseName;
		this.babyExpenseValuePerDay = babyExpenseValuePerDay;
		this.babyexpenseStartDate = babyexpenseStartDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBabyExpenseName() {
		return babyExpenseName;
	}

	public void setBabyExpenseName(String babyExpenseName) {
		this.babyExpenseName = babyExpenseName;
	}

	public double getBabyExpenseValuePerDay() {
		return babyExpenseValuePerDay;
	}

	public void setBabyExpenseValuePerDay(double babyExpenseValuePerDay) {
		this.babyExpenseValuePerDay = babyExpenseValuePerDay;
	}

	public Date getBabyexpenseStartDate() {
		return babyexpenseStartDate;
	}

	public void setBabyexpenseStartDate(Date babyexpenseStartDate) {
		this.babyexpenseStartDate = babyexpenseStartDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((babyExpenseName == null) ? 0 : babyExpenseName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(babyExpenseValuePerDay);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((babyexpenseStartDate == null) ? 0 : babyexpenseStartDate.hashCode());
		result = prime * result + id;
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
		BabyExpense other = (BabyExpense) obj;
		if (babyExpenseName == null) {
			if (other.babyExpenseName != null)
				return false;
		} else if (!babyExpenseName.equals(other.babyExpenseName))
			return false;
		if (Double.doubleToLongBits(babyExpenseValuePerDay) != Double.doubleToLongBits(other.babyExpenseValuePerDay))
			return false;
		if (babyexpenseStartDate == null) {
			if (other.babyexpenseStartDate != null)
				return false;
		} else if (!babyexpenseStartDate.equals(other.babyexpenseStartDate))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BabyExpense [id=" + id + ", babyExpenseName=" + babyExpenseName + ", babyExpenseValuePerDay="
				+ babyExpenseValuePerDay + ", babyexpenseStartDate=" + babyexpenseStartDate + "]";
	}
	
	
	

}
