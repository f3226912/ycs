package com.ycszh.ssh.dao.bfc.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.bfc.BfcJbxxbDao;
import com.ycszh.ssh.hbm.bfc.BfcJbxxb;

public class BfcJbxxbDaoImpl extends BaseDaoImpl<BfcJbxxb, String> implements BfcJbxxbDao {
	@Override
	public BfcJbxxb getRepository(String pk) {
		return super.getHibernateTemplate().get(BfcJbxxb.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<BfcJbxxb> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
