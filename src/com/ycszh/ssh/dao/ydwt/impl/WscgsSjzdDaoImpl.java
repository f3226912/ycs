package com.ycszh.ssh.dao.ydwt.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.ydwt.WscgsSjzdDao;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;


/**
 * @包名:com.example.ydwt.dao.impl
 * @文件名:WscgsSjzdDaoImpl.java
 * @描述:
 * @版本:V 1.0
 */
public class WscgsSjzdDaoImpl extends BaseDaoImpl<WscgsSjzd, String> implements WscgsSjzdDao {

	@Override
	public WscgsSjzd getRepository(String pk) throws Exception {
		return null;
	}
	/**
	 * 受托核发检验合格标志机构(stjg)
	 * （355	粤Y	粤Y :佛山市公安局南海分局交通警察大队车辆管理所）例如
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<WscgsSjzd> getStjg() throws Exception{
		StringBuffer hql = new StringBuffer("from WscgsSjzd as ws where ws.dmlb = '1001' order by dmz ");
		List<WscgsSjzd> wscgsSjzdList = new ArrayList<WscgsSjzd>();
		wscgsSjzdList =this.getRepositories(hql.toString());
		return wscgsSjzdList;
	}
	/**
	 * 得到受委托机构字典
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getQueryStjg() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql = new StringBuffer("select ws.dmz,dmz||' :'||dmsm1 from wscgs_sjzd ws where ws.dmlb = '1001' order by dmz");
		SQLQuery query = session.createSQLQuery(sql.toString());
		List resultList = query.list();
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	/**
	 * 查询号牌种类字典(hpzl)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getQueryHpzl() throws Exception{
		Map<String,String> map = new HashMap<String,String>();
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql = new StringBuffer("select ws.dmz,ws.dmsm1 from wscgs_sjzd ws where ws.dmlb = '7' order by ws.dmz ");
		SQLQuery query = session.createSQLQuery(sql.toString());
		List resultList = query.list();
		Object obj [] = new Object[2];
		for (int i = 0; i < resultList.size(); i++) {
			obj = (Object[])resultList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	
	/**
	 * 查询号牌种类字典(hpzl)
	 */
	@SuppressWarnings("unchecked")
	public List<WscgsSjzd> getHpzl() throws Exception{
		StringBuffer hql = new StringBuffer("from WscgsSjzd as ws where ws.dmlb = '7' order by ws.dmz ");
		List<WscgsSjzd> wscgsSjzdList = new ArrayList<WscgsSjzd>();
		wscgsSjzdList = this.getRepositories(hql.toString());
		return wscgsSjzdList;
	}

}
