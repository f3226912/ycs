package com.ycszh.ssh.service.yanche;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.yanche.PdasmycSjzd;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFieldgl;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlow;

public interface YwlsglVehFlowService {
	/**
	 * 增加或者更新流水管理信息
	 * @param YwlsglVehFlow   流水管理信息bean对象
	 * @param YwlsglVehFlowId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdateVehicleTempMidOut(VehicleTempMidOut vehicleTempMidOut,HttpServletRequest request) throws Exception;
	
	/**
	 * 增加或者更新流水管理信息
	 * @param YwlsglVehFlow   流水管理信息bean对象
	 * @param YwlsglVehFlowId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdateVehicleTempMidIn(VehicleTempMidIn vehicleTempMidIn,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除流水管理信息
	 * 
	 * @param id 流水管理信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteYwlsglVehFlow(String lsh) throws Exception;
	
	/**
	 * 删除流水管理信息列表
	 * 
	 * @param ids 流水管理信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deleteYwlsglVehFlowList(String[] lshs) throws Exception;

	/**
	 * 获取所有流水管理信息
	 * 
	 * @param request
	 * @return List<YwlsglVehFlow>
	 */
	public List<YwlsglVehFlow> getYwlsglVehFlowList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取受理信息特殊字符修改
	 * 
	 * @param request
	 * @return List<YwlsglVehFlow>
	 */
	public List<VehicleTempMidIn> getYwlsglVehFlowListt(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取所有流水管理信息根据ajax
	 * 
	 * @param request
	 * @return List<YwlsglVehFlow>
	 */
	public List<YwlsglVehFlow> getYwlsglVehFlowList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param request
	*/
	public YwlsglVehFlow getYwlsglVehFlow(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param id 流水ID
	*/
	public YwlsglVehFlow getYwlsglVehFlow(String lsh) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param id 流水ID
	*/
	public YwlsglVehFlow getYwlsglVehFlow2(String lsh) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param id 流水ID
	*/
	public PdasmycTempMidOutN getPdasmycTempMidOutN(String lsh) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param id 流水ID
	*/
	public VehicleTempMidOut getVehicleTempMidOut(String lsh) throws Exception;
	
	/**
	 * 获取流水管理信息
	 * 
	 * @param id 流水ID
	*/
	public VehicleTempMidIn getVehicleTempMidIn(String lsh) throws Exception;
	
	
	/**
	 * 流水退办
	 * 
	 * @param lsh 流水号
	 * @return Integer 0成功 1异常
	*/
	public Integer lstb(String lsh,HttpServletRequest request) throws Exception;
	
	/**
	 * 受理岗流水退回查验岗
	 * 
	 * @param lsh 流水号
	 * @return Integer 0成功 1异常
	*/
	public Integer lsthyj(String lsh,HttpServletRequest request) throws Exception;
	
	/**
	 * 受理岗流水转嫌疑车
	 * 
	 * @param lsh 流水号
	 * @return Integer 0成功 1异常
	*/
	public Integer lszxyc(String lsh,HttpServletRequest request) throws Exception;
	
	/**
	 * 受理岗流水暂停与恢复
	 * 
	 * @param lsh 流水号
	 * @return Integer 0成功 1异常
	*/
	public Integer lszthf(String lsh,HttpServletRequest request) throws Exception;
	
	/**
	 * 获取所有流水管理信息根据
	 * 
	 * @param request
	 * @return List<YwlsglVehFlow>
	 */
	public List<YwlsglVehFlow> getYwlsglVehFlowListByYj(HttpServletRequest request,int currentPage) throws Exception;
	
	//获取数据字典值
	public List<PdasmycSjzd> getPdasmycSjzdListByHql(String hql) throws Exception;
	
	
	/**
	 * 获取所有流水管理列表
	 * 
	 * @param request
	 * @return List<YwlsglVehFlow>
	 */
	public List<PdasmycTempMidOutN> getPdasmycTempMidOutNList(HttpServletRequest request,int currentPage) throws Exception;
	
	//获取普通岗或领导岗修改字段
	public List<YwlsglVehFieldgl> getYwlsglVehFieldglList(String ld,String gw) throws Exception;
	
	//获取嫌疑车原因
	@SuppressWarnings("rawtypes")
	public List getXycyyList() throws Exception;
	
	//获取暂停原因
	@SuppressWarnings("rawtypes")
	public List getZtyyList() throws Exception;
	
	//获取退办原因
	@SuppressWarnings("rawtypes")
	public List getTbyyList(String gw) throws Exception;
	
	//获取退查验岗原因
	@SuppressWarnings("rawtypes")
	public List getTcygyyList() throws Exception;
	
	@SuppressWarnings("rawtypes")
	public List getPTMOListBySql(String sql) throws Exception;
}
