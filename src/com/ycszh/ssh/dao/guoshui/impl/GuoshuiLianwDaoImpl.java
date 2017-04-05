package com.ycszh.ssh.dao.guoshui.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.guoshui.IGuoshuiLianwDao;
import com.ycszh.ssh.hbm.guoshui.FpEsc;

public class GuoshuiLianwDaoImpl extends BaseDaoImpl<FpEsc, Long> implements IGuoshuiLianwDao {
	
	@Override
	public FpEsc getRepository(Long pk) throws Exception {
		return this.getHibernateTemplate().get(FpEsc.class, pk);
	}

	public void addObj(Object obj, HttpServletRequest request)
		throws Exception {
		//super.getHibernateTemplate().save(obj);
		
		// 大量倒出数据时，每插入一条自动提交;
		Session session = this.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		tran.begin();
		try {
			session.save(obj);
			tran.commit();
		} catch (Exception e) {
			tran.rollback();
			throw e;
		}
		finally{
			session.close();
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public Object getObj(String hql) throws Exception {
		List objList = null;
		objList = this.getHibernateTemplate().find(hql);
		if(objList != null && objList.size() > 0){
			return objList.get(0);
		}
		return null;
	}
		
	public void updateObj(Object obj) throws Exception {
		//this.getHibernateTemplate().update(obj);
		
		// 大量倒出数据时，每更新一条自动提交;
		Session session = this.getSessionFactory().openSession();
		Transaction tran = session.beginTransaction();
		tran.begin();
		try{
			session.update(obj);
			tran.commit();
		}catch (Exception e) {
			tran.rollback();
			throw e;
		}
		finally{
			session.close();
		}
	}

}
