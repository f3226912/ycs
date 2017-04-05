package com.ycszh.ssh.service.jdcjsrxpk;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface JdcjsrxpkService {

	// 根据(号牌号码 and 号牌种类) or 车辆识别代号查询图片码
	@SuppressWarnings("unchecked")
	public List getJdc(HttpServletRequest request) throws Exception;

	// 根据驾驶证号查出相片回执编号,然后通过相片回执编号
	@SuppressWarnings("unchecked")
	public List getJsr(HttpServletRequest request) throws Exception;
	
	// 根据身份证号码查询星级用户相关图文信息
	@SuppressWarnings("unchecked")
	public List getJsrxjxx(HttpServletRequest request) throws Exception;
	
}
