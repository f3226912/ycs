package com.ycszh.ssh.action.kgpb;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbTsrqb;
import com.ycszh.ssh.service.kcpb.KgpbTsrqbService;

/**
 * @包名:com.ycszh.ssh.action.kgpb
 * @文件名:KgpbTsrqbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-18
 * @描述:
 * @版本:V 1.0
 */
public class KgpbTsrqbAction extends BaseAction  {

	private KgpbTsrqbService kgpbTsrqbService;
	private KgpbTsrqb tsrqb;
	private int currentpage = 1;
	private static final Logger logger = Logger.getLogger(KgpbTsrqbAction.class);
	
	
	public KgpbTsrqbService getKgpbTsrqbService() {
		return kgpbTsrqbService;
	}
	public void setKgpbTsrqbService(KgpbTsrqbService kgpbTsrqbService) {
		this.kgpbTsrqbService = kgpbTsrqbService;
	}
	public KgpbTsrqb getTsrqb() {
		return tsrqb;
	}
	public void setTsrqb(KgpbTsrqb tsrqb) {
		this.tsrqb = tsrqb;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	

	public String getTsrqbList() throws Exception{
		
		kgpbTsrqbService.getTsrqbList(request, currentpage);
		
		request.setAttribute("kcxxbList", kgpbTsrqbService.getKgInfoBySql("select * from Kgpb_Kcxxb order by to_number(kcbh)", KgpbKcxxb.class));
		
		return "list";
	}
	
	public String initTsrqbUpdate() throws Exception{
		
		String id = request.getParameter("id");
		
		tsrqb = kgpbTsrqbService.getTsrqb(id);
		
		request.setAttribute("kcxxbList", kgpbTsrqbService.getKgInfoBySql("select * from Kgpb_Kcxxb", KgpbKcxxb.class));
		
		request.setAttribute("typeName", "修改");
		return "update";
	}
	
	public String addOrUpdateTsrqb() throws Exception{
		
		PrintWriter out = null;
		
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			String retValue = "";
			if(tsrqb.getId() == null || "".equals(tsrqb.getId())){
				retValue = "添加成功";
			}
			else{
				retValue = "修改成功";
			}
			int i = kgpbTsrqbService.addOrUpdateTsrqb(tsrqb,request);
			if(i == 0){
				out.print("<script>alert('"+retValue+"'); parent.window.location.href('"+request.getContextPath()+"/kgpb/tsrqb_getTsrqbList.action')</script>");
			}
			else{
				out.print("<script>alert('操作失败！');</script>");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.toString();
			estr = estr.replaceAll("\r", "");
			estr = estr.replaceAll("\n", "");
			estr = estr.replaceAll("\t", "");
			estr = estr.replaceAll("\f", "");
			estr = estr.replaceAll("\b", "");
			String exceptionstr = "异常信息:" + estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			out.println(exceptionstr);
		}
		finally{
			if(out != null){
				out.flush();
				out.close();
			}
			
		}
		return NONE;
	}
	
	public String delTsrqb() throws Exception{
		
		PrintWriter out = null;
		
		String id = request.getParameter("id");
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			
			int i = kgpbTsrqbService.deleteTsrqb(id, request);
			out.print(i);
		}
		catch (Exception e) {
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.toString();
			estr = estr.replaceAll("\r", "");
			estr = estr.replaceAll("\n", "");
			estr = estr.replaceAll("\t", "");
			estr = estr.replaceAll("\f", "");
			estr = estr.replaceAll("\b", "");
			String exceptionstr = "异常信息:" + estr + "</br>文件名:"
					+ stackTraceElement.getFileName() + "</br>行数:"
					+ stackTraceElement.getLineNumber() + "</br>方法名:"
					+ stackTraceElement.getMethodName();
			out.println(exceptionstr);
		}
		finally{
			if(out != null){
				out.flush();
				out.close();
			}
			
		}
		
		return NONE;
	}
	
	public String initAddTsrqb() throws Exception{
		request.setAttribute("typeName", "新增");
		request.setAttribute("kcxxbList", kgpbTsrqbService.getKgInfoBySql("select * from Kgpb_Kcxxb", KgpbKcxxb.class));
		return "update";
	}
	
	public String initSelTsrqb() throws Exception{
		
		tsrqb = kgpbTsrqbService.getTsrqb(request.getParameter("id"));
		request.setAttribute("typeName", "查看");
		request.setAttribute("kcxxbList", kgpbTsrqbService.getKgInfoBySql("select * from Kgpb_Kcxxb", KgpbKcxxb.class));
		return "update";
	}
	
}
