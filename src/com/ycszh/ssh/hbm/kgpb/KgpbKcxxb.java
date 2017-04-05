package com.ycszh.ssh.hbm.kgpb;
// default package

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * KgpbKcxxb entity. @author MyEclipse Persistence Tools
 */

public class KgpbKcxxb extends BaseObject {


    // Fields    

     private String id;
     private String kcbh;
     private String kcmc;
     private String zlzt;
     private String zrzt;
     private String zt;
     private String rs;
     private String bz;
     private String czr;
     private String czrxm;
     private String czrbm;
     private String czrkjbm;
     private String czip;
     private Date czsj;
     private String cznr;
     private String gdkgid;

    // Constructors

    /** default constructor */
    public KgpbKcxxb() {
    }

    
    /** full constructor */
    public KgpbKcxxb(String id, String kcbh, String kcmc, String zlzt,
			String zrzt, String zt, String rs, String bz, String czr,
			String czrxm, String czrbm, String czrkjbm, String czip, Date czsj,
			String cznr, String gdkgid) {
		super();
		this.id = id;
		this.kcbh = kcbh;
		this.kcmc = kcmc;
		this.zlzt = zlzt;
		this.zrzt = zrzt;
		this.zt = zt;
		this.rs = rs;
		this.bz = bz;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czrkjbm = czrkjbm;
		this.czip = czip;
		this.czsj = czsj;
		this.cznr = cznr;
		this.gdkgid = gdkgid;
	}

   
    // Property accessors
    
    public String getId() {
        return this.id;
    }
    
	public String getGdkgid() {
		return gdkgid;
	}


	public void setGdkgid(String gdkgid) {
		this.gdkgid = gdkgid;
	}


	public void setId(String id) {
        this.id = id;
    }

    public String getKcbh() {
        return this.kcbh;
    }
    
    public void setKcbh(String kcbh) {
        this.kcbh = kcbh;
    }

    public String getKcmc() {
        return this.kcmc;
    }
    
    public void setKcmc(String kcmc) {
        this.kcmc = kcmc;
    }

    public String getZlzt() {
        return this.zlzt;
    }
    
    public void setZlzt(String zlzt) {
        this.zlzt = zlzt;
    }

    public String getZrzt() {
        return this.zrzt;
    }
    
    public void setZrzt(String zrzt) {
        this.zrzt = zrzt;
    }

    public String getZt() {
        return this.zt;
    }
    
    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getRs() {
        return this.rs;
    }
    
    public void setRs(String rs) {
        this.rs = rs;
    }

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
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

    public String getCzrbm() {
        return this.czrbm;
    }
    
    public void setCzrbm(String czrbm) {
        this.czrbm = czrbm;
    }

    public String getCzrkjbm() {
        return this.czrkjbm;
    }
    
    public void setCzrkjbm(String czrkjbm) {
        this.czrkjbm = czrkjbm;
    }

    public String getCzip() {
        return this.czip;
    }
    
    public void setCzip(String czip) {
        this.czip = czip;
    }

    public Date getCzsj() {
        return this.czsj;
    }
    
    public void setCzsj(Date czsj) {
        this.czsj = czsj;
    }

    public String getCznr() {
        return this.cznr;
    }
    
    public void setCznr(String cznr) {
        this.cznr = cznr;
    }
   








}