package com.myb.portal.model.mongodb;

public class QuestionGroupVO {
	private String questionGroupId;
	public String getQuestionGroupId() {
		return questionGroupId;
	}
	public void setQuestionGroupId(String questionGroupId) {
		this.questionGroupId = questionGroupId;
	}
	/**
	 * group下选中问题的个数。为回填问卷使用
	 */
	private Integer selectQuestionCount;
	private String name;
	private String displayValue;
	private Integer sortNumber;
	private boolean filterFlag;
	private Float normCalculateValue;
	private Float normInputValue;
	private String createdBy;
	private Integer type;
	private String customQuestionType;
	private String chartOneDimnsn;
	private String chartMultiDimnsn;
	private String chartStore;
	private String chartTime;
	private String chartTimeDimnsn;
	private Integer activeFlag;
	private Integer optionalNumber;
	private String businessType;
	//当前问题组是否选中
	private boolean select;
	
	public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public Integer getSelectQuestionCount() {
		return selectQuestionCount;
	}
	public void setSelectQuestionCount(Integer selectQuestionCount) {
		this.selectQuestionCount = selectQuestionCount;
	}
	public boolean getFilterFlag() {
		return filterFlag;
	}
	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
	}
	public Integer getOptionalNumber() {
		return optionalNumber;
	}
	public void setOptionalNumber(Integer optionalNumber) {
		this.optionalNumber = optionalNumber;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public Integer getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}
	public Float getNormCalculateValue() {
		return normCalculateValue;
	}
	public void setNormCalculateValue(Float normCalculateValue) {
		this.normCalculateValue = normCalculateValue;
	}
	public Float getNormInputValue() {
		return normInputValue;
	}
	public void setNormInputValue(Float normInputValue) {
		this.normInputValue = normInputValue;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getCustomQuestionType() {
		return customQuestionType;
	}
	public void setCustomQuestionType(String customQuestionType) {
		this.customQuestionType = customQuestionType;
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
	public Integer getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	
}
