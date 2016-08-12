package com.myb.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.myb.portal.model.account.MybStore;

public interface MybStoreRepository extends JpaRepository<MybStore, String>{
	/**
	 * findByaccountIdOrderBystoreSortAsc TODO(根据用户ID查询门店) 
	 * @author wangzx
	 * @param accountId
	 * @return
	 */
	List<MybStore> findByAccountIdOrderByStoreSortAsc(String accountId);
	/**
	 * findById TODO(根据ID查询门店) 
	 * @author wangzx
	 * @param id
	 * @return
	 */
	List<MybStore> findByidIn(List<String> asList);
	@Modifying
	@Query("DELETE FROM MybStore h WHERE h.id in (?1)")
	public void deleteStoreById(List<String> list);
}
