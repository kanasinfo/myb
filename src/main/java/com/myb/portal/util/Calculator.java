package com.myb.portal.util;

/**
 * 
 * @author J.Mars
 *
 */
public class Calculator {
	private Calculator(){}
	
	/**
	 * Round a double with precision, precision with a positive number will remain the digits after point.
	 * @param source
	 * @param precision
	 * @return
	 */
	public static double round(double source,int precision){
		double digits=Math.pow(10, precision);
		return Math.round(source*digits)/digits;
	}
	
}
