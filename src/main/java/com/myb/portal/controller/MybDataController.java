package com.myb.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.model.mongodb.QuestionTmpltVO;
import com.myb.portal.model.question.MybQuestionGroup;
import com.myb.portal.model.question.MybQuestionnaireTemplate;
import com.myb.portal.service.MybAccountService;
import com.myb.portal.service.MybIndustryService;
import com.myb.portal.service.QuestionsService;
import com.myb.portal.util.AjaxReq;

/**
 * <p>
 * Title: MybDataController
 * </p>
 * <p>
 * Description: (数据展现业务处理)
 * </p>
 * <p>
 * date : 2015年12月28日 下午1:48:22
 * </p>
 * 
 * @author wangzx
 * @version 1.0
 */
@RequestMapping(value = "data")
@Controller
public class MybDataController {
	@Autowired
	MybIndustryService mybIndustryService;
	@Autowired
	QuestionsService questionsService;
	@Autowired
	MybAccountService mybAccountService;
	/**
	 * delQuestion TODO(删除问题) 
	 * @author wangzx
	 * @param templId
	 * @param questionId
	 * @return
	 */
	@RequestMapping(value="delQuestion",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq delQuestion(String templId,String questionId){
		return questionsService.delQuestion(templId,questionId);
	}
	/**
	 * queryIndustryByParentId TODO(查询行业子分类)
	 * 
	 * @author wangzx
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value = "queryIndustryByParentId", method = RequestMethod.GET)
	public @ResponseBody AjaxReq queryIndustryByParentId(String parentId) {
		return mybIndustryService.queryIndustryByParentId(parentId);
	}

	/**
	 * createQuestion TODO(创建问题)
	 * 
	 * @author wangzx
	 * @param sub_industry_id（行业ID）
	 * @param trend_name（）
	 * @param qustnr_name（问卷名称）
	 * @param credit_amount（问卷数量）
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "createQuestion", method = RequestMethod.POST)
	public ModelAndView createQuestion(String sub_industry_id,String sub_industry_name, String qustnr_name,
			String credit_amount,String industry_name,  ModelAndView mv) {
		
		MybQuestionnaireTemplate listTmplt = questionsService.queryQuestionnaireTemplateByIndustryId(sub_industry_id);
		List<MybQuestionGroup> listQustnrGroup = questionsService.queryQustnrGroup(listTmplt.getId());
		if(listQustnrGroup != null){
			AjaxReq aReq = questionsService.createTempl(sub_industry_id, qustnr_name, credit_amount, sub_industry_name, listQustnrGroup,listTmplt);
			QuestionTmpltVO questionTmpltVO =  (QuestionTmpltVO) aReq.getData();
			mv.addObject("templId", questionTmpltVO.getId());
			mv.setViewName("redirect:/page/main/editQuestion/"+questionTmpltVO.getId()+".html");
			return mv;
		}else{
			mv.setViewName("redirect:/page/main/editQuestion.html");
			return mv;
		}
		
		
	}
	/**
	 * publishQuestion TODO(数据采集) 
	 * @author wangzx
	 * @param modelAndView
	 * @return
	 */
	public ModelAndView publishQuestion(ModelAndView modelAndView){
		//获取发送问卷方式，
		//如果是门店的话 则需要分组信息
		return modelAndView;
	}
	@RequestMapping(value = "queryQuestion", method = RequestMethod.POST)
	@ResponseBody
	public String queryQuestion(int page,int pagesize) {
		return questionsService.queryQuestionTmplByUserIdForJson(page,pagesize);
	}
	/**
	 * queryGroupStore TODO(查询当前用户的分组信息) 
	 * @author wangzx
	 * @return
	 */
	public AjaxReq queryGroupStore(){
		return mybAccountService.queryGroupByAccountId();
	}
	/**
	 * delQuestionById TODO(根据主键删除调查问卷) 
	 * @author wangzx
	 * @return
	 */
	@RequestMapping(value="delQuestionById",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq delQuestionById(String id){
		return questionsService.delQuestionById(id);
	}
	/**
	 * stopQuestionById TODO(根据主键停止当前模板) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	@RequestMapping(value="stopQuestionById",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq stopQuestionById(String id){
		return questionsService.stopQuestionById(id);
	}
	
	/**
	 * saveQuestionRedio TODO(保存自定义单选问题) 
	 * @author wangzx
	 * @param templId（模板ID）
	 * @param groupId（分组ID）
	 * @param questionName（问题名称）
	 * @param question（问题描述）
	 * @return
	 */
	@RequestMapping(value="saveQuestionRedio",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq saveQuestionRedio(String templId,String groupId,String questionName,String question){
		return questionsService.saveQuestionredio(templId, groupId, questionName, question);
	}
	/**
	 * saveQuestionScore TODO(添加自定义分值问题) 
	 * @author wangzx
	 * @param templId
	 * @param groupId
	 * @param questionName
	 * @param question
	 * @param option
	 * @return
	 */
	@RequestMapping(value="saveQuestionScore",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq saveQuestionScore(String templId,String groupId,String questionName,String question,String option){
		return questionsService.saveQuestionScore(templId, groupId, questionName, question, option);
	}
	/**
	 * updateBasicQuestion TODO(更新基本信息) 
	 * @author wangzx 
	 * @param templId（主键ID）
	 * @param qustnr_name（问题名称）
	 * @param credit_amount（问卷数量）
	 * @return
	 */
	@RequestMapping(value="updateBasicQuestionById",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq updateBasicQuestionById(String templId,String qustnr_name,int credit_amount){
		return questionsService.updateBasicQuestionById(templId, qustnr_name, credit_amount);
	}
	@RequestMapping(value="updateQuestion",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq updateQuestion(String templId,String data){
		return questionsService.updateQuestion(templId, data);
	}
}
