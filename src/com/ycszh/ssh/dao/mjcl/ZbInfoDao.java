package com.ycszh.ssh.dao.mjcl;

import java.util.List;
import java.util.Map;

import com.ycszh.ssh.dao.BaseDao;

public interface ZbInfoDao  {

	public List<Object> getZbInfoData(String sfzmmc, String shzmhm) throws Exception;

}
