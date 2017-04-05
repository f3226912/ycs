package com.ycszh.ssh.hbm.dydj;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * DydjYwsbspb entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class DydjYwsbspbTemp extends BaseObject {

	// Fields

	public Integer id;              	//申请ID
	public String lsh;					//流水号
	public String ywlx;					//0 抵押登记  1 解除抵押登记
	public String sqlx;					//11银行代办个人申报  12 银行代办单位申报  21银行受理(车主自行办理)  22 车主自行申报 
	public String yhslLsh;				//银行受理流水号
	public String hphm;					//号牌号码,加密
	public String hpzl;					//号牌种类,加密
	public String clsbdh;				//车辆识别代号,加密
	public String djzsbh;				//登记证书编号 加密
	public String zhth;					//主合同号 加密
	public String dyhth;				//抵押合同号 加密
	public String xxZd1;				//信息字段1附 加密
	public String xxZd2;				//信息字段2附 加密
	public String xxZd3;				//信息字段3附 加密
	public String yhZzjgZh;				//银行_组织机构代码
	public String yhZzjgFrdb;			//银行_单位法人
	public String yhZzjgYyzz;			//银行_营业执照
	public String yhZzjgDwmc;			//银行_单位名称
	public String yhZzjgDz;				//银行_地址
	public String yhZzjgNjrq;			//银行_年检日期
	public String yhZzjgNjyxq;			//银行_年检有效期
	public String yhSfzCardname;		//银行经办人_身份证姓名*
	public String yhSfzCardsex;			//银行经办人_性别 *
	public String yhSfzCardno;			//银行经办人_身份证号码*
	public String yhSfzCardaddress;		//银行经办人_身份证上地址
	public String yhSfzCardphotoId;     //银行经办人_身份证上照片ID 对应DYDJ_YWSBSPB_PHOTO的ID
	
	public String dyrSfzCardname;		//抵押人(或经办人)_身份证姓名*
	public String dyrSfzCardsex;		//抵押人(或经办人)_性别 *
	public String dyrSfzCardno;			//抵押人(或经办人)_身份证号码*
	public String dyrSfzCardaddress;	//抵押人(或经办人)_身份证上地址
	public String dyrSfzCardphotoId;    //抵押人(或经办人)_身份证上照片ID 对应DYDJ_YWSBSPB_PHOTO的ID
	public String dyrZzjgZh;			//抵押人_组织机构代码
	public String dyrZzjgFrdb;			//抵押人_单位法人
	public String dyrZzjgYyzz;			//抵押人_营业执照
	public String dyrZzjgDwmc;			//抵押人_单位名称
	public String dyrZzjgDz;			//抵押人_地址
	public String dyrZzjgNjrq;			//抵押人_年检日期
	public String dyrZzjgNjyxq;		    //抵押人_年检有效期
	public String yhCgPch;				//银行/车管批次号 Y银行  C车管
	public Integer yhCgPchSl;			//银行/车管批次号_数量
	public String qjQjrxm; 				//取件—取件人姓名
	public String qjTddz;				//取件—投递地址
	public String qjYzbm;				//取件—邮政编码
	public String qjLxdh;				//取件—联系电话
	public String yjSjrxm;				//邮寄—收件人姓名
	public String yjTddz;				//邮寄—投递地址
	public String yjYzbm;				//邮寄—邮政编码
	public String yjLxdh;				//邮寄—联系电话
	public String yzqjrXm;				//邮政取件员姓名
	public String yzqjrLxdh;		    //邮政取件员联系电话
	public String sbSm;					//业务申报说明
	public String lrzh;					//录入账户	
	public String lrxm;					//录入姓名
	public Date lrsj;					//录入时间
	public String lrip;					//录入ip
	public String sbzt;					//业务申办状态 0 初始状态  1 邮政安排取件员 2邮政待签注资料移交车管  3车管待签注资料复核 4车管已签注资料移交邮政  5邮政回填EMS单号  CT-车管申办失败  YT-邮政申办失败
	public String sbztTbly;				//业务申办退办原因   退办才需要填理由(电脑初审不过的也要填)
	public String sbztKdbh;				//快递编号 加密
	public String cgYhdm;				//车管-用户代码
	public String cgYhxm;				//车管-用户姓名
	public String cgYhsj;				//车管-用户时间
	public String cgIp;				    //车管-ip
	public String yzYhdm;				//邮政-用户代码
	public String yzYhxm;				//邮政-用户姓名	
	public String yzYhsj;				//邮政-用户时间
	public String yzIp;					//邮政-ip
	public String synFlag;				//同步标志，外网UC，内网UW
	public String tranFlag;				//同步标志 1已同步
	public Date tranDate;				//同步时间
	public String ycsDeptId;
	public String ycsDeptName;
	public String yhSfzBz;			//银行经办人_未读取身份证原因
	public String dyrSfzBz;			//抵押人（或经办人）_未读取身份证原因
	public String yzyjpc;			//邮政一脚批次Y+序列号
	public String czlxfs;			//车主联系方式

	// Constructors

	/** default constructor */
	public DydjYwsbspbTemp() {
	}

	/** full constructor */
	public DydjYwsbspbTemp(String lsh, String ywlx, String sqlx, String yhslLsh,
			String hphm, String hpzl, String clsbdh, String djzsbh,
			String zhth, String dyhth, String xxZd1, String xxZd2,
			String xxZd3, String yhZzjgZh, String yhZzjgFrdb,
			String yhZzjgYyzz, String yhZzjgDwmc, String yhZzjgDz,
			String yhZzjgNjrq, String yhZzjgNjyxq, String dyrSfzCardname,
			String dyrSfzCardsex, String dyrSfzCardno,
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
			String yzIp, String synFlag, String tranFlag, Date tranDate) {
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
	
}