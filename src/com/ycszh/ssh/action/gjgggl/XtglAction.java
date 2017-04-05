package com.ycszh.ssh.action.gjgggl;

import java.io.PrintWriter;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.gjgggl.BusBase;
import com.ycszh.ssh.service.gjgggl.XtglService;

import common.Logger;

/**
 * 系统管理
 * 
 * @author ldy
 * 
 */
public class XtglAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(XtglAction.class);

	private XtglService xtglService;

	private BusBase busBase;

	/**
	 * 初始化公交账户管理界面数据
	 * 
	 * @return
	 */
	public String getBusBaseInital() throws Exception {
		xtglService.getBusBaseInital(request);
		return "gjgszhgl";
	}

	public String updateBusBasePwd() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = xtglService.updateBusBasePwd(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;

	}

	public String addBusBase() throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = xtglService.addBusBase(request, busBase);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;
	}

	public String getSzzdInital() throws Exception {
		xtglService.getSzzdInital(request);
		return "szzdgl";
	}

	public String updateSzzd() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = xtglService.updateSzzd(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}

		out.write(result);
		return NONE;

	}

	public XtglService getXtglService() {
		return xtglService;
	}

	public void setXtglService(XtglService xtglService) {
		this.xtglService = xtglService;
	}

	public static Logger getLogger() {
		return logger;
	}

	public BusBase getBusBase() {
		return busBase;
	}

	public void setBusBase(BusBase busBase) {
		this.busBase = busBase;
	}

}
