package com.ycszh.ssh.service.xyc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.xyc.YcsXycVeh;

public interface YcsXycVehService {
	/**
	 * 增加或者更新嫌疑车信息
	 * @param YcsXycVeh   嫌疑车信息bean对象
	 * @param YcsXycVehId 嫌疑车ID
	 * @param request
	 * @return
	 */
	public String insertOrUpdateYcsXycVeh(YcsXycVeh ycsXycVeh,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除嫌疑车信息
	 * 
	 * @param YcsXycVehId 嫌疑车信息ID
	 * @return Integer 0成功 1异常
	*/
	public String deleteYcsXycVeh(HttpServletRequest request, String ycsXycVehId) throws Exception;

	/**
	 * 获取所有嫌疑车信息
	 * 
	 * @param request
	 * @return List<YcsXycVeh>
	 */
	public List<YcsXycVeh> getYcsXycVehList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取嫌疑车信息
	 * 
	 * @param request
	*/
	public YcsXycVeh getYcsXycVeh(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取嫌疑车信息
	 * 
	 * @param YcsXycVehId 嫌疑车ID
	*/
	public YcsXycVeh getYcsXycVeh(String ycsXycVehId) throws Exception;
	
	/**
	 * 普通SQL查询
	 * 
	 * @param sql		SQL语句
	 * @param clasz		Class对象
	 * @throws Exception
	 */
	List findSQL(final String sql,final Class clasz) throws Exception;
	
	/**
	 * 普通SQL查询
	 * 
	 * @param sql		SQL语句
	 * @return
	 * @throws Exception
	 */
	List findSQL(final String sql) throws Exception;
	
}
