package com.ycszh.ssh.action.mjcl;

import org.apache.log4j.Logger;

import com.ycszh.common.ExportToExcel;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.mjcl.MjclService;

public class MjclAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MjclAction.class);
	private int currentpage = 1;
	private MjclService mjclService;
	
	//免检车辆工作量统计
	public String mjclQuery() throws Exception {
		mjclService.mjclQuery(request, currentpage);
		return "list";
	}
	
	//车辆限购违规业务办理统计分析
	public String yjgzQuery() throws Exception{
		mjclService.yjgzQuery(request, currentpage);
		return "yjgzlist";
	}
	
	//免检车辆部门统计
	public String mjclDeptTj() throws Exception {
		mjclService.mjclDeptTj(request);
		return "listTj";
	}
	
	//免检车辆工作量统计报表
	public String mjclQueryExcel()throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			mjclService.mjclQuery(request, currentpage);
			ExportToExcel.mjclExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}
	
	//免检车辆工作量部门统计报表
	public String mjclTjExcel()throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			mjclService.mjclDeptTj(request);
			ExportToExcel.mjclTjExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}
	
	//限购受理工作量统计
	public String xgslQuery() throws Exception {
		mjclService.xgslQuery(request, currentpage);
		return "listsg";
	}
	
	//限购受理工作量统计报表
	public String xgslQueryExcel()throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			mjclService.xgslQuery(request, currentpage);
			ExportToExcel.xgslExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}
	
	//车辆限购违规业务办理统计分析统计报表
	public String yjgzQueryExcel()throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			mjclService.yjgzQuery(request, currentpage);
			ExportToExcel.yjgzExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public MjclService getMjclService() {
		return mjclService;
	}
	public void setMjclService(MjclService mjclService) {
		this.mjclService = mjclService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}

}
