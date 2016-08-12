package com.myb.portal.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myb.portal.model.account.MybStoreGroup;
import com.myb.portal.repository.MybQuestionRepository;
import com.myb.portal.service.MybAccountService;
import com.myb.portal.util.AjaxReq;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybAccountServiceImplTest {
	@Autowired
	MybAccountService mybAccountService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	MybQuestionRepository mybQuestion;
	@Test
	public void saveGroupTest(){
		AjaxReq aReq =  mybAccountService.saveGroup("分组2");
		System.out.println(aReq.isSuccess());
		MybStoreGroup m =(MybStoreGroup)aReq.getData();
		System.out.println(m.getId());
		String[] storeId = {"123","2"};
		mybAccountService.saveGroupAndGroup(m.getId(), storeId);
	}

}
