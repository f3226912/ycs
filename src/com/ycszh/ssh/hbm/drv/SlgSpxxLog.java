package com.ycszh.ssh.hbm.drv;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

@SuppressWarnings("serial")
public class SlgSpxxLog extends BaseObject {
	public Long id;
	public String splx;
	public String splx2;
	public String hphm;
	public String hpzl;
	public String sfzmhm;
	public String xm;
	public String flag;
	public String sprCode;
	public String sprName;
	public String sprBm;
	public String sprBmkj;
	public Date sprSj;
	public Date cjsj;
	public Date yxsj;
	public String sprBmmc;
	public String bzsm;
	public String czr;
	public String cznr;
	public String czrxm;
	public String czbm;
	public Date czrq;
	public String czip;
	public String czmac;

	// Constructors

	/** default constructor */
	public SlgSpxxLog() {
	}

	/** full constructor */
	public SlgSpxxLog(String splx, String splx2, String hphm,
			String hpzl, String sfzmhm, String xm, String flag, String sprCode,
			String sprName, String sprBm, String sprBmkj, Date sprSj,
			Date cjsj, String czr, String cznr, Date yxsj, String bzsm, String czrxm, String czbm,
			Date czrq, String czip, String czmac) {
		this.splx = splx;
		this.splx2 = splx2;
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.sfzmhm = sfzmhm;
		this.xm = xm;
		this.flag = flag;
		this.sprCode = sprCode;
		this.sprName = sprName;
		this.sprBm = sprBm;
		this.sprBmkj = sprBmkj;
		this.sprSj = sprSj;
		this.cjsj = cjsj;
		this.yxsj = yxsj;
		this.bzsm = bzsm;
		this.czr = czr;
		this.cznr = cznr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSplx() {
		return this.splx;
	}

	public void setSplx(String splx) {
		this.splx = splx;
	}

	public String getSplx2() {
		return this.splx2;
	}

	public void setSplx2(String splx2) {
		this.splx2 = splx2;
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

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getSprCode() {
		return this.sprCode;
	}

	public void setSprCode(String sprCode) {
		this.sprCode = sprCode;
	}

	public String getSprName() {
		return this.sprName;
	}

	public void setSprName(String sprName) {
		this.sprName = sprName;
	}

	public String getSprBm() {
		return this.sprBm;
	}

	public void setSprBm(String sprBm) {
		this.sprBm = sprBm;
	}

	public String getSprBmkj() {
		return this.sprBmkj;
	}

	public void setSprBmkj(String sprBmkj) {
		this.sprBmkj = sprBmkj;
	}

	public Date getSprSj() {
		return this.sprSj;
	}

	public void setSprSj(Date sprSj) {
		this.sprSj = sprSj;
	}

	public Date getCjsj() {
		return this.cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public String getCzr() {
		return this.czr;
	}

	public void setCzr(String czr) {
		this.czr = czr;
	}

	public String getCznr() {
		return this.cznr;
	}

	public void setCznr(String cznr) {
		this.cznr = cznr;
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
	public Date getYxsj() {
		return yxsj;
	}

	public void setYxsj(Date yxsj) {
		this.yxsj = yxsj;
	}

	public String getSprBmmc() {
		return sprBmmc;
	}

	public void setSprBmmc(String sprBmmc) {
		this.sprBmmc = sprBmmc;
	}

	public String getBzsm() {
		return bzsm;
	}

	public void setBzsm(String bzsm) {
		this.bzsm = bzsm;
	}
	
}
