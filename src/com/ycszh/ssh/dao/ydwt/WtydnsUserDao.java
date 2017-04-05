package com.ycszh.ssh.dao.ydwt;

import java.util.List;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;

public interface WtydnsUserDao extends BaseDao<WtydnsUser, Long> {
	public int getSize(String sql) throws Exception;
	
	public List<WtydnsUser> getUserInfo(String userName,String password)throws Exception;
	/******/
    //添加自己的SQL方法
}
