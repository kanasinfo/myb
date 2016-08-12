package com.myb.portal.model.content;

import java.util.Date;

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

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="SpruceLink")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_column")
@JsonIgnoreProperties({"linkType"})
public class Link extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="label", length=50)
	private String label;
	
	@Column(name="weburl", length=200)
	private String weburl;

	@Column(name="description", length=500)
	private String description;

	@Column(name="img_url", length=200)
	private String imageUrl;
	
	@Column(name="sort_num")
	private String sortNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	@LastModifiedDate
	private Date updateTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "link_type_id", referencedColumnName = "id")
	private LinkType linkType;
	

	public Link(){
		this.id = StringUtils.generateUUID();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getWeburl() {
		return weburl;
	}

	public void setWeburl(String weburl) {
		this.weburl = weburl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(String sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public LinkType getLinkType() {
		return linkType;
	}

	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}

}
