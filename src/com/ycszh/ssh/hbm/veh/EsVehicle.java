package com.ycszh.ssh.hbm.veh;

import java.util.Date;

/**
 * EsVehicle01 entity. @author MyEclipse Persistence Tools
 */

public class EsVehicle implements java.io.Serializable {

	// Fields

	private String bsh;
	private String hphm;
	private String hpzl;
	private String cj;
	private String cp;
	private String xh;
	private String sfzmhm;
	private String sfzmmc;
	private String czmc;
	private String rlzl;
	private Date ccdjrq;
	private Integer pl;
	private String cllx;
	private String fdjxh;
	private String zzg;
	private Integer zzl;
	private Integer hdzzl;
	private Integer zbzl;
	private String clsbdh;
	private String djzsbh;
	private String zt;
	private String hgzbh;
	private Date fhgzrq;
	private String csys;
	private String dphgzbh;
	private String fdjh;
	private Double gl;
	private String hbdbqk;
	private Integer cwkc;
	private Short cwkg;
	private Short cwkk;
	private Integer hxnbcd;
	private Short hxnbkd;
	private Short hxnbgd;
	private Short gbthps;
	private Byte lts;
	private String ltgg;
	private Short qlj;
	private Short hlj;
	private Integer zj;
	private Boolean zs;
	private String zxxs;
	private Integer zqyzl;
	private String qpzk;
	private String hpzk;
	private Short hdzk;
	private Date ccrq;
	private Date qzbfqz;
	private Date yxqz;
	private String syxz;
	private String jbr;
	private Date yqjyqzbfqz;
	private String lsh;
	private String syq;
	private String zsxzqh;
	private String zsxxdz;
	private String yzbm1;
	private String lxdh;
	private String zzz;
	private String zzxzqh;
	private String zzxxdz;
	private String yzbm2;
	private String dzyx;
	private String sjhm;
	private Date djrq;

	// Constructors

	/** default constructor */
	public EsVehicle() {
	}

	/** minimal constructor */
	public EsVehicle(String bsh, String hphm, String hpzl, String cp,
			String xh, String czmc, Date ccdjrq, String cllx, String zzg,
			String clsbdh, String zt, String csys, String fdjh, Integer zj,
			Boolean zs, String syxz, String syq) {
		this.bsh = bsh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.cp = cp;
		this.xh = xh;
		this.czmc = czmc;
		this.ccdjrq = ccdjrq;
		this.cllx = cllx;
		this.zzg = zzg;
		this.clsbdh = clsbdh;
		this.zt = zt;
		this.csys = csys;
		this.fdjh = fdjh;
		this.zj = zj;
		this.zs = zs;
		this.syxz = syxz;
		this.syq = syq;
	}

	/** full constructor */
	public EsVehicle(String bsh, String hphm, String hpzl, String cj,
			String cp, String xh, String sfzmhm, String sfzmmc, String czmc,
			String rlzl, Date ccdjrq, Integer pl, String cllx, String fdjxh,
			String zzg, Integer zzl, Integer hdzzl, Integer zbzl,
			String clsbdh, String djzsbh, String zt, String hgzbh, Date fhgzrq,
			String csys, String dphgzbh, String fdjh, Double gl, String hbdbqk,
			Integer cwkc, Short cwkg, Short cwkk, Integer hxnbcd, Short hxnbkd,
			Short hxnbgd, Short gbthps, Byte lts, String ltgg, Short qlj,
			Short hlj, Integer zj, Boolean zs, String zxxs, Integer zqyzl,
			String qpzk, String hpzk, Short hdzk, Date ccrq, Date qzbfqz,
			Date yxqz, String syxz, String jbr, Date yqjyqzbfqz, String lsh,
			String syq, String zsxzqh, String zsxxdz, String yzbm1,
			String lxdh, String zzz, String zzxzqh, String zzxxdz,
			String yzbm2, String dzyx, String sjhm, Date djrq) {
		this.bsh = bsh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.cj = cj;
		this.cp = cp;
		this.xh = xh;
		this.sfzmhm = sfzmhm;
		this.sfzmmc = sfzmmc;
		this.czmc = czmc;
		this.rlzl = rlzl;
		this.ccdjrq = ccdjrq;
		this.pl = pl;
		this.cllx = cllx;
		this.fdjxh = fdjxh;
		this.zzg = zzg;
		this.zzl = zzl;
		this.hdzzl = hdzzl;
		this.zbzl = zbzl;
		this.clsbdh = clsbdh;
		this.djzsbh = djzsbh;
		this.zt = zt;
		this.hgzbh = hgzbh;
		this.fhgzrq = fhgzrq;
		this.csys = csys;
		this.dphgzbh = dphgzbh;
		this.fdjh = fdjh;
		this.gl = gl;
		this.hbdbqk = hbdbqk;
		this.cwkc = cwkc;
		this.cwkg = cwkg;
		this.cwkk = cwkk;
		this.hxnbcd = hxnbcd;
		this.hxnbkd = hxnbkd;
		this.hxnbgd = hxnbgd;
		this.gbthps = gbthps;
		this.lts = lts;
		this.ltgg = ltgg;
		this.qlj = qlj;
		this.hlj = hlj;
		this.zj = zj;
		this.zs = zs;
		this.zxxs = zxxs;
		this.zqyzl = zqyzl;
		this.qpzk = qpzk;
		this.hpzk = hpzk;
		this.hdzk = hdzk;
		this.ccrq = ccrq;
		this.qzbfqz = qzbfqz;
		this.yxqz = yxqz;
		this.syxz = syxz;
		this.jbr = jbr;
		this.yqjyqzbfqz = yqjyqzbfqz;
		this.lsh = lsh;
		this.syq = syq;
		this.zsxzqh = zsxzqh;
		this.zsxxdz = zsxxdz;
		this.yzbm1 = yzbm1;
		this.lxdh = lxdh;
		this.zzz = zzz;
		this.zzxzqh = zzxzqh;
		this.zzxxdz = zzxxdz;
		this.yzbm2 = yzbm2;
		this.dzyx = dzyx;
		this.sjhm = sjhm;
		this.djrq = djrq;
	}

	// Property accessors

	public String getBsh() {
		return this.bsh;
	}

	public void setBsh(String bsh) {
		this.bsh = bsh;
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

	public String getCj() {
		return this.cj;
	}

	public void setCj(String cj) {
		this.cj = cj;
	}

	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSfzmmc() {
		return this.sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getCzmc() {
		return this.czmc;
	}

	public void setCzmc(String czmc) {
		this.czmc = czmc;
	}

	public String getRlzl() {
		return this.rlzl;
	}

	public void setRlzl(String rlzl) {
		this.rlzl = rlzl;
	}

	public Date getCcdjrq() {
		return this.ccdjrq;
	}

	public void setCcdjrq(Date ccdjrq) {
		this.ccdjrq = ccdjrq;
	}

	public Integer getPl() {
		return this.pl;
	}

	public void setPl(Integer pl) {
		this.pl = pl;
	}

	public String getCllx() {
		return this.cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getFdjxh() {
		return this.fdjxh;
	}

	public void setFdjxh(String fdjxh) {
		this.fdjxh = fdjxh;
	}

	public String getZzg() {
		return this.zzg;
	}

	public void setZzg(String zzg) {
		this.zzg = zzg;
	}

	public Integer getZzl() {
		return this.zzl;
	}

	public void setZzl(Integer zzl) {
		this.zzl = zzl;
	}

	public Integer getHdzzl() {
		return this.hdzzl;
	}

	public void setHdzzl(Integer hdzzl) {
		this.hdzzl = hdzzl;
	}

	public Integer getZbzl() {
		return this.zbzl;
	}

	public void setZbzl(Integer zbzl) {
		this.zbzl = zbzl;
	}

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getDjzsbh() {
		return this.djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getHgzbh() {
		return this.hgzbh;
	}

	public void setHgzbh(String hgzbh) {
		this.hgzbh = hgzbh;
	}

	public Date getFhgzrq() {
		return this.fhgzrq;
	}

	public void setFhgzrq(Date fhgzrq) {
		this.fhgzrq = fhgzrq;
	}

	public String getCsys() {
		return this.csys;
	}

	public void setCsys(String csys) {
		this.csys = csys;
	}

	public String getDphgzbh() {
		return this.dphgzbh;
	}

	public void setDphgzbh(String dphgzbh) {
		this.dphgzbh = dphgzbh;
	}

	public String getFdjh() {
		return this.fdjh;
	}

	public void setFdjh(String fdjh) {
		this.fdjh = fdjh;
	}

	public Double getGl() {
		return this.gl;
	}

	public void setGl(Double gl) {
		this.gl = gl;
	}

	public String getHbdbqk() {
		return this.hbdbqk;
	}

	public void setHbdbqk(String hbdbqk) {
		this.hbdbqk = hbdbqk;
	}

	public Integer getCwkc() {
		return this.cwkc;
	}

	public void setCwkc(Integer cwkc) {
		this.cwkc = cwkc;
	}

	public Short getCwkg() {
		return this.cwkg;
	}

	public void setCwkg(Short cwkg) {
		this.cwkg = cwkg;
	}

	public Short getCwkk() {
		return this.cwkk;
	}

	public void setCwkk(Short cwkk) {
		this.cwkk = cwkk;
	}

	public Integer getHxnbcd() {
		return this.hxnbcd;
	}

	public void setHxnbcd(Integer hxnbcd) {
		this.hxnbcd = hxnbcd;
	}

	public Short getHxnbkd() {
		return this.hxnbkd;
	}

	public void setHxnbkd(Short hxnbkd) {
		this.hxnbkd = hxnbkd;
	}

	public Short getHxnbgd() {
		return this.hxnbgd;
	}

	public void setHxnbgd(Short hxnbgd) {
		this.hxnbgd = hxnbgd;
	}

	public Short getGbthps() {
		return this.gbthps;
	}

	public void setGbthps(Short gbthps) {
		this.gbthps = gbthps;
	}

	public Byte getLts() {
		return this.lts;
	}

	public void setLts(Byte lts) {
		this.lts = lts;
	}

	public String getLtgg() {
		return this.ltgg;
	}

	public void setLtgg(String ltgg) {
		this.ltgg = ltgg;
	}

	public Short getQlj() {
		return this.qlj;
	}

	public void setQlj(Short qlj) {
		this.qlj = qlj;
	}

	public Short getHlj() {
		return this.hlj;
	}

	public void setHlj(Short hlj) {
		this.hlj = hlj;
	}

	public Integer getZj() {
		return this.zj;
	}

	public void setZj(Integer zj) {
		this.zj = zj;
	}

	public Boolean getZs() {
		return this.zs;
	}

	public void setZs(Boolean zs) {
		this.zs = zs;
	}

	public String getZxxs() {
		return this.zxxs;
	}

	public void setZxxs(String zxxs) {
		this.zxxs = zxxs;
	}

	public Integer getZqyzl() {
		return this.zqyzl;
	}

	public void setZqyzl(Integer zqyzl) {
		this.zqyzl = zqyzl;
	}

	public String getQpzk() {
		return this.qpzk;
	}

	public void setQpzk(String qpzk) {
		this.qpzk = qpzk;
	}

	public String getHpzk() {
		return this.hpzk;
	}

	public void setHpzk(String hpzk) {
		this.hpzk = hpzk;
	}

	public Short getHdzk() {
		return this.hdzk;
	}

	public void setHdzk(Short hdzk) {
		this.hdzk = hdzk;
	}

	public Date getCcrq() {
		return this.ccrq;
	}

	public void setCcrq(Date ccrq) {
		this.ccrq = ccrq;
	}

	public Date getQzbfqz() {
		return this.qzbfqz;
	}

	public void setQzbfqz(Date qzbfqz) {
		this.qzbfqz = qzbfqz;
	}

	public Date getYxqz() {
		return this.yxqz;
	}

	public void setYxqz(Date yxqz) {
		this.yxqz = yxqz;
	}

	public String getSyxz() {
		return this.syxz;
	}

	public void setSyxz(String syxz) {
		this.syxz = syxz;
	}

	public String getJbr() {
		return this.jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public Date getYqjyqzbfqz() {
		return this.yqjyqzbfqz;
	}

	public void setYqjyqzbfqz(Date yqjyqzbfqz) {
		this.yqjyqzbfqz = yqjyqzbfqz;
	}

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getSyq() {
		return this.syq;
	}

	public void setSyq(String syq) {
		this.syq = syq;
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

	public String getDzyx() {
		return this.dzyx;
	}

	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}

	public String getSjhm() {
		return this.sjhm;
	}

	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}

	public Date getDjrq() {
		return this.djrq;
	}

	public void setDjrq(Date djrq) {
		this.djrq = djrq;
	}

}