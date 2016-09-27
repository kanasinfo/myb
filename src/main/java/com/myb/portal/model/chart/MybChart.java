package com.myb.portal.model.chart;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.myb.portal.util.AjaxReq;

public abstract class MybChart {
	protected String questionnaireId;
	protected String questionId;
	protected String filter;
	protected String dimensionType;
	protected String dimension;
	protected String groupId;
	protected String dataSourcesId;
	protected double normInputValue;
	protected String questionGroup;
	protected String questionName;
	protected String specialQuestions;



	public MybChart(){
		
	}
	
	public MybChart(String questionnaireId, String groupId, String questionId,String filter,
			String dimensionType, String dimension,String dataSourcesId, double normInputValue,String questionGroup,String questionName, String specialQuestions){
		this.questionId=questionId;
		this.questionnaireId=questionnaireId;
		this.groupId=groupId;
		this.filter=filter;
		this.dimensionType=dimensionType;
		this.dimension=dimension;
		this.normInputValue=normInputValue;
		this.dataSourcesId=dataSourcesId;
		this.questionGroup=questionGroup;
		this.questionName=questionName;
		this.specialQuestions=specialQuestions;
	}
	
	public abstract AjaxReq getData(MongoTemplate mongoTemplate);

	public String getQuestionnaireId() {
		return questionnaireId;
	}

	public void setQuestionnaireId(String questionnaireId) {
		this.questionnaireId = questionnaireId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getDimensionType() {
		return dimensionType;
	}

	public void setDimensionType(String dimensionType) {
		this.dimensionType = dimensionType;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public double getNormInputValue() {
		return normInputValue;
	}

	public void setNormInputValue(double normInputValue) {
		this.normInputValue = normInputValue;
	}
	
	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(String questionGroup) {
		this.questionGroup = questionGroup;
	}

	public String getDataSourcesId() {
		return dataSourcesId;
	}

	public void setDataSourcesId(String dataSourcesId) {
		this.dataSourcesId = dataSourcesId;
	}
	
	public String getSpecialQuestions() {
		return specialQuestions;
	}

	public void setSpecialQuestions(String specialQuestions) {
		this.specialQuestions = specialQuestions;
	}
	
}
