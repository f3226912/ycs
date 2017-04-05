package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * VDataCheckLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class VDataCheckLog extends BaseObject {

	// Fields

	public String xh;
	public String lsh;
	public String hphm;
	public String hpzl;
	public String syr;
	public String djzsbh;
	public String clxh;
	public String clsbdh;
	public String fdjh;
	public String checkFalg;
	public String checkMsg;
	public String checkBz;
	public String checkYh;
	public Date checkDate;
	public String tyzxh;
	public String ycLx;
	public String czr;
	public String czrxm;
	public String czrbm;
	public String czrbm2;
	public String czip;
	public String czmac;
	public String cznr;
	public Date czsj;

	// Constructors

	/** default constructor */
	public VDataCheckLog() {
	}

	/** minimal constructor */
	public VDataCheckLog(String xh, String lsh) {
		this.xh = xh;
		this.lsh = lsh;
	}

	/** full constructor */
	public VDataCheckLog(String xh, String lsh, String hphm, String hpzl,
			String syr, String djzsbh, String clxh, String clsbdh, String fdjh,
			String checkFalg, String checkMsg, String checkBz, String checkYh,
			Date checkDate, String tyzxh, String ycLx, String czr,
			String czrxm, String czrbm, String czrbm2, String czip,
			String czmac, String cznr, Date czsj) {
		this.xh = xh;
		this.lsh = lsh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.syr = syr;
		this.djzsbh = djzsbh;
		this.clxh = clxh;
		this.clsbdh = clsbdh;
		this.fdjh = fdjh;
		this.checkFalg = checkFalg;
		this.checkMsg = checkMsg;
		this.checkBz = checkBz;
		this.checkYh = checkYh;
		this.checkDate = checkDate;
		this.tyzxh = tyzxh;
		this.ycLx = ycLx;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czrbm2 = czrbm2;
		this.czip = czip;
		this.czmac = czmac;
		this.cznr = cznr;
		this.czsj = czsj;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
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

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getDjzsbh() {
		return this.djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getClxh() {
		return this.clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getFdjh() {
		return this.fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getCheckFalg() {
		return this.checkFalg;
	}

	public void setCheckFalg(String checkFalg) {
		this.checkFalg = checkFalg;
	}

	public String getCheckMsg() {
		return this.checkMsg;
	}

	public void setCheckMsg(String checkMsg) {
		this.checkMsg = checkMsg;
	}

	public String getCheckBz() {
		return this.checkBz;
	}

	public void setCheckBz(String checkBz) {
		this.checkBz = checkBz;
	}

	public String getCheckYh() {
		return this.checkYh;
	}

	public void setCheckYh(String checkYh) {
		this.checkYh = checkYh;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getTyzxh() {
		return this.tyzxh;
	}

	public void setTyzxh(String tyzxh) {
		this.tyzxh = tyzxh;
	}

	public String getYcLx() {
		return this.ycLx;
	}

	public void setYcLx(String ycLx) {
		this.ycLx = ycLx;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzrxm() {
		return this.czrxm;
	}

	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}

	public String getCzrbm() {
		return this.czrbm;
	}

	public void setCzrbm(String czrbm) {
		this.czrbm = czrbm;
	}

	public String getCzrbm2() {
		return this.czrbm2;
	}

	public void setCzrbm2(String czrbm2) {
		this.czrbm2 = czrbm2;
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

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

}