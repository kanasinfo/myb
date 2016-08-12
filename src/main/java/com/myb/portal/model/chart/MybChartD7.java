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

public class MybChartD7 extends MybChart{

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
			StringBuffer option =new StringBuffer("{tooltip : {trigger: 'axis',");
			option.append("toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},");
			option.append("magicType: {show: true, type: ['line', 'bar']},restore : {show: true},saveAsImage : {show: true}}},");
			option.append("calculable : true,legend: {data:['蒸发量','降水量','平均温度']},");
			option.append("xAxis : [{type : 'category',data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']}],");
			option.append("yAxis : [{type : 'value',name : '水量',axisLabel : {formatter: '{value} ml'}},");
			option.append("{type : 'value',name : '温度',axisLabel : {formatter: '{value} °C'}},");
			option.append("series : [{name:'蒸发量',type:'bar',data:[2.0, 4.9, 7.0, 23.2, 25.6, 76.7, 135.6, 162.2, 32.6, 20.0, 6.4, 3.3]},");
			option.append("{name:'降水量',type:'bar',data:[2.6, 5.9, 9.0, 26.4, 28.7, 70.7, 175.6, 182.2, 48.7, 18.8, 6.0, 2.3]},");
			option.append("{name:'平均温度',type:'line',yAxisIndex: 1,data:[2.0, 2.2, 3.3, 4.5, 6.3, 10.2, 20.3, 23.4, 23.0, 16.5, 12.0, 6.2]}]}");
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
