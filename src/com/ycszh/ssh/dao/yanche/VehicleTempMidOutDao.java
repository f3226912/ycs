package com.ycszh.ssh.dao.yanche;

import com.ycszh.global.User;
import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;

public interface VehicleTempMidOutDao extends BaseDao<VehicleTempMidOut, String> {
	public int getSize(String hql);
	
	//主库调用oracle存储过程
	public String getPro(VehicleTempMidOut vtmo,String zxykyy,User user) throws Exception;
}
