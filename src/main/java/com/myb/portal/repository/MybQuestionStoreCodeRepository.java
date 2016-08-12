package com.myb.portal.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.question.MybQuestionStoreCode;

public interface MybQuestionStoreCodeRepository extends JpaRepository<MybQuestionStoreCode, String>{
	public List<MybQuestionStoreCode> findByquestionId(String questionId);
}
