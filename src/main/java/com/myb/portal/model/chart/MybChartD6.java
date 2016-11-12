package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBList;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.chart.result.TwoDimnCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartD6 extends MybChart {

	@Override
	public AjaxReq getData(MongoTemplate mongoTemplate) {
		/**
		 * TO-ADD: title, name, norm,
		 * 
		 */
		AjaxReq aReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(questionnaireId)) {
				aReq.setSuccess(false);
				aReq.setMessage("数据不正确");
				return aReq;
			}
			if (StringUtils.isBlank(questionGroup)) {
				aReq.setSuccess(false);
				aReq.setMessage("数据不正确");
				return aReq;
			}
			if (StringUtils.isBlank(store)) {
				aReq.setSuccess(false);
				aReq.setMessage("数据不正确");
				return aReq;
			}
			Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();
			MybChartUtils.packDimensionsFilter("", filter, fdmp);
			Criteria ct = new Criteria()
					.andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));

			if (StringUtils.isNotBlank(store)) {
				JSONObject jbStore = JSONObject.fromObject(store);
				if ("store".equals(jbStore.getString("storeType"))) {
					List<String> storeIds = new ArrayList<String>();
					JSONArray ja = JSONArray.fromObject(jbStore.getJSONArray("store"));
					JSONObject jb = null;
					JSONArray jbTotal = new JSONArray();
					for (int i = 0; i < ja.size(); i++) {
						jb = ja.getJSONObject(i);
						jbTotal.add(jb.getString("storeName"));
						storeIds.add(jb.getString("storeId"));
					}
					JSONArray returnJa = new JSONArray();
					JSONArray questionJa = JSONArray.fromObject(questionGroup);
					JSONObject questionjb = null;
					Map<String, String> mapQuestionId = new HashMap<String, String>();
					for (int i = 0; i < questionJa.size(); i++) {
						questionjb = questionJa.getJSONObject(i);
						mapQuestionId.put(questionjb.getString("questionId"), questionjb.getString("questionName"));
					}
					String comments = "";
					// 分解store
					this.findStore(ct, returnJa, mapQuestionId, storeIds, questionnaireId, mongoTemplate);
					JSONObject rspjb = new JSONObject();
					JSONObject jbTable = new JSONObject();
					jbTable.put("total", jbTotal);
					jbTable.put("data", returnJa.toString());

						
					rspjb.put("type", "table");
					rspjb.put("title", "按门店纬度比较顾客对各服务环节的体验满意度如何？");
					rspjb.put("chartlegend", "");
					rspjb.put("option",jbTable.toString() );
					rspjb.put("comments", comments.toString());
					aReq.setSuccess(true);
					aReq.setData(rspjb);
					
					
					
				} else if ("storeGroup".equals(jbStore.getString("store_type"))) {
					List<String> storeIds = new ArrayList<String>();
					JSONArray ja = JSONArray.fromObject(jbStore.getJSONArray("storeGroup"));
					Map<String, List<String>> map = new HashMap<String, List<String>>();
					JSONObject jb = null;
					JSONObject jsGroupStore = null;
					JSONArray jbTotal = new JSONArray();
					for (int i = 0; i < ja.size(); i++) {
						jb = ja.getJSONObject(i);
						jbTotal.add(jb.getString("storeGroupName"));
						JSONArray groupStoreJa = jb.getJSONArray("store");
						for (int j = 0; j < groupStoreJa.size(); j++) {
							jsGroupStore = groupStoreJa.getJSONObject(j);
							storeIds.add(jsGroupStore.getString("storeId"));
						}
						map.put(jb.getString("storeGroupId"), storeIds);
					}
					JSONArray returnJa = new JSONArray();
					JSONArray questionJa = JSONArray.fromObject(questionGroup);
					JSONObject questionjb = null;
					Map<String, String> mapQuestionId = new HashMap<String, String>();
					for (int i = 0; i < questionJa.size(); i++) {
						questionjb = questionJa.getJSONObject(i);
						mapQuestionId.put(questionjb.getString("questionId"), questionjb.getString("questionName"));
					}
					String comments = "";
					// 分解store
					this.findStoreGroup(ct, returnJa, mapQuestionId, map, questionnaireId, mongoTemplate);
					aReq.setSuccess(true);
					JSONObject rspjb = new JSONObject();
					JSONObject jbTable = new JSONObject();
					jbTable.put("total", jbTotal);
					jbTable.put("data", returnJa.toString());
					
					
					rspjb.put("type", "table");
					rspjb.put("title", "按门店纬度比较顾客对各服务环节的体验满意度如何？");
					rspjb.put("chartlegend", "");
					rspjb.put("option", jbTable.toString());
					rspjb.put("comments", comments.toString());
					aReq.setSuccess(true);
					aReq.setData(rspjb);

				} else {
					aReq.setSuccess(false);
					aReq.setMessage("数据不正确");
					return aReq;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			aReq.setSuccess(false);
			aReq.setMessage("查询失败");
			return aReq;
		}
		return aReq;
	}

	/**
	 * findStore TODO(门店查询数据组装)
	 * 
	 * @author wangzx
	 * @param criteria
	 * @param ja
	 *            TODO(数据返回值)
	 * @param storeId
	 */
	protected void findStore(Criteria criteria, JSONArray ja, Map<String, String> questionid, List<String> storeId,
			String questionnaireId, MongoTemplate mongoTemplate) {
		// 解析纵坐标获取所选中的问题
		JSONObject jb = null;
		Criteria cr = null;
		List<Criteria> criterias = null;
		for (Map.Entry<String, String> ent : questionid.entrySet()) {
			jb = new JSONObject();
			jb.put("questionName", ent.getValue());
			JSONArray jaStore = new JSONArray();
			AggregationResults<TagCount> results = null;
			List<TagCount> tagCount = null;
			// 解析门店
			for (String s : storeId) {
				criterias = new ArrayList<Criteria>();
				// 封装查询条件
				criterias.add(Criteria.where("answers.questionIdValue").in(ent.getKey() + "_8", ent.getKey() + "_9",
						ent.getKey() + "_10"));
				cr = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				Aggregation aggregation = newAggregation(
						match(Criteria.where("questionnaireId").is(questionnaireId).and("storeId").is(s)),
						match(criteria), match(criteria), unwind("$answers"),
						match(Criteria.where("answers.questionId").is(ent.getKey())), match(cr),
						group("$answers.questionName").count().as("count"));
				results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				tagCount = results.getMappedResults();
				int percentageTrue = 0;
				if (tagCount.size() > 0) {
					percentageTrue = tagCount.get(0).getCount();
				}
				criterias.clear();
				criterias = new ArrayList<Criteria>();
				// 封装查询条件
				criterias.add(Criteria.where("answers.questionIdValue").in(ent.getKey() + "_1", ent.getKey() + "_2",
						ent.getKey() + "_3", ent.getKey() + "_4", ent.getKey() + "_5", ent.getKey() + "_6",
						ent.getKey() + "_7", ent.getKey() + "_8", ent.getKey() + "_9", ent.getKey() + "_10"));
				Aggregation aggregationa = newAggregation(
						match(Criteria.where("questionnaireId").is(questionnaireId).and("storeId").is(s)),
						match(criteria), match(criteria), unwind("$answers"),
						match(Criteria.where("answers.questionId").is(ent.getKey())),
						group("$answers.questionName").count().as("count"));
				results = mongoTemplate.aggregate(aggregationa, "answer", TagCount.class);
				tagCount = results.getMappedResults();
				int percentageAll = 0;
				if (tagCount.size() > 0) {
					percentageAll = tagCount.get(0).getCount();
				}
				double Percentage = 0;
				// 计算占比
				if (percentageTrue == 0) {
					Percentage = 0;
				} else {
					Percentage = percentageTrue / percentageAll * 100;
				}
				jaStore.add(Percentage + "%");
			}
			jb.put("data", jaStore);
			// 最终返回数据
			ja.add(jb);
		}
	}

	/**
	 * findStoreGroup TODO(门店组数据封装)
	 * 
	 * @author wangzx
	 */
	protected void findStoreGroup(Criteria criteria, JSONArray ja, Map<String, String> questionid,
			Map<String, List<String>> storeGroup, String questionnaireId, MongoTemplate mongoTemplate) {
		JSONObject jb = null;
		Criteria cr = null;
		List<Criteria> criterias = null;
		// 解析问题
		for (Map.Entry<String, String> entquestionId : questionid.entrySet()) {
			jb = new JSONObject();
			jb.put("questionName", entquestionId.getValue());
			JSONArray jaStore = new JSONArray();
			AggregationResults<TagCount> results = null;
			List<TagCount> tagCount = null;
			// 解析门店组
			for (Map.Entry<String, List<String>> ent : storeGroup.entrySet()) {
				int percentageTrue = 0;
				int percentageAll = 0;
				for (String s : ent.getValue()) {
					criterias = new ArrayList<Criteria>();
					// 封装查询条件
					criterias.add(Criteria.where("answers.questionIdValue").in(ent.getKey() + "_8", ent.getKey() + "_9",
							ent.getKey() + "_10"));
					cr = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					Aggregation aggregation = newAggregation(
							match(Criteria.where("questionnaireId").is(questionnaireId).and("storeId").is(s)),
							match(criteria), match(criteria), unwind("$answers"),
							match(Criteria.where("answers.questionId").is(ent.getKey())), match(cr),
							group("$answers.questionName").count().as("count"));
					results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					tagCount = results.getMappedResults();
					if (tagCount.size() > 0) {
						percentageTrue = tagCount.get(0).getCount();
					}
					criterias.clear();
					criterias = new ArrayList<Criteria>();
					// 封装查询条件
					criterias.add(Criteria.where("answers.questionIdValue").in(ent.getKey() + "_1", ent.getKey() + "_2",
							ent.getKey() + "_3", ent.getKey() + "_4", ent.getKey() + "_5", ent.getKey() + "_6",
							ent.getKey() + "_7", ent.getKey() + "_8", ent.getKey() + "_9", ent.getKey() + "_10"));
					Aggregation aggregationa = newAggregation(
							match(Criteria.where("questionnaireId").is(questionnaireId).and("storeId").is(s)),
							match(criteria), match(criteria), unwind("$answers"),
							match(Criteria.where("answers.questionId").is(ent.getKey())),
							group("$answers.questionName").count().as("count"));
					results = mongoTemplate.aggregate(aggregationa, "answer", TagCount.class);
					tagCount = results.getMappedResults();
					if (tagCount.size() > 0) {
						percentageAll = tagCount.get(0).getCount();
					}
				}
				double Percentage = 0;
				// 计算占比
				if (percentageTrue == 0) {
					Percentage = 0;
				} else {
					Percentage = percentageTrue / percentageAll * 100;
				}
				jaStore.add(Percentage + "%");
			}
			jb.put("data", jaStore);
			// 最终返回数据
			ja.add(jb);
		}
	}
}
