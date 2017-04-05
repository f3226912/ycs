package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.VVehUserYcs;

/**
 * @包名:com.ycszh.ssh.dao.yanche
 * @文件名:VVehUserYcsDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-5-15
 * @描述:
 * @版本:V 1.0
 */
public interface VVehUserYcsDao extends BaseDao<VVehUserYcs, String> {
	public int getSize(String hql);
	
}
