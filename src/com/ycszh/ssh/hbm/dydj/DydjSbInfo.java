package com.ycszh.ssh.hbm.dydj;

import java.io.Serializable;
import java.util.Date;

public class DydjSbInfo implements Serializable {
	/**
	 * 机动车流水表(部分字段)
	 */
	private static final long serialVersionUID = 1L;
	
	public String lsh;
	
	public String ywyy;
	
	public String syr;
	
	public String hpzl;
	
	public String hphm;
	
	public String clpp1;
	
	public String clxh;
	
    public Date sqrq;
    
    public Date bjrq;
    
    public String glbm; 
    
    public String clsbdh;
    
    public DydjSbInfo() {
    	
    }
	public String getLsh() {
		return lsh;
	}
	public String getYwyy() {
		return ywyy;
	}
	public String getSyr() {
		return syr;
	}
	public String getHpzl() {
		return hpzl;
	}
	public String getHphm() {
		return hphm;
	}
	public String getClpp1() {
		return clpp1;
	}
	public String getClxh() {
		return clxh;
	}
	public Date getSqrq() {
		return sqrq;
	}
	public Date getBjrq() {
		return bjrq;
	}
	public String getGlbm() {
		return glbm;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
	public void setYwyy(String ywyy) {
		this.ywyy = ywyy;
	}
	public void setSyr(String syr) {
		this.syr = syr;
	}
	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public void setClpp1(String clpp1) {
		this.clpp1 = clpp1;
	}
	public void setClxh(String clxh) {
		this.clxh = clxh;
	}
	public void setSqrq(Date sqrq) {
		this.sqrq = sqrq;
	}
	public void setBjrq(Date bjrq) {
		this.bjrq = bjrq;
	}
	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}
	public String getClsbdh() {
		return clsbdh;
	}
	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}
    
}
