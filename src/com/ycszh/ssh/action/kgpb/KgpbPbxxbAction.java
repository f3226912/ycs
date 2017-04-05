package com.ycszh.ssh.action.kgpb;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ycszh.util.excel.Util;
import com.ycszh.util.excel.ExportExcelBean;
import com.ycszh.ssh.action.BaseAction;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbPbxxb;
import com.ycszh.ssh.service.kcpb.KgpbKcxxbService;
import com.ycszh.ssh.service.kcpb.KgpbKgxxbService;
import com.ycszh.ssh.service.kcpb.KgpbPbxxbService;
import com.ycszh.util.JsonUtil;

/**
 * @包名:com.ycszh.ssh.action.kgpb
 * @文件名:KgpbPbxxbAction.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public class KgpbPbxxbAction extends BaseAction {

	private KgpbPbxxbService kgpbPbxxbService;
	private KgpbKcxxbService kgpbKcxxbService;
	private KgpbKgxxbService kgpbKgxxbService;
	private ExportExcelBean eeb;
	private Map<String, Object> parmsMap = new LinkedHashMap<String, Object>();
	
	public KgpbKgxxbService getKgpbKgxxbService() {
		return kgpbKgxxbService;
	}
	public void setKgpbKgxxbService(KgpbKgxxbService kgpbKgxxbService) {
		this.kgpbKgxxbService = kgpbKgxxbService;
	}
	public KgpbKcxxbService getKgpbKcxxbService() {
		return kgpbKcxxbService;
	}
	public void setKgpbKcxxbService(KgpbKcxxbService kgpbKcxxbService) {
		this.kgpbKcxxbService = kgpbKcxxbService;
	}
	public KgpbPbxxbService getKgpbPbxxbService() {
		return kgpbPbxxbService;
	}
	public void setKgpbPbxxbService(KgpbPbxxbService kgpbPbxxbService) {
		this.kgpbPbxxbService = kgpbPbxxbService;
	}
	
	
	public String getPbInfo() throws Exception{
		
		// 拿到排班信息
		Map<String, Map> pbMap = kgpbPbxxbService.getPbInfo(request);
		
		request.setAttribute("pbMap", pbMap);
		
		// 拿到考场信息
		List kcList = kgpbKcxxbService.getKcInfo(request);
		request.setAttribute("kcList", kcList);
		
		return "pbList";
	}
	
	public String initUpdatePb() throws Exception{
		
		String pbid = request.getParameter("id");
		
		PrintWriter out = response.getWriter();
		
		List list = kgpbKgxxbService.getKgInfoBySql("select jh,xm from KGPB_KGXXB where zt = '0' and jh not in " +
						" (select kgid from KGPB_PBXXB where pbrq = (select pbrq from KGPB_PBXXB where id = '"+pbid+"'))");
		
		out.print(JsonUtil.list2json(list));
		
		out.flush();
		out.close();
		
		return NONE;
	}
	
	public String updatePb() throws Exception{
		
		
		PrintWriter out = response.getWriter();
		
		int returnValue = -1;
		int dxValue = 0;
		int bgValue = kgpbPbxxbService.updatePbInfo(request);
		
		if(bgValue == 0){
			dxValue = kgpbPbxxbService.dxfs(request);
			if(bgValue == 0 && dxValue == 0){
				returnValue = 0;
			}
			else{
				returnValue = 1;
			}
		}
		
		out.print(returnValue);
		
		out.flush();
		out.close();
		
		return NONE;
	}
	
	public String getInfoExport() throws Exception{
		try{
			response.setContentType("text/html;charset=UTF-8"); 			
			List titleList = kgpbKcxxbService.getKcInfo(request);
			Map<String, Map> pbMap = kgpbPbxxbService.getPbInfo(request);
			List contentList = new ArrayList();
			// 由于第二列为部门ID，不需要，所以删除
//			for(int i = 0; i < contentList.size(); i++){
//				Object[] objs = (Object[])contentList.get(i);
//			}		
			eeb = new ExportExcelBean();
			if(pbMap.size() > 0){
				eeb.setFileName("考场排班");
				eeb.setFileTitle("考场排班");
				
				String[] kgmc = null;
				for(String pbrq : pbMap.keySet()){
					kgmc = new String[titleList.size()+1];
					kgmc[0] = pbrq;
					for(int i = 1; i <= titleList.size(); i ++){
						KgpbKcxxb kgpbKcxxb = (KgpbKcxxb)titleList.get(i-1);
						List list = (List)pbMap.get(pbrq).get(kgpbKcxxb.getKcmc());
						if(list != null){
							for(int y = 0; y < list.size(); y++){
								Object[] obj = (Object[])list.get(y);
								if(kgmc[i] == null){
									kgmc[i] = obj[1].toString();
								}
								else{
									kgmc[i] = (kgmc[i]==null?"":kgmc[i] + "  " + obj[1].toString());
								}
								 
							}
						}
						else{
							kgmc[i] = "";
						}
					}
					contentList.add(kgmc);
				}
				
				eeb.setList(contentList);
				
				parmsMap.put("0", "日期/考场");
				for(int i = 1; i <= titleList.size(); i++){
					KgpbKcxxb obj = (KgpbKcxxb)titleList.get(i-1);
					parmsMap.put(i+"", obj.getKcmc());
				}				
				eeb.setParmsMap(parmsMap);
				Util.exportExcel(response, eeb);
			}else{
				request.setAttribute("exportData", "当前导出数据为空！");  
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
