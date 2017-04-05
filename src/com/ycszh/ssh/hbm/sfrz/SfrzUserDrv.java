package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzUserDrv entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzUserDrv extends BaseObject {

	// Fields

	private String did;
	private String jszhm;
	private String xm;
	private String dabh;
	private String uid;
	private Date bindTime;
	private String bindType;
	private String bindIp;
	private String bindmac;
	private String zt;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;

	// Constructors

	/** default constructor */
	public SfrzUserDrv() {
	}

	/** minimal constructor */
	public SfrzUserDrv(String did) {
		this.did = did;
	}

	/** full constructor */
	public SfrzUserDrv(String did, String jszhm, String xm, String dabh,
			String uid, Date bindTime, String bindType, String bindIp,
			String bindmac, String zt, String synFlag, Date tranDate,
			String tranFlag) {
		this.did = did;
		this.jszhm = jszhm;
		this.xm = xm;
		this.dabh = dabh;
		this.uid = uid;
		this.bindTime = bindTime;
		this.bindType = bindType;
		this.bindIp = bindIp;
		this.bindmac = bindmac;
		this.zt = zt;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
	}

	// Property accessors

	public String getDid() {
		return this.did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getJszhm() {
		return this.jszhm;
	}

	public void setJszhm(String jszhm) {
		this.jszhm = jszhm;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Date getBindTime() {
		return this.bindTime;
	}

	public void setBindTime(Date bindTime) {
		this.bindTime = bindTime;
	}

	public String getBindType() {
		return this.bindType;
	}

	public void setBindType(String bindType) {
		this.bindType = bindType;
	}

	public String getBindIp() {
		return this.bindIp;
	}

	public void setBindIp(String bindIp) {
		this.bindIp = bindIp;
	}

	public String getBindmac() {
		return this.bindmac;
	}

	public void setBindmac(String bindmac) {
		this.bindmac = bindmac;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

}