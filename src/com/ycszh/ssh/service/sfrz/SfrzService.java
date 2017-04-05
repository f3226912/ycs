package com.ycszh.ssh.service.sfrz;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.ssh.hbm.rsc.RscDby;
import com.ycszh.ssh.hbm.rsc.RscDbyZjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzCjxxb;
import com.ycszh.ssh.hbm.sfrz.SfrzUserinfo;

public interface SfrzService {
	public Map<String,String> insertOrUpdateSfrzCjxxb(SfrzCjxxb sfrzCjxxb, File file1,File file2,File file01,File file02, File file03, File file04, File file05, File file06,File file07,File file08,File file09, HttpServletRequest request) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getSfrzCjxxbList(HttpServletRequest request, int currentPage) throws Exception;
	
	public SfrzCjxxb parseXml2(String strxml) throws Exception;
	
	public SfrzCjxxb getSfrzCjxxb(String cid) throws Exception;
	
	public SfrzCjxxb getSfrzCjxxbInfo(String cid) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getVeglist(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public SfrzUserinfo getSfrzA(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public RscDby getRscDby(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public RscDbyZjxxb getRscDbyZjxxb(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getDrvList(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getXphzList(String sfzmhm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Map getCode(String dmlb) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Map getPhoto(String cid) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Map getPhotoBlob(String cid) throws Exception;
	
	public String getCodeVal(String dmlb,String dmz) throws Exception;
	
	public Integer getJzzinfo(String jzzno,String xm) throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getUserInfo(String loginuser,String rejsval)throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getdbrList(String sfzhm,String rzjs)throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getdbr(String sfzhm)throws Exception;
	
	@SuppressWarnings("unchecked")
	public String getsfshList(String sfzmhm,String xm)throws Exception;

	public int getYzdbrSfff(String sfzmhm) throws Exception ;
	
	
	//查询代办员列表
	@SuppressWarnings("unchecked")
	public List getDbyList(HttpServletRequest request) throws Exception;
	
	public Integer updateDby(HttpServletRequest request, String cid,
			String dbyzt, String tbyy) throws Exception;
	
}
