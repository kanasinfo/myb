package com.myb.portal.service;

import java.util.List;
import java.util.Map;
/**
 * <p>Title: MybWarningDataService</p>
 * <p>Description: (封装)</p>
 * <p>date : 2016年11月6日 下午10:07:10 </p>
 * @author wangzx
 * @version 1.0
 */
public interface MybWarningDataService {
	/**
	 * initCount TODO(默认查询数据数据不包含任何条件) 
	 * @author wangzx
	 * @param map
	 */
	public void initCount(List<Map<String, String>> map,String id,String date);
	/**
	 * initStore TODO(根据门店统计) 
	 * @author wangzx
	 * @param map
	 * @param id
	 * @param date
	 */
	public void initStore(List<Map<String, String>> map,String id,String date);
	/**
	 * initStoreGroup TODO(根据门店组统计) 
	 * @author wangzx
	 * @param map
	 * @param id
	 * @param date
	 */
	public void initStoreGroup(List<Map<String, String>> map,String id,String date);
	/**
	 * selectCount TODO(选中分店或者预警条件查询总计) 
	 * @author wangzx
	 * @param map
	 * @param id
	 * @param date
	 * @param storeId
	 * @param questionId
	 */
	public void selectCount(List<Map<String, String>> map,String id,String date,String storeId,String storeGroup,String questionId);
	/**
	 * selectCount TODO(选中分店或者预警条件查询总计) 
	 * @author wangzx
	 * @param map
	 * @param id
	 * @param date
	 * @param storeId
	 * @param questionId
	 */
	public void selectStoreCount(List<Map<String, String>> map,String id,String date,String storeId,String questionId);
	/**
	 * selectStoreGroupCount TODO(根据分组进行查询) 
	 * @author wangzx
	 * @param map
	 * @param id
	 * @param date
	 * @param storeGroup
	 * @param questionId
	 */
	public void selectStoreGroupCount(List<Map<String, String>> map,String id,String date,String storeGroup,String questionId);
}
