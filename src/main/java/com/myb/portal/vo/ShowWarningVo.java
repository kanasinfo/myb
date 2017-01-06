package com.myb.portal.vo;

import java.io.Serializable;
/**
 * <p>Title: ShowWarningVo</p>
 * <p>Description: (统计页面vo展现)</p>
 * <p>date : 2016年10月18日 下午10:21:49 </p>
 * @author wangzx
 * @version 1.0
 */
public class ShowWarningVo implements Serializable{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	//分店名称
	private String storeName;
	//预警名称
	private String warningName;
	//未处理
	private int untreated;
	//正在处理
	private int treated;
	//已处理
	private int alReady;
	//合计
	private int total;
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getWarningName() {
		return warningName;
	}
	public void setWarningName(String warningName) {
		this.warningName = warningName;
	}
	public int getUntreated() {
		return untreated;
	}
	public void setUntreated(int untreated) {
		this.untreated = untreated;
	}
	public int getTreated() {
		return treated;
	}
	public void setTreated(int treated) {
		this.treated = treated;
	}
	public int getAlReady() {
		return alReady;
	}
	public void setAlReady(int alReady) {
		this.alReady = alReady;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
