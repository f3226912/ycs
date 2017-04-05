package com.ycszh.ssh.dao.veh.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.veh.VehSpDao;
import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;

public class VehSpDaoImpl extends BaseDaoImpl<SlgSpxx, Long> implements VehSpDao {
	
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

	public int getDbjgZjxxbSize(String hql) throws Exception {
		List<DbjgZjxxb> list = this.getHibernateTemplate().find(hql);
		return list.size();
	}

}
