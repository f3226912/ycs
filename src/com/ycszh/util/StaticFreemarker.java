package com.ycszh.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @包名:com.ycszh.util
 * @文件名:StaticFreemarker.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class StaticFreemarker {
	private static final Logger logger = Logger
			.getLogger(StaticFreemarker.class);

	// * @throws IOException
	// * @throws TemplateException
	/**
	 * 初始化模板引擎
	 * 
	 * @param ftl
	 *            模板名称
	 * @param htmlName
	 *            需要生成html页面的名称
	 * @param map
	 *            模板中需要的参数集合
	 * @param relativePath
	 *            模板相对于根路径的相对路径
	 * @param publishPath
	 *            静态页面的发布路径
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String makeHTMl(String ftl, String htmlName, Map map,
			String relativePath, String publishPath) {

		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "/" + relativePath);
		// freemarkerCfg.setEncoding(Locale.getDefault(), "gbk");
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		// freemarkerCfg.setClassForTemplateLoading(this.getClass(),
		// templatePath);
		Template template = null;
		Writer out = null;
		try {

			template = freemarkerCfg.getTemplate(ftl);
			template.setEncoding("UTF-8");
			// template.setEncoding("gbk");
			String path = publishPath + htmlName;

			File htmlFile = new File(path);
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(htmlFile), "UTF-8"));
			template.process(map, out);
			out.flush();
			out.close();
			return path;
		} catch (IOException e) {
			e.printStackTrace();
			logger.info("创建静态页IO异常！   " + e.getMessage());
			return "failure";
		} catch (TemplateException e) {
			e.printStackTrace();
			logger.info("创建静态页模板异常！   " + e.getMessage());
			return "failure";
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					logger.info("创建静态页关闭流异常！    " + e.getMessage());
					e.printStackTrace();
				}
			}

		}

		// String path =
		// ServletActionContext.getServletContext().getRealPath("/");

		// Writer out = new BufferedWriter(new OutputStreamWriter(
		// new FileOutputStream(htmlFile), "gbk"));

		// Writer out = new BufferedWriter(new OutputStreamWriter(
		// new FileOutputStream(htmlFile), "UTF-8"));
		// template.process(map, out);
		// out.flush();
		// out.close();
	}
}
