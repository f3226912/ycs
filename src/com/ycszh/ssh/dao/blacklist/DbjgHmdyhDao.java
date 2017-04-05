package com.ycszh.ssh.dao.blacklist;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyh;

/**
 * @包名:com.ycszh.ssh.dao.blacklist
 * @文件名:DbjgHmdyhDao.java
 * @描述:黑名单用户表Dao
 * @版本:V 1.0
 */
public interface DbjgHmdyhDao extends BaseDao<DbjgHmdyh,String> {
	public int getSize(String sql) throws Exception;
}
