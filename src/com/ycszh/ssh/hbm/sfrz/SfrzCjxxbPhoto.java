package com.ycszh.ssh.hbm.sfrz;

import java.sql.Blob;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * SfrzCjxxbPhoto entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class SfrzCjxxbPhoto extends BaseObject {

	// Fields
	private String pid;
	private Blob photo;
	private String photoStr;
	private String photoType;
	private Integer picnum;
	private String isJm;
	private String cid;
	private String shzt;
	private String tbyy;
	private String shrdm;
	private String shrxm;
	private String shbm;
	private String shbmKj;
	private Date shsj;
	private String ship;
	private String shmac;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;

	// Constructors

	/** default constructor */
	public SfrzCjxxbPhoto() {
	}

	/** minimal constructor */
	public SfrzCjxxbPhoto(String pid) {
		this.pid = pid;
	}

	/** full constructor */
	public SfrzCjxxbPhoto(String pid, Blob photo, String photoStr,
			String photoType, Integer picnum, String isJm, String cid,
			String shzt, String tbyy, String shrdm, String shrxm, String shbm,
			String shbmKj, Date shsj, String ship, String shmac,
			String synFlag, Date tranDate, String tranFlag) {
		this.pid = pid;
		this.photo = photo;
		this.photoStr = photoStr;
		this.photoType = photoType;
		this.picnum = picnum;
		this.isJm = isJm;
		this.cid = cid;
		this.shzt = shzt;
		this.tbyy = tbyy;
		this.shrdm = shrdm;
		this.shrxm = shrxm;
		this.shbm = shbm;
		this.shbmKj = shbmKj;
		this.shsj = shsj;
		this.ship = ship;
		this.shmac = shmac;
		this.synFlag = synFlag;
		this.tranDate = tranDate;
		this.tranFlag = tranFlag;
	}

	// Property accessors

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Blob getPhoto() {
		return this.photo;
	}

	public void setPhoto(Blob photo) {
		this.photo = photo;
	}

	public String getPhotoStr() {
		return this.photoStr;
	}

	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}

	public String getPhotoType() {
		return this.photoType;
	}

	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}

	public Integer getPicnum() {
		return this.picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	public String getIsJm() {
		return this.isJm;
	}

	public void setIsJm(String isJm) {
		this.isJm = isJm;
	}

	public String getCid() {
		return this.cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getShzt() {
		return this.shzt;
	}

	public void setShzt(String shzt) {
		this.shzt = shzt;
	}

	public String getTbyy() {
		return this.tbyy;
	}

	public void setTbyy(String tbyy) {
		this.tbyy = tbyy;
	}

	public String getShrdm() {
		return this.shrdm;
	}

	public void setShrdm(String shrdm) {
		this.shrdm = shrdm;
	}

	public String getShrxm() {
		return this.shrxm;
	}

	public void setShrxm(String shrxm) {
		this.shrxm = shrxm;
	}

	public String getShbm() {
		return this.shbm;
	}

	public void setShbm(String shbm) {
		this.shbm = shbm;
	}

	public String getShbmKj() {
		return this.shbmKj;
	}

	public void setShbmKj(String shbmKj) {
		this.shbmKj = shbmKj;
	}

	public Date getShsj() {
		return this.shsj;
	}

	public void setShsj(Date shsj) {
		this.shsj = shsj;
	}

	public String getShip() {
		return this.ship;
	}

	public void setShip(String ship) {
		this.ship = ship;
	}

	public String getShmac() {
		return this.shmac;
	}

	public void setShmac(String shmac) {
		this.shmac = shmac;
	}

	public String getSynFlag() {
		return this.synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranFlag() {
		return this.tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

}