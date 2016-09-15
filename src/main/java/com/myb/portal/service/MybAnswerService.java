package com.myb.portal.service;

import java.util.Map;

import com.myb.portal.model.mongodb.ReleaseOnlyOneAnwserData;
import com.myb.portal.util.AjaxReq;

/**
 * <p>Title: MybAnswerService</p>
 * <p>Description: (回填问卷服务类)</p>
 * <p>date : 2016年4月28日 上午6:14:44 </p>
 * @author wangzx
 * @version 1.0
 */
public interface MybAnswerService {
	/**
	 * queryMongodbQuestion TODO(问卷查询) 
	 * @author wangzx
	 * @return
	 */
	Map<String,Object> queryMongodbQuestion(String questionId,String storeId);
	/**
	 * addAnswer TODO(回填问卷) 
	 * @author wangzx
	 * @param data
	 * @return
	 */
	AjaxReq addAnswer(String data);
	/**
	 * previewQuestion TODO(预览问卷) 
	 * @author wangzx
	 * @param data
	 * @return
	 */
	public Map<String, Object> previewQuestion(String data);
	/**
	 * checkUrl TODO(验证当前连接是否有效) 
	 * @author wangzx
	 * @param questionId
	 * @param id
	 * @return
	 */
	public AjaxReq checkUrl(String questionId,String id);
	/**
	 * 
	 * updateUrl TODO(把当前连接置为已经使用) 
	 * @author wangzx
	 * @param questionId
	 * @param id
	 * @return
	 */
	public AjaxReq updateUrl(String questionId,String id);
}
