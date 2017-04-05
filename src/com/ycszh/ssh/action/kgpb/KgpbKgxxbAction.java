package com.ycszh.ssh.action.kgpb;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbKgxxb;
import com.ycszh.ssh.service.kcpb.KgpbKgxxbService;

/**
 * @包名:com.ycszh.ssh.action.kgpb
 * @文件名:KgpbkcxxbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-17
 * @描述:
 * @版本:V 1.0
 */
public class KgpbKgxxbAction extends BaseAction {

	private KgpbKgxxbService kgpbKgxxbService;
	private KgpbKgxxb kgxxb;
	private int currentpage = 1;
	private static final Logger logger = Logger.getLogger(KgpbKgxxbAction.class);
	
	
	public KgpbKgxxb getKgxxb() {
		return kgxxb;
	}
	public void setKgxxb(KgpbKgxxb kgxxb) {
		this.kgxxb = kgxxb;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public KgpbKgxxbService getKgpbKgxxbService() {
		return kgpbKgxxbService;
	}
	public void setKgpbKgxxbService(KgpbKgxxbService kgpbKgxxbService) {
		this.kgpbKgxxbService = kgpbKgxxbService;
	}
	
	private List getKjDept() throws Exception{
		return kgpbKgxxbService.getKgInfoBySql("select org_id,org_name from vehoffice.v_veh_org_ycs t start with org_id='C34702A8FEF17CBFE040007F0100339B' connect by prior org_id = up_org");
	}
	
	public String getKgInfoList() throws Exception{
		// 拿到科级部门
		List list = getKjDept();
		request.setAttribute("kjList", list);
		
		kgpbKgxxbService.getKgInfoList(request, currentpage);
		
		return "list";
	}
	
	public String initKgUpdate() throws Exception{
		
		String id = request.getParameter("id");
		
		kgxxb = kgpbKgxxbService.getKgInfo(id);
		
		// 拿到科级部门
		List list = getKjDept();
		request.setAttribute("kjList", list);
		
		request.setAttribute("typeName", "修改");
		return "update";
	}
	
	public String addOrUpdateKgInfo() throws Exception{
		
		PrintWriter out = null;
		
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			String retValue = "";
			if(kgxxb.getId() == null || "".equals(kgxxb.getId())){
				retValue = "添加成功";
			}
			else{
				retValue = "修改成功";
			}
			int i = kgpbKgxxbService.addOrUpdateKgInfo(kgxxb,request);
			if(i == 0){
				out.print("<script>alert('"+retValue+"'); parent.window.location.href('"+request.getContextPath()+"/kgpb/kgxxb_getKgInfoList.action')</script>");
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
	
	public String delKgInfo() throws Exception{
		
		PrintWriter out = null;
		
		String id = request.getParameter("id");
		try{
			response.setCharacterEncoding("GBK");
			out = response.getWriter();
			
			int i = kgpbKgxxbService.deleteKgInfo(id, request);
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
	
	public String initAddKgInfo() throws Exception{
		request.setAttribute("typeName", "新增");
		// 拿到科级部门
		List list = getKjDept();
		request.setAttribute("kjList", list);
		
		return "update";
	}
	
	public String initSelKg() throws Exception{
		
		kgxxb = kgpbKgxxbService.getKgInfo(request.getParameter("id"));
		request.setAttribute("typeName", "查看");
		// 拿到科级部门
		List list = getKjDept();
		request.setAttribute("kjList", list);
		
		return "update";
	}
}
