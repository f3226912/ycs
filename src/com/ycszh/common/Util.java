package com.ycszh.common;
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
import org.slf4j.Logger;
import com.ycszh.global.SysConst;



/**
 * 下载工具类
 * @author  PENGGUOJUN
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
    private static final Logger logger = ZxdLogger.getZxdLogger(StringUtil.class);
    
	/**
     * 导出excel文件公共方法
     * TODO
     * @param response HttpServletResponse对象
     * @param eeb 导出excel文件信息bean
     * @return reuslt 返回导出结果SUCCESS/FAIL
     */
    public static String exportExcel(HttpServletResponse response, ExportExcelBean eeb)
    {
        //返回结果
        String result = SysConst.RESULT_SUCCESS;
        WritableWorkbook wwb = null; // Excel工作簿
        WritableSheet ws = null;  //Excel sheet
        WritableCellFormat format = null; 
        OutputStream out = null;
        try
        {
            out = response.getOutputStream();
            
            //设置下载文件名称
            String fileName = SysConst.DEFAULT_FILENAME;//默认下载文件名
            if (!StringUtil.isNull(eeb.getFileName()))
            {
                fileName = eeb.getFileName();
            }
            response.setContentType("application/x-excel");
            response.setHeader("Content-disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8") + ".xls");
            wwb = Workbook.createWorkbook(out);
            
            
            //初始化表格
            format = new WritableCellFormat(WritableWorkbook.ARIAL_10_PT,
                    NumberFormats.TEXT);
            //创建表格标题
            ws = wwb.createSheet(eeb.getFileTitle(), 0);
//            // 字体样式(字体,大小,是否粗体,是否斜体)
//            WritableFont wf = new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD,true);   
//            format = new WritableCellFormat(wf);//实例化文字格式化 
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
//                ws.addCell(new WritableFont(WritableFont.ARIAL,11, WritableFont.BOLD,true),String.valueOf(eeb.getParmsMap()
//                        .get(key)), format);
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
            logger.error("DownloadUtil.exportExcel() fail:", e);
            result = SysConst.RESULT_FAIL;
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
						logger.error("调用excel导出方法错误信息:"+exceptionstr);
					   result = SysConst.RESULT_FAIL;
				}
            }
            catch (IOException e)
            {
                result = SysConst.RESULT_FAIL;
            }
        }
        return result;
    }
}
