package com.myb.portal.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.service.MybAnswerService;
import com.myb.portal.util.AjaxReq;

@RequestMapping(value = "answer")
@Controller
public class MybAnswerController {
	@Autowired
	MybAnswerService mybAnswerService;
	@RequestMapping(value="previewAnswer",method=RequestMethod.POST)
	public ModelAndView previewAnswer(String data){
		ModelAndView mv = new ModelAndView();
		Map<String, Object> map = mybAnswerService.previewQuestion(data);
		mv.addObject("data", map.get("data"));
		mv.addObject("list", map.get("mapQuestion"));
		mv.addObject("msg",map.get("msg"));
		mv.addObject("projectName",map.get("projectName"));
		mv.setViewName("previewAnswer");
		return mv;
	}
	
	@RequestMapping(value="queryQuestion",method=RequestMethod.GET)
	public ModelAndView queryQuestion(ModelAndView mv,String id,String storeId){
		Map<String, Object> map = mybAnswerService.queryMongodbQuestion(id, storeId);
		mv.addObject("data", map.get("data"));
		mv.addObject("list", map.get("mapQuestion"));
		mv.addObject("msg",map.get("msg"));
		mv.setViewName("answer");
		return mv;
	}

	@RequestMapping(value="questionaire/{id}.html",method=RequestMethod.GET)
	public ModelAndView questionaire(ModelAndView mv,@PathVariable("id")String id,String storeId){
		Map<String, Object> map = mybAnswerService.queryMongodbQuestion(id, storeId);
		mv.addObject("data", map.get("data"));
		mv.addObject("list", map.get("mapQuestion"));
		mv.addObject("msg",map.get("msg"));
		mv.addObject("projectName",map.get("projectName"));
		mv.setViewName("questionaire");
		return mv;
	}
	
	@RequestMapping(value="addAnswer",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq addAnswer(String data){
		return mybAnswerService.addAnswer(data);
	}
}
