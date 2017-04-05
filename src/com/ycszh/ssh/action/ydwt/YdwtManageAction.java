package com.ycszh.ssh.action.ydwt;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ycszh.common.ExportExcelBean;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.ydwt.WscgsSjzd;
import com.ycszh.ssh.hbm.ydwt.WtydnsUser;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspbLog;
import com.ycszh.ssh.hbm.ydwt.YdwtDeclareAndQuit;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.ydwt.WtydnsYwsbspbService;
/**
 * 异地委托业务管理
 * @author Administrator
 * @since 2013-09-11
 *
 */
public class YdwtManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(YdwtManageAction.class);
	
	private List<String> pchLsit;       //批次
	
	public List<WtydnsYwsbspb> spbList; //查询出的数据
	
	public List<WscgsSjzd>  hpzlList;   //号牌种类
	
	private WtydnsYwsbspbService pbService;
	
	private List<YdwtDeclareAndQuit> ydwtList; 
	
	private WtydnsUser wtydnsUser;
	
	private List<WtydnsUser> userList;
	
	private WtydnsYwsbspb pb;
	
	private List<WtydnsYwsbspbLog> pbLogs;
	
	private SlgDrvService slgDrvService;
	
	private int currentpage = 1;
	
	private Integer returnInteger = 1;
	
	private List<DydjSbInfo> warnSbList ;
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	/**
	 * 委托异地年检业务信息管理功能(多条件查询)
	 * @return
	 * @throws Exception
	 */
	public String businessManage() throws Exception {
		this.setHpzlList(this.pbService.getHpzl());
		this.setPchLsit(this.pbService.getPCHItems(null));
		this.setSpbList(pbService.getYwsbspbListByCondition(request, currentpage));
		return "busList";
	}
	/**
	 * 导出excel违法信息
	 * @return
	 */
	public String exportStatis() throws Exception{
		request.setCharacterEncoding("UTF-8");
		ExportExcelBean eeb = new ExportExcelBean();  
		 List<WtydnsYwsbspb> spbList = new ArrayList<WtydnsYwsbspb>();		 
		 spbList=pbService.getYwsbspbListExcelByCondition(request);
	  try{
		  
		Map<String,Object> parmsMap = new LinkedHashMap<String,Object>();
		if(!spbList.isEmpty()){
			for (int i = 0; i < spbList.size(); i++) {
				WtydnsYwsbspb pb = (WtydnsYwsbspb)spbList.get(i);
				if("0".endsWith(pb.getSbzt())){
					pb.setSbzt("待初审");
				}else if("1".equals(pb.getSbzt())){
					pb.setSbzt("已初审");
				}else if("2".equals(pb.getSbzt())){
					pb.setSbzt("已复核");
				}else if("3".equals(pb.getSbzt())){
					pb.setSbzt("通知书已接收");
				}else if("4".equals(pb.getSbzt())){
					pb.setSbzt("快递已寄出");
				}else if("CT".equals(pb.getSbzt())){
					pb.setSbzt("车管所退办");
				}else if("YT".equals(pb.getSbzt())){
					pb.setSbzt("邮政退办");
				}else if("QT".equals(pb.getSbzt())){
					pb.setSbzt("个人取消申请");
				}else if("QQ".equals(pb.getSbzt())){
					pb.setSbzt("群众业务退办");
				}else if("CC".equals(pb.getSbzt())){
					pb.setSbzt("车管业务退办");
				}else{
					pb.setSbzt("");
				}
				pb.setId(i+1);  //利用id设置excel生成的序号列
			}
			eeb.setFileName("导出车管所委托异地核发检验合格标志业务审核数据");
			eeb.setFileTitle("导出车管所委托异地核发检验合格标志业务审核数据");
			eeb.setList(spbList);
			parmsMap.put("id","序号");
			parmsMap.put("hphm", "号牌号码");
			parmsMap.put("hpzl", "号牌种类");
			parmsMap.put("clsbdh", "车辆识别代号后4位");
			parmsMap.put("sfzmhm", "车主身份证明号码");
			parmsMap.put("syr", "车主姓名");
			parmsMap.put("stjg", "受托检验机构");			
			parmsMap.put("yjYzbm", "录入时间");//yjYzbm代替
			parmsMap.put("lrip", "申报ip地址");
			parmsMap.put("sbzt", "申报状态");
			parmsMap.put("pch", "批次号");
			parmsMap.put("cgYhdm", "车管审核人");
			parmsMap.put("cgYhxm", "车管审核人姓名");
			parmsMap.put("cgYhsj", "车管审核时间");
			parmsMap.put("cgIp", "车管审核ip");
			parmsMap.put("clsyq", "车辆所有权（0是个人，1是公司）");
			parmsMap.put("lrzh", "录入账户");
			eeb.setParmsMap(parmsMap);
			com.ycszh.common.Util.exportExcel(response, eeb);
	      }
	 }catch(Exception e){
		e.printStackTrace();
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
		logger.error("ReCheckBusBidAction-->exportStatis导出execl方法:"+exceptionstr);
	 }
		return NONE;
   }
	
	/**
	 * 根据id显示申报详情
	 * @return
	 * @throws Exception
	 */
	public String displayDetail() throws Exception {
		String id = request.getParameter("id");
       if(id!=null){
    	   WtydnsYwsbspb pb = this.pbService.getWtydsnIfo(Integer.parseInt(id));
    	   if(pb!=null){
        	   this.setPb(pb);
        	   this.setPbLogs(this.pbService.getWtydsnLogInfo(Integer.parseInt(id)));//查询日志记录。
        	   return "busDetail";
           }else{
        	   request.setAttribute("errorTip", "用户不存在，请核实后再操作!");
    		   return "Exception";
           }
       }else{
    	   request.setAttribute("errorTip", "服务器忙,请稍后再试!");
    	   return "Exception";
       }      		
	}
	/**
	 * 互联网业务申报、办结、退办数据统计功能
	 * @return
	 * @throws Exception
	 */
	public String declareAndQuitStat() throws Exception {
		this.setYdwtList(this.pbService.getYdwtDeclareAndquitStat(request));
		return "quitStat";
	}
	/**
	 * 材料移交邮政信息查询统计
	 * @return
	 * @throws Exception
	 */
	public String ydwtPosQuitStat() throws Exception {
		this.setYdwtList(this.pbService.getYdwtPosQuitStat(request));
		return "postQuit";
	}	
	
	/**
	 * 初始化 
	 * @return
	 */
	public String initUser() throws Exception {
		request.setAttribute("editType", "新增");
		return "initAdd";
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String editUserInit() throws Exception {
		this.setWtydnsUser(this.pbService.getWtydnsUserInfo(request));
		request.setAttribute("editType", "修改");
		return "initAdd";
	}
	/**
	 * 用户管理
	 * @return
	 * @throws Exception
	 */
	public String getUsersList() throws Exception {
		this.setUserList(this.pbService.getUserList(request, currentpage));
		return "userList";
	}
		
	/**
	 * 邮政、车管用户开户添加
	 * @return
	 * @throws Exception
	 */
	public String addUser() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (wtydnsUser != null) {
			try{
			   int returnInteger =this.pbService.addUserInfo(request, wtydnsUser);
			   if(returnInteger!=3){
				   if (returnInteger >0) {
						out.println("alert('操作成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/ydwt/ydwt_getUsersList.action';");
					} else {
						out.println("alert('操作失败!')");
					}
			   }else{
				   out.println("alert('该用户代码已存在!');");
				   out.println("parent.document.getElementById('yzYhdm').style.borderColor = '#FF0000';");
			       out.println("parent.document.getElementById('yzYhdm').focus();");
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
	
	//冻结解冻用户信息
	public String freezeUser() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		if(null != id){
			this.setReturnInteger(pbService.freezeUser(Long.parseLong(id)));
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
				throw e;
			}
		}
		return null;
	}
	/**
	 * 重置密码
	 */
	public String reSetPwd() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		 String id = request.getParameter("id");
		if(null!= id){
			try {
				this.setReturnInteger(pbService.resetPwd(id, request));
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
				response.getWriter().print(0);
			}
			return null;
		}else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	/**
	 * ydwt未经申报办理业务预警（功能）
	 * @return
	 * @throws Exception
	 */
	public String initWarnYdwt() throws Exception {
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		this.setWarnSbList(this.pbService.getWarnInfo(request,currentpage));		
		return "initWarn";
	}
	
	public List<String> getPchLsit() {
		return pchLsit;
	}
	public void setPchLsit(List<String> pchLsit) {
		this.pchLsit = pchLsit;
	}
	public List<WtydnsYwsbspb> getSpbList() {
		return spbList;
	}
	public void setSpbList(List<WtydnsYwsbspb> spbList) {
		this.spbList = spbList;
	}
	public List<WscgsSjzd> getHpzlList() {
		return hpzlList;
	}
	public void setHpzlList(List<WscgsSjzd> hpzlList) {
		this.hpzlList = hpzlList;
	}
	public WtydnsYwsbspbService getPbService() {
		return pbService;
	}
	public void setPbService(WtydnsYwsbspbService pbService) { 
		this.pbService = pbService;
	}
	public List<YdwtDeclareAndQuit> getYdwtList() {
		return ydwtList;
	}
	public void setYdwtList(List<YdwtDeclareAndQuit> ydwtList) {
		this.ydwtList = ydwtList;
	}   
    
	public WtydnsUser getWtydnsUser() {
		return wtydnsUser;
	}
	public void setWtydnsUser(WtydnsUser wtydnsUser) {
		this.wtydnsUser = wtydnsUser;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public List<WtydnsUser> getUserList() {
		return userList;
	}
	public void setUserList(List<WtydnsUser> userList) {
		this.userList = userList;
	}
	public Integer getReturnInteger() {
		return returnInteger;
	}
	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}
	public WtydnsYwsbspb getPb() {
		return pb;
	}
	public void setPb(WtydnsYwsbspb pb) {
		this.pb = pb;
	}
	public List<WtydnsYwsbspbLog> getPbLogs() {
		return pbLogs;
	}
	public void setPbLogs(List<WtydnsYwsbspbLog> pbLogs) {
		this.pbLogs = pbLogs;
	}
	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}
	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}
	public List<DydjSbInfo> getWarnSbList() {
		return warnSbList;
	}
	public void setWarnSbList(List<DydjSbInfo> warnSbList) {
		this.warnSbList = warnSbList;
	}
	
	
}
