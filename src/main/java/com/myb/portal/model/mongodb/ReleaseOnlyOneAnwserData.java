package com.myb.portal.model.mongodb;

import java.util.Date;

public class ReleaseOnlyOneAnwserData {
	//下载ID
	private String id;
	//是否是有那个该链接
	private boolean success;
	//该连接创建时间
	private Date createDate;
	//该链接使用时间
	private Date useDdate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUseDdate() {
		return useDdate;
	}
	public void setUseDdate(Date useDdate) {
		this.useDdate = useDdate;
	}
	
}
