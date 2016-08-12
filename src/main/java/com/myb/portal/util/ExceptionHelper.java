package com.myb.portal.util;

import org.slf4j.Logger;

/**
 * BusinessHelper, logging and handling exceptions.
 * 
 * @author Jan
 * @version 1.0
 */
public class ExceptionHelper{

	private ExceptionHelper(){
		
	}

	public static final void loggingDebug(Exception e, Logger logger){
		if(logger!=null&&logger.isDebugEnabled()){
			logger.debug(getExceptionStackTrace(e));
		}
	}
	
	public static final void loggingWarning(Exception e, Logger logger){
		if(logger!=null&&logger.isWarnEnabled()){
			logger.warn(getExceptionStackTrace(e));
		}
		else
			e.printStackTrace();
	}
	
	public static final void loggingError(Exception e, Logger logger){
		if(logger!=null&&logger.isErrorEnabled())
			logger.error(getExceptionStackTrace(e));
		else
			e.printStackTrace();
	}
	
	private static final String getExceptionStackTrace(Exception e){
		StringBuffer sb=new StringBuffer(e.toString()).append("\n");
		for(StackTraceElement ste:e.getStackTrace()){
			sb.append("\tat ").append(ste).append("\n");
		}
		return sb.toString();
	}
}
