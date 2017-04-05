package com.ycszh.ssh.action.ddc;

import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.ddc.VehtypeElectro;
import com.ycszh.ssh.service.ddc.IVehtypeElectroService;

public class VehtypeElectroAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(VehtypeElectroAction.class);
	private int currentpage = 1;
	private IVehtypeElectroService vehTypeElectroService;
	private VehtypeElectro veElectro;
	
	/**
	 * 纯电动车辆型号与电机型号列表
	 * @return
	 * @throws Exception
	 */
	public String getvehTypeElectroList() throws Exception{
		logger.info("VehtypeElectroAction Method of getvehTypeElectroList.....");
		this.vehTypeElectroService.getVehtypeElectroList(request, currentpage, "query");
		return "electroList";
	}
	
	public String editVehtypeElectro() throws Exception{
		logger.info("VehtypeElectroAction mehtod of editVehtypeElectro.....");
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out = response.getWriter();
		if(veElectro != null){
			try {
				String result = this.vehTypeElectroService.editVehtypeElectro(request, veElectro);
				out.println(result);
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
					exceptionstr += " 获取连接异常或提交信息为空!";
				}
				out.println(exceptionstr);
			}finally{
				if(out != null){
					out.flush();
					out.close();
				}
			}
		}else{
			out.println("0");
			if(out != null){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 初始化编辑信息
	 * @return
	 * @throws Exception
	 */
	public String initVehtypeElectro() throws Exception{
		logger.info("VehtypeElectroAction mehtod of initVehtypeElectro.....");
		List<VehtypeElectro> list = this.vehTypeElectroService.getVehtypeElectroByCondition(request);
		if(list != null && list.size() > 0){
			VehtypeElectro vehtypeElectro = list.get(0);
			request.setAttribute("veElectro", vehtypeElectro);
			String editType = request.getParameter("editType");
			request.setAttribute("editType", editType);
		}
		return "updateElectro";
	}
	
	public String delVehtypeElectro() throws Exception{
		logger.info("VehtypeElectroAction mehtod of delVehtypeElectro.....");
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out = response.getWriter();
		try {
			String xh = request.getParameter("xh");
			String result = this.vehTypeElectroService.delVehtypeElectro(request, xh);
			out.println(result);
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
				exceptionstr += " 获取连接异常或提交信息为空!";
			}
			out.println(exceptionstr);
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public IVehtypeElectroService getVehTypeElectroService() {
		return vehTypeElectroService;
	}
	public void setVehTypeElectroService(
			IVehtypeElectroService vehTypeElectroService) {
		this.vehTypeElectroService = vehTypeElectroService;
	}
	public VehtypeElectro getVeElectro() {
		return veElectro;
	}
	public void setVeElectro(VehtypeElectro veElectro) {
		this.veElectro = veElectro;
	}
	
}
