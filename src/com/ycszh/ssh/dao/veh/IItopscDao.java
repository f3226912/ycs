package com.ycszh.ssh.dao.veh;

/**
 * @包名:com.ckjg.ssh.dao
 * @文件名:IItopscDao.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-12-30
 * @描述:
 * @版本:V 1.0
 */
public interface IItopscDao {
	
	/**
	 * 调取统一版流水号信息
	 * @return
	 * @throws Exception
	 */
	public Object[] getTyblshinfo(String lsh) throws Exception;
	
	/**
	 * 调取统一版流水号信息
	 * @return
	 * @throws Exception
	 */
	public Object[] get10036(String invalue) throws Exception;
	
	/**
	 * 调取车档信息
	 * @param invalue
	 * @return
	 * @throws Exception
	 */
	public Object[] get10059(String invalue) throws Exception;
	
	public String get10178(String invalue) throws Exception;
	
	public String getPrc(String cardNo,String name)throws Exception;
	
	/**
	 * 调用缴费接口
	 * @param invalue
	 * @return
	 * @throws Exception 
	 */
	public String get10149(String invalue) throws Exception;
	
}
