package com.ycszh.ssh.action.yanche;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.yanche.PdasmycChdlr;
import com.ycszh.ssh.service.yanche.PdasmycChdlrService;

/**
 * @包名:com.ycszh.ssh.action.yanche
 * @文件名:PdasmycChdlrAction.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-17
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChdlrAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private PdasmycChdlr pdasmycChdlr;
	private PdasmycChdlrService pdasmycChdlrService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<PdasmycChdlr> pdasmycChdlrList = new ArrayList<PdasmycChdlr>();
	
	// 车行机构信息列表
	public String initPdasmycChdlrList() throws Exception {
		this.setPdasmycChdlrList(pdasmycChdlrService.getPdasmycChdlrList(request, currentpage));
		return "list";
	}
	
	// 初始化添加页面
	public String insertPdasmycChdlr() throws Exception {
		request.setAttribute("editType", "新增");
		return "insert";
	}
	
	// 初始化修改页面
	public String updatePdasmycChdlr() throws Exception {
		if (pdasmycChdlr.getId() != null || !"".equals(pdasmycChdlr.getId())) {
			this.setPdasmycChdlr(pdasmycChdlrService.getPdasmycChdlr(pdasmycChdlr.getId()));
			if (this.getPdasmycChdlr() != null) {
				request.setAttribute("editType", "修改");
				return "update";
			} else {
				request.setAttribute("errorTip", "没有该车行机构信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该车行机构信息!");
			return "Exception";
		}
	}
	
	// 查看页面
	public String initPdasmycChdlr() throws Exception {
		if (pdasmycChdlr.getId() != null) {
			this.setPdasmycChdlr(pdasmycChdlrService.getPdasmycChdlr(pdasmycChdlr.getId()));
			if (this.getPdasmycChdlr() != null) {
				request.setAttribute("editType", "查看");
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该车行机构信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该车行机构信息!");
			return "Exception";
		}
	}
	
	// 编辑车行机构信息信息
	public String editPdasmycChdlr() throws Exception {
		if (pdasmycChdlr != null) {
			String pdasmycChdlrid = pdasmycChdlr.getId();
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = pdasmycChdlrService.insertOrUpdatePdasmycChdlr(pdasmycChdlr, request);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					if(null != pdasmycChdlrid && !"".equals(pdasmycChdlrid)){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chdlr_initPdasmycChdlrList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chdlr_initPdasmycChdlrList.action';");
					}
				} else {
					out.println("alert('编辑失败!')");
				}
			} catch (Exception e) {
				out.println("parent.closechuli();");
				e.printStackTrace();
				logger.error(e);
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				String estr = e.getMessage();
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				String exceptionstr = "异常信息:" + estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
				out.println("parent.exception('" + exceptionstr + "');");
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	// 删除车行机构信息信息
	public String deletePdasmycChdlr() throws Exception {
		String pdasmycChdlrId = request.getParameter("pdasmycChdlrId");
		if(null != pdasmycChdlrId){
			this.setReturnInteger(pdasmycChdlrService.deletePdasmycChdlr(pdasmycChdlrId));
			try {
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				PrintWriter out = response.getWriter();
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return null;
	}

	// 删除车行机构信息信息(多个)
	public String deletePdasmycChdlrList() throws Exception {
		String[] pdasmycChdlrIds = request.getParameterValues("ids");
		this.setReturnInteger(pdasmycChdlrService.deletePdasmycChdlrList(pdasmycChdlrIds));
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	// 代理人信息审核
	public String dlrshzt() throws Exception {
		String id = request.getParameter("id");
		String shzt = request.getParameter("shzt");
		String shbz = request.getParameter("shbz");
		if(null != id && null != shzt){
			this.setReturnInteger(pdasmycChdlrService.dlrshzt(request,id,shzt,shbz));
			try {
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				PrintWriter out = response.getWriter();
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return null;
	}
	
	
	
	
	public PdasmycChdlr getPdasmycChdlr() {
		return pdasmycChdlr;
	}
	public void setPdasmycChdlr(PdasmycChdlr pdasmycChdlr) {
		this.pdasmycChdlr = pdasmycChdlr;
	}
	public PdasmycChdlrService getPdasmycChdlrService() {
		return pdasmycChdlrService;
	}
	public void setPdasmycChdlrService(PdasmycChdlrService pdasmycChdlrService) {
		this.pdasmycChdlrService = pdasmycChdlrService;
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
	public List<PdasmycChdlr> getPdasmycChdlrList() {
		return pdasmycChdlrList;
	}
	public void setPdasmycChdlrList(List<PdasmycChdlr> pdasmycChdlrList) {
		this.pdasmycChdlrList = pdasmycChdlrList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}
}
