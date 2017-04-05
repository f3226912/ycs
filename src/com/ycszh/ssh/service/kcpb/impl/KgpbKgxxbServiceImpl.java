package com.ycszh.ssh.service.kcpb.impl;

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
import com.ycszh.ssh.hbm.kgpb.KgpbKgxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbKgxxbLog;
import com.ycszh.ssh.service.kcpb.KgpbKgxxbService;
import com.ycszh.util.ToolsUtil;

/**
 * @包名:com.ycszh.ssh.service.kcpb.impl
 * @文件名:KgpbKgxxbServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-14
 * @描述:
 * @版本:V 1.0
 */
public class KgpbKgxxbServiceImpl implements KgpbKgxxbService {

	private DefaultDao defaultDao;
	private final static Logger log = Logger.getLogger(KgpbKgxxbServiceImpl.class);
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public List getKgInfoBySql(String sql) throws Exception{
		
		return defaultDao.findSQL(sql);
	}
	
	public List getKgInfo(HttpServletRequest request) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from KGPB_KCXXB where 1=1");
		
		return defaultDao.findSQL(sql.toString(), KgpbKgxxb.class);
	}
	
	public List<KgpbKgxxb> getKgInfoList(HttpServletRequest request,
			int currentPage) throws Exception {
		
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from KGPB_KGXXB t where 1=1 ");
		StringBuffer sqlCount = new StringBuffer("select count(0) from KGPB_KGXXB t where 1=1 ");
		
		String kgjh = request.getParameter("kgjh");
		String kgxm = request.getParameter("kgxm");
		String zt = request.getParameter("zt");
		String ssbm = request.getParameter("ssbm");
		
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		
		// 考官编号
		if (kgjh != null && !"".equals(kgjh)) {
			sql.append(" and t.jh = '" + kgjh + "' ");
			sqlCount.append(" and t.jh = '" + kgjh + "' ");
			request.setAttribute("kgjh", kgjh);
		}
		
		if (kgxm != null && !"".equals(kgxm)) {
			sql.append(" and t.xm like '%" + kgxm + "%' ");
			sqlCount.append(" and t.xm like '%" + kgxm + "%' ");
			request.setAttribute("kgxm", kgxm);
		}
		
		if (zt != null && !"".equals(zt)) {
			sql.append(" and t.zt = '" + zt + "' ");
			sqlCount.append(" and t.zt = '" + zt + "' ");
			request.setAttribute("zt", zt);
		}
		
		if (ssbm != null && !"".equals(ssbm)) {
			sql.append(" and t.ssbm = '" + ssbm + "' ");
			sqlCount.append(" and t.ssbm = '" + ssbm + "' ");
			request.setAttribute("ssbm", ssbm);
		}
		
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
	
	public int addOrUpdateKgInfo(KgpbKgxxb kgxxb,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		if(kgxxb!= null){
			
			List list = new ArrayList();
			list.add("czr");
			list.add("czrbm");
			list.add("czrxm");
			list.add("czsj");
			list.add("cznr");
			list.add("czip");
			
			if(kgxxb.getId() == null || "".equals(kgxxb.getId())){
				defaultDao.addRepository(kgxxb);
				KgpbKgxxbLog kcxxbLog = new KgpbKgxxbLog();
				kcxxbLog.setCzr(user.getName());
				kcxxbLog.setCzrbm(user.getBmid());
				kcxxbLog.setCzrxm(user.getYgxm());
				kcxxbLog.setCzsj(new Date());
				kcxxbLog.setCznr("添加");
				kcxxbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(kcxxbLog, kgxxb, list);
			}
			else{
				defaultDao.updateRepository(kgxxb);
				KgpbKgxxbLog kcxxbLog = new KgpbKgxxbLog();
				kcxxbLog.setCzr(user.getName());
				kcxxbLog.setCzrbm(user.getBmid());
				kcxxbLog.setCzrxm(user.getYgxm());
				kcxxbLog.setCzsj(new Date());
				kcxxbLog.setCznr("修改");
				kcxxbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(kcxxbLog, kgxxb, list);
			}
		}
		
		return 0;
	}
	
	public int deleteKgInfo(String id,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		KgpbKgxxb kgxxb = (KgpbKgxxb)defaultDao.getRepository(id, KgpbKgxxb.class);
		// 判断该考官是否有排班
		List list = defaultDao.findSQL("select count(0) from KGPB_PBXXB where KGID = '"+kgxxb.getJh()+"' and to_char(pbrq,'yyyy-MM-dd') >= to_char(sysdate,'yyyy-MM-dd')");
		
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
			
			defaultDao.deleteRepository(kgxxb);
			KgpbKgxxbLog kgxxbLog = new KgpbKgxxbLog();
			kgxxbLog.setCzr(user.getName());
			kgxxbLog.setCzrbm(user.getBmid());
			kgxxbLog.setCzrxm(user.getYgxm());
			kgxxbLog.setCzsj(new Date());
			kgxxbLog.setCznr("删除");
			kgxxbLog.setCzip(ToolsUtil.getIpAddr(request));
			defaultDao.addRepositoryLog(kgxxbLog, kgxxb, zdlist);
		}
		
		return 0;
	}
	
	public KgpbKgxxb getKgInfo(String id) throws Exception {
		return (KgpbKgxxb)defaultDao.getRepository(id, KgpbKgxxb.class);
	}
}
