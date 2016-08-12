package com.myb.portal.model.account;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.myb.portal.support.AbstractModel;
@Entity(name="MybStore")
@EntityListeners({AuditingEntityListener.class})
@Table(name="STORE")
public class MybStore extends AbstractModel<String>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="NAME",length=100)
	private String name;
	@Column(name="ACCOUNT_ID",length=32)
	private String accountId;
	@Column(name="ADDRESS",length=200)
	private String address ;
	@Column(name="STORESORT",length=11)
	private int storeSort ;
	

	@ManyToMany(mappedBy="mybStore")
	private Set<MybStoreGroup> mybStoreGroup;
	public Set<MybStoreGroup> getMybStoreGroup() {
		return mybStoreGroup;
	}
	public void setMybStoreGroup(Set<MybStoreGroup> mybStoreGroup) {
		this.mybStoreGroup = mybStoreGroup;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getAccountId() {
		return accountId;
	}




	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public int getStoreSort() {
		return storeSort;
	}




	public void setStoreSort(int storeSort) {
		this.storeSort = storeSort;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}




}
