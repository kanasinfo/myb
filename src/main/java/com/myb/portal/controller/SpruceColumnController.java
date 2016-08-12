package com.myb.portal.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.model.content.SpruceColumn;
import com.myb.portal.service.SpruceColumnService;
import com.myb.portal.support.ControllerSupport;
import com.myb.portal.util.ExceptionHelper;

@Controller
@RequestMapping("column")
public class SpruceColumnController extends ControllerSupport{

	@Autowired
	private SpruceColumnService spruceColumnService;

	@RequestMapping(value="/showcolumn.html")
	public ModelAndView showColumn(ModelAndView mv,HttpServletResponse response){
		SpruceColumn sc = spruceColumnService.findByShortName("news");
		if(sc==null){
			try {
				response.sendError(404);
			} catch (IOException e) {
				ExceptionHelper.loggingError(e, logger);
			}
			return null;
		}else{
			mv.addObject("column", sc);
		}
		mv.setViewName("themes/column");
		return mv;
	}
	
	
	public SpruceColumnService getSpruceColumnService() {
		return spruceColumnService;
	}

	public void setSpruceColumnService(SpruceColumnService spruceColumnService) {
		this.spruceColumnService = spruceColumnService;
	}
	
	
}
