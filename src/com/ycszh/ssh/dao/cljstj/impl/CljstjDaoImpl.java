package com.ycszh.ssh.dao.cljstj.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.cljstj.CljstjDao;
import com.ycszh.ssh.hbm.cljstj.DxfsDxfsB;
import com.ycszh.ssh.hbm.cljstj.Healthdata;

public class CljstjDaoImpl extends BaseDaoImpl<Healthdata, String> implements CljstjDao{

	@Override
	public Healthdata getRepository(String pk) throws Exception {
		Long id=Long.valueOf(pk);
		Healthdata hd=super.getHibernateTemplate().get(Healthdata.class, id);
		return hd;
	}
	
	/*@SuppressWarnings("unchecked")
	public String fdx(DxfsDxfsB dxfsB) throws Exception{
		String ss="";
		
		Session sess =HibernateSessionFactory_cljstj.getSession();
		Transaction tx= sess.beginTransaction();
		
		List list = sess.createSQLQuery("select DXFS_DXFSB_SEQ.Nextval from dual ").list();
		dxfsB.setId(Long.valueOf(list.get(0).toString()));
		
		ss = sess.save(dxfsB).toString();
		tx.commit();
		HibernateSessionFactory_cljstj.closeSession();
		
		return ss;
	}*/
	
	public String fDx(DxfsDxfsB dxfsB) throws Exception{
		Connection conn = null;
		CallableStatement proc = null;
		Statement stc = null;
		ResultSet rs = null;
		String jg="0";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@100.100.21.101:1521:edb", "dpublish", "pub9513");
			proc = conn.prepareCall("select DXFS_DXFSB_SEQ.Nextval from dual");
			rs = proc.executeQuery();
			while(rs.next()){
				dxfsB.setId(Long.valueOf(rs.getString(1)));
				stc = conn.createStatement();
				String ss="insert into dxfs_dxfsb(id,ywlx,ywlx_xx,lsh,sfzmhm,dhhm,slsj,bjsj,dmnr,dmxz,cjsj) " +
				" values('"+dxfsB.getId()+"','"+dxfsB.getYwlx()+"','"+dxfsB.getYwlxXx()+"','"+
				dxfsB.getLsh()+"','"+dxfsB.getSfzmhm()+"','"+dxfsB.getDhhm()+"','"+dxfsB.getSlsj()+"','"+
				dxfsB.getBjsj()+"','"+dxfsB.getDmnr()+"','"+dxfsB.getDmxz()+"',sysdate)";
				
				stc.executeUpdate(ss);
				jg="1";
				//stc.executeUpdate("insert into dxfs_dxfsb(id,ywlx,ywlx_xx,lsh,sfzmhm,dhhm,slsj,bjsj,dmnr,dmxz,cjsj) values('2332675557','B','驾驶人审验','2130910799999','612323197508151618','13510566521','2013/09/10 07:00:56','2013/09/10 07:00:56','【深圳市公安局交通警察局】','A',sysdate)");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				rs.close();
			}
			if(proc != null){
				proc.close();
			}
			if(stc != null){
				stc.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		return jg;
	}

}
