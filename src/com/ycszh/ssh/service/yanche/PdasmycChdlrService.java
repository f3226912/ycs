package com.ycszh.ssh.service.yanche;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.yanche.PdasmycChdlr;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;

/**
 * @包名:com.ycszh.ssh.service.yanche
 * @文件名:PdasmycChdlrService.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-16
 * @描述:
 * @版本:V 1.0
 */
public interface PdasmycChdlrService {
	/**
	 * 增加或者更新代理人信息
	 * @param PdasmycChdlr   代理人信息bean对象
	 * @param PdasmycChdlrId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdatePdasmycChdlr(PdasmycChdlr pdasmycChdlr,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除代理人信息
	 * 
	 * @param id 代理人信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycChdlr(String id) throws Exception;
	
	/**
	 * 删除代理人信息列表
	 * 
	 * @param ids 代理人信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycChdlrList(String[] ids) throws Exception;

	/**
	 * 获取所有代理人信息
	 * 
	 * @param request
	 * @return List<PdasmycChdlr>
	 */
	public List<PdasmycChdlr> getPdasmycChdlrList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取所有代理人信息根据ajax
	 * 
	 * @param request
	 * @return List<PdasmycChdlr>
	 */
	public List<PdasmycChdlr> getPdasmycChdlrList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取代理人信息
	 * 
	 * @param request
	*/
	public PdasmycChdlr getPdasmycChdlr(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取代理人信息
	 * 
	 * @param id 帐户ID
	*/
	public PdasmycChdlr getPdasmycChdlr(String id) throws Exception;
	
	
	/**
	 * 获取业务流水管理数据字典集合
	 * 
	 * @param dmlb 代码类别
	*/
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList(String dmlb) throws Exception;
	
	/**
	 * 修改代理人信息审核状态
	 * 
	 * @param id	代理人信息id
	 * @param shzt	审核状态
	*/
	public Integer dlrshzt(HttpServletRequest request,String id,String shzt,String shbz) throws Exception;
}
