package com.myb.portal.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myb.portal.gridFsPhoto.GridFSPhotoUtils;
import com.myb.portal.model.account.MybStore;
import com.myb.portal.model.account.MybStoreGroup;
import com.myb.portal.model.mongodb.ActivePeriod;
import com.myb.portal.model.mongodb.QuestionTmpltVO;
import com.myb.portal.model.mongodb.QuestionsVo;
import com.myb.portal.model.mongodb.ReleaseOnlyOneAnwser;
import com.myb.portal.model.mongodb.ReleaseOnlyOneAnwserData;
import com.myb.portal.model.mongodb.ReleaseQuestionVo;
import com.myb.portal.model.mongodb.Store;
import com.myb.portal.model.mongodb.StoreGroupVO;
import com.myb.portal.model.mongodb.StoreList;
import com.myb.portal.repository.MybStoreGroupRepository;
import com.myb.portal.repository.MybStoreRepository;
import com.myb.portal.service.MybReleaseService;
import com.myb.portal.service.QuestionsService;
import com.myb.portal.shiro.ShiroDb;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.JsonUtil;
import com.myb.portal.util.Utils;
import com.myb.portal.util.ZipCompressorByAnt;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MybReleaseServiceImpl implements MybReleaseService {
	@Autowired
	MongoTemplate mongoTemplate;
	@Autowired
	GridFSPhotoUtils gridFSPhotoUtils;
	@Autowired
	MybStoreRepository mybStoreRepository;
	@Autowired
	MybStoreGroupRepository mybStoreGroupRepository;
	@Autowired
	QuestionsService questionsService;
	@Autowired
	EntityManager em;

	@Override
	public Map<String, Object> releaseQuestion(String id) throws IOException {
		Query query = new Query();
		Criteria criterg = Criteria.where("id").is(id);
		query.addCriteria(criterg);
		ReleaseQuestionVo releaseQuestionVo = mongoTemplate.findOne(query, ReleaseQuestionVo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询未发布的question模板
		Query queryQustnnr = new Query();
		Criteria critergQustnnr = Criteria.where("_id").is(id);
		queryQustnnr.addCriteria(critergQustnnr);
		Update update = new Update();
		update.set("qustnnrStatus", 0);
		mongoTemplate.updateMulti(queryQustnnr, update, QuestionTmpltVO.class);
		QuestionTmpltVO questionTmpltVO = mongoTemplate.findOne(queryQustnnr, QuestionTmpltVO.class);
		if (releaseQuestionVo != null) {
			contrastQuestion(questionTmpltVO, releaseQuestionVo);
			mongoTemplate.remove(queryQustnnr, "qustnnr");
			mongoTemplate.save(questionTmpltVO);
			mongoTemplate.remove(query, "release_qustnnr");
			gridFSPhotoUtils.removeFile(id);
		} else {
			unCheckQuestion(questionTmpltVO);
			mongoTemplate.remove(queryQustnnr, "qustnnr");
			mongoTemplate.save(questionTmpltVO);
		}

		// String questionJson = JsonUtil.objectToJson(questionTmpltVO);
		// 保存到发布中
		questionTmpltVO.setQustnnrId(id);
		mongoTemplate.save(questionTmpltVO, "release_qustnnr");
		// 生成URL连接
		gridFSPhotoUtils.saveFile(Utils.ParseProperties("RELEASE_URL") + id + ".html", id,
				questionTmpltVO.getQustnrName());
		map.put("imgurl", gridFSPhotoUtils.getFileById(id));
		return map;
	}

	public String getQRurl(String id) {
		String url = Utils.ParseProperties("RELEASE_URL") + id + ".html";
		return url;
	}

	public void unCheckQuestion(QuestionTmpltVO questionTmpltVO) {
		List<ActivePeriod> list = new ArrayList<ActivePeriod>();
		ActivePeriod a = new ActivePeriod();
		a.setStartTime(Utils.getDate());
		a.setEndTime("");
		list.add(a);
		for (QuestionsVo q : questionTmpltVO.getQuestions()) {
			if (q.isActiveFlag()) {
				q.setActivePeriod(list);
			}
		}
	}

	public void contrastQuestion(QuestionTmpltVO questionTmpltVO, ReleaseQuestionVo releaseQuestionVo) {
		Map<String, QuestionsVo> mapRelease = new HashMap<String, QuestionsVo>();
		ActivePeriod a = new ActivePeriod();
		a.setStartTime(Utils.getDate());
		a.setEndTime("");
		for (QuestionsVo rq : releaseQuestionVo.getQuestions()) {
			mapRelease.put(rq.getQuestionId(), rq);
		}
		QuestionsVo oldQuestion = null;
		for (QuestionsVo r : questionTmpltVO.getQuestions()) {
			oldQuestion = mapRelease.get(r.getQuestionId());
			if (oldQuestion != null) {
				if (r.isActiveFlag() != oldQuestion.isActiveFlag()) {
					if (r.isActiveFlag() == false && oldQuestion.isActiveFlag() == true) {
						for (ActivePeriod ac : r.getActivePeriod()) {
							if (ac.getEndTime() == null) {
								ac.setEndTime(Utils.getDate());
							}
						}
					} else if (r.isActiveFlag()) {
						r.getActivePeriod().add(a);
					}
				}
			}
		}

	}

	/**
	 * uploadXls TODO(上传门店信息)
	 * 
	 * @author wangzx
	 * @param input
	 * @return
	 */
	public AjaxReq uploadXls(InputStream input, AjaxReq req) {
		try {
			String accountId = ShiroDb.getAccount().getId();
			// 查询已有的门店信息
			List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
			Map<String, MybStore> map = new HashMap<String, MybStore>();
			// 把数据封装到map中，为去重复提供便利
			for (MybStore mybStore : myblist) {
				map.put(mybStore.getName(), mybStore);
			}
			req.setSuccess(true);
			List<MybStore> list = new ArrayList<MybStore>();
			XSSFWorkbook work = new XSSFWorkbook(input);
			XSSFSheet sheet = work.getSheetAt(0);
			MybStore mybStore = null;
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				Cell cell = null;
				Row row = sheet.getRow(i);
				if (row.getPhysicalNumberOfCells() != 6) {
					req.setMessage("上传失败");
					req.setSuccess(false);
				} else {
					mybStore = new MybStore();
					mybStore.setId(Utils.getUUid());
					mybStore.setAccountId(accountId);
					// 获取门店名称
					cell = row.getCell(0);
					mybStore.setName(cell.getStringCellValue());
					// 门店管理者名称
					cell = row.getCell(1);
					mybStore.setManagerName(cell.getStringCellValue());
					// 门店管理者电话
					cell = row.getCell(2);
					mybStore.setManagerPhone(Integer.parseInt(cell.getStringCellValue()));
					// 门店管理者Email
					cell = row.getCell(3);
					mybStore.setManagerEmail(cell.getStringCellValue());
					// 门店管理者微信号
					cell = row.getCell(4);
					mybStore.setManagerWechatNumber(cell.getStringCellValue());
					// 门店地址
					cell = row.getCell(5);
					mybStore.setAddress(cell.getStringCellValue());
					list.add(mybStore);
				}

			}
			if (req.isSuccess()) {
				for (int j = 0; j < list.size(); j++) {
					em.persist(list.get(j));
					if (j % 30 == 0) {
						em.flush();
						em.clear();
					}
				}
				work.close();
				req.setMessage("上传成功");
				req.setSuccess(true);
				return req;
			} else {
				work.close();
				req.setMessage("格式不正确");
				req.setSuccess(false);
				return req;
			}
		} catch (Exception e) {
			req.setMessage("上传失败");
			req.setSuccess(false);
		}
		req.setMessage("上传失败");
		req.setSuccess(false);
		return req;
	}

	/**
	 * addStore TODO(这里用一句话描述这个类的作用)
	 * 
	 * @author wangzx
	 * @param store
	 * @return
	 */
	@Transactional
	public AjaxReq addStore(String store) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(store)) {
				ar.setMessage("参数不能为空！");
				return ar;
			}
			JSONArray ja = JSONArray.fromObject(store);
			String accountId = ShiroDb.getAccount().getId();
			List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
			Map<String, MybStore> map = new HashMap<String, MybStore>();
			// 把数据封装到map中，为去重复提供便利
			for (MybStore mybStore : myblist) {
				map.put(mybStore.getName(), mybStore);
			}
			List<MybStore> list = new ArrayList<MybStore>();
			MybStore mybStore = null;
			for (int i = 0; i < ja.size(); i++) {
				if (map.get(ja.get(i).toString()) == null) {
					JSONObject jb = ja.getJSONObject(i);
					mybStore = new MybStore();
					mybStore.setId(Utils.getUUid());
					mybStore.setAccountId(accountId);
					mybStore.setName(jb.getString("storeName"));
					mybStore.setManagerName(jb.getString("managerName"));
					mybStore.setManagerEmail(jb.getString("managerEmail"));
					mybStore.setManagerPhone(jb.getInt("managerPhone"));
					mybStore.setManagerWechatNumber(jb.getString("managerNumber"));
					;
					list.add(mybStore);
				}
			}
			for (int j = 0; j < list.size(); j++) {
				em.persist(list.get(j));
				if (j % 30 == 0) {
					em.flush();
					em.clear();
				}
			}
			ar.setMessage("保存成功");
			ar.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ar;
	}

	/**
	 * queryGroupStoreById TODO(查询所有的门店)
	 * 
	 * @author wangzx
	 * @return
	 */
	@Transactional
	public AjaxReq queryGroupStoreByAccountId(String groupId) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				ar.setMessage("查询失败!");
			}
			String accountId = ShiroDb.getAccount().getId();
			// 查询所有的store
			MybStoreGroup myb = mybStoreGroupRepository.findByAccountIdAndId(accountId, groupId);
			JSONObject jb = new JSONObject();
			JSONArray jaStore = new JSONArray();
			JSONObject jbParent = null;
			if (myb != null) {
				for (MybStore mybStore : myb.getMybStore()) {
					jbParent = new JSONObject();
					jbParent.put("storeName", mybStore.getName());
					jbParent.put("storeId", mybStore.getId());
					jaStore.add(jbParent);
				}
			}

			jb.put("store", jaStore);
			ar.setData(jb);
			ar.setSuccess(true);
		} catch (Exception e) {
			ar.setMessage("查询失败");
			ar.setSuccess(false);
		}
		return ar;
	}

	/**
	 * queryGroupStoreById TODO(查询所有的门店)
	 * 
	 * @author wangzx
	 * @return
	 */
	@Transactional
	public AjaxReq queryAllStoreByAccountId() {
		AjaxReq ar = new AjaxReq();
		try {
			// 查询所有的group
			String accountId = ShiroDb.getAccount().getId();
			List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
			List<MybStoreGroup> mybGroup = mybStoreGroupRepository.findByAccountId(accountId);
			JSONObject jb = new JSONObject();
			JSONArray jaStore = new JSONArray();
			JSONObject jbParent = null;
			for (MybStore mybStore : myblist) {
				jbParent = new JSONObject();
				jbParent.put("storeName", mybStore.getName());
				jbParent.put("storeId", mybStore.getId());
				String groupName = "";
				for (MybStoreGroup s : mybStore.getMybStoreGroup()) {
					groupName += s.getName() + ",";
				}
				if (groupName.equals("")) {
					jbParent.put("group", groupName);
				} else {
					groupName = groupName.substring(0, groupName.length() - 1);
					jbParent.put("group", groupName);
				}

				jaStore.add(jbParent);
			}
			jb.put("store", jaStore);
			JSONArray jaGroup = new JSONArray();
			for (MybStoreGroup mybStoreGroup : mybGroup) {
				jbParent = new JSONObject();
				jbParent.put("groupName", mybStoreGroup.getName());
				jbParent.put("groupId", mybStoreGroup.getId());
				jaGroup.add(jbParent);
			}
			jb.put("group", jaGroup);
			ar.setSuccess(true);
			ar.setData(jb);
			// 查询所有门店
		} catch (Exception e) {
			ar.setMessage("查询失败");
			ar.setSuccess(false);
		}
		return ar;
	}

	/**
	 * addStoreGroup TODO(添加group分组)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	@Transactional
	public AjaxReq addGroup(String id, String groupName) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(id)) {
				ar.setMessage("保存失败！");
			}
			if (StringUtils.isBlank(groupName)) {
				ar.setMessage("保存失败！");
			}
			String accountId = ShiroDb.getAccount().getId();
			MybStoreGroup mybStoreGroup = new MybStoreGroup();
			mybStoreGroup.setId(id);
			mybStoreGroup.setName(groupName);
			mybStoreGroup.setAccountId(accountId);
			mybStoreGroupRepository.save(mybStoreGroup);
			ar.setMessage("保存成功");
			ar.setSuccess(true);
		} catch (Exception e) {
			ar.setMessage("保存失败！");
		}
		return ar;
	}

	/**
	 * addStoreGroup TODO(添加分组与门店关联关系)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @param storeId
	 * @return
	 */
	public AjaxReq addStoreGroup(String groupId, String storeId) {

		return null;
	}

	/**
	 * delStore TODO(删除分组与门店关联关系)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	@Transactional
	public AjaxReq delStore(String storeId) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(storeId)) {
				ar.setMessage("删除失败！");
				return ar;
			}
			JSONArray ja = JSONArray.fromObject(storeId);
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < ja.size(); i++) {
				list.add(ja.getString(i));
			}
			mybStoreRepository.deleteStoreById(list);
			ar.setMessage("删除成功！");
			ar.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return ar;
	}

	/**
	 * delGroupById TODO(根据groupid删除分组)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	@Transactional
	public AjaxReq delGroupById(String groupId) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				ar.setMessage("删除失败");
				return ar;
			}
			mybStoreGroupRepository.delete(groupId);
			ar.setSuccess(true);
			ar.setMessage("删除成功");
		} catch (Exception e) {
			ar.setMessage("删除失败");
		}
		return ar;
	}

	/**
	 * addStoreToGroup TODO(添加group和store关联关系)
	 * 
	 * @author wangzx
	 * @param id
	 * @param groupId
	 * @return
	 */
	@Transactional
	public AjaxReq addStoreToGroup(String id, String groupId) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(id)) {
				ar.setMessage("添加失败");
				return ar;
			}
			if (StringUtils.isBlank(groupId)) {
				ar.setMessage("添加失败");
				return ar;
			}
			Set<MybStore> store = new HashSet<MybStore>();
			JSONArray ja = JSONArray.fromObject(id);
			String accountId = ShiroDb.getAccount().getId();
			List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
			Map<String, MybStore> map = new HashMap<String, MybStore>();
			for (MybStore mybStoreList : myblist) {
				map.put(mybStoreList.getId(), mybStoreList);
			}
			for (int i = 0; i < ja.size(); i++) {
				if (map.get(ja.getString(i)) != null) {
					store.add(map.get(ja.getString(i)));
				}
			}
			MybStoreGroup myb = mybStoreGroupRepository.findByAccountIdAndId(accountId, groupId);
			myb.setMybStore(store);
			mybStoreGroupRepository.save(myb);
			ar.setMessage("保存成功！");
			ar.setSuccess(true);
			// 查询数据
		} catch (Exception e) {
			e.printStackTrace();
			ar.setMessage("添加失败");
		}
		return ar;
	}

	/**
	 * delGroupStore TODO(删除当前分组与门店关系)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @param storeId
	 * @return
	 */
	@Transactional
	public AjaxReq delGroupStore(String groupId, String storeId) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				ar.setMessage("删除失败");
				return ar;
			}
			if (StringUtils.isBlank(storeId)) {
				ar.setMessage("删除失败");
				return ar;
			}
			JSONArray ja = JSONArray.fromObject(storeId);
			Map<String, String> map = new HashMap<String, String>();
			for (int i = 0; i < ja.size(); i++) {
				map.put(ja.getString(i), ja.getString(i));
			}
			String accountId = ShiroDb.getAccount().getId();
			MybStoreGroup myb = mybStoreGroupRepository.findByAccountIdAndId(accountId, groupId);
			for (MybStore mybStore : myb.getMybStore()) {
				if (map.get(mybStore.getId()) != null) {
					myb.getMybStore().remove(mybStore);
				}
			}
			mybStoreGroupRepository.save(myb);
			ar.setMessage("保存成功！");
			ar.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			ar.setMessage("添加失败");
		}
		return ar;
	}

	/**
	 * queryGroupByAccountid TODO(根据用户查询所有的的group)
	 * 
	 * @author wangzx
	 * @return
	 */

	public List<MybStoreGroup> queryGroupByAccountid() {
		String accountId = ShiroDb.getAccount().getId();
		List<MybStoreGroup> list = mybStoreGroupRepository.findByAccountId(accountId);
		return list;
	}

	/**
	 * queryStoreByGroupIdForList TODO(根据groupid查询所有的数据)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	@Transactional
	public List<StoreList> queryStoreByGroupIdForList(String templateId) {
		List<StoreList> list = new ArrayList<StoreList>();
		try {
			String accountId = ShiroDb.getAccount().getId();
			// 如果groupid为空则查询全部
			JSONObject jb = null;
			JSONArray ja = new JSONArray();
			jb = new JSONObject();
			List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
			StoreList storeList = null;
			for (MybStore mybStore : myblist) {
				storeList = new StoreList();
				storeList.setStoreId(mybStore.getId());
				storeList.setStoreName(mybStore.getName());
				storeList.setUrl(
						Utils.ParseProperties("RELEASE_URL") + templateId + ".html?storeId=" + mybStore.getId());
				list.add(storeList);
			}
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * queryStoreByGroupId TODO(根据groupid查询所有的数据)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @return
	 */
	@Transactional
	public AjaxReq queryStoreByGroupId(String groupId, String templateId) {
		AjaxReq ar = new AjaxReq();
		try {
			String accountId = ShiroDb.getAccount().getId();
			// 如果groupid为空则查询全部
			JSONObject jb = null;
			JSONArray ja = new JSONArray();
			if (StringUtils.isBlank(groupId)) {
				jb = new JSONObject();
				List<MybStore> myblist = mybStoreRepository.findByAccountIdOrderByStoreSortAsc(accountId);
				for (MybStore mybStore : myblist) {
					jb.put("storeId", mybStore.getId());
					jb.put("storeName", mybStore.getName());
					jb.put("url", Utils.ParseProperties("RELEASE_URL") + templateId + ".html");
					ja.add(jb);
				}
				ar.setSuccess(true);
				ar.setData(ja);
			} else {
				jb = new JSONObject();
				MybStoreGroup myb = mybStoreGroupRepository.findByAccountIdAndId(accountId, groupId);
				for (MybStore mybStore : myb.getMybStore()) {
					jb.put("storeId", mybStore.getId());
					jb.put("storeName", mybStore.getName());
					jb.put("url",Utils.ParseProperties("RELEASE_STORE") + templateId + "/" + mybStore.getId() + ".html");
					ja.add(jb);
				}
				ar.setData(ja);
				ar.setSuccess(true);
			}
		} catch (Exception e) {
			ar.setSuccess(false);
			ar.setMessage("查询失败！");
		}
		return ar;
	}

	public void downOnlyONeLoadExcel(String questionId, String classPath,String downCount,ServletOutputStream outputStream,AjaxReq ar) {
		try {
			if (StringUtils.isBlank(questionId)) {
				ar.setSuccess(false);
				ar.setMessage("下载失败");
			}else{
				ar.setSuccess(true);
				XSSFWorkbook work = new XSSFWorkbook();
				XSSFSheet sheet = work.createSheet();
				XSSFRow row = null;
				row = sheet.createRow(0);
				ReleaseOnlyOneAnwser releaseOnlyOneAnwser = new ReleaseOnlyOneAnwser();
				releaseOnlyOneAnwser.setQuestionId(questionId);
				List<ReleaseOnlyOneAnwserData> list = new ArrayList<ReleaseOnlyOneAnwserData>();
				ReleaseOnlyOneAnwserData data = null;
				row.createCell(0).setCellValue("问卷访问路径");
				for (int i = 0; i < Integer.parseInt(downCount); i++) {
					data = new ReleaseOnlyOneAnwserData();
					String id = Utils.getUUid();
					data.setId(id);
					data.setSuccess(false);
					data.setCreateDate(new Date());
					row = sheet.createRow(i + 1);
					list.add(data);
					row.createCell(0).setCellValue(Utils.ParseProperties("RELEASE_ONLY_ONE") + questionId + "/" + id + ".html");
				}
				releaseOnlyOneAnwser.setListDate(list);
				mongoTemplate.save(releaseOnlyOneAnwser);
				work.write(outputStream);
				work.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * downGroupLoadExcel TODO(下载分店连接)
	 * 
	 * @author wangzx
	 * @param groupId
	 * @param questionId
	 * @return
	 */
	public AjaxReq downGroupLoadExcel(String parentId, String data, String questionId, String groupId,
			String classPath) {
		AjaxReq ar = new AjaxReq();
		try {
			if (StringUtils.isBlank(data)) {
				ar.setMessage("下载失败");
				return ar;
			}
			if (StringUtils.isBlank(questionId)) {
				ar.setMessage("下载失败");
				return ar;
			}
			Query query = new Query();
			ShiroDb.getAccount().getId();
			Criteria criterg = Criteria.where("_id").is(questionId);
			query.addCriteria(criterg);
			QuestionTmpltVO questionTmpltVO = mongoTemplate.findOne(query, QuestionTmpltVO.class);
			// 封装数据结构
			Map<String, Store> mapStore = new HashMap<String, Store>();
			for (Store s : questionTmpltVO.getStore()) {
				if (s.getType() == 0) {
					mapStore.put(s.getStoreId(), s);
				}
			}
			;
			Map<String, StoreGroupVO> mapStoreGroup = new HashMap<String, StoreGroupVO>();
			for (StoreGroupVO s : questionTmpltVO.getStoreGroup()) {
				mapStoreGroup.put(s.getStoreGroupId(), s);
			}
			;

			// 判断是否是根据门店组发布 parentId 为null时 无门店组发布
			if (StringUtils.isBlank(parentId)) {
				List<String> list = new ArrayList<String>();
				JSONArray jaStoreId = JSONArray.fromObject(data);
				for (int i = 0; i < jaStoreId.size(); i++) {
					String[] store = jaStoreId.getString(i).split("_");
					list.add(store[0]);
				}
				// 查询所有选中的门店
				if (list.size() != 0) {
					List<MybStore> listStore = mybStoreRepository.findByidIn(list);
					Store store = null;
					for (MybStore mybStore : listStore) {
						store = new Store();
						store.setId(mybStore.getId());
						store.setAddress(mybStore.getAccountId());
						store.setManagerEmail(mybStore.getManagerEmail());
						store.setManagerName(mybStore.getManagerName());
						store.setManagerPhone(mybStore.getManagerPhone());
						store.setManagerWechatNumber(mybStore.getManagerWechatNumber());
						store.setType(0);
						if (mapStore.get(store.getStoreId()) == null) {
							List<String> listDate = new ArrayList<String>();
							listDate.add(Utils.getDate());
							store.setDownDate(listDate);
						} else {
							store.getDownDate().add(Utils.getDate());
						}

						questionTmpltVO.getStore().add(store);
					}
				}
				questionsService.updateQuestion(questionId, JsonUtil.objectToJson(questionTmpltVO));
				this.releaseQuestion(questionId);
			} else {
				// 查询分组信息
				MybStoreGroup group = mybStoreGroupRepository.findOne(parentId);
				StoreGroupVO storogroup = new StoreGroupVO();
				if (group != null) {
					storogroup.setStoreGroupId(group.getId());
					storogroup.setStoreGroupName(group.getName());
				}
				// 查询分组以及下面的门店
				List<String> list = new ArrayList<String>();
				JSONArray jaStoreId = JSONArray.fromObject(data);
				for (int i = 0; i < jaStoreId.size(); i++) {
					String[] store = jaStoreId.getString(i).split("_");
					list.add(store[0]);
				}
				// 查询所有选中的门店
				if (list.size() != 0) {
					List<MybStore> listStore = mybStoreRepository.findByidIn(list);
					List<Store> addStore = new ArrayList<Store>();
					Store store = null;
					for (MybStore mybStore : listStore) {
						store = new Store();
						store.setId(mybStore.getId());
						store.setAddress(mybStore.getAccountId());
						store.setManagerEmail(mybStore.getManagerEmail());
						store.setManagerName(mybStore.getManagerName());
						store.setManagerPhone(mybStore.getManagerPhone() == null ? 0 : mybStore.getManagerPhone());
						store.setManagerWechatNumber(mybStore.getManagerWechatNumber());
						store.setType(1);
						addStore.add(store);
						questionTmpltVO.getStore().add(store);
					}
					if (mapStoreGroup.get(storogroup.getStoreGroupId()) == null) {
						List<String> listDate = new ArrayList<String>();
						listDate.add(Utils.getDate());
						storogroup.setDownDate(listDate);
					} else {
						storogroup.getDownDate().add(Utils.getDate());
					}
					storogroup.setStore(addStore);
				}
				questionTmpltVO.getStoreGroup().add(storogroup);
				questionsService.updateQuestion(questionId, JsonUtil.objectToJson(questionTmpltVO));
				this.releaseQuestion(questionId);
			}
			String path = classPath + Utils.ParseProperties("QR_CODE_URL") + questionId;
			String xlsxPath = classPath + Utils.ParseProperties("QR_CODE_URL") + questionId + "/门店统计.xlsx";
			File file = new File(path);
			file.mkdir();
			// 生成二维码写入文件夹
			// 生成xlsx文件
			XSSFWorkbook work = new XSSFWorkbook();
			XSSFSheet sheet = work.createSheet();
			XSSFRow row = null;
			row = sheet.createRow(0);
			row.createCell(0).setCellValue("门店名称");
			row.createCell(1).setCellValue("问卷访问路径");
			row.createCell(2).setCellValue("二维码");
			JSONArray jaStoreId = JSONArray.fromObject(data);
			FileOutputStream output = null;
			File fileImg = null;
			for (int i = 0; i < jaStoreId.size(); i++) {
				String[] store = jaStoreId.getString(i).split("_");
				row = sheet.createRow(i + 1);
				row.createCell(0).setCellValue(store[1]);
				row.createCell(1)
						.setCellValue(Utils.ParseProperties("RELEASE_URL") + questionId + ".html?storeId=" + store[0]);
				row.createCell(2).setCellValue(store[1] + ".png");
				String key = classPath + Utils.ParseProperties("QR_CODE_URL") + questionId + "/" + store[1] + ".png";
				fileImg = new File(key);
				output = new FileOutputStream(fileImg);
				output.write(
						Utils.storeCode(Utils.ParseProperties("RELEASE_URL") + questionId + ".html?storeId=" + store[0])
								.toByteArray());
				output.close();
			}
			FileOutputStream os = new FileOutputStream(xlsxPath);
			work.write(os);
			os.close();
			work.close();
			String zipPath = path + ".zip";
			ZipCompressorByAnt zip = new ZipCompressorByAnt(path + ".zip");
			boolean res = zip.compressExe(path + "/");
			if (res) {
				zip.deleteDir(new File(path + "/"));
				ar.setData(zipPath);
				ar.setSuccess(true);
				ar.setMessage(Utils.getDate() + ".zip");
			} else {
				ar.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ar;
	}
}
