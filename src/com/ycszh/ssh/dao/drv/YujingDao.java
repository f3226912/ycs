package com.ycszh.ssh.dao.drv;

import java.util.List;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.drv.EsDrvFlow;


/**
 * @包名:com.example.ssh.dao
 * @文件名:YujingDao.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-20
 * @描述:
 * @版本:V 1.0
 */
public interface YujingDao extends BaseDao<EsDrvFlow, Long> {
	
	@SuppressWarnings("unchecked")
	public List getSlgDrvXxcjbList(String dmlb, String dmz) throws Exception;
	
	public Object getYwlx(String ywlx) throws Exception;
	
	public Object getGlbm(String glbm) throws Exception;

}
