package com.myb.portal.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordCaptchaToken extends UsernamePasswordToken{
	private static final long serialVersionUID = 1L;

	private String captcha;

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public UsernamePasswordCaptchaToken(String username, char[] cs, boolean rememberMe, String host, String captcha2) {
		super();

	}

	public UsernamePasswordCaptchaToken(String username,String password,
			boolean rememberMe, String captcha) {
		super(username, password, rememberMe);
		this.captcha = captcha;
	}
}
