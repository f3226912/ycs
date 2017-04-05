package com.ycszh.ssh.dao.bfc.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.bfc.BfcJbxxbLogDao;
import com.ycszh.ssh.hbm.bfc.BfcJbxxbLog;

public class BfcJbxxbLogDaoImpl extends BaseDaoImpl<BfcJbxxbLog, String> implements BfcJbxxbLogDao {
	@Override
	public BfcJbxxbLog getRepository(String pk) {
		return super.getHibernateTemplate().get(BfcJbxxbLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<BfcJbxxbLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
