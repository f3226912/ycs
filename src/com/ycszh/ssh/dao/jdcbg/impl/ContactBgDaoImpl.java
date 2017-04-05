package com.ycszh.ssh.dao.jdcbg.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.jdcbg.ContactBgDao;
import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;

public class ContactBgDaoImpl extends BaseDaoImpl<TJdclxfsbg, String> implements ContactBgDao{

	@Override
	public TJdclxfsbg getRepository(String pk) throws Exception {
		TJdclxfsbg jdc=super.getHibernateTemplate().get(TJdclxfsbg.class, pk);
		return jdc;
	}

}
