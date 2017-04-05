package com.ycszh.ssh.service.dydj;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbTemp;

public interface IDydjService {
	
	/**
	 * 根据流水号查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public DydjYwsbspbTemp getDydjYwwbspByLsh(HttpServletRequest request, String lsh) throws Exception;
	
	/**
	 * 查找科级部门
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getDeptOrgId(HttpServletRequest request) throws Exception;
	
	/**
	 * 读取图片
	 * @param tpm
	 * @return
	 * @throws Exception
	 */
	public byte[] getImageBlob(HttpServletRequest request, Integer tpm) throws Exception;
	/**
	  * 查出预警数据
	  * @throws Exception
	  */
	public List<DydjSbInfo> getWarnInfo(HttpServletRequest request,int currentPage) throws Exception;
	/**
	 * 分页查询
	 * @param request
	 * @param currentpage
	 * @param cztype
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List getDydjsbspList(HttpServletRequest request, int currentpage, String cztype) throws Exception;
	
	/**
	 * 读取用户公章图片
	 * @param request
	 * @param yzyhdm
	 * @return
	 * @throws Exception
	 */
	public byte[] getUserImageBlob(HttpServletRequest request, String yzyhdm) throws Exception;

}
