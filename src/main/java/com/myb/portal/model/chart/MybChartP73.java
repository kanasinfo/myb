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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartP73 extends MybChart {

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
			Criteria ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			Aggregation aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
					group("$answers.optionValue").count().as("count"));
			AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			List<TagCount> tagCount = results.getMappedResults();
			//查询出早中晚用户总量和相关数据
			Map<String, String> mapQuestion = new HashMap<String,String>();
			JSONObject jbParen = JSONObject.fromObject(dimension);
			JSONObject jb = null;
			JSONArray jalegend = new JSONArray();
			for (int i =0;i<jbParen.getJSONArray("options").size();i++) {
				jb = jbParen.getJSONArray("options").getJSONObject(i);
				jalegend.add(jb.getString("optionName"));
				mapQuestion.put(jb.getString("optionId"), jb.getString("optionName"));
			}
			
			JSONArray categoryJa= new JSONArray();
			Map<String, Integer> mapQuestionCount = new HashMap<String, Integer>(); 
			for (int i = 0; i < tagCount.size(); i++) {
				TagCount t = tagCount.get(i);
				categoryJa.add(t.get_id());
				mapQuestionCount.put(t.get_id(), t.getCount());
//				jb.put("value", t.getCount());			
			}
			List<Criteria> criterias =null;
			Criteria criteria = null;
			JSONArray jacount = null;
			Map<String, JSONArray> mapJa = new HashMap<String, JSONArray>();
			for (Map.Entry<String, Integer> entry : mapQuestionCount.entrySet()) {
				for (Map.Entry<String, String> ent : mapQuestion.entrySet()) {
					criterias =new ArrayList<Criteria>();
					criterias.add(Criteria.where("answers.questionIdValue").in(jbParen.getString("questionId")+"_"+ent.getValue()));
					criterias.add(Criteria.where("answers.questionIdValue").in(questionId+"_"+entry.getKey()));
					criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							match(criteria),match(ct),unwind("$answers"),
							match(Criteria.where("answers.questionId").is(questionId)),
							group("$answers.questionName").count().as("count"));
					results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 criterias.clear();
					 tagCount = results.getMappedResults();
					 if(tagCount.size()>0){
						 if(tagCount.get(0).getCount()==0){
							 if(mapJa.get(ent.getValue())==null){
								 jacount = new JSONArray();
								 jacount.add(0);
								 mapJa.put(ent.getValue(), jacount);	 
							 }else{
								 mapJa.get(ent.getValue()).add(0);
							 }
						 }else{
							 if(mapJa.get(ent.getValue())==null){
								 jacount = new JSONArray();
								 jacount.add(tagCount.get(0).getCount()/mapQuestionCount.get(entry.getKey()));
								 mapJa.put(ent.getValue(), jacount);	 
							 }else{
								 mapJa.get(ent.getValue()).add(tagCount.get(0).getCount()/mapQuestionCount.get(entry.getKey()));
							 }
						 }
					 }else{
						 
						 if(mapJa.get(ent.getValue())==null){
							 jacount = new JSONArray();
							 jacount.add(0);
							 mapJa.put(ent.getValue(), jacount);	 
						 }else{
							 mapJa.get(ent.getValue()).add(0);
						 }
						 
					 }
				}
				criterias = new ArrayList<Criteria>();
			}
			
			
			/**
			 * No comments ;
			 * 
			 */
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis',axisPointer : {type : 'shadow'}},");
			option.append("legend: {data:"+jalegend+"},");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},restore : ");
			option.append("{show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,");
			option.append("xAxis : [{type : 'value'}],");
			option.append("yAxis : [{type : 'category',data : "+categoryJa+"}],");
			option.append("series : [");
			int ii = 0;
			for (Map.Entry<String, JSONArray> j :mapJa.entrySet()) {
				++ii;
				if(mapJa.size()==ii){
					option.append("{name:'"+j.getKey()+"',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:"+j.getValue()+"}");		
				}else{
					option.append("{name:'"+j.getKey()+"',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:"+j.getValue()+"},");
				}
			}
			option.append("]}");
			JSONObject rspjb = new JSONObject();	
			rspjb.put("type", "pie");
			rspjb.put("title", questionName);
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
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
