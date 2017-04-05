package com.ycszh.ssh.hbm.ezxfw;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * EzJdcChanApp entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class EzJdcChanApp extends BaseObject {

	// Fields

	private String wwlsh;
	private String ywlx;
	private String ywyy;
	private String jdcsyr;
	private String sfzmmc;
	private String sfzmhm;
	private String fjszd;
	private String jzzhm;
	private String hphm;
	private String hpzl;
	private String clsbdh;
	private String fdjh;
	private String zsxxdz;
	private String gddh;
	private String yddh;
	private String dzyx;
	private String sjrxm;
	private String sjrdz;
	private String sjrsj;
	private Date wwlrsj;
	private String wwlrip;
	private String lybz;
	private String xszzp;
	private String dzqmzp;
	private String tyblsh;
	private String pch;
	private String zhclhj;
	private String zhclzt;
	private Date zhclsj;
	private String zhclr;
	private String zhclrxm;
	private String zhclrbm;
	private String zhclrbmKj;
	private String zhclrbmmc;
	private String zhclsm;
	private String clxxgc;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String sfzmapid;
	private String sfzmbpid;
	private String jzzapid;
	private String jzzbpid;
	private String loginuser;
	private String yhly;
	private String cspch;
	private String csshrzh;
	private String csshrbm;
	private Integer cspcsl;
	private Date cspcsj;
	private String csshrxm;
	private String bhlx;//翻译的值
	private String hpzl2;
	private String clxh;//车辆型号
	private String cllx;//车辆类型;
	private String gcjk;//国产进口
	
	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getGcjk() {
		return gcjk;
	}

	public void setGcjk(String gcjk) {
		this.gcjk = gcjk;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public EzJdcChanApp(String wwlsh, String ywlx, String ywyy, String jdcsyr,
			String sfzmmc, String sfzmhm, String fjszd, String jzzhm,
			String hphm, String hpzl, String clsbdh, String fdjh,
			String zsxxdz, String gddh, String yddh, String dzyx, String sjrxm,
			String sjrdz, String sjrsj, Date wwlrsj, String wwlrip,
			String lybz, String xszzp, String dzqmzp, String tyblsh,
			String pch, String zhclhj, String zhclzt, Date zhclsj,
			String zhclr, String zhclrxm, String zhclrbm, String zhclrbmKj,
			String zhclrbmmc, String zhclsm, String clxxgc, String synFlag,
			String tranFlag, Date tranDate, String sfzmapid, String sfzmbpid,
			String jzzapid, String jzzbpid, String loginuser, String yhly,
			String cspch, String csshrzh, String csshrbm, Integer cspcsl,
			Date cspcsj, String csshrxm, String bhlx, String hpzl2,
			String clxh, String cllx, String gcjk, String shzt) {
		super();
		this.wwlsh = wwlsh;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.jdcsyr = jdcsyr;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.fjszd = fjszd;
		this.jzzhm = jzzhm;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.clsbdh = clsbdh;
		this.fdjh = fdjh;
		this.zsxxdz = zsxxdz;
		this.gddh = gddh;
		this.yddh = yddh;
		this.dzyx = dzyx;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wwlrsj = wwlrsj;
		this.wwlrip = wwlrip;
		this.lybz = lybz;
		this.xszzp = xszzp;
		this.dzqmzp = dzqmzp;
		this.tyblsh = tyblsh;
		this.pch = pch;
		this.zhclhj = zhclhj;
		this.zhclzt = zhclzt;
		this.zhclsj = zhclsj;
		this.zhclr = zhclr;
		this.zhclrxm = zhclrxm;
		this.zhclrbm = zhclrbm;
		this.zhclrbmKj = zhclrbmKj;
		this.zhclrbmmc = zhclrbmmc;
		this.zhclsm = zhclsm;
		this.clxxgc = clxxgc;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.sfzmapid = sfzmapid;
		this.sfzmbpid = sfzmbpid;
		this.jzzapid = jzzapid;
		this.jzzbpid = jzzbpid;
		this.loginuser = loginuser;
		this.yhly = yhly;
		this.cspch = cspch;
		this.csshrzh = csshrzh;
		this.csshrbm = csshrbm;
		this.cspcsl = cspcsl;
		this.cspcsj = cspcsj;
		this.csshrxm = csshrxm;
		this.bhlx = bhlx;
		this.hpzl2 = hpzl2;
		this.clxh = clxh;
		this.cllx = cllx;
		this.gcjk = gcjk;
		this.shzt = shzt;
	}

	private String shzt;

	public String getBhlx() {
		return bhlx;
	}

	public void setBhlx(String bhlx) {
		this.bhlx = bhlx;
	}

	public String getHpzl2() {
		return hpzl2;
	}

	public void setHpzl2(String hpzl2) {
		this.hpzl2 = hpzl2;
	}

	public EzJdcChanApp(String wwlsh, String ywlx, String ywyy, String jdcsyr,
			String sfzmmc, String sfzmhm, String fjszd, String jzzhm,
			String hphm, String hpzl, String clsbdh, String fdjh,
			String zsxxdz, String gddh, String yddh, String dzyx, String sjrxm,
			String sjrdz, String sjrsj, Date wwlrsj, String wwlrip,
			String lybz, String xszzp, String dzqmzp, String tyblsh,
			String pch, String zhclhj, String zhclzt, Date zhclsj,
			String zhclr, String zhclrxm, String zhclrbm, String zhclrbmKj,
			String zhclrbmmc, String zhclsm, String clxxgc, String synFlag,
			String tranFlag, Date tranDate, String sfzmapid, String sfzmbpid,
			String jzzapid, String jzzbpid, String loginuser, String yhly,
			String cspch, String csshrzh, String csshrbm, Integer cspcsl,
			Date cspcsj, String csshrxm) {
		super();
		this.wwlsh = wwlsh;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.jdcsyr = jdcsyr;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.fjszd = fjszd;
		this.jzzhm = jzzhm;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.clsbdh = clsbdh;
		this.fdjh = fdjh;
		this.zsxxdz = zsxxdz;
		this.gddh = gddh;
		this.yddh = yddh;
		this.dzyx = dzyx;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wwlrsj = wwlrsj;
		this.wwlrip = wwlrip;
		this.lybz = lybz;
		this.xszzp = xszzp;
		this.dzqmzp = dzqmzp;
		this.tyblsh = tyblsh;
		this.pch = pch;
		this.zhclhj = zhclhj;
		this.zhclzt = zhclzt;
		this.zhclsj = zhclsj;
		this.zhclr = zhclr;
		this.zhclrxm = zhclrxm;
		this.zhclrbm = zhclrbm;
		this.zhclrbmKj = zhclrbmKj;
		this.zhclrbmmc = zhclrbmmc;
		this.zhclsm = zhclsm;
		this.clxxgc = clxxgc;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.sfzmapid = sfzmapid;
		this.sfzmbpid = sfzmbpid;
		this.jzzapid = jzzapid;
		this.jzzbpid = jzzbpid;
		this.loginuser = loginuser;
		this.yhly = yhly;
		this.cspch = cspch;
		this.csshrzh = csshrzh;
		this.csshrbm = csshrbm;
		this.cspcsl = cspcsl;
		this.cspcsj = cspcsj;
		this.csshrxm = csshrxm;
	}

	// Constructors

	public String getCspch() {
		return cspch;
	}

	public void setCspch(String cspch) {
		this.cspch = cspch;
	}

	public String getCsshrzh() {
		return csshrzh;
	}

	public void setCsshrzh(String csshrzh) {
		this.csshrzh = csshrzh;
	}

	public String getCsshrbm() {
		return csshrbm;
	}

	public void setCsshrbm(String csshrbm) {
		this.csshrbm = csshrbm;
	}

	public Integer getCspcsl() {
		return cspcsl;
	}

	public void setCspcsl(Integer cspcsl) {
		this.cspcsl = cspcsl;
	}

	public Date getCspcsj() {
		return cspcsj;
	}

	public void setCspcsj(Date cspcsj) {
		this.cspcsj = cspcsj;
	}

	public String getCsshrxm() {
		return csshrxm;
	}

	public void setCsshrxm(String csshrxm) {
		this.csshrxm = csshrxm;
	}

	/** default constructor */
	public EzJdcChanApp() {
	}

	/** minimal constructor */
	public EzJdcChanApp(String wwlsh, String zhclhj, String synFlag) {
		this.wwlsh = wwlsh;
		this.zhclhj = zhclhj;
		this.synFlag = synFlag;
	}

	/** full constructor */
	public EzJdcChanApp(String wwlsh, String ywlx, String ywyy, String jdcsyr,
			String sfzmmc, String sfzmhm, String fjszd, String jzzhm,
			String hphm, String hpzl, String clsbdh, String fdjh,
			String zsxxdz, String gddh, String yddh, String dzyx, String sjrxm,
			String sjrdz, String sjrsj, Date wwlrsj, String wwlrip,
			String lybz, String xszzp, String dzqmzp, String tyblsh,
			String pch, String zhclhj, String zhclzt, Date zhclsj,
			String zhclr, String zhclrxm, String zhclrbm, String zhclrbmKj,
			String zhclrbmmc, String zhclsm, String clxxgc, String synFlag,
			String tranFlag, Date tranDate) {
		this.wwlsh = wwlsh;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.jdcsyr = jdcsyr;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.fjszd = fjszd;
		this.jzzhm = jzzhm;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.clsbdh = clsbdh;
		this.fdjh = fdjh;
		this.zsxxdz = zsxxdz;
		this.gddh = gddh;
		this.yddh = yddh;
		this.dzyx = dzyx;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wwlrsj = wwlrsj;
		this.wwlrip = wwlrip;
		this.lybz = lybz;
		this.xszzp = xszzp;
		this.dzqmzp = dzqmzp;
		this.tyblsh = tyblsh;
		this.pch = pch;
		this.zhclhj = zhclhj;
		this.zhclzt = zhclzt;
		this.zhclsj = zhclsj;
		this.zhclr = zhclr;
		this.zhclrxm = zhclrxm;
		this.zhclrbm = zhclrbm;
		this.zhclrbmKj = zhclrbmKj;
		this.zhclrbmmc = zhclrbmmc;
		this.zhclsm = zhclsm;
		this.clxxgc = clxxgc;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public String getWwlsh() {
		return this.wwlsh;
	}

	public void setWwlsh(String wwlsh) {
		this.wwlsh = wwlsh;
	}

	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getYwyy() {
		return this.ywyy;
	}

	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}

	public String getJdcsyr() {
		return this.jdcsyr;
	}

	public void setJdcsyr(String jdcsyr) {
		this.jdcsyr = jdcsyr;
	}

	public String getSfzmmc() {
		return this.sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getFjszd() {
		return this.fjszd;
	}

	public void setFjszd(String fjszd) {
		this.fjszd = fjszd;
	}

	public String getJzzhm() {
		return this.jzzhm;
	}

	public void setJzzhm(String jzzhm) {
		this.jzzhm = jzzhm;
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

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getFdjh() {
		return this.fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public String getZsxxdz() {
		return this.zsxxdz;
	}

	public void setZsxxdz(String zsxxdz) {
		this.zsxxdz = zsxxdz;
	}

	public String getGddh() {
		return this.gddh;
	}

	public void setGddh(String gddh) {
		this.gddh = gddh;
	}

	public String getYddh() {
		return this.yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getSjrxm() {
		return this.sjrxm;
	}

	public void setSjrxm(String sjrxm) {
		this.sjrxm = sjrxm;
	}

	public String getSjrdz() {
		return this.sjrdz;
	}

	public void setSjrdz(String sjrdz) {
		this.sjrdz = sjrdz;
	}

	public String getSjrsj() {
		return this.sjrsj;
	}

	public void setSjrsj(String sjrsj) {
		this.sjrsj = sjrsj;
	}

	public Date getWwlrsj() {
		return this.wwlrsj;
	}

	public void setWwlrsj(Date wwlrsj) {
		this.wwlrsj = wwlrsj;
	}

	public String getWwlrip() {
		return this.wwlrip;
	}

	public void setWwlrip(String wwlrip) {
		this.wwlrip = wwlrip;
	}

	public String getLybz() {
		return this.lybz;
	}

	public void setLybz(String lybz) {
		this.lybz = lybz;
	}

	public String getXszzp() {
		return this.xszzp;
	}

	public void setXszzp(String xszzp) {
		this.xszzp = xszzp;
	}

	public String getDzqmzp() {
		return this.dzqmzp;
	}

	public void setDzqmzp(String dzqmzp) {
		this.dzqmzp = dzqmzp;
	}

	public String getTyblsh() {
		return this.tyblsh;
	}

	public void setTyblsh(String tyblsh) {
		this.tyblsh = tyblsh;
	}

	public String getPch() {
		return this.pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public String getZhclhj() {
		return this.zhclhj;
	}

	public void setZhclhj(String zhclhj) {
		this.zhclhj = zhclhj;
	}

	public String getZhclzt() {
		return this.zhclzt;
	}

	public void setZhclzt(String zhclzt) {
		this.zhclzt = zhclzt;
	}

	public Date getZhclsj() {
		return this.zhclsj;
	}

	public void setZhclsj(Date zhclsj) {
		this.zhclsj = zhclsj;
	}

	public String getZhclr() {
		return this.zhclr;
	}

	public void setZhclr(String zhclr) {
		this.zhclr = zhclr;
	}

	public String getZhclrxm() {
		return this.zhclrxm;
	}

	public void setZhclrxm(String zhclrxm) {
		this.zhclrxm = zhclrxm;
	}

	public String getZhclrbm() {
		return this.zhclrbm;
	}

	public void setZhclrbm(String zhclrbm) {
		this.zhclrbm = zhclrbm;
	}

	public String getZhclrbmKj() {
		return this.zhclrbmKj;
	}

	public void setZhclrbmKj(String zhclrbmKj) {
		this.zhclrbmKj = zhclrbmKj;
	}

	public String getZhclrbmmc() {
		return this.zhclrbmmc;
	}

	public void setZhclrbmmc(String zhclrbmmc) {
		this.zhclrbmmc = zhclrbmmc;
	}

	public String getZhclsm() {
		return this.zhclsm;
	}

	public void setZhclsm(String zhclsm) {
		this.zhclsm = zhclsm;
	}

	public String getClxxgc() {
		return this.clxxgc;
	}

	public void setClxxgc(String clxxgc) {
		this.clxxgc = clxxgc;
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

	public String getSfzmapid() {
		return sfzmapid;
	}

	public void setSfzmapid(String sfzmapid) {
		this.sfzmapid = sfzmapid;
	}

	public String getSfzmbpid() {
		return sfzmbpid;
	}

	public void setSfzmbpid(String sfzmbpid) {
		this.sfzmbpid = sfzmbpid;
	}

	public String getJzzapid() {
		return jzzapid;
	}

	public void setJzzapid(String jzzapid) {
		this.jzzapid = jzzapid;
	}

	public String getJzzbpid() {
		return jzzbpid;
	}

	public void setJzzbpid(String jzzbpid) {
		this.jzzbpid = jzzbpid;
	}

	public String getLoginuser() {
		return loginuser;
	}

	public void setLoginuser(String loginuser) {
		this.loginuser = loginuser;
	}

	public String getYhly() {
		return yhly;
	}

	public void setYhly(String yhly) {
		this.yhly = yhly;
	}


}