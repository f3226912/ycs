package com.ycszh.ssh.service.drv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.drv.YujingDao;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.drv.VehYujing;
import com.ycszh.ssh.hbm.drv.Yujing;
import com.ycszh.ssh.service.drv.SlgDrvService;
import com.ycszh.ssh.service.drv.YujingService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.StringUtil;

import common.Logger;

/**
 * @包名:com.example.ssh.service.impl
 * @文件名:YujingServiceImpl.java
 * @作者:wy E-mail:wyong@szjst.com.cn
 * @创建日期:2013-5-20
 * @描述:
 * @版本:V 1.0
 */
public class YujingServiceImpl implements YujingService {
	
	private static final Logger logger = Logger.getLogger(YujingServiceImpl.class);
	private String uri = "";
	private YujingDao yujingDao;
	private SlgDrvService slgDrvService;
	
	public YujingDao getYujingDao() {
		return yujingDao;
	}
	public void setYujingDao(YujingDao yujingDao) {
		this.yujingDao = yujingDao;
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	public List getEsDrvFlowList(HttpServletRequest request, String cztype,
			int currentPage) throws Exception {
		
		//查询业务类型
		int count = 0;
		Map map = new HashMap();
		DateUtil du = new DateUtil();
		StringBuffer ywlxBuffer = new StringBuffer("");
		StringBuffer sizesqlBuffer = new StringBuffer("select count(1) from (");
		StringBuffer listsqlBuffer = new StringBuffer("select edf.lsh, edf.sfzmhm, cast(edf.dabh as varchar2(20)), edf.xm, edf.ywlx, edf.ywyy, edf.kssj, edf.jssj, edf.kskm, edf.ywgw, edf.xygw, edf.ywblbm from (");
		StringBuffer sqlBuffer = new StringBuffer("");
		sqlBuffer.append("select * from es_drv_flow where 1=1 ");
		String sdate = request.getParameter("s_date");
		String edate = request.getParameter("e_date");
		if((sdate == null || "".equals(sdate)) && (edate == null || "".equals(edate))){
			String date = du.date2String(new Date());
			sdate = date + " 00:00:00";
			edate = date + " 23:59:59";
		}else if(sdate == null && "".equals(sdate)){
			String date = du.date2String(new Date());
			sdate = date + " 00:00:00";
		}else if(edate == null && "".equals(edate)){
			String date = du.date2String(new Date());
			edate = date + " 23:59:59";
		}
		int pagesize = SysConst.PAGESIZE;
		int offset = (currentPage-1)*pagesize;
		if("query".equals(cztype)){
			uri = request.getRequestURI();
		}
		List<SlgSjzd> ywlxList = this.yujingDao.getSlgDrvXxcjbList("YZYWLX", "");
		if(ywlxList != null && ywlxList.size() > 0){
			int size = ywlxList.size();
			for(int i = 0; i < size; i++){
				ywlxBuffer.append("'"+ywlxList.get(i).getDmz()+"',");
			}
		}
		int len = ywlxBuffer.toString().length();
		String ywlx = "";
		if(len > 0){
			ywlx = ywlxBuffer.substring(0, len -1);
		}
		if(ywlx != null && !"".equals(ywlx)){
			sqlBuffer.append(" and ywlx in ("+ywlx+")");
		}
		//查询业务原因
		List<SlgSjzd> ywyyList = this.yujingDao.getSlgDrvXxcjbList("NYZYWLX", "");
		if(ywyyList != null && ywyyList.size() > 0){
			int ywyysize = ywyyList.size();
			for(int i = 0; i < ywyysize; i++){
				sqlBuffer.append(" and (ywlx <> '"+ywyyList.get(i).getDmz()+"' and ywyy <> '"+ywyyList.get(i).getDmms2()+"')");
			}
		}
		sqlBuffer.append(" and kssj between to_date('"+sdate+"', 'YYYY-MM-DD HH24:MI:SS') and to_date('"+edate+"', 'YYYY-MM-DD HH24:MI:SS')");
		//查询时间段
		int sjday = 0;
		List sjfwList = this.yujingDao.getSlgDrvXxcjbList("YZSJFW", "");
		if(sjfwList != null && sjfwList.size() > 0){
			SlgSjzd sjfw = (SlgSjzd)sjfwList.get(0);
			String dmz = sjfw.getDmz();
			if(dmz != null && !"".equals(dmz)){
				sjday = Integer.parseInt(dmz);
			}
		}
		sqlBuffer.append(")edf left join(");
		sqlBuffer.append("select * from slg_drv_xxcjb where czrq between to_date('"+sdate+"', 'YYYY-MM-DD HH24:MI:SS')-"+sjday+" and to_date('"+edate+"', 'YYYY-MM-DD HH24:MI:SS')+"+sjday);
		sqlBuffer.append(")slg on edf.sfzmhm = slg.sfzmhm where slg.cjid is null");
		String sfzmhm = request.getParameter("sfzmhm");
		if(!StringUtil.isNull(sfzmhm)){
			sqlBuffer.append(" and edf.sfzmhm = '"+sfzmhm+"'");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		sqlBuffer.append(" order by edf.kssj desc");
		count = this.yujingDao.getRepositoryBySQLListSize(sizesqlBuffer.toString()+sqlBuffer.toString());
		List esDrvFlowList = new ArrayList();
		List<Yujing> yujingList = new ArrayList<Yujing>();
		if(count > 0){
			if("query".equals(cztype)){
				esDrvFlowList = this.yujingDao.findSQLByPage(listsqlBuffer.toString()+sqlBuffer.toString(), offset, pagesize);
			}else if("export".equals(cztype)){
				if(count > 10000){
					map.put("uri", uri);
					map.put("pagesize", pagesize);
					map.put("rscount", count);
					map.put("currentpage", currentPage);
					request.setAttribute("s_date", sdate);
					request.setAttribute("e_date", edate);
					request.setAttribute("rscount", count);
					request.setAttribute("map", map);
					request.setAttribute("esDrvFlowList", yujingList);
					request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
					return yujingList;
				}
				esDrvFlowList = this.yujingDao.findSQL(listsqlBuffer.toString()+sqlBuffer.toString());
			}
			
			if(esDrvFlowList != null && esDrvFlowList.size() > 0){
				for(int i = 0; i < esDrvFlowList.size(); i++){
					Yujing yujing = new Yujing();
					Object[] objarr = (Object[])esDrvFlowList.get(i);
					Object glbmobj = this.yujingDao.getGlbm(objarr[11].toString());
					if(glbmobj != null ){
						Object[] glbmarr = (Object[])glbmobj;
						if(glbmarr != null){
							yujing.setGlbm(glbmarr[1].toString());
						}else{
							yujing.setGlbm("");
						}
					}else{
						yujing.setGlbm("");
					}
					Object ywlxobj = this.yujingDao.getYwlx(objarr[4].toString());
					if(ywlxobj != null){
						Object[] ywlxarr = (Object[])ywlxobj;
						if(ywlxarr != null){
							yujing.setYwlx(ywlxarr[3].toString());
						}else{
							yujing.setYwlx("");
						}
					}else{
						yujing.setYwlx("");
					}
					if(objarr[0] != null){
						yujing.setLsh(objarr[0].toString());
					}else{
						yujing.setLsh("");
					}
					if(objarr[1] != null){
						yujing.setSfzmhm(objarr[1].toString());
					}else{
						yujing.setSfzmhm("");
					}
					if(objarr[2] != null){
						yujing.setDabh(objarr[2].toString());
					}else{
						yujing.setDabh("");
					}
					if(objarr[3] != null){
						yujing.setXm(objarr[3].toString());
					}else{
						yujing.setXm("");
					}
					if(objarr[6] != null){
						yujing.setKssj(objarr[6].toString());
					}else{
						yujing.setKssj("");
					}
					yujingList.add(yujing);
				}
			}
		}
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", count);
		map.put("currentpage", currentPage);
		request.setAttribute("s_date", sdate);
		request.setAttribute("e_date", edate);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("esDrvFlowList", yujingList);
		
		logger.info("method:getEsDrvFlowList|pagaram:currentPage="+currentPage);
		return yujingList;
	}
	
	@SuppressWarnings("unchecked")
	public List getVehFlowList(HttpServletRequest request, String cztype, int currentPage) throws Exception{
		List<VehYujing> vehYujList = new ArrayList<VehYujing>();
		String sdate = request.getParameter("s_date");
		String edate = request.getParameter("e_date");
		String lsh = request.getParameter("lsh");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String ywlxparam = request.getParameter("ywlx");
		List<SlgSjzd> hpzlList = new ArrayList<SlgSjzd>();
		hpzlList = this.slgDrvService.getYwlxYwyy(request, "", "", "7", "", "");
		request.setAttribute("hpzlList", hpzlList);
		if((sdate == null || "".equals(sdate)) && (edate == null || "".equals(edate))){
			Date beforedate = DateUtil.getAppointDate(-1);
			String date = DateUtil.date2String(beforedate);
			sdate = date;
			edate = date;
		}else if(sdate == null && "".equals(sdate)){
			Date beforedate = DateUtil.getAppointDate(-1);
			String date = DateUtil.date2String(beforedate);
			sdate = date;
		}else if(edate == null && "".equals(edate)){
			Date beforedate = DateUtil.getAppointDate(-1);
			String date = DateUtil.date2String(beforedate);
			edate = date;
		}
		Map map = new HashMap();
		String url = "";
		if("query".equals(cztype)){
			url = request.getRequestURI();
		}
		int pagesize = SysConst.PAGESIZE;
		int offset = (currentPage-1)*pagesize;
		int count = 0;
		
//		StringBuffer sizeStringBuffer = new StringBuffer("select count(1) from ( "); 
//		StringBuffer listStringBuffer = new StringBuffer("select t.lsh, t.hphm, t.hpzl, t.clsbdh, t.syr, t.sqrq, t.ywlx, t.glbm from es_veh_flow t");
//		listStringBuffer.append(" where ((((not exists (select * from dbjg_zjxxb t1 where t1.hphm = t.hphm and t1.hpzl = t.hpzl and t1.lrsj >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.lrsj < to_date('"+edate+"', 'yyyy-MM-dd')+1)) or");
//		listStringBuffer.append(" (exists (select *  from dbjg_zjxxb t1");
//		listStringBuffer.append(" where t1.hphm = t.hphm and t1.hpzl = t.hpzl and trunc(t.sqrq) <> trunc(t1.lrsj) and t1.lrsj >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.lrsj < to_date('"+edate+"', 'yyyy-MM-dd')+1))) and ");
//		listStringBuffer.append("  ywlx <> 'V' and hphm is not null and hpzl is not null) or");
//		listStringBuffer.append(" ((not exists");
//		listStringBuffer.append(" (select * from dbjg_zjxxb t1 where t1.lsh = t.lsh and t1.lrsj >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.lrsj < to_date('"+edate+"', 'yyyy-MM-dd')+1) or exists ");
//		listStringBuffer.append(" (select * from dbjg_zjxxb t1  where t1.lsh = t.lsh and trunc(t.sqrq) <> trunc(t1.lrsj) and t1.lrsj >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.lrsj < to_date('"+edate+"', 'yyyy-MM-dd')+1)) and ywlx <> 'V' and  lsh is not null)) ");
//		//listStringBuffer.append(" (select * from dbjg_zjxxb t1 where t1.lsh = t.lsh) or exists ");
//		
//		
//		listStringBuffer.append(" and sqrq >= to_date('"+sdate+"', 'yyyy-MM-dd') and sqrq < to_date('"+edate+"', 'yyyy-MM-dd')+1 ");
//		
//		//流水号
//		if(!StringUtil.isNull(lsh)){
//			listStringBuffer.append(" and lsh = '"+lsh+"'");
//			request.setAttribute("lsh", lsh);
//		}
//		//号牌号码和号牌种类
//		if(!StringUtil.isNull(hphm)){
//			hphm = hphm.toUpperCase();
//			if(hphm.contains("粤")){
//				hphm = hphm.replaceAll("粤", "");
//			}
//			listStringBuffer.append(" and hphm = '"+hphm+"' ");
//			request.setAttribute("hphm", hphm);
//		}
//		if(!StringUtil.isNull(hpzl)){
//			listStringBuffer.append(" and hpzl = '"+hpzl+"' ");
//			request.setAttribute("hpzl", hpzl);
//		}
		
		StringBuffer sizeStringBuffer = new StringBuffer("select count(1) from  es_veh_flow t1 where not exists (select * from dbjg_zjxxb t2  where (t1.LSH = t2.lsh or (t1.HPHM = t2.hphm and t1.HPZL = t2.hpzl))  and to_char(t1.SQRQ, 'yyyymmdd') = to_char(t2.lrsj, 'yyyymmdd')) "); 
		StringBuffer listStringBuffer = new StringBuffer("select t1.lsh, t1.hphm, cast(t1.hpzl as varchar2(20)), t1.clsbdh, t1.syr, t1.sqrq, t1.ywlx, t1.glbm, t1.ywyy from es_veh_flow t1 where not exists (select * from dbjg_zjxxb t2  where (t1.LSH = t2.lsh or (t1.HPHM = t2.hphm and t1.HPZL = t2.hpzl))  and to_char(t1.SQRQ, 'yyyymmdd') = to_char(t2.lrsj, 'yyyymmdd')) ");
		
		sizeStringBuffer.append(" and t1.sqrq >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.sqrq < to_date('"+edate+"', 'yyyy-MM-dd') + 1");
		listStringBuffer.append(" and t1.sqrq >= to_date('"+sdate+"', 'yyyy-MM-dd') and t1.sqrq < to_date('"+edate+"', 'yyyy-MM-dd') + 1");
		
		//流水号
		if(!StringUtil.isNull(lsh)){
			sizeStringBuffer.append(" and t1.lsh = '"+lsh+"'");
			listStringBuffer.append(" and t1.lsh = '"+lsh+"'");
			request.setAttribute("lsh", lsh);
		}
		//号牌号码和号牌种类
		if(!StringUtil.isNull(hphm)){
			hphm = hphm.toUpperCase();
			if(hphm.contains("粤")){
				hphm = hphm.replaceAll("粤", "");
			}
			listStringBuffer.append(" and t1.hphm = '"+hphm+"' ");
			sizeStringBuffer.append(" and t1.hphm = '"+hphm+"'");
			request.setAttribute("hphm", hphm);
		}
		if(!StringUtil.isNull(hpzl)){
			listStringBuffer.append(" and t1.hpzl = '"+hpzl+"' ");
			sizeStringBuffer.append(" and t1.hpzl = '"+hpzl+"'");
			request.setAttribute("hpzl", hpzl);
		}
		
		//业务类型
		if(StringUtil.isNull(ywlxparam)){
			sizeStringBuffer.append(" and t1.ywlx <> 'V' ");
			listStringBuffer.append(" and t1.ywlx <> 'V' ");
		}else{
			if("A:A".equals(ywlxparam)){
				sizeStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'A' and t1.ywyy = 'A') ");
				listStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'A' and t1.ywyy = 'A') ");
			}else if("B:B".equals(ywlxparam)){
				sizeStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'B' and t1.ywyy = 'B') ");
				listStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'B' and t1.ywyy = 'B') ");
			}else if("B:C".equals(ywlxparam)){
				sizeStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'B' and t1.ywyy = 'C') ");
				listStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = 'B' and t1.ywyy = 'C') ");
			}else{
				sizeStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = '"+ywlxparam+"')");
				listStringBuffer.append(" and (t1.ywlx <> 'V' and t1.ywlx = '"+ywlxparam+"')");
			}
			request.setAttribute("ywlx", ywlxparam);
		}
		
		count = this.yujingDao.getRepositoryBySQLListSize(sizeStringBuffer.toString());
		listStringBuffer.append(" order by t1.sqrq desc ");
		if(count > 0){
			List yujiingList = new ArrayList();
			if("query".equals(cztype)){
				yujiingList = this.yujingDao.findSQLByPage(listStringBuffer.toString(), offset, pagesize);
			}else if("export".equals(cztype)){
				if(count > 1000){
					map.put("uri", uri);
					map.put("pagesize", pagesize);
					map.put("rscount", count);
					map.put("currentpage", currentPage);
					request.setAttribute("s_date", sdate);
					request.setAttribute("e_date", edate);
					request.setAttribute("rscount", count);
					request.setAttribute("map", map);
					request.setAttribute("esDrvFlowList", vehYujList);
					request.setAttribute("exportData", "数据量太多无法一次导出，请筛选！");
					return vehYujList;
				}
				yujiingList = this.yujingDao.findSQL(listStringBuffer.toString());
			}
			//翻译业务办理部门和业务类型
			if(yujiingList != null && yujiingList.size() > 0){
				//查询所有业务类型
				List ywlxList = getEsVehCode(request, "", "0060", "");
				Map<String, String> ywlxMap = new HashMap<String, String>();
				if (ywlxList != null && ywlxList.size() > 0) {
					for (int i = 0; i < ywlxList.size(); i++) {
						Object[] objs = (Object[])ywlxList.get(i);
						ywlxMap.put(objs[1].toString(), objs[2].toString());
					}
				}
				
				//管理部门
				List glbmList = getVehDepartment(request, "", "");
				Map<String, String> glbmMap = new HashMap<String, String>();
				if(glbmList != null && glbmList.size() > 0){
					for(int i = 0; i < glbmList.size(); i++){
						Object[] objs = (Object[])glbmList.get(i);
						glbmMap.put(objs[0].toString(), objs[1].toString());
					}
				}
				
				//号牌种类
				Map<String, String> hpzlMap = new HashMap<String, String>();
				if(hpzlList != null && hpzlList.size() > 0){
					for(int i = 0; i < hpzlList.size(); i++){
						SlgSjzd slgSjzd = (SlgSjzd)hpzlList.get(i);
						hpzlMap.put(slgSjzd.getDmz(), slgSjzd.getDmms1());
					}
				}
				
				for(int i = 0; i < yujiingList.size(); i++){
					VehYujing vehYujing = new VehYujing();
					Object[] objs = (Object[])yujiingList.get(i);
					if(objs[0] != null){
						vehYujing.setLsh(objs[0].toString());
					}else{
						vehYujing.setLsh("");
					}
					if(objs[1] != null){
						vehYujing.setHphm(objs[1].toString());
					}else{
						vehYujing.setHphm("");
					}
					if(objs[2] != null){
						String hpzlstr = objs[2].toString();
						if(hpzlstr.length() == 1){
							hpzlstr = "0"+hpzlstr;
						}
						String hpzlval = hpzlMap.get(hpzlstr);
						if(!StringUtil.isNull(hpzlval)){
							vehYujing.setHpzl(hpzlval);
						}else{
							vehYujing.setHpzl(objs[2].toString());
						}
						
					}else{
						vehYujing.setHpzl("");
					}
					if(objs[3] != null){
						vehYujing.setSfzmhm(objs[3].toString());
					}else{
						vehYujing.setSfzmhm("");
					}
					if(objs[4] != null){
						vehYujing.setSyr(objs[4].toString());
					}else{
						vehYujing.setSyr("");
					}
					if(objs[5] != null){
						vehYujing.setSqrq(objs[5].toString());
					}else{
						vehYujing.setSqrq("");
					}
					if(objs[6] != null){
						if("A".equals(objs[6]+"") && "A".equals(objs[8]+"")){
							vehYujing.setYwlx("注册登记");
						}else if("B".equals(objs[6]+"") && "B".equals(objs[8]+"")){
							vehYujing.setYwlx("转移登记(市内过户)");
						}else if("B".equals(objs[6]+"") && "C".equals(objs[8]+"")){
							vehYujing.setYwlx("转移登记(市外过户)");
						}else{
							String ywlx = ywlxMap.get(objs[6].toString());
							if(!StringUtil.isNull(ywlx)){
								vehYujing.setYwlx(ywlx);
							}else{
								vehYujing.setYwlx(objs[6].toString());
							}
						}
						
					}else{
						vehYujing.setYwlx("");
					}
					if(objs[7] != null){
						String ywblbm = glbmMap.get(objs[7].toString());
						if(!StringUtil.isNull(ywblbm)){
							vehYujing.setYwblbm(ywblbm);
						}else{
							vehYujing.setYwblbm(objs[7].toString());
						}
						
					}else{
						vehYujing.setYwblbm("");
					}
					vehYujList.add(vehYujing);
				}
			}
			
		}
		
		map.put("uri", url);
		map.put("pagesize", pagesize);
		map.put("rscount", count);
		map.put("currentpage", currentPage);
		request.setAttribute("s_date", sdate);
		request.setAttribute("e_date", edate);
		request.setAttribute("rscount", count);
		request.setAttribute("map", map);
		request.setAttribute("esDrvFlowList", vehYujList);
		return vehYujList;
	}
	
	@SuppressWarnings("unchecked")
	public List<SlgSjzd> getSlgSjzdList(HttpServletRequest request, String dmlb, String dmz) throws Exception{
		return this.yujingDao.getSlgDrvXxcjbList(dmlb, "");
	}
	
	@SuppressWarnings("unchecked")
	public List getEsVehCode(HttpServletRequest request, String xtlb, String dmlb, String dmz)  throws Exception{
		List vehCode = new ArrayList();
		String sql = "select dmlb, dmz, dmsm1, dmsm2, dmsm3, dmsm4 from es_veh_code t where 1=1 ";
		if(!StringUtil.isNull(xtlb)){
			sql += " and t.xtlb = '"+xtlb+"'";
		}
		if(!StringUtil.isNull(dmlb)){
			sql += " and t.dmlb = '"+dmlb+"'";
		}
		if(!StringUtil.isNull(dmz)){
			sql += " and t.dmz = '"+dmz+"'";
		}
		vehCode = this.yujingDao.findSQL(sql);
		return vehCode;
	}
	
	@SuppressWarnings("unchecked")
	public List getVehDepartment(HttpServletRequest request, String sjbm, String glbm) throws Exception{
		List departList = new ArrayList();
		//v_wf_department
		//extshare_wf.v_frm_department
		String sql = "select  * from extshare_wf.v_frm_department t where 1=1 ";
		if(!StringUtil.isNull(sjbm)){
			sql += " and t.sjbm = '"+sjbm+"'";
		}
		if(!StringUtil.isNull(glbm)){
			sql += " and t.xtlb = '"+glbm+"'";
		}
		departList = this.yujingDao.findSQL(sql);
		return departList;
	}
	public SlgDrvService getSlgDrvService() {
		return slgDrvService;
	}
	public void setSlgDrvService(SlgDrvService slgDrvService) {
		this.slgDrvService = slgDrvService;
	}
	
}
