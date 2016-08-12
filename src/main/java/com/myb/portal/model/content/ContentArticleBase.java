package com.myb.portal.model.content;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;


@MappedSuperclass
public class ContentArticleBase extends AbstractModel<String> {

	public static enum StatusType{
		//length is limited to 10
		DRAFT,
		RELEASE;
		@Override
		public String toString(){
			return this.name();
		}
	}
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", length=32)
	private String id;
	@Column(name="title", length=100)
	private String title;
	@Column(name="author", length=100)
	private String author;
	@Column(name="keywords",length=200)
	private String keywords;
	@Column(name="summary",length=1000)
	private String summary;
	@Column(name="content")
	private String content;
	@Temporal(TemporalType.DATE)
	@Column(name="issue_time")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date issueTime;
	@Column(name="cover_picture", length=200)
	private String coverPicture;
	@Column(name="sort_num")
	private Integer sortNumber;
	@Enumerated(EnumType.STRING)
	@Column(name="status")
	private StatusType status;

	
	public ContentArticleBase() {
		this.id = StringUtils.generateUUID();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		if(title == null) title = "";
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		if(content == null) content = "";
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}
	public String getCoverPicture() {
		return coverPicture;
	}

	public void setCoverPicture(String coverPicture) {
		this.coverPicture = coverPicture;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}
	
	public void setStatus(String status) {
		this.status = StatusType.valueOf(status);
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}
