package com.ycszh.ssh.dao.bfc;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.bfc.BfcJbxxbLog;


public interface BfcJbxxbLogDao extends BaseDao<BfcJbxxbLog, String> {
	public int getSize(String hql);
}
