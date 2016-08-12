package com.myb.portal.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.unwind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.myb.portal.gridFsPhoto.GridFSPhotoUtils;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.service.MybProReportService;
import com.myb.portal.service.QuestionsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class QuestionServiceTest {
	@Autowired
	QuestionsService questionsService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	GridFSPhotoUtils grid;
	@Autowired
	MybProReportService mybProReportService;
//	public void saveQuestionScoreTest(){
//		try {
//			Aggregation aggregation = newAggregation(match(Criteria.where("questionnaire_id").is("").and("").in("")),
//					unwind("$answers"), match(Criteria.where("answers.question_id").is("")),
//					group("$answers.option_value").count().as("count"));
//			AggregationResults<TagCount> results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
//			List<TagCount> tagCount = results.getMappedResults();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
////		String aa = "满意,满意,满意,满意,满意,满意,满意,满意,满意,满意";
////		questionsService.saveQuestionScore("56a87647368ce121a64857e7", "1", "总体满意度1", "您对问题师傅满意", aa);
//	}
//	@Test
//	public void delQuestionTest() throws IOException{
//		GridFS g = new GridFS(mongoTemplate.getDb(), "qr_code");
//		GridFSInputFile gfsfile = g.createFile(new File("/Users/wangzx/Desktop/123.png"));
//		gfsfile.setFilename("123");
//		gfsfile.save();
//	}
	@Test
	public void delQuestionTest1() throws IOException{
		mybProReportService.queryTemplateById("572dfa59b6c07f8dd38a2fc5");
	}
}
 