package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycTempMidOutDao;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOut;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycTempMidOutDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-13
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycTempMidOutDaoImpl extends BaseDaoImpl<PdasmycTempMidOut, String> implements PdasmycTempMidOutDao {
	@Override
	public PdasmycTempMidOut getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycTempMidOut.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycTempMidOut> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
