package com.ycszh.ssh.dao.dydj;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.dydj.DydjYhdbr;

public interface IDydjYhdbrDao extends BaseDao<DydjYhdbr, Integer>{
	
	public void addObj(Object obj, HttpServletRequest request) throws Exception;

}
