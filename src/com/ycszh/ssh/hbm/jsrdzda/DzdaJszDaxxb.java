package com.ycszh.ssh.hbm.jsrdzda;

import java.util.Date;
import java.util.List;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DzdaJszDaxxb entity. @author MyEclipse Persistence Tools
 * 电子档案——驾驶证档案信息采集表
 */

public class DzdaJszDaxxb extends BaseObject {

	// Fields

	private String cjxh;   //采集序号,  外网W+序号，内网直接是序号
	private String xm;     //姓名
	private String sfzmhm; //身份证明号码/驾驶证号码
	private String sfzmmc; //身份证明名称
	private String xb;     //性别，0男1女
	private String zjcx;   //准驾车型
	private String dabh;   //档案编号
	private String ywlx;   //业务类型（根据一窗式驾驶证业务类型表）
	private String ywyy;   //业务原因
	private String cjzt;   //0待驾校初审，1待车管复核，2归档、3档案移交邮政，B补审，CT车管退办，GT归档退办
	private String tbyy;   //审核退办原因，累加所有该宗数据名下所有不符合规范的图片信息
	private String remark;
	private String lsh;    //流水号（统一版业务流水号）
	private String yzfw;   //邮政服务（1立等可取，2邮递上门）
	private String lxdh;   //联系电话
	private String lxdz;   //联系地址
	private String yzQjr;  //取件人姓名
	private String yzQjrdz;//取件人地址
	private String yzQjrlxdh;//取件人联系电话
	private String yzQjryzbm;//取件人邮政编码
	private String cjr;
	private String cjxm;
	private String cjbm;
	private String cjbmKj;
	private Date cjsj;       //采集时间
	private String cjip;
	private String cjmac;
	private String synFlag;
	private String tranFlag;
	private Date tranDate;
	private String isLock; //锁定状态 0 未锁定 1已锁定
	private String lockr;  //锁定代码
	private String lockxm; //锁定人姓名
	private String lockbm; //锁定人部门
	private String lockIp; //锁定ip
	private String lockBM_kj;//锁定人部门科级
	private Date lockIptime; //锁定时间
	private String yzbm;     //邮政编码

	private List<DzdaJszDaxxbPhoto> photoLx; //资料类型
	// Constructors

	/** default constructor */
	public DzdaJszDaxxb() {
	}

	/** minimal constructor */
	public DzdaJszDaxxb(String cjxh) {
		this.cjxh = cjxh;
	}

	/** full constructor */
	public DzdaJszDaxxb(String cjxh, String xm, String sfzmhm, String sfzmmc,
			String xb, String zjcx, String dabh, String ywlx, String ywyy,
			String cjzt, String tbyy,String remark, String lsh, String yzfw, String lxdh,
			String lxdz, String yzQjr, String yzQjrdz, String yzQjrlxdh,
			String yzQjryzbm, String cjr, String cjxm, String cjbm,
			String cjbmKj, Date cjsj, String cjip, String cjmac,
			String synFlag, String tranFlag, Date tranDate,String isLock,String lockr,String lockxm,
			String lockbm,String lockIp,String lockBM_kj,Date lockIptime,String yzbm) {
		this.cjxh = cjxh;
		this.xm = xm;
		this.sfzmhm = sfzmhm;
		this.sfzmmc = sfzmmc;
		this.xb = xb;
		this.zjcx = zjcx;
		this.dabh = dabh;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.cjzt = cjzt;
		this.tbyy = tbyy;
		this.remark =remark;
		this.lsh = lsh;
		this.yzfw = yzfw;
		this.lxdh = lxdh;
		this.lxdz = lxdz;
		this.yzQjr = yzQjr;
		this.yzQjrdz = yzQjrdz;
		this.yzQjrlxdh = yzQjrlxdh;
		this.yzQjryzbm = yzQjryzbm;
		this.cjr = cjr;
		this.cjxm = cjxm;
		this.cjbm = cjbm;
		this.cjbmKj = cjbmKj;
		this.cjsj = cjsj;
		this.cjip = cjip;
		this.cjmac = cjmac;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.isLock=isLock;
		this.lockr =lockr;
		this.lockxm=lockxm;
		this.lockbm=lockbm;
		this.lockIp=lockIp;
		this.lockBM_kj=lockBM_kj;
		this.lockIptime=lockIptime;
		this.yzbm = yzbm;
	}

	// Property accessors

	public String getCjxh() {
		return this.cjxh;
	}

	public void setCjxh(String cjxh) {
		this.cjxh = cjxh;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSfzmmc() {
		return this.sfzmmc;
	}

	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getZjcx() {
		return this.zjcx;
	}

	public void setZjcx(String zjcx) {
		this.zjcx = zjcx;
	}

	public String getDabh() {
		return this.dabh;
	}

	public void setDabh(String dabh) {
		this.dabh = dabh;
	}

	public String getYwlx() {
		return this.ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getYwyy() {
		return this.ywyy;
	}

	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}

	public String getCjzt() {
		return this.cjzt;
	}

	public void setCjzt(String cjzt) {
		this.cjzt = cjzt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getYzfw() {
		return this.yzfw;
	}

	public void setYzfw(String yzfw) {
		this.yzfw = yzfw;
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

	public String getYzQjr() {
		return this.yzQjr;
	}

	public void setYzQjr(String yzQjr) {
		this.yzQjr = yzQjr;
	}

	public String getYzQjrdz() {
		return this.yzQjrdz;
	}

	public void setYzQjrdz(String yzQjrdz) {
		this.yzQjrdz = yzQjrdz;
	}

	public String getYzQjrlxdh() {
		return this.yzQjrlxdh;
	}

	public void setYzQjrlxdh(String yzQjrlxdh) {
		this.yzQjrlxdh = yzQjrlxdh;
	}

	public String getYzQjryzbm() {
		return this.yzQjryzbm;
	}

	public void setYzQjryzbm(String yzQjryzbm) {
		this.yzQjryzbm = yzQjryzbm;
	}

	public String getCjr() {
		return this.cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getCjxm() {
		return this.cjxm;
	}

	public void setCjxm(String cjxm) {
		this.cjxm = cjxm;
	}

	public String getCjbm() {
		return this.cjbm;
	}

	public void setCjbm(String cjbm) {
		this.cjbm = cjbm;
	}

	public String getCjbmKj() {
		return this.cjbmKj;
	}

	public void setCjbmKj(String cjbmKj) {
		this.cjbmKj = cjbmKj;
	}

	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getCjip() {
		return this.cjip;
	}

	public void setCjip(String cjip) {
		this.cjip = cjip;
	}

	public String getCjmac() {
		return this.cjmac;
	}

	public void setCjmac(String cjmac) {
		this.cjmac = cjmac;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public String getLockr() {
		return lockr;
	}

	public void setLockr(String lockr) {
		this.lockr = lockr;
	}

	public String getLockxm() {
		return lockxm;
	}

	public void setLockxm(String lockxm) {
		this.lockxm = lockxm;
	}

	public String getLockbm() {
		return lockbm;
	}

	public void setLockbm(String lockbm) {
		this.lockbm = lockbm;
	}

	public String getLockIp() {
		return lockIp;
	}

	public void setLockIp(String lockIp) {
		this.lockIp = lockIp;
	}

	public String getLockBM_kj() {
		return lockBM_kj;
	}

	public void setLockBM_kj(String lockBMKj) {
		lockBM_kj = lockBMKj;
	}

	public Date getLockIptime() {
		return lockIptime;
	}

	public void setLockIptime(Date lockIptime) {
		this.lockIptime = lockIptime;
	}

	public List<DzdaJszDaxxbPhoto> getPhotoLx() {
		return photoLx;
	}

	public void setPhotoLx(List<DzdaJszDaxxbPhoto> photoLx) {
		this.photoLx = photoLx;
	}

	public String getYzbm() {
		return yzbm;
	}

	public void setYzbm(String yzbm) {
		this.yzbm = yzbm;
	}


	
}