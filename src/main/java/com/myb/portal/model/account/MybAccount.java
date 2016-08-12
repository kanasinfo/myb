package com.myb.portal.model.account;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="MybAccount")
@EntityListeners({AuditingEntityListener.class})
@Table(name="ACCOUNT")
public class MybAccount extends AbstractModel<String>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="LOGIN_EMAIL", length=100)
	private String loginEmail;
	
	@Column(name="LOGIN_PASS", length=100)
	private String loginPass;
	@Column(name="LOGINPWD_SALT",length=20)
	private String loginPwdSalt;
	@Column(name="EMAIL_CHECK_STRING", length=100)
	private String emailCheckStr;
	
	@Column(name="PHONE", length=100)
	private String phone;
	
	@Column(name="EMAIL_CHECK_STATUS", length=100)
	private String emailCheckStatus;
	
	@Column(name="REG_DATE")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date regDate;
	
	@Column(name="CREDIT_AMOUNT")
	private Integer creaditAmount;
	
	@Column(name="CREATED_BY",length=100)
	private String createdBy;
	
	@Column(name="CREATED_TIME")
	private Date createdTime;
	
	@Column(name="UPDATED_TIME")
	private Date updatedfTime;

	
	public String getLoginPwdSalt() {
		return loginPwdSalt;
	}

	public void setLoginPwdSalt(String loginPwdSalt) {
		this.loginPwdSalt = loginPwdSalt;
	}
	public MybAccount(){
		this.id = StringUtils.generateUUID();
	}

	@Override
	public String getId() {
		return id;
	}


	public String getLoginEmail() {
		return loginEmail;
	}


	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}


	public String getLoginPass() {
		return loginPass;
	}


	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}


	public String getEmailCheckStr() {
		return emailCheckStr;
	}


	public void setEmailCheckStr(String emailCheckStr) {
		this.emailCheckStr = emailCheckStr;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmailCheckStatus() {
		return emailCheckStatus;
	}


	public void setEmailCheckStatus(String emailCheckStatus) {
		this.emailCheckStatus = emailCheckStatus;
	}


	public Date getRegDate() {
		return regDate;
	}


	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}


	public Integer getCreaditAmount() {
		return creaditAmount;
	}


	public void setCreaditAmount(Integer creaditAmount) {
		this.creaditAmount = creaditAmount;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		if (createdTime==null) {
			this.createdTime = new Date();
		}else{
			this.createdTime = createdTime;
		}
		
	}

	public Date getUpdatedfTime() {
		return updatedfTime;
	}

	public void setUpdatedfTime(Date updatedfTime) {
		if (updatedfTime==null) {
			this.updatedfTime = new Date();
		}else{
			this.updatedfTime = updatedfTime;	
		}
		
	}
	
	
	

}
