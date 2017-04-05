package com.ycszh.ssh.action.sjk;

import java.io.File;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.sjk.SjkInfoService;


public class SjkInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SjkInfoAction.class);
	private int currentpage = 1;
	private File uploadExcel;
	private String dxrn;
	private SjkInfoService sjkInfoService;
	
	public SjkInfoService getSjkInfoService() {
		return sjkInfoService;
	}

	public void setSjkInfoService(SjkInfoService sjkInfoService) {
		this.sjkInfoService = sjkInfoService;
	}

	/**
	 * 短信发送
	 * @return
	 * @throws Exception
	 */
	public String getExcelInfo() throws Exception{
		response.setCharacterEncoding("GBK");//IE用GBK  FF用UTF-8    问题待解决
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(uploadExcel != null && !"".equals(dxrn)){
			String resultContent = sjkInfoService.readExcel(uploadExcel,dxrn);
			out.println("alert('"+resultContent+"');");
		}
		else{
			out.println("alert('发送失败，请选择excel文件并填写短信内容!');");
		}
		out.println("</script>");
		return null;
	}
	
	public String getDxrn() {
		return dxrn;
	}

	public void setDxrn(String dxrn) {
		this.dxrn = dxrn;
	}

	public File getUploadExcel() {
		return uploadExcel;
	}

	public void setUploadExcel(File uploadExcel) {
		this.uploadExcel = uploadExcel;
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
	
}
