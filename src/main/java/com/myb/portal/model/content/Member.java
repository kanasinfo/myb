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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.myb.portal.support.AbstractModel;
import com.myb.portal.util.StringUtils;

@Entity(name="SpruceMember")
@EntityListeners({AuditingEntityListener.class})
@Table(name="spruce_member")
@JsonIgnoreProperties({"memberType"})
public class Member extends AbstractModel<String>{

	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id", length=32)
	private String id;

	@Column(name="name", length=100)
	private String name;

	@Column(name="address", length=200)
	private String address;

	@Column(name="address_level1", length=50)
	private String addressLevelOne;

	@Column(name="address_level2", length=50)
	private String addressLevelTwo;

	@Column(name="website", length=100)
	private String website;

	@Column(name="description", length=500)
	private String description;

	@Column(name="contact_name", length=50)
	private String contactName;

	@Column(name="contact_phone", length=50)
	private String contactPhone;

	@Column(name="contact_email", length=100)
	private String contactEmail;

	@Column(name="login_name", length=100)
	private String loginName;

	@Column(name="login_passwd", length=100)
	private String loginPassword;

	@Column(name="member_level")
	private Integer memberLevel;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_date")
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
	private Date createDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_type_id", referencedColumnName = "id")
	private MemberType memberType;
	

	public Member(){
		this.id = StringUtils.generateUUID();
		this.createDate = new Date();
	}
	
	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressLevelOne() {
		return addressLevelOne;
	}

	public void setAddressLevelOne(String addressLevelOne) {
		this.addressLevelOne = addressLevelOne;
	}

	public String getAddressLevelTwo() {
		return addressLevelTwo;
	}

	public void setAddressLevelTwo(String addressLevelTwo) {
		this.addressLevelTwo = addressLevelTwo;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public Integer getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(Integer memberLevel) {
		this.memberLevel = memberLevel;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public MemberType getMemberType() {
		return memberType;
	}

	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}

}
