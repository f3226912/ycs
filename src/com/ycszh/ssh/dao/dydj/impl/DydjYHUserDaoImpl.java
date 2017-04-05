package com.ycszh.ssh.dao.dydj.impl;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import org.hibernate.Session;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.dydj.DydjYHUserDao;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;

public class DydjYHUserDaoImpl extends BaseDaoImpl<DydjYhUser, Integer> implements DydjYHUserDao{

	@Override
	public DydjYhUser getRepository(Integer pk) {
		return super.getHibernateTemplate().get(DydjYhUser.class, pk);
	}
	@SuppressWarnings("deprecation")
	public int uploadDydyYhphoto(File file,DydjYhUser yhUser) throws Exception{
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
				int c=stc.executeUpdate("update DYDJ_YH_USER  set PHOTOIMG = empty_blob() where id="+yhUser.getId()+"");
				//锁定数据行进行更新，注意"for update"语句   
				rs = stc.executeQuery("select PHOTOIMG from DYDJ_YH_USER where id = "+yhUser.getId()+" for update");
				if(rs.next()){
					// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB  
					oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob("PHOTOIMG");
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
	public int editeBlobByByte(DydjYhUser yhUser, byte[] byte_array) throws Exception{
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
				int c=stc.executeUpdate("update DYDJ_YH_USER  set PHOTOIMG = empty_blob() where id="+yhUser.getId()+"");
				//锁定数据行进行更新，注意"for update"语句   
				rs = stc.executeQuery("select PHOTOIMG from DYDJ_YH_USER where id = "+yhUser.getId()+" for update");
				if(rs.next()){
					// 得到java.sql.Blob对象后强制转换为oracle.sql.BLOB  
					oracle.sql.BLOB blob = (oracle.sql.BLOB)rs.getBlob("PHOTOIMG");
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
	public byte[] getImageBlob(Integer id) throws Exception {
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.getSession().connection();
			String sql = "select id, PHOTOIMG from  DYDJ_YH_USER where id = "+id;
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("PHOTOIMG");
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