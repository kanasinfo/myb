package com.myb.portal.service;

import java.util.List;
import java.util.Map;
import com.myb.portal.model.mongodb.WarningOptionVo;
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
	/**
	 * queryQuestionWarning TODO(查询所有的预警条件) 
	 * @author wangzx
	 * @param questionnaireId
	 * @return
	 */
	public List<WarningOptionVo> queryQuestionWarning(String questionnaireId);
	/**
	 * addQuestionWarning TODO(添加预警条件) 
	 * @author wangzx
	 * @param questionnaireId
	 * @param data
	 * @return
	 */
	public AjaxReq addQuestionWarning(String questionnaireId,String questionId,String warningName,String questionName,String data);
	/**
	 * delQuestionWarning TODO(删除预警条件) 
	 * @author wangzx
	 * @param questionnaireId
	 * @return
	 */
	public AjaxReq delQuestionWarning(String questionnaireId,String warningId);
	/**
	 * updateWarning TODO(更改选中未选中条件) 
	 * @author wangzx
	 * @param id
	 * @param check
	 * @param uncheck
	 * @return
	 */
	public AjaxReq updateWarning(String id,String check,String uncheck);
	/**
	 * queryManagerWarningMain TODO(按照预警条件查询统计数据) 
	 * @author wangzx
	 * @param questionid
	 * @param dateTime
	 * @param store
	 * @param warning
	 * @return
	 */
	public Map<String, Map> queryManagerWarningMainAll(String id,String dateTime,String questionId);
}
