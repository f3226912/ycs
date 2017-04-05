package com.ycszh.ssh.dao.drv;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.drv.SlgSpxx;

public interface SlgSpxxDao extends BaseDao<SlgSpxx, Long> {
	
	public void addObj(Object obj, HttpServletRequest request) throws Exception;

}
