/*
 * 文 件 名:  ExportExcelBean.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修改时间:  2012-2-21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 * 修 改 人:  wangyong
 */
package com.ycszh.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

 
/**
 * 导出实体Bean类
 * @version  [版本号, 2012-2-21]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class ExportExcelBean {
    private final int MAX = 24;
	/** <默认构造函数>
	 */
	public ExportExcelBean() {
	}

	 /**
     * 生成的下载文件的文件名
     */
    private String fileName;
    
    /**
     * 表格标题
     */
    private String fileTitle;
    
    /**
     * 列表显示信息
     */
    private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();
    
    /**
     * 表格数据列表
     */
    private List<? extends Object> list;
    
    /**
     * 覆盖toString方法
     * 
     * @return bean的字符串
     */
    @Override
	public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer(MAX);
        stringBuffer.append(Constants.LINE_SEPARATOR).append(">>>>ExportExcelBean bean");
        stringBuffer.append(Constants.LINE_SEPARATOR).append("fileName = ").append(this.fileName);
        stringBuffer.append(Constants.LINE_SEPARATOR).append("fileTitle = ").append(this.fileTitle);
        stringBuffer.append(Constants.LINE_SEPARATOR).append("ExportExcelBean bean<<<<");
        return stringBuffer.toString();
    }
    
    /**
     * 向map中传入参数
     * @param name key键
     * @param value 值
     */
    public void setParameter(String name, Object value)
    {
        parmsMap.put(name, value);
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileTitle()
    {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle)
    {
        this.fileTitle = fileTitle;
    }

    public List<? extends Object> getList()
    {
        return list;
    }

    public void setList(List<? extends Object> list)
    {
        this.list = list;
    }

    public Map<String, Object> getParmsMap()
    {
        return parmsMap;
    }

    public void setParmsMap(Map<String, Object> parmsMap)
    {
        this.parmsMap = parmsMap;
    }
}
