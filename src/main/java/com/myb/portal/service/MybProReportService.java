package com.myb.portal.service;
/**
 * <p>Title: MybProReportService</p>
 * <p>Description: (统计workSpace业务处理)</p>
 * <p>date : 2016年2月22日 上午9:54:03 </p>
 * @author wangzx
 * @version 1.0
 */
import com.myb.portal.model.mongodb.ChartsFragement;
import com.myb.portal.util.AjaxReq;

public interface MybProReportService {
	/**
	 * queryqueryTemplateById TODO(根据模板主键查询模板信息) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	AjaxReq queryTemplateById(String id);
	
	/**
	 * delTemplateFilterById TODO(根据模板ID和filterID删除filter) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @return
	 */
	public AjaxReq delTemplateFilterById(String id,String filterId);
	/**
	 * updateTemplateFilterById TODO(根据模板主键和filter主键,更新具体信息) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @param data
	 * @return
	 */
	public AjaxReq updateTemplateFilterById(String id,String filterId,String data);
	/**
	 * addTemplateFilter TODO(保存或更新filter属性) 
	 * @author wangzx
	 * @param id
	 * @param data
	 * @return
	 */
	public AjaxReq addTemplateFilter(String id,String data);
	/**
	 * delTemplateDimensionsById TODO(根据模板ID和DimensionId删除Dimensions) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @return
	 */
	public AjaxReq delTemplateDimensionsById(String id,String dimensionId);
	/**
	 * updateTemplateDimensionsById TODO(根据模板主键和Dimensions主键,更新具体信息) 
	 * @author wangzx
	 * @param id
	 * @param filterId
	 * @param data
	 * @return
	 */
	public AjaxReq updateTemplateDimensionsById(String id,String dimensionId,String data);
	/**
	 * addTemplateDimensions TODO(保存或更新Dimensions属性) 
	 * @author wangzx
	 * @param id
	 * @param data
	 * @return
	 */
	public AjaxReq addTemplateDimensions(String id,String data);

	/**
	 * addSavedDimensionsList TODO(保存或更新选中的Dimensions) 
	 * @author dw
	 * @param id
	 * @param data
	 * @return
	 */
	public AjaxReq addSavedDimensionsList(String id,String data);
	
	/**
	 * querySampleCountById TODO(查询当前条件下的答卷总量) 
	 * @author dw
	 * @param id
	 * @param data
	 * @return
	 */
	public AjaxReq querySampleCountById(String id,String data);


    /**
     * 保存统计图片及查询条件
     */
    AjaxReq saveChartsFragement(String questionId, ChartsFragement chartsFragement);

    AjaxReq deleteChartsFragement(String id, String fragementId);

	AjaxReq findChartsFragementById(String id, String fragementId);
}
