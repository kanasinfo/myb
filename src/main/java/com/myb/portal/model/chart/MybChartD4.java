package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

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
			Criteria ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));

			
			Aggregation aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
					group("$answers.optionValue").count().as("count"));
			AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			List<TagCount> tagCount = results.getMappedResults();
			StringBuffer legendStr = new StringBuffer("[");
			JSONObject jb = null;
			JSONArray ja = new JSONArray();
			for (int i = 0; i < tagCount.size(); i++) {
				TagCount t = tagCount.get(i);
				jb = new JSONObject();
				jb.put("name", t.get_id());
				legendStr.append("'"+t.get_id()+"',");
				jb.put("value", t.getCount());			
				ja.add(jb);
			}
		
			legendStr.substring(0, legendStr.length()-1);
			legendStr.append("]");
			JSONArray jaGroup = JSONArray.fromObject(questionGroup);
			JSONArray groupName = new JSONArray();
			JSONObject jbQuestion = null;
			for (int i = 0; i < jaGroup.size(); i++) {
				jbQuestion = jaGroup.getJSONObject(i);
				groupName.add( jbQuestion.getString("questionName"));
						
			}
			JSONArray jaYname= new JSONArray();
			jaYname.add("坐标");
			JSONArray yName = JSONObject.fromObject(dimension).getJSONArray("options");
			for (int i = 0; i < yName.size(); i++) {
				jaYname.add(yName.getJSONObject(i).get("optionName"));
			}
			
			
			/**
			 * No comments ;
			 * 
			 */
			StringBuffer comments = new StringBuffer("");
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis'},toolbox: {show : true,feature : {");
			option.append("mark : {show: true},dataView : {show: true, readOnly: false},magicType: {show: true, type: ['line', 'bar']},restore : {show: true},");
			option.append("saveAsImage : {show: true}}},calculable : true,legend: {data:"+jaYname+"},");
			option.append("xAxis : [{type : 'category',axisLabel: {rotate:80},data : "+groupName+"}],");
			option.append("yAxis : [{type : 'value',min :0,max:100,name : '百分比',axisLabel : {formatter: '{value} %'}},{type : 'value',min :0,max:100,show:false,name : '温度',axisLabel : {formatter: '{value} °C'}}],");
			option.append("series : [{name:'达标',type:'bar',data:[2.0, 4.9, 7.0, 23.2, 25.6]},");
			for (int i = 0; i < yName.size(); i++) {
				if(i!=yName.size()){
					option.append("{name:'"+yName.getJSONObject(i).get("optionName")+"',type:'line',yAxisIndex: 1,data:["+(2.0+(int)(Math.random()*20))+", "+(2.2+(int)(Math.random()*20))+", "+(3.3+(int)(Math.random()*20))+", "+(22.5+(int)(Math.random()*20))+", "+(22.3+(int)(Math.random()*20))+"]},");	
				}else if(i==yName.size()){
					option.append("{name:'"+yName.getJSONObject(i).get("optionName")+"',type:'line',yAxisIndex: 1,data:["+(2.0+(int)(Math.random()*20))+", "+(2.2+(int)(Math.random()*20))+", "+(3.3+(int)(Math.random()*20))+", "+(22.5+(int)(Math.random()*20))+", "+(22.3+(int)(Math.random()*20))+"]}");		
				}
					
			}
			
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
