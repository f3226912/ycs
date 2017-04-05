package com.ycszh.ssh.hbm.ezxfw;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * EzXxdPrintPhoto entity. @author MyEclipse Persistence Tools
 */

public class EzXxdPrintPhoto extends BaseObject {

	// Fields

	private String tpid;
	private String printXh;
	private String sqlx;
	private String photo;
	private Date lrsj;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public EzXxdPrintPhoto() {
	}

	/** minimal constructor */
	public EzXxdPrintPhoto(String tpid) {
		this.tpid = tpid;
	}

	/** full constructor */
	public EzXxdPrintPhoto(String tpid, String printXh, String sqlx,
			String photo, Date lrsj, String synFlag, String tranFlag,
			Date tranDate) {
		this.tpid = tpid;
		this.printXh = printXh;
		this.sqlx = sqlx;
		this.photo = photo;
		this.lrsj = lrsj;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public String getTpid() {
		return this.tpid;
	}

	public void setTpid(String tpid) {
		this.tpid = tpid;
	}

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

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
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