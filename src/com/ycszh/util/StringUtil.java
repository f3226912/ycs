package com.ycszh.util;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: 字符串处理工具类</p>
 * <p>Description: 字符串处理工具类</p>
 * @author wangyong
 */
public final class StringUtil
{
    
    /**
     * SQL的like语句查询条件中的转义字符
     */
    public static final char SQL_ESCAPE_CHAR = '\\';
    
    private static final int BUFFER_LENGTH = 16;
   
    /**
     * 记录日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);
    
    /**
     * <默认构造函数>
     */
    private StringUtil()
    {
        
    }
    
   /**
    * 
    *
    * @param 截断字符串 
    * @param num
    * @return [参数说明]
    * 
    * @return string [返回类型说明]
    * @exception throws [违例类型] [违例说明]
    * @see [类、类#方法、类#成员]
    */
    public static String CutString(Object content, int num)
    {
        if (content.toString().length() > num - 2)
            return content.toString().substring(0, num - 2) + "...";
        else
            return content.toString();
    }
    
    /**
     * 验证是否为空
     * @author wangyong
     * @param value 需要验证的字符串
     * @return boolean
     */
    public static boolean isNull(String value)
    {
        if (value == null || value.length() == 0 || value.trim().length() == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 替换 特殊字符--避免页面非法传参
     * @param str [参数说明]
     * @return void [返回类型说明]
     * @author wangyong
     */
    public static String replaceTag(String str)
    {
        String replaceStr = "";
        if(!StringUtil.isNull(str))
        {
            replaceStr = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;")
            .replaceAll("\"", "&Prime;").replaceAll("'", "&prime;");
        }
        return replaceStr;
    }
    
    
    /**
     * 将字符串转换成一个字符串的列表
     * @param tmp tmp
     * @return List<String>
     */
    public static List<String> convertToList(String tmp)
    {
        List<String> results = new ArrayList<String>();
        if (tmp != null)
        {
            char[] chars = tmp.toCharArray();
            for (int i = 0; i < chars.length; i++)
            {
                char tt = chars[i];
                
                Character cc = Character.valueOf(tt);
                results.add(cc.toString());
            }
        }
        return results;
    }
    
    /**
     * 组装带有时间的开始时间
     * @param value value
     * @return String
     */
    public static String populateBeginTime(String value)
    {
        String beginTime = null;
        if (!isNull(value))
        {
            beginTime = value + " 00:00:00";
        }
        return beginTime;
    }
    
    /**
     * 组装带有时间的结束时间
     * @param value value
     * @return String
     */
    public static String populateEndTime(String value)
    {
        String endTime = null;
        
        if (!isNull(value))
        {
            endTime = value + " 23:59:59";
        }
        return endTime;
    }
    
    /**
     * SQL字符串转义(此函数仅仅对like语句的"查询条件字符串"进行转义)
     * @param sql 需要转义的字符串
     * @return 转义后的字符串
     */
    public static String escapeSql(String sql)
    {
        if (null == sql || "".equals(sql))
        {
            return sql;
        }
        else
        {
            StringBuilder sb = new StringBuilder(BUFFER_LENGTH);
            for (int i = 0; i < sql.length(); i++)
            {
                char c = sql.charAt(i);
                if (SQL_ESCAPE_CHAR == c || '%' == c || '_' == c)
                {
                    sb.append(SQL_ESCAPE_CHAR);
                }
                sb.append(c);
            }
            return sb.toString();
        }
        
    }
    
    /**
     * 字符串转换成日期格式
     * @param value value
     * @param format format
     * @return Date
     */
    public static Date str2Date(String value, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try
        {
            return sdf.parse(value);
        }
        catch (ParseException e)
        {
            logger.error("StringUtil.str2Date() ParseException：",e);
        }
        return null;
    }
    
    /**
     * 字符串List转换为字符串(可以指定间隔符)
     * @param strList  字符串List
     * @param separator  间隔符
     * @return 转换后的字符串
     */
    
    public static String strListToString(List<String> strList, String separator)
    { 
        String t = "";
        if (null == strList)
        {
            return t;
        }
       
        StringBuffer buf = new StringBuffer();
        for (String str : strList)
        {
            buf.append(str);
            buf.append(separator);
        }
        
        t = buf.toString();
        
        if (t.length() > 0)
        {
            t = t.substring(0, t.length() - separator.length());
        }
       
        return t;
    }
    
    /**
     * 去掉字符串最后一位特殊字符
     * @param str 字符串
     * @param separator 间隔符
     * @return 转换后的字符串
     */
    public static String replaceLastChar(String str, String separator)
    {
        String t = "";
        
        if (null == str)
        {
            return t;
        }
        t = "".equals(str) ? "" : str.substring(0, str.length() - 1);
        
        return t;
    }
    
    /**
     * 获取当前日期的前一天
     * @return 字符串
     */
    public static String getFormerDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(date);
    }
    
    /**
     * 获取当前日期
     * @return 字符串
     */
    public static String getNowDate()
    {
        Date date = new Date();
        SimpleDateFormat sDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat1.format(date);
    }
    
    /**
     * 将字符串数组转换成int数组
     * @param strings 字符串数组
     * @return int[]
     */
    public static int[] strings2Ints(String[] strings)
    {
        if (null == strings || strings.length <= 0)
        {
            return null;
        }
        else
        {
            int[] ints = new int[strings.length];
            for (int i = 0; i < strings.length; i++)
            {
                ints[i] = Integer.parseInt(strings[i]);
            }
            return ints;
        }
    }
    
    /**
     * 将字符串转换成int
     * @param string 字符串
     * @return int
     */
    public static int string2Int(String string)
    {
        return Integer.parseInt(string);
    }
    
    /**
     * 字符串长度校验
     * @param string 待校验字符串
     * @param expectLength 期望字符串长度
     * @return true 长度超过，false 长度未超过
     */
    public static boolean is2LongStr(String string, int expectLength)
    {
        try
        {
            if (string.getBytes("UTF-8").length > expectLength)
            {
                return true;
            }
        }
        catch (UnsupportedEncodingException e)
        {
            logger.error("StringUtil.is2LongStr() ：",e);
            return false;
        }
        return false;
    }
    
    /**
     * 字符串是否为正整数判断
     * @param string 待判断字符串
     * @return true 不是正整数，flase 是正整数
     */
    public static boolean isNotPosInt(String string)
    {
        int num = 0;
        try
        {
            num = Integer.parseInt(string);
        }
        catch (NumberFormatException e)
        {
            logger.error("StringUtil.str2Date NumberFormatException:", e);
            return true;
        }
        if (num <= 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 服务起始时间和服务结束时间比较
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return true 服务起始时间小于服务结束时间 false 服务起始时间大于服务结束时间
     */
    public static boolean timeCompare(String beginTime, String endTime)
    {
        return timeCompare(beginTime, endTime, "yyyy-MM-dd");
    }
    /**
     * 服务起始时间和服务结束时间比较
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @param format 时间格式
     * @return true 服务起始时间小于服务结束时间 false 服务起始时间大于服务结束时间
     */
    public static boolean timeCompare(String beginTime, String endTime, String format)
    {
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return  sdf.parse(beginTime).before(sdf.parse(endTime));
           
        }
        catch (ParseException e)
        {
            logger.error("Method:StringUtil.timeCompare() error: ", e);
            return false;
        }
        
    }
    
    /**
     * 将空字符串转换成null
     * @param param 参数
     * @return String 
     */
    public static String emptyStr2Null(String param)
    {
        if (null == param || "".equals(param))
        {
            return null;
        }
        else
        {
            return param;
        }
        
    }
    
    /**
     * 去掉字符串的首尾空格
     * @param param 参数
     * @return String 
     */
    public static String trim(String param)
    {
        if (null == param)
        {
            return null;
        }
        else
        {
            return param.trim();
        }
    }
    
    /**
     * 将ISO-8859-1格式的转换成UTF-8编码
     * @param param 参数
     * @return String 
     */
    public static String toUtf8(String param)
    {
        String str = param;
        if (!isNull(param))
        {
            try
            {
                str = new String(param.getBytes("ISO-8859-1"), "UTF-8");
            }
            catch (UnsupportedEncodingException e)
            {
                logger.error("Method:StringUtil.toUtf8() error: ", e);
            }
        }
        return str;
    }
    
    /**
     * 判断数组是否为空
     * @param param 数组参数
     * @return true/false 
     */
    public static boolean isNullArray(Object[] param)
    {
        if (null == param || param.length == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * 
     * 将秒转换成时间格式为hh:mi:ss的字符串
     * @param second 秒
     * @return String 
     */
    public static String intToDate(int second)
    {
        String str = "";
        int hour = second / 3600;
        str += getStr(hour) + ":";
        int minute = (second % 3600) / 60;
        str += getStr(minute) + ":";
        int ss = (second % 3600) % 60;
        str += getStr(ss);
        return str;
    }

    private static String getStr(int time)
    {
        String str = "";
        if (time < 10)
        {
            str = "0" + time;
        }
        else
        {
            str = time + "";
        }
        return str;
    }
    
    /**
     * 获取随机数 
     * @param maxNum 最大数值 （从1 开始）
     * @param length 随机的个数
     * @return int[] 
     */
    public static int[] getRandomInts(int maxNum, int length)
    {
        if (maxNum == 0 || length == 0)
        {
            return null; 
        }   
                
        if (maxNum  < length)
        {
            length =  maxNum;
        }   
        int[] randoms = new int[length];
        Set<Integer> set = new HashSet<Integer>();
        Random random = new Random();

        while (set.size() < length)
        {
            set.add(random.nextInt(maxNum));
        }
        Iterator<Integer> it = set.iterator();
        int i = 0;
        while (it.hasNext())
        {
            randoms[i] = it.next();
            i++;
        }
        return randoms;
    }
    
    
    /**
     * 得到文件的短路径, 不保护目录.
     *
     * @param fileName
     *            需要处理的文件的名字.
     * @return the short version of the file's name.
     */
    public static String getShortFileName(String fileName) {
        if (fileName != null) {
            String oldFileName = new String(fileName);

            fileName = fileName.replace('\\', '/');
            
            // Handle dir
            if(fileName.endsWith("/")) {
                int idx = fileName.indexOf('/');
                if(idx == -1 || idx == fileName.length() - 1) {
                    return oldFileName;
                } else {
                    return  oldFileName.substring(idx + 1, fileName.length() - 1);
                }

            }
            if(fileName.lastIndexOf("/") > 0) {
                fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
            }

            return fileName;
        }
        return "";
    }
    /**
     * 换码
     *
     * @param src
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }

    /**
     * 反码
     *
     * @param src
     * @return [参数说明]
     * 
     * @return String [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
    /**
     * 判断字符串是否未空, 如果为 null 或者长度为0, 均返回 true.
     */
    public static boolean isEmpty(String input) {
        return (input == null || input.length() == 0);
    }
    
    /**
	 * 获取两个数字不同的元素
	 * 
	 * @param strArr1
	 * @param strArr2
	 * @return
	 */
	public static String[] getAllSameElement1(String[] list, List<String> list2) {
		String[] arr3 = new String[100];
		int k = 0;
		int flag = 0;
		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list2.size(); j++) {
				if (list2.get(j).toString().equals(list[i])) {
					flag = 1;
				}
			}
			if (flag != 1) {
				arr3[k] = list[i].toString();
				k++;
			}
			flag = 0;
		}
		return arr3;
	}

	/**
	 * 获取两个数字相同的元素
	 * 
	 * @param strArr1
	 * @param strArr2
	 * @return
	 */
	public static List<String> getAllSameElement2(String[] strArr1,
			String[] strArr2) {
		if (strArr1 == null || strArr2 == null) {
			return null;
		}
		Arrays.sort(strArr1);
		Arrays.sort(strArr2);
		List<String> list = new ArrayList<String>();

		int k = 0;
		int j = 0;
		while (k < strArr1.length && j < strArr2.length) {
			if (strArr1[k].compareTo(strArr2[j]) == 0) {
				if (strArr1[k].equals(strArr2[j])) {
					list.add(strArr1[k]);
					k++;
					j++;
				}
				continue;
			} else if (strArr1[k].compareTo(strArr2[j]) < 0) {
				k++;
			} else {
				j++;
			}
		}
		return list;
	}
}
