package com.ycszh.ssh.service.jdcbg.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.jdcbg.ContactBgDao;
import com.ycszh.ssh.hbm.jdcbg.TJdclxfsbg;
import com.ycszh.ssh.service.jdcbg.ContactBgService;
import com.ycszh.util.DateUtil;

public class ContactBgServiceImpl implements ContactBgService{
	private final static Logger log = Logger.getLogger(ContactBgServiceImpl.class);
	private ContactBgDao contactBgDao;
	
	@SuppressWarnings("unchecked")
	public List<TJdclxfsbg> contactBgQuery(HttpServletRequest request,int currentPage,String ci) throws Exception {
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("from TJdclxfsbg where zhclhj='18' and zhclzt='1' and lockr='"+user.getYgxm()+"' ");
		StringBuffer hqlsize=new StringBuffer("select count(t) from TJdclxfsbg t where zhclhj='18' and zhclzt='1' and lockr='"+user.getYgxm()+"' ");
		StringBuffer hqlzon=new StringBuffer("select count(t) from TJdclxfsbg t where zhclhj='18' and zhclzt='1' and tjsj >= to_date('2014-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') ");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<TJdclxfsbg> bgList=new ArrayList<TJdclxfsbg>();
		
		int zon = contactBgDao.getRepositoryByHQLListSize(hqlzon.toString());
		int size = contactBgDao.getRepositoryByHQLListSize(hqlsize.toString());
		if (size > 0) {
			hql.append(" order by zhclsj desc");
			List list = null;
			list = contactBgDao.findHQLByPage(hql.toString(), offset, pageSize);
			bgList = (List<TJdclxfsbg>)list;
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		
		Map map=new HashMap();
		if (ci=="2"){
			map.put("uri", "/ycszhyw/jdcbg/jdcbg_contactBgQuery.action");
		}else{
			map.put("uri", curi);
		}
		
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("zon", zon);
		request.setAttribute("bgList", bgList);
		
		return bgList;
	}
	
	@SuppressWarnings("unchecked")
	public String contactBgHq(HttpServletRequest request)throws Exception{
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("from TJdclxfsbg where zhclhj='18' and zhclzt='1' and tjsj >= to_date('2014-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') and lockr is null ");
		StringBuffer hqlsize=new StringBuffer("select count(t) from TJdclxfsbg t where zhclhj='18' and zhclzt='1'  and tjsj >= to_date('2014-01-01 00:00:00','yyyy-MM-dd HH24:mi:ss') and lockr is null ");
		List list = null;
		List<TJdclxfsbg> bgList=new ArrayList<TJdclxfsbg>();
		
		int size = contactBgDao.getRepositoryByHQLListSize(hqlsize.toString());
		if (size > 0) {
			list = contactBgDao.getRepositories(hql.toString());
			bgList = (List<TJdclxfsbg>)list;
			if(list.size()>=20){
				for (int i = 0; i < 20; i++) {
					TJdclxfsbg jdcbg=bgList.get(i);
					jdcbg.setLockzt("1");
					jdcbg.setLockr(user.getYgxm());
					jdcbg.setLockbm(user.getYgid());
					jdcbg.setLockbmKj(user.getYgid());
					jdcbg.setLockip(getLoginIp(request));
					jdcbg.setLocksj(new Date());
					contactBgDao.updateRepository(jdcbg);
				} 
			}else {
				for (int i = 0; i < list.size(); i++) {
					TJdclxfsbg jdcbg=bgList.get(i);
					jdcbg.setLockzt("1");
					jdcbg.setLockr(user.getYgxm());
					jdcbg.setLockbm(user.getYgid());
					jdcbg.setLockbmKj(user.getYgid());
					jdcbg.setLockip(getLoginIp(request));
					jdcbg.setLocksj(new Date());
					contactBgDao.updateRepository(jdcbg);
				} 
			}
		} else {
			request.setAttribute("rscount", 0);
		}
		
		return null;
	}
	
	public String contactBgShym(HttpServletRequest request)throws Exception{
		String lsh=request.getParameter("lsh");
		
		TJdclxfsbg bgym=contactBgDao.getRepository(lsh);
		request.setAttribute("bgym", bgym);
		return null;
	}
	
	public String contactBgSh(HttpServletRequest request)throws Exception{
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		String lsh=request.getParameter("lsh");
		String zt=request.getParameter("zt")==null?"":request.getParameter("zt").trim();
		String tylsh=request.getParameter("tylsh");
		String sbyy=request.getParameter("sbyy");
		
		String slzt="";
		if(zt.equals("1")){//审核成功
			TJdclxfsbg bgym=contactBgDao.getRepository(lsh);
			bgym.setLsh(tylsh);
			bgym.setLockzt("");
			bgym.setLockr("");
			bgym.setLockbm("");
			bgym.setLockbmKj("");
			bgym.setLockip("");
			bgym.setLocksj(null);
			bgym.setZhclsm("成功");
			bgym.setZhclr(user.getYgxm());
			bgym.setZhclsj(new Date());
			bgym.setZhclzt("1");
			bgym.setZhclhj("888");
			contactBgDao.updateRepository(bgym);
			slzt=tylsh;
		}else if(zt.equals("2")){//审核失败 不予受理
			TJdclxfsbg bgym=contactBgDao.getRepository(lsh);
			bgym.setLockzt("");
			bgym.setLockr("");
			bgym.setLockbm("");
			bgym.setLockbmKj("");
			bgym.setLockip("");
			bgym.setLocksj(null);
			bgym.setZhclsm(sbyy);
			bgym.setZhclr(user.getYgxm());
			bgym.setZhclsj(new Date());
			bgym.setZhclzt("0");
			bgym.setZhclhj("888");
			contactBgDao.updateRepository(bgym);
			slzt=sbyy;
		}
		request.setAttribute("slzt", slzt);
		request.setAttribute("zt", zt);
		return null;
	}
	
	//机动车联系方式变更查询
	@SuppressWarnings("unchecked")
	public List<TJdclxfsbg> jdcBgQuery(HttpServletRequest request, int currentPage,String ci)throws Exception{	
 		String wwlshs=request.getParameter("wwlshs");
		String syrs=request.getParameter("syrs");
		String sfzmhms=request.getParameter("sfzmhms");
		String startDay=request.getParameter("startDay")==null?"":request.getParameter("startDay").trim();
		String endDay=request.getParameter("endDay")==null?"":request.getParameter("endDay").trim();
		String zhclrs=request.getParameter("zhclrs");
		
		StringBuffer hql=new StringBuffer("from TJdclxfsbg t where 1=1  and tjsj >= to_date('20140101','yyyy/MM/dd') ");
		StringBuffer hqlsize=new StringBuffer("select count(t) from TJdclxfsbg t where 1=1  and tjsj >= to_date('20140101','yyyy/MM/dd') ");
		StringBuffer hqlren=new StringBuffer("select distinct(zhclr) from T_Jdclxfsbg t where zhclr is not null ");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<TJdclxfsbg> bgList=new ArrayList<TJdclxfsbg>();
		
		// 流水号
		if (wwlshs != null && !wwlshs.equals("")) {
			wwlshs = wwlshs.replaceAll(" ", "");
			wwlshs = wwlshs.replaceAll("'", "");
			wwlshs = wwlshs.replaceAll("\"", "");
			wwlshs = wwlshs.replaceAll("，", ",");
			hql.append(" and t.wwlsh = '" + wwlshs + "' ");
			hqlsize.append(" and t.wwlsh = '" + wwlshs + "' ");
			request.setAttribute("wwlshs", wwlshs);
		}
		
		// 所有人
		if (syrs != null && !syrs.equals("")) {
			syrs = syrs.replaceAll(" ", "");
			syrs = syrs.replaceAll("'", "");
			syrs = syrs.replaceAll("\"", "");
			syrs = syrs.replaceAll("，", ",");
			hql.append(" and t.syr = '" + syrs + "' ");
			hqlsize.append(" and t.syr = '" + syrs + "' ");
			request.setAttribute("syrs", syrs);
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
		
		// 最后处理人
		if (zhclrs != null && !zhclrs.equals("")) {
			zhclrs = zhclrs.replaceAll(" ", "");
			zhclrs = zhclrs.replaceAll("'", "");
			zhclrs = zhclrs.replaceAll("\"", "");
			zhclrs = zhclrs.replaceAll("，", ",");
			hql.append(" and t.zhclr = '" + zhclrs + "' ");
			hqlsize.append(" and t.zhclr = '" + zhclrs + "' ");
			request.setAttribute("zhclrs", zhclrs);
		}
		
		//申报时间
		if (startDay != null && endDay != null && !startDay.equals("")&& !endDay.equals("")) {
			hql.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		} else if (startDay != null && endDay.equals("")&& endDay == null && !startDay.equals("")) {
			endDay = DateUtil.date2String(new Date());
			hql.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		} else if (endDay != null && startDay.equals("") && startDay == null && !endDay.equals("")) {
			Date s=DateUtil.string2Date("20140101");
			startDay = DateUtil.date2String(s);
			hql.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		}
		/*else  {//(endDay == null && startDay == null && startDay.equals("")&& endDay.equals(""))
			Date s=DateUtil.string2Date("2014-01-01","yyyy-MM-dd");
			startDay = DateUtil.date2String(s);
			endDay=DateUtil.date2String(new Date());
			hql.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			hqlsize.append(" and (t.tjsj between to_date('" + startDay
					+ "','yyyy-MM-dd') and to_date('" + endDay+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("startDay", startDay);
			request.setAttribute("endDay", endDay);
		}*/
		
		List<Object>  zhr=contactBgDao.findSQL(hqlren.toString());
		int size = contactBgDao.getRepositoryByHQLListSize(hqlsize.toString());
		if (size > 0) {
			hql.append(" order by tjsj desc ");
			List list = null;
			list = contactBgDao.findHQLByPage(hql.toString(), offset, pageSize);
			bgList = (List<TJdclxfsbg>)list;
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
		request.setAttribute("zhr", zhr);
		request.setAttribute("bgList", bgList);
		
		return bgList;
	}
	
	//机动车联系方式变更清除
	@SuppressWarnings("unchecked")
	public String contactBgQc(HttpServletRequest request)throws Exception{
		User user =(User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		StringBuffer hql=new StringBuffer("from TJdclxfsbg where zhclhj='18' and zhclzt='1' and lockr = '"+user.getYgxm()+"' ");
		StringBuffer hqlsize=new StringBuffer("select count(t) from TJdclxfsbg t where zhclhj='18' and zhclzt='1' and lockr = '"+user.getYgxm()+"' ");
		List list = null;
		List<TJdclxfsbg> bgList=new ArrayList<TJdclxfsbg>();
		
		int size = contactBgDao.getRepositoryByHQLListSize(hqlsize.toString());
		if (size > 0) {
			list = contactBgDao.getRepositories(hql.toString());
			bgList = (List<TJdclxfsbg>)list;
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					TJdclxfsbg jdcbg=bgList.get(i);
					jdcbg.setLockzt("");
					jdcbg.setLockr("");
					jdcbg.setLockbm("");
					jdcbg.setLockbmKj("");
					jdcbg.setLockip("");
					jdcbg.setLocksj(null);
					contactBgDao.updateRepository(jdcbg);
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
	
	public ContactBgDao getContactBgDao() {
		return contactBgDao;
	}
	public void setContactBgDao(ContactBgDao contactBgDao) {
		this.contactBgDao = contactBgDao;
	}
	public static Logger getLog() {
		return log;
	}
}
