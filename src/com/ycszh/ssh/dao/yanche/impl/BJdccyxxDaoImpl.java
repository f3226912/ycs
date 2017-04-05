package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.BJdccyxxDao;
import com.ycszh.ssh.hbm.yanche.BJdccyxx;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:BJdccyxxDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-30
 * @描述:
 * @版本:V 1.0
 */
public class BJdccyxxDaoImpl extends BaseDaoImpl<BJdccyxx, String> implements BJdccyxxDao {
	
	//DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public BJdccyxx getRepository(String pk) {
		return super.getHibernateTemplate().get(BJdccyxx.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<BJdccyxx> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

}
