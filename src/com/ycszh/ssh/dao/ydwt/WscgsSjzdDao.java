package com.ycszh.ssh.dao.ydwt;

import java.util.List;
import java.util.Map;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;

/**
 * @包名:com.example.ydwt.dao
 * @文件名:WscgsSjzdDao.java
 * @描述:
 * @版本:V 1.0
 */
public interface WscgsSjzdDao extends BaseDao<WscgsSjzd,String> {
	public List<WscgsSjzd> getStjg() throws Exception;
	public List<WscgsSjzd> getHpzl() throws Exception;
	public Map<String,String> getQueryStjg() throws Exception;
	public Map<String, String> getQueryHpzl() throws Exception;
}
