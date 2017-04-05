package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VVehOrgYcsDao;
import com.ycszh.ssh.hbm.yanche.VVehOrgYcs;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:VVehOrgYcsDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-15
 * @描述:
 * @版本:V 1.0
 */
public class VVehOrgYcsDaoImpl extends BaseDaoImpl<VVehOrgYcs, String> implements VVehOrgYcsDao {
	@Override
	public VVehOrgYcs getRepository(String pk) {
		return super.getHibernateTemplate().get(VVehOrgYcs.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<VVehOrgYcs> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
