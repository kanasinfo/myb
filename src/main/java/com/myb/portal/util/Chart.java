/**
 * 
 */
package com.myb.portal.util;

/**
 * @author William
 *
 */
public abstract class Chart {

	private String type;
	private String title;
	private String data;
	private String legend;
	private String summary;
	private String comments;
	
	public abstract String getTitle();
	public void setTitle(String title) {
		this.title = title;
	}
	public abstract String getData();
	public void setData(String data) {
		this.data = data;
	}
	public String getLegend() {
		return legend;
	}
	public void setLegend(String legend) {
		this.legend = legend;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
