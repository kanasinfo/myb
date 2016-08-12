package com.myb.portal.model.question;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
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
@EntityListeners({AuditingEntityListener.class})
@Table(name="INDUSTRY")
public class MybIndustry extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;

	@Column(name="name", length=100)
	private String name;
	
	@Column(name="image", length=255)
	private String image;
	
	@Column(name="description", length=300)
	private String description;
	
	@Column(name="isactive")
	private Integer activeFlag;
	
	@Column(name="sortnumber")
	private Integer sortNumber;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private MybIndustry parentIndustry;
	
	@OneToMany(mappedBy = "parentIndustry",cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.LAZY)      
	@OrderBy(value = "sortNumber ASC")
	private Set<MybIndustry> childrenIndustries;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_time")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date createTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="updated_time")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date updateTime;
	
	@Transient
	private String parent;
	
	@Transient
	private Boolean hasChildren;
	
	@Transient
	private Integer level;
	
	@Transient
	private Integer templateFlag;

	public MybIndustry(){
		this.id = UUID.randomUUID().toString().replaceAll("-", "");
		this.createTime=new Date();
	}
	
	public void updateParentView(){
		this.setParent(this.getParentIndustry()==null?null:this.getParentIndustry().getId());
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

	public MybIndustry getParentIndustry() {
		return parentIndustry;
	}

	public void setParentIndustry(MybIndustry parentIndustry) {
		this.parentIndustry = parentIndustry;
		this.parent = parentIndustry==null?null:this.parentIndustry.getId();
	}

	public Set<MybIndustry> getChildrenIndustries() {
		return childrenIndustries;
	}

	public void setChildrenIndustries(Set<MybIndustry> childrenIndustries) {
		if(childrenIndustries==null)
			childrenIndustries = new HashSet<MybIndustry>();
		this.childrenIndustries = childrenIndustries;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Boolean getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(Boolean hasChildren) {
		this.hasChildren = hasChildren;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Integer activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getTemplateFlag() {
		return templateFlag;
	}

	public void setTemplateFlag(Integer templateFlag) {
		this.templateFlag = templateFlag;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
