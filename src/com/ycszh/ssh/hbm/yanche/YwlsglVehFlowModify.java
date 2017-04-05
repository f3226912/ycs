package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * YwlsglVehFlowModify entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class YwlsglVehFlowModify extends BaseObject {

	// Fields

	private String id;
	private String lsh;
	private String zdmc;
	private String zdsm;
	private String zdOld;
	private String zdNew;
	private String czr;
	private String czrxm;
	private String czrbm;
	private String czrbmKj;
	private String czip;
	private String czmac;
	private String cznr;
	private Date czsj;

	// Constructors

	/** default constructor */
	public YwlsglVehFlowModify() {
	}

	/** minimal constructor */
	public YwlsglVehFlowModify(String id) {
		this.id = id;
	}

	/** full constructor */
	public YwlsglVehFlowModify(String id, String lsh, String zdmc, String zdsm,
			String zdOld, String zdNew, String czr, String czrxm, String czrbm,
			String czrbmKj, String czip, String czmac, String cznr, Date czsj) {
		this.id = id;
		this.lsh = lsh;
		this.zdmc = zdmc;
		this.zdsm = zdsm;
		this.zdOld = zdOld;
		this.zdNew = zdNew;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czrbmKj = czrbmKj;
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

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
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

	public String getZdOld() {
		return this.zdOld;
	}

	public void setZdOld(String zdOld) {
		this.zdOld = zdOld;
	}

	public String getZdNew() {
		return this.zdNew;
	}

	public void setZdNew(String zdNew) {
		this.zdNew = zdNew;
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

	public String getCzrbmKj() {
		return this.czrbmKj;
	}

	public void setCzrbmKj(String czrbmKj) {
		this.czrbmKj = czrbmKj;
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