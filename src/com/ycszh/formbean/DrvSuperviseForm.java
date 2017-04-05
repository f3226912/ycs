package com.ycszh.formbean;

import java.util.List;

public class DrvSuperviseForm<T> {
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public String getYwyy() {
		return ywyy;
	}
	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	public String getJszhm() {
		return jszhm;
	}
	public void setJszhm(String jszhm) {
		this.jszhm = jszhm;
	}
	public int getCurrentPage() {
		if(currentPage==0){
			return 1;
		}
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public List<T> getDrvList() {
		return drvList;
	}
	public void setDrvList(List<T> drvList) {
		this.drvList = drvList;
	}
	private String ywlx;
	private String ywyy;
	private String startDt;
	private String endDt;
	private String jszhm;
	private int currentPage;
	private int pageSize;
	private int pageCount;
	private String pageUrl;
	private List<T> drvList;
}
