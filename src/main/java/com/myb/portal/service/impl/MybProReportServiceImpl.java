package com.myb.portal.service.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.myb.portal.model.mongodb.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.myb.portal.model.chart.MybChartUtils;
import com.myb.portal.model.chart.result.TagCount;
import com.myb.portal.model.question.MybQuestion;
import com.myb.portal.model.question.MybQuestionnaireTemplate;
import com.myb.portal.repository.MybQuestionRepository;
import com.myb.portal.service.MybProReportService;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MybProReportServiceImpl implements MybProReportService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    MybQuestionRepository mybQuestionRepository;

    public AjaxReq queryTemplateById(String id) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("数据不正确");
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("qustnnrId").is(id);
            query.addCriteria(criterg);
            ReleaseQuestionVo releaseQuestionVo = mongoTemplate.findOne(query, ReleaseQuestionVo.class);
            MybQuestionnaireTemplate m = new MybQuestionnaireTemplate();
            m.setId(releaseQuestionVo.getQustnrTmpltId());

            // 查询ArchiveCharts
            ArchiveCharts archiveCharts = mongoTemplate.findOne(Query.query(Criteria.where("questionId").is(id)), ArchiveCharts.class);
            if(archiveCharts != null) {
                List<ChartsFragement> chartsFragements = archiveCharts.getChartsFragements();
                releaseQuestionVo.setChartsFragements(chartsFragements);
            }
            try {
                List<MybQuestion> list = mybQuestionRepository.findByquestionTemplate(m);
                Map<String, MybQuestion> map = new HashMap<String, MybQuestion>();
                for (MybQuestion mybQuestion : list) {
                    map.put(mybQuestion.getId(), mybQuestion);
                }
                for (QuestionsVo questionsVo : releaseQuestionVo.getQuestions()) {
                    if (map.get(questionsVo.getQuestionId()) != null) {
                        questionsVo.setNormCalculateValue(map.get(questionsVo.getQuestionId()).getNormCalculateValue() == null ? 0 : map.get(questionsVo.getQuestionId()).getNormCalculateValue());
                        questionsVo.setNormInputValue(map.get(questionsVo.getQuestionId()).getNormInputValue() == null ? 0 : map.get(questionsVo.getQuestionId()).getNormInputValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                ajaxReq.setSuccess(true);
                ajaxReq.setData(JsonUtil.objectToJsonObject(releaseQuestionVo));
            }
            ajaxReq.setSuccess(true);
            ajaxReq.setData(JsonUtil.objectToJsonObject(releaseQuestionVo));
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("业务处理失败，请稍后重试！");
        }
        return ajaxReq;
    }

    /**
     * delTemplateFilterById TODO(根据模板ID和filterID删除filter)
     *
     * @param id
     * @param filterId
     * @return
     * @author wangzx
     */
    @Transactional
    public AjaxReq delTemplateFilterById(String id, String filterId) {
        AjaxReq aReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                aReq.setSuccess(false);
                aReq.setMessage("数据错误请稍后重试");
                return aReq;
            }
            if (StringUtils.isBlank(filterId)) {
                aReq.setSuccess(false);
                aReq.setMessage("数据错误请稍后重试");
                return aReq;
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            update.pull("filters", new BasicDBObject("filter_id", filterId));
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            aReq.setSuccess(true);
            aReq.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            aReq.setMessage("数据错误请稍后重试");
        }
        return aReq;
    }

    /**
     * updateTemplateFilterById TODO(根据模板主键和filter主键,更新具体信息)
     *
     * @param id
     * @param filterId
     * @param data
     * @return
     * @author wangzx
     */
    @Transactional
    public AjaxReq updateTemplateFilterById(String id, String filterId, String data) {
        AjaxReq aReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            if (StringUtils.isBlank(filterId)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            if (StringUtils.isBlank(data)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            criterg.andOperator(Criteria.where("filters").elemMatch(Criteria.where("filter_id").is(filterId)));
            query.addCriteria(criterg);
            Update update = new Update();
            QuestionFilter questionFilter = JsonUtil.jsonToObject(data, QuestionFilter.class);
            update.set("filters.$.name", questionFilter.getName());
            update.set("filters.$.value", questionFilter.getValue());
            update.set("filters.$.label", questionFilter.getLabel());
            update.set("filters.$.period", questionFilter.getPeriod());
            update.set("filters.$.store", questionFilter.getStore());
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            aReq.setSuccess(true);
            aReq.setMessage("添加成功");
        } catch (Exception e) {
            aReq.setSuccess(false);
            aReq.setMessage("更新失败");
        }
        return aReq;
    }

    /**
     * addTemplateFilter TODO(保存或更新filter属性)
     *
     * @param id
     * @param data
     * @return
     * @author wangzx
     */
    @Transactional
    public AjaxReq addTemplateFilter(String id, String data) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            if (StringUtils.isBlank(data)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            update.addToSet("filters", JsonUtil.jsonToObject(data, QuestionFilter.class));
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("添加成功");
        } catch (Exception e) {
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("添加失败");
            return ajaxReq;
        }
        return ajaxReq;
    }

    /**
     * delTemplateDimensionsById TODO(根据模板ID和filterID删除filter)
     *
     * @param id
     * @return
     * @author wangzx
     */
    @Transactional
    public AjaxReq delTemplateDimensionsById(String id, String dimensionId) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            if (StringUtils.isBlank(dimensionId)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }

            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            update.pull("dimensions", new BasicDBObject("dimensionId", dimensionId));
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("数据错误请稍后重试");
        }
        return ajaxReq;
    }

    /**
     * updateTemplateDimensionsById TODO(根据模板主键和filter主键,更新具体信息)
     *
     * @param id
     * @param data
     * @return
     * @author wangzx
     */
    public AjaxReq updateTemplateDimensionsById(String id, String dimensionId, String data) {
        AjaxReq aReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            if (StringUtils.isBlank(dimensionId)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            if (StringUtils.isBlank(data)) {
                aReq.setSuccess(false);
                aReq.setMessage("删除失败");
                return aReq;
            }
            this.delTemplateDimensionsById(id, dimensionId);
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            JSONObject jb = JSONObject.fromObject(data);
            QuestionDimensions questionDimensions = new QuestionDimensions();
            questionDimensions.setDimensionId(jb.getString("dimensionId"));
            questionDimensions.setName(jb.getString("name"));
            JSONArray jArray = jb.getJSONArray("members");
            List<DimensionsMenbers> list = new ArrayList<DimensionsMenbers>();
            DimensionsMenbers dimensionsMenbers = null;
            for (int i = 0; i < jArray.size(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);
                dimensionsMenbers = new DimensionsMenbers();
                dimensionsMenbers.setMemberId(jsonObject.getString("memberId"));
                dimensionsMenbers.setName(jsonObject.getString("name"));
                dimensionsMenbers.setPeriod(jsonObject.getString("period"));
                dimensionsMenbers.setStore(jsonObject.getString("store"));
                dimensionsMenbers.setValue(jsonObject.getString("value"));
                dimensionsMenbers.setLabel(jsonObject.getString("label"));
                list.add(dimensionsMenbers);
            }
            questionDimensions.setMembers(list);
            update.addToSet("dimensions", questionDimensions);
//			QuestionDimensions questionFilter = JsonUtil.jsonToObject(data, QuestionDimensions.class);
//			update.set("dimensions.$.name", questionFilter.getName());
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            aReq.setSuccess(true);
            aReq.setMessage("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            aReq.setSuccess(false);
            aReq.setMessage("更新失败");
        }
        return aReq;
    }

    /**
     * addTemplateDimensions TODO(保存或更新filter属性)
     *
     * @param id
     * @param data
     * @return
     * @author wangzx
     */
    public AjaxReq addTemplateDimensions(String id, String data) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            if (StringUtils.isBlank(data)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            JSONObject jb = JSONObject.fromObject(data);
            QuestionDimensions questionDimensions = new QuestionDimensions();
            questionDimensions.setDimensionId(jb.getString("dimensionId"));
            questionDimensions.setName(jb.getString("name"));
            JSONArray jArray = jb.getJSONArray("members");
            List<DimensionsMenbers> list = new ArrayList<DimensionsMenbers>();
            DimensionsMenbers dimensionsMenbers = null;
            for (int i = 0; i < jArray.size(); i++) {
                JSONObject jsonObject = jArray.getJSONObject(i);
                dimensionsMenbers = new DimensionsMenbers();
                dimensionsMenbers.setMemberId(jsonObject.getString("memberId"));
                dimensionsMenbers.setName(jsonObject.getString("name"));
                dimensionsMenbers.setPeriod(jsonObject.getString("period"));
                dimensionsMenbers.setStore(jsonObject.getString("store"));
                dimensionsMenbers.setValue(jsonObject.getString("value"));
                dimensionsMenbers.setLabel(jsonObject.getString("label"));
                list.add(dimensionsMenbers);
            }
            questionDimensions.setMembers(list);
            update.addToSet("dimensions", questionDimensions);
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("添加失败");
            return ajaxReq;
        }
        return ajaxReq;
    }


    /**
     * addSavedDimensionsList TODO(保存或更新选中的Dimensions)
     *
     * @param id
     * @param data
     * @return
     * @author dw
     */
    public AjaxReq addSavedDimensionsList(String id, String data) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            if (StringUtils.isBlank(data)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("添加失败");
                return ajaxReq;
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("_id").is(id);
            query.addCriteria(criterg);
            Update update = new Update();
            update.unset("savedDimnTab");
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            update = new Update();
            update.addToSet("savedDimnTab", data);
            mongoTemplate.updateMulti(query, update, ReleaseQuestionVo.class);
            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("添加失败");
            return ajaxReq;
        }
        return ajaxReq;
    }

    @Override
    public AjaxReq querySampleCountById(String id, String data) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            if (StringUtils.isBlank(id)) {
                ajaxReq.setSuccess(false);
                ajaxReq.setMessage("数据不正确");
            }
            Query query = new Query();
            Criteria criterg = Criteria.where("qustnnrId").is(id);
            query.addCriteria(criterg);
            Map<String, List<Criteria>> fdmp = new HashMap<String, List<Criteria>>();
            MybChartUtils.packDimensionsFilter("", data, fdmp);
            Criteria ct = null;
            if (fdmp.get("filterOnly") != null) {
                ct = new Criteria().andOperator(fdmp.get("filterOnly").toArray(new Criteria[fdmp.get("filterOnly").size()]));
            }
            Aggregation aggregation;
            AggregationResults<TagCount> results = null;
            List<TagCount> tagCount = null;
            int totalCount = 0;
            //Calculate total count
            aggregation = newAggregation(match(Criteria.where("questionnaireId").is(id)), match(ct),
                    group("$questionnaireId").count().as("count"));
            results = mongoTemplate.aggregate(aggregation, "answer", TagCount.class);
            tagCount = results.getMappedResults();
            if (tagCount.size() > 0) {
                totalCount = tagCount.get(0).getCount();
            }
            ajaxReq.setSuccess(true);
            ajaxReq.setData("{'totalCount':" + totalCount + "}");
        } catch (Exception e) {
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("业务处理失败，请稍后重试！");
        }
        return ajaxReq;
    }

    @Override
    public AjaxReq saveChartsFragement(String questionId, ChartsFragement chartsFragement) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            Query query = new Query();
            Criteria criterg = Criteria.where("questionId").is(questionId);
            query.addCriteria(criterg);

            ArchiveCharts archiveCharts = mongoTemplate.findOne(query, ArchiveCharts.class);


            if (archiveCharts == null) {
                archiveCharts = new ArchiveCharts();
                archiveCharts.setQuestionId(questionId);
                archiveCharts.getChartsFragements().add(chartsFragement);
                mongoTemplate.save(archiveCharts);
            }else{
                Update update = new Update();
                update.addToSet("chartsFragements", chartsFragement);
                mongoTemplate.updateMulti(query, update, ArchiveCharts.class);
            }


            ajaxReq.setData(chartsFragement.getFragementId());
            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("保存失败");
            return ajaxReq;
        }
        return ajaxReq;
    }

    @Override
    public AjaxReq deleteChartsFragement(String id, String fragementId) {
        AjaxReq ajaxReq = new AjaxReq();
        try {
            Query query = new Query();
            Criteria criteria = Criteria.where("questionId").is(id);
            query.addCriteria(criteria);

            Update update = new Update();
            update.pull("chartsFragements", Query.query(Criteria.where("fragementId").is(fragementId)));
            mongoTemplate.updateMulti(query, update, ArchiveCharts.class);

            ajaxReq.setSuccess(true);
            ajaxReq.setMessage("保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            ajaxReq.setSuccess(false);
            ajaxReq.setMessage("保存失败");
            return ajaxReq;
        }
        return ajaxReq;
    }

    @Override
    public AjaxReq findChartsFragementById(String id, String fragementId) {
        AjaxReq ajaxReq = new AjaxReq();
        ChartsFragement fragement = null;
        ArchiveCharts archiveCharts = mongoTemplate.findOne(Query.query(Criteria.where("questionId").is(id)), ArchiveCharts.class);
        List<ChartsFragement> chartsFragements = archiveCharts.getChartsFragements();
        int index = 0;
        if (chartsFragements != null && !chartsFragements.isEmpty()) {
            for (ChartsFragement chartsFragement : chartsFragements) {
                if (chartsFragement.getFragementId().equals(fragementId)) {
                    fragement = chartsFragement;
                    index = chartsFragements.indexOf(chartsFragement);
                    break;
                }
            }

            if(index > 0)
                fragement.setLeft(chartsFragements.get(index - 1).getFragementId());
            if(index < chartsFragements.size() - 1)
                fragement.setRight(chartsFragements.get(index + 1).getFragementId());
        }

        ajaxReq.setSuccess(true);
        ajaxReq.setData(fragement);
        return ajaxReq;
    }


}
