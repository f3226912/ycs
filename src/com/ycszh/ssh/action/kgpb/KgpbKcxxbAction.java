package com.ycszh.ssh.action.kgpb;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;
import com.ycszh.ssh.service.kcpb.KgpbKcxxbService;
import com.ycszh.ssh.service.kcpb.KgpbKgxxbService;

/**
 * @包名:com.ycszh.ssh.action.kgpb
 * @文件名:KgpbkcxxbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-17
 * @描述:
 * @版本:V 1.0
 */
public class KgpbKcxxbAction extends BaseAction {

	private KgpbKcxxbService kgpbKcxxbService;
	private KgpbKgxxbService kgpbKgxxbService;
	private KgpbKcxxb kcxxb;
	private int currentpage = 1;
	private static final Logger logger = Logger.getLogger(KgpbKcxxbAction.class);
	
	public KgpbKcxxb getKcxxb() {
		return kcxxb;
	}
	public void setKcxxb(KgpbKcxxb kcxxb) {
		this.kcxxb = kcxxb;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public KgpbKcxxbService getKgpbKcxxbService() {
		return kgpbKcxxbService;
	}
	public void setKgpbKcxxbService(KgpbKcxxbService kgpbKcxxbService) {
		this.kgpbKcxxbService = kgpbKcxxbService;
	}
	public KgpbKgxxbService getKgpbKgxxbService() {
		return kgpbKgxxbService;
	}
	public void setKgpbKgxxbService(KgpbKgxxbService kgpbKgxxbService) {
		this.kgpbKgxxbService = kgpbKgxxbService;
	}
	
	public String getKcInfoList() throws Exception{
		
		kgpbKcxxbService.getKgpbKcxxbList(request, currentpage);
		
		return "list";
	}
	
	public String initKcUpdate() throws Exception{
		
		String id = request.getParameter("id");
		
		kcxxb = kgpbKcxxbService.getKgpbKcxxb(id);
		
		List list = kgpbKgxxbService.getKgInfoBySql("select id,xm from kgpb_kgxxb where zt = '2' and id not in (select gdkgid from kgpb_kcxxb where gdkgid is not null) union "
										+" select id,xm from kgpb_kgxxb where id in (select gdkgid from kgpb_kcxxb where id = '"+id+"')");
		
		request.setAttribute("gdkgList", list);
		
		request.setAttribute("typeName", "修改");
		return "update";
	}
	
	public String addOrUpdateKcInfo() throws Exception{
		
		PrintWriter out = null;
		
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			String retValue = "";
			if(kcxxb.getId() == null || "".equals(kcxxb.getId())){
				retValue = "添加成功";
			}
			else{
				retValue = "修改成功";
			}
			int i = kgpbKcxxbService.addOrUpdateKcInfo(kcxxb,request);
			if(i == 0){
				out.print("<script>alert('"+retValue+"'); parent.window.location.href('"+request.getContextPath()+"/kgpb/kcxxb_getKcInfoList.action')</script>");
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
	
	public String delKcInfo() throws Exception{
		
		PrintWriter out = null;
		
		String id = request.getParameter("id");
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			
			int i = kgpbKcxxbService.deleteKcInfo(id, request);
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
	
	public String initAddKcInfo() throws Exception{
		request.setAttribute("typeName", "新增");
		
		List list = kgpbKgxxbService.getKgInfoBySql("select id,xm from kgpb_kgxxb where zt = '2' and id not in (select gdkgid from kgpb_kcxxb)");
		request.setAttribute("gdkgList", list);
		
		return "update";
	}
	
	public String initSelKc() throws Exception{
		
		String id = request.getParameter("id");
		
		kcxxb = kgpbKcxxbService.getKgpbKcxxb(id);
		request.setAttribute("typeName", "查看");
		
		List list = kgpbKgxxbService.getKgInfoBySql("select id,xm from kgpb_kgxxb where zt = '2' and id not in (select gdkgid from kgpb_kcxxb where gdkgid is not null) union "
				+" select id,xm from kgpb_kgxxb where id in (select gdkgid from kgpb_kcxxb where id = '"+id+"')");

		request.setAttribute("gdkgList", list);
		
		return "update";
	}
}
