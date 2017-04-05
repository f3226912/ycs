package com.ycszh.ssh.dao.drv.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgYwlxDao;
import com.ycszh.ssh.hbm.drv.SlgYwlx;
/**
 * @包名:com.ycszh.ssh.dao.drv.impl
 * @文件名:SlgYwlxDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgYwlxDaoImpl extends BaseDaoImpl<SlgYwlx, String> implements SlgYwlxDao {
	@Override
	public SlgYwlx getRepository(String pk) {
		return super.getHibernateTemplate().get(SlgYwlx.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<SlgYwlx> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
