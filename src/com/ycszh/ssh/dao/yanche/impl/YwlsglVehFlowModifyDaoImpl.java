package com.ycszh.ssh.dao.yanche.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowModifyDao;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlowModify;

public class YwlsglVehFlowModifyDaoImpl extends BaseDaoImpl<YwlsglVehFlowModify, String> implements YwlsglVehFlowModifyDao {
	
	@Override
	public YwlsglVehFlowModify getRepository(String pk) {
		return super.getHibernateTemplate().get(YwlsglVehFlowModify.class, pk);
	}

}
