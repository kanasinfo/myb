package com.myb.portal.model.mongodb;

/**
 * <p>Title: QuestionChartMapping</p>
 * <p>Description: (查询命名条件)</p>
 * <p>date : 2016年2月27日 下午2:25:26 </p>
 * @author william
 * @version 1.0
 */
public class QuestionChartMapping {

	private String chartMappingId;
	private String questionGroupId;
	private String questionId;
	private String chartType;
	public String getChartMappingId() {
		return chartMappingId;
	}
	public void setChartMappingId(String chartMappingId) {
		this.chartMappingId = chartMappingId;
	}
	public String getQuestionGroupId() {
		return questionGroupId;
	}
	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getChartType() {
		return chartType;
	}
	public void setChartType(String chartType) {
		this.chartType = chartType;
	}
	
	
}	
