package com.ycszh.ssh.hbm.dydj;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * DydjYhUser entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DydjYhUser extends BaseObject {

	// Fields

	private Integer id;
	private String yzYhdm;//银行代码
	private String yhxm;//银行名称
	private String ymmm;//用户密码 加密
	private String zjjgdmz;//组织结构代码证	
	private String frmc;//法人名称
	private String lxrmc;//联系人名称
	private String lxdh;//联系电话
	private String lxdz;//联系地址
	private String photoimg;//银行电子图鉴
	private String mac1;//mac地址1
	private String mac2;//mac地址2
	private String mac3;//mac地址3
	private String bz1;//	
	private String bz2;//
	private String bz3;//
	private String bz4;//
	private String bz5;//
	private String bz6;//
	private String bz7;//
	private String bz8;//
	private String bz9;//
	private String bz10;//
	private String lrr3;//录入人
	private String lrr2;//录入人姓名
	private String lrbmid;//部门编码id
	private String lrbmmc;//部门编码名称
	private String lrip;//录入ip
	private String status;//0:冻结用户；1：活动用户
	private String synFlag;//同步标志，外网UC，内网UW
	private String tranFlag;//同步标志 1已同步
	private Date tranDate;//同步时间
    private Date yxqz;    //有效期止
	// Constructors

	/** default constructor */
	public DydjYhUser() {
	}

	/** full constructor */
	public DydjYhUser(String yzYhdm, String yhxm, String ymmm, String zjjgdmz,
			String frmc, String lxrmc, String lxdh, String lxdz,String photoimg, String mac1,
			String mac2, String mac3, String bz1, String bz2, String bz3,
			String bz4, String bz5, String bz6, String bz7, String bz8,
			String bz9, String bz10, String lrr3, String lrr2, String lrbmid,
			String lrbmmc, String lrip, String status, String synFlag,
			String tranFlag, Date tranDate,Date yxqz) {
		this.yzYhdm = yzYhdm;
		this.yhxm = yhxm;
		this.ymmm = ymmm;
		this.zjjgdmz = zjjgdmz;
		this.frmc = frmc;
		this.lxrmc = lxrmc;
		this.lxdh = lxdh;
		this.lxdz = lxdz;
		this.photoimg=photoimg;
		this.mac1 = mac1;
		this.mac2 = mac2;
		this.mac3 = mac3;
		this.bz1 = bz1;
		this.bz2 = bz2;
		this.bz3 = bz3;
		this.bz4 = bz4;
		this.bz5 = bz5;
		this.bz6 = bz6;
		this.bz7 = bz7;
		this.bz8 = bz8;
		this.bz9 = bz9;
		this.bz10 = bz10;
		this.lrr3 = lrr3;
		this.lrr2 = lrr2;
		this.lrbmid = lrbmid;
		this.lrbmmc = lrbmmc;
		this.lrip = lrip;
		this.status = status;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.yxqz =yxqz;
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

	public String getZjjgdmz() {
		return this.zjjgdmz;
	}

	public void setZjjgdmz(String zjjgdmz) {
		this.zjjgdmz = zjjgdmz;
	}

	public String getFrmc() {
		return this.frmc;
	}

	public void setFrmc(String frmc) {
		this.frmc = frmc;
	}

	public String getLxrmc() {
		return this.lxrmc;
	}

	public void setLxrmc(String lxrmc) {
		this.lxrmc = lxrmc;
	}

	public String getLxdh() {
		return this.lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getLxdz() {
		return this.lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	
	public String getPhotoimg() {
		return photoimg;
	}

	public void setPhotoimg(String photoimg) {
		this.photoimg = photoimg;
	}

	public String getMac1() {
		return this.mac1;
	}

	public void setMac1(String mac1) {
		this.mac1 = mac1;
	}

	public String getMac2() {
		return this.mac2;
	}

	public void setMac2(String mac2) {
		this.mac2 = mac2;
	}

	public String getMac3() {
		return this.mac3;
	}

	public void setMac3(String mac3) {
		this.mac3 = mac3;
	}

	public String getBz1() {
		return this.bz1;
	}

	public void setBz1(String bz1) {
		this.bz1 = bz1;
	}

	public String getBz2() {
		return this.bz2;
	}

	public void setBz2(String bz2) {
		this.bz2 = bz2;
	}

	public String getBz3() {
		return this.bz3;
	}

	public void setBz3(String bz3) {
		this.bz3 = bz3;
	}

	public String getBz4() {
		return this.bz4;
	}

	public void setBz4(String bz4) {
		this.bz4 = bz4;
	}

	public String getBz5() {
		return this.bz5;
	}

	public void setBz5(String bz5) {
		this.bz5 = bz5;
	}

	public String getBz6() {
		return this.bz6;
	}

	public void setBz6(String bz6) {
		this.bz6 = bz6;
	}

	public String getBz7() {
		return this.bz7;
	}

	public void setBz7(String bz7) {
		this.bz7 = bz7;
	}

	public String getBz8() {
		return this.bz8;
	}

	public void setBz8(String bz8) {
		this.bz8 = bz8;
	}

	public String getBz9() {
		return this.bz9;
	}

	public void setBz9(String bz9) {
		this.bz9 = bz9;
	}

	public String getBz10() {
		return this.bz10;
	}

	public void setBz10(String bz10) {
		this.bz10 = bz10;
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

	public Date getYxqz() {
		return yxqz;
	}

	public void setYxqz(Date yxqz) {
		this.yxqz = yxqz;
	}
    
}