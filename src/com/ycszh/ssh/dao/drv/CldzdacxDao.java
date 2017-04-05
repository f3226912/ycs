package com.ycszh.ssh.dao.drv;

import java.util.List;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;

public interface CldzdacxDao extends BaseDao<SlgDrvXxcjb, String> {
	public byte[] getImageBlob(String tpm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getImageInfo(String hphm, String hpzl) throws Exception;
}
