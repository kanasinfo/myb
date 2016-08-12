package com.myb.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.question.MybOption;

public interface MybOptionRepository extends JpaRepository<MybOption, String>{
	
}
