package com.myb.portal.model.chart;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.aggregation.Field;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MybChartUtils {
	

	/**
	 * 
	 * @param data
	 * @param list
	 * 
	 * 
	 */
	public static void packFilter(String data,List<Criteria> list){
		JSONObject jb = JSONObject.fromObject(data);		
		JSONArray ja = null;
		//Period in filter
		JSONObject jbPeriod = JSONObject.fromObject(jb.getString("period"));
		if (isNotNull(jbPeriod.getString("start_time")) && isNotNull(jbPeriod.getString("end_time"))) {
			list.add(Criteria.where("createdDate").gte(new Date(jbPeriod.getString("start_time")))
					.lte(new Date(jbPeriod.getString("end_time"))));
		} else if (isNotNull(jbPeriod.getString("start_time")) && !isNotNull(jbPeriod.getString("end_time"))) {
			list.add(Criteria.where("createdDate").gte(new Date(jbPeriod.getString("start_time"))));
		} else if (!isNotNull(jbPeriod.getString("start_time")) && isNotNull(jbPeriod.getString("end_time"))) {
			list.add(Criteria.where("createdDate").lte(new Date(jbPeriod.getString("end_time"))));
		}

		// Store in filter
		JSONObject jbStore = JSONObject.fromObject(jb.getString("store"));

		if(!jbStore.has("storeType") || "nostore".equals(jbStore.getString("storeType"))){
			list.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
		}else if(isNotNull(jbStore.getString("storeType"))) {
			List<String> listParam = extractStoreIds(jbStore);
			list.add(Criteria.where("storeId").in(listParam.toArray()));			
		}
		
		//Questions/Options values in Filter
		JSONObject jbVlaue = JSONObject.fromObject(jb.getString("value"));
		Set<String> set = jbVlaue.keySet();
		for (String key : set) {
			try {
				ja = jbVlaue.getJSONArray(key);
				List<String> listParam = new ArrayList<String>();
				for (int i = 0; i < ja.size(); i++) {
					listParam.add(key+"_"+ja.getString(i));
				}
				if(listParam.size()!=0){
					list.add(Criteria.where("answers.questionIdValue").in(listParam.toArray()));	
				}
			} catch (Exception e) {
				list.add(Criteria.where("answers.questionIdValue").in(key+"_"+jbVlaue.getString(key)));
			}
			
		}
	
	}
	
	public static void packFiltersMap(String data,Map<String, List<Criteria>> map){
		List<Criteria> criteria = new ArrayList<Criteria>();
		packFilter(data,criteria);
		map.put("filterOnly",criteria);
	}
	
	private static boolean isNotNull(String v){
		if(v!=null&&!"".equals(v)&&!"null".equals(v)){
			return true;
		}else{
			return false;
		}
	}
	
	public static void packDimensions(String data,Map<String, List<Criteria>> map){
		JSONArray ja = JSONObject.fromObject(data).getJSONArray("members");
		for (int i = 0; i < ja.size(); i++) {
			JSONObject jb = ja.getJSONObject(i);
			JSONArray jaQuestionid = null;
			List<Criteria> criteria = new ArrayList<Criteria>();

			// Store in filter
			JSONObject jbStore = JSONObject.fromObject(jb.getString("store"));
			if(!isNotNull(jbStore.getString("storeType"))){
				criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
			}else if("nostore".equals(jbStore.getString("storeType"))){
				criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
			}else if(isNotNull(jbStore.getString("storeType"))) {
				List<String> listParam = extractStoreIds(jbStore);
				criteria.add(Criteria.where("storeId").in(listParam.toArray()));
			}
			
			JSONObject jbValue = JSONObject.fromObject(jb.getString("value"));
			Set<String> set = jbValue.keySet();			
			for (String key : set) {				
				try {
					jaQuestionid = jbValue.getJSONArray(key);
					List<String> listParam = new ArrayList<String>();
					for (int j = 0; j < jaQuestionid.size(); j++) {
						listParam.add(key+"_"+jaQuestionid.getString(j));
					}
					criteria.add(Criteria.where("answers.questionIdValue").in(listParam.toArray()));
				} catch (Exception e) {
					criteria.add(Criteria.where("answers.questionIdValue").in(key+"_"+jbValue.getString(key)));
				}
			}
			map.put(jb.getString("memberId"),criteria);	
		}
	}
	
	public static void packDimensionsFilter(String dimension,String filter,Map<String, List<Criteria>> map){
		if(dimension != null && !"".equals(dimension) && !"\"\"".equals(dimension) && JSONObject.fromObject(dimension).isArray()){
			JSONArray ja = JSONObject.fromObject(dimension).getJSONArray("members");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jb = ja.getJSONObject(i);
				JSONArray jaQuestionid = null;
				List<Criteria> criteria = new ArrayList<Criteria>();
				// Store in filter
				JSONObject jbStore = JSONObject.fromObject(jb.getString("store"));
				if(!isNotNull(jbStore.getString("storeType"))){
					criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
				}else if("nostore".equals(jbStore.getString("storeType"))){
					criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
				}else if(isNotNull(jbStore.getString("storeType"))) {
					List<String> listParam = extractStoreIds(jbStore);
					criteria.add(Criteria.where("storeId").in(listParam.toArray()));
				}
				
				JSONObject jbValue = JSONObject.fromObject(jb.getString("value"));
				Set<String> set = jbValue.keySet();			
				for (String key : set) {				
					try {
						jaQuestionid = jbValue.getJSONArray(key);
						List<String> listParam = new ArrayList<String>();
						for (int j = 0; j < jaQuestionid.size(); j++) {
							listParam.add(key+"_"+jaQuestionid.getString(j));
						}
						packFilter(filter, criteria);
						criteria.add(Criteria.where("answers.questionIdValue").in(listParam.toArray()));
					} catch (Exception e) {
						criteria.add(Criteria.where("answers.questionIdValue").in(key+"_"+jbValue.getString(key)));
					}
				}
				map.put(jb.getString("memberId"),criteria);	
			}
		}else{
			packFiltersMap(filter, map);
		}	
	}
	
	private static List<String> extractStoreIds(JSONObject jbStore) {
		JSONArray jbStoreTmp = null;
		JSONArray jbStoreGroupTmp = null;
		List<String> listParam = new ArrayList<String>();
		if ("store".equals(jbStore.getString("storeType"))) {
			jbStoreTmp = jbStore.getJSONArray("store");
			for (int i1 = 0; i1 < jbStoreTmp.size(); i1++) {
				System.out.println("storeID:"+jbStoreTmp.getJSONObject(i1).getString("storeId"));
				if(jbStoreTmp.getJSONObject(i1).getString("storeId") != null && !listParam.contains(jbStoreTmp.getJSONObject(i1).getString("storeId"))){
					listParam.add(jbStoreTmp.getJSONObject(i1).getString("storeId"));
				}
			}					
		} else {
			jbStoreGroupTmp =  jbStore.getJSONArray("storeGroup");
			for (int j = 0; j < jbStoreGroupTmp.size(); j++){
				jbStoreTmp = jbStoreGroupTmp.getJSONObject(j).getJSONArray("store");
				for (int i1 = 0; i1 < jbStoreTmp.size(); i1++) {
					System.out.println("storeID:"+jbStoreTmp.getJSONObject(i1).getString("storeId"));
					if(jbStoreTmp.getJSONObject(i1).getString("storeId") != null && !listParam.contains(jbStoreTmp.getJSONObject(i1).getString("storeId"))){
						listParam.add(jbStoreTmp.getJSONObject(i1).getString("storeId"));
					}
				}	
			}
		}
		return listParam;
	}
}
