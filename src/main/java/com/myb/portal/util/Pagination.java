package com.myb.portal.util;

/**
 * 
 * @author J.Mars
 *
 */
public class Pagination {
	private int totalResults=0;
	private int currentPage=1;
	private int numPerPage=20;
	private int firstResult=0;
	private int totalPage=0;
	private boolean sync=false;
	public void setFirstResult(Integer firstResult) {
		if(firstResult==null||firstResult==this.firstResult)return;
		this.firstResult = firstResult;
		this.currentPage=(this.firstResult/this.numPerPage)+1;
		this.sync=false;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		this.sync=false;
	}
	public Pagination(){
	}
	public Pagination(int totalResults){
		this.totalResults=totalResults;
		if(totalResults<numPerPage&&totalResults!=0)numPerPage=totalResults;
	}
	private void synchronize(){
		if(!sync){
			int tmptt;
			if(totalResults>0){
				tmptt=(int)(totalResults/numPerPage);
				if((float)tmptt==(float)((float)totalResults/(float)numPerPage))totalPage=tmptt;
				else totalPage=tmptt+1;
			}
			else totalPage=0;
			if(currentPage>totalPage)currentPage=totalPage;
			if(currentPage<1)currentPage=1;
			firstResult=(currentPage-1)*numPerPage;
			this.sync=true;
		}
	}
	public int getCurrentPage() {
		synchronize();
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage==null)this.currentPage=1;
		else this.currentPage = currentPage;
		this.sync=false;
	}
	public int getNumPerPage() {
		synchronize();
		return numPerPage;
	}
	public void setNumPerPage(Integer numPerPage) {
		if(numPerPage==null||numPerPage==this.numPerPage||numPerPage<=0)return;
		this.numPerPage = numPerPage;
		this.currentPage=(this.firstResult/this.numPerPage)+1;
		this.sync=false;
	}
	public int getLastResult() {
		synchronize();
		int lastResult=currentPage*numPerPage;
		if(lastResult>totalResults)return totalResults;
		else return lastResult;
	}
	public int getFirstResult() {
		synchronize();
		return firstResult;
	}
	public int getTotalPage() {
		synchronize();
		return totalPage;
	}
	public int getTotalResults() {
		synchronize();
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
		this.sync=false;
	}
	public int[] getPagesArray(int num){
		synchronize();
		int midnum=(int)(num/2);
		int[] pages;
		int startPage=0,endPage=0;
		if(getTotalPage()>num){
			pages=new int[num];
			if(getCurrentPage()<=midnum){
				startPage=1;
				endPage=num;
			}else if(getCurrentPage()>=getTotalPage()-midnum){
				startPage=getTotalPage()-num+1;
				endPage=getTotalPage();
			}else {
				startPage=getCurrentPage()-midnum;
				endPage=startPage+num-1;
			}
		}
		else{ 
			pages=new int[getTotalPage()];
			startPage=1;
			endPage=getTotalPage();
		}
		for(int p=startPage,t=0;p<=endPage;p++,t++){
			pages[t]=p;
		}
		return pages;
	}
	public int getMorePreviousPage(int num){
		synchronize();
		int midnum=(int)(num/2);
		int p=getCurrentPage()-num;
		while(p<getCurrentPage()-midnum){
			if(p>=1)return p;
			p++;
		}
		return 0;
	}
	public int getMoreNextPage(int num){
		synchronize();
		int midnum=(int)(num/2);
		int p=getCurrentPage()+num;
		while(p>getCurrentPage()+midnum){
			if(p<=getTotalPage())return p;
			p--;
		}
		return -1;
	}
}
