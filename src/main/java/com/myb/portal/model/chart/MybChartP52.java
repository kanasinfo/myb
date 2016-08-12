package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

public class MybChartP52 extends MybChart{

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
			Criteria ct =null;
			if(fdmp.get("filterOnly") != null){
				ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			}
			
			Criteria criteria = null;
			Aggregation aggregation ;
			AggregationResults<TagCount> results =null;
			List<TagCount> tagCount = null;
			List<Criteria> criterias =null;
			//String[] splice = dataSourcesId.split(",");
			List<JSONObject> jaQG = JSONArray.fromObject(questionGroup);
			JSONObject jOResult = null;
			List<JSONObject> jAResult = new JSONArray();
			for (JSONObject jo:jaQG) {
				criterias = new ArrayList<Criteria>();
				String id = jo.getString("questionId");
				jOResult = new JSONObject();
				jOResult.put("name", jo.getString("questionName"));	
				criterias.add(Criteria.where("answers.questionIdValue").in(id+"_8",id+"_9",id+"_10"));
				criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(id)),group("$answers.questionName").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();
				 if(tagCount.size()>0){	 
					 jOResult.put("sValue", tagCount.get(0).getCount());
				 }else{
					 jOResult.put("sValue",0);					 
				 }
				  
			 criterias.clear();
			criterias.add(Criteria.where("answers.questionIdValue").in(id+"_1",id+"_2",id+"_3",id+"_4",id+"_5",id+"_6",id+"_7"));

				criteria = new Criteria().andOperator(criterias.toArray(new Criteria[criterias.size()]));
				 aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(criteria),match(ct),unwind("$answers"),match(Criteria.where("answers.questionId").is(id)),group("$answers.questionName").count().as("count"));
				 results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
				 tagCount = results.getMappedResults();
				 if(tagCount.size()>0){	 
					 jOResult.put("dValue", tagCount.get(0).getCount());
				 }else{
					 jOResult.put("dValue",0);					 
				 }
				 jOResult.put("total",jOResult.getInt("sValue") + jOResult.getInt("dValue"));
				 jOResult.put("sPct",(jOResult.getInt("sValue") + jOResult.getInt("dValue"))==0?0:jOResult.getInt("sValue")*100/(jOResult.getInt("sValue") + jOResult.getInt("dValue")));
				 jOResult.put("dPct",(jOResult.getInt("sValue") + jOResult.getInt("dValue"))==0?100:jOResult.getInt("dValue")*100/(jOResult.getInt("sValue") + jOResult.getInt("dValue")));
				 jAResult.add(jOResult);
				 System.out.println("name: "+jOResult.getString("name")+",total:"+jOResult.getInt("total") +
						 ", sValue:"+jOResult.getInt("sValue")+",dValue:"+jOResult.getInt("dValue") +
						 "sPct:" +jOResult.getInt("sPct")+",dPct:"+jOResult.getInt("dPct"));
			}

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
			 */

			List<JSONObject> jbOpt = jAResult;				
			Collections.sort(jbOpt, new Comparator<JSONObject>(){
				@Override
				public int compare(JSONObject o1, JSONObject o2) {
					return (o2.getInt("sPct") - o1.getInt("sPct"));
				}
			});
			List<String> nameSorted = new ArrayList<String>();
			for(JSONObject jot:jbOpt){
				nameSorted.add(jot.getString("name"));
			}
			StringBuffer nameStr = new StringBuffer("[");
			JSONArray names = new JSONArray();
			StringBuffer series = new StringBuffer("[");	
			int sCount = 0;
			for(int i=0;i<jAResult.size();i++){
				JSONObject jo = jAResult.get(i);
				nameStr.append("'"+jo.getString("name")+"',");
				names.add(jo.getString("name"));
				if(jo.getInt("sPct")>=80){
					sCount++;
				}
				int x = (2*((i)%5+1)-1)*10;
				int y = i/5>=1?70:30;                                                          
				series.append("{type : 'pie',")
				.append("center : ['"+x+"%', '"+y+"%'],")
				.append("radius : [40, 55],")
				//.append("x:'0%',")
				.append("itemStyle:")
				.append("reportGlobal.P52.labelFromatter")
				.append(",")
				.append("data:[")
				.append("{name:'非满意顾客',value:")
				.append(jo.getString("dPct"))
				.append(",itemStyle:")
				.append("reportGlobal.P52.labelBottom")
				.append("},{name:'")
				.append(jo.getString("name"))
				.append("',value:")
				.append(jo.getString("sPct"))
				.append(",itemStyle:")
				.append("reportGlobal.P52.labelTop")
				.append("}]},");
			}
			nameStr.substring(0, nameStr.length()-1);
			nameStr.append("]");
			series.append("]");
			
			StringBuffer option =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("toolbox: {show: true,feature: {dataView: {show: true, readOnly: false},restore: {show: true},saveAsImage: {show: true}}},")
					.append("tooltip : {trigger: 'item',formatter: '{a} <br/>{b} : {c} ({d}%)'},")
					.append("legend: {x:'left',y:'top',data: ")
					.append(nameStr.toString())
					.append("},")
					.append("series : ")
					.append(series.toString())
					.append("}");
			String gapDesc = "";			
			if(sCount == jAResult.size()){
				gapDesc = "本店的各服务细节表现优秀，各项指标均达到了高满意度评价（80分及以上）";
			}else if(sCount >= jAResult.size()/2){
				gapDesc = "超过半数的服务环节达到了高满意度评价（80分及以上）";
			}else if(sCount > 0 && sCount <= jAResult.size()/2){
				gapDesc = "超过半数的服务环节未能达到高满意度评价（80分及以上）";
			}else if(sCount == 0){
				gapDesc = "本店的各项服务环节均未达到高满意度评价（80分以上）";
			}

			//当指标数量小于3个时，不显示Comments。当指标数量为3个时，n=1；指标数量为4-5个时，n=2；指标数量大于或等于6个时，n=3.
			StringBuffer comments = new StringBuffer("");
			if(jAResult.size()>=3){
				comments.append("1、从顾客对各项服务环节的评价来看， ")
				.append(gapDesc)
				.append(";<br>2、相对来讲， ");
						if(jAResult.size() == 3){
							comments.append(nameSorted.get(0));
							comments.append("满意度评分较高， ");
							comments.append(nameSorted.get(jAResult.size()-1));
							comments.append("满意度评分较低。");
						}else if(jAResult.size() ==4 || jAResult.size() ==5){
							comments.append(nameSorted.get(0)+","+nameSorted.get(1));
							comments.append("满意度评分较高， ");
							comments.append(nameSorted.get(jAResult.size()-1)+','+nameSorted.get(jAResult.size()-2));
							comments.append("满意度评分较低。");
						}else{
							comments.append(nameSorted.get(0)+","+nameSorted.get(1)+","+nameSorted.get(2));
							comments.append("满意度评分较高， ");
							comments.append(nameSorted.get(jAResult.size()-1)+','+nameSorted.get(jAResult.size()-2)+','+nameSorted.get(jAResult.size()-3));
							comments.append("满意度评分较低。");
						}						
			}
	
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "pie");
			rspjb.put("title", "顾客对各服务环节的体验满意度如何？");
			rspjb.put("chartlegend", "");
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
