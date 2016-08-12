package com.myb.portal.support;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;



/**
 * 
 * @author J.Mars
 *
 */
public abstract class ControllerSupport {
	
	public static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd";
	
	protected final Logger logger;

	public ControllerSupport(){
		logger = LoggerFactory.getLogger(this.getClass());
	}
	
	protected String dateFormat=DEFAULT_DATE_FORMAT; 
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(this.dateFormat);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
		
		//used to parse a comma separated integer field.
		binder.registerCustomEditor(Integer.class,new IntegerEditor());
	}
}
