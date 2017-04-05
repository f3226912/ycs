package com.ycszh.ssh.hbm.sfrz;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzSjzd entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzSjzd extends BaseObject {

	// Fields

	private String xh;
	private String dmz;
	private String dmlb;
	private String dmsm;
	private String dmlbms;

	// Constructors

	/** default constructor */
	public SfrzSjzd() {
	}

	/** minimal constructor */
	public SfrzSjzd(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public SfrzSjzd(String xh, String dmz, String dmlb, String dmsm,
			String dmlbms) {
		this.xh = xh;
		this.dmz = dmz;
		this.dmlb = dmlb;
		this.dmsm = dmsm;
		this.dmlbms = dmlbms;
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

	public String getDmsm() {
		return this.dmsm;
	}

	public void setDmsm(String dmsm) {
		this.dmsm = dmsm;
	}

	public String getDmlbms() {
		return this.dmlbms;
	}

	public void setDmlbms(String dmlbms) {
		this.dmlbms = dmlbms;
	}

}