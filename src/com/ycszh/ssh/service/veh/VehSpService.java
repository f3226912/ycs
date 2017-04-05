package com.ycszh.ssh.service.veh;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.hbm.veh.VehPodbsp;

public interface VehSpService {
	
	/**
	 * 采集审批查询
	 * @param request
	 * @param currentPage
	 * @return
	 * @throws Exception
	 */
	public List<SlgSpxx> getSlgSpxxList(HttpServletRequest request, int currentPage) throws Exception;
	/**
	 * 采集审批计数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public Integer getSlgSpxxCount(HttpServletRequest request) throws Exception;
	
	public Integer insertOrUpdateSlgSpxx(HttpServletRequest request, SlgSpxx slgSpxx, String cznr) throws Exception;
	
	public Integer deleteSlgSpxx(HttpServletRequest request, Long id, String cznr) throws Exception;
	
	public SlgSpxx getSlgSpxxById(HttpServletRequest request, Long id) throws Exception;
	
	/**
	 * 添加或修改配偶审批信息
	 * @param request
	 * @param vehPodbsp
	 * @return
	 * @throws Exception
	 */
	public Integer insertOrUpdatePoVehSpxx(HttpServletRequest request, VehPodbsp vehPodbsp) throws Exception;
	
	/**
	 * 配偶审批列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<VehPodbsp> getVehPodbspList(HttpServletRequest request, int currentPage) throws Exception;
	
	public VehPodbsp getVehPodbspById(HttpServletRequest request, Long id) throws Exception;
	
	public Integer deleteVelPoSpxx(HttpServletRequest request, Long id) throws Exception;
}
