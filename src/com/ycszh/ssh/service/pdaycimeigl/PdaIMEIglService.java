package com.ycszh.ssh.service.pdaycimeigl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycszh.ssh.hbm.pdaycimeigl.TPdaYcimei;

public interface PdaIMEIglService {

	/**
	 * 根据部门编号级联获取用户
	 * @param request
	 * @throws Exception
	 */
	public String getUserByDeptId(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取登记管理列表
	 * @param request
	 * @throws Exception
	 */
	public void getTPdaYcimei(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取要修改的数据
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getTPdaYcimeiToChange(HttpServletRequest request) throws Exception;

	/**
	 * 初始化要添加页面的数据
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getTPdaYcimeiToAdd(HttpServletRequest request) throws Exception;

	/**
	 * 修改数据
	 * 
	 * @param request
	 * @param tPdaYcimei
	 * @return
	 * @throws Exception
	 */
	public String updateTPdaYcimei(HttpServletRequest request, TPdaYcimei tPdaYcimei) throws Exception;

	/**
	 * 添加新数据
	 * 
	 * @param request
	 * @param tPdaYcimei
	 * @return
	 * @throws Exception
	 */
	public String addTPdaYcimei(HttpServletRequest request, TPdaYcimei tPdaYcimei) throws Exception;

	/**
	 * 删除数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String deleteTPdaYcimei(HttpServletRequest request) throws Exception;

	/**
	 * 导出excel
	 * 
	 * @param ssbm
	 * @param IMEI
	 * @return
	 * @throws Exception
	 */
	public String getOutExcel(HttpServletResponse response,HttpServletRequest request,String ssbm, String IMEI) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
