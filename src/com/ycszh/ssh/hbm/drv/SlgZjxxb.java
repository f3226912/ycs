package com.ycszh.ssh.hbm.drv;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.hbm.drv
 * @文件名:SlgZjxxb.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class SlgZjxxb extends BaseObject {

	// Fields

	private String id;
	private String cjid;
	private String cardname;
	private String cardsex;
	private String cardno;
	private String cardaddress;
	private String cardphoto;
	private String cardnameDbr;
	private String cardsexDbr;
	private String cardnoDbr;
	private String cardaddressDbr;
	private String cardphotoDbr;
	private String zzjgZh;
	private String zzjgFrdb;
	private String zzjgYyzz;
	private String zzjgDwmc;
	private String zzjgDz;
	private String zzjgNjrq;
	private String sxtZp;
	private String gpyZp;
	private String czr;
	private String cznr;
	private String czrxm;
	private String czbm;
	private Date czrq;
	private String czip;
	private String czmac;

	// Constructors

	/** default constructor */
	public SlgZjxxb() {
	}

	/** minimal constructor */
	public SlgZjxxb(String id, String cjid) {
		this.id = id;
		this.cjid = cjid;
	}

	/** full constructor */
	public SlgZjxxb(String id, String cjid, String cardname, String cardsex,
			String cardno, String cardaddress, String cardphoto,
			String cardnameDbr, String cardsexDbr, String cardnoDbr,
			String cardaddressDbr, String cardphotoDbr, String zzjgZh,
			String zzjgFrdb, String zzjgYyzz, String zzjgDwmc, String zzjgDz,
			String zzjgNjrq, String sxtZp, String gpyZp, String czr,
			String cznr, String czrxm, String czbm, Date czrq, String czip,
			String czmac) {
		this.id = id;
		this.cjid = cjid;
		this.cardname = cardname;
		this.cardsex = cardsex;
		this.cardno = cardno;
		this.cardaddress = cardaddress;
		this.cardphoto = cardphoto;
		this.cardnameDbr = cardnameDbr;
		this.cardsexDbr = cardsexDbr;
		this.cardnoDbr = cardnoDbr;
		this.cardaddressDbr = cardaddressDbr;
		this.cardphotoDbr = cardphotoDbr;
		this.zzjgZh = zzjgZh;
		this.zzjgFrdb = zzjgFrdb;
		this.zzjgYyzz = zzjgYyzz;
		this.zzjgDwmc = zzjgDwmc;
		this.zzjgDz = zzjgDz;
		this.zzjgNjrq = zzjgNjrq;
		this.sxtZp = sxtZp;
		this.gpyZp = gpyZp;
		this.czr = czr;
		this.cznr = cznr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCjid() {
		return this.cjid;
	}

	public void setCjid(String cjid) {
		this.cjid = cjid;
	}

	public String getCardname() {
		return this.cardname;
	}

	public void setCardname(String cardname) {
		this.cardname = cardname;
	}

	public String getCardsex() {
		return this.cardsex;
	}

	public void setCardsex(String cardsex) {
		this.cardsex = cardsex;
	}

	public String getCardno() {
		return this.cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public String getCardaddress() {
		return this.cardaddress;
	}

	public void setCardaddress(String cardaddress) {
		this.cardaddress = cardaddress;
	}

	public String getCardphoto() {
		return this.cardphoto;
	}

	public void setCardphoto(String cardphoto) {
		this.cardphoto = cardphoto;
	}

	public String getCardnameDbr() {
		return this.cardnameDbr;
	}

	public void setCardnameDbr(String cardnameDbr) {
		this.cardnameDbr = cardnameDbr;
	}

	public String getCardsexDbr() {
		return this.cardsexDbr;
	}

	public void setCardsexDbr(String cardsexDbr) {
		this.cardsexDbr = cardsexDbr;
	}

	public String getCardnoDbr() {
		return this.cardnoDbr;
	}

	public void setCardnoDbr(String cardnoDbr) {
		this.cardnoDbr = cardnoDbr;
	}

	public String getCardaddressDbr() {
		return this.cardaddressDbr;
	}

	public void setCardaddressDbr(String cardaddressDbr) {
		this.cardaddressDbr = cardaddressDbr;
	}

	public String getCardphotoDbr() {
		return this.cardphotoDbr;
	}

	public void setCardphotoDbr(String cardphotoDbr) {
		this.cardphotoDbr = cardphotoDbr;
	}

	public String getZzjgZh() {
		return this.zzjgZh;
	}

	public void setZzjgZh(String zzjgZh) {
		this.zzjgZh = zzjgZh;
	}

	public String getZzjgFrdb() {
		return this.zzjgFrdb;
	}

	public void setZzjgFrdb(String zzjgFrdb) {
		this.zzjgFrdb = zzjgFrdb;
	}

	public String getZzjgYyzz() {
		return this.zzjgYyzz;
	}

	public void setZzjgYyzz(String zzjgYyzz) {
		this.zzjgYyzz = zzjgYyzz;
	}

	public String getZzjgDwmc() {
		return this.zzjgDwmc;
	}

	public void setZzjgDwmc(String zzjgDwmc) {
		this.zzjgDwmc = zzjgDwmc;
	}

	public String getZzjgDz() {
		return this.zzjgDz;
	}

	public void setZzjgDz(String zzjgDz) {
		this.zzjgDz = zzjgDz;
	}

	public String getZzjgNjrq() {
		return this.zzjgNjrq;
	}

	public void setZzjgNjrq(String zzjgNjrq) {
		this.zzjgNjrq = zzjgNjrq;
	}

	public String getSxtZp() {
		return this.sxtZp;
	}

	public void setSxtZp(String sxtZp) {
		this.sxtZp = sxtZp;
	}

	public String getGpyZp() {
		return this.gpyZp;
	}

	public void setGpyZp(String gpyZp) {
		this.gpyZp = gpyZp;
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

}