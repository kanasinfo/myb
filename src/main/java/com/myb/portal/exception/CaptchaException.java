package com.myb.portal.exception;
import org.apache.shiro.authc.AuthenticationException;
/**
 * <p>Title: CaptchaException</p>
 * <p>Description: (验证码Exception)</p>
 * <p>date : 2015年12月27日 下午4:46:50 </p>
 * @author wangzx
 * @version 1.0
 */
public class CaptchaException extends AuthenticationException{
	private static final long serialVersionUID = 1L;

	public CaptchaException() {

		super();

	}

	public CaptchaException(String message, Throwable cause) {

		super(message, cause);

	}

	public CaptchaException(String message) {

		super(message);

	}

	public CaptchaException(Throwable cause) {

		super(cause);

	}
}
