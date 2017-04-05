package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycPoliceSettingDao;
import com.ycszh.ssh.hbm.yanche.PdasmycPoliceSetting;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycPoliceSettingDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycPoliceSettingDaoImpl extends BaseDaoImpl<PdasmycPoliceSetting, String> implements PdasmycPoliceSettingDao {
	@Override
	public PdasmycPoliceSetting getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycPoliceSetting.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycPoliceSetting> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
