package com.ycszh.ssh.dao.sfrz.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.sfrz.FileUploadDao;

public class FileUploadDaoImpl extends HibernateDaoSupport implements FileUploadDao{

	public String uploadFile(File file) throws Exception {
		System.out.println("图文上传：" + file.length());
		Connection conn = null;
		CallableStatement proc = null;
		Statement stmt = null;
		ResultSet rs = null;
		oracle.sql.BLOB pc = null;
		byte[] bytes = null;
		String imagestr = "";
		FileInputStream fis = null;
		InputStream in = null;
		Session session = null;
		try {
			/*conn = connectionMan.getConnection("efsinterface");
			if(null == conn || conn.isClosed()){
				conn = connectionMan.getConnection("efsinterface");
			}*/
			session = this.getSession();
			conn = session.connection();
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

}
