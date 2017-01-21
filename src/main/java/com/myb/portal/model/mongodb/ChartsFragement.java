package com.myb.portal.model.mongodb;

import com.myb.portal.util.StringUtils;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.Date;

public class ChartsFragement {

    private String questionnaireId;
    private String questionId;
    private String page;
    private String fragementId;
    private String filter;
    private String dimensiontype;
    private String dimension;
    private String questionGroup;
    private String groupId;
    private String questionName;
    private String specialQuestions;
    private String store;
    private String data;                        // 统计参数
    private String image;                       // 图片
    private Date createdDate = new Date();                   // 添加时间

    private String left;       // 左边FragementId
    private String right;      // 右边FragementId

    public ChartsFragement() {
        fragementId = StringUtils.generateUUID();
    }

    public String getFragementId() {
        return fragementId;
    }

    public void setFragementId(String fragementId) {
        this.fragementId = fragementId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getDimensiontype() {
        return dimensiontype;
    }

    public void setDimensiontype(String dimensiontype) {
        this.dimensiontype = dimensiontype;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(String questionGroup) {
        this.questionGroup = questionGroup;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getSpecialQuestions() {
        return specialQuestions;
    }

    public void setSpecialQuestions(String specialQuestions) {
        this.specialQuestions = specialQuestions;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(String questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }
}