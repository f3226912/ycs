package com.ycszh.ssh.hbm.rsc;

import java.math.BigDecimal;
import java.util.Date;

/**
 * RscDbjg entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class RscDbjg implements java.io.Serializable {

	// Fields

	private BigDecimal xh;
	private String jgmc;
	private String jgfzr;
	private String lxdz;
	private String lxdh;
	private String jgzt;
	private Date bayxq;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String scbz;
	private BigDecimal fwzxh;
	private String zzjgdmz;
	private String sslx;
	private String cid;

	// Constructors

	/** default constructor */
	public RscDbjg() {
	}

	/** minimal constructor */
	public RscDbjg(BigDecimal xh) {
		this.xh = xh;
	}

	/** full constructor */
	public RscDbjg(BigDecimal xh, String jgmc, String jgfzr, String lxdz,
			String lxdh, String jgzt, Date bayxq, String synFlag,
			String tranFlag, Date tranDate, String scbz, BigDecimal fwzxh,
			String zzjgdmz, String sslx, String cid) {
		this.xh = xh;
		this.jgmc = jgmc;
		this.jgfzr = jgfzr;
		this.lxdz = lxdz;
		this.lxdh = lxdh;
		this.jgzt = jgzt;
		this.bayxq = bayxq;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.scbz = scbz;
		this.fwzxh = fwzxh;
		this.zzjgdmz = zzjgdmz;
		this.sslx = sslx;
		this.cid = cid;
	}

	// Property accessors

	public BigDecimal getXh() {
		return this.xh;
	}

	public void setXh(BigDecimal xh) {
		this.xh = xh;
	}

	public String getJgmc() {
		return this.jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getJgfzr() {
		return this.jgfzr;
	}

	public void setJgfzr(String jgfzr) {
		this.jgfzr = jgfzr;
	}

	public String getLxdz() {
		return this.lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getJgzt() {
		return this.jgzt;
	}

	public void setJgzt(String jgzt) {
		this.jgzt = jgzt;
	}

	public Date getBayxq() {
		return this.bayxq;
	}

	public void setBayxq(Date bayxq) {
		this.bayxq = bayxq;
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

	public String getScbz() {
		return this.scbz;
	}

	public void setScbz(String scbz) {
		this.scbz = scbz;
	}

	public BigDecimal getFwzxh() {
		return this.fwzxh;
	}

	public void setFwzxh(BigDecimal fwzxh) {
		this.fwzxh = fwzxh;
	}

	public String getZzjgdmz() {
		return this.zzjgdmz;
	}

	public void setZzjgdmz(String zzjgdmz) {
		this.zzjgdmz = zzjgdmz;
	}

	public String getSslx() {
		return this.sslx;
	}

	public void setSslx(String sslx) {
		this.sslx = sslx;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

}