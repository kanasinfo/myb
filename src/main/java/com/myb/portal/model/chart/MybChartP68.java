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

public class MybChartP68 extends MybChart{

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
					jb.put("name", t.get_id() + "分");
					legendStr.append("'"+t.get_id()+"分',");
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
			 * 综合考虑xx餐厅在各个方面的满意度表现和顾客对各个方面的关注程度，我们得以将有待改进的各个方面进行排序，帮助餐厅用最小的投入来获得最大的提升。本次研究发现：餐厅<插入判别描述>
			 * 
			 * 鼠标滑过每个区域时，出示每个区域释义；
			 * 有无亟需改善  判别描述
			 * 有  在xx、xx(急需改善区域)等关键要素上表现较弱，需要优先考虑投入资源，改善提升
			 * 无  在顾客重点关注的几个要素上餐厅表现较好，资源允许的情况下可以目前需要关注xx, xx (其次改善区域)
			 * 注：xx表示落在这一区域的关键要素；
			 * 横轴取值：P52对应指标的“满意客户比例”
			 * 纵轴取值：P63对应指标的“重要性”。
			 * 气泡大小:满意顾客比例减行业平均值,Gap绝对值增大,气泡大小随之增大;
			 * 气泡颜色: 满意顾客比例减行业平均值,如果为正值,气泡为红色,如果为负值,气泡为绿色;
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
			

			StringBuffer comments = new StringBuffer("综合考虑");
			comments.append(custName)
			.append("在各个方面的满意度表现和顾客对各个方面的关注程度，我们得以将有待改进的各个方面进行排序，帮助餐厅用最小的投入来获得最大的提升。本次研究发现：餐厅");
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
