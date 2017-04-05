package com.ycszh.ssh.dao.drv.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.CldzdacxDao;
import com.ycszh.ssh.dao.jdbcpool.DBConnectionManager;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;

public class CldzdacxDaoImpl extends BaseDaoImpl<SlgDrvXxcjb, String> implements CldzdacxDao {
	
	DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	
	@Override
	public SlgDrvXxcjb getRepository(String pk) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public byte[] getImageBlob(String tpm) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		Blob b = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@10.42.30.162:1521:efs", "efsinterface",
					"efsface");
			/*conn = connectionMan.getConnection("efsinterface");
			if(null == conn || conn.isClosed()){
				conn = connectionMan.getConnection("efsinterface");
			}*/
			proc = conn.prepareCall("{call pic_readfile_blob2(?,?,?)}");
			proc.setString(1, "BAEEB5C6AE2EBFBDBEE4BC6135225273E8FC8AC0B6BD54E54F74D9C19EE816AF");
			proc.setString(2, tpm);
			proc.registerOutParameter(3, Types.BLOB);
			//cs.registerOutParameter("c_ret", oracle.jdbc.OracleTypes.CURSOR);
			proc.execute();
			b = proc.getBlob(3);
			byte[] byte_array = null;
			if(b != null){
				int length = (int) b.length();
				byte_array = b.getBytes(1, length);
			}
			return byte_array;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			//connectionMan.freeConnection("efsinterface",conn);//释放，但并未断开连接
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if(b != null){
				b = null;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List getImageInfo(String hphm, String hpzl) throws Exception{
		List imageList = null;
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		String xh = "";
		try {
			/***********************非单机测试以下代码注释************************************/
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@192.168.0.250:1521:orcl", "IMG_VFILE",
//					"PICSAVE");
			/*************************非单机测试以上代码注释**********************************/
			String sql = "select * from es_veh_vehicle where hphm = ? AND hpzl = ?";
			/*************************非单机测试以下代码注释去掉*******************************/
			conn = connectionMan.getConnection("img_vfile");
			if(null == conn || conn.isClosed()){
				conn = connectionMan.getConnection("img_vfile");
			}
			/*************************非单机测试以上代码注释去掉*******************************/
			proc = conn.prepareStatement(sql);
			proc.setString(1, hphm);
			proc.setString(2, hpzl);
			rs = proc.executeQuery();
			if(rs != null){
				while(rs.next()){
					xh = rs.getString("xh");
					if(xh != null && !"".equals(xh)){
						break;
					}
				}
			}
			if(xh != null && !"".equals(xh)){
				//String imgsql = "select t.pageimage,t1.pagecut from filepagetable1 t,filepagecut1 t1 where t.xh=? and t.id=t1.id";
				String imgsql = "select t.id, t.pageimage from filepagetable1 t  where t.xh=? ";
				proc = conn.prepareStatement(imgsql);
				proc.setString(1, xh);
				rs = proc.executeQuery();
				if(rs != null){
					imageList = new ArrayList();
					while(rs.next()){
						Object[] objs = new Object[2];
						objs[0] = rs.getString("pageimage");
						//objs[1] = rs.getString("pagecut");
						objs[1] = rs.getString("id");
						imageList.add(objs);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			connectionMan.freeConnection("img_vfile",conn);//释放，但并未断开连接
			if(rs != null){
				rs.close();
			}
			if(proc != null){
				proc.close();
			}
			if(conn != null){
				conn.close();
			}
		}
		return imageList;
	}
	
	public void insertOrUpdate() throws Exception{
		Connection conn = null;
		Statement proc = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.141:1521:orcl", "WY", "WANGYONG");
			conn.setAutoCommit(false);
			String sql = "insert into test values(4, 'fairy', empty_blob())";
			String upsql = "select img from test where tid = 4 for update";
			File file = new File("c:\\printzj.jpg");
			byte[] data = new byte[1024];
			Long ilong = file.length();
			data = new byte[Integer.parseInt(ilong.toString())];
			FileInputStream fis  = new FileInputStream(file);
			InputStream in = new BufferedInputStream(fis,Integer.parseInt(ilong.toString()));
			in.read(data);
			proc = conn.createStatement();
			proc.executeUpdate(sql);
			rs = proc.executeQuery(upsql);
			if(rs.next()){
				oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob(1);
				OutputStream outStream = blob.getBinaryOutputStream();
				outStream.write(data, 0, data.length);
				outStream.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs != null){
				rs.close();
			}
			if(proc != null){
				proc.close();
			}
			if(conn != null){
				conn.commit();
				conn.close();
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		CldzdacxDaoImpl cl = new CldzdacxDaoImpl();
		cl.insertOrUpdate();
		
		InputStream is = new FileInputStream(new File("D:\\images\\cwxx.jpg"));
		//InputStream ins = new ByteArrayInputStream(arg0);
	}

}
