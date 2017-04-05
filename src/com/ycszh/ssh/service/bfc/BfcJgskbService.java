package com.ycszh.ssh.service.bfc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.bfc.BfcJgskb;

/**
 * @包名:com.ycszh.ssh.service.bfc
 * @文件名:BfcJgskbService.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-10-14
 * @描述:
 * @版本:V 1.0
 */
public interface BfcJgskbService {

	public void getBfcJgskbList(HttpServletRequest request,int currentPage) throws Exception;

	public BfcJgskb getBfcJgskbInfo(String xh) throws Exception;
}
