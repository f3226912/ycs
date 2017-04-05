package com.ycszh.ssh.hbm.veh;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;



/**
 * 动态拍照字典
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
public class DtpzZd extends BaseObject {

	private Integer id;
	private String ywlxid;
	private String ywlxms;
	private String title;
	private String url;
	private String czr;
	private String czrxm;
	private String czbm;
	private Date czrq;
	private String czip;
	private String czmac;
	private String vehOrDrv;

	// Constructors

	/** default constructor */
	public DtpzZd() {
	}

	/** minimal constructor */
	public DtpzZd(Integer id, String ywlxid, String ywlxms, String title,
			String url) {
		this.id = id;
		this.ywlxid = ywlxid;
		this.ywlxms = ywlxms;
		this.title = title;
		this.url = url;
	}

	/** full constructor */
	public DtpzZd(Integer id, String ywlxid, String ywlxms, String title,
			String url, String czr, String czrxm, String czbm, Date czrq,
			String czip, String czmac,String vehOrDrv) {
		this.id = id;
		this.ywlxid = ywlxid;
		this.ywlxms = ywlxms;
		this.title = title;
		this.url = url;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
		this.vehOrDrv = vehOrDrv;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYwlxid() {
		return this.ywlxid;
	}

	public void setYwlxid(String ywlxid) {
		this.ywlxid = ywlxid;
	}

	public String getYwlxms() {
		return this.ywlxms;
	}

	public void setYwlxms(String ywlxms) {
		this.ywlxms = ywlxms;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
	public String getVehOrDrv() {
		return vehOrDrv;
	}

	public void setVehOrDrv(String vehOrDrv) {
		this.vehOrDrv = vehOrDrv;
	}

}