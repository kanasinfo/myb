package com.myb.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.myb.portal.model.chart.MybChart;
import com.myb.portal.service.MybProReportEchartService;
import com.myb.portal.util.AjaxReq;

@Service
public class MybProReportEchartServiceImpl implements MybProReportEchartService {
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public AjaxReq getData(MybChart chart) {
			return chart.getData(mongoTemplate);
	}

}
