package com.myb.portal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.myb.portal.model.account.MybStoreGroup;
import com.myb.portal.model.mongodb.StoreList;
import com.myb.portal.util.AjaxReq;

/**
 * <p>Title: MybReleaseService</p>
 * <p>Description: (发布问卷业务处理)</p>
 * <p>date : 2016年5月7日 上午9:50:32 </p>
 * @author wangzx
 * @version 1.0
 */
public interface MybReleaseService {
	/**
	 * queryStoreByGroupIdForList TODO(根据groupid查询所有的数据) 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	public List<StoreList> queryStoreByGroupIdForList(String templateId);
	/**
	 * releaseQuestion TODO(发布问卷) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	public Map<String,Object> releaseQuestion(String id)throws IOException ;
	
	public String getQRurl(String id);
	/**
	 * uploadXls TODO(上传门店信息) 
	 * @author wangzx
	 * @param input
	 * @return
	 */
	public AjaxReq uploadXls(InputStream input,AjaxReq req);
	/**
	 * addStoreGroup TODO(添加group分组) 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	public AjaxReq addGroup(String id,String groupName);
	/**
	 * addStoreGroup TODO(添加分组与门店关联关系) 
	 * @author wangzx
	 * @param groupId
	 * @param storeId
	 * @return
	 */
	public AjaxReq addStoreGroup(String groupId,String storeId);
	/**
	 * delStore TODO(删除分组与门店关联关系) 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	public AjaxReq delStore(String storeId);
	/**
	 * addStore TODO(这里用一句话描述这个类的作用) 
	 * @author wangzx
	 * @param store
	 * @return
	 */
	public AjaxReq addStore(String store);
	/**
	 * queryGroupStoreById TODO(查询所有的门店和分组) 
	 * @author wangzx
	 * @return
	 */
	public AjaxReq queryGroupStoreByAccountId(String groupId);
	/**
	 * delGroupById TODO(根据groupid删除分组) 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	public AjaxReq delGroupById(String groupId);
	/**
	 * queryGroupStoreById TODO(查询所有的门店) 
	 * @author wangzx
	 * @return
	 */
	public AjaxReq queryAllStoreByAccountId();
	/**
	 * addStoreToGroup TODO(添加group和store关联关系) 
	 * @author wangzx
	 * @param id
	 * @param groupId
	 * @return
	 */
	public AjaxReq addStoreToGroup(String id ,String groupId);
	/**
	 * delGroupStore TODO(删除当前分组与门店关系) 
	 * @author wangzx
	 * @param groupId
	 * @param storeId
	 * @return
	 */
	public AjaxReq delGroupStore(String groupId,String storeId);
	/**
	 * queryGroupByAccountid TODO(根据用户查询所有的的group) 
	 * @author wangzx
	 * @return
	 */
	public List<MybStoreGroup> queryGroupByAccountid();
	/**
	 * queryStoreByGroupId TODO(根据groupid查询所有的数据) 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	public AjaxReq queryStoreByGroupId(String groupId,String templateId);
	/**
	 * downGroupLoadExcel TODO(下载分店连接) 
	 * @author wangzx
	 * @param groupId
	 * @param questionId
	 * @return
	 */
	public AjaxReq downGroupLoadExcel( String parentId,String data,String questionId,String groupId,String classPath);
	/**
	 * downOnlyONeLoadExcel TODO(每个用户生成一个连接) 
	 * @author wangzx
	 * @param groupId
	 * @param questionId
	 * @return
	 */
	public void downOnlyONeLoadExcel(String questionId, String classPath,String downCount,ServletOutputStream outputStream,AjaxReq ar);
	
}
