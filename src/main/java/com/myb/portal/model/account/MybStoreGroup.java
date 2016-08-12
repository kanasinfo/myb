package com.myb.portal.model.account;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.myb.portal.support.AbstractModel;
/**
 * <p>Title: MybStoreGroup</p>
 * <p>Description: (用户分组表)</p>
 * <p>date : 2016年1月11日 上午10:12:42 </p>
 * @author wangzx
 * @version 1.0
 */
@Table(name="STOREGROUP")
@EntityListeners({AuditingEntityListener.class})
@Entity(name="MybStoreGroup")
public class MybStoreGroup extends AbstractModel<String>{
	
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@Column(name="NAME")
	private String name;
	@Column(name="ACCOUNT_ID")
	private String accountId;
	@Column(name="SORTNUMBER")
	private int sortNumber;
	/**
	 * 多对多关系，by指向mybStoreGroup表的多对多属性	
	 */
	@ManyToMany(fetch=FetchType.LAZY, cascade={CascadeType.PERSIST,CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="STOREGROUP_MAP",
	joinColumns={@JoinColumn(name="STOREGROUP_ID",referencedColumnName="id")},
	inverseJoinColumns={@JoinColumn(name="STORE_ID",referencedColumnName="id")})
	private Set<MybStore> mybStore;
	
	public int getSortNumber() {
		return sortNumber;
	}
	public void setSortNumber(int sortNumber) {
		this.sortNumber = sortNumber;
	}
	public Set<MybStore> getMybStore() {
		return mybStore;
	}
	public void setMybStore(Set<MybStore> mybStore) {
		this.mybStore = mybStore;
	}
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
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	
}
