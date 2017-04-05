package com.ycszh.ssh.dao.veh.impl;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.veh.IVehPodbspDao;
import com.ycszh.ssh.hbm.veh.VehPodbsp;

public class VehPodbspDaoImpl extends BaseDaoImpl<VehPodbsp, Long> implements IVehPodbspDao {

	@Override
	public VehPodbsp getRepository(Long pk) throws Exception {
		VehPodbsp vehPodbsp = null;
		vehPodbsp = this.getHibernateTemplate().get(VehPodbsp.class, pk);
		return vehPodbsp;
	}
	
	public void addObj(Object obj, HttpServletRequest request)
		throws Exception {
		super.getHibernateTemplate().save(obj);
	}

	

}
