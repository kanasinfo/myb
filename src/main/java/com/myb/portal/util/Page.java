package com.myb.portal.util;


public class Page {
	/**
	 * pageHtml TODO(这里用一句话描述这个类的作用) 
	 * @author wangzx
	 * @param count TODO(总页数)
	 * @param pageSize TODO（每页多少条）
	 * @param currentPage TODO（第几页）
	 * @return
	 */
	public static String pageHtml(int count,int pageSize,int currentPage){
		int page = count%pageSize==0?count/pageSize:count/pageSize+1;
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("<div class=\"quotes\">");
		if(currentPage==1){
			sBuffer.append("<span class=\"disabled\">");
			sBuffer.append("<");
			sBuffer.append("</span>");
		}else{
			sBuffer.append("<span>");
			sBuffer.append("<a href=\"question.html?currentPage="+(currentPage-1)+"&pageSize="+pageSize+"\"><</a>");
			sBuffer.append("</span>");
		}
		
		for (int i = 1; i <=page; i++) {
			if(i==currentPage){
				sBuffer.append("<span class=\"current\">");
				sBuffer.append(i);
				sBuffer.append("</span>");
			}else{
				sBuffer.append("<a href=\"question.html?currentPage="+i+"&pageSize="+pageSize+"\">"+i+"</a>");
				
			}
		}
		if(currentPage==page){
			sBuffer.append("<span class=\"disabled\">");
			sBuffer.append("&gt");
			sBuffer.append("</span>");
		}else{
			sBuffer.append("<span>");
			sBuffer.append("<a href=\"question.html?currentPage="+(currentPage+1)+"&pageSize="+pageSize+"\">&gt</a>");
			sBuffer.append("</span>");
		}
		
		return sBuffer.toString();
	}
	public static void main(String[] args) {
		Page page = new Page();
		String aa =page.pageHtml(11,2, 2);
		System.out.println(aa);
	}
}
	