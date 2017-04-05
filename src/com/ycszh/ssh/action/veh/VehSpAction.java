package com.ycszh.ssh.action.veh;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgSpxxAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.hbm.veh.VehPodbsp;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.veh.VehSpService;
import com.ycszh.util.StringUtil;

import common.Logger;

@SuppressWarnings("serial")
public class VehSpAction extends BaseAction{
	
	private static final Logger logger = Logger.getLogger(SlgSpxxAction.class);
	private List<SlgSpxx> slgSpxxList = new ArrayList<SlgSpxx>();
	private List<VehPodbsp> vehPodbsps = new ArrayList<VehPodbsp>();
	private int currentpage = 1;
	private SlgSpxx slgSpxx;
	private Integer result = null;
	private VehSpService vehSpService;
	private SlgDrvService slgDrvService;
	private VehPodbsp vehPodbsp;
	
	@SuppressWarnings("unchecked")
	public String initSlgSpxxList() throws Exception{
		logger.info("VehSpAction method initSlgSpxxList....");
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		request.setAttribute("deptList", list);
		this.setSlgSpxxList(this.vehSpService.getSlgSpxxList(request, currentpage));
		return "list";
	}
	
	public String editSlgSpxx() throws Exception{
		logger.info("VehSpAction method editSlgSpxx....");
		if(slgSpxx != null){
			Long slgSpxxid = slgSpxx.getId();
			response.setCharacterEncoding("gbk");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			Integer result = null;
			try {
				result = this.vehSpService.insertOrUpdateSlgSpxx(request, slgSpxx, "");
				out.println("parent.closechuli();");
				if(result == 1){
					if(slgSpxxid != null && slgSpxxid > 0){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/veh/vehSpxx_initSlgSpxxList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/veh/vehSpxx_initSlgSpxxList.action';");
					}
				}else{
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
		}else{
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	public String selSlgSpxx() throws Exception{
		SlgSpxx slg = null;
		String editType = request.getParameter("editType");
		if("edit".equals(editType) || "query".equals(editType)){
			Long id = Long.parseLong(request.getParameter("id"));
			slg = this.vehSpService.getSlgSpxxById(request, id);
		}
		request.setAttribute("editType", editType);
		request.setAttribute("slgSpxx", slg);
		return "edit";
	}
	
	public String delSlgSpxx() throws Exception{
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			result = this.vehSpService.deleteSlgSpxx(request, id, "");
//			String jsonString = JsonUtil.object2json(result);
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//			response.getWriter().print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return "success";
	}
	
	/**
	 * 机动车配偶审批列表
	 * @return
	 * @throws Exception
	 */
	public String initVehPodbSpList() throws Exception{
		logger.info("VehSpAction method initVehPodbSpList....");
		this.setVehPodbsps(this.vehSpService.getVehPodbspList(request, currentpage));
		return "posplist";
	}
	
	/**
	 * 初始化编辑配偶审批信息页面
	 * @return
	 * @throws Exception
	 */
	public String selVehPoSpxx() throws Exception{
		VehPodbsp vehPodbsp = null;
		String editType = request.getParameter("editType");
		if("edit".equals(editType) || "query".equals(editType)){
			String strid = request.getParameter("id");
			if(!StringUtil.isNull(strid)){
				Long id = Long.parseLong(request.getParameter("id"));
				vehPodbsp = this.vehSpService.getVehPodbspById(request, id);
			}
			
		}
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("editType", editType);
		request.setAttribute("hpzlList", hpzlList);
		request.setAttribute("vehPodbsp", vehPodbsp);
		return "pospedit";
	}
	
	/**
	 * 编辑机动车配偶审批信息
	 * @return
	 * @throws Exception
	 */
	public String editVehPodbxx() throws Exception{
		logger.info("VehSpAction method editVehPodbxx....");
		if(vehPodbsp != null){
			Long id = vehPodbsp.getId();
			response.setCharacterEncoding("gbk");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			Integer result = null;
			try {
				result = this.vehSpService.insertOrUpdatePoVehSpxx(request, vehPodbsp);
				out.println("parent.closechuli();");
				if(result == 1){
					if(id != null){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/veh/vehSpxx_initVehPodbSpList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/veh/vehSpxx_initVehPodbSpList.action';");
					}
				}else{
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
		}else{
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	/**
	 * 删除机动车配偶审批信息
	 * @return
	 * @throws Exception
	 */
	public String deleteVehPodb() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			result = this.vehSpService.deleteVelPoSpxx(request, id);
			out.println(result);
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
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	public static Logger getLogger() {
		return logger;
	}
	public List<SlgSpxx> getSlgSpxxList() {
		return slgSpxxList;
	}
	public void setSlgSpxxList(List<SlgSpxx> slgSpxxList) {
		this.slgSpxxList = slgSpxxList;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public SlgSpxx getSlgSpxx() {
		return slgSpxx;
	}
	public void setSlgSpxx(SlgSpxx slgSpxx) {
		this.slgSpxx = slgSpxx;
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public VehSpService getVehSpService() {
		return vehSpService;
	}
	public void setVehSpService(VehSpService vehSpService) {
		this.vehSpService = vehSpService;
	}

	public List<VehPodbsp> getVehPodbsps() {
		return vehPodbsps;
	}

	public void setVehPodbsps(List<VehPodbsp> vehPodbsps) {
		this.vehPodbsps = vehPodbsps;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}

	public VehPodbsp getVehPodbsp() {
		return vehPodbsp;
	}

	public void setVehPodbsp(VehPodbsp vehPodbsp) {
		this.vehPodbsp = vehPodbsp;
	}
	
}
