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

public class MybChartP41 extends MybChart{

	@Override
	public AjaxReq getData(MongoTemplate mongoTemplate) {
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
			MybChartUtils.packDimensionsFilter(dimension, filter, fdmp);	
			Criteria ct =null;
			if(fdmp.get("filterOnly") != null){
				ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			}
			
			Aggregation aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),
					unwind("$answers"), match(Criteria.where("answers.questionId").is(questionId)),
					group("$answers.optionValue").count().as("count"));
			AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
			List<TagCount> tagCount = results.getMappedResults();
			Integer oneOrfive = 0;
			Integer sixOrseven =  0;
			Integer count =  0;
			Integer satisfied =  0;
			StringBuffer legendStr = new StringBuffer("[");
			JSONObject jb = null;
			JSONArray ja = new JSONArray();
			for (int i = 0; i < tagCount.size(); i++) {
				TagCount t = tagCount.get(i);
				if (Integer.parseInt(t.get_id()) > 7) {
					satisfied+=t.getCount();
					count +=t.getCount();
					jb = new JSONObject();
					jb.put("name", t.get_id() + "分");
					legendStr.append("'"+t.get_id()+"分',");
					jb.put("value", t.getCount());			
					ja.add(jb);
				} else if (Integer.parseInt(t.get_id()) >= 6 && Integer.parseInt(t.get_id()) <= 7) {
					sixOrseven += t.getCount();
				} else if (Integer.parseInt(t.get_id()) >= 1 && Integer.parseInt(t.get_id()) <= 5) {
					oneOrfive += t.getCount();
				}
			}
			if (sixOrseven != 0) {
				count +=sixOrseven;
				jb = new JSONObject();
				jb.put("name", "6-7分");
				legendStr.append("'6-7分',");
				jb.put("value", sixOrseven);
				ja.add(jb);
			}
			
			if (oneOrfive != 0) {
				count +=oneOrfive;
				jb = new JSONObject();
				jb.put("name", "1-5分");
				legendStr.append("'1-5分'");
				jb.put("value", oneOrfive);
				ja.add(jb);
			}
			
			legendStr.append("]");


			/**
			 * 1、满意度量表为10分制；
			 * 2、满意顾客占比=10分+9分+8分顾客占比；行业平均值有外部导入基础值并根据来自于数据库中同类别餐厅项目的满意顾客占比均值不断更新；
			 * 3、星级：90（含）-100%，5星；75（含）-90%，4星；60（含）-75%，3星；40（含）-60%，2星；40以下，1星；
			 * 4、警示灯：领先行业平均值3分及以上，绿灯(alertgreen)；落后行业平均值3分及以上，红灯(alertred)；中间，黄灯(alertyellow)
			 * 5、GAP=（满意度顾客占比-行业平均值)*100；
			 * Gap≥5:显著领先于行业平均水平
			 * 3≤Gap＜5:略微领先于行业平均水平
			 * -3＜GAP＜3:属于行业一般水平
			 * -5＜GAP ≤ -3:略微落后于行业平均水平
			 * GAP≤-5:显著落后于行业平均水平
			 * 
			 * Comments:
			 * 餐厅顾客再购意愿是反应顾客忠诚度的重要指标之一。
			 * 1、从本次数据来看，再购意愿较强、可能会成为回头客的顾客占比xx%, 再购意愿很弱、可能会流失的顾客占比xx%,摇摆不定的顾客占比xx%.
			 * 2、与行业水平相比，xx餐厅的潜在回头客占比<插入判别描述>
			 * 
			 */
			
			if(count > 0){
			Double satifiedPct =  new Double((satisfied/count)*100);
			Double normPct = new Double(45);
			String starVal = "20";
			String alertColor = "alertred";
			String gapDesc = "显著落后于行业平均水平";
			String custName = "xx餐厅";//TO-DO			
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
				gapDesc = "显著领先于行业平均水平";
				alertColor = "alertgreen";
			}else if((satifiedPct - normPct) >= 3){
				gapDesc = "略微领先于行业平均水平";
				alertColor = "alertgreen";
			}else if((satifiedPct - normPct) > -3){
				gapDesc = "属于行业一般水平";
				alertColor = "alertyellow";				
			}else if((satifiedPct - normPct) > -5){
				gapDesc = "略微落后于行业平均水平";
				alertColor = "alertred";
			}else{
				gapDesc = "显著落后于行业平均水平";
				alertColor = "alertred";
			}


			
			StringBuffer comments = new StringBuffer("餐厅顾客再购意愿是反应顾客忠诚度的重要指标之一。<br />");
			comments.append("1、从本次数据来看，再购意愿较强、可能会成为回头客的顾客占比<span class=\"satisfied\">")
			.append(satifiedPct)
			.append("%</span>, 再购意愿很弱、可能会流失的顾客占比<span class=\"unsatisfied\">"
					+ (oneOrfive/count)*100
					+"%,摇摆不定的顾客占比<span class=\"neutral\">"
					+(sixOrseven/count)*100
					+ "</span>.<br />"
					+ "2、与行业水平相比，<span class=\"store\">"
					+ custName
					+ "</span>的潜在回头客占比<span class=\"comment-desc\">"
					+ gapDesc
					+ "</span>.");

			StringBuffer chartLegend = new StringBuffer("<div style=\"line-height:20px\">");			
			chartLegend.append("<div class=\"legendtitle\">本餐厅满意顾客占比</div><div class=\"legendtitlenumber satisfiedPercent\">")
			.append(satifiedPct)
			.append("%</div> <br><div class=\"legenddetails\">行业平均值</div><div class=\"legenddetailsnumber satisfiednorm\">")
			.append(normPct)
			.append("%</div><div class=\"legenddetails\">顾客满意星级</div><div class=\"legenddetailsnumber\"><div class=\"star-rating rating-active\"><div class=\"rating-container rating-gly-star\" data-content=\"\"><div class=\"rating-stars satisfiedstar\" data-content=\"\" style=\"width: ")
			.append(starVal)
			.append("%;\"></div></div></div> </div><div class=\"legenddetails\">竞争指示灯</div><div class=\"legenddetailsnumber satisfiedalert\"><div class=\"")
			.append(alertColor)
			.append("\"></div></div></div>");
			
			StringBuffer option =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("toolbox: {show: true,feature: {dataView: {show: true, readOnly: false},magicType: {show: true, type: ['pie','line', 'bar','stack','tiled']},restore: {show: true},saveAsImage: {show: true}}},")
					.append("tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\"},")
					.append("legend: {orient : 'horizontal',x : 'left',data: ")
					.append(legendStr)
					.append("},calculable : true,")
					.append("series : [{name:'',type:'pie',radius : '60%',center: ['50%', '50%'],")
					.append("data: ")
					.append(ja.toString())
					.append("}]}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", "总体再购意愿");
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
			rspjb.put("comments", comments.toString());
			rspjb.put("nodata", false);
			aReq.setSuccess(true);
			aReq.setData(rspjb);
			}else{
				JSONObject rspjb = new JSONObject();
				rspjb.put("type", "pie");
				rspjb.put("title", "总体再购意愿在此条件下没有数据！");
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

}
