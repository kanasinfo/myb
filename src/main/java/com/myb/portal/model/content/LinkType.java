package com.myb.portal.model.content;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="SpruceLinkType")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_link_type")
@JsonIgnoreProperties({"links"})
public class LinkType extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	
	@Column(name="label", length=50)
	private String label;
	
	@Column(name="description", length=1000)
	private String description;
	
	@Column(name="img_width")
	private Integer imageWidth;
	
	@Column(name="img_height")
	private Integer imageHeight;
	
	@Column(name="img_force_scale")
	private Integer imageForceScale;

	@OneToMany(mappedBy = "linkType",cascade = {CascadeType.MERGE, CascadeType.REFRESH},fetch=FetchType.LAZY)      
	@OrderBy(value = "sortNumber ASC")
	private Set<Link> links;
	
	public LinkType(){
		this.id = StringUtils.generateUUID();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(Integer imageWidth) {
		this.imageWidth = imageWidth;
	}

	public Integer getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(Integer imageHeight) {
		this.imageHeight = imageHeight;
	}

	public Integer getImageForceScale() {
		return imageForceScale;
	}

	public void setImageForceScale(Integer imageForceScale) {
		this.imageForceScale = imageForceScale;
	}

	public Set<Link> getLinks() {
		return links;
	}

	public void setLinks(Set<Link> links) {
		this.links = links;
	}

}
