package com.ycszh.ssh.hbm.gjgggl;

import java.util.Date;

public class BusCertifyCzcd implements java.io.Serializable {

	private String xh;
	private String lsh;
	private String hphm;
	private String hpzl;
	private String cllx;
	private String clxh;
	private String fdjhm;
	private String jdcsyr;
	private String clsbdh;
	private Date yxq;
	private String gjgsid;
	private String ggjgid;
	private Date sbrq;
	private Date zzrq;
	private String zzmjcode;
	private String zzmjxm;
	private String zzmjbm;
	private String zzmjbmKj;

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getLsh() {
		return lsh;
	}

	public void setLsh(String lsh) {
		this.lsh = lsh;
	}

	public String getHphm() {
		return hphm;
	}

	public void setHphm(String hphm) {
		this.hphm = hphm;
	}

	public String getHpzl() {
		return hpzl;
	}

	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getClxh() {
		return clxh;
	}

	public void setClxh(String clxh) {
		this.clxh = clxh;
	}

	public String getFdjhm() {
		return fdjhm;
	}

	public void setFdjhm(String fdjhm) {
		this.fdjhm = fdjhm;
	}

	public String getJdcsyr() {
		return jdcsyr;
	}

	public void setJdcsyr(String jdcsyr) {
		this.jdcsyr = jdcsyr;
	}

	public String getClsbdh() {
		return clsbdh;
	}

	public void setClsbdh(String clsbdh) {
		this.clsbdh = clsbdh;
	}

	public Date getYxq() {
		return yxq;
	}

	public void setYxq(Date yxq) {
		this.yxq = yxq;
	}

	public String getGjgsid() {
		return gjgsid;
	}

	public void setGjgsid(String gjgsid) {
		this.gjgsid = gjgsid;
	}

	public String getGgjgid() {
		return ggjgid;
	}

	public void setGgjgid(String ggjgid) {
		this.ggjgid = ggjgid;
	}

	public Date getSbrq() {
		return sbrq;
	}

	public void setSbrq(Date sbrq) {
		this.sbrq = sbrq;
	}

	public Date getZzrq() {
		return zzrq;
	}

	public void setZzrq(Date zzrq) {
		this.zzrq = zzrq;
	}

	public String getZzmjcode() {
		return zzmjcode;
	}

	public void setZzmjcode(String zzmjcode) {
		this.zzmjcode = zzmjcode;
	}

	public String getZzmjxm() {
		return zzmjxm;
	}

	public void setZzmjxm(String zzmjxm) {
		this.zzmjxm = zzmjxm;
	}

	public String getZzmjbm() {
		return zzmjbm;
	}

	public void setZzmjbm(String zzmjbm) {
		this.zzmjbm = zzmjbm;
	}

	public String getZzmjbmKj() {
		return zzmjbmKj;
	}

	public void setZzmjbmKj(String zzmjbmKj) {
		this.zzmjbmKj = zzmjbmKj;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cllx == null) ? 0 : cllx.hashCode());
		result = prime * result + ((clsbdh == null) ? 0 : clsbdh.hashCode());
		result = prime * result + ((clxh == null) ? 0 : clxh.hashCode());
		result = prime * result + ((fdjhm == null) ? 0 : fdjhm.hashCode());
		result = prime * result + ((ggjgid == null) ? 0 : ggjgid.hashCode());
		result = prime * result + ((gjgsid == null) ? 0 : gjgsid.hashCode());
		result = prime * result + ((hphm == null) ? 0 : hphm.hashCode());
		result = prime * result + ((hpzl == null) ? 0 : hpzl.hashCode());
		result = prime * result + ((jdcsyr == null) ? 0 : jdcsyr.hashCode());
		result = prime * result + ((lsh == null) ? 0 : lsh.hashCode());
		result = prime * result + ((sbrq == null) ? 0 : sbrq.hashCode());
		result = prime * result + ((xh == null) ? 0 : xh.hashCode());
		result = prime * result + ((yxq == null) ? 0 : yxq.hashCode());
		result = prime * result + ((zzmjbm == null) ? 0 : zzmjbm.hashCode());
		result = prime * result + ((zzmjbmKj == null) ? 0 : zzmjbmKj.hashCode());
		result = prime * result + ((zzmjcode == null) ? 0 : zzmjcode.hashCode());
		result = prime * result + ((zzmjxm == null) ? 0 : zzmjxm.hashCode());
		result = prime * result + ((zzrq == null) ? 0 : zzrq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BusCertifyCzcd other = (BusCertifyCzcd) obj;
		if (cllx == null) {
			if (other.cllx != null)
				return false;
		} else if (!cllx.equals(other.cllx))
			return false;
		if (clsbdh == null) {
			if (other.clsbdh != null)
				return false;
		} else if (!clsbdh.equals(other.clsbdh))
			return false;
		if (clxh == null) {
			if (other.clxh != null)
				return false;
		} else if (!clxh.equals(other.clxh))
			return false;
		if (fdjhm == null) {
			if (other.fdjhm != null)
				return false;
		} else if (!fdjhm.equals(other.fdjhm))
			return false;
		if (ggjgid == null) {
			if (other.ggjgid != null)
				return false;
		} else if (!ggjgid.equals(other.ggjgid))
			return false;
		if (gjgsid == null) {
			if (other.gjgsid != null)
				return false;
		} else if (!gjgsid.equals(other.gjgsid))
			return false;
		if (hphm == null) {
			if (other.hphm != null)
				return false;
		} else if (!hphm.equals(other.hphm))
			return false;
		if (hpzl == null) {
			if (other.hpzl != null)
				return false;
		} else if (!hpzl.equals(other.hpzl))
			return false;
		if (jdcsyr == null) {
			if (other.jdcsyr != null)
				return false;
		} else if (!jdcsyr.equals(other.jdcsyr))
			return false;
		if (lsh == null) {
			if (other.lsh != null)
				return false;
		} else if (!lsh.equals(other.lsh))
			return false;
		if (sbrq == null) {
			if (other.sbrq != null)
				return false;
		} else if (!sbrq.equals(other.sbrq))
			return false;
		if (xh == null) {
			if (other.xh != null)
				return false;
		} else if (!xh.equals(other.xh))
			return false;
		if (yxq == null) {
			if (other.yxq != null)
				return false;
		} else if (!yxq.equals(other.yxq))
			return false;
		if (zzmjbm == null) {
			if (other.zzmjbm != null)
				return false;
		} else if (!zzmjbm.equals(other.zzmjbm))
			return false;
		if (zzmjbmKj == null) {
			if (other.zzmjbmKj != null)
				return false;
		} else if (!zzmjbmKj.equals(other.zzmjbmKj))
			return false;
		if (zzmjcode == null) {
			if (other.zzmjcode != null)
				return false;
		} else if (!zzmjcode.equals(other.zzmjcode))
			return false;
		if (zzmjxm == null) {
			if (other.zzmjxm != null)
				return false;
		} else if (!zzmjxm.equals(other.zzmjxm))
			return false;
		if (zzrq == null) {
			if (other.zzrq != null)
				return false;
		} else if (!zzrq.equals(other.zzrq))
			return false;
		return true;
	}

}