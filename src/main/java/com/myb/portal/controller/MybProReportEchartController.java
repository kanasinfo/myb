package com.myb.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myb.portal.model.chart.MybChart;
import com.myb.portal.service.MybProReportEchartService;
import com.myb.portal.util.AjaxReq;

@Controller
@RequestMapping(value = "reportEchart")
public class MybProReportEchartController {
	@Autowired
	MybProReportEchartService mybProReportEcharService;

	@RequestMapping(value = "getChartAllInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReq getChartAllInfo(@RequestParam(value = "questionnaire_id", required = true) String questionnaireId,
			@RequestParam(value = "group_id", required = false) String groupId,
			@RequestParam("question_id") String questionId, String page, String filter,
			@RequestParam("dimensiontype") String dimensionType, String dimension,
			@RequestParam(value = "questionGroup", required = false) String questionGroup,
			@RequestParam(value = "questionName", required = false) String questionName,
			@RequestParam(value = "specialQuestions", required = false) String specialQuestions) {		
		try {
			MybChart chart = (MybChart) Class.forName("com.myb.portal.model.chart.MybChart" + page.toUpperCase())
					.newInstance();
			chart.setDimension(dimension);
			chart.setDimensionType(dimensionType);
			chart.setFilter(filter);
			chart.setGroupId(groupId);
			chart.setQuestionId(questionId);
			chart.setQuestionnaireId(questionnaireId);
			chart.setQuestionGroup(questionGroup);
			chart.setQuestionName(questionName);
			chart.setSpecialQuestions(specialQuestions);
			return mybProReportEcharService.getData(chart);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "getChartSummaryInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReq getChartSummaryInfo(
			@RequestParam(value = "questionnaire_id", required = true) String questionnaireId,
			@RequestParam(value = "groupId", required = false) String groupId,
			@RequestParam(value = "question_id", required = false) String questionId, String page, String filter,
			@RequestParam(value = "questionGroup", required = false) String questionGroup) {
		try {
			MybChart chart = (MybChart) Class.forName("com.myb.portal.model.chart.MybChart" + page.toUpperCase())
					.newInstance();
			chart.setFilter(filter);
			chart.setGroupId(groupId);
			chart.setQuestionId(questionId);
			chart.setQuestionnaireId(questionnaireId);
			chart.setQuestionGroup(questionGroup);
			return mybProReportEcharService.getData(chart);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		return null;
	}
}
