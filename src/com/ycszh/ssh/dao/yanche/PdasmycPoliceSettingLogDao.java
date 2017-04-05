package com.ycszh.ssh.dao.yanche;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.yanche.PdasmycPoliceSettingLog;

/**
 * @包名:com.ycszh.ssh.dao.yanche
 * @文件名:PdasmycPoliceSettingLogDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public interface PdasmycPoliceSettingLogDao extends BaseDao<PdasmycPoliceSettingLog, String> {
	public int getSize(String hql);
	
}
