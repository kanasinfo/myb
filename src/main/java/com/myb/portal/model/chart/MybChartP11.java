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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.aggregation.Fields;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.chart.result.TwoDimnCount;
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
			if (StringUtils.isBlank(store)) {
				aReq.setSuccess(false);
				aReq.setMessage("数据不正确");
				return aReq;
			}
			//chartOneDimnsn,chartMultiDimnsn,chartStore,chartTime,chartTimeDimnsn,chartCustDimnsn		
			/**
			 * Deconstruct store string
			 * 
			store: storeids/storegroupids selected by users. 			 			 
			var store = {
				store_type : "",
				store_id : [ "" ],
				store_labels : [ "" ]
			};			 
			 */
			List<Criteria> criteria = new ArrayList<Criteria>();
			JSONObject jbStore = JSONObject.fromObject(store);					
			List<String> listParam = new ArrayList<String>();
			for (int i = 0; i < jbStore.getJSONArray("store_id").size(); i++) {
				listParam.add(jbStore.getJSONArray("store_id").getString(i));
			}
			criteria.add(Criteria.where(jbStore.getString("store_type").replaceAll("group","Group") + "Id").in(listParam.toArray()));
			
			
			
			String qstId ="1";
			String qstName = "";
			List<String> names = new ArrayList<String>();
			List<String> memberIds = new ArrayList<String>();
			List<JSONObject> jaMembers =  null;
			String[] groups = {"10","9","8","6-7","1-5"};
			String dimensionStr = "";			
			/**
			1. 查找门店的满意度分布（1-5，6-7，8，9，10）
			2. 计算总体满意顾客占比 (8+9+10)/total
			3. 计算各个分数的占比 10/total, 9/total, 8/total, 6-7/total, 1-5/total			
			**/

			JSONObject jb = null;
			StringBuffer legendStr = new StringBuffer("[");
			StringBuffer yAxisStr = new StringBuffer("[");
			JSONObject jbOld = null;				
			JSONArray ja = new JSONArray();
			Integer count =  0;
			
			Integer satisfied =  0;
			String[][] resultDimn = new String[groups.length][names.size()];
			
			Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();						
			MybChartUtils.packDimensionsFilter(dimensionStr, filter, fdmp);	
			Criteria ct =null;
			BasicDBList list = null;
			Aggregation aggregation = null;
			AggregationResults<TwoDimnCount> results = null;

			ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
			Field[] fields = new Field[2];			
			Field x = Fields.field("x","$answers."+qstId+"_optionValue");
			fields[0] = x;			
			Field y = Fields.field("y","$answers."+questionId+"_optionValue");
			fields[1] = y;		
			//Total
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),				
					group(Fields.from(fields)).count().as("count"));
			results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			list = (BasicDBList)results.getRawResults().get("result");	
			
			
			
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),				
					group(Fields.from(fields)).count().as("count"));
			results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			list = (BasicDBList)results.getRawResults().get("result");	
			
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),				
					group(Fields.from(fields)).count().as("count"));
			results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			list = (BasicDBList)results.getRawResults().get("result");	
			
			
			aggregation = newAggregation(match(Criteria.where("questionnaireId").is(questionnaireId)),match(ct),				
					group(Fields.from(fields)).count().as("count"));
			results = mongoTemplate.aggregate(aggregation, "answer", TwoDimnCount.class);
			list = (BasicDBList)results.getRawResults().get("result");	


			for(int k = 0; k < groups.length; k++){
				//boolean gFlag = false;
				legendStr.append("'"+groups[k]+"分',");
				JSONArray jaResult = new JSONArray();
				for(int j = 0; j < names.size(); j++){
					boolean gFlag = false;
					Integer oneOrfive = 0;
					Integer sixOrseven =  0;
					String name = names.get(j);				
					for(int i = 0; i < list.size(); i ++){  
			            BasicDBObject obj = (BasicDBObject)list.get(i);
			            String xStr = "";
			            String yStr = "";
			            Integer tmpCnt =(Integer)obj.get("count");
			            if("chartCustDimnsn".equals(dimensionType)){
			            	xStr =(String)obj.get("_x");				            	
			            	yStr = (String)obj.get("_id");	
			            }else{
				    		jb = JSONObject.fromObject(obj.get("_id"));				    		
				    		if(((List<String>)jb.get("x")).size()>0 && ((List<String>)jb.get("y")).size() >0){
				    			xStr =((List<String>)jb.get("x")).get(0);
					    		yStr =((List<String>)jb.get("y")).get(0);	
				    		}				    		
			            }
			            Pattern pattern = Pattern.compile("[0-9]*"); 
			            Matcher isNum = pattern.matcher(yStr);
			            if(!"".equals(yStr) && isNum.matches() && !"".equals(xStr)){
							if (Integer.parseInt(yStr) > 7 && groups[k].equals(yStr) && name.equals(xStr)) {
								gFlag = true;
								satisfied += tmpCnt;
								count += tmpCnt;
								jaResult.add(tmpCnt.toString());
							} else if (Integer.parseInt(yStr) >= 6 && Integer.parseInt(yStr) <= 7 && groups[k].equals("6-7") && name.equals(xStr)) {
								gFlag = true;
								sixOrseven += tmpCnt;
							} else if (Integer.parseInt(yStr) >= 1 && Integer.parseInt(yStr) <= 5 && groups[k].equals("1-5") && name.equals(xStr)) {
								gFlag = true;
								oneOrfive += tmpCnt;
							}	
			            }
					}
					if(!gFlag){
						jaResult.add("0");
					}
					if (sixOrseven != 0) {
						count +=sixOrseven;
						jaResult.add(sixOrseven.toString());
					}					
					if (oneOrfive != 0) {
						count +=oneOrfive;
						jaResult.add(oneOrfive.toString());
					}
				}				
				List<String> ls = (List<String>)jaResult;
				System.out.println(ls.size());
				String[] ds = (String[]) ls.toArray(new String[names.size()]);
				resultDimn[k] = ds;
				jbOld = new JSONObject();
				jbOld.put("name", groups[k]+"分");
				jbOld.put("type", "bar");
				jbOld.put("stack", "stack");
				jbOld.put("itemStyle", "{ normal: {label : {show: true, position: 'insideRight',formatter: '{c} %'}}}");
				jbOld.put("data", jaResult);
				ja.add(jbOld); 
			}
			
			int b[][] = new int [names.size()][groups.length];
			int c[][] = new int[groups.length][names.size()];
	        for (int i = 0; i < names.size(); i++) {
	            for (int j = 0; j < groups.length; j++) {
	                b[i][j] = Integer.parseInt(resultDimn[j][i]);
	                if (j < groups.length) {
	                    System.out.print(b[i][j] + " " );
	                } else {
	                    System.out.println(b[i][j] + " " );
	                }
	            }
	        }
	        List<JSONObject> saRult = new JSONArray();
	        JSONObject saObj = new JSONObject();
	        for (int i = 0; i < names.size(); i++) {
	        	int[] n = b[i];
	        	int total = sumHelper(0,n);
	        	int satiTotal = 0;
	        	 System.out.println(total);
	            for (int j = 0; j < groups.length; j++) {
	            	if(j<3){
	            		satiTotal+=b[i][j];
	            	}
	            	c[j][i] = total==0?0:(Math.round(b[i][j]*100/total));
	                if (j < groups.length) {
	                    System.out.print(c[j][i] + "% " );
	                } else {
	                    System.out.println(c[j][i] + "% " );
	                }
	            }
	            saObj.put("name", names.get(i));
	            saObj.put("value", total==0?0:satiTotal*100/total);
	            saRult.add(saObj);
	        }
	       	
			Collections.sort(saRult, new Comparator<JSONObject>(){
				@Override
				public int compare(JSONObject o1, JSONObject o2) {
					return (o1.getInt("value") - o2.getInt("value"));
				}
			}); 				

	        
	        //顺序有可能不对，要验证c[i]是否有用
	        JSONArray jaRult = new JSONArray();	     
	        for (int i = 0;i<groups.length;i++){
	        	JSONObject jot = ja.getJSONObject(i);
	        	jot.remove("data");
	        	jot.put("data",c[i]);
	        	jaRult.add(jot);
	        }	
			
			for(int m = 0; m < names.size(); m++){
				yAxisStr.append("'"+names.get(m)+"',");
			}
			
			yAxisStr.substring(0, yAxisStr.length()-1);
			legendStr.append("]");
			yAxisStr.append("]");

			if(count > 0){
			String gapDesc = "显著落后于行业平均水平";	
			int gap = saRult.get(saRult.size()-1).getInt("value") - saRult.get(0).getInt("value");
			if(gap >=5){
				gapDesc = "较大差异";
			}else if(gap >=3 && gap<5){
				gapDesc = "略有差异";
			}else{
				gapDesc = "无明显差异";
			}
			
			

			StringBuffer comments = new StringBuffer("从不同维度入手了解客群之间的满意度表现差异，可以更直观的透视顾客中“满意顾客“和“不满意顾客”的典型特征。<br> ");
			comments.append("按"+qstName+"来看，不同"+qstName+"的顾客满意度存在"+gapDesc + "。其中<最大值类别："+saRult.get(saRult.size()-1).getString("name")+"相对较好，<最小值类别："+saRult.get(0).getString("name")+">相对较差。");

			StringBuffer chartLegend = new StringBuffer("<div style=\"line-height:20px;width:40%;float:right\">");
			chartLegend.append("<div style=\"height:90px;text-align: left;\">满意顾客比例</div>");
			for(int i=names.size()-1;i>-1;i--){
				for(JSONObject jo:saRult){
					if(jo.get("name").equals(names.get(i))){
						chartLegend.append("<div style=\"height:90px;padding-left:20px\">")
						.append(jo.get("value"))
						.append("%</div>");						
					}
				}
			}
			chartLegend.append("</div>");
			
			StringBuffer option =new StringBuffer("{title : {text: '',subtext: '',x:'center'},")
					.append("toolbox: {show: true,feature: {dataView: {show: true, readOnly: false},magicType: {show: true, type: ['pie','line', 'bar','stack','tiled']},restore: {show: true},saveAsImage: {show: true}}},")
					.append("tooltip : {trigger: 'item',formatter: \"{a} <br/>{b} : {c}%\"},")
					.append("legend: {data: ")
					.append(legendStr)
					.append("},calculable : true,xAxis: [{type : 'value',}],")
					.append("yAxis : [{type : 'category',data : ")
					.append(yAxisStr)
					.append("}],")
					.append("series :")
					.append(jaRult.toString())
					.append("}");
			JSONObject rspjb = new JSONObject();
			rspjb.put("type", "bar");
			rspjb.put("title", qstName);
			rspjb.put("chartlegend", chartLegend.toString());
			rspjb.put("option", option.toString());
			rspjb.put("comments", comments.toString());
			rspjb.put("nodata", false);
			aReq.setSuccess(true);
			aReq.setData(rspjb);
			}else{
				JSONObject rspjb = new JSONObject();
				rspjb.put("type", "bar");
				rspjb.put("title", qstName+"在此条件下没有数据！");
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
	
    private static int sumHelper( int i, int... numbers)  
    {  
       return i == numbers.length ? 0 :  
           numbers[i] + sumHelper(i + 1, numbers);  
    }  

}
