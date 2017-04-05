package com.ycszh.ssh.action.xyc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.xyc.YcsXycCode;
import com.ycszh.ssh.service.xyc.YcsXycCodeService;

public class YcsXycCodeAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(YcsXycCodeAction.class);
	private YcsXycCode YcsXycCode;
	private YcsXycCodeService ycsXycCodeService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<YcsXycCode> ycsXycCodeList = new ArrayList<YcsXycCode>();
	
	// 嫌疑车字典信息列表
	public String initYcsXycCodeList() throws Exception {
		this.setYcsXycCodeList(ycsXycCodeService.getYcsXycCodeList(request, currentpage));
		request.setAttribute("ycsXycCodeList", ycsXycCodeList);
		//request.setAttribute("dmlbList", ycsXycCodeService.getDmlbList());
		return "list";
	}
	
	// 初始化添加页面
	public String insertYcsXycCode() throws Exception {
		request.setAttribute("editType", "新增");
		return "insert";
	}
	
	// 初始化修改页面
	public String updateYcsXycCode() throws Exception {
		if (YcsXycCode.getId() != null || !"".equals(YcsXycCode.getId())) {
			this.setYcsXycCode(ycsXycCodeService.getYcsXycCode(YcsXycCode.getId()));
			if (this.getYcsXycCode() != null) {
				request.setAttribute("editType", "修改");
				return "update";
			} else {
				request.setAttribute("errorTip", "没有该数据字典信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该数据字典信息!");
			return "Exception";
		}
	}
	
	// 查看页面
	public String initYcsXycCode() throws Exception {
		if (YcsXycCode.getId() != null) {
			this.setYcsXycCode(ycsXycCodeService.getYcsXycCode(YcsXycCode.getId()));
			if (this.getYcsXycCode() != null) {
				request.setAttribute("editType", "查看");
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该数据字典信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该数据字典信息!");
			return "Exception";
		}
	}
	
	// 编辑嫌疑车字典信息
	public String editYcsXycCode() throws Exception {
		if (YcsXycCode != null) {
			String YcsXycCodeid = YcsXycCode.getId();
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = ycsXycCodeService.insertOrUpdateYcsXycCode(YcsXycCode, request);
				if (returnInteger == 0) {
					if(null != YcsXycCodeid && !"".equals(YcsXycCodeid)){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/xyc/xyccode_initYcsXycCodeList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/xyc/xyccode_initYcsXycCodeList.action';");
					}
				} else {
					out.println("alert('编辑失败!')");
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
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	// 删除嫌疑车字典信息
	public String deleteYcsXycCode() throws Exception{
		String YcsXycCodeId = request.getParameter("YcsXycCodeId");
		if(null != YcsXycCodeId){
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			try {
				this.setReturnInteger(ycsXycCodeService.deleteYcsXycCode(request, YcsXycCodeId));
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println(returnInteger);
				out.flush();
				out.close();
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
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println("异常信息："+exceptionstr);
				out.flush();
				out.close();
			}
		}
		return null;
	}

	// 删除嫌疑车字典信息(多个)
	public String deleteYcsXycCodeList() throws Exception{
		String[] YcsXycCodeIds = request.getParameterValues("uid");
		response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(ycsXycCodeService.deleteYcsXycCodeList(request, YcsXycCodeIds));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out = response.getWriter();
			out.println(returnInteger);
			out.flush();
			out.close();
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
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println("异常信息："+exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	
	

	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public YcsXycCode getYcsXycCode() {
		return YcsXycCode;
	}

	public void setYcsXycCode(YcsXycCode YcsXycCode) {
		this.YcsXycCode = YcsXycCode;
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

	public YcsXycCodeService getYcsXycCodeService() {
		return ycsXycCodeService;
	}

	public void setYcsXycCodeService(YcsXycCodeService ycsXycCodeService) {
		this.ycsXycCodeService = ycsXycCodeService;
	}

	public List<YcsXycCode> getYcsXycCodeList() {
		return ycsXycCodeList;
	}

	public void setYcsXycCodeList(List<YcsXycCode> ycsXycCodeList) {
		this.ycsXycCodeList = ycsXycCodeList;
	}

	
}
