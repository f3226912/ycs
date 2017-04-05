package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * PdasmycPoliceSettingLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PdasmycPoliceSettingLog extends BaseObject {

	// Fields

	private String id;
	private Date czsj;
	private String czip;
	private String czmac;
	private String cznr;
	private String szrbm2;
	private String szrmac;
	private String szrip;
	private String policeCode;
	private String policeName;
	private String policeDepart;
	private String policeDepartKj;
	private String kzCllx;
	private String kzSyxz;
	private String kz01;
	private String czrxm;
	private String czr;
	private String kz02;
	private String kz03;
	private String kz04;
	private String kz05;
	private String szr;
	private String szrxm;
	private Date szsj;
	private String szrbm;
	private String czrbm;

	// Constructors

	/** default constructor */
	public PdasmycPoliceSettingLog() {
	}

	/** minimal constructor */
	public PdasmycPoliceSettingLog(String id) {
		this.id = id;
	}

	/** full constructor */
	public PdasmycPoliceSettingLog(String id, Date czsj, String czip,
			String czmac, String cznr, String szrbm2, String szrmac,
			String szrip, String policeCode, String policeName,
			String policeDepart, String policeDepartKj, String kzCllx,
			String kzSyxz, String kz01, String czrxm, String czr, String kz02,
			String kz03, String kz04, String kz05, String szr, String szrxm,
			Date szsj, String szrbm, String czrbm) {
		this.id = id;
		this.czsj = czsj;
		this.czip = czip;
		this.czmac = czmac;
		this.cznr = cznr;
		this.szrbm2 = szrbm2;
		this.szrmac = szrmac;
		this.szrip = szrip;
		this.policeCode = policeCode;
		this.policeName = policeName;
		this.policeDepart = policeDepart;
		this.policeDepartKj = policeDepartKj;
		this.kzCllx = kzCllx;
		this.kzSyxz = kzSyxz;
		this.kz01 = kz01;
		this.czrxm = czrxm;
		this.czr = czr;
		this.kz02 = kz02;
		this.kz03 = kz03;
		this.kz04 = kz04;
		this.kz05 = kz05;
		this.szr = szr;
		this.szrxm = szrxm;
		this.szsj = szsj;
		this.szrbm = szrbm;
		this.czrbm = czrbm;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSzrbm2() {
		return this.szrbm2;
	}

	public void setSzrbm2(String szrbm2) {
		this.szrbm2 = szrbm2;
	}

	public String getSzrmac() {
		return this.szrmac;
	}

	public void setSzrmac(String szrmac) {
		this.szrmac = szrmac;
	}

	public String getSzrip() {
		return this.szrip;
	}

	public void setSzrip(String szrip) {
		this.szrip = szrip;
	}

	public String getPoliceCode() {
		return this.policeCode;
	}

	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}

	public String getPoliceName() {
		return this.policeName;
	}

	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}

	public String getPoliceDepart() {
		return this.policeDepart;
	}

	public void setPoliceDepart(String policeDepart) {
		this.policeDepart = policeDepart;
	}

	public String getPoliceDepartKj() {
		return this.policeDepartKj;
	}

	public void setPoliceDepartKj(String policeDepartKj) {
		this.policeDepartKj = policeDepartKj;
	}

	public String getKzCllx() {
		return this.kzCllx;
	}

	public void setKzCllx(String kzCllx) {
		this.kzCllx = kzCllx;
	}

	public String getKzSyxz() {
		return this.kzSyxz;
	}

	public void setKzSyxz(String kzSyxz) {
		this.kzSyxz = kzSyxz;
	}

	public String getKz01() {
		return this.kz01;
	}

	public void setKz01(String kz01) {
		this.kz01 = kz01;
	}

	public String getCzrxm() {
		return this.czrxm;
	}

	public void setCzrxm(String czrxm) {
		this.czrxm = czrxm;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getKz02() {
		return this.kz02;
	}

	public void setKz02(String kz02) {
		this.kz02 = kz02;
	}

	public String getKz03() {
		return this.kz03;
	}

	public void setKz03(String kz03) {
		this.kz03 = kz03;
	}

	public String getKz04() {
		return this.kz04;
	}

	public void setKz04(String kz04) {
		this.kz04 = kz04;
	}

	public String getKz05() {
		return this.kz05;
	}

	public void setKz05(String kz05) {
		this.kz05 = kz05;
	}

	public String getSzr() {
		return this.szr;
	}

	public void setSzr(String szr) {
		this.szr = szr;
	}

	public String getSzrxm() {
		return this.szrxm;
	}

	public void setSzrxm(String szrxm) {
		this.szrxm = szrxm;
	}

	public Date getSzsj() {
		return this.szsj;
	}

	public void setSzsj(Date szsj) {
		this.szsj = szsj;
	}

	public String getSzrbm() {
		return this.szrbm;
	}

	public void setSzrbm(String szrbm) {
		this.szrbm = szrbm;
	}

	public String getCzrbm() {
		return this.czrbm;
	}

	public void setCzrbm(String czrbm) {
		this.czrbm = czrbm;
	}

}