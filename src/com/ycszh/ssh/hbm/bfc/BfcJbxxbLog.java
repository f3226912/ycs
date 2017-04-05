package com.ycszh.ssh.hbm.bfc;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * BfcJbxxbLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BfcJbxxbLog extends BaseObject {

	// Fields

	public String xh;
	public String hphm;
	public String hpzl;
	public String sfzmmc;
	public String sfzmhm;
	public String syr;
	public String clsbdh;
	public Date qzbfqz;
	public String zt;
	public String bz;
	public String sfyx;
	public String czr;
	public String czrxm;
	public String czrbm;
	public String czip;
	public Date czsj;
	public String synFlag;
	public String tranFlag;
	public Date tranDate;
	public String cznr;

	// Constructors

	/** default constructor */
	public BfcJbxxbLog() {
	}

	/** minimal constructor */
	public BfcJbxxbLog(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public BfcJbxxbLog(String xh, String hphm, String hpzl, String sfzmmc,
			String sfzmhm, String syr, String clsbdh, Date qzbfqz, String zt,
			String bz, String sfyx, String czr, String czrxm, String czrbm,
			String czip, Date czsj, String cznr) {
		this.xh = xh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.syr = syr;
		this.clsbdh = clsbdh;
		this.qzbfqz = qzbfqz;
		this.zt = zt;
		this.bz = bz;
		this.sfyx = sfyx;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czip = czip;
		this.czsj = czsj;
		this.cznr = cznr;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHphm() {
		return this.hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return this.hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getSfzmmc() {
		return this.sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public Date getQzbfqz() {
		return this.qzbfqz;
	}

	public void setQzbfqz(Date qzbfqz) {
		this.qzbfqz = qzbfqz;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getSfyx() {
		return this.sfyx;
	}

	public void setSfyx(String sfyx) {
		this.sfyx = sfyx;
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

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	/**
	 * @return the synFlag
	 */
	public String getSynFlag() {
		return synFlag;
	}

	/**
	 * @param synFlag the synFlag to set
	 */
	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	/**
	 * @return the tranFlag
	 */
	public String getTranFlag() {
		return tranFlag;
	}

	/**
	 * @param tranFlag the tranFlag to set
	 */
	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

	/**
	 * @return the tranDate
	 */
	public Date getTranDate() {
		return tranDate;
	}

	/**
	 * @param tranDate the tranDate to set
	 */
	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

}