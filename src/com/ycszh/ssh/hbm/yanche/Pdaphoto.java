package com.ycszh.ssh.hbm.yanche;

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * Pdaphoto entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class Pdaphoto extends BaseObject {

	// Fields

	private String xh;
	private String lsh;
	private String photoName;
	private String tyzxh;
	private Date photoDate;
	private String userCode;
	private String typeZt;

	// Constructors

	/** default constructor */
	public Pdaphoto() {
	}

	/** minimal constructor */
	public Pdaphoto(String xh) {
		this.xh = xh;
	}

	/** full constructor */
	public Pdaphoto(String xh, String lsh, String photoName, String tyzxh,
			Date photoDate, String userCode, String typeZt) {
		this.xh = xh;
		this.lsh = lsh;
		this.photoName = photoName;
		this.tyzxh = tyzxh;
		this.photoDate = photoDate;
		this.userCode = userCode;
		this.typeZt = typeZt;
	}

	// Property accessors

	public String getXh() {
		return this.xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getLsh() {
		return this.lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getPhotoName() {
		return this.photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getTyzxh() {
		return this.tyzxh;
	}

	public void setTyzxh(String tyzxh) {
		this.tyzxh = tyzxh;
	}

	public Date getPhotoDate() {
		return this.photoDate;
	}

	public void setPhotoDate(Date photoDate) {
		this.photoDate = photoDate;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getTypeZt() {
		return this.typeZt;
	}

	public void setTypeZt(String typeZt) {
		this.typeZt = typeZt;
	}

}