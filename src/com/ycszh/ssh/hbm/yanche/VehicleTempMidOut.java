package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * VehicleTempMidOut entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class VehicleTempMidOut extends BaseObject {

	// Fields

	public String lsh;
	public String xh;
	public String hpzl;
	public String hphm;
	public String clpp1;
	public String clxh;
	public String clpp2;
	public String gcjk;
	public String zzg;
	public String zzcmc;
	public String clsbdh;
	public String fdjh;
	public String cllx;
	public String csys;
	public String syxz;
	public String sfzmhm;
	public String syq;
	public Date ccdjrq;
	public Date djrq;
	public Date yxqz;
	public Date qzbfqz;
	public String fzjg;
	public String glbm;
	public Date fprq;
	public Date fzrq;
	public Date fdjrq;
	public Date fhgzrq;
	public Date bxzzrq;
	public String djzsbh;
	public String dabh;
	public String xzqh;
	public String dybj;
	public String jbr;
	public String clly;
	public String fdjxh;
	public String rlzl;
	public Integer pl;
	public Double gl;
	public String zxxs;
	public Integer cwkc;
	public Integer cwkk;
	public Integer cwkg;
	public Integer hxnbcd;
	public Integer hxnbkd;
	public Integer hxnbgd;
	public Integer gbthps;
	public Integer zs;
	public Integer zj;
	public Integer qlj;
	public Integer hlj;
	public String ltgg;
	public Integer lts;
	public Integer zzl;
	public Integer zbzl;
	public Integer hdzzl;
	public Integer hdzk;
	public Integer zqyzl;
	public Integer qpzk;
	public Integer hpzk;
	public String bh;
	public String dpid;
	public String hbdbqk;
	public Date ccrq;
	public String hdfs;
	public String llpz1;
	public String pzbh1;
	public String llpz2;
	public String pzbh2;
	public String xsdw;
	public Integer xsjg;
	public Date xsrq;
	public String jkpz;
	public String jkpzhm;
	public String hgzbh;
	public String nszm;
	public String nszmbh;
	public String xgzl;
	public String qmbh;
	public String hmbh;
	public String bz;
	public String cjdw;
	public String jyjg;
	public String bxpzh;
	public Long bxje;
	public Date sxrq;
	public Date zzrq;
	public String bxgs;
	public String yhpzl;
	public String yhphm;
	public String ysyxz;
	public String zcd;
	public String jcjgzms;
	public String zylx;
	public String bgnr;
	public String hdid;
	public Integer bzcs;
	public Integer bpcs;
	public Integer bdjcs;
	public Integer zdjzshs;
	public Date qzrq;
	public String sfzmmc;
	public String syr;
	public String zsxzqh;
	public String zsxxdz;
	public String yzbm1;
	public String lxdh;
	public String zzz;
	public String zzxzqh;
	public String zzxxdz;
	public String yzbm2;
	public String ywlx;
	public String ywyy;
	public String jyw;
	public String cyry;
	public String dphgzbh;
	public String dzyx;
	public String xszbh;
	public String sjhm;
	public String jyhgbzbh;
	public String localIshandle;
	public String localOperat;
	public Date localOpertime;
	public String localName;
	public String netXm;
	public String netYjdz;
	public String netYzbm;
	public String netLxdh;
	public String netDzxx;
	public String netJbrxm;
	public String netJbrlxdh;
	public String chid;
	public Date chrq;
	public String sfxzyc;
	public String outIn;
	public String md5;
	public String synFlag;
	public String tranFlag;
	public Date tranDate;
	public String netFph;
	public String postZj;
	public String postTp;
	public String postSjr;
	public String postLxdh;
	public String postYjdz;
	public String postYzbm;
	public String ctccs;
	public String tempBz1;
	public String tempBz2;
	public String tempBz3;
	public String tempBz4;
	public String tempBz5;
	
	public String tybLsh;//out表本没有这个字段，为了实体映射故加此字段
	
	public String fpdm;
    public String fphm;
    public String gzszmbh;
    public String yzGsInfo;
    
    public String netSfzmhm;
    
    public String refeshFlag;
	public String refeshMsg;
	public Date refeshDate;
	public String dbyzh;
	public String tyzxh;
	
	// Constructors

	/** default constructor */
	public VehicleTempMidOut() {
	}

	/** minimal constructor */
	public VehicleTempMidOut(String lsh, String clsbdh) {
		this.lsh = lsh;
		this.clsbdh = clsbdh;
	}

	/** full constructor */
	public VehicleTempMidOut(String lsh, String xh, String hpzl, String hphm,
			String clpp1, String clxh, String clpp2, String gcjk, String zzg,
			String zzcmc, String clsbdh, String fdjh, String cllx, String csys,
			String syxz, String sfzmhm, String syq, Date ccdjrq, Date djrq,
			Date yxqz, Date qzbfqz, String fzjg, String glbm, Date fprq,
			Date fzrq, Date fdjrq, Date fhgzrq, Date bxzzrq, String djzsbh,
			String dabh, String xzqh, String dybj, String jbr, String clly,
			String fdjxh, String rlzl, Integer pl, Double gl, String zxxs,
			Integer cwkc, Integer cwkk, Integer cwkg, Integer hxnbcd, Integer hxnbkd,
			Integer hxnbgd, Integer gbthps, Integer zs, Integer zj, Integer qlj,
			Integer hlj, String ltgg, Integer lts, Integer zzl, Integer zbzl,
			Integer hdzzl, Integer hdzk, Integer zqyzl, Integer qpzk, Integer hpzk,
			String bh, String dpid, String hbdbqk, Date ccrq, String hdfs,
			String llpz1, String pzbh1, String llpz2, String pzbh2,
			String xsdw, Integer xsjg, Date xsrq, String jkpz, String jkpzhm,
			String hgzbh, String nszm, String nszmbh, String xgzl, String qmbh,
			String hmbh, String bz, String cjdw, String jyjg, String bxpzh,
			Long bxje, Date sxrq, Date zzrq, String bxgs, String yhpzl,
			String yhphm, String ysyxz, String zcd, String jcjgzms,
			String zylx, String bgnr, String hdid, Integer bzcs, Integer bpcs,
			Integer bdjcs, Integer zdjzshs, Date qzrq, String sfzmmc, String syr,
			String zsxzqh, String zsxxdz, String yzbm1, String lxdh,
			String zzz, String zzxzqh, String zzxxdz, String yzbm2,
			String ywlx, String ywyy, String jyw, String cyry, String dphgzbh,
			String dzyx, String xszbh, String sjhm, String jyhgbzbh,
			String localIshandle, String localOperat, Date localOpertime,
			String localName, String netXm, String netYjdz, String netYzbm,
			String netLxdh, String netDzxx, String netJbrxm, String netJbrlxdh,
			String chid, Date chrq, String sfxzyc, String outIn, String md5,
			String synFlag, String tranFlag, Date tranDate, String refeshFlag,
			String refeshMsg, Date refeshDate, String netFph, String postZj,
			String postTp, String postSjr, String postLxdh, String postYjdz,
			String postYzbm, String ctccs, String tempBz1, String tempBz2,
			String tempBz3, String tempBz4, String tempBz5, String dbyzh,
			String tyzxh) {
		this.lsh = lsh;
		this.xh = xh;
		this.hpzl = hpzl;
		this.hphm = hphm;
		this.clpp1 = clpp1;
		this.clxh = clxh;
		this.clpp2 = clpp2;
		this.gcjk = gcjk;
		this.zzg = zzg;
		this.zzcmc = zzcmc;
		this.clsbdh = clsbdh;
		this.fdjh = fdjh;
		this.cllx = cllx;
		this.csys = csys;
		this.syxz = syxz;
		this.sfzmhm = sfzmhm;
		this.syq = syq;
		this.ccdjrq = ccdjrq;
		this.djrq = djrq;
		this.yxqz = yxqz;
		this.qzbfqz = qzbfqz;
		this.fzjg = fzjg;
		this.glbm = glbm;
		this.fprq = fprq;
		this.fzrq = fzrq;
		this.fdjrq = fdjrq;
		this.fhgzrq = fhgzrq;
		this.bxzzrq = bxzzrq;
		this.djzsbh = djzsbh;
		this.dabh = dabh;
		this.xzqh = xzqh;
		this.dybj = dybj;
		this.jbr = jbr;
		this.clly = clly;
		this.fdjxh = fdjxh;
		this.rlzl = rlzl;
		this.pl = pl;
		this.gl = gl;
		this.zxxs = zxxs;
		this.cwkc = cwkc;
		this.cwkk = cwkk;
		this.cwkg = cwkg;
		this.hxnbcd = hxnbcd;
		this.hxnbkd = hxnbkd;
		this.hxnbgd = hxnbgd;
		this.gbthps = gbthps;
		this.zs = zs;
		this.zj = zj;
		this.qlj = qlj;
		this.hlj = hlj;
		this.ltgg = ltgg;
		this.lts = lts;
		this.zzl = zzl;
		this.zbzl = zbzl;
		this.hdzzl = hdzzl;
		this.hdzk = hdzk;
		this.zqyzl = zqyzl;
		this.qpzk = qpzk;
		this.hpzk = hpzk;
		this.bh = bh;
		this.dpid = dpid;
		this.hbdbqk = hbdbqk;
		this.ccrq = ccrq;
		this.hdfs = hdfs;
		this.llpz1 = llpz1;
		this.pzbh1 = pzbh1;
		this.llpz2 = llpz2;
		this.pzbh2 = pzbh2;
		this.xsdw = xsdw;
		this.xsjg = xsjg;
		this.xsrq = xsrq;
		this.jkpz = jkpz;
		this.jkpzhm = jkpzhm;
		this.hgzbh = hgzbh;
		this.nszm = nszm;
		this.nszmbh = nszmbh;
		this.xgzl = xgzl;
		this.qmbh = qmbh;
		this.hmbh = hmbh;
		this.bz = bz;
		this.cjdw = cjdw;
		this.jyjg = jyjg;
		this.bxpzh = bxpzh;
		this.bxje = bxje;
		this.sxrq = sxrq;
		this.zzrq = zzrq;
		this.bxgs = bxgs;
		this.yhpzl = yhpzl;
		this.yhphm = yhphm;
		this.ysyxz = ysyxz;
		this.zcd = zcd;
		this.jcjgzms = jcjgzms;
		this.zylx = zylx;
		this.bgnr = bgnr;
		this.hdid = hdid;
		this.bzcs = bzcs;
		this.bpcs = bpcs;
		this.bdjcs = bdjcs;
		this.zdjzshs = zdjzshs;
		this.qzrq = qzrq;
		this.sfzmmc = sfzmmc;
		this.syr = syr;
		this.zsxzqh = zsxzqh;
		this.zsxxdz = zsxxdz;
		this.yzbm1 = yzbm1;
		this.lxdh = lxdh;
		this.zzz = zzz;
		this.zzxzqh = zzxzqh;
		this.zzxxdz = zzxxdz;
		this.yzbm2 = yzbm2;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.jyw = jyw;
		this.cyry = cyry;
		this.dphgzbh = dphgzbh;
		this.dzyx = dzyx;
		this.xszbh = xszbh;
		this.sjhm = sjhm;
		this.jyhgbzbh = jyhgbzbh;
		this.localIshandle = localIshandle;
		this.localOperat = localOperat;
		this.localOpertime = localOpertime;
		this.localName = localName;
		this.netXm = netXm;
		this.netYjdz = netYjdz;
		this.netYzbm = netYzbm;
		this.netLxdh = netLxdh;
		this.netDzxx = netDzxx;
		this.netJbrxm = netJbrxm;
		this.netJbrlxdh = netJbrlxdh;
		this.chid = chid;
		this.chrq = chrq;
		this.sfxzyc = sfxzyc;
		this.outIn = outIn;
		this.md5 = md5;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.refeshFlag = refeshFlag;
		this.refeshMsg = refeshMsg;
		this.refeshDate = refeshDate;
		this.netFph = netFph;
		this.postZj = postZj;
		this.postTp = postTp;
		this.postSjr = postSjr;
		this.postLxdh = postLxdh;
		this.postYjdz = postYjdz;
		this.postYzbm = postYzbm;
		this.ctccs = ctccs;
		this.tempBz1 = tempBz1;
		this.tempBz2 = tempBz2;
		this.tempBz3 = tempBz3;
		this.tempBz4 = tempBz4;
		this.tempBz5 = tempBz5;
		this.dbyzh = dbyzh;
		this.tyzxh = tyzxh;
	}

	// Property accessors

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHpzl() {
		return this.hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getHphm() {
		return this.hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getClpp1() {
		return this.clpp1;
	}

	public void setClpp1(String clpp1) {
		this.clpp1 = clpp1;
	}

	public String getClxh() {
		return this.clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getClpp2() {
		return this.clpp2;
	}

	public void setClpp2(String clpp2) {
		this.clpp2 = clpp2;
	}

	public String getGcjk() {
		return this.gcjk;
	}

	public void setGcjk(String gcjk) {
		this.gcjk = gcjk;
	}

	public String getZzg() {
		return this.zzg;
	}

	public void setZzg(String zzg) {
		this.zzg = zzg;
	}

	public String getZzcmc() {
		return this.zzcmc;
	}

	public void setZzcmc(String zzcmc) {
		this.zzcmc = zzcmc;
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

	public String getCllx() {
		return this.cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getCsys() {
		return this.csys;
	}

	public void setCsys(String csys) {
		this.csys = csys;
	}

	public String getSyxz() {
		return this.syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSyq() {
		return this.syq;
	}

	public void setSyq(String syq) {
		this.syq = syq;
	}

	public Date getCcdjrq() {
		return this.ccdjrq;
	}

	public void setCcdjrq(Date ccdjrq) {
		this.ccdjrq = ccdjrq;
	}

	public Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

	public Date getYxqz() {
		return this.yxqz;
	}

	public void setYxqz(Date yxqz) {
		this.yxqz = yxqz;
	}

	public Date getQzbfqz() {
		return this.qzbfqz;
	}

	public void setQzbfqz(Date qzbfqz) {
		this.qzbfqz = qzbfqz;
	}

	public String getFzjg() {
		return this.fzjg;
	}

	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}

	public String getGlbm() {
		return this.glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	public Date getFprq() {
		return this.fprq;
	}

	public void setFprq(Date fprq) {
		this.fprq = fprq;
	}

	public Date getFzrq() {
		return this.fzrq;
	}

	public void setFzrq(Date fzrq) {
		this.fzrq = fzrq;
	}

	public Date getFdjrq() {
		return this.fdjrq;
	}

	public void setFdjrq(Date fdjrq) {
		this.fdjrq = fdjrq;
	}

	public Date getFhgzrq() {
		return this.fhgzrq;
	}

	public void setFhgzrq(Date fhgzrq) {
		this.fhgzrq = fhgzrq;
	}

	public Date getBxzzrq() {
		return this.bxzzrq;
	}

	public void setBxzzrq(Date bxzzrq) {
		this.bxzzrq = bxzzrq;
	}

	public String getDjzsbh() {
		return this.djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getXzqh() {
		return this.xzqh;
	}

	public void setXzqh(String xzqh) {
		this.xzqh = xzqh;
	}

	public String getDybj() {
		return this.dybj;
	}

	public void setDybj(String dybj) {
		this.dybj = dybj;
	}

	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getClly() {
		return this.clly;
	}

	public void setClly(String clly) {
		this.clly = clly;
	}

	public String getFdjxh() {
		return this.fdjxh;
	}

	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}

	public String getRlzl() {
		return this.rlzl;
	}

	public void setRlzl(String rlzl) {
		this.rlzl = rlzl;
	}

	public Integer getPl() {
		return this.pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

	public Double getGl() {
		return this.gl;
	}

	public void setGl(Double gl) {
		this.gl = gl;
	}

	public String getZxxs() {
		return this.zxxs;
	}

	public void setZxxs(String zxxs) {
		this.zxxs = zxxs;
	}

	public Integer getCwkc() {
		return this.cwkc;
	}

	public void setCwkc(Integer cwkc) {
		this.cwkc = cwkc;
	}

	public Integer getCwkk() {
		return this.cwkk;
	}

	public void setCwkk(Integer cwkk) {
		this.cwkk = cwkk;
	}

	public Integer getCwkg() {
		return this.cwkg;
	}

	public void setCwkg(Integer cwkg) {
		this.cwkg = cwkg;
	}

	public Integer getHxnbcd() {
		return this.hxnbcd;
	}

	public void setHxnbcd(Integer hxnbcd) {
		this.hxnbcd = hxnbcd;
	}

	public Integer getHxnbkd() {
		return this.hxnbkd;
	}

	public void setHxnbkd(Integer hxnbkd) {
		this.hxnbkd = hxnbkd;
	}

	public Integer getHxnbgd() {
		return this.hxnbgd;
	}

	public void setHxnbgd(Integer hxnbgd) {
		this.hxnbgd = hxnbgd;
	}

	public Integer getGbthps() {
		return this.gbthps;
	}

	public void setGbthps(Integer gbthps) {
		this.gbthps = gbthps;
	}

	public Integer getZs() {
		return this.zs;
	}

	public void setZs(Integer zs) {
		this.zs = zs;
	}

	public Integer getZj() {
		return this.zj;
	}

	public void setZj(Integer zj) {
		this.zj = zj;
	}

	public Integer getQlj() {
		return this.qlj;
	}

	public void setQlj(Integer qlj) {
		this.qlj = qlj;
	}

	public Integer getHlj() {
		return this.hlj;
	}

	public void setHlj(Integer hlj) {
		this.hlj = hlj;
	}

	public String getLtgg() {
		return this.ltgg;
	}

	public void setLtgg(String ltgg) {
		this.ltgg = ltgg;
	}

	public Integer getLts() {
		return this.lts;
	}

	public void setLts(Integer lts) {
		this.lts = lts;
	}

	public Integer getZzl() {
		return this.zzl;
	}

	public void setZzl(Integer zzl) {
		this.zzl = zzl;
	}

	public Integer getZbzl() {
		return this.zbzl;
	}

	public void setZbzl(Integer zbzl) {
		this.zbzl = zbzl;
	}

	public Integer getHdzzl() {
		return this.hdzzl;
	}

	public void setHdzzl(Integer hdzzl) {
		this.hdzzl = hdzzl;
	}

	public Integer getHdzk() {
		return this.hdzk;
	}

	public void setHdzk(Integer hdzk) {
		this.hdzk = hdzk;
	}

	public Integer getZqyzl() {
		return this.zqyzl;
	}

	public void setZqyzl(Integer zqyzl) {
		this.zqyzl = zqyzl;
	}

	public Integer getQpzk() {
		return this.qpzk;
	}

	public void setQpzk(Integer qpzk) {
		this.qpzk = qpzk;
	}

	public Integer getHpzk() {
		return this.hpzk;
	}

	public void setHpzk(Integer hpzk) {
		this.hpzk = hpzk;
	}

	public String getBh() {
		return this.bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getDpid() {
		return this.dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public String getHbdbqk() {
		return this.hbdbqk;
	}

	public void setHbdbqk(String hbdbqk) {
		this.hbdbqk = hbdbqk;
	}

	public Date getCcrq() {
		return this.ccrq;
	}

	public void setCcrq(Date ccrq) {
		this.ccrq = ccrq;
	}

	public String getHdfs() {
		return this.hdfs;
	}

	public void setHdfs(String hdfs) {
		this.hdfs = hdfs;
	}

	public String getLlpz1() {
		return this.llpz1;
	}

	public void setLlpz1(String llpz1) {
		this.llpz1 = llpz1;
	}

	public String getPzbh1() {
		return this.pzbh1;
	}

	public void setPzbh1(String pzbh1) {
		this.pzbh1 = pzbh1;
	}

	public String getLlpz2() {
		return this.llpz2;
	}

	public void setLlpz2(String llpz2) {
		this.llpz2 = llpz2;
	}

	public String getPzbh2() {
		return this.pzbh2;
	}

	public void setPzbh2(String pzbh2) {
		this.pzbh2 = pzbh2;
	}

	public String getXsdw() {
		return this.xsdw;
	}

	public void setXsdw(String xsdw) {
		this.xsdw = xsdw;
	}

	public Integer getXsjg() {
		return this.xsjg;
	}

	public void setXsjg(Integer xsjg) {
		this.xsjg = xsjg;
	}

	public Date getXsrq() {
		return this.xsrq;
	}

	public void setXsrq(Date xsrq) {
		this.xsrq = xsrq;
	}

	public String getJkpz() {
		return this.jkpz;
	}

	public void setJkpz(String jkpz) {
		this.jkpz = jkpz;
	}

	public String getJkpzhm() {
		return this.jkpzhm;
	}

	public void setJkpzhm(String jkpzhm) {
		this.jkpzhm = jkpzhm;
	}

	public String getHgzbh() {
		return this.hgzbh;
	}

	public void setHgzbh(String hgzbh) {
		this.hgzbh = hgzbh;
	}

	public String getNszm() {
		return this.nszm;
	}

	public void setNszm(String nszm) {
		this.nszm = nszm;
	}

	public String getNszmbh() {
		return this.nszmbh;
	}

	public void setNszmbh(String nszmbh) {
		this.nszmbh = nszmbh;
	}

	public String getXgzl() {
		return this.xgzl;
	}

	public void setXgzl(String xgzl) {
		this.xgzl = xgzl;
	}

	public String getQmbh() {
		return this.qmbh;
	}

	public void setQmbh(String qmbh) {
		this.qmbh = qmbh;
	}

	public String getHmbh() {
		return this.hmbh;
	}

	public void setHmbh(String hmbh) {
		this.hmbh = hmbh;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getCjdw() {
		return this.cjdw;
	}

	public void setCjdw(String cjdw) {
		this.cjdw = cjdw;
	}

	public String getJyjg() {
		return this.jyjg;
	}

	public void setJyjg(String jyjg) {
		this.jyjg = jyjg;
	}

	public String getBxpzh() {
		return this.bxpzh;
	}

	public void setBxpzh(String bxpzh) {
		this.bxpzh = bxpzh;
	}

	public Long getBxje() {
		return this.bxje;
	}

	public void setBxje(Long bxje) {
		this.bxje = bxje;
	}

	public Date getSxrq() {
		return this.sxrq;
	}

	public void setSxrq(Date sxrq) {
		this.sxrq = sxrq;
	}

	public Date getZzrq() {
		return this.zzrq;
	}

	public void setZzrq(Date zzrq) {
		this.zzrq = zzrq;
	}

	public String getBxgs() {
		return this.bxgs;
	}

	public void setBxgs(String bxgs) {
		this.bxgs = bxgs;
	}

	public String getYhpzl() {
		return this.yhpzl;
	}

	public void setYhpzl(String yhpzl) {
		this.yhpzl = yhpzl;
	}

	public String getYhphm() {
		return this.yhphm;
	}

	public void setYhphm(String yhphm) {
		this.yhphm = yhphm;
	}

	public String getYsyxz() {
		return this.ysyxz;
	}

	public void setYsyxz(String ysyxz) {
		this.ysyxz = ysyxz;
	}

	public String getZcd() {
		return this.zcd;
	}

	public void setZcd(String zcd) {
		this.zcd = zcd;
	}

	public String getJcjgzms() {
		return this.jcjgzms;
	}

	public void setJcjgzms(String jcjgzms) {
		this.jcjgzms = jcjgzms;
	}

	public String getZylx() {
		return this.zylx;
	}

	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	public String getBgnr() {
		return this.bgnr;
	}

	public void setBgnr(String bgnr) {
		this.bgnr = bgnr;
	}

	public String getHdid() {
		return this.hdid;
	}

	public void setHdid(String hdid) {
		this.hdid = hdid;
	}

	public Integer getBzcs() {
		return this.bzcs;
	}

	public void setBzcs(Integer bzcs) {
		this.bzcs = bzcs;
	}

	public Integer getBpcs() {
		return this.bpcs;
	}

	public void setBpcs(Integer bpcs) {
		this.bpcs = bpcs;
	}

	public Integer getBdjcs() {
		return this.bdjcs;
	}

	public void setBdjcs(Integer bdjcs) {
		this.bdjcs = bdjcs;
	}

	public Integer getZdjzshs() {
		return this.zdjzshs;
	}

	public void setZdjzshs(Integer zdjzshs) {
		this.zdjzshs = zdjzshs;
	}

	public Date getQzrq() {
		return this.qzrq;
	}

	public void setQzrq(Date qzrq) {
		this.qzrq = qzrq;
	}

	public String getSfzmmc() {
		return this.sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getZsxzqh() {
		return this.zsxzqh;
	}

	public void setZsxzqh(String zsxzqh) {
		this.zsxzqh = zsxzqh;
	}

	public String getZsxxdz() {
		return this.zsxxdz;
	}

	public void setZsxxdz(String zsxxdz) {
		this.zsxxdz = zsxxdz;
	}

	public String getYzbm1() {
		return this.yzbm1;
	}

	public void setYzbm1(String yzbm1) {
		this.yzbm1 = yzbm1;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getZzz() {
		return this.zzz;
	}

	public void setZzz(String zzz) {
		this.zzz = zzz;
	}

	public String getZzxzqh() {
		return this.zzxzqh;
	}

	public void setZzxzqh(String zzxzqh) {
		this.zzxzqh = zzxzqh;
	}

	public String getZzxxdz() {
		return this.zzxxdz;
	}

	public void setZzxxdz(String zzxxdz) {
		this.zzxxdz = zzxxdz;
	}

	public String getYzbm2() {
		return this.yzbm2;
	}

	public void setYzbm2(String yzbm2) {
		this.yzbm2 = yzbm2;
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

	public String getJyw() {
		return this.jyw;
	}

	public void setJyw(String jyw) {
		this.jyw = jyw;
	}

	public String getCyry() {
		return this.cyry;
	}

	public void setCyry(String cyry) {
		this.cyry = cyry;
	}

	public String getDphgzbh() {
		return this.dphgzbh;
	}

	public void setDphgzbh(String dphgzbh) {
		this.dphgzbh = dphgzbh;
	}

	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getXszbh() {
		return this.xszbh;
	}

	public void setXszbh(String xszbh) {
		this.xszbh = xszbh;
	}

	public String getSjhm() {
		return this.sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public String getJyhgbzbh() {
		return this.jyhgbzbh;
	}

	public void setJyhgbzbh(String jyhgbzbh) {
		this.jyhgbzbh = jyhgbzbh;
	}

	public String getLocalIshandle() {
		return this.localIshandle;
	}

	public void setLocalIshandle(String localIshandle) {
		this.localIshandle = localIshandle;
	}

	public String getLocalOperat() {
		return this.localOperat;
	}

	public void setLocalOperat(String localOperat) {
		this.localOperat = localOperat;
	}

	public Date getLocalOpertime() {
		return this.localOpertime;
	}

	public void setLocalOpertime(Date localOpertime) {
		this.localOpertime = localOpertime;
	}

	public String getLocalName() {
		return this.localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getNetXm() {
		return this.netXm;
	}

	public void setNetXm(String netXm) {
		this.netXm = netXm;
	}

	public String getNetYjdz() {
		return this.netYjdz;
	}

	public void setNetYjdz(String netYjdz) {
		this.netYjdz = netYjdz;
	}

	public String getNetYzbm() {
		return this.netYzbm;
	}

	public void setNetYzbm(String netYzbm) {
		this.netYzbm = netYzbm;
	}

	public String getNetLxdh() {
		return this.netLxdh;
	}

	public void setNetLxdh(String netLxdh) {
		this.netLxdh = netLxdh;
	}

	public String getNetDzxx() {
		return this.netDzxx;
	}

	public void setNetDzxx(String netDzxx) {
		this.netDzxx = netDzxx;
	}

	public String getNetJbrxm() {
		return this.netJbrxm;
	}

	public void setNetJbrxm(String netJbrxm) {
		this.netJbrxm = netJbrxm;
	}

	public String getNetJbrlxdh() {
		return this.netJbrlxdh;
	}

	public void setNetJbrlxdh(String netJbrlxdh) {
		this.netJbrlxdh = netJbrlxdh;
	}

	public String getChid() {
		return this.chid;
	}

	public void setChid(String chid) {
		this.chid = chid;
	}

	public Date getChrq() {
		return this.chrq;
	}

	public void setChrq(Date chrq) {
		this.chrq = chrq;
	}

	public String getSfxzyc() {
		return this.sfxzyc;
	}

	public void setSfxzyc(String sfxzyc) {
		this.sfxzyc = sfxzyc;
	}

	public String getOutIn() {
		return this.outIn;
	}

	public void setOutIn(String outIn) {
		this.outIn = outIn;
	}

	public String getMd5() {
		return this.md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
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

	public String getRefeshFlag() {
		return this.refeshFlag;
	}

	public void setRefeshFlag(String refeshFlag) {
		this.refeshFlag = refeshFlag;
	}

	public String getRefeshMsg() {
		return this.refeshMsg;
	}

	public void setRefeshMsg(String refeshMsg) {
		this.refeshMsg = refeshMsg;
	}

	public Date getRefeshDate() {
		return this.refeshDate;
	}

	public void setRefeshDate(Date refeshDate) {
		this.refeshDate = refeshDate;
	}

	public String getNetFph() {
		return this.netFph;
	}

	public void setNetFph(String netFph) {
		this.netFph = netFph;
	}

	public String getPostZj() {
		return this.postZj;
	}

	public void setPostZj(String postZj) {
		this.postZj = postZj;
	}

	public String getPostTp() {
		return this.postTp;
	}

	public void setPostTp(String postTp) {
		this.postTp = postTp;
	}

	public String getPostSjr() {
		return this.postSjr;
	}

	public void setPostSjr(String postSjr) {
		this.postSjr = postSjr;
	}

	public String getPostLxdh() {
		return this.postLxdh;
	}

	public void setPostLxdh(String postLxdh) {
		this.postLxdh = postLxdh;
	}

	public String getPostYjdz() {
		return this.postYjdz;
	}

	public void setPostYjdz(String postYjdz) {
		this.postYjdz = postYjdz;
	}

	public String getPostYzbm() {
		return this.postYzbm;
	}

	public void setPostYzbm(String postYzbm) {
		this.postYzbm = postYzbm;
	}

	public String getCtccs() {
		return this.ctccs;
	}

	public void setCtccs(String ctccs) {
		this.ctccs = ctccs;
	}

	public String getTempBz1() {
		return this.tempBz1;
	}

	public void setTempBz1(String tempBz1) {
		this.tempBz1 = tempBz1;
	}

	public String getTempBz2() {
		return this.tempBz2;
	}

	public void setTempBz2(String tempBz2) {
		this.tempBz2 = tempBz2;
	}

	public String getTempBz3() {
		return this.tempBz3;
	}

	public void setTempBz3(String tempBz3) {
		this.tempBz3 = tempBz3;
	}

	public String getTempBz4() {
		return this.tempBz4;
	}

	public void setTempBz4(String tempBz4) {
		this.tempBz4 = tempBz4;
	}

	public String getTempBz5() {
		return this.tempBz5;
	}

	public void setTempBz5(String tempBz5) {
		this.tempBz5 = tempBz5;
	}

	public String getDbyzh() {
		return this.dbyzh;
	}

	public void setDbyzh(String dbyzh) {
		this.dbyzh = dbyzh;
	}

	public String getTyzxh() {
		return this.tyzxh;
	}

	public void setTyzxh(String tyzxh) {
		this.tyzxh = tyzxh;
	}

	/**
	 * @return the fpdm
	 */
	public String getFpdm() {
		return fpdm;
	}

	/**
	 * @param fpdm the fpdm to set
	 */
	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	/**
	 * @return the fphm
	 */
	public String getFphm() {
		return fphm;
	}

	/**
	 * @param fphm the fphm to set
	 */
	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	/**
	 * @return the gzszmbh
	 */
	public String getGzszmbh() {
		return gzszmbh;
	}

	/**
	 * @param gzszmbh the gzszmbh to set
	 */
	public void setGzszmbh(String gzszmbh) {
		this.gzszmbh = gzszmbh;
	}

	/**
	 * @return the yzGsInfo
	 */
	public String getYzGsInfo() {
		return yzGsInfo;
	}

	/**
	 * @param yzGsInfo the yzGsInfo to set
	 */
	public void setYzGsInfo(String yzGsInfo) {
		this.yzGsInfo = yzGsInfo;
	}

	public String getTybLsh() {
		return tybLsh;
	}

	public void setTybLsh(String tybLsh) {
		this.tybLsh = tybLsh;
	}
	
}