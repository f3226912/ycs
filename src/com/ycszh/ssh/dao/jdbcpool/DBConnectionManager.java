package com.ycszh.ssh.dao.jdbcpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

/**
 * @包名:com.ycszh.ssh.dao.jdbcpool
 * @文件名:DBConnectionManager.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2012-12-3
 * @描述:
 * @版本:V 1.0
 */
public class DBConnectionManager {
	static private DBConnectionManager instance;// 唯一数据库连接池管理实例类
	@SuppressWarnings("unused")
	static private int clients; // 客户连接数
	@SuppressWarnings("unchecked")
	private Vector drivers = new Vector();// 驱动信息
	@SuppressWarnings("unchecked")
	private Hashtable pools = new Hashtable();// 连接池

	/**
	 * 实例化管理类
	 */
	public DBConnectionManager() {

		this.init();
	}

	/**
	 * 得到唯一实例管理类
	 * 
	 * @return
	 */
	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}
		return instance;

	}

	/**
	 * 释放连接
	 * 
	 * @param name
	 * @param con
	 */
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);// 根据关键名字得到连接池
		if (pool != null)
			pool.freeConnection(con);// 释放连接
	}

	/**
	 * 得到一个连接根据连接池的名字name
	 * 
	 * @param name
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConnection(String name) throws SQLException {
		DBConnectionPool pool = null;
		Connection con = null;
		pool = (DBConnectionPool) pools.get(name);// 从名字中获取连接池
		con = pool.getConnection();// 从选定的连接池中获得连接
		if (con != null) {
			if(this.checkConnected(con)){
				System.out.println("得到连接。。。");
			}else{
				this.release();
				this.init();
				//getConnection(name);
			}
		}
		return con;
	}

	/**
	 * 得到一个连接，根据连接池的名字和等待时间
	 * 
	 * @param name
	 * @param time
	 * @return
	 * @throws SQLException 
	 */
	public Connection getConnection(String name, long timeout) throws SQLException {
		DBConnectionPool pool = null;
		Connection con = null;
		pool = (DBConnectionPool) pools.get(name);// 从名字中获取连接池
		con = pool.getConnection(timeout);// 从选定的连接池中获得连接
		if (con != null) {
			if(this.checkConnected(con)){
				System.out.println("得到连接。。。");
			}else{
				this.release();
				this.init();
				//getConnection(name);
			}
		}
		return con;
	}

	/**
	 * 释放所有连接
	 */
	@SuppressWarnings("unchecked")
	public synchronized void release() {
		Enumeration allpools = pools.elements();
		while (allpools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allpools.nextElement();
			if (pool != null)
				pool.release();
		}
		pools.clear();
	}

	/**
	 * 创建连接池
	 * 
	 * @param props
	 */
	@SuppressWarnings("unchecked")
	private void createPools(DSConfigBean dsb) {
		DBConnectionPool dbpool = new DBConnectionPool();
		dbpool.setName(dsb.getName());
		dbpool.setDriver(dsb.getDriver());
		dbpool.setUrl(dsb.getUrl());
		dbpool.setUser(dsb.getUsername());
		dbpool.setPassword(dsb.getPassword());
		dbpool.setMaxConn(dsb.getMaxconn());
		System.out.println("ioio:" + dsb.getMaxconn());
		pools.put(dsb.getName(), dbpool);
	}

	/**
	 * 初始化连接池的参数
	 */
	@SuppressWarnings("unchecked")
	private void init() {
		// 加载驱动程序
		this.loadDrivers();
		// 创建连接池
		Iterator alldriver = drivers.iterator();
		while (alldriver.hasNext()) {
			this.createPools((DSConfigBean) alldriver.next());
			System.out.println("创建连接池。。。");
		}
		System.out.println("创建连接池完毕。。。");
	}

	/**
	 * 加载驱动程序
	 * 
	 * @param props
	 */
	private void loadDrivers() {
		ParseDSConfig pd = new ParseDSConfig();
		// 读取数据库配置文件
		drivers = pd.readConfigInfo("ds.config.xml");
		System.out.println("加载驱动程序。。。");
	}

	/**
	 * 判断连接是否正常工作
	 * 
	 * @param connection
	 *            数据库连接对象
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private boolean checkConnected(Connection connection) throws SQLException {
		// 连接为空或已经关闭
		if (connection == null || connection.isClosed()) {
			return (false);
		}
		boolean connected = false;
		Statement stat = null;
		try {
			stat = connection.createStatement();
			stat.close();
			connected = true;
		} catch (SQLException e) {

		} finally {
			try {
				if (stat != null) {
					stat.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (connected);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DBConnectionManager connectionMan = DBConnectionManager.getInstance();// 得到唯一实例
		String name = "test250";// 从上下文得到你要访问的数据库的名字
		Connection conn = null;
		Statement stmt = null;
		// ResultSet rs = null;
		try {
			for (int i = 0; i < 100; i++) {
				conn = connectionMan.getConnection(name);
				if (null != conn) {
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					String sql = "insert into TABLETEST values('" + i + "','"
							+ i + "')";
					stmt.executeUpdate(sql);
					conn.commit();
					stmt.close();
					stmt = null;
				} else {
					System.out.println("没有空闲连接了!");
				}
				// connectionMan.freeConnection(name,conn);//释放，但并未断开连接
				/*
				 * if(null != stmt){ stmt.close(); stmt = null; } if(null !=
				 * conn){ conn.close(); conn = null; }
				 */
				System.out.println(i);
			}
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			connectionMan.freeConnection(name, conn);// 释放，但并未断开连接
		}
	}
}
