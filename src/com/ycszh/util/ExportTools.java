package com.ycszh.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * @包名:com.example.util
 * @文件名:ExportTools.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-22
 * @描述:
 * @版本:V 1.0
 */
public class ExportTools {
	
	/** <默认构造函数>
	 */
	public ExportTools() {
	}
	
	/**
     * 导出excel文件公共方法
     * TODO
     * @param response HttpServletResponse对象
     * @param eeb 导出excel文件信息bean
     * @return reuslt 返回导出结果SUCCESS/FAIL
     *  wangyong
     */
    public static String exportExcel(HttpServletResponse response, ExportExcelBean eeb)
    {
        //返回结果
        String result = Constants.RESULT_SUCCESS;
        WritableWorkbook wwb = null; // Excel工作簿
        WritableSheet ws = null;  //Excel sheet
        WritableCellFormat format = null; 
        OutputStream out = null;
        try
        {
            out = response.getOutputStream();
            
            //设置下载文件名称
            String fileName = Constants.DEFAULT_FILENAME;//默认下载文件名
            if (eeb.getFileName() != null && eeb.getFileName().length() != 0 && eeb.getFileName().trim().length() != 0)
            {
                fileName = eeb.getFileName();
            }
            response.setContentType("application/x-excel");
            response.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "utf-8") + ".xls");
            wwb = Workbook.createWorkbook(out);
            
            
            //初始化表格
            format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,
                    NumberFormats.TEXT);
            //创建表格标题
            ws = wwb.createSheet(eeb.getFileTitle(), 0);

            //得到需要创建表格的列名，创建表格列名称
            int rowIndex = 1;
            int colIndex = 0;
            Class<? extends Object> c = null;
            Method getMethod = null;
            String fieldName = null;
            String stringLetter = null;
            String getName = null;
            Set<String> keySet = eeb.getParmsMap().keySet();
            int j = 0;
            for (String key : keySet)
            {
                // 设置标题
                ws.addCell(new Label(j, 0, String.valueOf(eeb.getParmsMap()
                        .get(key)), format));
                j++;
            }
            for (int i = 0; i < eeb.getList().size(); i++)
            {
                Object obj = eeb.getList().get(i);
                c = obj.getClass();
                for (String key : keySet)
                {
                    fieldName = c.getDeclaredField(key).getName();
                    
                    stringLetter = fieldName.substring(0, 1).toUpperCase();
                    // 获得相应属性的getXXX方法名称
                    getName = "get" + stringLetter + fieldName.substring(1);
                    
                    getMethod = c.getMethod(getName, new Class[]{});
                    // 调用源对象的getXXX（）方法
                    Object tempVal = getMethod.invoke(obj, new Object[]{});
                    String value = null;
                    if (null == tempVal)
                    {
                        value = "";
                    }
                    else
                    {
                        value = getMethod.invoke(obj, new Object[]{}).toString();
                    }
                    ws.addCell(new Label(colIndex++, rowIndex, value, format));
                }
                rowIndex++;
                colIndex = 0;
            }

            wwb.write();
            
        }
        catch (Exception e)
        {
            result = Constants.RESULT_FAIL;
        }
        finally
        {
            try
            {
                try {
                    if(wwb != null){
                        wwb.close();
                    }
                    if(out != null){
                    	out.close();
                    }
				} catch (WriteException e) {
					result = Constants.RESULT_FAIL;
				}
            }
            catch (IOException e)
            {
                result = Constants.RESULT_FAIL;
            }
        }
        return result;
    }

}
