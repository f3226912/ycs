package com.ycszh.ssh.action.dagl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;


import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.dagl.SlgZdjdcjsygl;
import com.ycszh.ssh.service.dagl.SlgZdjdcjsrglService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.JxlTools;

/**
 * @author Administrator
 * @日期：2015-11-13
 * @描述：重点机动车驾驶人管理Action类
 */
public class SlgZdjdcjsrglAction extends BaseAction{
	private static final Logger log = Logger.getLogger(SlgZdjdcjsrglAction.class);
	private SlgZdjdcjsrglService slgZdjdcjsrglService;
	private int currentpage = 1;
	private SlgZdjdcjsygl zdjdcjsr;
	private File excelFile;
	
	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public SlgZdjdcjsrglService getSlgZdjdcjsrglService() {
		return slgZdjdcjsrglService;
	}

	public void setSlgZdjdcjsrglService(SlgZdjdcjsrglService slgZdjdcjsrglService) {
		this.slgZdjdcjsrglService = slgZdjdcjsrglService;
	}
	
	public SlgZdjdcjsygl getZdjdcjsr() {
		return zdjdcjsr;
	}

	public void setZdjdcjsr(SlgZdjdcjsygl zdjdcjsr) {
		this.zdjdcjsr = zdjdcjsr;
	}

	/**
	 * 查询重点驾驶人机动车集合
	 * @return
	 * @throws Exception
	 */
	public String initZdjdcjsrList () throws Exception{
		slgZdjdcjsrglService.initZdjdcjsrList(request,currentpage);
		return "list";
	}
	
	/**
	 * 编辑重点驾驶人机动车集合(页面初始)
	 * @return
	 * @throws Exception
	 */
	public String initEditZdjdcjsr () throws Exception{
		String type = request.getParameter("type");
		String editType = "";
		slgZdjdcjsrglService.initEditZdjdcjsr(request);
		if("insert".equals(type)){
			editType = "添加";
		}else{
			editType = "修改";
		}
		return "initEdit";
	}
	
	/**
	 * 编辑重点驾驶人机动车集合（增改操作）
	 * @return
	 * @throws Exception
	 */
	public String editZdjdcjsr() throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		String result = "";
		try{
			slgZdjdcjsrglService.editZdjdcjsr(request, zdjdcjsr);
		}catch(Exception e){
			System.out.println(e);
			result = "异常信息：calss:SlgZdjdcjsrglAction|method:editZdjdcjsr|erroinfo:"+e;
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}
	
	/**
	 * 删除重点驾驶人机动车
	 * @return
	 * @throws Exception
	 */
	public String delZdjdcjsr() throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		String result = "";
		try{
			slgZdjdcjsrglService.delZdjdcjsr(request);
		}catch(Exception e){
			System.out.println(e);
			result = "异常信息：calss:SlgZdjdcjsrglAction|method:delZdjdcjsr|erroinfo:"+e;
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}
	
	public String uniqueCheck() throws Exception{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out =response.getWriter();
		String result = "";
		try{
			int count = slgZdjdcjsrglService.uniqueCheck(request);
			result = count+"";
		}catch(Exception e){
			System.out.println(e);
			result = "异常信息：calss:SlgZdjdcjsrglAction|method:uniqueCheck|erroinfo:"+e;
		}
		out.write(result);
		out.flush();
		out.close();
		return NONE;
	}
	
	/**
	 * excel数据导入
	 * @return
	 * @throws Exception
	 */
	public String putExcel() throws Exception{
		String vehDrv = request.getParameter("vehDrv");
		List<SlgZdjdcjsygl> list =  readExcel(excelFile,"VEH");
		slgZdjdcjsrglService.putExcel(list,request);
		return "put_list";
	}
	
	/**
	 * 读取excel文件
	 * @param file
	 * @param vehDrv
	 * @return
	 * @throws Exception
	 */
	public List<SlgZdjdcjsygl> readExcel(File file,String vehDrv) throws Exception{
		InputStream is = new FileInputStream(excelFile);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		List<SlgZdjdcjsygl> zdjdcjsrList = new ArrayList<SlgZdjdcjsygl>();
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				SlgZdjdcjsygl zdjdcjsr = new SlgZdjdcjsygl();
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				HSSFCell cell1 = hssfRow.getCell(1);
				HSSFCell cell2 = hssfRow.getCell(2);
				HSSFCell cell3 = hssfRow.getCell(3);
				HSSFCell cell4 = hssfRow.getCell(4);
				HSSFCell cell5 = hssfRow.getCell(5);
				if("VEH".equals(vehDrv)){
					zdjdcjsr.setHphm(cell1.toString());
					zdjdcjsr.setHpzl(cell2.toString());
					zdjdcjsr.setBm(cell3.toString());
					zdjdcjsr.setClsbdh(cell4.toString());
					try{
						zdjdcjsr.setZcdjrq(cell5.getDateCellValue());
					}catch(IllegalStateException e){
						String c = cell5.toString().replaceAll("[^\\d\\s:-]", "");
						zdjdcjsr.setZcdjrq(DateUtil.string2Date(c.trim(), "yyyy-MM-dd HH:mm:ss"));
					}
					zdjdcjsr.setVehDrv("VEH");
					zdjdcjsr.setYjnr("档案科布控");
				}
				zdjdcjsrList.add(zdjdcjsr);
			}
		return zdjdcjsrList;
	}
}
