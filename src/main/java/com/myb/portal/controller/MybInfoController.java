package com.myb.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>Title: MybMainController</p>
 * <p>Description: (跳转页面url业务处理)</p>
 * <p>date : 2015年12月28日 下午1:49:08 </p>
 * @author wangzx
 * @version 1.0
 */
@RequestMapping(value="info")
@Controller
public class MybInfoController {

	@RequestMapping(value="{view}.html",method=RequestMethod.GET)
	public String info(@PathVariable("view")String view){
		return "info/"+view;
	}

}
