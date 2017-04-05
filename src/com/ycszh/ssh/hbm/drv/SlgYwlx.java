package com.ycszh.ssh.hbm.drv;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.hbm.drv
 * @文件名:SlgYwlx.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class SlgYwlx extends BaseObject {

	// Fields
	private String id;
	private String ywlx;
	private String ywyy;
	private String ywms_sub;
	private String ywms_main;
	private String ywms_main_ch;
	private String bz;
	private String veh_drv;

	// Constructors

	/** default constructor */
	public SlgYwlx() {
	}

	/**
	 * @param ywms_main
	 * @param ywms_main_ch
	 */
	public SlgYwlx(String ywms_main, String ywms_main_ch) {
		super();
		this.ywms_main = ywms_main;
		this.ywms_main_ch = ywms_main_ch;
	}

	public SlgYwlx(String id,String ywlx, String ywyy, String ywms_sub, String ywms_main,
			String ywms_main_ch, String bz, String veh_drv) {
		super();
		this.id = id;
		this.ywlx = ywlx;
		this.ywyy = ywyy;
		this.ywms_sub = ywms_sub;
		this.ywms_main = ywms_main;
		this.ywms_main_ch = ywms_main_ch;
		this.bz = bz;
		this.veh_drv = veh_drv;
	}

	public String getYwlx() {
		return ywlx;
	}

	public void setYwlx(String ywlx) {
		this.ywlx = ywlx;
	}

	public String getYwyy() {
		return ywyy;
	}

	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}

	public String getYwms_sub() {
		return ywms_sub;
	}

	public void setYwms_sub(String ywms_sub) {
		this.ywms_sub = ywms_sub;
	}

	public String getYwms_main() {
		return ywms_main;
	}

	public void setYwms_main(String ywms_main) {
		this.ywms_main = ywms_main;
	}

	public String getYwms_main_ch() {
		return ywms_main_ch;
	}

	public void setYwms_main_ch(String ywms_main_ch) {
		this.ywms_main_ch = ywms_main_ch;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getVeh_drv() {
		return veh_drv;
	}

	public void setVeh_drv(String veh_drv) {
		this.veh_drv = veh_drv;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	

}