package by.epamtc.jwd2020.dziadkouskaya.bean;

import java.io.Serializable;

public class User implements Serializable {
	public static final UserActivity DEFAULT_USER_ACTIVITY = new UserActivity(1, "Активный");

	private static final long serialVersionUID = 1L;

	private int userId;
	private String login;
	private String password;
	private String email;
	private String phone;
	private Role role;
	private UserActivity activity;

	public User() {

		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(int userId) {

		this.userId = userId;
	}

	public User(int userId, String login, String password) {

		this.userId = userId;
		this.login = login;
		this.password = password;
		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(String login, String password, Role role) {

		this.login = login;
		this.password = password;
		this.role = role;
		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(String login, String password, String email, Role role) {

		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(String login, String password, String email, String phone, Role role) {

		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(int userId, String login, String password, String email, String phone, Role role) {

		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.activity = DEFAULT_USER_ACTIVITY;
	}

	public User(int userId, String email, String phone, Role role) {

		this.userId = userId;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.activity = DEFAULT_USER_ACTIVITY;

	}

	public User(String login, String password, String email, Role role, UserActivity activity) {

		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.activity = activity;
	}

	public User(String login, String password, String email, String phone, Role role, UserActivity activity) {

		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.activity = activity;
	}

	public User(int userId, String login, String password, String email, String phone, Role role,
			UserActivity activity) {

		this.userId = userId;
		this.login = login;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.activity = activity;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public UserActivity getActivity() {
		return activity;
	}

	public void setActivity(UserActivity activity) {
		this.activity = activity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		User other = (User) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", login=" + login + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", role=" + role + ", activity=" + activity + "]";
	}

}
