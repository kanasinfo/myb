package com.myb.portal.model.content;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="SpruceNavigator")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_navigator")
@JsonIgnoreProperties({"parentNavigator", "childrenNavigators"})
public class SpruceNavigator extends AbstractModel<String>{
	
	public static enum LinkType{
		COLUMN,
		LINK,
		ARTICLE;
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

	@Column(name="label", length=100)
	private String label;
	
	@Enumerated(EnumType.STRING)
	@Column(name="type", length=20)
	private LinkType type;
	
	@Column(name="link", length=255)
	private String link;
	
	@Column(name="sortnum")
	private Integer sortNumber;
	
	@Column(name="active_flag")
	private Boolean activeFlag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id", referencedColumnName="id")
	private SpruceNavigator parentNavigator;
	
	@OneToMany(mappedBy = "parentNavigator",cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.LAZY)
	@OrderBy(value = "sortNumber ASC")
	private Set<SpruceNavigator> childrenNavigators;
	
	@Transient
	private String parent;
	
	@Transient
	private Boolean hasChildren;
	
	public SpruceNavigator(){
		this.id = StringUtils.generateUUID();
	}
	
	public void updateParentView(){
		this.setParent(this.getParentNavigator()==null?null:this.getParentNavigator().getId());
	}
	
	@Override
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public LinkType getType() {
		return type;
	}

	public void setType(LinkType type) {
		this.type = type;
	}
	
	public void setType(String type){
		this.type = LinkType.valueOf(type);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Integer sortNumber) {
		this.sortNumber = sortNumber;
	}

	public Boolean getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(Boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public SpruceNavigator getParentNavigator() {
		return parentNavigator;
	}

	public void setParentNavigator(SpruceNavigator parentNavigator) {
		this.parentNavigator = parentNavigator;
		this.parent = parentNavigator == null ? null : parentNavigator.getId();
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

	public Set<SpruceNavigator> getChildrenNavigators() {
		if(childrenNavigators == null){
			childrenNavigators = new HashSet<SpruceNavigator>();
		}
		return childrenNavigators;
	}

	public void setChildrenNavigators(Set<SpruceNavigator> childrenNavigators) {
		this.childrenNavigators = childrenNavigators;
	}
	

}
