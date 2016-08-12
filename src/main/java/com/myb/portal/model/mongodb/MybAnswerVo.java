package com.myb.portal.model.mongodb;

import java.util.Date;

import net.sf.json.JSONArray;

public class MybAnswerVo {
	private String questionnaireId;
	private String qustnrName;
	private String endUserIdentity;
	private String tenementId;
	private Date createdDate;
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
