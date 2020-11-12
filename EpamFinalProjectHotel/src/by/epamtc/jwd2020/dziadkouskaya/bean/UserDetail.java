package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;


public class UserDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	
	public static final int DEFAULT_USER_DETAIL_ID = 0;
		
	private int id;
	private int userId;
	private String firstName;
	private String secondName;
	private String thirdName;
	private String firstNameEnglish;
	private String secondNameEnglish;
	private Date birthDate;
	private String passportNumber;
	private String passportId;
	private String passportOtherInfo;
	private Country country;
	private ClientCategory category;
	
	public UserDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserDetail(int userId) {
		super();
		this.userId = userId;
	}
	
	public UserDetail(int userId, ClientCategory category) {
		super();
		this.userId = userId;
		this.category = category;
	}


	public UserDetail(int userId, String firstName, String secondName) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
	}
	
	

	public UserDetail(int userId, String firstName, String secondName, Country country) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.country = country;
	}

	public UserDetail(int userId, String firstName, String secondName, String thirdName, String firstNameEnglish,
			String secondNameEnglish, Date birthDate, String passportNumber, String passportId,
			String passportOtherInfo, Country country) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.firstNameEnglish = firstNameEnglish;
		this.secondNameEnglish = secondNameEnglish;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
		this.passportId = passportId;
		this.passportOtherInfo = passportOtherInfo;
		this.country = country;
	}
	
	public UserDetail(String firstName, String secondName, String thirdName, String firstNameEnglish,
			String secondNameEnglish, Date birthDate, String passportNumber, String passportId,
			String passportOtherInfo, Country country) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.firstNameEnglish = firstNameEnglish;
		this.secondNameEnglish = secondNameEnglish;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
		this.passportId = passportId;
		this.passportOtherInfo = passportOtherInfo;
		this.country = country;
	}
	

	public UserDetail(int id, int userId, String firstName, String secondName, String thirdName, String firstNameEnglish,
			String secondNameEnglish, Date birthDate, String passportNumber, String passportId,
			String passportOtherInfo, Country country) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.firstNameEnglish = firstNameEnglish;
		this.secondNameEnglish = secondNameEnglish;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
		this.passportId = passportId;
		this.passportOtherInfo = passportOtherInfo;
		this.country = country;
	}
	
	

	public UserDetail(int userId, String firstName, String secondName, String thirdName, String firstNameEnglish,
			String secondNameEnglish, Date birthDate, String passportNumber, String passportId,
			String passportOtherInfo, Country country, ClientCategory category) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.firstNameEnglish = firstNameEnglish;
		this.secondNameEnglish = secondNameEnglish;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
		this.passportId = passportId;
		this.passportOtherInfo = passportOtherInfo;
		this.country = country;
		this.category = category;
	}
	
	public UserDetail(int id,int userId, String firstName, String secondName, String thirdName, String firstNameEnglish,
			String secondNameEnglish, Date birthDate, String passportNumber, String passportId,
			String passportOtherInfo, Country country, ClientCategory category) {
		super();
		this.id = id;
		this.userId = userId;
		this.firstName = firstName;
		this.secondName = secondName;
		this.thirdName = thirdName;
		this.firstNameEnglish = firstNameEnglish;
		this.secondNameEnglish = secondNameEnglish;
		this.birthDate = birthDate;
		this.passportNumber = passportNumber;
		this.passportId = passportId;
		this.passportOtherInfo = passportOtherInfo;
		this.country = country;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public ClientCategory getCategory() {
		return category;
	}

	public void setCategory(ClientCategory category) {
		this.category = category;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getThirdName() {
		return thirdName;
	}

	public void setThirdName(String thirdName) {
		this.thirdName = thirdName;
	}

	public String getFirstNameEnglish() {
		return firstNameEnglish;
	}

	public void setFirstNameEnglish(String firstNameEnglish) {
		this.firstNameEnglish = firstNameEnglish;
	}

	public String getSecondNameEnglish() {
		return secondNameEnglish;
	}

	public void setSecondNameEnglish(String secondNameEnglish) {
		this.secondNameEnglish = secondNameEnglish;
	}


	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getPassportOtherInfo() {
		return passportOtherInfo;
	}

	public void setPassportOtherInfo(String passportOtherInfo) {
		this.passportOtherInfo = passportOtherInfo;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}


	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((firstNameEnglish == null) ? 0 : firstNameEnglish.hashCode());
		result = prime * result + id;
		result = prime * result + ((passportId == null) ? 0 : passportId.hashCode());
		result = prime * result + ((passportNumber == null) ? 0 : passportNumber.hashCode());
		result = prime * result + ((passportOtherInfo == null) ? 0 : passportOtherInfo.hashCode());
		result = prime * result + ((secondName == null) ? 0 : secondName.hashCode());
		result = prime * result + ((secondNameEnglish == null) ? 0 : secondNameEnglish.hashCode());
		result = prime * result + ((thirdName == null) ? 0 : thirdName.hashCode());
		result = prime * result + userId;
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
		UserDetail other = (UserDetail) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (firstNameEnglish == null) {
			if (other.firstNameEnglish != null)
				return false;
		} else if (!firstNameEnglish.equals(other.firstNameEnglish))
			return false;
		if (id != other.id)
			return false;
		if (passportId == null) {
			if (other.passportId != null)
				return false;
		} else if (!passportId.equals(other.passportId))
			return false;
		if (passportNumber == null) {
			if (other.passportNumber != null)
				return false;
		} else if (!passportNumber.equals(other.passportNumber))
			return false;
		if (passportOtherInfo == null) {
			if (other.passportOtherInfo != null)
				return false;
		} else if (!passportOtherInfo.equals(other.passportOtherInfo))
			return false;
		if (secondName == null) {
			if (other.secondName != null)
				return false;
		} else if (!secondName.equals(other.secondName))
			return false;
		if (secondNameEnglish == null) {
			if (other.secondNameEnglish != null)
				return false;
		} else if (!secondNameEnglish.equals(other.secondNameEnglish))
			return false;
		if (thirdName == null) {
			if (other.thirdName != null)
				return false;
		} else if (!thirdName.equals(other.thirdName))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDetail [id=" + id + ", userId=" + userId + ", firstName=" + firstName + ", secondName=" + secondName
				+ ", thirdName=" + thirdName + ", firstNameEnglish=" + firstNameEnglish + ", secondNameEnglish="
				+ secondNameEnglish + ", birthDate=" + birthDate + ", passportNumber=" + passportNumber
				+ ", passportId=" + passportId + ", passportOtherInfo=" + passportOtherInfo + ", country=" + country
				+ ", category=" + category + "]";
	}

	
	

}
