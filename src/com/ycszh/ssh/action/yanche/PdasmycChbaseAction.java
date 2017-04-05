package com.ycszh.ssh.action.yanche;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.yanche.PdasmycChbase;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;
import com.ycszh.ssh.service.yanche.PdasmycChbaseService;

/**
 * @包名:com.ycszh.ssh.action.yanche
 * @文件名:PdasmycChbaseAction.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-10
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycChbaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private PdasmycChbase pdasmycChbase;
	private PdasmycChbaseService pdasmycChbaseService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<PdasmycChbase> pdasmycChbaseList = new ArrayList<PdasmycChbase>();
	private List<YwlsglVehSjzd> ywlsglVehSjzdList = new ArrayList<YwlsglVehSjzd>();
	
	// 车行机构信息列表
	public String initPdasmycChbaseList() throws Exception {
		this.setPdasmycChbaseList(pdasmycChbaseService.getPdasmycChbaseList(request, currentpage));
		return "list";
	}
	
	// 初始化添加页面
	public String insertPdasmycChbase() throws Exception {
		this.setYwlsglVehSjzdList(pdasmycChbaseService.getYwlsglVehSjzdList("1001"));
		request.setAttribute("editType", "新增");
		return "insert";
	}
	
	// 初始化修改页面
	public String updatePdasmycChbase() throws Exception {
		if (pdasmycChbase.getId() != null || !"".equals(pdasmycChbase.getId())) {
			this.setYwlsglVehSjzdList(pdasmycChbaseService.getYwlsglVehSjzdList("1001"));
			this.setPdasmycChbase(pdasmycChbaseService.getPdasmycChbase(pdasmycChbase.getId()));
			if (this.getPdasmycChbase() != null) {
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
	public String initPdasmycChbase() throws Exception {
		if (pdasmycChbase.getId() != null) {
			this.setYwlsglVehSjzdList(pdasmycChbaseService.getYwlsglVehSjzdList("1001"));
			this.setPdasmycChbase(pdasmycChbaseService.getPdasmycChbase(pdasmycChbase.getId()));
			if (this.getPdasmycChbase() != null) {
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
	public String editPdasmycChbase() throws Exception {
		if (pdasmycChbase != null) {
			String pdasmycChbaseid = pdasmycChbase.getId();
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = pdasmycChbaseService.insertOrUpdatePdasmycChbase(pdasmycChbase, request);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					if(null != pdasmycChbaseid && !"".equals(pdasmycChbaseid)){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chbase_initPdasmycChbaseList.action';");
					}else{
						out.println("alert('添加成功!');");
						//out.println("parent.cleanmyform();");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chbase_initPdasmycChbaseList.action';");
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
	public String deletePdasmycChbase() throws Exception {
		String pdasmycChbaseId = request.getParameter("pdasmycChbaseId");
		if(null != pdasmycChbaseId){
			this.setReturnInteger(pdasmycChbaseService.deletePdasmycChbase(pdasmycChbaseId));
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
	public String deletePdasmycChbaseList() throws Exception {
		String[] pdasmycChbaseIds = request.getParameterValues("ids");
		this.setReturnInteger(pdasmycChbaseService.deletePdasmycChbaseList(pdasmycChbaseIds));
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
	
	
	
	
	
	
	public PdasmycChbase getPdasmycChbase() {
		return pdasmycChbase;
	}
	public void setPdasmycChbase(PdasmycChbase pdasmycChbase) {
		this.pdasmycChbase = pdasmycChbase;
	}
	public PdasmycChbaseService getPdasmycChbaseService() {
		return pdasmycChbaseService;
	}
	public void setPdasmycChbaseService(PdasmycChbaseService pdasmycChbaseService) {
		this.pdasmycChbaseService = pdasmycChbaseService;
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
	public List<PdasmycChbase> getPdasmycChbaseList() {
		return pdasmycChbaseList;
	}
	public void setPdasmycChbaseList(List<PdasmycChbase> pdasmycChbaseList) {
		this.pdasmycChbaseList = pdasmycChbaseList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * @return the ywlsglVehSjzdList
	 */
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList() {
		return ywlsglVehSjzdList;
	}

	/**
	 * @param ywlsglVehSjzdList the ywlsglVehSjzdList to set
	 */
	public void setYwlsglVehSjzdList(List<YwlsglVehSjzd> ywlsglVehSjzdList) {
		this.ywlsglVehSjzdList = ywlsglVehSjzdList;
	}
	
	
}
