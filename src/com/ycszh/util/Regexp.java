package com.ycszh.util;

import java.util.*;

import org.apache.oro.text.regex.*;

/**
 * @包名:com.ycszh.util
 * @文件名:Regexp.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class Regexp {
	/**
	 * 验证账户是否有效
	 * 
	 * @param loginName
	 * @return
	 * 
	 */
	public static final String account_regexp = "^[a-zA-Z][a-zA-Z0-9_]{3,20}$";

	/** 保放有四组对应分隔符 */
	static final Set SEPARATOR_SET = new TreeSet();
	{
		SEPARATOR_SET.add("(");
		SEPARATOR_SET.add(")");
		SEPARATOR_SET.add("[");
		SEPARATOR_SET.add("]");
		SEPARATOR_SET.add("{");
		SEPARATOR_SET.add("}");
		SEPARATOR_SET.add("<");
		SEPARATOR_SET.add(">");
	}

	/** 存放各种正规表达式(以key->value的形式) */
	public static HashMap regexpHash = new HashMap();

	/** 存放各种正规表达式(以key->value的形式) */
	public static List matchingResultList = new ArrayList();

	private Regexp() {

	}

	/**
	 * 返回 Regexp 实例
	 * 
	 * @return
	 */
	public static Regexp getInstance() {
		return new Regexp();
	}

	/**
	 * 匹配图象
	 * 
	 * 
	 * 格式: /相对路径/文件名.后缀 (后缀为gif,dmp,png)
	 * 
	 * 匹配 : /forum/head_icon/admini2005111_ff.gif 或 admini2005111.dmp
	 * 
	 * 
	 * 不匹配: c:/admins4512.gif
	 * 
	 */
	public static final String icon_regexp = "^(/{0,1}\\w){1,}\\.(gif|dmp|png|jpg)$|^\\w{1,}\\.(gif|dmp|png|jpg)$";

	/**
	 * 匹配email地址
	 * 
	 * 
	 * 格式: XXX@XXX.XXX.XX
	 * 
	 * 匹配 : foo@bar.com 或 foobar@foobar.com.au
	 * 
	 * 
	 * 不匹配: foo@bar 或 $$$@bar.com
	 * 
	 */
	// public static final String email_regexp
	// ="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	public static final String email_regexp = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";
	/**
	 * 匹配手机
	 */
	public static final String mobile_regexp = "^1[3\\|4\\|5\\|8][0-9]\\d{8}$";
	/**
	 * 匹配特殊符号
	 */
	public static final String special_regexp = "\\s|\\n|\\r|\\t$";

	/**
	 * 匹配并提取url
	 * 
	 * 
	 * 格式: XXXX://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX
	 * 
	 * 匹配 : http://www.suncer.com 或news://www
	 * 
	 * 
	 * 提取(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
	 * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
	 * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
	 * matchResult.group(4) = /index.html?login=true
	 * 
	 * 不匹配: c:\window
	 * 
	 */
	public static final String url_regexp = "(\\w+)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * 匹配并提取http
	 * 
	 * 
	 * 格式: http://XXX.XXX.XXX.XX/XXX.XXX?XXX=XXX 或 ftp://XXX.XXX.XXX 或
	 * https://XXX
	 * 
	 * 匹配 : http://www.suncer.com:8080/index.html?login=true
	 * 
	 * 
	 * 提取(MatchResult matchResult=matcher.getMatch()): matchResult.group(0)=
	 * http://www.suncer.com:8080/index.html?login=true matchResult.group(1) =
	 * http matchResult.group(2) = www.suncer.com matchResult.group(3) = :8080
	 * matchResult.group(4) = /index.html?login=true
	 * 
	 * 不匹配: news://www
	 * 
	 */
	public static final String http_regexp = "(http|https|ftp)://([^/:]+)(:\\d*)?([^#\\s]*)";

	/**
	 * 匹配日期
	 * 
	 * 
	 * 格式(首位不为0): XXXX-XX-XX 或 XXXX XX XX 或 XXXX-X-X
	 * 
	 * 
	 * 范围:1900--2099
	 * 
	 * 
	 * 匹配 : 2005-04-04
	 * 
	 * 
	 * 不匹配: 01-01-01
	 * 
	 */
	public static final String date_regexp = "^((((19){1}|(20){1})d{2})|d{2})[-\\s]{1}[01]{1}d{1}[-\\s]{1}[0-3]{1}d{1}$";// 匹配日期

	/**
	 * 匹配电话
	 * 
	 * 
	 * 格式为: 0XXX-XXXXXX(10-13位首位必须为0) 或0XXX XXXXXXX(10-13位首位必须为0) 或
	 * 
	 * (0XXX)XXXXXXXX(11-14位首位必须为0) 或 XXXXXXXX(6-8位首位不为0) 或
	 * XXXXXXXXXXX(11位首位不为0)
	 * 
	 * 
	 * 匹配 : 0371-123456 或 (0371)1234567 或 (0371)12345678 或 010-123456 或
	 * 010-12345678 或 12345678912
	 * 
	 * 
	 * 不匹配: 1111-134355 或 0123456789
	 * 
	 */
	public static final String phone_regexp = "^(?:0[0-9]{2,3}[-\\s]{1}|\\(0[0-9]{2,4}\\))[0-9]{6,8}$|^[1-9]{1}[0-9]{5,7}$|^[1-9]{1}[0-9]{10}$";

	/**
	 * 匹配身份证
	 * 
	 * 
	 * 格式为: XXXXXXXXXX(10位) 或 XXXXXXXXXXXXX(13位) 或 XXXXXXXXXXXXXXX(15位) 或
	 * XXXXXXXXXXXXXXXXXX(18位)
	 * 
	 * 
	 * 匹配 : 0123456789123
	 * 
	 * 
	 * 不匹配: 0123456
	 * 
	 */
	public static final String ID_card_regexp = "^\\d{10}|\\d{13}|\\d{15}|\\d{18}$";

	/**
	 * 匹配邮编代码
	 * 
	 * 
	 * 格式为: XXXXXX(6位)
	 * 
	 * 
	 * 匹配 : 012345
	 * 
	 * 
	 * 不匹配: 0123456
	 * 
	 */
	public static final String ZIP_regexp = "^[0-9]{6}$";// 匹配邮编代码

	/**
	 * 不包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠\
	 * 即空格,制表符,回车符等 )
	 * 
	 * 
	 * 格式为: x 或 一个一上的字符
	 * 
	 * 
	 * 匹配 : 012345
	 * 
	 * 
	 * 不匹配: 0123456
	 * 
	 */
	public static final String non_special_char_regexp = "^[^'\"\\;,:-<>\\s].+$";

	/**
	 * 包括特殊字符的匹配 (字符串中不包括符号 数学次方号^ 单引号' 双引号" 分号; 逗号, 帽号: 数学减号- 右尖括号> 左尖括号< 反斜杠\
	 * 即空格,制表符,回车符等 )
	 */
	public static final String special_char_regexp = "['\"\\;,:-<>\\s]";

	/**
	 * 匹配非负整数（正整数 + 0)
	 */
	public static final String non_negative_integers_regexp = "^\\d+$";

	/**
	 * 匹配不包括零的非负整数（正整数 > 0)
	 */
	public static final String non_zero_negative_integers_regexp = "^[1-9]+\\d*$";

	/**
	 * 
	 * 匹配正整数
	 * 
	 */
	public static final String positive_integer_regexp = "^[0-9]*[1-9][0-9]*$";

	/**
	 * 
	 * 匹配非正整数（负整数 + 0）
	 * 
	 */
	public static final String non_positive_integers_regexp = "^((-\\d+)|(0+))$";

	/**
	 * 
	 * 匹配负整数
	 * 
	 */
	public static final String negative_integers_regexp = "^-[0-9]*[1-9][0-9]*$";

	/**
	 * 
	 * 匹配整数
	 * 
	 */
	public static final String integer_regexp = "^-?\\d+$";

	/**
	 * 
	 * 匹配非负浮点数（正浮点数 + 0）
	 * 
	 */
	public static final String non_negative_rational_numbers_regexp = "^\\d+(\\.\\d+)?$";

	/**
	 * 
	 * 匹配正浮点数
	 * 
	 */
	public static final String positive_rational_numbers_regexp = "^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$";

	/**
	 * 
	 * 匹配非正浮点数（负浮点数 + 0）
	 * 
	 */
	public static final String non_positive_rational_numbers_regexp = "^((-\\d+(\\.\\d+)?)|(0+(\\.0+)?))$";

	/**
	 * 
	 * 匹配负浮点数
	 * 
	 */
	public static final String negative_rational_numbers_regexp = "^(-(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*)))$";

	/**
	 * 
	 * 匹配浮点数
	 * 
	 */
	public static final String rational_numbers_regexp = "^(-?\\d+)(\\.\\d+)?$";

	/**
	 * 
	 * 匹配由26个英文字母组成的字符串
	 * 
	 */
	public static final String letter_regexp = "^[A-Za-z]+$";

	/**
	 * 
	 * 匹配由26个英文字母的大写组成的字符串
	 * 
	 */
	public static final String upward_letter_regexp = "^[A-Z]+$";

	/**
	 * 
	 * 匹配由26个英文字母的小写组成的字符串
	 * 
	 */
	public static final String lower_letter_regexp = "^[a-z]+$";

	/**
	 * 
	 * 匹配由数字和26个英文字母组成的字符串
	 * 
	 */
	public static final String letter_number_regexp = "^[A-Za-z0-9]+$";

	/**
	 * 
	 * 匹配由数字、26个英文字母或者下划线组成的字符串
	 * 
	 */
	public static final String letter_number_underline_regexp = "^\\w+$";

	/**
	 * 添加正规表达式 (以key->value的形式存储)
	 * 
	 * @param regexpName
	 *            该正规表达式名称 `
	 * @param regexp
	 *            该正规表达式内容
	 */
	public void putRegexpHash(String regexpName, String regexp) {
		regexpHash.put(regexpName, regexp);
	}

	/**
	 * 得到正规表达式内容 (通过key名提取出value[正规表达式内容])
	 * 
	 * @param regexpName
	 *            正规表达式名称
	 * 
	 * @return 正规表达式内容
	 */
	public String getRegexpHash(String regexpName) {
		if (regexpHash.get(regexpName) != null) {
			return ((String) regexpHash.get(regexpName));
		} else {
			System.out.println("在regexpHash中没有此正规表达式");
			return "";
		}
	}

	/**
	 * 清除正规表达式存放单元
	 */
	public void clearRegexpHash() {
		regexpHash.clear();
		return;
	}

	/**
	 * 大小写敏感的正规表达式批配
	 * 
	 * @param source
	 *            批配的源字符串
	 * 
	 * @param regexp
	 *            批配的正规表达式
	 * 
	 * @return 如果源字符串符合要求返回真,否则返回假 如:
	 *         Regexp.isHardRegexpValidate("ygj@suncer.com.cn",email_regexp) 返回真
	 */
	public static boolean isHardRegexpValidate(String source, String regexp) {

		try {
			// 用于定义正规表达式对象模板类型
			PatternCompiler compiler = new Perl5Compiler();

			// 正规表达式比较批配对象
			PatternMatcher matcher = new Perl5Matcher();

			// 实例大小大小写敏感的正规表达式模板
			Pattern hardPattern = compiler.compile(regexp);

			// 返回批配结果
			return matcher.contains(source, hardPattern);

		} catch (MalformedPatternException e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * 
	 * 在字符串中替换字符串。
	 * 
	 * @param str
	 *            原始字符串
	 * @param reg
	 *            正则式
	 * @param str2
	 *            替换成为字符串
	 * @param num
	 *            替换次数,为0则replaceAll
	 */
	public static String replace(String str, String reg, String str2, int num) {
		String result = str;
		if (num == 0) {
			num = Util.SUBSTITUTE_ALL;
		}
		try {
			String content = str;
			String ps1 = reg;
			PatternCompiler orocom = new Perl5Compiler();
			Pattern pattern1 = orocom.compile(ps1);
			PatternMatcher matcher = new Perl5Matcher();
			result = Util.substitute(matcher, pattern1, new Perl5Substitution(
					str2), content, num);
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}

	public static void main(String[] args) {
		Regexp.getInstance();
		String loginName = Regexp.replace(
				"' \" :  dsadasdasd'    ", Regexp.special_char_regexp, "", 0);
		System.out.println(loginName);

	}
}
