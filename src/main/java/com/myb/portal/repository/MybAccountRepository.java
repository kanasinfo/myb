package com.myb.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.myb.portal.model.account.MybAccount;

public interface MybAccountRepository extends JpaRepository<MybAccount, String>{
	MybAccount findByloginEmail(String loginEmail);
	@Modifying @Query("update MybAccount set creaditAmount = creaditAmount-?1 where id=?2")
	void updateAmountById(int amount,String accountId);
}
