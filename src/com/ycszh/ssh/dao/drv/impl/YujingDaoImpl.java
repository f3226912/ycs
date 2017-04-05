package com.ycszh.ssh.dao.drv.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.YujingDao;
import com.ycszh.ssh.hbm.drv.EsDrvFlow;
import com.ycszh.ssh.hbm.drv.SlgSjzd;

/**
 * @包名:com.example.ssh.dao.impl
 * @文件名:YujingDaoImpl.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-20
 * @描述:
 * @版本:V 1.0
 */
public class YujingDaoImpl extends BaseDaoImpl<EsDrvFlow, Long> implements YujingDao {

	@Override
	public EsDrvFlow getRepository(Long pk) throws Exception {
		
		return super.getHibernateTemplate().get(EsDrvFlow.class, pk);
	}

	@SuppressWarnings("unchecked")
	public List<SlgSjzd> getSlgDrvXxcjbList(String dmlb, String dmz) throws Exception {
		List<SlgSjzd> slgDrvXxcList = new ArrayList<SlgSjzd>();
		StringBuffer hqlbuffer = new StringBuffer("from SlgSjzd as s where 1=1 ");
		if(dmlb != null && !"".equals(dmlb)){
			hqlbuffer.append(" and s.dmlb = '" + dmlb + "'");
		}
		if(dmz != null && !"".equals(dmz)){
			hqlbuffer.append(" and s.dmz = '"+dmz+"'");
		}
		slgDrvXxcList = this.getRepositories(hqlbuffer.toString());
		
		return slgDrvXxcList;
	}

	@SuppressWarnings("unchecked")
	public Object getGlbm(String glbm) throws Exception {
		String hql = "select * from v_frm_department where glbm = :glbm";
		Query query = this.getSession().createSQLQuery(hql);
		query.setParameter("glbm", glbm);
		List objList = query.list();
		if(objList != null && objList.size() > 0){
			return objList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object getYwlx(String ywlx) throws Exception {
		String hql = "select * from es_drv_code where xtlb='02' AND dmlb='0008' and dmz = :ywlx";
		Query query = this.getSession().createSQLQuery(hql);
		query.setParameter("ywlx", ywlx);
		List objList = query.list();
		if(objList != null && objList.size() > 0){
			return objList.get(0);
		}
		return null;
	}

}
