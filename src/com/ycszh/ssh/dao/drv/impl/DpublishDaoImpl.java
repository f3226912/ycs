package com.ycszh.ssh.dao.drv.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.drv.IDpublishDao;

public class DpublishDaoImpl extends HibernateDaoSupport implements IDpublishDao{
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List getDaxxInfo(String hm, String type) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = this.getSession().connection();
			stmt = conn.createStatement();
			String sql = "select trim(cast(dabh as varchar2(20))),sfzmhm,zjcx,CCLZRQ,LJJF,zt,xm,trim(cast(GJ as varchar2(20))),SYRQ,YXQZ,lxzsxzqh,djzsxzqh,lxzsxxdz,djzsxxdz,lxdh,csrq,xb,sfzmmc from es_drv_license ";
			if("1".equals(type)){
				sql += " where sfzmhm='" + hm + "'";
			}else if("2".equals(type)){
				sql += " where dabh='" + hm + "'";
			}
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				list.add(rs.getString(1));
				list.add(rs.getString(2));
				list.add(rs.getString(3));
				list.add(rs.getDate(4));
				list.add(rs.getString(5));
				list.add(rs.getString(6));
				list.add(rs.getString(7));
				list.add(rs.getString(8));
				list.add(rs.getDate(9));
				list.add(rs.getDate(10));
				list.add(rs.getString(11));
				list.add(rs.getString(12));
				list.add(rs.getString(13));
				list.add(rs.getString(14));
				list.add(rs.getString(15));
				list.add(rs.getDate(16));
				list.add(rs.getString(17));
				list.add(rs.getString(18));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if(null != stmt){
				stmt.close();
				stmt = null;
			}
			if(conn != null){
				conn.close();
			}
		}
		return list;
	}
}
