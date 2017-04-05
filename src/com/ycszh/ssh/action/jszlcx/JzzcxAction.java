package com.ycszh.ssh.action.jszlcx;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.jszlcx.JzzcxService;

public class JzzcxAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(JzzcxAction.class);
	
	private JzzcxService jzzcxService;
	
	// 居住证信息集合
	@SuppressWarnings("unchecked")
	private List jzzcxList = new ArrayList();

	public String jzzcxList() throws Exception{
		try {
			jzzcxService.getPro(request);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jzzxxList";
	}
	
	// 居住证查询
	public String jzzcxList2() throws Exception {
		response.setCharacterEncoding("GBK");	//IE用GBK，火狐用UTF-8
		PrintWriter out = response.getWriter();
		try{
			jzzcxService.getPro(request);	
			out.println("<script>");
			Object r = (String) request.getSession().getAttribute("result");
			//Object r = (String) request.getAttribute("result");
			if (r.equals("0")) {
				out.println("alert('输入信息有误，请重新输入！');");
				out.println("parent.window.location.href = '"+request.getContextPath()+"/pages/jszlcx/jzzcx.jsp';");
			} else {
				out.println("parent.window.location.href = '"+request.getContextPath()+"/pages/jszlcx/jzzcx.jsp';");
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			logger.error(e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if(estr != null){
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			}else{
				exceptionstr += " 获取连接异常";
			}
			out.println("parent.exception('" + exceptionstr + "');");
		}
		out.println("</script>");
		return null;
		
	}
	
	public JzzcxService getJzzcxService() {
		return jzzcxService;
	}

	public void setJzzcxService(JzzcxService jzzcxService) {
		this.jzzcxService = jzzcxService;
	}

	@SuppressWarnings("unchecked")
	public List getJzzcxList() {
		return jzzcxList;
	}

	@SuppressWarnings("unchecked")
	public void setJzzcxList(List jzzcxList) {
		this.jzzcxList = jzzcxList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}
	
	
}
