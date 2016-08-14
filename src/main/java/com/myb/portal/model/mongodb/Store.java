package com.myb.portal.model.mongodb;

import java.util.List;

public class Store {
	private String id;
	private String storeId;
	private String storeName;
	private String address;
	private String activeFlag;
	private int sortNumber;
	private int type;
	private String managerName;
	private String managerEmail;
	private Integer managerPhone;
	private String managerWechatNumber;
	private List<String> downDate;
	
	public List<String> getDownDate() {
		return downDate;
	}
	public void setDownDate(List<String> downDate) {
		this.downDate = downDate;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public Integer getManagerPhone() {
		return managerPhone;
	}
	public void setManagerPhone(Integer managerPhone) {
		this.managerPhone = managerPhone;
	}
	public String getManagerWechatNumber() {
		return managerWechatNumber;
	}
	public void setManagerWechatNumber(String managerWechatNumber) {
		this.managerWechatNumber = managerWechatNumber;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(String activeFlag) {
		this.activeFlag = activeFlag;
	}
	public int getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	
}
