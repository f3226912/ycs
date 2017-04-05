package com.ycszh.ssh.service.dagl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsygl;

/**
 * @author Administrator
 * @日期：2015-11-13
 * @描述：重点机动车驾驶人管理接口
 */
public interface SlgZdjdcjsrglService {

	/**
	 * 查询重点驾驶人机动车集合
	 * @param request
	 * @param currentpage 
	 * @throws Exception 
	 */
	public void initZdjdcjsrList(HttpServletRequest request, int currentpage) throws Exception;
	
	/**
	 * 编辑重点驾驶人机动车集合(页面初始)
	 * @param request
	 * @throws Exception 
	 */
	public void initEditZdjdcjsr(HttpServletRequest request) throws Exception;

	/**
	 * 编辑重点驾驶人机动车集合（增改操作）
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 */
	public void editZdjdcjsr(HttpServletRequest request, SlgZdjdcjsygl zdjdcjsr) throws Exception;

	/**
	 * 删除重点驾驶人机动车
	 * @param request
	 * @param zdjdcjsr
	 * @throws Exception 
	 */
	public void delZdjdcjsr(HttpServletRequest request) throws Exception;
	
	public int uniqueCheck(HttpServletRequest request) throws Exception;

	/**
	 * excel数据导入
	 * @param request 
	 * @param list 
	 * @throws Exception 
	 */
	public void putExcel(List<SlgZdjdcjsygl> list, HttpServletRequest request) throws Exception;

}
