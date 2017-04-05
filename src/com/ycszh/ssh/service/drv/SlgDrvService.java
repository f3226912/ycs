package com.ycszh.ssh.service.drv;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgYwlx;
import com.ycszh.ssh.hbm.drv.SlgZjxxb;

/**
 * @包名:com.ycszh.ssh.service.drv
 * @文件名:SlgDrvService.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public interface SlgDrvService {
	/**
	 * 增加或者更新驾驶证采集信息
	 * @param SlgDrvXxcjb   驾驶证采集信息bean对象
	 * @param SlgDrvXxcjbId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdateSlgDrvXxcjb2(SlgDrvXxcjb slgDrvXxcjb,SlgZjxxb slgZjxxb,HttpServletRequest request,File file1,File file2,File file01,File file02) throws Exception;
	public Integer insertOrUpdateSlgDrvXxcjb(SlgDrvXxcjb slgDrvXxcjb,SlgZjxxb slgZjxxb,HttpServletRequest request,File file1,File file2,File file01,File file02) throws Exception;
	
	/**
	 * 删除驾驶证采集信息
	 * 
	 * @param cjid 驾驶证采集信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteSlgDrvXxcjb(String cjid) throws Exception;
	
	/**
	 * 删除驾驶证采集信息列表
	 * 
	 * @param cjids 驾驶证采集信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteSlgDrvXxcjbList(String[] cjids) throws Exception;

	/**
	 * 获取所有驾驶证采集信息
	 * 
	 * @param request
	 * @return List<SlgDrvXxcjb>
	 */
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList2(HttpServletRequest request,int currentPage) throws Exception;
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 驾驶证采集信息导出
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<SlgDrvXxcjb> getSlgDrvXxcdjbExport(HttpServletRequest request, int currentPage) throws Exception;
	/**
	 * 获取所有驾驶证采集信息根据ajax
	 * 
	 * @param request
	 * @return List<SlgDrvXxcjb>
	 */
	public List<SlgDrvXxcjb> getSlgDrvXxcjbList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取驾驶证采集信息
	 * 
	 * @param request
	*/
	public SlgDrvXxcjb getSlgDrvXxcjb(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取驾驶证采集信息
	 * 
	 * @param cjid 采集ID
	*/
	public SlgDrvXxcjb getSlgDrvXxcjb(String cjid) throws Exception;
	
	/**
	 * 获取驾驶证证件信息
	 * 
	 * @param cjid 采集ID
	*/
	public SlgZjxxb getSlgZjxxb(String cjid) throws Exception;
	
	/**
	 * 获取业务类型数据集合
	 * 
	 * @param request
	 * @return List<SlgYwlx>
	 */
	public List<SlgYwlx> getSlgYwlxList(String veh_drv,String ywms_main) throws Exception;
	
	/**
	 * 获取业务类型数据集合
	 * 
	 * @param request
	 * @return List<SlgYwlx>
	 */
	public List<SlgYwlx> getSlgYwlxList() throws Exception;
	
	/**
	 * 获取档案信息
	 * 
	 * @param request
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	public List getDaxxInfo(String hm,String type) throws Exception;
	
	/**
	 * 获取统一版流水号信息
	 * 
	 * @param request
	 * @return List
	 */
	public Integer getjia6in1(String lsh) throws Exception;
	
	/**
	 * 获取国籍集合
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public List getGj() throws Exception;
	
	/**
	 * 获取国籍信息
	 * @return list
	 */
	public String getGjInfo(String gj) throws Exception;
	
	/**
	 * 获取驾驶证状态集合
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public List getZt() throws Exception;
	
	/**
	 * 获取驾驶证状态集合
	 * @return list
	 */
	public String getZtInfo(String zt) throws Exception;
	
	/**
	 * 获取行政区划集合
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public List getXzqh() throws Exception;
	
	/**
	 * 读取数据字典
	 * @param dmz 
	 * @param dmms2
	 * @param dmlb
	 * @param veh_drv
	 * @return
	 * @throws Exception
	 */
	public List<SlgSjzd> getYwlxYwyy(HttpServletRequest request, String dmz, String dmms2, String dmlb, String veh_drv, String is_flag) throws Exception;
	
	/**
	 * 查询所有部门
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getDeptList(String orgid, String uporg, String orgname) throws Exception;
	
	/**
	 * 查询本部门以及下级所有部门
	 * @param deptid
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getDeptBelowList(String deptid) throws Exception;
	
	/**
	 * 判断是否违法未处理超过10宗并且记分超过12分
	 * @param request
	 * @param hphm
	 * @param hpzl
	 * @return
	 * @throws Exception
	 */
	public String getIsChaoTen(HttpServletRequest request, String hphm, String hpzl, String jszh) throws Exception;
	
	/**
	 * 驾驶证审核验证
	 * @param request
	 * @param hphm
	 * @param hpzl
	 * @return
	 * @throws Exception
	 */
	public Integer drvShenheCheck(HttpServletRequest request, String shlsh, String sfzmhm,String sfzmmc,String xm,String dabh) throws Exception;
	
	/**
	 * 驾驶证审核
	 * @param request
	 * @param hphm
	 * @param hpzl
	 * @return
	 * @throws Exception
	 */
	public Integer drvShenhe(HttpServletRequest request, String shlsh, String shjg,String cjid,String bz1,String bz2) throws Exception;
	
	/*
	 * 驾驶证业务受理审核查询统计
	 */
	public void initDrvtj(HttpServletRequest request, int currentPage) throws Exception;
	
	/*
	 * 驾驶证业务受理审核查询统计查看详情
	 */
	public void initDrvtjView(HttpServletRequest request, int currentPage) throws Exception;
	
	public Integer getSpxx(HttpServletRequest request, String sfzmhm, String xm) throws Exception;
	
	public String getPro(HttpServletRequest request, String sfzmhm, String xm) throws Exception;
}
