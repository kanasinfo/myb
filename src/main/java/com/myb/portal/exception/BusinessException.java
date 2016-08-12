package com.myb.portal.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Title: 服务层异常类.
 * </p>
 * 
 * <p>
 * Description:
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * 
 * <p>
 * Company: PERAGLOBAL
 * </p>
 * 
 * @author he.ma
 * 
 * @version 1.0
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(RuntimeException.class);

	private Throwable mRootCause = null;

	/**
	 * Construct emtpy exception object.
	 */
	public BusinessException() {
		super();
	}

	/**
	 * Construct RollerException with message string.
	 * 
	 * @param s
	 *            Error message string.
	 */
	public BusinessException(String s) {
		super(s);
	}

	/**
	 * Construct RollerException, wrapping existing throwable.
	 * 
	 * @param s
	 *            Error message
	 * @param t
	 *            Existing connection to wrap.
	 */
	public BusinessException(String s, Throwable t) {
		super(s);
		mRootCause = t;
	}

	/**
	 * Construct RollerException, wrapping existing throwable.
	 * 
	 * @param t
	 *            Existing exception to be wrapped.
	 */
	public BusinessException(Throwable t) {
		mRootCause = t;
	}

	/**
	 * Get root cause object, or null if none.
	 * 
	 * @return Root cause or null if none.
	 */
	public Throwable getRootCause() {
		return mRootCause;
	}

	/**
	 * Get root cause message.
	 * 
	 * @return Root cause message.
	 */
	public String getRootCauseMessage() {
		String rcmessage = null;
		if (getRootCause() != null) {
			if (getRootCause().getCause() != null) {
				rcmessage = getRootCause().getCause().getMessage();
			}
			rcmessage = (rcmessage == null) ? getRootCause().getMessage() : rcmessage;
			rcmessage = (rcmessage == null) ? super.getMessage() : rcmessage;
			rcmessage = (rcmessage == null) ? "NONE" : rcmessage;
		}
		return rcmessage;
	}

	/**
	 * Print stack trace for exception and for root cause exception if htere is
	 * one.
	 * 
	 * @see java.lang.Throwable#printStackTrace()
	 */
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		if (mRootCause != null) {
			logger.error("--- ROOT CAUSE ---");
			mRootCause.printStackTrace();
		}
	}

	/**
	 * Print stack trace for exception and for root cause exception if htere is
	 * one.
	 * 
	 * @param s
	 *            Stream to print to.
	 */
	@Override
	public void printStackTrace(PrintStream s) {
		super.printStackTrace(s);
		if (mRootCause != null) {
			s.println("--- ROOT CAUSE ---");
			mRootCause.printStackTrace(s);
		}
	}

	/**
	 * Print stack trace for exception and for root cause exception if htere is
	 * one.
	 * 
	 * @param s
	 *            Writer to write to.
	 */
	@Override
	public void printStackTrace(PrintWriter s) {
		super.printStackTrace(s);
		if (null != mRootCause) {
			s.println("--- ROOT CAUSE ---");
			mRootCause.printStackTrace(s);
		}
	}

}
