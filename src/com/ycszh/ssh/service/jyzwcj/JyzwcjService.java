package com.ycszh.ssh.service.jyzwcj;

import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.jyzwcj.PoliceFingerInfo;

public interface JyzwcjService {

	/**
	 * 部门、警员信息查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List bmjyList(HttpServletRequest request, int currentPage)throws Exception;
	
	/**
	 * 获取警员信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getJYxx(HttpServletRequest request)throws Exception;

	/**
	 * 将指纹采集数据添加到指定的采集表及其日志表
	 * @param user
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer insertFinger(PoliceFingerInfo user, HttpServletRequest request) throws Exception;
	
}
