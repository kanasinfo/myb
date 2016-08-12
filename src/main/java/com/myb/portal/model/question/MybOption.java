package com.myb.portal.model.question;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.myb.portal.support.AbstractModel;

@Entity

@EntityListeners({AuditingEntityListener.class})
@Table(name="OPTIONS")
public class MybOption extends AbstractModel<String>{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="value", length=100)
	private String value;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="QUESTION_ID", referencedColumnName="ID")
	private MybQuestion question;
	
	@Column(name="SORT_NUM")
	private Integer sortNumber;
	
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
	
	public MybOption(){
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MybQuestion getQuestion() {
		return question;
	}

	public void setQuestion(MybQuestion question) {
		this.question = question;
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

}
