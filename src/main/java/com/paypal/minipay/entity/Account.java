package com.paypal.minipay.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paypal.minipay.commons.CURRENCY;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ACCNT_NBR", updatable = false, nullable = false)
	private int accountNumber;

	@Column(name = "ACCNT_BAL")
	private double balance;

	@Column(name = "ACCNT_CURR")
	private CURRENCY currency;

	@Column(name = "CREATE_TS")
	private Date createTs;

	@Column(name = "LAST_CHANGE_TS")
	private Date lastChangeTs;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Transient
	@JsonIgnore
	private double amount;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public CURRENCY getCurrency() {
		return currency;
	}

	public void setCurrency(CURRENCY currency) {
		this.currency = currency;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	

}
