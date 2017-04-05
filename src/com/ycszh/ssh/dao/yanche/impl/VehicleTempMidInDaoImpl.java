package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VehicleTempMidInDao;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;

public class VehicleTempMidInDaoImpl extends BaseDaoImpl<VehicleTempMidIn, String> implements VehicleTempMidInDao {
	@Override
	public VehicleTempMidIn getRepository(String pk) {
		return super.getHibernateTemplate().get(VehicleTempMidIn.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<VehicleTempMidIn> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
