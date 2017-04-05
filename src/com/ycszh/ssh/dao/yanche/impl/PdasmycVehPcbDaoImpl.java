package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycVehPcbDao;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycVehPcbDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycVehPcbDaoImpl extends BaseDaoImpl<PdasmycVehPcb, String> implements PdasmycVehPcbDao {
	@Override
	public PdasmycVehPcb getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycVehPcb.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycVehPcb> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
