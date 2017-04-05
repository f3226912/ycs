package com.ycszh.ssh.service.drv;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.drv.SlgSpxx;

public interface SlgSpxxService {
	
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
	
	public SlgSpxx getSlgSpxxByCondition(HttpServletRequest request) throws Exception;
	
	public SlgSpxx getSlgSpxxByCondition(HttpServletRequest request, String sfzmhm, String name, String splx, String splx2, String yxsj) throws Exception;
	
	public void updateSlgSpxx(HttpServletRequest request, String sfzmhm, String xm, String splx, String splx2, String cjsj) throws Exception;

}
