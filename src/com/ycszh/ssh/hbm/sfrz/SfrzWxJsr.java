package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

/**
 * SfrzWxJsr entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzWxJsr implements java.io.Serializable {

	// Fields

	private String xh;
	private String UId;
	private String jszhm;
	private String xm;
	private String dabh;
	private Date lrsj;
	private String lrip;
	private String shzt;
	private String tbyy;
	private String shrdm;
	private String shrxm;
	private String shbm;
	private String shbmKj;
	private Date shsj;
	private String ship;
	private String shmac;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;

	// Constructors

	/** default constructor */
	public SfrzWxJsr() {
	}

	/** minimal constructor */
	public SfrzWxJsr(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public SfrzWxJsr(String xh, String UId, String jszhm, String xm,
			String dabh, Date lrsj, String lrip, String shzt, String tbyy,
			String shrdm, String shrxm, String shbm, String shbmKj, Date shsj,
			String ship, String shmac, String synFlag, Date tranDate,
			String tranFlag) {
		this.xh = xh;
		this.UId = UId;
		this.jszhm = jszhm;
		this.xm = xm;
		this.dabh = dabh;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.shzt = shzt;
		this.tbyy = tbyy;
		this.shrdm = shrdm;
		this.shrxm = shrxm;
		this.shbm = shbm;
		this.shbmKj = shbmKj;
		this.shsj = shsj;
		this.ship = ship;
		this.shmac = shmac;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	public String getJszhm() {
		return this.jszhm;
	}

	public void setJszhm(String jszhm) {
		this.jszhm = jszhm;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getShrdm() {
		return this.shrdm;
	}

	public void setShrdm(String shrdm) {
		this.shrdm = shrdm;
	}

	public String getShrxm() {
		return this.shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
	}

	public String getShbmKj() {
		return this.shbmKj;
	}

	public void setShbmKj(String shbmKj) {
		this.shbmKj = shbmKj;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getShip() {
		return this.ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getShmac() {
		return this.shmac;
	}

	public void setShmac(String shmac) {
		this.shmac = shmac;
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