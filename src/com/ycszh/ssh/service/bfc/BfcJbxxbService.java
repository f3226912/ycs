package com.ycszh.ssh.service.bfc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.bfc.BfcJbxxb;

public interface BfcJbxxbService {
	/**
	 * 获取所有报废车信息
	 * 
	 * @param request
	 * @return List<SlgDrvXxcjb>
	 */
	public List<BfcJbxxb> getBfcJbxxbList(HttpServletRequest request,int currentPage) throws Exception;
	
	/**
	 * 增加或者更新报废车信息
	 * @param BfcJbxxb   报废车信息bean对象
	 * @param request
	 * @return
	 */
	public Integer UpdateBfcJbxxbZt(HttpServletRequest request) throws Exception;
}
