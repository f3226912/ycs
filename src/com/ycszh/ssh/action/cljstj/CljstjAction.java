package com.ycszh.ssh.action.cljstj;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.cljstj.Healthdata;
import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;
import com.ycszh.ssh.service.cljstj.CljstjService;

public class CljstjAction  extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CljstjAction.class);
	private int currentpage = 1;
	private CljstjService cljstjService;
	private TJdclxfsbg jdclxfsbg;
	private List<Healthdata> tjList=new ArrayList<Healthdata>();
	
	/**
	 * 超龄驾驶体检查询
	 */
	public String cljstjQuery() throws Exception {
		this.tjList=cljstjService.cljstjQuery(request, currentpage);
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "shlist";
	}
	
	/**
	 * 超龄驾驶体检获取
	 */
	public String cljstjHq() throws Exception {
		cljstjService.cljstjHq(request);
		this.tjList=cljstjService.cljstjShList(request, currentpage, "1");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "shlist";
	}
	
	/**
	 * 超龄驾驶体检审核数据列表
	 */
	public String cljstjShList() throws Exception {
		String qc=request.getParameter("qc")==null?"":request.getParameter("qc").trim();
		if(qc.equals("2")){
			cljstjService.cljstjQc(request);
		}
		this.tjList=cljstjService.cljstjShList(request, currentpage, "2");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "shlist";
	}
	
	/**
	 * 超龄驾驶体检审核数据
	 */
	public String cljstjShYm() throws Exception {
		cljstjService.cljstjShYm(request);
		String ck=request.getParameter("ck")==null?"":request.getParameter("ck").trim();
		if(ck.equals("2")){
			return "cxym";
		}
		return "shym";
	}
	
	/**
	 * 超龄驾驶体检审核
	 */
	public String cljstjSh() throws Exception {
		cljstjService.cljstjSh(request);
		this.tjList=cljstjService.cljstjShList(request, currentpage, "1");
		String cz=request.getParameter("cz")==null?"":request.getParameter("cz").trim();
		request.setAttribute("cz", cz);
		return "shlist";
	}	
	
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public CljstjService getCljstjService() {
		return cljstjService;
	}

	public void setCljstjService(CljstjService cljstjService) {
		this.cljstjService = cljstjService;
	}

	public TJdclxfsbg getJdclxfsbg() {
		return jdclxfsbg;
	}
	public void setJdclxfsbg(TJdclxfsbg jdclxfsbg) {
		this.jdclxfsbg = jdclxfsbg;
	}

	public List<Healthdata> getTjList() {
		return tjList;
	}

	public void setTjList(List<Healthdata> tjList) {
		this.tjList = tjList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public static Logger getLogger() {
		return logger;
	}
}
