package com.ycszh.ssh.dao.veh.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import oracle.jdbc.OracleTypes;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.veh.SlgVehDao;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.hbm.veh.TemporaryLicense;
import com.ycszh.util.StringUtil;

public class SlgVehDaoImpl extends BaseDaoImpl<DbjgZjxxb, String> implements SlgVehDao{

	public void addObj(Object obj, HttpServletRequest request) throws Exception{
		this.getHibernateTemplate().save(obj);
	}
	
	public DbjgZjxxb getRepository(String pk) throws Exception {
		return super.getHibernateTemplate().get(DbjgZjxxb.class, pk);
	}
	
	public void updateObj(Object obj) throws Exception{
		this.getHibernateTemplate().update(obj);
	}
	
	@SuppressWarnings("deprecation")
	public String getIsBlackByFun(String ywlx, String ywzl, String hphm, String hpzl, String dsrsfzmhm, String dbrsfzmhm) throws Exception{
		if(!StringUtil.isNull(hphm)){
			hphm = hphm.toUpperCase();
		}
		String xmlString = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			proc = conn.prepareCall("{? = call vehcile_db.veh_db_verify(?,?,?,?,?,?,?)}");
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, ywlx);
			proc.setString(3, ywzl);
			proc.setString(4, hphm);
			proc.setString(5, hpzl);
			proc.setString(6, dsrsfzmhm);
			proc.setString(7, dbrsfzmhm);
			proc.registerOutParameter(8, Types.VARCHAR);
			proc.execute();
			String result = proc.getString(1);
			String regMsg = proc.getString(8);
			xmlString = result + "+" + regMsg;
		} catch (Exception e) {
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
		
		return xmlString;
	}
	
	@SuppressWarnings("deprecation")
	public String jszYuyue(HttpServletRequest request, String xml) throws Exception{
		String result = "0+系统异常，请稍后再试";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			String sql = "{? = call ycs_qzyy.proc_qzyy_yz(?, ?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, xml);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			result = proc.getString(1);
			String para3 = proc.getString(3);
			if(para3 != null && !"".equals(para3)){
				result = result + "+" + proc.getString(3);
			}
			//result = "1+成功预约";
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public String getIsyanzzb(HttpServletRequest request, String xml) throws Exception{
		String result = " [0000]成功!";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			String sql = "{? = call ycs_qzyy.clxg_zbyz(?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, xml);
			proc.execute();
			result = proc.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public String insertShenjiinfo(HttpServletRequest request, String lsh, String srcs) throws Exception{
		String result = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			lsh = lsh.substring(0, 13);
			conn = this.getSession().connection();
			String sql = "{? = call clxg_buesines_schedule.insertShenjiinfo(?, ?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, lsh);
			proc.setString(3, srcs);
			proc.execute();
			result = proc.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public String getZblist(HttpServletRequest request, String xml) throws Exception{
		String result = "{\"total\":0,\"rows\":[{\"id\":\"<span style='color:red;'>温馨提示:获取指标信息异常}]}";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			String sql = "{? = call clxg_buesines_schedule.fun_getZblist(?, ?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, OracleTypes.CLOB);
			proc.setString(2, xml);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			result = proc.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public String getIsneedzb(HttpServletRequest request, String hphm, String hpzl, String ywlx, String ywyy, String sfzmhm, String xm, String sfzmmc) throws Exception{
		String result = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			String sql = "{? = call clxg_buesines_schedule.fun_isneedzb(?, ?, ?, ?, ?, ?, ?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, hphm);
			proc.setString(3, hpzl);
			proc.setString(4, ywlx);
			proc.setString(5, ywyy);
			proc.setString(6, sfzmhm);
			proc.setString(7, xm);
			proc.setString(8, sfzmmc);
			proc.execute();
			result = proc.getString(1);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public String getTbyanz(HttpServletRequest request, String xml) throws Exception{
		String result = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		try {
			conn = this.getSession().connection();
			String sql = "{? = call clxg_buesines_schedule_test2.fun_zbyanz(?, ?)}";
			proc = conn.prepareCall(sql);
			proc.registerOutParameter(1, Types.VARCHAR);
			proc.setString(2, xml);
			proc.registerOutParameter(3, Types.VARCHAR);
			proc.execute();
			result = proc.getString(1)+"+"+proc.getString(3);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			if(rs != null){
				rs.close();
				rs = null;
			}
			if(proc != null){
				proc.close();
				proc = null;
			}
			if(conn != null){
				conn.close();
				conn = null;
			}
		}
		return result;
	}
	
	@SuppressWarnings("deprecation")
	public TemporaryLicense getTemporaryLicense(String lsh) throws Exception{
		TemporaryLicense tempLicense = new TemporaryLicense();
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();
		try {
			conn = session.connection();
			proc = conn.prepareCall("{? = call psbp.get_temporary_license(?)}");
			proc.registerOutParameter(1, OracleTypes.CURSOR);
			proc.setString(2, lsh);
			proc.execute();
			rs = (ResultSet)proc.getObject(1);
			while (rs.next()) {
				tempLicense.setAddress(rs.getString(2)); //居住地址
				tempLicense.setAppointmentDate(rs.getString(3)); //预约日期
				tempLicense.setBookNumber(rs.getString(4)); //预约号码
				tempLicense.setCarType(rs.getString(5)); //车辆类型
				tempLicense.setChassisNumber(rs.getString(6)); //车架号后4位
				tempLicense.setChineseBrand(rs.getString(7)); //中文品牌
				tempLicense.setEngineNumber(rs.getString(9)); //发动机号
				tempLicense.setIdNumber(rs.getString(10)); //身份证号码
				tempLicense.setName(rs.getString(11)); //车主姓名
				tempLicense.setPassengerNumber(rs.getString(12)); //载客人数
				tempLicense.setPhoneNumber(rs.getString(13)); //电话号码
				tempLicense.setVehicleType(rs.getString(14)); //车辆类型
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
		return tempLicense;
	}

}
