package com.ycszh.ssh.dao.yanche.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowDao;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlow;

public class YwlsglVehFlowDaoImpl extends BaseDaoImpl<YwlsglVehFlow, String> implements YwlsglVehFlowDao {
	
	@Override
	public YwlsglVehFlow getRepository(String pk) {
		return super.getHibernateTemplate().get(YwlsglVehFlow.class, pk);
	}

}
