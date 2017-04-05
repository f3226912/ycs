package com.ycszh.ssh.action.jyzwcj;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.jyzwcj.PoliceFingerInfo;
import com.ycszh.ssh.service.jyzwcj.JyzwcjService;

public class JyzwcjAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(JyzwcjAction.class);
	private int currentpage = 1;
	private Integer returnInteger = 0;

	private PoliceFingerInfo finger;
	private JyzwcjService jyzwcjService;
	
	@SuppressWarnings({ "unused", "unchecked" })
	private List userList = new ArrayList();		//部门警员信息集合
	
	@SuppressWarnings("unchecked")					
	private List jyxxList = new ArrayList();		//单个警员信息集合
	
	// 部门用户信息列表
	public String bmjyList() throws Exception {
		try {
			this.setUserList(jyzwcjService.bmjyList(request, currentpage));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bmjyList";
	}
	
	// 初始化采集页面
	public String jyxx() throws Exception{
		try {
			this.setJyxxList(jyzwcjService.getJYxx(request));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jyxx";
	}
	
	// 指纹采集
	public String zwcjInsert() throws Exception {
		response.setCharacterEncoding("GBK");	//IE用GBK，火狐用UTF-8
		PrintWriter out = response.getWriter();
		try{
			returnInteger = jyzwcjService.insertFinger(finger, request);	
			out.println("<script>");
			if (returnInteger == 0) {
				out.println("alert('指纹采集成功!');");	// 未采集添加，已采集修改
				out.println("parent.cleanmyform();");
			} else {
				out.println("alert('指纹采集失败!')");
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

	
	@SuppressWarnings("unchecked")
	public void setUserList(List userList) {
		this.userList = userList;
	}
	
	
	@SuppressWarnings("unchecked")
	public List getJyxxList() {
		return jyxxList;
	}

	@SuppressWarnings("unchecked")
	public void setJyxxList(List jyxxList) {
		this.jyxxList = jyxxList;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public JyzwcjService getJyzwcjService() {
		return jyzwcjService;
	}

	public void setJyzwcjService(JyzwcjService jyzwcjService) {
		this.jyzwcjService = jyzwcjService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public PoliceFingerInfo getFinger() {
		return finger;
	}

	public void setFinger(PoliceFingerInfo finger) {
		this.finger = finger;
	}
	
	
	
}