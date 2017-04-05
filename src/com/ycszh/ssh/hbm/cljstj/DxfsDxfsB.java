package com.ycszh.ssh.hbm.cljstj;

import java.util.Date;

public class DxfsDxfsB {
	
	private Long id;	//主键	读取序列” DXFS_DXFSB_SEQ”	
	private String ywlx;	//业务类型	固定填写”G”	
	private String ywlxXx;	//业务类型详细	机动车/驾驶证	
	private String lsh;	//流水号	具体业务流水号	
	private String sfzmhm;	//身份证明号码	 	
	private String dhhm;	//手机号码		
	private String slsj;	//受理时间		
	private String bjsj;	//办结时间		
	private String dmnr;	//短信内容		
	private String dmxz;	//短信性质	“A”受理短信 “B”办结短信 “C”退办短信	
	private Date cjsj;	//采集时间
	
	public DxfsDxfsB() {
		super();
	}
	
	public DxfsDxfsB(Long id, String ywlx, String ywlxXx, String lsh,
			String sfzmhm, String dhhm, String slsj, String bjsj, String dmnr,
			String dmxz, Date cjsj) {
		super();
		this.id = id;
		this.ywlx = ywlx;
		this.ywlxXx = ywlxXx;
		this.lsh = lsh;
		this.sfzmhm = sfzmhm;
		this.dhhm = dhhm;
		this.slsj = slsj;
		this.bjsj = bjsj;
		this.dmnr = dmnr;
		this.dmxz = dmxz;
		this.cjsj = cjsj;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public String getYwlxXx() {
		return ywlxXx;
	}
	public void setYwlxXx(String ywlxXx) {
		this.ywlxXx = ywlxXx;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getDhhm() {
		return dhhm;
	}
	public void setDhhm(String dhhm) {
		this.dhhm = dhhm;
	}
	public String getSlsj() {
		return slsj;
	}
	public void setSlsj(String slsj) {
		this.slsj = slsj;
	}
	public String getBjsj() {
		return bjsj;
	}
	public void setBjsj(String bjsj) {
		this.bjsj = bjsj;
	}
	public String getDmnr() {
		return dmnr;
	}
	public void setDmnr(String dmnr) {
		this.dmnr = dmnr;
	}
	public String getDmxz() {
		return dmxz;
	}
	public void setDmxz(String dmxz) {
		this.dmxz = dmxz;
	}
	public Date getCjsj() {
		return cjsj;
	}
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}
}
