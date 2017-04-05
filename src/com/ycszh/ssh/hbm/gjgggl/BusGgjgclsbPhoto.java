package com.ycszh.ssh.hbm.gjgggl;

import java.sql.Blob;
import java.util.Date;


public class BusGgjgclsbPhoto implements java.io.Serializable {


	private String lsh;
	private Blob LPic;
	private Blob RPic;
	private String synFlag;
	private Date tranDate;
	private String tranFlag;

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public Blob getLPic() {
		return LPic;
	}

	public void setLPic(Blob lPic) {
		LPic = lPic;
	}

	public Blob getRPic() {
		return RPic;
	}

	public void setRPic(Blob rPic) {
		RPic = rPic;
	}

	public String getSynFlag() {
		return synFlag;
	}

	public void setSynFlag(String synFlag) {
		this.synFlag = synFlag;
	}

	public Date getTranDate() {
		return tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranFlag() {
		return tranFlag;
	}

	public void setTranFlag(String tranFlag) {
		this.tranFlag = tranFlag;
	}

}