package com.ycszh.ssh.service.dydj;

import java.io.File;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.ycszh.ssh.hbm.dydj.DydjUser;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;
import com.ycszh.ssh.hbm.dydj.DydjYhdbr;

public interface DydjYwsbspbService {
	/**
	 * 查询用户信息
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	public List<DydjUser> getUserList(HttpServletRequest request,int currentPage) throws Exception;
	/**
	 * 获取科级以下的部门
	 * @return
	 * @throws Exception
	 */
	public List getDeptList(String orgid, String uporg, String orgname) throws Exception;
	/**
	 * 新增用户信息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int addUserInfo(HttpServletRequest request,DydjUser user) throws Exception;
	
	  /**
	  * 冻结/解冻用户
	  */
	public Integer freezeUser(HttpServletRequest request,Integer id) throws Exception;
	/**
	 * 重置密码
	 */
	public Integer resetPwd(String id,HttpServletRequest request) throws Exception;
	
	/**
	 * 银行信息查询
	 */
	public List<DydjYhUser> getYHUserList(HttpServletRequest request,int currentPage) throws Exception;

	/**
	 * 获取银行信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<DydjYhUser>  getYhUser(HttpServletRequest request,DydjYhUser user) throws Exception;
	/**
	 * 添加银行信息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public DydjYhUser addYhUser(HttpServletRequest request,DydjYhUser user) throws Exception ;
	
	/**
	 * 清空银行用户的mac地址
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer clearnMac(String id,HttpServletRequest request) throws Exception ;
	public int editPhoto (DydjYhUser user,File file) throws Exception ;
	public byte[] getImageBlob(HttpServletRequest request, Integer tpm) throws Exception;
	public int editeBlobByByte(byte[] arry_byte,DydjYhUser yhUser) throws Exception;
	/**
	 * 详情
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DydjYhUser  getDydjYhUser(HttpServletRequest request) throws Exception;
	
	/**
	 * 银行代办人list
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getYhdbrList(HttpServletRequest request) throws Exception;
	
	/**
	 * 查询银行代办人信息
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DydjYhdbr getDydjYhdbrInfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 删除银行代办信息
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer delYhdbrInfo(HttpServletRequest request, String id) throws Exception;
	
	/**
	 * 添加银行代办人信息
	 * @param request
	 * @param yhdbr
	 * @return
	 * @throws Exception
	 */
	public Integer addYhdbrInfo(HttpServletRequest request, DydjYhdbr yhdbr) throws Exception;
	/**
	 * 修改银行信息
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public DydjYhUser editYhUser(HttpServletRequest request,DydjYhUser user) throws Exception;
	
	/**
	 * 修改银行代办人有效性
	 * @param request
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Integer updateYhdbrInfo(HttpServletRequest request, String id, String flag) throws Exception;
}