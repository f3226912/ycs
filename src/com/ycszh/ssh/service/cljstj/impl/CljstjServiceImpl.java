package com.ycszh.ssh.service.cljstj.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.cljstj.CljstjDao;
import com.ycszh.ssh.hbm.cljstj.DxfsDxfsB;
import com.ycszh.ssh.hbm.cljstj.Healthdata;
import com.ycszh.ssh.service.cljstj.CljstjService;
import com.ycszh.util.DateUtil;

public class CljstjServiceImpl implements CljstjService{
	private final static Logger log = Logger.getLogger(CljstjServiceImpl.class);
	private CljstjDao cljstjDao;
	
	@SuppressWarnings("unchecked")
	public List<Healthdata> cljstjQuery(HttpServletRequest request, int currentPage)throws Exception {
		String wwlshs=request.getParameter("wwlshs");
		String sfzmhms=request.getParameter("sfzmhms");
		String startDay=request.getParameter("startDay");
		String endDay=request.getParameter("endDay");
		
		StringBuffer hql=new StringBuffer("select * from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and lrlb is null ");
		StringBuffer hqlsize=new StringBuffer("select count(*) from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and lrlb is null ");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<Healthdata> tjList=new ArrayList<Healthdata>();
		
		// 流水号
		if (wwlshs != null && !wwlshs.equals("")) {
			wwlshs = wwlshs.replaceAll(" ", "");
			wwlshs = wwlshs.replaceAll("'", "");
			wwlshs = wwlshs.replaceAll("\"", "");
			wwlshs = wwlshs.replaceAll("，", ",");
			hql.append(" and t.lsh = '" + wwlshs + "' ");
			hqlsize.append(" and t.lsh = '" + wwlshs + "' ");
			request.setAttribute("wwlshs", wwlshs);
		}
		
		// 身份证明号码
		if (sfzmhms != null && !sfzmhms.equals("")) {
			sfzmhms = sfzmhms.replaceAll(" ", "");
			sfzmhms = sfzmhms.replaceAll("'", "");
			sfzmhms = sfzmhms.replaceAll("\"", "");
			sfzmhms = sfzmhms.replaceAll("，", ",");
			hql.append(" and t.sfzmhm = '" + sfzmhms + "' ");
			hqlsize.append(" and t.sfzmhm = '" + sfzmhms + "' ");
			request.setAttribute("sfzmhms", sfzmhms);
		}
		
		//体检时间
		if (startDay != null && endDay != null && !startDay.equals("")&& !endDay.equals("")) {
			hql.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		} else if (startDay != null && endDay.equals("")&& endDay == null && !startDay.equals("")) {
			endDay = DateUtil.date2String(new Date());
			hql.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		} else if (endDay != null && startDay.equals("") && startDay == null && !endDay.equals("")) {
			Date d = DateUtil.getAppointDate(730);
			startDay = DateUtil.date2String(d);
			hql.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjrq between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		}
		
		int size = cljstjDao.getRepositoryBySQLListSize(hqlsize.toString());
		if (size > 0) {
			hql.append(" order by zhclsj desc");
			List list = null;
			list = cljstjDao.findSQLByPage(hql.toString(), offset, pageSize,Healthdata.class);
			tjList = (List<Healthdata>)list;
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		
		Map map=new HashMap();
		map.put("uri", curi);		
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("tjList", tjList);
		
		return tjList;
	}
	
	@SuppressWarnings("unchecked")
	public String cljstjHq(HttpServletRequest request) throws Exception {
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("select * from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and zhclhj=1 and zhclzt=1 and lockr is null and lrlb is null ");
		StringBuffer hqlsize=new StringBuffer("select count(*) from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and zhclhj=1 and zhclzt=1 and lockr is null and lrlb is null");
		List list = null;
		List<Healthdata> tjList=new ArrayList<Healthdata>();
		
		int size = cljstjDao.getRepositoryBySQLListSize(hqlsize.toString());
		if (size > 0) {
			list = cljstjDao.findSQL(hql.toString(),Healthdata.class);
			tjList = (List<Healthdata>)list;
			if(list.size()>=20){
				for (int i = 0; i < 20; i++) {
					Healthdata hl=tjList.get(i);
					hl.setLockzt("1");
					hl.setLockr(user.getYgxm());
					hl.setLockbm(user.getYgid());
					hl.setLockbmKj(user.getYgid());
					hl.setLockip(getLoginIp(request));
					hl.setLocksj(new Date());
					cljstjDao.updateRepository(hl);
				} 
			}else {
				for (int i = 0; i < list.size(); i++) {
					Healthdata hl=tjList.get(i);
					hl.setLockzt("1");
					hl.setLockr(user.getYgxm());
					hl.setLockbm(user.getYgid());
					hl.setLockbmKj(user.getYgid());
					hl.setLockip(getLoginIp(request));
					hl.setLocksj(new Date());
					cljstjDao.updateRepository(hl);
				} 
			}
		} else {
			request.setAttribute("rscount", 0);
		}
		
		return null;
	}
	
	//超龄驾驶体检审核数据列表
	@SuppressWarnings("unchecked")
	public List<Healthdata> cljstjShList(HttpServletRequest request, int currentPage, String ci ) throws Exception {
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("select * from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and zhclhj=1 and zhclzt=1 and lockr='"+user.getYgxm()+"' and lrlb is null ");
		StringBuffer hqlsize=new StringBuffer("select count(*) from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and zhclhj=1 and zhclzt=1 and lockr='"+user.getYgxm()+"' and lrlb is null ");
		StringBuffer hqlzon=new StringBuffer("select count(*) from healthdata t where  t.tjrq >= to_date('20140414','yyyy-MM-dd') and zhclhj=1 and zhclzt=1 and lrlb is null ");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<Healthdata> tjList=new ArrayList<Healthdata>();
		
		int zon = cljstjDao.getRepositoryBySQLListSize(hqlzon.toString());
		int size = cljstjDao.getRepositoryBySQLListSize(hqlsize.toString());
		if (size > 0) {
			hql.append(" order by tjrq desc");
			List list = null;
			list = cljstjDao.findSQLByPage(hql.toString(), offset, pageSize,Healthdata.class);
			tjList = (List<Healthdata>)list;
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		
		Map map=new HashMap();
		if(ci.equals("1")){
			map.put("uri", "/ycszhyw/cljstj/cljstj_cljstjShList.action");
		}else{
			map.put("uri", curi);	
		}
		
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("zon", zon);
		request.setAttribute("tjList", tjList);
		
		return tjList;
	}
	
	public Healthdata cljstjShYm(HttpServletRequest request) throws Exception {
		String xh=request.getParameter("xh");
		
		Healthdata hl=cljstjDao.getRepository(xh);
		request.setAttribute("hl", hl);
		return hl;
	}
	
	public String cljstjSh(HttpServletRequest request) throws Exception {
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		String xh=request.getParameter("xh");
		String zt=request.getParameter("zt")==null?"":request.getParameter("zt").trim();
		String tylsh=request.getParameter("tylsh");
		String sbyy=request.getParameter("sbyy");
		String dhhm=request.getParameter("dhhm");
		
		String slzt="";
		boolean flag=false;
		if(zt.equals("1")){//审核成功
			Healthdata hl=cljstjDao.getRepository(xh);
			hl.setLsh(tylsh);
			hl.setLockzt("");
			hl.setLockr("");
			hl.setLockbm("");
			hl.setLockbmKj("");
			hl.setLockip("");
			hl.setLocksj(null);
			hl.setZhclsm("成功");
			hl.setZhclr(user.getYgxm());
			hl.setZhclsj(new Date());
			hl.setZhclzt("1");
			hl.setZhclhj("888");
			cljstjDao.updateRepository(hl);
			
			String cnc="^(13|14|15|18)\\d{9}$";
			Pattern p=Pattern.compile(cnc);
			Matcher m=p.matcher(dhhm);
			flag = m.matches();
			if(flag){
				String nd=hl.getTjrq().toString().substring(0, 4);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String str=sdf.format(hl.getTjrq());
				
				DxfsDxfsB dxfsB=new DxfsDxfsB();
				dxfsB.setYwlx("G");
				dxfsB.setYwlxXx("驾驶证");
				dxfsB.setLsh(hl.getSxh().toString());
				dxfsB.setSfzmhm(hl.getSfzmhm());
				dxfsB.setDhhm(dhhm);//hl.getLxdh();  dhhm
				dxfsB.setSlsj(DateUtil.date2String(hl.getTjrq(),"yyyy/MM/dd HH:mm:ss"));
				dxfsB.setBjsj(DateUtil.date2String(hl.getZhclsj(),"yyyy/MM/dd HH:mm:ss"));
				dxfsB.setDmnr("尊敬的"+hl.getXm()+"驾驶员（身份证明号码："+hl.getSfzmhm()+"），您于"+str+"时间提交的"+nd+"年度驾驶人体检数据已成功生效。深圳交警祝您出行平安！");
				dxfsB.setDmxz("B");
				dxfsB.setCjsj(new Date());
				String jg=cljstjDao.fDx(dxfsB);
				request.setAttribute("jg", jg);
			}
			
			slzt=tylsh;
		}else if(zt.equals("2")){//审核失败 不予受理
			Healthdata hl=cljstjDao.getRepository(xh);
			hl.setLockzt("");
			hl.setLockr("");
			hl.setLockbm("");
			hl.setLockbmKj("");
			hl.setLockip("");
			hl.setLocksj(null);
			hl.setZhclsm(sbyy);
			hl.setZhclr(user.getYgxm());
			hl.setZhclsj(new Date());
			hl.setZhclzt("0");
			hl.setZhclhj("888");
			cljstjDao.updateRepository(hl);
			
			String cnc="^(13|14|15|18)\\d{9}$";
			Pattern p=Pattern.compile(cnc);
			Matcher m=p.matcher(dhhm);
			flag= m.matches();
			if(flag){
				String nd=hl.getTjrq().toString().substring(0, 4);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String str=sdf.format(hl.getTjrq());
				
				DxfsDxfsB dxfsB=new DxfsDxfsB();
				dxfsB.setYwlx("G");
				dxfsB.setYwlxXx("驾驶证");
				dxfsB.setLsh(hl.getSxh().toString());
				dxfsB.setSfzmhm(hl.getSfzmhm());
				dxfsB.setDhhm(dhhm);//hl.getLxdh();
				dxfsB.setSlsj(DateUtil.date2String(hl.getTjrq(),"yyyy/MM/dd HH:mm:ss"));
				dxfsB.setBjsj(DateUtil.date2String(hl.getZhclsj(),"yyyy/MM/dd HH:mm:ss"));
				dxfsB.setDmnr("尊敬的"+hl.getXm()+"驾驶员（身份证明号码："+hl.getSfzmhm()+"），您于"+str+"时间提交的"+nd+"年度驾驶人体检数据未生效，未生效原因为："+hl.getZhclsm()+"。深圳交警祝您出行平安！");
				dxfsB.setDmxz("C");
				dxfsB.setCjsj(new Date());
				String jg=cljstjDao.fDx(dxfsB);
				request.setAttribute("jg", jg);
			}

			slzt=sbyy;
		}
		request.setAttribute("slzt", slzt);
		request.setAttribute("zt", zt);
		return null;
	}

	@SuppressWarnings("unchecked")
	public String cljstjQc(HttpServletRequest request) throws Exception {
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("select * from healthdata t where  zhclhj=1 and zhclzt=1 and lockr='"+user.getYgxm()+"' ");
		StringBuffer hqlsize=new StringBuffer("select count(*) from healthdata t where  zhclhj=1 and zhclzt=1 and lockr='"+user.getYgxm()+"' ");
		List list = null;
		List<Healthdata> tjList=new ArrayList<Healthdata>();
		
		int size = cljstjDao.getRepositoryBySQLListSize(hqlsize.toString());
		if (size > 0) {
			list = cljstjDao.findSQL(hql.toString(),Healthdata.class);
			tjList = (List<Healthdata>)list;
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					Healthdata hl=tjList.get(i);
					hl.setLockzt("");
					hl.setLockr("");
					hl.setLockbm("");
					hl.setLockbmKj("");
					hl.setLockip("");
					hl.setLocksj(null);
					cljstjDao.updateRepository(hl);
				} 
			}
		}
		return null;
	}
	
	public String getLoginIp(HttpServletRequest request) throws Exception{
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
	
	public CljstjDao getCljstjDao() {
		return cljstjDao;
	}
	public void setCljstjDao(CljstjDao cljstjDao) {
		this.cljstjDao = cljstjDao;
	}
	public static Logger getLog() {
		return log;
	}
}
