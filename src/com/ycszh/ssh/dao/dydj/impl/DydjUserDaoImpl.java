package com.ycszh.ssh.dao.dydj.impl;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.dydj.DydjUserDao;
import com.ycszh.ssh.hbm.dydj.DydjUser;

public class DydjUserDaoImpl extends BaseDaoImpl<DydjUser, Integer> implements DydjUserDao{

	@Override
	public DydjUser getRepository(Integer pk) {
		return super.getHibernateTemplate().get(DydjUser.class, pk);
	}

}
