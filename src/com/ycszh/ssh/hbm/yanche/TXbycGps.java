package com.ycszh.ssh.hbm.yanche;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * TXbycGps entity. @author MyEclipse Persistence Tools
 */

public class TXbycGps extends BaseObject {

	// Fields

	public String gpsId;
	public String jgmc;
	public String xxdz;
	public String gpsx;
	public String gpsy;
	public String zt;
	public String czr;
	public String czrxm;
	public String czbm;
	public String czbmKj;
	public Date czrq;
	public String czip;
	public String yxjl;

	// Constructors

	/** default constructor */
	public TXbycGps() {
	}

	/** minimal constructor */
	public TXbycGps(String gpsId) {
		this.gpsId = gpsId;
	}

	/** full constructor */
	public TXbycGps(String gpsId, String jgmc, String xxdz,
			String gpsx, String gpsy, String zt, String czr,
			String czrxm, String czbm, String czbmKj, Date czrq, String czip,
			String yxjl) {
		this.gpsId = gpsId;
		this.jgmc = jgmc;
		this.xxdz = xxdz;
		this.gpsx = gpsx;
		this.gpsy = gpsy;
		this.zt = zt;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
		this.yxjl = yxjl;
	}

	// Property accessors

	public String getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	public String getJgmc() {
		return this.jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getXxdz() {
		return this.xxdz;
	}

	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}

	public String getGpsx() {
		return this.gpsx;
	}

	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}

	public String getGpsy() {
		return this.gpsy;
	}

	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
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

	public String getYxjl() {
		return this.yxjl;
	}

	public void setYxjl(String yxjl) {
		this.yxjl = yxjl;
	}

}