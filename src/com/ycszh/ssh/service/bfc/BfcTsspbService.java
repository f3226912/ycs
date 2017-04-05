package com.ycszh.ssh.service.bfc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.bfc.BfcTsspb;

/**
 * @包名:com.ycszh.ssh.service.bfc
 * @文件名:BfcTsspbService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-13
 * @描述:
 * @版本:V 1.0
 */
public interface BfcTsspbService {

	
	/**
	 * 添加报废车特殊审批
	 * @param bfcTsspb
	 * @return
	 */
	public int addBfcTsspb(BfcTsspb bfcTsspb,HttpServletRequest request);
	
	/**
	 * 根据XH 取得报废车审批信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public BfcTsspb getBfcTsspbByXh(String xh) throws Exception;
	
	/**
	 * 根据条件查询报废车特殊审批信息
	 * @param request
	 * @return
	 */
	public void getBfcTsspbInfo(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 根据条件查询报废车特殊审批信息
	 * @param request
	 * @return
	 */
	public void getBfcTsspbInfo2(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 根据ID 删除
	 * @param id
	 * @return
	 */
	public int delBfcTsspbInfo(String id,HttpServletRequest request) throws Exception;
	
	/**
	 * 根据SQL查询
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List getBfcTsspbInfoBySql(String sql) throws Exception;
	
	/**
	 * 根据条件查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getBfcTsspbInfoByPar(HttpServletRequest request) throws Exception;
	
	/**
	 * 修改报废车信息
	 * @param bfcTsspb
	 * @return
	 * @throws Exception
	 */
	public void updateTsspbInfo(BfcTsspb bfcTsspb,HttpServletRequest request) throws Exception;
	
}
