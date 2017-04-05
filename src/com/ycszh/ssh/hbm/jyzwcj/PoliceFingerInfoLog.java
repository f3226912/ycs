package com.ycszh.ssh.hbm.jyzwcj;

import java.util.Date;

/**
 * PoliceFingerInfoLogId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PoliceFingerInfoLog implements java.io.Serializable {

	// Fields

	private String jh;		//警号
	private String xm;		//姓名
	private String bmbh;	//部门编号
	private String finger1;	//指纹1
	private String finger2;	//指纹2
	private String finger3;	//指纹3
	private String finger4;	//指纹4
	private String zt;		//采集状态0：为采集；1：已采集
	private String czr;		//操作人编号
	private String czrXm;	//操作人姓名
	private String czrBm;	//操作人部门
	private String czrKjbm;	//操作人科级部门
	private Date czsj;		//操作时间
	private String czip;	//操作IP
	private String czmac;	//操作MAC
	private String cznr;	//操作内容

	// Constructors

	/** default constructor */
	public PoliceFingerInfoLog() {
	}

	/** full constructor */
	public PoliceFingerInfoLog(String jh, String xm, String bmbh,
			String finger1, String finger2, String finger3, String finger4,
			String zt, String czr, String czrXm, String czrBm, String czrKjbm,
			Date czsj, String czip, String czmac, String cznr) {
		this.jh = jh;
		this.xm = xm;
		this.bmbh = bmbh;
		this.finger1 = finger1;
		this.finger2 = finger2;
		this.finger3 = finger3;
		this.finger4 = finger4;
		this.zt = zt;
		this.czr = czr;
		this.czrXm = czrXm;
		this.czrBm = czrBm;
		this.czrKjbm = czrKjbm;
		this.czsj = czsj;
		this.czip = czip;
		this.czmac = czmac;
		this.cznr = cznr;
	}

	// Property accessors

	public String getJh() {
		return this.jh;
	}

	public void setJh(String jh) {
		this.jh = jh;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBmbh() {
		return this.bmbh;
	}

	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}

	public String getFinger1() {
		return this.finger1;
	}

	public void setFinger1(String finger1) {
		this.finger1 = finger1;
	}

	public String getFinger2() {
		return this.finger2;
	}

	public void setFinger2(String finger2) {
		this.finger2 = finger2;
	}

	public String getFinger3() {
		return this.finger3;
	}

	public void setFinger3(String finger3) {
		this.finger3 = finger3;
	}

	public String getFinger4() {
		return this.finger4;
	}

	public void setFinger4(String finger4) {
		this.finger4 = finger4;
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

	public String getCzrXm() {
		return this.czrXm;
	}

	public void setCzrXm(String czrXm) {
		this.czrXm = czrXm;
	}

	public String getCzrBm() {
		return this.czrBm;
	}

	public void setCzrBm(String czrBm) {
		this.czrBm = czrBm;
	}

	public String getCzrKjbm() {
		return this.czrKjbm;
	}

	public void setCzrKjbm(String czrKjbm) {
		this.czrKjbm = czrKjbm;
	}

	public Date getCzsj() {
		return this.czsj;
	}

	public void setCzsj(Date czsj) {
		this.czsj = czsj;
	}

	public String getCzip() {
		return this.czip;
	}

	public void setCzip(String czip) {
		this.czip = czip;
	}

	public String getCzmac() {
		return this.czmac;
	}

	public void setCzmac(String czmac) {
		this.czmac = czmac;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PoliceFingerInfoLog))
			return false;
		PoliceFingerInfoLog castOther = (PoliceFingerInfoLog) other;

		return ((this.getJh() == castOther.getJh()) || (this.getJh() != null
				&& castOther.getJh() != null && this.getJh().equals(
				castOther.getJh())))
				&& ((this.getXm() == castOther.getXm()) || (this.getXm() != null
						&& castOther.getXm() != null && this.getXm().equals(
						castOther.getXm())))
				&& ((this.getBmbh() == castOther.getBmbh()) || (this.getBmbh() != null
						&& castOther.getBmbh() != null && this.getBmbh()
						.equals(castOther.getBmbh())))
				&& ((this.getFinger1() == castOther.getFinger1()) || (this
						.getFinger1() != null
						&& castOther.getFinger1() != null && this.getFinger1()
						.equals(castOther.getFinger1())))
				&& ((this.getFinger2() == castOther.getFinger2()) || (this
						.getFinger2() != null
						&& castOther.getFinger2() != null && this.getFinger2()
						.equals(castOther.getFinger2())))
				&& ((this.getFinger3() == castOther.getFinger3()) || (this
						.getFinger3() != null
						&& castOther.getFinger3() != null && this.getFinger3()
						.equals(castOther.getFinger3())))
				&& ((this.getFinger4() == castOther.getFinger4()) || (this
						.getFinger4() != null
						&& castOther.getFinger4() != null && this.getFinger4()
						.equals(castOther.getFinger4())))
				&& ((this.getZt() == castOther.getZt()) || (this.getZt() != null
						&& castOther.getZt() != null && this.getZt().equals(
						castOther.getZt())))
				&& ((this.getCzr() == castOther.getCzr()) || (this.getCzr() != null
						&& castOther.getCzr() != null && this.getCzr().equals(
						castOther.getCzr())))
				&& ((this.getCzrXm() == castOther.getCzrXm()) || (this
						.getCzrXm() != null
						&& castOther.getCzrXm() != null && this.getCzrXm()
						.equals(castOther.getCzrXm())))
				&& ((this.getCzrBm() == castOther.getCzrBm()) || (this
						.getCzrBm() != null
						&& castOther.getCzrBm() != null && this.getCzrBm()
						.equals(castOther.getCzrBm())))
				&& ((this.getCzrKjbm() == castOther.getCzrKjbm()) || (this
						.getCzrKjbm() != null
						&& castOther.getCzrKjbm() != null && this.getCzrKjbm()
						.equals(castOther.getCzrKjbm())))
				&& ((this.getCzsj() == castOther.getCzsj()) || (this.getCzsj() != null
						&& castOther.getCzsj() != null && this.getCzsj()
						.equals(castOther.getCzsj())))
				&& ((this.getCzip() == castOther.getCzip()) || (this.getCzip() != null
						&& castOther.getCzip() != null && this.getCzip()
						.equals(castOther.getCzip())))
				&& ((this.getCzmac() == castOther.getCzmac()) || (this
						.getCzmac() != null
						&& castOther.getCzmac() != null && this.getCzmac()
						.equals(castOther.getCzmac())))
				&& ((this.getCznr() == castOther.getCznr()) || (this.getCznr() != null
						&& castOther.getCznr() != null && this.getCznr()
						.equals(castOther.getCznr())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getJh() == null ? 0 : this.getJh().hashCode());
		result = 37 * result + (getXm() == null ? 0 : this.getXm().hashCode());
		result = 37 * result
				+ (getBmbh() == null ? 0 : this.getBmbh().hashCode());
		result = 37 * result
				+ (getFinger1() == null ? 0 : this.getFinger1().hashCode());
		result = 37 * result
				+ (getFinger2() == null ? 0 : this.getFinger2().hashCode());
		result = 37 * result
				+ (getFinger3() == null ? 0 : this.getFinger3().hashCode());
		result = 37 * result
				+ (getFinger4() == null ? 0 : this.getFinger4().hashCode());
		result = 37 * result + (getZt() == null ? 0 : this.getZt().hashCode());
		result = 37 * result
				+ (getCzr() == null ? 0 : this.getCzr().hashCode());
		result = 37 * result
				+ (getCzrXm() == null ? 0 : this.getCzrXm().hashCode());
		result = 37 * result
				+ (getCzrBm() == null ? 0 : this.getCzrBm().hashCode());
		result = 37 * result
				+ (getCzrKjbm() == null ? 0 : this.getCzrKjbm().hashCode());
		result = 37 * result
				+ (getCzsj() == null ? 0 : this.getCzsj().hashCode());
		result = 37 * result
				+ (getCzip() == null ? 0 : this.getCzip().hashCode());
		result = 37 * result
				+ (getCzmac() == null ? 0 : this.getCzmac().hashCode());
		result = 37 * result
				+ (getCznr() == null ? 0 : this.getCznr().hashCode());
		return result;
	}

}