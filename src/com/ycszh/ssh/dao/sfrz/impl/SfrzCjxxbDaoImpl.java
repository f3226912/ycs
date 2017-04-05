package com.ycszh.ssh.dao.sfrz.impl;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.sfrz.SfrzCjxxbDao;
import com.ycszh.util.DateUtil;

public class SfrzCjxxbDaoImpl extends HibernateDaoSupport implements SfrzCjxxbDao {

	@SuppressWarnings("deprecation")
	public String insertOrUpdateSfrzCjxxb(String xmlstr) throws Exception {
		String retxml = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		System.out.println(xmlstr);
		try {
			conn = session.connection();
			Reader clobReader = new StringReader(xmlstr);
			proc = conn.prepareCall("{call PKG_SFRZ_INFO.PRO_SFRZ_XXCJ (?,?)}");
			proc.setCharacterStream(1, clobReader,xmlstr.length());
			proc.registerOutParameter(2, OracleTypes.CLOB);
			proc.execute();
			Clob clob =  proc.getClob(2);
			if(null != clob){
				BufferedReader reader = new BufferedReader(clob.getCharacterStream());
				StringBuffer buf = new StringBuffer();
				String line = null;
				while((line = reader.readLine()) != null){
					buf.append(line);
				}
				retxml = buf.toString();
			}
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
		return retxml;
	}

	@SuppressWarnings("deprecation")
	public String getSfrzCjxxb(String cid) throws Exception {
		String retxml = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call PKG_SFRZ_INFO.PRO_SFZMHM_CHECK (?,?)}");
			proc.setString(1, cid);
			proc.registerOutParameter(2, OracleTypes.VARCHAR);
			proc.execute();
			retxml =  proc.getString(2);
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
		return retxml;
	}
	
	@SuppressWarnings({ "static-access", "deprecation" })
	public Integer getJzzinfo(String jzzno,String xm) throws Exception{
		Integer retint = 0;
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call itosc.itosc_jst_soap_api.resident_interface2 (?,?,?)}");
			proc.setString(1, jzzno);
			proc.setString(2, xm);
			proc.registerOutParameter(3, OracleTypes.VARCHAR);
			proc.execute();
			String retstr =  proc.getString(3);
			if(null != retstr && !"".equals(retstr)){
				if("0".equals(retstr)){
					retint = 1;	//无居住证信息
				}else{
					//String bb = retstr.substring(0,retstr.lastIndexOf(";"));
					//String cc = bb.substring(bb.lastIndexOf(";") + 1);
					
					String yxq =retstr.substring(0,retstr.lastIndexOf(";"));
					String rq= yxq.substring(yxq.lastIndexOf(";") + 1);
					Date d = new Date();
					DateUtil du = new DateUtil();
					Date d2 = du.string2Date(rq, "yyyyMMdd");
					if(d2.before(d)){
						return retint = 3;
					}else{
						return retint = 2;
					}
				}
			}
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
		return retint;
	}

	public String getJzzinfo2(String jzzno,String xm) throws Exception{
		String retstr = "0";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call itosc.itosc_jst_soap_api.resident_interface2 (?,?,?)}");
			proc.setString(1, jzzno);
			proc.setString(2, xm);
			proc.registerOutParameter(3, OracleTypes.VARCHAR);
			proc.execute();
			retstr =  proc.getString(3);
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
		return retstr;
	}
	
}
