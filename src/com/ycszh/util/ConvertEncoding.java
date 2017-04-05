package com.ycszh.util;

import java.io.UnsupportedEncodingException;

/**
 * @包名:com.ycszh.util
 * @文件名:ConvertEncoding.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class ConvertEncoding {
	/**
	 * 转化为ISO08859-1编码格式
	 * 
	 * @param old
	 *            String
	 * @return String
	 */
	public static String toIso(String old) {
		try {
			return new String(old.getBytes("ISO08859-1"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "编码转化出错";
		}
	}

	/**
	 * 转化为GBK编码格式
	 * 
	 * @param old
	 *            String
	 * @return String
	 */
	public static String toGBK(String old) {
		try {
			return new String(old.getBytes("GBK"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "编码转化出错";
		}
	}

	/**
	 * 自定义编码转换
	 * 
	 * @param str
	 * @param charcode
	 * @return
	 */
	public static String transByBytes(String str, String oldcode, String newcode) {
		try {
			return new String(str.getBytes(oldcode), newcode);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 转化为GB2312编码格式
	 * 
	 * @param old
	 *            String
	 * @return String
	 */
	public static String toGB(String old) {
		try {
			return new String(old.getBytes("GB2312"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "编码转化出错";
		}
	}

	/**
	 * 转化为UTF-8编码格式
	 * 
	 * @param old
	 *            String
	 * @return String
	 */
	public static String toUTF(String old) {
		try {
			return new String(old.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "编码转化出错";
		}
	}

	// 传输汉字转码
	public static String toGb2312(String str) {
		if (str == null)
			return null;
		try {
			byte b[] = str.getBytes("ISO8859_1");
			for (int i = 0; i < b.length; i++) {
				if (b[i] == 63)
					break; // 转后有乱码则不转
				else if (b[i] > 0)
					continue; // 没有中文字符继续判断
				else if (b[i] < 0) { // 不可能为0，0为字符串结束符
					str = new String(b, "GB2312");// 有乱码则转
					break;
				}
			}
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
		}
		return str;
	}

	/**
	 * UTF-8转化GB2312为编码格式
	 * 
	 * @param old
	 *            String
	 * @return String
	 */
	public static String utf8Togb2312(String str) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			switch (c) {
			case '+':
				sb.append(' ');
				break;
			case '%':
				try {
					sb.append((char) Integer.parseInt(str.substring(i + 1,
							i + 3), 16));
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException();
				}
				i += 2;
				break;
			default:
				sb.append(c);
				break;
			}
		}
		// Undo conversion to external encoding
		String result = sb.toString();
		String res = null;
		try {
			byte[] inputBytes = result.getBytes("8859_1");
			res = new String(inputBytes, "UTF-8");
		} catch (Exception e) {
		}
		return res;
	}
}
