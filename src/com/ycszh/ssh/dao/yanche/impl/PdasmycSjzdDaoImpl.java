package com.ycszh.ssh.dao.yanche.impl;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycSjzdDao;
import com.ycszh.ssh.hbm.yanche.PdasmycSjzd;

/**
 * @包名:com.chyy.ssh.dao.impl
 * @文件名:PdasmycSjzdDaoImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2013-4-24
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycSjzdDaoImpl extends BaseDaoImpl<PdasmycSjzd, String> implements
		PdasmycSjzdDao {

	@Override
	public PdasmycSjzd getRepository(String pk) throws Exception {
		return getHibernateTemplate().get(PdasmycSjzd.class, pk);
	}

	
}
