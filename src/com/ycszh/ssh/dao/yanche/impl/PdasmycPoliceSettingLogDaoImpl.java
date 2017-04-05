package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycPoliceSettingLogDao;
import com.ycszh.ssh.hbm.yanche.PdasmycPoliceSettingLog;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycPoliceSettingLogDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycPoliceSettingLogDaoImpl extends BaseDaoImpl<PdasmycPoliceSettingLog, String> implements PdasmycPoliceSettingLogDao {
	@Override
	public PdasmycPoliceSettingLog getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycPoliceSettingLog.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycPoliceSettingLog> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
