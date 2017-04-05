package com.ycszh.ssh.dao.drv.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbLogDao;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjbLog;
/**
 * @包名:com.ycszh.ssh.dao.drv.impl
 * @文件名:SlgDrvXxcjbLogDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgDrvXxcjbLogDaoImpl extends BaseDaoImpl<SlgDrvXxcjbLog, String> implements SlgDrvXxcjbLogDao {
	@Override
	public SlgDrvXxcjbLog getRepository(String pk) {
		return super.getHibernateTemplate().get(SlgDrvXxcjbLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<SlgDrvXxcjbLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
