package com.myb.portal.model.chart.result;

import java.util.List;

public class TagTowDimension {
	private TagTow tagTow;
	private double count;
	public TagTow getTagTow() {
		return tagTow;
	}
	public void setTagTow(TagTow tagTow) {
		this.tagTow = tagTow;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	
}
class TagTow{
	private List<String> xaxis;
	private List<String> yaxis;
	public List<String> getXaxis() {
		return xaxis;
	}
	public void setXaxis(List<String> xaxis) {
		this.xaxis = xaxis;
	}
	public List<String> getYaxis() {
		return yaxis;
	}
	public void setYaxis(List<String> yaxis) {
		this.yaxis = yaxis;
	}
	
}