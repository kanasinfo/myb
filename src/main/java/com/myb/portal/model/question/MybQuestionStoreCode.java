package com.myb.portal.model.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.myb.portal.support.AbstractModel;
@Entity
@EntityListeners({ AuditingEntityListener.class })
@Table(name = "QUESTION_STORE_CODE")
public class MybQuestionStoreCode extends AbstractModel<String> {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	//主键ID
	@Id
	private String id;
	//问题模板ID
	@Column(name="question_id")
	private String questionId;
	//所有门店ID
	@Column(name="store_id")
	private String storeId;
	//门店名称
	@Column(name="store_name")
	private String storeName;
	//网页访问地址
	@Column(name="path")
	private String path;
	//二维码地址
	@Column(name="code_path")
	private String codePath;
	
	
	public String getCodePath() {
		return codePath;
	}
	public void setCodePath(String codePath) {
		this.codePath = codePath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
