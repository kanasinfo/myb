package com.myb.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myb.portal.model.account.MybAccount;

public interface MybAccountRepository extends JpaRepository<MybAccount, String>{
	MybAccount findByloginEmail(String loginEmail);
}
