package com.ycszh.ssh.hbm.cljstj;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

public class HosHospitalUserinfo extends BaseObject {

	// Fields
	private static final long serialVersionUID = 1L;
	private String yyxh;
	private String zzjgdmz;
	private String yhmm;
	private String yymc;
	private String lxdh;
	private String lxdz;
	private String yylxrxm;
	private String yylxrsfzmhm;
	private String yylxrdh;
	private Date stopDate;
	private String lrr;
	private String lrrdm;
	private String lrrbm;
	private String lrrbmKj;
	private String lrip;
	private String lrmac;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String userlevel;
	private String zt;

	// Constructors

	/** default constructor */
	public HosHospitalUserinfo() {
	}

	/** full constructor */
	public HosHospitalUserinfo(String zzjgdmz, String yhmm, String yymc, String lxdh, String lxdz, String yylxrxm, String yylxrsfzmhm, String yylxrdh, Date stopDate, String lrr, String lrrdm,
			String lrrbm, String lrrbmKj, String lrip, String lrmac, String synFlag, String tranFlag, Date tranDate, String userlevel, String zt) {
		this.zzjgdmz = zzjgdmz;
		this.yhmm = yhmm;
		this.yymc = yymc;
		this.lxdh = lxdh;
		this.lxdz = lxdz;
		this.yylxrxm = yylxrxm;
		this.yylxrsfzmhm = yylxrsfzmhm;
		this.yylxrdh = yylxrdh;
		this.stopDate = stopDate;
		this.lrr = lrr;
		this.lrrdm = lrrdm;
		this.lrrbm = lrrbm;
		this.lrrbmKj = lrrbmKj;
		this.lrip = lrip;
		this.lrmac = lrmac;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.userlevel = userlevel;
		this.zt = zt;
	}

	// Property accessors

	public String getYyxh() {
		return this.yyxh;
	}

	public void setYyxh(String yyxh) {
		this.yyxh = yyxh;
	}

	public String getZzjgdmz() {
		return this.zzjgdmz;
	}

	public void setZzjgdmz(String zzjgdmz) {
		this.zzjgdmz = zzjgdmz;
	}

	public String getYhmm() {
		return this.yhmm;
	}

	public void setYhmm(String yhmm) {
		this.yhmm = yhmm;
	}

	public String getYymc() {
		return this.yymc;
	}

	public void setYymc(String yymc) {
		this.yymc = yymc;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLxdz() {
		return this.lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	public String getYylxrxm() {
		return this.yylxrxm;
	}

	public void setYylxrxm(String yylxrxm) {
		this.yylxrxm = yylxrxm;
	}

	public String getYylxrsfzmhm() {
		return this.yylxrsfzmhm;
	}

	public void setYylxrsfzmhm(String yylxrsfzmhm) {
		this.yylxrsfzmhm = yylxrsfzmhm;
	}

	public String getYylxrdh() {
		return this.yylxrdh;
	}

	public void setYylxrdh(String yylxrdh) {
		this.yylxrdh = yylxrdh;
	}

	public Date getStopDate() {
		return this.stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrdm() {
		return this.lrrdm;
	}

	public void setLrrdm(String lrrdm) {
		this.lrrdm = lrrdm;
	}

	public String getLrrbm() {
		return this.lrrbm;
	}

	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}

	public String getLrrbmKj() {
		return this.lrrbmKj;
	}

	public void setLrrbmKj(String lrrbmKj) {
		this.lrrbmKj = lrrbmKj;
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

	public String getUserlevel() {
		return this.userlevel;
	}

	public void setUserlevel(String userlevel) {
		this.userlevel = userlevel;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

}