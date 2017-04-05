package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycVehicleTsspDao;
import com.ycszh.ssh.hbm.yanche.PdasmycVehicleTssp;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycVehicleTsspDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-6-3
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycVehicleTsspDaoImpl extends BaseDaoImpl<PdasmycVehicleTssp, String> implements PdasmycVehicleTsspDao {
	@Override
	public PdasmycVehicleTssp getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycVehicleTssp.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycVehicleTssp> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}