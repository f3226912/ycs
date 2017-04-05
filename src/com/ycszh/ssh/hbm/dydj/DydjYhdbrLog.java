package com.ycszh.ssh.hbm.dydj;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DydjYhdbr entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DydjYhdbrLog extends BaseObject {

	// Fields

	public Integer id;
	public Integer yhUserId;
	public String dbrSfzmhm;
	public String dbrXm;
	public String dbrSex;
	public String dbrLxdz;
	public String dbrSjhm;
	public Date yxqz;
	public String flag;
	public String dmms1;
	public String dmms2;
	public String lrr;
	public String lrrxm;
	public String lrrbm;
	public String lrrkjbm;
	public Date lrsj;
	public String lrip;
	
	public String czr;
	public String czrxm;
	public String czrbm;
	public String czrkjbm;
	public Date czsj;
	public String czip;
	public String czmac;

	// Constructors

	/** default constructor */
	public DydjYhdbrLog() {
	}

	/** full constructor */
	public DydjYhdbrLog(Integer yhUserId, String dbrSfzmhm, String dbrXm,
			Date yxqz, String flag, String dmms1, String dmms2, String lrr,
			String lrrxm, String lrrbm, String lrrkjbm, Date lrsj, String lrip) {
		this.yhUserId = yhUserId;
		this.dbrSfzmhm = dbrSfzmhm;
		this.dbrXm = dbrXm;
		this.yxqz = yxqz;
		this.flag = flag;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.lrr = lrr;
		this.lrrxm = lrrxm;
		this.lrrbm = lrrbm;
		this.lrrkjbm = lrrkjbm;
		this.lrsj = lrsj;
		this.lrip = lrip;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYhUserId() {
		return this.yhUserId;
	}

	public void setYhUserId(Integer yhUserId) {
		this.yhUserId = yhUserId;
	}

	public String getDbrSfzmhm() {
		return this.dbrSfzmhm;
	}

	public void setDbrSfzmhm(String dbrSfzmhm) {
		this.dbrSfzmhm = dbrSfzmhm;
	}

	public String getDbrXm() {
		return this.dbrXm;
	}

	public void setDbrXm(String dbrXm) {
		this.dbrXm = dbrXm;
	}

	public Date getYxqz() {
		return this.yxqz;
	}

	public void setYxqz(Date yxqz) {
		this.yxqz = yxqz;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
	
	public String getLrr() {
		return lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrxm() {
		return lrrxm;
	}

	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}

	public String getLrrbm() {
		return lrrbm;
	}

	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}

	public String getLrrkjbm() {
		return lrrkjbm;
	}

	public void setLrrkjbm(String lrrkjbm) {
		this.lrrkjbm = lrrkjbm;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public String getCzr() {
		return czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzrxm() {
		return czrxm;
	}

	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}

	public String getCzrbm() {
		return czrbm;
	}

	public void setCzrbm(String czrbm) {
		this.czrbm = czrbm;
	}

	public String getCzrkjbm() {
		return czrkjbm;
	}

	public void setCzrkjbm(String czrkjbm) {
		this.czrkjbm = czrkjbm;
	}

	public Date getCzsj() {
		return czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCzip() {
		return czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public String getCzmac() {
		return czmac;
	}

	public void setCzmac(String czmac) {
		this.czmac = czmac;
	}

	public String getDbrSex() {
		return dbrSex;
	}

	public void setDbrSex(String dbrSex) {
		this.dbrSex = dbrSex;
	}

	public String getDbrLxdz() {
		return dbrLxdz;
	}

	public void setDbrLxdz(String dbrLxdz) {
		this.dbrLxdz = dbrLxdz;
	}

	public String getDbrSjhm() {
		return dbrSjhm;
	}

	public void setDbrSjhm(String dbrSjhm) {
		this.dbrSjhm = dbrSjhm;
	}
	
}