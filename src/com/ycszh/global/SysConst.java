package com.ycszh.global;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dengsiqi E-mail:dengsiqi@vip.qq.com
 * @version 创建时间：2012-4-8 下午04:59:25 SysConst.java类说明
 */
public class SysConst {
	public static final String USERBEAN = "userbean";// 登录用户信息
	public static final String USERMENU = "usermenu";// 登录用户授权菜单
	public static final String USERACTION = "useraction";// 登录用户授权控件
	public static final int PAGESIZE = 12;// 每页显示数据量
	
		// 手机库用户
	public static final String sendsms_user = "DXYJ01";
	public static final String sendsms_pwd = "sms_sj";
	
	//密钥
	public static final String P_KEY = "EF57";

	/**
	 * 返回结果
	 */
    public static final String RESULT_FAIL = "FAIL";
    /**
     * 返回结果
     */
    public static final String RESULT_SUCCESS = "SUCCESS";
    /**
     * 默认文件名称
     */
    public static final String DEFAULT_FILENAME = "DEFAULT_FILENAME";
    
    /**
     * 换行
     */
    public static final String LINE_SEPARATOR = "\n";

	
	public static final Map<String, String> CODES=new HashMap<String, String>();
	
	static{
		CODES.put("01", "大型汽车");
		CODES.put("02", "小型汽车");
		CODES.put("03", "使馆汽车");
		CODES.put("04", "领馆汽车");
		CODES.put("05", "境外汽车");
		CODES.put("06", "外籍汽车");
		CODES.put("07", "普通摩托车");
		CODES.put("08", "轻便摩托车");
		CODES.put("09", "使馆摩托车");
		CODES.put("10", "领馆摩托车");
		CODES.put("11", "境外摩托车");
		CODES.put("12", "外籍摩托车");
		CODES.put("13", "低速车");
		CODES.put("14", "拖拉机");
		CODES.put("15", "挂车");
		CODES.put("16", "教练汽车");
		CODES.put("17", "教练摩托车");
		CODES.put("18", "试验汽车");
		CODES.put("19", "试验摩托车");
		CODES.put("20", "临时入境汽车");
		CODES.put("21", "临时入境摩托车");
		CODES.put("22", "临时行驶车");
		CODES.put("23", "警用汽车");
		CODES.put("24", "警用摩托");
		CODES.put("25", "原农机号牌");
		CODES.put("26", "香港入出境车");
		CODES.put("27", "澳门入出境车");
	}
}
