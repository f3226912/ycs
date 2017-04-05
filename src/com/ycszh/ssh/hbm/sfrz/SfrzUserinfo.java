package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzUserinfo entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzUserinfo extends BaseObject {

	// Fields

	private String UId;
	private String loginUser;
	private String loginPwd;
	private String loginTruename;
	private String loginNickname;
	private String yhly;
	private String yhjs;
	private String userLevel;
	private String cid;
	private String zt;
	private String lrr;
	private String lrrxm;
	private String lrbmdm;
	private String lrbmmc;
	private Date lrsj;
	private String lrip;
	private String lrmac;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;
	private String headphoto;
	private String isBind;
	private String sjhm;
	private String xb;
	private String gj;
	private String mz;
	private String jg;
	private String dzyx;
	private String gddh;
	private String wx;
	private String wb;
	private String qq;
	private String sfzyxq;
	private String djzzdz;
	private String sfsh;
	private String jzzhm;
	private String jzzdz;
	private String jzzyxq;
	private String xphz;
	private String ssdw;
	private String txdz;

	// Constructors

	/** default constructor */
	public SfrzUserinfo() {
	}

	/** minimal constructor */
	public SfrzUserinfo(String UId) {
		this.UId = UId;
	}

	/** full constructor */
	public SfrzUserinfo(String UId, String loginUser, String loginPwd,
			String loginTruename, String loginNickname, String yhly,
			String yhjs, String userLevel, String cid, String zt, String lrr,
			String lrrxm, String lrbmdm, String lrbmmc, Date lrsj, String lrip,
			String lrmac, String synFlag, Date tranDate, String tranFlag,
			String headphoto, String isBind, String sjhm, String xb, String gj,
			String mz, String jg, String dzyx, String gddh, String wx,
			String wb, String qq, String sfzyxq, String djzzdz, String sfsh,
			String jzzhm, String jzzdz, String jzzyxq, String xphz,
			String ssdw, String txdz) {
		this.UId = UId;
		this.loginUser = loginUser;
		this.loginPwd = loginPwd;
		this.loginTruename = loginTruename;
		this.loginNickname = loginNickname;
		this.yhly = yhly;
		this.yhjs = yhjs;
		this.userLevel = userLevel;
		this.cid = cid;
		this.zt = zt;
		this.lrr = lrr;
		this.lrrxm = lrrxm;
		this.lrbmdm = lrbmdm;
		this.lrbmmc = lrbmmc;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.lrmac = lrmac;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
		this.headphoto = headphoto;
		this.isBind = isBind;
		this.sjhm = sjhm;
		this.xb = xb;
		this.gj = gj;
		this.mz = mz;
		this.jg = jg;
		this.dzyx = dzyx;
		this.gddh = gddh;
		this.wx = wx;
		this.wb = wb;
		this.qq = qq;
		this.sfzyxq = sfzyxq;
		this.djzzdz = djzzdz;
		this.sfsh = sfsh;
		this.jzzhm = jzzhm;
		this.jzzdz = jzzdz;
		this.jzzyxq = jzzyxq;
		this.xphz = xphz;
		this.ssdw = ssdw;
		this.txdz = txdz;
	}

	// Property accessors

	public String getUId() {
		return this.UId;
	}

	public void setUId(String UId) {
		this.UId = UId;
	}

	public String getLoginUser() {
		return this.loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getLoginPwd() {
		return this.loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginTruename() {
		return this.loginTruename;
	}

	public void setLoginTruename(String loginTruename) {
		this.loginTruename = loginTruename;
	}

	public String getLoginNickname() {
		return this.loginNickname;
	}

	public void setLoginNickname(String loginNickname) {
		this.loginNickname = loginNickname;
	}

	public String getYhly() {
		return this.yhly;
	}

	public void setYhly(String yhly) {
		this.yhly = yhly;
	}

	public String getYhjs() {
		return this.yhjs;
	}

	public void setYhjs(String yhjs) {
		this.yhjs = yhjs;
	}

	public String getUserLevel() {
		return this.userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrxm() {
		return this.lrrxm;
	}

	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}

	public String getLrbmdm() {
		return this.lrbmdm;
	}

	public void setLrbmdm(String lrbmdm) {
		this.lrbmdm = lrbmdm;
	}

	public String getLrbmmc() {
		return this.lrbmmc;
	}

	public void setLrbmmc(String lrbmmc) {
		this.lrbmmc = lrbmmc;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public String getLrmac() {
		return this.lrmac;
	}

	public void setLrmac(String lrmac) {
		this.lrmac = lrmac;
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

	public String getHeadphoto() {
		return this.headphoto;
	}

	public void setHeadphoto(String headphoto) {
		this.headphoto = headphoto;
	}

	public String getIsBind() {
		return this.isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public String getSjhm() {
		return this.sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getMz() {
		return this.mz;
	}

	public void setMz(String mz) {
		this.mz = mz;
	}

	public String getJg() {
		return this.jg;
	}

	public void setJg(String jg) {
		this.jg = jg;
	}

	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getGddh() {
		return this.gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}

	public String getWx() {
		return this.wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}

	public String getWb() {
		return this.wb;
	}

	public void setWb(String wb) {
		this.wb = wb;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getSfzyxq() {
		return this.sfzyxq;
	}

	public void setSfzyxq(String sfzyxq) {
		this.sfzyxq = sfzyxq;
	}

	public String getDjzzdz() {
		return this.djzzdz;
	}

	public void setDjzzdz(String djzzdz) {
		this.djzzdz = djzzdz;
	}

	public String getSfsh() {
		return this.sfsh;
	}

	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	public String getJzzhm() {
		return this.jzzhm;
	}

	public void setJzzhm(String jzzhm) {
		this.jzzhm = jzzhm;
	}

	public String getJzzdz() {
		return this.jzzdz;
	}

	public void setJzzdz(String jzzdz) {
		this.jzzdz = jzzdz;
	}

	public String getJzzyxq() {
		return this.jzzyxq;
	}

	public void setJzzyxq(String jzzyxq) {
		this.jzzyxq = jzzyxq;
	}

	public String getXphz() {
		return this.xphz;
	}

	public void setXphz(String xphz) {
		this.xphz = xphz;
	}

	public String getSsdw() {
		return this.ssdw;
	}

	public void setSsdw(String ssdw) {
		this.ssdw = ssdw;
	}

	public String getTxdz() {
		return this.txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
}