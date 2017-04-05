package com.ycszh.ssh.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ycszh.ssh.hbm.BaseObject;

/**
 * @包名:com.ycszh.ssh.dao
 * @文件名:BaseDao.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public interface BaseDao<T extends BaseObject, PK> {
	/**
	 * 分页查询
	 * 
	 * @param hql		HQL语句
	 * @param offset	开始页数
	 * @param pageSize	显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findHQLByPage(final String hql, final int offset, final int pageSize) throws Exception;
	

	/**
	 * 分页查询
	 * 
	 * @param hql		HQL语句
	 * @param map		参数Map数组对象
	 * @param offset	开始页数
	 * @param pageSize	显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findHQLByPage(final String hql, final Map<String, Object> map,
			final int offset, final int pageSize) throws Exception;


	/**
	 * 分页查询
	 * 
	 * @param hql		HQL语句
	 * @param values	参数Object数组对象
	 * @param offset	开始页数
	 * @param pageSize	显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findHQLByPage(final String hql, final Object[] values, final int offset,
			final int pageSize) throws Exception;
	
	/**
	 *查询所有存储器数据
	 * 
	 * @param hql	HQL语句
	 * @return List
	 */
	@SuppressWarnings("rawtypes")
	List getRepositories(String hql) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param sql		SQL语句
	 * @param offset	开始页数
	 * @param pageSize	显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findSQLByPage(final String sql, final int offset, final int pageSize) throws Exception;
	
	/**
	 * 分页查询
	 * 
	 * @param sql		SQL语句
	 * @param offset	开始页数
	 * @param pageSize	显示数量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findSQLByPage(final String sql, final int offset, final int pageSize,final Class clasz) throws Exception;
	
	/**
	 * 普通SQL查询
	 * 
	 * @param sql		SQL语句
	 * @param clasz		Class对象
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findSQL(final String sql,final Class clasz) throws Exception;
	
	/**
	 * 普通SQL查询
	 * 
	 * @param sql		SQL语句
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	List findSQL(final String sql) throws Exception;
	

	/**
	 * 插入实体
	 * 
	 * @param t		实体类对象
	 * @return
	 */
	T addRepository(T t) throws Exception;

	/**
	 * 更新实体
	 * 
	 * @param t		实体类对象
	 * @return
	 */
	T updateRepository(T t) throws Exception;
	
	/**
	 * 更新实体
	 * 
	 * @param t		实体类对象
	 * @return
	 */
	T mergeRepository(T t) throws Exception;
	
	/**
	 * 根据主键获取存储器对象
	 * 
	 * @param pk	主键
	 * @return
	 */
	T getRepository(PK pk) throws Exception;

	/**
	 * 获取存储器列表对象数
	 * 
	 * @param hql	HQL语句
	 * @return
	 */
	int getRepositoryByHQLListSize(String hql) throws Exception;
	
	/**
	 * 获取存储器列表对象数
	 * 
	 * @param sqls	SQL语句
	 * @return
	 */
	int getRepositoryBySQLListSize(String sql) throws Exception;


	/**
	 * 删除存储器对象
	 * 
	 * @param t		实体类对象
	 */
	void deleteRepository(T t) throws Exception;

	/**
	 * 批量删除存储器对象
	 * 
	 * @param tCollection	Collection集合对象
	 */
	void deleteRepositoryList(Collection<T> tCollection) throws Exception;
	
	/**
	 * 普通sql方式更新存储器
	 * 
	 * @param sql
	 * 
	 */
	void updateRepositoryBySql(final String sql) throws Exception;
}
