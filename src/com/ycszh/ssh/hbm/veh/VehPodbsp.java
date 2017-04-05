package com.ycszh.ssh.hbm.veh;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

@SuppressWarnings("serial")
public class VehPodbsp extends BaseObject {
	// Fields

	public Long id;
	public String hphm;
	public String hpzl;
	public String czSfzmhm;
	public String czSfzmmc;
	public String czXm;
	public String poSfzmhm;
	public String poSfzmmc;
	public String poXm;
	public Date yxrq;
	public String sprCode;
	public String sprName;
	public String sprBm;
	public String sprBmkj;
	public Date sprSj;
	public String bz;

	// Constructors

	/** default constructor */
	public VehPodbsp() {
	}

	/** full constructor */
	public VehPodbsp(String hphm, String hpzl, String czSfzmhm,
			String czSfzmmc, String czXm, String poSfzmhm, String poSfzmmc,
			String poXm, Date yxrq, String sprCode, String sprName,
			String sprBm, String sprBmkj, Date sprSj, String bz) {
		this.hphm = hphm;
		this.hpzl = hpzl;
		this.czSfzmhm = czSfzmhm;
		this.czSfzmmc = czSfzmmc;
		this.czXm = czXm;
		this.poSfzmhm = poSfzmhm;
		this.poSfzmmc = poSfzmmc;
		this.poXm = poXm;
		this.yxrq = yxrq;
		this.sprCode = sprCode;
		this.sprName = sprName;
		this.sprBm = sprBm;
		this.sprBmkj = sprBmkj;
		this.sprSj = sprSj;
		this.bz = bz;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
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

	public String getCzSfzmhm() {
		return this.czSfzmhm;
	}

	public void setCzSfzmhm(String czSfzmhm) {
		this.czSfzmhm = czSfzmhm;
	}

	public String getCzSfzmmc() {
		return this.czSfzmmc;
	}

	public void setCzSfzmmc(String czSfzmmc) {
		this.czSfzmmc = czSfzmmc;
	}

	public String getCzXm() {
		return this.czXm;
	}

	public void setCzXm(String czXm) {
		this.czXm = czXm;
	}

	public String getPoSfzmhm() {
		return this.poSfzmhm;
	}

	public void setPoSfzmhm(String poSfzmhm) {
		this.poSfzmhm = poSfzmhm;
	}

	public String getPoSfzmmc() {
		return this.poSfzmmc;
	}

	public void setPoSfzmmc(String poSfzmmc) {
		this.poSfzmmc = poSfzmmc;
	}

	public String getPoXm() {
		return this.poXm;
	}

	public void setPoXm(String poXm) {
		this.poXm = poXm;
	}

	public Date getYxrq() {
		return this.yxrq;
	}

	public void setYxrq(Date yxrq) {
		this.yxrq = yxrq;
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

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
