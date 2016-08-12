package com.myb.portal.model.design;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;


@Entity
@Table(name="spruce_template")
public class SpruceTemplate extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="name", length=100)
	private String name;
	
	@Column(name="template_path", length=100)
	private String templatePath;

	@Column(name="sort_number")
	private Integer sortNumber;	

	@Transient
	private String themeName;
	
	@Transient
	private String templateName;
	
	public SpruceTemplate(){
		this.id = StringUtils.generateUUID();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getName() {                             
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTemplatePath() {
		return templatePath;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}

	public String getThemeName() {
		if(themeName==null&&templatePath!=null) updateThemeTemplate();
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getTemplateName() {
		if(templateName==null&&templatePath!=null) updateThemeTemplate();
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public void updateTemplatePath(){
		this.templatePath=this.themeName+"/"+this.templateName;
	}
	
	private void updateThemeTemplate(){
		if(templatePath==null){
			themeName=null;
			templateName=null;
		}else{
			templatePath=templatePath.replace("\\", "/");
			int pos = templatePath.lastIndexOf("/");
			if(pos!=-1){
				themeName=templatePath.substring(0, pos);
				templateName=templatePath.substring(pos+1);
			}else{
				themeName="";
				templateName=templatePath;
			}
		}
	}
}
