package com.ycszh.ssh.service.yanche;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;
import com.ycszh.ssh.hbm.yanche.VDataCheck;
import com.ycszh.ssh.hbm.yanche.VVehOrgYcs;
import com.ycszh.ssh.hbm.yanche.VVehUserYcs;

/**
 * @包名:com.ycszh.ssh.service.yanche
 * @文件名:PdasmycVehPcbService.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public interface PdasmycVehPcbService {
	/**
	 * 增加或者更新验车分配信息
	 * @param PdasmycVehPcb   验车分配信息bean对象
	 * @param PdasmycVehPcbId 用户ID
	 * @param request
	 * @return
	 */
	public Integer insertOrUpdatePdasmycVehPcb(PdasmycVehPcb pdasmycVehPcb,HttpServletRequest request) throws Exception;
	
	/**
	 * 删除验车分配信息
	 * 
	 * @param id 验车分配信息ID
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycVehPcb(String id) throws Exception;
	
	/**
	 * 删除验车分配信息列表
	 * 
	 * @param ids 验车分配信息ID数组
	 * @return Integer 0成功 1异常
	*/
	public Integer deletePdasmycVehPcbList(String[] ids) throws Exception;

	/**
	 * 获取所有验车分配信息
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取所有验车分配信息(修改预约部门)
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbList2(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 验车未拍照超时审批(科)
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbList3(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 验车未拍照超时审批(处)
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbList4(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取所有验车分配信息根据ajax
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取所有验车分配信息根据
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbViewList(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取所有验车分配信息根据
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<PdasmycVehPcb> getPdasmycVehPcbViewList2(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取验车分配信息
	 * 
	 * @param request
	*/
	public PdasmycVehPcb getPdasmycVehPcb(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取验车分配信息
	 * 
	 * @param id 帐户ID
	*/
	public PdasmycVehPcb getPdasmycVehPcb(String id) throws Exception;
	
	/**
	 * 获取指定部门人员
	 * 
	 * @param 
	*/
	public List<VVehUserYcs> getVVehUserYcsList(HttpServletRequest request) throws Exception;
	
	/**
	 * 分配验车民警
	 * 
	 * @param ids	流水号集合
	 * @param szmj	选择民警
	 * @param yjsj	预计上门验车时间
	 * @param fpms	分配描述
	*/
	public Integer fenpeiycmj(String[] ids,String szmj,String yjsj,String fpms,HttpServletRequest request) throws Exception;
	
	/**
	 * 分配验车民警
	 * 
	 * @param ids	流水号集合
	 * @param tbyyms	退办描述
	*/
	public Integer fenpeitb(String[] ids,String tbyyms,HttpServletRequest request) throws Exception;
	
	/**
	 * 获取本部门验车任务管理列表
	 * 
	 * @param 
	*/
	public List<VVehUserYcs> getYcrwList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取民警验车任务管理列表
	 * 
	 * @param 
	*/
	public List<PdasmycVehPcb> getYcrwList2(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 根据验车批次号获取验车任务列表
	 * 
	 * @param 
	*/
	public List<PdasmycVehPcb> getYcrwList3(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 获取民警验车任务管理列表
	 * 
	 * @param 
	*/
	public List<PdasmycVehPcb> getYcrwList4(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 根据验车批次号获取验车任务列表
	 * 
	 * @param 
	*/
	public List<PdasmycVehPcb> getYcrwList5(HttpServletRequest request, HttpServletResponse response,int currentPage) throws Exception;
	
	/**
	 * 获取验车分配信息
	 * 
	 * @param lsh 流水号
	 * @param clsbdh 车辆识别代号
	*/
	public PdasmycVehPcb getPdasmycVehPcb(String lsh,String clsbdh) throws Exception;
	
	/**
	 * 获取验车分配信息
	 * 
	 * @param lsh 流水号
	 * @param clsbdh 车辆识别代号
	*/
	public PdasmycVehPcb getPdasmycVehPcb2(String lsh) throws Exception;
	
	
	/**
	 * 获取所有本人验车结果修改列表
	 * 
	 * @param request
	 * @return List<PdasmycVehPcb>
	 */
	public List<VDataCheck> getVDataCheckList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 验车结果修改
	 * 
	 * @param 
	*/
	public Integer updateVDataCheck(HttpServletRequest request) throws Exception;
	
	//获取所有验车部门
	public List<VVehOrgYcs> getOrgList() throws Exception;
	
	//修改预约部门
	public Integer updateYybm(HttpServletRequest request) throws Exception;
	
	//验车未拍照超时审批
	public Integer updateShenPi(HttpServletRequest request) throws Exception;
	
	//获取验车信息outn表
	public PdasmycTempMidOutN getPdasmycTempMidOutN(String lsh) throws Exception;
	
	//获取暂停原因
	@SuppressWarnings("rawtypes")
	public List getBhgyyList() throws Exception;
}
