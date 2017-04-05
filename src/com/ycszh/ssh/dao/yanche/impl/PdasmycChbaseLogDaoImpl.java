package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycChbaseLogDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChbaseLog;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycChbaseLogDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-10
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChbaseLogDaoImpl extends BaseDaoImpl<PdasmycChbaseLog, String> implements PdasmycChbaseLogDao {
	@Override
	public PdasmycChbaseLog getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycChbaseLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycChbaseLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
