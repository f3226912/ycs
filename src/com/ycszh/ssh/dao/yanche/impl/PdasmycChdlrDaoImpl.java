package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycChdlrDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChdlr;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycChdlrDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-16
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChdlrDaoImpl extends BaseDaoImpl<PdasmycChdlr, String> implements PdasmycChdlrDao {
	@Override
	public PdasmycChdlr getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycChdlr.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycChdlr> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}