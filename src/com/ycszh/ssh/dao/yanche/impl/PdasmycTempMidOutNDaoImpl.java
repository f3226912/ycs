package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycTempMidOutNDao;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycTempMidOutNDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-13
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycTempMidOutNDaoImpl extends BaseDaoImpl<PdasmycTempMidOutN, String> implements PdasmycTempMidOutNDao {
	@Override
	public PdasmycTempMidOutN getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycTempMidOutN.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycTempMidOutN> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
