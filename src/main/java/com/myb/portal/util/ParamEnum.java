package com.myb.portal.util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum ParamEnum {
	//模板状态
	QUESTION_TEMPLATE_STATUS_START(0,"进行中"),
	QUESTION_TEMPLATE_STATUS_STOP(1,"已停止"),
	QUESTION_TEMPLATE_STATUS_CREATE(2,"创建中"),
	QUESTION_TEMPLATE_COLOR_CREATE(1,"235 189 90"),
	QUESTION_TEMPLATE_COLOR_START(1,"120 151 63"),
	QUESTION_TEMPLATE_COLOR_STOP(1,"204 204 204"),
	INDUSTRY_TYPE_PARANT(1,"业态"),
	INDUSTRY_TYPE_TEMPL(0,"模板");
	private static final Map<Integer, String> lookup = new HashMap<Integer, String>(); 
	static{
		for (ParamEnum iterable_element : EnumSet.allOf(ParamEnum.class)) {
			System.out.println(iterable_element.getCode()+"aaaaaa"+iterable_element.getMessage());;
			lookup.put(iterable_element.getCode(), iterable_element.getMessage());
		}
	}
	private final int code;
	private final String message;
	private ParamEnum(int code,String message){
		this.code = code;
		this.message = message;
	}
	public String getMessage(){
		return message;
	}
	public int getCode(){
		return code;
	}
}
