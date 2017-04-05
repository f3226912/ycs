package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzLevelYwlx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzLevelYwlx extends BaseObject {

	// Fields

	private String yid;
	private String ywdm;
	private String ywmc;
	private String ywlx;
	private String bllx;
	private String vehdrv;
	private String userLevel;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;

	// Constructors

	/** default constructor */
	public SfrzLevelYwlx() {
	}

	/** minimal constructor */
	public SfrzLevelYwlx(String yid) {
		this.yid = yid;
	}

	/** full constructor */
	public SfrzLevelYwlx(String yid, String ywdm, String ywmc, String ywlx,
			String bllx, String vehdrv, String userLevel, String synFlag,
			Date tranDate, String tranFlag) {
		this.yid = yid;
		this.ywdm = ywdm;
		this.ywmc = ywmc;
		this.ywlx = ywlx;
		this.bllx = bllx;
		this.vehdrv = vehdrv;
		this.userLevel = userLevel;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
	}

	// Property accessors

	public String getYid() {
		return this.yid;
	}

	public void setYid(String yid) {
		this.yid = yid;
	}

	public String getYwdm() {
		return this.ywdm;
	}

	public void setYwdm(String ywdm) {
		this.ywdm = ywdm;
	}

	public String getYwmc() {
		return this.ywmc;
	}

	public void setYwmc(String ywmc) {
		this.ywmc = ywmc;
	}

	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getBllx() {
		return this.bllx;
	}

	public void setBllx(String bllx) {
		this.bllx = bllx;
	}

	public String getVehdrv() {
		return this.vehdrv;
	}

	public void setVehdrv(String vehdrv) {
		this.vehdrv = vehdrv;
	}

	public String getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

}