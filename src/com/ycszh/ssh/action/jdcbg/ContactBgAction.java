package com.ycszh.ssh.action.jdcbg;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;
import com.ycszh.ssh.service.jdcbg.ContactBgService;


public class ContactBgAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(ContactBgAction.class);
	private int currentpage = 1;
	private ContactBgService contactBgService;
	private TJdclxfsbg jdclxfsbg;
	private List<TJdclxfsbg> bgList=new ArrayList<TJdclxfsbg>();
	
	/**
	 * 机动车联系方式变更审核列表
	 */
	public String contactBgQuery() throws Exception {
		String cs=request.getParameter("cs")==null?"":request.getParameter("cs").trim();
		if(cs.equals("2")){
			contactBgService.contactBgQc(request);
		}
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		this.bgList=contactBgService.contactBgQuery(request, currentpage,"1");
		return "list";
	}
	
	/**
	 * 机动车联系方式变更审核
	 */
	public String contactBgSh() throws Exception {
		contactBgService.contactBgSh(request);
		this.bgList=contactBgService.contactBgQuery(request, currentpage,"2");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "list";
	}
	
	/**
	 * 机动车联系方式变更审核页面
	 */
	public String contactBgShym() throws Exception {
		contactBgService.contactBgShym(request);
		String ck=request.getParameter("ck")==null?"":request.getParameter("ck").trim();
		if(ck.equals("1")){
			return "jdcbgck";
		}
		return "jdcbgsh";
	}
	
	/**
	 * 机动车联系方式变更获取
	 */
	public String contactBgHq() throws Exception {
		contactBgService.contactBgHq(request);
		this.bgList=contactBgService.contactBgQuery(request, currentpage,"2");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "list";
	}
	
	/**
	 * 机动车联系方式变更列表
	 */
	public String jdcBgQuery() throws Exception {
		this.bgList=contactBgService.jdcBgQuery(request, currentpage,"2");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "list";
	}
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public ContactBgService getContactBgService() {
		return contactBgService;
	}
	public void setContactBgService(ContactBgService contactBgService) {
		this.contactBgService = contactBgService;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}
	public TJdclxfsbg getJdclxfsbg() {
		return jdclxfsbg;
	}
	public void setJdclxfsbg(TJdclxfsbg jdclxfsbg) {
		this.jdclxfsbg = jdclxfsbg;
	}
	public List<TJdclxfsbg> getBgList() {
		return bgList;
	}
	public void setBgList(List<TJdclxfsbg> bgList) {
		this.bgList = bgList;
	}
	
}
