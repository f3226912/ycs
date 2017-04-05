package com.ycszh.ssh.action.yanche;


import java.io.File;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.action.drv.SlgDrvAction;
import com.ycszh.ssh.hbm.yanche.TXbycCode;
import com.ycszh.ssh.hbm.yanche.TXbycGps;
import com.ycszh.ssh.service.yanche.YczdglService;
import com.ycszh.util.FileTools;

public class YczdglAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private YczdglService yczdglService;
	private int currentpage = 1;
	private int returnInteger = 1;
	private int returnInteger1 = 1;
	private TXbycCode yanche;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private TXbycGps gps;

//==========================电子签名管理
	//查看各部门签名用户列表
	public String findQmUser() throws Exception{
		try {
			//获取部门信息
			yczdglService.initDeptNode(request);
			//获取用户签名列表
			yczdglService.getQmUserList(request, currentpage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "dzqmlist";
	}
	
	
	//查看用户签名
	public String showDzqmInfo() throws Exception{
		yczdglService.getUserDzqmInfo(request);
		return "showdzqm";
	}
	
	//*******查看用户签名信息列表
	/*public String findUserDzqm() throws Exception{
		try {
			yczdglService.getUserDzqm(request, currentpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dzqmlist";
	}*/
	
	//签名信息录入
	public void qmlrSubmit() throws Exception{		
		response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		try {
			//User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String loginid = request.getParameter("loginid");
			imageFileName=loginid+".jpg";
			returnInteger = yczdglService.updateDzqm(request, image, imageFileName);
			//获取返回签名用户列表参数
			String url = request.getParameter("url");
			String action = request.getParameter("action");
			String currentpage = request.getParameter("currentpage");
			String orgid = request.getParameter("orgid");
			
			if (returnInteger == 0) {
					out.println("alert('电子签名上传成功!');");
					out.println("parent.window.location.href = '"+request.getContextPath()+"/yanche/yczd_dzqmgl.action?" +
							"url="+url+"&action="+action+"&currentpage="+currentpage+"&orgid="+orgid+"&loginid="+loginid+"';");
			} else if(returnInteger == 2){
				out.println("alert('电子签名格式不正确，只能为图片!')");
			}else if(returnInteger == 3){
				out.println("alert('电子签名上传失败!')");
			}else {
				out.println("alert('系统繁忙，请稍后再试!')");
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
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
	}
	
	//获取签名信息
	public String dzqmgl() throws Exception{
		yczdglService.getDzqmInfo(request);
		return "dzqm";
	}
	
//==========================验车点位置管理
	//删除验车点GPS位置信息
	public String deleteYcGps() throws Exception{
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(yczdglService.deleteGps(request));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
	
	//提交编辑验车点GPS位置信息
	public String editYcGps() throws Exception{
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(yczdglService.editYcGps(request,gps));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		} catch (Exception e) {
			e.printStackTrace();
			this.setReturnInteger(4);
		}
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
	//编辑验车点GPS位置信息
	public String intieditYcGps() throws Exception{
		yczdglService.initEditycGps(request);
		return "initEditGsp";
	}
	
	//获得验车点位置信息列表
	public String ycGpsgl() throws Exception {
		yczdglService.getYcgpsList(request, currentpage);
		return "ycGpslist";
	}
	
//===========================查验记录查看与打印
	//打印表单
	public String printCybd() throws Exception{
		try {
			String type = (String) yczdglService.getCybd(request);
			if(type=="xc"){
				return "print_xc";
			}else if(type=="qt"){
				return "print_qt"; 
			}else{
				return "exception";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	//查看查验表单
	public String showCjbd() throws Exception{
		try {
			String type = (String) yczdglService.getCybd(request);
			if(type=="xc"){
				return "cybd_xc";
			}else if(type=="qt"){
				return "cybd_qt"; 
			}else{
				return "exception";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "exception";
		}
	}
	
	//查看查验记录列表
	public String showCjyjl() throws Exception{
		yczdglService.getCyjl(request, currentpage);
		return "cyjl";
	}

//===========================验车用户类型性质配置管理
	//获取用户车辆类型使用性质配置信息
	public String showYhlxxz() throws Exception{
		try {
			yczdglService.getYcyhlxxzList(request, currentpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ycyhlxxz";
	}
	
	//初始化用户类型性质配置信息
	public String initEditYhlxxz() throws Exception{
		try {
			yczdglService.getEditYhlxxz(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "initEditYcyh";
	}
	
	//修改用户验车类型性质配置
	public String updateYhlxxz() throws Exception{
		response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			this.setReturnInteger(yczdglService.updateYcyhlxxz(request));
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
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
//=============================验车字典管理	
	//添加/修改提交
	public String saveOrupdateSubmit() throws Exception{
		PrintWriter out = response.getWriter();
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			if(imageFileName!=null){
				request.setAttribute("fileName", imageFileName);
			}
			String type = request.getParameter("type"); //1.修改 /2.添加
			String yeType = request.getParameter("yeType");
			if(yeType=="pz"||yeType.equals("pz")){
				if(imageFileName!=null&&!imageFileName.equals("")){
					request.setAttribute("fileName", imageFileName);
					//上传文件
					this.setReturnInteger1(yczdglService.uploadFile(request, image, imageFileName));
					if(returnInteger1==0){
						//修改/添加库信息
						this.setReturnInteger(yczdglService.saveOrupdateYanche(request));
						if(type=="1"||type.equals("1")){//修改
							//删除原先旧图片
							String servletPath = request.getSession().getServletContext().getRealPath("/");  //获取工程绝对路径
							imageFileName=servletPath+request.getAttribute("imgUrl");	//获取原先图片路径
							Boolean bool =FileTools.deleteFileByFileName(imageFileName);
							if(bool){
								this.setReturnInteger1(0);
							}else{
								this.setReturnInteger1(1);
							}
						}
					}
					request.setAttribute("returnInteger1", returnInteger1);
				}else{
					//修改/添加库信息
					this.setReturnInteger1(yczdglService.saveOrupdateYanche(request));
				}
				return "pzsubmit";
			}else{
				this.setReturnInteger(yczdglService.saveOrupdateYanche(request));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
	//初始化【使用性质/汽车类型】编辑页面
	public String CyxmEditinit() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		yczdglService.getCllxOrSyxzEditInit(request);
		return "editInit";
	}
	
	//初始化【查验项目/拍照规格】编辑页面
	public String CyOrPzEditInit() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		yczdglService.getCyOrPzEditInit(request);
		return "edit_cyOrpz";
	}
	
	//初始化【其它项目】编辑页面
	public String qtEditInit() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		yczdglService.getCyOrPzEditInit(request);
		return "edit_qt";
	}
	
	//获取验车管理信息(固定字典查询)
	@SuppressWarnings("unchecked")
	public String initYcgl() throws Exception{
		String fjsz = request.getParameter("fjsz");
		try {
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			yczdglService.getYcType(request,currentpage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(fjsz!=null&&!fjsz.equals("")){
			return "fjtjgl";
		}else{
			return "initTree";
		}
	}
	
	//复检条件修改
	public String fjtjgl() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		this.setReturnInteger(yczdglService.updateFjtj(request));
		PrintWriter out = response.getWriter();
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
	//获取参数配置字典管理
	public String initPzzdgl() throws Exception{
		String ly = request.getParameter("ly");
		yczdglService.getSyxzOrCllx(request, currentpage);
		return ly;
	} 
	
	//【车辆类型/使用性质】获取查验依据或者拍照规格
	public String showCyyjOrPzgg() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		request.setCharacterEncoding("utf-8");
		yczdglService.getCyyjOrPzgg(request);
		return "cyyjOrpzgg";
	}
	
	//【查验项目/拍照规格】总目录
	public String findCyxmOrPzggML() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		yczdglService.getCyxmOrPzggML(request);
		return "cyyjOrpzgg";
	}
	
	//根据业务类型判断代码值（DMZ）是否存在
	public String existsBydmz() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		this.setReturnInteger(yczdglService.exitesByDmz(request));
		PrintWriter out = response.getWriter();
		out.println(returnInteger);
		out.flush();
		out.close();
		return null;
	}
	
	public YczdglService getYczdglService() {
		return yczdglService;
	}
	public void setYczdglService(YczdglService yczdglService) {
		this.yczdglService = yczdglService;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public TXbycCode getYanche() {
		return yanche;
	}
	public void setYanche(TXbycCode yanche) {
		this.yanche = yanche;
	}
	public int getReturnInteger() {
		return returnInteger;
	}
	public void setReturnInteger(int returnInteger) {
		this.returnInteger = returnInteger;
	}
	public static Logger getLogger() {
		return logger;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public int getReturnInteger1() {
		return returnInteger1;
	}
	public void setReturnInteger1(int returnInteger1) {
		this.returnInteger1 = returnInteger1;
	}
	public TXbycGps getGps() {
		return gps;
	}
	public void setGps(TXbycGps gps) {
		this.gps = gps;
	}


	
	
}
