package com.myb.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.service.MybIndustryService;
import com.myb.portal.service.QuestionsService;

/**
 * <p>Title: MybMainController</p>
 * <p>Description: (跳转页面url业务处理)</p>
 * <p>date : 2015年12月28日 下午1:49:08 </p>
 * @author wangzx
 * @version 1.0
 */
@RequestMapping(value="main")
@Controller
public class MybMainController {
	public final String ACCOUNT_PATH= "account/";
	public final String THEMES_PATH= "/";
	@Autowired
	MybIndustryService mybIndustryService;
	@Autowired
	QuestionsService questionsService;
	

	@RequestMapping(value="home",method=RequestMethod.GET)
	public ModelAndView home(ModelAndView mv){
		mv.setViewName("home");
		return mv;
	}
	
	@RequestMapping(value="product",method=RequestMethod.GET)
	public ModelAndView product(ModelAndView mv){
		mv.setViewName("product");
		return mv;
	}
	
	@RequestMapping(value="price",method=RequestMethod.GET)
	public ModelAndView price(ModelAndView mv){
		mv.setViewName("price");
		return mv;
	}
	/**
	 * report TODO(跳转到报告页面)
	 * @author wangzx
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="report/{id}.html",method=RequestMethod.GET)
	public ModelAndView report(@PathVariable("id")String id,ModelAndView mv){
		mv.addObject("id", id);
		mv.setViewName("report");
		return mv;
	}
	
	/**
	 * report TODO(跳转到专业报告页面)
	 * @author dongw
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="proreport/{id}.html",method=RequestMethod.GET)
	public ModelAndView proreport(ModelAndView mv,@PathVariable("id")String id){
		mv.addObject("id",id);
		mv.setViewName("proreport");
		return mv;
	}	
	@RequestMapping(value="question",method=RequestMethod.GET)
	public ModelAndView main(@RequestParam(value="currentPage",required=false) String currentPage,@RequestParam(value="pageSize",required=false) String pageSize, ModelAndView mv){
		mv.setViewName("question");
		if(pageSize==null){
			pageSize="10";
		}
		if(currentPage==null){
			currentPage="1";
		}
		Map<String, Object> map = questionsService.queryQuestionTmplByUserId(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
		mv.addObject("questionList", map.get("list"));
		mv.addObject("page", map.get("page"));
		return mv;
	}
	@RequestMapping(value="test",method=RequestMethod.GET)
	public ModelAndView test(ModelAndView mv){
		mv.setViewName("test");
		return mv;
	}
	/**
	 * newQuestion TODO(查询所有行业)
	 * @author wangzx
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="createQuestion",method=RequestMethod.GET)
	public ModelAndView newQuestion(ModelAndView mv){
		mv.addObject("industry",  mybIndustryService.queryIndustry());
		mv.setViewName("createQuestion");
		return mv;
	}
	/**
	 * newQuestion TODO(查询所有行业)
	 * @author wangzx
	 * @param mv
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="editQuestion/{templId}",method=RequestMethod.GET)
	public ModelAndView newQuestion(@PathVariable String templId,ModelAndView mv) throws UnsupportedEncodingException{
		//查询所有行业
//		mv.addObject("industry",  mybIndustryService.queryIndustry());
		Map<String, Object> map = questionsService.queryMongodbQuestionBytempletId(templId);
		mv.addObject("templId", templId);
		mv.addObject("id", templId);
		mv.addObject("industry",  map.get("template"));
		mv.addObject("data",map.get("data"));
		mv.addObject("list",   map.get("mapQuestion"));
		mv.setViewName("editQuestion");
		return mv;
	}
	@RequestMapping(value="showQuestion",method=RequestMethod.GET)
	public ModelAndView showQuestion(String id, ModelAndView mv){
		mv.setViewName("showQuestion");
		return mv;
		
	}
}
