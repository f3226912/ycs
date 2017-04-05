package com.ycszh.ssh.action.pdaycimeigl;

import java.io.PrintWriter;
import java.util.List;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.pdaycimeigl.TPdaYcimei;
import com.ycszh.ssh.service.pdaycimeigl.PdaIMEIglService;
import com.ycszh.util.JsonUtil;

import common.Logger;

/**
 * PDA验车IMEI码登记管理
 * 
 * @author ldy
 * 
 */
public class PdaIMEIglAction extends BaseAction {

	private static final Logger logger = Logger.getLogger(PdaIMEIglAction.class);
	private PdaIMEIglService pdaIMEIglService;
	private TPdaYcimei pdaYcimei;
	private String returnString;
	
	//通过部门编号级联获取用户信息
	public String getUserbydept() throws Exception {
		PrintWriter out = response.getWriter();
		this.setReturnString(pdaIMEIglService.getUserByDeptId(request));
		response.setCharacterEncoding("GBK");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out.println(returnString);
		out.flush();
		out.close();
		
		return null;
	}


	//获得登记管理列表
	public String getTPdaYcimei() throws Exception {
		pdaIMEIglService.getTPdaYcimei(request);
		return "tPdaYcimei";
	}
	
	//初始化修改页面
	public String getTPdaYcimeiToChange() throws Exception {
		pdaIMEIglService.getTPdaYcimeiToChange(request);
		return "tPdaYcimeiChange";
	}
	
	//提交修改
	public String updateTPdaYcimei() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = pdaIMEIglService.updateTPdaYcimei(request, pdaYcimei);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String getTPdaYcimeiToAdd() throws Exception {
		pdaIMEIglService.getTPdaYcimeiToAdd(request);
		return "tPdaYcimeiAdd";
	}

	public String addTPdaYcimei() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = pdaIMEIglService.addTPdaYcimei(request, pdaYcimei);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String deleteTPdaYcimei() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String result = "";
		try {
			result = pdaIMEIglService.deleteTPdaYcimei(request);
		} catch (Exception e) {
			result = "-" + e.getMessage();
		}
		out.write(result);
		out.flush();
		out.close();

		return NONE;
	}

	public String getExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String ssbm = request.getParameter("ssbm") == null ? "" : request.getParameter("ssbm").trim();
			String IMEI = request.getParameter("IMEI") == null ? "" : request.getParameter("IMEI").trim();
			pdaIMEIglService.getOutExcel(response, request, ssbm, IMEI);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return NONE;
	}

	public PdaIMEIglService getPdaIMEIglService() {
		return pdaIMEIglService;
	}

	public void setPdaIMEIglService(PdaIMEIglService pdaIMEIglService) {
		this.pdaIMEIglService = pdaIMEIglService;
	}

	public static Logger getLogger() {

		return logger;
	}

	public TPdaYcimei getPdaYcimei() {
		return pdaYcimei;
	}

	public void setPdaYcimei(TPdaYcimei pdaYcimei) {
		this.pdaYcimei = pdaYcimei;
	}

	public String getReturnString() {
		return returnString;
	}

	public void setReturnString(String returnString) {
		this.returnString = returnString;
	}
	
}
