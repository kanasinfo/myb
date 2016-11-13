package com.myb.portal.service;

import com.myb.portal.model.chart.MybChart;
import com.myb.portal.util.AjaxReq;

public interface MybProReportEchartService {
	AjaxReq getData(MybChart chart);
	/**
	 * getVoiceOfCustomer TODO(查询顾客之声) 
	 * @author wangzx
	 * @param questionnaireId
	 * @param questionId
	 * @return
	 */
	AjaxReq getVoiceOfCustomer(String questionnaireId);
}
