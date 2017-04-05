package com.ycszh.ssh.dao.cljstj;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.cljstj.DxfsDxfsB;
import com.ycszh.ssh.hbm.cljstj.Healthdata;

public interface CljstjDao extends BaseDao<Healthdata,String>{
	public String fDx(DxfsDxfsB dxfsB) throws Exception;
}
