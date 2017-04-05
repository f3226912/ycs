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
import com.ycszh.ssh.hbm.bfc.BfcJbxxb;
import com.ycszh.ssh.hbm.drv.SlgSjzd;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxb;
import com.ycszh.ssh.hbm.kgpb.KgpbKcxxbLog;
import com.ycszh.ssh.service.bfc.impl.BfcJbxxbServiceImpl;
import com.ycszh.ssh.service.kcpb.KgpbKcxxbService;
import com.ycszh.util.ToolsUtil;

/**
 * @包名:com.ycszh.ssh.service.kcpb.impl
 * @文件名:KgpbKcxxbServiceImpl.java
 * @作者:caocheng E-mail:341297512@qq.com
 * @创建日期:2014-11-13
 * @描述:
 * @版本:V 1.0
 */
public class KgpbKcxxbServiceImpl implements KgpbKcxxbService {

	private DefaultDao defaultDao;
	private final static Logger log = Logger.getLogger(KgpbKcxxbServiceImpl.class);
	
	public DefaultDao getDefaultDao() {
		return defaultDao;
	}
	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public List getKcInfo(HttpServletRequest request) throws Exception {
		
		StringBuffer sql = new StringBuffer("select * from KGPB_KCXXB where 1=1 order by to_number(kcbh)");
		
		return defaultDao.findSQL(sql.toString(), KgpbKcxxb.class);
	}
	
	public List<KgpbKcxxb> getKgpbKcxxbList(HttpServletRequest request,
			int currentPage) throws Exception {
		
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from KGPB_KCXXB t where 1=1 ");
		StringBuffer sqlCount = new StringBuffer("select count(0) from KGPB_KCXXB t where 1=1 ");
		String kcbh = request.getParameter("kcbh");
		String kcmc = request.getParameter("kcmc");
		String zt = request.getParameter("zt");
		String lrzt = request.getParameter("lrzt");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		
		
		// 考场编号
		if (kcbh != null && !"".equals(kcbh)) {
			sql.append(" and t.kcbh = '" + kcbh + "' ");
			sqlCount.append(" and t.kcbh = '" + kcbh + "' ");
			request.setAttribute("kcbh", kcbh);
		}
		
		// 考场名称
		if (kcmc != null && !"".equals(kcmc)) {
			sql.append(" and t.kcmc like '%" + kcmc + "%' ");
			sqlCount.append(" and t.kcmc like '%" + kcmc + "%' ");
			request.setAttribute("kcmc", kcmc);
		}
		
		// 状态
		if (zt != null && !"".equals(zt)) {
			sql.append(" and t.zt = '" + zt + "' ");
			sqlCount.append(" and t.zt = '" + zt + "' ");
			request.setAttribute("zt", zt);
		}
		
		if (lrzt != null && !"".equals(lrzt)) {
			if(lrzt.equals("zl0")){
				sql.append(" and t.zlzt = '0' ");
				sqlCount.append(" and t.zlzt = '0' ");
			}
			
			if(lrzt.equals("zl1")){
				sql.append(" and t.zlzt = '1' ");
				sqlCount.append(" and t.zlzt = '1' ");
			}
			
			if(lrzt.equals("zr0")){
				sql.append(" and t.zrzt = '0' ");
				sqlCount.append(" and t.zrzt = '0' ");
			}
			
			if(lrzt.equals("zr1")){
				sql.append(" and t.zrzt = '1' ");
				sqlCount.append(" and t.zrzt = '1' ");
			}
			
			request.setAttribute("lrzt", lrzt);
		}
		
		sql.append(" order by to_number(id)");
		
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
		log.info("method:getKgpbKcxxbList|param:request="+request+",currentPage="+currentPage);
		return list;
	}
	
	public int addOrUpdateKcInfo(KgpbKcxxb kcxxb,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		if(kcxxb!= null){
			List zdlist = new ArrayList();
			zdlist.add("czr");
			zdlist.add("czrbm");
			zdlist.add("czrxm");
			zdlist.add("czsj");
			zdlist.add("cznr");
			zdlist.add("czip");
			
			if(kcxxb.getId() == null || "".equals(kcxxb.getId())){
				defaultDao.addRepository(kcxxb);
				KgpbKcxxbLog kcxxbLog = new KgpbKcxxbLog();
				kcxxbLog.setCzr(user.getName());
				kcxxbLog.setCzrbm(user.getBmid());
				kcxxbLog.setCzrxm(user.getYgxm());
				kcxxbLog.setCzsj(new Date());
				kcxxbLog.setCznr("添加");
				kcxxbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(kcxxbLog, kcxxb, zdlist);
			}
			else{
				defaultDao.updateRepository(kcxxb);
				KgpbKcxxbLog kcxxbLog = new KgpbKcxxbLog();
				kcxxbLog.setCzr(user.getName());
				kcxxbLog.setCzrbm(user.getBmid());
				kcxxbLog.setCzrxm(user.getYgxm());
				kcxxbLog.setCzsj(new Date());
				kcxxbLog.setCznr("修改");
				kcxxbLog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(kcxxbLog, kcxxb, zdlist);
			}
		}
		
		return 0;
	}
	
	public int deleteKcInfo(String id,HttpServletRequest request) throws Exception {
		
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		
		// 判断该考场是否有排班
		List list = defaultDao.findSQL("select count(0) from KGPB_PBXXB where kcid = '"+id+"' and to_char(pbrq,'yyyy-MM-dd') >= to_char(sysdate,'yyyy-MM-dd')");
		
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
			
			KgpbKcxxb kcxxb = (KgpbKcxxb)defaultDao.getRepository(id, KgpbKcxxb.class);
			defaultDao.deleteRepository(kcxxb);
			KgpbKcxxbLog kcxxbLog = new KgpbKcxxbLog();
			kcxxbLog.setCzr(user.getName());
			kcxxbLog.setCzrbm(user.getBmid());
			kcxxbLog.setCzrxm(user.getYgxm());
			kcxxbLog.setCzsj(new Date());
			kcxxbLog.setCznr("删除");
			kcxxbLog.setCzip(ToolsUtil.getIpAddr(request));
			defaultDao.addRepositoryLog(kcxxbLog, kcxxb, zdlist);
		}
		
		return 0;
	}
	
	public KgpbKcxxb getKgpbKcxxb(String id) throws Exception {
		return (KgpbKcxxb)defaultDao.getRepository(id, KgpbKcxxb.class);
	}

}
