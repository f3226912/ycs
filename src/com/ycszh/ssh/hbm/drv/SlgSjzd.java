package com.ycszh.ssh.hbm.drv;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.hbm.drv
 * @文件名:SlgSjzd.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class SlgSjzd extends BaseObject {

	// Fields
	private String id;
	private String dmz;
	private String dmmc;
	private String dmlb;
	private String bz;
	private String vehDrv;
	private String dmms1;
	private String dmms2;
	private String isFlag;

	// Constructors

	/** default constructor */
	public SlgSjzd() {
	}

	/** minimal constructor */
	public SlgSjzd(String dmz, String dmmc, String dmlb) {
		this.dmz = dmz;
		this.dmmc = dmmc;
		this.dmlb = dmlb;
	}

	/** full constructor */
	public SlgSjzd(String dmz, String dmmc, String dmlb, String bz,
			String vehDrv, String dmms2, String dmms1) {
		this.dmz = dmz;
		this.dmmc = dmmc;
		this.dmlb = dmlb;
		this.bz = bz;
		this.vehDrv = vehDrv;
		this.dmms2 = dmms2;
		this.dmms1 = dmms1;
	}

	// Property accessors

	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
	}

	public String getDmmc() {
		return this.dmmc;
	}

	public void setDmmc(String dmmc) {
		this.dmmc = dmmc;
	}

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getVehDrv() {
		return this.vehDrv;
	}

	public void setVehDrv(String vehDrv) {
		this.vehDrv = vehDrv;
	}

	public String getDmms2() {
		return dmms2;
	}

	public void setDmms2(String dmms2) {
		this.dmms2 = dmms2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDmms1() {
		return dmms1;
	}

	public void setDmms1(String dmms1) {
		this.dmms1 = dmms1;
	}

	public String getIsFlag() {
		return isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}
	
}