package com.ycszh.ssh.hbm.jsrdzda;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DzdaJszSjzd entity. @author MyEclipse Persistence Tools
 */

public class DzdaJszSjzd extends BaseObject {

	// Fields

	private String xh;
	private String dmz;
	private String dmlb;
	private String dmms1;
	private String dmms2;
	private String bz;
	private String czr;
	private String czrxm;
	private String czbm;
	private String czbmKj;
	private Date czrq;
	private String czip;
	private String czmac;

	// Constructors

	/** default constructor */
	public DzdaJszSjzd() {
	}

	/** minimal constructor */
	public DzdaJszSjzd(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public DzdaJszSjzd(String xh, String dmz, String dmlb, String dmms1,
			String dmms2, String bz, String czr, String czrxm, String czbm,
			String czbmKj, Date czrq, String czip, String czmac) {
		this.xh = xh;
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

}