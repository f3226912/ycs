package com.ycszh.ssh.service.jdcbg;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;

public interface ContactBgService {
	
	//机动车联系方式变更列表
	public List<TJdclxfsbg> contactBgQuery(HttpServletRequest request, int currentpage,String ci)throws Exception;
	
	//机动车联系方式变更获取
	public String contactBgHq(HttpServletRequest request)throws Exception;
	
	//机动车联系方式变更审核页面
	public String contactBgShym(HttpServletRequest request)throws Exception;
	
	//机动车联系方式变更审核
	public String contactBgSh(HttpServletRequest request)throws Exception;

	//机动车联系方式变更查询
	public List<TJdclxfsbg> jdcBgQuery(HttpServletRequest request, int currentpage,String ci)throws Exception;
	
	//机动车联系方式变更清除
	public String contactBgQc(HttpServletRequest request)throws Exception;
	
}
