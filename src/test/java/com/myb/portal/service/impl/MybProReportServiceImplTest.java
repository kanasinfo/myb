package com.myb.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
 
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.myb.portal.model.mongodb.DimensionsMenbers;
import com.myb.portal.model.mongodb.QuestionDimensions;
import com.myb.portal.model.mongodb.QuestionFilter;
import com.myb.portal.service.MybProReportService;
import com.myb.portal.util.JsonUtil;
import com.myb.portal.util.Utils;
import net.sf.json.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MybProReportServiceImplTest {
	@Autowired
	MybProReportService mybProReportService;
	@Autowired
	MongoTemplate mongoTemplate;

	@Test
	public void testQueryqueryTemplateById() {
		mybProReportService.queryTemplateById("572dfa59b6c07f8dd38a2fc5");
	}

	@Test
	public void testDelTemplateFilterById() {
		mybProReportService.delTemplateFilterById("56c87d416305190f78f4b1ea", "1");
	}

	@Test
	public void testUpdateTemplateFilterById() {
		QuestionFilter questionFilter = new QuestionFilter();
		questionFilter.setLabel("111111xintianjia");
		questionFilter.setName("11111name1");
		questionFilter.setValue("11111111values2");
		mybProReportService.updateTemplateFilterById("56cc643b83de9b4860fef742", "110014a821ff4be2894195ca143cc8e4",
				JsonUtil.objectToJson(questionFilter));

	}

	@Test
	public void testAddTemplateFilter() {
		QuestionFilter questionFilter = new QuestionFilter();
		questionFilter.setFilter_id(Utils.getUUid());
		questionFilter.setLabel("xintianjia");
		questionFilter.setName("name1");
		questionFilter.setValue("values2");
		mybProReportService.addTemplateFilter("56cc643b83de9b4860fef742", JsonUtil.objectToJson(questionFilter));
	}

	@Test
	public void testDelTemplateDimensionsById() {
		// ObjectId("56cc643b83de9b4860fef742")
		mybProReportService.delTemplateDimensionsById("56cc643b83de9b4860fef742", "2d29c367eead4acd8b7f804d4a8ddf77");
	}

	@Test
	public void testUpdateTemplateDimensionsById() {
		List<DimensionsMenbers> list = new ArrayList<DimensionsMenbers>();
		DimensionsMenbers dimensionsMenbers = new DimensionsMenbers();
		dimensionsMenbers.setMemberId(Utils.getUUid());
		dimensionsMenbers.setName("dim");
		dimensionsMenbers.setValue("test");
		QuestionDimensions questionDimensions = new QuestionDimensions();
		questionDimensions.setName("test2");
		questionDimensions.setMembers(list);
		mybProReportService.updateTemplateDimensionsById("56cc03d35e861f44d404b6fd", "ab0d6f4a11ef44938b9003c80de28721",
				JsonUtil.objectToJson(questionDimensions));
	}

	@Test
	public void testAddTemplateDimensions() {
		List<DimensionsMenbers> list = new ArrayList<DimensionsMenbers>();
		DimensionsMenbers dimensionsMenbers = new DimensionsMenbers();
		dimensionsMenbers.setMemberId(Utils.getUUid());
		dimensionsMenbers.setName("dim");
		dimensionsMenbers.setValue("test");
		JSONObject jb = new JSONObject();
		jb.put("dd", "bb");
		list.add(dimensionsMenbers);
		QuestionDimensions questionDimensions = new QuestionDimensions();
		questionDimensions.setDimensionId(Utils.getUUid());
		questionDimensions.setName("test");
		questionDimensions.setMembers(list);
		mybProReportService.addTemplateDimensions("56cc643b83de9b4860fef742",
				JsonUtil.objectToJson(questionDimensions));
	}

	@Test
	public void testSum(){
		Aggregation aggregation = newAggregation(
		match(Criteria.where("questionnaire_id").is("56640f088e11a61c9a42df58")),
		unwind("$answers"),
		match(Criteria.where("answers.question_id").is("bad7aa2dd8a641f896d464260b3b7063")),
		group("$answers.option_value").count().as("count")
//		group("$answers.option_value").sum("$answers.option_value").as("num_tutorial")
		);
//		AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "test", TagCount.class);
//		List<TagCount> tagCount = results.getMappedResults();
//		System.out.println(tagCount.size());
	}

}
