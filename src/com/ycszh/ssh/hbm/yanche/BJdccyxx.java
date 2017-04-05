package com.ycszh.ssh.hbm.yanche;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * BJdccyxx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BJdccyxx extends BaseObject {

	// Fields

	public String lsh;
	public String cybhgxm;
	public String wjbhgxm;
	public BigDecimal wjcs;
	public String cyjg;
	public String jdcxh;
	public Date gxsj;
	public String tyzxh;
	public String hphm;
	public String hpzl;

	// Constructors

	/** default constructor */
	public BJdccyxx() {
	}

	/** minimal constructor */
	public BJdccyxx(String lsh, BigDecimal wjcs, String cyjg) {
		this.lsh = lsh;
		this.wjcs = wjcs;
		this.cyjg = cyjg;
	}

	/** full constructor */
	public BJdccyxx(String lsh, String cybhgxm, String wjbhgxm,
			BigDecimal wjcs, String cyjg, String jdcxh, Date gxsj,
			String tyzxh, String hphm, String hpzl) {
		this.lsh = lsh;
		this.cybhgxm = cybhgxm;
		this.wjbhgxm = wjbhgxm;
		this.wjcs = wjcs;
		this.cyjg = cyjg;
		this.jdcxh = jdcxh;
		this.gxsj = gxsj;
		this.tyzxh = tyzxh;
		this.hphm = hphm;
		this.hpzl = hpzl;
	}

	// Property accessors

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getCybhgxm() {
		return this.cybhgxm;
	}

	public void setCybhgxm(String cybhgxm) {
		this.cybhgxm = cybhgxm;
	}

	public String getWjbhgxm() {
		return this.wjbhgxm;
	}

	public void setWjbhgxm(String wjbhgxm) {
		this.wjbhgxm = wjbhgxm;
	}

	public BigDecimal getWjcs() {
		return this.wjcs;
	}

	public void setWjcs(BigDecimal wjcs) {
		this.wjcs = wjcs;
	}

	public String getCyjg() {
		return this.cyjg;
	}

	public void setCyjg(String cyjg) {
		this.cyjg = cyjg;
	}

	public String getJdcxh() {
		return this.jdcxh;
	}

	public void setJdcxh(String jdcxh) {
		this.jdcxh = jdcxh;
	}

	public Date getGxsj() {
		return this.gxsj;
	}

	public void setGxsj(Date gxsj) {
		this.gxsj = gxsj;
	}

	public String getTyzxh() {
		return this.tyzxh;
	}

	public void setTyzxh(String tyzxh) {
		this.tyzxh = tyzxh;
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

}