package com.ycszh.ssh.service.ezxfw.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ycszh.formbean.DrvSuperviseForm;
import com.ycszh.formbean.VehSuperviseForm;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.dao.ezxfw.FileimageDao;
import com.ycszh.ssh.dao.veh.IItopscDao;
import com.ycszh.ssh.hbm.ezxfw.EzDrvLiceChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzDzwt;
import com.ycszh.ssh.hbm.ezxfw.EzJdcChanApp;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrint;
import com.ycszh.ssh.hbm.ezxfw.EzXxdPrintPhoto;
import com.ycszh.ssh.hbm.sfrz.SfrzUserinfo;
import com.ycszh.ssh.hbm.sfrz.SfrzWxJdc;
import com.ycszh.ssh.hbm.sfrz.SfrzWxJsr;
import com.ycszh.ssh.service.ezxfw.EzxfwService;
import com.ycszh.util.DateUtil;

public class EzxfwServiceImpl implements EzxfwService {
	private DefaultDao defaultDao;
	private IItopscDao itopscDao;

	private FileimageDao fileimageDao;

	public FileimageDao getFileimageDao() {
		return fileimageDao;
	}

	public void setFileimageDao(FileimageDao fileimageDao) {
		this.fileimageDao = fileimageDao;
	}

	
	@SuppressWarnings("unchecked")
	public List getJdcCheckView(HttpServletRequest request) throws Exception {
		String fpzh = request.getParameter("fpzh");
		String lx = request.getParameter("lx");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String ywlx = request.getParameter("ywlx");
		List ezJdcCheckView = new ArrayList();

		StringBuffer sqlQuery = null;

		sqlQuery = new StringBuffer(
				"select csshrzh,csshrxm,cspch,yshs + wshs zs,yshs,wshs,zhtqsj"
						+ " from (select csshrzh,csshrxm,cspch," + " sum(case"
						+ " when ZHCLZT <> '3' then" + " 1" + " else" + " 0"
						+ " end) yshs, " + " sum(case"
						+ " when ZHCLZT = '3' then" + " 1" + " else" + " 0"
						+ " end) wshs, " + " min(t.CSPCSJ) zhtqsj"
						+ " from EZ_JDC_CHAN_APP t where 1=1");
		if (lx != null && lx.equals("jdc")) {
			sqlQuery.append(" and t.zhclzt='3' ");
		}
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlQuery.append(" and t.ywlx = '" + ywlx + "' ");
		}
		if (fpzh != null && !"".equals(fpzh)) {
			sqlQuery.append(" and t.csshrzh='" + fpzh + "'");
		}
		if (sdate != null && !"".equals(sdate)) {
			sqlQuery.append(" and t.cspcsj >= to_date('" + sdate
					+ "', 'yyyy-mm-dd')");
		}
		if (edate != null && !"".equals(edate)) {
			sqlQuery.append(" and t.cspcsj < to_date('" + edate
					+ "', 'yyyy-mm-dd')+1");
		}
		sqlQuery.append(" group by csshrzh,csshrxm,cspch)");
		sqlQuery.append(" order by zhtqsj desc");

		ezJdcCheckView = this.defaultDao.findSQL(sqlQuery.toString());
		request.getSession().setAttribute("ezJdcCheckView", ezJdcCheckView);
		return ezJdcCheckView;
	}

	@SuppressWarnings("unchecked")
	public List getDrvCheckView(HttpServletRequest request) throws Exception {
		String fpzh = request.getParameter("fpzh");
		String lx = request.getParameter("lx");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String hblx = request.getParameter("hblx");
		List ezDrvLiceCheckView = new ArrayList();
		StringBuffer sqlQuery = null;
		sqlQuery = new StringBuffer(
				"select csshrzh,csshrxm,cspch,yshs + wshs zs,yshs,wshs,zhtqsj"
						+ " from (select csshrzh,csshrxm,cspch," + " sum(case"
						+ " when ZHCLZT <> '3' then" + " 1" + " else" + " 0"
						+ " end) yshs, " + " sum(case"
						+ " when ZHCLZT = '3' then" + " 1" + " else" + " 0"
						+ " end) wshs, " + " min(t.CSPCSJ) zhtqsj"
						+ " from EZ_DRV_LICE_CHAN_APP t where 1=1");
		if (lx != null && lx.equals("drv")) {
			sqlQuery.append(" and t.zhclzt='3' ");
		}
		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			sqlQuery.append(" and t.hblx = '" + hblx + "' ");
		}
		if (fpzh != null && !"".equals(fpzh)) {
			sqlQuery.append(" and t.csshrzh='" + fpzh + "'");
		}
		if (sdate != null && !"".equals(sdate)) {
			sqlQuery.append(" and t.cspcsj >= to_date('" + sdate
					+ "', 'yyyy-mm-dd')");
		}
		if (edate != null && !"".equals(edate)) {
			sqlQuery.append(" and t.cspcsj < to_date('" + edate
					+ "', 'yyyy-mm-dd')+1");
		}
		sqlQuery.append(" group by csshrzh,csshrxm,cspch)");
		sqlQuery.append(" order by zhtqsj desc");
		ezDrvLiceCheckView = this.defaultDao.findSQL(sqlQuery.toString());
		request.getSession().setAttribute("ezDrvLiceCheckView",
				ezDrvLiceCheckView);
		return ezDrvLiceCheckView;
	}

	@SuppressWarnings("unchecked")
	public void getJdcCheckList(HttpServletRequest request, int currentPage)
			throws Exception {
		List ezJdcCheckList = new ArrayList();
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select CSSHRZH,CSSHRXM,YWLX from  EZ_JDC_CHAN_APP t where 1=1");
		StringBuffer sqlList = new StringBuffer(
				"Select CSSHRZH,"
						+ // 分配账号
						"CSSHRXM,"
						+ // 分配姓名
						"YWLX,"
						+ // 业务类型
						"yshstg+yshswtg + wshs zs,"
						+ // 自动提取数量
						"yshstg, "
						+ // 已审核通过
						"wshs, "
						+ // 未审核数
						"zhtqsj, "
						+ // 最后分配时间
						"ywlxkey, "
						+ // 业务类型键
						"yshswtg "
						+ // 审核未通过
						"From (select CSSHRZH,CSSHRXM,YWLX,sum(case "
						+ " when ZHCLZT <> '3' and ZHCLZT<>'TB' then  1 else 0 "
						+ " end) yshstg, sum(case when ZHCLZT='TB' then "
						+ " 1 else 0 end) yshswtg, sum(case when ZHCLZT = '3' then "
						+ " 1 else 0 end) wshs, "
						+ "min(t.CSPCSJ) zhtqsj,YWLX as ywlxkey from EZ_JDC_CHAN_APP t where 1=1");

		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String q_account = request.getParameter("q_account");
		String ywlx = request.getParameter("ywlx");
		String type = request.getParameter("type");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			sqlCount.append(" and t.CSPCSJ >= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ <to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else {
			if (!"jdc".equals(type)) {
				s_date = DateUtil.date2String(new Date());
				e_date = s_date;
				sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
						+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('"
						+ e_date + "', 'yyyy-mm-dd')+1");
				sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
						+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('"
						+ e_date + "', 'yyyy-mm-dd')+1");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			}
		}
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlCount.append(" and t.ywlx = '" + ywlx + "' ");
			sqlList.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlx", ywlx);
		}
		if (q_account != null && !"".equals(q_account)) {
			sqlCount.append(" and t.CSSHRZH = '" + q_account + "' ");
			sqlList.append(" and t.CSSHRZH = '" + q_account + "' ");
			request.setAttribute("q_account", q_account);
		}
		if ("jdc".equals(type)) {
			sqlCount.append(" group by CSSHRZH,CSSHRXM,YWLX ");
			sqlList.append(" group by CSSHRZH,CSSHRXM,YWLX ");
			sqlCount
					.append(" having sum(case when ZHCLZT = '3' then 1 else 0 end)!='0' )");
			sqlList
					.append(" having sum(case when ZHCLZT = '3' then 1 else 0 end)!='0' )");
		} else {
			sqlCount.append(" group by CSSHRZH,CSSHRXM,YWLX )");
			sqlList.append(" group by CSSHRZH,CSSHRXM,YWLX )");
		}
		List slList = new ArrayList();
		StringBuffer slhql = new StringBuffer(
				"select dfp,dsh from (select sum(case when ZHCLZT = '0' then "
						+ " 1 else 0 end) dfp,sum(case when ZHCLZT='3' then 1 else 0 end) dsh from EZ_JDC_CHAN_APP)");
		slList = this.defaultDao.findSQL(slhql.toString());
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		// 业务类型
		Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
		if (size > 0) {
			sqlList.append(" order by zhtqsj desc");
			ezJdcCheckList = this.defaultDao.findSQLByPage(sqlList.toString(),
					offset, pageSize);
			for (int i = 0; i < ezJdcCheckList.size(); i++) {
				Object[] objs = (Object[]) ezJdcCheckList.get(i);
				if (objs[2] != null) {
					objs[2] = ywlxList.get(objs[2]);
				}
			}

		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("slList", slList);
		request.setAttribute("ezJdcCheckList", ezJdcCheckList);
		request.getSession().setAttribute("ezJdcCheckList", ezJdcCheckList);
	}

	@SuppressWarnings("unchecked")
	public List getDrvCheckList(HttpServletRequest request, int currentPage)
			throws Exception {
		List ezDrvLiceCheckList = new ArrayList();
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select CSSHRZH,CSSHRXM,HBLX from  ez_drv_lice_chan_app t where 1=1");
		StringBuffer sqlList = new StringBuffer(
				"Select CSSHRZH,"
						+ // 分配账号
						"CSSHRXM,"
						+ // 分配姓名
						"HBLX,"
						+ // 业务类型
						"yshstg+yshswtg + wshs zs,"
						+ // 自动提取数量
						"yshstg, "
						+ // 已审核通过
						"wshs, "
						+ // 未审核数
						"zhtqsj, "
						+ // 最后分配时间
						"hblxkey, "
						+ "yshswtg "
						+ // 已审核未通过
						"From (select CSSHRZH,CSSHRXM,HBLX,sum(case "
						+ " when ZHCLZT <> '3' and ZHCLZT<>'TB' then  1 else 0 "
						+ " end) yshstg, sum(case when ZHCLZT='TB' then "
						+ " 1 else 0 end) yshswtg, sum(case when ZHCLZT = '3' then "
						+ " 1 else 0 end) wshs, "
						+ "min(t.CSPCSJ) zhtqsj,hblx hblxkey from EZ_DRV_LICE_CHAN_APP t where 1=1");

		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String q_account = request.getParameter("q_account");
		String hblx = request.getParameter("hblx");
		String type = request.getParameter("type");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			sqlCount.append(" and t.CSPCSJ >= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ <to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else {
			if (!"drv".equals(type)) {
				s_date = DateUtil.date2String(new Date());
				e_date = s_date;
				sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
						+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('"
						+ e_date + "', 'yyyy-mm-dd')+1");
				sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
						+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('"
						+ e_date + "', 'yyyy-mm-dd')+1");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			}
		}
		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			sqlCount.append(" and t.hblx = '" + hblx + "' ");
			sqlList.append(" and t.hblx = '" + hblx + "' ");
			request.setAttribute("hblx", hblx);
		}

		if (q_account != null && !"".equals(q_account)) {
			sqlCount.append(" and t.CSSHRZH = '" + q_account + "' ");
			sqlList.append(" and t.CSSHRZH = '" + q_account + "' ");
			request.setAttribute("q_account", q_account);
		}

		if ("drv".equals(type)) {
			sqlCount.append(" group by CSSHRZH,CSSHRXM,HBLX");
			sqlList.append(" group by CSSHRZH,CSSHRXM,HBLX");
			sqlCount
					.append(" having sum(case when ZHCLZT = '3' then 1 else 0 end)!='0' )");
			sqlList
					.append(" having sum(case when ZHCLZT = '3' then 1 else 0 end)!='0' )");
			request.setAttribute("type", type);
		} else {
			sqlCount.append(" group by CSSHRZH,CSSHRXM,HBLX)");
			sqlList.append(" group by CSSHRZH,CSSHRXM,HBLX)");
		}
		Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
		List slList = new ArrayList();
		StringBuffer slhql = new StringBuffer(
				"select dfp,dsh from (select sum(case when ZHCLZT = '0' then "
						+ " 1 else 0 end) dfp,sum(case when ZHCLZT='3' then 1 else 0 end) dsh from EZ_DRV_LICE_CHAN_APP)");
		slList = this.defaultDao.findSQL(slhql.toString());
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		if (size > 0) {
			sqlList.append(" order by zhtqsj desc");
			ezDrvLiceCheckList = this.defaultDao.findSQLByPage(sqlList
					.toString(), offset, pageSize);
			for (int i = 0; i < ezDrvLiceCheckList.size(); i++) {
				Object[] objs = (Object[]) ezDrvLiceCheckList.get(i);
				if (objs[2] != null) {
					objs[2] = ywlxList.get(objs[2]);
				}
			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("ezDrvLiceCheckList", ezDrvLiceCheckList);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("slList", slList);
		request.getSession().setAttribute("ezDrvLiceCheckList",
				ezDrvLiceCheckList);
		return ezDrvLiceCheckList;
	}

	@SuppressWarnings("unchecked")
	public List getDrvGzlList(HttpServletRequest request, int currentPage)
			throws Exception {
		List ezDrvLiceGzlList = new ArrayList();
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select CSSHRZH,CSSHRXM,HBLX from  ez_drv_lice_chan_app t where 1=1");
		StringBuffer sqlList = new StringBuffer(
				"Select CSSHRZH,"
						+ // 分配账号
						"CSSHRXM,"
						+ // 分配姓名
						"HBLX,"
						+ // 业务类型
						"yshstg+yshswtg + wshs zs,"
						+ // 自动提取数量
						"yshstg, "
						+ // 已审核通过
						"wshs, "
						+ // 未审核数
						"zhtqsj, "
						+ // 最后分配时间
						"hblxkey, "
						+ "yshswtg "
						+ // 已审核未通过
						"From (select CSSHRZH,CSSHRXM,HBLX,sum(case "
						+ " when ZHCLZT <> '3' and ZHCLZT<>'TB'  then  1 else 0 "
						+ " end) yshstg, sum(case when ZHCLZT='TB' then "
						+ " 1 else 0 end) yshswtg, sum(case when ZHCLZT = '3' then "
						+ " 1 else 0 end) wshs, "
						+ "min(t.CSPCSJ) zhtqsj,hblx hblxkey from EZ_DRV_LICE_CHAN_APP t where 1=1");

		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String q_account = request.getParameter("q_account");
		String hblx = request.getParameter("hblx");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			sqlCount.append(" and t.CSPCSJ >= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ <to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = DateUtil.date2String(new Date());
			e_date = s_date;
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			sqlCount.append(" and t.hblx = '" + hblx + "' ");
			sqlList.append(" and t.hblx = '" + hblx + "' ");
			request.setAttribute("hblx", hblx);
		}

		if (q_account != null && !"".equals(q_account)) {
			sqlCount.append(" and t.CSSHRZH = '" + q_account + "' ");
			sqlList.append(" and t.CSSHRZH = '" + q_account + "' ");
			request.setAttribute("q_account", q_account);
		}

		sqlCount.append(" group by CSSHRZH,CSSHRXM,HBLX)");
		sqlList.append(" group by CSSHRZH,CSSHRXM,HBLX)");
		Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
		List slList = new ArrayList();
		StringBuffer slhql = new StringBuffer(
				"select dfp,dsh from (select sum(case when ZHCLZT = '0' then "
						+ " 1 else 0 end) dfp,sum(case when ZHCLZT='3' then 1 else 0 end) dsh from EZ_DRV_LICE_CHAN_APP)");
		slList = this.defaultDao.findSQL(slhql.toString());
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		if (size > 0) {
			sqlList.append(" order by zhtqsj desc");
			ezDrvLiceGzlList = this.defaultDao.findSQLByPage(
					sqlList.toString(), offset, pageSize);
			for (int i = 0; i < ezDrvLiceGzlList.size(); i++) {
				Object[] objs = (Object[]) ezDrvLiceGzlList.get(i);
				if (objs[2] != null) {
					objs[2] = ywlxList.get(objs[2]);
				}
			}
		}

		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("ezDrvLiceGzlList", ezDrvLiceGzlList);
		request.setAttribute("slList", slList);
		request.setAttribute("ywlxList", ywlxList);
		request.getSession().setAttribute("ezDrvLiceGzlList", ezDrvLiceGzlList);
		return ezDrvLiceGzlList;
	}

	@SuppressWarnings("unchecked")
	public List getDrvGzlView(HttpServletRequest request) throws Exception {
		String fpzh = request.getParameter("fpzh");
		String lx = request.getParameter("lx");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		String hblx = request.getParameter("hblx");
		List ezDrvLiceGzlView = new ArrayList();

		StringBuffer sqlQuery = null;
		if (lx != null && lx.equals("drv")) {
			sqlQuery = new StringBuffer(
					"select csshrzh,csshrxm,cspch,yshs,wshs,zhtqsj"
							+ " from (select csshrzh,csshrxm,cspch,"
							+ " sum(case" + " when ZHCLZT <> '3' then" + " 1"
							+ " else" + " 0" + " end) yshs, " + " sum(case"
							+ " when ZHCLZT = '3' then" + " 1" + " else" + " 0"
							+ " end) wshs, min(t.CSPCSJ) zhtqsj"
							+ " from EZ_DRV_LICE_CHAN_APP t where 1=1");
		}

		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			sqlQuery.append(" and t.hblx = '" + hblx + "' ");
		}
		if (fpzh != null && !"".equals(fpzh)) {
			sqlQuery.append(" and t.csshrzh='" + fpzh + "'");
		}
		if (sdate != null && !"".equals(sdate)) {
			sqlQuery.append(" and t.CSPCSJ >= to_date('" + sdate
					+ "', 'yyyy-mm-dd')");
		}
		if (edate != null && !"".equals(edate)) {
			sqlQuery.append(" and t.CSPCSJ < to_date('" + edate
					+ "', 'yyyy-mm-dd')+1");
		}
		sqlQuery.append(" group by csshrzh,csshrxm,cspch)");
		sqlQuery.append(" order by zhtqsj desc");

		ezDrvLiceGzlView = this.defaultDao.findSQL(sqlQuery.toString());
		request.getSession().setAttribute("ezDrvLiceGzlView", ezDrvLiceGzlView);
		return ezDrvLiceGzlView;
	}

	@SuppressWarnings("unchecked")
	public void getJdcGzlList(HttpServletRequest request, int currentPage)
			throws Exception {
		List ezJdcGzlList = new ArrayList();
		StringBuffer sqlCount = new StringBuffer(
				"select count(0) from (select CSSHRZH,CSSHRXM,ywlx from  EZ_JDC_CHAN_APP t where 1=1");
		StringBuffer sqlList = new StringBuffer(
				"Select CSSHRZH,"
						+ // 分配账号
						"CSSHRXM,"
						+ // 分配姓名
						"YWLX,"
						+ // 业务类型
						"yshstg+yshswtg + wshs zs,"
						+ // 自动提取数量
						"yshstg, "
						+ // 已审核通过
						"wshs, "
						+ // 未审核数
						"zhtqsj, "
						+ // 最后分配时间
						"ywlxkey, "
						+ // 业务类型键
						"yshswtg "
						+ // 审核未通过
						"From (select CSSHRZH,CSSHRXM,YWLX,sum(case "
						+ " when ZHCLZT <> '3' and ZHCLZT <> 'TB' then  1 else 0 "
						+ " end) yshstg, sum(case when  ZHCLZT='TB' then "
						+ " 1 else 0 end) yshswtg, sum(case when ZHCLZT = '3' then "
						+ " 1 else 0 end) wshs, "
						+ "min(t.CSPCSJ) zhtqsj,YWLX as ywlxkey from EZ_JDC_CHAN_APP t where 1=1");

		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String q_account = request.getParameter("q_account");
		String ywlx = request.getParameter("ywlx");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			sqlCount.append(" and t.CSPCSJ >= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ <to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");

			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = DateUtil.date2String(new Date());
			e_date = s_date;
			sqlCount.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			sqlList.append(" and t.CSPCSJ>= to_date('" + s_date
					+ "', 'yyyy-mm-dd')" + " and t.CSPCSJ < to_date('" + e_date
					+ "', 'yyyy-mm-dd')+1");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlCount.append(" and t.ywlx = '" + ywlx + "' ");
			sqlList.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlx", ywlx);
		}

		if (q_account != null && !"".equals(q_account)) {
			sqlCount.append(" and t.CSSHRZH = '" + q_account + "' ");
			sqlList.append(" and t.CSSHRZH = '" + q_account + "' ");
			request.setAttribute("q_account", q_account);
		}

		sqlCount.append(" group by CSSHRZH,CSSHRXM,ywlx)");
		sqlList.append(" group by CSSHRZH,CSSHRXM,ywlx)");
		List slList = new ArrayList();
		StringBuffer slhql = new StringBuffer(
				"select dfp,dsh from (select sum(case when ZHCLZT = '0' then "
						+ " 1 else 0 end) dfp,sum(case when ZHCLZT='3' then 1 else 0 end) dsh from EZ_JDC_CHAN_APP)");
		int size = defaultDao.getRepositoryBySQLListSize(sqlCount.toString());
		slList = this.defaultDao.findSQL(slhql.toString());
		Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
		if (size > 0) {
			sqlList.append(" order by zhtqsj desc");
			ezJdcGzlList = this.defaultDao.findSQLByPage(sqlList.toString(),
					offset, pageSize);
			for (int i = 0; i < ezJdcGzlList.size(); i++) {
				Object[] objs = (Object[]) ezJdcGzlList.get(i);
				if (objs[2] != null) {
					objs[2] = ywlxList.get(objs[2]);
				}
			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("slList", slList);
		request.setAttribute("ezJdcGzlList", ezJdcGzlList);
		request.setAttribute("ywlxList", ywlxList);
		request.getSession().setAttribute("ezJdcGzlList", ezJdcGzlList);
	}

	@SuppressWarnings("unchecked")
	public List getJdcGzlView(HttpServletRequest request) throws Exception {
		String fpzh = request.getParameter("fpzh");
		String lx = request.getParameter("lx");
		String ywlx = request.getParameter("ywlx");
		String sdate = request.getParameter("sdate");
		String edate = request.getParameter("edate");
		List ezJdcGzlView = new ArrayList();

		StringBuffer sqlQuery = null;

		if (lx != null && lx.equals("jdc")) {
			sqlQuery = new StringBuffer(
					"select csshrzh,csshrxm,cspch,yshs,wshs,zhtqsj"
							+ " from (select csshrzh,csshrxm,cspch,"
							+ " sum(case" + " when ZHCLZT <> '3' then" + " 1"
							+ " else" + " 0" + " end) yshs, " + " sum(case"
							+ " when ZHCLZT = '3' then" + " 1" + " else" + " 0"
							+ " end) wshs, " + " min(t.CSPCSJ) zhtqsj"
							+ " from EZ_JDC_CHAN_APP t where 1=1");
		}

		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			sqlQuery.append(" and t.ywlx ='" + ywlx + "'");
		}

		if (fpzh != null && !"".equals(fpzh)) {
			sqlQuery.append(" and t.csshrzh='" + fpzh + "'");
		}
		if (sdate != null && !"".equals(sdate)) {
			sqlQuery.append(" and t.CSPCSJ >= to_date('" + sdate
					+ "', 'yyyy-mm-dd')");
		}
		if (edate != null && !"".equals(edate)) {
			sqlQuery.append(" and t.CSPCSJ < to_date('" + edate
					+ "', 'yyyy-mm-dd')+1");
		}
		sqlQuery.append(" group by csshrzh,csshrxm,cspch)");
		sqlQuery.append(" order by zhtqsj desc");
		ezJdcGzlView = this.defaultDao.findSQL(sqlQuery.toString());
		request.getSession().setAttribute("ezJdcGzlView", ezJdcGzlView);
		return ezJdcGzlView;
	}

	@SuppressWarnings("unchecked")
	public EzDrvLiceChanApp getDrvChanInfo(HttpServletRequest request,
			String wwlsh) throws Exception {
		EzDrvLiceChanApp edlca = new EzDrvLiceChanApp();
		if (null != wwlsh) {
			edlca = (EzDrvLiceChanApp) defaultDao.getRepository(wwlsh,
					EzDrvLiceChanApp.class);
			if (null != edlca) {
				edlca.setSfzmmc(getCodeVal("19", edlca.getSfzmmc()));
				edlca.setGj(getCodeVal("31", edlca.getGj()));
				/*
				 * if ("H".equals(edlca.getHblx())) { edlca.setHblx("换证"); }
				 * else if ("B".equals(edlca.getHblx())) { edlca.setHblx("补证");
				 * }
				 */
				System.out.println("====" + edlca.getXb());
				if ("1".equals(edlca.getXb())) {
					edlca.setXb("男");
				} else if ("2".equals(edlca.getXb())) {
					edlca.setXb("女");
				}
				if ("1".equals(edlca.getSqfs())) {
					edlca.setSqfs("本人申请");
				} else if ("2".equals(edlca.getSqfs())) {
					edlca.setSqfs("监护人申请");
				} else if ("3".equals(edlca.getSqfs())) {
					edlca.setSqfs("委托他人申请");
					EzDzwt ezdzwt = new EzDzwt();
					ezdzwt = (EzDzwt) defaultDao.getRepository(edlca
							.getSfzmhm(), EzDzwt.class);
					request.setAttribute("ezdzwt", ezdzwt);
				}
				if ("0".equals(edlca.getHjszd())) {
					edlca.setHjszd("深户");
				} else if ("1".equals(edlca.getHjszd())) {
					edlca.setHjszd("非深户");
				}
				if ("0".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("待初审（未分配）");
				} else if ("1".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("初审通过");
				} else if ("2".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("车管已制证");
				} else if ("3".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("待初审(已分配)");
				} else if ("TB".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("退办");
				}
				if ("0".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("待初审");
				} else if ("1".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("已初审");
				} else if ("2".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("已制证");
				}
				if ("0".equals(edlca.getBsl())) {
					edlca.setBsl("不合格");
				} else if ("1".equals(edlca.getBsl())) {
					edlca.setBsl("合格");
				}
				if ("0".equals(edlca.getTl())) {
					edlca.setTl("不合格");
				} else if ("1".equals(edlca.getTl())) {
					edlca.setTl("合格");
				}
				if ("0".equals(edlca.getSz())) {
					edlca.setSz("不合格");
				} else if ("1".equals(edlca.getSz())) {
					edlca.setSz("合格");
				}
				if ("0".equals(edlca.getZxz())) {
					edlca.setZxz("不合格");
				} else if ("1".equals(edlca.getZxz())) {
					edlca.setZxz("合格");
				}
				if ("0".equals(edlca.getYxz())) {
					edlca.setYxz("不合格");
				} else if ("1".equals(edlca.getYxz())) {
					edlca.setYxz("合格");
				}
				if ("0".equals(edlca.getQgjb())) {
					edlca.setQgjb("不合格");
				} else if ("1".equals(edlca.getQgjb())) {
					edlca.setQgjb("合格");
				}
				if ("0".equals(edlca.getSftjhg())) {
					edlca.setSftjhg("不合格");
				} else if ("1".equals(edlca.getSftjhg())) {
					edlca.setSftjhg("合格");
				}
				Map<String, String> lybzList = getCodeJszYwlx("YHLY");
				String lybzval = lybzList.get(edlca.getLybz());
				edlca.setLybz(lybzval);
				edlca.setYhly(lybzval);
				Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
				String hblxval = ywlxList.get(edlca.getHblx());
				edlca.setHblx(hblxval);
			}

		}
		return edlca;
	}
	//驾驶证审核
	@SuppressWarnings("unchecked")
	public Integer updateDrvChan(HttpServletRequest request, String wwlsh,
			String clzt, String clztsm) throws Exception {
		String ywlx = request.getParameter("ywlx");
		String sql="select count(*) from sfrz_sjzd where dmlb='DRV_LX' and dmsm='"+ywlx.trim()+"' and dmsm1='1'";
		int valueList=defaultDao.getRepositoryBySQLListSize(sql);
		
		EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) defaultDao.getRepository(
				wwlsh, EzDrvLiceChanApp.class);
		if (null != edlca) {
			User user = (User) request.getSession().getAttribute(
					SysConst.USERBEAN);
			edlca.setZhclsm(clztsm);
			if(valueList>0){
				edlca.setZhclhj("2");
				edlca.setZhclzt("2");
			}else{
				edlca.setZhclhj("1");
				edlca.setZhclzt(clzt);
			}
			edlca.setZhclsj(new Date());
			edlca.setZhclr(user.getName());
			edlca.setZhclrxm(user.getYgxm());
			edlca.setZhclrbm(user.getBmid());
			edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
			edlca.setZhclrbmmc(user.getBmmc());
			edlca.setSynFlag("UW");
			edlca.setTranFlag(null);
			edlca.setTranDate(null);
			defaultDao.updateRepository(edlca);
			if ("TB".equals(clzt)) {
				StringBuffer sms = new StringBuffer();
				Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
				String hblxval = ywlxList.get(edlca.getHblx());
				sms
						.append("<?xml version=\"1.0\"  encoding=\"utf-8\"?><request>");
				sms.append("<userZh>sfrz</userZh>");
				sms.append("<userMy>sfrz1234</userMy>");
				sms.append("<mobile>" + edlca.getSjrsj() + "</mobile>");
				sms.append("<content>" + edlca.getXm() + ",你好！您通过星级用户中心申请的"
						+ hblxval + "业务已退办，退办原因为：" + clztsm + "！</content>");
				sms.append("</request>");
				String sql1 = "INSERT INTO XDXPT_SENDSMS (xh, ywlsh, ywlx, sjhm, sms, lrsj, fssj, zt) "
						+ " values(SEQ_XDXPT_SENDSMS.Nextval, '"
						+ wwlsh
						+ "', 'JSZ("
						+ edlca.getHblx()
						+ ")', '"
						+ edlca.getSjrsj()
						+ "', '"
						+ sms
						+ "', sysdate, null, '0')";
				defaultDao.updateRepositoryBySql(sql1);
			}
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public List getDrvChanList(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		request.setAttribute("csshrzhs", user.getName());
		List ezDrvLiceChanAppList = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"from EzDrvLiceChanApp as t where 1=1 ");
		StringBuffer sql = new StringBuffer(
				"select count(1) from ez_drv_lice_chan_app t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String wwlsh = request.getParameter("wwlsh");
		String yhly = request.getParameter("yhly");
		String xm = request.getParameter("xm");
		String hblx = request.getParameter("hblx");
		String zhclzt = request.getParameter("zhclzt");
		String sfzmhm = request.getParameter("sfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		int size = 0;
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			hql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			hql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = "2015-08-17";
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wslrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 采集流水号
		if (wwlsh != null && !"".equals(wwlsh)) {
			hql.append(" and t.wwlsh = '" + wwlsh + "' ");
			sql.append(" and t.wwlsh = '" + wwlsh + "' ");
			request.setAttribute("wwlsh", wwlsh);
		}
		// 姓名
		if (xm != null && !"".equals(xm)) {
			hql.append(" and t.xm = '" + xm + "' ");
			sql.append(" and t.xm = '" + xm + "' ");
			request.setAttribute("xm", xm);
		}
		// 数据来源
		if (yhly != null && !"".equals(yhly)) {
			hql.append(" and t.yhly = '" + yhly + "' ");
			sql.append(" and t.yhly = '" + yhly + "' ");
			request.setAttribute("yhly", yhly);
		}
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			hql.append(" and t.hblx = '" + hblx + "' ");
			sql.append(" and t.hblx = '" + hblx + "' ");
			request.setAttribute("hblx", hblx);
		}

		// 处理状态
		if (zhclzt != null && !"".equals(zhclzt)) {
			hql.append(" and t.zhclzt = '" + zhclzt + "' ");
			sql.append(" and t.zhclzt = '" + zhclzt + "' ");
			request.setAttribute("zhclzt", zhclzt);
		}

		hql.append(" order by t.wslrsj desc ");
		size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		Map<String, String> sfzmmcSjzdMap = getCode("19");
		Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
		Map<String, String> yhlyList = getCodeJszYwlx("YHLY");
		if (size > 0) {
			ezDrvLiceChanAppList = this.defaultDao.findHQLByPage(
					hql.toString(), offset, pageSize);
			for (int i = 0; i < ezDrvLiceChanAppList.size(); i++) {
				EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) ezDrvLiceChanAppList
						.get(i);
				if (null != edlca) {
					String sfzmmcval = sfzmmcSjzdMap.get(edlca.getSfzmmc());
					edlca.setSfzmmc(sfzmmcval);
					String hblxval = ywlxList.get(edlca.getHblx());
					edlca.setHblx(hblxval);
					if ("0".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审（未分配）");
					} else if ("1".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("初审通过");
					} else if ("2".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("车管已制证");
					} else if ("3".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审(已分配)");
					} else if ("TB".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("退办");
					}
				}
			}
		}

		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("yhlyList", yhlyList);
		request.setAttribute("map", map);
		request.setAttribute("ezDrvLiceChanAppList", ezDrvLiceChanAppList);
		return ezDrvLiceChanAppList;
	}

	@SuppressWarnings("unchecked")
	public EzJdcChanApp getJdcChanInfo(String wwlsh) throws Exception {
		EzJdcChanApp edlca = new EzJdcChanApp();
		if (null != wwlsh) {
			edlca = (EzJdcChanApp) defaultDao.getRepository(wwlsh,
					EzJdcChanApp.class);
			if (null != edlca) {
				edlca.setSfzmmc(getCodeVal("19", edlca.getSfzmmc()));
				edlca.setHpzl(getCodeVal("7", edlca.getHpzl()));
				edlca.setCllx(getCodeDrv("1004", edlca.getCllx()));
				// edlca.setYwlx(getCodeVal("18", edlca.getYwlx()));getCodeDrv
				if ("0".equals(edlca.getFjszd())) {
					edlca.setFjszd("深圳");
				} else if ("1".equals(edlca.getFjszd())) {
					edlca.setFjszd("外地");
				}
				if ("0".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("待初审（未分配）");
				} else if ("1".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("初审通过");
				} else if ("2".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("车管已制证");
				} else if ("3".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("待初审(已分配)");
				} else if ("TB".equals(edlca.getZhclzt())) {
					edlca.setZhclzt("退办");
				}
				/*
				 * if ("0".equals(edlca.getLybz())) { edlca.setLybz("外网"); }
				 * else if ("1".equals(edlca.getLybz())) { edlca.setLybz("PDA");
				 * } else if ("2".equals(edlca.getLybz())) {
				 * edlca.setLybz("移动"); } else if ("3".equals(edlca.getLybz()))
				 * { edlca.setLybz("金融联受理"); }
				 */
				/*
				 * if ("H".equals(edlca.getYwyy())) { edlca.setYwyy("换证"); }
				 * else if ("B".equals(edlca.getYwyy())) { edlca.setYwyy("补证");
				 * }
				 */
				if ("0".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("待初审");
				} else if ("1".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("已初审");
				} else if ("2".equals(edlca.getZhclhj())) {
					edlca.setZhclhj("已制证");
				}
				Map<String, String> lybzList = getCodeJszYwlx("YHLY");
				String lybzval = lybzList.get(edlca.getLybz());
				edlca.setLybz(lybzval);
				edlca.setYhly(lybzval);
				Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
				String ywlxval = ywlxList.get(edlca.getYwlx());
				edlca.setYwlx(ywlxval);
				/*
				 * if ("1".equals(edlca.getYwlx())) { edlca.setYwlx("行驶证业务"); }
				 * else if ("2".equals(edlca.getYwlx())) {
				 * edlca.setYwlx("合格标志业务"); }
				 */
			}
		}
		return edlca;
	}

	@SuppressWarnings("unchecked")
	public List getTqdrv(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		List ezDrvLiceChanAppList = new ArrayList();
		List ezDrvList = new ArrayList();
		String hblx = request.getParameter("hblxtq");
		StringBuffer hql = new StringBuffer(
				"from EzDrvLiceChanApp as t where t.zhclzt='3' and t.csshrzh='"
						+ user.getName() + "' and 1=1 ");
		StringBuffer sql = new StringBuffer();
		// 业务类型
		if (hblx != null && !"".equals(hblx)) {
			hql.append(" and t.hblx = '" + hblx + "' ");
			request.setAttribute("hblxtq", hblx);
		}

		int pageSize = 50;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		ezDrvList = defaultDao.getRepositories(hql.toString());
		int size = ezDrvList.size();
		if (ezDrvList.size() > 0) {
			String pch = "";
			for (int i = 0; i < ezDrvList.size(); i++) {
				EzDrvLiceChanApp edlca1 = (EzDrvLiceChanApp) ezDrvList.get(i);
				pch = edlca1.getCspch();
			}
			hql = new StringBuffer(
					"from EzDrvLiceChanApp as t where t.csshrzh='"
							+ user.getName() + "' and cspch='" + pch
							+ "' and 1=1 order by zhclzt desc");
			// 业务类型
			if (hblx != null && !"".equals(hblx)) {
				hql.append(" and t.hblx = '" + hblx + "' ");
				request.setAttribute("hblxtq", hblx);
			}
			ezDrvLiceChanAppList = this.defaultDao.findHQLByPage(
					hql.toString(), offset, pageSize);
			request.setAttribute("num", 3);
		} else {
			hql = new StringBuffer(
					"from EzDrvLiceChanApp t where t.zhclzt='0' ");
			sql = new StringBuffer(
					"select count(1) from (select * from ez_drv_lice_chan_app where zhclzt='0' order by WSLRSJ asc ) where rownum <=50");
			size = defaultDao.getRepositoryBySQLListSize(sql.toString());
			offset = SysConst.PAGESIZE * (currentPage - 1);
			curi = request.getRequestURI();
			// 业务类型
			if (hblx != null && !"".equals(hblx)) {
				hql.append(" and t.hblx = '" + hblx + "' ");
				request.setAttribute("hblxtq", hblx);
			}
			hql.append(" order by t.wslrsj asc ");
			ezDrvLiceChanAppList = this.defaultDao.findHQLByPage(
					hql.toString(), offset, pageSize);
		}
		Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
		Map<String, String> yhlyList = getCodeJszYwlx("YHLY");
		Map<String, String> shztList = getCodeJszYwlx("SHZT");
		Map<String, String> sfzmmcSjzdMap = getCode("19");
		if (ezDrvLiceChanAppList.size() > 0) {
			for (int i = 0; i < ezDrvLiceChanAppList.size(); i++) {
				EzDrvLiceChanApp edlca1 = (EzDrvLiceChanApp) ezDrvLiceChanAppList
						.get(i);
				String sfzmmcval = sfzmmcSjzdMap.get(edlca1.getSfzmmc());
				edlca1.setSfzmc(sfzmmcval);
				String bhlxval = ywlxList.get(edlca1.getHblx());
				edlca1.setBhlx(bhlxval);
				String shztval = shztList.get(edlca1.getZhclzt());
				edlca1.setShzt(shztval);
				request.setAttribute("cspch", edlca1.getCspch());
				System.out.println(edlca1.getWslrsj());
			}
		} else {
			request.setAttribute("num", 0);
		}
		request.setAttribute("csshrzhs", user.getName());
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", 50);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("yhlyList", yhlyList);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("map", map);
		request.setAttribute("ezDrvLiceChanAppList", ezDrvLiceChanAppList);
		return ezDrvLiceChanAppList;
	}

	@SuppressWarnings("unchecked")
	public List getTqjdc(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		List ezJdcChanAppList = new ArrayList();
		List ezJdcList = new ArrayList();
		String ywlx = request.getParameter("ywlxtq");
		StringBuffer hql = new StringBuffer(
				"from EzJdcChanApp as t where t.zhclzt='3' and t.csshrzh='"
						+ user.getName() + "' and 1=1");
		StringBuffer sql = new StringBuffer();
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			hql.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlxtq", ywlx);
		}
		int pageSize = 50;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		ezJdcList = defaultDao.getRepositories(hql.toString());
		int size = ezJdcList.size();
		if (ezJdcList.size() > 0) {
			String pch = "";
			for (int i = 0; i < ezJdcList.size(); i++) {
				EzJdcChanApp edlca1 = (EzJdcChanApp) ezJdcList.get(i);
				pch = edlca1.getCspch();
			}
			hql = new StringBuffer("from EzJdcChanApp as t where t.csshrzh='"
					+ user.getName() + "' and cspch='" + pch
					+ "' and 1=1 order by zhclzt desc ");
			// 业务类型
			if (ywlx != null && !"".equals(ywlx)) {
				hql.append(" and t.ywlx = '" + ywlx + "' ");
				request.setAttribute("ywlxtq", ywlx);
			}
			ezJdcChanAppList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			request.setAttribute("num", 3);
		} else {
			hql = new StringBuffer("from EzJdcChanApp t where zhclzt='0' ");
			sql = new StringBuffer(
					"select count(1) from (select * from ez_jdc_chan_app where zhclzt='0' order by wwlrsj asc ) where rownum <=50 ");
			size = defaultDao.getRepositoryBySQLListSize(sql.toString());
			offset = SysConst.PAGESIZE * (currentPage - 1);
			curi = request.getRequestURI();
			// 业务类型
			if (ywlx != null && !"".equals(ywlx)) {
				hql.append(" and t.ywlx = '" + ywlx + "' ");
				request.setAttribute("ywlxtq", ywlx);
			}
			hql.append(" order by WWLRSJ asc ");
			ezJdcChanAppList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
		}
		Map<String, String> hpzlSjzdMap = getCode("7");
		Map<String, String> yhlyList = getCodeJszYwlx("YHLY");
		Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
		Map<String, String> shztList = getCodeJszYwlx("SHZT");
		if (ezJdcChanAppList.size() > 0) {
			for (int i = 0; i < ezJdcChanAppList.size(); i++) {
				EzJdcChanApp edlca1 = (EzJdcChanApp) ezJdcChanAppList.get(i);
				String hpzlval = hpzlSjzdMap.get(edlca1.getHpzl());
				edlca1.setHpzl2(hpzlval);
				String ywlxval = ywlxList.get(edlca1.getYwlx());
				edlca1.setBhlx(ywlxval);
				String shztval = shztList.get(edlca1.getZhclzt());
				edlca1.setShzt(shztval);
				request.setAttribute("cspch", edlca1.getCspch());
			}
		} else {
			request.setAttribute("num", 0);
		}
		request.setAttribute("csshrzhs", user.getName());
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", 50);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("yhlyList", yhlyList);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("map", map);
		request.setAttribute("ezJdcChanAppList", ezJdcChanAppList);
		request.setAttribute("hpzlSjzdMap", hpzlSjzdMap);
		return ezJdcChanAppList;
	}

	@SuppressWarnings("unchecked")
	public List getJdcChanList(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		request.setAttribute("csshrzhs", user.getName());
		// 状态
		List ezJdcChanAppList = new ArrayList();
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String hphm = request.getParameter("hphm");
		String hpzl = request.getParameter("hpzl");
		String jdcsyr = request.getParameter("jdcsyr");
		String ywyy = request.getParameter("ywyy");
		String yhly = request.getParameter("yhly");
		String ywlx = request.getParameter("ywlx");
		String zhclzt = request.getParameter("zhclzt");
		String sfzmhm = request.getParameter("sfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		StringBuffer hql = new StringBuffer("from EzJdcChanApp as t where 1=1 ");
		StringBuffer sql = new StringBuffer(
				"select count(1) from ez_jdc_chan_app t where 1=1 ");
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			hql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			hql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = "2015-08-17";
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.wwlrsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 号牌号码
		if (hphm != null && !"".equals(hphm)) {
			hql.append(" and t.hphm = '" + hphm + "' ");
			sql.append(" and t.hphm = '" + hphm + "' ");
			request.setAttribute("hphm", hphm);
		}
		// 数据来源
		if (yhly != null && !"".equals(yhly)) {
			hql.append(" and t.yhly = '" + yhly + "' ");
			sql.append(" and t.yhly = '" + yhly + "' ");
			request.setAttribute("yhly", yhly);
		}
		// 机动车所有人
		if (jdcsyr != null && !"".equals(jdcsyr)) {
			hql.append(" and t.jdcsyr = '" + jdcsyr + "' ");
			sql.append(" and t.jdcsyr = '" + jdcsyr + "' ");
			request.setAttribute("jdcsyr", jdcsyr);
		}
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		}
		// 换补原因
		if (ywyy != null && !"".equals(ywyy)) {
			hql.append(" and t.ywyy = '" + ywyy + "' ");
			sql.append(" and t.ywyy = '" + ywyy + "' ");
			request.setAttribute("ywyy", ywyy);
		}
		// 业务类型
		if (ywlx != null && !"".equals(ywlx)) {
			hql.append(" and t.ywlx = '" + ywlx + "' ");
			sql.append(" and t.ywlx = '" + ywlx + "' ");
			request.setAttribute("ywlx", ywlx);
		}

		// 处理状态
		if (zhclzt != null && !"".equals(zhclzt)) {
			hql.append(" and t.zhclzt = '" + zhclzt + "' ");
			sql.append(" and t.zhclzt = '" + zhclzt + "' ");
			request.setAttribute("zhclzt", zhclzt);
		}
		// 号牌种类
		if (hpzl != null && !"".equals(hpzl)) {
			hql.append(" and t.hpzl = '" + hpzl + "' ");
			sql.append(" and t.hpzl = '" + hpzl + "' ");
			request.setAttribute("hpzl", hpzl);
		}

		hql.append(" order by t.wwlrsj desc ");
		int size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		Map<String, String> sfzmmcSjzdMap = getCode("19");
		Map<String, String> hpzlSjzdMap = getCode("7");
		Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
		Map<String, String> yhlyList = getCodeJszYwlx("YHLY");
		if (size > 0) {
			ezJdcChanAppList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			for (int i = 0; i < ezJdcChanAppList.size(); i++) {
				EzJdcChanApp edlca = (EzJdcChanApp) ezJdcChanAppList.get(i);
				if (null != edlca) {
					String sfzmmcval = sfzmmcSjzdMap.get(edlca.getSfzmmc());
					edlca.setSfzmmc(sfzmmcval);
					String hpzlval = hpzlSjzdMap.get(edlca.getHpzl());
					edlca.setHpzl(hpzlval);
					String ywlxval = ywlxList.get(edlca.getYwlx());
					edlca.setYwlx(ywlxval);
					if ("0".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审（未分配）");
					} else if ("1".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("初审通过");
					} else if ("2".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("车管已制证");
					} else if ("3".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审(已分配)");
					} else if ("TB".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("退办");
					}

				}
			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("yhlyList", yhlyList);
		request.setAttribute("map", map);
		request.setAttribute("ezJdcChanAppList", ezJdcChanAppList);
		request.setAttribute("hpzlSjzdMap", hpzlSjzdMap);
		return ezJdcChanAppList;
	}

	public Integer updateJdcChan(HttpServletRequest request, String wwlsh,
			String clzt, String clztsm) throws Exception {
		String ywlx=request.getParameter("ywlx");
		System.out.println(ywlx);
		String sql="select count(*) from sfrz_sjzd where dmlb='VEH_LX' and dmsm='"+ywlx.trim()+"' and dmsm1='1'";
		int valueList=defaultDao.getRepositoryBySQLListSize(sql);
		EzJdcChanApp edlca = (EzJdcChanApp) defaultDao.getRepository(wwlsh,
				EzJdcChanApp.class);
		if (null != edlca) {
			User user = (User) request.getSession().getAttribute(
					SysConst.USERBEAN);
			edlca.setZhclsm(clztsm);
			if(valueList>0){
				edlca.setZhclhj("2");
				edlca.setZhclzt("2");
			}else{
				edlca.setZhclhj("1");
				edlca.setZhclzt(clzt);
			}
			edlca.setZhclsj(new Date());
			edlca.setZhclr(user.getName());
			edlca.setZhclrxm(user.getYgxm());
			edlca.setZhclrbm(user.getBmid());
			edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
			edlca.setZhclrbmmc(user.getBmmc());
			edlca.setSynFlag("UW");
			edlca.setTranFlag(null);
			edlca.setTranDate(null);
			defaultDao.updateRepository(edlca);
			if ("TB".equals(clzt)) {
				StringBuffer sms = new StringBuffer();
				Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
				String ywlxval = ywlxList.get(edlca.getYwlx());
				// <?xml version="1.0" encoding="utf-8"?>
				sms
						.append("<?xml version=\"1.0\"  encoding=\"utf-8\"?><request>");
				sms.append("<userZh>sfrz</userZh>");
				sms.append("<userMy>sfrz1234</userMy>");
				sms.append("<mobile>" + edlca.getSjrsj() + "</mobile>");
				sms.append("<content>" + edlca.getJdcsyr() + ",你好！您通过星级用户中心申请的"
						+ ywlxval + "业务已退办，退办原因为：" + clztsm + "！</content>");
				sms.append("</request>");
				String sql1 = "INSERT INTO XDXPT_SENDSMS (xh, ywlsh, ywlx, sjhm, sms, lrsj, fssj, zt) "
						+ "values (SEQ_XDXPT_SENDSMS.Nextval, '"
						+ wwlsh
						+ "', 'JDC("
						+ edlca.getYwlx()
						+ ")', '"
						+ edlca.getSjrsj()
						+ "', '"
						+ sms
						+ "', sysdate, null, '0')";
				defaultDao.updateRepositoryBySql(sql1);
			}
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public List getChanList(HttpServletRequest request, int currentPage,
			String type) throws Exception {
		List chanAppList = new ArrayList();
		String typesql = "";
		if ("drv".equals(type)) {
			typesql = "ez_drv_lice_chan_app";
		} else if ("jdc".equals(type)) {
			typesql = "ez_jdc_chan_app";
		}
		StringBuffer sql = new StringBuffer(
				"select t.pch,t.zhclrxm,t.zhclrbm,t.CSSHRXM,t.CSSHRZH from "
						+ typesql + " t where t.pch is not null ");
		/*
		 * String s_date = request.getParameter("s_date"); String e_date =
		 * request.getParameter("e_date");
		 */
		String pch = request.getParameter("pch");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		/*
		 * if (s_date != null && e_date != null && !"".equals(s_date) &&
		 * !"".equals(e_date)) { sql.append(" and (t.zhclsj between to_date('" +
		 * s_date + "','yyyy-MM-dd') and to_date('" + e_date +
		 * "','yyyy-MM-dd')+1 )"); request.setAttribute("s_date", s_date);
		 * request.setAttribute("e_date", e_date); } else if (s_date != null &&
		 * !"".equals(s_date) && (e_date == null || "".equals(e_date))) { e_date
		 * = DateUtil.date2String(new Date());
		 * sql.append(" and (t.zhclsj between to_date('" + s_date +
		 * "','yyyy-MM-dd') and to_date('" + e_date + "','yyyy-MM-dd')+1 )");
		 * request.setAttribute("s_date", s_date);
		 * request.setAttribute("e_date", e_date); } else if (e_date != null &&
		 * !"".equals(e_date) && (s_date == null || "".equals(s_date))) { Date d
		 * = DateUtil.getAppointDate(-7); s_date = DateUtil.date2String(d);
		 * sql.append(" and (t.zhclsj between to_date('" + s_date +
		 * "','yyyy-MM-dd') and to_date('" + e_date + "','yyyy-MM-dd')+1 )");
		 * request.setAttribute("s_date", s_date);
		 * request.setAttribute("e_date", e_date); }
		 */
		// 批次号
		if (pch != null && !"".equals(pch)) {
			sql.append(" and t.pch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		/*
		 * t.CSSHRXM表示复核成功数量，t.CSSHRZH表示复核总数量
		 */
		sql
				.append(" group by t.pch,t.zhclrxm,t.zhclrbm,t.CSSHRXM,t.CSSHRZH order by pch desc ");
		String sqlsize = "select count(0) from (" + sql.toString() + ")";
		int size = defaultDao.getRepositoryBySQLListSize(sqlsize);
		if (size > 0) {
			chanAppList = this.defaultDao.findSQLByPage(sql.toString(), offset,
					pageSize);
			for (int i = 0; i < chanAppList.size(); i++) {
				Object[] edlca = (Object[]) chanAppList.get(i);
				if (null != edlca) {
					if (null != edlca[2]) {
						edlca[2] = getDeptName(edlca[2].toString());
					}
					// 查询每个批次号的总数量和审核完成的数量
					String sql1 = "";
					String sql2 = "";
					if ("drv".equals(type)) {
						sql1 = "select count(1) from ez_drv_lice_chan_app where pch='"
								+ edlca[0] + "' and zhclzt='2'";
						sql2 = "select count(1) from ez_drv_lice_chan_app where pch='"
								+ edlca[0] + "'";
					} else if ("jdc".equals(type)) {
						sql1 = "select count(1) from ez_jdc_chan_app where pch='"
								+ edlca[0] + "' and zhclzt='2'";
						sql2 = "select count(1) from ez_jdc_chan_app where pch='"
								+ edlca[0] + "'";
					}
					int size1 = defaultDao.getRepositoryBySQLListSize(sql1);
					int size2 = defaultDao.getRepositoryBySQLListSize(sql2);
					edlca[3] = size1;
					edlca[4] = size2;
				}
			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("chanAppList", chanAppList);
		request.setAttribute("type", type);
		return chanAppList;
	}

	@SuppressWarnings("unchecked")
	public List getChaninfoByflow(String tyblsh, String type) throws Exception {
		List dlist = new ArrayList();
		if ("drv".equals(type)) {
			String sql = "select sfzmhm from es_drv_flow t where t.lsh = '"
					+ tyblsh + "'";
			List list = defaultDao.findSQL(sql);
			if (null != list && list.size() > 0) {
				String sfzmhm = (String) list.get(0);
				if (null != sfzmhm) {
					String hql = "from EzDrvLiceChanApp as t where t.zhclzt='1' and t.sfzmhm='"
							+ sfzmhm + "'";
					List xlist = this.defaultDao.getRepositories(hql);
					if (null != xlist && xlist.size() > 0) {
						for (int i = 0; i < xlist.size(); i++) {
							EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) xlist
									.get(i);
							if (null != edlca) {
								List zlist = new ArrayList();
								zlist.add(tyblsh);
								zlist.add(edlca.getWwlsh());
								zlist.add(edlca.getSfzmhm() + ","
										+ edlca.getXm() + "," + edlca.getYddh()
										+ "," + edlca.getLxzsdz());
								dlist.add(zlist);
							}
						}
					}
				}
			}
		} else if ("jdc".equals(type)) {
			String sql = "select hphm,cast(hpzl as varchar2(20)),clsbdh from es_veh_flow t where t.lsh = '"
					+ tyblsh + "'";
			List list = defaultDao.findSQL(sql);
			if (null != list && list.size() > 0) {
				Object[] obj = (Object[]) list.get(0);
				if (null != obj) {
					String hphm = obj[0].toString();
					String hpzl = obj[1].toString();
					String clsbdh = obj[2].toString();
					String hql = "from EzJdcChanApp as t where t.zhclzt='1' and ((t.hphm = '"
							+ hphm + "' and t.hpzl='" + hpzl + "') or clsbdh='"+clsbdh+"')";
					List xlist = this.defaultDao.getRepositories(hql);
					if (null != xlist && xlist.size() > 0) {
						for (int i = 0; i < xlist.size(); i++) {
							EzJdcChanApp edlca = (EzJdcChanApp) xlist.get(i);
							if (null != edlca) {
								List zlist = new ArrayList();
								zlist.add(tyblsh);
								zlist.add(edlca.getWwlsh());
								zlist.add(edlca.getHphm() + ","
										+ edlca.getHpzl() + ","
										+ edlca.getSfzmhm() + ","
										+ edlca.getJdcsyr() + ","
										+ edlca.getYddh() + ","
										+ edlca.getSjrdz());
								dlist.add(zlist);
							}
						}
					}
				}
			}
		}
		return dlist;
	}

	@SuppressWarnings("unchecked")
	public List getChanViewList(HttpServletRequest request, String pch,
			String type) throws Exception {
		List datalist = new ArrayList();
		if (null != pch) {
			if ("drvv".equals(type)) {
				String hql = "from EzDrvLiceChanApp as t where t.pch = '" + pch
						+ "'";
				List xlist = this.defaultDao.getRepositories(hql);
				if (null != xlist && xlist.size() > 0) {
					for (int i = 0; i < xlist.size(); i++) {
						EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) xlist
								.get(i);
						if (null != edlca) {
							List zlist = new ArrayList();
							zlist.add(edlca.getTyblsh());
							zlist.add(edlca.getWwlsh());
							zlist.add(edlca.getSfzmhm() + "," + edlca.getXm()
									+ "," + edlca.getYddh() + ","
									+ edlca.getLxzsdz());
							zlist.add(edlca.getPch());
							zlist.add(edlca.getZhclzt());
							datalist.add(zlist);
						}
					}
				}
			} else if ("jdcv".equals(type)) {
				String hql = "from EzJdcChanApp as t where t.pch = '" + pch
						+ "'";
				List xlist = this.defaultDao.getRepositories(hql);
				if (null != xlist && xlist.size() > 0) {
					for (int i = 0; i < xlist.size(); i++) {
						EzJdcChanApp edlca = (EzJdcChanApp) xlist.get(i);
						if (null != edlca) {
							List zlist = new ArrayList();
							zlist.add(edlca.getTyblsh());
							zlist.add(edlca.getWwlsh());
							zlist.add(edlca.getHphm() + "," + edlca.getHpzl()
									+ "," + edlca.getSfzmhm() + ","
									+ edlca.getJdcsyr() + "," + edlca.getYddh()
									+ "," + edlca.getSjrdz());
							zlist.add(edlca.getPch());
							zlist.add(edlca.getZhclzt());
							datalist.add(zlist);
						}
					}
				}
			}
		}
		request.setAttribute("datalist", datalist);
		request.getSession().setAttribute("ezxfwdatachanlist", datalist);
		return datalist;
	}

	public Integer updateChanDg(HttpServletRequest request, String tyblsh,
			String type, String applsh) throws Exception {
		if ("drvv".equals(type)) {
			String invalue = "";
			invalue = tyblsh + ",B,,";
			String result = itopscDao.get10178(invalue);
			if ("0".equals(result)) {
				EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) defaultDao
						.getRepository(applsh, EzDrvLiceChanApp.class);
				if (null != edlca) {
					User user = (User) request.getSession().getAttribute(
							SysConst.USERBEAN);
					edlca.setTyblsh(tyblsh);
					edlca.setZhclzt("2");
					edlca.setZhclhj("2");
					edlca.setZhclsm("车管已制证");
					edlca.setZhclsj(new Date());
					edlca.setZhclr(user.getName());
					edlca.setZhclrxm(user.getYgxm());
					edlca.setZhclrbm(user.getBmid());
					edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
					edlca.setZhclrbmmc(user.getBmmc());
					edlca.setSynFlag("UW");
					edlca.setTranFlag(null);
					edlca.setTranDate(null);
					defaultDao.updateRepository(edlca);
					return 0;
				}
			}
		} else if ("jdcv".equals(type)) {
			EzJdcChanApp edlca = (EzJdcChanApp) defaultDao.getRepository(
					applsh, EzJdcChanApp.class);
			if (null != edlca) {
				User user = (User) request.getSession().getAttribute(
						SysConst.USERBEAN);
				edlca.setTyblsh(tyblsh);
				edlca.setZhclzt("2");
				edlca.setZhclhj("2");
				edlca.setZhclsm("车管已制证");
				edlca.setZhclsj(new Date());
				edlca.setZhclr(user.getName());
				edlca.setZhclrxm(user.getYgxm());
				edlca.setZhclrbm(user.getBmid());
				edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
				edlca.setZhclrbmmc(user.getBmmc());
				edlca.setSynFlag("UW");
				edlca.setTranFlag(null);
				edlca.setTranDate(null);
				defaultDao.updateRepository(edlca);
				return 0;
			}
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public Integer updateChan(HttpServletRequest request, String appsval,
			String type) throws Exception {
		if (null != appsval) {
			/*
			 * String pch = ""; String sql =
			 * "select EZ_CHAN_PCH.Nextval from dual"; List list =
			 * defaultDao.findSQL(sql); if (null != list && list.size() > 0) {
			 * pch = list.get(0).toString(); }
			 */
			if (appsval.indexOf("&") >= 0) {
				String[] vals = appsval.split("&");
				if (null != vals && vals.length > 0) {
					for (int i = 0; i < vals.length; i++) {
						String[] vals2 = vals[i].split(",");
						String applsh = vals2[0];
						String tyblsh = vals2[1];
						if ("drv".equals(type)) {
							String invalue = "";
							invalue = tyblsh + ",B,,";
							String result = itopscDao.get10178(invalue);
							if ("0".equals(result)) {
								EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) defaultDao
										.getRepository(applsh,
												EzDrvLiceChanApp.class);
								if (null != edlca) {
									User user = (User) request.getSession()
											.getAttribute(SysConst.USERBEAN);
									edlca.setTyblsh(tyblsh);
									edlca.setPch(edlca.getCspch());
									edlca.setZhclzt("2");
									edlca.setZhclhj("2");
									edlca.setZhclsm("车管已制证");
									edlca.setZhclsj(new Date());
									edlca.setZhclr(user.getName());
									edlca.setZhclrxm(user.getYgxm());
									edlca.setZhclrbm(user.getBmid());
									edlca.setZhclrbmKj(getDeptUpid(user
											.getBmid()));
									edlca.setZhclrbmmc(user.getBmmc());
									edlca.setSynFlag("UW");
									edlca.setTranFlag(null);
									edlca.setTranDate(null);
									defaultDao.updateRepository(edlca);
								}
							}
						} else if ("jdc".equals(type)) {
							EzJdcChanApp edlca = (EzJdcChanApp) defaultDao
									.getRepository(applsh, EzJdcChanApp.class);
							if (null != edlca) {
								User user = (User) request.getSession()
										.getAttribute(SysConst.USERBEAN);
								edlca.setTyblsh(tyblsh);
								edlca.setPch(edlca.getCspch());
								edlca.setZhclzt("2");
								edlca.setZhclhj("2");
								edlca.setZhclsm("车管已制证");
								edlca.setZhclsj(new Date());
								edlca.setZhclr(user.getName());
								edlca.setZhclrxm(user.getYgxm());
								edlca.setZhclrbm(user.getBmid());
								edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
								edlca.setZhclrbmmc(user.getBmmc());
								edlca.setSynFlag("UW");
								edlca.setTranFlag(null);
								edlca.setTranDate(null);
								defaultDao.updateRepository(edlca);
							}
						}
					}
				}
			} else {
				String[] vals = appsval.split(",");
				String applsh = vals[0];
				String tyblsh = vals[1];
				if ("drv".equals(type)) {
					String invalue = "";
					invalue = tyblsh + ",B,,";
					String result = itopscDao.get10178(invalue);
					if ("0".equals(result)) {
						EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) defaultDao
								.getRepository(applsh, EzDrvLiceChanApp.class);
						if (null != edlca) {
							User user = (User) request.getSession()
									.getAttribute(SysConst.USERBEAN);
							edlca.setTyblsh(tyblsh);
							edlca.setPch(edlca.getCspch());
							edlca.setZhclzt("2");
							edlca.setZhclsm("车管已制证");
							edlca.setZhclsj(new Date());
							edlca.setZhclr(user.getName());
							edlca.setZhclrxm(user.getYgxm());
							edlca.setZhclrbm(user.getBmid());
							edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
							edlca.setZhclrbmmc(user.getBmmc());
							edlca.setSynFlag("UW");
							edlca.setTranFlag(null);
							edlca.setTranDate(null);
							defaultDao.updateRepository(edlca);
						}
					}
				} else if ("jdc".equals(type)) {
					EzJdcChanApp edlca = (EzJdcChanApp) defaultDao
							.getRepository(applsh, EzJdcChanApp.class);
					if (null != edlca) {
						User user = (User) request.getSession().getAttribute(
								SysConst.USERBEAN);
						edlca.setTyblsh(tyblsh);
						edlca.setPch(edlca.getCspch());
						edlca.setZhclzt("2");
						edlca.setZhclsm("车管已制证");
						edlca.setZhclsj(new Date());
						edlca.setZhclr(user.getName());
						edlca.setZhclrxm(user.getYgxm());
						edlca.setZhclrbm(user.getBmid());
						edlca.setZhclrbmKj(getDeptUpid(user.getBmid()));
						edlca.setZhclrbmmc(user.getBmmc());
						edlca.setSynFlag("UW");
						edlca.setTranFlag(null);
						edlca.setTranDate(null);
						defaultDao.updateRepository(edlca);
					}
				}
			}
			return 0;
		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public String getSfrzUserinfo(String loginuser) throws Exception {
		if (null != loginuser) {
			String hql = "from SfrzUserinfo where loginUser='" + loginuser
					+ "'";
			List list = defaultDao.getRepositories(hql);
			if (null != list && list.size() > 0) {
				SfrzUserinfo su = (SfrzUserinfo) list.get(0);
				if (null != su) {
					return su.getCid();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getDeptUpid(String deptid) throws Exception {
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with "
				+ "org_id = '"
				+ deptid
				+ "' connect by prior up_org = org_id) "
				+ "where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = this.defaultDao.findSQL(deptsql);
		String deptids = "";
		if (null != deptidlist && deptidlist.size() > 0) {
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}

	public String getLoginIp(HttpServletRequest request) throws Exception {
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

	@SuppressWarnings("unchecked")
	public Map getCode(String dmlb) throws Exception {
		String sql = "select dmz,dmsm1 from es_veh_code where dmlb='" + dmlb
				+ "'";
		List list = defaultDao.findSQL(sql);
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] ss = (Object[]) list.get(i);
			map.put(ss[0], ss[1]);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public Map getCodeJszYwlx(String lx) throws Exception {
		String sql = "select dmz,dmsm from sfrz_sjzd t where dmlb='" + lx + "'";
		List list = defaultDao.findSQL(sql);
		Map map = new HashMap();
		for (int i = 0; i < list.size(); i++) {
			Object[] ss = (Object[]) list.get(i);
			map.put(ss[0], ss[1]);
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public String getCodeVal(String dmlb, String dmz) throws Exception {
		String sql = "SELECT dmsm1 from es_veh_code WHERE DMLB='" + dmlb
				+ "' and dmz='" + dmz + "'";
		List list = defaultDao.findSQL(sql);
		String restr = "";
		if (null != list && list.size() > 0) {
			restr = list.get(0).toString();
		} else {
			restr = dmz;
		}
		return restr;
	}

	@SuppressWarnings("unchecked")
	public String getCodeDrv(String dmlb, String dmz) throws Exception {
		String sql = "SELECT dmsm1 from es_drv_code WHERE DMLB='" + dmlb
				+ "' and dmz='" + dmz + "'";
		List list = defaultDao.findSQL(sql);
		String restr = "";
		if (null != list && list.size() > 0) {
			restr = list.get(0).toString();
		} else {
			restr = dmz;
		}
		return restr;
	}

	@SuppressWarnings( { "unchecked" })
	public String getDeptName(String deptid) throws Exception {
		String sql = "select t.org_name from v_veh_org_ycs t where t.org_id='"
				+ deptid + "'";
		List list = defaultDao.findSQL(sql);
		String restr = "";
		if (null != list && list.size() > 0) {
			restr = list.get(0).toString();
		} else {
			restr = deptid;
		}
		return restr;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	@SuppressWarnings("unchecked")
	public Integer updateTqjdc(HttpServletRequest request, List list)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String pch = "";
		Date cspcsj = new Date();
		String sql = "select EZ_CHAN_CSPCH.Nextval from dual";
		List cspch = defaultDao.findSQL(sql);
		if (null != cspch && cspch.size() > 0) {
			pch = cspch.get(0).toString();
		}
		if (list.size() > 0) {
			EzJdcChanApp edlca = new EzJdcChanApp();
			for (int i = 0; i < list.size(); i++) {
				edlca = (EzJdcChanApp) list.get(i);
				edlca.setCspch(pch);
				edlca.setCsshrzh(user.getName());
				edlca.setCsshrbm(user.getBmid());
				edlca.setCsshrxm(user.getYgxm());
				edlca.setCspcsl(list.size());
				edlca.setCspcsj(cspcsj);
				edlca.setZhclzt("3");
				edlca.setSynFlag("UW");
				edlca.setTranFlag(null);
				defaultDao.updateRepository(edlca);
			}
			request.setAttribute("cspch", edlca.getCspch());
			request.setAttribute("num", 1);
			return 0;

		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public Integer updateTqdrv(HttpServletRequest request, List list)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String pch = "";
		String sql = "select EZ_CHAN_DRV_CSPCH.Nextval from dual";
		List cspch = defaultDao.findSQL(sql);
		Date cspcsj = new Date();
		if (null != cspch && cspch.size() > 0) {
			pch = cspch.get(0).toString();
		}
		if (list.size() > 0) {
			EzDrvLiceChanApp edlca = new EzDrvLiceChanApp();
			for (int i = 0; i < list.size(); i++) {
				edlca = (EzDrvLiceChanApp) list.get(i);
				edlca.setCspch(pch);
				edlca.setCsshrzh(user.getName());
				edlca.setCsshrbm(user.getBmid());
				edlca.setCsshrxm(user.getYgxm());
				edlca.setCspcsl(list.size());
				edlca.setCspcsj(cspcsj);
				edlca.setZhclzt("3");
				edlca.setSynFlag("UW");
				edlca.setTranFlag(null);
				defaultDao.updateRepository(edlca);
			}
			request.setAttribute("cspch", edlca.getCspch());
			request.setAttribute("num", 1);
			return 0;

		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public String getSfrzWxJdc(String sfzmhm) throws Exception {
		if (null != sfzmhm) {
			String hql = "from SfrzWxJdc where czfzmhm='" + sfzmhm + "'";
			List list = defaultDao.getRepositories(hql);
			if (null != list && list.size() > 0) {
				SfrzWxJdc su = (SfrzWxJdc) list.get(0);
				if (null != su) {
					return su.getXh();
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String getSfrzWxJsr(String jszhm) throws Exception {
		if (null != jszhm) {
			String hql = "from SfrzWxJsr where jszhm='" + jszhm + "'";
			List list = defaultDao.getRepositories(hql);
			if (null != list && list.size() > 0) {
				SfrzWxJsr su = (SfrzWxJsr) list.get(0);
				if (null != su) {
					return su.getXh();
				}
			}
		}
		return null;
	}

	public IItopscDao getItopscDao() {
		return itopscDao;
	}

	public void setItopscDao(IItopscDao itopscDao) {
		this.itopscDao = itopscDao;
	}

	@SuppressWarnings( { "unchecked", "unchecked" })
	public List getDrvLscxList(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		request.setAttribute("csshrzhs", user.getName());
		List ezDrvLiceLscxList = new ArrayList();
		StringBuffer hql = new StringBuffer(
				"from EzDrvLiceChanApp as t where 1=1 ");
		StringBuffer sql = new StringBuffer(
				"select count(1) from ez_drv_lice_chan_app t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String wwlsh = request.getParameter("wwlsh");
		String hblx = request.getParameter("hblx");
		String sjhm = request.getParameter("sjhm");
		String sfzmhm = request.getParameter("sfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		int size = 0;
		String curi = request.getRequestURI();
		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		} else {
			if (s_date != null && e_date != null && !"".equals(s_date)
					&& !"".equals(e_date)) {
				hql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (s_date != null && !"".equals(s_date)
					&& (e_date == null || "".equals(e_date))) {
				e_date = DateUtil.date2String(new Date());
				hql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (e_date != null && !"".equals(e_date)
					&& (s_date == null || "".equals(s_date))) {
				Date d = DateUtil.getAppointDate(-7);
				s_date = DateUtil.date2String(d);
				hql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
				// 开始日期与结束日期都会空，即第一次加载
			} else {
				s_date = "2015-08-17";
				e_date = DateUtil.date2String(new Date());
				hql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wslrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			}
			// 采集流水号
			if (wwlsh != null && !"".equals(wwlsh)) {
				hql.append(" and t.wwlsh = '" + wwlsh + "' ");
				sql.append(" and t.wwlsh = '" + wwlsh + "' ");
				request.setAttribute("wwlsh", wwlsh);
			}
			// 手机号码
			if (sjhm != null && !"".equals(sjhm)) {
				hql.append(" and t.sjhm = '" + sjhm + "' ");
				sql.append(" and t.sjhm = '" + sjhm + "' ");
				request.setAttribute("sjhm", sjhm);
			}
			// 业务类型
			if (hblx != null && !"".equals(hblx)) {
				hql.append(" and t.hblx = '" + hblx + "' ");
				sql.append(" and t.hblx = '" + hblx + "' ");
				request.setAttribute("hblx", hblx);
			}
		}
		hql.append(" order by t.wslrsj desc ");
		size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		Map<String, String> sfzmmcSjzdMap = getCode("19");
		Map<String, String> ywlxList = getCodeJszYwlx("DRV_LX");
		if (size > 0) {
			ezDrvLiceLscxList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			for (int i = 0; i < ezDrvLiceLscxList.size(); i++) {
				EzDrvLiceChanApp edlca = (EzDrvLiceChanApp) ezDrvLiceLscxList
						.get(i);
				if (null != edlca) {
					String sfzmmcval = sfzmmcSjzdMap.get(edlca.getSfzmmc());
					edlca.setSfzmmc(sfzmmcval);
					String hblxval = ywlxList.get(edlca.getHblx());
					edlca.setHblx(hblxval);
					if ("0".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审（未分配）");
					} else if ("1".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("初审通过");
					} else if ("2".equals(edlca.getZhclzt())) {
						StringBuffer sqllsh = new StringBuffer(
								"select pch from xwebfile.xxgx_yz_drv where sfzmhm='"
										+ edlca.getSfzmhm() + "'");
						List pch = this.defaultDao.findSQL(sqllsh.toString());
						if (pch != null && pch.size() > 0) {
							edlca.setZhclzt("邮政已取");
						} else {
							edlca.setZhclzt("待邮政取走");
						}
					} else if ("3".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审(已分配)");
					} else if ("TB".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("退办");
					}
				}
			}
		}

		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("map", map);
		request.setAttribute("ezDrvLiceLscxList", ezDrvLiceLscxList);
		return ezDrvLiceLscxList;
	}

	@SuppressWarnings("unchecked")
	public List getJdcLscxList(HttpServletRequest request, int currentPage)
			throws Exception {
		// 状态
		List ezJdcLscxAppList = new ArrayList();
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String hphm = request.getParameter("hphm");
		String wwlsh = request.getParameter("wwlsh");
		String sjrsj = request.getParameter("sjrsj");
		String ywlx = request.getParameter("ywlx");
		String zhclzt = request.getParameter("zhclzt");
		String sfzmhm = request.getParameter("sfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		String curi = request.getRequestURI();
		StringBuffer hql = new StringBuffer("from EzJdcChanApp as t where 1=1 ");
		StringBuffer sql = new StringBuffer(
				"select count(1) from ez_jdc_chan_app t where 1=1 ");

		// 身份证明号码
		if (sfzmhm != null && !"".equals(sfzmhm)) {
			hql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			sql.append(" and t.sfzmhm = '" + sfzmhm + "' ");
			request.setAttribute("sfzmhm", sfzmhm);
		} else {
			if (s_date != null && e_date != null && !"".equals(s_date)
					&& !"".equals(e_date)) {
				hql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (s_date != null && !"".equals(s_date)
					&& (e_date == null || "".equals(e_date))) {
				e_date = DateUtil.date2String(new Date());
				hql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			} else if (e_date != null && !"".equals(e_date)
					&& (s_date == null || "".equals(s_date))) {
				Date d = DateUtil.getAppointDate(-7);
				s_date = DateUtil.date2String(d);
				hql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
				// 开始日期与结束日期都会空，即第一次加载
			} else {
				s_date = "2015-08-17";
				e_date = DateUtil.date2String(new Date());
				hql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				sql.append(" and (t.wwlrsj between to_date('" + s_date
						+ "','yyyy-MM-dd') and to_date('" + e_date
						+ "','yyyy-MM-dd')+1 )");
				request.setAttribute("s_date", s_date);
				request.setAttribute("e_date", e_date);
			}
			// 号牌号码
			if (hphm != null && !"".equals(hphm)) {
				hql.append(" and t.hphm = '" + hphm + "' ");
				sql.append(" and t.hphm = '" + hphm + "' ");
				request.setAttribute("hphm", hphm);
			}
			// 流水号
			if (wwlsh != null && !"".equals(wwlsh)) {
				hql.append(" and t.wwlsh = '" + wwlsh + "' ");
				sql.append(" and t.wwlsh = '" + wwlsh + "' ");
				request.setAttribute("wwlsh", wwlsh);
			}
			// 业务类型
			if (ywlx != null && !"".equals(ywlx)) {
				hql.append(" and t.ywlx = '" + ywlx + "' ");
				sql.append(" and t.ywlx = '" + ywlx + "' ");
				request.setAttribute("ywlx", ywlx);
			}

			// 处理状态
			if (zhclzt != null && !"".equals(zhclzt)) {
				hql.append(" and t.zhclzt = '" + zhclzt + "' ");
				sql.append(" and t.zhclzt = '" + zhclzt + "' ");
				request.setAttribute("zhclzt", zhclzt);
			}
			// 号牌种类
			if (sjrsj != null && !"".equals(sjrsj)) {
				hql.append(" and t.sjrsj = '" + sjrsj + "' ");
				sql.append(" and t.sjrsj = '" + sjrsj + "' ");
				request.setAttribute("sjrsj", sjrsj);
			}
		}

		hql.append(" order by t.wwlrsj desc ");
		int size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		Map<String, String> sfzmmcSjzdMap = getCode("19");
		Map<String, String> hpzlSjzdMap = getCode("7");
		Map<String, String> ywlxList = getCodeJszYwlx("VEH_LX");
		if (size > 0) {
			ezJdcLscxAppList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			for (int i = 0; i < ezJdcLscxAppList.size(); i++) {
				EzJdcChanApp edlca = (EzJdcChanApp) ezJdcLscxAppList.get(i);
				if (null != edlca) {
					String sfzmmcval = sfzmmcSjzdMap.get(edlca.getSfzmmc());
					edlca.setSfzmmc(sfzmmcval);
					String hpzlval = hpzlSjzdMap.get(edlca.getHpzl());
					edlca.setHpzl(hpzlval);
					String ywlxval = ywlxList.get(edlca.getYwlx());
					edlca.setYwlx(ywlxval);
					if ("0".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审（未分配）");
					} else if ("1".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("初审通过");
					} else if ("2".equals(edlca.getZhclzt())) {
						StringBuffer sqllsh = new StringBuffer(
								"select pch from xwebfile.xxgx_yz_veh where sfzmhm='"
										+ edlca.getSfzmhm() + "'");
						List pch = this.defaultDao.findSQL(sqllsh.toString());
						if (pch.size() > 0 && pch != null) {
							edlca.setZhclzt("邮政已取");
						} else {
							edlca.setZhclzt("待邮政取走");
						}
					} else if ("3".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("待初审(已分配)");
					} else if ("TB".equals(edlca.getZhclzt())) {
						edlca.setZhclzt("退办");
					}

				}
			}
		}
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("ywlxList", ywlxList);
		request.setAttribute("map", map);
		request.setAttribute("ezJdcLscxAppList", ezJdcLscxAppList);
		request.setAttribute("hpzlSjzdMap", hpzlSjzdMap);
		return ezJdcLscxAppList;
	}

	public Integer getCheckGzsbh(String clsbdh) throws Exception {
		String sql = "select count(0) from offxt.fp_xcgzs o,ez_jdc_chan_app e where o.cjh=e.clsbdh and e.clsbdh='"
				+ clsbdh + "'";
		return defaultDao.getRepositoryBySQLListSize(sql);
	}

	@SuppressWarnings("unchecked")
	public List getEzXxdPrintList(HttpServletRequest request, int currentPage)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		request.setAttribute("csshrzhs", user.getName());
		List ezXxdPrintList = new ArrayList();
		StringBuffer hql = new StringBuffer("from EzXxdPrint as t where 1=1 ");
		StringBuffer sql = new StringBuffer(
				"select count(1) from ez_xxd_print t where 1=1 ");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		String printXh = request.getParameter("printXh");
		String sqly = request.getParameter("sqly");
		String sqrxm = request.getParameter("sqrxm");
		String sqlx = request.getParameter("sqlx");
		String shzt = request.getParameter("shzt");
		String sqrsfzmhm = request.getParameter("sqrsfzmhm");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE * (currentPage - 1);
		int size = 0;
		String curi = request.getRequestURI();
		if (s_date != null && e_date != null && !"".equals(s_date)
				&& !"".equals(e_date)) {
			hql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !"".equals(s_date)
				&& (e_date == null || "".equals(e_date))) {
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !"".equals(e_date)
				&& (s_date == null || "".equals(s_date))) {
			Date d = DateUtil.getAppointDate(-7);
			s_date = DateUtil.date2String(d);
			hql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
			// 开始日期与结束日期都会空，即第一次加载
		} else {
			s_date = "2015-08-17";
			e_date = DateUtil.date2String(new Date());
			hql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.sqsj between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		// 互联网ID
		if (printXh != null && !"".equals(printXh)) {
			hql.append(" and t.printXh = '" + printXh + "' ");
			sql.append(" and t.PRINT_XH = '" + printXh + "' ");
			request.setAttribute("printXh", printXh);
		}
		// 姓名
		if (sqrxm != null && !"".equals(sqrxm)) {
			hql.append(" and t.sqrxm = '" + sqrxm + "' ");
			sql.append(" and t.sqrxm= '" + sqrxm + "' ");
			request.setAttribute("sqrxm", sqrxm);
		}
		// 数据来源
		if (sqly != null && !"".equals(sqly)) {
			hql.append(" and t.sqly = '" + sqly + "' ");
			sql.append(" and t.sqly = '" + sqly + "' ");
			request.setAttribute("sqly", sqly);
		}
		// 身份证明号码
		if (sqrsfzmhm != null && !"".equals(sqrsfzmhm)) {
			hql.append(" and t.sqrsfzmhm = '" + sqrsfzmhm + "' ");
			sql.append(" and t.sqrsfzmhm = '" + sqrsfzmhm + "' ");
			request.setAttribute("sqrsfzmhm", sqrsfzmhm);
		}
		// 业务类型
		if (sqlx != null && !"".equals(sqlx)) {
			hql.append(" and t.sqlx = '" + sqlx + "' ");
			sql.append(" and t.sqlx = '" + sqlx + "' ");
			request.setAttribute("sqlx", sqlx);
		}

		// 处理状态
		if (shzt != null && !"".equals(shzt)) {
			hql.append(" and t.shzt = '" + shzt + "' ");
			sql.append(" and t.shzt = '" + shzt + "' ");
			request.setAttribute("shzt", shzt);
		}

		hql.append(" order by t.sqsj desc ");
		size = defaultDao.getRepositoryBySQLListSize(sql.toString());
		Map<String, String> sqlxList = getCodeJszYwlx("SQLX");
		Map<String, String> sqlyList = getCodeJszYwlx("YHLY");
		Map<String, String> shztList = getCodeJszYwlx("SHZTT");
		if (size > 0) {
			ezXxdPrintList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			for (int i = 0; i < ezXxdPrintList.size(); i++) {
				EzXxdPrint edlca = (EzXxdPrint) ezXxdPrintList.get(i);
				if (null != edlca) {
					String sqlxval = sqlxList.get(edlca.getSqlx());
					edlca.setSqlx(sqlxval);
					if ("0".equals(edlca.getShzt())) {
						edlca.setShzt("待审核（待分配）");
					} else if ("1".equals(edlca.getShzt())) {
						edlca.setShzt("待审核（已分配）");
					} else if ("2".equals(edlca.getShzt())) {
						edlca.setShzt("审核通过");
					} else if ("TB".equals(edlca.getShzt())) {
						edlca.setShzt("退办");
					}
				}
			}
		}

		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("sqlxList", sqlxList);
		request.setAttribute("shztList", shztList);
		request.setAttribute("sqlyList", sqlyList);
		request.setAttribute("map", map);
		request.setAttribute("ezXxdPrintList", ezXxdPrintList);
		return ezXxdPrintList;
	}

	@SuppressWarnings("unchecked")
	public List getTqprint(HttpServletRequest request, int currentPagent)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		List ezXxdPrintList = new ArrayList();
		List ezDrvList = new ArrayList();
		String sqlx = request.getParameter("sqlxtq");
		StringBuffer hql = new StringBuffer(
				"from EzXxdPrint as t where t.shzt='1' and t.pczh='"
						+ user.getName() + "' and 1=1 ");
		StringBuffer sql = new StringBuffer();
		System.out.println(user.getName());
		// 业务类型
		if (sqlx != null && !"".equals(sqlx)) {
			hql.append(" and t.sqlx = '" + sqlx + "' ");
			request.setAttribute("sqlxtq", sqlx);
		}

		int pageSize = 50;
		int offset = SysConst.PAGESIZE * (currentPagent - 1);
		String curi = request.getRequestURI();
		ezDrvList = defaultDao.getRepositories(hql.toString());
		int size = ezDrvList.size();
		if (ezDrvList.size() > 0) {
			String pch = "";
			for (int i = 0; i < ezDrvList.size(); i++) {
				EzXxdPrint edlca1 = (EzXxdPrint) ezDrvList.get(i);
				pch = edlca1.getPch();
			}
			hql = new StringBuffer("from EzXxdPrint as t where t.pczh='"
					+ user.getName() + "' and pch='" + pch
					+ "' and 1=1 order by shzt desc");
			// 业务类型
			if (sqlx != null && !"".equals(sqlx)) {
				hql.append(" and t.sqlx = '" + sqlx + "' ");
				request.setAttribute("sqlxtq", sqlx);
			}
			ezXxdPrintList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
			request.setAttribute("num", 3);
		} else {
			hql = new StringBuffer("from EzXxdPrint t where t.shzt='0' ");
			sql = new StringBuffer(
					"select count(1) from (select * from ez_xxd_print where shzt='0' order by SQSJ asc ) where rownum <=50");
			size = defaultDao.getRepositoryBySQLListSize(sql.toString());
			offset = SysConst.PAGESIZE * (currentPagent - 1);
			curi = request.getRequestURI();
			// 业务类型
			if (sqlx != null && !"".equals(sqlx)) {
				hql.append(" and t.sqlx = '" + sqlx + "' ");
				request.setAttribute("sqlxtq", sqlx);
			}
			hql.append(" order by t.sqsj asc ");
			ezXxdPrintList = this.defaultDao.findHQLByPage(hql.toString(),
					offset, pageSize);
		}
		Map<String, String> sqlxList = getCodeJszYwlx("SQLX");
		Map<String, String> sqlyList = getCodeJszYwlx("YHLY");
		Map<String, String> shztList = getCodeJszYwlx("SHZTT");
		if (ezXxdPrintList.size() > 0) {
			for (int i = 0; i < ezXxdPrintList.size(); i++) {
				EzXxdPrint edlca1 = (EzXxdPrint) ezXxdPrintList.get(i);
				String ywlxval = sqlxList.get(edlca1.getSqlx());
				edlca1.setYwlx(ywlxval);
				String zhclztval = shztList.get(edlca1.getShzt());
				System.out.println("============" + zhclztval);
				edlca1.setZhclzt(zhclztval);
				request.setAttribute("cspch", edlca1.getPch());
			}
		} else {
			request.setAttribute("num", 0);
		}
		request.setAttribute("csshrzhs", user.getName());
		Map map = new HashMap();
		map.put("uri", curi);
		map.put("pagesize", 50);
		map.put("rscount", size);
		map.put("currentpage", currentPagent);
		request.setAttribute("rscount", size);
		request.setAttribute("sqlyList", sqlyList);
		request.setAttribute("sqlxList", sqlxList);
		request.setAttribute("map", map);
		request.setAttribute("ezXxdPrintList", ezXxdPrintList);
		return ezXxdPrintList;
	}

	@SuppressWarnings("unchecked")
	public Integer updateTqprint(HttpServletRequest request, List list)
			throws Exception {
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String pch = "";
		String sql = "select EZ_XXD_PRINT_PCH.Nextval from dual";
		List cspch = defaultDao.findSQL(sql);
		Date cspcsj = new Date();
		if (null != cspch && cspch.size() > 0) {
			pch = cspch.get(0).toString();
		}
		if (list.size() > 0) {
			EzXxdPrint edlca = new EzXxdPrint();
			for (int i = 0; i < list.size(); i++) {
				edlca = (EzXxdPrint) list.get(i);
				edlca.setPch(pch);
				edlca.setPcsl(new BigDecimal(list.size()));
				edlca.setPcsj(new Date());
				edlca.setPczh(user.getName());
				edlca.setPcxm(user.getYgxm());
				edlca.setPcbm(user.getBmid());
				edlca.setPcbmmc(user.getBmmc());
				edlca.setPcip(com.ycszh.util.ToolsUtil.getIpAddr(request));
				edlca.setPcsj(cspcsj);
				edlca.setShzt("1");
				edlca.setSynFlag("UW");
				edlca.setTranFlag(null);
				defaultDao.updateRepository(edlca);
			}
			request.setAttribute("cspch", edlca.getPch());
			request.setAttribute("num", 1);
			return 0;

		}
		return 1;
	}

	@SuppressWarnings("unchecked")
	public EzXxdPrint getEzXxdPrintInfo(HttpServletRequest request,
			String printXh) throws Exception {
		EzXxdPrint edlca = new EzXxdPrint();
		if (null != printXh) {
			edlca = (EzXxdPrint) defaultDao.getRepository(printXh,
					EzXxdPrint.class);
			if (null != edlca) {
				request.setAttribute("ywlx", edlca.getSqlx());
				if ("1".equals(edlca.getSqlx())) {
					edlca.setSqlx("驾驶人信息单");
				} else if ("2".equals(edlca.getSqlx())) {
					edlca.setSqlx("机动车信息单");
				} else if ("3".equals(edlca.getSqlx())) {
					edlca.setSqlx("无车证明申请");
				} else if ("4".equals(edlca.getSqlx())) {
					edlca.setSqlx("驾驶人安全事故信息表");
				}
				edlca.setHpzl(getCodeVal("7", edlca.getHpzl()));
				Map<String, String> sqlyList = getCodeJszYwlx("YHLY");
				String sqlyval = sqlyList.get(edlca.getSqly());
				edlca.setSqly(sqlyval);
				Map<String, String> shztList = getCodeJszYwlx("SHZTT");
				String shztval = shztList.get(edlca.getShzt());
				edlca.setShzt(shztval);
			}

		}
		return edlca;
	}

	@SuppressWarnings("unchecked")
	public Integer updatePrintChan(HttpServletRequest request, String printXh,
			String shzt, String tbyy, String tpid) throws Exception {
		EzXxdPrint edlca = (EzXxdPrint) defaultDao.getRepository(printXh,
				EzXxdPrint.class);
		if (null != edlca) {
			User user = (User) request.getSession().getAttribute(
					SysConst.USERBEAN);
			edlca.setShzt(shzt);
			edlca.setTbyy(tbyy);
			edlca.setShip(com.ycszh.util.ToolsUtil.getIpAddr(request));
			edlca.setShsj(new Date());
			edlca.setShrzh(user.getName());
			edlca.setShrxm(user.getYgxm());
			edlca.setShrbm(user.getBmid());
			edlca.setShrbmKj(getDeptUpid(user.getBmid()));
			edlca.setShrbmmc(user.getBmmc());
			edlca.setSynFlag("UW");
			edlca.setTranFlag(null);
			edlca.setTranDate(null);
			edlca.setTpid(tpid);
			defaultDao.updateRepository(edlca);
			if ("TB".equals(shzt)) {
				StringBuffer sms = new StringBuffer();
				Map<String, String> sqlxList = getCodeJszYwlx("SQLX");
				String sqlxval = sqlxList.get(edlca.getSqlx());
				sms
						.append("<?xml version=\"1.0\"  encoding=\"utf-8\"?><request>");
				sms.append("<userZh>sfrz</userZh>");
				sms.append("<userMy>sfrz1234</userMy>");
				sms.append("<mobile>" + edlca.getLxdh() + "</mobile>");
				sms.append("<content>" + edlca.getSqrxm() + ",你好！您通过星级用户中心申请的"
						+ sqlxval + "业务已退办，退办原因为：" + tbyy + "！</content>");
				sms.append("</request>");
				String sql1 = "INSERT INTO XDXPT_SENDSMS (xh, ywlsh, ywlx, sjhm, sms, lrsj, fssj, zt) "
						+ " values(SEQ_XDXPT_SENDSMS.Nextval, '"
						+ printXh
						+ "', 'JSZ("
						+ edlca.getSqlx()
						+ ")', '"
						+ edlca.getLxdh()
						+ "', '"
						+ sms
						+ "', sysdate, null, '0')";
				defaultDao.updateRepositoryBySql(sql1);
			}
			return 0;
		}
		return 1;
	}

	public EzXxdPrintPhoto editPrintPhoto(HttpServletRequest request,
			EzXxdPrintPhoto printPhoto) throws Exception {
		printPhoto.setLrsj(new Date());
		printPhoto.setSynFlag("UW");
		EzXxdPrintPhoto photo = (EzXxdPrintPhoto) this.defaultDao
				.addRepository(printPhoto);
		return photo;
	}

	public int editPhoto(EzXxdPrintPhoto printPhoto, File file)
			throws Exception {
		return this.fileimageDao.uploadPrintphoto(file, printPhoto);
	}

	@SuppressWarnings("unchecked")
	public String getTpid() throws Exception {
		String id = "";
		String sql = "select EZ_XXD_PRINT_PHOTO_ID.Nextval from dual";
		List cspch = defaultDao.findSQL(sql);
		if (null != cspch && cspch.size() > 0) {
			id = cspch.get(0).toString();
		}
		return id;
	}

	public byte[] getImageBlob(HttpServletRequest request, String tpid)
			throws Exception {
		byte[] b = null;
		b = this.fileimageDao.getImageBlob(tpid);
		return b;
	}

	public int editeBlobByByte(byte[] arry_byte, EzXxdPrintPhoto printPhoto)
			throws Exception {
		return this.fileimageDao.editeBlobByByte(printPhoto, arry_byte);
	}

	/**
	 * 机动车监管业务查询
	 */
	@SuppressWarnings("unchecked")
	public List getVehBusinessSuperviseData(VehSuperviseForm form)
			throws Exception {
		StringBuilder sbBody = new StringBuilder();
		sbBody.append("select lsh, xh, ywlx, ywyy, syr, cast(hpzl as varchar2(5)) hpzl, hphm, clpp1, clxh, cast(cllx as varchar2(5)) cllx, xzqh, sqrq, bjrq, xygw, ywlc, lszt, glbm, fpbj, ffbj, rkbj, clsbdh from es_veh_flow t1 where t1.glbm='440300000418'");
		
		StringBuilder sbCount = new StringBuilder();
		sbCount.append("select count(0) from es_veh_flow t1 where t1.glbm='440300000418'");
		
		StringBuilder sbWhere = new StringBuilder();
		
		if (form.getStartDt()== null || "".equals(form.getStartDt())) {
			return null;
		}
		if (form.getEndDt()== null || "".equals(form.getEndDt())) {
			return null;
		}

		if (form.getYwlx() != null && !"".equals(form.getYwlx())) {
			if (form.getYwlx().length() > 1) {
				form.setYwyy(form.getYwlx().substring(2, 3));
				sbWhere.append(" and t1.ywlx='" + form.getYwlx().substring(0, 1)
						+ "'");
				sbWhere.append(" and t1.ywyy='" + form.getYwyy() + "'");
			} else {
				sbWhere.append(" and t1.ywlx='" + form.getYwlx() + "'");
			}
			//E 抵押登记 
			if(form.getYwlx().equals("E")){
				sbWhere.append(" and not exists(select 1 " +
						                        " from dydj_ywsbspb_in t2 " +
						                       " where t2.lsh=t1.lsh " +
						                         " and t2.lrsj>= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
				                                 " and t2.lrsj<= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) ");
			}
			//END
			
			//Q:A 异地委托检验
			if(form.getYwlx().equals("Q:A")){
				sbWhere.append("and not exists (select 1 "+					
                                                " from wtydns_ywsbspb_in t2 "+
                                               " where substr(t2.hphm, 2) = t1.hphm "+
                                                 " and t2.hpzl = t1.hpzl "+
                                                 " and t2.lrsj>= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
				                                 " and t2.lrsj<= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) ");
			}
			//END
			
			//K:B 补领行驶证
			if(form.getYwlx().equals("K:B")){
				sbWhere.append(" and not exists (select 1 "+
                                                 " from (select wwlsh "+
                                                               " from xwebfile.t_jdc_chan_app "+
                                                              " where ywlx = '1' "+
                                                                " and wwlrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                                                " and wwlrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd') "+
                                                          " union all "+
                                                       " select wwlsh "+
                                                               " from ez_jdc_chan_app "+
                                                              " where ywlx = '1' "+
                                                                " and wwlrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                                                " and wwlrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) t2" +
                                                " where t2.wwlsh=t1.lsh)");
				
			}
			//END
			
			//K:H 补领检验合格证
			if(form.getYwlx().equals("K:H")){
				sbWhere.append(" and not exists (select 1 "+
                                                 " from (select wwlsh "+
                                                               " from xwebfile.t_jdc_chan_app "+
                                                              " where ywlx = '2' "+
                                                                " and wwlrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                                                " and wwlrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd') "+
                                                          " union all "+
                                                       " select wwlsh "+
                                                               " from ez_jdc_chan_app "+
                                                              " where ywlx = '2' "+
                                                                " and wwlrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                                                " and wwlrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) t2" +
                                                " where t2.wwlsh=t1.lsh)");
				
			}
			//END
			
			//O 临牌业务
			if(form.getYwlx().equals("O")){
				sbWhere.append(" and not exists (select 1 "+					
                                                 " from ez_jdc_chan_app t2 "+
                                                " where t2.wwlsh = t1.lsh "+
                                                  " and t2.ywlx='4' "+
                                                  " and t2.wwlrsj>= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
 				                                  " and t2.wwlrsj<= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) ");
			}
			//END
			
			//P:F 核发检验标志
			//if(form.getYwlx().equals("P:F")){
			//	//todo:
			//}
		    //END
		}

	
	    sbWhere.append(" and t1.sqrq>=to_date('" + form.getStartDt()
					+ "','yyyy-mm-dd')");
		
		sbWhere.append(" and t1.sqrq<=to_date('" + form.getEndDt()
					+ "','yyyy-mm-dd')");
		
		if (form.getHphm() != null & !"".equals(form.getHphm())) {
			
			sbWhere.append(" and t1.hphm='" + form.getHphm().replace("粤", "") + "'");
		}
		if (form.getHpzl() != null & !"".equals(form.getHpzl())) {
			sbWhere.append(" and t1.hpzl='" + form.getHpzl() + "'");
		}

		
		
		sbWhere.append(" order by bjrq desc ");

		sbCount.append(sbWhere.toString());
		sbBody.append(sbWhere.toString());
		System.out.println(sbBody.toString());

		form.setPageCount(this.defaultDao.getRepositoryBySQLListSize(sbCount
				.toString()));

		return this.defaultDao.findSQLByPage(sbBody.toString(), form
				.getPageSize()
				* (form.getCurrentPage() - 1), form.getPageSize());

	}

	@SuppressWarnings("unchecked")
	public List getDrvBusinessSuperviseData(DrvSuperviseForm form)
			throws Exception {
		StringBuilder sbBody = new StringBuilder();
		sbBody.append("select lsh, sfzmhm, cast(dabh as varchar2(12)) dabh, xm,  ywlx, ywyy, kssj, jssj, ywgw, cast(kskm as varchar2(6)) kskm, xygw, glbm, ffbz, rkbz, hdbz, xgzl, zjcx, ywblbm  from es_drv_flow t1 where t1.glbm='440300000418'");
		
		StringBuilder sbCount = new StringBuilder();
		sbCount.append("select count(0) from es_drv_flow t1 where t1.glbm='440300000418'");
		
		StringBuilder sbWhere = new StringBuilder();

		if (form.getYwlx() != null && !"".equals(form.getYwlx())) {
			if (form.getYwlx().length() > 1) {
				form.setYwyy(form.getYwlx().substring(2, 3));
				sbWhere.append(" and t1.ywlx='" + form.getYwlx().substring(0, 1)
						+ "'");
				sbWhere.append(" and t1.ywyy='" + form.getYwyy() + "'");
			} else {
				sbWhere.append(" and t1.ywlx='" + form.getYwlx() + "'");
			}
		}
		if (form.getStartDt()== null || "".equals(form.getStartDt())) {
			return null;
		}
		if (form.getEndDt()== null || "".equals(form.getEndDt())) {
			return null;
		}
		sbWhere.append(" and not exists ( select 1"+
                                          " from ez_drv_lice_chan_app t2"+
                                         " where t2.wwlsh = t1.lsh"+
                                           " and (t2.hblx = 'H' or t2.hblx = 'B' or t2.hblx = 'Y')"+
                                           " and t2.wslrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                           " and t2.wslrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) "+
                       " and not exists ( select 1"+
                                          " from xwebfile.t_drv_lice_chan_app t3"+
                                         " where t3.wwlsh = t1.lsh"+
                                           " and (t3.hblx = 'H' or t3.hblx = 'B')"+
                                           " and t3.wslrsj >= to_date('" + form.getStartDt()+ "','yyyy-mm-dd')-90 "+
                                           " and t3.wslrsj <= to_date('" + form.getEndDt()+ "','yyyy-mm-dd')) ");

		
	    sbWhere.append(" and t1.kssj>=to_date('" + form.getStartDt()
					+ "','yyyy-mm-dd')");
		
	    sbWhere.append(" and t1.kssj<=to_date('" + form.getEndDt()
					+ "','yyyy-mm-dd')");
	
		              
		
		if (form.getJszhm() != null & !"".equals(form.getJszhm())) {
			sbWhere.append(" and t1.sfzmhm='" + form.getJszhm() + "'");
		}
		
		
		sbCount.append(sbWhere.toString());
		sbWhere.append(" order by kssj desc ");
		sbBody.append(sbWhere.toString());
		
		//System.out.println(sbBody.toString());

		form.setPageCount(this.defaultDao.getRepositoryBySQLListSize(sbCount
				.toString()));

		return this.defaultDao.findSQLByPage(sbBody.toString(), form
				.getPageSize()
				* (form.getCurrentPage() - 1), form.getPageSize());
	}

}
