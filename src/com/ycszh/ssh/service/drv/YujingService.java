package com.ycszh.ssh.service.drv;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.drv.SlgSjzd;

/**
 * @包名:com.example.ssh.service
 * @文件名:YujingService.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-20
 * @描述:
 * @版本:V 1.0
 */
public interface YujingService {
	
	/**
	 * 驾驶证预警查询
	 * @param request
	 * @param cztype 操作类型 （分页查询<query>和导出查询<export>）
	 * @param currentPage 当前页
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getEsDrvFlowList(HttpServletRequest request, String cztype, int currentPage) throws Exception;
	
	public List<SlgSjzd> getSlgSjzdList(HttpServletRequest request, String dmlb, String dmz) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getVehFlowList(HttpServletRequest request, String cztype, int currentPage) throws Exception;
	
	/**
	 * 机动车代码值翻译（业务类型）
	 * @param request
	 * @param xtlb
	 * @param dmlb
	 * @param dmz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getEsVehCode(HttpServletRequest request, String xtlb, String dmlb, String dmz)  throws Exception; 
	
	/**
	 * 机动车办理部门
	 * @param request
	 * @param sjbm
	 * @param glbm
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getVehDepartment(HttpServletRequest request, String sjbm, String glbm) throws Exception;

}
