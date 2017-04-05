package com.ycszh.ssh.hbm.yanche;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * YwlsglVehSjzd entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class YwlsglVehSjzd extends BaseObject {

	// Fields

	private String id;
	private String dmz;
	private String dmsm1;
	private String dmsm2;
	private String dmsm3;
	private String dmlb;
	private String vehDrv;

	// Constructors

	/** default constructor */
	public YwlsglVehSjzd() {
	}

	/** minimal constructor */
	public YwlsglVehSjzd(String id) {
		this.id = id;
	}

	/** full constructor */
	public YwlsglVehSjzd(String id, String dmz, String dmsm1, String dmsm2,
			String dmsm3, String dmlb, String vehDrv) {
		this.id = id;
		this.dmz = dmz;
		this.dmsm1 = dmsm1;
		this.dmsm2 = dmsm2;
		this.dmsm3 = dmsm3;
		this.dmlb = dmlb;
		this.vehDrv = vehDrv;
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

	public String getDmsm1() {
		return this.dmsm1;
	}

	public void setDmsm1(String dmsm1) {
		this.dmsm1 = dmsm1;
	}

	public String getDmsm2() {
		return this.dmsm2;
	}

	public void setDmsm2(String dmsm2) {
		this.dmsm2 = dmsm2;
	}

	public String getDmsm3() {
		return this.dmsm3;
	}

	public void setDmsm3(String dmsm3) {
		this.dmsm3 = dmsm3;
	}

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getVehDrv() {
		return this.vehDrv;
	}

	public void setVehDrv(String vehDrv) {
		this.vehDrv = vehDrv;
	}

}