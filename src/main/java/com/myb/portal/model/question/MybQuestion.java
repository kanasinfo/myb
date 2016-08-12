package com.myb.portal.model.question;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.myb.portal.support.AbstractModel;

@Entity
@EntityListeners({ AuditingEntityListener.class })
@Table(name = "QUESTION")
public class MybQuestion extends AbstractModel<String> {
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="NAME", length=100)
	private String name;
	
	@Column(name="VALUE", length=100)
	private String value;
	
	@Column(name="QUESTION_TYPE", length=100)
	private String questionType;//String, 数据放{com.myb.questiontype.Degree, com.myb.questiontype.Judge, com.myb.questiontype.SingleSelect, com.myb.questiontype.TextAnswer}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="QUESTION_GROUP_ID", referencedColumnName = "ID")
	private MybQuestionGroup questionGroup;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUSTNR_TEMP_ID", referencedColumnName = "ID")
	private MybQuestionnaireTemplate questionTemplate;

	@Column(name="SORT_NUM")
	private Integer sortNumber;
	
	@Column(name="NORM_CALC_VAL")
	private Float normCalculateValue;

	@Column(name="NORM_INPUT_VAL")
	private Float normInputValue;

	@Column(name="STATUS", length=20)
	private String status;
	
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
	
	@Column(name="top_flag")
	private int topFlag;

	@Column(name="edit_flag")
	private int editFlag;
	
	@Column(name="chart_one_dimnsn", length=100)
	private String chartOneDimension;
	
	@Column(name="chart_multi_dimnsn", length=100)
	private String chartMultiDimension;
	
	@Column(name="chart_store", length=100)
	private String chartStore;
	
	@Column(name="chart_time", length=100)
	private String chartTime;
	
	@Column(name="chart_time_dimnsn", length=100)
	private String chartTimeDimension;
	
	@Column(name="BUSINESS_TYPE") 
	private String businessType;
	
	@Column(name="FILTER_FLAG")
	private boolean filterFlag;
	
	@OneToMany(mappedBy="question",fetch=FetchType.LAZY)
	@OrderBy(value = "sortNumber ASC")
	private Set<MybOption> options;
	
	@Column(name="DATA_SOURCES_ID")
	private String dataSourcesId;
	
	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public boolean isFilterFlag() {
		return filterFlag;
	}

	public void setFilterFlag(boolean filterFlag) {
		this.filterFlag = filterFlag;
	}

	public String getDataSourcesId() {
		return dataSourcesId;
	}

	public void setDataSourcesId(String dataSourcesId) {
		this.dataSourcesId = dataSourcesId;
	}


	public MybQuestion(){
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.createTime=new Date();
		this.updateTime=this.createTime;
	}

	public int getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public MybQuestionnaireTemplate getQuestionTemplate() {
		return questionTemplate;
	}

	public void setQuestionTemplate(MybQuestionnaireTemplate questionTemplate) {
		this.questionTemplate = questionTemplate;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNum) {
		this.sortNumber = sortNum;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public MybQuestionGroup getQuestionGroup() {
		return questionGroup;
	}

	public void setQuestionGroup(MybQuestionGroup questionGroup) {
		this.questionGroup = questionGroup;
	}

	public String getChartOneDimension() {
		return chartOneDimension;
	}

	public void setChartOneDimension(String chartOneDimension) {
		this.chartOneDimension = chartOneDimension;
	}

	public String getChartMultiDimension() {
		return chartMultiDimension;
	}

	public void setChartMultiDimension(String chartMultiDimension) {
		this.chartMultiDimension = chartMultiDimension;
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

	public String getChartTimeDimension() {
		return chartTimeDimension;
	}

	public void setChartTimeDimension(String chartTimeDimension) {
		this.chartTimeDimension = chartTimeDimension;
	}

	public Set<MybOption> getOptions() {
		return options;
	}

	public void setOptions(Set<MybOption> options) {
		this.options = options;
	}


	public int getTopFlag() {
		return topFlag;
	}

	public void setTopFlag(int topFlag) {
		this.topFlag = topFlag;
	}

}
