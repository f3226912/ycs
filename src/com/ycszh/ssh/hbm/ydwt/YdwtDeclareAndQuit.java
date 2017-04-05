package com.ycszh.ssh.hbm.ydwt;

import java.io.Serializable;

public class YdwtDeclareAndQuit  implements Serializable{

	/**
	 * 互联网业务申报、办结、退办数据统计bean
	 */
	private static final long serialVersionUID = 1L;
	
	public String lrsj;   //申报日期
	
	public int declareNum;//申报总是
	
	public int firstTrialNum;//初审总是
	
	public int recheckNum;   //复核总数
	
	public int moveNum;     //移交总数
	
	public int cgsQuitNum;  //车管退办总数

	public String getLrsj() {
		return lrsj;
	}

	public void setLrsj(String lrsj) {
		this.lrsj = lrsj;
	}

	public int getDeclareNum() {
		return declareNum;
	}

	public void setDeclareNum(int declareNum) {
		this.declareNum = declareNum;
	}

	public int getFirstTrialNum() {
		return firstTrialNum;
	}

	public void setFirstTrialNum(int firstTrialNum) {
		this.firstTrialNum = firstTrialNum;
	}

	public int getRecheckNum() {
		return recheckNum;
	}

	public void setRecheckNum(int recheckNum) {
		this.recheckNum = recheckNum;
	}

	public int getMoveNum() {
		return moveNum;
	}

	public void setMoveNum(int moveNum) {
		this.moveNum = moveNum;
	}

	public int getCgsQuitNum() {
		return cgsQuitNum;
	}

	public void setCgsQuitNum(int cgsQuitNum) {
		this.cgsQuitNum = cgsQuitNum;
	}
	
    
}
 