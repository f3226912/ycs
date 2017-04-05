package com.ycszh.ssh.dao.yanche.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowLogDao;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlowLog;

public class YwlsglVehFlowLogDaoImpl extends BaseDaoImpl<YwlsglVehFlowLog, String> implements YwlsglVehFlowLogDao {
	
	@Override
	public YwlsglVehFlowLog getRepository(String pk) {
		return super.getHibernateTemplate().get(YwlsglVehFlowLog.class, pk);
	}

}