package com.ycszh.ssh.hbm.sfrz;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzCjxxb entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzCjxxb extends BaseObject {

	// Fields

	private String cid;
	private String xm;
	private String sfzmmc;
	private String sfzmhm;
	private String xb;
	private String gj;
	private String mz;
	private String jg;
	private String txdz;
	private String dzyx;
	private String gddh;
	private String yddh;
	private String wx;
	private String wb;
	private String qq;
	private String rzly;
	private String rzjs;
	private String czxm;
	private String czsfzmhm;
	private String hphm;
	private String hpzl;
	private String dsrSfzCardname1;
	private String dsrSfzCardsex1;
	private String dsrSfzCardno1;
	private String dsrSfzCardaddress1;
	private String dsrSfzYy1;
	private String dsrZzjgZh1;
	private String dsrZzjgFrdb1;
	private String dsrZzjgYyzz1;
	private String dsrZzjgDwmc1;
	private String dsrZzjgDz1;
	private String dsrZzjgNjrq1;
	private String dsrZzjgNjyxq1;
	private String dsrZzjgYy1;
	private String dbrSfzCardname1;
	private String dbrSfzCardsex1;
	private String dbrSfzCardno1;
	private String dbrSfzCardaddress1;
	private String dbrSfzYy1;
	private String dbrZzjgZh1;
	private String dbrZzjgFrdb1;
	private String dbrZzjgYyzz1;
	private String dbrZzjgDwmc1;
	private String dbrZzjgDz1;
	private String dbrZzjgNjrq1;
	private String dbrZzjgNjyxq1;
	private String dbrZzjgYy1;
	private String lrr;
	private String lrrxm;
	private String lrbmdm;
	private String lrbmmc;
	private Date lrsj;
	private String lrip;
	private String lrmac;
	private String shzt;
	private String tbyy;
	private String shrdm;
	private String shrxm;
	private String shbm;
	private String shbmKj;
	private Date shsj;
	private String ship;
	private String shmac;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;
	private String isBind;
	private String czsfzmmc;
	private String djzsdz;
	private String xphz;
	private String jzzdz;
	private String sfzmhmyxq;
	private String jzzyxq;
	private String sfsh;
	private String jzzhm;
	
	//辅助列
	private String finger1;
	
	private String finger2;
	
	private String finger3;
	
	private String finger4;

	// Constructors

	public String getFinger1() {
		return finger1;
	}

	public void setFinger1(String finger1) {
		this.finger1 = finger1;
	}

	public String getFinger2() {
		return finger2;
	}

	public void setFinger2(String finger2) {
		this.finger2 = finger2;
	}

	public String getFinger3() {
		return finger3;
	}

	public void setFinger3(String finger3) {
		this.finger3 = finger3;
	}

	public String getFinger4() {
		return finger4;
	}

	public void setFinger4(String finger4) {
		this.finger4 = finger4;
	}

	/** default constructor */
	public SfrzCjxxb() {
	}

	/** minimal constructor */
	public SfrzCjxxb(String cid) {
		this.cid = cid;
	}

	/** full constructor */
	public SfrzCjxxb(String cid, String xm, String sfzmmc, String sfzmhm,
			String xb, String gj, String mz, String jg, String txdz,
			String dzyx, String gddh, String yddh, String wx, String wb,
			String qq, String rzly, String rzjs, String czxm, String czsfzmhm,
			String hphm, String hpzl, String dsrSfzCardname1,
			String dsrSfzCardsex1, String dsrSfzCardno1,
			String dsrSfzCardaddress1, String dsrSfzYy1, String dsrZzjgZh1,
			String dsrZzjgFrdb1, String dsrZzjgYyzz1, String dsrZzjgDwmc1,
			String dsrZzjgDz1, String dsrZzjgNjrq1, String dsrZzjgNjyxq1,
			String dsrZzjgYy1, String dbrSfzCardname1, String dbrSfzCardsex1,
			String dbrSfzCardno1, String dbrSfzCardaddress1, String dbrSfzYy1,
			String dbrZzjgZh1, String dbrZzjgFrdb1, String dbrZzjgYyzz1,
			String dbrZzjgDwmc1, String dbrZzjgDz1, String dbrZzjgNjrq1,
			String dbrZzjgNjyxq1, String dbrZzjgYy1, String lrr, String lrrxm,
			String lrbmdm, String lrbmmc, Date lrsj, String lrip, String lrmac,
			String shzt, String tbyy, String shrdm, String shrxm, String shbm,
			String shbmKj, Date shsj, String ship, String shmac,
			String synFlag, Date tranDate, String tranFlag, String isBind,
			String czsfzmmc) {
		this.cid = cid;
		this.xm = xm;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.xb = xb;
		this.gj = gj;
		this.mz = mz;
		this.jg = jg;
		this.txdz = txdz;
		this.dzyx = dzyx;
		this.gddh = gddh;
		this.yddh = yddh;
		this.wx = wx;
		this.wb = wb;
		this.qq = qq;
		this.rzly = rzly;
		this.rzjs = rzjs;
		this.czxm = czxm;
		this.czsfzmhm = czsfzmhm;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.dsrSfzCardname1 = dsrSfzCardname1;
		this.dsrSfzCardsex1 = dsrSfzCardsex1;
		this.dsrSfzCardno1 = dsrSfzCardno1;
		this.dsrSfzCardaddress1 = dsrSfzCardaddress1;
		this.dsrSfzYy1 = dsrSfzYy1;
		this.dsrZzjgZh1 = dsrZzjgZh1;
		this.dsrZzjgFrdb1 = dsrZzjgFrdb1;
		this.dsrZzjgYyzz1 = dsrZzjgYyzz1;
		this.dsrZzjgDwmc1 = dsrZzjgDwmc1;
		this.dsrZzjgDz1 = dsrZzjgDz1;
		this.dsrZzjgNjrq1 = dsrZzjgNjrq1;
		this.dsrZzjgNjyxq1 = dsrZzjgNjyxq1;
		this.dsrZzjgYy1 = dsrZzjgYy1;
		this.dbrSfzCardname1 = dbrSfzCardname1;
		this.dbrSfzCardsex1 = dbrSfzCardsex1;
		this.dbrSfzCardno1 = dbrSfzCardno1;
		this.dbrSfzCardaddress1 = dbrSfzCardaddress1;
		this.dbrSfzYy1 = dbrSfzYy1;
		this.dbrZzjgZh1 = dbrZzjgZh1;
		this.dbrZzjgFrdb1 = dbrZzjgFrdb1;
		this.dbrZzjgYyzz1 = dbrZzjgYyzz1;
		this.dbrZzjgDwmc1 = dbrZzjgDwmc1;
		this.dbrZzjgDz1 = dbrZzjgDz1;
		this.dbrZzjgNjrq1 = dbrZzjgNjrq1;
		this.dbrZzjgNjyxq1 = dbrZzjgNjyxq1;
		this.dbrZzjgYy1 = dbrZzjgYy1;
		this.lrr = lrr;
		this.lrrxm = lrrxm;
		this.lrbmdm = lrbmdm;
		this.lrbmmc = lrbmmc;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.lrmac = lrmac;
		this.shzt = shzt;
		this.tbyy = tbyy;
		this.shrdm = shrdm;
		this.shrxm = shrxm;
		this.shbm = shbm;
		this.shbmKj = shbmKj;
		this.shsj = shsj;
		this.ship = ship;
		this.shmac = shmac;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
		this.isBind = isBind;
		this.czsfzmmc = czsfzmmc;
	}

	// Property accessors

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getTxdz() {
		return this.txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
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

	public String getRzly() {
		return this.rzly;
	}

	public void setRzly(String rzly) {
		this.rzly = rzly;
	}

	public String getRzjs() {
		return this.rzjs;
	}

	public void setRzjs(String rzjs) {
		this.rzjs = rzjs;
	}

	public String getCzxm() {
		return this.czxm;
	}

	public void setCzxm(String czxm) {
		this.czxm = czxm;
	}

	public String getCzsfzmhm() {
		return this.czsfzmhm;
	}

	public void setCzsfzmhm(String czsfzmhm) {
		this.czsfzmhm = czsfzmhm;
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

	public String getDsrSfzCardname1() {
		return this.dsrSfzCardname1;
	}

	public void setDsrSfzCardname1(String dsrSfzCardname1) {
		this.dsrSfzCardname1 = dsrSfzCardname1;
	}

	public String getDsrSfzCardsex1() {
		return this.dsrSfzCardsex1;
	}

	public void setDsrSfzCardsex1(String dsrSfzCardsex1) {
		this.dsrSfzCardsex1 = dsrSfzCardsex1;
	}

	public String getDsrSfzCardno1() {
		return this.dsrSfzCardno1;
	}

	public void setDsrSfzCardno1(String dsrSfzCardno1) {
		this.dsrSfzCardno1 = dsrSfzCardno1;
	}

	public String getDsrSfzCardaddress1() {
		return this.dsrSfzCardaddress1;
	}

	public void setDsrSfzCardaddress1(String dsrSfzCardaddress1) {
		this.dsrSfzCardaddress1 = dsrSfzCardaddress1;
	}

	public String getDsrSfzYy1() {
		return this.dsrSfzYy1;
	}

	public void setDsrSfzYy1(String dsrSfzYy1) {
		this.dsrSfzYy1 = dsrSfzYy1;
	}

	public String getDsrZzjgZh1() {
		return this.dsrZzjgZh1;
	}

	public void setDsrZzjgZh1(String dsrZzjgZh1) {
		this.dsrZzjgZh1 = dsrZzjgZh1;
	}

	public String getDsrZzjgFrdb1() {
		return this.dsrZzjgFrdb1;
	}

	public void setDsrZzjgFrdb1(String dsrZzjgFrdb1) {
		this.dsrZzjgFrdb1 = dsrZzjgFrdb1;
	}

	public String getDsrZzjgYyzz1() {
		return this.dsrZzjgYyzz1;
	}

	public void setDsrZzjgYyzz1(String dsrZzjgYyzz1) {
		this.dsrZzjgYyzz1 = dsrZzjgYyzz1;
	}

	public String getDsrZzjgDwmc1() {
		return this.dsrZzjgDwmc1;
	}

	public void setDsrZzjgDwmc1(String dsrZzjgDwmc1) {
		this.dsrZzjgDwmc1 = dsrZzjgDwmc1;
	}

	public String getDsrZzjgDz1() {
		return this.dsrZzjgDz1;
	}

	public void setDsrZzjgDz1(String dsrZzjgDz1) {
		this.dsrZzjgDz1 = dsrZzjgDz1;
	}

	public String getDsrZzjgNjrq1() {
		return this.dsrZzjgNjrq1;
	}

	public void setDsrZzjgNjrq1(String dsrZzjgNjrq1) {
		this.dsrZzjgNjrq1 = dsrZzjgNjrq1;
	}

	public String getDsrZzjgNjyxq1() {
		return this.dsrZzjgNjyxq1;
	}

	public void setDsrZzjgNjyxq1(String dsrZzjgNjyxq1) {
		this.dsrZzjgNjyxq1 = dsrZzjgNjyxq1;
	}

	public String getDsrZzjgYy1() {
		return this.dsrZzjgYy1;
	}

	public void setDsrZzjgYy1(String dsrZzjgYy1) {
		this.dsrZzjgYy1 = dsrZzjgYy1;
	}

	public String getDbrSfzCardname1() {
		return this.dbrSfzCardname1;
	}

	public void setDbrSfzCardname1(String dbrSfzCardname1) {
		this.dbrSfzCardname1 = dbrSfzCardname1;
	}

	public String getDbrSfzCardsex1() {
		return this.dbrSfzCardsex1;
	}

	public void setDbrSfzCardsex1(String dbrSfzCardsex1) {
		this.dbrSfzCardsex1 = dbrSfzCardsex1;
	}

	public String getDbrSfzCardno1() {
		return this.dbrSfzCardno1;
	}

	public void setDbrSfzCardno1(String dbrSfzCardno1) {
		this.dbrSfzCardno1 = dbrSfzCardno1;
	}

	public String getDbrSfzCardaddress1() {
		return this.dbrSfzCardaddress1;
	}

	public void setDbrSfzCardaddress1(String dbrSfzCardaddress1) {
		this.dbrSfzCardaddress1 = dbrSfzCardaddress1;
	}

	public String getDbrSfzYy1() {
		return this.dbrSfzYy1;
	}

	public void setDbrSfzYy1(String dbrSfzYy1) {
		this.dbrSfzYy1 = dbrSfzYy1;
	}

	public String getDbrZzjgZh1() {
		return this.dbrZzjgZh1;
	}

	public void setDbrZzjgZh1(String dbrZzjgZh1) {
		this.dbrZzjgZh1 = dbrZzjgZh1;
	}

	public String getDbrZzjgFrdb1() {
		return this.dbrZzjgFrdb1;
	}

	public void setDbrZzjgFrdb1(String dbrZzjgFrdb1) {
		this.dbrZzjgFrdb1 = dbrZzjgFrdb1;
	}

	public String getDbrZzjgYyzz1() {
		return this.dbrZzjgYyzz1;
	}

	public void setDbrZzjgYyzz1(String dbrZzjgYyzz1) {
		this.dbrZzjgYyzz1 = dbrZzjgYyzz1;
	}

	public String getDbrZzjgDwmc1() {
		return this.dbrZzjgDwmc1;
	}

	public void setDbrZzjgDwmc1(String dbrZzjgDwmc1) {
		this.dbrZzjgDwmc1 = dbrZzjgDwmc1;
	}

	public String getDbrZzjgDz1() {
		return this.dbrZzjgDz1;
	}

	public void setDbrZzjgDz1(String dbrZzjgDz1) {
		this.dbrZzjgDz1 = dbrZzjgDz1;
	}

	public String getDbrZzjgNjrq1() {
		return this.dbrZzjgNjrq1;
	}

	public void setDbrZzjgNjrq1(String dbrZzjgNjrq1) {
		this.dbrZzjgNjrq1 = dbrZzjgNjrq1;
	}

	public String getDbrZzjgNjyxq1() {
		return this.dbrZzjgNjyxq1;
	}

	public void setDbrZzjgNjyxq1(String dbrZzjgNjyxq1) {
		this.dbrZzjgNjyxq1 = dbrZzjgNjyxq1;
	}

	public String getDbrZzjgYy1() {
		return this.dbrZzjgYy1;
	}

	public void setDbrZzjgYy1(String dbrZzjgYy1) {
		this.dbrZzjgYy1 = dbrZzjgYy1;
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

	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getShrdm() {
		return this.shrdm;
	}

	public void setShrdm(String shrdm) {
		this.shrdm = shrdm;
	}

	public String getShrxm() {
		return this.shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
	}

	public String getShbmKj() {
		return this.shbmKj;
	}

	public void setShbmKj(String shbmKj) {
		this.shbmKj = shbmKj;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getShip() {
		return this.ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getShmac() {
		return this.shmac;
	}

	public void setShmac(String shmac) {
		this.shmac = shmac;
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

	public String getIsBind() {
		return this.isBind;
	}

	public void setIsBind(String isBind) {
		this.isBind = isBind;
	}

	public String getCzsfzmmc() {
		return this.czsfzmmc;
	}

	public void setCzsfzmmc(String czsfzmmc) {
		this.czsfzmmc = czsfzmmc;
	}

	public String getDjzsdz() {
		return djzsdz;
	}

	public void setDjzsdz(String djzsdz) {
		this.djzsdz = djzsdz;
	}

	public String getXphz() {
		return xphz;
	}

	public void setXphz(String xphz) {
		this.xphz = xphz;
	}

	public String getJzzdz() {
		return jzzdz;
	}

	public void setJzzdz(String jzzdz) {
		this.jzzdz = jzzdz;
	}

	public String getSfzmhmyxq() {
		return sfzmhmyxq;
	}

	public void setSfzmhmyxq(String sfzmhmyxq) {
		this.sfzmhmyxq = sfzmhmyxq;
	}

	public String getJzzyxq() {
		return jzzyxq;
	}

	public void setJzzyxq(String jzzyxq) {
		this.jzzyxq = jzzyxq;
	}

	public String getSfsh() {
		return sfsh;
	}

	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	public String getJzzhm() {
		return jzzhm;
	}

	public void setJzzhm(String jzzhm) {
		this.jzzhm = jzzhm;
	}

}