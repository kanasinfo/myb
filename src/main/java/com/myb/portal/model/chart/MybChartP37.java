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
import org.springframework.data.mongodb.core.query.Query;

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.model.mongodb.ReleaseQuestionVo;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartP37 extends MybChart{

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
			Criteria criteria = null;
			AggregationResults<TagCount> results =null;
			List<TagCount> tagCount = null;
			Aggregation aggregation ;
			List<Criteria> criterias = new ArrayList<Criteria>();
			//稳定客户
			double loyal = 0d;
			//流失客户
			double loss = 0d;
			//不稳定客户
			double instable = 0d;
			Query query = new Query();
			Criteria criterg = Criteria.where("qustnnrId").is(questionnaireId);
			query.addCriteria(criterg);
			//根据相关type查询questionId
			ReleaseQuestionVo listId = mongoTemplate.findOne(query, ReleaseQuestionVo.class);
			//查询忠诚客户(8-10)
			for (QuestionsVo q : listId.getQuestions()) {
				if(q.getBusinessType()!=null && !"".equals(q.getBusinessType()) && 
						(q.getBusinessType().equals("qustRepurchase")||q.getBusinessType().equals("qustRecommendation"))){
					criterias.add(Criteria.where("answers.questionIdValue").in(q.getQuestionId()+"_8",q.getQuestionId()+"_9",q.getQuestionId()+"_10"));	
				}			
			}
			
			
			Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();	
			if(filter == null || "".equals(filter) || "\"\"".equals(filter) ){
				setFilter("{'value' : '{}','period' : '{\"start_time\":\"2016/01/01\",\"end_time\":\"2016/06/01\",}','store' : '{\"store_type\":\"\",\"store_id\":[\"\"],\"store_labels\":[\"\"]}'}");
			}
			MybChartUtils.packDimensionsFilter(dimension, filter, fdmp);	
			Criteria ct =null;
			if(fdmp.get("filterOnly") != null){
				ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			}
			 criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			 aggregation = newAggregation(match(criteria),match(ct),match(Criteria.where("questionnaireId").is(questionnaireId)),group("$questionnaireId").count().as("count"));
			 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			 tagCount = results.getMappedResults();
			 for (TagCount t : tagCount) {
				loyal = t.getCount();
			}
			 
			 //查询流失客户(1-6)
			criterias.clear();
			for (QuestionsVo q : listId.getQuestions()) {
				if(q.getBusinessType()!=null && !"".equals(q.getBusinessType()) && (q.getBusinessType().equals("qustRepurchase")||q.getBusinessType().equals("qustRecommendation"))){
					criterias.add(Criteria.where("answers.questionIdValue").in(q.getQuestionId()+"_1",q.getQuestionId()+"_2",q.getQuestionId()+"_3",q.getQuestionId()+"_4",q.getQuestionId()+"_5"));
				}
			}
			 criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
			 aggregation = newAggregation(match(criteria),match(ct),match(Criteria.where("questionnaireId").is(questionnaireId)),group("$questionnaireId").count().as("count"));
			 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			 tagCount = results.getMappedResults();
			 for (TagCount t : tagCount) {
				loss = t.getCount();
			}
			//获取所有模板
			 criterias.clear();
			 aggregation = newAggregation(match(ct),match(Criteria.where("questionnaireId").is(questionnaireId)),group("$questionnaireId").count().as("count"));
			 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			 tagCount = results.getMappedResults();
			 double countAnser = 0d;
			 for (TagCount t : tagCount) {
				 countAnser = t.getCount();
			}
//			 Query queryAnswer = new Query();
//			 Criteria critergAnswers = Criteria.where("questionnaireId").is(questionnaireId);
//			 queryAnswer.addCriteria(critergAnswers);
//			 double countAnser = mongoTemplate.count(queryAnswer, "answer");
			//查询不稳定客户(7)
			 instable = countAnser - loyal - loss;
			 
			//计算当前问题平均值
			 JSONArray ja = new JSONArray();
			 JSONObject jb = null;
			 jb = new JSONObject();
			 jb.put("name", "忠诚顾客");
			 jb.put("value", loyal);
			 ja.add(jb);
			 jb = new JSONObject();
			 jb.put("name", "流失顾客");
			 jb.put("value", loss);
			 ja.add(jb);
			 jb = new JSONObject();
			 jb.put("name", "不稳定顾客");
			 jb.put("value", instable);
			 ja.add(jb);
			 
			/**
			 * 
			 * 1、满意顾客比例=“8-10分”顾客占比
			 * 2、鼠标晃动右侧数字时显示“与行业平均值相比处于领先/落后/相当水平”（仅限标准问卷中的指标，标准参照总体满意度）
			 * 3、鼠标晃动图表中色块时，显示对应的百分比数字。
			 * 4、折线表示 满意顾客占比=“8-10分”顾客占比
			 * 5、当指标数量小于3个时，不显示Comments。当指标数量为3个时，n=1；指标数量为4-5个时，n=2；指标数量大于或等于6个时，n=3.
			 * 
			 * Comments:
			 * 1、从顾客对各项服务环节的评价来看， <插入判别描述1>；
			 * 2、相对来讲， <插入满意顾客占比最高的n项指标>满意度评分较高， <插入满意顾客占比最低的n项指标>满意度评分较低。
			 * 
			 * 判别条件:判别描述1
			 * 每个指标的满意顾客比例均≥80: 本店的各服务细节表现优秀，各项指标均达到了高满意度评价（80分及以上）
			 * 半数及以上指标的满意顾客比例≥80:超过半数的服务环节达到了高满意度评价（80分及以上）
			 * 超过半数指标的满意顾客比例＜80:超过半数的服务环节未能达到高满意度评价（80分及以上）
			 * 每项指标的满意顾客比例均＜80:本店的各项服务环节均未达到高满意度评价（80分以上）
			 * 
			 * 
			 * 菜品口味，
			 * 
			 */
			 double loyalCalculation = 0;
			 double lossCalculation = 0;
			 double instableCalculation = 0;
			//计算忠诚客户占比
			 if(loyal!=0d){
				  loyalCalculation = (loyal/(loyal+loss+instable))*100;
			 }
			 
			//计算流失客户占比
			 if(loss!=0d){
				 lossCalculation = (loss/(loyal+loss+instable))*100;	 
			 }
			 
//			 计算不稳定顾客占比
			 if(instable!=0d){
				 instableCalculation = (instable/(loyal+loss+instable))*100;	 
			 }
			 
			 double healthy = (1.5*loyalCalculation+0.8*instableCalculation-0.5*lossCalculation);
			 String starVal = "";
			 if(healthy>=100){
				 starVal = "100";
			 }else if(healthy<100&&healthy>=80){
				 starVal = "80";
			 }else if(healthy<80&&healthy>=60){
				 starVal = "50";
			 }else if(healthy<60&&healthy>=40){
				 starVal = "40";
			 }else if(healthy<40){
				 starVal = "20";
			 }
			String alertColor = "alertred";
			//描述1
			String gapDesc = "显著落后于行业平均水平";
			if((healthy - normInputValue) >=5){
				gapDesc = "显著领先于行业平均水平";
				alertColor = "alertgreen";
			}else if((healthy - normInputValue) >= 3){
				gapDesc = "略微领先于行业平均水平";
				alertColor = "alertgreen";
			}else if((healthy - normInputValue) > -3){
				gapDesc = "属于行业一般水平";
				alertColor = "alertyellow";				
			}else if((healthy - normInputValue) > -5){
				gapDesc = "略微落后于行业平均水平";
				alertColor = "alertred";
			}else{
				gapDesc = "显著落后于行业平均水平";
				alertColor = "alertred";
			}
			String gapTow = "顾客结构健康";
//			描述2
			if(loyalCalculation>60){
				gapTow = "顾客结构两极化。";
			}else if(lossCalculation>60){
				gapTow = "顾客结构两极化。";
			}else if(loyalCalculation>50){
				gapTow = "顾客结构健康。";
			}else if (lossCalculation>50){
				gapTow = "顾客结构相对均衡。";
			}else if(instableCalculation>50){
				gapTow = "顾客结构不理想。";
			}
			StringBuffer comments = new StringBuffer();
			comments.append("总体顾客健康状况是综合了消费者的推荐意愿及再购意愿用来反映顾客与餐厅之间关系紧密程度的指标，我们用顾客健康指数来表示，顾客健康指数越高，表示顾客结构越健康。从本次研究来看，1、本餐厅的顾客健康指数为");
			comments.append(gapDesc).append(";");
			comments.append("2、本餐厅的忠诚顾客比例为xx，不稳定顾客比例为").append(instableCalculation).append("%,");
			comments.append("流失顾客比例为").append(lossCalculation).append("%。").append(gapTow);
			
			StringBuffer chartLegend = new StringBuffer("<div style=\"line-height:20px\">");			
			chartLegend.append("<div class=\"legendtitle\">本餐厅健康指数</div><div class=\"legendtitlenumber satisfiedPercent\">")
			.append(healthy)
			.append("</div> <br><div class=\"legenddetails\">行业平均值</div><div class=\"legenddetailsnumber satisfiednorm\">")
			.append(normInputValue)
			.append("%</div><div class=\"legenddetails\">星级</div><div class=\"legenddetailsnumber\"><div class=\"star-rating rating-active\"><div class=\"rating-container rating-gly-star\" data-content=\"\"><div class=\"rating-stars satisfiedstar\" data-content=\"\" style=\"width: ")
			.append(starVal)
			.append("%;\"></div></div></div> </div><div class=\"legenddetails\">竞争指示灯</div><div class=\"legenddetailsnumber satisfiedalert\"><div class=\"")
			.append(alertColor)
			.append("\"></div></div></div>");
			String tooltip = "tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\"},";
			String legend = "legend: {x : 'left',data:['忠诚顾客','不稳定顾客','流失顾客']},";
			String toolbox = "toolbox: {show : true,feature : {mark : {show: true},dataView : {show: true, readOnly: false},magicType : {show: true, type: ['pie', 'funnel'],option: {funnel: {x: '25%',width: '50%',funnelAlign: 'center',max: 1548}}},restore : {show: true},saveAsImage : {show: true}}},calculable : true,";
			StringBuffer series = new StringBuffer();
			series.append("{").append(tooltip).append(legend).append(toolbox);
			series.append("series : [{name:'',type:'pie',radius : ['50%', '70%'],itemStyle : {normal : {label : {show : false},labelLine : {show : false}}},");
			series.append("data:");
			series.append(ja);
			series.append("}]}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", "总体顾客健康状况");
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("data", ja);
			rspjb.put("option", series.toString());
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
