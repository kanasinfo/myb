package com.myb.portal.model.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.myb.portal.support.AbstractModel;
@Entity(name="MybTransHistory")
@EntityListeners({AuditingEntityListener.class})
@Table(name="TRANS_HISTORY")
public class MybTransHistory extends AbstractModel<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="ACCOUNT_ID",length=32)
	private String accountId;
	@Column(name="TRANS_TIME")
	private Date transTime;
	@Column(name="TRANS_AMOUNT")
	private double transAmount;
	@Column(name="TRANS_CREDIT_AMOUNT")
	private double transCreditAmount;
	@Column(name="CREDIT_AMOUNT")
	private double creditAmount;
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	
	
	public String getAccountId() {
		return accountId;
	}



	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}



	public Date getTransTime() {
		return transTime;
	}



	public void setTransTime(Date transTime) {
		this.transTime = transTime;
	}



	public double getTransAmount() {
		return transAmount;
	}



	public void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}



	public double getTransCreditAmount() {
		return transCreditAmount;
	}



	public void setTransCreditAmount(double transCreditAmount) {
		this.transCreditAmount = transCreditAmount;
	}



	public double getCreditAmount() {
		return creditAmount;
	}



	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}



	public Date getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}



	public void setId(String id) {
		this.id = id;
	}



	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
