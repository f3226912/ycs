package com.ycszh.ssh.action.ezxfw;

import java.awt.Color;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ycszh.common.ExportToExcel;
import com.ycszh.formbean.DrvSuperviseForm;
import com.ycszh.formbean.VehSuperviseForm;
import com.ycszh.global.SysConst;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.ezxfw.EzDrvLiceChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzJdcChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrint;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrintPhoto;
import com.ycszh.ssh.service.ezxfw.EzxfwService;
import com.ycszh.ssh.service.sfrz.SfrzService;
import com.ycszh.util.ImageUtils;
import com.ycszh.util.JsonUtil;

@SuppressWarnings("serial")
public class EzxfwAction extends BaseAction {
	
	public EzxfwAction(){
		
		//System.out.println("EzxfwAction created");
	}
	private EzxfwService ezxfwService;
	private SfrzService sfrzService;
	private String result;
	private int currentpage = 1;
	private Integer returnInteger = 1;
	private String appsval;
	private static final Logger logger = Logger.getLogger(EzxfwAction.class);
	private EzDrvLiceChanApp ezDrvLiceChanApp;
	private List<EzDrvLiceChanApp> ezDrvLiceChanAppList = new ArrayList<EzDrvLiceChanApp>();

	private List<EzXxdPrint> ezXxdPrintList = new ArrayList<EzXxdPrint>();
	private EzXxdPrint ezXxdPrint;
	private File picPath;



	@SuppressWarnings("unchecked")
	private VehSuperviseForm vehSupervise;
	private DrvSuperviseForm drvSupervise;

	public DrvSuperviseForm getDrvSupervise() {
		return drvSupervise;
	}

	public void setDrvSupervise(DrvSuperviseForm drvSupervise) {
		this.drvSupervise = drvSupervise;
	}

	@SuppressWarnings("unchecked")
	public VehSuperviseForm getVehSupervise() {
		return vehSupervise;
	}

	@SuppressWarnings("unchecked")
	public void setVehSupervise(VehSuperviseForm vehSupervise) {
		this.vehSupervise = vehSupervise;
	}

	public File getPicPath() {
		return picPath;
	}

	public void setPicPath(File picPath) {
		this.picPath = picPath;
	}

	public static Logger getLogger() {
		return logger;
	}

	public EzXxdPrint getEzXxdPrint() {
		return ezXxdPrint;
	}

	public void setEzXxdPrint(EzXxdPrint ezXxdPrint) {
		this.ezXxdPrint = ezXxdPrint;
	}

	private EzJdcChanApp ezJdcChanApp;
	private List<EzJdcChanApp> ezJdcChanAppList = new ArrayList<EzJdcChanApp>();

	public List<EzXxdPrint> getEzXxdPrintList() {
		return ezXxdPrintList;
	}

	public void setEzXxdPrintList(List<EzXxdPrint> ezXxdPrintList) {
		this.ezXxdPrintList = ezXxdPrintList;
	}

	/**
	 * 获取机动车未监管数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getVehSuperviseData() throws Exception {
		vehSupervise.setPageSize(SysConst.PAGESIZE);
		vehSupervise.setPageUrl(request.getRequestURI());
		vehSupervise
				.setCurrentPage(request.getParameter("currentpage") == null ? 0
						: Integer.parseInt(request.getParameter("currentpage")));
		List vehList = this.ezxfwService
				.getVehBusinessSuperviseData(vehSupervise);
		vehSupervise.setVehList(vehList);

		return "vehSupervise";
	}

	/**
	 * 获取驾驶证未监管数据
	 * 
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String getDrvSuperviseData() throws Exception {
		drvSupervise.setPageSize(SysConst.PAGESIZE);
		drvSupervise.setPageUrl(request.getRequestURI());
		drvSupervise
				.setCurrentPage(request.getParameter("currentpage") == null ? 0
						: Integer.parseInt(request.getParameter("currentpage")));
		List drvList = this.ezxfwService
				.getDrvBusinessSuperviseData(drvSupervise);
		drvSupervise.setDrvList(drvList);
		return "drvSupervise";
	}

	/**
	 * 02驾驶证补证/换证初审excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwDrvExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.drvCsExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 01驾驶证补证/换证初审excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwDrvListExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.drvCsListExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 02驾驶证补证/换证初审工作量excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwDrvGzlExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.drvCsGzlExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 01驾驶证补证/换证初审工作量excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwDrvGzlListExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.drvCsGzlListExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 02机动车补证/换证初审excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwJdcExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.jdcCsExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 01机动车补证/换证初审excel导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwJdcListExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.jdcCsListExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 02机动车补证/换证初审工作量数据导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwJdcGzlExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.jdcCsGzlExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 01机动车补证/换证初审工作量数据导出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String ezxfwJdcGzlListExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.jdcCsGzlListExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 驾驶证补证/换证初审
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDrvCheckList() throws Exception {
		this.ezxfwService.getDrvCheckList(request, currentpage);
		return "drvchecklist";
	}

	/**
	 * 驾驶证补证/换证初审监管查看详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDrvCheckView() throws Exception {
		this.ezxfwService.getDrvCheckView(request);
		return "drvcheckview";
	}

	/**
	 * 机动车补领行驶证/检验合格标志初审监管
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initJdcCheckList() throws Exception {
		this.ezxfwService.getJdcCheckList(request, currentpage);
		return "jdcchecklist";
	}

	/**
	 * 机动车补领行驶证/检验合格标志初审监管查看详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initJdcCheckView() throws Exception {
		this.ezxfwService.getJdcCheckView(request);
		return "jdccheckview";
	}

	/**
	 * 驾驶证补证/换证工作量统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDrvGzlList() throws Exception {
		this.ezxfwService.getDrvGzlList(request, currentpage);
		return "drvgzllist";
	}

	/**
	 * 驾驶证补证/换证工作量统计详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDrvGzlView() throws Exception {
		this.ezxfwService.getDrvGzlView(request);
		return "drvgzlview";
	}

	/**
	 * 机动车补领行驶证/检验合格标志工作量统计
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initJdcGzlList() throws Exception {
		this.ezxfwService.getJdcGzlList(request, currentpage);
		return "jdcgzllist";
	}

	/**
	 * 机动车补领行驶证/检验合格标志初审监管工作量查看详细
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initJdcGzlView() throws Exception {
		this.ezxfwService.getJdcGzlView(request);
		return "jdcgzlview";
	}

	/**
	 * 初始化驾驶证补换证信息列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initDrvLscxList() throws Exception {
		try {
			request.setAttribute("cx", "cx");
			this.setEzDrvLiceChanAppList(this.ezxfwService.getDrvLscxList(
					request, currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvlscxlist";
	}

	/**
	 * 初始化驾驶证补换证信息列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initDrvChanList() throws Exception {
		try {
			request.setAttribute("cx", "cx");
			this.setEzDrvLiceChanAppList(this.ezxfwService.getDrvChanList(
					request, currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvchanlist";
	}

	/**
	 * 初始化驾驶证信息单打印申请页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initezXxdPrintList() throws Exception {
		try {
			request.setAttribute("cx", "cx");
			this.setEzXxdPrintList(this.ezxfwService.getEzXxdPrintList(request,
					currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "ezXxdPrintlist";
	}

	/**
	 * 初始化驾驶证信息单审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initEzXxdPrintInfo() throws Exception {
		try {
			String printXh = request.getParameter("printXh");
			String xg = request.getParameter("xg");
			if (xg != null && !"".equals(xg)) {
				request.setAttribute("editType", "修改");
			} else {
				request.setAttribute("editType", "审核");
			}
			this.setEzXxdPrint(this.ezxfwService.getEzXxdPrintInfo(request,
					printXh));
			String cid = ezxfwService
					.getSfrzUserinfo(ezXxdPrint.getSqrsfzmhm());
			String xh = ezxfwService.getSfrzWxJsr(ezXxdPrint.getSqrsfzmhm());
			Map photolist = new HashMap();
			Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			photolist3 = sfrzService.getPhoto(xh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "ezXxdPrintview";
	}

	/**
	 * 审核驾驶证信息单
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updatePrintChanInfo() throws Exception {
		String printXh = request.getParameter("printXh");
		String shzt = "";
		String tbyy = request.getParameter("tbyy");
		String sqlx = request.getParameter("sqlx");
		String ywlx = request.getParameter("ywlx");
		String tpid = ezxfwService.getTpid();
		EzXxdPrintPhoto photo = new EzXxdPrintPhoto();
		photo.setPrintXh(printXh);
		photo.setSqlx(sqlx);
		photo.setTpid(tpid);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		// String
		// pressImg=request.getSession().getServletContext().getRealPath("/");
		// //获取工程绝对路径
		// picPath=ImageUtils.pressImageTest(picPath,100,200,0,0);
		picPath = ImageUtils.pressText2(ywlx, picPath, "宋体", 1, new Color(192,
				192, 192, 192), 100, 0, 0, 0);
		if (null != printXh) {
			try {
				if (picPath != null) {
					shzt = "2";
				}
				if (tbyy != null && !"".equals(tbyy)) {
					shzt = "TB";
				}
				returnInteger = this.ezxfwService.updatePrintChan(request,
						printXh, shzt, tbyy, tpid);
				if (tbyy == null || "".equals(tbyy)) {
					if (picPath != null) {
						if (picPath.exists() && picPath.isFile()) {
							// 在hibernate进行映射修改数据对象之前，将blob字段取出，不然会被修改为null,页面展示就会错误图片
							EzXxdPrintPhoto photoPrint = this.ezxfwService
									.editPrintPhoto(request, photo);
							returnInteger = this.ezxfwService.editPhoto(
									photoPrint, picPath);
						} else {
							out.println("alert('该路径不存在或文件不存在!');");
						}
					} else {
						byte[] byte_array = this.ezxfwService.getImageBlob(
								request, tpid);
						EzXxdPrintPhoto photoPrint = this.ezxfwService
								.editPrintPhoto(request, photo);
						returnInteger = this.ezxfwService.editeBlobByByte(
								byte_array, photoPrint);
					}
				}
				if (tbyy != null && !"".equals(tbyy)) {
					if (returnInteger == 0) {
						out.println("alert('审核成功!');");
						out.println("parent.opener.updatezt('" + printXh
								+ "','" + shzt + "');");
						out.println("parent.window.close();");
					} else {
						out.println("alert('审核失败!')");
					}
				} else {
					if (returnInteger > 0) {
						out.println("alert('审核成功!');");
						out.println("parent.opener.updatezt('" + printXh
								+ "','" + shzt + "');");
						out.println("parent.window.close();");

					} else {
						out.println("alert('审核失败!')");
					}
				}
			} catch (Exception e) {
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
				out.println("parent.exception('" + exceptionstr + "');");
			} finally {
				out.println("</script>");
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 初始化驾驶证信息单审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initEzXxdPrintInfoview() throws Exception {
		try {
			String printXh = request.getParameter("printXh");
			request.setAttribute("editType", "查看");
			this.setEzXxdPrint(this.ezxfwService.getEzXxdPrintInfo(request,
					printXh));
			String cid = ezxfwService
					.getSfrzUserinfo(ezXxdPrint.getSqrsfzmhm());
			String xh = ezxfwService.getSfrzWxJsr(ezXxdPrint.getSqrsfzmhm());
			Map photolist = new HashMap();
			Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			photolist3 = sfrzService.getPhoto(xh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "ezXxdPrintview";
	}

	/**
	 * 初始化驾驶证补换证信息审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initDrvChanInfo() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			String xg = request.getParameter("xg");
			if (xg != null && !"".equals(xg)) {
				request.setAttribute("editType", "修改");
			} else {
				request.setAttribute("editType", "审核");
			}
			this.setEzDrvLiceChanApp(this.ezxfwService.getDrvChanInfo(request,
					wwlsh));
			String cid = ezxfwService.getSfrzUserinfo(ezDrvLiceChanApp
					.getSfzmhm());
			String xh = ezxfwService.getSfrzWxJsr(ezDrvLiceChanApp.getJszhm());
			if ("A".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融");
			} else if ("B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("面签");
			} else if ("C".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信");
			} else if ("C+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融+面签");
			}
			if ("0".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("女");
			} else if ("1".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("男");
			}
			Map photolist = new HashMap();
			Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			photolist3 = sfrzService.getPhoto(xh);
			photolist2 = sfrzService.getPhoto(wwlsh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
			request.setAttribute("xphzbh", ezDrvLiceChanApp.getXphzbh());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvchanview";
	}

	/**
	 * 初始化驾驶证补换证信息查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initDrvChanInfoview() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			request.setAttribute("editType", "查看");
			this.setEzDrvLiceChanApp(this.ezxfwService.getDrvChanInfo(request,
					wwlsh));
			String cid = ezxfwService.getSfrzUserinfo(ezDrvLiceChanApp
					.getSfzmhm());
			String xh = ezxfwService.getSfrzWxJsr(ezDrvLiceChanApp.getJszhm());
			if ("A".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融");
			} else if ("B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("面签");
			} else if ("C".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信");
			} else if ("C+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融+面签");
			}
			if ("0".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("女");
			} else if ("1".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("男");
			}
			Map photolist = new HashMap();
			// Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			String xphzbh = "";
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			Map photolist2 = new HashMap();
			photolist3 = sfrzService.getPhoto(xh);
			photolist2 = sfrzService.getPhoto(wwlsh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
			request.setAttribute("xphzbh", ezDrvLiceChanApp.getXphzbh());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvchanview";
	}

	/*
	 * 驾驶证 根据外网流水号查询数据 给表赋值
	 */
	public String drvshowPrint() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			this.setEzDrvLiceChanApp(this.ezxfwService.getDrvChanInfo(request,
					wwlsh));
			if ("A".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融");
			} else if ("B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("面签");
			} else if ("C".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信");
			} else if ("C+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezDrvLiceChanApp.getYhly())) {
				ezDrvLiceChanApp.setYhly("金融+面签");
			}
			if ("0".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("女");
			} else if ("1".equals(ezDrvLiceChanApp.getXb())) {
				ezDrvLiceChanApp.setXb("男");
			}
			String sfzhm = ezDrvLiceChanApp.getSfzmhm();
			request.setAttribute("s1", sfzhm.charAt(0));
			request.setAttribute("s2", sfzhm.charAt(1));
			request.setAttribute("s3", sfzhm.charAt(2));
			request.setAttribute("s4", sfzhm.charAt(3));
			request.setAttribute("s5", sfzhm.charAt(4));
			request.setAttribute("s6", sfzhm.charAt(5));
			request.setAttribute("s7", sfzhm.charAt(6));
			request.setAttribute("s8", sfzhm.charAt(7));
			request.setAttribute("s9", sfzhm.charAt(8));
			request.setAttribute("s10", sfzhm.charAt(9));
			request.setAttribute("s11", sfzhm.charAt(10));
			request.setAttribute("s12", sfzhm.charAt(11));
			request.setAttribute("s13", sfzhm.charAt(12));
			request.setAttribute("s14", sfzhm.charAt(13));
			request.setAttribute("s15", sfzhm.charAt(14));
			request.setAttribute("s16", sfzhm.charAt(15));
			request.setAttribute("s17", sfzhm.charAt(16));
			request.setAttribute("s18", sfzhm.charAt(17));
			Date date = new Date();
			request.setAttribute("date", date);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvshowPrint";
	}

	/**
	 * 审核驾驶证补换证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateDrvChanInfo() throws Exception {
		String wwlsh = request.getParameter("wwlsh");
		String zt = request.getParameter("zt");
		String sm = request.getParameter("sm");
		if (null != wwlsh) {
			response.setCharacterEncoding("GBK");// IE用GBK FF用UTF-8 问题待解决
			PrintWriter out = response.getWriter();
			try {
				this.setReturnInteger(ezxfwService.updateDrvChan(request,
						wwlsh, zt, sm));
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				String exceptionstr = "异常信息:";
				e.printStackTrace();
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				String estr = e.getMessage();
				if (estr != null) {
					estr = estr.replaceAll("\r", "");
					estr = estr.replaceAll("\n", "");
					estr = estr.replaceAll("\t", "");
					estr = estr.replaceAll("\f", "");
					estr = estr.replaceAll("\b", "");
					exceptionstr += estr + "</br>文件名:"
							+ stackTraceElement.getFileName() + "</br>行数:"
							+ stackTraceElement.getLineNumber() + "</br>方法名:"
							+ stackTraceElement.getMethodName();
				} else {
					exceptionstr += " 获取连接异常";
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println("异常信息：" + exceptionstr);
				out.flush();
				out.close();
			}
		}
		return null;
	}

	/**
	 * 审核居住证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkJzzinfo() throws Exception {
		String jzzno = request.getParameter("jzzno");
		String xm = request.getParameter("xm");
		response.setCharacterEncoding("GBK");// IE用GBK FF用UTF-8 问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(sfrzService.getJzzinfo(jzzno, xm));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if (estr != null) {
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			} else {
				exceptionstr += " 获取连接异常";
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println("异常信息：" + exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 初始化机动车流水查询列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initJdcLscxList() throws Exception {
		try {
			this.setEzJdcChanAppList(this.ezxfwService.getJdcLscxList(request,
					currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdclscxlist";
	}

	/**
	 * 初始化机动车补换证信息列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initJdcChanList() throws Exception {
		try {
			request.setAttribute("cx", "cx");
			this.setEzJdcChanAppList(this.ezxfwService.getJdcChanList(request,
					currentpage));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdcchanlist";
	}

	/**
	 * 初始化驾驶证信息单打印申请页面自动提取数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getTqPrint() throws Exception {
		try {
			this.setEzXxdPrintList(this.ezxfwService.getTqprint(request,
					currentpage));
			for (int i = 0; i < ezXxdPrintList.size(); i++) {
				EzXxdPrint print = (EzXxdPrint) ezXxdPrintList.get(i);
				if (print.getShzt().equals("1")) {
					break;
				}
				if (print.getShzt().equals("0")) {
					returnInteger = ezxfwService.updateTqprint(request,
							ezXxdPrintList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "ezXxdPrintlist";
	}

	/**
	 * 初始化驾驶证补换证信息列表页面自动提取数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getTqdrvs() throws Exception {
		try {
			this.setEzDrvLiceChanAppList(this.ezxfwService.getTqdrv(request,
					currentpage));
			for (int i = 0; i < ezDrvLiceChanAppList.size(); i++) {
				EzDrvLiceChanApp edlca1 = (EzDrvLiceChanApp) ezDrvLiceChanAppList
						.get(i);
				if (edlca1.getZhclzt().equals("3")) {
					break;
				}
				if (edlca1.getZhclzt().equals("0")) {
					returnInteger = ezxfwService.updateTqdrv(request,
							ezDrvLiceChanAppList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "drvchanlist";
	}

	/**
	 * 初始化机动车补换证信息审核页面自动提取数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getTqjdcs() throws Exception {
		try {
			this.setEzJdcChanAppList(this.ezxfwService.getTqjdc(request,
					currentpage));
			if (ezJdcChanAppList != null) {
				for (int i = 0; i < ezJdcChanAppList.size(); i++) {
					EzJdcChanApp edlca1 = (EzJdcChanApp) ezJdcChanAppList
							.get(i);
					if (edlca1.getZhclzt().equals("3")) {
						break;
					}
					if (edlca1.getZhclzt().equals("0")) {
						returnInteger = ezxfwService.updateTqjdc(request,
								ezJdcChanAppList);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdcchanlist";
	}

	/**
	 * 初始化机动车补换证信息审核页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initJdcChanInfo() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			String xg = request.getParameter("xg");
			if (xg != null && !"".equals(xg)) {
				request.setAttribute("editType", "修改");
			} else {
				request.setAttribute("editType", "审核");
			}
			this.setEzJdcChanApp(this.ezxfwService.getJdcChanInfo(wwlsh));
			String cid = ezxfwService.getSfrzUserinfo(ezJdcChanApp.getSfzmhm());
			String xh = ezxfwService.getSfrzWxJdc(ezJdcChanApp.getSfzmhm());
			if ("A".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融");
			} else if ("B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("面签");
			} else if ("C".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信");
			} else if ("C+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融+面签");
			}
			Map photolist = new HashMap();
			Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			photolist2 = sfrzService.getPhoto(wwlsh);
			photolist3 = sfrzService.getPhoto(xh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
			// request.setAttribute("xszzp", ezJdcChanApp.getXszzp());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdcchanview";
	}

	/**
	 * 初始化机动车补换证信息查看页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initJdcChanInfoview() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			request.setAttribute("editType", "查看");
			this.setEzJdcChanApp(this.ezxfwService.getJdcChanInfo(wwlsh));
			String cid = ezxfwService.getSfrzUserinfo(ezJdcChanApp.getSfzmhm());
			String xh = ezxfwService.getSfrzWxJdc(ezJdcChanApp.getSfzmhm());
			if ("A".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融");
			} else if ("B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("面签");
			} else if ("C".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信");
			} else if ("C+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融+面签");
			}
			Map photolist = new HashMap();
			Map photolist2 = new HashMap();
			Map photolist3 = new HashMap();
			if (null != cid) {
				photolist = sfrzService.getPhoto(cid);
			}
			photolist3 = sfrzService.getPhoto(xh);
			photolist2 = sfrzService.getPhoto(wwlsh);
			request.setAttribute("photolist", photolist);
			request.setAttribute("photolist2", photolist2);
			request.setAttribute("photolist3", photolist3);
			// request.setAttribute("xszzp", ezJdcChanApp.getXszzp());
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdcchanview";
	}

	/*
	 * 机动车驾驶证 根据外网流水号查询数据 给表赋值
	 */
	public String jdcshowPrint() throws Exception {
		try {
			String wwlsh = request.getParameter("wwlsh");
			this.setEzJdcChanApp(this.ezxfwService.getJdcChanInfo(wwlsh));
			if ("A".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融");
			} else if ("B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("面签");
			} else if ("C".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信");
			} else if ("C+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("微信+面签");
			} else if ("A+B".equals(ezJdcChanApp.getYhly())) {
				ezJdcChanApp.setYhly("金融+面签");
			}
			if ("B".equals(ezJdcChanApp.getYwyy())) {
				ezJdcChanApp.setYwyy("补证");
				request.setAttribute("ywyy", ezJdcChanApp.getYwyy());
			} else if ("H".equals(ezJdcChanApp.getYwyy())) {
				ezJdcChanApp.setYwyy("换证");
				request.setAttribute("ywyy", ezJdcChanApp.getYwyy());
			}
			Date date = new Date();
			request.setAttribute("date", date);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "jdcshowPrint";
	}

	/**
	 * 审核机动车补换证信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateJdcChanInfo() throws Exception {
		String wwlsh = request.getParameter("wwlsh");
		String zt = request.getParameter("zt");
		String sm = request.getParameter("sm");
		if (null != wwlsh) {
			response.setCharacterEncoding("GBK");// IE用GBK FF用UTF-8 问题待解决
			PrintWriter out = response.getWriter();
			try {
				this.setReturnInteger(ezxfwService.updateJdcChan(request,
						wwlsh, zt, sm));
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println(returnInteger);
				out.flush();
				out.close();
			} catch (Exception e) {
				String exceptionstr = "异常信息:";
				e.printStackTrace();
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				String estr = e.getMessage();
				if (estr != null) {
					estr = estr.replaceAll("\r", "");
					estr = estr.replaceAll("\n", "");
					estr = estr.replaceAll("\t", "");
					estr = estr.replaceAll("\f", "");
					estr = estr.replaceAll("\b", "");
					exceptionstr += estr + "</br>文件名:"
							+ stackTraceElement.getFileName() + "</br>行数:"
							+ stackTraceElement.getLineNumber() + "</br>方法名:"
							+ stackTraceElement.getMethodName();
				} else {
					exceptionstr += " 获取连接异常";
				}
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				out.println("异常信息：" + exceptionstr);
				out.flush();
				out.close();
			}
		}
		return null;
	}

	// 验证购置税编号
	public String checkGzsbh() throws Exception {
		String clsbdh = request.getParameter("clsbdh");
		response.setCharacterEncoding("GBK");// IE用GBK FF用UTF-8 问题待解决
		PrintWriter out = response.getWriter();
		try {
			this.setReturnInteger(ezxfwService.getCheckGzsbh(clsbdh));
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println(returnInteger);
			out.flush();
			out.close();
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if (estr != null) {
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			} else {
				exceptionstr += " 获取连接异常";
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println("异常信息：" + exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 初始化补换证发证列表页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String initChanList() throws Exception {
		try {
			String type = request.getParameter("type");
			if ("drv".equals(type)) {
				this.setEzDrvLiceChanAppList(this.ezxfwService.getChanList(
						request, currentpage, type));
			} else if ("jdc".equals(type)) {
				this.setEzJdcChanAppList(this.ezxfwService.getChanList(request,
						currentpage, type));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "chanlist";
	}

	/**
	 * 初始化补换证发证列表详细页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initChanInfoview() throws Exception {
		try {
			String type = request.getParameter("type");
			String typeval = "";
			if ("drv".equals(type)) {
				typeval = "驾驶证";
			} else if ("jdc".equals(type)) {
				typeval = "行驶证";
			} else if ("drvv".equals(type)) {
				String pch = request.getParameter("pch");
				typeval = "驾驶证";
				ezxfwService.getChanViewList(request, pch, type);
			} else if ("jdcv".equals(type)) {
				String pch = request.getParameter("pch");
				typeval = "行驶证";
				ezxfwService.getChanViewList(request, pch, type);
			}
			request.setAttribute("typeval", typeval);
			request.setAttribute("type", type);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return "chanview";
	}

	/**
	 * 根据统一版流水号获取app数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getChanList() throws Exception {
		PrintWriter out = response.getWriter();
		try {
			String tyblsh = request.getParameter("tyblsh");
			String type = request.getParameter("type");
			List chanlist = this.ezxfwService.getChaninfoByflow(tyblsh, type);
			if (null != chanlist) {
				String jsonString = JsonUtil.list2json(chanlist);
				response.setContentType("text/xml; charset=UTF-8");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);
				response.getWriter().print(jsonString);
			}
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if (estr != null) {
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			} else {
				exceptionstr += " 获取连接异常";
			}
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			out.println("异常信息：" + exceptionstr);
			out.flush();
			out.close();
		}
		return null;
	}

	// 导出
	public String ezxfwchanExcel() throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			ExportToExcel.ezxfwchanExcel(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return NONE;
	}

	/**
	 * 复核单个批量审核未成功的机动车/驾驶证
	 */
	public String updateChanDg() throws Exception {
		response.setCharacterEncoding("GBK");
		String type = request.getParameter("type");
		String tyblsh = request.getParameter("tyblsh");
		String applsh = request.getParameter("applsh");
		PrintWriter out = response.getWriter();
		try {
			// 保存信息
			returnInteger = this.ezxfwService.updateChanDg(request, tyblsh,
					type, applsh);
			out.println(returnInteger);
		} catch (Exception e) {
			String exceptionstr = "异常信息:";
			e.printStackTrace();
			logger.error(e);
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			String estr = e.getMessage();
			if (estr != null) {
				estr = estr.replaceAll("\r", "");
				estr = estr.replaceAll("\n", "");
				estr = estr.replaceAll("\t", "");
				estr = estr.replaceAll("\f", "");
				estr = estr.replaceAll("\b", "");
				exceptionstr += estr + "</br>文件名:"
						+ stackTraceElement.getFileName() + "</br>行数:"
						+ stackTraceElement.getLineNumber() + "</br>方法名:"
						+ stackTraceElement.getMethodName();
			} else {
				exceptionstr += " 获取连接异常";
			}
			out.println(exceptionstr);
			out.flush();
			out.close();
		} finally {
			out.flush();
			out.close();
		}
		return null;
	}

	/**
	 * 编辑app数据源数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String editChan() throws Exception {
		if (appsval != null) {
			System.out.println(appsval);
			response.setCharacterEncoding("GBK");
			String type = request.getParameter("type");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			try {
				// 保存信息
				returnInteger = this.ezxfwService.updateChan(request, appsval,
						type);
				if (returnInteger == 0) {
					out.println("alert('保存成功!');");
					out.println("parent.initform();");
				} else {
					out.println("alert('保存失败!')");
				}
			} catch (Exception e) {
				out.println("parent.closechuli();");
				String exceptionstr = "异常信息:";
				e.printStackTrace();
				StackTraceElement stackTraceElement = e.getStackTrace()[0];
				String estr = e.getMessage();
				if (estr != null) {
					estr = estr.replaceAll("\r", "");
					estr = estr.replaceAll("\n", "");
					estr = estr.replaceAll("\t", "");
					estr = estr.replaceAll("\f", "");
					estr = estr.replaceAll("\b", "");
					exceptionstr += estr + "</br>文件名:"
							+ stackTraceElement.getFileName() + "</br>行数:"
							+ stackTraceElement.getLineNumber() + "</br>方法名:"
							+ stackTraceElement.getMethodName();
				}
				out.println("parent.exception('" + exceptionstr + "');");
			}
			out.println("</script>");
			return null;
		} else {
			request.setAttribute("errorTip", "服务器忙,请稍后再试!");
			return "Exception";
		}
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public EzDrvLiceChanApp getEzDrvLiceChanApp() {
		return ezDrvLiceChanApp;
	}

	public void setEzDrvLiceChanApp(EzDrvLiceChanApp ezDrvLiceChanApp) {
		this.ezDrvLiceChanApp = ezDrvLiceChanApp;
	}

	public List<EzDrvLiceChanApp> getEzDrvLiceChanAppList() {
		return ezDrvLiceChanAppList;
	}

	public void setEzDrvLiceChanAppList(
			List<EzDrvLiceChanApp> ezDrvLiceChanAppList) {
		this.ezDrvLiceChanAppList = ezDrvLiceChanAppList;
	}

	public EzxfwService getEzxfwService() {
		return ezxfwService;
	}

	public void setEzxfwService(EzxfwService ezxfwService) {
		this.ezxfwService = ezxfwService;
	}

	public Integer getReturnInteger() {
		return returnInteger;
	}

	public void setReturnInteger(Integer returnInteger) {
		this.returnInteger = returnInteger;
	}

	public EzJdcChanApp getEzJdcChanApp() {
		return ezJdcChanApp;
	}

	public void setEzJdcChanApp(EzJdcChanApp ezJdcChanApp) {
		this.ezJdcChanApp = ezJdcChanApp;
	}

	public List<EzJdcChanApp> getEzJdcChanAppList() {
		return ezJdcChanAppList;
	}

	public void setEzJdcChanAppList(List<EzJdcChanApp> ezJdcChanAppList) {
		this.ezJdcChanAppList = ezJdcChanAppList;
	}

	public String getAppsval() {
		return appsval;
	}

	public void setAppsval(String appsval) {
		this.appsval = appsval;
	}

	public SfrzService getSfrzService() {
		return sfrzService;
	}

	public void setSfrzService(SfrzService sfrzService) {
		this.sfrzService = sfrzService;
	}

}
