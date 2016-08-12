package com.myb.portal.model.chart;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.util.AjaxReq;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartP63 extends MybChart{

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
			Aggregation aggregation = newAggregation(match(Criteria.where("questionnaire_id").is(questionnaireId)),
					unwind("$answers"), match(Criteria.where("answers.question_id").is(questionId)),
					group("$answers.option_value").count().as("count"));
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
				if (t.getCount() > 7) {
					satisfied+=t.getCount();
					count +=t.getCount();
					jb = new JSONObject();
					jb.put("name", t.getCount() + "分");
					legendStr.append("'"+t.getCount()+"分',");
					jb.put("value", t.getCount());			
					ja.add(jb);
				} else if (t.getCount() >= 6 && t.getCount() <= 7) {
					sixOrseven += t.getCount();
				} else if (t.getCount() >= 1 && t.getCount() <= 5) {
					oneOrfive += t.getCount();
				}
			}

			legendStr.append("]");

			/**
			 * 
			 * Comments:
			 * 重要性的分析是去分辨顾客潜意识下，对各个产品服务环节的关注程度。通过本次调研的数据，我们发现影响本店顾客满意度的最主要三项因素为：<插入top3>。
			 * 
			 * 1、算法参见《满意度驱动算法》；
			 * 2、图表中全部展示，文字描述中只列出前三。
			 * 
			 * 
			 */
			Double satifiedPct =  count==0?0:new Double((satisfied/count)*100);
			Double normPct = new Double(45);
			String starVal = "20";
			String alertColor = "alertred";
			String gapDesc = "显著落后于行业平均水平";
			String custName = "xx餐厅";//TO-DO			
			

			StringBuffer comments = new StringBuffer("重要性的分析是去分辨顾客潜意识下，对各个产品服务环节的关注程度。通过本次调研的数据，我们发现影响本店顾客满意度的最主要三项因素为：");
			comments.append(gapDesc);

			StringBuffer chartLegend = new StringBuffer("");			
			
			StringBuffer option =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c} ({d}%)\"},")
					.append("xAxis : [ {type : 'value',boundaryGap : [0, 0.01]}],")
					.append("yAxis : [{type : 'category',data : ")
					.append(legendStr)
					.append("}],")
					//['巴西','印尼','美国','印度','中国','世界人口(万)']
					.append("series : [{type:'bar',data:[")
					.append(ja.toString())
					//.append("[{\"name\":\"9分\",\"value\":1},{\"name\":\"1-5分\",\"value\":2},{\"name\":\"6-7分\",\"value\":1}]")
					//        {name:'2011年',type:'bar',data:[18203, 23489, 29034, 104970, 131744, 630230]
					.append("]}]}");
			System.out.println(option.toString());
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "bar");
			rspjb.put("title", "总体满意度");
			rspjb.put("chartlegend", chartLegend.toString());
			//rspjb.put("data", ja);
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
