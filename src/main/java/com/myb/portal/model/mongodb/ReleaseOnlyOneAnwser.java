package com.myb.portal.model.mongodb;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="releaseCount")
public class ReleaseOnlyOneAnwser {
	@Id
	private String id;
	//模板ID
	private String questionId;
	//详情数据
	List<ReleaseOnlyOneAnwserData> listDate;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public List<ReleaseOnlyOneAnwserData> getListDate() {
		return listDate;
	}
	public void setListDate(List<ReleaseOnlyOneAnwserData> listDate) {
		this.listDate = listDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
