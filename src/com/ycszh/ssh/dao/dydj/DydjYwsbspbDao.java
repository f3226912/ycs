package com.ycszh.ssh.dao.dydj;

import java.util.List;
import java.util.Map;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;

public interface DydjYwsbspbDao extends BaseDao<DydjYwsbspb, Integer> {
	public int getSize(String hql) throws Exception;
	public List getPCHItems(String sbzt) throws Exception;
	public List<YdwtDeclareAndQuit> getDeclareAndQuitStat(String startTime,String endTime) throws Exception;
	public List<YdwtDeclareAndQuit> getPosQuitStat(String startTime,String endTime) throws Exception;
	public Map<String,String> getQueryStjg() throws Exception;
	public Map<String, String> getQueryHpzl() throws Exception;
	/**
	 * 加密
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public String getEncrypt(final String value,String p_key) throws Exception;
	/**
	 * 解密
	 * @param value
	 * @param p_key
	 * @return
	 * @throws Exception
	 */
	public String getDecrypt(final String value,String p_key) throws Exception;

}
