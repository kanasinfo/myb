package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartD7 extends MybChart {

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
			if (StringUtils.isBlank(questionId)) {
				aReq.setSuccess(false);
				aReq.setMessage("数据不正确");
				return aReq;
			}

			Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();
			MybChartUtils.packDimensionsFilter("", filter, fdmp);
			Criteria ct = null;
			if (fdmp.get("filterOnly") != null) {
				ct = new Criteria()
						.andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			}
			String yName = JSONObject.fromObject(dimension).getString("dateType");

			Criteria criteria = null;
			Aggregation aggregation;
			AggregationResults<TagCount> results = null;
			List<TagCount> tagCount = null;
			List<Criteria> criterias = null;
			String currentDate = "0";
			String priorDate = "0";
			JSONArray xName = new JSONArray();
			// 步骤1:获取最新两个时间
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)), match(ct),
					group("$" + yName).count().as("count"), sort(Sort.Direction.DESC, "_id"));
			results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			tagCount = results.getMappedResults();
			if (tagCount.size() > 0) {
				currentDate = tagCount.get(0).get_id();
				if (tagCount.size() > 1) {
					priorDate = tagCount.get(1).get_id();
				}
			}
			// 步骤2:获取当前总数
			Aggregation aggregationCount = null;
			aggregationCount = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
					match(Criteria.where(yName).in(currentDate)), match(ct),
					group("$questionnaireId").count().as("count"));
			List<TagCount> tagAllCount = mongoTemplate.aggregate(aggregationCount, "answer", TagCount.class)
					.getMappedResults();
			double allAnswerCount = 0;
			if (tagAllCount.size() > 0) {
				allAnswerCount = tagAllCount.get(0).getCount();
			}
			// 步骤3:获取上期总数
			aggregationCount = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
					match(Criteria.where(yName).in(priorDate)), match(ct),
					group("$questionnaireId").count().as("count"));
			tagAllCount = mongoTemplate.aggregate(aggregationCount, "answer", TagCount.class)
					.getMappedResults();
			double priorAnswerCount = 0;
			if (tagAllCount.size() > 0) {
				priorAnswerCount = tagAllCount.get(0).getCount();
			}

			
			
			criterias = new ArrayList<Criteria>();			
			List<JSONObject> jaQG = JSONArray.fromObject(questionGroup);
			JSONObject jOResult = null;
			List<JSONObject> jAResult = new JSONArray();
			for (JSONObject jo : jaQG) {
				criterias = new ArrayList<Criteria>();
				String id = jo.getString("questionId");
				jOResult = new JSONObject();
				jOResult.put("name", jo.getString("questionName"));
				
				// 步骤4:获取当前不达标数量
				criterias.clear();
				criterias.add(Criteria.where("answers.questionIdValue").in(id + "_true"));
				criterias.add(Criteria.where(yName).in(currentDate));
				criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
						match(criteria), match(ct), unwind("$answers"),
						match(Criteria.where("answers.questionId").is(id)),
						group("$answers.questionName").count().as("count"));
				results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				tagCount = results.getMappedResults();
				if (tagCount.size() > 0) {
					jOResult.put("sValue", (100 * tagCount.get(0).getCount()) / allAnswerCount );
				} else {
					jOResult.put("sValue", 0);
				}
				
				// 步骤5:获取上期不达标数量
				criterias.clear();
				criterias.add(Criteria.where("answers.questionIdValue").in(id + "_true"));
				criterias.add(Criteria.where(yName).in(priorDate));
				criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
						match(criteria), match(ct), unwind("$answers"),
						match(Criteria.where("answers.questionId").is(id)),
						group("$answers.questionName").count().as("count"));
				results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				tagCount = results.getMappedResults();
				if (tagCount.size() > 0) {
					jOResult.put("pValue", (100 * tagCount.get(0).getCount()) / priorAnswerCount );
				} else {
					jOResult.put("pValue", 0);
				}
								
				jAResult.add(jOResult);
			}
			
			List<String> currentValueList = new ArrayList<String>();
			List<String> priorValueList = new ArrayList<String>();		
			for (int i = 0; i < jAResult.size(); i++) {
				JSONObject jo = jAResult.get(i);
				currentValueList.add(((Integer)jo.getInt("sValue")).toString());
				priorValueList.add(((Integer)jo.getInt("pValue")).toString());
			}

			/**
			 * 
			 * 1、满意顾客比例=“8-10分”顾客占比
			 * 2、鼠标晃动右侧数字时显示“与行业平均值相比处于领先/落后/相当水平”（仅限标准问卷中的指标，标准参照总体满意度）
			 * 3、鼠标晃动图表中色块时，显示对应的百分比数字。 4、折线表示 满意顾客占比=“8-10分”顾客占比
			 * 5、当指标数量小于3个时，不显示Comments。当指标数量为3个时，n=1；指标数量为4-5个时，n=2；指标数量大于或等于6个时，n=3.
			 * 
			 * Comments: 1、从顾客对各项服务环节的评价来看， <插入判别描述1>； 2、相对来讲，
			 * <插入满意顾客占比最高的n项指标>满意度评分较高， <插入满意顾客占比最低的n项指标>满意度评分较低。
			 * 
			 * 判别条件:判别描述1 每个指标的满意顾客比例均≥80: 本店的各服务细节表现优秀，各项指标均达到了高满意度评价（80分及以上）
			 * 半数及以上指标的满意顾客比例≥80:超过半数的服务环节达到了高满意度评价（80分及以上）
			 * 超过半数指标的满意顾客比例＜80:超过半数的服务环节未能达到高满意度评价（80分及以上）
			 * 每项指标的满意顾客比例均＜80:本店的各项服务环节均未达到高满意度评价（80分以上）
			 * 
			 */

			List<JSONObject> jbOpt = jAResult;

			JSONArray name = new JSONArray();
			List<String> valueList = new ArrayList<String>();
			List<String> avgValueList = new ArrayList<String>();
			Map<String, String> mapTree = new TreeMap<String, String>();
			for (JSONObject jot : jbOpt) {
				name.add(jot.getString("name"));
				avgValueList.add(String.valueOf((Double.parseDouble(jot.getString("sValue")) - Math.random() * 20 < 0
						? Double.parseDouble(jot.getString("sValue"))
						: Double.parseDouble(jot.getString("sValue")) - Math.random() * 20)));
				mapTree.put(jot.getString("name"),
						String.valueOf((Double.parseDouble(jot.getString("sValue")) - Math.random() * 20 < 0
								? Double.parseDouble(jot.getString("sValue"))
								: Double.parseDouble(jot.getString("sValue")) - Math.random() * 20)));
				valueList.add(jot.getString("sValue"));
			}
			StringBuffer gapDesc = new StringBuffer();
			JSONArray comments = new JSONArray();
			JSONArray jaQuestionName = new JSONArray();
			if (mapTree.size() >= 3) {
				int i = 0;
				for (Map.Entry<String, String> object : mapTree.entrySet()) {
					if (i >= 3) {
						++i;
						jaQuestionName.add(object.getValue());
					}
				}

			} else {
				for (Map.Entry<String, String> object : mapTree.entrySet()) {
					jaQuestionName.add(object.getValue());
				}
			}
			if (avgValueList.size() > 4) {

			} else {
				int leAvg = 0;
				int gtAvg = 0;
				for (Map.Entry<String, String> ent : mapTree.entrySet()) {
					if (Double.valueOf(ent.getValue()) <= 10) {
						++leAvg;
						if (leAvg == avgValueList.size()) {
							gapDesc.append("1、从各项服务规范的达标状况看,").append("本店的各项服务规范执行均比较到位，各项规范均达到了较高标准（90%及以上）2、")
									.append("相对来讲，未达标率较高的是:").append(jaQuestionName);
						} else if (leAvg >= valueList.size() / 2) {
							gapDesc.append("1、从各项服务规范的达标状况看,").append("超过半数的服务规范达到了较高标准（90%及以上） 2、")
									.append("相对来讲，未达标率较高的是:").append(jaQuestionName);
						}

					} else if (Double.valueOf(ent.getValue()) >= 10) {
						++gtAvg;
						if (gtAvg == avgValueList.size()) {
							gapDesc.append("1、从各项服务规范的达标状况看,").append("超过半数的服务环节未能达到90%的达标率 2、")
									.append("相对来讲，未达标率较高的是:").append(jaQuestionName);
						} else if (gtAvg >= valueList.size() / 2) {
							gapDesc.append("1、从各项服务规范的达标状况看,").append("本店的各项服务环节均未达到90%的达标率 2、")
									.append("相对来讲，未达标率较高的是:").append(jaQuestionName);
						}
					}
				}
			}

	
			StringBuffer sb = new StringBuffer();
			sb.append("{tooltip : {trigger: 'axis'},");
			sb.append("toolbox: {show : true,feature : {mark : {show: true},");
			sb.append("dataView : {show: true, readOnly: false},magicType: {show: true, type: ['line', 'bar']},");
			sb.append("restore : {show: true},saveAsImage : {show: true}}},");
			sb.append("calculable : true,legend: {data:['本期未达标率','上期未达标率']},");
			sb.append("xAxis : [{type : 'category',data : " + name + "}],");
			sb.append("yAxis : [{type : 'value',name : '未达标率',axisLabel : {formatter: '{value} %'}}],");
			sb.append("series : [{name:'本期未达标率',type:'line',data:" + currentValueList + "},");
			sb.append("{name:'上期未达标率',type:'bar',data:" + priorValueList + "}]}");

			// 当指标数量小于3个时，不显示Comments。当指标数量为3个时，n=1；指标数量为4-5个时，n=2；指标数量大于或等于6个时，n=3.

			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", "顾客对各服务环节的体验满意度如何？");
			rspjb.put("chartlegend", "");
			rspjb.put("option", sb.toString());
			rspjb.put("comments", comments.toString());
			aReq.setSuccess(true);
			aReq.setData(rspjb);
		} catch (Exception e) {
			e.printStackTrace();
			aReq.setSuccess(false);
			aReq.setMessage("查询失败");
			return aReq;
		}
		return aReq;
	}

}
