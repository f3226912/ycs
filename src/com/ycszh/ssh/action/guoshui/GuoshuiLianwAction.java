package com.ycszh.ssh.action.guoshui;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.guoshui.FpEsc;
import com.ycszh.ssh.hbm.guoshui.FpXc;
import com.ycszh.ssh.hbm.guoshui.FpXcgzs;
import com.ycszh.ssh.service.guoshui.IGuoshuiLianwService;
/**
 * @包名:com.ycszh.ssh.action.guoshui
 * @文件名:GuoshuiLianwAction.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-7-8
 * @描述:
 * @版本:V 1.0
 */
@SuppressWarnings("serial")
public class GuoshuiLianwAction extends BaseAction {
	private IGuoshuiLianwService guoShuiLianwService;
	private static final Logger logger = Logger.getLogger(GuoshuiLianwAction.class);
	private List<FpXc> fpxcList;
	private List<FpEsc> fpescList;
	private List<FpXcgzs> fpxcgzsList;
	private String result = "true";
	
	public String initGuoshuiList() throws Exception {
		String cxlx = request.getParameter("cxlx");
		if(cxlx != null){
			try {
				if("changgui".equals(cxlx)){
					this.setFpxcList(this.guoShuiLianwService.getFpxcList(request));
					this.setFpxcgzsList(this.guoShuiLianwService.getFpxcgzsList(request));
					this.setFpescList(this.guoShuiLianwService.getFpEscList(request));
				}else if("wanshui".equals(cxlx)){
					this.setFpxcList(null);
					this.setFpxcgzsList(this.guoShuiLianwService.getFpxcgzsList(request));
					this.setFpescList(null);
					request.setAttribute("tabnum", 1);
				}else if("fplx".equals(cxlx)){
					String fplx = request.getParameter("fplx");
					String fpdm = request.getParameter("ssfpdm");
					String fphm = request.getParameter("ssfphm");
					if("XCFPStr.CGS.SJYY".equals(fplx)){
						fpxcList = new ArrayList<FpXc>();
						FpXc fpxc = (FpXc)this.guoShuiLianwService.getGuoShui(fplx, fpdm, fphm);
						if(fpxc != null){
							fpxcList.add(fpxc);
						}
						this.setFpescList(null);
						this.setFpxcgzsList(null);
						request.setAttribute("tabnum", 0);
						request.setAttribute("fpxcList", fpxcList);
						
					}else if("ESCFPStr.CGS.SJYY".equals(fplx)){
						fpescList = new ArrayList<FpEsc>();
						FpEsc fpesc = (FpEsc)this.guoShuiLianwService.getGuoShui(fplx, fpdm, fphm);
						if(fpesc != null){
							fpescList.add(fpesc);
						}
						this.setFpxcList(null);
						this.setFpxcgzsList(null);
						request.setAttribute("tabnum", 2);
						request.setAttribute("fpescList", fpescList);
					}else if("CgsFpStr.CGS.SJYY".equals(fplx)){
						fpxcgzsList = new ArrayList<FpXcgzs>();
						FpXcgzs fpXcgzs = (FpXcgzs)this.guoShuiLianwService.getGuoShui(fplx, fpdm, fphm);
						if(fpXcgzs != null){
							fpxcgzsList.add(fpXcgzs);
						}
						this.setFpxcList(null);
						this.setFpescList(null);
						request.setAttribute("tabnum", 1);
						request.setAttribute("fpxcgzsList", fpxcgzsList);
					}
					request.setAttribute("ssfpdm", fpdm);
					request.setAttribute("ssfphm", fphm);
					request.setAttribute("fplx", fplx);
					response.setCharacterEncoding("GBK");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("parent.closechuli();");
					out.println("</script>");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
				throw e;
			}
		}
		return "list";
	}
	
	public String initGuoshuiCount() throws Exception {
		int countxc = 0;
		int countcxgzs = 0;
		int countesc = 0;
		try {
			countxc = this.guoShuiLianwService.getFpxcCount(request);
			countcxgzs = this.guoShuiLianwService.getFpxcgzsCount(request);
			countesc = this.guoShuiLianwService.getFpEscCount(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);
			throw e;
		}
		if(countxc > 100){
			result = "false";
		}
		if(countcxgzs > 100){
			result = "false";
		}
		if(countesc > 100){
			result = "false";
		}
		return "count";
	}
	
	
	public IGuoshuiLianwService getGuoShuiLianwService() {
		return guoShuiLianwService;
	}

	public void setGuoShuiLianwService(IGuoshuiLianwService guoShuiLianwService) {
		this.guoShuiLianwService = guoShuiLianwService;
	}

	public List<FpXc> getFpxcList() {
		return fpxcList;
	}

	public void setFpxcList(List<FpXc> fpxcList) {
		this.fpxcList = fpxcList;
	}

	public List<FpEsc> getFpescList() {
		return fpescList;
	}

	public void setFpescList(List<FpEsc> fpescList) {
		this.fpescList = fpescList;
	}

	public List<FpXcgzs> getFpxcgzsList() {
		return fpxcgzsList;
	}

	public void setFpxcgzsList(List<FpXcgzs> fpxcgzsList) {
		this.fpxcgzsList = fpxcgzsList;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	
}
