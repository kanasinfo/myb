package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.question.MybIndustry;
public interface MybIndustryRepository extends JpaRepository<MybIndustry, String>{
	/**
	 * findByparentIdOrderBysortNumberDesc TODO(查询该行业的下一级几点) 
	 * @author wangzx
	 * @param parentId
	 * @return
	 */
	List<MybIndustry> findMybIndustryByParentIndustryAndActiveFlagOrderBySortNumberAsc(MybIndustry mybIndustry,Integer activeFlag);
	List<MybIndustry> findByparentIndustryIsNullOrderBySortNumberAsc();
	
}
