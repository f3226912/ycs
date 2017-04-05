package com.ycszh.ssh.dao.blacklist.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.blacklist.DbjgSjzdLogDao;
import com.ycszh.ssh.hbm.blacklist.DbjgSjzdLog;


/**
 * @包名: com.ycszh.ssh.dao.blacklist.impl
 * @文件名:DbjgSjzdLogDaoImpl.java
 * @描述:
 * @版本:V 1.0
 */
public class DbjgSjzdLogDaoImpl extends BaseDaoImpl<DbjgSjzdLog, String> implements DbjgSjzdLogDao {

	@Override
	public DbjgSjzdLog getRepository(String pk) throws Exception {
		return super.getHibernateTemplate().get(DbjgSjzdLog.class, pk);
	}
	
}
