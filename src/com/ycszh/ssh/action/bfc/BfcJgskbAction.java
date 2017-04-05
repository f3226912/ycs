package com.ycszh.ssh.action.bfc;

import java.util.List;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.bfc.BfcJgskbService;

/**
 * @包名:com.ycszh.ssh.action.bfc
 * @文件名:BfcJgskbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-14
 * @描述:
 * @版本:V 1.0
 */
public class BfcJgskbAction extends BaseAction{

	BfcJgskbService bfcJgskbService;
	private int currentpage = 1;
	
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public BfcJgskbService getBfcJgskbService() {
		return bfcJgskbService;
	}
	public void setBfcJgskbService(BfcJgskbService bfcJgskbService) {
		this.bfcJgskbService = bfcJgskbService;
	}


	public String getBfcJgskbList() throws Exception{
		try{
			bfcJgskbService.getBfcJgskbList(request,currentpage);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "bfcInfoList";
	}
	
	public String getBfcJgskbInfo() throws Exception{
		
		String xh = request.getParameter("xh");
		
		request.setAttribute("bfcJgskb", bfcJgskbService.getBfcJgskbInfo(xh));
	
		return "selBfcJgskbInfo";
	}
	
}
