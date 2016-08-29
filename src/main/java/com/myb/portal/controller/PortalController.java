package com.myb.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.service.MybAccountService;
import com.myb.portal.service.MybIndustryService;
import com.myb.portal.service.QuestionsService;
import com.myb.portal.util.AjaxReq;

/**
 * <p>Title: MybMainController</p>
 * <p>Description: (跳转页面url业务处理)</p>
 * <p>date : 2015年12月28日 下午1:49:08 </p>
 * @author wangzx
 * @version 1.0
 */
@RequestMapping(value="/")
@Controller
public class PortalController {
	public final String ACCOUNT_PATH= "account/";
	public final String THEMES_PATH= "/";
	@Autowired
	MybIndustryService mybIndustryService;
	@Autowired
	QuestionsService questionsService;
	@Autowired
	MybAccountService mybAccountService;
	

	@RequestMapping(value="index.html",method=RequestMethod.GET)
	public String home(ModelAndView mv){
		return "themes/simple_flat_full/index";
	}
}
