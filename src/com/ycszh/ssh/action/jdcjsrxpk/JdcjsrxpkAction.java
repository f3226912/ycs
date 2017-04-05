package com.ycszh.ssh.action.jdcjsrxpk;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.service.jdcjsrxpk.JdcjsrxpkService;

public class JdcjsrxpkAction extends BaseAction{
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(JdcjsrxpkAction.class);
	private int currentpage = 1;
	private Integer returnInteger = 0;

	private JdcjsrxpkService jdcjsrxpkService;
	
	@SuppressWarnings({"unchecked" })
	private List jdcList = new ArrayList();		//机动车图片库信息集合
	
	@SuppressWarnings("unchecked")					
	private List jsrList = new ArrayList();		//驾驶人图片库信息集合
	
	// 机动车相片库信息集合
	public String jdcxpkList() throws Exception {
		try {
			jdcjsrxpkService.getJdc(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jdcxpkList";
	}

	// 驾驶人相片库信息集合
	@SuppressWarnings("unchecked")
	public String jsrxpkList() throws Exception{
		try {
			jdcjsrxpkService.getJsr(request);
			String jszhm = request.getParameter("jszhms");	// 驾驶证号码
			if(jszhm != null){
				List list = (List) request.getSession().getAttribute("jsrList");
				for (int i = 0; i < list.size(); i++) {
					Object[] obj = (Object[]) list.get(i);
					String hzbh = (String) obj[1];
					String sfzmhm = (String)obj[0];
					request.getSession().setAttribute("hzbh", hzbh);
					request.getSession().setAttribute("sfzmhm", sfzmhm);
					if(sfzmhm != null && !sfzmhm.equals("")){
						jdcjsrxpkService.getJsrxjxx(request);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jsrxpkList";
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}



	public JdcjsrxpkService getJdcjsrxpkService() {
		return jdcjsrxpkService;
	}



	public void setJdcjsrxpkService(JdcjsrxpkService jdcjsrxpkService) {
		this.jdcjsrxpkService = jdcjsrxpkService;
	}



	@SuppressWarnings("unchecked")
	public List getJdcList() {
		return jdcList;
	}



	@SuppressWarnings("unchecked")
	public void setJdcList(List jdcList) {
		this.jdcList = jdcList;
	}


	@SuppressWarnings("unchecked")
	public List getJsrList() {
		return jsrList;
	}


	@SuppressWarnings("unchecked")
	public void setJsrList(List jsrList) {
		this.jsrList = jsrList;
	}	
	
	
}