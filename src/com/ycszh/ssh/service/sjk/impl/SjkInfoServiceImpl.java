package com.ycszh.ssh.service.sjk.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ycszh.ssh.dao.sjk.SjkInfoDao;
import com.ycszh.ssh.service.sjk.SjkInfoService;

/**
 * @包名:com.ycszh.ssh.service.sjk.impl
 * @文件名:SjkInfoServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-7-22
 * @描述:
 * @版本:V 1.0
 */
public class SjkInfoServiceImpl implements SjkInfoService {

	private SjkInfoDao sjkInfoDao;
	
	public SjkInfoDao getSjkInfoDao() {
		return sjkInfoDao;
	}

	public void setSjkInfoDao(SjkInfoDao sjkInfoDao) {
		this.sjkInfoDao = sjkInfoDao;
	}

	public String readExcel(File uploadExcel, String dxrn) throws Exception {
		List<List> datas = this.getData(uploadExcel,1);//读取数据（读取数据的源Excel，读取数据忽略的行数）
		// 返回内容
		String resultContent = "";
		if(datas != null){
			// 拿到对应的手机号码
			String hphm = "";
			String hpzl = "";
			// 短信发送返回结果
			int result = -1;
			
			for(int i = 0; i < datas.size(); i++){
				List hphms = datas.get(i);
				hphm = hphms.get(0)+"";
				hpzl = hphms.get(1)+"";
				
				// 取得对应的手机号码
				List list = sjkInfoDao.findSQL("select sxsjhm from sjk_info where hphm = '"+hphm+"' and hpzl = '"+hpzl+"'");
				if(list.size() < 1){
					continue;
				}
				
				// 发送短信,暂时不给真实用户发送短信
//				list.clear();
//				list.add("15019451210");
//				if(i == 0){
//					list.add("18566299351");
//				}
//				else if(i == 1){
//					list.add("18588208825");
//				}
				
				result = sjkInfoDao.getFunSendsms(list.get(0)+"", dxrn);
				
				if(result != 0){
					resultContent += "第"+i+2+"行：发送失败，请检查格式是否正确！<br/>sdfsdf";
				}
			}
			if(resultContent == ""){
				resultContent = "短信发送成功";
			}
			
		}
		return resultContent;
	}
	
	protected List getData(File file, int ignoreRows) {
	      
	    //result 第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
		List<List> result = new ArrayList<List>();

		try {
		      //获取工作薄workbook
		  HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file)); //读取文件流
		  //获得 sheet总数
		  int sheetCount  =workbook.getNumberOfSheets();
		 
		  //遍历sheet
		  for (int sheetIndex = 0; sheetIndex < sheetCount;sheetIndex++) {
		 
			 //获得指定的sheet对象
		     HSSFSheet sheet =workbook.getSheetAt(sheetIndex);
		      
		     //获得本sheet的总行数
		     int rowCount =sheet.getLastRowNum();
		   
		     //如果没有数据
		     if(rowCount < 1){
		         return result;
		     }
		      
		     //如果有数据 进入下边
		      
		     //遍历行row ignoreRows忽略的行数。即标题行，不取
		     for (int rowIndex = ignoreRows; rowIndex<= rowCount; rowIndex++) {
		     
			     //准备rowData 收集每一行数据
			     List<Object> rowData = new ArrayList<Object>();
			      
			      //获得行row对象
			     HSSFRow row =sheet.getRow(rowIndex);
			     
			      //如果此行为空，则进入下一个循环
		         if (row == null) {
		            continue;
		         }
			         
			         
		         //获得本行中单元格的个数
		         int cellCount =row.getLastCellNum();
		         
		         //遍历每个单元格cell
		         for (int cellIndex = 0;cellIndex < cellCount; cellIndex++) {
		         
			         //获得单元格cell对象
			         HSSFCell cell =row.getCell(cellIndex);
			           
			         //获得指定单元格中的数据
			         Object cellContent =this.getCellContent(cell);
			           
			         //将单元格内容放入 行数据中
			         rowData.add(cellContent);
		         }
		         
		         //将每行的数据rowData 放入结果集中
		         result.add(rowData);
		      }
		      
		    //将每个sheet的结果 此处忽略掉收集了
		  }
		 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
		//遍历完整个excel之后
		  return result;
		}

	}
	
		
	 /**
	  * 获取一个cell的数据类型
       	CELL_TYPE_STRING：字符型  
  		CELL_TYPE_NUMERIC：数值型   
  		CELL_TYPE_BOOLEAN：布尔型   
  		CELL_TYPE_FORMULA：公式型   
  		CELL_TYPE_BLANK：空值  
     * @param cell
     * @return
     */
	protected Object getCellContent(HSSFCell cell) {

	    Object result = null;
	     
	    //如果此单元格为空，则返回null;
	    if (cell == null) {
	    	return result;
	    }
	     
	    //单元格对象不为空
	         
	    //单元格类型：Numeric:0, String:1,Formula:2, Blank:3, Boolean:4, Error:5
	    int cellType =cell.getCellType();
	         
	    //判断单元格类型
	    switch (cellType) {
	         
	        case HSSFCell.CELL_TYPE_STRING://CELL_TYPE_STRING：字符型 
	 
	           String tempResult=cell.getRichStringCellValue().getString();
	            
	           result=this.rightTrim(tempResult);
	            
	           break;
	            
	        case HSSFCell.CELL_TYPE_NUMERIC://CELL_TYPE_NUMERIC：数值型  
	         
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					result = cell.getDateCellValue();
				}else{
					result = cell.getNumericCellValue();  
				}
				break;
	        case HSSFCell.CELL_TYPE_FORMULA://CELL_TYPE_FORMULA：公式型
	         
	            result =cell.getNumericCellValue();
	            
	            break;
	            
	        case HSSFCell.CELL_TYPE_BOOLEAN://CELL_TYPE_BOOLEAN：布尔型   
	         
	            result =cell.getBooleanCellValue();
	            
	            break;
	            
	        case HSSFCell.CELL_TYPE_BLANK:
	         
	            result = null;
	            
	            break;
	            
	        case HSSFCell.CELL_TYPE_ERROR:
	         
	            result = null;
	            
	            break;
	            
	        default:
	           System.out.println("枚举了所有类型");
	        
	            break;
	         }
	         
	         return result;
	     }   

	    /**

	     * 去掉字符串右边的空格

	     * @param str 要处理的字符串

	     * @return 处理后的字符串 213.13

	     */

	protected static String rightTrim(String str) {

       if (str == null) {

           return"";

       }

       int length = str.length();

       for (int i = length - 1; i >= 0;i--) {

           if (str.charAt(i) !=0x20) {

              break;

           }

           length--;

       }

       return str.substring(0,length);

    }

}
