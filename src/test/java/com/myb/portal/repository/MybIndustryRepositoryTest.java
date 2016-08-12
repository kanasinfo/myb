package com.myb.portal.repository;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myb.portal.model.question.MybIndustry;
import com.myb.portal.util.Utils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybIndustryRepositoryTest {
	// ApplicationContext applicationContext = null;
	@Autowired
	MybIndustryRepository mybIndustryRepository;
	@Test
	public void svaeIndustyryTest(){
		MybIndustry mybIndustry	 = new MybIndustry();
		String parentId = Utils.getUUid();
		//保存根行业
		mybIndustry.setId(parentId);
		mybIndustry.setName("餐饮");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(1);
		mybIndustryRepository.save(mybIndustry);
		
		//保存子行业
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("中式正餐");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(1);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("西式正餐");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(2);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("中式快餐");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(3);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("西式快餐");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(4);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("咖啡厅");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(5);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("西点房");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(6);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("外卖/订餐服务");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(7);
		mybIndustryRepository.save(mybIndustry);
		mybIndustry.setId(Utils.getUUid());
		mybIndustry.setName("银行");
		mybIndustry.setCreateTime(new Date());
		mybIndustry.setUpdateTime(new Date());
		mybIndustry.setSortNumber(8);
		mybIndustryRepository.save(mybIndustry);
	}

	/**
	 * TestFindByparentIdIsNullOrderBysortNumberDesc TODO(查询跟节点)
	 * 
	 * @author wangzx
	 */
	@Test
	public void findByParentIdIsNullOrderBySortNumberAscTest() {
//		List<MybIndustry> list = mybIndustryRepository.findByParentIdIsNullOrderBySortNumberAsc();
//		for (MybIndustry mybIndustry : list) {
//			System.out.println(mybIndustry.getName());
//		}

	}

	@Test
	public void findByparentIdOrderBySortNumberAscTest() {
//		List<MybIndustry> list = mybIndustryRepository.findByparentIdOrderBySortNumberAsc("14f9997d735b4f84a82965b22fac5fb4");
//		for (MybIndustry mybIndustry : list) {
//			System.out.println(mybIndustry.getName());
//		}
	}
}
