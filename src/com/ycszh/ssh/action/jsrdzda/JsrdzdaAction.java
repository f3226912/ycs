package com.ycszh.ssh.action.jsrdzda;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import org.apache.log4j.Logger;
import com.ycszh.global.SysConst;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxb;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhoto;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszDaxxbPhotoLog;
import com.ycszh.ssh.hbm.jsrdzda.DzdaJszSjzd;
import com.ycszh.ssh.hbm.jsrdzda.EsDrvLicense;
import com.ycszh.ssh.service.jsrdzda.JsrdzdaService;
import com.ycszh.util.JsonUtil;

public class JsrdzdaAction extends BaseAction {	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(JsrdzdaAction.class);
	private JsrdzdaService jsrdzdaService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<DzdaJszDaxxb> jsrCjList;
	private DzdaJszDaxxb dzxxb;
	private Map<String,String> ywlxMap;   
	private Map<String,String> ywlxAMap;   //业务类型
	private List<DzdaJszDaxxbPhoto> photos;//照片
	private Map<String,String> zjcxMap;    //准驾车型
	private List<DzdaJszSjzd> tpTbyyMap;  //图片退办原因
    private List list ;
	private EsDrvLicense esDrv;
	/**
	 * 获取取待驾校初审和车管退办的采集数据	
	 * 1：清空本人名下已经锁定的记录
	 * 2：获取本人名下的JT,CT的数据
	 */
	public String initJsrCjList() throws Exception {			
		this.jsrdzdaService.updateLockByCurrentUser(request,"first");//解除当前用户下的
		this.setJsrCjList(jsrdzdaService.getJsrUserList(request,"select"));
		this.setList(this.jsrdzdaService.getStatList(request));//提示窗的数据显示
		return "cjlist";
	}
	
	/*	
	 * 点击新增按钮后，锁定数据
	 */
	public String addCurrentLockList() throws Exception {
		String btnType = request.getParameter("btnType");
		if(btnType==null){
			this.jsrdzdaService.lockRecoredByUser(request,"first");//获取待审15条
		}	   
	   this.setJsrCjList(jsrdzdaService.getJsrUserList(request,"add"));
		return "cjlist";
	}
	
	/**
	 * 确定锁定单一记录，
	 * @return
	 * @throws Exception
	 */
	public String lockRecoredBysfz() throws Exception {
		this.jsrdzdaService.lockRecordByCurrentUsr(request);
		this.setJsrCjList(jsrdzdaService.getLockBySfz(request));
		return "cjlist";
	}
	
	/**
	 *  初始化初审页面
	 */
	public String showJsrShPage() throws Exception {
		String cjxh = request.getParameter("cjxh");
		this.setDzxxb(this.jsrdzdaService.getJsrCjInfo(cjxh));		
		this.setPhotos(this.jsrdzdaService.getPhotoListByCjxh(cjxh,this.getDzxxb().getYwlx()));
		this.setZjcxMap(this.jsrdzdaService.getJszSjzdDict("ZJCX"));   
		this.setYwlxAMap(this.jsrdzdaService.getJszSjzdDict("YWLX_"+this.getDzxxb().getYwlx()));//所有的资料
		
		//查看该份资料中是否有不在字典类型的资料里
		List<DzdaJszDaxxbPhoto> noMatchList = new ArrayList<DzdaJszDaxxbPhoto>();
		List<DzdaJszDaxxbPhoto>  allPhotos = this.jsrdzdaService.getPhotosByxh(cjxh);
		if(allPhotos.size()>0){
			for (DzdaJszDaxxbPhoto pto : allPhotos) {
				if(ywlxAMap.get(pto.getZllx())==null){
					noMatchList.add(pto);       //不在字典内的资料类型集合,其他类型资料
				}
			}
		}
		request.setAttribute("noMatchList",noMatchList);		
		this.setTpTbyyMap(this.jsrdzdaService.getJszSjzdList("TBYY_PHOTO"));//图片退办原因
		if(this.getDzxxb() != null){
			String editType = request.getParameter("editType");
			request.setAttribute("editType",editType);			
			if(editType.equals("show")){
				request.setAttribute("temp", request.getParameter("temp"));//展示采集人，审核人信息的标志（档案卷宗查询 功能的查询列表的查看中）
				return "showJsr";  //查看采集档案
			}else if(editType.equals("check")){
				return "jsrCj";    //驾校初审
			}else if(editType.equals("cgCk")){
				return "cgCheck";  //车管审核	
			}else if(editType.equals("buCheck")) {
				return "buCheckCj";//驾校补审				
			}else{
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该驾驶人采集信息!");
			return "Exception";
		}
	}
	
	//根据采集序号(cjxh)和资料类型(zllx)获取图片列表中对应的序号
	public String checkPhotoXh() throws Exception {
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out =null;
		String result = "true";
		try{			
			Integer data=this.jsrdzdaService.getPhotoXhByInfo(request);
			out= response.getWriter();
			if(data==0){
				out.println(result);
			}			
			out.println(data);
		}catch(Exception e){
			e.printStackTrace();
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
		}finally{
			if(out!=null){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	/***
	 * 驾校初审/车管复核
	 * @return
	 * @throws Exception
	 */
	public String jxFirstCheck() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		out.println("<script>");
		try{
			  if(dzxxb.getYwlx()==null || dzxxb.getYwlx().equals("--")){
				  out.println("alert('请选择业务类型!');");
  				  out.println("parent.document.getElementById('ywlx').focus();");
  				  flag = true;
			  }else{
				  if(dzxxb.getDabh()==null || dzxxb.getDabh().equals("")){
					  out.println("alert('请填写档案编号!');");
	  				  out.println("parent.document.getElementById('dabh').focus();");
	  				  flag = true;
				  }else{
					  if(dzxxb.getXm()==null || dzxxb.getXm().equals("")){
						  out.println("alert('请填写姓名!');");
		  				  out.println("parent.document.getElementById('xm').focus();");
		  				  flag = true;
					  }else{
						  if(dzxxb.getZjcx()==null || dzxxb.getZjcx().equals("--")){
							  out.println("alert('请选择准驾车型!');");
			  				  out.println("parent.document.getElementById('zjcx').focus();");
			  				  flag = true;
						  }else{
							  if(dzxxb.getSfzmhm()==null || dzxxb.getSfzmhm().equals("")){
								  out.println("alert('请填写身份证明号码!');");
				  				  out.println("parent.document.getElementById('sfzmhm').focus();");
				  				  flag = true;
							  } else{
								  if(dzxxb.getSfzmmc()==null || dzxxb.getSfzmmc().equals("")){
									  out.println("alert('请填写身份证明名称!');");
					  				  out.println("parent.document.getElementById('sfzmmc').focus();");
					  				  flag = true;
								  }else{
//									  if(dzxxb.getLxdh()==null || dzxxb.getLxdh().equals("")){
//										  out.println("alert('请填写手机号码!');");
//						  				  out.println("parent.document.getElementById('lxdh').focus();");
//						  				  flag = true;
//									  }
								  }
							  }
						  }
					  }
				  }
			  }
			  if(!flag){
				  //车挂初审/复核
				int count = this.jsrdzdaService.getJsrCjInfoCount(dzxxb.getCjxh());
				if(count!=0){
					 List<DzdaJszDaxxbPhoto> photoList = this.jsrdzdaService.getPhotosByxh(dzxxb.getCjxh());
					 if(photoList!=null && photoList.size()>0){			
						 String type = request.getParameter("type");
						 DzdaJszSjzd resultInfo = this.jsrdzdaService.jsrDaFirstCheck(dzxxb, photoList, request,type);//新增
						 int temp = Integer.parseInt(resultInfo.getDmz());
						 if(temp >0){
//							for (DzdaJszDaxxbPhotoLog log : temp) { //去掉修改图片信息，改用图文库
//						           //将dzda_jsz_daxxb_photo的图片信息插入到dzda_jsz_daxxb_photo_log日志表的图片中，因为日志表的图片是空的，必须从dzda_jsz_daxxb_photo取出。
//						     	  byte[] btyeArrayLog = this.jsrdzdaService.getImageLBlob(log.getXh(), "dzda_jsz_daxxb_photo");
//						     	  this.jsrdzdaService. editeBlobByByte(log.getId(), btyeArrayLog, "dzda_jsz_daxxb_photo_log","log");
//						     	  countTemp++;
//							}
//							if(countTemp == temp.size()){
								if(type.equals("cg")){
									 out.println("alert('车管复审成功,邮政编码是:"+resultInfo.getDmms1()+"');");
									 out.println("parent.closeChuli();");
									 out.println("parent.checkPrint('"+resultInfo.getDmms1()+"');");
									 out.println("parent.window.location.href = '"+request.getContextPath()+"/jsrdzda/jsrdzda_initCgCheckJsrList.action';");
								}else{
								  out.println("alert('车管初审成功!');");
								  out.println("parent.window.location.href = '"+request.getContextPath()+"/jsrdzda/jsrdzda_initJsrCjList.action';");
								}
						}
						//}
					 }else{
						 out.println("alert('该驾驶人电子档案图片不存在,请核实后再操作!');");
						 out.println("parent.closeChuli();");
						 // out.println("parent.window.location.href = '"+request.getContextPath()+"/user/user_initJsrCjList.action';");
					 }
				}else{
					out.println("alert('该驾驶人档案信息不存在,请核实后再操作!');");
					out.println("parent.closeChuli();");
				    //out.println("parent.window.location.href = '"+request.getContextPath()+"/user/user_initJsrCjList.action';");
				}
			  }else{
				  out.println("parent.closeChuli();");
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
			estr = estr.replaceAll("\\\\", "/");
			String exceptionstr = "异常信息:" + estr + "</br>文      件名:"
			+ stackTraceElement.getFileName() + "</br>行数:"
			+ stackTraceElement.getLineNumber() + "</br>方法名:"
			+ stackTraceElement.getMethodName();
			out.println("parent.exception('"+exceptionstr+"');");
		}finally{
			out.println("</script>");
			out.flush();
			out.close();
		}		
		return "";
	}
	
	
	/**
	 * 异步获取需要补审的信息
	 * @return
	 * @throws Exception
	 */
	public String addViewOnPhoto() throws Exception {
		 response.setContentType("text/html;charset=UTF-8");
		 response.setHeader("Cache-Control", "no-cache");
		 response.setDateHeader("Expires", 0);
		 PrintWriter out = response.getWriter();
			try {
				DzdaJszDaxxbPhoto  photo = this.jsrdzdaService.getPhotoInfoByxh(request);
				if(photo!=null){
					 out.write(JsonUtil.bean2json(photo));
				}else{
					out.write("");
				}
				out.flush();
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
				DzdaJszDaxxbPhoto sd = new DzdaJszDaxxbPhoto();
				sd.setRemark(exceptionstr);
				out.write(JsonUtil.bean2json(sd));  //将异常信息用bean封装，再以json格式返回。
				out.flush();
			}finally{
				if(out!=null){
					out.close();
				}
			}		
			return null;
	}
	/**
	 * 驾校补审
	 * @return
	 * @throws Exception
	 */
	public String jxBuCheck() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		boolean flag = false;
		out.println("<script>");
		try{
			  if(dzxxb.getYwlx()==null || dzxxb.getYwlx().equals("--")){
				  out.println("alert('请选择业务类型!');");
  				  out.println("parent.document.getElementById('ywlx').focus();");
  				  flag = true;
			  }else{
				  if(dzxxb.getDabh()==null || dzxxb.getDabh().equals("")){
					  out.println("alert('请填写档案编号!');");
	  				  out.println("parent.document.getElementById('dabh').focus();");
	  				  flag = true;
				  }else{
					  if(dzxxb.getXm()==null || dzxxb.getXm().equals("")){
						  out.println("alert('请填写姓名!');");
		  				  out.println("parent.document.getElementById('xm').focus();");
		  				  flag = true;
					  }else{
						  if(dzxxb.getZjcx()==null || dzxxb.getZjcx().equals("--")){
							  out.println("alert('请选择准驾车型!');");
			  				  out.println("parent.document.getElementById('zjcx').focus();");
			  				  flag = true;
						  }else{
							  if(dzxxb.getSfzmhm()==null || dzxxb.getSfzmhm().equals("")){
								  out.println("alert('请填写身份证明号码!');");
				  				  out.println("parent.document.getElementById('sfzmhm').focus();");
				  				  flag = true;
							  } else{
								  if(dzxxb.getSfzmmc()==null || dzxxb.getSfzmmc().equals("")){
									  out.println("alert('请填写身份证明名称!');");
					  				  out.println("parent.document.getElementById('sfzmmc').focus();");
					  				  flag = true;
								  }else{
//									  if(dzxxb.getLxdh()==null || dzxxb.getLxdh().equals("")){
//										  out.println("alert('请填写手机号码!');");
//						  				  out.println("parent.document.getElementById('lxdh').focus();");
//						  				  flag = true;
//									  } else if(dzxxb.getLxdz()==null || dzxxb.getLxdz().equals("")){
//										  out.println("alert('请填写联系地址!');");
//						  				  out.println("parent.document.getElementById('lxdz').focus();");
//						  				  flag = true;
//									  }
								  }
							  }
						  }
					  }
				  }
			  }
			  if(!flag){
				  //驾校做补审
				int count = this.jsrdzdaService.getJsrCjInfoCount(dzxxb.getCjxh());
				if(count!=0){
					 List<DzdaJszDaxxbPhoto> photoList = this.jsrdzdaService.getPhotosByxh(dzxxb.getCjxh());
					 if(photoList!=null && photoList.size()>0){						 
						List<DzdaJszDaxxbPhotoLog> temp = this.jsrdzdaService.jsrDaBuCheck(dzxxb, photoList, request);
//						int countTemp=0;
						if(temp.size() >0){
//							for (DzdaJszDaxxbPhotoLog log : temp) {
//						           //将dzda_jsz_daxxb_photo的图片信息插入到dzda_jsz_daxxb_photo_log日志表的图片中，因为日志表的图片是空的，必须从dzda_jsz_daxxb_photo取出。
//						     	  byte[] btyeArrayLog = this.jsrdzdaService.getImageLBlob(log.getXh(), "dzda_jsz_daxxb_photo");
//						     	  this.jsrdzdaService. editeBlobByByte(log.getId(), btyeArrayLog, "dzda_jsz_daxxb_photo_log","log");
//						     	  countTemp++;
//							}
//							if(countTemp == temp.size()){
								 out.println("alert('驾校补审成功!');");
								 out.println("parent.window.location.href = '"+request.getContextPath()+"/jsrdzda/jsrdzda_initJsrCjList.action';");
							}else{
								 out.println("alert('驾校补审失败!');");
								 out.println("parent.closeChuli();");
							}
//						}
					 }else{
						 out.println("alert('该驾驶人电子档案图片不存在,请核实后再操作!');");
						 out.println("parent.closeChuli();");
						 // out.println("parent.window.location.href = '"+request.getContextPath()+"/jsrdzda/jsrdzda_initJsrCjList.action';");
					 }
				}else{
					out.println("alert('该驾驶人档案信息不存在,请核实后再操作!');");
					out.println("parent.closeChuli();");
				    //out.println("parent.window.location.href = '"+request.getContextPath()+"/jsrdzda/jsrdzda_initJsrCjList.action';");
				}
			  }else{
				  out.println("parent.closeChuli();");
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
			estr = estr.replaceAll("\\\\", "/");
			String exceptionstr = "异常信息:" + estr + "</br>文件名:"
			+ stackTraceElement.getFileName() + "</br>行数:"
			+ stackTraceElement.getLineNumber() + "</br>方法名:"
			+ stackTraceElement.getMethodName();
			out.println("parent.exception('"+exceptionstr+"');");
		}finally{
			out.println("</script>");
			out.flush();
			out.close();
		}		
		return "";
	}
		
	
	/***********************************车管所审核信息*****************************/

	
	/**
	 *进入复核初始化时， 解锁当前用户下的待复审的数据
	 */
	public String initCgCheckJsrList() throws Exception {
		this.jsrdzdaService.updateLockByCurrentUser(request,"recheck");
		this.setJsrCjList(this.jsrdzdaService.getcgCheckList(request,"select")); 
		return "cgJsrList";
	}
	
	/*
	 * 复核中点击新增按钮后，锁定数据或查询数据
	 */
	public String addCurrentLockByRecheck() throws Exception {		
		String btnType = request.getParameter("btnType");
		if(btnType==null){
			   this.jsrdzdaService.lockRecoredByUser(request,"recheck");//锁定15条复核数据
		}
	    this.setJsrCjList(this.jsrdzdaService.getcgCheckList(request,"add"));
		return "cgJsrList";
	}
	
	
	/**
	 * 写入 生成一维码图片页面初始化
	 * @return
	 * @throws Exception
	 */
	public String initPrint() throws Exception {
		this.setDzxxb(this.jsrdzdaService.getDzdaJszInfo(request.getParameter("yzbm")));	
		return "printYzbm";
	}
	
	
	/**
	 * 档案卷宗查询
	 * @return
	 * @throws Exception
	 */
	public String initQueryInfo() throws Exception {		
		try{
			String temp = request.getParameter("temp");
			if(temp!=null && !temp.equals("")){
				 String sfzmhm = request.getParameter("sfzmhm");
				 String dabh = request.getParameter("dabh");
				if((sfzmhm!=null && !sfzmhm.equals("")) || (dabh!=null && !dabh.equals(""))){
					this.setEsDrv(this.jsrdzdaService.getJsrBasicInfo(request));
				}		
			}
		}catch(Exception se){
			se.printStackTrace();
		}
		return "findInfo";
	}
	
	/**
	 * 测试打印水印信息在图片上
	 * @return
	 * @throws Exception
	 */
	public String showImage() throws Exception {	
		ServletOutputStream sos =null;
			response.setContentType("image/jpeg");	
//			try {
//				//测试水印打印换行 页面：     <img style="width:662px; height:885px;" src="<%=request.getContextPath()%>/jsrdzda/jsrdzda_showImage.action?xh=14" />
//			 byte[] byte_array=this.jsrdzdaService.createBazPic("公安交通管理综合应用平台违法辅助系统改造工作，目前已完成了接入平台的搭建","14","dzda_jsz_daxxb_photo");	
//				// 生成jpeg图片
//				if(null != byte_array){
//					sos= response.getOutputStream();
//					if(byte_array.length>0){
//						for (int i = 0; i < byte_array.length; i++) {
//							sos.write(byte_array[i]);
//						}
//						sos.flush();
//						sos.close(); 
//					}			
//			   response.flushBuffer();
//				byte_array = null;
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally{
//				if(sos!=null){
//					sos.close();
//				}
//			}
			 return null;
		}	  
	
	
	/**
	 * 用户退出
	 * @return null
	 * @throws Exception
	 */
	public String logout() throws Exception{
		request.getSession().removeAttribute(SysConst.USERBEAN);
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return null;
	}
	
	// 进入密码修改页面
	public String initUpdatePwd() throws Exception {
		return "upwd";
	}
	
	
//	/**
//	 * 修改密码
//	 * @return false
//	 * @throws Exception
//	 */
//	public String updatePwd() throws Exception{
//		String pwdnew = request.getParameter("confirm_spwd");
//		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
//		user.setPassword(pwdnew);
//		try {
//			this.setReturnInteger(jsrdzdaService.insertOrUpdateUser(user, request));
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//			PrintWriter out = response.getWriter();
//			out.println(returnInteger);
//			out.flush();
//			out.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error(e);
//			response.getWriter().print(1);
//		}
//		return null;
//	}
	
	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public JsrdzdaService getJsrdzdaService() {
		return jsrdzdaService;
	}
	public void setJsrdzdaService(JsrdzdaService jsrdzdaService) {
		this.jsrdzdaService = jsrdzdaService;
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
	public List<DzdaJszDaxxb> getJsrCjList() {
		return jsrCjList;
	}
	public void setJsrCjList(List<DzdaJszDaxxb> jsrCjList) {
		this.jsrCjList = jsrCjList;
	}

	public DzdaJszDaxxb getDzxxb() {
		return dzxxb;
	}

	public void setDzxxb(DzdaJszDaxxb dzxxb) {
		this.dzxxb = dzxxb;
	}

	public Map<String, String> getYwlxMap() {
		return ywlxMap;
	}

	public void setYwlxMap(Map<String, String> ywlxMap) {
		this.ywlxMap = ywlxMap;
	}

	public Map<String, String> getYwlxAMap() {
		return ywlxAMap;
	}

	public void setYwlxAMap(Map<String, String> ywlxAMap) {
		this.ywlxAMap = ywlxAMap;
	}

	public List<DzdaJszDaxxbPhoto> getPhotos() {
		return photos;
	}

	public void setPhotos(List<DzdaJszDaxxbPhoto> photos) {
		this.photos = photos;
	}
	public Map<String, String> getZjcxMap() {
		return zjcxMap;
	}
	public void setZjcxMap(Map<String, String> zjcxMap) {
		this.zjcxMap = zjcxMap;
	}
	public List<DzdaJszSjzd> getTpTbyyMap() {
		return tpTbyyMap;
	}
	public void setTpTbyyMap(List<DzdaJszSjzd> tpTbyyMap) {
		this.tpTbyyMap = tpTbyyMap;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public EsDrvLicense getEsDrv() {
		return esDrv;
	}

	public void setEsDrv(EsDrvLicense esDrv) {
		this.esDrv = esDrv;
	}
	
}
