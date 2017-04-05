package com.ycszh.ssh.action.sfrz;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.rsc.RscDby;
import com.ycszh.ssh.hbm.rsc.RscDbyZjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzCjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzUserinfo;
import com.ycszh.ssh.service.sfrz.SfrzService;
import com.ycszh.util.JsonUtil;


@SuppressWarnings("serial")
public class SfrzAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(SfrzAction.class);
	
	private SfrzService sfrzService;
	private String result;
	private int currentpage = 1;
	private String inputPath;
	private Integer returnInteger = 1;	
	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	private SfrzCjxxb sfrzCjxxb;
	private SfrzUserinfo sfrzUserinfo;
	public SfrzUserinfo getSfrzUserinfo() {
		return sfrzUserinfo;
	}

	public void setSfrzUserinfo(SfrzUserinfo sfrzUserinfo) {
		this.sfrzUserinfo = sfrzUserinfo;
	}

	private List<SfrzCjxxb> sfrzCjxxbList = new ArrayList<SfrzCjxxb>();
	
	/**
	 * 上传文件实体对象
	 */
	private File file1;
	private File file2;
	private File file01;
	private File file02;
	private File file03;
	private File file04;
	private File file05;
	private File file06;
	private File file07;
	private File file08;
	private File file09;

	/**
	 * 上传文件类型
	 */
	private String file1ContentType;
	private String file2ContentType;
	private String file01ContentType;
	private String file02ContentType;
	private String file03ContentType;
	private String file04ContentType;
	private String file05ContentType;
	private String file06ContentType;
	private String file07ContentType;
	private String file08ContentType;
	private String file09ContentType;
	

	/**
	 * 上传文件名
	 */
	private String file1FileName;
	private String file2FileName;
	private String file01FileName;
	private String file02FileName;
	private String file03FileName;
	private String file04FileName;
	private String file05FileName;
	private String file06FileName;
	private String file07FileName;
	private String file08FileName;
	private String file09FileName;
	
	/**
	 * 初始化编辑页面
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String initEditPage() throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		Map gjlist = sfrzService.getCode("31");
		request.setAttribute("gjlist", gjlist);
		request.setAttribute("editType", "采集");
		request.setAttribute("users", user);
		return "insert";
	}
	
	/**
	 * 初始化查看页面
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String initView() throws Exception {
		if (sfrzCjxxb.getCid() != null) {
			this.setSfrzCjxxb(sfrzService.getSfrzCjxxbInfo(sfrzCjxxb.getCid()));
			if (this.getSfrzCjxxb() != null) {
				Map gjlist = sfrzService.getCode("31");
				Map photolist = sfrzService.getPhoto(sfrzCjxxb.getCid());
				request.setAttribute("gjlist", gjlist);
				request.setAttribute("photolist", photolist);
				request.setAttribute("editType", "查看");
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该身份认证信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该身份认证信息!");
			return "Exception";
		}
	}
	/**
	 * 初始化查看页面
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String initdbyView() throws Exception {
		System.out.println(sfrzCjxxb.getCid()+"===============");
		String cid=sfrzCjxxb.getCid().trim();
		if (cid!= null) {
			this.setSfrzCjxxb(sfrzService.getSfrzCjxxbInfo(cid));
			if (this.getSfrzCjxxb() != null) {
				String xh=request.getParameter("xh");
				
				Map gjlist = sfrzService.getCode("31");
				Map photolist = sfrzService.getPhoto(cid);
				request.setAttribute("gjlist", gjlist);
				request.setAttribute("photolist", photolist);
				request.setAttribute("editType", "审核");
				request.setAttribute("xh", xh);
				return "dbyview";
			} else {
				request.setAttribute("errorTip", "没有该代办员认证信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该代办员认证信息!");
			return "Exception";
		}
	}
	
	/**
	 * 审核驾驶证补换证信息
	 * @return
	 * @throws Exception 
	 */
	public String updateDby() throws Exception {
		String cid = request.getParameter("cid");
		String dbyzt = request.getParameter("dbyzt");
		String tbyy = request.getParameter("tbyy");
		if(null != cid){
			response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			try {
				this.setReturnInteger(sfrzService.updateDby(request, cid, dbyzt, tbyy));
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println(returnInteger);
				out.flush();
				out.close();
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
	
	/**
	 * 初始化列表页面
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String initSfrzCjxxbList() throws Exception {
		try {
			this.setSfrzCjxxbList(this.sfrzService.getSfrzCjxxbList(request, currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "list";
	}
	
	public String getSfrzCjxxbInfo() throws Exception {
		try {
			String cid = request.getParameter("cid");
			SfrzCjxxb sc = this.sfrzService.getSfrzCjxxb(cid);
			if(null != sc){
				String jsonString = JsonUtil.object2json(sc);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getVehList() throws Exception {
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			List vehlist = this.sfrzService.getVeglist(sfzmhm);
			//request.getSession().setAttribute("vehlist", vehlist);
			if(null != vehlist){
				String jsonString = JsonUtil.list2json(vehlist);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	public String getsfshList() throws Exception {
		try {
			String sfzmhm = request.getParameter("sfzhm");
			String mz = request.getParameter("mz");
			String restrs = this.sfrzService.getsfshList(sfzmhm,mz);
			if(null != restrs){
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(restrs);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getDrvList() throws Exception {
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			List drvlist = this.sfrzService.getDrvList(sfzmhm);
			//request.getSession().setAttribute("drvlist", drvlist);
			if(null != drvlist){
				String jsonString = JsonUtil.list2json(drvlist);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public String getXphzList() throws Exception {
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			List xphzlist = this.sfrzService.getXphzList(sfzmhm);
			if(null != xphzlist){
				String jsonString = JsonUtil.list2json(xphzlist);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			
		}
		return null;
	}
	
	/**
	 * 编辑
	 * @return
	 * @throws Exception
	 */
	public String editSfrzCjxxb() throws Exception{ 
		if(sfrzCjxxb != null){
			String ccid=request.getParameter("ccid");
			System.out.println("===========1155===="+ccid);
			if(ccid!=null&&""!=ccid){
				sfrzCjxxb.setCid(ccid);
			}
			String cid = sfrzCjxxb.getCid();
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				//添加信息
				Map<String,String> resultMap = this.sfrzService.insertOrUpdateSfrzCjxxb(sfrzCjxxb, file1, file2, file01, file02, file03, file04, file05, file06,file07,file08,file09, request);
				if ("0000".equals(resultMap.get("code"))) {
					out.println("parent.closechuli();");
					if (null != cid && !"".equals(cid)) {
						out.println("alert('"+resultMap.get("msg")+"');");
						out.println("parent.window.location.href = '"
								+ request.getContextPath()
								+ "/sfrz/sfrz_initEditPage.action';");
					} else {
						//out.println("if(window.confirm('采集成功!是否要马上打印采集信息?')){window.open('print.action?slgDrvXxcjb.cjid="+slgDrvXxcjb.getCjid()+"');}");
						out.println("alert('"+resultMap.get("msg")+"');");
						out.println("parent.initform();");
						out.println("parent.window.location.href = '"
								+ request.getContextPath()
								+ "/sfrz/sfrz_initEditPage.action';");
					}
				}else{
					out.println("parent.closechuli();");
					out.println("alert('"+resultMap.get("msg")+"')");
				}
			} catch (Exception e) {
				out.println("parent.closechuli();");
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
				}
				out.println("parent.exception('" + exceptionstr + "');");
			}
			out.println("</script>");
			return null;
		}else{
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	@SuppressWarnings("unchecked")
	public String initPrint() throws Exception{
		String sfzmhm = "";
		if(request.getParameter("sfzmhm")!=null && !request.getParameter("sfzmhm").equals("")){
			sfzmhm=request.getParameter("sfzmhm");
			sfzmhm=URLDecoder.decode(sfzmhm,"utf-8");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		String sfzmmc = "";
		if(request.getParameter("sfzmmc")!=null && !request.getParameter("sfzmmc").equals("")){
			sfzmmc=request.getParameter("sfzmmc");
			sfzmmc=URLDecoder.decode(sfzmmc,"utf-8");
			sfzmmc = sfrzService.getCodeVal("19", sfzmmc);
			request.setAttribute("sfzmmc", sfzmmc);
		}
		String xm = "";
		if(request.getParameter("xm")!=null && !request.getParameter("xm").equals("")){
			xm=request.getParameter("xm");
			xm=URLDecoder.decode(xm,"utf-8");
			request.setAttribute("xm", xm);
		}
		String xb = "";
		if(request.getParameter("xb")!=null && !request.getParameter("xb").equals("")){
			xb=request.getParameter("xb");
			xb=URLDecoder.decode(xb,"utf-8");
			if("1".equals(xb)){
				xb = "男";
			}else if("2".equals(xb)){
				xb = "女";
			}
			request.setAttribute("xb", xb);
		}
		String gj = "";
		if(request.getParameter("gj")!=null && !request.getParameter("gj").equals("")){
			gj=request.getParameter("gj");
			gj=URLDecoder.decode(gj,"utf-8");
			gj = sfrzService.getCodeVal("31", gj);
			request.setAttribute("gj", gj);
		}
		String mz = "";
		if(request.getParameter("mz")!=null && !request.getParameter("mz").equals("")){
			mz=request.getParameter("mz");
			mz=URLDecoder.decode(mz,"utf-8");
			request.setAttribute("mz", mz);
		}
		String jg = "";
		if(request.getParameter("jg")!=null && !request.getParameter("jg").equals("")){
			jg=request.getParameter("jg");
			jg=URLDecoder.decode(jg,"utf-8");
			request.setAttribute("jg", jg);
		}
		String yddh = "";
		if(request.getParameter("yddh")!=null && !request.getParameter("yddh").equals("")){
			yddh=request.getParameter("yddh");
			yddh=URLDecoder.decode(yddh,"utf-8");
			request.setAttribute("yddh", yddh);
		}
		String txdz = "";
		if(request.getParameter("txdz")!=null && !request.getParameter("txdz").equals("")){
			txdz=request.getParameter("txdz");
			txdz=URLDecoder.decode(txdz,"utf-8");
			request.setAttribute("txdz", txdz);
		}
		String dzyx = "";
		if(request.getParameter("dzyx")!=null && !request.getParameter("dzyx").equals("")){
			dzyx=request.getParameter("dzyx");
			dzyx=URLDecoder.decode(dzyx,"utf-8");
			request.setAttribute("dzyx", dzyx);
		}
		
		String gddh = "";
		if(request.getParameter("gddh")!=null && !request.getParameter("gddh").equals("")){
			gddh=request.getParameter("gddh");
			gddh=URLDecoder.decode(gddh,"utf-8");
			request.setAttribute("gddh", gddh);
		}
		String wx = "";
		if(request.getParameter("wx")!=null && !request.getParameter("wx").equals("")){
			wx=request.getParameter("wx");
			wx=URLDecoder.decode(wx,"utf-8");
			request.setAttribute("wx", wx);
		}
		String wb = "";
		if(request.getParameter("wb")!=null && !request.getParameter("wb").equals("")){
			wb=request.getParameter("wb");
			wb=URLDecoder.decode(wb,"utf-8");
			request.setAttribute("wb", wb);
		}
		String qq = "";
		if(request.getParameter("qq")!=null && !request.getParameter("qq").equals("")){
			qq=request.getParameter("qq");
			qq=URLDecoder.decode(qq,"utf-8");
			request.setAttribute("qq", qq);
		}
		List vehlist = (List)request.getSession().getAttribute("vehlist");
		List drvlist = (List)request.getSession().getAttribute("drvlist");
		request.setAttribute("vehlist", vehlist);
		request.setAttribute("drvlist", drvlist);
		
		return "print";
	}
	@SuppressWarnings("unchecked")
	public String getUserInfo() throws Exception{
		try {
			String rejsval = request.getParameter("rejsval");
			String loginUser = request.getParameter("loginUser");
			List likeList=this.sfrzService.getUserInfo(loginUser,rejsval);
			if(null != likeList){
				String jsonString = JsonUtil.list2json(likeList);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
		
	}
	@SuppressWarnings("unchecked")
	public String getdbrList() throws Exception {
		try {
			String rzjs = request.getParameter("rzjs");
			String sfzmhm = request.getParameter("sfzmhm");
			List cdbrlist = this.sfrzService.getdbrList(sfzmhm,rzjs);
			if(null != cdbrlist){
				String jsonString = JsonUtil.list2json(cdbrlist);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	// 下载控件驱动
	public InputStream getRarFile() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	
	//验证认证角色前四位在sfrz_userinfo表是否有数据
	public String getSfrzA() throws Exception {
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			System.out.println(sfzmhm);
			SfrzUserinfo userInfo = this.sfrzService.getSfrzA(sfzmhm);
			if(null!=userInfo){
				String jsonString = JsonUtil.object2json(userInfo);
				System.out.println(jsonString);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	//根据身份证明号码在Rsc_Dby表中查询是否有数据
	public String getRscDby() throws Exception{
		try {
			String sfzmhm = request.getParameter("sfzmhms");
			RscDby dby = this.sfrzService.getRscDby(sfzmhm);
			if(null!=dby){
				String jsonString = JsonUtil.object2json(dby);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	//根据身份证明号码在Rsc_Dby_zjxxb表中查询是否有数据
	public String getRscDbyZjxxb() throws Exception{
		try {
			String sfzmhm = request.getParameter("sfzmhm");
			RscDbyZjxxb zjxxb = this.sfrzService.getRscDbyZjxxb(sfzmhm);
			//request.getSession().setAttribute("vehlist", vehlist);
			if(null!=zjxxb){
				String jsonString = JsonUtil.object2json(zjxxb);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return null;
	}
	
	/*
	 * 读卡根据sfzmhm验证是否非法代办
	 */
	public String getDbyList() throws Exception{
		try {
			this.sfrzService.getDbyList(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return "dbylist";
	}
	
	//查询代办员列表
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public File getFile2() {
		return file2;
	}
	public void setFile2(File file2) {
		this.file2 = file2;
	}
	public File getFile01() {
		return file01;
	}
	public void setFile01(File file01) {
		this.file01 = file01;
	}
	public File getFile02() {
		return file02;
	}
	public void setFile02(File file02) {
		this.file02 = file02;
	}
	public String getFile1ContentType() {
		return file1ContentType;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	public String getFile2ContentType() {
		return file2ContentType;
	}
	public void setFile2ContentType(String file2ContentType) {
		this.file2ContentType = file2ContentType;
	}
	public String getFile01ContentType() {
		return file01ContentType;
	}
	public void setFile01ContentType(String file01ContentType) {
		this.file01ContentType = file01ContentType;
	}
	public String getFile02ContentType() {
		return file02ContentType;
	}
	public void setFile02ContentType(String file02ContentType) {
		this.file02ContentType = file02ContentType;
	}
	public String getFile1FileName() {
		return file1FileName;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public String getFile2FileName() {
		return file2FileName;
	}
	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}
	public String getFile01FileName() {
		return file01FileName;
	}
	public void setFile01FileName(String file01FileName) {
		this.file01FileName = file01FileName;
	}
	public String getFile02FileName() {
		return file02FileName;
	}
	public void setFile02FileName(String file02FileName) {
		this.file02FileName = file02FileName;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public File getFile03() {
		return file03;
	}

	public void setFile03(File file03) {
		this.file03 = file03;
	}

	public File getFile04() {
		return file04;
	}

	public void setFile04(File file04) {
		this.file04 = file04;
	}

	public File getFile05() {
		return file05;
	}

	public void setFile05(File file05) {
		this.file05 = file05;
	}

	public File getFile06() {
		return file06;
	}

	public void setFile06(File file06) {
		this.file06 = file06;
	}

	public String getFile03ContentType() {
		return file03ContentType;
	}

	public void setFile03ContentType(String file03ContentType) {
		this.file03ContentType = file03ContentType;
	}

	public String getFile04ContentType() {
		return file04ContentType;
	}

	public void setFile04ContentType(String file04ContentType) {
		this.file04ContentType = file04ContentType;
	}

	public String getFile05ContentType() {
		return file05ContentType;
	}

	public void setFile05ContentType(String file05ContentType) {
		this.file05ContentType = file05ContentType;
	}

	public String getFile06ContentType() {
		return file06ContentType;
	}

	public void setFile06ContentType(String file06ContentType) {
		this.file06ContentType = file06ContentType;
	}

	public String getFile03FileName() {
		return file03FileName;
	}

	public void setFile03FileName(String file03FileName) {
		this.file03FileName = file03FileName;
	}

	public String getFile04FileName() {
		return file04FileName;
	}

	public void setFile04FileName(String file04FileName) {
		this.file04FileName = file04FileName;
	}

	public String getFile05FileName() {
		return file05FileName;
	}

	public void setFile05FileName(String file05FileName) {
		this.file05FileName = file05FileName;
	}

	public String getFile06FileName() {
		return file06FileName;
	}

	public void setFile06FileName(String file06FileName) {
		this.file06FileName = file06FileName;
	}

	public SfrzService getSfrzService() {
		return sfrzService;
	}

	public void setSfrzService(SfrzService sfrzService) {
		this.sfrzService = sfrzService;
	}

	public SfrzCjxxb getSfrzCjxxb() {
		return sfrzCjxxb;
	}

	public void setSfrzCjxxb(SfrzCjxxb sfrzCjxxb) {
		this.sfrzCjxxb = sfrzCjxxb;
	}

	public List<SfrzCjxxb> getSfrzCjxxbList() {
		return sfrzCjxxbList;
	}

	public void setSfrzCjxxbList(List<SfrzCjxxb> sfrzCjxxbList) {
		this.sfrzCjxxbList = sfrzCjxxbList;
	}

	public static Logger getLogger() {
		return logger;
	}

	public File getFile07() {
		return file07;
	}

	public void setFile07(File file07) {
		this.file07 = file07;
	}

	public File getFile08() {
		return file08;
	}

	public void setFile08(File file08) {
		this.file08 = file08;
	}

	public File getFile09() {
		return file09;
	}

	public void setFile09(File file09) {
		this.file09 = file09;
	}

	public String getFile07ContentType() {
		return file07ContentType;
	}

	public void setFile07ContentType(String file07ContentType) {
		this.file07ContentType = file07ContentType;
	}

	public String getFile08ContentType() {
		return file08ContentType;
	}

	public void setFile08ContentType(String file08ContentType) {
		this.file08ContentType = file08ContentType;
	}

	public String getFile09ContentType() {
		return file09ContentType;
	}

	public void setFile09ContentType(String file09ContentType) {
		this.file09ContentType = file09ContentType;
	}

	public String getFile07FileName() {
		return file07FileName;
	}

	public void setFile07FileName(String file07FileName) {
		this.file07FileName = file07FileName;
	}

	public String getFile08FileName() {
		return file08FileName;
	}

	public void setFile08FileName(String file08FileName) {
		this.file08FileName = file08FileName;
	}

	public String getFile09FileName() {
		return file09FileName;
	}

	public void setFile09FileName(String file09FileName) {
		this.file09FileName = file09FileName;
	}

}
