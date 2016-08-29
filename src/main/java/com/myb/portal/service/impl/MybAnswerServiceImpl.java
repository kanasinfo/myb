package com.myb.portal.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.myb.portal.exception.ServiceException;
import com.myb.portal.model.mongodb.MybAnswerVo;
import com.myb.portal.model.mongodb.Options;
import com.myb.portal.model.mongodb.QuestionGroupVO;
import com.myb.portal.model.mongodb.QuestionTmpltVO;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.service.MybAnswerService;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;
import com.myb.portal.util.Param;
import com.myb.portal.util.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class MybAnswerServiceImpl implements MybAnswerService{
	@Autowired
	MongoTemplate mongoTemplate;
	
	public Map<String, Object> previewQuestion(String data){
		try {
			if(StringUtils.isBlank(data)){
				throw new ServiceException("");
			}
			QuestionTmpltVO questionTmpltVO = JsonUtil.jsonToObject(data, QuestionTmpltVO.class);
			JSONObject jb = new JSONObject();
			JSONArray ja = new JSONArray();
			JSONObject jsondata = JSONObject.fromObject(data);
			Map<String, Object> retunMap = new HashMap<String, Object>();
			Utils.packageAnserQuestionTemplate(questionTmpltVO,jb,"");
			Map<String, QuestionGroupVO> questionMap = new TreeMap<String, QuestionGroupVO>();
			List<QuestionGroupVO> listGroup  = JsonUtil.jsonToList(jsondata.getJSONArray("questionGroup").toString(), QuestionGroupVO.class);
			for (QuestionGroupVO mybQustnrGroup : listGroup) {
				mybQustnrGroup.setCustomQuestionType(mybQustnrGroup.getCustomQuestionType());
				if(mybQustnrGroup.getActiveFlag()!=0){
					questionMap.put(mybQustnrGroup.getQuestionGroupId(), mybQustnrGroup);	
				}
			}
			Map<String, List<QuestionsVo>> mapQuestion = new TreeMap<String, List<QuestionsVo>>();
			JSONArray listQuestion = jsondata.getJSONArray("questions");
			// 根据ID进行分组
			if (listQuestion != null && listQuestion.size() != 0) {
				QuestionsVo questionsVo = null;
				JSONObject jsonQuestion =  null;
				List<Options> listOption = null;
					for (int i = 0; i < listQuestion.size(); i++) {
						jsonQuestion = listQuestion.getJSONObject(i);
						questionsVo = JsonUtil.jsonToObject(jsonQuestion.toString(), QuestionsVo.class);
						listOption = JsonUtil.jsonToList(jsonQuestion.getJSONArray("options").toString(), Options.class);
						if (mapQuestion.get(questionsVo.getQuestionGroupId()) != null) {
							questionsVo.setQuestionType(questionsVo.getQuestionType());
							Utils.packageAnserQuestion(questionsVo, ja,questionMap);
							questionsVo.setOptions(listOption);
							mapQuestion.get(questionsVo.getQuestionGroupId()).add(questionsVo);
						} else {
							List<QuestionsVo> list2 = new ArrayList<QuestionsVo>();
							questionsVo.setQuestionType(questionsVo.getQuestionType());
							Utils.packageAnserQuestion(questionsVo, ja,questionMap);
							questionsVo.setOptions(listOption);
							list2.add(questionsVo);
							mapQuestion.put(questionsVo.getQuestionGroupId(), list2);
						}
						
					}
			}
			if(questionTmpltVO.getWelcome().get(Param.ACTIVE_FLAG).equals(true)){
				retunMap.put("msg", questionTmpltVO.getWelcome().get(Param.WELCOME_MSG));
			}else{
				retunMap.put("msg", "");
			}
			
			Map<QuestionGroupVO, List<QuestionsVo>> callMap = new HashMap<QuestionGroupVO, List<QuestionsVo>>();
			// 合并分组结合
			for (Entry<String, QuestionGroupVO> entry : questionMap.entrySet()) {
				if (mapQuestion.get(entry.getKey()) != null) {
					callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
				}else{
					callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
				}
			}
			
			List<Map.Entry<QuestionGroupVO, List<QuestionsVo>>> infoIds = new ArrayList<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>(callMap.entrySet());
			Collections.sort(infoIds, new Comparator<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>() {
				public int compare(Map.Entry<QuestionGroupVO, List<QuestionsVo>> o1,
						Map.Entry<QuestionGroupVO, List<QuestionsVo>> o2) {
							return String.valueOf(o1.getKey().getSortNumber()).compareTo(String.valueOf(o2.getKey().getSortNumber()));
						}
						}); 
			jb.put("answers", ja);
			try {
				retunMap.put("data", java.net.URLEncoder.encode(JsonUtil.objectToJson(JsonUtil.objectToJson(jb)) , "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retunMap.put("mapQuestion", infoIds);
			retunMap.put("projectName", questionTmpltVO.getQustnrName());
			return retunMap;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	@Override
	public Map<String, Object> queryMongodbQuestion(String questionId,String storeId) {
		JSONObject jb = new JSONObject();
		JSONArray ja = new JSONArray();
		Map<String, Object> retunMap = new HashMap<String, Object>();
		// 查询所有的分组
		Query query = new Query();
		Criteria criterg = Criteria.where("_id").is(questionId);
		query.addCriteria(criterg);
		QuestionTmpltVO questionTmpltVO = mongoTemplate.findOne(query, QuestionTmpltVO.class,"release_qustnnr");
		Utils.packageAnserQuestionTemplate(questionTmpltVO,jb,storeId);
		Map<String, QuestionGroupVO> questionMap = new TreeMap<String, QuestionGroupVO>();
		for (QuestionGroupVO mybQustnrGroup : questionTmpltVO.getQuestionGroup()) {
			mybQustnrGroup.setCustomQuestionType(mybQustnrGroup.getCustomQuestionType());
			if(mybQustnrGroup.getActiveFlag()!=0){
				questionMap.put(mybQustnrGroup.getQuestionGroupId(), mybQustnrGroup);	
			}
		}
		Map<String, List<QuestionsVo>> mapQuestion = new TreeMap<String, List<QuestionsVo>>();
		// 根据ID进行分组
		if (questionTmpltVO.getQuestions() != null && questionTmpltVO.getQuestions().size() != 0) {
			for (QuestionsVo questionsVo : questionTmpltVO.getQuestions()) {
				if (mapQuestion.get(questionsVo.getQuestionGroupId()) != null) {
					questionsVo.setQuestionType(questionsVo.getQuestionType());
					Utils.packageAnserQuestion(questionsVo, ja,questionMap);
					mapQuestion.get(questionsVo.getQuestionGroupId()).add(questionsVo);
				} else {
					List<QuestionsVo> list2 = new ArrayList<QuestionsVo>();
					questionsVo.setQuestionType(questionsVo.getQuestionType());
					Utils.packageAnserQuestion(questionsVo, ja,questionMap);
					list2.add(questionsVo);
					mapQuestion.put(questionsVo.getQuestionGroupId(), list2);
				}
			}
		}
		if(questionTmpltVO.getWelcome().get(Param.ACTIVE_FLAG).equals(true)){
			retunMap.put("msg", questionTmpltVO.getWelcome().get(Param.WELCOME_MSG));
		}else{
			retunMap.put("msg", "");
		}
		
		Map<QuestionGroupVO, List<QuestionsVo>> callMap = new HashMap<QuestionGroupVO, List<QuestionsVo>>();
		// 合并分组结合
		for (Entry<String, QuestionGroupVO> entry : questionMap.entrySet()) {
			if (mapQuestion.get(entry.getKey()) != null) {
				callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
			}else{
				callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
			}
		}
		
		List<Map.Entry<QuestionGroupVO, List<QuestionsVo>>> infoIds = new ArrayList<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>(callMap.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>() {
			public int compare(Map.Entry<QuestionGroupVO, List<QuestionsVo>> o1,
					Map.Entry<QuestionGroupVO, List<QuestionsVo>> o2) {
						return String.valueOf(o1.getKey().getSortNumber()).compareTo(String.valueOf(o2.getKey().getSortNumber()));
					}
					}); 
		jb.put("answers", ja);
		try {
			retunMap.put("data", java.net.URLEncoder.encode(JsonUtil.objectToJson(JsonUtil.objectToJson(jb)) , "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		retunMap.put("projectName", questionTmpltVO.getQustnrName());
		retunMap.put("mapQuestion", infoIds);
		return retunMap;
	}

	@Override
	public AjaxReq addAnswer(String data) {
		AjaxReq ar = new AjaxReq();
		try {
			ar.setSuccess(true);
			ar.setMessage("谢谢评价");
			JSONObject jb = JSONObject.fromObject(data);
			MybAnswerVo mybanswer = new MybAnswerVo();
			mybanswer.setTenementId(jb.getString("tenementId"));
			mybanswer.setQustnrName(jb.getString("qustnrName"));
			mybanswer.setEndUserIdentity(jb.getString("endUserIdentity"));
			mybanswer.setQuestionnaireId(jb.getString("questionnaireId"));
			mybanswer.setCreatedDate(new Date());
			mybanswer.setAnswers(jb.getJSONArray("answers"));
			mongoTemplate.save(mybanswer,"answer");
		} catch (Exception e) {
			ar.setMessage("提交失败，请稍后重试");
		}
		return ar;
	}
	
	public MybAnswerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

}
