package com.ycszh.ssh.hbm.kgpb;
// default package

import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * KgpbPbxxbLog entity. @author MyEclipse Persistence Tools
 */

public class KgpbPbxxbLog extends BaseObject {


    // Fields    

     private String id;
     private String kgid;
     private String kcid;
     private Date pbrq;
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
    public KgpbPbxxbLog() {
    }

    
    /** full constructor */
    public KgpbPbxxbLog(String kgid, String kcid, Date pbrq, String bz, String czr, String czrxm, String czrbm, String czrkjbm, String czip, Date czsj, String cznr, String zbid) {
        this.kgid = kgid;
        this.kcid = kcid;
        this.pbrq = pbrq;
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

    public String getKgid() {
        return this.kgid;
    }
    
    public void setKgid(String kgid) {
        this.kgid = kgid;
    }

    public String getKcid() {
        return this.kcid;
    }
    
    public void setKcid(String kcid) {
        this.kcid = kcid;
    }

    public Date getPbrq() {
        return this.pbrq;
    }
    
    public void setPbrq(Date pbrq) {
        this.pbrq = pbrq;
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