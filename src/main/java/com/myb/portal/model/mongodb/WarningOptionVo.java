package com.myb.portal.model.mongodb;

public class WarningOptionVo {
	//主键id
	private String warningId;
	//自定义名称
	private String warningName;
	//选中问题的id
	private String questionId;
	//选中问题名称
	private String questionName;
	//是否选中
	private String warningFlag;
	private String questionType;
	
	//选中的分值
	private String waringStr;
	
	
	
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getWarningId() {
		return warningId;
	}
	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}
	public String getWarningName() {
		return warningName;
	}
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public String getWarningFlag() {
		return warningFlag;
	}
	public void setWarningFlag(String warningFlag) {
		this.warningFlag = warningFlag;
	}
	public String getWaringStr() {
		return waringStr;
	}
	public void setWaringStr(String waringStr) {
		this.waringStr = waringStr;
	}
	
	
}	
