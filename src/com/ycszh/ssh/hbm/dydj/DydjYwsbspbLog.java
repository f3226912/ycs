package com.ycszh.ssh.hbm.dydj;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * DydjYwsbspbLogId entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DydjYwsbspbLog extends BaseObject {

	// Fields

	public Integer id;
	public String lsh;
	public String ywlx;
	public String sqlx;
	public String yhslLsh;
	public String hphm;
	public String hpzl;
	public String clsbdh;
	public String djzsbh;
	public String zhth;
	public String dyhth;
	public String xxZd1;
	public String xxZd2;
	public String xxZd3;
	public String yhZzjgZh;
	public String yhZzjgFrdb;
	public String yhZzjgYyzz;
	public String yhZzjgDwmc;
	public String yhZzjgDz;
	public String yhZzjgNjrq;
	public String yhZzjgNjyxq;
	public String yhSfzCardname;		//银行经办人_身份证姓名*
	public String yhSfzCardsex;			//银行经办人_性别 *
	public String yhSfzCardno;			//银行经办人_身份证号码*
	public String yhSfzCardaddress;		//银行经办人_身份证上地址
	public String yhSfzCardphotoId;  
	
	public String dyrSfzCardname;
	public String dyrSfzCardsex;
	public String dyrSfzCardno;
	public String dyrSfzCardaddress;
	public String dyrSfzCardphotoId;
	public String dyrZzjgZh;
	public String dyrZzjgFrdb;
	public String dyrZzjgYyzz;
	public String dyrZzjgDwmc;
	public String dyrZzjgDz;
	public String dyrZzjgNjrq;
	public String dyrZzjgNjyxq;
	public String yhCgPch;
	public Integer yhCgPchSl;
	public String qjQjrxm;
	public String qjTddz;
	public String qjYzbm;
	public String qjLxdh;
	public String yjSjrxm;
	public String yjTddz;
	public String yjYzbm;
	public String yjLxdh;
	public String yzqjrXm;
	public String yzqjrLxdh;
	public String sbSm;
	public String lrzh;
	public String lrxm;
	public Date lrsj;
	public String lrip;
	public String sbzt;
	public String sbztTbly;
	public String sbztKdbh;
	public String cgYhdm;
	public String cgYhxm;
	public String cgYhsj;
	public String cgIp;
	public String yzYhdm;
	public String yzYhxm;
	public String yzYhsj;
	public String yzIp;
	public String synFlag;
	public String tranFlag;
	public Date tranDate;
	public String ycsDeptId;
	public String ycsDeptName;
	public String yhSfzBz;			//银行经办人_未读取身份证原因
	public String dyrSfzBz;			//抵押人（或经办人）_未读取身份证原因
	public String yzyjpc;			//邮政一脚批次Y+序列号
	public String czlxfs;			//车主联系方式
	
	public String czr;
	public String czrxm;
	public String czrbm;
	public Date czsj;
	public String czip;
	public String czmac;
	public String cznr;

	// Constructors

	/** default constructor */
	public DydjYwsbspbLog() {
	}

	/** full constructor */
	public DydjYwsbspbLog(Integer id, String lsh, String ywlx,
			String sqlx, String yhslLsh, String hphm, String hpzl,
			String clsbdh, String djzsbh, String zhth, String dyhth,
			String xxZd1, String xxZd2, String xxZd3, String yhZzjgZh,
			String yhZzjgFrdb, String yhZzjgYyzz, String yhZzjgDwmc,
			String yhZzjgDz, String yhZzjgNjrq, String yhZzjgNjyxq,
			String dyrSfzCardname, String dyrSfzCardsex, String dyrSfzCardno,
			String dyrSfzCardaddress, String dyrSfzCardphotoId,
			String dyrZzjgZh, String dyrZzjgFrdb, String dyrZzjgYyzz,
			String dyrZzjgDwmc, String dyrZzjgDz, String dyrZzjgNjrq,
			String dyrZzjgNjyxq, String yhCgPch, Integer yhCgPchSl,
			String qjQjrxm, String qjTddz, String qjYzbm, String qjLxdh,
			String yjSjrxm, String yjTddz, String yjYzbm, String yjLxdh,
			String yzqjrXm, String yzqjrLxdh, String sbSm, String lrzh,
			String lrxm, Date lrsj, String lrip, String sbzt, String sbztTbly,
			String sbztKdbh, String cgYhdm, String cgYhxm, String cgYhsj,
			String cgIp, String yzYhdm, String yzYhxm, String yzYhsj,
			String yzIp, String synFlag, String tranFlag, Date tranDate,
			String czr, String czrxm, String czrbm, Date czsj, String czip,
			String czmac, String cznr) {
		this.id = id;
		this.lsh = lsh;
		this.ywlx = ywlx;
		this.sqlx = sqlx;
		this.yhslLsh = yhslLsh;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.clsbdh = clsbdh;
		this.djzsbh = djzsbh;
		this.zhth = zhth;
		this.dyhth = dyhth;
		this.xxZd1 = xxZd1;
		this.xxZd2 = xxZd2;
		this.xxZd3 = xxZd3;
		this.yhZzjgZh = yhZzjgZh;
		this.yhZzjgFrdb = yhZzjgFrdb;
		this.yhZzjgYyzz = yhZzjgYyzz;
		this.yhZzjgDwmc = yhZzjgDwmc;
		this.yhZzjgDz = yhZzjgDz;
		this.yhZzjgNjrq = yhZzjgNjrq;
		this.yhZzjgNjyxq = yhZzjgNjyxq;
		this.dyrSfzCardname = dyrSfzCardname;
		this.dyrSfzCardsex = dyrSfzCardsex;
		this.dyrSfzCardno = dyrSfzCardno;
		this.dyrSfzCardaddress = dyrSfzCardaddress;
		this.dyrSfzCardphotoId = dyrSfzCardphotoId;
		this.dyrZzjgZh = dyrZzjgZh;
		this.dyrZzjgFrdb = dyrZzjgFrdb;
		this.dyrZzjgYyzz = dyrZzjgYyzz;
		this.dyrZzjgDwmc = dyrZzjgDwmc;
		this.dyrZzjgDz = dyrZzjgDz;
		this.dyrZzjgNjrq = dyrZzjgNjrq;
		this.dyrZzjgNjyxq = dyrZzjgNjyxq;
		this.yhCgPch = yhCgPch;
		this.yhCgPchSl = yhCgPchSl;
		this.qjQjrxm = qjQjrxm;
		this.qjTddz = qjTddz;
		this.qjYzbm = qjYzbm;
		this.qjLxdh = qjLxdh;
		this.yjSjrxm = yjSjrxm;
		this.yjTddz = yjTddz;
		this.yjYzbm = yjYzbm;
		this.yjLxdh = yjLxdh;
		this.yzqjrXm = yzqjrXm;
		this.yzqjrLxdh = yzqjrLxdh;
		this.sbSm = sbSm;
		this.lrzh = lrzh;
		this.lrxm = lrxm;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.sbzt = sbzt;
		this.sbztTbly = sbztTbly;
		this.sbztKdbh = sbztKdbh;
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
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czsj = czsj;
		this.czip = czip;
		this.czmac = czmac;
		this.cznr = cznr;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getSqlx() {
		return this.sqlx;
	}

	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}

	public String getYhslLsh() {
		return this.yhslLsh;
	}

	public void setYhslLsh(String yhslLsh) {
		this.yhslLsh = yhslLsh;
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

	public String getDjzsbh() {
		return this.djzsbh;
	}

	public void setDjzsbh(String djzsbh) {
		this.djzsbh = djzsbh;
	}

	public String getZhth() {
		return this.zhth;
	}

	public void setZhth(String zhth) {
		this.zhth = zhth;
	}

	public String getDyhth() {
		return this.dyhth;
	}

	public void setDyhth(String dyhth) {
		this.dyhth = dyhth;
	}

	public String getXxZd1() {
		return this.xxZd1;
	}

	public void setXxZd1(String xxZd1) {
		this.xxZd1 = xxZd1;
	}

	public String getXxZd2() {
		return this.xxZd2;
	}

	public void setXxZd2(String xxZd2) {
		this.xxZd2 = xxZd2;
	}

	public String getXxZd3() {
		return this.xxZd3;
	}

	public void setXxZd3(String xxZd3) {
		this.xxZd3 = xxZd3;
	}

	public String getYhZzjgZh() {
		return this.yhZzjgZh;
	}

	public void setYhZzjgZh(String yhZzjgZh) {
		this.yhZzjgZh = yhZzjgZh;
	}

	public String getYhZzjgFrdb() {
		return this.yhZzjgFrdb;
	}

	public void setYhZzjgFrdb(String yhZzjgFrdb) {
		this.yhZzjgFrdb = yhZzjgFrdb;
	}

	public String getYhZzjgYyzz() {
		return this.yhZzjgYyzz;
	}

	public void setYhZzjgYyzz(String yhZzjgYyzz) {
		this.yhZzjgYyzz = yhZzjgYyzz;
	}

	public String getYhZzjgDwmc() {
		return this.yhZzjgDwmc;
	}

	public void setYhZzjgDwmc(String yhZzjgDwmc) {
		this.yhZzjgDwmc = yhZzjgDwmc;
	}

	public String getYhZzjgDz() {
		return this.yhZzjgDz;
	}

	public void setYhZzjgDz(String yhZzjgDz) {
		this.yhZzjgDz = yhZzjgDz;
	}

	public String getYhZzjgNjrq() {
		return this.yhZzjgNjrq;
	}

	public void setYhZzjgNjrq(String yhZzjgNjrq) {
		this.yhZzjgNjrq = yhZzjgNjrq;
	}

	public String getYhZzjgNjyxq() {
		return this.yhZzjgNjyxq;
	}

	public void setYhZzjgNjyxq(String yhZzjgNjyxq) {
		this.yhZzjgNjyxq = yhZzjgNjyxq;
	}

	public String getDyrSfzCardname() {
		return this.dyrSfzCardname;
	}

	public void setDyrSfzCardname(String dyrSfzCardname) {
		this.dyrSfzCardname = dyrSfzCardname;
	}

	public String getDyrSfzCardsex() {
		return this.dyrSfzCardsex;
	}

	public void setDyrSfzCardsex(String dyrSfzCardsex) {
		this.dyrSfzCardsex = dyrSfzCardsex;
	}

	public String getDyrSfzCardno() {
		return this.dyrSfzCardno;
	}

	public void setDyrSfzCardno(String dyrSfzCardno) {
		this.dyrSfzCardno = dyrSfzCardno;
	}

	public String getDyrSfzCardaddress() {
		return this.dyrSfzCardaddress;
	}

	public void setDyrSfzCardaddress(String dyrSfzCardaddress) {
		this.dyrSfzCardaddress = dyrSfzCardaddress;
	}

	public String getDyrSfzCardphotoId() {
		return this.dyrSfzCardphotoId;
	}

	public void setDyrSfzCardphotoId(String dyrSfzCardphotoId) {
		this.dyrSfzCardphotoId = dyrSfzCardphotoId;
	}

	public String getDyrZzjgZh() {
		return this.dyrZzjgZh;
	}

	public void setDyrZzjgZh(String dyrZzjgZh) {
		this.dyrZzjgZh = dyrZzjgZh;
	}

	public String getDyrZzjgFrdb() {
		return this.dyrZzjgFrdb;
	}

	public void setDyrZzjgFrdb(String dyrZzjgFrdb) {
		this.dyrZzjgFrdb = dyrZzjgFrdb;
	}

	public String getDyrZzjgYyzz() {
		return this.dyrZzjgYyzz;
	}

	public void setDyrZzjgYyzz(String dyrZzjgYyzz) {
		this.dyrZzjgYyzz = dyrZzjgYyzz;
	}

	public String getDyrZzjgDwmc() {
		return this.dyrZzjgDwmc;
	}

	public void setDyrZzjgDwmc(String dyrZzjgDwmc) {
		this.dyrZzjgDwmc = dyrZzjgDwmc;
	}

	public String getDyrZzjgDz() {
		return this.dyrZzjgDz;
	}

	public void setDyrZzjgDz(String dyrZzjgDz) {
		this.dyrZzjgDz = dyrZzjgDz;
	}

	public String getDyrZzjgNjrq() {
		return this.dyrZzjgNjrq;
	}

	public void setDyrZzjgNjrq(String dyrZzjgNjrq) {
		this.dyrZzjgNjrq = dyrZzjgNjrq;
	}

	public String getDyrZzjgNjyxq() {
		return this.dyrZzjgNjyxq;
	}

	public void setDyrZzjgNjyxq(String dyrZzjgNjyxq) {
		this.dyrZzjgNjyxq = dyrZzjgNjyxq;
	}

	public String getYhCgPch() {
		return this.yhCgPch;
	}

	public void setYhCgPch(String yhCgPch) {
		this.yhCgPch = yhCgPch;
	}

	public Integer getYhCgPchSl() {
		return this.yhCgPchSl;
	}

	public void setYhCgPchSl(Integer yhCgPchSl) {
		this.yhCgPchSl = yhCgPchSl;
	}

	public String getQjQjrxm() {
		return this.qjQjrxm;
	}

	public void setQjQjrxm(String qjQjrxm) {
		this.qjQjrxm = qjQjrxm;
	}

	public String getQjTddz() {
		return this.qjTddz;
	}

	public void setQjTddz(String qjTddz) {
		this.qjTddz = qjTddz;
	}

	public String getQjYzbm() {
		return this.qjYzbm;
	}

	public void setQjYzbm(String qjYzbm) {
		this.qjYzbm = qjYzbm;
	}

	public String getQjLxdh() {
		return this.qjLxdh;
	}

	public void setQjLxdh(String qjLxdh) {
		this.qjLxdh = qjLxdh;
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

	public String getYzqjrXm() {
		return this.yzqjrXm;
	}

	public void setYzqjrXm(String yzqjrXm) {
		this.yzqjrXm = yzqjrXm;
	}

	public String getYzqjrLxdh() {
		return this.yzqjrLxdh;
	}

	public void setYzqjrLxdh(String yzqjrLxdh) {
		this.yzqjrLxdh = yzqjrLxdh;
	}

	public String getSbSm() {
		return this.sbSm;
	}

	public void setSbSm(String sbSm) {
		this.sbSm = sbSm;
	}

	public String getLrzh() {
		return this.lrzh;
	}

	public void setLrzh(String lrzh) {
		this.lrzh = lrzh;
	}

	public String getLrxm() {
		return this.lrxm;
	}

	public void setLrxm(String lrxm) {
		this.lrxm = lrxm;
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

	public String getCzrbm() {
		return this.czrbm;
	}

	public void setCzrbm(String czrbm) {
		this.czrbm = czrbm;
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
	public String getYcsDeptId() {
		return ycsDeptId;
	}

	public void setYcsDeptId(String ycsDeptId) {
		this.ycsDeptId = ycsDeptId;
	}

	public String getYcsDeptName() {
		return ycsDeptName;
	}

	public void setYcsDeptName(String ycsDeptName) {
		this.ycsDeptName = ycsDeptName;
	}
	

	public String getYhSfzCardname() {
		return yhSfzCardname;
	}

	public void setYhSfzCardname(String yhSfzCardname) {
		this.yhSfzCardname = yhSfzCardname;
	}

	public String getYhSfzCardsex() {
		return yhSfzCardsex;
	}

	public void setYhSfzCardsex(String yhSfzCardsex) {
		this.yhSfzCardsex = yhSfzCardsex;
	}

	public String getYhSfzCardno() {
		return yhSfzCardno;
	}

	public void setYhSfzCardno(String yhSfzCardno) {
		this.yhSfzCardno = yhSfzCardno;
	}

	public String getYhSfzCardaddress() {
		return yhSfzCardaddress;
	}

	public void setYhSfzCardaddress(String yhSfzCardaddress) {
		this.yhSfzCardaddress = yhSfzCardaddress;
	}

	public String getYhSfzCardphotoId() {
		return yhSfzCardphotoId;
	}

	public void setYhSfzCardphotoId(String yhSfzCardphotoId) {
		this.yhSfzCardphotoId = yhSfzCardphotoId;
	}
	
	public String getYhSfzBz() {
		return yhSfzBz;
	}

	public void setYhSfzBz(String yhSfzBz) {
		this.yhSfzBz = yhSfzBz;
	}

	public String getDyrSfzBz() {
		return dyrSfzBz;
	}

	public void setDyrSfzBz(String dyrSfzBz) {
		this.dyrSfzBz = dyrSfzBz;
	}

	public String getYzyjpc() {
		return yzyjpc;
	}

	public void setYzyjpc(String yzyjpc) {
		this.yzyjpc = yzyjpc;
	}

	public String getCzlxfs() {
		return czlxfs;
	}

	public void setCzlxfs(String czlxfs) {
		this.czlxfs = czlxfs;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof DydjYwsbspbLog))
			return false;
		DydjYwsbspbLog castOther = (DydjYwsbspbLog) other;

		return ((this.getId() == castOther.getId()) || (this.getId() != null
				&& castOther.getId() != null && this.getId().equals(
				castOther.getId())))
				&& ((this.getLsh() == castOther.getLsh()) || (this.getLsh() != null
						&& castOther.getLsh() != null && this.getLsh().equals(
						castOther.getLsh())))
				&& ((this.getYwlx() == castOther.getYwlx()) || (this.getYwlx() != null
						&& castOther.getYwlx() != null && this.getYwlx()
						.equals(castOther.getYwlx())))
				&& ((this.getSqlx() == castOther.getSqlx()) || (this.getSqlx() != null
						&& castOther.getSqlx() != null && this.getSqlx()
						.equals(castOther.getSqlx())))
				&& ((this.getYhslLsh() == castOther.getYhslLsh()) || (this
						.getYhslLsh() != null
						&& castOther.getYhslLsh() != null && this.getYhslLsh()
						.equals(castOther.getYhslLsh())))
				&& ((this.getHphm() == castOther.getHphm()) || (this.getHphm() != null
						&& castOther.getHphm() != null && this.getHphm()
						.equals(castOther.getHphm())))
				&& ((this.getHpzl() == castOther.getHpzl()) || (this.getHpzl() != null
						&& castOther.getHpzl() != null && this.getHpzl()
						.equals(castOther.getHpzl())))
				&& ((this.getClsbdh() == castOther.getClsbdh()) || (this
						.getClsbdh() != null
						&& castOther.getClsbdh() != null && this.getClsbdh()
						.equals(castOther.getClsbdh())))
				&& ((this.getDjzsbh() == castOther.getDjzsbh()) || (this
						.getDjzsbh() != null
						&& castOther.getDjzsbh() != null && this.getDjzsbh()
						.equals(castOther.getDjzsbh())))
				&& ((this.getZhth() == castOther.getZhth()) || (this.getZhth() != null
						&& castOther.getZhth() != null && this.getZhth()
						.equals(castOther.getZhth())))
				&& ((this.getDyhth() == castOther.getDyhth()) || (this
						.getDyhth() != null
						&& castOther.getDyhth() != null && this.getDyhth()
						.equals(castOther.getDyhth())))
				&& ((this.getXxZd1() == castOther.getXxZd1()) || (this
						.getXxZd1() != null
						&& castOther.getXxZd1() != null && this.getXxZd1()
						.equals(castOther.getXxZd1())))
				&& ((this.getXxZd2() == castOther.getXxZd2()) || (this
						.getXxZd2() != null
						&& castOther.getXxZd2() != null && this.getXxZd2()
						.equals(castOther.getXxZd2())))
				&& ((this.getXxZd3() == castOther.getXxZd3()) || (this
						.getXxZd3() != null
						&& castOther.getXxZd3() != null && this.getXxZd3()
						.equals(castOther.getXxZd3())))
				&& ((this.getYhZzjgZh() == castOther.getYhZzjgZh()) || (this
						.getYhZzjgZh() != null
						&& castOther.getYhZzjgZh() != null && this
						.getYhZzjgZh().equals(castOther.getYhZzjgZh())))
				&& ((this.getYhZzjgFrdb() == castOther.getYhZzjgFrdb()) || (this
						.getYhZzjgFrdb() != null
						&& castOther.getYhZzjgFrdb() != null && this
						.getYhZzjgFrdb().equals(castOther.getYhZzjgFrdb())))
				&& ((this.getYhZzjgYyzz() == castOther.getYhZzjgYyzz()) || (this
						.getYhZzjgYyzz() != null
						&& castOther.getYhZzjgYyzz() != null && this
						.getYhZzjgYyzz().equals(castOther.getYhZzjgYyzz())))
				&& ((this.getYhZzjgDwmc() == castOther.getYhZzjgDwmc()) || (this
						.getYhZzjgDwmc() != null
						&& castOther.getYhZzjgDwmc() != null && this
						.getYhZzjgDwmc().equals(castOther.getYhZzjgDwmc())))
				&& ((this.getYhZzjgDz() == castOther.getYhZzjgDz()) || (this
						.getYhZzjgDz() != null
						&& castOther.getYhZzjgDz() != null && this
						.getYhZzjgDz().equals(castOther.getYhZzjgDz())))
				&& ((this.getYhZzjgNjrq() == castOther.getYhZzjgNjrq()) || (this
						.getYhZzjgNjrq() != null
						&& castOther.getYhZzjgNjrq() != null && this
						.getYhZzjgNjrq().equals(castOther.getYhZzjgNjrq())))
				&& ((this.getYhZzjgNjyxq() == castOther.getYhZzjgNjyxq()) || (this
						.getYhZzjgNjyxq() != null
						&& castOther.getYhZzjgNjyxq() != null && this
						.getYhZzjgNjyxq().equals(castOther.getYhZzjgNjyxq())))
				&& ((this.getDyrSfzCardname() == castOther.getDyrSfzCardname()) || (this
						.getDyrSfzCardname() != null
						&& castOther.getDyrSfzCardname() != null && this
						.getDyrSfzCardname().equals(
								castOther.getDyrSfzCardname())))
				&& ((this.getDyrSfzCardsex() == castOther.getDyrSfzCardsex()) || (this
						.getDyrSfzCardsex() != null
						&& castOther.getDyrSfzCardsex() != null && this
						.getDyrSfzCardsex()
						.equals(castOther.getDyrSfzCardsex())))
				&& ((this.getDyrSfzCardno() == castOther.getDyrSfzCardno()) || (this
						.getDyrSfzCardno() != null
						&& castOther.getDyrSfzCardno() != null && this
						.getDyrSfzCardno().equals(castOther.getDyrSfzCardno())))
				&& ((this.getDyrSfzCardaddress() == castOther
						.getDyrSfzCardaddress()) || (this
						.getDyrSfzCardaddress() != null
						&& castOther.getDyrSfzCardaddress() != null && this
						.getDyrSfzCardaddress().equals(
								castOther.getDyrSfzCardaddress())))
				&& ((this.getDyrSfzCardphotoId() == castOther
						.getDyrSfzCardphotoId()) || (this
						.getDyrSfzCardphotoId() != null
						&& castOther.getDyrSfzCardphotoId() != null && this
						.getDyrSfzCardphotoId().equals(
								castOther.getDyrSfzCardphotoId())))
				&& ((this.getDyrZzjgZh() == castOther.getDyrZzjgZh()) || (this
						.getDyrZzjgZh() != null
						&& castOther.getDyrZzjgZh() != null && this
						.getDyrZzjgZh().equals(castOther.getDyrZzjgZh())))
				&& ((this.getDyrZzjgFrdb() == castOther.getDyrZzjgFrdb()) || (this
						.getDyrZzjgFrdb() != null
						&& castOther.getDyrZzjgFrdb() != null && this
						.getDyrZzjgFrdb().equals(castOther.getDyrZzjgFrdb())))
				&& ((this.getDyrZzjgYyzz() == castOther.getDyrZzjgYyzz()) || (this
						.getDyrZzjgYyzz() != null
						&& castOther.getDyrZzjgYyzz() != null && this
						.getDyrZzjgYyzz().equals(castOther.getDyrZzjgYyzz())))
				&& ((this.getDyrZzjgDwmc() == castOther.getDyrZzjgDwmc()) || (this
						.getDyrZzjgDwmc() != null
						&& castOther.getDyrZzjgDwmc() != null && this
						.getDyrZzjgDwmc().equals(castOther.getDyrZzjgDwmc())))
				&& ((this.getDyrZzjgDz() == castOther.getDyrZzjgDz()) || (this
						.getDyrZzjgDz() != null
						&& castOther.getDyrZzjgDz() != null && this
						.getDyrZzjgDz().equals(castOther.getDyrZzjgDz())))
				&& ((this.getDyrZzjgNjrq() == castOther.getDyrZzjgNjrq()) || (this
						.getDyrZzjgNjrq() != null
						&& castOther.getDyrZzjgNjrq() != null && this
						.getDyrZzjgNjrq().equals(castOther.getDyrZzjgNjrq())))
				&& ((this.getDyrZzjgNjyxq() == castOther.getDyrZzjgNjyxq()) || (this
						.getDyrZzjgNjyxq() != null
						&& castOther.getDyrZzjgNjyxq() != null && this
						.getDyrZzjgNjyxq().equals(castOther.getDyrZzjgNjyxq())))
				&& ((this.getYhCgPch() == castOther.getYhCgPch()) || (this
						.getYhCgPch() != null
						&& castOther.getYhCgPch() != null && this.getYhCgPch()
						.equals(castOther.getYhCgPch())))
				&& ((this.getYhCgPchSl() == castOther.getYhCgPchSl()) || (this
						.getYhCgPchSl() != null
						&& castOther.getYhCgPchSl() != null && this
						.getYhCgPchSl().equals(castOther.getYhCgPchSl())))
				&& ((this.getQjQjrxm() == castOther.getQjQjrxm()) || (this
						.getQjQjrxm() != null
						&& castOther.getQjQjrxm() != null && this.getQjQjrxm()
						.equals(castOther.getQjQjrxm())))
				&& ((this.getQjTddz() == castOther.getQjTddz()) || (this
						.getQjTddz() != null
						&& castOther.getQjTddz() != null && this.getQjTddz()
						.equals(castOther.getQjTddz())))
				&& ((this.getQjYzbm() == castOther.getQjYzbm()) || (this
						.getQjYzbm() != null
						&& castOther.getQjYzbm() != null && this.getQjYzbm()
						.equals(castOther.getQjYzbm())))
				&& ((this.getQjLxdh() == castOther.getQjLxdh()) || (this
						.getQjLxdh() != null
						&& castOther.getQjLxdh() != null && this.getQjLxdh()
						.equals(castOther.getQjLxdh())))
				&& ((this.getYjSjrxm() == castOther.getYjSjrxm()) || (this
						.getYjSjrxm() != null
						&& castOther.getYjSjrxm() != null && this.getYjSjrxm()
						.equals(castOther.getYjSjrxm())))
				&& ((this.getYjTddz() == castOther.getYjTddz()) || (this
						.getYjTddz() != null
						&& castOther.getYjTddz() != null && this.getYjTddz()
						.equals(castOther.getYjTddz())))
				&& ((this.getYjYzbm() == castOther.getYjYzbm()) || (this
						.getYjYzbm() != null
						&& castOther.getYjYzbm() != null && this.getYjYzbm()
						.equals(castOther.getYjYzbm())))
				&& ((this.getYjLxdh() == castOther.getYjLxdh()) || (this
						.getYjLxdh() != null
						&& castOther.getYjLxdh() != null && this.getYjLxdh()
						.equals(castOther.getYjLxdh())))
				&& ((this.getYzqjrXm() == castOther.getYzqjrXm()) || (this
						.getYzqjrXm() != null
						&& castOther.getYzqjrXm() != null && this.getYzqjrXm()
						.equals(castOther.getYzqjrXm())))
				&& ((this.getYzqjrLxdh() == castOther.getYzqjrLxdh()) || (this
						.getYzqjrLxdh() != null
						&& castOther.getYzqjrLxdh() != null && this
						.getYzqjrLxdh().equals(castOther.getYzqjrLxdh())))
				&& ((this.getSbSm() == castOther.getSbSm()) || (this.getSbSm() != null
						&& castOther.getSbSm() != null && this.getSbSm()
						.equals(castOther.getSbSm())))
				&& ((this.getLrzh() == castOther.getLrzh()) || (this.getLrzh() != null
						&& castOther.getLrzh() != null && this.getLrzh()
						.equals(castOther.getLrzh())))
				&& ((this.getLrxm() == castOther.getLrxm()) || (this.getLrxm() != null
						&& castOther.getLrxm() != null && this.getLrxm()
						.equals(castOther.getLrxm())))
				&& ((this.getLrsj() == castOther.getLrsj()) || (this.getLrsj() != null
						&& castOther.getLrsj() != null && this.getLrsj()
						.equals(castOther.getLrsj())))
				&& ((this.getLrip() == castOther.getLrip()) || (this.getLrip() != null
						&& castOther.getLrip() != null && this.getLrip()
						.equals(castOther.getLrip())))
				&& ((this.getSbzt() == castOther.getSbzt()) || (this.getSbzt() != null
						&& castOther.getSbzt() != null && this.getSbzt()
						.equals(castOther.getSbzt())))
				&& ((this.getSbztTbly() == castOther.getSbztTbly()) || (this
						.getSbztTbly() != null
						&& castOther.getSbztTbly() != null && this
						.getSbztTbly().equals(castOther.getSbztTbly())))
				&& ((this.getSbztKdbh() == castOther.getSbztKdbh()) || (this
						.getSbztKdbh() != null
						&& castOther.getSbztKdbh() != null && this
						.getSbztKdbh().equals(castOther.getSbztKdbh())))
				&& ((this.getCgYhdm() == castOther.getCgYhdm()) || (this
						.getCgYhdm() != null
						&& castOther.getCgYhdm() != null && this.getCgYhdm()
						.equals(castOther.getCgYhdm())))
				&& ((this.getCgYhxm() == castOther.getCgYhxm()) || (this
						.getCgYhxm() != null
						&& castOther.getCgYhxm() != null && this.getCgYhxm()
						.equals(castOther.getCgYhxm())))
				&& ((this.getCgYhsj() == castOther.getCgYhsj()) || (this
						.getCgYhsj() != null
						&& castOther.getCgYhsj() != null && this.getCgYhsj()
						.equals(castOther.getCgYhsj())))
				&& ((this.getCgIp() == castOther.getCgIp()) || (this.getCgIp() != null
						&& castOther.getCgIp() != null && this.getCgIp()
						.equals(castOther.getCgIp())))
				&& ((this.getYzYhdm() == castOther.getYzYhdm()) || (this
						.getYzYhdm() != null
						&& castOther.getYzYhdm() != null && this.getYzYhdm()
						.equals(castOther.getYzYhdm())))
				&& ((this.getYzYhxm() == castOther.getYzYhxm()) || (this
						.getYzYhxm() != null
						&& castOther.getYzYhxm() != null && this.getYzYhxm()
						.equals(castOther.getYzYhxm())))
				&& ((this.getYzYhsj() == castOther.getYzYhsj()) || (this
						.getYzYhsj() != null
						&& castOther.getYzYhsj() != null && this.getYzYhsj()
						.equals(castOther.getYzYhsj())))
				&& ((this.getYzIp() == castOther.getYzIp()) || (this.getYzIp() != null
						&& castOther.getYzIp() != null && this.getYzIp()
						.equals(castOther.getYzIp())))
				&& ((this.getSynFlag() == castOther.getSynFlag()) || (this
						.getSynFlag() != null
						&& castOther.getSynFlag() != null && this.getSynFlag()
						.equals(castOther.getSynFlag())))
				&& ((this.getTranFlag() == castOther.getTranFlag()) || (this
						.getTranFlag() != null
						&& castOther.getTranFlag() != null && this
						.getTranFlag().equals(castOther.getTranFlag())))
				&& ((this.getTranDate() == castOther.getTranDate()) || (this
						.getTranDate() != null
						&& castOther.getTranDate() != null && this
						.getTranDate().equals(castOther.getTranDate())))
				&& ((this.getCzr() == castOther.getCzr()) || (this.getCzr() != null
						&& castOther.getCzr() != null && this.getCzr().equals(
						castOther.getCzr())))
				&& ((this.getCzrxm() == castOther.getCzrxm()) || (this
						.getCzrxm() != null
						&& castOther.getCzrxm() != null && this.getCzrxm()
						.equals(castOther.getCzrxm())))
				&& ((this.getCzrbm() == castOther.getCzrbm()) || (this
						.getCzrbm() != null
						&& castOther.getCzrbm() != null && this.getCzrbm()
						.equals(castOther.getCzrbm())))
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

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
		result = 37 * result
				+ (getLsh() == null ? 0 : this.getLsh().hashCode());
		result = 37 * result
				+ (getYwlx() == null ? 0 : this.getYwlx().hashCode());
		result = 37 * result
				+ (getSqlx() == null ? 0 : this.getSqlx().hashCode());
		result = 37 * result
				+ (getYhslLsh() == null ? 0 : this.getYhslLsh().hashCode());
		result = 37 * result
				+ (getHphm() == null ? 0 : this.getHphm().hashCode());
		result = 37 * result
				+ (getHpzl() == null ? 0 : this.getHpzl().hashCode());
		result = 37 * result
				+ (getClsbdh() == null ? 0 : this.getClsbdh().hashCode());
		result = 37 * result
				+ (getDjzsbh() == null ? 0 : this.getDjzsbh().hashCode());
		result = 37 * result
				+ (getZhth() == null ? 0 : this.getZhth().hashCode());
		result = 37 * result
				+ (getDyhth() == null ? 0 : this.getDyhth().hashCode());
		result = 37 * result
				+ (getXxZd1() == null ? 0 : this.getXxZd1().hashCode());
		result = 37 * result
				+ (getXxZd2() == null ? 0 : this.getXxZd2().hashCode());
		result = 37 * result
				+ (getXxZd3() == null ? 0 : this.getXxZd3().hashCode());
		result = 37 * result
				+ (getYhZzjgZh() == null ? 0 : this.getYhZzjgZh().hashCode());
		result = 37
				* result
				+ (getYhZzjgFrdb() == null ? 0 : this.getYhZzjgFrdb()
						.hashCode());
		result = 37
				* result
				+ (getYhZzjgYyzz() == null ? 0 : this.getYhZzjgYyzz()
						.hashCode());
		result = 37
				* result
				+ (getYhZzjgDwmc() == null ? 0 : this.getYhZzjgDwmc()
						.hashCode());
		result = 37 * result
				+ (getYhZzjgDz() == null ? 0 : this.getYhZzjgDz().hashCode());
		result = 37
				* result
				+ (getYhZzjgNjrq() == null ? 0 : this.getYhZzjgNjrq()
						.hashCode());
		result = 37
				* result
				+ (getYhZzjgNjyxq() == null ? 0 : this.getYhZzjgNjyxq()
						.hashCode());
		result = 37
				* result
				+ (getDyrSfzCardname() == null ? 0 : this.getDyrSfzCardname()
						.hashCode());
		result = 37
				* result
				+ (getDyrSfzCardsex() == null ? 0 : this.getDyrSfzCardsex()
						.hashCode());
		result = 37
				* result
				+ (getDyrSfzCardno() == null ? 0 : this.getDyrSfzCardno()
						.hashCode());
		result = 37
				* result
				+ (getDyrSfzCardaddress() == null ? 0 : this
						.getDyrSfzCardaddress().hashCode());
		result = 37
				* result
				+ (getDyrSfzCardphotoId() == null ? 0 : this
						.getDyrSfzCardphotoId().hashCode());
		result = 37 * result
				+ (getDyrZzjgZh() == null ? 0 : this.getDyrZzjgZh().hashCode());
		result = 37
				* result
				+ (getDyrZzjgFrdb() == null ? 0 : this.getDyrZzjgFrdb()
						.hashCode());
		result = 37
				* result
				+ (getDyrZzjgYyzz() == null ? 0 : this.getDyrZzjgYyzz()
						.hashCode());
		result = 37
				* result
				+ (getDyrZzjgDwmc() == null ? 0 : this.getDyrZzjgDwmc()
						.hashCode());
		result = 37 * result
				+ (getDyrZzjgDz() == null ? 0 : this.getDyrZzjgDz().hashCode());
		result = 37
				* result
				+ (getDyrZzjgNjrq() == null ? 0 : this.getDyrZzjgNjrq()
						.hashCode());
		result = 37
				* result
				+ (getDyrZzjgNjyxq() == null ? 0 : this.getDyrZzjgNjyxq()
						.hashCode());
		result = 37 * result
				+ (getYhCgPch() == null ? 0 : this.getYhCgPch().hashCode());
		result = 37 * result
				+ (getYhCgPchSl() == null ? 0 : this.getYhCgPchSl().hashCode());
		result = 37 * result
				+ (getQjQjrxm() == null ? 0 : this.getQjQjrxm().hashCode());
		result = 37 * result
				+ (getQjTddz() == null ? 0 : this.getQjTddz().hashCode());
		result = 37 * result
				+ (getQjYzbm() == null ? 0 : this.getQjYzbm().hashCode());
		result = 37 * result
				+ (getQjLxdh() == null ? 0 : this.getQjLxdh().hashCode());
		result = 37 * result
				+ (getYjSjrxm() == null ? 0 : this.getYjSjrxm().hashCode());
		result = 37 * result
				+ (getYjTddz() == null ? 0 : this.getYjTddz().hashCode());
		result = 37 * result
				+ (getYjYzbm() == null ? 0 : this.getYjYzbm().hashCode());
		result = 37 * result
				+ (getYjLxdh() == null ? 0 : this.getYjLxdh().hashCode());
		result = 37 * result
				+ (getYzqjrXm() == null ? 0 : this.getYzqjrXm().hashCode());
		result = 37 * result
				+ (getYzqjrLxdh() == null ? 0 : this.getYzqjrLxdh().hashCode());
		result = 37 * result
				+ (getSbSm() == null ? 0 : this.getSbSm().hashCode());
		result = 37 * result
				+ (getLrzh() == null ? 0 : this.getLrzh().hashCode());
		result = 37 * result
				+ (getLrxm() == null ? 0 : this.getLrxm().hashCode());
		result = 37 * result
				+ (getLrsj() == null ? 0 : this.getLrsj().hashCode());
		result = 37 * result
				+ (getLrip() == null ? 0 : this.getLrip().hashCode());
		result = 37 * result
				+ (getSbzt() == null ? 0 : this.getSbzt().hashCode());
		result = 37 * result
				+ (getSbztTbly() == null ? 0 : this.getSbztTbly().hashCode());
		result = 37 * result
				+ (getSbztKdbh() == null ? 0 : this.getSbztKdbh().hashCode());
		result = 37 * result
				+ (getCgYhdm() == null ? 0 : this.getCgYhdm().hashCode());
		result = 37 * result
				+ (getCgYhxm() == null ? 0 : this.getCgYhxm().hashCode());
		result = 37 * result
				+ (getCgYhsj() == null ? 0 : this.getCgYhsj().hashCode());
		result = 37 * result
				+ (getCgIp() == null ? 0 : this.getCgIp().hashCode());
		result = 37 * result
				+ (getYzYhdm() == null ? 0 : this.getYzYhdm().hashCode());
		result = 37 * result
				+ (getYzYhxm() == null ? 0 : this.getYzYhxm().hashCode());
		result = 37 * result
				+ (getYzYhsj() == null ? 0 : this.getYzYhsj().hashCode());
		result = 37 * result
				+ (getYzIp() == null ? 0 : this.getYzIp().hashCode());
		result = 37 * result
				+ (getSynFlag() == null ? 0 : this.getSynFlag().hashCode());
		result = 37 * result
				+ (getTranFlag() == null ? 0 : this.getTranFlag().hashCode());
		result = 37 * result
				+ (getTranDate() == null ? 0 : this.getTranDate().hashCode());
		result = 37 * result
				+ (getCzr() == null ? 0 : this.getCzr().hashCode());
		result = 37 * result
				+ (getCzrxm() == null ? 0 : this.getCzrxm().hashCode());
		result = 37 * result
				+ (getCzrbm() == null ? 0 : this.getCzrbm().hashCode());
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