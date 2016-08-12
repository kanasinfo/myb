package com.myb.portal.model.mongodb;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="answer_url")
public class AnswerUrl {
	private String id;
	private String questionId;
	private Date createDate;
	private List<AnswerOption> listUrl;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<AnswerOption> getListUrl() {
		return listUrl;
	}
	public void setListUrl(List<AnswerOption> listUrl) {
		this.listUrl = listUrl;
	}
}
