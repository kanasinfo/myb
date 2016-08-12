package com.myb.portal.service;

import java.util.List;

import com.myb.portal.model.question.MybIndustry;
import com.myb.portal.util.AjaxReq;

/**
 * <p>Title: MybIndustryService</p>
 * <p>Description: (行业处理)</p>
 * <p>date : 2015年12月28日 下午2:45:56 </p>
 * @author wangzx
 * @version 1.0
 */
public interface MybIndustryService {
	/**
	 * queryIndustry TODO(查询所有的行业) 
	 * @author wangzx
	 * @return
	 */
	public List<MybIndustry> queryIndustry();
	/**
	 * queryIndustryByParentId TODO(查询子行业) 
	 * @author wangzx
	 * @param parentId
	 * @return
	 */
	public AjaxReq queryIndustryByParentId(String parentId);
}
