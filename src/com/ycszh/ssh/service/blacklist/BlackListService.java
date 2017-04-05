package com.ycszh.ssh.service.blacklist;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyh;
import com.ycszh.ssh.hbm.blacklist.DbjgSjzd;

public interface BlackListService {
	/**
	 * 获取黑名单信息列表（含分页和模糊查询）
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	public List<DbjgHmdyh> getBlackYHList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
     * 根据xh删除黑名单用户信息
     */;
	public Integer delHmyhByxh(String xh,HttpServletRequest request) throws Exception;
	/**
	 * 添加黑名单用户信息
	 * @param request
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public int addHmdUser(HttpServletRequest request,DbjgHmdyh hmYh) throws Exception;
	/**
	 * 修过黑名单用户状态
	 * @param hmdyh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer editHmyhByxh(DbjgHmdyh  hmdyh,HttpServletRequest request) throws Exception;
	
	/**
	 * 查询黑名单锁定/解锁原因列表字典
	 */
	public List<DbjgSjzd> getHmdYyDict(String type,boolean flag) throws Exception;
	/**
	 * 获取未备案代办员日，月代办字典
	 * @return
	 * @throws Exception
	 */
	public List<DbjgSjzd> getHmdDbDict(String type) throws Exception;
	/**
	 * 根据xh 获取 黑名单用户信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DbjgHmdyh getHmdyhByxh(HttpServletRequest request) throws Exception;
	/**
	 * 修改字典
	 * @param pageHmd
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer editDbjgSjzdByxh(DbjgSjzd dict,HttpServletRequest request) throws Exception;
	/**
	 * 修改锁定或解锁原因的代码说明
	 * @param xh
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer editDbjgSdbyxh(String xh,HttpServletRequest request) throws Exception;
	/**
	 * 生成黑名单解锁/锁定的代码值
	 * @param type ：1表示锁定;2表示解锁
	 * @return
	 * @throws Exception
	 */
	public String getDictCode(String type) throws Exception;
	/**
	 * 生成一年后的有效期止
	 * @return
	 * @throws Exception
	 */
	public Date getAutoYxqz() throws Exception;
	/**
	 * 添加字典信息
	 * @param request
	 * @param  
	 * @return
	 * @throws Exception
	 */
	public int addDict(HttpServletRequest request,DbjgSjzd dict) throws Exception;
	/**
     * 根据xh删除字典信息
     */
	public Integer delDictByxh(String xh,HttpServletRequest request) throws Exception;
}