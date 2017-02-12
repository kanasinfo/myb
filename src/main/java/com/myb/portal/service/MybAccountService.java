package com.myb.portal.service;

import com.myb.portal.model.account.MybAccount;
import com.myb.portal.util.AjaxReq;

public interface MybAccountService {
	/**
	 * registerAccount TODO(用户注册) 
	 * @author wangzx
	 * @param mybAccount
	 * @return
	 */
	public AjaxReq registerAccount(MybAccount mybAccount);
	/**
	 * saveGroup TODO(保存该用户的分组) 
	 * @author wangzx
	 * @param groupName
	 * @return
	 */
	public AjaxReq saveGroup(String groupName);
	/**
	 * saveAccountAndGroup TODO(保存门店和组的关系) 
	 * @author wangzx
	 * @return
	 */
	public AjaxReq saveGroupAndGroup(String groupId,String[] stores);
	/**
	 * queryGroupByAccountId TODO(根据用户ID查询分组信息) 
	 * @author wangzx
	 * @param accountId
	 * @return
	 */
	public AjaxReq queryGroupByAccountId();
	/**
	 * queryAmount TODO(查询可用样本数) 
	 * @author wangzx
	 * @return
	 */
	public int queryAmount();

	MybAccount findByPhone(String phone);

	MybAccount findByLoginEmail(String loginEmail);
}
