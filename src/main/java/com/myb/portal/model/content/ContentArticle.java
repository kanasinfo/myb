package com.myb.portal.model.content;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name="KanasSpruceContentArticle")
@Table(name="spruce_content_article")
@JsonIgnoreProperties({"column"})
public class ContentArticle extends ContentArticleBase {

	@Transient
	private static final long serialVersionUID = 1L;


	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="column_id",referencedColumnName="id")
	private SpruceColumn column;
	
	
	@Transient
	private String columnId;
	@Transient
	private String columnLabel;

	public void fetchColumn(){
		if(this.column!=null){
			this.columnId=this.column.getId();
			this.columnLabel=this.column.getLabel();
		}
	}

	public SpruceColumn getColumn() {
		return column;
	}

	public void setColumn(SpruceColumn column) {
		this.column = column;
	}

	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getColumnLabel() {
		return columnLabel;
	}

	public void setColumnLabel(String columnLabel) {
		this.columnLabel = columnLabel;
	}
	
}
