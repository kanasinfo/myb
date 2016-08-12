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

public class MybChartP11 extends MybChart {

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
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis',axisPointer : {type : 'shadow'}},");
			option.append("legend: {data:['直接访问', '邮件营销','联盟广告','视频广告','搜索引擎']},");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},restore : ");
			option.append("{show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,");
			option.append("xAxis : [{type : 'value'}],");
			option.append("yAxis : [{type : 'category',data : ['周一','周二','周三','周四','周五','周六','周日']}],");
			option.append("series : [{name:'直接访问',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:[320, 302, 301, 334, 390, 330, 320]},");
			option.append("{name:'邮件营销',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:[120, 132, 101, 134, 90, 230, 210]},");
			option.append("{name:'联盟广告',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:[220, 182, 191, 234, 290, 330, 310]},");
			option.append("{name:'视频广告',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:[150, 212, 201, 154, 190, 330, 410]},");
			option.append("{name:'搜索引擎',type:'bar',stack: '总量',itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},data:[820, 832, 901, 934, 1290, 1330, 1320]}]}");
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
