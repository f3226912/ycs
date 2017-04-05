package com.ycszh.ssh.service.cljstj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.cljstj.Healthdata;

public interface CljstjService {
	//超龄驾驶体检查询
	public List<Healthdata> cljstjQuery(HttpServletRequest request, int currentpage) throws Exception;
	
	//超龄驾驶体检获取
	public String cljstjHq(HttpServletRequest request) throws Exception ;
	
	//超龄驾驶体检审核数据
	public Healthdata cljstjShYm(HttpServletRequest request) throws Exception ;
	
	//超龄驾驶体检审核
	public String cljstjSh(HttpServletRequest request) throws Exception ;
	
	//超龄驾驶体检清除
	public String cljstjQc(HttpServletRequest request) throws Exception ;
	
	//超龄驾驶体检审核数据列表
	public List<Healthdata> cljstjShList(HttpServletRequest request, int currentpage, String ci) throws Exception ;
}
