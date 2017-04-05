package com.ycszh.ssh.hbm.xyc;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * YcsXycCode entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class YcsXycCode extends BaseObject {

	// Fields

	private String id;
	private String dmz;
	private String dmms1;
	private String dmms2;
	private String dmlb;
	private String bz;
	private String lrrdm;
	private String lrrxm;
	private String lrrbm;
	private Date lrsj;

	// Constructors

	/** default constructor */
	public YcsXycCode() {
	}

	/** minimal constructor */
	public YcsXycCode(String id, String dmz) {
		this.id = id;
		this.dmz = dmz;
	}

	/** full constructor */
	public YcsXycCode(String id, String dmz, String dmms1, String dmms2,
			String dmlb, String bz, String lrrdm, String lrrxm, String lrrbm,
			Date lrsj) {
		this.id = id;
		this.dmz = dmz;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.dmlb = dmlb;
		this.bz = bz;
		this.lrrdm = lrrdm;
		this.lrrxm = lrrxm;
		this.lrrbm = lrrbm;
		this.lrsj = lrsj;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
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

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getLrrdm() {
		return this.lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getLrrxm() {
		return this.lrrxm;
	}

	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}

	public String getLrrbm() {
		return this.lrrbm;
	}

	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

}