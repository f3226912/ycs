package com.ycszh.ssh.dao.blacklist.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.blacklist.DbjgHmdyhLogDao;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyhLog;
/**
 * @包名: com.ycszh.ssh.dao.blacklist.impl
 * @文件名:DbjgHmdyhLogDaoImpl.java
 * @描述:
 * @版本:V 1.0
 */
public class DbjgHmdyhLogDaoImpl extends BaseDaoImpl<DbjgHmdyhLog, String> implements DbjgHmdyhLogDao {

	@Override
	public DbjgHmdyhLog getRepository(String pk) throws Exception {
		return super.getHibernateTemplate().get(DbjgHmdyhLog.class, pk);
	}
	
	@SuppressWarnings("unchecked")
	public int getSize(String sql) throws Exception {
		List<DbjgHmdyhLog> list = this.getHibernateTemplate().find(sql);
		return list.size();
	}

}
