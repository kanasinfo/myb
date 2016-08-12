package com.myb.portal.model.mongodb;
/**
 * <p>Title: QuestionFilter</p>
 * <p>Description: (查询命名条件)</p>
 * <p>date : 2016年2月22日 下午2:25:26 </p>
 * @author wangzx
 * @version 1.0
 */
public class QuestionFilter {
	
	private String filter_id;
	private String name;
	private String value;
	private String label;
	private String period;
	private String store;
	
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	public String getFilter_id() {
		return filter_id;
	}
	public void setFilter_id(String filter_id) {
		this.filter_id = filter_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}	
