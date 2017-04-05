package com.ycszh.ssh.service.gjgggl;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.gjgggl.BusGgjgBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;

public interface GjbaxxspService {

	/**
	 * 查询备案公交车辆集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BusVehicleBase> getBusVehicleBaseList(HttpServletRequest request) throws Exception;

	/**
	 * 根据xh查询车辆信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public BusVehicleBase getBusVehicleBase(HttpServletRequest request) throws Exception;

	/**
	 * 查询车辆信息
	 * 
	 * @param hphm
	 * @param hpzl
	 * @param shzt
	 * @return
	 * @throws Exception
	 */
	public List<BusVehicleBase> getBusVehicleBaseList(String hphm, String hpzl, String shzt) throws Exception;

	/**
	 * 根据xh查询车辆信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public Blob getXszPic(String xh) throws Exception;

	/**
	 * 修改车辆信息
	 * 
	 * @param bus
	 * @return
	 * @throws Exception
	 */
	public String updateBusVehicleBase(BusVehicleBase bus, HttpServletRequest request) throws Exception;

	/**
	 * 退办申报的车辆数据
	 * 
	 * @param bus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String cancelBusVehicleBase(HttpServletRequest request) throws Exception;

	/**
	 * 查询号牌种类
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getHpzlMap(boolean hasLine) throws Exception;

	/**
	 * 查询车辆类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getCllxMap(boolean hasLine) throws Exception;

	/**
	 * 获取广告公司map
	 * 
	 * @param hasLine
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getGggsMap(boolean hasLine) throws Exception;

	/**
	 * 验证车档(查镜像库)
	 * 
	 * @param bus
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkCd(BusVehicleBase bus) throws Exception;

	/**
	 * 查询广告机构数据集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgBase> getBusGgjgBaseList(HttpServletRequest request) throws Exception;

	/**
	 * 查询公交公司名称集合
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGjgsMap(String name) throws Exception;

	/**
	 * 查询广告机构名称集合
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGjjgMap(String name) throws Exception;

	/**
	 * 更新广告机构
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgBase(HttpServletRequest request) throws Exception;

	/**
	 * 初始化广告机构登录密码
	 * 
	 * @param ggjgid
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgBaseByCzlx(HttpServletRequest request) throws Exception;

	/**
	 * 获取广告机构代理人集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgdlr> ggjgDbrList(HttpServletRequest request) throws Exception;

	/**
	 * 查询广告机构集合
	 * 
	 * @param ggjgid
	 * @param ggjgmc
	 * @param zzjgdm
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgBase> getBusGgjgBases(String ggjgid, String ggjgmc, String zzjgdm) throws Exception;

	/**
	 * 更新广告机构代办人员信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateGgjgDbryByCzlx(HttpServletRequest request) throws Exception;

	/**
	 * 获取加密密码
	 * 
	 * @param str
	 * @param key
	 *            (密钥)
	 * @return
	 * @throws Exception
	 */
	public String getPwd(String str, String key) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
