package com.ycszh.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @包名:com.ycszh.util
 * @文件名:PingYinUtil.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class PingYinUtil {
	private static Log logger = LogFactory.getLog(PingYinUtil.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// getPingYin("资源fasd");
		try {
			getPinYin("资源fasd");
			getPinYinByUpper("资源fasd");
			getPinYinByLower("资源fasd");
			getPinYinByFormat("资源fasd", getToneOutputFormat());
		} catch (BadHanyuPinyinOutputFormatCombination e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取默认拼音（首字母大写）
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYin(String zhongwen)
			throws BadHanyuPinyinOutputFormatCombination {
		logger.debug("-------->>Input ZhongWen=" + zhongwen);

		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (char c : chars) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(c,
					getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += capitalize(pinYin[0]);
			} else {
				zhongWenPinYin += c;
			}
		}
		System.out.println(zhongWenPinYin);
		logger.debug("-------->>Output PinYin=" + zhongWenPinYin);
		return zhongWenPinYin;
	}

	/**
	 * 获取小写拼音
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYinByLower(String zhongwen)
			throws BadHanyuPinyinOutputFormatCombination {
		logger.debug("-------->>Input ZhongWen=" + zhongwen);
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (char c : chars) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(c,
					getDefaultOutputFormat());
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += pinYin[0];
			} else {
				zhongWenPinYin += c;
			}
		}
		System.out.println(zhongWenPinYin);
		logger.debug("-------->>Output PinYin=" + zhongWenPinYin);
		return zhongWenPinYin;
	}

	/**
	 * 获取大写拼音
	 * 
	 * @param zhongwen
	 * @return
	 * @throws BadHanyuPinyinOutputFormatCombination
	 */
	public static String getPinYinByUpper(String zhongwen)
			throws BadHanyuPinyinOutputFormatCombination {
		logger.debug("-------->>Input ZhongWen=" + zhongwen);
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (char c : chars) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(c, format);
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += pinYin[0];
			} else {
				zhongWenPinYin += c;
			}
		}
		System.out.println(zhongWenPinYin);
		logger.debug("-------->>Output PinYin=" + zhongWenPinYin);
		return zhongWenPinYin;
	}

	public static String getPinYinByFormat(String zhongwen,
			HanyuPinyinOutputFormat format)
			throws BadHanyuPinyinOutputFormatCombination {
		logger.debug("-------->>Input ZhongWen=" + zhongwen);
		String zhongWenPinYin = "";
		char[] chars = zhongwen.toCharArray();
		for (char c : chars) {
			String[] pinYin = PinyinHelper.toHanyuPinyinStringArray(c, format);
			// 当转换不是中文字符时,返回null
			if (pinYin != null) {
				zhongWenPinYin += pinYin[0];
			} else {
				zhongWenPinYin += c;
			}
		}
		System.out.println(zhongWenPinYin);
		logger.debug("-------->>Output PinYin=" + zhongWenPinYin);
		return zhongWenPinYin;
	}

	/**
	 * Default Format 默认输出格式
	 * 
	 * @return
	 */
	public static HanyuPinyinOutputFormat getDefaultOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 没有音调数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示

		return format;
	}

	/**
	 * Capitalize 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String capitalize(String s) {
		char ch[];
		ch = s.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		String newString = new String(ch);
		return newString;
	}

	/**
	 * 带音标输出格式
	 * 
	 * @return
	 */
	public static HanyuPinyinOutputFormat getToneOutputFormat() {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setToneType(HanyuPinyinToneType.WITH_TONE_MARK); // 有音调没数字
		format.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);// u显示
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
		// format.setVCharType(HanyuPinyinVCharType.WITH_U_AND_COLON);// u显示

		return format;
	}

	// public static String getPingYin(String src){
	// char[] t1 ;
	// t1=src.toCharArray();
	// String[] t2 = new String[t1.length];
	// HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
	// t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
	// t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	// t3.setVCharType(HanyuPinyinVCharType.WITH_V);
	// t3.setToneType(HanyuPinyinToneType.WITH_TONE_MARK); //设置显示拼音声调
	// t3.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);//设置显示拼音声调
	// t3.setCaseType(HanyuPinyinCaseType.UPPERCASE);
	// t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
	// t3.setVCharType(HanyuPinyinVCharType.WITH_V);
	// String t4="";
	// int t0=t1.length;
	// try {
	// for (int i=0;i<t0;i++)
	// {
	// //判断是否为汉字字符函数
	// if(java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+"))
	// {
	// t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
	// t4+=t2[0];
	// }
	// else
	// t4+=java.lang.Character.toString(t1[i]);
	// }
	// System.out.println(t4);
	// return t4;
	// }
	// catch (BadHanyuPinyinOutputFormatCombination e1) {
	// e1.printStackTrace();
	// }
	// return t4;
	// }
}
