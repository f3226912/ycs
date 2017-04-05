package com.ycszh.ssh.service.yanche;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import sun.rmi.transport.proxy.HttpReceiveSocket;

import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;
import com.ycszh.ssh.hbm.yanche.TXbycCode;
import com.ycszh.ssh.hbm.yanche.TXbycGps;
import com.ycszh.ssh.hbm.yanche.TXbycGpsLog;

public interface YczdglService {
	
//======================================查验表管理
	/**
	 * 查看各部门签名用户列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getQmUserList(HttpServletRequest request,int currentpage) throws Exception;
	/**
	 * 初始化部门信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String initDeptNode(HttpServletRequest request) throws Exception;
	
	/**
	 * 显示用户电子签名及信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getUserDzqmInfo(HttpServletRequest request) throws Exception; 
	
	/**
	 * 查看用户电子签名信息列表
	 * @param request
	 * @return
	 * @throws Exception
	 *//*
	public List getUserDzqm(HttpServletRequest request,int currentpage) throws Exception;*/
	
	/**
	 * 修改用户电子签名
	 * @param fileName
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer updateDzqm(HttpServletRequest request,File file,String fileName) throws Exception;
	
	//电子签名管理	
	/**
	 * 获取查验员信息及签名文件
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getDzqmInfo(HttpServletRequest request) throws Exception;
	
	
	
	//查验表打印
	/**
	 * 获取查验表单信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Object getCybd(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取查验记录
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	public List getCyjl(HttpServletRequest request,int currentpage) throws Exception;
	
	
//==================================验车位置点管理
	/**
	 * 删除验车位子GPS信息
	 * @param request
	 * @throws Exception
	 */
	public Integer deleteGps(HttpServletRequest request) throws Exception;
	
	/**
	 * 提交编辑验车位子GPS信息
	 * @param request
	 * @throws Exception
	 */
	public Integer editYcGps(HttpServletRequest request,TXbycGps gps) throws Exception;
	
	/**
	 * 初始化编辑验车位子GPS信息
	 * @param request
	 * @throws Exception
	 */
	public String initEditycGps(HttpServletRequest request) throws Exception;
	
	/**
	 * 获得验车点位置信息列表
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	public List getYcgpsList(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 将实体表添加到日志表
	 * @param TXbycGps
	 */
	public Object getXclog(Object obj1, Object obj2) throws Exception;
	
//===================================查验记录表查看打印	
	
	
		
	
	
//====================================用户使用性质/车辆类型配置管理	
	/**
	 * 初始化用户类型性质编辑页面
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getEditYhlxxz(HttpServletRequest request) throws Exception;
	
	/**
	 * 获取用户账号使用性质/车辆类型配置列表信息
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getYcyhlxxzList(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 修改用户验车类型性质配置
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer updateYcyhlxxz(HttpServletRequest request) throws Exception;
	
//====================================验车字典管理	
	
	/**
	 * 固定字典项目查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<TXbycCode> getYcType(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 复检条件修改
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer updateFjtj(HttpServletRequest request) throws Exception;
	
	/**
	 * 使用性质/车辆类型查询（配置）
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	public List<TXbycCode> getSyxzOrCllx(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 查验项目/拍照规格查询（配置）
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	public List<TXbycCode> getCyxmOrPzgg(HttpServletRequest request,int currentpage) throws Exception;
	
	/**
	 * 获取查验依据（可根据查验代码值获取查验依据详情）
	 * @param request
	 * @return
	 */
	public List getCyyjOrPzgg(HttpServletRequest request) throws Exception; 
	
	/**
	 * 查验项目和拍照规格总目录及信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List getCyxmOrPzggML(HttpServletRequest request) throws Exception;
	
	/**
	 * 初始化编辑页面【使用性质/车辆类型】
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public TXbycCode getCllxOrSyxzEditInit(HttpServletRequest request) throws Exception;
	
	/**
	 * 初始化编辑页面【查验项/拍照规格】
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public TXbycCode getCyOrPzEditInit(HttpServletRequest request) throws Exception;
	
	/**
	 * 添加或者修改【使用性质/车辆类型】
	 * @param request
	 * @throws Exception
	 */
	public Integer saveOrupdateYanche(HttpServletRequest request) throws Exception;
	
	/**
	 * 上传文件
	 * @param request
	 * @param image
	 * @param imageFileName
	 */
	public Integer uploadFile(HttpServletRequest request, File image,String imageFileName) throws Exception;
	
	/**
	 * 判断代码值是否存在
	 * @param request
	 * @return
	 */
	public Integer exitesByDmz(HttpServletRequest request)  throws Exception;
	
	
	
}
