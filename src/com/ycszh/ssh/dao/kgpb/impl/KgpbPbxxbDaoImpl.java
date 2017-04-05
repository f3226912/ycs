package com.ycszh.ssh.dao.kgpb.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.hibernate.SessionFactory;

import oracle.jdbc.OracleTypes;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.DefaultDaoImpl;
import com.ycszh.ssh.dao.kgpb.KgpbPbxxbDao;
import com.ycszh.ssh.hbm.kgpb.KgpbPbxxb;

/**
 * @包名:com.ycszh.ssh.dao.kgpb.impl
 * @文件名:KgpbPbxxbDaoImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-17
 * @描述:
 * @版本:V 1.0
 */
public class KgpbPbxxbDaoImpl extends BaseDaoImpl<KgpbPbxxb, String> implements KgpbPbxxbDao {

	public String dxFs(String sjhm,String dxnr) throws Exception {
		String returnValue;
		Connection conn = null;
		CallableStatement proc = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.33)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.34)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = tpdb)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))", 
					"VehOffice",
					"off123");
			proc = conn.prepareCall("{? = call sjxxk.sendmsg_union(?,?,?,?,?,?,?)}");
			proc.registerOutParameter(1, OracleTypes.VARCHAR);
			proc.setString(2, "kgpb01");
			proc.setString(3, "kgpb01#");
			proc.setString(4, sjhm);
			proc.setDate(5, null);
			proc.setString(6, "");
			proc.setString(7, dxnr);
			proc.registerOutParameter(8, OracleTypes.VARCHAR);
			
			proc.execute();
			returnValue = proc.getObject(1)+"";
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return returnValue;
	}

	@Override
	public KgpbPbxxb getRepository(String pk) throws Exception {
		return getHibernateTemplate().get(KgpbPbxxb.class,pk);
	}

}
