package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * TXbycYhlxxz entity. @author MyEclipse Persistence Tools
 */

public class TXbycYhlxxz extends BaseObject {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String yhlxxzId;
	private String loginId;
	private String sfqx;
	private String cllx;
	private String syxz;
	private String ywlx;
	private String imei;
	private String zt;
	private String czr;
	private String czrxm;
	private String czbm;
	private String czbmKj;
	private Date czrq;
	private String czip;

	// Constructors

	/** default constructor */
	public TXbycYhlxxz() {
	}

	/** minimal constructor */
	public TXbycYhlxxz(String yhlxxzId) {
		this.yhlxxzId = yhlxxzId;
	}

	/** full constructor */
	public TXbycYhlxxz(String yhlxxzId, String loginId, String sfqx,
			String cllx, String syxz, String ywlx, String imei, String zt,
			String czr, String czrxm, String czbm, String czbmKj, Date czrq,
			String czip) {
		this.yhlxxzId = yhlxxzId;
		this.loginId = loginId;
		this.sfqx = sfqx;
		this.cllx = cllx;
		this.syxz = syxz;
		this.ywlx = ywlx;
		this.imei = imei;
		this.zt = zt;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
	}

	// Property accessors

	public String getYhlxxzId() {
		return this.yhlxxzId;
	}

	public void setYhlxxzId(String yhlxxzId) {
		this.yhlxxzId = yhlxxzId;
	}

	public String getLoginId() {
		return this.loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getSfqx() {
		return this.sfqx;
	}

	public void setSfqx(String sfqx) {
		this.sfqx = sfqx;
	}

	public String getCllx() {
		return this.cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getSyxz() {
		return this.syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getImei() {
		return this.imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
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