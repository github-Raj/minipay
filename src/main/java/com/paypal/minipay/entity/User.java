package com.paypal.minipay.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.paypal.minipay.commons.Usertype;

@Entity
@Table(name = "USER")
public class User {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", updatable = false, nullable = false)
	private String userId;

	@Column(name = "USER_NAME")
	private String name;

	@Column(name = "USER_EMAIL")
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "USER_TYPE")
	private Usertype type;

	@Column(name = "CREATE_TS")
	private Date createTs;

	@Column(name = "LAST_CHANGE_TS")
	private Date lastChangeTs;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private Account account;

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

	public Usertype getType() {
		return type;
	}

	public void setType(Usertype type) {
		this.type = type;
	}

	public Date getCreateTs() {
		return createTs;
	}

	public void setCreateTs(Date createTs) {
		this.createTs = createTs;
	}

	public Date getLastChangeTs() {
		return lastChangeTs;
	}

	public void setLastChangeTs(Date lastChangeTs) {
		this.lastChangeTs = lastChangeTs;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=").append(userId).append(", name=").append(name).append(", email=").append(email)
				.append(", password=").append(password).append(", type=").append(type).append(", createTs=")
				.append(createTs).append(", lastChangeTs=").append(lastChangeTs).append(", account=").append(account)
				.append("]");
		return builder.toString();
	}

}
