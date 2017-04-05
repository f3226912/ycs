package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * PdasmycChdlr entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PdasmycChdlr extends BaseObject {

	// Fields

	private String id;
	private String dlrxm;
	private String dlrlxdh;
	private String dlrsfzmhm;
	private String chid;
	private String dlrzh;
	private String chmc;
	private Date lrsj;
	private String lrip;
	private String lrmac;
	private String shzt;
	private String shbz;
	private String shr;
	private String shrxm;
	private Date shsj;
	private String ship;
	private String shmac;
	private String shbm;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public PdasmycChdlr() {
	}

	/** minimal constructor */
	public PdasmycChdlr(String id) {
		this.id = id;
	}

	/** full constructor */
	public PdasmycChdlr(String id, String dlrxm, String dlrlxdh,
			String dlrsfzmhm, String chid, String dlrzh, String chmc,
			Date lrsj, String lrip, String lrmac, String shzt, String shbz,
			String shr, String shrxm, Date shsj, String ship, String shmac,
			String shbm, String synFlag, String tranFlag, Date tranDate) {
		this.id = id;
		this.dlrxm = dlrxm;
		this.dlrlxdh = dlrlxdh;
		this.dlrsfzmhm = dlrsfzmhm;
		this.chid = chid;
		this.dlrzh = dlrzh;
		this.chmc = chmc;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.lrmac = lrmac;
		this.shzt = shzt;
		this.shbz = shbz;
		this.shr = shr;
		this.shrxm = shrxm;
		this.shsj = shsj;
		this.ship = ship;
		this.shmac = shmac;
		this.shbm = shbm;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDlrxm() {
		return this.dlrxm;
	}

	public void setDlrxm(String dlrxm) {
		this.dlrxm = dlrxm;
	}

	public String getDlrlxdh() {
		return this.dlrlxdh;
	}

	public void setDlrlxdh(String dlrlxdh) {
		this.dlrlxdh = dlrlxdh;
	}

	public String getDlrsfzmhm() {
		return this.dlrsfzmhm;
	}

	public void setDlrsfzmhm(String dlrsfzmhm) {
		this.dlrsfzmhm = dlrsfzmhm;
	}

	public String getChid() {
		return this.chid;
	}

	public void setChid(String chid) {
		this.chid = chid;
	}

	public String getDlrzh() {
		return this.dlrzh;
	}

	public void setDlrzh(String dlrzh) {
		this.dlrzh = dlrzh;
	}

	public String getChmc() {
		return this.chmc;
	}

	public void setChmc(String chmc) {
		this.chmc = chmc;
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

	public String getLrmac() {
		return this.lrmac;
	}

	public void setLrmac(String lrmac) {
		this.lrmac = lrmac;
	}

	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getShbz() {
		return this.shbz;
	}

	public void setShbz(String shbz) {
		this.shbz = shbz;
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

	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
	}

	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

}