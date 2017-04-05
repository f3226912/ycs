package com.ycszh.ssh.dao.xyc.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.xyc.XycVehDao;


public class XycVehDaoImpl extends HibernateDaoSupport implements XycVehDao {

	public String InsertXyc(String yylx,String ywyy,String syr,String sfzmmc,String sfzmhm,String clsbdh,String clxh,String zwppxh,String hphm,String hpzl,String xyyy,String lsh,String lybz,Integer zt,String bz,String zrfs,String lrrdm,String lrrmc,String lrrbm,String lrrbmKj,String lrimac,String lrip,String czrdm,String czrxm,String czrbm,String czmac,String czip) throws Exception{
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call XYC_VEH_CZ.XYC_VEH_INSERT (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			proc.setString(1, yylx);
			proc.setString(2, ywyy);
			proc.setString(3, syr);
			proc.setString(4, sfzmmc);
			proc.setString(5, sfzmhm);
			proc.setString(6, clsbdh);
			proc.setString(7, clxh);
			proc.setString(8, zwppxh);
			proc.setString(9, hphm);
			proc.setString(10, hpzl);
			proc.setString(11, xyyy);
			proc.setString(12, lsh);
			proc.setString(13, lybz);
			proc.setInt(14, zt);
			proc.setString(15, bz);
			proc.setString(16, zrfs);
			proc.setString(17, lrrdm);
			proc.setString(18, lrrmc);
			proc.setString(19, lrrbm);
			proc.setString(20, lrrbmKj);
			proc.setString(21, lrimac);
			proc.setString(22, lrip);
			proc.setString(23, czrdm);
			proc.setString(24, czrxm);
			proc.setString(25, czrbm);
			proc.setString(26, czmac);
			proc.setString(27, czip);
			proc.registerOutParameter(28, OracleTypes.VARCHAR);
			proc.execute();
			String returnstr = proc.getString(28);
			return returnstr;
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	public String UpdateXyc(String xh,String yylx,String ywyy,String syr,String sfzmmc,String sfzmhm,String clsbdh,String clxh,String zwppxh,String hphm,String hpzl,String xyyy,String lsh,String lybz,Integer zt,String bz,String zrfs,String czrdm,String czrxm,String czrbm,String czmac,String czip) throws Exception{
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call XYC_VEH_CZ.XYC_VEH_UPDATE (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			proc.setString(1, xh);
			proc.setString(2, yylx);
			proc.setString(3, ywyy);
			proc.setString(4, syr);
			proc.setString(5, sfzmmc);
			proc.setString(6, sfzmhm);
			proc.setString(7, clsbdh);
			proc.setString(8, clxh);
			proc.setString(9, zwppxh);
			proc.setString(10, hphm);
			proc.setString(11, hpzl);
			proc.setString(12, xyyy);
			proc.setString(13, lsh);
			proc.setString(14, lybz);
			proc.setInt(15, zt);
			proc.setString(16, bz);
			proc.setString(17, zrfs);
			proc.setString(18, czrdm);
			proc.setString(19, czrxm);
			proc.setString(20, czrbm);
			proc.setString(21, czmac);
			proc.setString(22, czip);
			proc.registerOutParameter(23, OracleTypes.VARCHAR);
			proc.execute();
			String returnstr = proc.getString(23);
			return returnstr;
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	public String deleteXyc(String xh, String lrrdm, String lrrmc,
			String lrrbm, String lrimac, String lrip) throws Exception{
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call XYC_VEH_CZ.XYC_VEH_DELETE (?,?,?,?,?,?,?)}");
			proc.setString(1, xh);
			proc.setString(2, lrrdm);
			proc.setString(3, lrrmc);
			proc.setString(4, lrrbm);
			proc.setString(5, lrimac);
			proc.setString(6, lrip);
			proc.registerOutParameter(7, OracleTypes.VARCHAR);
			proc.execute();
			String returnstr = proc.getString(7);
			return returnstr;
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

}
