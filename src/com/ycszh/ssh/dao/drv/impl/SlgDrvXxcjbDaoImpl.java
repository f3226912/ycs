package com.ycszh.ssh.dao.drv.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgDrvXxcjbDao;
import com.ycszh.ssh.dao.jdbcpool.DBConnectionManager;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;

/**
 * @包名:com.ycszh.ssh.dao.drv.impl
 * @文件名:SlgDrvXxcjbDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgDrvXxcjbDaoImpl extends BaseDaoImpl<SlgDrvXxcjb, String>
		implements SlgDrvXxcjbDao {
	
	DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public SlgDrvXxcjb getRepository(String pk) {
		return super.getHibernateTemplate().get(SlgDrvXxcjb.class, pk);
	}

	@SuppressWarnings("unchecked")
	public int getSize(String hql) {
		List<SlgDrvXxcjb> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

	public Integer getjia6in1(String lsh) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String xmlString = "";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@100.100.21.101:1521:edb", "dpublish",
					"pub9513");
			proc = conn.prepareCall("{? = call func_drivinfoquery_d(?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, lsh);
			proc.execute();
			xmlString = proc.getString(1);
			if ("0000".equals(xmlString)) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
			}
			/*
			 * if (conn != null) { conn.close(); conn = null; }
			 */
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public String uploadFile(File file) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		Statement stmt = null;
		ResultSet rs = null;
		oracle.sql.BLOB pc = null;
		byte[] bytes = null;
		String imagestr = "";
		FileInputStream fis = null;
		InputStream in = null;
		try {
			/*conn = connectionMan.getConnection("efsinterface");
			if(null == conn || conn.isClosed()){
				conn = connectionMan.getConnection("efsinterface");
			}*/
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.42.30.162:1521:efs", "efsinterface",
					"efsface");
			if(null != file){
				Long ilong = file.length();
				bytes = new byte[Integer.parseInt(ilong.toString())];
				fis = new FileInputStream(file);
				in = new BufferedInputStream(fis,Integer.parseInt(ilong.toString()));
				in.read(bytes);
				if(null != bytes){
					stmt = conn.createStatement();
					stmt.executeUpdate("insert into czjk_image(id,imgblob) values(1,empty_blob())");
					rs = stmt.executeQuery("select IMGBLOB from czjk_image where id=1 for update");
					if(rs.next()){
						pc = (oracle.sql.BLOB)rs.getBlob(1);
					}
					pc.putBytes(1, bytes);
					proc = conn.prepareCall("{call PIC_Register(?,?,?)}");
					proc.setString(1, "BAEEB5C6AE2EBFBDBEE4BC6135225273E8FC8AC0B6BD54E54F74D9C19EE816AF");
					proc.registerOutParameter(2, Types.VARCHAR);
					proc.registerOutParameter(3, Types.VARCHAR);
					proc.execute();
					String a = proc.getString(2);
					String b = proc.getString(3);
					if(null != b && !"".equals(b)){
						if(b.indexOf("0000") > 0){
							proc = null;
							proc = conn.prepareCall("{call Pic_InsertFile(?,?,?,?)}");
							proc.setString(1, a);
							proc.setBlob(2, pc);
							proc.registerOutParameter(3, Types.VARCHAR);
							proc.registerOutParameter(4, Types.VARCHAR);
							proc.execute();
							String c = proc.getString(3);
							String d = proc.getString(4);
							if(null != d && !"".equals(d)){
								if(d.indexOf("0000") > 0){
									if(null != c && !"".equals(c)){
										imagestr = c;
										return imagestr;
									}
								}else{
									Exception etemp = new Exception("异常:图文系统上传返回码:" + d + ",请重新点击保存按钮!");
									throw etemp;
								}
							}
						}else{
							Exception etemp = new Exception("异常:图文系统注册返回码:" + b + ",请重新点击保存按钮!");
							throw etemp;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			//connectionMan.freeConnection("efsinterface", conn);// 释放，但并未断开连接
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if(null != stmt){
				stmt.close();
				stmt = null;
			}
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if(in != null){
				in.close();
				in = null;
			}
			if(fis != null){
				fis.close();
				fis = null;
			}
		}
		return "";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getDaxxInfo(String hm, String type) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@100.100.21.101:1521:edb", "dpublish",
					"pub9513");
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
			/*
			 * if (conn != null) { conn.close(); conn = null; }
			 */
		}
		return list;
	}
}
