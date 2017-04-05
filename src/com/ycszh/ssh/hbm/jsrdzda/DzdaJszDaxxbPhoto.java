package com.ycszh.ssh.hbm.jsrdzda;

import java.util.Date;
import java.util.List;

/**
 * DzdaJszDaxxbPhoto entity. @author MyEclipse Persistence Tools
 */

public class DzdaJszDaxxbPhoto implements java.io.Serializable {

	// Fields

	private String xh;
	private String zllx;
	private String zllxMc;
	private String photo;
	private String zt;
	private String tbyy;
	private String zflx;
	private String flat;
	private String cjxh;
	private String outIn;
	private String picStr;
	private Date picDate;
	private String cjr;
	private String cjxm;
	private String cjbm;
	private String cjbmKj;
	private Date cjsj;
	private String cjip;
	private String cjmac;
	private String xm;
	private String sfzmhm;
	private String remark;
	private List<String> historyId;
//	private String isLock;
//	private String lockr;
//	private String lockxm;
//	private String lockbm;
//	private String lockIp;
//	private String lockBM_kj;
//	private Date lockIptime;


	// Constructors

	/** default constructor */
	public DzdaJszDaxxbPhoto() {
	}

	/** minimal constructor */
	public DzdaJszDaxxbPhoto(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public DzdaJszDaxxbPhoto(String xh, String zllx,String zllxMc, String photo, String zt,
			String tbyy, String zflx, String flat, String cjxh, String outIn,
			String picStr, Date picDate, String cjr, String cjxm, String cjbm,
			String cjbmKj, Date cjsj, String cjip, String cjmac, String xm,
			String sfzmhm,String remark,List<String> historyId,String isLock,String lockr,String lockxm,
			String lockbm,String lockIp,String lockBM_kj,Date lockIptime) {
		this.xh = xh;
		this.zllx = zllx;
		this.photo = photo;
		this.zt = zt;
		this.tbyy = tbyy;
		this.zflx = zflx;
		this.flat = flat;
		this.cjxh = cjxh;
		this.outIn = outIn;
		this.picStr = picStr;
		this.picDate = picDate;
		this.cjr = cjr;
		this.cjxm = cjxm;
		this.cjbm = cjbm;
		this.cjbmKj = cjbmKj;
		this.cjsj = cjsj;
		this.cjip = cjip;
		this.cjmac = cjmac;
		this.xm = xm;
		this.sfzmhm = sfzmhm;
		this.zllxMc=zllxMc;
		this.remark=remark;
		this.historyId =historyId;
//		this.isLock=isLock;
//		this.lockr =lockr;
//		this.lockxm=lockxm;
//		this.lockbm=lockbm;
//		this.lockIp=lockIp;
//		this.lockBM_kj=lockBM_kj;
//		this.lockIptime=lockIptime;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getZllx() {
		return this.zllx;
	}

	public void setZllx(String zllx) {
		this.zllx = zllx;
	}

	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getZflx() {
		return this.zflx;
	}

	public void setZflx(String zflx) {
		this.zflx = zflx;
	}

	public String getFlat() {
		return this.flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getCjxh() {
		return this.cjxh;
	}

	public void setCjxh(String cjxh) {
		this.cjxh = cjxh;
	}

	public String getOutIn() {
		return this.outIn;
	}

	public void setOutIn(String outIn) {
		this.outIn = outIn;
	}

	public String getPicStr() {
		return this.picStr;
	}

	public void setPicStr(String picStr) {
		this.picStr = picStr;
	}

	public Date getPicDate() {
		return this.picDate;
	}

	public void setPicDate(Date picDate) {
		this.picDate = picDate;
	}

	public String getCjr() {
		return this.cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjxm() {
		return this.cjxm;
	}

	public void setCjxm(String cjxm) {
		this.cjxm = cjxm;
	}

	public String getCjbm() {
		return this.cjbm;
	}

	public void setCjbm(String cjbm) {
		this.cjbm = cjbm;
	}

	public String getCjbmKj() {
		return this.cjbmKj;
	}

	public void setCjbmKj(String cjbmKj) {
		this.cjbmKj = cjbmKj;
	}

	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getCjip() {
		return this.cjip;
	}

	public void setCjip(String cjip) {
		this.cjip = cjip;
	}

	public String getCjmac() {
		return this.cjmac;
	}

	public void setCjmac(String cjmac) {
		this.cjmac = cjmac;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getZllxMc() {
		return zllxMc;
	}

	public void setZllxMc(String zllxMc) {
		this.zllxMc = zllxMc;
	}

	public List<String> getHistoryId() {
		return historyId;
	}

	public void setHistoryId(List<String> historyId) {
		this.historyId = historyId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

//	public String getIsLock() {
//		return isLock;
//	}
//
//	public void setIsLock(String isLock) {
//		this.isLock = isLock;
//	}
//
//	public String getLockr() {
//		return lockr;
//	}
//
//	public void setLockr(String lockr) {
//		this.lockr = lockr;
//	}
//
//	public String getLockxm() {
//		return lockxm;
//	}
//
//	public void setLockxm(String lockxm) {
//		this.lockxm = lockxm;
//	}
//
//	public String getLockbm() {
//		return lockbm;
//	}
//
//	public void setLockbm(String lockbm) {
//		this.lockbm = lockbm;
//	}
//
//	public String getLockIp() {
//		return lockIp;
//	}
//
//	public void setLockIp(String lockIp) {
//		this.lockIp = lockIp;
//	}
//
//	public String getLockBM_kj() {
//		return lockBM_kj;
//	}
//
//	public void setLockBM_kj(String lockBMKj) {
//		lockBM_kj = lockBMKj;
//	}
//
//	public Date getLockIptime() {
//		return lockIptime;
//	}
//
//	public void setLockIptime(Date lockIptime) {
//		this.lockIptime = lockIptime;
//	}
	
	
}