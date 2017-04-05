package com.ycszh.ssh.service.kcpb.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.kgpb.KgpbTsrqb;
import com.ycszh.ssh.hbm.kgpb.KgpbTsrqbLog;
import com.ycszh.ssh.service.kcpb.KgpbTsrqbService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ToolsUtil;

/**
 * @包名:com.ycszh.ssh.service.kcpb.impl
 * @文件名:KgpbTsrqbServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-18
 * @描述:
 * @版本:V 1.0
 */
public class KgpbTsrqbServiceImpl implements KgpbTsrqbService {
	
	private DefaultDao defaultDao;
	private final static Logger log = Logger.getLogger(KgpbTsrqbServiceImpl.class);
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public List getKgInfoBySql(String sql,Class cla) throws Exception{
		
		return defaultDao.findSQL(sql,cla);
	}
	
	public List getTsrqb(HttpServletRequest request) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from KGPB_Tsrqb where 1=1");
		
		return defaultDao.findSQL(sql.toString(), KgpbTsrqb.class);
	}
	
	public List<KgpbTsrqb> getTsrqbList(HttpServletRequest request,
			int currentPage) throws Exception {
		
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select id,(select kcmc from KGPB_KCXXB where kcid = kcbh) kcid,tsrq,type,bz from KGPB_Tsrqb t where 1=1 ");
		StringBuffer sqlCount = new StringBuffer("select count(0) from KGPB_Tsrqb t where 1=1 ");
		
		String kcid = request.getParameter("kcid");
		String tsrq = request.getParameter("tsrq");
		String type = request.getParameter("type");
		
		
		
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		
		if (kcid != null && !"".equals(kcid)) {
			sql.append(" and t.kcid = '" + kcid + "' ");
			sqlCount.append(" and t.kcid = '" + kcid + "' ");
			request.setAttribute("kcid", kcid);
		}
		
		if (tsrq != null && !"".equals(tsrq)) {
			sql.append(" and t.tsrq = to_date('" + tsrq + "','yyyy-MM-dd') ");
			sqlCount.append(" and t.tsrq = to_date('" + tsrq + "','yyyy-MM-dd') ");
			request.setAttribute("tsrq", tsrq);
		}
		
		if (type != null && !"".equals(type)) {
			sql.append(" and t.type = '" + type + "' ");
			sqlCount.append(" and t.type = '" + type + "' ");
			request.setAttribute("type", type);
		}
		
		sql.append(" order by tsrq desc");
		
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		if (size > 0) {
			list = defaultDao.findSQLByPage(sql.toString(),offset,pageSize);
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
		request.setAttribute("kcInfoList", list);
		log.info("method:getKgInfoList|param:request="+request+",currentPage="+currentPage);
		return list;
	}
	
	public int addOrUpdateTsrqb(KgpbTsrqb tsrqb,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		if(tsrqb!= null){
			List zdlist = new ArrayList();
			zdlist.add("czr");
			zdlist.add("czrbm");
			zdlist.add("czrxm");
			zdlist.add("czsj");
			zdlist.add("cznr");
			zdlist.add("czip");
			
			if(tsrqb.getId() == null || "".equals(tsrqb.getId())){
				defaultDao.addRepository(tsrqb);
				KgpbTsrqbLog tsrqbLog = new KgpbTsrqbLog();
				tsrqbLog.setCzr(user.getName());
				tsrqbLog.setCzrbm(user.getBmid());
				tsrqbLog.setCzrxm(user.getYgxm());
				tsrqbLog.setCzsj(new Date());
				tsrqbLog.setCznr("添加");
				tsrqbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(tsrqbLog, tsrqb, zdlist);
			}
			else{
				defaultDao.updateRepository(tsrqb);
				KgpbTsrqbLog tsrqbLog = new KgpbTsrqbLog();
				tsrqbLog.setCzr(user.getName());
				tsrqbLog.setCzrbm(user.getBmid());
				tsrqbLog.setCzrxm(user.getYgxm());
				tsrqbLog.setCzsj(new Date());
				tsrqbLog.setCznr("修改");
				tsrqbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(tsrqbLog, tsrqb, zdlist);
			}
		}
		
		return 0;
	}
	
	public int deleteTsrqb(String id,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		KgpbTsrqb tsrqb = (KgpbTsrqb)defaultDao.getRepository(id, KgpbTsrqb.class);
		
		// 判断该日期是否已过期
		if(new Date().getTime() > tsrqb.getTsrq().getTime()){
			return 1;
		}
		
		// 判断该特殊日期是否有排班
		List list = defaultDao.findSQL("select count(0) from KGPB_PBXXB where to_char(pbrq,'yyyy-MM-dd') = '"+DateUtil.date2String(tsrqb.getTsrq())+"'");
		
		int pbCount = Integer.parseInt(list.get(0)+"");
		
		if(pbCount > 0){
			return 1;
		}
		else{
			List zdlist = new ArrayList();
			zdlist.add("czr");
			zdlist.add("czrbm");
			zdlist.add("czrxm");
			zdlist.add("czsj");
			zdlist.add("cznr");
			zdlist.add("czip");
			
			defaultDao.deleteRepository(tsrqb);
			KgpbTsrqbLog kgxxbLog = new KgpbTsrqbLog();
			kgxxbLog.setCzr(user.getName());
			kgxxbLog.setCzrbm(user.getBmid());
			kgxxbLog.setCzrxm(user.getYgxm());
			kgxxbLog.setCzsj(new Date());
			kgxxbLog.setCznr("删除");
			kgxxbLog.setCzip(ToolsUtil.getIpAddr(request));
			defaultDao.addRepositoryLog(kgxxbLog, tsrqb, zdlist);
		}
		
		return 0;
	}
	
	public KgpbTsrqb getTsrqb(String id) throws Exception {
		return (KgpbTsrqb)defaultDao.getRepository(id, KgpbTsrqb.class);
	}

}
