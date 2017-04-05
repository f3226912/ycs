package com.ycszh.ssh.dao.dydj.impl;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.dydj.IDydjYhdbrDao;
import com.ycszh.ssh.hbm.dydj.DydjYhdbr;

public class DydjYhdbrDaoImpl extends BaseDaoImpl<DydjYhdbr, Integer> implements IDydjYhdbrDao{

	@Override
	public DydjYhdbr getRepository(Integer pk) throws Exception {
		return super.getHibernateTemplate().get(DydjYhdbr.class, pk);
	}
	
	public void addObj(Object obj, HttpServletRequest request)
		throws Exception {
		super.getHibernateTemplate().save(obj);
	}

}
