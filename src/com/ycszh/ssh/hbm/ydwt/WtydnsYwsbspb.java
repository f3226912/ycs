package com.ycszh.ssh.hbm.ydwt;

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * WtydnsYwsbspb entity. @author MyEclipse Persistence Tools
 */
public class WtydnsYwsbspb extends BaseObject {

	// Fields

	public Integer id;//申请ID
	public String hphm;  //号牌号码,加密
	public String hpzl;  //号牌种类,加密
	public String clsbdh;//车辆识别代号后4位,加密
	public String sfzmhm;//身份证明号码 加密
	public String syr;   //所有人 加密
	public String stjg;  //受托核发检验合格标志机构 字典
	public String dzzplb;//电子照片 0-行驶证 1-登记证书 电子照片
	public String yjSjrxm;//邮寄—收件人姓名
	public String yjTddz; //邮寄—投递地址
	public String yjYzbm; //邮寄—邮政编码
	public String yjLxdh; //邮寄—联系电话
	public Date lrsj;     //录入时间
	public String lrip;   //录入ip
	public String sbzt;   //业务申办状态 0 初始状态 1 初审 2 复核  3 通知书已接收 4快递已寄出  CT-车管申办失败  YT-邮政申办失败
	public String sbztTbly;//业务申办退办原因   退办才需要填理由
	public String sbztKdbh;//快递编号 加密
	public String pch;     //批次号
	public String cgYhdm;  //车管-用户代码
	public String cgYhxm;  //车管-用户姓名
	public String cgYhsj;  //车管-用户时间
	public String cgIp;    //车管-ip
	public String yzYhdm;  //邮政-用户代码
	public String yzYhxm;  //邮政-用户姓名
	public String yzYhsj;  //邮政-用户时间
	public String yzIp;    //邮政-ip
	public String synFlag; //同步标志，外网UC，内网UW
	public String tranFlag;//同步标志 1已同步
	public Date tranDate;  //同步时间
	public String clsyq;   //车辆所属权（0个人，1单位)
	public String lrzh;    //录入账户
	// Constructors

	/** default constructor */
	public WtydnsYwsbspb() {
	}

	/** full constructor */
	public WtydnsYwsbspb(Integer id,String hphm, String hpzl, String clsbdh,
			String sfzmhm, String syr, String stjg, String dzzplb,
			String yjSjrxm, String yjTddz, String yjYzbm, String yjLxdh,
			Date lrsj, String lrip, String sbzt, String sbztTbly,
			String sbztKdbh, String pch, String cgYhdm, String cgYhxm,
			String cgYhsj, String cgIp, String yzYhdm, String yzYhxm,
			String yzYhsj, String yzIp, String synFlag, String tranFlag,
			Date tranDate, String clsyq, String lrzh) {
		this.id=id;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.clsbdh = clsbdh;
		this.sfzmhm = sfzmhm;
		this.syr = syr;
		this.stjg = stjg;
		this.dzzplb = dzzplb;
		this.yjSjrxm = yjSjrxm;
		this.yjTddz = yjTddz;
		this.yjYzbm = yjYzbm;
		this.yjLxdh = yjLxdh;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.sbzt = sbzt;
		this.sbztTbly = sbztTbly;
		this.sbztKdbh = sbztKdbh;
		this.pch = pch;
		this.cgYhdm = cgYhdm;
		this.cgYhxm = cgYhxm;
		this.cgYhsj = cgYhsj;
		this.cgIp = cgIp;
		this.yzYhdm = yzYhdm;
		this.yzYhxm = yzYhxm;
		this.yzYhsj = yzYhsj;
		this.yzIp = yzIp;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.clsyq = clsyq;
		this.lrzh = lrzh;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getClsbdh() {
		return this.clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSyr() {
		return this.syr;
	}

	public void setSyr(String syr) {
		this.syr = syr;
	}

	public String getStjg() {
		return this.stjg;
	}

	public void setStjg(String stjg) {
		this.stjg = stjg;
	}

	public String getDzzplb() {
		return this.dzzplb;
	}

	public void setDzzplb(String dzzplb) {
		this.dzzplb = dzzplb;
	}

	public String getYjSjrxm() {
		return this.yjSjrxm;
	}

	public void setYjSjrxm(String yjSjrxm) {
		this.yjSjrxm = yjSjrxm;
	}

	public String getYjTddz() {
		return this.yjTddz;
	}

	public void setYjTddz(String yjTddz) {
		this.yjTddz = yjTddz;
	}

	public String getYjYzbm() {
		return this.yjYzbm;
	}

	public void setYjYzbm(String yjYzbm) {
		this.yjYzbm = yjYzbm;
	}

	public String getYjLxdh() {
		return this.yjLxdh;
	}

	public void setYjLxdh(String yjLxdh) {
		this.yjLxdh = yjLxdh;
	}

	public Date getLrsj() {
		return this.lrsj;
	}

	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}

	public String getLrip() {
		return this.lrip;
	}

	public void setLrip(String lrip) {
		this.lrip = lrip;
	}

	public String getSbzt() {
		return this.sbzt;
	}

	public void setSbzt(String sbzt) {
		this.sbzt = sbzt;
	}

	public String getSbztTbly() {
		return this.sbztTbly;
	}

	public void setSbztTbly(String sbztTbly) {
		this.sbztTbly = sbztTbly;
	}

	public String getSbztKdbh() {
		return this.sbztKdbh;
	}

	public void setSbztKdbh(String sbztKdbh) {
		this.sbztKdbh = sbztKdbh;
	}

	public String getPch() {
		return this.pch;
	}

	public void setPch(String pch) {
		this.pch = pch;
	}

	public String getCgYhdm() {
		return this.cgYhdm;
	}

	public void setCgYhdm(String cgYhdm) {
		this.cgYhdm = cgYhdm;
	}

	public String getCgYhxm() {
		return this.cgYhxm;
	}

	public void setCgYhxm(String cgYhxm) {
		this.cgYhxm = cgYhxm;
	}

	public String getCgYhsj() {
		return this.cgYhsj;
	}

	public void setCgYhsj(String cgYhsj) {
		this.cgYhsj = cgYhsj;
	}

	public String getCgIp() {
		return this.cgIp;
	}

	public void setCgIp(String cgIp) {
		this.cgIp = cgIp;
	}

	public String getYzYhdm() {
		return this.yzYhdm;
	}

	public void setYzYhdm(String yzYhdm) {
		this.yzYhdm = yzYhdm;
	}

	public String getYzYhxm() {
		return this.yzYhxm;
	}

	public void setYzYhxm(String yzYhxm) {
		this.yzYhxm = yzYhxm;
	}

	public String getYzYhsj() {
		return this.yzYhsj;
	}

	public void setYzYhsj(String yzYhsj) {
		this.yzYhsj = yzYhsj;
	}

	public String getYzIp() {
		return this.yzIp;
	}

	public void setYzIp(String yzIp) {
		this.yzIp = yzIp;
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

	public String getClsyq() {
		return this.clsyq;
	}

	public void setClsyq(String clsyq) {
		this.clsyq = clsyq;
	}

	public String getLrzh() {
		return this.lrzh;
	}

	public void setLrzh(String lrzh) {
		this.lrzh = lrzh;
	}

}