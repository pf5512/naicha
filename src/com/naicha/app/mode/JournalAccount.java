package com.naicha.app.mode;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "journalAccount")
public class JournalAccount implements Serializable{

	
	private Integer id;
	private Integer userId;
	private String userIdTransaction;//交易对象
	private Double transactionAmt;//交易金额
	private Double accountBalance;//账户余额
	private Date transactionDate;//交易日期
	private Date accountingDate;//记账日期
	private String transactionType;//交易类型
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "userId", nullable = false)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@Column(name = "userIdTransaction", nullable = false)
	public String getUserIdTransaction() {
		return userIdTransaction;
	}
	public void setUserIdTransaction(String userIdTransaction) {
		this.userIdTransaction = userIdTransaction;
	}
	
	@Column(name = "transactionAmt", nullable = false)
	public Double getTransactionAmt() {
		return transactionAmt;
	}
	public void setTransactionAmt(Double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}
	
	@Column(name = "accountBalance", nullable = false)
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	@Column(name = "transactionDate", nullable = false)
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	@Column(name = "transactionType", nullable = false)
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	
	@Column(name = "accountingDate", nullable = false)
	public Date getAccountingDate() {
		return accountingDate;
	}
	public void setAccountingDate(Date accountingDate) {
		this.accountingDate = accountingDate;
	}
	
	
}
