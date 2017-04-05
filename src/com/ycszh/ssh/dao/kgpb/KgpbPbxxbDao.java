package com.ycszh.ssh.dao.kgpb;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.kgpb.KgpbPbxxb;

/**
 * @包名:com.ycszh.ssh.dao.kgpb
 * @文件名:KgpbPbxxbDao.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-17
 * @描述:
 * @版本:V 1.0
 */
public interface KgpbPbxxbDao extends BaseDao<KgpbPbxxb, String> {

	public String dxFs(String sjhm,String dxnr) throws Exception;
	
}
