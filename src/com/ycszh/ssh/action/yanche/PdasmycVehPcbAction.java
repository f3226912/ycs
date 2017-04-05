package com.ycszh.ssh.action.yanche;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;
import com.ycszh.ssh.hbm.yanche.VDataCheck;
import com.ycszh.ssh.hbm.yanche.VVehOrgYcs;
import com.ycszh.ssh.hbm.yanche.VVehUserYcs;
import com.ycszh.ssh.service.yanche.PdasmycVehPcbService;
import com.ycszh.util.DownUtil;
import com.ycszh.util.ExportExcelBean;
import com.ycszh.util.JsonUtil;

/**
 * @包名:com.ycszh.ssh.action.yanche
 * @文件名:PdasmycVehPcbAction.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-24
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycVehPcbAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private PdasmycVehPcb pdasmycVehPcb;
	private PdasmycVehPcbService pdasmycVehPcbService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
	private List<VVehUserYcs> vVehUserYcsList = new ArrayList<VVehUserYcs>();
	private List<VDataCheck> vDataCheckList = new ArrayList<VDataCheck>();
	private List<VVehOrgYcs> vvoylist = new ArrayList<VVehOrgYcs>();
	
	// 验车分配信息列表
	public String initPdasmycVehPcbList() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbList(request, currentpage));
		return "list";
	}
	
	
	// 验车预约部门修改列表 
	public String initPdasmycVehPcbList2() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbList2(request, currentpage));
		this.setVvoylist(pdasmycVehPcbService.getOrgList());
		return "list2";
	}
	
	// 验车未拍照超时审批(科)
	public String initPdasmycVehPcbList3() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbList3(request, currentpage));
		return "list3";
	}
	
	// 验车未拍照超时审批(处)
	public String initPdasmycVehPcbList4() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbList4(request, currentpage));
		return "list4";
	}
	
	// 验车分配信息列表
	public String initPdasmycVehPcbViewList() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbViewList(request));
		this.setvVehUserYcsList(pdasmycVehPcbService.getVVehUserYcsList(request));
		return "view";
	}
	
	// 验车分配信息列表
	public String initPdasmycVehPcbViewList2() throws Exception {
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getPdasmycVehPcbViewList2(request, currentpage));
		return "viewlist";
	}
	
	// 初始化添加页面
	public String insertPdasmycVehPcb() throws Exception {
		request.setAttribute("editType", "新增");
		return "insert";
	}
	
	// 初始化修改页面
	public String updatePdasmycVehPcb() throws Exception {
		if (pdasmycVehPcb.getLsh() != null || !"".equals(pdasmycVehPcb.getLsh())) {
			this.setPdasmycVehPcb(pdasmycVehPcbService.getPdasmycVehPcb(pdasmycVehPcb.getLsh()));
			if (this.getPdasmycVehPcb() != null) {
				request.setAttribute("editType", "修改");
				return "update";
			} else {
				request.setAttribute("errorTip", "没有该验车分配信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该验车分配信息!");
			return "Exception";
		}
	}
	
	// 查看页面
	public String initPdasmycVehPcb() throws Exception {
		if (pdasmycVehPcb.getLsh() != null) {
			this.setPdasmycVehPcb(pdasmycVehPcbService.getPdasmycVehPcb2(pdasmycVehPcb.getLsh()));
			if (this.getPdasmycVehPcb() != null) {
				request.setAttribute("editType", "查看");
				return "view2";
			} else {
				request.setAttribute("errorTip", "没有该验车分配信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该验车分配信息!");
			return "Exception";
		}
	}
	
	// 编辑验车分配信息信息
	public String editPdasmycVehPcb() throws Exception {
		if (pdasmycVehPcb != null) {
			String pdasmycVehPcbid = pdasmycVehPcb.getLsh();
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = pdasmycVehPcbService.insertOrUpdatePdasmycVehPcb(pdasmycVehPcb, request);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					if(null != pdasmycVehPcbid && !"".equals(pdasmycVehPcbid)){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chbase_initPdasmycVehPcbList.action';");
					}else{
						out.println("alert('添加成功!');");
						//out.println("parent.cleanmyform();");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/chbase_initPdasmycVehPcbList.action';");
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
			out.flush();
			out.close();
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	// 删除验车分配信息信息
	public String deletePdasmycVehPcb() throws Exception {
		String pdasmycVehPcbId = request.getParameter("pdasmycVehPcbId");
		if(null != pdasmycVehPcbId){
			this.setReturnInteger(pdasmycVehPcbService.deletePdasmycVehPcb(pdasmycVehPcbId));
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

	// 删除验车分配信息信息(多个)
	public String deletePdasmycVehPcbList() throws Exception {
		String[] pdasmycVehPcbIds = request.getParameterValues("ids");
		this.setReturnInteger(pdasmycVehPcbService.deletePdasmycVehPcbList(pdasmycVehPcbIds));
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
	
	
	// 分配验车民警
	public String fenpeiycmj() throws Exception {
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		String[] ids = request.getParameterValues("id");
		String szmj = request.getParameter("szmj");
		String yjsj = request.getParameter("yjsj");
		String fpms = request.getParameter("fpms");
		if(null != fpms && !"".equals(fpms)){
			fpms = URLDecoder.decode(fpms,"UTF-8");
		}
		try {
			this.setReturnInteger(pdasmycVehPcbService.fenpeiycmj(ids,szmj,yjsj,fpms,request));
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
	
	// 分配验车退办
	public String fenpeitb() throws Exception {
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		String[] ids = request.getParameterValues("id");
		String tbyyms = request.getParameter("tbyyms");
		if(null != tbyyms && !"".equals(tbyyms)){
			tbyyms = URLDecoder.decode(tbyyms,"UTF-8");
		}
		try {
			this.setReturnInteger(pdasmycVehPcbService.fenpeitb(ids,tbyyms,request));
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
	
	
	// 本部门验车任务管理列表
	public String initYcrwList() throws Exception {
		pdasmycVehPcbService.getYcrwList(request, currentpage);
		return "ycrwlist";
	}
	
	// 民警验车任务管理列表
	public String initYcrwList2() throws Exception {
		pdasmycVehPcbService.getYcrwList2(request, currentpage);
		return "ycrwlist2";
	}
	
	// 民警验车任务详细列表
	public String initYcrwList3() throws Exception {
		pdasmycVehPcbService.getYcrwList3(request, currentpage);
		return "ycrwlist3";
	}
	
	// 民警验车任务管理列表
	public String initYcrwList4() throws Exception {
		pdasmycVehPcbService.getYcrwList4(request, currentpage);
		return "ycrwlist2";
	}
	
	// 打印民警验车任务列表
	public String initYcrwList5() throws Exception {
		pdasmycVehPcbService.getYcrwList3(request, currentpage);
		return "ycrwlist5";
	}
	
	//导出扫描结果表
	public String exportExcel() throws Exception{
		request.setCharacterEncoding("UTF-8");
		this.setPdasmycVehPcbList(pdasmycVehPcbService.getYcrwList5(request, response,currentpage));
		if(!this.getPdasmycVehPcbList().isEmpty()){
			Map<String,Object> paramMap = new LinkedHashMap<String,Object>();
			ExportExcelBean eeb = new ExportExcelBean();
			eeb.setFileName("民警验车任务列表");
			eeb.setFileTitle("民警验车任务列表");
			eeb.setList(this.getPdasmycVehPcbList());
			paramMap.put("chip", "流水号条形码");
			paramMap.put("chmc", "车行名称");
			paramMap.put("chid", "车辆识别代号");
			paramMap.put("smycyypch", "申报批次号");
			paramMap.put("yclx", "验车状态");
			paramMap.put("ycjg", "验车结果");
			paramMap.put("clcfdd", "车辆停放地点");
			
			
			eeb.setParmsMap(paramMap);
			DownUtil.exportExcel(response, eeb);
			
		}
		return NONE;

	}
	
	//ajax获取验车结果查询
	public String getYcjgAjax() throws Exception{
		String lsh = request.getParameter("lsh");
		String clsbdh = request.getParameter("clsbdh");
		try {
			this.setPdasmycVehPcb(pdasmycVehPcbService.getPdasmycVehPcb(lsh, clsbdh));
			String jsonString = JsonUtil.object2json(pdasmycVehPcb);
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			out.print(jsonString);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	
	// 验车结果修改列表
	@SuppressWarnings("rawtypes")
	public String initVDataCheckList() throws Exception {
		this.setvDataCheckList(pdasmycVehPcbService.getVDataCheckList(request, currentpage));
		//嫌疑库原因集合
		List bhgyylist = pdasmycVehPcbService.getBhgyyList();
		request.setAttribute("bhgyylist", bhgyylist);
		return "ycjgxg";
	}
	
	// 验车结果修改
	public String updateVDataCheck() throws Exception {
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(pdasmycVehPcbService.updateVDataCheck(request));
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
	
	// 验车修改预约部门
	public String updateYybm() throws Exception {
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(pdasmycVehPcbService.updateYybm(request));
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
	
	// 验车未拍照超时审批
	public String updateShenPi() throws Exception {
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(pdasmycVehPcbService.updateShenPi(request));
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
	
	
	
	
	
	public PdasmycVehPcb getPdasmycVehPcb() {
		return pdasmycVehPcb;
	}
	public void setPdasmycVehPcb(PdasmycVehPcb pdasmycVehPcb) {
		this.pdasmycVehPcb = pdasmycVehPcb;
	}
	public PdasmycVehPcbService getPdasmycVehPcbService() {
		return pdasmycVehPcbService;
	}
	public void setPdasmycVehPcbService(PdasmycVehPcbService pdasmycVehPcbService) {
		this.pdasmycVehPcbService = pdasmycVehPcbService;
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
	public List<PdasmycVehPcb> getPdasmycVehPcbList() {
		return pdasmycVehPcbList;
	}
	public void setPdasmycVehPcbList(List<PdasmycVehPcb> pdasmycVehPcbList) {
		this.pdasmycVehPcbList = pdasmycVehPcbList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * @return the vVehUserYcsList
	 */
	public List<VVehUserYcs> getvVehUserYcsList() {
		return vVehUserYcsList;
	}

	/**
	 * @param vVehUserYcsList the vVehUserYcsList to set
	 */
	public void setvVehUserYcsList(List<VVehUserYcs> vVehUserYcsList) {
		this.vVehUserYcsList = vVehUserYcsList;
	}

	/**
	 * @return the vDataCheckList
	 */
	public List<VDataCheck> getvDataCheckList() {
		return vDataCheckList;
	}

	/**
	 * @param vDataCheckList the vDataCheckList to set
	 */
	public void setvDataCheckList(List<VDataCheck> vDataCheckList) {
		this.vDataCheckList = vDataCheckList;
	}

	/**
	 * @return the vvoylist
	 */
	public List<VVehOrgYcs> getVvoylist() {
		return vvoylist;
	}

	/**
	 * @param vvoylist the vvoylist to set
	 */
	public void setVvoylist(List<VVehOrgYcs> vvoylist) {
		this.vvoylist = vvoylist;
	}

}
