package com.ycszh.ssh.dao.yanche.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.YczdglDao;
import com.ycszh.ssh.hbm.yanche.PdasmycChbase;
import com.ycszh.ssh.hbm.yanche.TXbycCode;

public class YczdglDaoImpl extends BaseDaoImpl<TXbycCode, String> implements YczdglDao{

	@Override
	public TXbycCode getRepository(String pk) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<PdasmycChbase> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}
	
	//获取查验项查验结果
	@SuppressWarnings("deprecation")
	public String getCyxjg(String lsh) throws Exception {
		String result = "";
		Connection conn = null;
		CallableStatement proc = null;		
		try {
			//conn = this.getSession().connection();
			String url = "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.33)(PORT = 1521))(ADDRESS = (PROTOCOL = TCP)(HOST = 100.100.21.34)(PORT = 1521))(LOAD_BALANCE = yes)(CONNECT_DATA =(SERVER = DEDICATED)(SERVICE_NAME = tpdb)(FAILOVER_MODE =(TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";
			String user = "cgskcb";
			String password = "6ae91a21";
			conn = DriverManager.getConnection(url, user, password);
			
			String sql = "{call pkg_xbyc.sp_cyxm(?,?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(2, Types.VARCHAR);
			proc.setString(1, lsh);
			proc.execute();
			result = proc.getString(2);
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return result;
	}


}
