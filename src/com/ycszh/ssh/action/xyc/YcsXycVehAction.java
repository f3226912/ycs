package com.ycszh.ssh.action.xyc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.util.JsonUtil;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.xyc.YcsXycCode;
import com.ycszh.ssh.hbm.xyc.YcsXycVeh;
import com.ycszh.ssh.service.xyc.YcsXycVehService;

public class YcsXycVehAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(YcsXycVehAction.class);
	private YcsXycVeh ycsXycVeh;
	private YcsXycVehService ycsXycVehService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<YcsXycVeh> ycsXycVehList = new ArrayList<YcsXycVeh>();
	
	// 嫌疑车字典信息列表
	public String initYcsXycVehList() throws Exception {
		this.setYcsXycVehList(ycsXycVehService.getYcsXycVehList(request, currentpage));
		request.setAttribute("ycsXycVehList", ycsXycVehList);
		return "list";
	}
	
	// 初始化添加页面
	public String insertYcsXycVeh() throws Exception {
		request.setAttribute("editType", "新增");
		// 从字典表中得到嫌疑原因
		List<YcsXycCode> ycsXycCodeList = ycsXycVehService.findSQL("select * from ycs_xyc_code where dmlb = 'XYYY'",YcsXycCode.class);
		request.setAttribute("ycsXycCodeList", ycsXycCodeList);
		// 从另外一个字典表中得到号牌种类
		List esVehCodeList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=7 ");
		request.setAttribute("esVehCodeList", esVehCodeList);
		
		List sfzmmcList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=19 ");
		request.setAttribute("sfzmmcList", sfzmmcList);
		
		// 从另外一个字典表中得到业务类型
		List ywlxList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX'");
		request.setAttribute("ywlxList", ywlxList);
		
		// 从另外一个字典表中得到业务原因
		/*List ywyyList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX_%'");
		request.setAttribute("ywyyList", ywyyList);*/
		return "insert";
	}
	
	// 初始化修改页面
	public String updateYcsXycVeh() throws Exception {
		if (ycsXycVeh.getXh() != null || !"".equals(ycsXycVeh.getXh())) {
			this.setYcsXycVeh(ycsXycVehService.getYcsXycVeh(ycsXycVeh.getXh()));
			if (this.getYcsXycVeh() != null) {
				request.setAttribute("editType", "修改");
				// 从字典表中得到嫌疑原因
				List<YcsXycCode> ycsXycCodeList = ycsXycVehService.findSQL("select * from ycs_xyc_code where dmlb = 'XYYY'",YcsXycCode.class);
				request.setAttribute("ycsXycCodeList", ycsXycCodeList);
				// 从另外一个字典表中得到号牌种类
				List esVehCodeList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=7 ");
				request.setAttribute("esVehCodeList", esVehCodeList);
				
				List sfzmmcList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=19 ");
				request.setAttribute("sfzmmcList", sfzmmcList);
				
				// 从另外一个字典表中得到业务类型
				List ywlxList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX'");
				request.setAttribute("ywlxList", ywlxList);
				
				// 从另外一个字典表中得到业务原因
				/*List ywyyList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX_%'");
				request.setAttribute("ywyyList", ywyyList);*/
				String lrrbm = ycsXycVehService.findSQL("select org_name from vehoffice.v_veh_org_ycs t where org_id = '"+this.getYcsXycVeh().getLrrbm()+"'").toString();
				this.getYcsXycVeh().setLrrbm(lrrbm);
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
	public String initYcsXycVeh() throws Exception {
		if (ycsXycVeh.getXh() != null) {
			this.setYcsXycVeh(ycsXycVehService.getYcsXycVeh(ycsXycVeh.getXh()));
			if (this.getYcsXycVeh() != null) {
				request.setAttribute("editType", "查看");
				// 从字典表中得到嫌疑原因
				List<YcsXycCode> ycsXycCodeList = ycsXycVehService.findSQL("select * from ycs_xyc_code where dmlb = 'XYYY'",YcsXycCode.class);
				request.setAttribute("ycsXycCodeList", ycsXycCodeList);
				// 从另外一个字典表中得到号牌种类
				List esVehCodeList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=7 ");
				request.setAttribute("esVehCodeList", esVehCodeList);
				
				List sfzmmcList = ycsXycVehService.findSQL("select dmz,dmsm1 from es_veh_code where dmlb=19 ");
				request.setAttribute("sfzmmcList", sfzmmcList);
				
				// 从另外一个字典表中得到业务类型
				List ywlxList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX'");
				request.setAttribute("ywlxList", ywlxList);
				String lrrbm = ycsXycVehService.findSQL("select org_name from vehoffice.v_veh_org_ycs t where org_id = '"+this.getYcsXycVeh().getLrrbm()+"'").toString();
				this.getYcsXycVeh().setLrrbm(lrrbm);
				// 从另外一个字典表中得到业务原因
				/*List ywyyList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb like 'XYC_YWLX_%'");
				request.setAttribute("ywyyList", ywyyList);*/
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
	
	// 异步查询申领发放详细信息集合
	public String getYwyyList() throws Exception {
		String ywlx = request.getParameter("ywlx");
		List aywyyList = ycsXycVehService.findSQL("select dmz,dmsm1 from pdasmyc_sjzd t where dmlb = 'XYC_YWLX_"+ywlx+"'");
		if (null != aywyyList) {
			try {
				String jsonString = JsonUtil.object2json(aywyyList);
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
		}
		return null;
	}
	
	// 编辑嫌疑车字典信息
	public String editYcsXycVeh() throws Exception {
		if (ycsXycVeh != null) {
			String YcsXycVehid = ycsXycVeh.getXh();
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				String returnstr = ycsXycVehService.insertOrUpdateYcsXycVeh(ycsXycVeh, request);
				if ("0".equals(returnstr)) {
					if(null != YcsXycVehid && !"".equals(YcsXycVehid)){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/xyc/xycveh_initYcsXycVehList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/xyc/xycveh_initYcsXycVehList.action';");
					}
				} else {
					out.println("alert('编辑失败!原因:"+returnstr+"')");
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
	public String deleteYcsXycVeh() throws Exception{
		String ycsXycVehId = request.getParameter("ycsXycVehId");
		if(null != ycsXycVehId){
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			try {
				String returnstr = ycsXycVehService.deleteYcsXycVeh(request, ycsXycVehId);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println(returnstr);
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

	

	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public YcsXycVeh getYcsXycVeh() {
		return ycsXycVeh;
	}

	public void setYcsXycVeh(YcsXycVeh ycsXycVeh) {
		this.ycsXycVeh = ycsXycVeh;
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

	public YcsXycVehService getYcsXycVehService() {
		return ycsXycVehService;
	}

	public void setYcsXycVehService(YcsXycVehService ycsXycVehService) {
		this.ycsXycVehService = ycsXycVehService;
	}

	public List<YcsXycVeh> getYcsXycVehList() {
		return ycsXycVehList;
	}

	public void setYcsXycVehList(List<YcsXycVeh> ycsXycVehList) {
		this.ycsXycVehList = ycsXycVehList;
	}

	
}
