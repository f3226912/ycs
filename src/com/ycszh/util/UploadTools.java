package com.ycszh.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @包名:com.ycszh.util
 * @文件名:UploadTools.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class UploadTools {
	private static final Log loger = LogFactory.getLog(UploadTools.class);
	private static final int BUFFER_SIZE = 16 * 1024;

	/**
	 * 文件上传
	 * 
	 * @param src
	 *            源地址
	 * @param dst
	 *            目标地址
	 */
	public static String upload(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			String checkString = dst.getAbsolutePath();
			int temp = checkString.lastIndexOf(".");
			if (temp > 0)
				checkString = checkString.substring(temp);

			FileInputStream fis = new FileInputStream(src);
			int len = fis.available();
			if (len / 1024 > 300 && !".xls".equalsIgnoreCase(checkString)) {
				loger.info("上传文件大于300k，而且不是excel文件！");
				return "failure";
			} else {
				in = new BufferedInputStream(fis, BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "failure";
	}

	/**
	 * 文件上传
	 * 
	 * @param src
	 *            源地址
	 * @param dst
	 *            目标地址
	 * @param size
	 *            文件大小单位K
	 * 
	 */
	public static String upload(File src, File dst, int size) {
		InputStream in = null;
		OutputStream out = null;
		try {
			String checkString = dst.getAbsolutePath();
			int temp = checkString.lastIndexOf(".");
			if (temp > 0)
				checkString = checkString.substring(temp);

			FileInputStream fis = new FileInputStream(src);
			int len = fis.available();
			if (len / 1024 > size && !".xls".equalsIgnoreCase(checkString)) {
				loger.info("上传文件大于" + size + "k，而且不是excel文件！");
				return "failure";
			} else {
				in = new BufferedInputStream(fis, BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "failure";
	}

	/**
	 * 文件上传
	 * 
	 * @param src
	 *            源地址
	 * @param dst
	 *            目标地址
	 * @param size
	 *            文件大小单位K
	 * 
	 */
	public static String uploadTemplate(File src, File dst, int size) {
		InputStream in = null;
		OutputStream out = null;
		try {
			String checkString = dst.getAbsolutePath();
			int temp = checkString.lastIndexOf(".");
			if (temp > 0)
				checkString = checkString.substring(temp);

			FileInputStream fis = new FileInputStream(src);
			int len = fis.available();
			if (len / 1024 > size && !".html".equalsIgnoreCase(checkString)) {
				loger.info("上传文件大于" + size + "k，而且不是html文件！");
				return "failure";
			} else {
				in = new BufferedInputStream(fis, BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
				return "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return "failure";
	}
}
