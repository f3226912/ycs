package com.ycszh.ssh.hbm.ezxfw;

import java.math.BigDecimal;
import java.util.Date;

/**
 * EzDzwt entity. @author MyEclipse Persistence Tools
 */

public class EzDzwt implements java.io.Serializable {

	// Fields

	private String xh;
	private String wtYwlx;
	private String wtYwyy;
	private String wtXm;
	private String wtSfzmhm;
	private String wtHphm;
	private String wtHpzl;
	private Date wtSj;
	private BigDecimal wtYxq;
	private String stXm;
	private String stSfzmhm;
	private String stLxdh;
	private String lybz;
	private String zt;
	private String ywlsh;
	private String ywblzh;
	private Date ywblsj;
	private String ywblip;
	private String ywblmac;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public EzDzwt() {
	}

	/** minimal constructor */
	public EzDzwt(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public EzDzwt(String xh, String wtYwlx, String wtYwyy, String wtXm,
			String wtSfzmhm, String wtHphm, String wtHpzl, Date wtSj,
			BigDecimal wtYxq, String stXm, String stSfzmhm, String stLxdh,
			String lybz, String zt, String ywlsh, String ywblzh, Date ywblsj,
			String ywblip, String ywblmac, String synFlag, String tranFlag,
			Date tranDate) {
		this.xh = xh;
		this.wtYwlx = wtYwlx;
		this.wtYwyy = wtYwyy;
		this.wtXm = wtXm;
		this.wtSfzmhm = wtSfzmhm;
		this.wtHphm = wtHphm;
		this.wtHpzl = wtHpzl;
		this.wtSj = wtSj;
		this.wtYxq = wtYxq;
		this.stXm = stXm;
		this.stSfzmhm = stSfzmhm;
		this.stLxdh = stLxdh;
		this.lybz = lybz;
		this.zt = zt;
		this.ywlsh = ywlsh;
		this.ywblzh = ywblzh;
		this.ywblsj = ywblsj;
		this.ywblip = ywblip;
		this.ywblmac = ywblmac;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getWtYwlx() {
		return this.wtYwlx;
	}

	public void setWtYwlx(String wtYwlx) {
		this.wtYwlx = wtYwlx;
	}

	public String getWtYwyy() {
		return this.wtYwyy;
	}

	public void setWtYwyy(String wtYwyy) {
		this.wtYwyy = wtYwyy;
	}

	public String getWtXm() {
		return this.wtXm;
	}

	public void setWtXm(String wtXm) {
		this.wtXm = wtXm;
	}

	public String getWtSfzmhm() {
		return this.wtSfzmhm;
	}

	public void setWtSfzmhm(String wtSfzmhm) {
		this.wtSfzmhm = wtSfzmhm;
	}

	public String getWtHphm() {
		return this.wtHphm;
	}

	public void setWtHphm(String wtHphm) {
		this.wtHphm = wtHphm;
	}

	public String getWtHpzl() {
		return this.wtHpzl;
	}

	public void setWtHpzl(String wtHpzl) {
		this.wtHpzl = wtHpzl;
	}

	public Date getWtSj() {
		return this.wtSj;
	}

	public void setWtSj(Date wtSj) {
		this.wtSj = wtSj;
	}

	public BigDecimal getWtYxq() {
		return this.wtYxq;
	}

	public void setWtYxq(BigDecimal wtYxq) {
		this.wtYxq = wtYxq;
	}

	public String getStXm() {
		return this.stXm;
	}

	public void setStXm(String stXm) {
		this.stXm = stXm;
	}

	public String getStSfzmhm() {
		return this.stSfzmhm;
	}

	public void setStSfzmhm(String stSfzmhm) {
		this.stSfzmhm = stSfzmhm;
	}

	public String getStLxdh() {
		return this.stLxdh;
	}

	public void setStLxdh(String stLxdh) {
		this.stLxdh = stLxdh;
	}

	public String getLybz() {
		return this.lybz;
	}

	public void setLybz(String lybz) {
		this.lybz = lybz;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getYwlsh() {
		return this.ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getYwblzh() {
		return this.ywblzh;
	}

	public void setYwblzh(String ywblzh) {
		this.ywblzh = ywblzh;
	}

	public Date getYwblsj() {
		return this.ywblsj;
	}

	public void setYwblsj(Date ywblsj) {
		this.ywblsj = ywblsj;
	}

	public String getYwblip() {
		return this.ywblip;
	}

	public void setYwblip(String ywblip) {
		this.ywblip = ywblip;
	}

	public String getYwblmac() {
		return this.ywblmac;
	}

	public void setYwblmac(String ywblmac) {
		this.ywblmac = ywblmac;
	}

	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

}