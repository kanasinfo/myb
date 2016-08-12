package com.myb.portal.util;

import java.text.DecimalFormat;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * String utilities used to format, convert, handle strings
 * @author J.Mars
 *
 */
public class StringUtils {
	
	private StringUtils(){ }
	
	/**
	 * convert <code>byte[]</code> data to hex string
	 * @param data byte[]
	 * @return String
	 */
	public static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
        	int halfbyte = (data[i] >>> 4) & 0x0F;
        	int two_halfs = 0;
        	do {
	            if ((0 <= halfbyte) && (halfbyte <= 9))
	                buf.append((char) ('0' + halfbyte));
	            else
	            	buf.append((char) ('a' + (halfbyte - 10)));
	            halfbyte = data[i] & 0x0F;
        	} while(two_halfs++ < 1);
        }
        return buf.toString();
    }
	
	/**
	 * Format a Decimal object to String
	 * @param source, the number before formatted
	 * @param format, the format form
	 * @return
	 */
	public static String formatDecimal(Number source,String format){
		DecimalFormat df=new DecimalFormat(format);
		return df.format(source);
	}
	
	/**
	 * generate 32 bit UUID
	 * @return
	 */
	public static String generateUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String encodeToLine(String source){
		String target;
		target = source.replaceAll("\n", "\\\\n");
		target = target.replaceAll("\"", "\\\\\"");
		return target;
	}
	
	/**
	 * Get pure text from html contnet.
	 * @param inputString
	 * @return
	 */
	public static String html2Text(String inputString){
		String htmlStr = inputString;
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
	    java.util.regex.Pattern p_style;
	    java.util.regex.Matcher m_style;
	    java.util.regex.Pattern p_html;
	    java.util.regex.Matcher m_html;
	    try{
	          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
	          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
	          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

	          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
	          m_script = p_script.matcher(htmlStr);
	          htmlStr = m_script.replaceAll(""); //过滤script标签

	          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
	          m_style = p_style.matcher(htmlStr);
	          htmlStr = m_style.replaceAll(""); //过滤style标签

	          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	          m_html = p_html.matcher(htmlStr);
	          htmlStr = m_html.replaceAll(""); //过滤html标签

	          textStr = htmlStr;
	     }catch(Exception e){
	    	 System.out.println("Html2Text: " +e.getMessage());
	     }
	     return textStr;
	}
}
