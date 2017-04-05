package com.ycszh.ssh.service.kcpb;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;

/**
 * @包名:com.ycszh.ssh.service.kcpb
 * @文件名:KgpbKcxxbService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public interface KgpbKcxxbService {

	/**
	 * 根据条件得到考场信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getKcInfo(HttpServletRequest request) throws Exception; 
	
	public KgpbKcxxb getKgpbKcxxb(String id) throws Exception;
	
	/**
	 * 得到考场信息（分页）
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	public List<KgpbKcxxb> getKgpbKcxxbList(HttpServletRequest request,
			int currentPage) throws Exception;
	
	/**
	 * 添加或者修改信息
	 * @param kgpbKcxxb
	 * @throws Exception
	 */
	public int addOrUpdateKcInfo(KgpbKcxxb kgpbKcxxb,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteKcInfo(String id,HttpServletRequest request) throws Exception;
}
