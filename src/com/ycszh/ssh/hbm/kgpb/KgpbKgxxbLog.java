package com.ycszh.ssh.hbm.kgpb;
// default package

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * KgpbKgxxbLog entity. @author MyEclipse Persistence Tools
 */

public class KgpbKgxxbLog extends BaseObject {


    // Fields    

     private String id;
     private String jh;
     private String xm;
     private String ssbm;
     private String ssbmmc;
     private String zt;
     private String sjhm;
     private String bz;
     private String czr;
     private String czrxm;
     private String czrbm;
     private String czrkjbm;
     private String czip;
     private Date czsj;
     private String cznr;
     private String zbid;


    // Constructors

    /** default constructor */
    public KgpbKgxxbLog() {
    }

    
    /** full constructor */
    public KgpbKgxxbLog(String jh, String xm, String ssbm, String ssbmmc, String zt, String sjhm, String bz, String czr, String czrxm, String czrbm, String czrkjbm, String czip, Date czsj, String cznr, String zbid) {
        this.jh = jh;
        this.xm = xm;
        this.ssbm = ssbm;
        this.ssbmmc = ssbmmc;
        this.zt = zt;
        this.sjhm = sjhm;
        this.bz = bz;
        this.czr = czr;
        this.czrxm = czrxm;
        this.czrbm = czrbm;
        this.czrkjbm = czrkjbm;
        this.czip = czip;
        this.czsj = czsj;
        this.cznr = cznr;
        this.zbid = zbid;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getJh() {
        return this.jh;
    }
    
    public void setJh(String jh) {
        this.jh = jh;
    }

    public String getXm() {
        return this.xm;
    }
    
    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSsbm() {
        return this.ssbm;
    }
    
    public void setSsbm(String ssbm) {
        this.ssbm = ssbm;
    }

    public String getSsbmmc() {
        return this.ssbmmc;
    }
    
    public void setSsbmmc(String ssbmmc) {
        this.ssbmmc = ssbmmc;
    }

    public String getZt() {
        return this.zt;
    }
    
    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getSjhm() {
        return this.sjhm;
    }
    
    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
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

    public String getZbid() {
        return this.zbid;
    }
    
    public void setZbid(String zbid) {
        this.zbid = zbid;
    }
   








}