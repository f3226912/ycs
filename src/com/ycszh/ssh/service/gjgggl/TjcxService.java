package com.ycszh.ssh.service.gjgggl;

import javax.servlet.http.HttpServletRequest;

public interface TjcxService {

	public void getLscx(HttpServletRequest request) throws Exception;

	/**
	 * 查询流水详细
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getLscx_shwoDetail(HttpServletRequest request) throws Exception;

	/**
	 * 根据流水号查询广告证
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByLsh(HttpServletRequest request) throws Exception;

	/**
	 * 广告证档案查询
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getGgzdacx(HttpServletRequest request) throws Exception;

	/**
	 * 审核情况统计
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getShqktj(HttpServletRequest request) throws Exception;

	/**
	 * 审核情况统计_详细
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getShqktj_detail(HttpServletRequest request) throws Exception;

	/**
	 * 证件发放情况统计
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getZjffqktj(HttpServletRequest request) throws Exception;

	/**
	 * 证件发放情况统计_详细
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getZjffqktj_detail(HttpServletRequest request) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
