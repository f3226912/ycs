package com.ycszh.ssh.dao.sjk;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.BaseObject;
import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;

/**
 * @包名:com.ycszh.ssh.dao.sjk
 * @文件名:SjkInfoDao.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-7-22
 * @描述:
 * @版本:V 1.0
 */
public interface SjkInfoDao extends BaseDao<BaseObject,String> {

	public int getFunSendsms(String sjhm,String msg) throws Exception;
	
}
