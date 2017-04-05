package com.ycszh.ssh.dao.guoshui;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.guoshui.FpEsc;

public interface IGuoshuiLianwDao extends BaseDao<FpEsc, Long> {
	
	public void addObj(Object obj, HttpServletRequest request) throws Exception;
	
	public Object getObj(String hql) throws Exception; 
	
	public void updateObj(Object obj) throws Exception;

}
