package com.myb.portal.shiro;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.myb.portal.exception.CaptchaException;
import com.myb.portal.model.account.MybAccount;
import com.myb.portal.repository.MybAccountRepository;

public class ShiroDb extends AuthorizingRealm {
	public static final int HASH_INTERATIONS = 1024;
	public static final String HASH_ALGORITHM = "SHA-1";
	@Autowired
	MybAccountRepository mybAccountRepository;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authzToken) {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authzToken;
		try {
			long time = System.currentTimeMillis();
			String captcha = token.getCaptcha();
			String exitCode = (String) SecurityUtils.getSubject().getSession()
					.getAttribute(CaptchaServlet.KEY_CAPTCHA);
			if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
				throw new CaptchaException("验证码错误");
			}
			MybAccount mybAccount = mybAccountRepository.findByloginEmail(token.getUsername());
			System.out.println("查询数据所需时间----"+(System.currentTimeMillis()-time));
			if (mybAccount == null) {
				throw new UnknownAccountException();
			}
			byte[] salt = ShiroDb.decodeHex(mybAccount.getLoginPwdSalt());
			AuthenticationInfo a = new SimpleAuthenticationInfo(
					new ShiroUser(mybAccount.getId(), mybAccount.getLoginPass(), mybAccount.getLoginEmail()),
					mybAccount.getLoginPass(), ByteSource.Util.bytes(salt), getName());
			this.setSession(new ShiroUser(mybAccount.getId(), mybAccount.getLoginPass(), mybAccount.getLoginEmail()));
			return a;
		} catch (UnknownAccountException uae) { // 用户名未知
			throw new UnknownAccountException();
		} catch (IncorrectCredentialsException ice) {// 凭据不正确，例如密码不正确
			throw new IncorrectCredentialsException();
		} catch (LockedAccountException lae) { // 用户被锁定，例如管理员把某个用户禁用
			throw new LockedAccountException();
		} catch (CaptchaException e) {
			throw new CaptchaException();
		} catch (ExcessiveAttemptsException eae) {// 尝试认证次数多余系统指定次数
			throw new ExcessiveAttemptsException();
		} catch (AuthenticationException ae) {
			throw new AuthenticationException();// 其他未指定异常
		} 
	}

	/**
	 * Hex解码.
	 */
	public static byte[] decodeHex(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			// throw Exception;
		}
		return null;
	}

	/**
	 * 将数据存放到ShiroSession中，以便于其他地方使用 setSession TODO(这里用一句话描述这个类的作用)
	 * 
	 * @author wangzx
	 * @param key
	 * @param value
	 */
	private void setSession(ShiroUser s) {
		Subject currentUser = SecurityUtils.getSubject();
		if (null != currentUser) {
			currentUser.getSession().setAttribute("principals", s);
		}
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
		matcher.setHashIterations(HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {
		private static final long serialVersionUID = -1373760761780840081L;
		public String id;
		public String loginName;
		public String name;

		public ShiroUser(String id, String loginName, String name) {
			this.id = id;
			this.loginName = loginName;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		public String getLoginName() {
			return loginName;
		}

		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		/**
		 * 重载equals,只计算loginName;
		 */
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this, "loginName");
		}

		/**
		 * 重载equals,只比较loginName
		 */
		@Override
		public boolean equals(Object obj) {
			return EqualsBuilder.reflectionEquals(this, obj, "loginName");
		}
	}
	public static ShiroUser getAccount(){
		ShiroUser shiroUser =  (ShiroUser)SecurityUtils.getSubject().getPrincipal();
		if(shiroUser!=null){
			return shiroUser;
		}else{
			return null;
		}
	}
	public static ShiroUser getShiroUser(final String sessionId) {
		SessionKey sessionKey = new SessionKey() {
			@Override
			public Serializable getSessionId() {
				return sessionId;
			}
		};
		try {
			ShiroDb.ShiroUser shiroUser = (ShiroUser) SecurityUtils.getSecurityManager().getSession(sessionKey)
					.getAttribute("principals");
			SecurityUtils.getSecurityManager().getSession(sessionKey).touch();
			return shiroUser;
		} catch (UnknownSessionException e) {
			return null;
		}
	}
}
