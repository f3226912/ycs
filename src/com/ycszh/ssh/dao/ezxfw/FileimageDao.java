package com.ycszh.ssh.dao.ezxfw;

import java.io.File;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrintPhoto;

/**
 * @包名:com.ycszh.ssh.dao.ezxfw
 * @文件名:FileimageDao.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2016-3-4
 * @描述:
 * @版本:V 1.0
 */
public interface FileimageDao extends BaseDao<EzXxdPrintPhoto, Integer>{
	public int uploadPrintphoto( File file,EzXxdPrintPhoto ezPhoto) throws Exception;
	public byte[] getImageBlob(String tpid) throws Exception;
	public int editeBlobByByte(EzXxdPrintPhoto ezPhoto, byte[] byte_array) throws Exception;

}
