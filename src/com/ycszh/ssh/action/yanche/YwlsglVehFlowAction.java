package com.ycszh.ssh.action.yanche;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.yanche.PdasmycSjzd;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFieldgl;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlow;
import com.ycszh.ssh.service.yanche.PdasmycVehPcbService;
import com.ycszh.ssh.service.yanche.YwlsglVehFlowService;
import com.ycszh.util.JsonUtil;

public class YwlsglVehFlowAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private YwlsglVehFlow ywlsglVehFlow;
	private PdasmycTempMidOutN pdasmycTempMidOutN;
	private VehicleTempMidOut ptdo;
	private VehicleTempMidIn ptdoi;
	private YwlsglVehFlowService ywlsglVehFlowService;
	private PdasmycVehPcbService pdasmycVehPcbService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<YwlsglVehFlow> ywlsglVehFlowList = new ArrayList<YwlsglVehFlow>();
	private List<VehicleTempMidIn> vehicleTempMidInList = new ArrayList<VehicleTempMidIn>();
	private List<PdasmycTempMidOutN> ptdoList = new ArrayList<PdasmycTempMidOutN>();
	private List<YwlsglVehFieldgl> ywlsglVehFieldgllist = new ArrayList<YwlsglVehFieldgl>();
	
	// 流水信息列表
	public String initYwlsglVehFlowList() throws Exception {
		String type = request.getParameter("type");
		String gw = request.getParameter("gw");
		String ld = request.getParameter("ld");
		request.setAttribute("ld", ld);
		request.setAttribute("type", type);
		request.setAttribute("gw", gw);
		this.setYwlsglVehFlowList(ywlsglVehFlowService.getYwlsglVehFlowList(request, currentpage));
		return "list";
	}
	
	// 受理信息特殊字符修改列表
	public String initYwlsglVehFlowListt() throws Exception {
		String gw = request.getParameter("gw");
		request.setAttribute("gw", gw);
		this.setVehicleTempMidInList(ywlsglVehFlowService.getYwlsglVehFlowListt(request, currentpage));
		return "listt";
	}
	
	// 初始化修改页面
	@SuppressWarnings("rawtypes")
	public String updateYwlsglVehFlow() throws Exception {
		if (ptdo.getLsh() != null || !"".equals(ptdo.getLsh())) {
			String type = request.getParameter("type");
			String gw = request.getParameter("gw");
			String ld = request.getParameter("ld");
			String thyj = request.getParameter("thyj");
			request.setAttribute("ld", ld);
			request.setAttribute("type", type);
			request.setAttribute("gw", gw);
			request.setAttribute("thyj", thyj);
			this.setPtdo(ywlsglVehFlowService.getVehicleTempMidOut(ptdo.getLsh()));
			HttpSession session = request.getSession();
			session.setAttribute("ptdo", this.getPtdo());
			this.setYwlsglVehFlow(ywlsglVehFlowService.getYwlsglVehFlow2(ptdo.getLsh()));
			this.setYwlsglVehFieldgllist(ywlsglVehFlowService.getYwlsglVehFieldglList(ld, gw));
			//嫌疑库原因集合
			List zxykyylist = ywlsglVehFlowService.getXycyyList();
			request.setAttribute("zxykyylist", zxykyylist);
			//暂停原因集合
			List ztyylist = ywlsglVehFlowService.getZtyyList();
			request.setAttribute("ztyylist", ztyylist);
			//退办原因集合
			List tbyylist = ywlsglVehFlowService.getTbyyList(gw);
			request.setAttribute("tbyylist", tbyylist);
			//退查验岗原因集合
			List tcygyylist = ywlsglVehFlowService.getTcygyyList();
			request.setAttribute("tcygyylist", tcygyylist);
			if (this.getPtdo() != null) {
				// 身份证明
				List<PdasmycSjzd> sfzmList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '19' order by dmz");
				request.setAttribute("sfzmList", sfzmList);
				
				// 取得住所邮政编码
				List<PdasmycSjzd> yzbmList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '50' order by dmz");
				request.setAttribute("yzbmList", yzbmList);
				
				// 取得使用性质
				List<PdasmycSjzd> syxzList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '3' order by dmz");
				request.setAttribute("syxzList", syxzList);
				
				// 取得所有权
				List<PdasmycSjzd> syqList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '5' order by dmz");
				request.setAttribute("syqList", syqList);
				
				// 取得获得方式
				List<PdasmycSjzd> hdfsList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '1' order by dmz");
				request.setAttribute("hdfsList", hdfsList);
				
				// 取得号牌种类
				List<PdasmycSjzd> hpzlList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '7' order by dmz");
				request.setAttribute("hpzlList", hpzlList);
				
				// 承检单位
				List<PdasmycSjzd> cjdwList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '57' order by dmz");
				request.setAttribute("cjdwList", cjdwList);
				
				// 取得车辆类型
				List<PdasmycSjzd> cllxList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '4' order by dmz");
				request.setAttribute("cllxList", cllxList);
				
				// 车身颜色
				List<PdasmycSjzd> csysList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '8' order by dmz"); 
				request.setAttribute("csysList", csysList);
				
				// 国产/进口
				List<PdasmycSjzd> gcjkList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '12' order by dmz");
				request.setAttribute("gcjkList", gcjkList);
				
				// 制造国
				List<PdasmycSjzd> zzgList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '31' order by dmz");
				request.setAttribute("zzgList", zzgList);
				
				// 燃料种类
				List<PdasmycSjzd> rlzlList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '9' order by dmz");
				request.setAttribute("rlzlList", rlzlList);
				
				// 转向形式
				List<PdasmycSjzd> zxxsList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '16' order by dmz");
				request.setAttribute("zxxsList", zxxsList);
				
				return "lsedit";
			} else {
				request.setAttribute("errorTip", "没有该流水信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该流水信息!");
			return "Exception";
		}
	}
	
	// 初始化修改页面
	@SuppressWarnings("rawtypes")
	public String updateYwlsglVehFlowt() throws Exception {
		if (ptdoi.getLsh() != null || !"".equals(ptdoi.getLsh())) {
			String gw = request.getParameter("gw");
			String thyj = request.getParameter("thyj");
			request.setAttribute("gw", gw);
			request.setAttribute("thyj", thyj);
			this.setPtdoi(ywlsglVehFlowService.getVehicleTempMidIn(ptdoi.getLsh()));
			HttpSession session = request.getSession();
			session.setAttribute("ptdoi", this.getPtdoi());
			//this.setYwlsglVehFlow(ywlsglVehFlowService.getYwlsglVehFlow2(ptdoi.getLsh()));
			this.setYwlsglVehFieldgllist(ywlsglVehFlowService.getYwlsglVehFieldglList("0", "2001"));
			//嫌疑库原因集合
			List zxykyylist = ywlsglVehFlowService.getXycyyList();
			request.setAttribute("zxykyylist", zxykyylist);
			//暂停原因集合
			List ztyylist = ywlsglVehFlowService.getZtyyList();
			request.setAttribute("ztyylist", ztyylist);
			//退办原因集合
			List tbyylist = ywlsglVehFlowService.getTbyyList(gw);
			request.setAttribute("tbyylist", tbyylist);
			//退查验岗原因集合
			List tcygyylist = ywlsglVehFlowService.getTcygyyList();
			request.setAttribute("tcygyylist", tcygyylist);
			if (this.getPtdoi() != null) {
				// 身份证明
				List<PdasmycSjzd> sfzmList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '19' order by dmz");
				request.setAttribute("sfzmList", sfzmList);
				
				// 取得住所邮政编码
				List<PdasmycSjzd> yzbmList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '50' order by dmz");
				request.setAttribute("yzbmList", yzbmList);
				
				// 取得使用性质
				List<PdasmycSjzd> syxzList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '3' order by dmz");
				request.setAttribute("syxzList", syxzList);
				
				// 取得所有权
				List<PdasmycSjzd> syqList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '5' order by dmz");
				request.setAttribute("syqList", syqList);
				
				// 取得获得方式
				List<PdasmycSjzd> hdfsList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '1' order by dmz");
				request.setAttribute("hdfsList", hdfsList);
				
				// 取得号牌种类
				List<PdasmycSjzd> hpzlList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '7' order by dmz");
				request.setAttribute("hpzlList", hpzlList);
				
				// 承检单位
				List<PdasmycSjzd> cjdwList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '57' order by dmz");
				request.setAttribute("cjdwList", cjdwList);
				
				// 取得车辆类型
				List<PdasmycSjzd> cllxList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '4' order by dmz");
				request.setAttribute("cllxList", cllxList);
				
				// 车身颜色
				List<PdasmycSjzd> csysList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '8' order by dmz"); 
				request.setAttribute("csysList", csysList);
				
				// 国产/进口
				List<PdasmycSjzd> gcjkList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '12' order by dmz");
				request.setAttribute("gcjkList", gcjkList);
				
				// 制造国
				List<PdasmycSjzd> zzgList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '31' order by dmz");
				request.setAttribute("zzgList", zzgList);
				
				// 燃料种类
				List<PdasmycSjzd> rlzlList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '9' order by dmz");
				request.setAttribute("rlzlList", rlzlList);
				
				// 转向形式
				List<PdasmycSjzd> zxxsList = ywlsglVehFlowService.getPdasmycSjzdListByHql(" from PdasmycSjzd where dmlb = '16' order by dmz");
				request.setAttribute("zxxsList", zxxsList);
				
				return "lseditt";
			} else {
				request.setAttribute("errorTip", "没有该流水信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该流水信息!");
			return "Exception";
		}
	}
	
	// 查看页面
	public String initYwlsglVehFlow() throws Exception {
		if (ywlsglVehFlow.getLsh() != null) {
			this.setYwlsglVehFlow(ywlsglVehFlowService.getYwlsglVehFlow(ywlsglVehFlow.getLsh()));
			if (this.getYwlsglVehFlow() != null) {
				request.setAttribute("editType", "查看");
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该流水信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该流水信息!");
			return "Exception";
		}
	}
	
	// 编辑流水信息
	public String editPtdo() throws Exception {
		if (ptdo != null) {
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = ywlsglVehFlowService.insertOrUpdateVehicleTempMidOut(ptdo, request);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					String type = request.getParameter("type");
					String gw = request.getParameter("gw");
					String ld = request.getParameter("ld");
					String thyj = request.getParameter("thyj");
					out.println("alert('修改成功!');");
					if("1".equals(thyj)){
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/lsgl_initFlowInfo_thyj.action?type="+type+"&gw="+gw+"&ld="+ld+"';");
					}else{
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/lsgl_initYwlsglVehFlowList.action?type="+type+"&gw="+gw+"&ld="+ld+"';");
					}
				} else {
					out.println("alert('编辑失败!');parent.loadinit();");
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
	
	// 编辑流水信息
	public String editPtdoi() throws Exception {
		if (ptdoi != null) {
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = ywlsglVehFlowService.insertOrUpdateVehicleTempMidIn(ptdoi, request);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					out.println("alert('修改成功!');");
					out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/lsgl_initYwlsglVehFlowListt.action?gw=2001';");
				} else {
					out.println("alert('编辑失败!');parent.loadinit();");
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
	
	// 删除流水信息
	public String deleteYwlsglVehFlow() throws Exception {
		String ywlsglVehFlowId = request.getParameter("ywlsglVehFlowId");
		if(null != ywlsglVehFlowId){
			this.setReturnInteger(ywlsglVehFlowService.deleteYwlsglVehFlow(ywlsglVehFlowId));
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

	// 删除流水信息(多个)
	public String deleteYwlsglVehFlowList() throws Exception {
		String[] ywlsglVehFlowIds = request.getParameterValues("ids");
		this.setReturnInteger(ywlsglVehFlowService.deleteYwlsglVehFlowList(ywlsglVehFlowIds));
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
	
	
	
	//流水退办
	public String lstb() throws Exception{
		String lsh = request.getParameter("lsh");
		if(null != lsh){
			this.setReturnInteger(ywlsglVehFlowService.lstb(lsh,request));
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
	
	//受理岗流水退回查验岗
	public String lsthyj() throws Exception{
		String lsh = request.getParameter("lsh");
		if(null != lsh){
			this.setReturnInteger(ywlsglVehFlowService.lsthyj(lsh,request));
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
	
	//受理岗流水转嫌疑车
	public String lszxyc() throws Exception{
		response.setCharacterEncoding("UTF-8");//IE用GBK  FF用UTF-8    问题待解决
		String lsh = request.getParameter("lsh");
		PrintWriter out = response.getWriter();
		if(null != lsh){
			this.setReturnInteger(ywlsglVehFlowService.lszxyc(lsh,request));
			try {
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
		}
		out.flush();
		out.close();
		return null;
	}
	
	//受理岗流水暂停恢复
	public String lszthf() throws Exception{
		String lsh = request.getParameter("lsh");
		if(null != lsh){
			this.setReturnInteger(ywlsglVehFlowService.lszthf(lsh,request));
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
	
	// 验证车管车型参数库
	@SuppressWarnings("rawtypes")
	public String yzcgcx() throws Exception {
		String clxh = request.getParameter("clxh");
		try {
			List cgcxccList = ywlsglVehFlowService.getPTMOListBySql("select * from PCB_FINAL_PARA where clxh = '"+ clxh +"'");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			if(cgcxccList.size() < 1){
				out.print(1);
			}else{
				out.print(0);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	//验证环保
	@SuppressWarnings("rawtypes")
	public String yzhb() throws Exception {
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			PrintWriter out = response.getWriter();
			// 车辆识别代号
			String clsbdh = request.getParameter("clsbdh");
			// 车辆型号
			String clxh = request.getParameter("clxh");
			// 发动机型号
			String fdjxh = request.getParameter("fdjxh");
			// 国产还是进口
			String gcjk = request.getParameter("gcjk");
			if("gc".equals(gcjk)){
				List list = ywlsglVehFlowService.getPTMOListBySql("select * from VEHTYPE_AUDITING  where clxh='"+clxh+"' and fdjxh = '"+fdjxh+"'");
				if(list.size() < 1){
					out.print(1);
				}else{
					out.print(0);
				}
			}else{
				// 先查 环保凭证是否有数据
				List hbpzList = ywlsglVehFlowService.getPTMOListBySql("select * from vehtype_login where clsbdh = '"+clsbdh + "'");
				if(hbpzList.size() < 1){
					// 如果没有 再查车型参数表
					List cxccList = ywlsglVehFlowService.getPTMOListBySql("select * from VEHTYPE_AUDITING  where clxh='"+clxh+"' and fdjxh = '" + fdjxh +"'");
					if(cxccList.size() < 1){
						out.print(1);
					}else{
						out.print(0);
					}
				}else{
					out.print(0);
				}
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	// 查询发票信息
	@SuppressWarnings("rawtypes")
	public String yzfp(){
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		
		String fpdm = request.getParameter("fpdm");
		String fphm = request.getParameter("fphm");
		String gzsbh = request.getParameter("gzsbh");
		try{
			out = response.getWriter();
			// 新车比对 fpdm fphm，购置税 gzszmbh  都有记录则返回信息
			List xcList = ywlsglVehFlowService.getPTMOListBySql("select * from fp_xc where fpdm = '"+fpdm+"' and fphm = '"+fphm+"'");
			List gzsList = ywlsglVehFlowService.getPTMOListBySql("select * from fp_xcgzs where gzszmbh = '"+gzsbh+"'");
			if(xcList.size() > 0 && gzsList.size() > 0){
				Object[] xcArray = (Object[])xcList.get(0);
				Object[] gzsArray = (Object[])gzsList.get(0);
				String[] returnInfo = new String[]{
						xcArray[4].toString(),gzsArray[3].toString()
				};
				out.print(JsonUtil.array2json(returnInfo));
			}
			else{
				out.print(0);
			}
		}
		catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			out.print(1);
		}
		finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	public String initFlowInfo_thyj() throws Exception{
		String type = request.getParameter("type");
		String gw = request.getParameter("gw");
		String ld = request.getParameter("ld");
		request.setAttribute("ld", ld);
		request.setAttribute("type", type);
		request.setAttribute("gw", gw);
		this.setYwlsglVehFlowList(ywlsglVehFlowService.getYwlsglVehFlowListByYj(request, currentpage));
		return "yjlist";
	}
	
	/**
	 * @return the ywlsglVehFlow
	 */
	public YwlsglVehFlow getYwlsglVehFlow() {
		return ywlsglVehFlow;
	}

	/**
	 * @param ywlsglVehFlow the ywlsglVehFlow to set
	 */
	public void setYwlsglVehFlow(YwlsglVehFlow ywlsglVehFlow) {
		this.ywlsglVehFlow = ywlsglVehFlow;
	}

	/**
	 * @return the ywlsglVehFlowService
	 */
	public YwlsglVehFlowService getYwlsglVehFlowService() {
		return ywlsglVehFlowService;
	}

	/**
	 * @param ywlsglVehFlowService the ywlsglVehFlowService to set
	 */
	public void setYwlsglVehFlowService(YwlsglVehFlowService ywlsglVehFlowService) {
		this.ywlsglVehFlowService = ywlsglVehFlowService;
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
	 * @return the ywlsglVehFlowList
	 */
	public List<YwlsglVehFlow> getYwlsglVehFlowList() {
		return ywlsglVehFlowList;
	}

	/**
	 * @param ywlsglVehFlowList the ywlsglVehFlowList to set
	 */
	public void setYwlsglVehFlowList(List<YwlsglVehFlow> ywlsglVehFlowList) {
		this.ywlsglVehFlowList = ywlsglVehFlowList;
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

	/**
	 * @return the pdasmycVehPcbService
	 */
	public PdasmycVehPcbService getPdasmycVehPcbService() {
		return pdasmycVehPcbService;
	}

	/**
	 * @param pdasmycVehPcbService the pdasmycVehPcbService to set
	 */
	public void setPdasmycVehPcbService(PdasmycVehPcbService pdasmycVehPcbService) {
		this.pdasmycVehPcbService = pdasmycVehPcbService;
	}


	/**
	 * @return the pdasmycTempMidOutN
	 */
	public PdasmycTempMidOutN getPdasmycTempMidOutN() {
		return pdasmycTempMidOutN;
	}

	/**
	 * @param pdasmycTempMidOutN the pdasmycTempMidOutN to set
	 */
	public void setPdasmycTempMidOutN(PdasmycTempMidOutN pdasmycTempMidOutN) {
		this.pdasmycTempMidOutN = pdasmycTempMidOutN;
	}

	/**
	 * @return the ptdo
	 */
	public VehicleTempMidOut getPtdo() {
		return ptdo;
	}

	/**
	 * @param ptdo the ptdo to set
	 */
	public void setPtdo(VehicleTempMidOut ptdo) {
		this.ptdo = ptdo;
	}

	/**
	 * @return the ptdoList
	 */
	public List<PdasmycTempMidOutN> getPtdoList() {
		return ptdoList;
	}

	/**
	 * @param ptdoList the ptdoList to set
	 */
	public void setPtdoList(List<PdasmycTempMidOutN> ptdoList) {
		this.ptdoList = ptdoList;
	}

	/**
	 * @return the ywlsglVehFieldgllist
	 */
	public List<YwlsglVehFieldgl> getYwlsglVehFieldgllist() {
		return ywlsglVehFieldgllist;
	}

	/**
	 * @param ywlsglVehFieldgllist the ywlsglVehFieldgllist to set
	 */
	public void setYwlsglVehFieldgllist(List<YwlsglVehFieldgl> ywlsglVehFieldgllist) {
		this.ywlsglVehFieldgllist = ywlsglVehFieldgllist;
	}

	public List<VehicleTempMidIn> getVehicleTempMidInList() {
		return vehicleTempMidInList;
	}

	public void setVehicleTempMidInList(List<VehicleTempMidIn> vehicleTempMidInList) {
		this.vehicleTempMidInList = vehicleTempMidInList;
	}

	public VehicleTempMidIn getPtdoi() {
		return ptdoi;
	}

	public void setPtdoi(VehicleTempMidIn ptdoi) {
		this.ptdoi = ptdoi;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
