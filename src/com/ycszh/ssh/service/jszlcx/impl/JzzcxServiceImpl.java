package com.ycszh.ssh.service.jszlcx.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.service.jszlcx.JzzcxService;
import javax.servlet.http.HttpServletRequest;

public class JzzcxServiceImpl extends HibernateDaoSupport implements JzzcxService{

	private DefaultDao defaultDao;

	private final static Logger log = Logger.getLogger(JzzcxServiceImpl.class);
	
	// 调用居住证查询的存储过程，根据”身份证号码“和”姓名“进行查询，存储过程返回的是字符串数组，用;进行分割，要注意，用split()方法进行分割取值
	@SuppressWarnings("deprecation")
	public Object[] getPro(HttpServletRequest request) throws Exception {
		String idno = request.getParameter("idno");
		String xm = request.getParameter("xm");
		String result = "";
		Object[] obj = null;
		Connection conn = null ;
		CallableStatement proc = null;
		SessionFactory sf = this.getHibernateTemplate().getSessionFactory();
		//获取session,getCurrentSession方法获取session后 spring自动管理事务,不须手动关闭
		Session session = sf.getCurrentSession();
		try {
			conn = session.connection();
			proc = conn.prepareCall("{call ITOSC_JST_SOAP_API.Resident_Interface2(?,?,?)}");
			//返回值
			proc.registerOutParameter(3, OracleTypes.VARCHAR);
			//传入参数值
			proc.setString(1, idno);
			proc.setString(2, xm);
			proc.execute();
			result = proc.getString(3);
			obj = ((String) result).split(";");
			//System.out.println(obj[6]);
			request.getSession().setAttribute("obj", obj);
			request.setAttribute("obj", obj);
			request.getSession().setAttribute("result", result);
			
			
		} catch (Exception re) {
			re.printStackTrace();
			throw re;
		} finally {
			if (proc != null) {
				proc.close();
				proc = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
		return obj;
	}
	
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public static Logger getLog() {
		return log;
	}
}
