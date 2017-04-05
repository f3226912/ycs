package com.ycszh.common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.hbm.ezxfw.EzDrvLiceChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzJdcChanApp;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportToExcel {	
	@SuppressWarnings("unchecked")
	public static String mjclExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getAttribute("listCount");

			String fileNameTemp = "免检车辆工作量统计报表";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("免检车辆工作量统计", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 15);
			ws.setColumnView(4, 25);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 15);
			
			ws.addCell(new Label(0, 0, "流水号", format));
			ws.addCell(new Label(1, 0, "序号", format));
			ws.addCell(new Label(2, 0, "号牌号码", format));
			ws.addCell(new Label(3, 0, "号牌种类", format));
			ws.addCell(new Label(4, 0, "所有人", format));
			ws.addCell(new Label(5, 0, "车辆型号", format));
			ws.addCell(new Label(6, 0, "申请日期", format));
			ws.addCell(new Label(7, 0, "办结日期", format));
			ws.addCell(new Label(8, 0, "管理部门", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			// 数据
			for (int i = 0; i < list.size(); i++) {
				Object[] obj =  (Object[]) list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0].toString(), format));
				ws.addCell(new Label(1, i + 1, obj[1].toString(), format));
				ws.addCell(new Label(2, i + 1, obj[2].toString(), format));
				ws.addCell(new Label(3, i + 1, SysConst.CODES.get(obj[3].toString()), format));
				ws.addCell(new Label(4, i + 1, obj[4].toString(), format));
				ws.addCell(new Label(5, i + 1, obj[5].toString(), format));
				ws.addCell(new Label(6, i + 1, obj[6].toString(), format));
				if(obj[7]!=null){
					ws.addCell(new Label(7, i + 1, obj[7].toString(), format));
				}else{
					ws.addCell(new Label(7, i + 1, null, format));
				}
				ws.addCell(new Label(8, i + 1, obj[8].toString(), format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String mjclTjExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getAttribute("deptTj");

			String fileNameTemp = "免检车辆部门统计报表";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("免检车辆部门统计", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 15);
			
			ws.addCell(new Label(0, 0, "序号", format));
			ws.addCell(new Label(1, 0, "管理部门", format));
			ws.addCell(new Label(2, 0, "总数", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			// 数据
			for (int i = 0; i < list.size(); i++) {
				Object[] obj =  (Object[]) list.get(i);
				ws.addCell(new Label(0, i + 1, i+1+"", format));
				ws.addCell(new Label(1, i + 1, obj[1].toString(), format));
				ws.addCell(new Label(2, i + 1, obj[2].toString(), format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String xgslExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getAttribute("listCount");

			String fileNameTemp = "综合应用平台业务受理情况统计报表";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("综合应用平台业务受理情况统计", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 15);
			ws.setColumnView(4, 25);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 15);
			
			ws.addCell(new Label(0, 0, "流水号", format));
			ws.addCell(new Label(1, 0, "序号", format));
			ws.addCell(new Label(2, 0, "号牌号码", format));
			ws.addCell(new Label(3, 0, "号牌种类", format));
			ws.addCell(new Label(4, 0, "所有人", format));
			ws.addCell(new Label(5, 0, "车辆型号", format));
			ws.addCell(new Label(6, 0, "申请日期", format));
			ws.addCell(new Label(7, 0, "办结日期", format));
			ws.addCell(new Label(8, 0, "管理部门", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			// 数据
			for (int i = 0; i < list.size(); i++) {
				Object[] obj =  (Object[]) list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0].toString(), format));
				ws.addCell(new Label(1, i + 1, obj[1].toString(), format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2]+"", format));
				ws.addCell(new Label(3, i + 1, SysConst.CODES.get(obj[3].toString()), format));
				ws.addCell(new Label(4, i + 1, obj[4].toString(), format));
				ws.addCell(new Label(5, i + 1, obj[5].toString(), format));
				ws.addCell(new Label(6, i + 1, obj[6].toString(), format));
				if(obj[7]!=null){
					ws.addCell(new Label(7, i + 1, obj[7].toString(), format));
				}else{
					ws.addCell(new Label(7, i + 1, null, format));
				}
				ws.addCell(new Label(8, i + 1, obj[8].toString(), format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String yjgzExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getAttribute("listCount");

			String fileNameTemp = "车辆限购违规业务办理统计分析";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("车辆限购违规业务办理统计分析", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 15);
			ws.setColumnView(3, 15);
			ws.setColumnView(4, 25);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			ws.setColumnView(7, 20);
			ws.setColumnView(8, 15);
			ws.setColumnView(9, 15);
			ws.setColumnView(10, 500);
			
			ws.addCell(new Label(0, 0, "流水号", format));
			ws.addCell(new Label(1, 0, "身份证明号码", format));
			ws.addCell(new Label(2, 0, "姓名", format));
			ws.addCell(new Label(3, 0, "车辆类型", format));
			ws.addCell(new Label(4, 0, "所有人", format));
			ws.addCell(new Label(5, 0, "业务类型", format));
			ws.addCell(new Label(6, 0, "处理日期", format));
			ws.addCell(new Label(7, 0, "经办人", format));
			ws.addCell(new Label(8, 0, "管理部门名称", format));
			ws.addCell(new Label(9, 0, "违规种类", format));
			ws.addCell(new Label(10, 0, "短信内容", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			// 数据
			for (int i = 0; i < list.size(); i++) {
				Object[] obj =  (Object[]) list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0]+"", format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1]+"", format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2]+"", format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3]+"", format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4]+"", format));
				ws.addCell(new Label(5, i + 1, obj[5]==null?"":obj[5]+"", format));
				if(obj[6]!=null){
					ws.addCell(new Label(6, i + 1, obj[6]==null?"":obj[6]+"", format));
				}else{
					ws.addCell(new Label(6, i + 1, null, format));
				}
				ws.addCell(new Label(7, i + 1, obj[7]==null?"":obj[7]+"", format));
				ws.addCell(new Label(8, i + 1, obj[8]==null?"":obj[8]+"", format));
				ws.addCell(new Label(9, i + 1, obj[9]==null?"":obj[9]+"", format));
				ws.addCell(new Label(10, i + 1, obj[10]==null?"":obj[10]+"", format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public static String ezxfwchanExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezxfwdatachanlist");

			String fileNameTemp = "E中心业务服务与制证情况统计报表";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			// 创建表格标题
			ws = wwb.createSheet("E中心业务服务与制证情况统计报表", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 50);
			
			ws.addCell(new Label(0, 0, "批次号", format));
			ws.addCell(new Label(1, 0, "统一版流水号", format));
			ws.addCell(new Label(2, 0, "app流水号", format));
			ws.addCell(new Label(3, 0, "申报信息", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			// 数据
			for (int i = 0; i < list.size(); i++) {
				List obj =  (List) list.get(i);
				ws.addCell(new Label(0, i + 1, obj.get(3)==null?"":obj.get(3)+"", format));
				ws.addCell(new Label(1, i + 1, obj.get(0)==null?"":obj.get(0)+"", format));
				ws.addCell(new Label(2, i + 1, obj.get(1)==null?"":obj.get(1)+"", format));
				ws.addCell(new Label(3, i + 1, obj.get(2)==null?"":obj.get(2)+"", format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
			e.printStackTrace();
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String drvCsExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezDrvLiceCheckView");

			String fileNameTemp = "驾驶证补证换证初审监管统计分析02";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("驾驶证补证换证初审监管统计分析02", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 40);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "批次号", format));
			ws.addCell(new Label(3, 0, "自动分配数量", format));
			ws.addCell(new Label(4, 0, "已审核数量", format));
			ws.addCell(new Label(5, 0, "未审核数量", format));
			ws.addCell(new Label(6, 0, "分配数据时间", format));
			

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(5, i + 1, obj[5]==null?"":obj[5].toString() , format));
				ws.addCell(new Label(6, i + 1, obj[6]==null?"":obj[6].toString() , format));

			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String drvCsListExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezDrvLiceCheckList");

			String fileNameTemp = "驾驶证补证换证初审监管统计分析01";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("驾驶证补证换证初审监管统计分析01", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 40);
			ws.setColumnView(6, 40);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "业务类型", format));
			ws.addCell(new Label(3, 0, "自动分配数量", format));
			ws.addCell(new Label(4, 0, "已审核数量", format));
			ws.addCell(new Label(5, 0, "未审核数量", format));
			ws.addCell(new Label(6, 0, "分配数据时间", format));
			
			

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(5, i + 1, obj[5]==null?"":obj[5].toString() , format));
				ws.addCell(new Label(6, i + 1, obj[6]==null?"":obj[6].toString() , format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String drvCsGzlExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezDrvLiceGzlView");

			String fileNameTemp = "驾驶证补证与换证初审工作量统计02";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("驾驶证补证换证初审工作量统计02", 0);
			
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "批次号", format));
			ws.addCell(new Label(3, 0, "已审核数量", format));
			ws.addCell(new Label(4, 0, "未审核数量", format));
		

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));

			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String drvCsGzlListExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezDrvLiceGzlList");

			String fileNameTemp = "驾驶证补证与换证初审工作量统计01";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("驾驶证补证换证初审工作量统计01", 0);
			
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
	
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "业务类型", format));
			ws.addCell(new Label(3, 0, "已审核数量", format));
			ws.addCell(new Label(4, 0, "未审核数量", format));
		

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			
			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[5]==null?"":obj[5].toString() , format));

			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String jdcCsExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezJdcCheckView");

			String fileNameTemp = "机动车补领行驶证或检验合格标志初审监管统计分析02";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("机动车补领行驶证或检验合格标志初审监管02", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 40);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "批次号", format));
			ws.addCell(new Label(3, 0, "自动分配数量", format));
			ws.addCell(new Label(4, 0, "已审核数量", format));
			ws.addCell(new Label(5, 0, "未审核数量", format));
			ws.addCell(new Label(6, 0, "分配数据时间", format));
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(5, i + 1, obj[5]==null?"":obj[5].toString() , format));
				ws.addCell(new Label(6, i + 1, obj[6]==null?"":obj[6].toString() , format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String jdcCsListExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezJdcCheckList");

			String fileNameTemp = "机动车补领行驶证或检验合格标志初审监管统计分析01";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("机动车补领行驶证或检验合格标志初审监管01", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			ws.setColumnView(5, 20);
			ws.setColumnView(6, 20);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "业务类型", format));
			ws.addCell(new Label(3, 0, "自动分配数量", format));
			ws.addCell(new Label(4, 0, "已审核数量", format));
			ws.addCell(new Label(5, 0, "未审核数量", format));
			ws.addCell(new Label(6, 0, "分配数据时间", format));
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(5, i + 1, obj[5]==null?"":obj[5].toString() , format));
				ws.addCell(new Label(6, i + 1, obj[6]==null?"":obj[6].toString() , format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String jdcCsGzlExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezJdcGzlView");

			String fileNameTemp = "机动车补领行驶证或检验合格标志初审工作量统计02";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("机动车补领行驶证与检验合格标志初审工作量统计02", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(4, 20);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "批次号", format));
			ws.addCell(new Label(3, 0, "已审核数量", format));
			ws.addCell(new Label(4, 0, "未审核数量", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[3]==null?"":obj[3].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[4]==null?"":obj[4].toString() , format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 驾驶证初审数据导出
	 */
	public static String jdcCsGzlListExcel(HttpServletRequest request,HttpServletResponse response) {
		String result = "SUCCESS";
		WritableWorkbook wwb = null; // Excel工作簿
		WritableSheet ws = null; // Excel sheet
		WritableCellFormat format = null;
		OutputStream out = null;
		try {
			out = response.getOutputStream();
			List list = (List) request.getSession().getAttribute("ezJdcGzlList");

			String fileNameTemp = "机动车补领行驶证或检验合格标志初审工作量统计01";

			if (request.getHeader("User-Agent").toLowerCase().indexOf("firefox") > 0) {
				fileNameTemp = new String(fileNameTemp.getBytes("UTF-8"),"ISO8859-1");
			}else if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0) {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			} else {
				fileNameTemp = URLEncoder.encode(fileNameTemp, "UTF-8");
			}
			response.setContentType("application/x-excel");
			response.setHeader("Content-disposition", "attachment; filename="+ fileNameTemp + ".xls");
			wwb = Workbook.createWorkbook(out);
			// 初始化表格
			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);
			WritableFont wtf = new WritableFont(WritableFont.createFont("宋体"),12, WritableFont.BOLD, false);
			format = new WritableCellFormat(wtf);

			
			// 创建表格标题
			ws = wwb.createSheet("机动车补领行驶证与检验合格标志初审工作量统计01", 0);
			
			ws.setColumnView(0, 20);
			ws.setColumnView(1, 20);
			ws.setColumnView(2, 20);
			ws.setColumnView(3, 20);
			ws.setColumnView(3, 20);
			
			ws.addCell(new Label(0, 0, "分配账号", format));
			ws.addCell(new Label(1, 0, "分配姓名", format));
			ws.addCell(new Label(2, 0, "业务类型", format));
			ws.addCell(new Label(3, 0, "已审核数量", format));
			ws.addCell(new Label(4, 0, "未审核数量", format));

			format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,NumberFormats.TEXT);

			for (int i = 0; i < list.size(); i++) {
				Object[] obj = (Object[])list.get(i);
				ws.addCell(new Label(0, i + 1, obj[0]==null?"":obj[0].toString() , format));
				ws.addCell(new Label(1, i + 1, obj[1]==null?"":obj[1].toString() , format));
				ws.addCell(new Label(2, i + 1, obj[2]==null?"":obj[2].toString() , format));
				ws.addCell(new Label(3, i + 1, obj[4]==null?"":obj[4].toString() , format));
				ws.addCell(new Label(4, i + 1, obj[5]==null?"":obj[5].toString() , format));
			}
			wwb.write();
		} catch (Exception e) {
			result = "FAIL";
		} finally {
			try {
				try {
					if (wwb != null) {
						wwb.close();
					}
					if (out != null) {
						out.close();
					}
				} catch (WriteException e) {
					result = "FAIL";
				}
			} catch (IOException e) {
				result = "FAIL";
			}
		}
		return result;
	}
}
