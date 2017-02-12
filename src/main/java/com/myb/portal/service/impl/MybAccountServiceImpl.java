package com.myb.portal.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.myb.portal.exception.BusinessException;
import com.myb.portal.exception.ServiceException;
import com.myb.portal.model.account.MybAccount;
import com.myb.portal.model.account.MybStore;
import com.myb.portal.model.account.MybStoreGroup;
import com.myb.portal.repository.MybAccountRepository;
import com.myb.portal.repository.MybStoreGroupRepository;
import com.myb.portal.repository.MybStoreRepository;
import com.myb.portal.service.MybAccountService;
import com.myb.portal.shiro.ShiroDb;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.PasswordUtil;
import com.myb.portal.util.Utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class MybAccountServiceImpl implements MybAccountService {

	@Autowired
	private MybAccountRepository mybAccountRepository;
	@Autowired
	private MybStoreGroupRepository mybStoreGroupRepository;
	@Autowired
	MybStoreRepository mybStoreRepository;
	public int queryAmount(){
		int amount = 0;
		try {
			String accountId = ShiroDb.getAccount().getId();
			MybAccount mybAccount = mybAccountRepository.findOne(accountId);
			amount = mybAccount.getCreaditAmount();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return amount;
	}

	@Override
	public MybAccount findByPhone(String phone) {
		return mybAccountRepository.findByPhone(phone);
	}

	@Override
	public MybAccount findByLoginEmail(String loginEmail) {
		return mybAccountRepository.findByloginEmail(loginEmail);
	}

	/**
	 * registerAccount TODO(用户注册)
	 *
	 * @author wangzx
	 * @param mybAccount
	 * @return
	 */
	@Transactional
	public AjaxReq registerAccount(MybAccount mybAccount) {
		AjaxReq aReq = new AjaxReq();
		try {
			if (mybAccount == null) {
				throw new ServiceException("参数不能为空");
			}
			if (StringUtils.isBlank(mybAccount.getLoginEmail())) {
				throw new ServiceException("用户名不能为空");
			}
			if (StringUtils.isBlank(mybAccount.getLoginPass())) {
				throw new ServiceException("密码不能为空");
			}
			// 验证当前用户是否存在
			MybAccount m = mybAccountRepository.findByloginEmail(mybAccount.getLoginEmail());
			if (m != null) {
				throw new BusinessException("用户已存在");
			}
			// 设置密码加密规则，并获取加密盐值
			PasswordUtil.entryptPassword(mybAccount);
			// 保存注册数据
			mybAccount.setId(Utils.getUUid());
			mybAccountRepository.save(mybAccount);
			aReq.setSuccess(true);
			aReq.setData("注册成功");
		} catch (ServiceException e) {
		    aReq.setSuccess(false);
		    aReq.setMessage(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
            aReq.setSuccess(false);
            aReq.setMessage(e.getMessage());
			e.printStackTrace();
		}
		return aReq;
	}

	/**
	 * saveGroup TODO(保存该用户的分组)
	 *
	 * @author wangzx
	 * @param groupName
	 * @return
	 */
	@Transactional
	public AjaxReq saveGroup(String groupName) {
		AjaxReq ajaxReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupName)) {
				ajaxReq.setMessage("名称不能为空！");
				return ajaxReq;
			}
			// String accountId = ShiroDb.getAccount().getId();
			String accountId = "03421a2a7ebf4bde9b93efb436c55a0e";
			MybStoreGroup mybStoreGroup = new MybStoreGroup();
			mybStoreGroup.setId(Utils.getUUid());
			mybStoreGroup.setName(groupName);
			mybStoreGroup.setAccountId(accountId);
			mybStoreGroupRepository.save(mybStoreGroup);
			ajaxReq.setData(mybStoreGroup);
			ajaxReq.setSuccess(true);
			ajaxReq.setMessage("保存成功");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ajaxReq;
	}

	/**
	 * saveAccountAndGroup TODO(保存门店和组的关系)
	 *
	 * @author wangzx
	 * @return
	 */
	@Transactional
	public AjaxReq saveGroupAndGroup(String groupId, String[] stores) {
		AjaxReq aReq = new AjaxReq();
		try {
			if (StringUtils.isBlank(groupId)) {
				aReq.setMessage("分组不能为空");
				return aReq;
			}
			if (stores == null || stores.length == 0) {
				aReq.setMessage("用户不能为空");
				return aReq;
			}
			MybStoreGroup mybStoreGroup = mybStoreGroupRepository.findOne(groupId);
			if (mybStoreGroup != null) {
				Set<MybStore> mybStore = new HashSet<MybStore>(stores.length);
				for (String store : stores) {
					mybStore.add(mybStoreRepository.findOne(store));
				}
				mybStoreGroup.setMybStore(mybStore);
				mybStoreGroupRepository.save(mybStoreGroup);
				aReq.setSuccess(true);
				aReq.setMessage("保存成功");
			} else {
				aReq.setMessage("分组不能为空");
				return aReq;
			}
		} catch (Exception e) {
		}

		return aReq;
	}

	/**
	 * queryGroupByAccountId TODO(根据用户ID查询分组信息)
	 *
	 * @author wangzx
	 * @param accountId
	 * @return
	 */
	public AjaxReq queryGroupByAccountId() {
		AjaxReq aReq = new AjaxReq();
		try {
			List<MybStoreGroup> list = mybStoreGroupRepository.findByAccountId(ShiroDb.getAccount().getId());
			JSONArray jArray = new JSONArray();
			JSONObject jb = null;
			for (MybStoreGroup mybStoreGroup : list) {
				jb = new JSONObject();
				jb.put("id", mybStoreGroup.getId());
				jb.put("name", mybStoreGroup.getName());
				jArray.add(jb);
			}
			aReq.setSuccess(true);
			aReq.setData(jArray.toString());
			return aReq;
		} catch (Exception e) {
			// TODO: handle exception
		}
		aReq.setMessage("查询失败");
		return aReq;
	}

	public static void main(String[] args) throws IOException, WriterException {
		String text = "您好";
		int width = 200;
		int height = 200;
		String format = "png";
		Hashtable hints = new Hashtable();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
		File outputFile = new File("/Users/wangzx/Desktop/png/new.png");
		MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);

	}
}
