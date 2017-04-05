package com.ycszh.ssh.dao.sfrz;

public interface SfrzCjxxbDao {
	public String insertOrUpdateSfrzCjxxb(String xmlstr) throws Exception;
	
	public String getSfrzCjxxb(String cid) throws Exception;
	
	public Integer getJzzinfo(String jzzno,String xm) throws Exception;
	
	public String getJzzinfo2(String jzzno,String xm) throws Exception;
}
