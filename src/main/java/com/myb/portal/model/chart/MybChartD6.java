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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartD6 extends MybChart {

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

			/**
			 * No comments ;
			 * 
			 */
			StringBuffer comments = new StringBuffer("");
			StringBuffer chartLegend = new StringBuffer("");
			StringBuffer option =new StringBuffer("{title : {text: '未来一周气温变化',subtext: '纯属虚构'},");
			option.append("tooltip : {trigger: 'axis'},legend: {data:['最高气温','最低气温']},");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true, type: ['line', 'bar']},");
			option.append("restore : {show: true},saveAsImage : {show: true}}},calculable : true,");
			option.append("xAxis : [{type : 'category',boundaryGap : false,data : ['周一','周二','周三','周四','周五','周六','周日']}],");
			option.append("yAxis : [{type : 'value',axisLabel : {formatter: '{value} °C'}}],");
			option.append("series : [{name:'最高气温',type:'line',data:[11, 11, 15, 13, 12, 13, 10],markPoint : {");
			option.append("data : [{type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]},");
			option.append("markLine : {data : [{type : 'average', name: '平均值'}]}},");
			option.append("{name:'最低气温',type:'line',data:[1, -2, 2, 5, 3, 2, 0],markPoint : {");
			option.append("data : [{name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}]},");
			option.append("markLine : {data : [{type : 'average', name : '平均值'}]}}]}");
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
