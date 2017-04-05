package com.ycszh.ssh.hbm.veh;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * 机动车辅助相片表
 * @author Administrator
 *
 */

@SuppressWarnings("serial")
public class DbjgFzxp extends BaseObject {

	// Fields

	private Integer id;
	private String jdcxxcjid;
	private String ywlxid;
	private String ywlxms;
	private String twm;
	private String czr;
	private String czrxm;
	private String czbm;
	private Date czrq;
	private String czip;
	private String czmac;
	private String vehOrDrv;
	private String pzid;
	private String pztitle;

	// Constructors

	/** default constructor */
	public DbjgFzxp() {
	}

	/** minimal constructor */
	public DbjgFzxp(Integer id, String jdcxxcjid, String ywlxid,
			String ywlxms, String twm) {
		this.id = id;
		this.jdcxxcjid = jdcxxcjid;
		this.ywlxid = ywlxid;
		this.ywlxms = ywlxms;
		this.twm = twm;
	}

	/** full constructor */
	public DbjgFzxp(Integer id, String jdcxxcjid, String ywlxid,
			String ywlxms, String twm, String czr, String czrxm, String czbm,
			Date czrq, String czip, String czmac,String vehOrDrv,String pzid,String pztitle) {
		this.id = id;
		this.jdcxxcjid = jdcxxcjid;
		this.ywlxid = ywlxid;
		this.ywlxms = ywlxms;
		this.twm = twm;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czbm = czbm;
		this.czrq = czrq;
		this.czip = czip;
		this.czmac = czmac;
		this.vehOrDrv = vehOrDrv;
		this.pzid = pzid;
		this.pztitle = pztitle;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJdcxxcjid() {
		return this.jdcxxcjid;
	}

	public void setJdcxxcjid(String jdcxxcjid) {
		this.jdcxxcjid = jdcxxcjid;
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

	public String getTwm() {
		return this.twm;
	}

	public void setTwm(String twm) {
		this.twm = twm;
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
	
	public String getPzid() {
		return pzid;
	}

	public void setPzid(String pzid) {
		this.pzid = pzid;
	}

	public String getPztitle() {
		return pztitle;
	}

	public void setPztitle(String pztitle) {
		this.pztitle = pztitle;
	}

}