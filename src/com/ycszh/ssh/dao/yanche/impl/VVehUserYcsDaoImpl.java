package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VVehUserYcsDao;
import com.ycszh.ssh.hbm.yanche.VVehUserYcs;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:VVehUserYcsDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-15
 * @描述:
 * @版本:V 1.0
 */
public class VVehUserYcsDaoImpl extends BaseDaoImpl<VVehUserYcs, String> implements VVehUserYcsDao {
	@Override
	public VVehUserYcs getRepository(String pk) {
		return super.getHibernateTemplate().get(VVehUserYcs.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<VVehUserYcs> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}