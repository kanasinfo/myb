package com.myb.portal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myb.portal.exception.CaptchaException;
import com.myb.portal.model.account.MybAccount;
import com.myb.portal.service.MybAccountService;
import com.myb.portal.shiro.UsernamePasswordCaptchaToken;
import com.myb.portal.support.ControllerSupport;
import com.myb.portal.util.AjaxReq;

@Controller
@RequestMapping("account")
public class MybAccountController extends ControllerSupport{
	public final String ACCOUNT_PATH= "account/";
	public final String THEMES_PATH= "themes/";
	
	
	@Autowired
	private MybAccountService mybAccountService;
	@RequestMapping("/listall.html")
	public ModelAndView listAll(ModelAndView mv){
//		List<MybAccount> accountlist = mybAccountService.findAllAccount();
//		mv.setViewName("themes/account");
//		mv.addObject("accountlist", accountlist);
		return mv;
	}
	/**
	 * login TODO(用户登陆页面跳转) 
	 * @author wangzx
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/login.html",method=RequestMethod.GET)
	public ModelAndView login1(HttpServletRequest request,ModelAndView mv){
		System.out.println(request.getServletPath());
		mv.setViewName(ACCOUNT_PATH+"login");
		return mv;
	}
	@RequestMapping(value="test",method=RequestMethod.GET)
	public ModelAndView test(){
		ModelAndView mView = new ModelAndView();
		mView.setViewName(THEMES_PATH+"main");
		return mView;
	}
	/**
	 * login TODO(用户登陆post请求) 
	 * @author wangzx
	 * @param request
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="/login.html",method=RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request,ModelAndView mv) throws CaptchaException{
		SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		long time = System.currentTimeMillis();
		//获取用户名
		String loginName = request.getParameter("loginName");
		//获取密码
		String passWord = request.getParameter("passWord");
		String code = request.getParameter("code");
		UsernamePasswordCaptchaToken token = new UsernamePasswordCaptchaToken(loginName, passWord,true,code); 
		//5.设置记住我  
         token.setRememberMe(true);  
         //6.获取Subject对象  
         Subject currentUser = SecurityUtils.getSubject();  
         try {
        	 currentUser.login(token);
        	 try {
        		 mv.setViewName("redirect:"+savedRequest.getRequestUrl().replace("/myb-portal", ""));
        	 } catch (Exception e) {
        		 mv.setViewName("redirect:../main/question.html");
        		 }
        	 //        	 mv.setViewName("question");
         } catch (UnknownAccountException uae) { //用户名未知...  
        	 mv.setViewName(ACCOUNT_PATH+"login");
 			mv.addObject("msg", "用户名不正确");
 		} catch ( IncorrectCredentialsException ice ) {//凭据不正确，例如密码不正确 ...
 			mv.setViewName(ACCOUNT_PATH+"login");
 			mv.addObject("msg", "密码不正确");
 		} catch ( LockedAccountException lae ) { //用户被锁定，例如管理员把某个用户禁用...
 			mv.setViewName(ACCOUNT_PATH+"login");
 			mv.addObject("msg", "用户被锁定");
 		}catch (CaptchaException e) {
 			mv.setViewName(ACCOUNT_PATH+"login");
 			mv.addObject("msg", "验证码错误");
		} catch ( ExcessiveAttemptsException eae ) {//尝试认证次数多余系统指定次数 ...
 			throw new ExcessiveAttemptsException();
 		} catch ( AuthenticationException ae ) {  
 			throw new AuthenticationException();
 		//其他未指定异常  
 		}
         System.out.println("登陆耗费的时间----"+(System.currentTimeMillis()-time));
         
		return mv;
	}
	@RequestMapping(value="loginOut",method=RequestMethod.GET)
	public ModelAndView loginOut(){
		ModelAndView mv = new ModelAndView();
	    Subject currentUser = SecurityUtils.getSubject(); 
	    currentUser.logout();
	    mv.setViewName(THEMES_PATH+"main");
		return mv;
	}
	@RequestMapping(value="register",method=RequestMethod.GET)
	public ModelAndView register(ModelAndView mv){
		mv.setViewName(ACCOUNT_PATH+"register");
		return mv;
	}
	/**
	 * registerAccount TODO(注册功能) 
	 * @author wangzx
	 * @param mybAccount
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	public ModelAndView registerAccount(MybAccount mybAccount){
		ModelAndView mv = new ModelAndView();
		AjaxReq aReq = mybAccountService.registerAccount(mybAccount);
		//判断是否注册成功
		if(aReq.isSuccess()){
			mv.setViewName(THEMES_PATH+"main");
			return mv;
		}else{
			mv.addObject("mes", aReq.getData());
			mv.setViewName(ACCOUNT_PATH+"login");
			return mv;
		}
	}
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
	}
}
