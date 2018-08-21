package com.paypal.minipay.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.paypal.minipay.commons.CURRENCY;
import com.paypal.minipay.commons.TRANSSTATUS;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRANS_ID", updatable = false, nullable = false)
	private String transactionId;

	@Column(name = "TRANS_AMT")
	private double amount;

	@Column(name = "TOTAL_BAL")
	private double balance;

	@Column(name = "TRANS_CURR")
	private CURRENCY currency;

	@Column(name = "TRANS_TS")
	private Date tarnsactionTs;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCNT_NBR")
	private Account account;

	@Column(name = "TRANS_STATUS")
	private TRANSSTATUS status;

	@Column(name = "TRANS_COMMENT")
	private String comment;

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CURRENCY getCurrency() {
		return currency;
	}

	public void setCurrency(CURRENCY currency) {
		this.currency = currency;
	}

	public Date getTarnsactionTs() {
		return tarnsactionTs;
	}

	public void setTarnsactionTs(Date tarnsactionTs) {
		this.tarnsactionTs = tarnsactionTs;
	}

	public TRANSSTATUS getStatus() {
		return status;
	}

	public void setStatus(TRANSSTATUS status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaction [transactionId=").append(transactionId).append(", amount=").append(amount)
				.append(", currency=").append(currency).append(", tarnsactionTs=").append(tarnsactionTs)
				.append(", account=").append(account).append(", status=").append(status).append(", comment=")
				.append(comment).append("]");
		return builder.toString();
	}

}
