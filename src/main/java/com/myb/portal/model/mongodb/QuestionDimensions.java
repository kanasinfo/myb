package com.myb.portal.model.mongodb;

import java.util.List;

public class QuestionDimensions {
	private String dimensionId;
	private String name;
	private List<DimensionsMenbers> members;
	
	public String getDimensionId() {
		return dimensionId;
	}
	public void setDimensionId(String dimensionId) {
		this.dimensionId = dimensionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DimensionsMenbers> getMembers() {
		return members;
	}
	public void setMembers(List<DimensionsMenbers> members) {
		this.members = members;
	}
	
}
