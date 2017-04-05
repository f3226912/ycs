package com.ycszh.ssh.hbm.guoshui;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * FpXcgzsLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class FpXcgzsLog extends BaseObject {

	// Fields

	/**
	 * 
	 */
	public String id;
	public String gzszmbh;
	public String nsrmc;
	public String cpxh;
	public String cjh;
	public String fdjh;
	public String msbz;
	public String fpdm;
	public String fphm;
	public String czdm;
	public String czxm;
	public String czbm;
	public String cznr;
	public String czip;
	public Date czrq;

	// Constructors

	/** default constructor */
	public FpXcgzsLog() {
	}

	/** minimal constructor */
	public FpXcgzsLog(String gzszmbh) {
		this.gzszmbh = gzszmbh;
	}

	/** full constructor */
	public FpXcgzsLog(String gzszmbh, String nsrmc, String cpxh, String cjh,
			String fdjh, String msbz, String fpdm, String fphm, String czdm,
			String czxm, String czbm, String cznr, String czip, Date czrq) {
		this.gzszmbh = gzszmbh;
		this.nsrmc = nsrmc;
		this.cpxh = cpxh;
		this.cjh = cjh;
		this.fdjh = fdjh;
		this.msbz = msbz;
		this.fpdm = fpdm;
		this.fphm = fphm;
		this.czdm = czdm;
		this.czxm = czxm;
		this.czbm = czbm;
		this.cznr = cznr;
		this.czip = czip;
		this.czrq = czrq;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGzszmbh() {
		return this.gzszmbh;
	}

	public void setGzszmbh(String gzszmbh) {
		this.gzszmbh = gzszmbh;
	}

	public String getNsrmc() {
		return this.nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getCpxh() {
		return this.cpxh;
	}

	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}

	public String getCjh() {
		return this.cjh;
	}

	public void setCjh(String cjh) {
		this.cjh = cjh;
	}

	public String getFdjh() {
		return this.fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getMsbz() {
		return this.msbz;
	}

	public void setMsbz(String msbz) {
		this.msbz = msbz;
	}

	public String getFpdm() {
		return this.fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFphm() {
		return this.fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public String getCzdm() {
		return this.czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	public String getCzxm() {
		return this.czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

}