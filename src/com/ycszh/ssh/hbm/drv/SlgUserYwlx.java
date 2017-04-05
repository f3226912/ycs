package com.ycszh.ssh.hbm.drv;

import java.util.Date;
import com.ycszh.ssh.hbm.BaseObject;

/**
 * SlgUserYwlx entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SlgUserYwlx extends BaseObject{

	// Fields

	private Long id;
	private String userId;
	private String sjzdId;
	private String bz;
	private String lrr;
	private String lrrxm;
	private String lrrbm;
	private String lrrbmkj;
	private Date lrsj;
	private String lrip;
	private String lrmac;

	// Constructors

	/** default constructor */
	public SlgUserYwlx() {
	}

	/** full constructor */
	public SlgUserYwlx(String userId, String sjzdId, String bz, String lrr,
			String lrrxm, String lrrbm, String lrrbmkj, Date lrsj, String lrip,
			String lrmac) {
		this.userId = userId;
		this.sjzdId = sjzdId;
		this.bz = bz;
		this.lrr = lrr;
		this.lrrxm = lrrxm;
		this.lrrbm = lrrbm;
		this.lrrbmkj = lrrbmkj;
		this.lrsj = lrsj;
		this.lrip = lrip;
		this.lrmac = lrmac;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSjzdId() {
		return this.sjzdId;
	}

	public void setSjzdId(String sjzdId) {
		this.sjzdId = sjzdId;
	}

	public String getBz() {
		return this.bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getLrr() {
		return this.lrr;
	}

	public void setLrr(String lrr) {
		this.lrr = lrr;
	}

	public String getLrrxm() {
		return this.lrrxm;
	}

	public void setLrrxm(String lrrxm) {
		this.lrrxm = lrrxm;
	}

	public String getLrrbm() {
		return this.lrrbm;
	}

	public void setLrrbm(String lrrbm) {
		this.lrrbm = lrrbm;
	}

	public String getLrrbmkj() {
		return this.lrrbmkj;
	}

	public void setLrrbmkj(String lrrbmkj) {
		this.lrrbmkj = lrrbmkj;
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

	public String getLrmac() {
		return this.lrmac;
	}

	public void setLrmac(String lrmac) {
		this.lrmac = lrmac;
	}

}