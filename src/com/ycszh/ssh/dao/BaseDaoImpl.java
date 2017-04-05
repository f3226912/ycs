package com.ycszh.ssh.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ycszh.ssh.dao.BaseDao;
import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.dao
 * @文件名:BaseDaoImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public abstract class BaseDaoImpl<T extends BaseObject, PK> extends
		HibernateDaoSupport implements BaseDao<T, PK> {
	private static final Log logger = LogFactory.getLog(BaseDaoImpl.class);

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param offset
	 *            开始页数
	 * @param pageSize
	 *            显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findHQLByPage(final String hql, final int offset,
			final int pageSize) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createQuery(hql).setFirstResult(
								offset).setMaxResults(pageSize).list();
						return result;
					}
				});
		logger.info("执行分页查询操作:" + hql);
		return list;
	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param map
	 *            参数Map数组对象
	 * @param offset
	 *            开始页数
	 * @param pageSize
	 *            显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findHQLByPage(final String hql, final Map<String, Object> map,
			final int offset, final int pageSize) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (String key : map.keySet())
							query.setParameter(key, map.get(key));
						List result = query.setFirstResult(offset)
								.setMaxResults(pageSize).list();
						return result;
					}
				});
		logger.info("执行分页查询操作:" + hql);
		return list;

	}

	/**
	 * 分页查询
	 * 
	 * @param hql
	 *            HQL语句
	 * @param values
	 *            参数Object数组对象
	 * @param offset
	 *            开始页数
	 * @param pageSize
	 *            显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findHQLByPage(final String hql, final Object[] values,
			final int offset, final int pageSize) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					@SuppressWarnings("unchecked")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < values.length; i++) {
							query.setParameter(i, values[i]);
						}
						List<T> result = query.setFirstResult(offset)
								.setMaxResults(pageSize).list();

						return result;
					}
				});
		logger.info("执行分页查询操作:" + hql);
		return list;
	}

	/**
	 *查询所有存储器数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getRepositories(String hql) throws Exception {
		logger.info("执行查询操作:" + hql);
		return this.getHibernateTemplate().find(hql);
	}

	/**
	 * 普通SQL分页查询
	 * 
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始页数
	 * @param pageSize
	 *            显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findSQLByPage(final String sql, final int offset,
			final int pageSize) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createSQLQuery(sql)
								.setFirstResult(offset).setMaxResults(pageSize)
								.list();
						return result;
					}
				});
		logger.info("执行查询操作:" + sql);
		return list;
	}
	
	/**
	 * 普通SQL分页查询
	 * 
	 * @param sql
	 *            SQL语句
	 * @param offset
	 *            开始页数
	 * @param pageSize
	 *            显示数量
	 * @param clasz
	 *            Class对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findSQLByPage(final String sql, final int offset,
			final int pageSize, final Class clasz) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createSQLQuery(sql).addEntity(clasz)
								.setFirstResult(offset).setMaxResults(pageSize)
								.list();
						return result;
					}
				});
		logger.info("执行查询操作:" + sql);
		return list;
	}

	/**
	 * 普通SQL查询
	 * 
	 * @param sql
	 *            SQL语句
	 * @param clasz
	 *            Class对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findSQL(final String sql, final Class clasz) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createSQLQuery(sql).addEntity(
								clasz).list();
						return result;
					}
				});
		logger.info("执行查询操作:" + sql);
		return list;
	}

	/**
	 * 普通SQL查询
	 * 
	 * @param sql
	 *            SQL语句
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findSQL(final String sql) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createSQLQuery(sql).list();
						return result;
					}
				});
		logger.info("执行查询操作:" + sql);
		return list;
	}

	/**
	 * 插入实体
	 * 
	 * @param t
	 *            实体类对象
	 * @return
	 */
	public T addRepository(T t) throws Exception {
		this.getHibernateTemplate().save(t);
		logger.info("执行插入操作:" + t);
		return t;
	}

	/**
	 * 更新实体
	 * 
	 * @param t
	 *            实体类对象
	 * @return
	 */
	public T updateRepository(T t) throws Exception {
		this.getHibernateTemplate().update(t);
		logger.info("执行更新操作:" + t);
		return t;
	}
	
	/**
	 * 更新实体
	 * 
	 * @param t
	 *            实体类对象
	 * @return
	 */
	public T mergeRepository(T t) throws Exception {
		this.getHibernateTemplate().merge(t);
		logger.info("执行更新操作:" + t);
		return t;
	}

	/**
	 * 根据主键获取存储器对象
	 * 
	 * @param pk
	 *            主键
	 * @return
	 */
	public abstract T getRepository(PK pk) throws Exception;

	/**
	 * 获取存储器列表对象数
	 * 
	 * @param hql
	 *            HQL语句
	 * @return int 返回集合个数
	 */
	public int getRepositoryByHQLListSize(String hql) throws Exception {
		Query q = this.getSession().createQuery(hql);
		logger.info("执行查询记录数:" + hql);
		return ((Long) q.uniqueResult()).intValue();
	}

	/**
	 * 获取存储器列表对象数
	 * 
	 * @param sql
	 *            SQL语句
	 * @return int 返回集合个数
	 */
	@SuppressWarnings("rawtypes")
	public int getRepositoryBySQLListSize(final String sql) throws Exception {
		List list = this.getHibernateTemplate().executeFind(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						List result = session.createSQLQuery(sql).list();
						return result;
					}
				});
		if (null != list && list.size() > 0) {
			int i = Integer.parseInt(list.get(0).toString());
			return i;
		}
		return 0;
	}

	/**
	 * 删除存储器对象
	 * 
	 * @param t
	 */
	public void deleteRepository(T t) throws Exception {
		this.getHibernateTemplate().delete(t);
		logger.info("执行删除:" + t);
	}

	/**
	 * 批量删除存储器对象
	 * 
	 * @param customerCollection
	 */
	public void deleteRepositoryList(Collection<T> tCollection)
			throws Exception {
		this.getHibernateTemplate().deleteAll(tCollection);
		logger.info("执行批量删除:" + tCollection);
	}

	/**
	 * 普通sql方式更新存储器
	 * 
	 * @param sql
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateRepositoryBySql(final String sql) throws Exception {
		this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						int result = session.createSQLQuery(sql)
								.executeUpdate();
						return result;
					}
				});
		logger.info("native sql方式执行更新操作:" + sql);
	}
}
