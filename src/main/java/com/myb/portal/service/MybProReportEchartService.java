package com.myb.portal.service;

import com.myb.portal.model.chart.MybChart;
import com.myb.portal.util.AjaxReq;

public interface MybProReportEchartService {
	AjaxReq getData(MybChart chart);
}
