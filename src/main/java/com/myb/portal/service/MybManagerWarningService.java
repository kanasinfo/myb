package com.myb.portal.service;

import com.myb.portal.util.AjaxReq;
/**
 * 
 * <p>Title: MybManagerWarningService</p>
 * <p>Description: (预警管理业务处理)</p>
 * <p>date : 2016年9月25日 上午12:20:09 </p>
 * @author wangzx
 * @version 1.0
 */
public interface MybManagerWarningService {
	/**
	 * queryQuestion TODO(查询所有可以预警的问题) 
	 * @author wangzx
	 * @return
	 */
	public AjaxReq queryQuestion(String questionnaireId);
}
