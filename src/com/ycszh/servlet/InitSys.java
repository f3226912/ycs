package com.ycszh.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

/**
 * @author dengsiqi E-mail:dengsiqi@vip.qq.com
 * @version 创建时间：2012-4-8 下午05:07:38
 * InitSys.java类说明
 */
public class InitSys extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		initLog4j();
	}

	private void initLog4j() {
		PropertyConfigurator.configure(this.getServletContext().getRealPath("/WEB-INF/classes/log4j.properties"));
	}
}
