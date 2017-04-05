package com.ycszh.ssh.dao.dagl.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.dagl.SlgZdjdcjsrglDao;
import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsygl;

public class SlgZdjdcjsrglDaoImpl extends HibernateDaoSupport implements SlgZdjdcjsrglDao{
	private static final Log logger = LogFactory
	.getLog(SlgZdjdcjsrglDaoImpl.class);


	/**
	* 插入实体
	* 
	* @param t		实体类对象
	* @return
	*/
	public Object addRepository(Object t) throws Exception {
		this.getHibernateTemplate().save(t);
		this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		logger.info("执行插入操作T:" + t);
		return t;
	}
	
	public Session getCurrSession(){
		return this.getHibernateTemplate().getSessionFactory().getCurrentSession();
	}
}
