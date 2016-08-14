package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.BasicDBList;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.model.mongodb.ReleaseQuestionVo;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartSUMMARYHEALTH extends MybChart{

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
			List<Map<String,Object>> joTrends = new ArrayList<Map<String,Object>>();
			Map<String,Object> trendMap = null;
			JSONObject criteriaTrend = null;
			Criteria ct =null;
			Criteria loyalCt =null;
			Criteria lossCt =null;
			JSONObject jb = JSONObject.fromObject(filter);
			JSONObject jbPeriod = JSONObject.fromObject(jb.getString("period"));
			String start_time = jbPeriod.getString("start_time");
			String end_time = jbPeriod.getString("end_time");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Calendar rightNow = Calendar.getInstance();
			rightNow.setTime(new Date(end_time));
			rightNow.set(Calendar.DATE, 1);
			criterias.add(Criteria.where("createdDate").gte(new Date(start_time)).lte(new Date(end_time)));	
			if("month".equals(jb.getString("cycle"))){				
				Date td = rightNow.getTime();
				trendMap = new HashMap(); 
				trendMap.put("time", sdf.format(td).substring(0, 7));
				trendMap.put("sort", 0);
				trendMap.put("criteria", Criteria.where("createdDate").gte(td));				
				joTrends.add(trendMap);
				criterias.add(Criteria.where("createdDate").gte(td));
				
				for(int i=1;i<5;i++){
					td = rightNow.getTime();
					rightNow.add(Calendar.MONTH,-1);
					criteriasPP.add(Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));	
					trendMap = new HashMap(); 
					trendMap.put("time", sdf.format(rightNow.getTime()).substring(0, 7));
					trendMap.put("sort", i);
					trendMap.put("criteria", Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));				
					joTrends.add(trendMap);
				}
			}else if("quarter".equals(jb.getString("cycle"))){					
            	rightNow.set(Calendar.MONTH, 3*(rightNow.get(Calendar.MONTH)/3));
            	Date td = rightNow.getTime();
            	trendMap = new HashMap(); 
            	trendMap.put("time", sdf.format(td).substring(0, 7));
            	trendMap.put("sort", 0);
            	trendMap.put("criteria", Criteria.where("createdDate").gte(td));				
				joTrends.add(trendMap);
				criterias.add(Criteria.where("createdDate").gte(td));
				for(int i=1;i<5;i++){
					td = rightNow.getTime();
					rightNow.add(Calendar.MONTH,-3);
					trendMap = new HashMap(); 
					trendMap.put("time", sdf.format(rightNow.getTime()).substring(0, 7));
					trendMap.put("sort", i);
					trendMap.put("criteria", Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));				
					joTrends.add(trendMap);
				}
								
			}else if("year".equals(jb.getString("cycle"))){				
				rightNow.set(Calendar.MONTH, 0);				
				Date td = rightNow.getTime();
				trendMap = new HashMap(); 
				trendMap.put("time", sdf.format(td).substring(0, 7));
				trendMap.put("sort", 0);
				trendMap.put("criteria", Criteria.where("createdDate").gte(td));	
				joTrends.add(trendMap);
				criterias.add(Criteria.where("createdDate").gte(td));
				for(int i=1;i<5;i++){
					td = rightNow.getTime();
					rightNow.add(Calendar.YEAR,-1);
					trendMap = new HashMap(); 
					trendMap.put("time", sdf.format(rightNow.getTime()).substring(0, 7));
					trendMap.put("sort", i);
					trendMap.put("criteria", Criteria.where("createdDate").gte(rightNow.getTime()).lt(td));				
					joTrends.add(trendMap);
				}
			}
			
			List<Criteria> loyalCriterias = new ArrayList<Criteria>();
			List<Criteria> lossCriterias = new ArrayList<Criteria>();
			Query query = new Query();
			Criteria criterg = Criteria.where("id").is(questionnaireId).and("questions.businessType").in("qustRepurchase","qustRecommendation");
			query.addCriteria(criterg);
			List<String> qIds = new ArrayList<String>();
			//根据相关type查询questionId
			ReleaseQuestionVo listId = mongoTemplate.findOne(query, ReleaseQuestionVo.class,"release_qustnnr");
			//查询忠诚客户(8-10)
			for (QuestionsVo q : listId.getQuestions()) {
				if(q.getBusinessType() != null && ("qustRepurchase".equals(q.getBusinessType())||"qustRecommendation".equals(q.getBusinessType()))){
					qIds.add(q.getQuestionId());
					loyalCriterias.add(Criteria.where("answers.questionIdValue").in(q.getQuestionId()+"_8",q.getQuestionId()+"_9",q.getQuestionId()+"_10"));
					lossCriterias.add(Criteria.where("answers.questionIdValue").in(q.getQuestionId()+"_1",q.getQuestionId()+"_2",q.getQuestionId()+"_3",q.getQuestionId()+"_4",q.getQuestionId()+"_5"));
				}
			}
			
			ct = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			loyalCt = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			lossCt = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			Aggregation aggregation ;
			AggregationResults<TagCount> results =null;
			List<TagCount> tagCount = null;
			int totalCount = 0;
			int loyal = 0;
			int loss = 0;
			int instable = 0;
			 double healthy = 0;
			 double loyalCalculation = 0;
			 double lossCalculation = 0;
			 double instableCalculation = 0;			
			//Calculate total count
			 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					 group("$questionnaireId").count().as("count"));
			 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			 tagCount = results.getMappedResults();
			 if(tagCount.size()>0){
				 totalCount = tagCount.get(0).getCount();
			 }
			if(totalCount>0){
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
						 match(ct),unwind("$answers"),
						 match(loyalCt),group("$questionnaireId").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();				 
				 TagCount t;
				for (int i = 0; i < tagCount.size(); i++) {
					t = tagCount.get(i);
					loyal = t.getCount();
				}
				
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
						 match(ct),unwind("$answers"),
						 match(lossCt),group("$questionnaireId").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();				 
				for (int i = 0; i < tagCount.size(); i++) {
					t = tagCount.get(i);
					loss = t.getCount();
				} 

				//计算忠诚客户占比
				 if(loyal!=0d){
					  loyalCalculation = (loyal/totalCount)*100;
				 }
				//计算流失客户占比
				 if(loss!=0d){
					 lossCalculation = (loss/totalCount)*100;	 
				 }
//				 计算不稳定顾客占比
				 if(instable!=0d){
					 instableCalculation = ((totalCount-loyal-loss)/totalCount)*100;	 
				 }
				 
				 healthy = (1.5*loyalCalculation+0.8*instableCalculation-0.5*lossCalculation);	
				
			}
			
			//For last 5 periods trends
			int historyTotalCount = 0;
			StringBuffer legendStr = new StringBuffer("[");
			JSONArray jaData = new JSONArray();
			StringBuffer dataStr = new StringBuffer("[");
			double healthTmp = 0;
			
			for(int i=(joTrends.size()-1);i>=0;i--){
				trendMap = joTrends.get(i);
				Criteria ca = (Criteria)trendMap.get("criteria");
				 ct = Criteria.where("createdDate").gte(new Date(start_time)).lte(new Date(end_time));
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
						 match(ca),
						 group("$questionnaireId").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();
				 if(tagCount.size()>0){
					 historyTotalCount = tagCount.get(0).getCount();
					 legendStr.append("'"+trendMap.get("time")+"',");
				 }
				 BasicDBList list = null;
				 double result = 0;
				 double resultLoss = 0;
				if(historyTotalCount>0){			
					 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							 match(ct),
							 match(ca),
							 unwind("$answers"),
							 match(loyalCt),group("$questionnaireId").count().as("count"));
					 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 tagCount = results.getMappedResults();				 
					if(tagCount.size()>0) {
						result = tagCount.get(0).getCount()*100/historyTotalCount;
					}					
					aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),
							 match(ct),
							 match(ca),
							 unwind("$answers"),
							 match(lossCt),group("$questionnaireId").count().as("count"));
					 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
					 tagCount = results.getMappedResults();				 
					if(tagCount.size()>0) {
						resultLoss = tagCount.get(0).getCount()*100/historyTotalCount;
					} 	
					healthTmp = 100*(1.5*result+0.8*(historyTotalCount-result-resultLoss)-0.5*resultLoss)/historyTotalCount;	
					jaData.add(healthTmp);
				}
			}
			
			legendStr = legendStr.append("]");

			if(totalCount > 0){
			Double satifiedPct =  healthy;
			Double normPct = new Double(45);
			String starVal = "20";
			String alertColor = "alertred";
			if(satifiedPct >=90){
				starVal = "100";
			}else if(satifiedPct >= 75 && satifiedPct < 90){
				starVal = "80";
			}else if(satifiedPct >= 60 && satifiedPct < 75){
				starVal = "50";
			}else if(satifiedPct >= 40 && satifiedPct < 60){
				starVal = "40";
			}else if(satifiedPct < 40){
				starVal = "20";
			}
			if((satifiedPct - normPct) >=5){
				alertColor = "sumalertgreen";
			}else if((satifiedPct - normPct) >= 3){
				alertColor = "sumalertgreen";
			}else if((satifiedPct - normPct) > -3){
				alertColor = "sumalertyellow";				
			}else if((satifiedPct - normPct) > -5){
				alertColor = "sumalertred";
			}else{
				alertColor = "sumalertred";
			}


			StringBuffer chartLegend = new StringBuffer("<div style='margin-top: 40px;'>");			
			chartLegend.append("<span style='float: left;font-size: 12px;font-weight: bold;color:#6f6f6f'>健康指数</span><span class='"
					+ alertColor
					+ "'></span><br><br>")
			.append("<div class='legenddetailsnumber' style='float:left'><div class='star-rating rating-active'><div class='rating-container rating-gly-star' data-content=''><div class='rating-stars satisfiedstar' data-content='' style='width: ")
			.append(starVal)
			.append("%;'></div></div></div> </div></div>");
			
			StringBuffer option =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c}\"},")
					.append("legend: {show:false}, ")
					.append("series : [{name:'健康指数',type:'pie',radius : ['67%', '70%'],startAngle:90,clockWise:true,avoidLabelOverlap: false,color: ['#78973e', '#eaeaea'],center: ['45%', '50%'],")
					.append("label: {normal: {show: true,position: 'center',formatter: '{c}',textStyle: {color: '#78973e',fontStyle: 'normal',fontWeight: 'normal',fontFamily: 'sans-serif',fontSize: 26}},emphasis: {show: true,textStyle: {fontSize: '30',fontWeight: 'bold'}}},")
					.append("data: ")
					.append("[{value:"
							+ healthy
							+ ", name:'健康指数'},{value:"
							+ (100-healthy)
							+ ", name:'',itemStyle:{  normal : {label : {show : false},labelLine : {show : false}}}}]")
					.append("}]}");
			
			StringBuffer optionTrend =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("tooltip : { trigger: 'axis',axisPointer: {type: 'shadow'}},")
					.append("legend: {show:false}, ")
					.append("yAxis: {type: 'value',splitLine: {show: false},axisLine: {show:false},axisLabel: {show:false},axisTick: {show:false},boundaryGap: [0, 0.01]},")
					.append("xAxis: {type: 'category',splitLine: {show: false},data: ")
					.append(legendStr)
					.append("},")
					.append("series : [{name:'健康指数',type:'bar',itemStyle:{normal:{color:'#78973e'}},")
					.append("data: ")
					.append(jaData.toString())				
					.append("}]}");
			
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", "健康指数");
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
			rspjb.put("optionTrend", optionTrend.toString());
			rspjb.put("nodata", false);
			aReq.setSuccess(true);
			aReq.setData(rspjb);
			}else{
				JSONObject rspjb = new JSONObject();
				rspjb.put("type", "pie");
				rspjb.put("title", "总体满意度在此条件下没有数据！");
				rspjb.put("nodata", true);
				rspjb.put("nodatacomment", "此条件下没有数据！");
				aReq.setSuccess(true);
				aReq.setData(rspjb);
			}
			
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
