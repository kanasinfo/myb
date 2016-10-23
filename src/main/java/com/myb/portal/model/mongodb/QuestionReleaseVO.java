package com.myb.portal.model.mongodb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.myb.portal.util.Param;

/**
 * <p>Title: QuestionReleaseVO</p>
 * <p>Description: (模板VO模型)</p>
 * <p>date : 2016年5月7日 下午10:52:43 </p>
 * @author wangzx
 * @version 1.0
 */
@Document(collection="release_qustnnr")
public class QuestionReleaseVO {
	//模板ID
	@Id
	private String id;
	//模板ID
	private String qustnrTmpltId;
	//行业名称
	private String industry;
	private String trendName;
	private String qustnrName;
	private String tenementId;
	private int creditAmount;
	//统计多少份
	private int collectedAmount;
	private String companyName;
	//状态
	private int qustnnrStatus;
	private Date createdTime;
	private Date updatedTime;
	//该用户有多少门店
	private List<Store> store;
	//用户选择了多少问题
	private List<QuestionsVo> questions;
	private List<StoreGroupVO> storeGroup;
	@Transient
	private String qustnnrStatusColor;
	@Transient
	private String qustnnrStatusName;
	private List<QuestionFilter> filters;
	private List<QuestionDimensions> dimensions;
	private List<QuestionChartMapping> chartMapping;
	private List<QuestionGroupVO> questionGroup;
	
	public List<QuestionGroupVO> getQuestionGroup() {
		return questionGroup;
	}
	public void setQuestionGroup(List<QuestionGroupVO> questionGroup) {
		this.questionGroup = questionGroup;
	}
	public List<QuestionDimensions> getDimensions() {
		return dimensions;
	}
	public void setDimensions(List<QuestionDimensions> dimensions) {
		this.dimensions = dimensions;
	}
	public List<QuestionFilter> getFilters() {
		return filters;
	}
	public void setFilters(List<QuestionFilter> filters) {
		this.filters = filters;
	}
	//欢迎语
	private Map<String, Object> welcome;
	
	public Map<String, Object> getWelcome() {
		return welcome;
	}
	public void setWelcome(Map<String, Object> welcome) {
		this.welcome = welcome;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQustnrTmpltId() {
		return qustnrTmpltId;
	}
	public void setQustnrTmpltId(String qustnrTmpltId) {
		this.qustnrTmpltId = qustnrTmpltId;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	public String getQustnrName() {
		return qustnrName;
	}
	public void setQustnrName(String qustnrName) {
		this.qustnrName = qustnrName;
	}
	public String getTenementId() {
		return tenementId;
	}
	public void setTenementId(String tenementId) {
		this.tenementId = tenementId;
	}
	public int getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(int creditAmount) {
		this.creditAmount = creditAmount;
	}
	public int getCollectedAmount() {
		return collectedAmount;
	}
	public void setCollectedAmount(int collectedAmount) {
		this.collectedAmount = collectedAmount;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getQustnnrStatus() {
		return qustnnrStatus;
	}
	public void setQustnnrStatus(int qustnnrStatus) {
		this.qustnnrStatus = qustnnrStatus;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getUpdatedTime() {
		return updatedTime;
	}
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	public List<StoreGroupVO> getStoreGroup() {
		return storeGroup;
	}
	public void setStoreGroup(List<StoreGroupVO> storeGroup) {
		this.storeGroup = storeGroup;
	}
	public String getQustnnrStatusColor() {
		return  Param.mapColor.get(qustnnrStatus);
	}
	public void setQustnnrStatusColor(String qustnnrStatusColor) {
		this.qustnnrStatusColor = Param.mapColor.get(qustnnrStatus);;
	}
	public String getQustnnrStatusName() {
		return Param.mapStatusName.get(qustnnrStatus);
	}
	public void setQustnnrStatusName(String qustnnrStatusName) {
		this.qustnnrStatusName =  Param.mapStatusName.get(qustnnrStatus);
	}
	public List<QuestionChartMapping> getChartMapping() {
		return chartMapping;
	}
	public void setChartMapping(List<QuestionChartMapping> chartMapping) {
		this.chartMapping = chartMapping;
	}
	public List<Store> getStore() {
		return store;
	}
	public void setStore(List<Store> store) {
		this.store = store;
	}
	public List<QuestionsVo> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionsVo> questions) {
		this.questions = questions;
	}
	
	
}
