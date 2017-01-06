package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartD3 extends MybChart{

	@Override
	public AjaxReq getData(MongoTemplate mongoTemplate) {
		/**
		 * TO-ADD: title, name, norm,
		 * 
		 * */
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
			Criteria ct =null;
			if(fdmp.get("filterOnly") != null){
				ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			}
			
			Criteria criteria = null;
			Aggregation aggregation ;
			List<Criteria> criterias =null;
			MybChartUtils.packDimensionsFilter("", filter, fdmp);
			//封装查询条件
			JSONArray jaBackgrou = JSONObject.fromObject(dimension).getJSONArray("options");
			Map<String, String> map = new HashMap<String, String>();
			JSONArray jaTBackgroup = new JSONArray();
			JSONArray jaTBackgroupId = new JSONArray();
			JSONObject jb = null;
			Map<String, List<Double>> resMap = new HashMap<String, List<Double>>();
			Map<String, Double> commentMap = new HashMap<String, Double>();
			for (int i = 0; i < jaBackgrou.size(); i++) {
				jb = jaBackgrou.getJSONObject(i);
				jaTBackgroup.add(jb.getString("optionName"));
				jaTBackgroupId.add(jb.getString("optionId"));
				map.put(jb.getString("optionId"), jb.getString("optionName"));
			}
			String questionId = JSONObject.fromObject(dimension).getString("questionId");
			//封装细向纬度
			JSONArray jaQuestion = JSONArray.fromObject(questionGroup);
			JSONArray jaTquestion = new JSONArray();
			JSONArray jaTquestionId = new JSONArray();
			Map<String, String> mapQuestion = new HashMap<String, String>();
			for (int i = 0; i < jaQuestion.size(); i++) {
				jb = jaQuestion.getJSONObject(i);
				jaTquestion.add(jb.getString("questionName"));
				jaTquestionId.add(jb.getString("questionId"));
				mapQuestion.put(jb.getString("questionId"), jb.getString("questionName"));
			}
			AggregationResults<TagCount> newResults =null;
			List<TagCount> newTagCount = null;
			AggregationResults<TagCount> results =null;
			List<TagCount> tagCount = null;
			//开始查询数据
			List<Double> list = null;
			for (int i = 0; i <jaTBackgroup.size() ; i++) {
				for (int j = 0; j < jaTquestion.size(); j++) {
					criterias = new ArrayList<Criteria>();
					criterias.add(Criteria.where("answers.questionIdValue").in(questionId+"_"+map.get(jaTBackgroupId.getString(i))));
					criterias.add(Criteria.where("answers.questionIdValue").in(jaTquestionId.getString(j)+"_8",jaTquestionId.getString(j)+"_9",jaTquestionId.getString(j)+"_10"));	
					criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(jaTquestionId.getString(j))),group("$answers.questionName").count().as("count"));
					 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 criterias.clear();
					 tagCount = results.getMappedResults();
					 criterias.add(Criteria.where("answers.questionIdValue").in(questionId+"_"+map.get(jaTBackgroupId.getString(i))));
						criterias.add(Criteria.where("answers.questionIdValue").in(jaTquestionId.getString(j)+"_1",jaTquestionId.getString(j)+"_2",jaTquestionId.getString(j)+"_3",
								jaTquestionId.getString(j)+"_4",jaTquestionId.getString(j)+"_5",jaTquestionId.getString(j)+"_6",jaTquestionId.getString(j)+"_7",
								jaTquestionId.getString(j)+"_8",jaTquestionId.getString(j)+"_9",jaTquestionId.getString(j)+"_10"));	
						criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(jaTquestionId.getString(j))),group("$answers.questionName").count().as("count").max("$answers.optionValue").as("max").min("$answers.optionValue").as("min"));
					newResults = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					newTagCount= newResults.getMappedResults();
					 if(tagCount.size()>0&&newTagCount.size()>0){	 
						 if(resMap.get(jaTBackgroupId.get(i))==null){
							 list = new ArrayList<Double>();
							 list.add(0d);
							 resMap.put(jaTBackgroupId.getString(i), list);
							 commentMap.put(jaTBackgroupId.getString(i), 0d);
						 }else{
							 resMap.get(jaTBackgroupId.get(i)).add((double)tagCount.get(0).getCount()/(double)newTagCount.get(0).getCount()*100);
							 commentMap.put(jaTBackgroupId.getString(i), Double.valueOf(newTagCount.get(0).getMax()-newTagCount.get(0).getMin()));
						 }
					 }else{
						 if(resMap.get(jaTBackgroupId.get(i))==null){
							 list = new ArrayList<Double>();
							 list.add(0d);
							 resMap.put(jaTBackgroupId.getString(i), list);
						 }else{
							 resMap.get(jaTBackgroupId.get(i)).add(0d);	 
						 }
					 }
					 criterias.clear();
				}
			}
			
			//获取每个指标的最大值和最小值
			int count = commentMap.size();
			int ltFive = 0;
			int geFive = 0;
			StringBuffer sbCommont = new StringBuffer();
			String name = JSONObject.fromObject(dimension).getString("questionValue");;
			sbCommont.append("比较不同").append(name).append("客户的满意度评价,发现");
			Map<Double, String> scoreMap = new TreeMap<Double, String>();
			for (Map.Entry<String, Double> ent : commentMap.entrySet()) {
				if(ent.getValue()>=5){
					++ltFive;
					if(ltFive==count){
						scoreMap.put(ent.getValue(), map.get(ent.getKey()));
					}else if(ltFive>count/2){
						scoreMap.put(ent.getValue(), map.get(ent.getKey()));
					}
				}else if(ent.getValue()<5){
					++geFive;
					if(geFive==count){
						scoreMap.put(ent.getValue(), map.get(ent.getKey()));
					}else if(geFive<count/2){
						scoreMap.put(ent.getValue(), map.get(ent.getKey()));
					}
				}
			}
			JSONArray jaQuestionName=  new JSONArray();
			if(scoreMap.size()>=3){
				int i = 0;
				for (Map.Entry<Double, String> object : scoreMap.entrySet()) {
					if(i>=3){
						++i;
						jaQuestionName.add(object.getValue());	
					}
				}
				
			}else{
				for (Map.Entry<Double, String> object : scoreMap.entrySet()) {
						jaQuestionName.add(object.getValue());	
				}
			}
			for (Map.Entry<String, Double> ent : commentMap.entrySet()) {
				if(ent.getValue()>=5){
					++ltFive;
					if(ltFive==count){
						sbCommont.append("各服务环节在各").append(name).append("的表现差异非常大;");
						sbCommont.append("其中，差异最大的指标是:").append(jaQuestionName);
					}else if(ltFive>count/2){
						sbCommont.append("各服务环节在各").append(name).append("的表现差异比较大;");
						sbCommont.append("其中，差异最大的指标是:").append(jaQuestionName);
					}
				}else if(ent.getValue()<5){
					++geFive;
					if(geFive==count){
						sbCommont.append("各服务环节在各").append(name).append("的表现无显著差异;");
					}else if(geFive<count/2){
						sbCommont.append("各服务环节在各").append(name).append("的表现差异比较小;");
						sbCommont.append("其中，差异最大的指标是:").append(jaQuestionName);
					}
				}
			}
			
			/**
			 * No comments ;
			 * 
			 */
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis',axisPointer : {type : 'shadow'}},");
			option.append("legend: {data:"+jaTBackgroup+"},");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},restore : ");
			option.append("{show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,");
			option.append("xAxis : [{type : 'value'}],");
			option.append("yAxis : [{type : 'category',data : "+jaTquestion+"}],");
			option.append("series : [");
			int i = 0;
			for (Map.Entry<String, List<Double>> ent : resMap.entrySet()) {
				if(i!=resMap.size()){
					++i;
					option.append("{name:'"+map.get(ent.getKey())+"',type:'line',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:"+JsonUtil.listToJson(ent.getValue())+"},");		
				}else{
					++i;
					option.append("{name:'"+map.get(ent.getKey())+"',type:'line',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:"+JsonUtil.listToJson(ent.getValue())+"}");
				}
			}
			option.append("]}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", questionName);
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
			rspjb.put("comments", sbCommont.toString());
			aReq.setSuccess(true);
			aReq.setData(rspjb);
		} catch (Exception e){
			e.printStackTrace();
			aReq.setSuccess(false);
			aReq.setMessage("查询失败");
			return aReq;
		}
		return aReq;
	}

}
