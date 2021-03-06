package com.myb.portal.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.mongodb.BasicDBObject;
import com.myb.portal.model.mongodb.Options;
import com.myb.portal.model.mongodb.QuestionGroupVO;
import com.myb.portal.model.mongodb.QuestionTmpltVO;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.model.mongodb.Store;
import com.myb.questiontype.GroupType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public abstract class Utils {
	public final static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public static String getDate(){
		return format.format(new Date());
	}
	public static Date getDateTime(int d){
		Calendar calendar=Calendar.getInstance();   
		calendar.setTime(new Date()); 
		System.out.println(calendar.get(Calendar.MONTH));
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)-d);
		System.out.println(calendar.getTime());
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(s.format(calendar.getTime()));
		try {
			return s.parse(s.format(calendar.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	public static Integer getType(String className){
		try {
			GroupType groupType = (GroupType) Class.forName(className).newInstance();
			return groupType.getType();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public static String getUUid(){
		return UUID.randomUUID().toString().replace("-","");
	}
	public static Options packageOption(boolean res){
		Options options = new Options();
		options.setSortNumber(1);
		options.setActiveFlag(false);
		options.setOptionId(getUUid());
		if(res){
			options.setOptionName("是");
		}else {
			options.setOptionName("否");
		}
		
		return options;
	}
	/**
	 * 
	 * storeCode TODO(生成二维码) 
	 * @author wangzx
	 * @param url
	 * @return
	 */
	public static ByteArrayOutputStream storeCode(String url){
		String path = "";
		try {
			//生成二维码
			int width = 200;
			int height = 200;
			String format = "png";  
			Hashtable hints= new Hashtable();
	        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, width, height,hints);  
	        File outputFile = new File("/Users/wangzx/Desktop/new.png");   
	        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
	        ByteArrayOutputStream b = new ByteArrayOutputStream();
	        MatrixToImageWriter.writeToStream(bitMatrix, format, b);
	        return b;
		} catch (Exception e) {
			
		}
		
		return null;
	}
	public static void packageAnserQuestionTemplate(QuestionTmpltVO vo,JSONObject jb,String storeId){
		jb.put("questionnaireId", vo.getId());
		jb.put("qustnrName", vo.getQustnrName());
		jb.put("endUserIdentity", vo.getIndustry());
		jb.put("tenementId", vo.getTenementId());
		jb.put("storeId", storeId);
		jb.put("status", 0);
		Calendar calendar = Calendar.getInstance();
		jb.put("year", calendar.get(Calendar.YEAR));
		jb.put("quarter", Utils.quarter(calendar.get(Calendar.MONTH)+1));
		jb.put("month", calendar.get(Calendar.MONTH)+1);
		jb.put("day", calendar.get(Calendar.DATE));
		for (Store s : vo.getStore()) {
			if(storeId.equals(s.getStoreId())){
				jb.put("storeName", s.getStoreName());		
			}
		}
		BasicDBObject time = new BasicDBObject("ts", new Date());
		jb.put("createdTime",time);
	}
	public static void packageAnserQuestion(QuestionsVo questionsVo,JSONArray ja,Map<String, QuestionGroupVO> questionMap){
		JSONObject jb = new JSONObject();
		if(questionMap.get(questionsVo.getQuestionGroupId())!=null&&questionMap.get(questionsVo.getQuestionGroupId()).getCustomQuestionType().equals("com.myb.questiontype.Judge")){
			jb.put("questionIdValue", questionsVo.getQuestionId()+"_false");
			jb.put(questionsVo.getQuestionId()+"_optionValue", "false");
			jb.put("optionValue", "false");
		}else{
			jb.put("questionIdValue", "");
			jb.put("optionValue", "");
			jb.put(questionsVo.getQuestionId()+"_optionValue", "");
		}
		jb.put("businessType", questionsVo.getBusinessType());
		jb.put("questionId", questionsVo.getQuestionId());
		jb.put("questionGroupId", questionsVo.getQuestionGroupId());
		jb.put("questionGroupName", questionsVo.getQuestionGroupName());
		jb.put("questionName", questionsVo.getQuestionName());
			
		jb.put("optionValue", "");
		
		ja.add(jb);
	}
	public static String ParseProperties(String key){
		try {
			Properties p = new Properties();
			p.load(Utils.class.getClassLoader().getResourceAsStream("config.properties"));  
			return p.getProperty(key);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	public static int quarter(int month){
		int res = 0;
		if(month>=1&&month<=3){
			res = 1;
		}else if(month>=4&&month<=6){
			res = 2;
		}else if(month>=7&&month<=9){
			res = 3;
		}else if(month>=10&&month<=12){
			res = 4;
		}
		return res;
	}
	
}
