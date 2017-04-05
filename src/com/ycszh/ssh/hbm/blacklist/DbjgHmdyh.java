package com.ycszh.ssh.hbm.blacklist;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * DbjgHmdyh entity. @author MyEclipse Persistence Tools
 */
public class DbjgHmdyh extends BaseObject {

	// Fields
    /**
     * 序号
     */
	public  String xh;      //序号
	public String sfzmhm;  //身份证明号码 
	public String xm;      //姓名 
	public String rklx;    //入库类型(1:手动添加2:系统自动运算3:其它)  (界面不显示：为1)
	public String dqzt;    //当前状态(1:锁定2:解锁)    *
	public String sdyy;    //锁定原因(字典表控制) *
	public String sdbz;    //锁定备注    *
	public String sdlx;    //锁定类型(1:日期有效止,2:为永久锁定)   *
	public Date sdyxqz;    //锁定有效期止(锁定类型为1时有值)     *
	public String jsyy;    //解锁原因(字典表控制)   
	public String jsbz;    //解锁备注
	public String bz;      //备注
	public String synFlag; //同步标志，外网UC，内网UW
	public String tranFlag;//同步标志 1已同步
	public Date tranDate;  //同步时间
	public String czr;     //操作人代码
	public String czrxm;   //操作人姓名
	public String czbm;    //操作部门
	public String czbmKj;  //操作部门(科级)
	public Date czrq;      //操作日期
	public String czip;    //操作IP
	public String czmac;   //操作MAC

	// Constructors

	/** default constructor */
	public DbjgHmdyh() {
	}

	/** full constructor */
	public DbjgHmdyh(String sfzmhm, String xm, String rklx, String dqzt,
			String sdyy, String sdbz, String sdlx, Date sdyxqz, String jsyy,
			String jsbz, String bz, String synFlag, String tranFlag,
			Date tranDate, String czr, String czrxm, String czbm,
			String czbmKj, Date czrq, String czip, String czmac) {
		this.sfzmhm = sfzmhm;
		this.xm = xm;
		this.rklx = rklx;
		this.dqzt = dqzt;
		this.sdyy = sdyy;
		this.sdbz = sdbz;
		this.sdlx = sdlx;
		this.sdyxqz = sdyxqz;
		this.jsyy = jsyy;
		this.jsbz = jsbz;
		this.bz = bz;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
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

	public String getSfzmhm() {
		return this.sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getRklx() {
		return this.rklx;
	}

	public void setRklx(String rklx) {
		this.rklx = rklx;
	}

	public String getDqzt() {
		return this.dqzt;
	}

	public void setDqzt(String dqzt) {
		this.dqzt = dqzt;
	}

	public String getSdyy() {
		return this.sdyy;
	}

	public void setSdyy(String sdyy) {
		this.sdyy = sdyy;
	}

	public String getSdbz() {
		return this.sdbz;
	}

	public void setSdbz(String sdbz) {
		this.sdbz = sdbz;
	}

	public String getSdlx() {
		return this.sdlx;
	}

	public void setSdlx(String sdlx) {
		this.sdlx = sdlx;
	}

	public Date getSdyxqz() {
		return this.sdyxqz;
	}

	public void setSdyxqz(Date sdyxqz) {
		this.sdyxqz = sdyxqz;
	}

	public String getJsyy() {
		return this.jsyy;
	}

	public void setJsyy(String jsyy) {
		this.jsyy = jsyy;
	}

	public String getJsbz() {
		return this.jsbz;
	}

	public void setJsbz(String jsbz) {
		this.jsbz = jsbz;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
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