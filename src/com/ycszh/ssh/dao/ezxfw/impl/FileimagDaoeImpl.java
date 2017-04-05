package com.ycszh.ssh.dao.ezxfw.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.ezxfw.FileimageDao;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrintPhoto;

/**
 * @包名:com.ycszh.ssh.dao.ezxfw.Impl
 * @文件名:FileimageImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2016-3-4
 * @描述:
 * @版本:V 1.0
 */
public class FileimagDaoeImpl extends BaseDaoImpl<EzXxdPrintPhoto, Integer> implements FileimageDao {

	@Override
	public EzXxdPrintPhoto getRepository(Integer pk) {
		return super.getHibernateTemplate().get(EzXxdPrintPhoto.class, pk);
	}
	@SuppressWarnings("deprecation")
	public int uploadPrintphoto(File file,EzXxdPrintPhoto ezPhoto) throws Exception{
		Session session = null;
		Connection con = null;
		Statement stc = null;
		ResultSet rs = null;
		int count =0;
		if(file != null){
			try {
				session = this.getSession();
				con = session.connection();
				stc = con.createStatement();
				con.setAutoCommit(false);//取消自动提交
				OutputStream os = null;
				// 插入一个空对象empty_blob()   
				int c=stc.executeUpdate("update EZ_XXD_PRINT_PHOTO  set PHOTO = empty_blob() where TPID="+ezPhoto.getTpid()+"");
				//锁定数据行进行更新，注意"for update"语句   
				rs = stc.executeQuery("select PHOTO from EZ_XXD_PRINT_PHOTO where TPID = "+ezPhoto.getTpid()+" for update");
				if(rs.next()){
					// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB  
					oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob("PHOTO");
					// 通过getBinaryOutputStream()方法获得向数据库中插入图片的"管道"
					os = blob.getBinaryOutputStream();
					InputStream is = new FileInputStream(file);
					//依次读取流字节,并输出到已定义好的数据库字段中
					int i = 0;
					while((i = is.read()) != -1){
						os.write(i);
					}
					count++;
				}
				if(os!=null){
					os.flush();
					os.close();
				}					
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				if(rs != null){
					rs.close();
				}
				if(stc != null){
					stc.close();
				}
				if(con != null){
					con.commit();
					con.setAutoCommit(true);
					con.close();
				}
			}
		}
		return count;
	}
	/**
	 * 用二进制插入blob
	 */
	public int editeBlobByByte(EzXxdPrintPhoto ezPhoto, byte[] byte_array) throws Exception{
		Session session = null;
		Connection con = null;
		Statement stc = null;
		ResultSet rs = null;
		int count =0;
			try {
				session = this.getSession();
				con = session.connection();
				stc = con.createStatement();
				con.setAutoCommit(false);//取消自动提交
				OutputStream os = null;
				// 插入一个空对象empty_blob()   
				int c=stc.executeUpdate("update EZ_XXD_PRINT_PHOTO  set PHOTO = empty_blob() where TPID="+ezPhoto.getTpid()+"");
				//锁定数据行进行更新，注意"for update"语句   
				rs = stc.executeQuery("select PHOTO from EZ_XXD_PRINT_PHOTO where TPID = "+ezPhoto.getTpid()+" for update");
				if(rs.next()){
					// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB  
					oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob("PHOTO");
					// 通过getBinaryOutputStream()方法获得向数据库中插入图片的"管道"
					os = blob.getBinaryOutputStream();
					if(null != byte_array){
						for (int i = 0; i < byte_array.length; i++) {
							os.write(byte_array[i]);
						}
					}
					count++;
				}
				if(os!=null){
					os.flush();
					os.close();
				}				
				if(byte_array != null){
					byte_array = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally{
				if(rs != null){
					rs.close();
				}
				if(stc != null){
					stc.close();
				}
				if(con != null){
					con.commit();
					con.setAutoCommit(true);
					con.close();
				}
			}		
		  return count;
	}
	@SuppressWarnings("deprecation")
	public byte[] getImageBlob(String tpid) throws Exception {
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.getSession().connection();
			String sql = "select tpid, PHOTO from  EZ_XXD_PRINT_PHOTO where tpid = "+tpid;
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("PHOTO");
			}
			byte[] byte_array = null;
			if(blob != null){
				int length = (int) blob.length();
				byte_array = blob.getBytes(1, length);
			}
			return byte_array;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if(blob!= null){
				blob = null;
			}
		}
	}

}
