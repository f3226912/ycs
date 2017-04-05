package com.ycszh.ssh.hbm.drv;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.example.ssh.hbm
 * @文件名:Yujing.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-21
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class Yujing extends BaseObject{
	
	private String lsh;
	private String sfzmhm;
	private String dabh;
	private String xm;
	private String kssj;
	private String ywlx;
	private String glbm;
	
	public Yujing() {
		super();
	}
	public Yujing(String lsh, String sfzmhm, String dabh, String xm,
			String kssj, String ywlx, String glbm) {
		super();
		this.lsh = lsh;
		this.sfzmhm = sfzmhm;
		this.dabh = dabh;
		this.xm = xm;
		this.kssj = kssj;
		this.ywlx = ywlx;
		this.glbm = glbm;
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
	public String getDabh() {
		return dabh;
	}
	public void setDabh(String dabh) {
		this.dabh = dabh;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getYwlx() {
		return ywlx;
	}
	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	
	

}
