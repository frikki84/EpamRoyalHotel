package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class UserActivity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String activityName;
	
	public UserActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserActivity(int id) {
		super();
		this.id = id;
	}

	public UserActivity(int id, String activityName) {
		super();
		this.id = id;
		this.activityName = activityName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityName == null) ? 0 : activityName.hashCode());
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
		UserActivity other = (UserActivity) obj;
		if (activityName == null) {
			if (other.activityName != null)
				return false;
		} else if (!activityName.equals(other.activityName))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserActivity [id=" + id + ", activityName=" + activityName + "]";
	}
	
	
	
	
	
	

}
