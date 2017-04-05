package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzUserVeh entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzUserVeh extends BaseObject {

	// Fields

	private String vid;
	private String hphm;
	private String hpzl;
	private String sfbr;
	private String cjh4;
	private String czxm;
	private String czfzmhm;
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
	public SfrzUserVeh() {
	}

	/** minimal constructor */
	public SfrzUserVeh(String vid) {
		this.vid = vid;
	}

	/** full constructor */
	public SfrzUserVeh(String vid, String hphm, String hpzl, String sfbr,
			String cjh4, String czxm, String czfzmhm, String uid,
			Date bindTime, String bindType, String bindIp, String bindmac,
			String zt, String synFlag, Date tranDate, String tranFlag) {
		this.vid = vid;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.sfbr = sfbr;
		this.cjh4 = cjh4;
		this.czxm = czxm;
		this.czfzmhm = czfzmhm;
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

	public String getVid() {
		return this.vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
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

	public String getSfbr() {
		return this.sfbr;
	}

	public void setSfbr(String sfbr) {
		this.sfbr = sfbr;
	}

	public String getCjh4() {
		return this.cjh4;
	}

	public void setCjh4(String cjh4) {
		this.cjh4 = cjh4;
	}

	public String getCzxm() {
		return this.czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getCzfzmhm() {
		return this.czfzmhm;
	}

	public void setCzfzmhm(String czfzmhm) {
		this.czfzmhm = czfzmhm;
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