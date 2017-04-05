package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.VVehOrgYcs;

/**
 * @包名:com.ycszh.ssh.dao.yanche
 * @文件名:VVehOrgYcsDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-15
 * @描述:
 * @版本:V 1.0
 */
public interface VVehOrgYcsDao extends BaseDao<VVehOrgYcs, String> {
	public int getSize(String hql);
	
}
