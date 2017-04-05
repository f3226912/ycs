package com.ycszh.ssh.hbm.jyzwcj;

import java.util.Date;

/**
 * PoliceFingerInfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PoliceFingerInfo implements java.io.Serializable {

	// Fields

	private String jh;			//警号
	private String xm;			//姓名
	private String bmbh;		//部门编号
	private String finger1;		//指纹1
	private String finger2;		//指纹2
	private String finger3;		//指纹3
	private String finger4;		//指纹4
	private String zt;			//采集状态0：没采集；1：已采集
	private String czr;			//采集人编号
	private String czrXm;		//采集人姓名
	private String czrBm;		//采集人部门
	private String czrKjbm;		//采集人科级部门
	private Date czsj;			//采集时间
	private String czip;		//采集IP
	private String czmac;		//采集MAC
	// Constructors

	/** default constructor */
	public PoliceFingerInfo() {
	}

	/** full constructor */
	public PoliceFingerInfo(String xm, String bmbh, String finger1,
			String finger2, String finger3, String finger4, String zt,
			String czr, String czrXm, String czrBm, String czrKjbm, Date czsj,
			String czip, String czmac) {
		this.xm = xm;
		this.bmbh = bmbh;
		this.finger1 = finger1;
		this.finger2 = finger2;
		this.finger3 = finger3;
		this.finger4 = finger4;
		this.zt = zt;
		this.czr = czr;
		this.czrXm = czrXm;
		this.czrBm = czrBm;
		this.czrKjbm = czrKjbm;
		this.czsj = czsj;
		this.czip = czip;
		this.czmac = czmac;
	}

	// Property accessors

	public String getJh() {
		return this.jh;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBmbh() {
		return this.bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	public String getFinger1() {
		return this.finger1;
	}

	public void setFinger1(String finger1) {
		this.finger1 = finger1;
	}

	public String getFinger2() {
		return this.finger2;
	}

	public void setFinger2(String finger2) {
		this.finger2 = finger2;
	}

	public String getFinger3() {
		return this.finger3;
	}

	public void setFinger3(String finger3) {
		this.finger3 = finger3;
	}

	public String getFinger4() {
		return this.finger4;
	}

	public void setFinger4(String finger4) {
		this.finger4 = finger4;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzrXm() {
		return this.czrXm;
	}

	public void setCzrXm(String czrXm) {
		this.czrXm = czrXm;
	}

	public String getCzrBm() {
		return this.czrBm;
	}

	public void setCzrBm(String czrBm) {
		this.czrBm = czrBm;
	}

	public String getCzrKjbm() {
		return this.czrKjbm;
	}

	public void setCzrKjbm(String czrKjbm) {
		this.czrKjbm = czrKjbm;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date string) {
		this.czsj = string;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public String getCzmac() {
		return this.czmac;
	}

	public void setCzmac(String czmac) {
		this.czmac = czmac;
	}

}