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
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.chart.result.TwoDimnCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartP13 extends MybChart{

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
			String yName = JSONObject.fromObject(dimension).getString("dateType");

			List<Criteria> criterias =null;
			Criteria criteria = null;
			BasicDBList listAll = null;
			BasicDBList list = null;
			Aggregation aggregation = null;			
			Field[] fields = new Field[2];			
			Field x = Fields.field("x","$year");
			fields[0] = x;			
			Field y = Fields.field("y","$"+yName);
			fields[1] = y;			
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
					group(Fields.from(fields)).count().as("count"));
			AggregationResults<TwoDimnCount> results2= mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			listAll = (BasicDBList)results2.getRawResults().get("result");	

			criterias = new ArrayList<Criteria>();				
			criterias.add(Criteria.where("answers.questionIdValue").in(questionId+"_8",questionId+"_9",questionId+"_10"));
			criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),match(criteria),
					unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
					group(Fields.from(fields)).count().as("count"));
			
			AggregationResults<TwoDimnCount> results= mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			list = (BasicDBList)results.getRawResults().get("result");
			JSONObject jb = null;
			JSONArray ja = new JSONArray();
			JSONObject jbt = null;
			Integer tmpCnt = 0;
			Integer tmpTotalCnt = 0;
			JSONArray xName= new JSONArray();			
			JSONArray xValue = new JSONArray();			
			
				for(int i = 0; i < listAll.size(); i ++){  
		            BasicDBObject obj = (BasicDBObject)listAll.get(i);
		            String xStr = "";
		            String yStr = "";
		            tmpTotalCnt =(Integer)obj.get("count");
	            	jb = JSONObject.fromObject(obj.get("_id"));				    		
		    		xStr = jb.getString("x");
		    		yStr =  jb.getString("y");	
		    		for(int j = 0; j < list.size(); j ++ ){
		    			jb = JSONObject.fromObject(((BasicDBObject)list.get(j)).get("_id"));	
		    			if(xStr.equals(jb.getString("x")) && yStr.equals(jb.getString("y"))){
		    				tmpCnt = (Integer)((BasicDBObject)list.get(j)).get("count");
		    			};
		    		}
		    		xName.add(yStr);
		    		xValue.add((tmpCnt*100)/tmpTotalCnt);		    		
				}
						
			StringBuffer legendStr = new StringBuffer("[");			
			
			legendStr.substring(0, legendStr.length()-1);
			legendStr.append("]");

			

			StringBuffer comments = new StringBuffer("");
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis'},legend: {data:"+xValue+"},");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},");
			option.append("magicType : {show: true, type: ['line', 'bar']},restore : {show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,");
			option.append("xAxis : [{type : 'category',boundaryGap : false,data : "+xName+"}],");
			option.append("yAxis : [{type : 'value',min:0,max:100,axisLabel : {formatter: '{value} %'}}],");
			option.append("series : [{name:'',type:'line',data:"+xValue+",markPoint : {");
			option.append("data : [{type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]},");
			option.append("markLine : {data : [{type : 'average', name: '平均值'}]}}]}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "line");
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
