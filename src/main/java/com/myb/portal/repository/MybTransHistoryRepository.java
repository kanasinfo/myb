package com.myb.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.account.MybTransHistory;

public interface MybTransHistoryRepository extends JpaRepository<MybTransHistory, String>{

}
