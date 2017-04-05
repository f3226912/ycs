package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.TXbycGps;



public interface TXbycGpsDao extends BaseDao<TXbycGps, String> {
	public int getSize(String hql);
}