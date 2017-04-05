package com.ycszh.ssh.hbm.yanche;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * TXbycCode entity. @author MyEclipse Persistence Tools
 */

public class TXbycCode extends BaseObject {

	// Fields

	private String codeId;
	private String dmlb;
	private String lbms;
	private String dmz;
	private String dmsm;
	private String dmsm1;
	private String dmsm2;
	private String dmsm3;
	private String dmsm4;
	private String dmsx;
	private String sxh;
	private String zt;
	private String csbj;
	private String czr;
	private String czrxm;
	private String czbm;
	private String czbmKj;
	private Date czrq;
	private String czip;

	// Constructors

	/** default constructor */
	public TXbycCode() {
	}

	/** minimal constructor */
	public TXbycCode(String codeId, String dmlb, String dmz) {
		this.codeId = codeId;
		this.dmlb = dmlb;
		this.dmz = dmz;
	}

	/** full constructor */
	public TXbycCode(String codeId, String dmlb, String lbms, String dmz,
			String dmsm, String dmsm1, String dmsm2, String dmsm3,
			String dmsm4, String dmsx, String sxh, String zt, String csbj,
			String czr, String czrxm, String czbm, String czbmKj, Date czrq,
			String czip) {
		this.codeId = codeId;
		this.dmlb = dmlb;
		this.lbms = lbms;
		this.dmz = dmz;
		this.dmsm = dmsm;
		this.dmsm1 = dmsm1;
		this.dmsm2 = dmsm2;
		this.dmsm3 = dmsm3;
		this.dmsm4 = dmsm4;
		this.dmsx = dmsx;
		this.sxh = sxh;
		this.zt = zt;
		this.csbj = csbj;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
	}
	
	
	
	public TXbycCode(String dmlb, String lbms, String dmz, String dmsm,
			String dmsm1, String dmsm2, String dmsm3, String dmsm4,
			String dmsx, String sxh, String zt, String czr, String czrxm,
			String czbm, Date czrq, String czip) {
		super();
		this.dmlb = dmlb;
		this.lbms = lbms;
		this.dmz = dmz;
		this.dmsm = dmsm;
		this.dmsm1 = dmsm1;
		this.dmsm2 = dmsm2;
		this.dmsm3 = dmsm3;
		this.dmsm4 = dmsm4;
		this.dmsx = dmsx;
		this.sxh = sxh;
		this.zt = zt;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.czip = czip;
	}

	// Property accessors

	public String getCodeId() {
		return this.codeId;
	}

	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getLbms() {
		return this.lbms;
	}

	public void setLbms(String lbms) {
		this.lbms = lbms;
	}

	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
	}

	public String getDmsm() {
		return this.dmsm;
	}

	public void setDmsm(String dmsm) {
		this.dmsm = dmsm;
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

	public String getDmsm4() {
		return this.dmsm4;
	}

	public void setDmsm4(String dmsm4) {
		this.dmsm4 = dmsm4;
	}

	public String getDmsx() {
		return this.dmsx;
	}

	public void setDmsx(String dmsx) {
		this.dmsx = dmsx;
	}

	public String getSxh() {
		return this.sxh;
	}

	public void setSxh(String sxh) {
		this.sxh = sxh;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCsbj() {
		return this.csbj;
	}

	public void setCsbj(String csbj) {
		this.csbj = csbj;
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

	public String getCzbmKj() {
		return this.czbmKj;
	}

	public void setCzbmKj(String czbmKj) {
		this.czbmKj = czbmKj;
	}

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

}