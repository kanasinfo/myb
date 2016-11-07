package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.myb.portal.model.chart.result.TwoDimnCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartP80 extends MybChart{

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

			// chartOneDimnsn,chartMultiDimnsn,chartStore,chartTime,chartTimeDimnsn,chartCustDimnsn
			String qstId = "1";
			String qstName = "";
			JSONArray names = new JSONArray();
			List<String> memberIds = new ArrayList<String>();
			List<String> optionIds = new ArrayList<String>();
			List<JSONObject> jaMembers = null;
			List<JSONObject> jbOpt = null;
			String dimensionStr = "";
			String satId = "";
			JSONObject jbDimn = null;
			List<JSONObject> specQusts = JSONArray.fromObject(specialQuestions);
			for (JSONObject specQust : specQusts) {
				if ("qustSatisfaction".equals(specQust.getString("businessType"))) {
					satId = specQust.getString("questionId");
				}
			}
			
			dimensionStr = "";
			jbDimn = JSONObject.fromObject(questionGroup);
			qstId = jbDimn.getString("questionId");
			qstName = jbDimn.getString("questionName");
			jbOpt = jbDimn.getJSONArray("options");

			Collections.sort(jbOpt, new Comparator<JSONObject>() {
				@Override
				public int compare(JSONObject o1, JSONObject o2) {
					return (o1.getInt("sortNumber") - o2.getInt("sortNumber"));
				}
			});
			for (JSONObject jot : jbOpt) {
				names.add(jot.getString("optionName"));
				optionIds.add(jot.getString("optionId"));
			}

			

			Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();
			MybChartUtils.packDimensionsFilter(dimensionStr, filter, fdmp);
			String yName = JSONObject.fromObject(dimension).getString("dateType");

			List<Criteria> criterias = null;
			List<Criteria> cts = new ArrayList<Criteria>();
			Criteria criteria = null;
			Criteria ct = null;
			BasicDBList listAll = null;
			BasicDBList list = null;
			BasicDBList listsubTotal = null;
			Aggregation aggregation = null;
			AggregationResults<TwoDimnCount> results = null;
			JSONObject jb = null;
			Field[] fields = new Field[2];
			Field x = Fields.field("x", "$year");
			fields[0] = x;
			Field y = Fields.field("y", "$" + yName);
			fields[1] = y;
			StringBuffer serialValues = new StringBuffer("[");
			JSONArray xName = new JSONArray();

			if (fdmp.get("filterOnly") != null) {
				ct = new Criteria()
						.andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
				// 步骤1: 找到所有的样本
				aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)), match(ct),
						unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
						group(Fields.from(fields)).count().as("count"), sort(Sort.Direction.ASC, "_id.x", "_id.y"));
				AggregationResults<TwoDimnCount> results2 = mongoTemplate.aggregate(aggregation, "answer",
						TwoDimnCount.class);
				listAll = (BasicDBList) results2.getRawResults().get("result");
				for (int i = 0; i < listAll.size(); i++) {
					BasicDBObject obj = (BasicDBObject) listAll.get(i);
					jb = JSONObject.fromObject(obj.get("_id"));
					xName.add(getDateName(jb.getString("y"), yName));
				}

				// 纬度
				JSONArray xValue = null;
				for (JSONObject opt : jbOpt) {
					Integer tmpCnt = 0;
					Integer tmpTotalCnt = 0;
					Integer tmpSubTotalCnt = 0;
					xValue = new JSONArray();

					// 步骤2: 找到纬度的所有样本（分数为1-10）
					criterias = new ArrayList<Criteria>();
					criterias.add(
							Criteria.where("answers.questionIdValue").in(qstId + "_" + opt.getString("optionValue")));
					criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							match(ct), match(criteria), unwind("$answers"),
							match(Criteria.where("answers.questionId").is(qstId)),
							group(Fields.from(fields)).count().as("count"), sort(Sort.Direction.ASC, "_id.x", "_id.y"));
					results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
					listsubTotal = (BasicDBList) results.getRawResults().get("result");

					// 步骤3: 找到纬度的满意的样本（分数为8，9，10）
					criterias = new ArrayList<Criteria>();
					criterias.add(Criteria.where("answers.questionIdValue").in(questionId + "_8", questionId + "_9",
							questionId + "_10"));
					criterias.add(
							Criteria.where("answers.questionIdValue").in(qstId + "_" + opt.getString("optionValue")));
					criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));

					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							match(ct), match(criteria), unwind("$answers"),
							match(Criteria.where("answers.questionId").is(qstId)),
							group(Fields.from(fields)).count().as("count"), sort(Sort.Direction.ASC, "_id.x", "_id.y"));
					results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
					list = (BasicDBList) results.getRawResults().get("result");

					for (int i = 0; i < listAll.size(); i++) {
						BasicDBObject obj = (BasicDBObject) listAll.get(i);
						String xStr = "";
						String yStr = "";
						tmpCnt = 0;
						tmpTotalCnt = (Integer) obj.get("count");
						jb = JSONObject.fromObject(obj.get("_id"));
						xStr = jb.getString("x");
						yStr = jb.getString("y");
						for (int p = 0; p < listsubTotal.size(); p++) {
							jb = JSONObject.fromObject(((BasicDBObject) listsubTotal.get(p)).get("_id"));
							if (xStr.equals(jb.getString("x")) && yStr.equals(jb.getString("y"))) {
								tmpSubTotalCnt = (Integer) ((BasicDBObject) listsubTotal.get(p)).get("count");
								for (int j = 0; j < list.size(); j++) {
									jb = JSONObject.fromObject(((BasicDBObject) list.get(j)).get("_id"));
									if (xStr.equals(jb.getString("x")) && yStr.equals(jb.getString("y"))) {
										tmpCnt = (Integer) ((BasicDBObject) list.get(j)).get("count");
									}
								}
							}

						}
						xValue.add(tmpSubTotalCnt == 0 ? 0 : (tmpCnt * 100) / tmpSubTotalCnt);

					}
					serialValues.append("{").append("name:'").append(opt.getString("optionValue"))
							.append("',type:'line',smooth:true,data:").append(xValue).append("},");

				}

				criterias = new ArrayList<Criteria>();
				criterias.add(Criteria.where("answers.questionIdValue").in(questionId + "_8", questionId + "_9",
						questionId + "_10"));
				criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				// 步骤4:找到总体满意的样本（分数为8，9，10）
				aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)), match(ct),
						match(criteria), unwind("$answers"), match(Criteria.where("answers.questionId").is(qstId)),
						group(Fields.from(fields)).count().as("count"), sort(Sort.Direction.ASC, "_id.x", "_id.y"));
				AggregationResults<TwoDimnCount> resultOverall = mongoTemplate.aggregate(aggregation, "answer",
						TwoDimnCount.class);
				list = (BasicDBList) resultOverall.getRawResults().get("result");
				Integer tmpCnt = 0;
				Integer tmpTotalCnt = 0;
				xValue = new JSONArray();
				for (int i = 0; i < listAll.size(); i++) {
					BasicDBObject obj = (BasicDBObject) listAll.get(i);
					String xStr = "";
					String yStr = "";
					tmpCnt = 0;
					tmpTotalCnt = (Integer) obj.get("count");
					jb = JSONObject.fromObject(obj.get("_id"));
					xStr = jb.getString("x");
					yStr = jb.getString("y");
					for (int j = 0; j < list.size(); j++) {
						jb = JSONObject.fromObject(((BasicDBObject) list.get(j)).get("_id"));
						if (xStr.equals(jb.getString("x")) && yStr.equals(jb.getString("y"))) {
							tmpCnt = (Integer) ((BasicDBObject) list.get(j)).get("count");
						}
						;
					}
					xValue.add((tmpCnt * 100) / tmpTotalCnt);

				}

				serialValues.append("{").append("name:'").append("总体客户")
						.append("',type:'line',smooth:true,itemStyle: { normal: { areaStyle: { type: 'default' } }}, data:")
						.append(xValue).append("}");
				serialValues.append("]");
				names.add("总体客户");
				/**
				 * { series: [ { name: '早餐客户', type: 'line', smooth: true, data:
				 * [20, 12, 21, 54, 60, 30, 10, 45, 34, 23, 65, 76] }, { name:
				 * '午餐客户', type: 'line', smooth: true, data: [30, 82, 34, 11,
				 * 39, 30, 10, 10, 12, 21, 24, 10] }, { name: '晚餐客户', type:
				 * 'line', smooth: true, data: [50, 6, 45, 35, 1, 40, 80, 45,
				 * 54, 56, 11, 14] }, { name: '总体客户', type: 'line', smooth:
				 * true, itemStyle: { normal: { areaStyle: { type: 'default' } }
				 * }, data: [30, 26, 45, 65, 35, 63, 70, 55, 25, 62, 74, 36] }]
				 */

			} else {
				// 自定义纬度
				list = new BasicDBList();
				for (JSONObject member : jaMembers) {
					ct = new Criteria().andOperator(fdmp.get(member.get("memberId"))
							.toArray(new Criteria[fdmp.get(member.get("memberId")).size()]));
					cts.add(ct);
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							match(ct), unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
							group("$answers.optionValue").count().as("count"));
//					AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer",
//							TagCount.class);
//					BasicDBList listTmp = (BasicDBList) results.getRawResults().get("result");
//					if (listTmp.size() > 0) {
//						BasicDBObject obj = (BasicDBObject) listTmp.get(0);
//						obj.put("_x", member.get("name"));
//						list.add(obj);
//					}

				}

			}

			StringBuffer legendStr = new StringBuffer("[");

			legendStr.substring(0, legendStr.length() - 1);
			legendStr.append("]");

			JSONArray lengendNames = new JSONArray();
			JSONArray serialValue = new JSONArray();
			// serialValue.add(value)
			// lengendNames: '早餐客户', '午餐客户', '晚餐客户'
			// xName: '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月',
			// '十月', '十一月', '十二月'
			// xValue
			/**
			 * 
			 * 
			 * 
			 * series: [ { name: '早餐客户', type: 'line', smooth: true, data: [20,
			 * 12, 21, 54, 60, 30, 10, 45, 34, 23, 65, 76] }, { name: '午餐客户',
			 * type: 'line', smooth: true, data: [30, 82, 34, 11, 39, 30, 10,
			 * 10, 12, 21, 24, 10] }, { name: '晚餐客户', type: 'line', smooth:
			 * true, data: [50, 6, 45, 35, 1, 40, 80, 45, 54, 56, 11, 14] }, {
			 * name: '总体客户', type: 'line', smooth: true, itemStyle: { normal: {
			 * areaStyle: { type: 'default' } } }, data: [30, 26, 45, 65, 35,
			 * 63, 70, 55, 25, 62, 74, 36] }]
			 */
			StringBuffer seriesString = new StringBuffer("");

			StringBuffer comments = new StringBuffer("");
			StringBuffer chartLegend = new StringBuffer("满意顾客占比");
			StringBuffer option = new StringBuffer(
					"{tooltip : {trigger: 'axis'},legend: {x:'right',y:'center',orient:'vertical',data:"
							+ names.toString().replaceAll("\"", "'") + "},");
			option.append(
					"toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},");
			option.append(
					"magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},restore : {show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,");
			option.append("xAxis : [{type : 'category',boundaryGap : false,data : "
					+ xName.toString().replaceAll("\"", "'") + "}],");
			option.append("yAxis : [{type : 'value',min:0,max:100,axisLabel : {formatter: '{value} %'}}],");
			option.append("series : ");
			option.append(serialValues);
			option.append("}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "line");
			rspjb.put("title", questionName);
			rspjb.put("chartlegend", legendStr.toString());
			rspjb.put("option", option.toString());
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

	public String getDateName(String dateNum, String dateType) {
		String dateName = dateNum;
		if ("month".equals(dateType)) {
			switch (dateNum) {
			case "1":
				dateName = "一月";
				break;
			case "2":
				dateName = "二月";
				break;
			case "3":
				dateName = "三月";
				break;
			case "4":
				dateName = "四月";
				break;
			case "5":
				dateName = "五月";
				break;
			case "6":
				dateName = "六月";
				break;
			case "7":
				dateName = "七月";
				break;
			case "8":
				dateName = "八月";
				break;
			case "9":
				dateName = "九月";
				break;
			case "10":
				dateName = "十月";
				break;
			case "11":
				dateName = "十一月";
				break;
			case "12":
				dateName = "十二月";
				break;
			}
		} else if ("quarter".equals(dateType)) {
			switch (dateNum) {
			case "1":
				dateName = "一季度";
				break;
			case "2":
				dateName = "二季度";
				break;
			case "3":
				dateName = "三季度";
				break;
			case "4":
				dateName = "四季度";
				break;
			}
		} else {
			dateName = dateNum;
		}
		return dateName;

	}
}
