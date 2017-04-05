package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.TXbycCode;


public interface YczdglDao extends BaseDao<TXbycCode, String> {
	public int getSize(String hql);
	
	/**
	 * 获取查验项结果
	 * @param lsh
	 * @return
	 */
	public String getCyxjg(String lsh) throws Exception;
}
