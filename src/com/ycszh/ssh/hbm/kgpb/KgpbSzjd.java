package com.ycszh.ssh.hbm.kgpb;

import com.ycszh.ssh.hbm.BaseObject;
// default package



/**
 * KgpbSzjd entity. @author MyEclipse Persistence Tools
 */

public class KgpbSzjd extends BaseObject {


    // Fields    

     private String id;
     private String dmlb;
     private String dmz;
     private String dmsm1;
     private String dmsm2;
     private String bz;


    // Constructors

    /** default constructor */
    public KgpbSzjd() {
    }

    
    /** full constructor */
    public KgpbSzjd(String dmlb, String dmz, String dmsm1, String dmsm2, String bz) {
        this.dmlb = dmlb;
        this.dmz = dmz;
        this.dmsm1 = dmsm1;
        this.dmsm2 = dmsm2;
        this.bz = bz;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getDmlb() {
        return this.dmlb;
    }
    
    public void setDmlb(String dmlb) {
        this.dmlb = dmlb;
    }

    public String getDmz() {
        return this.dmz;
    }
    
    public void setDmz(String dmz) {
        this.dmz = dmz;
    }

    public String getDmsm1() {
        return this.dmsm1;
    }
    
    public void setDmsm1(String dmsm1) {
        this.dmsm1 = dmsm1;
    }

    public String getDmsm2() {
        return this.dmsm2;
    }
    
    public void setDmsm2(String dmsm2) {
        this.dmsm2 = dmsm2;
    }

    public String getBz() {
        return this.bz;
    }
    
    public void setBz(String bz) {
        this.bz = bz;
    }
   








}