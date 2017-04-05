package com.ycszh.ssh.service.xyc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.xyc.YcsXycCode;
import com.ycszh.ssh.hbm.xyc.YcsXycCodeLog;
import com.ycszh.ssh.service.xyc.YcsXycCodeService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ToolsUtil;

public class YcsXycCodeServiceImpl implements YcsXycCodeService {
	
	private DefaultDao defaultDao;
	private final static Logger log = Logger.getLogger(YcsXycCodeServiceImpl.class);

	public Integer deleteYcsXycCode(HttpServletRequest request,String ycsXycCodeId) throws Exception {
		YcsXycCode ycsXycCode = (YcsXycCode) defaultDao.getRepository(ycsXycCodeId, YcsXycCode.class);
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		if (user != null) {
			defaultDao.deleteRepository(ycsXycCode);
			YcsXycCodeLog ycsXycCodelog = new YcsXycCodeLog();
			ycsXycCodelog.setCzr(user.getName());
			ycsXycCodelog.setCzbm(user.getBmid());
			ycsXycCodelog.setCzrxm(user.getYgxm());
			ycsXycCodelog.setCzrq(new Date());
			ycsXycCodelog.setCznr("删除");
			ycsXycCodelog.setCzip(ToolsUtil.getIpAddr(request));
			defaultDao.addRepositoryLog(ycsXycCodelog, ycsXycCode, null);
			log.info("method:deleteYcsXycCode|param:ycsXycCodeId="+ycsXycCodeId);
			return 0;
		} else {
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	public Integer deleteYcsXycCodeList(HttpServletRequest request, String[] ycsXycCodeIds) throws Exception {
		Collection userlist = new ArrayList();
		User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
		if (null != ycsXycCodeIds) {
			for (String ycsXycCodeId : ycsXycCodeIds) {
				if(null != ycsXycCodeId && !"".equals(ycsXycCodeId)){
					YcsXycCode ycsXycCode = (YcsXycCode) defaultDao.getRepository(ycsXycCodeId, YcsXycCode.class);
					if (ycsXycCode != null) {
						YcsXycCodeLog ycsXycCodeLog = new YcsXycCodeLog();
						ycsXycCodeLog.setCzr(user.getName());
						ycsXycCodeLog.setCzbm(user.getBmid());
						ycsXycCodeLog.setCzrxm(user.getYgxm());
						ycsXycCodeLog.setCzrq(new Date());
						ycsXycCodeLog.setCznr("删除");
						ycsXycCodeLog.setCzip(ToolsUtil.getIpAddr(request));
						defaultDao.addRepositoryLog(ycsXycCodeLog, ycsXycCode, null);
						userlist.add(user);
					}
				}
			}
		}
		if (userlist != null && userlist.size() > 0) {
			defaultDao.deleteRepositoryList(userlist);
			log.info("method:deleteYcsXycCodeList|param:ycsXycCodeIds="+ycsXycCodeIds);
			return 0;
		} else {
			return 1;
		}
	}

	public YcsXycCode getYcsXycCode(HttpServletRequest request) throws Exception {
		String ycsXycCodeId = request.getParameter("ycsXycCodeId");
		if(null != ycsXycCodeId && !"".equals(ycsXycCodeId)){
			log.info("method:getYcsXycCode|param:ycsXycCodeId="+ycsXycCodeId);
			return (YcsXycCode)defaultDao.getRepository(ycsXycCodeId, YcsXycCode.class);
		}
		return null;
	}

	public YcsXycCode getYcsXycCode(String ycsXycCodeId) throws Exception {
		log.info("method:getYcsXycCode|param:ycsXycCodeId="+ycsXycCodeId);
		return (YcsXycCode)defaultDao.getRepository(ycsXycCodeId, YcsXycCode.class);
	}

	@SuppressWarnings({ "unchecked", "null", "static-access"})
	public List<YcsXycCode> getYcsXycCodeList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("from YcsXycCode as t where 1=1 ");
		StringBuffer sqlsize = new StringBuffer("select count(0) from YCS_XYC_CODE t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		
		//DateUtil
		DateUtil du = new DateUtil();
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<YcsXycCode> ycsXycCodeList = new ArrayList<YcsXycCode>();
		
		//登录时间
		if (s_date != null && e_date != null && !s_date.equals("")
				&& !e_date.equals("")) {
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && e_date == null && !s_date.equals("")
				&& e_date.equals("")) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && s_date == null && s_date.equals("")
				&& !e_date.equals("")) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sqlsize.append(" and (t.lrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}

		int size = this.defaultDao.getRepositoryBySQLListSize(sqlsize.toString());
		if (size > 0) {
			hql.append(" order by t.id desc");
			List list = null;
			list = defaultDao.findHQLByPage(hql.toString(),offset,pageSize);
			ycsXycCodeList = (List<YcsXycCode>)list;
			
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
		request.setAttribute("ycsXycCodeList", ycsXycCodeList);
		log.info("method:getUserList|param:request="+request+",currentPage="+currentPage);
		return ycsXycCodeList;
	}
	
	

	public Integer insertOrUpdateYcsXycCode(YcsXycCode ycsXycCode,HttpServletRequest request) throws Exception {
		if (ycsXycCode != null) {
			String ycsXycCodeId = ycsXycCode.getId();
			User user = (User)request.getSession().getAttribute(SysConst.USERBEAN);
			if(null == ycsXycCodeId || "".equals(ycsXycCodeId)){
				ycsXycCode.setLrrdm(user.getName());
				ycsXycCode.setLrrxm(user.getYgxm());
				ycsXycCode.setLrrbm(user.getBmid());
				ycsXycCode.setLrsj(new Date());
				YcsXycCode u = (YcsXycCode)this.defaultDao.addRepository(ycsXycCode);
				YcsXycCodeLog ycsXycCodelog = new YcsXycCodeLog();
				ycsXycCodelog.setCzr(user.getName());
				ycsXycCodelog.setCzbm(user.getBmid());
				ycsXycCodelog.setCzrxm(user.getYgxm());
				ycsXycCodelog.setCzrq(new Date());
				ycsXycCodelog.setCznr("新增");
				ycsXycCodelog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(ycsXycCodelog, u, null);
				log.info("method:insertOrUpdateYcsXycCode|param:ycsXycCode="+ycsXycCode);
				return 0;
			}else{
				YcsXycCode u = (YcsXycCode)this.defaultDao.updateRepository(ycsXycCode);
				YcsXycCodeLog ycsXycCodelog = new YcsXycCodeLog();
				ycsXycCodelog.setCzr(user.getName());
				ycsXycCodelog.setCzbm(user.getBmid());
				ycsXycCodelog.setCzrxm(user.getYgxm());
				ycsXycCodelog.setCzrq(new Date());
				ycsXycCodelog.setCznr("修改");
				ycsXycCodelog.setCzip(ToolsUtil.getIpAddr(request));
				defaultDao.addRepositoryLog(ycsXycCodelog, u, null);
				log.info("method:insertOrUpdateUser|param:user="+user);
				return 0;
			}
		} else {
			return 1;
		}
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	@SuppressWarnings("unchecked")
	public List getDmlbList() throws Exception {
		List list = defaultDao.findSQL("select distinct(dmlb) dmlb,bz from  ycs_xyc_code");
		return list;
	}
	

}
