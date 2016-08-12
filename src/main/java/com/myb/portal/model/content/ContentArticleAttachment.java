package com.myb.portal.model.content;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity
@Table(name="spruce_article_attachment")
@JsonIgnoreProperties({""})
public class ContentArticleAttachment extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="filename", length=100)
	private String filename;

	@Column(name="article_id")
	private String articleId;
	
	public ContentArticleAttachment(){
		this.id = StringUtils.generateUUID();
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

}
