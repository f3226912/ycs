package com.ycszh.ssh.dao.drv.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgZjxxbDao;
import com.ycszh.ssh.hbm.drv.SlgZjxxb;
/**
 * @包名:com.ycszh.ssh.dao.drv.impl
 * @文件名:SlgZjxxbDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgZjxxbDaoImpl extends BaseDaoImpl<SlgZjxxb, String> implements SlgZjxxbDao {
	@Override
	public SlgZjxxb getRepository(String pk) {
		return super.getHibernateTemplate().get(SlgZjxxb.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<SlgZjxxb> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
}
