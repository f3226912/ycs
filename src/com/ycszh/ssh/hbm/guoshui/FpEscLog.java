package com.ycszh.ssh.hbm.guoshui;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * FpEscLog entity. @author MyEclipse Persistence Tools
 */

public class FpEscLog extends BaseObject{

	// Fields

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public String id;
	public String fpdm;
	public String fphm;
	public Date kprq;
	public String mfxm;
	public String mfid;
	public String mflx;
	public String mfxm1;
	public String mfid1;
	public String mfl1;
	public String cph;
	public String djzsbh;
	public String cjh;
	public String hjje;
	public String jydw;
	public String escsc;
	public String nsrsbh;
	public String czdm;
	public String czxm;
	public String czbm;
	public String cznr;
	public String czip;
	public Date czrq;

	// Constructors

	/** default constructor */
	public FpEscLog() {
	}

	/** minimal constructor */
	public FpEscLog(String fpdm, String fphm) {
		this.fpdm = fpdm;
		this.fphm = fphm;
	}

	/** full constructor */
	public FpEscLog(String fpdm, String fphm, Date kprq, String mfxm,
			String mfid, String mflx, String mfxm1, String mfid1, String mfl1,
			String cph, String djzsbh, String cjh, String hjje,
			String jydw, String escsc, String nsrsbh, String czdm, String czxm,
			String czbm, String cznr, String czip, Date czrq) {
		this.fpdm = fpdm;
		this.fphm = fphm;
		this.kprq = kprq;
		this.mfxm = mfxm;
		this.mfid = mfid;
		this.mflx = mflx;
		this.mfxm1 = mfxm1;
		this.mfid1 = mfid1;
		this.mfl1 = mfl1;
		this.cph = cph;
		this.djzsbh = djzsbh;
		this.cjh = cjh;
		this.hjje = hjje;
		this.jydw = jydw;
		this.escsc = escsc;
		this.nsrsbh = nsrsbh;
		this.czdm = czdm;
		this.czxm = czxm;
		this.czbm = czbm;
		this.cznr = cznr;
		this.czip = czip;
		this.czrq = czrq;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFpdm() {
		return this.fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFphm() {
		return this.fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public Date getKprq() {
		return this.kprq;
	}

	public void setKprq(Date kprq) {
		this.kprq = kprq;
	}

	public String getMfxm() {
		return this.mfxm;
	}

	public void setMfxm(String mfxm) {
		this.mfxm = mfxm;
	}

	public String getMfid() {
		return this.mfid;
	}

	public void setMfid(String mfid) {
		this.mfid = mfid;
	}

	public String getMflx() {
		return this.mflx;
	}

	public void setMflx(String mflx) {
		this.mflx = mflx;
	}

	public String getMfxm1() {
		return this.mfxm1;
	}

	public void setMfxm1(String mfxm1) {
		this.mfxm1 = mfxm1;
	}

	public String getMfid1() {
		return this.mfid1;
	}

	public void setMfid1(String mfid1) {
		this.mfid1 = mfid1;
	}

	public String getMfl1() {
		return this.mfl1;
	}

	public void setMfl1(String mfl1) {
		this.mfl1 = mfl1;
	}

	public String getCph() {
		return this.cph;
	}

	public void setCph(String cph) {
		this.cph = cph;
	}

	public String getDjzsbh() {
		return this.djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getCjh() {
		return this.cjh;
	}

	public void setCjh(String cjh) {
		this.cjh = cjh;
	}

	public String getHjje() {
		return this.hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getJydw() {
		return this.jydw;
	}

	public void setJydw(String jydw) {
		this.jydw = jydw;
	}

	public String getEscsc() {
		return this.escsc;
	}

	public void setEscsc(String escsc) {
		this.escsc = escsc;
	}

	public String getNsrsbh() {
		return this.nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getCzdm() {
		return this.czdm;
	}

	public void setCzdm(String czdm) {
		this.czdm = czdm;
	}

	public String getCzxm() {
		return this.czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
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

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

}