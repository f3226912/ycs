package com.ycszh.ssh.dao.dagl;

import org.hibernate.Session;

import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsygl;

public interface SlgZdjdcjsrglDao {
	/**
	 * 插入实体
	 * 
	 * @param t		实体类对象
	 * @return
	 */
	 public Object addRepository(Object t) throws Exception;
	 
	 public Session getCurrSession();
}
