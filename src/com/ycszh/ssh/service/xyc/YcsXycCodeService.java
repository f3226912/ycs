package com.ycszh.ssh.service.xyc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.xyc.YcsXycCode;

public interface YcsXycCodeService {
	/**
	 * 增加或者更新嫌疑车字典信息
	 * @param YcsXycCode   嫌疑车字典信息bean对象
	 * @param YcsXycCodeId 嫌疑车字典ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdateYcsXycCode(YcsXycCode ycsXycCode,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除嫌疑车字典信息
	 * 
	 * @param YcsXycCodeUd 嫌疑车字典信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteYcsXycCode(HttpServletRequest request, String ycsXycCodeId) throws Exception;
	
	/**
	 * 删除嫌疑车字典信息列表
	 * 
	 * @param YcsXycCodeIds 嫌疑车字典信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteYcsXycCodeList(HttpServletRequest request, String[] ycsXycCodeIds) throws Exception;

	/**
	 * 获取所有嫌疑车字典信息
	 * 
	 * @param request
	 * @return List<YcsXycCode>
	 */
	public List<YcsXycCode> getYcsXycCodeList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取嫌疑车字典信息
	 * 
	 * @param request
	*/
	public YcsXycCode getYcsXycCode(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取嫌疑车字典信息
	 * 
	 * @param YcsXycCodeId 嫌疑车字典ID
	*/
	public YcsXycCode getYcsXycCode(String ycsXycCodeId) throws Exception;
	
	public List getDmlbList() throws Exception;
}
