package com.ycszh.ssh.dao.ydwt;

import java.util.List;
import java.util.Map;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;

public interface WtydnsYwsbspbDao extends BaseDao<WtydnsYwsbspb, Integer> {
	public int getSize(String hql) throws Exception;
	public List getPCHItems(String sbzt) throws Exception;
	public List<YdwtDeclareAndQuit> getDeclareAndQuitStat(String startTime,String endTime) throws Exception;
	public List<YdwtDeclareAndQuit> getPosQuitStat(String startTime,String endTime) throws Exception;
	public Map<String,String> getQueryStjg() throws Exception;
	public Map<String, String> getQueryHpzl() throws Exception;
	public String getEncrypt(final String value,String p_key) throws Exception;
	public String getDecrypt(final String value,String p_key) throws Exception;

}
