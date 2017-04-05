package com.ycszh.ssh.hbm.blacklist;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DbjgSjzd entity. @author MyEclipse Persistence Tools
 */

public class DbjgSjzd extends BaseObject {

	// Fields
 
	public  String xh;    //序号
	public String dmz;   //字典值
	public String dmlb;  //字典类别
	public String dmms1; //代码描述1
	public String dmms2; //代码描述2
	public String bz;    //备注
	public String czr;   //操作人代码
	public String czrxm; //操作人姓名
	public String czbm;  //操作部门
	public String czbmKj;//操作部门(科级)
	public Date czrq;    //操作日期
	public String czip;  //操作IP
	public String czmac; //操作MAC

	// Constructors

	/** default constructor */
	public DbjgSjzd() {
	}

	/** full constructor */
	public DbjgSjzd(String dmz, String dmlb, String dmms1, String dmms2,
			String bz, String czr, String czrxm, String czbm, String czbmKj,
			Date czrq, String czip, String czmac) {
		this.dmz = dmz;
		this.dmlb = dmlb;
		this.dmms1 = dmms1;
		this.dmms2 = dmms2;
		this.bz = bz;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czbmKj = czbmKj;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getDmz() {
		return this.dmz;
	}

	public void setDmz(String dmz) {
		this.dmz = dmz;
	}

	public String getDmlb() {
		return this.dmlb;
	}

	public void setDmlb(String dmlb) {
		this.dmlb = dmlb;
	}

	public String getDmms1() {
		return this.dmms1;
	}

	public void setDmms1(String dmms1) {
		this.dmms1 = dmms1;
	}

	public String getDmms2() {
		return this.dmms2;
	}

	public void setDmms2(String dmms2) {
		this.dmms2 = dmms2;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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

	public String getCzmac() {
		return this.czmac;
	}

	public void setCzmac(String czmac) {
		this.czmac = czmac;
	}

}