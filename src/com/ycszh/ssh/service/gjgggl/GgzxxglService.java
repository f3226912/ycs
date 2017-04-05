package com.ycszh.ssh.service.gjgggl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface GgzxxglService {

	public void getBusGgjgclsb(HttpServletRequest request) throws Exception;

	public void getBusGgjgclsb_cd(HttpServletRequest request) throws Exception;

	/**
	 * 根据流水号查询广告证
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByLsh(HttpServletRequest request) throws Exception;

	/**
	 * 添加打印记录信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyInfo(HttpServletRequest request) throws Exception;

	/**
	 * 错证重打
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyCzcd(HttpServletRequest request) throws Exception;

	/**
	 * 获取要注销的广告证数据集合
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getZxData(HttpServletRequest request) throws Exception;

	/**
	 * 初始化备案证数据
	 * 
	 * @param request
	 * @param lsh
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initalBazData(HttpServletRequest request, String lsh) throws Exception;

	/**
	 * 获取左前和右前图片
	 * 
	 * @param lsh
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public byte[] getImage(String lsh, String position) throws Exception;

	/**
	 * 生成备案证图片
	 * 
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public byte[] createBazPic(Map<String, Object> bazMap) throws Exception;

	public Map<String, String> getBusBases(String gjgsid, String gjgsName) throws Exception;

	/**
	 * 注销或恢复广告证
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zxOrfhBusCertifyInfo(HttpServletRequest request) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
