package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.myb.portal.model.question.MybQuestionGroup;
import com.myb.portal.model.question.MybQuestionnaireTemplate;

public interface MybQuestionGroupRepository extends JpaRepository<MybQuestionGroup, String>{
	/**
	 * findAllOrderBysortNumAsc TODO(查询模板分组) 
	 * @author wangzx
	 * @return
	 */
	MybQuestionGroup findByid(String id);
	List<MybQuestionGroup> findAll();
	@Modifying
//	@Query("update MybQuestionGroup a set a.displayValue = ?1 where a.id=?2")
//	void updateQuestionGroupDisplayValByQuestionType(String display,String questionGroupId);
	List<MybQuestionGroup> findMybQuestionGroupByQuestionnaireTemplateOrderBySortNumberAsc(MybQuestionnaireTemplate Template);
}
