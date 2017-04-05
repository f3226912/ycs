package com.ycszh.ssh.action.dydj;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletOutputStream;
import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.dydj.DydjUser;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;
import com.ycszh.ssh.hbm.dydj.DydjYhdbr;
import com.ycszh.ssh.hbm.ydwt.WtydnsYwsbspb;
import com.ycszh.ssh.service.dydj.DydjYwsbspbService;
import com.ycszh.util.StringUtil;

/**
 * 抵押登记业务管理
 * @author Administrator
 * @since 2013-11-20
 *
 */
public class DydjManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = Logger.getLogger(DydjManageAction.class);
	
	public List<WtydnsYwsbspb> spbList; //查询出的数据
	
	private DydjYwsbspbService dydjYwsbspbService;
		
	private DydjUser dydjUser ;
	
	private DydjYhUser yhUser;
	
	private DydjYhdbr yhDbr;
	
	private List<DydjUser> userList;
	
	private List<DydjYhUser> yhUserList;	
	
	private int currentpage = 1;
	
	private Integer returnInteger = 1;
	
	private List list;
	
	/**
	 * 上传文件实体对象
	 */
	private File file;
	/**
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/**
	 * ( 车管、邮政)用户管理
	 * @return
	 * @throws Exception
	 */
	public String getUsersList() throws Exception {
		this.setUserList(this.dydjYwsbspbService.getUserList(request, currentpage));
		return "userList";
	}
	/**
	 * 初始化 (进入添加页面)
	 * @return
	 */
	public String initUser() throws Exception {
		this.setList(this.dydjYwsbspbService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", ""));
		return "initAdd";
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
		if (dydjUser != null) {
			try{
			    int returnInteger =this.dydjYwsbspbService.addUserInfo(request, dydjUser);
				if (returnInteger ==1) {
					out.println("alert('添加成功!');");
					out.println("parent.window.location.href = '"+request.getContextPath()+"/dydj/dydj_getUsersList.action';");
				} else if (returnInteger ==2) {
					out.println("alert('该用户代码已存在!');");
			    	out.println("parent.document.getElementById('yzYhdm').style.borderColor = '#FF0000';");
			    	out.println("parent.document.getElementById('yzYhdm').focus();");
				}else{
					out.println("alert('添加失败!')");
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

	/**
	 * 冻结解冻用户信息
	 * @return
	 * @throws Exception
	 */
	public String freezeUser() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		if(null != id){
			try {
				this.setReturnInteger(dydjYwsbspbService.freezeUser(request,Integer.parseInt(id)));
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
		}else{
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";	
		}
	}
	/**
	 * 重置密码
	 */
	public String reSetPwd() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		 String id = request.getParameter("id");
		if(null!= id){
			try {
				this.setReturnInteger(dydjYwsbspbService.resetPwd(id, request));
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
	 * 清空mac地址(银行用户 )
	 */
	public String clearnMac() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		if(null!= id){
			try {
				this.setReturnInteger(dydjYwsbspbService.clearnMac(id, request));
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
	 * ( 银行)用户管理
	 * @return
	 * @throws Exception
	 */
	public String getYHUsersList() throws Exception {
		this.setYhUserList(this.dydjYwsbspbService.getYHUserList(request, currentpage));
		return "yhUserList";
	}
	
	/**
	 * 进入添加银行用户
	 * @return
	 * @throws Exception
	 */
	public String initYHUser() throws Exception {
		request.setAttribute("editType", "添加");
		return "initYhAdd";
	}
	public String getYhUserById() throws Exception {
		request.setAttribute("editType", "查看");
		this.setYhUser(this.dydjYwsbspbService.getDydjYhUser(request));
		return "initYhAdd";
	}
	//初始化修改
	public String getInitEditById() throws Exception {
		request.setAttribute("editType", "修改");
		this.setYhUser(this.dydjYwsbspbService.getDydjYhUser(request));
		return "initYhAdd";
	}
	/**
	 * 添加银行用户
	 * @return
	 * @throws Exception
	 */
	public String addYHUser() throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if (yhUser != null) {
			try{
				String editType = request.getParameter("editType");
				if("修改".equals(editType.trim())){					
					if(file!=null){	
						if(file.exists() && file.isFile()){
							//在hibernate进行映射修改数据对象之前，将blob字段取出，不然会被修改为null,页面展示就会错误图片
							 DydjYhUser yhusr=this.dydjYwsbspbService.editYhUser(request, yhUser);
							 returnInteger= this.dydjYwsbspbService.editPhoto(yhusr,file);
						}else{
							 out.println("alert('该路径不存在或文件不存在!');");
						}
			    	 }else{
			    		 byte[] byte_array = this.dydjYwsbspbService.getImageBlob(request, yhUser.getId());
			    		 DydjYhUser yhusr=this.dydjYwsbspbService.editYhUser(request, yhUser);
			    		 returnInteger=this.dydjYwsbspbService.editeBlobByByte(byte_array, yhusr);
			    	 }				
					if (returnInteger >0) {
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/dydj/dydj_getYHUsersList.action';");
					} else {
						out.println("alert('修改失败!')");
					}		
				}else{					
			    	int returnInteger =0;
			    	if(file==null){			    		
			    		String path = "/"+request.getRealPath("/")+"\\pages\\dydj\\officeSeal.jpg";
			    		file = new File(path);
			    	 }
			    	 if(!file.exists() && !file.isFile()){
		    			 out.println("alert('该路径不存在或文件不存在!');");
		    		 }else{
		    			 List<DydjYhUser> user =this.dydjYwsbspbService.getYhUser(request, yhUser);
					     if(user.isEmpty()){ //表示可以新增
					    	 DydjYhUser addUser = this.dydjYwsbspbService.addYhUser(request, yhUser);
					    	 returnInteger= this.dydjYwsbspbService.editPhoto(addUser, file);
							 if (returnInteger >0) {
							  	out.println("alert('添加成功!');");
								out.println("parent.window.location.href = '"+request.getContextPath()+"/dydj/dydj_getYHUsersList.action';");
							 } else {
								out.println("alert('添加失败!')");
							 }
					     }else{//表示该银行代码已存在
					    	out.println("alert('该银行代码已存在!');");
					    	out.println("parent.document.getElementById('yzYhdm').style.borderColor = '#FF0000';");
					    	out.println("parent.document.getElementById('yzYhdm').focus();");
					     }					    						    			 
		    		 }
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
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	/**
	 * 显示图片
	 * @throws Exception
	 */
	public void showImage() throws Exception{
		String img = request.getParameter("id");
		if(!StringUtil.isNull(img)){
			Integer id = Integer.parseInt(img);
			byte[] byte_array = this.dydjYwsbspbService.getImageBlob(request, id);			
			try {
				if(null != byte_array){
					//int length = (int) blob.length();
					//byte[] byte_array = blob.getBytes(1, length);
					response.setContentType("image/jpeg");
					ServletOutputStream sos = response.getOutputStream();
					for (int i = 0; i < byte_array.length; i++) {
						sos.write(byte_array[i]);
					}
					sos.close();
					byte_array = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(byte_array != null){
					byte_array = null;
				}
			}
		}
	}
	
	
	/**
	 * 查询银行代办人list
	 * @return
	 * @throws Exception
	 */
	public String getDydjdbrList() throws Exception{
		logger.info("DydjManageAction method getDydjdbrList.....");
		try {
			this.dydjYwsbspbService.getYhdbrList(request);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "dydjYhdbrList";
	}
	
	/**
	 * 初始化编辑银行代办人
	 * @return
	 * @throws Exception
	 */
	public String initEditYhdbr() throws Exception{
		logger.info("DydjManageAction method initEditYhdbr.....");
		String yhUserId = request.getParameter("yhUserId");
		String type = request.getParameter("type");
		if("update".equals(type)){
			DydjYhdbr yhDbr = this.dydjYwsbspbService.getDydjYhdbrInfo(request);
			request.setAttribute("yhDbr", yhDbr);
			request.setAttribute("cztype", "修改");
		}else{
			request.setAttribute("cztype", "新增");
		}
		request.setAttribute("yhUserId", yhUserId);
		return "editYhdbr";
	}
	
	/**
	 * 编辑银行代办人信息
	 * @return -1: 信息提交不完整
	 * @throws Exception
	 */
	public String editDydjYhdbrInfo() throws Exception{
		logger.info("DydjManageAction method editDydjYhdbrInfo.....");
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out = response.getWriter();
		if(yhDbr != null){
			try {
				Integer result = this.dydjYwsbspbService.addYhdbrInfo(request, yhDbr);
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
			out.println("-1");
			if(out != null){
				out.flush();
				out.close();
			}
		}
		return null;
	}
	
	/**
	 * 删除银行代办人信息
	 * @return
	 * @throws Exception
	 */
	public String delYhdbrInfo() throws Exception{
		logger.info("DydjManageAction method delYhdbrInfo.....");
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out = response.getWriter();
		try {
			String id = request.getParameter("id");
			Integer result = this.dydjYwsbspbService.delYhdbrInfo(request, id);
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
	
	/**
	 * 修改银行代办人员有效性
	 * @return
	 * @throws Exception
	 */
	public String updateDydjDbrInfo() throws Exception{
		logger.info("DydjManageAction method updateDydjDbrInfo.....");
		PrintWriter out = null;
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		out = response.getWriter();
		try {
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			Integer result = this.dydjYwsbspbService.updateYhdbrInfo(request, id, flag);
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
	
	public List<WtydnsYwsbspb> getSpbList() {
		return spbList;
	}
	public void setSpbList(List<WtydnsYwsbspb> spbList) {
		this.spbList = spbList;
	}
	
	public DydjYwsbspbService getDydjYwsbspbService() {
		return dydjYwsbspbService;
	}

	public void setDydjYwsbspbService(DydjYwsbspbService dydjYwsbspbService) {
		this.dydjYwsbspbService = dydjYwsbspbService;
	}
	
	public DydjUser getDydjUser() {
		return dydjUser;
	}

	public void setDydjUser(DydjUser dydjUser) {
		this.dydjUser = dydjUser;
	}

	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public List<DydjUser> getUserList() {
		return userList;
	}
	public void setUserList(List<DydjUser> userList) {
		this.userList = userList;
	}
	public Integer getReturnInteger() {
		return returnInteger;
	}
	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public List<DydjYhUser> getYhUserList() {
		return yhUserList;
	}

	public void setYhUserList(List<DydjYhUser> yhUserList) {
		this.yhUserList = yhUserList;
	}

	public DydjYhUser getYhUser() {
		return yhUser;
	}

	public void setYhUser(DydjYhUser yhUser) {
		this.yhUser = yhUser;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public DydjYhdbr getYhDbr() {
		return yhDbr;
	}

	public void setYhDbr(DydjYhdbr yhDbr) {
		this.yhDbr = yhDbr;
	}
	
	
}
