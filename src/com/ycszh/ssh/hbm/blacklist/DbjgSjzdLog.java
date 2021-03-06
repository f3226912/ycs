package com.ycszh.ssh.hbm.blacklist;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DbjgSjzdLog entity. @author MyEclipse Persistence Tools
 */

public class DbjgSjzdLog extends BaseObject {

	// Fields

	public String xh;
	public String dmz;
	public String dmlb;
	public String dmms1;
	public String dmms2;
	public String bz;
	public String czr;
	public String czrxm;
	public String czbm;
	public String czbmKj;
	public Date czrq;
	public String czip;
	public String czmac;
	public String cznr;

	// Constructors

	/** default constructor */
	public DbjgSjzdLog() {
	}

	/** full constructor */
	public DbjgSjzdLog(String dmz, String dmlb, String dmms1, String dmms2,
			String bz, String czr, String czrxm, String czbm, String czbmKj,
			Date czrq, String czip, String czmac, String cznr) {
		this.dmz = dmz;
		this.dmlb = dmlb;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.bz = bz;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
		this.cznr = cznr;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
	}

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getDmms1() {
		return this.dmms1;
	}

	public void setDmms1(String dmms1) {
		this.dmms1 = dmms1;
	}

	public String getDmms2() {
		return this.dmms2;
	}

	public void setDmms2(String dmms2) {
		this.dmms2 = dmms2;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}

	public String getCzbmKj() {
		return this.czbmKj;
	}

	public void setCzbmKj(String czbmKj) {
		this.czbmKj = czbmKj;
	}

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
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

}