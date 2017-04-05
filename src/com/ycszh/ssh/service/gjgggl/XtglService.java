package com.ycszh.ssh.service.gjgggl;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.gjgggl.BusBase;

public interface XtglService {

	public void getBusBaseInital(HttpServletRequest request) throws Exception;

	public void getSzzdInital(HttpServletRequest request) throws Exception;

	/**
	 * 初始化公交公司登录密码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusBasePwd(HttpServletRequest request) throws Exception;

	/**
	 * 添加公交公公司数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusBase(HttpServletRequest request, BusBase busBase) throws Exception;

	/**
	 * 更新数字字典
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateSzzd(HttpServletRequest request) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
