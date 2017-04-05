package com.ycszh.ssh.action.drv;

import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.jasper.tagplugins.jstl.core.Out;
import org.json.JSONArray;

import com.ycszh.util.DateUtil;
import com.ycszh.util.ExportExcelBean;
import com.ycszh.util.ExportTools;
import com.ycszh.util.JsonUtil;
import com.ycszh.util.StringUtil;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.drv.SlgDrvXxcjb;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.SlgYwlx;
import com.ycszh.ssh.hbm.drv.SlgZjxxb;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.SlgSpxxService;
import com.ycszh.ssh.service.drv.YujingService;

/**
 * @包名:com.ycszh.ssh.action.drv
 * @文件名:SlgDrvAction.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class SlgDrvAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(SlgDrvAction.class);
	private SlgDrvXxcjb slgDrvXxcjb;
	private SlgZjxxb slgZjxxb;
	private SlgDrvService slgDrvService;
	private YujingService yujingService;
	private SlgSpxxService slgService;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private List<SlgDrvXxcjb> slgDrvXxcjbList = new ArrayList<SlgDrvXxcjb>();
	private List<SlgYwlx> slgYwlxList = new ArrayList<SlgYwlx>();
	private List<SlgSjzd> slgSjzdList = new ArrayList<SlgSjzd>();
	private String inputPath;
	//导出实体
    private ExportExcelBean eeb;
    /**
     * 列表显示信息
     */
    private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();

	/**
	 * 上传文件实体对象
	 */
	private File file1;
	private File file2;
	private File file01;
	private File file02;

	/**
	 * 上传文件类型
	 */
	private String file1ContentType;
	private String file2ContentType;
	private String file01ContentType;
	private String file02ContentType;

	/**
	 * 上传文件名
	 */
	private String file1FileName;
	private String file2FileName;
	private String file01FileName;
	private String file02FileName;

	// 采集信息列表（新）
	@SuppressWarnings("unchecked")
	public String initSlgDrvXxcjbList2() throws Exception {
		//this.setSlgSjzdList(this.slgDrvService.getYwlxYwyy("", "", "JSZYWSL", "DRV_YWLX"));
		List list = this.slgDrvService.getDeptList("", "C34702A8FED97CBFE040007F0100339B", "");
		request.setAttribute("deptList", list);
		this.setSlgDrvXxcjbList(slgDrvService.getSlgDrvXxcjbList2(request,currentpage));
		return "list2";
	}
	
	public String initSlgDrvXxcjbExport() throws Exception{
		response.setContentType("text/html;charset=UTF-8"); 
		List<SlgDrvXxcjb>  xxcjList = null;
		xxcjList = this.slgDrvService.getSlgDrvXxcdjbExport(request, 1);
		Object exportData = request.getAttribute("exportData");
		if(exportData != null && !"".equals(exportData.toString())){
			return "list2";
		}
		eeb = new ExportExcelBean();
		if(!xxcjList.isEmpty() && xxcjList.size() > 0){
			eeb.setFileName("驾驶证受理信息");
			eeb.setFileTitle("驾驶证受理信息");
			eeb.setList(xxcjList);
			parmsMap.put("sfzmmc", "身份证证明名称");
			parmsMap.put("sfzmhm", "身份证号码 ");
			parmsMap.put("xm", "姓名");
			parmsMap.put("xb", "性别 ");
			parmsMap.put("ywlx", "业务类型");
			parmsMap.put("dabh", "档案编号 ");
			parmsMap.put("sjhm", "手机号码 ");
			parmsMap.put("sfzmhmdbr", "代办人身份证号码 ");
			parmsMap.put("xmdbr", "代办人姓名 ");
			parmsMap.put("shJg", "审核状态 ");
			parmsMap.put("czrxm", "采集人姓名");
			parmsMap.put("czbm", "采集人部门");
			parmsMap.put("czrq", "采集时间");
			
			//身份证证明名称  身份证号码 姓名  性别  业务类型   业务原因  档案编号	sjhm 手机号码   代办人身份证号码sfzmhmdbr    代办人姓名    操作人姓名  操作部门  操作日期
			eeb.setParmsMap(parmsMap);
			ExportTools.exportExcel(response, eeb);
		}else{
			request.setAttribute("exportData", "当前导出数据为空！");  
			return "list2";
		}
		return NONE;
	}
	
	// 采集信息列表（旧）
	public String initSlgDrvXxcjbList() throws Exception {
		this.setSlgYwlxList(slgDrvService.getSlgYwlxList());
		this.setSlgDrvXxcjbList(slgDrvService.getSlgDrvXxcjbList(request,currentpage));
		return "list";
	}

	// 初始化添加页面(旧)
	@SuppressWarnings("rawtypes")
	public String insertSlgDrvXxcjb() throws Exception {
		String ywlx = request.getParameter("ywlx");
		String ywms = "";
		String ywmsen = "";
		String ywlxstr = "";
		this.setSlgYwlxList(slgDrvService.getSlgYwlxList("DRV", ywlx));
		if (null != slgYwlxList && slgYwlxList.size() > 0) {
			SlgYwlx slgYwlx = slgYwlxList.get(0);
			if (null != slgYwlx) {
				ywms = slgYwlx.getYwms_main_ch();
				ywmsen = slgYwlx.getYwms_main();
				ywlxstr = slgYwlx.getYwlx();
			}
		}
//		List gjlist = slgDrvService.getGj();
//		List ztlist = slgDrvService.getZt();
//		List xzqhlist = slgDrvService.getXzqh();
//		request.setAttribute("gjlist", gjlist);
//		request.setAttribute("ztlist", ztlist);
//		request.setAttribute("xzqhlist", xzqhlist);
		request.setAttribute("ywms", ywms);
		request.setAttribute("ywmsen", ywmsen);
		request.setAttribute("ywlx", ywlx);
		request.setAttribute("ywlxstr", ywlxstr);
		request.setAttribute("editType", "新增");
		return "insert";
	}
	
	// 初始化添加页面（新）
	public String initInertSlgDrvXxcjb() throws Exception {
		this.setSlgSjzdList(this.yujingService.getSlgSjzdList(request, "JSZYWSL", ""));
		request.setAttribute("editType", "新增");
		return "insert2";
	}

	// 初始化修改页面（旧）
	public String updateSlgDrvXxcjb() throws Exception {
		if (slgDrvXxcjb.getCjid() != null || !"".equals(slgDrvXxcjb.getCjid())) {
			this.setSlgSjzdList(this.yujingService.getSlgSjzdList(request, "JSZYWSL", ""));
			this.setSlgDrvXxcjb(slgDrvService.getSlgDrvXxcjb(slgDrvXxcjb.getCjid()));
			if (this.getSlgDrvXxcjb() != null) {
				request.setAttribute("editType", "修改");
				return "update";
			} else {
				request.setAttribute("errorTip", "没有该采集信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该采集信息!");
			return "Exception";
		}
	}

	// 查看页面（新）
	@SuppressWarnings("rawtypes")
	public String initSlgDrvXxcjb2() throws Exception {
		if (slgDrvXxcjb.getCjid() != null) {
			//this.setSlgSjzdList(this.yujingService.getSlgSjzdList(request, "JSZYWSL", ""));
			this.setSlgDrvXxcjb(slgDrvService.getSlgDrvXxcjb(slgDrvXxcjb.getCjid()));
			if (this.getSlgDrvXxcjb() != null) {
				String ywlx = request.getParameter("ywlx");
				String ywms = "";
				String ywmsen = "";
				String ywlxstr = "";
//				this.setSlgYwlxList(slgDrvService.getSlgYwlxList("DRV", ywlx));
//				if (null != slgYwlxList && slgYwlxList.size() > 0) {
//					SlgYwlx slgYwlx = slgYwlxList.get(0);
//					if (null != slgYwlx) {
//						ywms = slgYwlx.getYwms_main_ch();
//						ywmsen = slgYwlx.getYwms_main();
//						ywlxstr = slgYwlx.getYwlx();
//					}
//				}
				this.setSlgZjxxb(slgDrvService.getSlgZjxxb(slgDrvXxcjb.getCjid()));
				
				if(null != slgDrvXxcjb.getZt() && !"".equals(slgDrvXxcjb.getZt())){
					if(slgDrvXxcjb.getZt().length() > 1){
						String zt = "";
						for(int i = 0; i < slgDrvXxcjb.getZt().length(); i ++){
							if(i == 0){
								zt += slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}else{
								zt += "," + slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}
						}
						slgDrvXxcjb.setZt(zt);
					}else{
						String zt = slgDrvService.getZtInfo(slgDrvXxcjb.getZt());
						slgDrvXxcjb.setZt(zt);
					}
				}
				
				if(null != slgDrvXxcjb.getYwlx() && !"".equals(slgDrvXxcjb.getYwlx())){
					String ywlxval = "";
					String[] ywyyarr = null;
					String[] ywlxarr = slgDrvXxcjb.getYwlx().split(",");
					List<SlgSjzd> slgsjzds = null;
					if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy())){
						ywyyarr = slgDrvXxcjb.getYwyy().split(",");
					}
					for(int i = 0; i < ywlxarr.length; i++){
						slgsjzds = new ArrayList<SlgSjzd>();
						slgsjzds = this.slgDrvService.getYwlxYwyy(request, ywlxarr[i], "", "JSZYWSL", "DRV_YWLX", "");
						if(slgsjzds.size() > 0){
							ywlxval += slgsjzds.get(0).getDmms1();
						}
						if(ywyyarr != null){
							String ywyystr = "";
							for(int j = 0; j < ywyyarr.length; j++){
								String ywyyval = ywyyarr[j];
								String[] ywyysplit = ywyyval.split(":");
								if(ywlxarr[i].equals(ywyysplit[0])){
									slgsjzds = new ArrayList<SlgSjzd>();
									slgsjzds = this.slgDrvService.getYwlxYwyy(request, ywyysplit[1], ywlxarr[i], "JSZYWSL", "DRV_YWYY", "");
									if(slgsjzds.size() > 0){
										ywyystr += slgsjzds.get(0).getDmms1()+",";
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								if(ywyystr.endsWith(",")){
									ywyystr =  ywyystr.substring(0, ywyystr.length()-1);
								}
								ywlxval += "("+ywyystr+")";
							}
							
						}
						ywlxval += ",";
					}
					if(ywlxval.endsWith(",")){
						ywlxval = ywlxval.substring(0, ywlxval.length()-1);
					}
					slgDrvXxcjb.setYwlx(ywlxval);
				}
				/************************wy注释********************************************/
//				List gjlist = slgDrvService.getGj();
//				List ztlist = slgDrvService.getZt();
//				List xzqhlist = slgDrvService.getXzqh();
//				request.setAttribute("gjlist", gjlist);
//				request.setAttribute("ztlist", ztlist);
//				request.setAttribute("xzqhlist", xzqhlist);
				/*************************wy注释*******************************************/
				request.setAttribute("ywms", ywms);
				request.setAttribute("ywmsen", ywmsen);
				request.setAttribute("ywlx", ywlx);
				request.setAttribute("ywlxstr", ywlxstr);
				request.setAttribute("editType", "查看");
				return "view2";
			} else {
				request.setAttribute("errorTip", "没有该采集信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该采集信息!");
			return "Exception";
		}
	}
	
	// 查看页面（旧）
	@SuppressWarnings("rawtypes")
	public String initSlgDrvXxcjb() throws Exception {
		if (slgDrvXxcjb.getCjid() != null) {
			this.setSlgDrvXxcjb(slgDrvService.getSlgDrvXxcjb(slgDrvXxcjb.getCjid()));
			if (this.getSlgDrvXxcjb() != null) {
				String ywlx = request.getParameter("ywlx");
				String ywms = "";
				String ywmsen = "";
				String ywlxstr = "";
				this.setSlgYwlxList(slgDrvService.getSlgYwlxList("DRV", ywlx));
				if (null != slgYwlxList && slgYwlxList.size() > 0) {
					SlgYwlx slgYwlx = slgYwlxList.get(0);
					if (null != slgYwlx) {
						ywms = slgYwlx.getYwms_main_ch();
						ywmsen = slgYwlx.getYwms_main();
						ywlxstr = slgYwlx.getYwlx();
					}
				}
				this.setSlgZjxxb(slgDrvService.getSlgZjxxb(slgDrvXxcjb.getCjid()));
				
				if(null != slgDrvXxcjb.getZt() && !"".equals(slgDrvXxcjb.getZt())){
					if(slgDrvXxcjb.getZt().length() > 1){
						String zt = "";
						for(int i = 0; i < slgDrvXxcjb.getZt().length(); i ++){
							if(i == 0){
								zt += slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}else{
								zt += "," + slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}
						}
						slgDrvXxcjb.setZt(zt);
					}else{
						String zt = slgDrvService.getZtInfo(slgDrvXxcjb.getZt());
						slgDrvXxcjb.setZt(zt);
					}
				}
				
				/*List gjlist = slgDrvService.getGj();
				List ztlist = slgDrvService.getZt();
				List xzqhlist = slgDrvService.getXzqh();
				request.setAttribute("gjlist", gjlist);
				request.setAttribute("ztlist", ztlist);
				request.setAttribute("xzqhlist", xzqhlist);*/
				request.setAttribute("ywms", ywms);
				request.setAttribute("ywmsen", ywmsen);
				request.setAttribute("ywlx", ywlx);
				request.setAttribute("ywlxstr", ywlxstr);
				request.setAttribute("editType", "查看");
				return "view";
			} else {
				request.setAttribute("errorTip", "没有该采集信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该采集信息!");
			return "Exception";
		}
	}
	
	// 初始化审核页面
	@SuppressWarnings("rawtypes")
	public String initSHSlgDrvXxcjb() throws Exception {
		if (slgDrvXxcjb.getCjid() != null) {
			//this.setSlgSjzdList(this.yujingService.getSlgSjzdList(request, "JSZYWSL", ""));
			this.setSlgDrvXxcjb(slgDrvService.getSlgDrvXxcjb(slgDrvXxcjb.getCjid()));
			if (this.getSlgDrvXxcjb() != null) {
				String ywlx = request.getParameter("ywlx");
				String ywms = "";
				String ywmsen = "";
				String ywlxstr = "";
//				this.setSlgYwlxList(slgDrvService.getSlgYwlxList("DRV", ywlx));
//				if (null != slgYwlxList && slgYwlxList.size() > 0) {
//					SlgYwlx slgYwlx = slgYwlxList.get(0);
//					if (null != slgYwlx) {
//						ywms = slgYwlx.getYwms_main_ch();
//						ywmsen = slgYwlx.getYwms_main();
//						ywlxstr = slgYwlx.getYwlx();
//					}
//				}
				this.setSlgZjxxb(slgDrvService.getSlgZjxxb(slgDrvXxcjb.getCjid()));
				
				if(null != slgDrvXxcjb.getZt() && !"".equals(slgDrvXxcjb.getZt())){
					if(slgDrvXxcjb.getZt().length() > 1){
						String zt = "";
						for(int i = 0; i < slgDrvXxcjb.getZt().length(); i ++){
							if(i == 0){
								zt += slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}else{
								zt += "," + slgDrvService.getZtInfo(String.valueOf(slgDrvXxcjb.getZt().charAt(i)));
							}
						}
						slgDrvXxcjb.setZt(zt);
					}else{
						String zt = slgDrvService.getZtInfo(slgDrvXxcjb.getZt());
						slgDrvXxcjb.setZt(zt);
					}
				}
				
				if(null != slgDrvXxcjb.getYwlx() && !"".equals(slgDrvXxcjb.getYwlx())){
					String ywlxval = "";
					String[] ywyyarr = null;
					String[] ywlxarr = slgDrvXxcjb.getYwlx().split(",");
					List<SlgSjzd> slgsjzds = null;
					if(null != slgDrvXxcjb.getYwyy() && !"".equals(slgDrvXxcjb.getYwyy())){
						ywyyarr = slgDrvXxcjb.getYwyy().split(",");
					}
					for(int i = 0; i < ywlxarr.length; i++){
						slgsjzds = new ArrayList<SlgSjzd>();
						slgsjzds = this.slgDrvService.getYwlxYwyy(request, ywlxarr[i], "", "JSZYWSL", "DRV_YWLX", "");
						if(slgsjzds.size() > 0){
							ywlxval += slgsjzds.get(0).getDmms1();
						}
						if(ywyyarr != null){
							String ywyystr = "";
							for(int j = 0; j < ywyyarr.length; j++){
								String ywyyval = ywyyarr[j];
								String[] ywyysplit = ywyyval.split(":");
								if(ywlxarr[i].equals(ywyysplit[0])){
									slgsjzds = new ArrayList<SlgSjzd>();
									slgsjzds = this.slgDrvService.getYwlxYwyy(request, ywyysplit[1], ywlxarr[i], "JSZYWSL", "DRV_YWYY", "");
									if(slgsjzds.size() > 0){
										ywyystr += slgsjzds.get(0).getDmms1()+",";
									}
								}
							}
							if(ywyystr != null && !"".equals(ywyystr)){
								if(ywyystr.endsWith(",")){
									ywyystr =  ywyystr.substring(0, ywyystr.length()-1);
								}
								ywlxval += "("+ywyystr+")";
							}
							
						}
						ywlxval += ",";
					}
					if(ywlxval.endsWith(",")){
						ywlxval = ywlxval.substring(0, ywlxval.length()-1);
					}
					slgDrvXxcjb.setYwlx(ywlxval);
				}
				/************************wy注释********************************************/
//				List gjlist = slgDrvService.getGj();
//				List ztlist = slgDrvService.getZt();
//				List xzqhlist = slgDrvService.getXzqh();
//				request.setAttribute("gjlist", gjlist);
//				request.setAttribute("ztlist", ztlist);
//				request.setAttribute("xzqhlist", xzqhlist);
				/*************************wy注释*******************************************/
				request.setAttribute("ywms", ywms);
				request.setAttribute("ywmsen", ywmsen);
				request.setAttribute("ywlx", ywlx);
				request.setAttribute("ywlxstr", ywlxstr);
				request.setAttribute("editType", "查看");
				request.setAttribute("editType2", "审核");
				return "view2";
			} else {
				request.setAttribute("errorTip", "没有该采集信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该采集信息!");
			return "Exception";
		}
	}

	// 编辑采集信息信息（新）
	public String editSlgDrvXxcjb2() throws Exception {
		if (slgDrvXxcjb != null) {
			String cjid = slgDrvXxcjb.getCjid();
			if(null != slgDrvXxcjb.getYwmsMain() && !"".equals(slgDrvXxcjb.getYwmsMain()))slgDrvXxcjb.setYwmsMain(slgDrvXxcjb.getYwmsMain().replaceAll(" ", "").replaceAll("　", ""));
			String ywlxchk = slgDrvXxcjb.getYwmsMain();
			if(ywlxchk != null && !"".equals(ywlxchk)){
				String ywlx = "";
				String[] chks = ywlxchk.split(",");
				if(chks != null && chks.length > 0){
					List<SlgYwlx> slgYwlxs = null;
					for(int i = 0; i < chks.length; i++){
						slgYwlxs = new ArrayList<SlgYwlx>();
						slgYwlxs = slgDrvService.getSlgYwlxList("DRV", chks[i]);
						if(null != slgYwlxs && slgYwlxs.size() > 0){
							String ywlxtemp = slgYwlxs.get(0).getYwlx();
							if(null != ywlxtemp){
								ywlx += ywlxtemp+",";
							}
						}
					}
					
				}
				if(ywlx.endsWith(",")){
					ywlx = ywlx.substring(0, ywlx.length()-1);
				}
				slgDrvXxcjb.setYwlx(ywlx);
			}
			response.setCharacterEncoding("GBK");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				returnInteger = slgDrvService.insertOrUpdateSlgDrvXxcjb2(slgDrvXxcjb, slgZjxxb, request, file1,file2, file01, file02);
				out.println("parent.closechuli();");
				if (returnInteger == 0) {
					String sfzmmc = slgDrvXxcjb.getSfzmmc();
					String isdbra = request.getParameter("isdbra");
					String ismyself = slgDrvXxcjb.getIsjsr();
					String cjsj = DateUtil.date2String(slgDrvXxcjb.getCzrq(), "yyyy-MM-dd HH:mm:ss");
					String isduka = slgZjxxb.getCardno();
					if(sfzmmc != null && (sfzmmc.equals("A") || sfzmmc.equals("M")) && StringUtil.isNull(isduka)){
						String sfzmhm = slgDrvXxcjb.getSfzmhm();
						String xm = slgDrvXxcjb.getXm();
						this.slgService.updateSlgSpxx(request, sfzmhm, xm, "drv", "0", cjsj);
					}
					if("0".equals(ismyself)){
						if("1".equals(isdbra)){
							String sfzmhmdbr = slgDrvXxcjb.getSfzmhmdbr();
							String xmdbr = slgDrvXxcjb.getXmdbr();
							this.slgService.updateSlgSpxx(request, sfzmhmdbr, xmdbr, "drv", "1", cjsj);
						}
					}
					
					if (null != cjid && !"".equals(cjid)) {
						out.println("alert('修改成功!');");
						out.println("parent.window.location.href = '"
								+ request.getContextPath()
								+ "/drv/initSlgDrvXxcjbList.action';");
					} else {
						out.println("if(window.confirm('采集成功!是否要马上打印采集信息?')){window.open('print.action?slgDrvXxcjb.cjid="+slgDrvXxcjb.getCjid()+"');}");
						//out.println("alert('采集成功!');");
						out.println("parent.cleanmyform(1);");
						//out.println("parent.window.location.href='" + request.getContextPath() + "/drv/insertSlgDrvXxcjb.action?ywlx=MFXX'");
					}
				} else {
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
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}
	// 编辑采集信息信息（旧）
	public String editSlgDrvXxcjb() throws Exception {
		if (this.slgDrvXxcjb != null) {
		    String cjid = this.slgDrvXxcjb.getCjid();
		    this.response.setCharacterEncoding("GBK");
		    PrintWriter out = this.response.getWriter();
		    out.println("<script>");
		    try {
		        this.returnInteger = this.slgDrvService.insertOrUpdateSlgDrvXxcjb(this.slgDrvXxcjb, this.slgZjxxb, this.request, this.file1, this.file2, this.file01, this.file02);
		        out.println("parent.closechuli();");
		        if (this.returnInteger.intValue() == 0) {
		          if ((cjid != null) && (!"".equals(cjid))) {
		            out.println("alert('修改成功!');");
		            out.println("parent.window.location.href = '" + 
		              this.request.getContextPath() + 
		              "/drv/initSlgDrvXxcjbList.action';");
		          } else {
		            out.println("if(window.confirm('采集成功!是否要马上打印采集信息?')){window.open('print.action?slgDrvXxcjb.cjid=" + this.slgDrvXxcjb.getCjid() + "');}");
		
		            out.println("parent.cleanmyform();");
		          }
		        }
		        else out.println("alert('编辑失败!')"); 
		   }catch (Exception e){
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
		        String exceptionstr = "异常信息:" + estr + "</br>文件名:" + 
		          stackTraceElement.getFileName() + "</br>行数:" + 
		          stackTraceElement.getLineNumber() + "</br>方法名:" + 
		          stackTraceElement.getMethodName();
		        out.println("parent.exception('" + exceptionstr + "');");
		   }
		   out.println("</script>");
		   return null;
		}
	    this.request.setAttribute("errorTip", "服务器忙,请稍后再试!");
	    return "Exception";
  }

	// 删除采集信息信息
	public String deleteSlgDrvXxcjb() throws Exception {
		String cjid = request.getParameter("cjid");
		if (null != cjid) {
			this.setReturnInteger(slgDrvService.deleteSlgDrvXxcjb(cjid));
			try {
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
			}
		}
		return null;
	}

	// 删除采集信息信息(多个)
	public String deleteSlgDrvXxcjbList() throws Exception {
		String[] cjids = request.getParameterValues("cjids");
		this.setReturnInteger(slgDrvService.deleteSlgDrvXxcjbList(cjids));
		try {
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
		}
		return null;
	}

	// 下载控件驱动
	public InputStream getRarFile() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	
	// 电子档案下载控件驱动
	public InputStream getRarFile2() throws Exception {
		return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
	}
	
	//根据身份证明号码查询档案信息
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getDaxx() throws Exception{
		String hm = request.getParameter("hm");
		String type = request.getParameter("type");
		List list = new ArrayList();
		list = slgDrvService.getDaxxInfo(hm, type);
		if (list != null && list.size() > 0) {
			try {
				if(null != list.get(5)){
					String zts = list.get(5).toString();
					if(null != zts && !"".equals(zts)){
						if(zts.length() > 1){
							String zt = "";
							for(int i = 0; i < zts.length(); i ++){
								if(i == 0){
									zt += slgDrvService.getZtInfo(String.valueOf(zts.charAt(i)));
								}else{
									zt += "," + slgDrvService.getZtInfo(String.valueOf(zts.charAt(i)));
								}
							}
							list.add(zt);
						}else{
							String zt = slgDrvService.getZtInfo(zts);
							list.add(zt);
						}
					}
				}
				String jsonString = JsonUtil.object2json(list);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return null;
	}
	
	//获取统一版流水号信息
	public String getTyblsh() throws Exception{
		String lsh = request.getParameter("lsh");
		if (null != lsh) {
			this.setReturnInteger(slgDrvService.getjia6in1(lsh));
			//this.setReturnInteger(0);
			try {
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				PrintWriter out = response.getWriter();
				out.print(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e);
			}
		}
		return null;
	}
	
	//打印表单
	public String print() throws Exception{
		if (slgDrvXxcjb.getCjid() != null) {
			this.setSlgDrvXxcjb(slgDrvService.getSlgDrvXxcjb(slgDrvXxcjb.getCjid()));
			if (this.getSlgDrvXxcjb() != null) {
				if("A".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("居民身份证");
				}else if("B".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("组织机构代码证书");
				}else if("C".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("军官证");
				}else if("D".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("士兵证");
				}else if("E".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("军官离退休证");
				}else if("F".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("境外人员身份证明");
				}else if("G".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("外交人员身份证明");
				}else if("H".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("居民户口薄");
				}else if("J".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("单位注销证明");
				}else if("K".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("居住暂住证明");
				}else if("L".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("驻华机构证明");
				}else if("M".equals(slgDrvXxcjb.getSfzmmc())){
					slgDrvXxcjb.setSfzmmc("临时居民身份证");
				}
				char[] sfzmhmstr = new char[20];
				if(null != slgDrvXxcjb.getSfzmhm() && !"".equals(slgDrvXxcjb.getSfzmhm())){
					sfzmhmstr = slgDrvXxcjb.getSfzmhm().toCharArray();
				}
				if(null != slgDrvXxcjb.getGj() && !"".equals(slgDrvXxcjb.getGj())){
					slgDrvXxcjb.setGj(slgDrvService.getGjInfo(slgDrvXxcjb.getGj()));
				}
				request.setAttribute("sfzmhmstr", sfzmhmstr);
				request.setAttribute("editType", "打印");
				return "print";
			} else {
				request.setAttribute("errorTip", "没有该采集信息!");
				return "Exception";
			}
		} else {
			request.setAttribute("errorTip", "没有该采集信息!");
			return "Exception";
		}
	}
	
	/**
	 * 获取未读卡原因字典
	 * @return
	 * @throws Exception 
	 */
	public String getNotIDCardrs() throws Exception{
		List<SlgSjzd> slgSList = this.yujingService.getSlgSjzdList(request, "WDKYY", "");
		if(slgSList != null && slgSList.size() > 0){
			JSONArray array=new JSONArray(slgSList);
			response.setContentType("text/json;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			PrintWriter out=response.getWriter();
			out.println(array.toString());
			out.flush();
			out.close();
		}
		return null;
	}
	
	public String getYwlxYwyy() throws Exception{
		List<SlgSjzd> slgSjzds = new ArrayList<SlgSjzd>();
		String dmz = request.getParameter("dmz");
		String dmms2 = request.getParameter("dmms2");
		String dmlb = request.getParameter("dmlb");
		String veh_drv = request.getParameter("veh_drv");
		String isflag = request.getParameter("flag");
		try {
			slgSjzds = this.slgDrvService.getYwlxYwyy(request, dmz, dmms2, dmlb, veh_drv, isflag);
			if(slgSjzds != null && slgSjzds.size() > 0){
				JSONArray array=new JSONArray(slgSjzds);
				response.setContentType("text/json;charset=utf-8");
				response.setCharacterEncoding("utf-8");
				PrintWriter out=response.getWriter();
				out.println(array.toString());
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	/**
	 * 判断是否违法未处理超过10宗并且记分超过12分
	 * @return
	 * @throws Exception
	 */
	public String getIsChaoTen() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String hphm = request.getParameter("hphm");
			String hpzl = request.getParameter("hpzl");
			String jszh = request.getParameter("jszh");
			String result = this.slgDrvService.getIsChaoTen(request, hphm, hpzl, jszh);
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
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 驾驶证业务审核验证
	 * @return
	 * @throws Exception
	 */
	public String drvShenheCheck() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String shlsh = request.getParameter("shlsh");
			String sfzmhm = request.getParameter("sfzmhm");
			String sfzmmc = request.getParameter("sfzmmc");
			String xm = request.getParameter("xm");
			String dabh = request.getParameter("dabh");
			Integer result = this.slgDrvService.drvShenheCheck(request, shlsh, sfzmhm,sfzmmc,xm,dabh);
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
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	/**
	 * 驾驶证业务审核
	 * @return
	 * @throws Exception
	 */
	public String drvShenhe() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		try {
			String shlsh = request.getParameter("shlsh");
			String shjg = request.getParameter("shjg");
			String cjid = request.getParameter("cjid");
			String bz1 = request.getParameter("bz1");
			String bz2 = request.getParameter("bz2");
			Integer result = this.slgDrvService.drvShenhe(request, shlsh, shjg,cjid,bz1,bz2);
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
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		}finally{
			out.flush();
			out.close();
		}
		return null;
	}
	
	public String checkslgIsSfsh() throws Exception{
		response.setContentType("text/xml; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		PrintWriter out = response.getWriter();
		String proStr="";
		String sfsh = request.getParameter("requestSfsh");
		String sfzmhm = request.getParameter("sfzmhm");
		String xm = request.getParameter("xm");
		if("1".equals(sfsh)){
			  int result = slgDrvService.getSpxx(request,sfzmhm,xm);
			  if(result==0){
				  try{
					  proStr = slgDrvService.getPro(request,sfzmhm,xm);
				  }catch (Exception e) {
					  out.println("4");
				}  
				   if(null != proStr && !"".equals(proStr)){
					   if("0".equals(proStr)){
							//out.println("alert('没有查到居住证信息，不能受理业务。');");
						   out.println("2");
						}else{
							String param[] = proStr.split(";");
						    String jssj = param[8];
						    if(!"".equals(jssj)){
						    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							    if(!sdf.parse(jssj).after(DateUtil.getCurrentDate())){
							    	//out.println("alert('非深户居住证信息无效，不能受理业务。');");
							    	out.println("3");
							    }
						    }else{
						    	out.println("3");
							}
						}
				   }
			  }else {
				  out.println("1");
			}
		}else{
			out.println("0");
		}
		return null;
	}
	
	/*
	 * 驾驶证受理业务审核查询统计
	 */
	public String cxtjSlgDrvXxcjb() throws Exception{
		slgDrvService.initDrvtj(request, currentpage);
		return "cxtjdrv";
	}
	
	/*
	 * 驾驶证受理业务审核查询统计查看详情
	 */
	public String initDrvtjView() throws Exception{
		slgDrvService.initDrvtjView(request, currentpage);
		return "initdrv";
	}
	

	public SlgDrvXxcjb getSlgDrvXxcjb() {
		return slgDrvXxcjb;
	}

	public void setSlgDrvXxcjb(SlgDrvXxcjb slgDrvXxcjb) {
		this.slgDrvXxcjb = slgDrvXxcjb;
	}

	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}

	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
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

	public List<SlgDrvXxcjb> getSlgDrvXxcjbList() {
		return slgDrvXxcjbList;
	}

	public void setSlgDrvXxcjbList(List<SlgDrvXxcjb> slgDrvXxcjbList) {
		this.slgDrvXxcjbList = slgDrvXxcjbList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static Logger getLogger() {
		return logger;
	}

	public SlgZjxxb getSlgZjxxb() {
		return slgZjxxb;
	}

	public void setSlgZjxxb(SlgZjxxb slgZjxxb) {
		this.slgZjxxb = slgZjxxb;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public File getFile1() {
		return file1;
	}

	public void setFile1(File file1) {
		this.file1 = file1;
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

	public List<SlgYwlx> getSlgYwlxList() {
		return slgYwlxList;
	}

	public void setSlgYwlxList(List<SlgYwlx> slgYwlxList) {
		this.slgYwlxList = slgYwlxList;
	}

	/**
	 * @return the file2
	 */
	public File getFile2() {
		return file2;
	}

	/**
	 * @param file2
	 *            the file2 to set
	 */
	public void setFile2(File file2) {
		this.file2 = file2;
	}

	/**
	 * @return the file2ContentType
	 */
	public String getFile2ContentType() {
		return file2ContentType;
	}

	/**
	 * @param file2ContentType
	 *            the file2ContentType to set
	 */
	public void setFile2ContentType(String file2ContentType) {
		this.file2ContentType = file2ContentType;
	}

	/**
	 * @return the file2FileName
	 */
	public String getFile2FileName() {
		return file2FileName;
	}

	/**
	 * @param file2FileName
	 *            the file2FileName to set
	 */
	public void setFile2FileName(String file2FileName) {
		this.file2FileName = file2FileName;
	}

	public YujingService getYujingService() {
		return yujingService;
	}

	public void setYujingService(YujingService yujingService) {
		this.yujingService = yujingService;
	}

	public List<SlgSjzd> getSlgSjzdList() {
		return slgSjzdList;
	}

	public void setSlgSjzdList(List<SlgSjzd> slgSjzdList) {
		this.slgSjzdList = slgSjzdList;
	}

	public SlgSpxxService getSlgService() {
		return slgService;
	}

	public void setSlgService(SlgSpxxService slgService) {
		this.slgService = slgService;
	}

	public ExportExcelBean getEeb() {
		return eeb;
	}

	public void setEeb(ExportExcelBean eeb) {
		this.eeb = eeb;
	}

	public Map<String, Object> getParmsMap() {
		return parmsMap;
	}

	public void setParmsMap(Map<String, Object> parmsMap) {
		this.parmsMap = parmsMap;
	}
	
}
