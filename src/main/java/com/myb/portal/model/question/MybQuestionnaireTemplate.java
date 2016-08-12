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
@EntityListeners({AuditingEntityListener.class})
@Table(name="QUSTNR_TMPLT")
public class MybQuestionnaireTemplate  extends AbstractModel<String>{

	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="ACTIVE_FLAG", length=20)
	private String activeFlag;
	
	@Column(name="CREATE_BY", length=32)
	private String createBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATED_TIME")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATED_TIME")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date updateTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INDUSTRY_ID", referencedColumnName = "ID")
	private MybIndustry industry;
	
	
	public MybQuestionnaireTemplate(){
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

	public String getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
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

	public MybIndustry getIndustry() {
		return industry;
	}

	public void setIndustry(MybIndustry industry) {
		this.industry = industry;
	}
	
}
