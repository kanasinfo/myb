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
		if("nostore".equals(jbStore.getString("store_type"))){
			list.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
		}else if(isNotNull(jbStore.getString("store_type"))) {
			String storeColumnName = "storeId";
			if ("store".equals(jbStore.getString("store_type"))) {
				storeColumnName = "storeId";
			} else {
				storeColumnName = "storeGroupId";
			}
			ja = jbStore.getJSONArray("store_id");
			List<String> listParam = new ArrayList<String>();
			for (int i = 0; i < ja.size(); i++) {
				listParam.add(ja.getString(i));
			}
			list.add(Criteria.where(storeColumnName).in(listParam.toArray()));
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
			if(!isNotNull(jbStore.getString("store_type"))){
				criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
			}else if("nostore".equals(jbStore.getString("store_type"))){
				criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
			}else if(isNotNull(jbStore.getString("store_type"))) {
				String storeColumnName = "storeId";
				if ("store".equals(jbStore.getString("store_type"))) {
					storeColumnName = "storeId";
				} else {
					storeColumnName = "storeGroupId";
				}
				jaQuestionid = jbStore.getJSONArray("store_id");
				List<String> listParam = new ArrayList<String>();
				for (int i1 = 0; i1 < jaQuestionid.size(); i1++) {
					listParam.add(jaQuestionid.getString(i1));
				}
				criteria.add(Criteria.where(storeColumnName).in(listParam.toArray()));
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
		if(dimension != null && !"".equals(dimension) && !"\"\"".equals(dimension)){
			JSONArray ja = JSONObject.fromObject(dimension).getJSONArray("members");
			for (int i = 0; i < ja.size(); i++) {
				JSONObject jb = ja.getJSONObject(i);
				JSONArray jaQuestionid = null;
				List<Criteria> criteria = new ArrayList<Criteria>();
				// Store in filter
				JSONObject jbStore = JSONObject.fromObject(jb.getString("store"));
				if(!isNotNull(jbStore.getString("store_type"))){
					criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
				}else if("nostore".equals(jbStore.getString("store_type"))){
					criteria.add(Criteria.where("storeId").in("",null).and("storeGroupId").in("",null));
				}else if(isNotNull(jbStore.getString("store_type"))) {
					String storeColumnName = "storeId";
					if ("store".equals(jbStore.getString("store_type"))) {
						storeColumnName = "storeId";
					} else {
						storeColumnName = "storeGroupId";
					}
					jaQuestionid = jbStore.getJSONArray("store_id");
					List<String> listParam = new ArrayList<String>();
					for (int i1 = 0; i1 < jaQuestionid.size(); i1++) {
						listParam.add(jaQuestionid.getString(i1));
					}
					criteria.add(Criteria.where(storeColumnName).in(listParam.toArray()));
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
	
	public class MybField implements Field{
		private final String name;
		private final String target;

		/**
		 * Creates an aggregation field with the given {@code name}.
		 * 
		 * @see MybField#MybField(String, String).
		 * @param name must not be {@literal null} or empty
		 */
		public MybField(String name) {
			this(name, null);
		}

		/**
		 * Creates an aggregation field with the given {@code name} and {@code target}.
		 * <p>
		 * The {@code name} serves as an alias for the actual backing document field denoted by {@code target}. If no target
		 * is set explicitly, the name will be used as target.
		 * 
		 * @param name must not be {@literal null} or empty
		 * @param target
		 */
		public MybField(String name, String target) {

			String nameToSet = cleanUp(name);
			String targetToSet = cleanUp(target);

			Assert.hasText(nameToSet, "AggregationField name must not be null or empty!");

			if (target == null && name.contains(".")) {
				this.name = nameToSet.substring(nameToSet.indexOf('.') + 1);
				this.target = nameToSet;
			} else {
				this.name = nameToSet;
				this.target = targetToSet;
			}
		}

		private final String cleanUp(String source) {

			if (source == null) {
				return source;
			}
//
//			if (MybField.SystemVariable.isReferingToSystemVariable(source)) {
//				return source;
//			}

			int dollarIndex = source.lastIndexOf('$');
			return dollarIndex == -1 ? source : source.substring(dollarIndex + 1);
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.mongodb.core.aggregation.Field#getKey()
		 */
		public String getName() {
			return name;
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.mongodb.core.aggregation.Field#getAlias()
		 */
		public String getTarget() {
			return StringUtils.hasText(this.target) ? this.target : this.name;
		}

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.mongodb.core.aggregation.Field#isAliased()
		 */
		@Override
		public boolean isAliased() {
			return !getName().equals(getTarget());
		}

		/* 
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return String.format("MybField - name: %s, target: %s", name, target);
		}

		/* 
		 * (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {

			if (this == obj) {
				return true;
			}

			if (!(obj instanceof MybField)) {
				return false;
			}

			MybField that = (MybField) obj;

			return this.name.equals(that.name) && ObjectUtils.nullSafeEquals(this.target, that.target);
		}

		/* 
		 * (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {

			int result = 17;

			result += 31 * name.hashCode();
			result += 31 * ObjectUtils.nullSafeHashCode(target);

			return result;
		}
		
	}
	
	
	
	public static void main(String[] args) {
		try {
//			String ss = new String(Files.readAllBytes(Paths.get("/Users/william/Desktop/1.txt")));
//			String dimn = new String(Files.readAllBytes(Paths.get("/Users/william/Desktop/2.txt")));
//			System.out.println(ss);
//			List<Criteria> list = new ArrayList<Criteria>();
//			packFilter(ss, list);
//			System.out.println("111111111111111111111");
//			Map<String, List<Criteria>> mp = new HashMap();
//			packDimensions(dimn, mp);
//			System.out.println("222222222222222222222");
//			Map<String, List<Criteria>> fdmp = new HashMap();
//			packDimensionsFilter(dimn, ss, fdmp);
//			String a = "storegroup";
//			System.out.println(a.replaceAll("group", "Group"));
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
