package com.myb.portal.model.content;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;


@Entity(name="SpruceMemberType")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_link_type")
public class MemberType extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;
	
	
	@Column(name="label", length=50)
	private String label;

	
	public MemberType(){
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

}
