package com.ycszh.ssh.dao.drv.impl;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.drv.SlgSpxxDao;
import com.ycszh.ssh.hbm.drv.SlgSpxx;

public class SlgSpxxDaoImpl extends BaseDaoImpl<SlgSpxx, Long> implements SlgSpxxDao {

	@Override
	public SlgSpxx getRepository(Long pk) throws Exception {
		SlgSpxx slgSpxx = null;
		slgSpxx = this.getHibernateTemplate().get(SlgSpxx.class, pk);
		return slgSpxx;
	}

	public void addObj(Object obj, HttpServletRequest request)
		throws Exception {
		super.getHibernateTemplate().save(obj);
	}

}
