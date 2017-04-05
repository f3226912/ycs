package com.ycszh.common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文 件 名:  ZxdLogger.java
 * 描    述:  <描述>
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 * 修 改 人: PENGGUOJUN
 */

/**
 * 日志操作类
 * 
 * @author PENGGUOJUN
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public final class ZxdLogger {

	/**
	 * <默认构造函数>
	 */
	public ZxdLogger() {
	}

	/**
	 * 定义一个全局静态日志类变量
	 */
	public static Logger logger;

	/**
	 * 返回Logger日志对象
	 * 
	 * @param 类
	 *            [参数说明]
	 * @return Logger [返回类型说明]
	 * @athour PENGGUOJUN
	 */
	public static Logger getZxdLogger(Class className) {
		logger = LoggerFactory.getLogger(className.getClass());
		return logger;
	}
	/**
	 * 返回Logger日志对象
	 * @param logName 【String类型】
	 * @return
	 */
	public static Logger getZxdLogger(String logName) {
		logger = LoggerFactory.getLogger(logName);
		return logger;
	}

}
