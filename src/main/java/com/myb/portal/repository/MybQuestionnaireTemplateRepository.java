package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.myb.portal.model.question.MybIndustry;
import com.myb.portal.model.question.MybQuestionnaireTemplate;

public interface MybQuestionnaireTemplateRepository extends JpaRepository<MybQuestionnaireTemplate, String>{
	/**
	 * findByindustryId TODO(根据行业查询所有的模板) 
	 * @author wangzx
	 * @param industyId
	 * @return
	 */
	MybQuestionnaireTemplate findByindustryIdAndActiveFlag(String industyId,String activeFlag);
	/**
	 * findIndustryInId TODO(根据行业ID模糊查询模板) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	@Modifying
	@Query("from MybQuestionnaireTemplate where industry in ?1")
	List<MybQuestionnaireTemplate> findIndustryInId(List<MybIndustry> id);
}
