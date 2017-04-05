package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VDataCheckLogDao;
import com.ycszh.ssh.hbm.yanche.VDataCheckLog;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:VDataCheckLogDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-30
 * @描述:
 * @版本:V 1.0
 */
public class VDataCheckLogDaoImpl  extends BaseDaoImpl<VDataCheckLog, String> implements VDataCheckLogDao {
	
	//DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public VDataCheckLog getRepository(String pk) {
		return super.getHibernateTemplate().get(VDataCheckLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<VDataCheckLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

}
