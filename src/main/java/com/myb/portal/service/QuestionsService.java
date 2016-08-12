package com.myb.portal.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import com.myb.portal.model.question.MybQuestionGroup;
import com.myb.portal.model.question.MybQuestionnaireTemplate;
import com.myb.portal.util.AjaxReq;

/**
 * <p>Title: QuestionsService</p>
 * <p>Description: (行业模板业务处理)</p>
 * <p>date : 2015年12月23日 下午11:11:03 </p>
 * @author wangzx
 * @version 1.0
 */
public interface QuestionsService {
	/**
	 * queryQustnrGroup TODO(查询所有的分组) 
	 * @author wangzx
	 * @return
	 */
	public List<MybQuestionGroup> queryQustnrGroup(String templateId);
	/**
	 * createTempl TODO(根据行业创建模板) 
	 * @author wangzx
	 * @param industryId
	 * @return
	 */
	public AjaxReq createTempl(String sub_industry_id,String qustnr_name, String credit_amount,String industry,List<MybQuestionGroup> listQustnrGroup,MybQuestionnaireTemplate listTmplt);
	/**
	 * queryQuestionByUserId TODO(查询当前所有的问题模板) 
	 * @author wangzx
	 * @return
	 */
	public Map<String,Object> queryQuestionTmplByUserId(int currentPage,int pageSize);
	/**
	 * queryQuestionByUserId TODO(查询当前所有的问题模板) 
	 * @author wangzx
	 * @return
	 */
	public String queryQuestionTmplByUserIdForJson(int currentPage,int pageSize);
	/**
	 * queryMongodbQuestionBytempletId TODO(根据模板主键查询数据) 
	 * @author wangzx
	 * @return
	 */
	public Map<String, Object> queryMongodbQuestionBytempletId(String id)  throws UnsupportedEncodingException;
	/**
	 * delQuestionById TODO() 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	public AjaxReq delQuestionById(String id);
	/**
	 * saveQuestionScore TODO(添加分值问题) 
	 * @author wangzx
	 * @param templId（模板ID）
	 * @param groupId（添加的分类）
	 * @param questionName（添加的问题名称）
	 * @param question（添加的问题）
	 * @param optionName（添加所有的数据）
	 * @return
	 */
	public AjaxReq saveQuestionScore(String templId,String groupId,String questionName,String question,String optionName);
	/**
	 * saveQuestionredio TODO(添加单选的问题) 
	 * @author wangzx
	 * @param templId（模板ID）
	 * @param groupId（添加的分类）
	 * @param questionName（添加的问题名称）
	 * @param question（添加的问题）
	 * @return
	 */
	public AjaxReq saveQuestionredio(String templId,String groupId,String questionName,String question);
	/**
	 * delQuestion TODO(根据模板和问题主键ID删除相关问题) 
	 * @author wangzx
	 * @param templId
	 * @param questionId
	 * @return
	 */
	public AjaxReq delQuestion(String templId,String questionId);
	/**
	 * stopQuestionById TODO(根据主键停止当前模板) 
	 * @author wangzx
	 * @param questionid
	 * @return
	 */
	public AjaxReq stopQuestionById(String questionid);
	/**
	 * updateBasicQuestion TODO(更新基本信息) 
	 * @author wangzx
	 * @param templId（主键ID）
	 * @param qustnr_name（问题名称）
	 * @param credit_amount（问卷数量）
	 * @return
	 */
	public AjaxReq updateBasicQuestionById(String templId,String qustnr_name,int credit_amount);
	/**
	 * updateQuestion TODO(动态添加数据) 
	 * @author wangzx
	 * @param templId
	 * @param data
	 * @return
	 */
	public AjaxReq updateQuestion(String templId,String data);
	/**
	 * queryQuestionnaireTemplateByIndustryId TODO(根据行业ID查询模板) 
	 * @author wangzx
	 * @param listTmplt
	 * @param subIndustryId
	 */
	public MybQuestionnaireTemplate queryQuestionnaireTemplateByIndustryId(String subIndustryId);
}

