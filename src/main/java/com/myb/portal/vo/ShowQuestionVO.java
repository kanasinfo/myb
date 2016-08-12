package com.myb.portal.vo;

public class ShowQuestionVO {
	//行业名称
	private String industryName;
	//子行业名称
	private String subIndustryName;
	//模板名称
	private String templName;
	//调查名称
	private String qustnrName;
	//调查数量
	private int creditAmount;
	//门店名称
	private String trendName;
	//欢迎语标识
	private boolean activeFlag;
	//欢迎语内容
	private String welcomeMsg;
	
	public String getTemplName() {
		return templName;
	}
	public void setTemplName(String templName) {
		this.templName = templName;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getSubIndustryName() {
		return subIndustryName;
	}
	public void setSubIndustryName(String subIndustryName) {
		this.subIndustryName = subIndustryName;
	}
	public String getQustnrName() {
		return qustnrName;
	}
	public void setQustnrName(String qustnrName) {
		this.qustnrName = qustnrName;
	}
	public int getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(int creditAmount) {
		this.creditAmount = creditAmount;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	public boolean isActiveFlag() {
		return activeFlag;
	}
	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	public String getWelcomeMsg() {
		return welcomeMsg;
	}
	public void setWelcomeMsg(String welcomeMsg) {
		this.welcomeMsg = welcomeMsg;
	}
	
	
	
	
}
