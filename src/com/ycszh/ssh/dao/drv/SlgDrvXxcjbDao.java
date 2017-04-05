package com.ycszh.ssh.dao.drv;

import java.io.File;
import java.util.List;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;
/**
 * @包名:com.ycszh.ssh.dao.drv
 * @文件名:SlgDrvXxcjbDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public interface SlgDrvXxcjbDao extends BaseDao<SlgDrvXxcjb, String> {
	public int getSize(String hql);
	
	public Integer getjia6in1(String lsh) throws Exception;
	
	public String uploadFile(File file) throws Exception;
	
	/**
	 * 获取档案信息集合
	 * @param hm   号码
	 * @param type 类别(1身份证明号码2档案编号)
	 * @return list
	 */
	@SuppressWarnings("rawtypes")
	public List getDaxxInfo(String hm,String type) throws Exception;
}
