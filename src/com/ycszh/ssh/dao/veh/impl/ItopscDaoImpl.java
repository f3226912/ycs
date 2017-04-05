package com.ycszh.ssh.dao.veh.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import oracle.jdbc.OracleTypes;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.veh.IItopscDao;

/**
 * @包名:com.ckjg.ssh.dao.impl
 * @文件名:ItopscDaoImpl.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-12-30
 * @描述:
 * @版本:V 1.0
 */
public class ItopscDaoImpl extends HibernateDaoSupport implements IItopscDao {
	
	@SuppressWarnings("deprecation")
	public Object[] getTyblshinfo(String lsh) throws Exception{
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			String invalue = lsh + ",";
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call itosc_access_6in1.callservice(?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, "10059");
			proc.setString(3, invalue);
			proc.registerOutParameter(4, OracleTypes.CURSOR);
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			rs = (ResultSet)proc.getObject(4);
			String result = proc.getString(1);
			Object[] objs = new Object[22];
			if("0".equals(result)){
				while(rs.next()){
					if("rkbj".equals(rs.getString(2))){
						objs[0] = rs.getString(3);
					}
					if("lszt".equals(rs.getString(2))){
						objs[1] = rs.getString(3);
					}
					if("lsh".equals(rs.getString(2))){
						objs[2] = rs.getString(3);
					}
					if("bjrq".equals(rs.getString(2))){
						objs[3] = rs.getString(3);
					}
					if("ffbj".equals(rs.getString(2))){
						objs[4] = rs.getString(3);
					}
					if("clpp1".equals(rs.getString(2))){
						objs[5] = rs.getString(3);
					}
					
					if("ywlx".equals(rs.getString(2))){
						objs[6] = rs.getString(3);
					}
					if("syr".equals(rs.getString(2))){
						objs[7] = rs.getString(3);
					}
					if("ly".equals(rs.getString(2))){
						objs[8] = rs.getString(3);
					}
					if("hphm".equals(rs.getString(2))){
						objs[9] = rs.getString(3);
					}
					if("glbm".equals(rs.getString(2))){
						objs[10] = rs.getString(3);
					}
					if("xh".equals(rs.getString(2))){
						objs[11] = rs.getString(3);
					}
					
					if("xygw".equals(rs.getString(2))){
						objs[12] = rs.getString(3);
					}
					if("fpbj".equals(rs.getString(2))){
						objs[13] = rs.getString(3);
					}
					if("ywlc".equals(rs.getString(2))){
						objs[14] = rs.getString(3);
					}
					if("cllx".equals(rs.getString(2))){
						objs[15] = rs.getString(3);
					}
					if("clxh".equals(rs.getString(2))){
						objs[16] = rs.getString(3);
					}
					if("xzqh".equals(rs.getString(2))){
						objs[17] = rs.getString(3);
					}
					
					if("hpzl".equals(rs.getString(2))){
						objs[18] = rs.getString(3);
					}
					if("clsbdh".equals(rs.getString(2))){
						objs[19] = rs.getString(3);
					}
					if("sqrq".equals(rs.getString(2))){
						objs[20] = rs.getString(3);
					}
					if("ywyy".equals(rs.getString(2))){
						objs[21] = rs.getString(3);
					}
				}
			}
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}

	public Object[] get10036(String invalue) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call itosc_access_6in1.callservice(?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, "10036");
			proc.setString(3, invalue);
			proc.registerOutParameter(4, OracleTypes.CURSOR);
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			rs = (ResultSet)proc.getObject(4);
			String result = proc.getString(1);
			Object[] objs = null;
			if("0".equals(result)){
				objs = new Object[4];
				while(rs.next()){
					if("lsh".equals(rs.getString(2))){
						objs[0] = rs.getString(3);
					}
					if("sfzmhm".equals(rs.getString(2))){
						objs[1] = rs.getString(3);
					}
					if("xm".equals(rs.getString(2))){
						objs[2] = rs.getString(3);
					}
					if("dabh".equals(rs.getString(2))){
						objs[3] = rs.getString(3);
					}
				}
			}
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}

	public String get10178(String invalue) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call itosc_access_6in1.callservice(?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, "10178");
			proc.setString(3, invalue);
			proc.registerOutParameter(4, OracleTypes.CURSOR);
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			//rs = (ResultSet)proc.getObject(4);
			String result = proc.getString(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}

	@SuppressWarnings("deprecation")
	public String getPrc(String cardNo,String name) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{call ITOSC_JST_SOAP_API.Resident_Interface2(?,?,?)}");
			//proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(1, cardNo);
			proc.setString(2, name);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			String result = proc.getString(3);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}

	//调取车档信息
	public Object[] get10059(String invalue) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call itosc_access_6in1.callservice(?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, "10059");
			proc.setString(3, invalue);
			proc.registerOutParameter(4, OracleTypes.CURSOR);
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			rs = (ResultSet)proc.getObject(4);
			String result = proc.getString(1);
			Object[] objs = null;
			if("0".equals(result)){
				objs = new Object[2];
				while(rs.next()){
					if("hphm".equals(rs.getString(2))){
						objs[0] = rs.getString(3);
					}
					if("hpzl".equals(rs.getString(2))){
						objs[1] = rs.getString(3);
					}
				}
			}
			return objs;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}

	//调用缴费接口
	public String get10149(String invalue) throws Exception {
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call itosc_access_6in1.callservice(?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, "10149");
			proc.setString(3, invalue);
			proc.registerOutParameter(4, OracleTypes.CURSOR);
			proc.registerOutParameter(5, Types.VARCHAR);
			proc.registerOutParameter(6, Types.VARCHAR);
			proc.execute();
			String result = proc.getString(1);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
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
				conn = null;
			}
		}
	}
	

}
