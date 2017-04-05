package com.ycszh.ssh.dao.yanche.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.YwlsglVehFieldglDao;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFieldgl;

public class YwlsglVehFieldglDaoImpl extends BaseDaoImpl<YwlsglVehFieldgl, String> implements YwlsglVehFieldglDao {
	
	@Override
	public YwlsglVehFieldgl getRepository(String pk) {
		return super.getHibernateTemplate().get(YwlsglVehFieldgl.class, pk);
	}

}
