package com.ycszh.ssh.service.veh;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.veh.DbjgZjxxbWzp;
import com.ycszh.ssh.hbm.veh.EsVehicle;
import com.ycszh.ssh.hbm.veh.SlgSjzdWzp;
import com.ycszh.ssh.hbm.veh.VehicleTempMidTest;

public interface SlgVehWpzService {
	/**
	 * 受理详情查询
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<DbjgZjxxbWzp> getSlxqWpz(HttpServletRequest request) throws Exception;
	
	/**
	 * 受理信息查询
	 * @param request
	 * @param currentpage
	 * @return
	 * @throws Exception
	 */
	public List<DbjgZjxxbWzp> getSlCxListWpz(HttpServletRequest request,int currentpage ,String cztype) throws Exception;
	
	/**
	 * 获取经办人
	 * @param orgid
	 * @param uporg
	 * @param orgname
	 * @return
	 * @throws Exception
	 */
	public List getDeptList(String orgid, String uporg, String orgname) throws Exception;
	
	/**
	 * 根据号牌号码、种类获取机动车登记信息和技术参数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public EsVehicle finjdcslInfo(HttpServletRequest request) throws Exception;
	
	/**
	 * 添加受理信息
	 * @param request
	 * @param dbZjxxb	受理表(证件信息)
	 * @param ptdo		临时表（登记信息、技术参数）
	 * @param file1		当事人身份证头像
	 * @param file2		代办人身份证头像
	 * @return
	 * @throws Exception
	 */
	public Integer insertJdcslWpz(HttpServletRequest request,DbjgZjxxbWzp dbZjxxb,VehicleTempMidTest ptdo,
			File file1,File file2) throws Exception;
	
	/**
	 * 加载业务类型业务原因
	 * @param request
	 * @param dmz
	 * @param dmms2
	 * @param dmlb
	 * @param veh_drv
	 * @param is_flag
	 * @return
	 * @throws Exception
	 */
	public List<SlgSjzdWzp> getYwlxYwyy_wpz(HttpServletRequest request, String dmz, String dmms2, String dmlb, String veh_drv, String is_flag) throws Exception;
}
