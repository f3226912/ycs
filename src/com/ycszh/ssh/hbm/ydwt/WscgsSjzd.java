package com.ycszh.ssh.hbm.ydwt;

import com.ycszh.ssh.hbm.BaseObject;

@SuppressWarnings("serial")
public class WscgsSjzd extends BaseObject {


    // Fields    

     private String id;
     private String dmz;
     private String dmlb;
     private String dmsm1;
     private String dmsm2;
     private String dmsm3;
     private String dmsm4;


    // Constructors

    /** default constructor */
    public WscgsSjzd() {
    }

    
    /** full constructor */
    public WscgsSjzd(String dmz, String dmlb, String dmsm1, String dmsm2, String dmsm3, String dmsm4) {
        this.dmz = dmz;
        this.dmlb = dmlb;
        this.dmsm1 = dmsm1;
        this.dmsm2 = dmsm2;
        this.dmsm3 = dmsm3;
        this.dmsm4 = dmsm4;
    }

   
    // Property accessors

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }

    public String getDmz() {
        return this.dmz;
    }
    
    public void setDmz(String dmz) {
        this.dmz = dmz;
    }

    public String getDmlb() {
        return this.dmlb;
    }
    
    public void setDmlb(String dmlb) {
        this.dmlb = dmlb;
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

    public String getDmsm3() {
        return this.dmsm3;
    }
    
    public void setDmsm3(String dmsm3) {
        this.dmsm3 = dmsm3;
    }

    public String getDmsm4() {
        return this.dmsm4;
    }
    
    public void setDmsm4(String dmsm4) {
        this.dmsm4 = dmsm4;
    }
   








}