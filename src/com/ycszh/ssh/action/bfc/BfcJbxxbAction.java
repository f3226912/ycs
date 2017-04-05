package com.ycszh.ssh.action.bfc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.bfc.BfcJbxxb;
import com.ycszh.ssh.service.bfc.BfcJbxxbService;

public class BfcJbxxbAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private BfcJbxxb bfcJbxxb;
	private BfcJbxxbService bfcJbxxbService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<BfcJbxxb> bfcJbxxbList = new ArrayList<BfcJbxxb>();
	
	//报废车信息列表
	public String initBfcJbxxbList() throws Exception {
		this.setBfcJbxxbList(bfcJbxxbService.getBfcJbxxbList(request,currentpage));
		return "list";
	}
	
	//报废车状态修改
	public String bfcztupdate() throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(bfcJbxxbService.UpdateBfcJbxxbZt(request));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
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
		out.flush();
		out.close();
		return null;
	}
	
	
	/**
	 * @return the bfcJbxxb
	 */
	public BfcJbxxb getBfcJbxxb() {
		return bfcJbxxb;
	}
	/**
	 * @param bfcJbxxb the bfcJbxxb to set
	 */
	public void setBfcJbxxb(BfcJbxxb bfcJbxxb) {
		this.bfcJbxxb = bfcJbxxb;
	}
	/**
	 * @return the bfcJbxxbService
	 */
	public BfcJbxxbService getBfcJbxxbService() {
		return bfcJbxxbService;
	}
	/**
	 * @param bfcJbxxbService the bfcJbxxbService to set
	 */
	public void setBfcJbxxbService(BfcJbxxbService bfcJbxxbService) {
		this.bfcJbxxbService = bfcJbxxbService;
	}
	/**
	 * @return the currentpage
	 */
	public int getCurrentpage() {
		return currentpage;
	}
	/**
	 * @param currentpage the currentpage to set
	 */
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	/**
	 * @return the returnInteger
	 */
	public Integer getReturnInteger() {
		return returnInteger;
	}
	/**
	 * @param returnInteger the returnInteger to set
	 */
	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}
	/**
	 * @return the bfcJbxxbList
	 */
	public List<BfcJbxxb> getBfcJbxxbList() {
		return bfcJbxxbList;
	}
	/**
	 * @param bfcJbxxbList the bfcJbxxbList to set
	 */
	public void setBfcJbxxbList(List<BfcJbxxb> bfcJbxxbList) {
		this.bfcJbxxbList = bfcJbxxbList;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
