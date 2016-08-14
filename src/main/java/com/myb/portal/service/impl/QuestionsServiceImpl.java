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
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.myb.portal.model.mongodb.ActivePeriod;
import com.myb.portal.model.mongodb.Options;
import com.myb.portal.model.mongodb.QuestionGroupVO;
import com.myb.portal.model.mongodb.QuestionTmpltVO;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.model.mongodb.Store;
import com.myb.portal.model.mongodb.StoreGroupVO;
import com.myb.portal.model.question.MybOption;
import com.myb.portal.model.question.MybQuestion;
import com.myb.portal.model.question.MybQuestionGroup;
import com.myb.portal.model.question.MybQuestionnaireTemplate;
import com.myb.portal.repository.MybQuestionGroupRepository;
import com.myb.portal.repository.MybQuestionRepository;
import com.myb.portal.repository.MybQuestionnaireTemplateRepository;
import com.myb.portal.repository.MybStoreGroupRepository;
import com.myb.portal.repository.MybStoreRepository;
import com.myb.portal.service.QuestionsService;
import com.myb.portal.shiro.ShiroDb;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;
import com.myb.portal.util.Page;
import com.myb.portal.util.Param;
import com.myb.portal.util.Utils;
import com.myb.portal.vo.ShowQuestionVO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class QuestionsServiceImpl implements QuestionsService {
	@Autowired
	MybStoreRepository mybStoreRepository;
	@Autowired
	MybQuestionRepository mybQuestionRepository;
	@Autowired
	MybQuestionnaireTemplateRepository mybQuestionnaireTemplateRepository;
	@Autowired
	MybQuestionGroupRepository groupRepository;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MybStoreGroupRepository mybStoreGroupRepository;

	/**
	 * delQuestion TODO(根据模板和问题主键ID删除相关问题)
	 * 
	 * @author wangzx
	 * @param templId
	 * @param questionId
	 * @return
	 */
	@Transactional
	public AjaxReq delQuestion(String templId, String questionId) {
		AjaxReq aReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(templId)) {
				aReq.setSuccess(false);
				aReq.setMessage("添加失败");
				return aReq;
			}
			if (StringUtils.isBlank(questionId)) {
				aReq.setSuccess(false);
				aReq.setMessage("添加失败");
				return aReq;
			}
			Update update = new Update();
			update.pull("questions", new BasicDBObject("question_id", questionId));
			Query query = Query.query(Criteria.where("_id").is(templId));
			mongoTemplate.updateMulti(query, update, QuestionTmpltVO.class);
			aReq.setSuccess(true);
			aReq.setMessage("删除成功！");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return aReq;
	}

	/**
	 * saveQuestionredio TODO(添加单选的问题)
	 * 
	 * @author wangzx
	 * @param templId（模板ID）
	 * @param groupId（添加的分类）
	 * @param questionName（添加的问题名称）
	 * @param question（添加的问题）
	 * @return
	 */
	@Transactional
	public AjaxReq saveQuestionredio(String templId, String groupId, String questionName, String question) {
		AjaxReq ajaxReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			if (StringUtils.isBlank(questionName)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			if (StringUtils.isBlank(question)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			// 查询当当前分组
			MybQuestionGroup mybQustnrGroup = groupRepository.findByid(groupId);
			QuestionsVo questionsVo = new QuestionsVo();
			questionsVo.setQuestionGroupId(mybQustnrGroup.getId());
			questionsVo.setQuestionGroupName(mybQustnrGroup.getName());
			questionsVo.setQuestionGroupSortNumber(mybQustnrGroup.getSortNumber());
			questionsVo.setQuestionGroupValue(mybQustnrGroup.getDisplayValue());
			questionsVo.setQuestionId(Utils.getUUid());
			questionsVo.setQuestionName(questionName);
			questionsVo.setActiveFlag(true);
			questionsVo.setTemplateFlag(false);
			questionsVo.setQuestionValue(question);
			List<Options> list = new ArrayList<Options>(10);
			Options options = null;
			options = new Options();
			options.setOptionId(Utils.getUUid());
			options.setOptionName("是");
			options.setOptionValue("是");
			options.setSortNumber(0);
			list.add(options);
			options = new Options();
			options.setOptionId(Utils.getUUid());
			options.setOptionName("否");
			options.setOptionValue("否");
			options.setSortNumber(0);
			list.add(options);
			questionsVo.setOptions(list);
			Query query = new Query();
			Criteria criterg = Criteria.where("_id").is(templId);
			query.addCriteria(criterg);
			Update update = new Update();
			update.addToSet("questions", questionsVo);
			mongoTemplate.updateMulti(query, update, QuestionTmpltVO.class);
			ajaxReq.setSuccess(true);
			ajaxReq.setMessage("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReq;
	}

	@Transactional
	public AjaxReq saveQuestionScore(String templId, String groupId, String questionName, String question,
			String optionName) {
		AjaxReq ajaxReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			if (StringUtils.isBlank(questionName)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			if (StringUtils.isBlank(question)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			if (StringUtils.isBlank(optionName)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			} else if (optionName.split(",").length != 10) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("添加失败");
				return ajaxReq;
			}
			// 查询当当前分组
			MybQuestionGroup mybQustnrGroup = groupRepository.findByid(groupId);
			QuestionsVo questionsVo = new QuestionsVo();
			questionsVo.setQuestionGroupId(mybQustnrGroup.getId());
			questionsVo.setQuestionGroupName(mybQustnrGroup.getName());
			questionsVo.setQuestionGroupSortNumber(mybQustnrGroup.getSortNumber());
			questionsVo.setQuestionGroupValue(mybQustnrGroup.getDisplayValue());
			questionsVo.setQuestionId(Utils.getUUid());
			questionsVo.setQuestionName(questionName);
			questionsVo.setActiveFlag(true);
			questionsVo.setTemplateFlag(false);
			questionsVo.setQuestionValue(question);
			List<Options> list = new ArrayList<Options>(10);
			String[] optionSplic = optionName.split(",");
			Options options = null;
			for (int i = 0; i < optionSplic.length; i++) {
				options = new Options();
				options.setOptionId(Utils.getUUid());
				options.setOptionName(optionSplic[i]);
				options.setOptionValue(optionSplic[i]);
				options.setSortNumber(i);
				list.add(options);
			}
			questionsVo.setOptions(list);
			Query query = new Query();
			Criteria criterg = Criteria.where("_id").is(templId);
			query.addCriteria(criterg);
			Update update = new Update();
			update.addToSet("questions", questionsVo);
			mongoTemplate.updateMulti(query, update, QuestionTmpltVO.class);
			ajaxReq.setSuccess(true);
			ajaxReq.setMessage("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ajaxReq;
	}

	/**
	 * queryQustnrGroup TODO(查询所有的分组)
	 * 
	 * @author wangzx
	 * @return
	 */
	public List<MybQuestionGroup> queryQustnrGroup(String templateId) {
		try {
			if (StringUtils.isBlank(templateId)) {
				return null;
			}
			MybQuestionnaireTemplate template = new MybQuestionnaireTemplate();
			template.setId(templateId);
			return groupRepository.findMybQuestionGroupByQuestionnaireTemplateOrderBySortNumberAsc(template);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public MybQuestionnaireTemplate queryQuestionnaireTemplateByIndustryId(String subIndustryId) {
		return mybQuestionnaireTemplateRepository.findByindustryIdAndActiveFlag(subIndustryId, "Y");
	}

	@Override
	@Transactional
	public AjaxReq createTempl(String sub_industry_id, String qustnr_name, String credit_amount, String industry,
			List<MybQuestionGroup> listQustnrGroup, MybQuestionnaireTemplate listTmplt) {
		AjaxReq aReq = new AjaxReq();
		try {
			// 存放页面展现所需数据
			QuestionTmpltVO questionTmpltVO = new QuestionTmpltVO();
			questionTmpltVO.setQustnrTmpltId(listTmplt.getId());
			questionTmpltVO.setIndustry(industry);
			questionTmpltVO.setQustnrName(qustnr_name);
			questionTmpltVO.setCreditAmount(Integer.parseInt(credit_amount));
			questionTmpltVO.setQustnnrStatus(2);
			String accountId = ShiroDb.getAccount().getId();
			questionTmpltVO.setTenementId(accountId);
			questionTmpltVO.setCreatedTime(new Date());
			questionTmpltVO.setUpdatedTime(new Date());
			Map<String, Object> welcome = new HashMap<String, Object>();
			welcome.put(Param.ACTIVE_FLAG, false);
			welcome.put(Param.WELCOME_MSG, "");
			questionTmpltVO.setWelcome(welcome);
			Map<String, Object> endWelcome = new HashMap<String, Object>();
			endWelcome.put(Param.ACTIVE_FLAG, false);
			endWelcome.put(Param.WELCOME_MSG, "");
			 questionTmpltVO.setEndWelcome(endWelcome);
			// questionTmpltVO.setWelcome(endWelcome);
			Map<String, MybQuestionGroup> map = new HashMap<String, MybQuestionGroup>();
			List<QuestionGroupVO> listGroup = new ArrayList<QuestionGroupVO>();
			// 把行业封装map
			QuestionGroupVO questionGroup = null;
			for (MybQuestionGroup mybQustnrGroup : listQustnrGroup) {
				questionGroup = new QuestionGroupVO();
				questionGroup.setQuestionGroupId(mybQustnrGroup.getId());
				questionGroup.setBusinessType(mybQustnrGroup.getBusinessType());
				questionGroup.setName(mybQustnrGroup.getName());
				questionGroup.setDisplayValue(mybQustnrGroup.getDisplayValue());
				questionGroup.setFilterFlag(mybQustnrGroup.isFilterFlag());
				questionGroup.setSortNumber(mybQustnrGroup.getSortNumber());
				questionGroup.setNormCalculateValue(mybQustnrGroup.getNormCalculateValue());
				questionGroup.setNormInputValue(mybQustnrGroup.getNormInputValue());
				questionGroup.setCreatedBy(mybQustnrGroup.getCreatedBy());
				questionGroup.setType(mybQustnrGroup.getType());
				questionGroup.setCustomQuestionType(mybQustnrGroup.getCustomQuestionType());
				questionGroup.setChartOneDimnsn(mybQustnrGroup.getChartOneDimnsn());
				questionGroup.setChartMultiDimnsn(mybQustnrGroup.getChartMultiDimnsn());
				questionGroup.setChartStore(mybQustnrGroup.getChartStore());
				questionGroup.setChartTime(mybQustnrGroup.getChartTime());
				questionGroup.setChartTimeDimnsn(mybQustnrGroup.getChartTimeDimnsn());
				questionGroup.setActiveFlag(mybQustnrGroup.getActiveFlag());
				questionGroup.setOptionalNumber(mybQustnrGroup.getOptionalNumber());
				questionGroup.setSelectQuestionCount(0);
				listGroup.add(questionGroup);
				map.put(mybQustnrGroup.getId(), mybQustnrGroup);
			}
			questionTmpltVO.setQuestionGroup(listGroup);
			// 根据行业查询模板
			if (listTmplt != null) {
				String tempId = listTmplt.getId();
				MybQuestionnaireTemplate templateId = new MybQuestionnaireTemplate();
				templateId.setId(tempId);
				// 根据模板查询所有的问题
				List<MybQuestion> listquestion = mybQuestionRepository.findByquestionTemplate(templateId);
				if (listquestion != null && listquestion.size() != -1) {
					QuestionsVo questionsVo = null;
					List<QuestionsVo> listq = new ArrayList<QuestionsVo>();
					for (MybQuestion mybQuestion : listquestion) {
						if (map.get(mybQuestion.getQuestionGroup().getId()) != null) {
							questionsVo = new QuestionsVo();
							questionsVo.setQuestionId(mybQuestion.getId());
							MybQuestionGroup group = map.get(mybQuestion.getQuestionGroup().getId());
							questionsVo.setQuestionGroupId(group.getId());
							questionsVo.setQuestionGroupName(group.getName());
							questionsVo.setQuestionGroupValue(group.getDisplayValue());
							questionsVo.setDataSourcesId(mybQuestion.getDataSourcesId());
							questionsVo.setQuestionGroupSortNumber(group.getSortNumber());
							questionsVo.setQuestionName(mybQuestion.getName());
							questionsVo.setQuestionValue(mybQuestion.getValue());
							questionsVo.setSortNumber(mybQuestion.getSortNumber());
							questionsVo.setQuestionType(mybQuestion.getQuestionType());
							questionsVo.setActiveFlag(false);
							questionsVo.setTopFlag(mybQuestion.getTopFlag());
							questionsVo.setEditFlag(mybQuestion.getEditFlag());
							questionsVo.setChartOneDimnsn(mybQuestion.getChartOneDimension());
							questionsVo.setChartMultiDimnsn(mybQuestion.getChartMultiDimension());
							questionsVo.setChartStore(mybQuestion.getChartStore());
							questionsVo.setBusinessType(mybQuestion.getBusinessType());
							questionsVo.setFilterFlag(mybQuestion.isFilterFlag());
							questionsVo.setChartTime(mybQuestion.getChartTime());
							questionsVo.setNormCalculateValue(mybQuestion.getNormCalculateValue() == null ? 0
									: mybQuestion.getNormCalculateValue());
							questionsVo.setNormInputValue(
									mybQuestion.getNormInputValue() == null ? 0 : mybQuestion.getNormInputValue());
							questionsVo.setChartTimeDimnsn(mybQuestion.getChartTimeDimension());
							questionsVo.setTopFlag(mybQuestion.getTopFlag());
							questionsVo.setTemplateFlag(true);
							List<Options> list = new ArrayList<Options>();
							Set<MybOption> listOptions = mybQuestion.getOptions();
							if (listOptions != null && listOptions.size() != 0) {
								Options options = null;
								for (MybOption mybOptions : listOptions) {
									options = new Options();
									options.setOptionId(mybOptions.getId());
									options.setOptionName(mybOptions.getName());
									options.setOptionValue(mybOptions.getValue());
									options.setActiveFlag(true);
									options.setSortNumber(mybOptions.getSortNumber());
									list.add(options);
								}
								questionsVo.setOptions(list);
							}
							listq.add(questionsVo);
						}
					}
					questionTmpltVO.setQuestions(listq);
				}
				mongoTemplate.save(questionTmpltVO);
				aReq.setData(questionTmpltVO);
				// 查询用户行业信息
				return aReq;
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return aReq;
	}

	/**
	 * queryQuestionByUserId TODO(查询当前所有的问题模板)
	 * 
	 * @author wangzx
	 * @return
	 */
	public Map<String, Object> queryQuestionTmplByUserId(int currentPage, int pageSize) {
		Query query = null;
		DBObject dbObjec = new BasicDBObject();
		dbObjec.put("tenementId", ShiroDb.getAccount().getId());
		DBObject dbfiled = new BasicDBObject();
		dbfiled.put("qustnrTmpltId", 1);
		dbfiled.put("industry", 1);
		dbfiled.put("trendName", 1);
		dbfiled.put("qustnrName", 1);
		dbfiled.put("tenementId", 1);
		dbfiled.put("creditAmount", 1);
		dbfiled.put("collectedAmount", 1);
		dbfiled.put("companyName", 1);
		dbfiled.put("qustnnrStatus", 1);
		dbfiled.put("createdTime", 1);
		dbfiled.put("updatedTime", 1);
		query = new BasicQuery(dbObjec, dbfiled);
		query.with(new Sort(Direction.DESC, "updatedTime"));
		// 获取总条数
		long totalCount = this.mongoTemplate.count(query, QuestionTmpltVO.class);
		// 总页数
		int skip = (currentPage - 1) * pageSize;
		query.skip(skip);// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录
		List<QuestionTmpltVO> list = mongoTemplate.find(query, QuestionTmpltVO.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("page", Page.pageHtml((int) totalCount, pageSize, currentPage));
		return map;
	}

	/**
	 * queryQuestionByUserId TODO(查询当前所有的问题模板)
	 * 
	 * @author wangzx
	 * @return
	 */
	public String queryQuestionTmplByUserIdForJson(int currentPage, int pageSize) {
		Query query = new Query();
		Criteria criterg = Criteria.where("tenementId").is(ShiroDb.getAccount().getId());
		query.addCriteria(criterg);
		// 获取总条数
		long totalCount = this.mongoTemplate.count(query, QuestionTmpltVO.class);
		// 总页数
		int totalPage = (int) (totalCount % pageSize);
		int skip = (currentPage - 1) * pageSize;
		query.skip(skip);// skip相当于从那条记录开始
		query.limit(pageSize);// 从skip开始,取多少条记录
		BasicDBObject field = new BasicDBObject();
		field.put("qustnrTmpltId", true);
		field.put("qustnrName", true);
		field.put("updatedTime", true);
		field.put("status", true);
		field.put("creditAmount", true);
		List<QuestionTmpltVO> list = mongoTemplate.find(query, QuestionTmpltVO.class);
		JSONArray jArray = new JSONArray();
		JSONObject jbParent = new JSONObject();
		JSONObject jb = null;
		for (QuestionTmpltVO questionTmpltVO : list) {
			jb = new JSONObject();
			jb.put("qustnrTmpltId", questionTmpltVO.getQustnrTmpltId());
			jb.put("qustnrName", questionTmpltVO.getQustnrName());
			jb.put("updatedTime", questionTmpltVO.getUpdatedTime());
			jb.put("status", questionTmpltVO.getQustnnrStatus());
			jb.put("creditAmount", questionTmpltVO.getCreditAmount());
			jArray.add(jb);
		}
		jbParent.put("Rows", jArray);
		jbParent.put("Total", String.valueOf(totalPage));
		return jbParent.toString();
	}

	/**
	 * queryMongodbQuestionBytempletId TODO(根据模板主键查询数据)
	 * 
	 * @author wangzx
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, Object> queryMongodbQuestionBytempletId(String id) throws UnsupportedEncodingException {
		Map<String, Object> retunMap = new HashMap<String, Object>();
		// 查询所有的分组
		Query query = new Query();
		ShiroDb.getAccount().getId();
		Criteria criterg = Criteria.where("_id").is(id);
		query.addCriteria(criterg);
		QuestionTmpltVO questionTmpltVO = mongoTemplate.findOne(query, QuestionTmpltVO.class);
		retunMap.put("data", java.net.URLEncoder.encode(JsonUtil.objectToJson(questionTmpltVO), "UTF-8"));
		Map<String, QuestionGroupVO> questionMap = new TreeMap<String, QuestionGroupVO>();
		for (QuestionGroupVO mybQustnrGroup : questionTmpltVO.getQuestionGroup()) {
			mybQustnrGroup.setCustomQuestionType(String.valueOf(Utils.getType(mybQustnrGroup.getCustomQuestionType())));
			if (mybQustnrGroup.getActiveFlag() != 0) {
				questionMap.put(mybQustnrGroup.getQuestionGroupId(), mybQustnrGroup);
			}
		}
		Map<String, List<QuestionsVo>> mapQuestion = new TreeMap<String, List<QuestionsVo>>();
		ShowQuestionVO showQuestionVO = new ShowQuestionVO();
		String[] splic = questionTmpltVO.getIndustry().split(",");
		if (splic.length == 2) {
			showQuestionVO.setIndustryName(splic[0].toString());
			showQuestionVO.setTemplName(splic[1].toString());
		} else if (splic.length == 3) {
			showQuestionVO.setIndustryName(splic[0].toString());
			showQuestionVO.setSubIndustryName(splic[1].toString());
			showQuestionVO.setTemplName(splic[2].toString());
		}
		Map<String, Object> map = questionTmpltVO.getWelcome();
		if (map.get(Param.ACTIVE_FLAG).equals(true)) {
			showQuestionVO.setActiveFlag(true);
		} else {
			showQuestionVO.setActiveFlag(false);
		}
		Map<String, Object> mapEndWlcome = questionTmpltVO.getWelcome();
		if (mapEndWlcome.get(Param.ACTIVE_FLAG).equals(true)) {
			showQuestionVO.setActiveFlag(true);
		} else {
			showQuestionVO.setActiveFlag(false);
		}
		showQuestionVO.setEndWelcomeMsg(mapEndWlcome.get(Param.WELCOME_MSG).toString());
		showQuestionVO.setWelcomeMsg(map.get(Param.WELCOME_MSG).toString());
		showQuestionVO.setQustnrName(questionTmpltVO.getQustnrName());
		showQuestionVO.setCreditAmount(questionTmpltVO.getCreditAmount());
		showQuestionVO.setTrendName(questionTmpltVO.getTrendName());
		retunMap.put("template", showQuestionVO);
		// 根据ID进行分组
		if (questionTmpltVO.getQuestions() != null && questionTmpltVO.getQuestions().size() != 0) {
			for (QuestionsVo questionsVo : questionTmpltVO.getQuestions()) {
				if (mapQuestion.get(questionsVo.getQuestionGroupId()) != null) {
					questionsVo.setQuestionType(String.valueOf(Utils.getType(questionsVo.getQuestionType())));
					mapQuestion.get(questionsVo.getQuestionGroupId()).add(questionsVo);
				} else {
					List<QuestionsVo> list2 = new ArrayList<QuestionsVo>();
					questionsVo.setQuestionType(String.valueOf(Utils.getType(questionsVo.getQuestionType())));
					list2.add(questionsVo);
					mapQuestion.put(questionsVo.getQuestionGroupId(), list2);
				}
			}
		}
		Map<QuestionGroupVO, List<QuestionsVo>> callMap = new HashMap<QuestionGroupVO, List<QuestionsVo>>();
		// 合并分组结合
		for (Entry<String, QuestionGroupVO> entry : questionMap.entrySet()) {
			if (mapQuestion.get(entry.getKey()) != null) {
				callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
			} else {
				callMap.put(questionMap.get(entry.getKey()), mapQuestion.get(entry.getKey()));
			}
		}

		List<Map.Entry<QuestionGroupVO, List<QuestionsVo>>> infoIds = new ArrayList<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>(
				callMap.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<QuestionGroupVO, List<QuestionsVo>>>() {
			public int compare(Map.Entry<QuestionGroupVO, List<QuestionsVo>> o1,
					Map.Entry<QuestionGroupVO, List<QuestionsVo>> o2) {
				return String.valueOf(o1.getKey().getSortNumber())
						.compareTo(String.valueOf(o2.getKey().getSortNumber()));
			}
		});
		retunMap.put("mapQuestion", infoIds);
		return retunMap;
	}

	/**
	 * publishQuestion TODO(数据采集问题)
	 * 
	 * @author wangzx
	 * @return
	 */

	/**
	 * stopQuestionById TODO(停止当前模板)
	 * 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	@Transactional
	public AjaxReq stopQuestionById(String questionid) {
		AjaxReq ajaxReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(questionid)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("数据不完整");
				return ajaxReq;
			}
			Query query = new Query();
			Criteria criterg = Criteria.where("_id").is(questionid);
			query.addCriteria(criterg);
			Update update = new Update();
			update.set("qustnnrStatus", "1");
			mongoTemplate.updateMulti(query, update, QuestionTmpltVO.class);
			ajaxReq.setSuccess(true);
		} catch (Exception e) {
			ajaxReq.setSuccess(false);
			ajaxReq.setMessage("数据不完整");
			return ajaxReq;
		}
		return ajaxReq;
	}

	/**
	 * delQuestionById TODO()
	 * 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	@Transactional
	public AjaxReq delQuestionById(String id) {
		AjaxReq req = new AjaxReq();
		try {
			Query query = new Query();
			Criteria criterg = Criteria.where("id").is(id);
			query.addCriteria(criterg);
			mongoTemplate.remove(query, QuestionTmpltVO.class);
			req.setSuccess(true);
		} catch (Exception e) {
			req.setSuccess(false);
			req.setMessage("删除失败！");
		}
		return req;
	}

	/**
	 * updateBasicQuestion TODO(更新基本信息)
	 * 
	 * @author wangzx
	 * @param templId（主键ID）
	 * @param qustnr_name（问题名称）
	 * @param credit_amount（问卷数量）
	 * @return
	 */
	@Transactional
	public AjaxReq updateBasicQuestionById(String templId, String qustnr_name, int credit_amount) {
		AjaxReq ajaxReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(templId)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("数据不完整");
				return ajaxReq;
			}
			if (StringUtils.isBlank(qustnr_name)) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("数据不完整");
				return ajaxReq;
			}
			if (credit_amount == 0) {
				ajaxReq.setSuccess(false);
				ajaxReq.setMessage("数据不完整");
				return ajaxReq;
			}
			Query query = new Query();
			Criteria criterg = Criteria.where("_id").is(templId);
			query.addCriteria(criterg);
			Update update = new Update();
			update.set("qustnrName", qustnr_name);
			update.set("creditAmount", credit_amount);
			mongoTemplate.updateMulti(query, update, QuestionTmpltVO.class);
			ajaxReq.setSuccess(true);
		} catch (Exception e) {
			ajaxReq.setSuccess(false);
			ajaxReq.setMessage("数据不完整");
			return ajaxReq;
		}
		return ajaxReq;
	}

	/**
	 * updateQuestion TODO(动态添加数据)
	 * 
	 * @author wangzx
	 * @param templId
	 * @param data
	 * @return
	 */
	@Transactional
	public AjaxReq updateQuestion(String templId, String data) {
		AjaxReq aReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(templId)) {
				aReq.setSuccess(false);
				aReq.setMessage("添加失败");
				return aReq;
			}
			if (StringUtils.isBlank(data)) {
				aReq.setSuccess(false);
				aReq.setMessage("添加失败");
				return aReq;
			}
			// 更新数据
			// groupRepository.updateQuestionGroupDisplayValByQuestionType(groupValue,questionGroupId);
			Query query = new Query();
			Criteria criterg = Criteria.where("id").is(templId);
			query.addCriteria(criterg);
			List<QuestionsVo> lsitquestion = new ArrayList<QuestionsVo>();
			JSONObject jb = JSONObject.fromObject(data);
			JSONArray jArray = jb.getJSONArray("questions");
			JSONArray jaGroup = jb.getJSONArray("questionGroup");
			JSONArray jaStore = jb.getJSONArray("store");
			JSONArray jaStoreGroup = jb.getJSONArray("storeGroup");
			JSONObject jbquestion = null;
			QuestionGroupVO questionVo = null;
			List<QuestionGroupVO> listQuestionGroup = new ArrayList<QuestionGroupVO>();
			for (int i = 0; i < jaGroup.size(); i++) {
				jbquestion = jaGroup.getJSONObject(i);
				questionVo = JsonUtil.jsonToObject(jbquestion.toString(), QuestionGroupVO.class);
				listQuestionGroup.add(questionVo);
			}
			//store json处理失败问题
			List<Store> listStorep = new ArrayList<Store>();
			Store store= null;
			for (int i = 0; i < jaStore.size(); i++) {
				jbquestion = jaStore.getJSONObject(i);
				store= JsonUtil.jsonToObject(jbquestion.toString(), Store.class);
				listStorep.add(store);
			}
			//处理storeGroup
			List<StoreGroupVO> listStoreGroup = new ArrayList<StoreGroupVO>();
			StoreGroupVO group = null;
			for (int i = 0; i <jaStoreGroup.size() ; i++) {
				jbquestion = jaStoreGroup.getJSONObject(i);
				group = JsonUtil.jsonToObject(jbquestion.toString(), StoreGroupVO.class);
				if (null != jbquestion.get("store")) {
					List<Store> listStore = JsonUtil.jsonToList(jbquestion.getJSONArray("store").toString(), Store.class);
					group.setStore(listStore);
				}
				listStoreGroup.add(group);
			}
			
			for (int i = 0; i < jArray.size(); i++) {
				jbquestion = jArray.getJSONObject(i);
				QuestionsVo questionsVo = JsonUtil.jsonToObject(jbquestion.toString(), QuestionsVo.class);
				if (null != jbquestion.get("options")) {
					List<Options> list = JsonUtil.jsonToList(jbquestion.getJSONArray("options").toString(),
							Options.class);
					Collections.sort(list, new Comparator<Options>() {
						public int compare(Options arg0, Options arg1) {
							return String.valueOf(arg0.getSortNumber()).compareTo(String.valueOf(arg1.getSortNumber()));
						}
					});
					questionsVo.setOptions(list);
				}
				if (null != jbquestion.get("activePeriod")) {
					List<ActivePeriod> list = JsonUtil.jsonToList(jbquestion.getJSONArray("activePeriod").toString(),
							ActivePeriod.class);
					questionsVo.setActivePeriod(list);
				}
				lsitquestion.add(questionsVo);
			}
			QuestionTmpltVO questionTmpltVO = JsonUtil.jsonToObject(data, QuestionTmpltVO.class);
			Collections.sort(lsitquestion, new Comparator<QuestionsVo>() {
				public int compare(QuestionsVo arg0, QuestionsVo arg1) {
					return String.valueOf(arg0.getSortNumber()).compareTo(String.valueOf(arg1.getSortNumber()));
				}
			});
			questionTmpltVO.getQuestions().clear();
			questionTmpltVO.setQuestions(lsitquestion);
			questionTmpltVO.getQuestionGroup().clear();
			questionTmpltVO.setQuestionGroup(listQuestionGroup);
			questionTmpltVO.setStore(listStorep);
			questionTmpltVO.setStoreGroup(listStoreGroup);
			mongoTemplate.remove(query, QuestionTmpltVO.class);
			mongoTemplate.save(questionTmpltVO);
			aReq.setSuccess(true);
			aReq.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			aReq.setSuccess(false);
			aReq.setMessage("添加失败");
			return aReq;
		}
		return aReq;
	}

}