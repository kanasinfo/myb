package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.account.MybStoreGroup;

public interface MybStoreGroupRepository extends JpaRepository<MybStoreGroup, String>{
	public List<MybStoreGroup> findByAccountId(String accountId);
	public MybStoreGroup findByAccountIdAndId(String accountId,String id);
	public List<MybStoreGroup> findByidIn(List<String> asList);
	
}
