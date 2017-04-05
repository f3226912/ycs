package com.ycszh.ssh.action.drv;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgSpxx;
import com.ycszh.ssh.service.drv.SlgSpxxService;
import common.Logger;

@SuppressWarnings("serial")
public class SlgSpxxAction extends BaseAction {
	
	private SlgSpxxService slgService;
	private static final Logger logger = Logger.getLogger(SlgSpxxAction.class);
	private List<SlgSpxx> slgSpxxList = new ArrayList<SlgSpxx>();
	private int currentpage = 1;
	private SlgSpxx slgSpxx;
	private Integer result = null;

	public SlgSpxxService getSlgService() {
		return slgService;
	}

	public void setSlgService(SlgSpxxService slgService) {
		this.slgService = slgService;
	}
	
	public String initSlgSpxxList() throws Exception{
		logger.info("SlgSpxxAction method initSlgSpxxList....");
		this.setSlgSpxxList(this.slgService.getSlgSpxxList(request, currentpage));
		return "list";
	}
	public String editSlgSpxx() throws Exception{
		logger.info("SlgSpxxAction method editSlgSpxx....");
		if(slgSpxx != null){
			Long slgSpxxid = slgSpxx.getId();
			response.setCharacterEncoding("gbk");//IE用GBK  FF用UTF-8    问题待解决
			PrintWriter out = response.getWriter();
			out.println("<script>");
			Integer result = null;
			try {
				result = this.slgService.insertOrUpdateSlgSpxx(request, slgSpxx, "");
				out.println("parent.closechuli();");
				if(result == 1){
					if(slgSpxxid != null && slgSpxxid > 0){
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yujing/slgSpxx_initSlgSpxxList.action';");
					}else{
						out.println("alert('添加成功!');");
						out.println("parent.window.location.href = '"+request.getContextPath()+"/yujing/slgSpxx_initSlgSpxxList.action';");
					}
				}else{
					out.println("alert('编辑失败!')");
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
		}else{
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	
	public String delSlgSpxx() throws Exception{
		try {
			Long id = Long.parseLong(request.getParameter("id"));
			result = this.slgService.deleteSlgSpxx(request, id, "");
//			String jsonString = JsonUtil.object2json(result);
//			response.setContentType("text/xml; charset=UTF-8");
//			response.setHeader("Cache-Control", "no-cache");
//			response.setDateHeader("Expires", 0);
//			response.getWriter().print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
		return "success";
	}
	
	public String selSlgSpxx() throws Exception{
		SlgSpxx slg = null;
		String editType = request.getParameter("editType");
		if("edit".equals(editType) || "query".equals(editType)){
			Long id = Long.parseLong(request.getParameter("id"));
			slg = this.slgService.getSlgSpxxById(request, id);
		}
		request.setAttribute("editType", editType);
		request.setAttribute("slgSpxx", slg);
		return "edit";
	}
	
	public String selSlgSpxxByCondition() throws Exception{
		SlgSpxx slg = null;
		slg = this.slgService.getSlgSpxxByCondition(request);
		if(slg != null){
			result = 1;
		}else{
			result = 0;
		}
		return "isSp";
	}
	

	public List<SlgSpxx> getSlgSpxxList() {
		return slgSpxxList;
	}

	public void setSlgSpxxList(List<SlgSpxx> slgSpxxList) {
		this.slgSpxxList = slgSpxxList;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public SlgSpxx getSlgSpxx() {
		return slgSpxx;
	}

	public void setSlgSpxx(SlgSpxx slgSpxx) {
		this.slgSpxx = slgSpxx;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}
	
}
