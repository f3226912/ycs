package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycVehPcbLogDao;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcbLog;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycVehPcbLogDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycVehPcbLogDaoImpl extends BaseDaoImpl<PdasmycVehPcbLog, String> implements PdasmycVehPcbLogDao {
	@Override
	public PdasmycVehPcbLog getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycVehPcbLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycVehPcbLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
