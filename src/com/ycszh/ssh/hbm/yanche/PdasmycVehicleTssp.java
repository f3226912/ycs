package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * PdasmycVehicleTssp entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PdasmycVehicleTssp extends BaseObject {

	// Fields

	private String id;
	private String ywlsh;
	private String tssplx;
	private String bz;
	private String shr;
	private String shrxm;
	private String shbm;
	private String shbm2;
	private Date shsj;
	private String ship;
	private String shmac;

	// Constructors

	/** default constructor */
	public PdasmycVehicleTssp() {
	}

	/** minimal constructor */
	public PdasmycVehicleTssp(String id) {
		this.id = id;
	}

	/** full constructor */
	public PdasmycVehicleTssp(String id, String ywlsh, String tssplx,
			String bz, String shr, String shrxm, String shbm, String shbm2,
			Date shsj, String ship, String shmac) {
		this.id = id;
		this.ywlsh = ywlsh;
		this.tssplx = tssplx;
		this.bz = bz;
		this.shr = shr;
		this.shrxm = shrxm;
		this.shbm = shbm;
		this.shbm2 = shbm2;
		this.shsj = shsj;
		this.ship = ship;
		this.shmac = shmac;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYwlsh() {
		return this.ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

	public String getTssplx() {
		return this.tssplx;
	}

	public void setTssplx(String tssplx) {
		this.tssplx = tssplx;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getShr() {
		return this.shr;
	}

	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShrxm() {
		return this.shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
	}

	public String getShbm2() {
		return this.shbm2;
	}

	public void setShbm2(String shbm2) {
		this.shbm2 = shbm2;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getShip() {
		return this.ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getShmac() {
		return this.shmac;
	}

	public void setShmac(String shmac) {
		this.shmac = shmac;
	}

}