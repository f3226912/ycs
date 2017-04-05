package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * TXbycGpsLogId entity. @author MyEclipse Persistence Tools
 */

public class TXbycGpsLog extends BaseObject {

	// Fields

	/**
	 * 
	 */
	public String gpsId;
	public String jgmc;
	public String xxdz;
	public String gpsx;
	public String gpsy;
	public String zt;
	public String czr;
	public String czrxm;
	public String czbm;
	public String czbmKj;
	public Date czrq;
	public String czip;
	public String yxjl;
	public String cznr;

	// Constructors

	/** default constructor */
	public TXbycGpsLog() {
	}

	/** full constructor */
	public TXbycGpsLog(String gpsId, String jgmc, String xxdz,
			String gpsx, String gpsy, String zt, String czr,
			String czrxm, String czbm, String czbmKj, Date czrq, String czip,
			String yxjl) {
		this.gpsId = gpsId;
		this.jgmc = jgmc;
		this.xxdz = xxdz;
		this.gpsx = gpsx;
		this.gpsy = gpsy;
		this.zt = zt;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
		this.yxjl = yxjl;
	}

	// Property accessors

	public String getGpsId() {
		return this.gpsId;
	}

	public void setGpsId(String gpsId) {
		this.gpsId = gpsId;
	}

	public String getJgmc() {
		return this.jgmc;
	}

	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	public String getXxdz() {
		return this.xxdz;
	}

	public void setXxdz(String xxdz) {
		this.xxdz = xxdz;
	}

	public String getGpsx() {
		return this.gpsx;
	}

	public void setGpsx(String gpsx) {
		this.gpsx = gpsx;
	}

	public String getGpsy() {
		return this.gpsy;
	}

	public void setGpsy(String gpsy) {
		this.gpsy = gpsy;
	}

	public String getZt() {
		return this.zt;
	}

	public void setZt(String zt) {
		this.zt = zt;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCzrxm() {
		return this.czrxm;
	}

	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}

	public String getCzbm() {
		return this.czbm;
	}

	public void setCzbm(String czbm) {
		this.czbm = czbm;
	}

	public String getCzbmKj() {
		return this.czbmKj;
	}

	public void setCzbmKj(String czbmKj) {
		this.czbmKj = czbmKj;
	}

	public Date getCzrq() {
		return this.czrq;
	}

	public void setCzrq(Date czrq) {
		this.czrq = czrq;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public String getYxjl() {
		return this.yxjl;
	}

	public void setYxjl(String yxjl) {
		this.yxjl = yxjl;
	}

	public String getCznr() {
		return cznr;
	}
	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TXbycGpsLog))
			return false;
		TXbycGpsLog castOther = (TXbycGpsLog) other;

		return ((this.getGpsId() == castOther.getGpsId()) || (this.getGpsId() != null
				&& castOther.getGpsId() != null && this.getGpsId().equals(
				castOther.getGpsId())))
				&& ((this.getJgmc() == castOther.getJgmc()) || (this.getJgmc() != null
						&& castOther.getJgmc() != null && this.getJgmc()
						.equals(castOther.getJgmc())))
				&& ((this.getXxdz() == castOther.getXxdz()) || (this.getXxdz() != null
						&& castOther.getXxdz() != null && this.getXxdz()
						.equals(castOther.getXxdz())))
				&& ((this.getGpsx() == castOther.getGpsx()) || (this.getGpsx() != null
						&& castOther.getGpsx() != null && this.getGpsx()
						.equals(castOther.getGpsx())))
				&& ((this.getGpsy() == castOther.getGpsy()) || (this.getGpsy() != null
						&& castOther.getGpsy() != null && this.getGpsy()
						.equals(castOther.getGpsy())))
				&& ((this.getZt() == castOther.getZt()) || (this.getZt() != null
						&& castOther.getZt() != null && this.getZt().equals(
						castOther.getZt())))
				&& ((this.getCzr() == castOther.getCzr()) || (this.getCzr() != null
						&& castOther.getCzr() != null && this.getCzr().equals(
						castOther.getCzr())))
				&& ((this.getCzrxm() == castOther.getCzrxm()) || (this
						.getCzrxm() != null
						&& castOther.getCzrxm() != null && this.getCzrxm()
						.equals(castOther.getCzrxm())))
				&& ((this.getCzbm() == castOther.getCzbm()) || (this.getCzbm() != null
						&& castOther.getCzbm() != null && this.getCzbm()
						.equals(castOther.getCzbm())))
				&& ((this.getCzbmKj() == castOther.getCzbmKj()) || (this
						.getCzbmKj() != null
						&& castOther.getCzbmKj() != null && this.getCzbmKj()
						.equals(castOther.getCzbmKj())))
				&& ((this.getCzrq() == castOther.getCzrq()) || (this.getCzrq() != null
						&& castOther.getCzrq() != null && this.getCzrq()
						.equals(castOther.getCzrq())))
				&& ((this.getCzip() == castOther.getCzip()) || (this.getCzip() != null
						&& castOther.getCzip() != null && this.getCzip()
						.equals(castOther.getCzip())))
				&& ((this.getYxjl() == castOther.getYxjl()) || (this.getYxjl() != null
						&& castOther.getYxjl() != null && this.getYxjl()
						.equals(castOther.getYxjl())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getGpsId() == null ? 0 : this.getGpsId().hashCode());
		result = 37 * result
				+ (getJgmc() == null ? 0 : this.getJgmc().hashCode());
		result = 37 * result
				+ (getXxdz() == null ? 0 : this.getXxdz().hashCode());
		result = 37 * result
				+ (getGpsx() == null ? 0 : this.getGpsx().hashCode());
		result = 37 * result
				+ (getGpsy() == null ? 0 : this.getGpsy().hashCode());
		result = 37 * result + (getZt() == null ? 0 : this.getZt().hashCode());
		result = 37 * result
				+ (getCzr() == null ? 0 : this.getCzr().hashCode());
		result = 37 * result
				+ (getCzrxm() == null ? 0 : this.getCzrxm().hashCode());
		result = 37 * result
				+ (getCzbm() == null ? 0 : this.getCzbm().hashCode());
		result = 37 * result
				+ (getCzbmKj() == null ? 0 : this.getCzbmKj().hashCode());
		result = 37 * result
				+ (getCzrq() == null ? 0 : this.getCzrq().hashCode());
		result = 37 * result
				+ (getCzip() == null ? 0 : this.getCzip().hashCode());
		result = 37 * result
				+ (getYxjl() == null ? 0 : this.getYxjl().hashCode());
		return result;
	}

}