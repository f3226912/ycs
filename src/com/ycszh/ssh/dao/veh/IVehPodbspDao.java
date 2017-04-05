package com.ycszh.ssh.dao.veh;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.veh.VehPodbsp;

public interface IVehPodbspDao extends BaseDao<VehPodbsp, Long> {
	
	public void addObj(Object obj, HttpServletRequest request) throws Exception;

}
