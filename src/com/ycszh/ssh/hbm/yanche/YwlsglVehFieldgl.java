package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * YwlsglVehFieldgl entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class YwlsglVehFieldgl extends BaseObject {

	// Fields

	private String id;
	private String zdmc;
	private String zdsm;
	private String gwkxgPtg;
	private String gwkxgLdg;
	private Integer showPx;
	private String czr;
	private String czrxm;
	private String czrbm;
	private String czrbm2;
	private String czip;
	private String czmac;
	private String cznr;
	private Date czsj;

	// Constructors

	/** default constructor */
	public YwlsglVehFieldgl() {
	}

	/** minimal constructor */
	public YwlsglVehFieldgl(String id) {
		this.id = id;
	}

	/** full constructor */
	public YwlsglVehFieldgl(String id, String zdmc, String zdsm,
			String gwkxgPtg, String gwkxgLdg, Integer showPx, String czr,
			String czrxm, String czrbm, String czrbm2, String czip,
			String czmac, String cznr, Date czsj) {
		this.id = id;
		this.zdmc = zdmc;
		this.zdsm = zdsm;
		this.gwkxgPtg = gwkxgPtg;
		this.gwkxgLdg = gwkxgLdg;
		this.showPx = showPx;
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

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getZdmc() {
		return this.zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public String getZdsm() {
		return this.zdsm;
	}

	public void setZdsm(String zdsm) {
		this.zdsm = zdsm;
	}

	public String getGwkxgPtg() {
		return this.gwkxgPtg;
	}

	public void setGwkxgPtg(String gwkxgPtg) {
		this.gwkxgPtg = gwkxgPtg;
	}

	public String getGwkxgLdg() {
		return this.gwkxgLdg;
	}

	public void setGwkxgLdg(String gwkxgLdg) {
		this.gwkxgLdg = gwkxgLdg;
	}

	public Integer getShowPx() {
		return this.showPx;
	}

	public void setShowPx(Integer showPx) {
		this.showPx = showPx;
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