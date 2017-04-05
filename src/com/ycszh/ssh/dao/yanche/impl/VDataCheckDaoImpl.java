package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VDataCheckDao;
import com.ycszh.ssh.hbm.yanche.VDataCheck;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:VDataCheckDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-30
 * @描述:
 * @版本:V 1.0
 */
public class VDataCheckDaoImpl extends BaseDaoImpl<VDataCheck, String> implements VDataCheckDao {
	
	//DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public VDataCheck getRepository(String pk) {
		return super.getHibernateTemplate().get(VDataCheck.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<VDataCheck> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

}
