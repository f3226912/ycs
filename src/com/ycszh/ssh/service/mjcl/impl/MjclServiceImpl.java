package com.ycszh.ssh.service.mjcl.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.service.mjcl.MjclService;
import com.ycszh.util.DateUtil;

public class MjclServiceImpl implements MjclService{
	private final static Logger log = Logger.getLogger(MjclServiceImpl.class);
	private DefaultDao defaultDao;
	
	@SuppressWarnings({"unchecked"})
	public String mjclQuery(HttpServletRequest request, int currentPage)throws Exception {
		StringBuffer hql = new StringBuffer("select lsh,cast(xh as varchar2(20)),hphm,cast(hpzl as varchar2(20)),syr,clxh,to_char(sqrq,'yyyy-MM-dd HH24:mi:ss'),to_char(bjrq,'yyyy-MM-dd HH24:mi:ss'),glbm from es_veh_flow where (Ywlx = 'P' OR Ywlx = 'R') and Ywyy = 'F' and Lszt <> 'Q' ");
		StringBuffer hqldept2 = new StringBuffer("select glbm,bmmc from v_frm_department ");
		StringBuffer hqldept =new StringBuffer("select t1.glbm,t1.bmmc from es_veh_flow t,v_frm_department t1 WHERE t.glbm = t1.GLBM and (t.Ywlx = 'P' OR t.Ywlx = 'R') AND t.Ywyy = 'F' AND t.Lszt <> 'Q' group by t1.glbm,t1.bmmc order by glbm");
		StringBuffer depttj = new StringBuffer("select t1.glbm,t1.bmmc,count(1) from es_veh_flow t,v_frm_department t1 WHERE t.glbm = t1.GLBM and (t.Ywlx = 'P' OR t.Ywlx = 'R') AND t.Ywyy = 'F' AND t.Lszt <> 'Q' ");
		
		String glbms = request.getParameter("glbms")==null?"":request.getParameter("glbms").trim();
		String s_date = request.getParameter("s_date")==null?"":request.getParameter("s_date").trim();
		String e_date = request.getParameter("e_date")==null?"":request.getParameter("e_date").trim();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		//管理部门
		if (glbms!= null&& !glbms.equals("")) {
			glbms = glbms.replaceAll(" ", "");
			glbms = glbms.replaceAll("'", "");
			glbms = glbms.replaceAll("\"", "");
			glbms = glbms.replaceAll("，", ",");
			hql.append(" and glbm = '"+glbms+"' ");
			request.setAttribute("glbms", glbms);
		}
		
		//申请时间
		if (s_date != null && e_date != null && !s_date.equals("")&& !e_date.equals("")) {
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null || !s_date.equals("")&& e_date.equals("")) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date == null && e_date != null || s_date.equals("")&& !e_date.equals("")) {
			Date d = DateUtil.getAppointDateByHour(192);
			s_date = DateUtil.date2String(d);
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}else {
			s_date = DateUtil.date2String(new Date());
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		
		List list = new ArrayList();
		List listCount = new ArrayList();
		List listDept = new ArrayList();
		List listDept2 = new ArrayList();
		List deptTj = new ArrayList();
		int size=0;
		
		depttj.append(" group by t1.glbm,t1.bmmc order by glbm  "); 
		listCount = defaultDao.findSQL(hql.toString());
		listDept2 = defaultDao.findSQL(hqldept2.toString());
		//deptTj = defaultDao.findSQL(depttj.toString());
		
		if(request.getSession().getAttribute("listDept")==null ){
			listDept = defaultDao.findSQL(hqldept.toString());
			request.getSession().setAttribute("listDept", listDept);
		}
		size=listCount.size();
		
		if(size>0 && listDept2.size()>0){
			for (int i = 0; i < listDept2.size(); i++) {
				Object de[]= (Object[]) listDept2.get(i);
				for (int j = 0; j < listCount.size(); j++) {
					Object mj[]=(Object[]) listCount.get(j);
					if(de[0].toString().equals(mj[8].toString())){
						mj[8]=de[1].toString();
					}
				}
			}
		}
		
		if(listCount.size()<pageSize){
			list=listCount;
		}else {
			if((offset+pageSize)>listCount.size()){
				list=listCount.subList(offset, listCount.size());			
			}else {
				list=listCount.subList(offset, offset+pageSize);
			}
		}
			
		request.setAttribute("rscount", size);
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		
		request.setAttribute("map", map);
		request.setAttribute("rscount", size);
		request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		request.setAttribute("deptTj", deptTj);
		
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public String yjgzQuery(HttpServletRequest request, int currentpage)throws Exception {
		StringBuffer hql = new StringBuffer("select lsh,sfzmhm,xm,cllx,syr,ywlx,to_char(clrq,'yyyy-MM-dd HH24:mi:ss'),jbr,glbmmc,sfwg_yy,send_info from jdc_wg_yjgz where sfwg='1' ");
		StringBuffer hqlsize = new StringBuffer("select count(0) from jdc_wg_yjgz where sfwg='1' ");
		StringBuffer hql2 = new StringBuffer("");
		StringBuffer hqldept =new StringBuffer("select t1.glbm,t1.bmmc from v_frm_department t1 WHERE exists(select t.glbm from jdc_wg_yjgz t where t.glbm = t1.GLBM) order by t1.glbm");
		StringBuffer hqlywlx = new StringBuffer("select * from es_veh_code where dmlb='0060'");
		StringBuffer hqlcllx=new StringBuffer("select * from es_veh_code where dmlb='4'");
		
		String ywlx = request.getParameter("ywlx")==null?"":request.getParameter("ywlx").trim();
		String lsh = request.getParameter("lsh")==null?"":request.getParameter("lsh").trim();
		String wglx = request.getParameter("sfwg")==null?"":request.getParameter("sfwg").trim();
		String glbm = request.getParameter("glbm")==null?"":request.getParameter("glbm").trim();
		String s_date = request.getParameter("s_date")==null?"":request.getParameter("s_date").trim();
		String e_date = request.getParameter("e_date")==null?"":request. getParameter("e_date").trim();
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentpage-1);
		String curi = request.getRequestURI();
		
 		if (glbm!= null&& !glbm.equals("")) {
			glbm = glbm.replaceAll(" ", "");
			glbm = glbm.replaceAll("'", "");
			glbm = glbm.replaceAll("\"", "");
			glbm = glbm.replaceAll("，", ",");
			hql.append(" and glbm = '"+glbm+"' ");
			hql2.append(" and glbm = '"+glbm+"' ");
			hqlsize.append(" and glbm = '"+glbm+"' ");
			request.setAttribute("glbm", glbm);
		}
		
		//处理时间
		if (s_date != null && e_date != null && !s_date.equals("")&& !e_date.equals("")) {
			hql.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null || !s_date.equals("")&& e_date.equals("")) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date == null && e_date != null || s_date.equals("")&& !e_date.equals("")) {
			Date d = DateUtil.getAppointDateByHour(192);
			s_date = DateUtil.date2String(d);
			hql.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}else {
			s_date =DateUtil.date2ld();
			e_date = DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
			hql.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (clrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		//流水号
		if(lsh!=null&&!lsh.equals("")){
			hql.append(" and lsh = '"+lsh+"' ");
			request.setAttribute("lsh", lsh);
		}
		//违规类型
		if (wglx!= null&& !wglx.equals("")) {
			hql.append(" and sfwg_yy = '"+wglx+"' ");
			hqlsize.append(" and sfwg_yy ='"+wglx+"'");
			request.setAttribute("sfwg_yy", wglx);
		}
		//业务类型
		if (ywlx!= null&& !ywlx.equals("")) {
			hql.append(" and ywlx ='"+ywlx+"' ");
			hqlsize.append(" and ywlx ='"+ywlx+"' ");
			request.setAttribute("ywlx", ywlx);
		}
		
		
		List listCount = new ArrayList();
		List listDept = new ArrayList();
		//List listDept2 = new ArrayList();
		//List deptTj = new ArrayList();
		List listywlx = new ArrayList();
		int size=0;
		@SuppressWarnings("unused")
		String hgrysize = "0";
		List listcllx = new ArrayList();
		List listsize = new ArrayList();
		hql.append(" order by clrq desc");
		List list = defaultDao.findSQLByPage(hql.toString(), offset, pageSize);
		listsize = defaultDao.findSQL(hqlsize.toString());
		
		
		if(request.getSession().getAttribute("listDept")==null ){
			listDept = defaultDao.findSQL(hqldept.toString());
			request.getSession().setAttribute("listDept", listDept);
		}else{
			listDept = (List) request.getSession().getAttribute("listDept");
		}
		
		if(request.getSession().getAttribute("listywlx")==null ){
			listywlx = defaultDao.findSQL(hqlywlx.toString());
			request.getSession().setAttribute("listywlx", listywlx);
		}
		if(request.getSession().getAttribute("listcllx")==null ){
			listywlx = defaultDao.findSQL(hqlcllx.toString());
			request.getSession().setAttribute("listcllx", listcllx);
		}
	   request.setAttribute("wglx", wglx);
	   Map<String,String> ywlxMap= this.getYwlx("0060");
  	   Map<String,String> cllxMap= this.getYwlx("4");

		if(null != list && list.size()>0){
  			for (int i = 0; i < list.size(); i++) {
  				Object[] objs = (Object[])list.get(i);
  				if(objs[3]!=null){
  					objs[3]=cllxMap.get(objs[3]);
  				}
  				if(objs[5]!=null){
  					objs[5]=ywlxMap.get(objs[5]);
  				}
  			}

		}
		if(null != listsize && listsize.size() > 0){
			size = Integer.parseInt(listsize.get(0).toString());
			if(size <= 50000){
				listCount = defaultDao.findSQL(hql.toString());
			}
		}
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentpage);
		
		request.setAttribute("map", map);
		request.setAttribute("rscount", size);
		request.setAttribute("list", list);
		request.setAttribute("listCount", listCount);
		return "";
	}
	
	//通过union all将表es_veh_flow_his和表es_veh_flow两表数据联合起来进行相关业务查询（最新）
	@SuppressWarnings({"unchecked"})
	public String xgslQuery(HttpServletRequest request, int currentPage)throws Exception {
		StringBuffer hql = new StringBuffer("select * from ( " +
				"select c.lsh,cast(c.xh as varchar2(20)),c.hphm,cast(c.hpzl as varchar2(20)),c.syr,c.clxh,to_char(c.sqrq,'yyyy-MM-dd hh24:mi:ss') sqrq,to_char(c.sqrq,'yyyy-MM-dd hh24:mi:ss') bjrq,c.glbm,c.ywlx,c.ywyy,c.CLLX from es_veh_flow c " +
				" union all " +
				"select d.lsh,cast(d.xh as varchar2(20)),d.hphm,cast(d.hpzl as varchar2(20)),d.syr,d.clxh,to_char(d.sqrq,'yyyy-MM-dd hh24:mi:ss') sqrq,to_char(d.sqrq,'yyyy-MM-dd hh24:mi:ss') bjrq,d.glbm,d.ywlx,d.ywyy,d.CLLX from es_veh_flow_his d) e where 1=1");
		StringBuffer hqlsize = new StringBuffer("select count(0) from (select * from es_veh_flow c union all select * from es_veh_flow_his d ) e where 1=1");
		StringBuffer hql2 = new StringBuffer("");
		StringBuffer hqldept =new StringBuffer("select t1.glbm, t1.bmmc from v_frm_department t1 where exists (select * from ( select t.glbm from es_veh_flow t union all select d.glbm from es_veh_flow_his d) e where e.glbm = t1.glbm) order by t1.glbm");
		
		StringBuffer hqlywlx = new StringBuffer("select dmz,dmsm1 from es_drv_code where XTLB='01' AND DMLB='0060' and dmz <> 'B'");
		StringBuffer hqlxglx = new StringBuffer("select dmz,dmms1 from CLXG_SJZD where DMLB='XGCLLX'");

		String glbms = request.getParameter("glbms")==null?"":request.getParameter("glbms").trim();
		String ywlxs = request.getParameter("ywlxs")==null?"":request.getParameter("ywlxs").trim();
		String[] xglxs = request.getParameterValues("xglxs");
		String s_date = request.getParameter("s_date")==null?"":request.getParameter("s_date").trim();
		String e_date = request.getParameter("e_date")==null?"":request.getParameter("e_date").trim();
		String qxch = request.getParameter("qxch")==null?"":request.getParameter("qxch").trim();
		request.setAttribute("qxch", qxch);
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		
		//管理部门
		if (glbms!= null&& !glbms.equals("")) {
			glbms = glbms.replaceAll(" ", "");
			glbms = glbms.replaceAll("'", "");
			glbms = glbms.replaceAll("\"", "");
			glbms = glbms.replaceAll("，", ",");
			hql.append(" and glbm = '"+glbms+"' ");
			hql2.append(" and e.glbm = '"+glbms+"' ");
			hqlsize.append(" and glbm = '"+glbms+"' ");
			
			request.setAttribute("glbms", glbms);
		}
		
		//业务类型
		if (ywlxs!= null&& !ywlxs.equals("")) {
			if("BB".equals(ywlxs) || "BC".equals(ywlxs)){
				if("BB".equals(ywlxs)){
					hql.append(" and e.ywlx = 'B' and e.ywyy='B'");
					hql2.append(" and e.ywlx = 'B' and e.ywyy='B'");
					hqlsize.append(" and e.ywlx = 'B' and e.ywyy='B'");

					request.setAttribute("ywlxs", ywlxs);
				}else{
					hql.append(" and e.ywlx = 'B' and e.ywyy='C'");
					hql2.append(" and e.ywlx = 'B' and e.ywyy='C'");
					hqlsize.append(" and e.ywlx = 'B' and e.ywyy='C'");

					request.setAttribute("ywlxs", ywlxs);
				}
			}else{
				hql.append(" and e.ywlx = '"+ywlxs+"' ");
				hql2.append(" and e.ywlx = '"+ywlxs+"' ");
				hqlsize.append(" and e.ywlx = '"+ywlxs+"' ");

				request.setAttribute("ywlxs", ywlxs);
			}
		}
		
		//限购类型
		if (xglxs!= null&& !xglxs.equals("")) {
			String xglxstr = "";
			String xglxstr2 = "";
			for(int i = 0 ; i < xglxs.length; i ++){
				if(xglxstr.equals("")){
					xglxstr += "'" + xglxs[i] + "'";
					xglxstr2 += xglxs[i];
				}else{
					xglxstr += ",'" + xglxs[i] + "'";
					xglxstr2 += "," + xglxs[i];
				}
			}
			if(xglxstr2!= null&& !xglxstr2.equals("")){
				hql.append(" and e.CLLX in( "+xglxstr+" )");
				hql2.append(" and e.CLLX in( "+xglxstr+" )");
				hqlsize.append(" and e.CLLX in( "+xglxstr+" )");

				request.setAttribute("xglxs", xglxstr2);
			}
		}
		
		//申请时间
		if (s_date != null && e_date != null && !s_date.equals("")&& !e_date.equals("")) {
			hql.append(" and sqrq >= '"+ s_date +"' and sqrq <= '"+ e_date +"'");
			hql2.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null || !s_date.equals("")&& e_date.equals("")) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and sqrq >= '"+ s_date +"' and sqrq <= '"+ e_date +"'");
			hql2.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date == null && e_date != null || s_date.equals("")&& !e_date.equals("")) {
			Date d = DateUtil.getAppointDateByHour(192);
			s_date = DateUtil.date2String(d);
			hql.append(" and sqrq >= '"+ s_date +"' and sqrq <= '"+ e_date +"'");
			hql2.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}else {
			s_date = "2015-02-03 00:00:00";
			e_date = DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
			hql.append(" and sqrq >= '"+ s_date +"' and sqrq <= '"+ e_date +"'");
			hql2.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (e.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		List list = new ArrayList();
		List listCount = new ArrayList();
		List listDept = new ArrayList();

		List listywlx = new ArrayList();
		List listxglx = new ArrayList();
		
		int size = 0;
		
		String tjstr = "";
		String gcpmsize = "0";
		String hgrysize = "0";
		String wdfpsize = "0";
		List listtj = new ArrayList();
		List listsize = new ArrayList();
		List listgcpm = new ArrayList();
		List listhgry = new ArrayList();
		List listwdfp = new ArrayList();
		
		list = defaultDao.findSQLByPage(hql.toString(), offset, pageSize);
		listsize = defaultDao.findSQL(hqlsize.toString());

		// 指标数量查询
		if("0".equals(qxch) && ("BB".equals(ywlxs)||"A".equals(ywlxs))){
			hql2.append("  and a.lsh=e.lsh");
			listtj = defaultDao.findSQL("select zbm, count(0) from (SELECT (select b.dmms1 from clxg_sjzd b where a.zblx = b.dmz and b.dmlb = 'ZBFL') zbm FROM VEHICLE_TEMP_MID_TEST a, (select * from es_veh_flow c union all select * from es_veh_flow_his d) e WHERE a.ZBLX IS NOT NULL "+hql2+") group by zbm");
			listgcpm = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, (select * from es_veh_flow c union all select * from es_veh_flow_his d) e WHERE a.zblx = 'BAZB' and a.gzh like 'GCPM%' "+hql2);
			listhgry = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, (select * from es_veh_flow c union all select * from es_veh_flow_his d) e WHERE a.zblx = 'BAZB' and a.gzh like 'HGRY%' "+hql2);
			listwdfp = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, (select * from es_veh_flow c union all select * from es_veh_flow_his d) e WHERE a.zblx = 'BAZB' and a.gzh like 'WDFP%' "+hql2);
		}
		
		if(null != listgcpm && listgcpm.size()>0){
			gcpmsize = listgcpm.get(0).toString();
		}
		
		if(null != listhgry && listhgry.size()>0){
			hgrysize = listhgry.get(0).toString();
		}
		if(null != listwdfp && listwdfp.size()>0){
			wdfpsize = listwdfp.get(0).toString();
		}
		
		if(null != listtj && listtj.size() > 0){
			for(int i = 0 ; i < listtj.size(); i ++){
				Object ooo[] = (Object[]) listtj.get(i);
				if("".equals(tjstr)){
					if("备案指标".equals(ooo[0])){
						tjstr = "温馨提示：以下数据，使用"+ooo[0]+ooo[1]+"宗（其中含公车拍卖"+gcpmsize+"宗，回国人员"+hgrysize+"宗，外地发票"+wdfpsize+"宗）";
					}else{
						tjstr = "温馨提示：以下数据，使用"+ooo[0]+ooo[1]+"宗";
					}
				}else{
					if("备案指标".equals(ooo[0])){
						tjstr += "，使用"+ooo[0]+ooo[1]+"宗（其中含公车拍卖"+gcpmsize+"宗，回国人员"+hgrysize+"宗，外地发票"+wdfpsize+"宗）";
					}else{
						tjstr += "，使用"+ooo[0]+ooo[1]+"宗";
					}
				}
			}
			request.setAttribute("tjstr", tjstr);
		}
		
		if(request.getSession().getAttribute("listDept") == null){
			listDept = defaultDao.findSQL(hqldept.toString());			
			request.getSession().setAttribute("listDept", listDept);
		}else{
			listDept = (List) request.getSession().getAttribute("listDept");
		}
		
		if(request.getSession().getAttribute("listywlx")==null ){
			listywlx = defaultDao.findSQL(hqlywlx.toString());
			Object obj[] = new Object[2];
			obj[0] = "BB";
			obj[1] = "转移登记(市内过户)";
			Object obj2[] = new Object[2];
			obj2[0] = "BC";
			obj2[1] = "转移登记(市外过户)";
			listywlx.add(1, obj);
			listywlx.add(2, obj2);
			request.getSession().setAttribute("listywlx", listywlx);
		}
		
		if(request.getSession().getAttribute("listxglx")==null ){
			listxglx = defaultDao.findSQL(hqlxglx.toString());
			request.getSession().setAttribute("listxglx", listxglx);
		}
		size = list.size();

		if(size>0 && listDept.size()>0){
			for (int i = 0; i < listDept.size(); i++) {
				Object de[]= (Object[]) listDept.get(i);
				for (int j = 0; j < list.size(); j++) {
					Object mj[]=(Object[]) list.get(j);
					if(de[0].toString().equals(mj[8].toString())){
						mj[8]=de[1].toString();
					}
				}
				for (int j = 0; j < listCount.size(); j++) {
					Object mj[]=(Object[]) listCount.get(j);
					if(de[0].toString().equals(mj[8].toString())){
						mj[8]=de[1].toString();
					}
				}
			}
		}
		
		if(null != listsize && listsize.size() > 0){
			size = Integer.parseInt(listsize.get(0).toString());
			if(size <= 50000){
				listCount = defaultDao.findSQL(hql.toString());				
			}
		}

		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		
		request.setAttribute("map", map);
		request.setAttribute("rscount", size);
		request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		
		return "";
	}
	
	@SuppressWarnings({"unchecked"})
	public String xgslQuery2(HttpServletRequest request, int currentPage)throws Exception {
		StringBuffer hql = new StringBuffer("select lsh,cast(xh as varchar2(20)),hphm,cast(hpzl as varchar2(20)),syr,clxh,to_char(sqrq,'yyyy-MM-dd HH24:mi:ss'),to_char(bjrq,'yyyy-MM-dd HH24:mi:ss'),glbm from es_veh_flow where 1=1");
		StringBuffer hqlsize = new StringBuffer("select count(0) from es_veh_flow where 1=1");
		StringBuffer hql2 = new StringBuffer("");
		//StringBuffer hqldept2 = new StringBuffer("select glbm,bmmc from v_frm_department ");
		StringBuffer hqldept =new StringBuffer("select t1.glbm,t1.bmmc from v_frm_department t1 WHERE exists(select t.glbm from es_veh_flow t where t.glbm = t1.GLBM) order by t1.glbm");
		//StringBuffer depttj = new StringBuffer("select t1.glbm,t1.bmmc,count(1) from es_veh_flow t,v_frm_department t1 WHERE t.glbm = t1.GLBM and (t.Ywlx = 'P' OR t.Ywlx = 'R') AND t.Ywyy = 'F' AND t.Lszt <> 'Q' ");
		StringBuffer hqlywlx = new StringBuffer("select dmz,dmsm1 from es_drv_code where XTLB='01' AND DMLB='0060' and dmz <> 'B'");
		StringBuffer hqlxglx = new StringBuffer("select dmz,dmms1 from CLXG_SJZD where DMLB='XGCLLX'");
		
		String glbms = request.getParameter("glbms")==null?"":request.getParameter("glbms").trim();
		String ywlxs = request.getParameter("ywlxs")==null?"":request.getParameter("ywlxs").trim();
		String[] xglxs = request.getParameterValues("xglxs");
		String s_date = request.getParameter("s_date")==null?"":request.getParameter("s_date").trim();
		String e_date = request.getParameter("e_date")==null?"":request.getParameter("e_date").trim();
		String qxch = request.getParameter("qxch")==null?"":request.getParameter("qxch").trim();
		request.setAttribute("qxch", qxch);
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		//管理部门
		if (glbms!= null&& !glbms.equals("")) {
			glbms = glbms.replaceAll(" ", "");
			glbms = glbms.replaceAll("'", "");
			glbms = glbms.replaceAll("\"", "");
			glbms = glbms.replaceAll("，", ",");
			hql.append(" and glbm = '"+glbms+"' ");
			hql2.append(" and c.glbm = '"+glbms+"' ");
			hqlsize.append(" and glbm = '"+glbms+"' ");
			request.setAttribute("glbms", glbms);
		}
		
		//业务类型
		if (ywlxs!= null&& !ywlxs.equals("")) {
			if("BB".equals(ywlxs) || "BC".equals(ywlxs)){
				if("BB".equals(ywlxs)){
					hql.append(" and ywlx = 'B' and ywyy='B'");
					hql2.append(" and c.ywlx = 'B' and c.ywyy='B'");
					hqlsize.append(" and ywlx = 'B' and ywyy='B'");
					request.setAttribute("ywlxs", ywlxs);
				}else{
					hql.append(" and ywlx = 'B' and ywyy='C'");
					hql2.append(" and c.ywlx = 'B' and c.ywyy='C'");
					hqlsize.append(" and ywlx = 'B' and ywyy='C'");
					request.setAttribute("ywlxs", ywlxs);
				}
			}else{
				hql.append(" and ywlx = '"+ywlxs+"' ");
				hql2.append(" and c.ywlx = '"+ywlxs+"' ");
				hqlsize.append(" and ywlx = '"+ywlxs+"' ");
				request.setAttribute("ywlxs", ywlxs);
			}
		}
		
		//限购类型
		if (xglxs!= null&& !xglxs.equals("")) {
			String xglxstr = "";
			String xglxstr2 = "";
			for(int i = 0 ; i < xglxs.length; i ++){
				if(xglxstr.equals("")){
					xglxstr += "'" + xglxs[i] + "'";
					xglxstr2 += xglxs[i];
				}else{
					xglxstr += ",'" + xglxs[i] + "'";
					xglxstr2 += "," + xglxs[i];
				}
			}
			if(xglxstr2!= null&& !xglxstr2.equals("")){
				hql.append(" and CLLX in( "+xglxstr+" )");
				hql2.append(" and c.CLLX in( "+xglxstr+" )");
				hqlsize.append(" and CLLX in( "+xglxstr+" )");
				request.setAttribute("xglxs", xglxstr2);
			}
		}
		
		//申请时间
		if (s_date != null && e_date != null && !s_date.equals("")&& !e_date.equals("")) {
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			//depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" + e_date+ "','yyyy-MM-dd')+1 )");
			hql2.append(" and (c.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null || !s_date.equals("")&& e_date.equals("")) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			//depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" + e_date+ "','yyyy-MM-dd')+1 )");
			hql2.append(" and (c.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('" 
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date == null && e_date != null || s_date.equals("")&& !e_date.equals("")) {
			Date d = DateUtil.getAppointDateByHour(192);
			s_date = DateUtil.date2String(d);
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			//depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"+ e_date+ "','yyyy-MM-dd')+1 )");
			hql2.append(" and (c.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}else {
			s_date = "2015-02-03 00:00:00";
			e_date = DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
			hql.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			//depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"+ e_date+ "','yyyy-MM-dd')+1 )");
			hql2.append(" and (c.sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			hqlsize.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd hh24:mi:ss') and to_date('"
					+ e_date+ "','yyyy-MM-dd hh24:mi:ss'))");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		
		List list = new ArrayList();
		List listCount = new ArrayList();
		List listDept = new ArrayList();
		//List listDept2 = new ArrayList();
		//List deptTj = new ArrayList();
		List listywlx = new ArrayList();
		List listxglx = new ArrayList();
		int size=0;
		String tjstr = "";
		String gcpmsize = "0";
		String hgrysize = "0";
		String wdfpsize = "0";
		List listtj = new ArrayList();
		List listsize = new ArrayList();
		List listgcpm = new ArrayList();
		List listhgry = new ArrayList();
		List listwdfp = new ArrayList();
		
		//depttj.append(" group by t1.glbm,t1.bmmc order by glbm  "); 
		list = defaultDao.findSQLByPage(hql.toString(), offset, pageSize);
		listsize = defaultDao.findSQL(hqlsize.toString());
		//listDept2 = defaultDao.findSQL(hqldept2.toString());
		//deptTj = defaultDao.findSQL(depttj.toString());
		
		if("0".equals(qxch) && ("BB".equals(ywlxs)||"A".equals(ywlxs))){
			hql2.append("  and a.lsh=c.lsh");
			listtj = defaultDao.findSQL("select zbm, count(*) from (SELECT (select b.dmms1 from clxg_sjzd b where a.zblx = b.dmz and b.dmlb = 'ZBFL') zbm FROM VEHICLE_TEMP_MID_TEST a, es_veh_flow c WHERE a.ZBLX IS NOT NULL "+hql2+") group by zbm");
			listgcpm = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, es_veh_flow c WHERE a.zblx = 'BAZB' and a.gzh like 'GCPM%' "+hql2);
			listhgry = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, es_veh_flow c WHERE a.zblx = 'BAZB' and a.gzh like 'HGRY%' "+hql2);
			listwdfp = defaultDao.findSQL("SELECT count(0) FROM VEHICLE_TEMP_MID_TEST a, es_veh_flow c WHERE a.zblx = 'BAZB' and a.gzh like 'WDFP%' "+hql2);
		}
		
		if(null != listgcpm && listgcpm.size()>0){
			gcpmsize = listgcpm.get(0).toString();
		}
		
		if(null != listhgry && listhgry.size()>0){
			hgrysize = listhgry.get(0).toString();
		}
		if(null != listwdfp && listwdfp.size()>0){
			wdfpsize = listwdfp.get(0).toString();
		}
		
		if(null != listtj && listtj.size() > 0){
			for(int i = 0 ; i < listtj.size(); i ++){
				Object ooo[] = (Object[]) listtj.get(i);
				if("".equals(tjstr)){
					if("备案指标".equals(ooo[0])){
						tjstr = "温馨提示：以下数据，使用"+ooo[0]+ooo[1]+"宗（其中含公车拍卖"+gcpmsize+"宗，回国人员"+hgrysize+"宗，外地发票"+wdfpsize+"宗）";
					}else{
						tjstr = "温馨提示：以下数据，使用"+ooo[0]+ooo[1]+"宗";
					}
				}else{
					if("备案指标".equals(ooo[0])){
						tjstr += "，使用"+ooo[0]+ooo[1]+"宗（其中含公车拍卖"+gcpmsize+"宗，回国人员"+hgrysize+"宗，外地发票"+wdfpsize+"宗）";
					}else{
						tjstr += "，使用"+ooo[0]+ooo[1]+"宗";
					}
				}
			}
			request.setAttribute("tjstr", tjstr);
		}
		
		if(request.getSession().getAttribute("listDept")==null ){
			listDept = defaultDao.findSQL(hqldept.toString());
			request.getSession().setAttribute("listDept", listDept);
		}else{
			listDept = (List) request.getSession().getAttribute("listDept");
		}
		
		if(request.getSession().getAttribute("listywlx")==null ){
			listywlx = defaultDao.findSQL(hqlywlx.toString());
			Object obj[] = new Object[2];
			obj[0] = "BB";
			obj[1] = "转移登记(市内过户)";
			Object obj2[] = new Object[2];
			obj2[0] = "BC";
			obj2[1] = "转移登记(市外过户)";
			listywlx.add(1, obj);
			listywlx.add(2, obj2);
			request.getSession().setAttribute("listywlx", listywlx);
		}
		
		if(request.getSession().getAttribute("listxglx")==null ){
			listxglx = defaultDao.findSQL(hqlxglx.toString());
			request.getSession().setAttribute("listxglx", listxglx);
		}
		size=list.size();
		
		if(size>0 && listDept.size()>0){
			for (int i = 0; i < listDept.size(); i++) {
				Object de[]= (Object[]) listDept.get(i);
				for (int j = 0; j < list.size(); j++) {
					Object mj[]=(Object[]) list.get(j);
					if(de[0].toString().equals(mj[8].toString())){
						mj[8]=de[1].toString();
					}
				}
				for (int j = 0; j < listCount.size(); j++) {
					Object mj[]=(Object[]) listCount.get(j);
					if(de[0].toString().equals(mj[8].toString())){
						mj[8]=de[1].toString();
					}
				}
			}
		}
		
		if(null != listsize && listsize.size() > 0){
			size = Integer.parseInt(listsize.get(0).toString());
			if(size <= 50000){
				listCount = defaultDao.findSQL(hql.toString());
			}
		}
		
//		if(listCount.size()<pageSize){
//			list=listCount;
//		}else {
//			if((offset+pageSize)>listCount.size()){
//				list=listCount.subList(offset, listCount.size());			
//			}else {
//				list=listCount.subList(offset, offset+pageSize);
//			}
//		}
			
		//request.setAttribute("rscount", size);
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		
		request.setAttribute("map", map);
		request.setAttribute("rscount", size);
		request.setAttribute("listCount", listCount);
		request.setAttribute("list", list);
		//request.setAttribute("deptTj", deptTj);
		
		return "";
	}
	
	//免检车辆部门统计
	@SuppressWarnings({"unchecked"})
	public String mjclDeptTj(HttpServletRequest request) throws Exception {
		StringBuffer depttj = new StringBuffer("select t1.glbm,t1.bmmc,count(1) from es_veh_flow t,v_frm_department t1 WHERE t.glbm = t1.GLBM and (t.Ywlx = 'P' OR t.Ywlx = 'R') AND t.Ywyy = 'F' AND t.Lszt <> 'Q' ");
		
		String s_date = request.getParameter("s_date")==null?"":request.getParameter("s_date").trim();
		String e_date = request.getParameter("e_date")==null?"":request.getParameter("e_date").trim();
		String count = request.getParameter("count")==null?"":request.getParameter("count").trim();
		
		//申请时间
		if (s_date != null && e_date != null && !s_date.equals("")&& !e_date.equals("")) {
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null || !s_date.equals("")&& e_date.equals("")) {
			e_date = DateUtil.date2String(new Date());
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('" 
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date == null && e_date != null || s_date.equals("")&& !e_date.equals("")) {
			Date d = DateUtil.getAppointDateByHour(192);
			s_date = DateUtil.date2String(d);
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}else {
			s_date = DateUtil.date2String(new Date());
			e_date = DateUtil.date2String(new Date());
			depttj.append(" and (sqrq between to_date('" + s_date + "','yyyy-MM-dd') and to_date('"
					+ e_date+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		List deptTj = new ArrayList();
		
		depttj.append(" group by t1.glbm,t1.bmmc order by glbm  "); 
		deptTj = defaultDao.findSQL(depttj.toString());
		String str[]=new String[3];
		str[0]="1";
		str[1]="总计";
		str[2]=""+count;
		deptTj.add(str);
		request.setAttribute("count", count);
		request.setAttribute("deptTj", deptTj);
		
		return "";
	}

	public static Logger getLog() {
		return log;
	}
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}
	
	/**
	 * 字典翻译
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> getYwlx(String dmlb )throws Exception{
		Map<String,String> map = new HashMap<String,String>();
 		StringBuffer hql = new StringBuffer("select dmz,dmsm1 from es_veh_code where dmlb='"+dmlb+"'");
		List ywlxList = this.defaultDao.findSQL(hql.toString());
		for (int i = 0; i < ywlxList.size(); i++) {
			 Object[] obj= (Object[])ywlxList.get(i);
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	


}
