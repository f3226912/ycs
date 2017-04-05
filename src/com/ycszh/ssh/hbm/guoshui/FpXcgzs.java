package com.ycszh.ssh.hbm.guoshui;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * FpXcgzs entity. @author MyEclipse Persistence Tools
 */

 @SuppressWarnings("serial")
public class FpXcgzs extends BaseObject {

	// Fields

	/**
	 * 
	 */
	public String gzszmbh;
	public String nsrmc;
	public String cpxh;
	public String cjh;
	public String fdjh;
	public String msbz;
	public String fpdm;
	public String fphm;

	// Constructors

	/** default constructor */
	public FpXcgzs() {
	}

	/** full constructor */
	public FpXcgzs(String nsrmc, String cpxh, String cjh, String fdjh,
			String msbz, String fpdm, String fphm) {
		this.nsrmc = nsrmc;
		this.cpxh = cpxh;
		this.cjh = cjh;
		this.fdjh = fdjh;
		this.msbz = msbz;
		this.fpdm = fpdm;
		this.fphm = fphm;
	}

	// Property accessors

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

}