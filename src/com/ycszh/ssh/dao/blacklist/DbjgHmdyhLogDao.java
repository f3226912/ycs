package com.ycszh.ssh.dao.blacklist;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyhLog;

/**
 * @包名:com.ycszh.ssh.dao.blacklist
 * @文件名:DbjgHmdyhLogDao.java
 * @描述:黑名单用户日志表Dao
 * @版本:V 1.0
 */
public interface DbjgHmdyhLogDao extends BaseDao<DbjgHmdyhLog,String> {
	public int getSize(String sql) throws Exception;
}
