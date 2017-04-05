package com.ycszh.ssh.action.bfc;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.bfc.BfcTsspb;
import com.ycszh.ssh.service.bfc.BfcTsspbService;
import com.ycszh.util.ToolsUtil;

/**
 * @包名:com.ycszh.ssh.action.bfc
 * @文件名:BfcTsspbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-13
 * @描述:
 * @版本:V 1.0
 */
public class BfcTsspbAction extends BaseAction {

	private BfcTsspbService bfcTsspbService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private BfcTsspb bfcTssp = new BfcTsspb();
	
	public String getBfcTsspbList() throws Exception{
		
		bfcTsspbService.getBfcTsspbInfo(request,currentpage);
		
		return "splist";
	}
	
	public String initAddBfcTsspb() throws Exception{
		
		List list = bfcTsspbService.getBfcTsspbInfoBySql("select DMZ,DMSM1 from extshare.ext_veh_code where dmlb = '2019'");
		request.setAttribute("sfzmmcList", list);
		
		return "add";
	}
	
	public String addBfcTsspb() throws Exception{
		
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		
		// 得到登陆用户
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		bfcTssp.setMSpid(user.getName());
		bfcTssp.setCzr(user.getName());
		
		bfcTssp.setMSpxm(user.getYgxm());
		bfcTssp.setCzrxm(user.getYgxm());
		
		bfcTssp.setMSpip(ToolsUtil.getIpAddr(request));
		bfcTssp.setCzip(ToolsUtil.getIpAddr(request));
		
		bfcTssp.setMSpbmid(user.getBmid());
		bfcTssp.setCzrbm(user.getBmid());
		
		bfcTssp.setMSpbmmc(user.getBmmc());
		
		bfcTssp.setMSpsj(new Date());
		bfcTssp.setCzsj(new Date());
		
		bfcTssp.setSpzt("1");
		
		// 拿到用户处级部门
		List list = bfcTsspbService.getBfcTsspbInfoBySql("select t.org_id from vehoffice.v_veh_org_ycs t"
												+" where org_id in (select t.org_id"
													+" from vehoffice.v_veh_org_ycs t"
													+" where level = '2'"
													+" start with org_id='C34702A8FED77CBFE040007F0100339B'"
													+" connect by prior org_id = up_org )"
												+" start with org_id='"+user.getBmid()+"'"
												+" connect by prior up_org = org_id");
		
		bfcTssp.setCzrkjbm(list.get(0)+"");
		
		
		
		int returnValue = bfcTsspbService.addBfcTsspb(bfcTssp, request);
		
		if(returnValue == 0){
			out.println("<script>");
			out.println("alert('添加成功!');");
			out.println("parent.window.location.href = '"+request.getContextPath()+"/bfc/bfcsp_getBfcTsspbList.action?jb=3';");
			out.println("</script>");
		}
		else{
			out.print("<script>alert('添加失败，请重试!');</script>");
		}
		
		out.flush();
		out.close();
//		
		return null;
	}
	
	
	public String delBfcTsspbInfoByXh() throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		
		try{
			List list = bfcTsspbService.getBfcTsspbInfoByPar(request);
			String xh = request.getParameter("xh");
			if(list.size() > 0){
				BfcTsspb bfc = (BfcTsspb)list.get(0);
				// 如果登陆用户是该记录的申请人
				User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
				
				// 判断该记录是否还为审核
				if("1".equals(bfc.getSpzt())){
					// 判断该记录是否为本人录入
					if(bfc.getMSpid().equals(user.getYgid())){
						// 删除该记录
						bfcTsspbService.delBfcTsspbInfo(xh, request);
						out.print(0);
					}
					else{
						// 该记录非本人录入
						out.print(1);
					}
				}
				else{
					// 该记录已经被审核
					out.print(3);
				}
			}
			else{
				// 记录不存在
				out.print(2);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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
		finally{
			out.flush();
			out.close();
		}
		
		
		return NONE;
	}
	
	// 审批  0 成功   1 记录已经被处理  2 非法操作！
	public String spBfc() throws Exception{
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		try{
			String xh = request.getParameter("xh");
			String zt = request.getParameter("zt");
			String bz = request.getParameter("bz");
			String jb = request.getParameter("jb");
			
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			
			List bfcList = bfcTsspbService.getBfcTsspbInfoByPar(request);
			BfcTsspb bfcTsspb = (BfcTsspb)bfcList.get(0);
			
			// 判断是否为处级
			if("1".equals(jb)){
				// 判断该记录状态是否处级待审批
				if("3".equals(bfcTsspb.getSpzt())){
					//判断状态，如果是审批通过，则需要推算有效日期
					if("0".equals(zt)){
						Date date=new   Date();//取时间 
					    Calendar calendar = new GregorianCalendar(); 
					    calendar.setTime(date); 
					    calendar.add(calendar.DATE,Integer.parseInt(bfcTsspb.getYsts()+""));//把日期往后增加一天.整数往后推,负数往前移动 
					    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
						bfcTsspb.setYsrq(date);
						bfcTsspb.setSpzt("6");
					}
					else{
						bfcTsspb.setSpzt("4");
						bfcTsspb.setYsrq(null);
					}
					bfcTsspb.setCSpid(user.getName());
					bfcTsspb.setCSpxm(user.getYgxm());
					bfcTsspb.setCSpbmid(user.getBmid());
					bfcTsspb.setCSpbmmc(user.getBmmc());
					bfcTsspb.setCSpsj(new Date());
					bfcTsspb.setCSpip(ToolsUtil.getIpAddr(request));
					
					bfcTsspb.setTranFlag(null);
					bfcTsspb.setTranDate(null);
					bfcTsspb.setBz(bz);
					bfcTsspbService.updateTsspbInfo(bfcTsspb, request);
					out.print(0);
				}
				else{
					// 该记录已经被处理。
					out.print(1);
				}
			}
			//  判断是否为科级
			else if("2".equals(jb)){
				// 判断该记录状态是否科级待审批
				if("1".equals(bfcTsspb.getSpzt())){
					//判断状态，如果是审批通过
					if("0".equals(zt)){
						// 判断审批级别
						if("1".equals(bfcTsspb.getSpjb())){
							bfcTsspb.setSpzt("5");
							Date date=new   Date();//取时间 
						    Calendar calendar = new GregorianCalendar(); 
						    calendar.setTime(date); 
						    calendar.add(calendar.DATE,Integer.parseInt(bfcTsspb.getYsts()+""));//把日期往后增加一天.整数往后推,负数往前移动 
						    date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
							bfcTsspb.setYsrq(date);
						}
						else{
							bfcTsspb.setSpzt("3");
							bfcTsspb.setYsrq(null);
						}
					}
					else{
						bfcTsspb.setSpzt("2");
						bfcTsspb.setYsrq(null);
					}
					bfcTsspb.setKSpid(user.getName());
					bfcTsspb.setKSpxm(user.getYgxm());
					bfcTsspb.setKSpbmid(user.getBmid());
					bfcTsspb.setKSpbmmc(user.getBmmc());
					bfcTsspb.setKSpsj(new Date());
					bfcTsspb.setKSpip(ToolsUtil.getIpAddr(request));
					
					bfcTsspb.setTranFlag(null);
					bfcTsspb.setTranDate(null);
					bfcTsspb.setBz(bz);
					bfcTsspbService.updateTsspbInfo(bfcTsspb, request);
					out.print(0);
				}
				else{
					// 该记录已经被处理。
					out.print(1);
				}
			}
			else{
				// 既不是处级又不是科级
				out.print(2);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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
		finally{
			out.flush();
			out.close();
		}
		
		return NONE;
	}
	
	// 报废车审批查询
	public String bfcSelList() throws Exception{
		
//		List list = bfcTsspbService.getBfcTsspbInfoBySql("select org_id,org_name"
//                +" from vehoffice.v_veh_org_ycs t"
//                +" where level = '2'"
//                +" start with org_id='C34702A8FED77CBFE040007F0100339B'"
//                +" connect by prior org_id = up_org");
		
		List list = bfcTsspbService.getBfcTsspbInfoBySql("select org_id,org_name"
                +" from vehoffice.v_veh_org_ycs t"
                +" where up_org='C34702A8FED97CBFE040007F0100339B'");

		request.setAttribute("bfcSpBmList", list);
		
		bfcTsspbService.getBfcTsspbInfo2(request,currentpage);
		
		return "spSelList";
	}

	// 报废车审批统计
	public String bfcSpCountList() throws Exception{
		String sql = "select a.org_id,a.org_name,"
					+" (select count(0) from bfc_tsspb "
						+" where m_spbmid in (select org_id from vehoffice.v_veh_org_ycs connect by prior org_id = up_org start with org_id= a.org_id)"
					+" ) cou"
					+" from ("
						+" select org_id,org_name"
						+" from vehoffice.v_veh_org_ycs t"
						+" where up_org = 'C34702A8FED97CBFE040007F0100339B') a order by cou desc";
		List list = bfcTsspbService.getBfcTsspbInfoBySql(sql);
		
		request.setAttribute("list", list);
		
		return "bfcSpCount";
	}
	
	// 报废车审批 详细信息查看
	public String getBfcTsspbByXh() throws Exception{
		
		String xh = request.getParameter("xh");
		BfcTsspb bfcTsspb = bfcTsspbService.getBfcTsspbByXh(xh);
		
		request.setAttribute("bfcTsspb", bfcTsspb);
		
		return "selBfcInfo";
	}
	
	public BfcTsspb getBfcTssp() {
		return bfcTssp;
	}
	public void setBfcTssp(BfcTsspb bfcTssp) {
		this.bfcTssp = bfcTssp;
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
	public BfcTsspbService getBfcTsspbService() {
		return bfcTsspbService;
	}
	public void setBfcTsspbService(BfcTsspbService bfcTsspbService) {
		this.bfcTsspbService = bfcTsspbService;
	}
	
}
