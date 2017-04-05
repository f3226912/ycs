package com.ycszh.ssh.dao.drv;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.drv.SlgYwlx;
/**
 * @包名:com.ycszh.ssh.dao.drv
 * @文件名:SlgYwlxDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public interface SlgYwlxDao extends BaseDao<SlgYwlx, String> {
	public int getSize(String hql);
}
