package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartSERVICES extends MybChart{

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
			
			List<Criteria> criterias = new ArrayList<Criteria>();
			List<Criteria> criteriasPP = new ArrayList<Criteria>();			
			Criteria ct =null;
			JSONObject jb = JSONObject.fromObject(filter);
			JSONObject jbPeriod = JSONObject.fromObject(jb.getString("period"));
			String start_time = jbPeriod.getString("start_time");
			String end_time = jbPeriod.getString("end_time");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(new Date(end_time));
			rightNow.set(Calendar.DATE, 1);
			criterias.add(Criteria.where("createdDate").gte(new Date(start_time)).lte(new Date(end_time)));	
			criteriasPP.add(Criteria.where("createdDate").gte(new Date(start_time)).lte(new Date(end_time)));
			if("month".equals(jb.getString("cycle"))){				
				Date td = rightNow.getTime();
				criterias.add(Criteria.where("createdDate").gte(td));				
				rightNow.add(Calendar.MONTH,-1);
				criteriasPP.add(Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));
			}else if("quarter".equals(jb.getString("cycle"))){					
            	rightNow.set(Calendar.MONTH, 3*(rightNow.get(Calendar.MONTH)/3));
            	Date td = rightNow.getTime();
				criterias.add(Criteria.where("createdDate").gte(td));
				rightNow.add(Calendar.MONTH,-3);
				criteriasPP.add(Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));				
			}else if("year".equals(jb.getString("cycle"))){				
				rightNow.set(Calendar.MONTH, 0);				
				Date td = rightNow.getTime();
				criterias.add(Criteria.where("createdDate").gte(td));
				rightNow.add(Calendar.YEAR,-1);
				criteriasPP.add(Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));			
			}
			ct = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));					
			Aggregation aggregation ;
			AggregationResults<TagCount> results =null;
			List<TagCount> tagCount = null;
			

			JSONArray jaCurrent = new JSONArray();
			int totalCount = 0;
			int priorCount = 0;
			
			//Calculate total count
			 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					 unwind("$answers"),match(Criteria.where("answers.questionGroupId").is(groupId)),
					 group("$answers.questionName").count().as("count"));
			 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			 tagCount = results.getMappedResults();
			 if(tagCount.size()>0){
				 totalCount = tagCount.get(0).getCount();
				 for(int i=0;i<tagCount.size();i++){
						TagCount t = tagCount.get(i);
						jb = new JSONObject();
						jb.put("name", t.get_id());						
						jaCurrent.add(jb);
				 }
	
			 }
			
			if(totalCount>0){
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),unwind("$answers"),match(Criteria.where("answers.questionGroupId").is(groupId)),
						 match(Criteria.where("answers.optionValue").in("1")),group("$answers.questionName").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();				 
				 
				for (int i = 0; i < tagCount.size(); i++) {
					TagCount t = tagCount.get(i);
					for(int j =0; j<jaCurrent.size();j++){
						jb = jaCurrent.getJSONObject(j);
						if(jb.getString("name").equals(t.get_id())){									
							jb.put("value", t.getCount());
							jb.put("pct", t.getCount()*100/totalCount);							
							jaCurrent.remove(j);
							jaCurrent.add(jb);	
						}
					}					
				} 
				ct = new Criteria().andOperator(criteriasPP.toArray(new Criteria[criteriasPP.size()]));	
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
						 group("questionnaireId").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();
				 if(tagCount.size()>0){
					 priorCount = tagCount.get(0).getCount();
				 }
				
				if(totalCount>0){
					 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),unwind("$answers"),match(Criteria.where("answers.questionGroupId").is(groupId)),
							 match(Criteria.where("answers.optionValue").in("1")),group("$answers.questionName").count().as("count"));
					 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 tagCount = results.getMappedResults();				 				 
					for (int i = 0; i < tagCount.size(); i++) {
						TagCount t = tagCount.get(i);
						for(int j =0; j<jaCurrent.size();j++){
							jb = jaCurrent.getJSONObject(j);
							if(jb.getString("name").equals(t.get_id())){	
								int pct = (jb.get("pct")!=null)?jb.getInt("pct"):0;
								jb.put("priorValue", t.getCount());
								jb.put("priorPct", t.getCount()*100/priorCount);
								jb.put("trend",  pct - jb.getInt("priorPct"));
								jaCurrent.remove(j);
								jaCurrent.add(jb);								
							}
						}
					} 
				}
			}
			
			for(int j =0; j<jaCurrent.size();j++){
				jb = jaCurrent.getJSONObject(j);				
				if(!jb.containsKey("trend")){									
					jb.put("priorValue", 0);
					jb.put("priorPct", 0);
					jb.put("trend",1);
					jaCurrent.remove(j);
					jaCurrent.add(jb);								
				}
				System.out.println(jb.toString());
			}

			JSONObject rspjb = new JSONObject();
			rspjb.put("data", jaCurrent);
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
	
	private static boolean isNotNull(String v){
		if(v!=null&&!"".equals(v)&&!"null".equals(v)){
			return true;
		}else{
			return false;
		}
	}
}
