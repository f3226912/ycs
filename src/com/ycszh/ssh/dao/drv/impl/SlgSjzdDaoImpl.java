package com.ycszh.ssh.dao.drv.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgSjzdDao;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
/**
 * @包名:com.ycszh.ssh.dao.drv.impl
 * @文件名:SlgSjzdDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgSjzdDaoImpl extends BaseDaoImpl<SlgSjzd, String> implements SlgSjzdDao {
	@Override
	public SlgSjzd getRepository(String pk) {
		return super.getHibernateTemplate().get(SlgSjzd.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<SlgSjzd> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
