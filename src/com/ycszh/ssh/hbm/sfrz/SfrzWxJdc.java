package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

/**
 * SfrzWxJdc entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzWxJdc implements java.io.Serializable {

	// Fields

	private String xh;
	private String hphm;
	private String hpzl;
	private String sfbr;
	private String cjh4;
	private String czxm;
	private String czfzmhm;
	private String UId;
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
	private String bindDepartment;
	private String czlxdh;

	// Constructors

	/** default constructor */
	public SfrzWxJdc() {
	}

	/** minimal constructor */
	public SfrzWxJdc(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public SfrzWxJdc(String xh, String hphm, String hpzl, String sfbr,
			String cjh4, String czxm, String czfzmhm, String UId, Date lrsj,
			String lrip, String shzt, String tbyy, String shrdm, String shrxm,
			String shbm, String shbmKj, Date shsj, String ship, String shmac,
			String synFlag, Date tranDate, String tranFlag,
			String bindDepartment, String czlxdh) {
		this.xh = xh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.sfbr = sfbr;
		this.cjh4 = cjh4;
		this.czxm = czxm;
		this.czfzmhm = czfzmhm;
		this.UId = UId;
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
		this.bindDepartment = bindDepartment;
		this.czlxdh = czlxdh;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHphm() {
		return this.hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return this.hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getSfbr() {
		return this.sfbr;
	}

	public void setSfbr(String sfbr) {
		this.sfbr = sfbr;
	}

	public String getCjh4() {
		return this.cjh4;
	}

	public void setCjh4(String cjh4) {
		this.cjh4 = cjh4;
	}

	public String getCzxm() {
		return this.czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getCzfzmhm() {
		return this.czfzmhm;
	}

	public void setCzfzmhm(String czfzmhm) {
		this.czfzmhm = czfzmhm;
	}

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
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

	public String getBindDepartment() {
		return this.bindDepartment;
	}

	public void setBindDepartment(String bindDepartment) {
		this.bindDepartment = bindDepartment;
	}

	public String getCzlxdh() {
		return this.czlxdh;
	}

	public void setCzlxdh(String czlxdh) {
		this.czlxdh = czlxdh;
	}

}