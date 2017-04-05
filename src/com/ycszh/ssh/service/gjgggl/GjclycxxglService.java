package com.ycszh.ssh.service.gjgggl;

import java.sql.Blob;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;

public interface GjclycxxglService {

	/**
	 * 查询广告喷涂业务申报集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void getBusGgjgclsbTotalList(HttpServletRequest request) throws Exception;

	/**
	 * 获取已审核但未办结的数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGgjgclsbTotalList_yshwbj(HttpServletRequest request) throws Exception;

	/**
	 * 按批次号查询广告喷涂业务申报集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void getBusGgjgclsbByPch(HttpServletRequest request) throws Exception;
	
	/**
	 * 按批次号打印数据
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByPch_dy(HttpServletRequest request) throws Exception;

	/**
	 * 查询广告喷涂业务申报集合(调配)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void getBusGgjgclsbTotalList_fp(HttpServletRequest request) throws Exception;

	/**
	 * 查询广告喷涂业务申报集合_批次(调配)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void getBusGgjgclsbTotalList_fp_pch(HttpServletRequest request) throws Exception;

	/**
	 * 按预约批次审核广告喷涂业务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch(HttpServletRequest request) throws Exception;

	/**
	 * 按预约批次分配验车部门
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch_fp(HttpServletRequest request) throws Exception;

	/**
	 * 按批次号退办广告喷涂业务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String tbBusGgjgclsbByPch(HttpServletRequest request) throws Exception;

	/**
	 * 按流水号批量审核广告喷涂业务
	 * 
	 * @param request
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByLsh(HttpServletRequest request) throws Exception;

	/**
	 * 按流水号查询审核广告喷涂业务
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByLsh(HttpServletRequest request) throws Exception;

	/**
	 * 根据流水号查询广告喷涂业务申报信息对应的图片信息
	 * 
	 * @param lsh
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public Blob getYcPic(String lsh, String position) throws Exception;

	/**
	 * 获取代办员集合
	 * 
	 * @param ggjgid
	 * @param zt
	 *            (状态T启用 F停用)
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgdlr> getBusGgjgdlrs(String ggjgid, String zt) throws Exception;

	/**
	 * 根据公交公司id查询公交车
	 * 
	 * @param gjgsid
	 * @return
	 * @throws Exception
	 */
	public List<BusVehicleBase> getBusVehicleBase(String gjgsid, String hphm, String hpzl, String shzt) throws Exception;

	/**
	 * 查询广告机构集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getGgjgMap(String gggjid, String ggjgName) throws Exception;

	/**
	 * 查询数字字典
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusSzjdMap(String dmz, String dmlb, String notE) throws Exception;

	/**
	 * 获取部门map
	 * 
	 * @param orgId
	 * @param notOrgId
	 *            (不包含)
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBmMap(String orgId, String notOrgId, boolean isAllBm) throws Exception;

	/**
	 * 获取用户所在科级部门
	 * 
	 * @param bm
	 *            (用户直接部门)
	 * @return
	 * @throws Exception
	 */
	public String[] getCurrentUserKjBm(String bm) throws Exception;

	/**
	 * 获取某部门对应于车辆管理处部门下的直接子部门
	 * 
	 * @param bm
	 * @return
	 * @throws Exception
	 */
	public String[] getCurrentBmInClglcbm(String bm) throws Exception;

	/**
	 * 修改广告喷涂业务数据
	 * 
	 * @param busGgjgclsb
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsb(HttpServletRequest request, BusGgjgclsb busGgjgclsb) throws Exception;

	public String checkStrNullAndReturn(Object str);
}
