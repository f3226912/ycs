package com.ycszh.ssh.dao.dydj;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;

public interface IDydjServiceDao extends BaseDao<DydjYwsbspb, Integer>{
	
	public byte[] getImageBlob(Integer id) throws Exception;
	
	public byte[] getUserImageBlob(String yzyhdm) throws Exception;

}
