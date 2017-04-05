package com.ycszh.ssh.hbm.dydj;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * DydjUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DydjUser extends BaseObject {
	// Fields

	private Integer id;
	private String yzYhdm; //用户代码
	private String yhxm;   //用户姓名
	private String ymmm;   //用户密码（加密）
	private String zhlx;
	private String cgQx01;
	private String cgQx02;
	private String cgQx03;
	private String cgQx04;
	private String cgQx05;
	private String cgQx06;
	private String cgQx07;
	private String cgQx08;
	private String cgQx09;
	private String cgQx10;
	private String yzQx01;
	private String yzQx02;
	private String yzQx03;
	private String yzQx04;
	private String yzQx05;
	private String yzQx06;
	private String yzQx07;
	private String yzQx08;
	private String yzQx09;
	private String yzQx10;//所属车管部门（用户部门，一个车管部门对应一个车管用户和邮政用户）
	private String yzCgJjm;//邮政、车管交接码
	private String lrr3;
	private String lrr2;
	private String lrbmid;
	private String lrbmmc;
	private String lrip;
	private String status;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;

	// Constructors

	/** default constructor */
	public DydjUser() {
	}

	/** full constructor */
	public DydjUser(String yzYhdm, String yhxm, String ymmm, String zhlx,
			String cgQx01, String cgQx02, String cgQx03, String cgQx04,
			String cgQx05, String cgQx06, String cgQx07, String cgQx08,
			String cgQx09, String cgQx10, String yzQx01, String yzQx02,
			String yzQx03, String yzQx04, String yzQx05, String yzQx06,
			String yzQx07, String yzQx08, String yzQx09, String yzQx10,
			String yzCgJjm, String lrr3, String lrr2, String lrbmid,
			String lrbmmc, String lrip, String status, String synFlag,
			String tranFlag, Date tranDate) {
		this.yzYhdm = yzYhdm;
		this.yhxm = yhxm;
		this.ymmm = ymmm;
		this.zhlx = zhlx;
		this.cgQx01 = cgQx01;
		this.cgQx02 = cgQx02;
		this.cgQx03 = cgQx03;
		this.cgQx04 = cgQx04;
		this.cgQx05 = cgQx05;
		this.cgQx06 = cgQx06;
		this.cgQx07 = cgQx07;
		this.cgQx08 = cgQx08;
		this.cgQx09 = cgQx09;
		this.cgQx10 = cgQx10;
		this.yzQx01 = yzQx01;
		this.yzQx02 = yzQx02;
		this.yzQx03 = yzQx03;
		this.yzQx04 = yzQx04;
		this.yzQx05 = yzQx05;
		this.yzQx06 = yzQx06;
		this.yzQx07 = yzQx07;
		this.yzQx08 = yzQx08;
		this.yzQx09 = yzQx09;
		this.yzQx10 = yzQx10;
		this.yzCgJjm = yzCgJjm;
		this.lrr3 = lrr3;
		this.lrr2 = lrr2;
		this.lrbmid = lrbmid;
		this.lrbmmc = lrbmmc;
		this.lrip = lrip;
		this.status = status;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYzYhdm() {
		return this.yzYhdm;
	}

	public void setYzYhdm(String yzYhdm) {
		this.yzYhdm = yzYhdm;
	}

	public String getYhxm() {
		return this.yhxm;
	}

	public void setYhxm(String yhxm) {
		this.yhxm = yhxm;
	}

	public String getYmmm() {
		return this.ymmm;
	}

	public void setYmmm(String ymmm) {
		this.ymmm = ymmm;
	}

	public String getZhlx() {
		return this.zhlx;
	}

	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}

	public String getCgQx01() {
		return this.cgQx01;
	}

	public void setCgQx01(String cgQx01) {
		this.cgQx01 = cgQx01;
	}

	public String getCgQx02() {
		return this.cgQx02;
	}

	public void setCgQx02(String cgQx02) {
		this.cgQx02 = cgQx02;
	}

	public String getCgQx03() {
		return this.cgQx03;
	}

	public void setCgQx03(String cgQx03) {
		this.cgQx03 = cgQx03;
	}

	public String getCgQx04() {
		return this.cgQx04;
	}

	public void setCgQx04(String cgQx04) {
		this.cgQx04 = cgQx04;
	}

	public String getCgQx05() {
		return this.cgQx05;
	}

	public void setCgQx05(String cgQx05) {
		this.cgQx05 = cgQx05;
	}

	public String getCgQx06() {
		return this.cgQx06;
	}

	public void setCgQx06(String cgQx06) {
		this.cgQx06 = cgQx06;
	}

	public String getCgQx07() {
		return this.cgQx07;
	}

	public void setCgQx07(String cgQx07) {
		this.cgQx07 = cgQx07;
	}

	public String getCgQx08() {
		return this.cgQx08;
	}

	public void setCgQx08(String cgQx08) {
		this.cgQx08 = cgQx08;
	}

	public String getCgQx09() {
		return this.cgQx09;
	}

	public void setCgQx09(String cgQx09) {
		this.cgQx09 = cgQx09;
	}

	public String getCgQx10() {
		return this.cgQx10;
	}

	public void setCgQx10(String cgQx10) {
		this.cgQx10 = cgQx10;
	}

	public String getYzQx01() {
		return this.yzQx01;
	}

	public void setYzQx01(String yzQx01) {
		this.yzQx01 = yzQx01;
	}

	public String getYzQx02() {
		return this.yzQx02;
	}

	public void setYzQx02(String yzQx02) {
		this.yzQx02 = yzQx02;
	}

	public String getYzQx03() {
		return this.yzQx03;
	}

	public void setYzQx03(String yzQx03) {
		this.yzQx03 = yzQx03;
	}

	public String getYzQx04() {
		return this.yzQx04;
	}

	public void setYzQx04(String yzQx04) {
		this.yzQx04 = yzQx04;
	}

	public String getYzQx05() {
		return this.yzQx05;
	}

	public void setYzQx05(String yzQx05) {
		this.yzQx05 = yzQx05;
	}

	public String getYzQx06() {
		return this.yzQx06;
	}

	public void setYzQx06(String yzQx06) {
		this.yzQx06 = yzQx06;
	}

	public String getYzQx07() {
		return this.yzQx07;
	}

	public void setYzQx07(String yzQx07) {
		this.yzQx07 = yzQx07;
	}

	public String getYzQx08() {
		return this.yzQx08;
	}

	public void setYzQx08(String yzQx08) {
		this.yzQx08 = yzQx08;
	}

	public String getYzQx09() {
		return this.yzQx09;
	}

	public void setYzQx09(String yzQx09) {
		this.yzQx09 = yzQx09;
	}

	public String getYzQx10() {
		return this.yzQx10;
	}

	public void setYzQx10(String yzQx10) {
		this.yzQx10 = yzQx10;
	}

	public String getYzCgJjm() {
		return this.yzCgJjm;
	}

	public void setYzCgJjm(String yzCgJjm) {
		this.yzCgJjm = yzCgJjm;
	}

	public String getLrr3() {
		return this.lrr3;
	}

	public void setLrr3(String lrr3) {
		this.lrr3 = lrr3;
	}

	public String getLrr2() {
		return this.lrr2;
	}

	public void setLrr2(String lrr2) {
		this.lrr2 = lrr2;
	}

	public String getLrbmid() {
		return this.lrbmid;
	}

	public void setLrbmid(String lrbmid) {
		this.lrbmid = lrbmid;
	}

	public String getLrbmmc() {
		return this.lrbmmc;
	}

	public void setLrbmmc(String lrbmmc) {
		this.lrbmmc = lrbmmc;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}