package com.ycszh.ssh.action.dydj;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletOutputStream;
import org.apache.log4j.Logger;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.dydj.DydjSbInfo;
import com.ycszh.ssh.hbm.dydj.DydjYwsbspbTemp;
import com.ycszh.ssh.hbm.veh.DbjgZjxxb;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.dydj.IDydjService;
import com.ycszh.ssh.service.veh.SlgVehService;
import com.ycszh.util.JsonUtil;
import com.ycszh.util.StringUtil;

@SuppressWarnings("serial")
public class DydjServiceAction extends BaseAction {
	
	private static final Logger logger = Logger.getLogger(DydjServiceAction.class);
	
	private IDydjService dydjService;	
	private SlgDrvService slgDrvService;
	private SlgVehService slgVehService;
	private int currentpage = 1;
	private List<DbjgZjxxb> SlCxList = new ArrayList<DbjgZjxxb>();
	private List<DydjSbInfo> warnSbList ;
	
	/**
	 * 根据流水号查询
	 * @return
	 * @throws Exception
	 */
	public String getDydjYwsbspByLsh() throws Exception{
		logger.info("DydjServiceAction method getDydjYwsbspByLsh...");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String jsonString = "";
			String lsh = request.getParameter("lsh");
			DydjYwsbspbTemp dydjYwsbspb = dydjService.getDydjYwwbspByLsh(request, lsh);
			if(dydjYwsbspb != null){
				jsonString = JsonUtil.bean2json(dydjYwsbspb);
			}
			out.println(jsonString);
			out.flush();
			out.close();
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
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	// 受理查询页面
	@SuppressWarnings("unchecked")
	public String initDydjList() throws Exception {
		logger.info("DydjServiceAction method initDydjList....");
		try {
			List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
			hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
			request.setAttribute("hpzlList", hpzlList);
			
			List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
			request.setAttribute("deptList", list);
			
			this.setSlCxList(this.dydjService.getDydjsbspList(request, currentpage, "query"));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return "dydjlist";
	}
	
	public String getDeptOrgId() throws Exception{
		logger.info("DydjServiceAction method getDeptOrgId...");
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String orgId = dydjService.getDeptOrgId(request);
			out.println(orgId);
			out.flush();
			out.close();
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
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 显示图片
	 * @throws Exception
	 */
	public void showImage() throws Exception{
		logger.info("DydjServiceAction method showImage...");
		String img = request.getParameter("id");
		if(!StringUtil.isNull(img)){
			Integer id = Integer.parseInt(img);
			byte[] byte_array = this.dydjService.getImageBlob(request, id);
			try {
				if(null != byte_array){
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
	 * 显示图片
	 * @throws Exception
	 */
	public void showUserImage() throws Exception{
		logger.info("DydjServiceAction method showImage...");
		String yzyhdm = request.getParameter("yzyhdm");
		if(!StringUtil.isNull(yzyhdm)){
			byte[] byte_array = this.dydjService.getUserImageBlob(request, yzyhdm);
			try {
				if(null != byte_array){
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
	 * （dydj）未经申报办理业务预警（功能）列表初始化
	 * @return
	 * @throws Exception
	 */
	public String initWarnDydj() throws Exception {
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		this.setWarnSbList(this.dydjService.getWarnInfo(request,currentpage));
		return "warnDydj";
	}
	
	/**
	 * 查看抵押登记信息
	 * @return
	 * @throws Exception
	 */
	public String initDydjInfo() throws Exception{
		logger.info("DydjServiceAction method initDydjInfo...");
		try {
			String lsh = request.getParameter("lsh");
			DydjYwsbspbTemp dydjYwsbspb = this.dydjService.getDydjYwwbspByLsh(request, lsh);
			if(dydjYwsbspb == null){
				dydjYwsbspb = new DydjYwsbspbTemp();
			}
			String sqlx = dydjYwsbspb.getSqlx()+"";
			List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
			hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
			request.setAttribute("wscList", hpzlList);
			request.setAttribute("dydjYwsbspb", dydjYwsbspb);
			request.setAttribute("editType", "查看");
			if("11".equals(sqlx) || "12".equals(sqlx) || "21".equals(sqlx)){
				return "yhdetail";
			}else if("22".equals(sqlx)){
				return "zzdetail";
			}else if("31".equals(sqlx)){
				return "grdetail";
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public IDydjService getDydjService() {
		return dydjService;
	}

	public void setDydjService(IDydjService dydjService) {
		this.dydjService = dydjService;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}

	public SlgVehService getSlgVehService() {
		return slgVehService;
	}

	public void setSlgVehService(SlgVehService slgVehService) {
		this.slgVehService = slgVehService;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public List<DbjgZjxxb> getSlCxList() {
		return SlCxList;
	}

	public void setSlCxList(List<DbjgZjxxb> slCxList) {
		SlCxList = slCxList;
	}

	public List<DydjSbInfo> getWarnSbList() {
		return warnSbList;
	}

	public void setWarnSbList(List<DydjSbInfo> warnSbList) {
		this.warnSbList = warnSbList;
	}
	
}
