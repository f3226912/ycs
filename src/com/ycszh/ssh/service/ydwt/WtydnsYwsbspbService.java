package com.ycszh.ssh.service.ydwt;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspbLog;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;

public interface WtydnsYwsbspbService {
	
	public List getPCHItems(String sbzt) throws Exception;
	public List<WtydnsYwsbspb> getYwsbspbListByCondition(HttpServletRequest request,int currentPage) throws Exception;
	public List<WscgsSjzd> getHpzl() throws Exception;
	public List<YdwtDeclareAndQuit> getYdwtDeclareAndquitStat(HttpServletRequest request) throws Exception;
	public List<YdwtDeclareAndQuit> getYdwtPosQuitStat(HttpServletRequest request) throws Exception;
	public int addUserInfo(HttpServletRequest request,WtydnsUser user) throws Exception;
	public List<WtydnsUser> getUserList(HttpServletRequest request,int currentPage) throws Exception;
	public Integer freezeUser(Long id) throws Exception;
	public Integer resetPwd(String id,HttpServletRequest request) throws Exception;
	public WtydnsYwsbspb getWtydsnIfo (int id) throws Exception;
	public List<WtydnsYwsbspbLog>  getWtydsnLogInfo(int id) throws Exception;
	/**
	 * 根据id获取用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public WtydnsUser getWtydnsUserInfo(HttpServletRequest request) throws Exception;
    /**
     * ydwt未经申报办理业务预警(30天内的)
     * @param request
     * @param currentPage
     * @return
     * @throws Exception
     */
	public List<DydjSbInfo> getWarnInfo(HttpServletRequest request,int currentPage) throws Exception; 
	public List<WtydnsYwsbspb> getYwsbspbListExcelByCondition(HttpServletRequest request) throws Exception;
}