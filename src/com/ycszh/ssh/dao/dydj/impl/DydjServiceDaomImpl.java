package com.ycszh.ssh.dao.dydj.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.dydj.IDydjServiceDao;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspb;

public class DydjServiceDaomImpl extends BaseDaoImpl<DydjYwsbspb, Integer> implements IDydjServiceDao {

	@Override
	public DydjYwsbspb getRepository(Integer pk) throws Exception {
		DydjYwsbspb dydjYwsbspb = this.getHibernateTemplate().get(DydjYwsbspb.class, pk);
		return dydjYwsbspb;
	}
	
	@SuppressWarnings("deprecation")
	public byte[] getImageBlob(Integer id) throws Exception {
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.getSession().connection();
			String sql = "select id, dzzplb_zp from  dydj_ywsbspb_photo where id = "+id;
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("dzzplb_zp");
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
			//connectionMan.freeConnection("efsinterface",conn);//释放，但并未断开连接
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
	
	@SuppressWarnings("deprecation")
	public byte[] getUserImageBlob(String yzyhdm) throws Exception{
		Connection conn = null;
		PreparedStatement proc = null;
		ResultSet rs = null;
		oracle.sql.BLOB blob = null;
		try {
			conn = this.getSession().connection();
			String sql = "select photoimg from dydj_yh_user where yz_yhdm = '"+yzyhdm+"'";
			proc = conn.prepareStatement(sql);
			rs = proc.executeQuery();
			while(rs.next()){
				blob = (oracle.sql.BLOB) rs.getBlob("photoimg");
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
			//connectionMan.freeConnection("efsinterface",conn);//释放，但并未断开连接
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
