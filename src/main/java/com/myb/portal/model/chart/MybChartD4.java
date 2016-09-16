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

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartD4 extends MybChart{

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
			Map<String, List<Integer>> resCountMap = new HashMap<String, List<Integer>>();
			for (int i = 0; i < jaBackgrou.size(); i++) {
				jb = jaBackgrou.getJSONObject(i);
				resMap.put(jb.getString("optionId"), new ArrayList<Double>());
				resCountMap.put(jb.getString("optionId"), new ArrayList<Integer>());
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
					criterias.add(Criteria.where("answers.questionIdValue").in(jaTquestionId.getString(j)+"_true"));	
					criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(jaTquestionId.getString(j))),group("$answers.questionName").count().as("count"));
					 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 criterias.clear();
					 tagCount = results.getMappedResults();
					 criterias.add(Criteria.where("answers.questionIdValue").in(questionId+"_"+map.get(jaTBackgroupId.getString(i))));
						criterias.add(Criteria.where("answers.questionIdValue").in(jaTquestionId.getString(j)+"_true",jaTquestionId.getString(j)+"_false"));	
						criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(jaTquestionId.getString(j))),group("$answers.questionName").count().as("count"));
					newResults = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					newTagCount= newResults.getMappedResults();
					 if(tagCount.size()>0&&newTagCount.size()>0){	 
						 if(resMap.get(jaTBackgroupId.get(i))==null){
							 list = new ArrayList<Double>();
							 list.add(0d);
							 resMap.put(jaTBackgroupId.getString(i), list);
						 }else{
							 resMap.get(jaTBackgroupId.get(i)).add((double)tagCount.get(0).getCount()/(double)newTagCount.get(0).getCount()*100);	
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
			
			/**
			 * No comments ;
			 * 
			 */
			StringBuffer comments = new StringBuffer("");
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis'},toolbox: {show : true,feature : {");
			option.append("mark : {show: true},dataView : {show: true, readOnly: false},magicType: {show: true, type: ['line', 'bar']},restore : {show: true},");
			option.append("saveAsImage : {show: true}}},calculable : true,legend: {data:"+jaTBackgroup+"},");
			option.append("xAxis : [{type : 'category',axisLabel: {rotate:80},data : "+jaTquestion+"}],");
			option.append("yAxis : [{type : 'value',min :0,max:100,name : '百分比',axisLabel : {formatter: '{value} %'}},{type : 'value',min :0,max:100,show:false,name : '温度',axisLabel : {formatter: '{value} °C'}}],");
			option.append("series : [{name:'达标',type:'bar',data:[2.0, 4.9]},");
			int i = 0;
			for (Map.Entry<String, List<Double>> ent : resMap.entrySet()) {
				if(i!=resMap.size()){
					++i;
					option.append("{name:'"+map.get(ent.getKey())+"',type:'line',yAxisIndex: 1,data:"+JsonUtil.listToJson(ent.getValue())+"},");		
				}else{
					++i;
					option.append("{name:'"+map.get(ent.getKey())+"',type:'line',yAxisIndex: 1,data:"+JsonUtil.listToJson(ent.getValue())+"}");
				}
			}
//			for (int i = 0; i < yName.size(); i++) {
//				if(i!=yName.size()){
//					option.append("{name:'"+yName.getJSONObject(i).get("optionName")+"',type:'line',yAxisIndex: 1,data:["+(2.0+(int)(Math.random()*20))+", "+(2.2+(int)(Math.random()*20))+", "+(3.3+(int)(Math.random()*20))+", "+(22.5+(int)(Math.random()*20))+", "+(22.3+(int)(Math.random()*20))+"]},");	
//				}else if(i==yName.size()){
//					option.append("{name:'"+yName.getJSONObject(i).get("optionName")+"',type:'line',yAxisIndex: 1,data:["+(2.0+(int)(Math.random()*20))+", "+(2.2+(int)(Math.random()*20))+", "+(3.3+(int)(Math.random()*20))+", "+(22.5+(int)(Math.random()*20))+", "+(22.3+(int)(Math.random()*20))+"]}");		
//				}
//					
//			}
			
			option.append("]}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", questionName);
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
			rspjb.put("comments", comments.toString());
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
