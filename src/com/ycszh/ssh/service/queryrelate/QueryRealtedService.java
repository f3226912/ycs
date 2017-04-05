package com.ycszh.ssh.service.queryrelate;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface QueryRealtedService {

	public List<Object> queryResult1(HttpServletRequest request) throws Exception;
	
	public List<Object> queryResult2(HttpServletRequest request) throws Exception;
	
	public List<Object> queryResult3(HttpServletRequest request) throws Exception;
	
	public List<Object> queryResult4(HttpServletRequest request) throws Exception;
	
	public List<Object> queryResult5(HttpServletRequest request) throws Exception;
	
	public List<Object> queryResult6(HttpServletRequest request) throws Exception;

	public String showDetailInfo(HttpServletRequest request) throws Exception;
	
	public void exportToExcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
