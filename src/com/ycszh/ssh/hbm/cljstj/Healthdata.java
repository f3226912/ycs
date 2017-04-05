package com.ycszh.ssh.hbm.cljstj;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * HealthdataId entity. @author MyEclipse Persistence Tools
 */

public class Healthdata  extends BaseObject{
	private static final long serialVersionUID = 1L;
	
	// Fields

	private Long sxh;
	private String sfzmmc;
	private String sfzmhm;
	private String dabh;
	private String xm;
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
	private String lsh;
	private Date rksj;
	private Date gxsj;
	private BigDecimal flag;
	private String remark;
	private String tjww;
	private Date tjwwsj;
	private String lrlb;
	private String lrr;
	private Date lrsj;
	private String lrbm;
	private Date syntime;
	private String filePath;
	private String zhclhj;
	private String zhclzt;
	private Date zhclsj;
	private String zhclr;
	private String zhclsm;
	private String lockzt;
	private String lockr;
	private String lockbm;
	private String lockbmKj;
	private Date locksj;
	private String lockip;
	private String lxdh;

	// Constructors

	/** default constructor */
	public Healthdata() {
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** minimal constructor */
	public Healthdata(String xm, Short sg, String bsl, String tl, String sz,
			String zxz, String yxz, String qgjb, Date tjrq, Date gxsj,
			BigDecimal flag, String zhclhj, String zhclzt) {
		this.xm = xm;
		this.sg = sg;
		this.bsl = bsl;
		this.tl = tl;
		this.sz = sz;
		this.zxz = zxz;
		this.yxz = yxz;
		this.qgjb = qgjb;
		this.tjrq = tjrq;
		this.gxsj = gxsj;
		this.flag = flag;
		this.zhclhj = zhclhj;
		this.zhclzt = zhclzt;
	}

	/** full constructor */
	public Healthdata(Long sxh, String sfzmmc, String sfzmhm, String dabh,
			String xm, Short sg, Double zsl, Double ysl, String bsl, String tl,
			String sz, String zxz, String yxz, String qgjb, Date tjrq,
			String tjyymc, String lsh, Date rksj, Date gxsj, BigDecimal flag,
			String remark, String tjww, Date tjwwsj, String lrlb, String lrr,
			Date lrsj, String lrbm, Date syntime, String filePath,
			String zhclhj, String zhclzt, Date zhclsj, String zhclr,
			String zhclsm, String lockzt, String lockr, String lockbm,
			String lockbmKj, Date locksj, String lockip) {
		this.sxh = sxh;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.dabh = dabh;
		this.xm = xm;
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
		this.lsh = lsh;
		this.rksj = rksj;
		this.gxsj = gxsj;
		this.flag = flag;
		this.remark = remark;
		this.tjww = tjww;
		this.tjwwsj = tjwwsj;
		this.lrlb = lrlb;
		this.lrr = lrr;
		this.lrsj = lrsj;
		this.lrbm = lrbm;
		this.syntime = syntime;
		this.filePath = filePath;
		this.zhclhj = zhclhj;
		this.zhclzt = zhclzt;
		this.zhclsj = zhclsj;
		this.zhclr = zhclr;
		this.zhclsm = zhclsm;
		this.lockzt = lockzt;
		this.lockr = lockr;
		this.lockbm = lockbm;
		this.lockbmKj = lockbmKj;
		this.locksj = locksj;
		this.lockip = lockip;
	}

	// Property accessors

	public Long getSxh() {
		return this.sxh;
	}

	public void setSxh(Long sxh) {
		this.sxh = sxh;
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

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
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

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public Date getRksj() {
		return this.rksj;
	}

	public void setRksj(Date rksj) {
		this.rksj = rksj;
	}

	public Date getGxsj() {
		return this.gxsj;
	}

	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}

	public BigDecimal getFlag() {
		return this.flag;
	}

	public void setFlag(BigDecimal flag) {
		this.flag = flag;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTjww() {
		return this.tjww;
	}

	public void setTjww(String tjww) {
		this.tjww = tjww;
	}

	public Date getTjwwsj() {
		return this.tjwwsj;
	}

	public void setTjwwsj(Date tjwwsj) {
		this.tjwwsj = tjwwsj;
	}

	public String getLrlb() {
		return this.lrlb;
	}

	public void setLrlb(String lrlb) {
		this.lrlb = lrlb;
	}

	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrbm() {
		return this.lrbm;
	}

	public void setLrbm(String lrbm) {
		this.lrbm = lrbm;
	}

	public Date getSyntime() {
		return this.syntime;
	}

	public void setSyntime(Date syntime) {
		this.syntime = syntime;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public String getZhclsm() {
		return this.zhclsm;
	}

	public void setZhclsm(String zhclsm) {
		this.zhclsm = zhclsm;
	}

	public String getLockzt() {
		return this.lockzt;
	}

	public void setLockzt(String lockzt) {
		this.lockzt = lockzt;
	}

	public String getLockr() {
		return this.lockr;
	}

	public void setLockr(String lockr) {
		this.lockr = lockr;
	}

	public String getLockbm() {
		return this.lockbm;
	}

	public void setLockbm(String lockbm) {
		this.lockbm = lockbm;
	}

	public String getLockbmKj() {
		return this.lockbmKj;
	}

	public void setLockbmKj(String lockbmKj) {
		this.lockbmKj = lockbmKj;
	}

	public Date getLocksj() {
		return this.locksj;
	}

	public void setLocksj(Date locksj) {
		this.locksj = locksj;
	}

	public String getLockip() {
		return this.lockip;
	}

	public void setLockip(String lockip) {
		this.lockip = lockip;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Healthdata))
			return false;
		Healthdata castOther = (Healthdata) other;

		return ((this.getSxh() == castOther.getSxh()) || (this.getSxh() != null
				&& castOther.getSxh() != null && this.getSxh().equals(
				castOther.getSxh())))
				&& ((this.getSfzmmc() == castOther.getSfzmmc()) || (this
						.getSfzmmc() != null
						&& castOther.getSfzmmc() != null && this.getSfzmmc()
						.equals(castOther.getSfzmmc())))
				&& ((this.getSfzmhm() == castOther.getSfzmhm()) || (this
						.getSfzmhm() != null
						&& castOther.getSfzmhm() != null && this.getSfzmhm()
						.equals(castOther.getSfzmhm())))
				&& ((this.getDabh() == castOther.getDabh()) || (this.getDabh() != null
						&& castOther.getDabh() != null && this.getDabh()
						.equals(castOther.getDabh())))
				&& ((this.getXm() == castOther.getXm()) || (this.getXm() != null
						&& castOther.getXm() != null && this.getXm().equals(
						castOther.getXm())))
				&& ((this.getSg() == castOther.getSg()) || (this.getSg() != null
						&& castOther.getSg() != null && this.getSg().equals(
						castOther.getSg())))
				&& ((this.getZsl() == castOther.getZsl()) || (this.getZsl() != null
						&& castOther.getZsl() != null && this.getZsl().equals(
						castOther.getZsl())))
				&& ((this.getYsl() == castOther.getYsl()) || (this.getYsl() != null
						&& castOther.getYsl() != null && this.getYsl().equals(
						castOther.getYsl())))
				&& ((this.getBsl() == castOther.getBsl()) || (this.getBsl() != null
						&& castOther.getBsl() != null && this.getBsl().equals(
						castOther.getBsl())))
				&& ((this.getTl() == castOther.getTl()) || (this.getTl() != null
						&& castOther.getTl() != null && this.getTl().equals(
						castOther.getTl())))
				&& ((this.getSz() == castOther.getSz()) || (this.getSz() != null
						&& castOther.getSz() != null && this.getSz().equals(
						castOther.getSz())))
				&& ((this.getZxz() == castOther.getZxz()) || (this.getZxz() != null
						&& castOther.getZxz() != null && this.getZxz().equals(
						castOther.getZxz())))
				&& ((this.getYxz() == castOther.getYxz()) || (this.getYxz() != null
						&& castOther.getYxz() != null && this.getYxz().equals(
						castOther.getYxz())))
				&& ((this.getQgjb() == castOther.getQgjb()) || (this.getQgjb() != null
						&& castOther.getQgjb() != null && this.getQgjb()
						.equals(castOther.getQgjb())))
				&& ((this.getTjrq() == castOther.getTjrq()) || (this.getTjrq() != null
						&& castOther.getTjrq() != null && this.getTjrq()
						.equals(castOther.getTjrq())))
				&& ((this.getTjyymc() == castOther.getTjyymc()) || (this
						.getTjyymc() != null
						&& castOther.getTjyymc() != null && this.getTjyymc()
						.equals(castOther.getTjyymc())))
				&& ((this.getLsh() == castOther.getLsh()) || (this.getLsh() != null
						&& castOther.getLsh() != null && this.getLsh().equals(
						castOther.getLsh())))
				&& ((this.getRksj() == castOther.getRksj()) || (this.getRksj() != null
						&& castOther.getRksj() != null && this.getRksj()
						.equals(castOther.getRksj())))
				&& ((this.getGxsj() == castOther.getGxsj()) || (this.getGxsj() != null
						&& castOther.getGxsj() != null && this.getGxsj()
						.equals(castOther.getGxsj())))
				&& ((this.getFlag() == castOther.getFlag()) || (this.getFlag() != null
						&& castOther.getFlag() != null && this.getFlag()
						.equals(castOther.getFlag())))
				&& ((this.getRemark() == castOther.getRemark()) || (this
						.getRemark() != null
						&& castOther.getRemark() != null && this.getRemark()
						.equals(castOther.getRemark())))
				&& ((this.getTjww() == castOther.getTjww()) || (this.getTjww() != null
						&& castOther.getTjww() != null && this.getTjww()
						.equals(castOther.getTjww())))
				&& ((this.getTjwwsj() == castOther.getTjwwsj()) || (this
						.getTjwwsj() != null
						&& castOther.getTjwwsj() != null && this.getTjwwsj()
						.equals(castOther.getTjwwsj())))
				&& ((this.getLrlb() == castOther.getLrlb()) || (this.getLrlb() != null
						&& castOther.getLrlb() != null && this.getLrlb()
						.equals(castOther.getLrlb())))
				&& ((this.getLrr() == castOther.getLrr()) || (this.getLrr() != null
						&& castOther.getLrr() != null && this.getLrr().equals(
						castOther.getLrr())))
				&& ((this.getLrsj() == castOther.getLrsj()) || (this.getLrsj() != null
						&& castOther.getLrsj() != null && this.getLrsj()
						.equals(castOther.getLrsj())))
				&& ((this.getLrbm() == castOther.getLrbm()) || (this.getLrbm() != null
						&& castOther.getLrbm() != null && this.getLrbm()
						.equals(castOther.getLrbm())))
				&& ((this.getSyntime() == castOther.getSyntime()) || (this
						.getSyntime() != null
						&& castOther.getSyntime() != null && this.getSyntime()
						.equals(castOther.getSyntime())))
				&& ((this.getFilePath() == castOther.getFilePath()) || (this
						.getFilePath() != null
						&& castOther.getFilePath() != null && this
						.getFilePath().equals(castOther.getFilePath())))
				&& ((this.getZhclhj() == castOther.getZhclhj()) || (this
						.getZhclhj() != null
						&& castOther.getZhclhj() != null && this.getZhclhj()
						.equals(castOther.getZhclhj())))
				&& ((this.getZhclzt() == castOther.getZhclzt()) || (this
						.getZhclzt() != null
						&& castOther.getZhclzt() != null && this.getZhclzt()
						.equals(castOther.getZhclzt())))
				&& ((this.getZhclsj() == castOther.getZhclsj()) || (this
						.getZhclsj() != null
						&& castOther.getZhclsj() != null && this.getZhclsj()
						.equals(castOther.getZhclsj())))
				&& ((this.getZhclr() == castOther.getZhclr()) || (this
						.getZhclr() != null
						&& castOther.getZhclr() != null && this.getZhclr()
						.equals(castOther.getZhclr())))
				&& ((this.getZhclsm() == castOther.getZhclsm()) || (this
						.getZhclsm() != null
						&& castOther.getZhclsm() != null && this.getZhclsm()
						.equals(castOther.getZhclsm())))
				&& ((this.getLockzt() == castOther.getLockzt()) || (this
						.getLockzt() != null
						&& castOther.getLockzt() != null && this.getLockzt()
						.equals(castOther.getLockzt())))
				&& ((this.getLockr() == castOther.getLockr()) || (this
						.getLockr() != null
						&& castOther.getLockr() != null && this.getLockr()
						.equals(castOther.getLockr())))
				&& ((this.getLockbm() == castOther.getLockbm()) || (this
						.getLockbm() != null
						&& castOther.getLockbm() != null && this.getLockbm()
						.equals(castOther.getLockbm())))
				&& ((this.getLockbmKj() == castOther.getLockbmKj()) || (this
						.getLockbmKj() != null
						&& castOther.getLockbmKj() != null && this
						.getLockbmKj().equals(castOther.getLockbmKj())))
				&& ((this.getLocksj() == castOther.getLocksj()) || (this
						.getLocksj() != null
						&& castOther.getLocksj() != null && this.getLocksj()
						.equals(castOther.getLocksj())))
				&& ((this.getLockip() == castOther.getLockip()) || (this
						.getLockip() != null
						&& castOther.getLockip() != null && this.getLockip()
						.equals(castOther.getLockip())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getSxh() == null ? 0 : this.getSxh().hashCode());
		result = 37 * result
				+ (getSfzmmc() == null ? 0 : this.getSfzmmc().hashCode());
		result = 37 * result
				+ (getSfzmhm() == null ? 0 : this.getSfzmhm().hashCode());
		result = 37 * result
				+ (getDabh() == null ? 0 : this.getDabh().hashCode());
		result = 37 * result + (getXm() == null ? 0 : this.getXm().hashCode());
		result = 37 * result + (getSg() == null ? 0 : this.getSg().hashCode());
		result = 37 * result
				+ (getZsl() == null ? 0 : this.getZsl().hashCode());
		result = 37 * result
				+ (getYsl() == null ? 0 : this.getYsl().hashCode());
		result = 37 * result
				+ (getBsl() == null ? 0 : this.getBsl().hashCode());
		result = 37 * result + (getTl() == null ? 0 : this.getTl().hashCode());
		result = 37 * result + (getSz() == null ? 0 : this.getSz().hashCode());
		result = 37 * result
				+ (getZxz() == null ? 0 : this.getZxz().hashCode());
		result = 37 * result
				+ (getYxz() == null ? 0 : this.getYxz().hashCode());
		result = 37 * result
				+ (getQgjb() == null ? 0 : this.getQgjb().hashCode());
		result = 37 * result
				+ (getTjrq() == null ? 0 : this.getTjrq().hashCode());
		result = 37 * result
				+ (getTjyymc() == null ? 0 : this.getTjyymc().hashCode());
		result = 37 * result
				+ (getLsh() == null ? 0 : this.getLsh().hashCode());
		result = 37 * result
				+ (getRksj() == null ? 0 : this.getRksj().hashCode());
		result = 37 * result
				+ (getGxsj() == null ? 0 : this.getGxsj().hashCode());
		result = 37 * result
				+ (getFlag() == null ? 0 : this.getFlag().hashCode());
		result = 37 * result
				+ (getRemark() == null ? 0 : this.getRemark().hashCode());
		result = 37 * result
				+ (getTjww() == null ? 0 : this.getTjww().hashCode());
		result = 37 * result
				+ (getTjwwsj() == null ? 0 : this.getTjwwsj().hashCode());
		result = 37 * result
				+ (getLrlb() == null ? 0 : this.getLrlb().hashCode());
		result = 37 * result
				+ (getLrr() == null ? 0 : this.getLrr().hashCode());
		result = 37 * result
				+ (getLrsj() == null ? 0 : this.getLrsj().hashCode());
		result = 37 * result
				+ (getLrbm() == null ? 0 : this.getLrbm().hashCode());
		result = 37 * result
				+ (getSyntime() == null ? 0 : this.getSyntime().hashCode());
		result = 37 * result
				+ (getFilePath() == null ? 0 : this.getFilePath().hashCode());
		result = 37 * result
				+ (getZhclhj() == null ? 0 : this.getZhclhj().hashCode());
		result = 37 * result
				+ (getZhclzt() == null ? 0 : this.getZhclzt().hashCode());
		result = 37 * result
				+ (getZhclsj() == null ? 0 : this.getZhclsj().hashCode());
		result = 37 * result
				+ (getZhclr() == null ? 0 : this.getZhclr().hashCode());
		result = 37 * result
				+ (getZhclsm() == null ? 0 : this.getZhclsm().hashCode());
		result = 37 * result
				+ (getLockzt() == null ? 0 : this.getLockzt().hashCode());
		result = 37 * result
				+ (getLockr() == null ? 0 : this.getLockr().hashCode());
		result = 37 * result
				+ (getLockbm() == null ? 0 : this.getLockbm().hashCode());
		result = 37 * result
				+ (getLockbmKj() == null ? 0 : this.getLockbmKj().hashCode());
		result = 37 * result
				+ (getLocksj() == null ? 0 : this.getLocksj().hashCode());
		result = 37 * result
				+ (getLockip() == null ? 0 : this.getLockip().hashCode());
		return result;
	}

}