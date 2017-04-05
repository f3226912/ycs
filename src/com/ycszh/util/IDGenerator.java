package com.ycszh.util;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.UUID;

import java.util.Random;

/**
 * @包名:com.ycszh.util
 * @文件名:IDGenerator.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-3-14
 * @描述:
 * @版本:V 1.0
 */
public class IDGenerator {
	private String table = "";

	public IDGenerator() {
	}

	public IDGenerator(String table) {
		super();
		this.table = table;
	}

	/**
	 * 生成主键
	 * 
	 * @return
	 */
	public String generate() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(this.table.toUpperCase());
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		buffer.append(sf.format(new Date()));
		Random ran = new Random();
		for (int i = 0; i < 6; i++) {
			buffer.append(ran.nextInt(10));
		}
		return new String(buffer);
	}


	/**
	 * 生成32位唯一识别主键
	 * 
	 * @return
	 */
	public static String uuidGenerate() {
		UUID uuid = UUID.randomUUID();
		String ukey = uuid.toString();
		ukey = ukey.replaceAll("-", "");
		return ukey;
	}

	// public static void main(String[] args) {
	// UUID uuid = UUID.randomUUID();
	// String ukey = uuid.toString();
	// ukey=ukey.replaceAll("-", "");
	// System.out.println(uuid.toString());
	// System.out.println(ukey);
	// }
}
