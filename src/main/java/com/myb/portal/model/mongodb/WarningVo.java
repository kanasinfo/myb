package com.myb.portal.model.mongodb;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection="question_warning")
public class WarningVo {
	//主键id
	private String questionTemplId;
	private List<WarningOptionVo> warningOptionVo;
	public String getQuestionTemplId() {
		return questionTemplId;
	}
	public void setQuestionTemplId(String questionTemplId) {
		this.questionTemplId = questionTemplId;
	}
	public List<WarningOptionVo> getWarningOptionVo() {
		return warningOptionVo;
	}
	public void setWarningOptionVo(List<WarningOptionVo> warningOptionVo) {
		this.warningOptionVo = warningOptionVo;
	}
	
}
