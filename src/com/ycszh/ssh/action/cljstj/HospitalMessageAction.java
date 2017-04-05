package com.ycszh.ssh.action.cljstj;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.cljstj.HosHospitalUserinfo;
import com.ycszh.ssh.service.cljstj.HospitalMessageService;

public class HospitalMessageAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HospitalMessageAction.class);
	private int currentpage = 1;
	private HospitalMessageService hospitalMessageService;
	private HosHospitalUserinfo hosHospitalUserinfo;
	private List<HosHospitalUserinfo> hospitalUserinfoList=new ArrayList<HosHospitalUserinfo>();
	private String returnInteger;
	
	/**
	 * 医院信息查询
	 */
	public String hospitalUserinfoQuery() throws Exception {
		this.hospitalUserinfoList= hospitalMessageService.hospitalUserInfoQuery(request, currentpage);
		return "huserinfolist";
	}
	
	/**
	 * 医生信息审核
	 * @return
	 * @throws Exception
	 */
	public String doctorMessageAuditList() throws Exception {
		hospitalMessageService.doctorUserInfoAuditQuery(request, currentpage);
		return "doctorMessageAudit";
	}
	
	public String saveHospitalUserinfo()throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			returnInteger = hospitalMessageService.insertHospitalUserInfo(request);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
}
	
	public String queryHospitalUserinfo()throws Exception{
		this.hosHospitalUserinfo = hospitalMessageService.queryHospitalUserInfoByYyxh(request);
		return "getHospitalUserinfo";
	}
	
	public String deleteHospitalUserInfo()throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			returnInteger = hospitalMessageService.deleteHospitalUserInfoByYyxh(request);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
}
	
	public String deleteHospitalUserInfoAll()throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			returnInteger = hospitalMessageService.deleteHospitalUserInfoAll(request);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
}
	
	public String HospitalUserStartOrStop()throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			returnInteger = hospitalMessageService.hospitalUserStartOrStop(request);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
}
	
	public String doctormessageAudit()throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try {
			returnInteger = hospitalMessageService.doctormessageAudit(request);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}
	
	public HospitalMessageService getHospitalMessageService() {
		return hospitalMessageService;
	}

	public void setHospitalMessageService(HospitalMessageService hospitalMessageService) {
		this.hospitalMessageService = hospitalMessageService;
	}
	
	public HosHospitalUserinfo getHosHospitalUserinfo() {
		return hosHospitalUserinfo;
	}

	public void setHosHospitalUserinfo(HosHospitalUserinfo hosHospitalUserinfo) {
		this.hosHospitalUserinfo = hosHospitalUserinfo;
	}

	public List<HosHospitalUserinfo> getHospitalUserinfoList() {
		return hospitalUserinfoList;
	}

	public void setHospitalUserinfoList(List<HosHospitalUserinfo> hospitalUserinfoList) {
		this.hospitalUserinfoList = hospitalUserinfoList;
	}
	
	public String getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(String returnInteger) {
		this.returnInteger = returnInteger;
	}
}