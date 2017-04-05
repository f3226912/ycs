package com.ycszh.formbean;

import java.util.Date;
import java.util.List;

public class VehSuperviseForm<T>{
	public VehSuperviseForm(){
	}

	public List<T> getVehList() {
		return vehList;
	}

	public void setVehList(List<T> vehList) {
		this.vehList = vehList;
	}

	private String ywlx;
	private String ywyy;
	private String startDt;
	private String endDt;
	private String hphm;
	private String hpzl;
	private int currentPage;
	private int pageSize;
	private int pageCount;
	private String pageUrl;
	private List<T> vehList;

	public String getPageUrl() {
		return pageUrl;
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

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
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

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
}
