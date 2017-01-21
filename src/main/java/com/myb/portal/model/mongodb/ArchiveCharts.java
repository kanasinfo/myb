package com.myb.portal.model.mongodb;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 统计图表存档
 * Created by gefangshuai on 2017/1/14.
 */
@Document(collection = "archive_charts")
public class ArchiveCharts {

    private String questionId;
    private List<ChartsFragement> chartsFragements = new ArrayList<>();

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<ChartsFragement> getChartsFragements() {
        return chartsFragements;
    }

    public void setChartsFragements(List<ChartsFragement> chartsFragements) {
        this.chartsFragements = chartsFragements;
    }



}
