/*
 * 文 件 名:  Util.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修改时间:  2012-2-21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 * 修 改 人:  wangyong
 */
package com.ycszh.util.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import jxl.Workbook;
import jxl.biff.FontRecord;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 * 下载工具类
 * @author  wangyong
 * @version  [版本号, 2012-2-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Util {

	/** <默认构造函数>
	 */
	public Util() {
	}
	 /**
     * 记录日志
     */
    private static final Logger logger = Logger.getLogger(Util.class);
    
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
        WritableCellFormat rnformat = null;
        WritableCellFormat titleFormat = null;
        OutputStream out = null;
        try
        {
            out = response.getOutputStream();
            
            //设置下载文件名称
            String fileName = Constants.DEFAULT_FILENAME;//默认下载文件名
            if (eeb.getFileName() != null && !"".equals(eeb.getFileName()))
            {
                fileName = eeb.getFileName();
            }
            response.setContentType("application/x-excel");
            response.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "utf-8") + ".xls");
            wwb = Workbook.createWorkbook(out);
            
            // 设置字体，14 粗体 
            WritableFont wf = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false);
            
            // 初始化标题字体样式
            titleFormat = new WritableCellFormat(wf);
            titleFormat.setBorder(Border.ALL, BorderLineStyle.HAIR);
            
            //初始化表格
            rnformat = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,
                    NumberFormats.TEXT);
            
            rnformat.setBorder(Border.ALL, BorderLineStyle.HAIR);
            
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
                        .get(key)), titleFormat));
               j++; 
            }
            for (int i = 0; i < eeb.getList().size(); i++)
            {
            	// 曹成修改，不再使用实体类，根据list集合直接展示对应项。有点：列头不固定时，可动态生成excle。不再局限于实体类
            	Object[] obj = (Object[])eeb.getList().get(i);
            	for(int k = 0; k < obj.length; k++){
            		ws.addCell(new Label(colIndex++, rowIndex, obj[k]+"", rnformat));
            	}
            	
            	
//                Object obj = eeb.getList().get(i);
//                c = obj.getClass();
//                for (String key : keySet)
//                {
//                    fieldName = c.getDeclaredField(key).getName();
//                    
//                    stringLetter = fieldName.substring(0, 1).toUpperCase();
//                    // 获得相应属性的getXXX方法名称
//                    getName = "get" + stringLetter + fieldName.substring(1);
//                    
//                    getMethod = c.getMethod(getName, new Class[]{});
//                    // 调用源对象的getXXX（）方法
//                    Object tempVal = getMethod.invoke(obj, new Object[]{});
//                    String value = null;
//                    if (null == tempVal)
//                    {
//                        value = "";
//                    }
//                    else
//                    {
//                        value = getMethod.invoke(obj, new Object[]{}).toString();
//                    }
//                    ws.addCell(new Label(colIndex++, rowIndex, value, format));
//                }
                rowIndex++;
                colIndex = 0;
            }

            wwb.write();
            
        }
        catch (Exception e)
        {
            logger.error("DownloadUtil.exportExcel() fail:", e);
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
					logger.error("DownloadUtil.exportExcel() WriteException: ", e);
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
