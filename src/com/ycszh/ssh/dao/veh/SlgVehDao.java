package com.ycszh.ssh.dao.veh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.TemporaryLicense;

public interface SlgVehDao extends BaseDao<DbjgZjxxb, String> {
	
	public void addObj(Object obj, HttpServletRequest request) throws Exception;
	
	public void updateObj(Object obj) throws Exception;
	
	/**
	 * 黑名单锁定
	 * @param request
	 * @param sfzmhm
	 * @return
	 * @throws Exception
	 */
	public String getIsBlackByFun(String ywlx, String ywzl, String hphm, String hpzl, String dsrsfzmhm, String dbrsfzmhm) throws Exception;
	
	/**
	 * 强制预约
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String jszYuyue(HttpServletRequest request, String xml) throws Exception;
	
	/**
	 * 验证指标函数
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getIsyanzzb(HttpServletRequest request, String xml) throws Exception;
	
	/**
	 * 写入新天地审计信息
	 * @return
	 * @throws Exception
	 */
	public String insertShenjiinfo(HttpServletRequest request, String lsh, String srcs) throws Exception;

	/**
	 * 获取指标信息
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getZblist(HttpServletRequest request, String xml) throws Exception;
	
	/**
	 * 验证是否需要指标
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getIsneedzb(HttpServletRequest request, String hphm, String hpzl, String ywlx, String ywyy, String sfzmhm, String xm, String sfzmmc) throws Exception;
	
	/**
	 * 退办验证
	 * @param request
	 * @param xml
	 * @return
	 * @throws Exception
	 */
	public String getTbyanz(HttpServletRequest request, String xml) throws Exception;
	
	/**
	 * 临牌接口
	 * @param lsh
	 * @return
	 * @throws Exception
	 */
	public TemporaryLicense getTemporaryLicense(String lsh) throws Exception;
}
