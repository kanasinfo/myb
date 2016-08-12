package com.myb.portal.util;

public class AjaxReq {
	/**
	 * 用户编码
	 */
	private String code;
	/**
	 * 业务处理标识
	 */
	private boolean success;
	/**
	 * 返回数据
	 */
	private Object data;
	/**
	 * 消息提示
	 */
	private String message;
	private int type;
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
