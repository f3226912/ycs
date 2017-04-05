package com.ycszh.ssh.service.ddc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.ddc.VehtypeElectro;

public interface IVehtypeElectroService {
	
	/**
	 * 分页查询
	 * @param request
	 * @param currentPage
	 * @param cztype
	 * @return
	 * @throws Exception
	 */
	public List<VehtypeElectro> getVehtypeElectroList(HttpServletRequest request, int currentPage, String cztype) throws Exception;
	
	/**
	 * 编辑电动车型
	 * @param request
	 * @param veElectro
	 * @return 0-对象为空  1-编辑成功  2-车辆型号和电机型号已存在
	 * @throws Exception
	 */
	public String editVehtypeElectro(HttpServletRequest request, VehtypeElectro vehElectro) throws Exception;
	
	/**
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<VehtypeElectro> getVehtypeElectroByCondition(HttpServletRequest request) throws Exception;
	
	/**
	 * 删除信息
	 * @param request
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public String delVehtypeElectro(HttpServletRequest request, String xh) throws Exception;
	

}
