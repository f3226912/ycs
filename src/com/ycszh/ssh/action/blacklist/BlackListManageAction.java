package com.ycszh.ssh.action.blacklist;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.blacklist.DbjgHmdyh;
import com.ycszh.ssh.hbm.blacklist.DbjgSjzd;
import com.ycszh.ssh.service.blacklist.BlackListService;
/**
 * 黑名单用户管理功能
 * @author Administrator
 * @since 2014-02-13
 */
public class BlackListManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(BlackListManageAction.class);
	
	private int currentpage = 1;
	
	private Integer returnInteger = 1;
	
	private BlackListService blackListService;
	
	private List<DbjgHmdyh> hmdYhList;
	
	private DbjgHmdyh hmdyh;
	
	private DbjgSjzd dict;
	
	private List<DbjgSjzd> sjzdSdList;
	
	private List<DbjgSjzd> sjzdJsList;
	
	private List<DbjgSjzd>  dbcsList;  //字典的设置
	
	private Date yxqzStr;
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	/**
	 * 黑名单用户管理
	 * @return
	 * @throws Exception
	 */
	public String getBlackList() throws Exception {
		this.setHmdYhList(this.blackListService.getBlackYHList(request, currentpage));
		return "blackYhList";
	}	
	/**
	 * 初始化 黑名单用户
	 * @return
	 */
	public String initHmdYh() throws Exception {
		request.setAttribute("editType", "新增");
		this.setSjzdSdList(this.blackListService.getHmdYyDict("1",false));
		this.setSjzdJsList(this.blackListService.getHmdYyDict("2",false));
        this.setYxqzStr(this.blackListService.getAutoYxqz());
		return "initAdd";
	}
	/**
	 * 查看黑名单用户信息
	 * @return
	 */
	public String initShowHmdYh() throws Exception {
		request.setAttribute("editType", "查看");
		this.setSjzdSdList(this.blackListService.getHmdYyDict("1",true));
		this.setSjzdJsList(this.blackListService.getHmdYyDict("2",true));
		this.setHmdyh(this.blackListService.getHmdyhByxh(request));
		this.setYxqzStr(this.hmdyh.getSdyxqz());
		return "initAdd";
	}
	/**
	 * 黑名单添加
	 * @return
	 * @throws Exception
	 */
	public String addHmdYh() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (hmdyh != null) {
			try{
			   int returnInteger =this.blackListService.addHmdUser(request, hmdyh);
			   if(returnInteger!=3){
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						out.println("parent.parent.window.location.href = '"+request.getContextPath()+"/blacklist/black_getBlackList.action';");
					} else {
						out.println("alert('操作失败!')");
					}
			   }else{
				   out.println("alert('该黑名单的身份证名号码已存在!');");
				   out.println("parent.document.getElementById('sfzmhm').style.borderColor = '#FF0000';");
			       out.println("parent.document.getElementById('sfzmhm').focus();");
			   }				
			}catch (Exception e) {
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
				out.println(exceptionstr);
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	/**
	 * 初始化黑名单状态
	 * @return
	 * @throws Exception
	 */
	public String initEditHmdYh() throws Exception {
		request.setAttribute("editType", "修改");
		this.setSjzdSdList(this.blackListService.getHmdYyDict("1",false));
		this.setSjzdJsList(this.blackListService.getHmdYyDict("2",false));
		this.setHmdyh(this.blackListService.getHmdyhByxh(request));	
		 this.setYxqzStr(this.blackListService.getAutoYxqz());
		return "initEdit";
	}
	
	/**
	 * 根据xh删除黑名单用户
	 * @return
	 * @throws Exception
	 */
	public String delHmyh() throws Exception {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String xh = request.getParameter("xh");
		if(null != xh && !"".equals(xh)){
			try {
				this.setReturnInteger(blackListService.delHmyhByxh(xh, request));
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw e;
			}
			return null;
		}else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	

	/**
	 * 修过黑名单状态
	 * @return
	 * @throws Exception
	 */
	public String editHmdYh() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String xh = hmdyh.getXh();
		out.println("<script>");
		if (xh!=null && !"".equals(xh)) {
			try{
			   int returnInteger =this.blackListService.editHmyhByxh(hmdyh, request);
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						out.println("parent.parent.window.location.href = '"+request.getContextPath()+"/blacklist/black_getBlackList.action';");
					} else {
						out.println("alert('操作失败!')");
					}			
			}catch (Exception e) {
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
				out.println(exceptionstr);
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
   //---------------------黑名单数据字段设置功能---------------------------------
	
	/**
	 * 默认未备案代办员可代办数列表
	 */
	public String getDictList() throws Exception {
		String type =request.getParameter("type");
		this.setSjzdJsList(this.blackListService.getHmdDbDict("js"));//解锁
		this.setSjzdSdList(this.blackListService.getHmdDbDict("sd"));//锁定
		this.setDbcsList(this.blackListService.getHmdDbDict("db"));//代办员（默认）
		request.setAttribute("type",type);
		return "dictList";
	}	
	/**
	 * 修改非备案代办员字典
	 * @return
	 * @throws Exception
	 */
	public String editDbDict() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String xh =dict.getXh();
		String type = request.getParameter("type");
		out.println("<script>");
		if (xh!=null && !"".equals(xh)) {
			try{
			   int returnInteger =this.blackListService.editDbjgSjzdByxh(dict, request);
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						out.println("parent.window.location.href ='"+request.getContextPath()+"/blacklist/black_getDictList.action?type="+type+"';");
					} else {
						out.println("alert('操作失败!')");
					}			
			}catch (Exception e) {
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
				out.println(exceptionstr);
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	/**
	 * 修改锁定/解锁信息字典
	 * @return
	 * @throws Exception
	 */
	public String editSDDict() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String xh =request.getParameter("sd_xh");
		String type = request.getParameter("type");
		out.println("<script>");
		if (xh!=null && !"".equals(xh)) {
			try{
			   int returnInteger =this.blackListService.editDbjgSdbyxh(xh, request);
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						out.println("parent.window.location.href ='"+request.getContextPath()+"/blacklist/black_getDictList.action?type="+type+"';");
					} else {
						out.println("alert('操作失败!')");
					}			
			}catch (Exception e) {
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
				out.println(exceptionstr);
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}		
	}
	/**
	 * 初始化添加解锁/锁定字典页面
	 * @return
	 * @throws Exception
	 */
	public String initDictAdd() throws Exception {
		String type = request.getParameter("type");
		String seqNo = this.blackListService.getDictCode(type);
		request.setAttribute("type", type);
		request.setAttribute("seqNo", seqNo);
		return "initDict";
	}
	/**
	 * 添加字典
	 * @return
	 * @throws Exception
	 */
	public String addDict() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (dict != null) {
			try{
			   int returnInteger =this.blackListService.addDict(request, dict);
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						String type=request.getParameter("type");
						out.println("parent.parent.window.location.href = '"+request.getContextPath()+"/blacklist/black_getDictList.action?type="+type+"';");
					} else {
						out.println("alert('操作失败!')");
					}			
			}catch (Exception e) {
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
				out.println(exceptionstr);
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	/**
	 * 根据xh删除字典信息
	 * @return
	 * @throws Exception
	 */
	public String delDict() throws Exception {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String xh = request.getParameter("xh");
		if(null != xh && !"".equals(xh)){
			try {
				this.setReturnInteger(blackListService.delDictByxh(xh, request));
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw e;
			}
			return null;
		}else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
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
	public BlackListService getBlackListService() {
		return blackListService;
	}
	public void setBlackListService(BlackListService blackListService) {
		this.blackListService = blackListService;
	}

	public List<DbjgHmdyh> getHmdYhList() {
		return hmdYhList;
	}
	public void setHmdYhList(List<DbjgHmdyh> hmdYhList) {
		this.hmdYhList = hmdYhList;
	}
	public DbjgHmdyh getHmdyh() {
		return hmdyh;
	}
	public void setHmdyh(DbjgHmdyh hmdyh) {
		this.hmdyh = hmdyh;
	}
	public List<DbjgSjzd> getSjzdSdList() {
		return sjzdSdList;
	}
	public List<DbjgSjzd> getSjzdJsList() {
		return sjzdJsList;
	}
	public void setSjzdSdList(List<DbjgSjzd> sjzdSdList) {
		this.sjzdSdList = sjzdSdList;
	}
	public void setSjzdJsList(List<DbjgSjzd> sjzdJsList) {
		this.sjzdJsList = sjzdJsList;
	}
	public List<DbjgSjzd> getDbcsList() {
		return dbcsList;
	}
	public void setDbcsList(List<DbjgSjzd> dbcsList) {
		this.dbcsList = dbcsList;
	}
	public DbjgSjzd getDict() {
		return dict;
	}
	public void setDict(DbjgSjzd dict) {
		this.dict = dict;
	}
	public Date getYxqzStr() {
		return yxqzStr;
	}
	public void setYxqzStr(Date yxqzStr) {
		this.yxqzStr = yxqzStr;
	}
	
}
