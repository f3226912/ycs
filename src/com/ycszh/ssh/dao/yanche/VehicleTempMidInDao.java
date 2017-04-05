package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;

public interface VehicleTempMidInDao extends BaseDao<VehicleTempMidIn, String> {
	public int getSize(String hql);
	
}
