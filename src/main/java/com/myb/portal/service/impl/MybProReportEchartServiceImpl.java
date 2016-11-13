package com.myb.portal.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.myb.portal.model.chart.MybChart;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.service.MybProReportEchartService;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MybProReportEchartServiceImpl implements MybProReportEchartService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public AjaxReq getData(MybChart chart) {
			return chart.getData(mongoTemplate);
	}
	/**
	 * getVoiceOfCustomer TODO(查询顾客之声) 
	 * @author wangzx
	 * @param questionnaireId
	 * @param questionId
	 * @return
	 */
	public AjaxReq getVoiceOfCustomer(String questionnaireId){
		AjaxReq ar = new AjaxReq();
		JSONArray returnja = new JSONArray();
		JSONObject returnjb = null;
		Aggregation aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
				unwind("$answers"), match(Criteria.where("answers.VoiceOfCustomers").is("VoiceOfCustomers")));
		AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
		JSONObject jb = JSONObject.fromObject(results.getRawResults());
		JSONArray  ja = jb.getJSONArray("result");
		JSONObject jbdata = null;
		for (int i = 0; i < ja.size(); i++) {
			jbdata = ja.getJSONObject(i);
			returnjb = new JSONObject();
			returnjb.put("picImage", "头像");
			returnjb.put("voiceOfComstomer",jbdata.getJSONObject("answers").get("optionValue"));
			returnjb.put("createTime", jbdata.get("createdDate")+"");
			returnjb.put("storeName", jbdata.get("storeName")+"");
			returnja.add(returnjb);
		}
		ar.setSuccess(true);
		ar.setData(returnja.toString());
		return ar;
	}
}
