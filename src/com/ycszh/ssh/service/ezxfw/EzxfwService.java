package com.ycszh.ssh.service.ezxfw;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.formbean.DrvSuperviseForm;
import com.ycszh.formbean.VehSuperviseForm;
import com.ycszh.ssh.hbm.dydj.DydjYhUser;
import com.ycszh.ssh.hbm.ezxfw.EzDrvLiceChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzJdcChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrint;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrintPhoto;

public interface EzxfwService {
	@SuppressWarnings("unchecked")
	public List getDrvChanList(HttpServletRequest request, int currentPage)
			throws Exception;

	@SuppressWarnings("unchecked")
	public void getJdcCheckList(HttpServletRequest request, int currentPage)
			throws Exception;

	@SuppressWarnings("unchecked")
	public List getDrvCheckList(HttpServletRequest request, int currentPage)
			throws Exception;

	@SuppressWarnings("unchecked")
	public List getDrvGzlList(HttpServletRequest request, int currentPage)
			throws Exception;

	@SuppressWarnings("unchecked")
	public List getDrvGzlView(HttpServletRequest request) throws Exception;

	@SuppressWarnings("unchecked")
	public void getJdcGzlList(HttpServletRequest request, int currentPage)
			throws Exception;
	@SuppressWarnings("unchecked")
	public List getJdcGzlView(HttpServletRequest request) throws Exception;
	@SuppressWarnings("unchecked")
	public List getDrvCheckView(HttpServletRequest request) throws Exception;

	@SuppressWarnings("unchecked")
	public List getJdcCheckView(HttpServletRequest request) throws Exception;

	public EzDrvLiceChanApp getDrvChanInfo(HttpServletRequest request,String wwlsh) throws Exception;

	public Integer updateDrvChan(HttpServletRequest request, String wwlsh,
			String clzt, String clztsm) throws Exception;

	@SuppressWarnings("unchecked")
	public List getJdcChanList(HttpServletRequest request, int currentPage)
			throws Exception;

	public EzJdcChanApp getJdcChanInfo(String wwlsh) throws Exception;

	public Integer updateJdcChan(HttpServletRequest request, String wwlsh,
			String clzt, String clztsm) throws Exception;

	@SuppressWarnings("unchecked")
	public List getChanList(HttpServletRequest request, int currentPage,
			String type) throws Exception;

	@SuppressWarnings("unchecked")
	public List getChaninfoByflow(String tyblsh, String type) throws Exception;

	public Integer updateChan(HttpServletRequest request, String appsval,String type)
			throws Exception;
	
	public Integer updateChanDg(HttpServletRequest request, String tyblsh,String type,String applsh)
	throws Exception;

	@SuppressWarnings("unchecked")
	public List getChanViewList(HttpServletRequest request, String pch,
			String type) throws Exception;

	public String getSfrzUserinfo(String loginuser)
			throws Exception;
	
	@SuppressWarnings("unchecked")
	public List getTqjdc(HttpServletRequest request,int currentPagent) throws Exception;
	
	public List getTqdrv(HttpServletRequest request,int currentPagent) throws Exception;
	
	public Integer updateTqjdc(HttpServletRequest request,List list) throws Exception;
	
	public Integer updateTqdrv(HttpServletRequest request,List list) throws Exception;
	
	
	/*
	 * 驾驶证流水查询
	 */
	public List getDrvLscxList(HttpServletRequest request, int currentPage) throws Exception;
	
	/*
	 * 机动车流水查询
	 */
	public List getJdcLscxList(HttpServletRequest request, int currentPage) throws Exception;
	
	public String getSfrzWxJdc(String sfzmhm)
	throws Exception;
	
	public String getSfrzWxJsr(String jszhm)
	throws Exception;
	public Integer getCheckGzsbh(String clsbdh) throws Exception;
	
	public List getEzXxdPrintList(HttpServletRequest request, int currentPage)
	throws Exception;
	
	public List getTqprint(HttpServletRequest request,int currentPagent) throws Exception;
	
	@SuppressWarnings("unchecked")
	public Integer updateTqprint(HttpServletRequest request,List list) throws Exception;
	
	public EzXxdPrint getEzXxdPrintInfo(HttpServletRequest request,String printXh) throws Exception;
	
	public Integer updatePrintChan(HttpServletRequest request, String printXh,
			String shzt, String tbyy,String tpid) throws Exception;
	
	public EzXxdPrintPhoto editPrintPhoto(HttpServletRequest request,EzXxdPrintPhoto printPhoto) throws Exception;
	
	public int editPhoto (EzXxdPrintPhoto printPhoto,File file) throws Exception;
	public String getTpid () throws Exception;
	public byte[] getImageBlob(HttpServletRequest request, String tpid) throws Exception;
	public int editeBlobByByte(byte[] arry_byte,EzXxdPrintPhoto printPhoto) throws Exception ;
	
	


    /***
     * 获取机动车六合一受理的数据，没有经过网上车管所或在线申请 (90内预警出来)
     * @param form
     * @throws Exception 
     */
	public List getVehBusinessSuperviseData(VehSuperviseForm form) throws Exception;
	/**
	 *  获取驾驶证六合一受理的数据，没有经过网上车管所或在线申请 (90内预警出来)
	 * @param from
	 * @return
	 * @throws Exception
	 */
	public List getDrvBusinessSuperviseData(DrvSuperviseForm form) throws Exception;
}
