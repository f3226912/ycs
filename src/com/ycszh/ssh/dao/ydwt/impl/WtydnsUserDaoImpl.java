package com.ycszh.ssh.dao.ydwt.impl;
import java.util.List;
import com.ycszh.ssh.dao.BaseDaoImpl;
import com.ycszh.ssh.dao.jdbcpool.DBConnectionManager;
import com.ycszh.ssh.dao.ydwt.WtydnsUserDao;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;

public class WtydnsUserDaoImpl extends BaseDaoImpl<WtydnsUser, Long> implements WtydnsUserDao{
	
	@SuppressWarnings("unchecked")
	public int getSize(String sql) throws Exception {
		List<WtydnsUser> list = this.getHibernateTemplate().find(sql);
		return list.size();
	}
	//定义外部连接池的单例对象
	DBConnectionManager connectionMan = DBConnectionManager.getInstance();
	@Override
	public WtydnsUser getRepository(Long pk) {
		return super.getHibernateTemplate().get(WtydnsUser.class, pk);
	}
	@SuppressWarnings("unchecked")
	public List<WtydnsUser> getUserInfo(final String userName,  final String password)
			throws Exception {
		return this.getHibernateTemplate().find("from WtydnsUser t where t.yzYhdm=? and t.ymmm=?",new Object[] { userName, password });
	}

}
