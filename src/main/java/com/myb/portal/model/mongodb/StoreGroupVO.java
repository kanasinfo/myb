package com.myb.portal.model.mongodb;

import java.util.List;

public class StoreGroupVO {
	private String id;
	private String storeGroupId;
	private String storeGroupName;
	private List<Store> store;
	private String activeFlag;
	private int sortNumber;
	private List<String> downDate;
	
	public List<String> getDownDate() {
		return downDate;
	}
	public void setDownDate(List<String> downDate) {
		this.downDate = downDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Store> getStore() {
		return store;
	}
	public void setStore(List<Store> store) {
		this.store = store;
	}
	public String getStoreGroupId() {
		return storeGroupId;
	}
	public void setStoreGroupId(String storeGroupId) {
		this.storeGroupId = storeGroupId;
	}
	public String getStoreGroupName() {
		return storeGroupName;
	}
	public void setStoreGroupName(String storeGroupName) {
		this.storeGroupName = storeGroupName;
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
