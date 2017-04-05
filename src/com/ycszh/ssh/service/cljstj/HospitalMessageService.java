package com.ycszh.ssh.service.cljstj;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.cljstj.HosHospitalUserinfo;


public interface HospitalMessageService {
	
	public List<HosHospitalUserinfo> hospitalUserInfoQuery(HttpServletRequest request, int currentpage) throws Exception;
	
	public String insertHospitalUserInfo(HttpServletRequest request)throws Exception;
	
	public HosHospitalUserinfo queryHospitalUserInfoByYyxh(HttpServletRequest request) throws Exception;
	
	public String deleteHospitalUserInfoByYyxh(HttpServletRequest request) throws Exception;
	
	public String deleteHospitalUserInfoAll(HttpServletRequest request) throws Exception;
	
	public String hospitalUserStartOrStop(HttpServletRequest request) throws Exception;
	
	public void doctorUserInfoAuditQuery(HttpServletRequest request, int currentpage) throws Exception;
	
	public String doctormessageAudit(HttpServletRequest request) throws Exception;
	
}
