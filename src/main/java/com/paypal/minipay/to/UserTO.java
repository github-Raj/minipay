package com.paypal.minipay.to;

public class UserTO {
	private String userId;

	private String name;

	private String email;

	private String password;

	private String type;

	private String currency;

	public UserTO() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserTO [name=").append(name).append(", email=").append(email).append(", password=")
				.append(password).append(", type=").append(type).append("]");
		return builder.toString();
	}

}
