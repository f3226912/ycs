package com.ycszh.ssh.hbm.ezxfw;

import java.math.BigDecimal;
import java.util.Date;

/**
 * EzXxdPrint entity. @author MyEclipse Persistence Tools
 */

public class EzXxdPrint implements java.io.Serializable {

	// Fields

	private String printXh;
	private String sqlx;
	private String hphm;
	private String hpzl;
	private String sqrxm;
	private String sqrsfzmhm;
	private String lxdh;
	private Date sqsj;
	private String shzt;
	private String tbyy;
	private String sqly;
	private String czrdm;
	private String czrxm;
	private String czrsfzmhm;
	private Date czsj;
	private String czip;
	private String pch;
	private BigDecimal pcsl;
	private Date pcsj;
	private String pczh;
	private String pcxm;
	private String pcbm;
	private String pcbmmc;
	private String pcbmKj;
	private String pcip;
	private String shrzh;
	private String shrxm;
	private String shrbm;
	private String shrbmmc;
	private String shrbmKj;
	private String ship;
	private Date shsj;
	private String shmac;
	private String tpid;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	
	//附加字段
	private String ywlx;
	private String zhclzt;
	
	// Constructors

	public String getZhclzt() {
		return zhclzt;
	}

	public void setZhclzt(String zhclzt) {
		this.zhclzt = zhclzt;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	/** default constructor */
	public EzXxdPrint() {
	}

	/** minimal constructor */
	public EzXxdPrint(String printXh) {
		this.printXh = printXh;
	}

	/** full constructor */
	public EzXxdPrint(String printXh, String sqlx, String hphm, String hpzl,
			String sqrxm, String sqrsfzmhm, String lxdh, Date sqsj,
			String shzt, String tbyy, String sqly, String czrdm, String czrxm,
			String czrsfzmhm, Date czsj, String czip, String pch,
			BigDecimal pcsl, Date pcsj, String pczh, String pcxm, String pcbm,
			String pcbmmc, String pcbmKj, String pcip, String shrzh,
			String shrxm, String shrbm, String shrbmmc, String shrbmKj,
			String ship, Date shsj, String shmac, String tpid, String synFlag,
			String tranFlag, Date tranDate) {
		this.printXh = printXh;
		this.sqlx = sqlx;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.sqrxm = sqrxm;
		this.sqrsfzmhm = sqrsfzmhm;
		this.lxdh = lxdh;
		this.sqsj = sqsj;
		this.shzt = shzt;
		this.tbyy = tbyy;
		this.sqly = sqly;
		this.czrdm = czrdm;
		this.czrxm = czrxm;
		this.czrsfzmhm = czrsfzmhm;
		this.czsj = czsj;
		this.czip = czip;
		this.pch = pch;
		this.pcsl = pcsl;
		this.pcsj = pcsj;
		this.pczh = pczh;
		this.pcxm = pcxm;
		this.pcbm = pcbm;
		this.pcbmmc = pcbmmc;
		this.pcbmKj = pcbmKj;
		this.pcip = pcip;
		this.shrzh = shrzh;
		this.shrxm = shrxm;
		this.shrbm = shrbm;
		this.shrbmmc = shrbmmc;
		this.shrbmKj = shrbmKj;
		this.ship = ship;
		this.shsj = shsj;
		this.shmac = shmac;
		this.tpid = tpid;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public String getPrintXh() {
		return this.printXh;
	}

	public void setPrintXh(String printXh) {
		this.printXh = printXh;
	}

	public String getSqlx() {
		return this.sqlx;
	}

	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
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

	public String getSqrxm() {
		return this.sqrxm;
	}

	public void setSqrxm(String sqrxm) {
		this.sqrxm = sqrxm;
	}

	public String getSqrsfzmhm() {
		return this.sqrsfzmhm;
	}

	public void setSqrsfzmhm(String sqrsfzmhm) {
		this.sqrsfzmhm = sqrsfzmhm;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public Date getSqsj() {
		return this.sqsj;
	}

	public void setSqsj(Date sqsj) {
		this.sqsj = sqsj;
	}

	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getSqly() {
		return this.sqly;
	}

	public void setSqly(String sqly) {
		this.sqly = sqly;
	}

	public String getCzrdm() {
		return this.czrdm;
	}

	public void setCzrdm(String czrdm) {
		this.czrdm = czrdm;
	}

	public String getCzrxm() {
		return this.czrxm;
	}

	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}

	public String getCzrsfzmhm() {
		return this.czrsfzmhm;
	}

	public void setCzrsfzmhm(String czrsfzmhm) {
		this.czrsfzmhm = czrsfzmhm;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public String getPch() {
		return this.pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public BigDecimal getPcsl() {
		return this.pcsl;
	}

	public void setPcsl(BigDecimal pcsl) {
		this.pcsl = pcsl;
	}

	public Date getPcsj() {
		return this.pcsj;
	}

	public void setPcsj(Date pcsj) {
		this.pcsj = pcsj;
	}

	public String getPczh() {
		return this.pczh;
	}

	public void setPczh(String pczh) {
		this.pczh = pczh;
	}

	public String getPcxm() {
		return this.pcxm;
	}

	public void setPcxm(String pcxm) {
		this.pcxm = pcxm;
	}

	public String getPcbm() {
		return this.pcbm;
	}

	public void setPcbm(String pcbm) {
		this.pcbm = pcbm;
	}

	public String getPcbmmc() {
		return this.pcbmmc;
	}

	public void setPcbmmc(String pcbmmc) {
		this.pcbmmc = pcbmmc;
	}

	public String getPcbmKj() {
		return this.pcbmKj;
	}

	public void setPcbmKj(String pcbmKj) {
		this.pcbmKj = pcbmKj;
	}

	public String getPcip() {
		return this.pcip;
	}

	public void setPcip(String pcip) {
		this.pcip = pcip;
	}

	public String getShrzh() {
		return this.shrzh;
	}

	public void setShrzh(String shrzh) {
		this.shrzh = shrzh;
	}

	public String getShrxm() {
		return this.shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShrbm() {
		return this.shrbm;
	}

	public void setShrbm(String shrbm) {
		this.shrbm = shrbm;
	}

	public String getShrbmmc() {
		return this.shrbmmc;
	}

	public void setShrbmmc(String shrbmmc) {
		this.shrbmmc = shrbmmc;
	}

	public String getShrbmKj() {
		return this.shrbmKj;
	}

	public void setShrbmKj(String shrbmKj) {
		this.shrbmKj = shrbmKj;
	}

	public String getShip() {
		return this.ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getShmac() {
		return this.shmac;
	}

	public void setShmac(String shmac) {
		this.shmac = shmac;
	}

	public String getTpid() {
		return this.tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
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