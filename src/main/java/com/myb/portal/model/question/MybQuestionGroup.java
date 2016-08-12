package com.myb.portal.model.question;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.myb.portal.support.AbstractModel;

@Entity
@EntityListeners({ AuditingEntityListener.class })
@Table(name = "QUESTION_GROUP")
public class MybQuestionGroup extends AbstractModel<String> {

	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="DISPLAY_VAL", length=100)
	private String displayValue;
	
	@Column(name="SORT_NUM")
	private Integer sortNumber;

	@Column(name="NORM_CALC_VAL")
	private Float normCalculateValue;
	
	@Column(name="NORM_INPUT_VAL")
	private Float normInputValue;
	
	@Column(name="CREATED_BY", length=32)
	private String createdBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_TIME")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date updateTime;
	
	@Column(name="TYPE", length=100)
	private Integer type;//1 表示显示组问题， 0表示显示问题的自己问题
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="qustnr_templt_id", referencedColumnName="id")
	private MybQuestionnaireTemplate questionnaireTemplate;
	
	@Column(name="custom_question_type")
	private String customQuestionType;//String, 数据放{com.myb.questiontype.Degree, com.myb.questiontype.Judge, com.myb.questiontype.SingleSelect, com.myb.questiontype.TextAnswer}// Class.forName();
	
	@Column(name="chart_one_dimnsn", length=100)
	private String chartOneDimnsn;
	
	@Column(name="chart_multi_dimnsn", length=100)
	private String chartMultiDimnsn;
	
	@Column(name="chart_store", length=100)
	private String chartStore;
	
	@Column(name="chart_time", length=100)
	private String chartTime;
	
	@Column(name="chart_time_dimnsn", length=100)
	private String chartTimeDimnsn;
	
	@Column(name="OPTIONAL_NUM")
	private Integer optionalNumber;
	
	@Column(name="active_flag")
	private Integer activeFlag;
	
	@Column(name="BUSINESS_TYPE") 
	private String businessType;
	
	@Column(name="FILTER_FLAG")
	private boolean filterFlag;
	

	public boolean isFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public MybQuestionGroup(){
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.createTime=new Date();
		this.updateTime=this.createTime;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public void setSortNumber(Integer sortNum) {
		this.sortNumber = sortNum;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public MybQuestionnaireTemplate getQuestionnaireTemplate() {
		return questionnaireTemplate;
	}

	public void setQuestionnaireTemplate(MybQuestionnaireTemplate questionTemplate) {
		this.questionnaireTemplate = questionTemplate;
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

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getOptionalNumber() {
		return optionalNumber;
	}

	public void setOptionalNumber(Integer optionalNumber) {
		this.optionalNumber = optionalNumber;
	}

}
