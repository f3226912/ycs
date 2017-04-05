package com.ycszh.ssh.action.drv;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.VehYujing;
import com.ycszh.ssh.hbm.drv.Yujing;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.YujingService;
import com.ycszh.util.ExportExcelBean;
import com.ycszh.util.ExportTools;

import common.Logger;

/**
 * @包名:com.example.ssh.action
 * @文件名:YujingAction.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-20
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class YujingAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(YujingAction.class);
	@SuppressWarnings("unchecked")
	private List esdrvFlowList = new ArrayList();
	//导出实体
    private ExportExcelBean eeb;
    /**
     * 列表显示信息
     */
    private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();
    private int currentpage = 1;
	private YujingService yujingService;
	private SlgDrvService slgDrvService;
	
	/**
	 * 驾驶证受理预警信息列表
	 * @return
	 * @throws Exception 
	 */
	public String initYujingList() throws Exception{
		this.setEsdrvFlowList(this.yujingService.getEsDrvFlowList(request, "query", currentpage));
		logger.info("initYujingList:" + esdrvFlowList.size());
		return "list";
	}
	
	/**
	 * 驾驶证受理预警信息导出
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String yujingListExport() throws Exception{
		response.setContentType("text/html;charset=UTF-8"); 
		List<Yujing> yujingList = this.yujingService.getEsDrvFlowList(request, "export", currentpage);
		Object exportData = request.getAttribute("exportData");
		if(exportData != null && !"".equals(exportData)){
			return "list";
		}
		eeb = new ExportExcelBean();
		if(!yujingList.isEmpty()){
			eeb.setFileName("驾驶证受理业务数据预警");
			eeb.setFileTitle("驾驶证受理业务数据预警");
			eeb.setList(yujingList);
			parmsMap.put("lsh", "流水号");
			parmsMap.put("sfzmhm", "身份证明号码");
			parmsMap.put("dabh", "档案编号");
			parmsMap.put("xm", "姓名");
			parmsMap.put("kssj", "开始时间");
			parmsMap.put("ywlx", "业务类型");
			parmsMap.put("glbm", "业务办理部门");
			eeb.setParmsMap(parmsMap);
			ExportTools.exportExcel(response, eeb);
		}else{
			request.setAttribute("exportData", "当前导出数据为空！");  
			return "list";
		}
		return NONE;
	}
	
	public String initVehYujingList() throws Exception{
		try {
			List<SlgSjzd> ywlxList = new ArrayList<SlgSjzd>();
			ywlxList = this.slgDrvService.getYwlxYwyy(request, "", "", "JDCYWSL", "VEH_YWLX", "");
			this.setEsdrvFlowList(this.yujingService.getVehFlowList(request, "query", currentpage));
			request.setAttribute("ywlxList", ywlxList);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "vehList";
	}
	
	/**
	 * 机动车受理预警信息导出
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String yujingVehListExport() throws Exception{
		response.setContentType("text/html;charset=UTF-8"); 
		List<VehYujing> yujingList = this.yujingService.getVehFlowList(request, "export", currentpage);
		Object exportData = request.getAttribute("exportData");
		if(exportData != null && !"".equals(exportData)){
			return "vehList";
		}
		eeb = new ExportExcelBean();
		if(!yujingList.isEmpty()){
			eeb.setFileName("机动车受理业务数据预警");
			eeb.setFileTitle("机动车受理业务数据预警");
			eeb.setList(yujingList);
			parmsMap.put("lsh", "流水号");
			parmsMap.put("hphm", "号牌号码");
			parmsMap.put("hpzl", "号牌种类");
			parmsMap.put("syr", "所有人");
			parmsMap.put("sqrq", "申请日期");
			parmsMap.put("ywlx", "业务类型");
			parmsMap.put("ywblbm", "管理部门");
			eeb.setParmsMap(parmsMap);
			ExportTools.exportExcel(response, eeb);
		}else{
			request.setAttribute("exportData", "当前导出数据为空！");  
			return "vehList";
		}
		return NONE;
	}
	
	@SuppressWarnings("unchecked")
	public List getEsdrvFlowList() {
		return esdrvFlowList;
	}
	@SuppressWarnings("unchecked")
	public void setEsdrvFlowList(List esdrvFlowList) {
		this.esdrvFlowList = esdrvFlowList;
	}
	public YujingService getYujingService() {
		return yujingService;
	}
	public void setYujingService(YujingService yujingService) {
		this.yujingService = yujingService;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentPage) {
		this.currentpage = currentPage;
	}

	public ExportExcelBean getEeb() {
		return eeb;
	}

	public void setEeb(ExportExcelBean eeb) {
		this.eeb = eeb;
	}

	public Map<String, Object> getParmsMap() {
		return parmsMap;
	}

	public void setParmsMap(Map<String, Object> parmsMap) {
		this.parmsMap = parmsMap;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}
	
}
