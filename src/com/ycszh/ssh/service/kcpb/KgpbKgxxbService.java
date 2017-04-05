package com.ycszh.ssh.service.kcpb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.kgpb.KgpbKgxxb;

/**
 * @包名:com.ycszh.ssh.service.kcpb
 * @文件名:KgpbKgxxbService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public interface KgpbKgxxbService {

	
	/**
	 * 根据SQL 查询信息
	 * @param sql
	 * @return
	 */
	public List getKgInfoBySql(String sql) throws Exception;
	
	
	/**
	 * 根据条件得到考官信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getKgInfo(HttpServletRequest request) throws Exception; 
	
	
	public KgpbKgxxb getKgInfo(String id) throws Exception;
	
	/**
	 * 得到考官信息（分页）
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	public List<KgpbKgxxb> getKgInfoList(HttpServletRequest request,
			int currentPage) throws Exception;
	
	/**
	 * 添加或者修改信息
	 * @param kgpbKcxxb
	 * @throws Exception
	 */
	public int addOrUpdateKgInfo(KgpbKgxxb kgpbKgxxb,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteKgInfo(String id,HttpServletRequest request) throws Exception;
	
}
