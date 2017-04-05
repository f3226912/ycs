package com.ycszh.ssh.hbm.guoshui;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * FpXcLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class FpXcLog extends BaseObject{

	// Fields

	/**
	 * 
	 */
	public String id;
	public String fpdm;
	public String fphm;
	public Date kprq;
	public String mfxm;
	public String mfid;
	public String cpxh;
	public String cjh;
	public String fdjh;
	public String hgzh;
	public String jkzmsh;
	public Long jshj;
	public String xhdw;
	public String nsrsbh;
	public String czdm;
	public String czxm;
	public String czbm;
	public String cznr;
	public String czip;
	public Date czrq;

	// Constructors

	/** default constructor */
	public FpXcLog() {
	}

	/** minimal constructor */
	public FpXcLog(String fpdm, String fphm) {
		this.fpdm = fpdm;
		this.fphm = fphm;
	}

	/** full constructor */
	public FpXcLog(String fpdm, String fphm, Date kprq, String mfxm,
			String mfid, String cpxh, String cjh, String fdjh, String hgzh,
			String jkzmsh, Long jshj, String xhdw, String nsrsbh,
			String czdm, String czxm, String czbm, String cznr, String czip,
			Date czrq) {
		this.fpdm = fpdm;
		this.fphm = fphm;
		this.kprq = kprq;
		this.mfxm = mfxm;
		this.mfid = mfid;
		this.cpxh = cpxh;
		this.cjh = cjh;
		this.fdjh = fdjh;
		this.hgzh = hgzh;
		this.jkzmsh = jkzmsh;
		this.jshj = jshj;
		this.xhdw = xhdw;
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

	public String getCpxh() {
		return this.cpxh;
	}

	public void setCpxh(String cpxh) {
		this.cpxh = cpxh;
	}

	public String getCjh() {
		return this.cjh;
	}

	public void setCjh(String cjh) {
		this.cjh = cjh;
	}

	public String getFdjh() {
		return this.fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getHgzh() {
		return this.hgzh;
	}

	public void setHgzh(String hgzh) {
		this.hgzh = hgzh;
	}

	public String getJkzmsh() {
		return this.jkzmsh;
	}

	public void setJkzmsh(String jkzmsh) {
		this.jkzmsh = jkzmsh;
	}

	public Long getJshj() {
		return this.jshj;
	}

	public void setJshj(Long jshj) {
		this.jshj = jshj;
	}

	public String getXhdw() {
		return this.xhdw;
	}

	public void setXhdw(String xhdw) {
		this.xhdw = xhdw;
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