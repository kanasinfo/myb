package com.myb.portal.model.mongodb;

import java.util.List;

public class QuestionsVo {
	//问题主键
	private String questionId;
	//所属问题组ID
	private String questionGroupId;
	//所属问题组名称
	private String questionGroupName;
	//所属问题描述
	private String questionGroupValue;
	//问题组描述
	private int questionGroupSortNumber;
	//问题名称
	private String questionName;
	//问题描述
	private String questionValue;
	//排序
	private int sortNumber;
	//是否选中该节点
	private boolean activeFlag;
	//是模板问题还是自定义问题
	private boolean templateFlag;
	//
	private boolean filterFlag;
	
	private int editFlag;
	//置顶表叔
	private Integer topFlag;
	//单维度
	private String chartOneDimnsn;
	//多维度
	private String chartMultiDimnsn;
	//门店纬度	
	private String chartStore;
	//时间纬度
	private String chartTime;
	//时间多维度图表
	private String chartTimeDimnsn;
	//自动统计norm值
	private double normCalculateValue;
	//手动输入norm值
	private double normInputValue;
	//问题答案
	private List<Options> options;
	private String questionType;
	private String dataSourcesId;
	private String businessType;
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	private List<ActivePeriod> activePeriod;
	
	public double getNormCalculateValue() {
		return normCalculateValue;
	}

	public void setNormCalculateValue(double normCalculateValue) {
		this.normCalculateValue = normCalculateValue;
	}

	public double getNormInputValue() {
		return normInputValue;
	}

	public void setNormInputValue(double normInputValue) {
		this.normInputValue = normInputValue;
	}

	public List<ActivePeriod> getActivePeriod() {
		return activePeriod;
	}

	public void setActivePeriod(List<ActivePeriod> activePeriod) {
		this.activePeriod = activePeriod;
	}

	public String getDataSourcesId() {
		return dataSourcesId;
	}

	public void setDataSourcesId(String dataSourcesId) {
		this.dataSourcesId = dataSourcesId;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionGroupId() {
		return questionGroupId;
	}

	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}

	public String getQuestionGroupName() {
		return questionGroupName;
	}

	public void setQuestionGroupName(String questionGroupName) {
		this.questionGroupName = questionGroupName;
	}

	public String getQuestionGroupValue() {
		return questionGroupValue;
	}

	public void setQuestionGroupValue(String questionGroupValue) {
		this.questionGroupValue = questionGroupValue;
	}

	public int getQuestionGroupSortNumber() {
		return questionGroupSortNumber;
	}

	public void setQuestionGroupSortNumber(int questionGroupSortNumber) {
		this.questionGroupSortNumber = questionGroupSortNumber;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionValue() {
		return questionValue;
	}

	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}

	public int getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public boolean isTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(boolean templateFlag) {
		this.templateFlag = templateFlag;
	}

	public boolean isFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
	}

	public String getChartOneDimnsn() {
		return chartOneDimnsn;
	}

	public void setChartOneDimnsn(String chartOneDimnsn) {
		this.chartOneDimnsn = chartOneDimnsn;
	}

	public String getChartMultiDimnsn() {
		return chartMultiDimnsn;
	}

	public void setChartMultiDimnsn(String chartMultiDimnsn) {
		this.chartMultiDimnsn = chartMultiDimnsn;
	}

	public String getChartStore() {
		return chartStore;
	}

	public void setChartStore(String chartStore) {
		this.chartStore = chartStore;
	}

	public String getChartTime() {
		return chartTime;
	}

	public void setChartTime(String chartTime) {
		this.chartTime = chartTime;
	}

	public String getChartTimeDimnsn() {
		return chartTimeDimnsn;
	}

	public void setChartTimeDimnsn(String chartTimeDimnsn) {
		this.chartTimeDimnsn = chartTimeDimnsn;
	}

	public int getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}

	public Integer getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(Integer topFlag) {
		this.topFlag = topFlag;
	}
	
	public List<Options> getOptions() {
		return options;
	}
	public void setOptions(List<Options> options) {
		this.options = options;
	}
}
