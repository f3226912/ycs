package com.ycszh.ssh.dao.yanche.impl;

import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.PdasmycChbaseDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChbase;

/**
 * @包名:com.ycszh.ssh.dao.yanche.impl
 * @文件名:PdasmycChbaseDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-10
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChbaseDaoImpl extends BaseDaoImpl<PdasmycChbase, String> implements PdasmycChbaseDao {
	
	//DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public PdasmycChbase getRepository(String pk) {
		return super.getHibernateTemplate().get(PdasmycChbase.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycChbase> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

}
