package com.ycszh.ssh.dao.bfc;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.bfc.BfcJbxxb;


public interface BfcJbxxbDao extends BaseDao<BfcJbxxb, String> {
	public int getSize(String hql);
}
