package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * PdasmycPoliceSetting entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class PdasmycPoliceSetting extends BaseObject {

	// Fields

	public String id;
	public String policeCode;
	public String policeName;
	public String policeDepart;
	public String policeDepartKj;
	public String kzCllx;
	public String kzSyxz;
	public String kz01;
	public String kz02;
	public String kz03;
	public String kz04;
	public String kz05;
	public String szr;
	public String szrxm;
	public Date szsj;
	public String szrbm;
	public String szrbm2;
	public String szrip;
	public String szrmac;

	// Constructors

	/** default constructor */
	public PdasmycPoliceSetting() {
	}

	/** minimal constructor */
	public PdasmycPoliceSetting(String id) {
		this.id = id;
	}

	/** full constructor */
	public PdasmycPoliceSetting(String id, String policeCode,
			String policeName, String policeDepart, String policeDepartKj,
			String kzCllx, String kzSyxz, String kz01, String kz02,
			String kz03, String kz04, String kz05, String szr, String szrxm,
			Date szsj, String szrbm, String szrbm2, String szrip, String szrmac) {
		this.id = id;
		this.policeCode = policeCode;
		this.policeName = policeName;
		this.policeDepart = policeDepart;
		this.policeDepartKj = policeDepartKj;
		this.kzCllx = kzCllx;
		this.kzSyxz = kzSyxz;
		this.kz01 = kz01;
		this.kz02 = kz02;
		this.kz03 = kz03;
		this.kz04 = kz04;
		this.kz05 = kz05;
		this.szr = szr;
		this.szrxm = szrxm;
		this.szsj = szsj;
		this.szrbm = szrbm;
		this.szrbm2 = szrbm2;
		this.szrip = szrip;
		this.szrmac = szrmac;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSzrbm2() {
		return this.szrbm2;
	}

	public void setSzrbm2(String szrbm2) {
		this.szrbm2 = szrbm2;
	}

	public String getSzrip() {
		return this.szrip;
	}

	public void setSzrip(String szrip) {
		this.szrip = szrip;
	}

	public String getSzrmac() {
		return this.szrmac;
	}

	public void setSzrmac(String szrmac) {
		this.szrmac = szrmac;
	}

}