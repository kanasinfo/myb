/**
 * 
 */
package com.myb.portal.controller;

import com.google.gson.Gson;
import com.myb.portal.model.mongodb.ChartsFragement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.myb.portal.service.MybProReportService;
import com.myb.portal.support.ControllerSupport;
import com.myb.portal.util.AjaxReq;

/**
 * @author William
 *
 */
@Controller
@RequestMapping("proreport")
public class MybProReportController extends ControllerSupport {
		
	@Autowired
	MybProReportService mybProReportService;
	/**
	 * queryTemplateById TODO(根据模板主键，查询模板信息) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/queryTemplateById.json",method= RequestMethod.POST)
	@ResponseBody
	public AjaxReq queryTemplateById(String id){
		return mybProReportService.queryTemplateById(id);
	}
	/**
	 * delTemplateFilterById TODO(根据模板ID和filterID删除filter) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @return
	 */
	@RequestMapping(value="delTemplateFilterById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq delTemplateFilterById(String id,String filterId){
		return mybProReportService.delTemplateFilterById(id, filterId);
	}
	/**
	 * updateTemplateFilterById TODO(根据模板主键和filter主键,更新具体信息) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @param data
	 * @return
	 */
	@RequestMapping(value="updateTemplateFilterById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq updateTemplateFilterById(String id,String filterId,String data){
		return mybProReportService.updateTemplateFilterById(id, filterId, data);
	}
	/**
	 * addTemplateFilter TODO(保存或更新filter属性) 
	 * @author wangzx
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping(value="addTemplateFilter.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq addTemplateFilter(String id,String data){
		return mybProReportService.addTemplateFilter(id, data);
	}
	
	/**
	 * delTemplateDimensionsById TODO(根据模板ID和DimensionId删除Dimensions) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @return
	 */
	@RequestMapping(value="delTemplateDimensionsById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq delTemplateDimensionsById(String id,String dimensionId){
		return mybProReportService.delTemplateDimensionsById(id, dimensionId);
	}
	
	/**
	 * updateTemplateDimensionsById TODO(根据模板主键和Dimensions主键,更新具体信息) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @param data
	 * @return
	 */
	@RequestMapping(value="updateTemplateDimensionsById.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq updateTemplateDimensionsById(String id,String dimensionId,String data){
		return mybProReportService.updateTemplateDimensionsById(id, dimensionId, data);
	}
	/**
	 * addTemplateDimensions TODO(保存或更新Dimensions属性) 
	 * @author wangzx
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping(value="addTemplateDimensions.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq addTemplateDimensions(String id,String data){
		return mybProReportService.addTemplateDimensions(id, data);
	}

	/**
	 * addSavedDimensionsList TODO(保存或更新用户选择的维度列表) 
	 * @author dw
	 * @param id
	 * @param data
	 * @return
	 */
	@RequestMapping(value="addSavedDimensionsList.json",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq addSavedDimensionsList(String id,String data){
		return mybProReportService.addSavedDimensionsList(id, data);
	}
	
	/**
	 * querySampleCountById TODO(根据模板主键，查询模板信息) 
	 * @author dw
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/querySampleCountById.json",method= RequestMethod.POST)
	@ResponseBody
	public AjaxReq querySampleCountById(@RequestParam(value="questionnaire_id", required=true) String id,@RequestParam(value="filter", required=false) String data){
		return mybProReportService.querySampleCountById(id,data);
	}

    /**
     * 保存图片及查询信息
     * @return
     */
	@RequestMapping(value = "/saveChartsFragment", method = RequestMethod.POST)
    @ResponseBody
	public AjaxReq saveChartsFragment(String questionnaireId, ChartsFragement chartsFragement){

        return mybProReportService.saveChartsFragement(questionnaireId, chartsFragement);
    }

    @RequestMapping(value = "/deleteChartsFragment", method = RequestMethod.GET)
    @ResponseBody
    public AjaxReq deleteChartsFragment(String id, String fragementId){
        return mybProReportService.deleteChartsFragement(id, fragementId);
    }

    @RequestMapping(value = "/modal/{fragementId}", method = RequestMethod.GET)
	public String showChartsFragementModel(@PathVariable String fragementId, String questionId, Model model) {
        model.addAttribute("fragementId", fragementId);
        model.addAttribute("questionnaireId", questionId);
        return "charts/modal";
    }

    @RequestMapping(value = "/modal/{fragementId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxReq loadChartsFragementModelData(@PathVariable String fragementId, String questionId) {
        return mybProReportService.findChartsFragementById(questionId, fragementId);
    }

    @RequestMapping(value = "/comment/{fragementId}", method = RequestMethod.POST)
    @ResponseBody
    public AjaxReq saveChartsFragementComment(@PathVariable String fragementId, String comment, String questionId) {

        return mybProReportService.saveChartsFragementCommnet(fragementId, comment, questionId);
    }
}
