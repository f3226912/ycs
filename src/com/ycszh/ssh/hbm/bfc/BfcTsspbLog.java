package com.ycszh.ssh.hbm.bfc;
// default package

import java.math.BigDecimal;
import java.util.Date;

import com.ycszh.ssh.hbm.BaseObject;


/**
 * BfcTsspbLog entity. @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class BfcTsspbLog  extends BaseObject {


    // Fields    

     private String xh;
     private String sfzmmc;
     private String sfzmhm;
     private String spzt;
     private String spjb;
     private Date ysrq;
     private BigDecimal ysts;
     private String MSpid;
     private String MSpxm;
     private String MSpbmid;
     private String MSpbmmc;
     private Date MSpsj;
     private String MSpip;
     private String KSpid;
     private String KSpxm;
     private String KSpbmid;
     private String KSpbmmc;
     private Date KSpsj;
     private String KSpip;
     private String CSpid;
     private String CSpxm;
     private String CSpbmid;
     private String CSpbmmc;
     private Date CSpsj;
     private String CSpip;
     private String bz;
     private String czr;
     private String czrxm;
     private String czrbm;
     private String czrkjbm;
     private String czip;
     private Date czsj;
     private String synFlag;
     private String tranFlag;
     private Date tranDate;
     private String zbid;
     private String cznr;

    // Constructors

    /** default constructor */
    public BfcTsspbLog() {
    }

    
    /** full constructor */
    

   
    // Property accessors

    public String getXh() {
        return this.xh;
    }
    
    public BfcTsspbLog(String xh, String sfzmmc, String sfzmhm, String spzt,
			String spjb, Date ysrq, BigDecimal ysts, String mSpid,
			String mSpxm, String mSpbmid, String mSpbmmc, Date mSpsj,
			String mSpip, String kSpid, String kSpxm, String kSpbmid,
			String kSpbmmc, Date kSpsj, String kSpip, String cSpid,
			String cSpxm, String cSpbmid, String cSpbmmc, Date cSpsj,
			String cSpip, String bz, String czr, String czrxm, String czrbm,
			String czrkjbm, String czip, Date czsj, String synFlag,
			String tranFlag, Date tranDate, String zbid, String cznr) {
		super();
		this.xh = xh;
		this.sfzmmc = sfzmmc;
		this.sfzmhm = sfzmhm;
		this.spzt = spzt;
		this.spjb = spjb;
		this.ysrq = ysrq;
		this.ysts = ysts;
		MSpid = mSpid;
		MSpxm = mSpxm;
		MSpbmid = mSpbmid;
		MSpbmmc = mSpbmmc;
		MSpsj = mSpsj;
		MSpip = mSpip;
		KSpid = kSpid;
		KSpxm = kSpxm;
		KSpbmid = kSpbmid;
		KSpbmmc = kSpbmmc;
		KSpsj = kSpsj;
		KSpip = kSpip;
		CSpid = cSpid;
		CSpxm = cSpxm;
		CSpbmid = cSpbmid;
		CSpbmmc = cSpbmmc;
		CSpsj = cSpsj;
		CSpip = cSpip;
		this.bz = bz;
		this.czr = czr;
		this.czrxm = czrxm;
		this.czrbm = czrbm;
		this.czrkjbm = czrkjbm;
		this.czip = czip;
		this.czsj = czsj;
		this.synFlag = synFlag;
		this.tranFlag = tranFlag;
		this.tranDate = tranDate;
		this.zbid = zbid;
		this.cznr = cznr;
	}


	public String getCznr() {
		return cznr;
	}


	public void setCznr(String cznr) {
		this.cznr = cznr;
	}


	public void setXh(String xh) {
        this.xh = xh;
    }

    public String getSfzmmc() {
        return this.sfzmmc;
    }
    
    public void setSfzmmc(String sfzmmc) {
        this.sfzmmc = sfzmmc;
    }

    public String getSfzmhm() {
        return this.sfzmhm;
    }
    
    public void setSfzmhm(String sfzmhm) {
        this.sfzmhm = sfzmhm;
    }

    public String getSpzt() {
        return this.spzt;
    }
    
    public void setSpzt(String spzt) {
        this.spzt = spzt;
    }

    public String getSpjb() {
        return this.spjb;
    }
    
    public void setSpjb(String spjb) {
        this.spjb = spjb;
    }

    public Date getYsrq() {
        return this.ysrq;
    }
    
    public void setYsrq(Date ysrq) {
        this.ysrq = ysrq;
    }

    public BigDecimal getYsts() {
        return this.ysts;
    }
    
    public void setYsts(BigDecimal ysts) {
        this.ysts = ysts;
    }

    public String getMSpid() {
        return this.MSpid;
    }
    
    public void setMSpid(String MSpid) {
        this.MSpid = MSpid;
    }

    public String getMSpxm() {
        return this.MSpxm;
    }
    
    public void setMSpxm(String MSpxm) {
        this.MSpxm = MSpxm;
    }

    public String getMSpbmid() {
        return this.MSpbmid;
    }
    
    public void setMSpbmid(String MSpbmid) {
        this.MSpbmid = MSpbmid;
    }

    public String getMSpbmmc() {
        return this.MSpbmmc;
    }
    
    public void setMSpbmmc(String MSpbmmc) {
        this.MSpbmmc = MSpbmmc;
    }

    public Date getMSpsj() {
        return this.MSpsj;
    }
    
    public void setMSpsj(Date MSpsj) {
        this.MSpsj = MSpsj;
    }

    public String getMSpip() {
        return this.MSpip;
    }
    
    public void setMSpip(String MSpip) {
        this.MSpip = MSpip;
    }

    public String getKSpid() {
        return this.KSpid;
    }
    
    public void setKSpid(String KSpid) {
        this.KSpid = KSpid;
    }

    public String getKSpxm() {
        return this.KSpxm;
    }
    
    public void setKSpxm(String KSpxm) {
        this.KSpxm = KSpxm;
    }

    public String getKSpbmid() {
        return this.KSpbmid;
    }
    
    public void setKSpbmid(String KSpbmid) {
        this.KSpbmid = KSpbmid;
    }

    public String getKSpbmmc() {
        return this.KSpbmmc;
    }
    
    public void setKSpbmmc(String KSpbmmc) {
        this.KSpbmmc = KSpbmmc;
    }

    public Date getKSpsj() {
        return this.KSpsj;
    }
    
    public void setKSpsj(Date KSpsj) {
        this.KSpsj = KSpsj;
    }

    public String getKSpip() {
        return this.KSpip;
    }
    
    public void setKSpip(String KSpip) {
        this.KSpip = KSpip;
    }

    public String getCSpid() {
        return this.CSpid;
    }
    
    public void setCSpid(String CSpid) {
        this.CSpid = CSpid;
    }

    public String getCSpxm() {
        return this.CSpxm;
    }
    
    public void setCSpxm(String CSpxm) {
        this.CSpxm = CSpxm;
    }

    public String getCSpbmid() {
        return this.CSpbmid;
    }
    
    public void setCSpbmid(String CSpbmid) {
        this.CSpbmid = CSpbmid;
    }

    public String getCSpbmmc() {
        return this.CSpbmmc;
    }
    
    public void setCSpbmmc(String CSpbmmc) {
        this.CSpbmmc = CSpbmmc;
    }

    public Date getCSpsj() {
        return this.CSpsj;
    }
    
    public void setCSpsj(Date CSpsj) {
        this.CSpsj = CSpsj;
    }

    public String getCSpip() {
        return this.CSpip;
    }
    
    public void setCSpip(String CSpip) {
        this.CSpip = CSpip;
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

    public String getSynFlag() {
        return this.synFlag;
    }
    
    public void setSynFlag(String synFlag) {
        this.synFlag = synFlag;
    }

    public String getTranFlag() {
        return this.tranFlag;
    }
    
    public void setTranFlag(String tranFlag) {
        this.tranFlag = tranFlag;
    }

    public Date getTranDate() {
        return this.tranDate;
    }
    
    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    public String getZbid() {
        return this.zbid;
    }
    
    public void setZbid(String zbid) {
        this.zbid = zbid;
    }
   








}