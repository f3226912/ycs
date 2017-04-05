package com.ycszh.ssh.dao.yanche.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import oracle.jdbc.OracleTypes;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ycszh.global.User;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.yanche.VehicleTempMidOutDao;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;

public class VehicleTempMidOutDaoImpl extends BaseDaoImpl<VehicleTempMidOut, String> implements VehicleTempMidOutDao {
	
	@Override
	public VehicleTempMidOut getRepository(String pk) {
		return super.getHibernateTemplate().get(VehicleTempMidOut.class, pk);
	}

	public int getSize(String hql) {
		return 0;
	}

	@SuppressWarnings("deprecation")
	public String getPro(VehicleTempMidOut vtmo,String zxykyy,User user) throws Exception {
		String returnstr = "";
		Connection conn = null;
		CallableStatement proc = null;
		ResultSet rs = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		Session session = sf.getCurrentSession();				//获取session	getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call xyc_veh_cz.xyc_veh_insert (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			proc.setString(1, vtmo.getYwlx());
			proc.setString(2, vtmo.getYwyy());
			proc.setString(3, vtmo.getSyr());
			proc.setString(4, vtmo.getSfzmmc());
			proc.setString(5, vtmo.getSfzmhm());
			proc.setString(6, vtmo.getClsbdh());
			proc.setString(7, vtmo.getClxh());
			proc.setString(8, vtmo.getClpp1());
			proc.setString(9, vtmo.getHphm());
			proc.setString(10, vtmo.getHpzl());
			proc.setString(11, zxykyy);
			proc.setString(12, vtmo.getLsh());
			proc.setString(13, "0");
			proc.setObject(14, "");
			proc.setString(15, "");
			proc.setString(16, "");
			proc.setString(17, "");
			proc.setString(18, "");
			proc.setString(19, "");
			proc.setString(20, "");
			proc.setString(21, "");
			proc.setString(22, "");
			proc.setString(23, user.getName());
			proc.setString(24, user.getYgxm());
			proc.setString(25, user.getBmid());
			proc.setString(26, "");
			proc.setString(27, "");
			proc.registerOutParameter(28, OracleTypes.VARCHAR);
			proc.execute();
			returnstr = proc.getString(28);
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
		return returnstr;
	}
}
