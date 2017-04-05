package com.ycszh.ssh.hbm.xyc;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * YcsXycVehLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class YcsXycVehLog extends BaseObject {

	// Fields

	private String xh;
	private String clsbdh;
	private String syr;
	private String hphm;
	private String hpzl;
	private String xyyy;
	private String yylx;
	private String ywyy;
	private String lrrdm;
	private String lrrmc;
	private String lrrbm;
	private Date lrrsj;
	private String lrip;
	private BigDecimal zt;
	private String bz;
	private String lybz;
	private String czr;
	private String czrxm;
	private String czbm;
	private Date czrq;
	private String cznr;
	private String czip;

	// Constructors

	/** default constructor */
	public YcsXycVehLog() {
	}

	/** minimal constructor */
	public YcsXycVehLog(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public YcsXycVehLog(String xh, String clsbdh, String syr, String hphm,
			String hpzl, String xyyy, String yylx, String ywyy, String lrrdm,
			String lrrmc, String lrrbm, Date lrrsj, String lrip, BigDecimal zt,
			String bz, String lybz, String czr, String czrxm, String czbm,
			Date czrq, String cznr, String czip) {
		this.xh = xh;
		this.clsbdh = clsbdh;
		this.syr = syr;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.xyyy = xyyy;
		this.yylx = yylx;
		this.ywyy = ywyy;
		this.lrrdm = lrrdm;
		this.lrrmc = lrrmc;
		this.lrrbm = lrrbm;
		this.lrrsj = lrrsj;
		this.lrip = lrip;
		this.zt = zt;
		this.bz = bz;
		this.lybz = lybz;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.cznr = cznr;
		this.czip = czip;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
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

	public String getXyyy() {
		return this.xyyy;
	}

	public void setXyyy(String xyyy) {
		this.xyyy = xyyy;
	}

	public String getYylx() {
		return this.yylx;
	}

	public void setYylx(String yylx) {
		this.yylx = yylx;
	}

	public String getYwyy() {
		return this.ywyy;
	}

	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}

	public String getLrrdm() {
		return this.lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getLrrmc() {
		return this.lrrmc;
	}

	public void setLrrmc(String lrrmc) {
		this.lrrmc = lrrmc;
	}

	public String getLrrbm() {
		return this.lrrbm;
	}

	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}

	public Date getLrrsj() {
		return this.lrrsj;
	}

	public void setLrrsj(Date lrrsj) {
		this.lrrsj = lrrsj;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public BigDecimal getZt() {
		return this.zt;
	}

	public void setZt(BigDecimal zt) {
		this.zt = zt;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getLybz() {
		return this.lybz;
	}

	public void setLybz(String lybz) {
		this.lybz = lybz;
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

	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

}