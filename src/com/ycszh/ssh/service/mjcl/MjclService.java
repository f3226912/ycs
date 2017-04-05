package com.ycszh.ssh.service.mjcl;

import javax.servlet.http.HttpServletRequest;

public interface MjclService {
	
	public String mjclQuery(HttpServletRequest request, int currentpage) throws Exception ;
	
	public String mjclDeptTj(HttpServletRequest request) throws Exception ;
	
	public String xgslQuery(HttpServletRequest request, int currentpage) throws Exception ;
	
	public String yjgzQuery(HttpServletRequest request, int currentpage) throws Exception ;
}
