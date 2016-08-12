package com.myb.portal.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myb.portal.model.question.MybIndustry;
import com.myb.portal.model.question.MybQuestionnaireTemplate;
import com.myb.portal.repository.MybIndustryRepository;
import com.myb.portal.repository.MybQuestionnaireTemplateRepository;
import com.myb.portal.service.MybIndustryService;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.ParamEnum;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class MybIndustryServiceImpl implements MybIndustryService {
	@Autowired
	MybIndustryRepository mybIndustryRepository;
	@Autowired
	MybQuestionnaireTemplateRepository mybQustnrTmpltRepository;
	@Override
	public List<MybIndustry> queryIndustry() {
		List<MybIndustry> list = null;
		try {
			 list = mybIndustryRepository.findByparentIndustryIsNullOrderBySortNumberAsc();
		} catch (Exception e) {
		}
		return list;
	}
	
	@Override
	public AjaxReq queryIndustryByParentId(String parentId) {
		AjaxReq aReq = new AjaxReq();
		try {
			if(StringUtils.isBlank(parentId)){
				aReq.setSuccess(false);
				aReq.setMessage("查询失败");
				return aReq;
			}
			MybIndustry mybIndustry = new MybIndustry();
			mybIndustry.setId(parentId);
			List<MybIndustry> listIndustry = mybIndustryRepository.findMybIndustryByParentIndustryAndActiveFlagOrderBySortNumberAsc(mybIndustry,1);
			if(listIndustry!=null&&listIndustry.size()!=0){
				List<MybQuestionnaireTemplate> listTemplate = mybQustnrTmpltRepository.findIndustryInId(listIndustry);	
				if (listTemplate.size()!=0) {
					JSONArray jArray = new JSONArray();
					JSONObject jb = null;
					for (MybIndustry industry : listIndustry) {
						jb = new JSONObject();
						jb.put("id", industry.getId());
						jb.put("name", industry.getName());
						jArray.add(jb);
					}
					aReq.setSuccess(true);
					aReq.setType(ParamEnum.INDUSTRY_TYPE_PARANT.getCode());
					aReq.setData(jArray.toString());
				}else{
					JSONArray jArray = new JSONArray();
					JSONObject jb = null;
					for (MybIndustry industry : listIndustry) {
						jb = new JSONObject();
						jb.put("id", industry.getId());
						jb.put("name", industry.getName());
						jArray.add(jb);
					}
					aReq.setSuccess(true);
					aReq.setData(jArray.toString());
					aReq.setType(ParamEnum.INDUSTRY_TYPE_TEMPL.getCode());
				}
			}else{
				aReq.setSuccess(false);
			}
			
			
			
				
		} catch (Exception e) {
			aReq.setSuccess(false);
			aReq.setMessage("查询失败");
		}
		return aReq;
	}

}
