package com.ycszh.ssh.hbm.veh;

/**
 * SlgSjzdWzp entity. @author MyEclipse Persistence Tools
 */

public class SlgSjzdWzp implements java.io.Serializable {

	// Fields

	private String id;
	private String dmz;
	private String dmms1;
	private String dmms2;
	private String dmlb;
	private String bz;
	private String vehDrv;
	private String dmmc;
	private String isFlag;

	// Constructors

	/** default constructor */
	public SlgSjzdWzp() {
	}

	/** minimal constructor */
	public SlgSjzdWzp(String id, String dmz) {
		this.id = id;
		this.dmz = dmz;
	}

	/** full constructor */
	public SlgSjzdWzp(String id, String dmz, String dmms1, String dmms2,
			String dmlb, String bz, String vehDrv, String dmmc, String isFlag) {
		this.id = id;
		this.dmz = dmz;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.dmlb = dmlb;
		this.bz = bz;
		this.vehDrv = vehDrv;
		this.dmmc = dmmc;
		this.isFlag = isFlag;
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

	public String getVehDrv() {
		return this.vehDrv;
	}

	public void setVehDrv(String vehDrv) {
		this.vehDrv = vehDrv;
	}

	public String getDmmc() {
		return this.dmmc;
	}

	public void setDmmc(String dmmc) {
		this.dmmc = dmmc;
	}

	public String getIsFlag() {
		return this.isFlag;
	}

	public void setIsFlag(String isFlag) {
		this.isFlag = isFlag;
	}

}