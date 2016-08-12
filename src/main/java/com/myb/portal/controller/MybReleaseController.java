package com.myb.portal.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.gridFsPhoto.GridFSPhotoUtils;
import com.myb.portal.service.MybReleaseService;
import com.myb.portal.util.AjaxReq;
import com.myb.portal.util.Utils;
@RequestMapping(value="release")
@Controller
public class MybReleaseController {
	@Autowired
	MybReleaseService mybReleaseService;
	@Autowired
	GridFSPhotoUtils grid;
	@RequestMapping(value="releaseOnlyOne/{templId}",method=RequestMethod.GET)
	public ModelAndView releaseOnlyOne(@PathVariable String templId, ModelAndView mv){
		mv.addObject("creditAmount", 100);
		mv.addObject("templId", templId);
		mv.addObject("id", templId); // 用于头文件
		mv.setViewName("release/releaseOnlyOne");
		return mv;
	}
	@RequestMapping(value="releaseStore/{templId}",method=RequestMethod.GET)
	public ModelAndView releaseStore(@PathVariable String templId, ModelAndView mv){
		mv.addObject("templId", templId);
		mv.addObject("id", templId); // 用于头文件
		//查询所有的group
		mv.addObject("groupList",mybReleaseService.queryGroupByAccountid());
		mv.setViewName("release/releaseStore");
		return mv;
	}
	/**
	 * releaseOne TODO(查询公用用户发布) 
	 * @author wangzx
	 * @param id
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="releaseOne/{templId}",method=RequestMethod.GET)
	public ModelAndView releaseOne(@PathVariable String templId,ModelAndView mv,HttpServletRequest request,HttpServletResponse resp){
		mv.addObject("templId", templId);
		mv.addObject("id", templId); // 用于头文件
		mv.addObject("url", mybReleaseService.getQRurl(templId));
		//发布新版本
		mv.setViewName("release/releaseone");
		return mv;
	}
	@RequestMapping(value="releaseQuestion/{templId}",method=RequestMethod.GET)
	public ModelAndView releaseQuestion(@PathVariable String templId,ModelAndView mv){
		Map<String, Object> map;
		try {
			map = mybReleaseService.releaseQuestion(templId);
			mv.addObject("url", map.get("url"));
			mv.addObject("templId", templId);
			mv.addObject("id", templId); // 用于头文件
			//发布新版本
			mv.setViewName("redirect:../releaseOne/"+templId+".html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
	}
	@RequestMapping(value="getImg",method=RequestMethod.GET)
	public void getImg(String id,String sotreId,HttpServletResponse resp){
		try {
			OutputStream os = resp.getOutputStream();    
	        os.write(((ByteArrayOutputStream)grid.getFileById(id).get("file")).toByteArray());	
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@RequestMapping(value = "downLoadExcel", method = RequestMethod.POST)
	public void downLoadExcel(String id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/x-msdownload;");
			Map<String, Object> map = grid.getFileById(id);
			response.setHeader("Content-disposition","attachment; filename=" + new String(String.valueOf(map.get("questionName"))+".png".getBytes("utf-8")));
			outputStream.write(((ByteArrayOutputStream)grid.getFileById(id).get("file")).toByteArray());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value="uploadXls",method=RequestMethod.POST)
	@ResponseBody
	public AjaxReq uploadXls(MultipartFile myfile){
		AjaxReq ar = new AjaxReq();
		try {
			String fileName = myfile.getName();
			fileName = fileName.substring(fileName.lastIndexOf("."), fileName.length());
			if(fileName==""){
				ar.setSuccess(false);
				ar.setMessage("不允许为空");
				return ar;
			}else if(!fileName.equals(".xlsx")){
				ar.setSuccess(false);
				ar.setMessage("文件不正确，请输入正确格式。");
				return ar;
			}
			InputStream input = myfile.getInputStream();
			mybReleaseService.uploadXls(input,ar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ar;
	}
	
	@RequestMapping(value = "downLoad", method = RequestMethod.POST)
	public void downLoad(String id, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			response.reset();// 不加这一句的话会出现下载错误
			response.setCharacterEncoding("UTF-8");  
	        response.setContentType("application/octet-stream;charset=UTF-8");     
	        Map<String, Object> map = grid.getFileById(id);
	        response.setHeader("Content-Disposition", "attachment; filename="+Utils.getDate()+".png");// 设定输出文件头   
			outputStream.write(((ByteArrayOutputStream)grid.getFileById(id).get("file")).toByteArray());
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@RequestMapping(value = "downGroupLoadExcel", method = RequestMethod.POST)
	public void downGroupLoadExcel(String data,String questionId,String groupId,HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			request.getRequestURI();
			String path = request.getRealPath("/");
			AjaxReq ar = mybReleaseService.downGroupLoadExcel(data, questionId, groupId,path);
			InputStream fis = new BufferedInputStream(new FileInputStream(ar.getData().toString()));
			if(ar.isSuccess()){
				response.reset();// 不加这一句的话会出现下载错误
				response.setCharacterEncoding("UTF-8");  
		        response.setContentType("application/zip");     
		        response.setHeader("Content-Disposition", "attachment; filename="+ar.getMessage());// 设定输出文件头   
//	            byte[] buffer = new byte[fis.available()];
		        byte[] b = new byte[1024];// 相当于我们的缓存
				int len = 0;// 该值用于计算当前实际下载了多少字节
				while ((len=fis.read(b))!=-1) {
					outputStream.write(b,0,len);
				}
			}
			outputStream.flush();
			outputStream.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="addStore",method = RequestMethod.POST)
	@ResponseBody
	public AjaxReq addStore(String store){
		return mybReleaseService.addStore(store);
	}
	@RequestMapping(value="queryAllStoreByAccountId",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq queryAllStoreByAccountId(){
		return mybReleaseService.queryAllStoreByAccountId();
	}
	
	@RequestMapping(value="delStoreId",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq delStoreId(String store){
		return mybReleaseService.delStore(store);
	}
	@RequestMapping(value="delGroupById",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq delGroupById(String groupId){
		return mybReleaseService.delGroupById(groupId);
	}
	@RequestMapping(value="addGroup",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq addGroup(String id,String groupName){
		return mybReleaseService.addGroup(id, groupName);
	}
	@RequestMapping(value="queryGroupStoreByAccountId",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq queryGroupStoreByAccountId(String groupId){
		return mybReleaseService.queryGroupStoreByAccountId(groupId);
	}
	@RequestMapping(value="addStoreToGroup",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq addStoreToGroup(String storeId,String groupId){
		return mybReleaseService.addStoreToGroup(storeId, groupId);
	}
	@RequestMapping(value="delGroupStore",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq delGroupStore(String groupId,String storeId){
		return mybReleaseService.delGroupStore(groupId, storeId);
	}
	@RequestMapping(value="queryStoreByGroupId",method=RequestMethod.GET)
	@ResponseBody
	public AjaxReq queryStoreByGroupId(String groupId,String templateId){
		return mybReleaseService.queryStoreByGroupId(groupId,templateId);
	}
}
