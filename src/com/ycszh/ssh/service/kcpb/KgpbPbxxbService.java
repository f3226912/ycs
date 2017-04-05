package com.ycszh.ssh.service.kcpb;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.kgpb.KgpbPbxxb;

/**
 * @包名:com.ycszh.ssh.service.kcpb
 * @文件名:KgpbPbxxbService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public interface KgpbPbxxbService {

	/**
	 * 根据条件查询排班信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Map<String, Map> getPbInfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 修改排班信息
	 * @param pb
	 * @return
	 */
	public int updatePbInfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 发送短信
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public int dxfs(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询排班信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public KgpbPbxxb getPbInfo(String id) throws Exception;
	
}
