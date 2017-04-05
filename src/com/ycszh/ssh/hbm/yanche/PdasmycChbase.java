package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * PdasmycChbase entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PdasmycChbase extends BaseObject {

	// Fields

	public String id;
	public String chid;
	public String chmm;
	public String chmc;
	public String chjglx;
	public String zzjgdm;
	public String jgdz;
	public Date cszrq;
	public String fr;
	public String frdh;
	public String zrr;
	public String zrrdh;
	public String pzzrr;
	public String pzzrrdh;
	public String xspp;
	public String ywfw;
	public String ywfwA;
	public String ywfwB;
	public String flag;
	public String yhkzh;
	public String yhkmc;
	public String smycbm;
	public String mac1;
	public String mac2;
	public String mac4;
	public String mac3;
	public String mac5;
	public String mac6;
	public Date tranDate;
	public String synFlag;
	public String tranFlag;

	// Constructors

	/** default constructor */
	public PdasmycChbase() {
	}

	/** minimal constructor */
	public PdasmycChbase(String id) {
		this.id = id;
	}

	/** full constructor */
	public PdasmycChbase(String id, String chid, String chmm, String chmc,
			String chjglx, String zzjgdm, String jgdz, Date cszrq, String fr,
			String frdh, String zrr, String zrrdh, String pzzrr,
			String pzzrrdh, String xspp, String ywfw, String ywfwA,
			String ywfwB, String flag, String yhkzh, String yhkmc, String mac1,
			String mac2, String mac4, String mac3, String mac5, String mac6,
			Date tranDate, String synFlag, String tranFlag) {
		this.id = id;
		this.chid = chid;
		this.chmm = chmm;
		this.chmc = chmc;
		this.chjglx = chjglx;
		this.zzjgdm = zzjgdm;
		this.jgdz = jgdz;
		this.cszrq = cszrq;
		this.fr = fr;
		this.frdh = frdh;
		this.zrr = zrr;
		this.zrrdh = zrrdh;
		this.pzzrr = pzzrr;
		this.pzzrrdh = pzzrrdh;
		this.xspp = xspp;
		this.ywfw = ywfw;
		this.ywfwA = ywfwA;
		this.ywfwB = ywfwB;
		this.flag = flag;
		this.yhkzh = yhkzh;
		this.yhkmc = yhkmc;
		this.mac1 = mac1;
		this.mac2 = mac2;
		this.mac4 = mac4;
		this.mac3 = mac3;
		this.mac5 = mac5;
		this.mac6 = mac6;
		this.tranDate = tranDate;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChid() {
		return this.chid;
	}

	public void setChid(String chid) {
		this.chid = chid;
	}

	public String getChmm() {
		return this.chmm;
	}

	public void setChmm(String chmm) {
		this.chmm = chmm;
	}

	public String getChmc() {
		return this.chmc;
	}

	public void setChmc(String chmc) {
		this.chmc = chmc;
	}

	public String getChjglx() {
		return this.chjglx;
	}

	public void setChjglx(String chjglx) {
		this.chjglx = chjglx;
	}

	public String getZzjgdm() {
		return this.zzjgdm;
	}

	public void setZzjgdm(String zzjgdm) {
		this.zzjgdm = zzjgdm;
	}

	public String getJgdz() {
		return this.jgdz;
	}

	public void setJgdz(String jgdz) {
		this.jgdz = jgdz;
	}

	public Date getCszrq() {
		return this.cszrq;
	}

	public void setCszrq(Date cszrq) {
		this.cszrq = cszrq;
	}

	public String getFr() {
		return this.fr;
	}

	public void setFr(String fr) {
		this.fr = fr;
	}

	public String getFrdh() {
		return this.frdh;
	}

	public void setFrdh(String frdh) {
		this.frdh = frdh;
	}

	public String getZrr() {
		return this.zrr;
	}

	public void setZrr(String zrr) {
		this.zrr = zrr;
	}

	public String getZrrdh() {
		return this.zrrdh;
	}

	public void setZrrdh(String zrrdh) {
		this.zrrdh = zrrdh;
	}

	public String getPzzrr() {
		return this.pzzrr;
	}

	public void setPzzrr(String pzzrr) {
		this.pzzrr = pzzrr;
	}

	public String getPzzrrdh() {
		return this.pzzrrdh;
	}

	public void setPzzrrdh(String pzzrrdh) {
		this.pzzrrdh = pzzrrdh;
	}

	public String getXspp() {
		return this.xspp;
	}

	public void setXspp(String xspp) {
		this.xspp = xspp;
	}

	public String getYwfw() {
		return this.ywfw;
	}

	public void setYwfw(String ywfw) {
		this.ywfw = ywfw;
	}

	public String getYwfwA() {
		return this.ywfwA;
	}

	public void setYwfwA(String ywfwA) {
		this.ywfwA = ywfwA;
	}

	public String getYwfwB() {
		return this.ywfwB;
	}

	public void setYwfwB(String ywfwB) {
		this.ywfwB = ywfwB;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getYhkzh() {
		return this.yhkzh;
	}

	public void setYhkzh(String yhkzh) {
		this.yhkzh = yhkzh;
	}

	public String getYhkmc() {
		return this.yhkmc;
	}

	public void setYhkmc(String yhkmc) {
		this.yhkmc = yhkmc;
	}

	public String getMac1() {
		return this.mac1;
	}

	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}

	public String getMac2() {
		return this.mac2;
	}

	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}

	public String getMac4() {
		return this.mac4;
	}

	public void setMac4(String mac4) {
		this.mac4 = mac4;
	}

	public String getMac3() {
		return this.mac3;
	}

	public void setMac3(String mac3) {
		this.mac3 = mac3;
	}

	public String getMac5() {
		return this.mac5;
	}

	public void setMac5(String mac5) {
		this.mac5 = mac5;
	}

	public String getMac6() {
		return this.mac6;
	}

	public void setMac6(String mac6) {
		this.mac6 = mac6;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
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

	public String getSmycbm() {
		return smycbm;
	}

	public void setSmycbm(String smycbm) {
		this.smycbm = smycbm;
	}

}