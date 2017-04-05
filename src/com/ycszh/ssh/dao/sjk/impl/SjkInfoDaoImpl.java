package com.ycszh.ssh.dao.sjk.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.jdbcpool.DBConnectionManager;
import com.ycszh.ssh.dao.sjk.SjkInfoDao;
import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.dao.sjk.impl
 * @文件名:SjkInfoDaoImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-7-22
 * @描述:
 * @版本:V 1.0
 */
public class SjkInfoDaoImpl extends BaseDaoImpl<BaseObject, String> implements SjkInfoDao {

	//定义外部连接池的单例对象
	DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	public int getFunSendsms(String sjhm,String msg) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		int returnValue = -1;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@100.100.21.101:1521:edb", "dpublish",
					"pub9513");
			proc = conn.prepareCall("{? = call sendsms(?,?,?,?,?,?)}");
			proc.registerOutParameter(1, OracleTypes.INTEGER);
			proc.setString(2, SysConst.sendsms_user);
			proc.setString(3, SysConst.sendsms_pwd);
			proc.setString(4, sjhm);
			proc.setDate(5, null);
			proc.setString(6, "");
			proc.setString(7, msg);
			
			proc.execute();
			returnValue = Integer.parseInt(proc.getObject(1)+"");
		}
		catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
			}
		}
		
		return returnValue;
	}

	@Override
	public BaseObject getRepository(String pk) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
