package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.question.MybQuestion;
import com.myb.portal.model.question.MybQuestionnaireTemplate;

public interface MybQuestionRepository extends JpaRepository<MybQuestion, String>{
	/**
	 * findByQustnrTempIdOrderBysortNumAsc TODO(根据模板查询问题) 
	 * @author wangzx
	 * @param tempId
	 * @return
	 */
	List<MybQuestion> findByquestionTemplate(MybQuestionnaireTemplate tempId);
//	/**
//	 * findByqustnrGroupIdOrderBysortNumAsc TODO(根据分组查询所有问题) 
//	 * @author wangzx
//	 * @param groupId
//	 * @return
//	 */
//	List<MybQuestion> findByqustnrGroupIdOrderBysortNumAsc(String groupId);
}
