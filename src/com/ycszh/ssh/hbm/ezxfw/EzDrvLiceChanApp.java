package com.ycszh.ssh.hbm.ezxfw;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * EzDrvLiceChanApp entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class EzDrvLiceChanApp extends BaseObject {

	// Fields

	private String wwlsh;
	private String hblx;
	private String hbyy;
	private String sfzmmc;
	private String sfzmhm;
	private String jszhm;
	private String xm;
	private String xb;
	private Date csrq;
	private String gj;
	private String djzsdzdm;
	private String djzsdz;
	private String lxzsdzdm;
	private String lxzsdz;
	private String yzbm;
	private String dzyx;
	private String gddh;
	private String yddh;
	private String sqfs;
	private String dlrxm;
	private String dlrsfzmmc;
	private String dlrsfzmhm;
	private String dlrlxdz;
	private String dlrlxdh;
	private String ydabh;
	private String zjcx;
	private String xphzbh;
	private String sftjhg;
	private String sjrxm;
	private String sjrdz;
	private String sjrsj;
	private Date wslrsj;
	private String ip;
	private Short sg;
	private Double zsl;
	private Double ysl;
	private String bsl;
	private String tl;
	private String sz;
	private String zxz;
	private String yxz;
	private String qgjb;
	private Date tjrq;
	private String tjyymc;
	private String hjszd;
	private String zzzhm;
	private String lybz;
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
	private String sfzmc;
	private String shzt;
	
	private String qcdcgs;
	private Date yqzzrq;
	private String yqyy;
	
	public String getQcdcgs() {
		return qcdcgs;
	}

	public void setQcdcgs(String qcdcgs) {
		this.qcdcgs = qcdcgs;
	}

	public Date getYqzzrq() {
		return yqzzrq;
	}

	public void setYqzzrq(Date yqzzrq) {
		this.yqzzrq = yqzzrq;
	}

	public String getYqyy() {
		return yqyy;
	}

	public void setYqyy(String yqyy) {
		this.yqyy = yqyy;
	}

	public String getShzt() {
		return shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getBhlx() {
		return bhlx;
	}

	public void setBhlx(String bhlx) {
		this.bhlx = bhlx;
	}

	public String getSfzmc() {
		return sfzmc;
	}

	public void setSfzmc(String sfzmc) {
		this.sfzmc = sfzmc;
	}

	public String getCspch() {
		return cspch;
	}

	public void setCspch(String cspch) {
		this.cspch = cspch;
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


	// Constructors

	public String getCsshrzh() {
		return csshrzh;
	}

	public void setCsshrzh(String csshrzh) {
		this.csshrzh = csshrzh;
	}


	/** default constructor */
	public EzDrvLiceChanApp() {
	}

	public EzDrvLiceChanApp(String pch,Date zhclsj,String zhclrbm) {
		this.pch = pch;
		this.zhclsj = zhclsj;
		this.zhclrbm = zhclrbm;
	}
	
	/** minimal constructor */
	public EzDrvLiceChanApp(String wwlsh, String hblx, String hbyy,
			String sfzmmc, String sfzmhm, String jszhm, String xm, String xb,
			Date csrq, String gj, String djzsdz, String lxzsdz, String sqfs,
			String xphzbh, String sftjhg, String sjrxm, String sjrdz,
			String sjrsj, Date wslrsj, String ip, String zhclhj, String synFlag) {
		this.wwlsh = wwlsh;
		this.hblx = hblx;
		this.hbyy = hbyy;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.jszhm = jszhm;
		this.xm = xm;
		this.xb = xb;
		this.csrq = csrq;
		this.gj = gj;
		this.djzsdz = djzsdz;
		this.lxzsdz = lxzsdz;
		this.sqfs = sqfs;
		this.xphzbh = xphzbh;
		this.sftjhg = sftjhg;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wslrsj = wslrsj;
		this.ip = ip;
		this.zhclhj = zhclhj;
		this.synFlag = synFlag;
	}

	public EzDrvLiceChanApp(String wwlsh, String hblx, String hbyy,
			String sfzmmc, String sfzmhm, String jszhm, String xm, String xb,
			Date csrq, String gj, String djzsdzdm, String djzsdz,
			String lxzsdzdm, String lxzsdz, String yzbm, String dzyx,
			String gddh, String yddh, String sqfs, String dlrxm,
			String dlrsfzmmc, String dlrsfzmhm, String dlrlxdz, String dlrlxdh,
			String ydabh, String zjcx, String xphzbh, String sftjhg,
			String sjrxm, String sjrdz, String sjrsj, Date wslrsj, String ip,
			Short sg, Double zsl, Double ysl, String bsl, String tl, String sz,
			String zxz, String yxz, String qgjb, Date tjrq, String tjyymc,
			String hjszd, String zzzhm, String lybz, String tyblsh, String pch,
			String zhclhj, String zhclzt, Date zhclsj, String zhclr,
			String zhclrxm, String zhclrbm, String zhclrbmKj, String zhclrbmmc,
			String zhclsm, String clxxgc, String synFlag, String tranFlag,
			Date tranDate, String sfzmapid, String sfzmbpid, String jzzapid,
			String jzzbpid, String loginuser, String yhly, String cspch,
			String csshrzh, String csshrbm, Integer cspcsl, Date cspcsj,
			String csshrxm) {
		super();
		this.wwlsh = wwlsh;
		this.hblx = hblx;
		this.hbyy = hbyy;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.jszhm = jszhm;
		this.xm = xm;
		this.xb = xb;
		this.csrq = csrq;
		this.gj = gj;
		this.djzsdzdm = djzsdzdm;
		this.djzsdz = djzsdz;
		this.lxzsdzdm = lxzsdzdm;
		this.lxzsdz = lxzsdz;
		this.yzbm = yzbm;
		this.dzyx = dzyx;
		this.gddh = gddh;
		this.yddh = yddh;
		this.sqfs = sqfs;
		this.dlrxm = dlrxm;
		this.dlrsfzmmc = dlrsfzmmc;
		this.dlrsfzmhm = dlrsfzmhm;
		this.dlrlxdz = dlrlxdz;
		this.dlrlxdh = dlrlxdh;
		this.ydabh = ydabh;
		this.zjcx = zjcx;
		this.xphzbh = xphzbh;
		this.sftjhg = sftjhg;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wslrsj = wslrsj;
		this.ip = ip;
		this.sg = sg;
		this.zsl = zsl;
		this.ysl = ysl;
		this.bsl = bsl;
		this.tl = tl;
		this.sz = sz;
		this.zxz = zxz;
		this.yxz = yxz;
		this.qgjb = qgjb;
		this.tjrq = tjrq;
		this.tjyymc = tjyymc;
		this.hjszd = hjszd;
		this.zzzhm = zzzhm;
		this.lybz = lybz;
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

	/** full constructor */
	public EzDrvLiceChanApp(String wwlsh, String hblx, String hbyy,
			String sfzmmc, String sfzmhm, String jszhm, String xm, String xb,
			Date csrq, String gj, String djzsdzdm, String djzsdz,
			String lxzsdzdm, String lxzsdz, String yzbm, String dzyx,
			String gddh, String yddh, String sqfs, String dlrxm,
			String dlrsfzmmc, String dlrsfzmhm, String dlrlxdz, String dlrlxdh,
			String ydabh, String zjcx, String xphzbh, String sftjhg,
			String sjrxm, String sjrdz, String sjrsj, Date wslrsj, String ip,
			Short sg, Double zsl, Double ysl, String bsl, String tl, String sz,
			String zxz, String yxz, String qgjb, Date tjrq, String tjyymc,
			String hjszd, String zzzhm, String lybz, String tyblsh, String pch,
			String zhclhj, String zhclzt, Date zhclsj, String zhclr,
			String zhclrxm, String zhclrbm, String zhclrbmKj, String zhclrbmmc,
			String zhclsm, String clxxgc, String synFlag, String tranFlag,
			Date tranDate) {
		this.wwlsh = wwlsh;
		this.hblx = hblx;
		this.hbyy = hbyy;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.jszhm = jszhm;
		this.xm = xm;
		this.xb = xb;
		this.csrq = csrq;
		this.gj = gj;
		this.djzsdzdm = djzsdzdm;
		this.djzsdz = djzsdz;
		this.lxzsdzdm = lxzsdzdm;
		this.lxzsdz = lxzsdz;
		this.yzbm = yzbm;
		this.dzyx = dzyx;
		this.gddh = gddh;
		this.yddh = yddh;
		this.sqfs = sqfs;
		this.dlrxm = dlrxm;
		this.dlrsfzmmc = dlrsfzmmc;
		this.dlrsfzmhm = dlrsfzmhm;
		this.dlrlxdz = dlrlxdz;
		this.dlrlxdh = dlrlxdh;
		this.ydabh = ydabh;
		this.zjcx = zjcx;
		this.xphzbh = xphzbh;
		this.sftjhg = sftjhg;
		this.sjrxm = sjrxm;
		this.sjrdz = sjrdz;
		this.sjrsj = sjrsj;
		this.wslrsj = wslrsj;
		this.ip = ip;
		this.sg = sg;
		this.zsl = zsl;
		this.ysl = ysl;
		this.bsl = bsl;
		this.tl = tl;
		this.sz = sz;
		this.zxz = zxz;
		this.yxz = yxz;
		this.qgjb = qgjb;
		this.tjrq = tjrq;
		this.tjyymc = tjyymc;
		this.hjszd = hjszd;
		this.zzzhm = zzzhm;
		this.lybz = lybz;
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

	public String getHblx() {
		return this.hblx;
	}

	public void setHblx(String hblx) {
		this.hblx = hblx;
	}

	public String getHbyy() {
		return this.hbyy;
	}

	public void setHbyy(String hbyy) {
		this.hbyy = hbyy;
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

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public Date getCsrq() {
		return this.csrq;
	}

	public void setCsrq(Date csrq) {
		this.csrq = csrq;
	}

	public String getGj() {
		return this.gj;
	}

	public void setGj(String gj) {
		this.gj = gj;
	}

	public String getDjzsdzdm() {
		return this.djzsdzdm;
	}

	public void setDjzsdzdm(String djzsdzdm) {
		this.djzsdzdm = djzsdzdm;
	}

	public String getDjzsdz() {
		return this.djzsdz;
	}

	public void setDjzsdz(String djzsdz) {
		this.djzsdz = djzsdz;
	}

	public String getLxzsdzdm() {
		return this.lxzsdzdm;
	}

	public void setLxzsdzdm(String lxzsdzdm) {
		this.lxzsdzdm = lxzsdzdm;
	}

	public String getLxzsdz() {
		return this.lxzsdz;
	}

	public void setLxzsdz(String lxzsdz) {
		this.lxzsdz = lxzsdz;
	}

	public String getYzbm() {
		return this.yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
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

	public String getYddh() {
		return this.yddh;
	}

	public void setYddh(String yddh) {
		this.yddh = yddh;
	}

	public String getSqfs() {
		return this.sqfs;
	}

	public void setSqfs(String sqfs) {
		this.sqfs = sqfs;
	}

	public String getDlrxm() {
		return this.dlrxm;
	}

	public void setDlrxm(String dlrxm) {
		this.dlrxm = dlrxm;
	}

	public String getDlrsfzmmc() {
		return this.dlrsfzmmc;
	}

	public void setDlrsfzmmc(String dlrsfzmmc) {
		this.dlrsfzmmc = dlrsfzmmc;
	}

	public String getDlrsfzmhm() {
		return this.dlrsfzmhm;
	}

	public void setDlrsfzmhm(String dlrsfzmhm) {
		this.dlrsfzmhm = dlrsfzmhm;
	}

	public String getDlrlxdz() {
		return this.dlrlxdz;
	}

	public void setDlrlxdz(String dlrlxdz) {
		this.dlrlxdz = dlrlxdz;
	}

	public String getDlrlxdh() {
		return this.dlrlxdh;
	}

	public void setDlrlxdh(String dlrlxdh) {
		this.dlrlxdh = dlrlxdh;
	}

	public String getYdabh() {
		return this.ydabh;
	}

	public void setYdabh(String ydabh) {
		this.ydabh = ydabh;
	}

	public String getZjcx() {
		return this.zjcx;
	}

	public void setZjcx(String zjcx) {
		this.zjcx = zjcx;
	}

	public String getXphzbh() {
		return this.xphzbh;
	}

	public void setXphzbh(String xphzbh) {
		this.xphzbh = xphzbh;
	}

	public String getSftjhg() {
		return this.sftjhg;
	}

	public void setSftjhg(String sftjhg) {
		this.sftjhg = sftjhg;
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

	public Date getWslrsj() {
		return this.wslrsj;
	}

	public void setWslrsj(Date wslrsj) {
		this.wslrsj = wslrsj;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Short getSg() {
		return this.sg;
	}

	public void setSg(Short sg) {
		this.sg = sg;
	}

	public Double getZsl() {
		return this.zsl;
	}

	public void setZsl(Double zsl) {
		this.zsl = zsl;
	}

	public Double getYsl() {
		return this.ysl;
	}

	public void setYsl(Double ysl) {
		this.ysl = ysl;
	}

	public String getBsl() {
		return this.bsl;
	}

	public void setBsl(String bsl) {
		this.bsl = bsl;
	}

	public String getTl() {
		return this.tl;
	}

	public void setTl(String tl) {
		this.tl = tl;
	}

	public String getSz() {
		return this.sz;
	}

	public void setSz(String sz) {
		this.sz = sz;
	}

	public String getZxz() {
		return this.zxz;
	}

	public void setZxz(String zxz) {
		this.zxz = zxz;
	}

	public String getYxz() {
		return this.yxz;
	}

	public void setYxz(String yxz) {
		this.yxz = yxz;
	}

	public String getQgjb() {
		return this.qgjb;
	}

	public void setQgjb(String qgjb) {
		this.qgjb = qgjb;
	}

	public Date getTjrq() {
		return this.tjrq;
	}

	public void setTjrq(Date tjrq) {
		this.tjrq = tjrq;
	}

	public String getTjyymc() {
		return this.tjyymc;
	}

	public void setTjyymc(String tjyymc) {
		this.tjyymc = tjyymc;
	}

	public String getHjszd() {
		return this.hjszd;
	}

	public void setHjszd(String hjszd) {
		this.hjszd = hjszd;
	}

	public String getZzzhm() {
		return this.zzzhm;
	}

	public void setZzzhm(String zzzhm) {
		this.zzzhm = zzzhm;
	}

	public String getLybz() {
		return this.lybz;
	}

	public void setLybz(String lybz) {
		this.lybz = lybz;
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