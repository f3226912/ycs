package com.ycszh.ssh.service.yanche;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.yanche.PdasmycChbase;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;

/**
 * @包名:com.ycszh.ssh.service.yanche
 * @文件名:PdasmycChbaseService.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-10
 * @描述:
 * @版本:V 1.0
 */
public interface PdasmycChbaseService {
	/**
	 * 增加或者更新车行机构帐户信息
	 * @param PdasmycChbase   车行机构帐户信息bean对象
	 * @param PdasmycChbaseId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdatePdasmycChbase(PdasmycChbase pdasmycChbase,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除车行机构帐户信息
	 * 
	 * @param id 车行机构帐户信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycChbase(String id) throws Exception;
	
	/**
	 * 删除车行机构帐户信息列表
	 * 
	 * @param ids 车行机构帐户信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycChbaseList(String[] ids) throws Exception;

	/**
	 * 获取所有车行机构帐户信息
	 * 
	 * @param request
	 * @return List<PdasmycChbase>
	 */
	public List<PdasmycChbase> getPdasmycChbaseList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取所有车行机构帐户信息根据ajax
	 * 
	 * @param request
	 * @return List<PdasmycChbase>
	 */
	public List<PdasmycChbase> getPdasmycChbaseList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取车行机构帐户信息
	 * 
	 * @param request
	*/
	public PdasmycChbase getPdasmycChbase(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取车行机构帐户信息
	 * 
	 * @param id 帐户ID
	*/
	public PdasmycChbase getPdasmycChbase(String id) throws Exception;
	
	
	/**
	 * 获取业务流水管理数据字典集合
	 * 
	 * @param dmlb 代码类别
	*/
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList(String dmlb) throws Exception;
	
	
}
