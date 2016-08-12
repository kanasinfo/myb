package com.myb.portal.model.content;

import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myb.portal.model.design.SpruceTemplate;
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="SpruceColumn")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_column")
@JsonIgnoreProperties({"parentColumn","childrenColumns"})
public class SpruceColumn extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	@Column(name="shortname", length=45)
	private String shortName;
	
	@Column(name="label", length=100)
	private String label;
	
	@Column(name="image", length=255)
	private String image;
	
	@Column(name="memberlevel")
	private int memberLevel;
	
	@Column(name="sortnum")
	private Integer sortNumber;
	
	@Column(name="isactive")
	private Integer isActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id")
	private SpruceColumn parentColumn;
	
	@OneToMany(mappedBy = "parentColumn",cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.LAZY)      
	@OrderBy(value = "sortNumber ASC")
	private Set<SpruceColumn> childrenColumns;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="template_id", referencedColumnName = "id")
	private SpruceTemplate spruceTemplate;

	@Column(name="content_article_id")
	private String defaultArticleId;
	
	@Column(name="description", length=300)
	private String description;
	
	@Transient
	private String parent;
	
	@Transient
	private Boolean hasChildren;
	
	public SpruceColumn(){
		this.id = StringUtils.generateUUID();
	}
	
	public void updateParentView(){
		this.setParent(this.getParentColumn()==null?null:this.getParentColumn().getId());
	}
	
	@Override
	public String getId() {
		return id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public SpruceColumn getParentColumn() {
		return parentColumn;
	}

	public void setParentColumn(SpruceColumn parentColumn) {
		this.parentColumn = parentColumn;
		this.parent = parentColumn==null?null:this.parentColumn.getId();
	}

	public Set<SpruceColumn> getChildrenColumns() {
		if(childrenColumns==null)
			childrenColumns = new HashSet<SpruceColumn>();
		return childrenColumns;
	}

	public void setChildrenColumns(Set<SpruceColumn> childrenColumns) {
		this.childrenColumns = childrenColumns;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SpruceTemplate getSpruceTemplate() {
		return spruceTemplate;
	}

	public void setSpruceTemplate(SpruceTemplate spruceTemplate) {
		this.spruceTemplate = spruceTemplate;
	}

	public String getDefaultArticleId() {
		return defaultArticleId;
	}

	public void setDefaultArticleId(String defaultArticleId) {
		this.defaultArticleId = defaultArticleId;
	}
	
}
