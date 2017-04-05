package com.ycszh.ssh.service.dydj;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.ydwt.WtydnsUser;

public interface DydjUserService {
	/**
	 * 增加或者更新用户信息
	 * @param user   用户信息bean对象
	 * @param userId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdateUser(WtydnsUser user,HttpServletRequest request) throws Exception;
	
	/**
	 * 
	 * 验证用户是否登录
	 * 
	 * @param userName
	 * @param password
	 * @param checkCode
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public Integer checkLogin(HttpServletRequest request) throws Exception;
	
}
