package com.ycszh.ssh.service.sjk;

import java.io.File;

/**
 * @包名:com.ycszh.ssh.service.sjk
 * @文件名:SjkInfoService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-7-22
 * @描述:
 * @版本:V 1.0
 */
public interface SjkInfoService {

	/**
	 * 短信发送
	 * @param uploadExcel
	 * @param dxrn
	 * @return
	 * @throws Exception
	 */
	public String readExcel(File uploadExcel, String dxrn) throws Exception;
	
}
