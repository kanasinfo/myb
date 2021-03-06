package com.myb.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
			@RequestParam(value = "specialQuestions", required = false) String specialQuestions,
			@RequestParam(value = "store", required = false) String store) {		
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
			chart.setStore(store);
			return mybProReportEcharService.getData(chart);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		return null;
	}
	/**
	 * getVoiceOfCustomer TODO(查询顾客之声) 
	 * @author wangzx
	 * @param questionnaire_id
	 * @param question_id
	 * @return
	 */
	@RequestMapping(value="getVoiceOfCustomer/{questionnaire_id}",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq getVoiceOfCustomer(@PathVariable("questionnaire_id")String questionnaire_id){
		return mybProReportEcharService.getVoiceOfCustomer(questionnaire_id);
	}

	@RequestMapping(value = "getChartSummaryInfo", method = RequestMethod.POST)
	@ResponseBody
	public AjaxReq getChartSummaryInfo(
			@RequestParam(value = "questionnaire_id", required = true) String questionnaireId,
			@RequestParam(value = "groupId", required = false) String groupId,
			@RequestParam(value = "question_id", required = false) String questionId, String page, String filter,
			@RequestParam(value = "questionGroup", required = false) String questionGroup,
			@RequestParam(value = "store", required = false) String store) {
		try {
			MybChart chart = (MybChart) Class.forName("com.myb.portal.model.chart.MybChart" + page.toUpperCase())
					.newInstance();
			chart.setFilter(filter);
			chart.setGroupId(groupId);
			chart.setQuestionId(questionId);
			chart.setQuestionnaireId(questionnaireId);
			chart.setQuestionGroup(questionGroup);
			chart.setStore(store);
			return mybProReportEcharService.getData(chart);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e1) {

			e1.printStackTrace();
		}
		return null;
	}
}
