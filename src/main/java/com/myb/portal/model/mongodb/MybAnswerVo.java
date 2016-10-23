package com.myb.portal.model.mongodb;

import java.util.Calendar;
import java.util.Date;

import com.myb.portal.util.Utils;

import net.sf.json.JSONArray;

public class MybAnswerVo {
	private String questionnaireId;
	private String qustnrName;
	private String endUserIdentity;
	private String tenementId;
	private Date createdDate;
	private String year;
	private String quarter;
	private String month;
	private String day;
	private String storeId;
	private int status;
	
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getQuarter() {
		return quarter;
	}
	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	private JSONArray answers;
	public String getQuestionnaireId() {
		return questionnaireId;
	}
	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}
	public String getQustnrName() {
		return qustnrName;
	}
	public void setQustnrName(String qustnrName) {
		this.qustnrName = qustnrName;
	}
	public String getEndUserIdentity() {
		return endUserIdentity;
	}
	public void setEndUserIdentity(String endUserIdentity) {
		this.endUserIdentity = endUserIdentity;
	}
	public String getTenementId() {
		return tenementId;
	}
	public void setTenementId(String tenementId) {
		this.tenementId = tenementId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public JSONArray getAnswers() {
		return answers;
	}
	public void setAnswers(JSONArray answers) {
		this.answers = answers;
	}
	
	
	
	
}
