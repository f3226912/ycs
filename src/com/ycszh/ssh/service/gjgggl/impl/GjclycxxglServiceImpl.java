package com.ycszh.ssh.service.gjgggl.impl;

import java.net.URLDecoder;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.map.LinkedMap;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.gjgggl.BusBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsbLog;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsbPhoto;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import com.ycszh.ssh.service.gjgggl.GjclycxxglService;
import com.ycszh.util.ToolsUtil;

public class GjclycxxglServiceImpl implements GjclycxxglService {

	private GjbaxxspService gjbaxxspService;
	private DefaultDao defaultDao;

	public void getBusGgjgclsbTotalList(HttpServletRequest request) throws Exception {

		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		// 预约批次号
		String yypch = checkStrNullAndReturn(request.getParameter("yypch"));
		// 权限(kj科级 cj处级)
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 历史批次号
		String lspch = checkStrNullAndReturn(request.getParameter("lspch"));

		// 验车部门
		Map<String, String> ycglbmMap = new HashMap<String, String>();
		// 已审核未办结申报数据
		Map<String, String> yshwbjMap = new LinkedHashMap<String, String>();

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// group by
		List<BusGgjgclsb> busGgjgclsbTotal = new ArrayList<BusGgjgclsb>();
		// 每个流水
		List<BusGgjgclsb> busGgjgclsbsSingle = new ArrayList<BusGgjgclsb>();

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			// 查历史审核数据(内网已审核,但未办结或退办的数据)
			StringBuffer sql_yshwbj = new StringBuffer();
			sql_yshwbj.append(" select a.yypch, a.ggjgid, a.ggjgmc, a.yyycbm, a.yyycbmmc, a.yypcsl from BUS_GGJGCLSB a ");
			sql_yshwbj.append(" where ");
			sql_yshwbj.append(" exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt  ='2' ) ");
			sql_yshwbj.append(" and not exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt in ('0','1')) ");

			String hql_total = " select a.yypch,a.ggjgid,a.ggjgmc,a.yyycbm,a.yyycbmmc,a.yypcsl from BUS_GGJGCLSB a  ";
			hql_total += " where exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt in ('1') ) ";

			if (qx.equals("kj")) {
				/*
				 * System.out.println("*************************************");
				 * System.out.println("userinfo:" + user.getId());
				 * System.out.println("userinfo:" + user.getName());
				 * System.out.println("userinfo:" + user.getYgid());
				 * System.out.println("userinfo:" + user.getYgxm());
				 * System.out.println("userinfo:" + user.getBmid());
				 * System.out.println("userinfo:" + user.getBmmc());
				 * System.out.println("*************************************");
				 */

				String[] currentKjBm = getCurrentUserKjBm(checkStrNullAndReturn(user.getBmid()));
				yyycbm = currentKjBm[0];
				hql_total += " and a.yyycbm='" + yyycbm + "' ";
				sql_yshwbj.append(" and a.yyycbm='" + yyycbm + "' ");

			} else {
				if (!yyycbm.equals("")) {
					hql_total += " and a.yyycbm='" + yyycbm + "' ";
				}
			}

			if (!lspch.equals("")) {
				hql_total += " and a.yypch='" + lspch + "' ";
				sql_yshwbj.append(" and a.yypch='" + lspch + "' ");
			} else {
				if (!yypch.equals("")) {
					hql_total += " and a.yypch='" + yypch + "' ";
				}
			}

			hql_total += " group by a.yypch,a.yypcsl,a.ggjgid,a.ggjgmc,a.yyycbm,a.yyycbmmc having a.yypcsl = count(1) order by a.yypch desc ";
			sql_yshwbj.append(" group by a.yypch, a.yypcsl, a.ggjgid, a.ggjgmc, a.yyycbm, a.yyycbmmc having a.yypcsl = count(1) order by a.yypch desc ");

			List<Object> tempList_Total = new ArrayList<Object>();
			if (lspch.equals("")) {
				tempList_Total = defaultDao.findSQLByPage(hql_total, offset, pageSize);
			} else {
				tempList_Total = defaultDao.findSQLByPage(sql_yshwbj.toString(), offset, pageSize);
			}

			String sql_count = " select count(*) from ( " + hql_total + " ) ";

			rscount = defaultDao.getRepositoryBySQLListSize(sql_count);

			yshwbjMap = getBusGgjgclsbTotalList_yshwbj(request);

			if (tempList_Total != null && tempList_Total.size() > 0) {

				Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);

				// 流水状态(包括：0未审核 1审核通过 2验车合格 3验车不合格 E办结，已发证 Q退办)
				Map<String, String> lsztMap = getBusSzjdMap(null, "LSZT", null);

				// 批次号集合
				StringBuffer pch = new StringBuffer();
				pch.append(" (");
				for (int x = 0; x < tempList_Total.size(); x++) {
					Object[] obj = (Object[]) tempList_Total.get(x);
					BusGgjgclsb busGgjgclsb = new BusGgjgclsb();

					busGgjgclsb.setYypch(checkStrNullAndReturn(obj[0]));
					busGgjgclsb.setGgjgid(checkStrNullAndReturn(obj[1]));
					busGgjgclsb.setGgjgmc(checkStrNullAndReturn(obj[2]));
					busGgjgclsb.setYyycbm(checkStrNullAndReturn(obj[3]));
					busGgjgclsb.setYyycbmmc(checkStrNullAndReturn(obj[4]));
					long yypcsl = Long.parseLong(obj[5] == null ? "0" : obj[5].toString().trim());
					busGgjgclsb.setYypcsl(yypcsl);

					busGgjgclsbTotal.add(busGgjgclsb);
					if (x != tempList_Total.size() - 1) {
						pch.append(" '" + busGgjgclsb.getYypch() + "', ");
					} else {
						pch.append(" '" + busGgjgclsb.getYypch() + "' ");
					}

				}
				pch.append(") ");

				String hql_single = " from BusGgjgclsb a where a.yypch in " + pch.toString() + " order by a.yypch desc,a.lsh desc ";
				List<Object> tempList_single = defaultDao.getRepositories(hql_single);
				for (Object obj : tempList_single) {
					if (obj != null) {
						BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
						busGgjgclsbsSingle.add(busGgjgclsb);
					}
				}

				long yyshsl = 0;
				for (BusGgjgclsb outside : busGgjgclsbTotal) {
					yyshsl = 0;
					String outPch = checkStrNullAndReturn(outside.getYypch());

					// 统计已审核数量(包括：0待车管部门审核 1审核通过 2审核成功 E办结，已发证 CQ退办)
					for (BusGgjgclsb inside : busGgjgclsbsSingle) {
						String inPch = checkStrNullAndReturn(inside.getYypch());
						String inZt = checkStrNullAndReturn(inside.getZt());
						// 已审核数量
						if (inPch.equals(outPch) && !inZt.equals("0") && !inZt.equals("1")) {
							yyshsl++;
						}
					}
					outside.setYyshsl(yyshsl);
				}

				for (BusGgjgclsb busGgjgclsb : busGgjgclsbsSingle) {
					busGgjgclsb.setHpzl(hpzlMap.get(busGgjgclsb.getHpzl()));
					// 流水状态
					busGgjgclsb.setZtMc(lsztMap.get(busGgjgclsb.getZt()));
				}

			}

		}

		if (qx.equals("kj")) {
			// 验车部门
			ycglbmMap = getBusSzjdMap(yyycbm, "YYBM", null);
			// ycglbmMap = getBmMap(yyycbm, null, false);
		} else {
			// 验车部门
			ycglbmMap = getBusSzjdMap(null, "YYBM", null);
			// ycglbmMap = getBmMap(null, null, false);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pageSize", pageSize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("qx", qx);
		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("yshwbjMap", yshwbjMap);
		request.setAttribute("yypch", yypch);
		request.setAttribute("lspch", lspch);
		request.setAttribute("ycglbmMap", ycglbmMap);

		request.setAttribute("busGgjgclsbTotal", busGgjgclsbTotal);
		request.setAttribute("busGgjgclsbsSingle", busGgjgclsbsSingle);

	}

	/**
	 * 获取已审核但未办结或未退办的数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGgjgclsbTotalList_yshwbj(HttpServletRequest request) throws Exception {

		// 权限(kj科级 cj处级)
		String qx = checkStrNullAndReturn(request.getParameter("qx"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		Map<String, String> yshwbjMap = new LinkedHashMap<String, String>();

		StringBuffer sql_yshwbj = new StringBuffer();
		sql_yshwbj.append(" select a.yypch, a.ggjgid, a.ggjgmc, a.yyycbm, a.yyycbmmc, a.yypcsl from BUS_GGJGCLSB a ");
		sql_yshwbj.append(" where ");
		sql_yshwbj.append(" exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt  ='2' ) ");
		sql_yshwbj.append(" and not exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt in ('0','1')) ");

		if (qx.equals("kj")) {

			String yyycbm = checkStrNullAndReturn(user.getBmid());
			String[] tempYyycbm = getCurrentUserKjBm(yyycbm);
			yyycbm = tempYyycbm[0];
			sql_yshwbj.append(" and a.yyycbm='" + tempYyycbm[0] + "' ");
		}
		sql_yshwbj.append(" group by a.yypch, a.yypcsl, a.ggjgid, a.ggjgmc, a.yyycbm, a.yyycbmmc having a.yypcsl = count(1) order by a.yypch desc ");

		List<Object> tempList = defaultDao.findSQL(sql_yshwbj.toString());
		for (Object obj : tempList) {
			if (obj != null) {
				Object[] tempObj = (Object[]) obj;
				String yypch = checkStrNullAndReturn(tempObj[0]);
				String yyycbm = checkStrNullAndReturn(tempObj[1]);
				if (!yypch.equals("")) {
					yshwbjMap.put(yypch, yyycbm);
				}
			}
		}

		return yshwbjMap;
	}

	/**
	 * 按批次号查询广告喷涂业务申报集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void getBusGgjgclsbByPch(HttpServletRequest request) throws Exception {

		// 预约批次号
		String yypch = checkStrNullAndReturn(request.getParameter("yypch"));
		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 每个流水
		List<BusGgjgclsb> busGgjgclsbsSingle = new ArrayList<BusGgjgclsb>();
		// 页面展示的数据
		List<BusGgjgclsb> realbusGgjgclsbs = new ArrayList<BusGgjgclsb>();

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);

			// 流水状态(包括：0未审核 1审核通过 2审核成功 E办结，已发证 CQ退办)
			Map<String, String> lsztMap = getBusSzjdMap(null, "LSZT", null);

			String hql = " from BusGgjgclsb a where a.yypch = '" + yypch + "' order by a.lsh desc ";
			String hql_count = " select count(*) " + hql;

			List<Object> tempList_single = defaultDao.findHQLByPage(hql, offset, pageSize);
			rscount = defaultDao.getRepositoryByHQLListSize(hql_count);

			Map<String, String> gjgsMap = getGjgsMap("", "");

			for (Object obj : tempList_single) {
				if (obj != null) {
					BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
					// 翻译公交公司名称
					if (gjgsMap != null) {
						busGgjgclsb.setGjgsid(checkStrNullAndReturn(gjgsMap.get(busGgjgclsb.getGjgsid())));
					}
					busGgjgclsbsSingle.add(busGgjgclsb);
				}
			}

			for (BusGgjgclsb busGgjgclsb : busGgjgclsbsSingle) {
				busGgjgclsb.setHpzl(hpzlMap.get(busGgjgclsb.getHpzl()));
				if (lsztMap != null) {
					// 翻译流水状态名称
					busGgjgclsb.setZtMc(checkStrNullAndReturn(lsztMap.get(busGgjgclsb.getZt())));
				}
			}

			// 把外网退办的放到最后位置
			for (BusGgjgclsb obj : busGgjgclsbsSingle) {
				String zt = checkStrNullAndReturn(obj.getZt());
				if (!zt.equals("Q")) {
					realbusGgjgclsbs.add(obj);
				}
			}

			for (BusGgjgclsb obj : busGgjgclsbsSingle) {
				String zt = checkStrNullAndReturn(obj.getZt());
				if (zt.equals("Q")) {
					realbusGgjgclsbs.add(obj);
				}
			}

		}

		// 验车部门
		Map<String, String> ycglbmMap = getBusSzjdMap(yyycbm, "YYBM", null);
		// Map<String, String> ycglbmMap = getBmMap(yyycbm, null, false);
		// 验车部门名称
		String yyycbmmc = ycglbmMap.get(yyycbm);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pageSize", pageSize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("yypch", yypch);
		request.setAttribute("yyycbmmc", yyycbmmc);

		request.setAttribute("busGgjgclsbsSingle", realbusGgjgclsbs);

	}

	/*
	 * 获取公交公司集合
	 */
	public Map<String, String> getGjgsMap(String gjgsid, String gjgsmc) throws Exception {

		Map<String, String> gjgsMap = new HashMap<String, String>();

		String hql = " from  BusBase  a where 1=1 ";
		if (!checkStrNullAndReturn(gjgsid).equals("")) {
			hql += " a.gjgsid='" + gjgsid + "' ";
		}
		if (!checkStrNullAndReturn(gjgsmc).equals("")) {
			hql += " a.gjgsmc like '%" + gjgsmc + "%' ";
		}
		List<Object> tempList = defaultDao.getRepositories(hql);
		if (tempList != null && tempList.size() > 0) {
			for (Object object : tempList) {
				if (object != null) {
					BusBase tempBase = (BusBase) object;
					String gjgsidStr = checkStrNullAndReturn(tempBase.getGjgsid());
					String gjgsmcStr = checkStrNullAndReturn(tempBase.getGjgsmc());
					gjgsMap.put(gjgsidStr, gjgsmcStr);
				}
			}
		}

		return gjgsMap;
	}

	/**
	 * 按批次号打印数据
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByPch_dy(HttpServletRequest request) throws Exception {

		// 预约批次号
		String yypch = checkStrNullAndReturn(request.getParameter("yypch"));
		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 每个流水
		List<BusGgjgclsb> busGgjgclsbsSingle = new ArrayList<BusGgjgclsb>();

		if (user != null) {

			Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);

			// 流水状态(包括：0未审核 1审核通过 2验车合格 3验车不合格 E办结，已发证 Q退办)
			Map<String, String> lsztMap = getBusSzjdMap(null, "LSZT", null);

			String hql = " from BusGgjgclsb a where a.yypch = '" + yypch + "' order by a.lsh desc ";

			List<Object> tempList_single = defaultDao.getRepositories(hql);

			for (Object obj : tempList_single) {
				if (obj != null) {
					BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
					busGgjgclsbsSingle.add(busGgjgclsb);
				}
			}

			for (BusGgjgclsb busGgjgclsb : busGgjgclsbsSingle) {
				busGgjgclsb.setHpzl(hpzlMap.get(busGgjgclsb.getHpzl()));
				busGgjgclsb.setZtMc(lsztMap.get(busGgjgclsb.getZt()));
			}

		}

		// 验车部门
		Map<String, String> ycglbmMap = getBusSzjdMap(yyycbm, "YYBM", null);
		// Map<String, String> ycglbmMap = getBmMap(yyycbm, null, false);
		// 验车部门名称
		String yyycbmmc = ycglbmMap.get(yyycbm);

		request.setAttribute("yypch", yypch);
		request.setAttribute("yyycbmmc", yyycbmmc);

		request.setAttribute("busGgjgclsbsSingle", busGgjgclsbsSingle);

	}

	public void getBusGgjgclsbTotalList_fp(HttpServletRequest request) throws Exception {

		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		// 广告机构id
		String ggjgid = checkStrNullAndReturn(request.getParameter("ggjgid"));
		// 预约批次号
		String yypch = checkStrNullAndReturn(request.getParameter("yypch"));

		String hphm = checkStrNullAndReturn(request.getParameter("hphm"));

		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// group by
		List<BusGgjgclsb> busGgjgclsbTotal = new ArrayList<BusGgjgclsb>();
		List<BusGgjgclsb> realBusGgjgclsbTotal = new ArrayList<BusGgjgclsb>();

		// 每个流水
		List<BusGgjgclsb> busGgjgclsbsSingle = new ArrayList<BusGgjgclsb>();

		// 分页参数
		int currentPage = new Integer(request.getParameter("currentPage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pageSize = SysConst.PAGESIZE;
		int offset = pageSize * (currentPage - 1);
		int rscount = 0;

		if (user != null) {

			// 全部同步,而且批次中的不存在已审核,已办结,退办的流水数据
			String hql_total = " select a.yypch,a.ggjgid,a.ggjgmc,a.yyycbm,a.yyycbmmc,a.yypcsl from BUS_GGJGCLSB a  ";

			hql_total += " where not exists (select 1 from BUS_GGJGCLSB b where a.yypch = b.yypch and b.zt in ('2','E','CQ','Q') ) ";

			if (!ggjgid.equals("")) {
				hql_total += "  and a.ggjgid='" + ggjgid + "' ";
			}

			hql_total += " group by a.yypch,a.yypcsl,a.ggjgid,a.ggjgmc,a.yyycbm,a.yyycbmmc having a.yypcsl = count(1) ";

			if (qx.equals("kj")) {
				yyycbm = user.getBmid();
				hql_total += " and a.yyycbm='" + yyycbm + "' ";
			} else {
				if (!yyycbm.equals("")) {
					hql_total += " and a.yyycbm='" + yyycbm + "' ";
				}
			}

			if (!yypch.equals("")) {
				hql_total += " and a.yypch='" + yypch + "' ";
			}

			List<Object> tempList_Total = defaultDao.findSQLByPage(hql_total, offset, pageSize);

			if (tempList_Total != null && tempList_Total.size() > 0) {

				Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);

				// 流水状态(包括：0未审核 1审核通过 E办结，已发证 Q退办)
				Map<String, String> lsztMap = getBusSzjdMap(null, "LSZT", null);

				// 批次号集合
				StringBuffer pch = new StringBuffer();
				pch.append(" (");
				for (int x = 0; x < tempList_Total.size(); x++) {
					Object[] obj = (Object[]) tempList_Total.get(x);
					BusGgjgclsb busGgjgclsb = new BusGgjgclsb();

					busGgjgclsb.setYypch(checkStrNullAndReturn(obj[0]));
					busGgjgclsb.setGgjgid(checkStrNullAndReturn(obj[1]));
					busGgjgclsb.setGgjgmc(checkStrNullAndReturn(obj[2]));
					busGgjgclsb.setYyycbm(checkStrNullAndReturn(obj[3]));
					busGgjgclsb.setYyycbmmc(checkStrNullAndReturn(obj[4]));
					long yypcsl = Long.parseLong(obj[5] == null ? "0" : obj[5].toString().trim());
					busGgjgclsb.setYypcsl(yypcsl);

					busGgjgclsbTotal.add(busGgjgclsb);
					if (x != tempList_Total.size() - 1) {
						pch.append(" '" + busGgjgclsb.getYypch() + "', ");
					} else {
						pch.append(" '" + busGgjgclsb.getYypch() + "' ");
					}

				}
				pch.append(") ");

				List<Object> tempList_single = new ArrayList<Object>();
				String hql_single = " from BusGgjgclsb a where a.yypch in " + pch.toString() + "  ";

				if (!hphm.equals("")) {
					hql_single += " and a.hphm='" + hphm + "' ";
					for (BusGgjgclsb tempBusGgjgclsb : busGgjgclsbTotal) {
						String tempPch = checkStrNullAndReturn(tempBusGgjgclsb.getYypch());
						String hql = " from BusGgjgclsb a where a.hphm='" + hphm + "' and a.yypch='" + tempPch + "' ";
						List<Object> tempList = defaultDao.getRepositories(hql);
						if (tempList != null && tempList.size() > 0) {
							tempList_single = tempList;
							realBusGgjgclsbTotal.add(tempBusGgjgclsb);
							break;
						}
					}
				} else {
					realBusGgjgclsbTotal = busGgjgclsbTotal;
				}
				hql_single += " order by a.yypch desc,a.lsh desc ";
				tempList_single = defaultDao.getRepositories(hql_single);

				for (Object obj : tempList_single) {
					if (obj != null) {
						BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
						busGgjgclsbsSingle.add(busGgjgclsb);
					}
				}

				// 统计已审核数量(包括：2审核成功 E办结，已发证 CQ退办)
				long yyshsl = 0;
				for (BusGgjgclsb outside : busGgjgclsbTotal) {
					yyshsl = 0;
					String outPch = checkStrNullAndReturn(outside.getYypch());

					for (BusGgjgclsb inside : busGgjgclsbsSingle) {
						String inPch = checkStrNullAndReturn(inside.getYypch());
						String inZt = checkStrNullAndReturn(inside.getZt());
						// 已审核数量
						if (inPch.equals(outPch) && !inZt.equals("0") && !inZt.equals("1")) {
							yyshsl++;
						}
					}
					outside.setYyshsl(yyshsl);
				}

				for (BusGgjgclsb busGgjgclsb : busGgjgclsbsSingle) {
					busGgjgclsb.setHpzl(hpzlMap.get(busGgjgclsb.getHpzl()));
					busGgjgclsb.setZtMc(lsztMap.get(busGgjgclsb.getZt()));
				}

			}

		}

		// 验车部门
		Map<String, String> ycglbmMap = getBusSzjdMap(null, "YYBM", null);
		// Map<String, String> ycglbmMap = getBmMap(null, null, false);
		// 广告机构
		Map<String, String> ggjgMap = getGgjgMap(null, null);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentPage", currentPage);
		map.put("uri", uri);
		map.put("pageSize", pageSize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("ggjgid", ggjgid);
		request.setAttribute("yypch", yypch);
		request.setAttribute("hphm", hphm);
		request.setAttribute("qx", qx);
		request.setAttribute("ycglbmMap", ycglbmMap);
		request.setAttribute("ggjgMap", ggjgMap);

		request.setAttribute("busGgjgclsbTotal", realBusGgjgclsbTotal);
		request.setAttribute("busGgjgclsbsSingle", busGgjgclsbsSingle);

	}

	public void getBusGgjgclsbTotalList_fp_pch(HttpServletRequest request) throws Exception {

		// 预约批次号
		String yypch = checkStrNullAndReturn(request.getParameter("yypch"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 每个流水
		List<BusGgjgclsb> busGgjgclsbsSingle = new ArrayList<BusGgjgclsb>();

		// 分页参数
		/*
		 * int currentPage = new Integer(request.getParameter("currentPage") ==
		 * null ? "1" : request.getParameter("currentpage")); String uri =
		 * request.getRequestURI(); int pageSize = SysConst.PAGESIZE; int offset
		 * = pageSize * (currentPage - 1); int rscount = 0;
		 */

		if (user != null) {

			Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);

			// 流水状态(包括：0未审核 1审核通过 2审核通过 E办结，已发证 CQ退办)
			Map<String, String> lsztMap = getBusSzjdMap(null, "LSZT", null);

			List<Object> tempList_single = new ArrayList<Object>();
			String hql = " from BusGgjgclsb a where a.yypch = '" + yypch + "' order by a.lsh desc ";

			// tempList_single = defaultDao.findHQLByPage(hql, offset,
			// pageSize);
			tempList_single = defaultDao.getRepositories(hql);

			for (Object obj : tempList_single) {
				if (obj != null) {
					BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
					busGgjgclsbsSingle.add(busGgjgclsb);
				}
			}

			for (BusGgjgclsb busGgjgclsb : busGgjgclsbsSingle) {
				busGgjgclsb.setHpzl(hpzlMap.get(busGgjgclsb.getHpzl()));
				// 流水状态
				busGgjgclsb.setZtMc(lsztMap.get(busGgjgclsb.getZt()));
			}

		}

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("currentPage", currentPage); map.put("uri", uri);
		 * map.put("pageSize", pageSize); map.put("rscount", rscount);
		 * request.setAttribute("map", map);
		 */

		request.setAttribute("yypch", yypch);

		request.setAttribute("busGgjgclsbsSingle", busGgjgclsbsSingle);

	}

	public Map<String, String> getGgjgMap(String gggjid, String ggjgName) throws Exception {

		Map<String, String> ggjgMap = new HashMap<String, String>();

		String hql = " from BusGgjgBase a where 1=1 ";

		if (gggjid != null && !gggjid.trim().equals("")) {
			hql += " and a.ggjgid='" + gggjid + "' ";
		}

		if (ggjgName != null && !ggjgName.trim().equals("")) {
			hql += " and a.ggjgmc='" + ggjgName + "' ";
		}

		List<Object> tempList = defaultDao.getRepositories(hql);
		for (Object obj : tempList) {
			BusGgjgBase busGgjgBase = (BusGgjgBase) obj;
			ggjgMap.put(busGgjgBase.getGgjgid(), busGgjgBase.getGgjgmc());
		}

		return ggjgMap;

	}

	/**
	 * 查询数字字典
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusSzjdMap(String dmz, String dmlb, String notDmz) throws Exception {

		Map<String, String> resultMap = new LinkedMap();

		String sql = " select a.dmz,a.dmms1 from BUS_SJZD a where a.dmlb='" + dmlb + "' ";

		if (dmz != null && !dmz.trim().equals("")) {
			sql += " and a.dmz='" + dmz + "'  ";
		}

		if (notDmz != null && !notDmz.equals("")) {
			sql += " and a.dmz !='" + notDmz + "'  ";
		}

		sql += " order by a.dmms1 asc ";

		List<Object> tempList = defaultDao.findSQL(sql);
		if (tempList != null && tempList.size() > 0) {
			for (Object obj : tempList) {
				if (obj != null) {
					Object[] tempObj = (Object[]) obj;
					resultMap.put(checkStrNullAndReturn(tempObj[0]), checkStrNullAndReturn(tempObj[1]));
				}
			}
		}
		return resultMap;
	}

	public Map<String, String> getBmMap(String orgId, String notOrgId, boolean isAllBm) throws Exception {

		Map<String, String> resultMap = new LinkedMap();

		String sql = " select a.ORG_Id,a.Org_Name from v_veh_org_ycs a where 1=1 ";

		if (!isAllBm) {
			sql += " and a.Up_Org='C34702A8FED97CBFE040007F0100339B' ";
		}

		if (orgId != null && !orgId.equals("")) {
			sql += " and a.ORG_Id ='" + orgId + "'  ";
		}

		if (notOrgId != null && !notOrgId.equals("")) {
			sql += " and a.ORG_Id !='" + notOrgId + "'  ";
		}

		sql += " order by Org_Name asc ";

		List<Object> tempList = defaultDao.findSQL(sql);
		if (tempList != null && tempList.size() > 0) {
			for (Object obj : tempList) {
				if (obj != null) {
					Object[] tempObj = (Object[]) obj;
					resultMap.put(checkStrNullAndReturn(tempObj[0]), checkStrNullAndReturn(tempObj[1]));
				}
			}
		}
		return resultMap;
	}

	/**
	 * 获取用户所在科级部门
	 * 
	 * @param bm
	 *            (用户直接部门)
	 * @return
	 * @throws Exception
	 */
	public String[] getCurrentUserKjBm(String bm) throws Exception {
		String[] kjBm = { "", "" };
		bm = checkStrNullAndReturn(bm);
		if (!bm.equals("")) {
			// String sql =
			// " select a.ORG_Id,a.Up_Org,a.Org_Name, level from v_veh_org_ycs a where a.Up_Org = 'C34702A8FED97CBFE040007F0100339B' start with a.org_id = '"
			// + bm + "' connect by prior a.org_id = a.up_org  ";
			String sql = " select a.ORG_Id,a.Up_Org,a.Org_Name, level from v_veh_org_ycs a where a.Up_Org = 'C34702A8FED97CBFE040007F0100339B' start with a.org_id = '" + bm
					+ "' connect by prior a.up_org =  a.org_id ";
			List<Object> tempList = defaultDao.findSQL(sql);
			if (tempList != null && tempList.size() > 0) {
				Object tempObj = tempList.get(0);
				if (tempObj != null) {
					Object[] temp = (Object[]) tempObj;
					kjBm[0] = checkStrNullAndReturn(temp[0]);
					kjBm[1] = checkStrNullAndReturn(temp[2]);
				}
			}
		}
		return kjBm;
	}

	/**
	 * 获取某部门对应于车辆管理处部门下的直接子部门
	 * 
	 * @param bm
	 * @return
	 * @throws Exception
	 */
	public String[] getCurrentBmInClglcbm(String bm) throws Exception {
		String[] ujBm = { "", "" };
		bm = checkStrNullAndReturn(bm);
		if (!bm.equals("")) {
			String sql = " select a.ORG_Id,a.Up_Org,a.Org_Name, level from v_veh_org_ycs a where a.Up_Org='C34702A8FED97CBFE040007F0100339B' start with a.org_id = 'C34702A8FEF07CBFE040007F0100339B' connect by prior a.up_org =a.org_id  ";
			List<Object> tempList = defaultDao.findSQL(sql);
			if (tempList != null && tempList.size() > 0) {
				Object tempObj = tempList.get(0);
				if (tempObj != null) {
					Object[] temp = (Object[]) tempObj;
					ujBm[0] = checkStrNullAndReturn(temp[0]);
					ujBm[1] = checkStrNullAndReturn(temp[2]);
				}
			}
		}
		return ujBm;
	}

	/**
	 * 按预约批次审核广告喷涂业务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch(HttpServletRequest request) throws Exception {

		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String pchList = checkStrNullAndReturn(request.getParameter("pchList"));
			String shzt = checkStrNullAndReturn(request.getParameter("shzt"));
			// 退办原因
			String tbyy = checkStrNullAndReturn(request.getParameter("tbyy"));
			String otherTbyy = checkStrNullAndReturn(request.getParameter("otherTbyy"));
			String realTbyyStr = "";
			String mac = checkStrNullAndReturn(request.getParameter("mac"));
			String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

			if (!pchList.equals("")) {
				String[] tempPcj = pchList.split("-");
				if (tempPcj.length > 0) {
					String tempParam = " ( ";
					for (int i = 0; i < tempPcj.length; i++) {
						if (i != tempPcj.length - 1) {
							tempParam += "'" + tempPcj[i] + "',";
						} else {
							tempParam += "'" + tempPcj[i] + "'";
						}
					}
					tempParam = tempParam + " ) ";
					String hql = " from BusGgjgclsb a where a.yypch in " + tempParam;
					List<Object> tempList = defaultDao.getRepositories(hql);
					if (tempList != null && tempList.size() > 0) {

						Map<String, String> tbyyMap = getBusSzjdMap(null, "TTBY", null);

						for (Object obj : tempList) {

							realTbyyStr = "";

							BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
							boolean change = false;
							// 广告业务状态
							// 0待车管部门审核 1审核通过 2审核成功 E办结，已发证 Q退办

							// shzt(2审核成功 CQ退办 )
							if (shzt.equals("CQ")) {

								if (busGgjgclsb.getZt() == null || (!busGgjgclsb.getZt().equals("E") && !busGgjgclsb.getZt().equals("CQ"))) {
									busGgjgclsb.setZt("CQ");
									// 验车结果1合格,2不合格
									busGgjgclsb.setYcjg("2");
									if (!tbyy.equals("A000")) {

										String[] tempTbyy = tbyy.split("\\*");
										for (int i = 0; i < tempTbyy.length; i++) {
											String str = checkStrNullAndReturn(tempTbyy[i]);
											if (str != null && !str.equals("")) {
												if (i < tempTbyy.length - 1) {
													realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str)) + ";";
												} else {
													realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str));
												}
											}
										}

										busGgjgclsb.setYcfpms(realTbyyStr);
										// 验车不通过选项
										busGgjgclsb.setYcjgYy(realTbyyStr);
									} else {

										otherTbyy = URLDecoder.decode(otherTbyy, "UTF-8");

										busGgjgclsb.setYcfpms(otherTbyy);
										// 验车不通过选项
										busGgjgclsb.setYcjgYy(otherTbyy);
									}
									change = true;
								}
							} else if (shzt.equals("2")) {
								if (busGgjgclsb.getZt() == null || busGgjgclsb.getZt().equals("1")) {

									// 清空退办原因
									busGgjgclsb.setYcfpms(null);
									busGgjgclsb.setYcjgYy(null);

									// 验车结果1合格,2不合格
									busGgjgclsb.setYcjg("1");
									busGgjgclsb.setZt("2");
									change = true;
								}
							}

							if (change) {

								Date currentDate = new Date();

								String[] currentUserKjbm = getCurrentUserKjBm(user.getBmid());

								// 审核人员信息
								busGgjgclsb.setYcmjcode(user.getName());
								busGgjgclsb.setYcmjxm(user.getYgxm());
								// 审核部门
								busGgjgclsb.setYcmjbm(user.getBmid());
								// 审核部门(科级)
								busGgjgclsb.setYcmjbmKj(checkStrNullAndReturn(currentUserKjbm[0]));
								busGgjgclsb.setYcmjrq(currentDate);

								busGgjgclsb.setSynFlag("UW");
								busGgjgclsb.setTranDate(null);
								busGgjgclsb.setTranFlag(null);

								// 更新广告业务状态
								defaultDao.updateRepository(busGgjgclsb);

								// 记录日志
								BusGgjgclsbLog log = new BusGgjgclsbLog();
								log.setCzr(user.getName());
								log.setCzrxm(user.getYgxm());
								log.setCzrbm(user.getBmid());
								log.setCzsj(currentDate);
								log.setCzmac(mac);
								log.setCzip(ip);
								log.setCznr("审核广告业务申报数据");
								List<Object> list = new ArrayList<Object>();
								list.add("yyshsl");
								list.add("ztMc");
								defaultDao.addRepositoryLog(log, busGgjgclsb, list);

							}

						}
					}
				} else {
					result = "-操作失败:无法获取要审核的批次号";
				}
			} else {
				result = "-操作失败:无法获取要审核的批次号";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 按预约批次分配验车部门
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByPch_fp(HttpServletRequest request) throws Exception {

		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String pchList = checkStrNullAndReturn(request.getParameter("pchList"));
			String ycbm = checkStrNullAndReturn(request.getParameter("ycbm"));
			String mac = checkStrNullAndReturn(request.getParameter("mac"));
			String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

			if (!pchList.equals("")) {
				String[] tempPcj = pchList.split("-");
				if (tempPcj.length > 0) {
					String tempParam = " ( ";
					for (int i = 0; i < tempPcj.length; i++) {
						if (i != tempPcj.length - 1) {
							tempParam += "'" + tempPcj[i] + "',";
						} else {
							tempParam += "'" + tempPcj[i] + "'";
						}
					}
					tempParam = tempParam + " ) ";
					String hql = " from BusGgjgclsb a where a.yypch in " + tempParam;
					List<Object> tempList = defaultDao.getRepositories(hql);
					if (tempList != null && tempList.size() > 0) {

						// 验车部门
						Map<String, String> ycglbmMap = getBusSzjdMap(null, "YYBM", null);
						// Map<String, String> ycglbmMap = getBmMap(null, null,
						// false);

						// 是否可以分配验车部门
						boolean isContinue = true;
						for (Object obj : tempList) {
							BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
							// 存在已办结的数据,则不允许分配验车部门
							if (busGgjgclsb.getZt().equals("E")) {
								isContinue = false;
								break;
							}
						}

						if (isContinue) {
							for (Object obj : tempList) {
								BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
								boolean change = false;
								// 广告业务状态 0待车管部门审核 1审核通过 2审核成功 E办结，已发证 CQ退办

								if (!busGgjgclsb.getZt().equals("E") && !busGgjgclsb.getZt().equals("CQ")) {
									busGgjgclsb.setYyycbm(ycbm);
									busGgjgclsb.setYyycbmmc(ycglbmMap.get(ycbm));
									change = true;
								}

								if (change) {

									Date currentDate = new Date();

									String[] currentUserkjBm = getCurrentUserKjBm(user.getBmid());
									busGgjgclsb.setYcfpcode(user.getName());
									busGgjgclsb.setYcfpxm(user.getYgxm());
									// 验车分配部门
									busGgjgclsb.setYcfpbm(user.getBmid());
									// 验车分配部门(科级)
									busGgjgclsb.setYcfpbmKj(checkStrNullAndReturn(currentUserkjBm[0]));
									busGgjgclsb.setYcfprq(currentDate);

									busGgjgclsb.setSynFlag("UW");
									busGgjgclsb.setTranDate(null);
									busGgjgclsb.setTranFlag(null);

									// 更新广告业务状态
									defaultDao.updateRepository(busGgjgclsb);

									// 记录日志
									BusGgjgclsbLog log = new BusGgjgclsbLog();
									log.setCzr(user.getName());
									log.setCzrxm(user.getYgxm());
									log.setCzrbm(user.getBmid());
									log.setCzsj(currentDate);
									log.setCzmac(mac);
									log.setCzip(ip);
									log.setCznr("审核部门调配 ");
									List<Object> list = new ArrayList<Object>();
									list.add("yyshsl");
									list.add("ztMc");
									defaultDao.addRepositoryLog(log, busGgjgclsb, list);

								}

							}
						} else {
							result = "-操作失败:该批次存在已办结数据，不允许更改验车部门";
						}

					}
				} else {
					result = "-操作失败:无法获取要审核的批次号";
				}
			} else {
				result = "-操作失败:无法获取要审核的批次号";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 按批次号退办广告喷涂业务
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String tbBusGgjgclsbByPch(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String yypch = checkStrNullAndReturn(request.getParameter("yypch"));
			String mac = checkStrNullAndReturn(request.getParameter("mac"));
			String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

			if (!yypch.equals("")) {
				String hql = " from BusGgjgclsb a where a.yypch='" + yypch + "' and a.zt not in ('E','Q') ";
				List<Object> tempList = defaultDao.getRepositories(hql);
				if (tempList != null && tempList.size() > 0) {
					for (Object obj : tempList) {
						BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
						// 广告业务状态
						// 0待车管部门审核 1审核通过 E办结，已发证 Q退办
						busGgjgclsb.setZt("Q");

						busGgjgclsb.setSynFlag("UW");
						busGgjgclsb.setTranDate(null);
						busGgjgclsb.setTranFlag(null);

						// 更新广告业务状态
						defaultDao.updateRepository(busGgjgclsb);

						// 记录日志
						BusGgjgclsbLog log = new BusGgjgclsbLog();
						log.setCzr(user.getName());
						log.setCzrxm(user.getYgxm());
						log.setCzrbm(user.getBmid());
						log.setCzsj(new Date());
						log.setCzmac(mac);
						log.setCzip(ip);
						log.setCznr("广告业务退办");
						List<Object> list = new ArrayList<Object>();
						list.add("yyshsl");
						list.add("ztMc");
						defaultDao.addRepositoryLog(log, busGgjgclsb, list);

					}
				}

			} else {
				result = "-操作失败:无法获取要退办的预约批次号";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 按流水号批量审核广告喷涂业务
	 * 
	 * @param request
	 * @throws Exception
	 */
	public String updateBusGgjgclsbByLsh(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		if (user != null) {
			String lshList = checkStrNullAndReturn(request.getParameter("lshList"));
			String shzt = checkStrNullAndReturn(request.getParameter("shzt"));
			// 退办原因(多个退办原因以*隔开)
			String tbyy = checkStrNullAndReturn(request.getParameter("tbyy"));
			String otherTbyy = checkStrNullAndReturn(request.getParameter("otherTbyy"));
			String realTbyyStr = "";
			String mac = checkStrNullAndReturn(request.getParameter("mac"));
			String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

			if (!lshList.equals("")) {
				String[] tempLsh = lshList.split("-");
				if (tempLsh.length > 0) {
					String tempParam = " ( ";
					for (int i = 0; i < tempLsh.length; i++) {
						if (i != tempLsh.length - 1) {
							tempParam += "'" + tempLsh[i] + "',";
						} else {
							tempParam += "'" + tempLsh[i] + "'";
						}
					}
					tempParam = tempParam + " ) ";
					String hql = " from BusGgjgclsb a where a.lsh in " + tempParam;
					List<Object> tempList = defaultDao.getRepositories(hql);
					if (tempList != null && tempList.size() > 0) {

						Map<String, String> tbyyMap = getBusSzjdMap(null, "TTBY", null);

						for (Object obj : tempList) {

							realTbyyStr = "";

							BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
							boolean change = false;
							// 广告业务状态
							// 0待车管部门审核 1审核通过 E办结，已发证 Q退办

							// shzt(2审核成功 CQ审核不通过 )
							if (shzt.equals("CQ")) {

								if (busGgjgclsb.getZt() == null || (!busGgjgclsb.getZt().equals("E") && !busGgjgclsb.getZt().equals("CQ"))) {
									busGgjgclsb.setZt("CQ");
									// 验车结果1合格,2不合格
									busGgjgclsb.setYcjg("2");
									if (!tbyy.equals("A000")) {

										String[] tempTbyy = tbyy.split("\\*");
										for (int i = 0; i < tempTbyy.length; i++) {
											String str = checkStrNullAndReturn(tempTbyy[i]);
											if (str != null && !str.equals("")) {
												if (i < tempTbyy.length - 1) {
													realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str)) + ";";
												} else {
													realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str));
												}

											}
										}

										busGgjgclsb.setYcfpms(realTbyyStr);
										// 验车不通过选项
										busGgjgclsb.setYcjgYy(realTbyyStr);
									} else {

										otherTbyy = URLDecoder.decode(otherTbyy, "UTF-8");

										busGgjgclsb.setYcfpms(otherTbyy);
										// 验车不通过选项
										busGgjgclsb.setYcjgYy(otherTbyy);
									}
									change = true;
								}

							} else if (shzt.equals("2")) {
								if (busGgjgclsb.getZt() == null || busGgjgclsb.getZt().equals("1")) {
									// 验车结果1合格,2不合格
									busGgjgclsb.setYcjg("1");
									busGgjgclsb.setZt("2");

									// 清空退办原因
									busGgjgclsb.setYcfpms(null);
									busGgjgclsb.setYcjgYy(null);

									change = true;
								}
							}

							if (change) {
								Date currentDate = new Date();

								String[] currentUserKjBm = getCurrentUserKjBm(user.getBmid());
								// 审核人员信息
								busGgjgclsb.setYcmjcode(user.getName());
								busGgjgclsb.setYcmjxm(user.getYgxm());
								// 审核部门
								busGgjgclsb.setYcmjbm(user.getBmid());
								// 审核部门(科级)
								busGgjgclsb.setYcmjbmKj(checkStrNullAndReturn(currentUserKjBm[0]));
								busGgjgclsb.setYcmjrq(currentDate);

								busGgjgclsb.setSynFlag("UW");
								busGgjgclsb.setTranDate(null);
								busGgjgclsb.setTranFlag(null);

								// 更新广告业务状态
								defaultDao.updateRepository(busGgjgclsb);

								// 记录日志
								BusGgjgclsbLog log = new BusGgjgclsbLog();
								log.setCzr(user.getName());
								log.setCzrxm(user.getYgxm());
								log.setCzrbm(user.getBmid());
								log.setCzsj(currentDate);
								log.setCzmac(mac);
								log.setCzip(ip);
								log.setCznr("广告业务审核");
								List<Object> list = new ArrayList<Object>();
								list.add("yyshsl");
								list.add("ztMc");
								defaultDao.addRepositoryLog(log, busGgjgclsb, list);

							}

						}
					}
				} else {
					result = "-操作失败:无法获取要审核的流水号";
				}
			} else {
				result = "-操作失败:无法获取要审核的流水号";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 按流水号查询审核广告喷涂业务
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByLsh(HttpServletRequest request) throws Exception {

		BusGgjgclsb busGgjgclsb = null;
		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
		// 代办员
		List<BusGgjgdlr> busGgjgdlrs = new ArrayList<BusGgjgdlr>();
		// 公交车
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		// 所属公交公司
		BusBase busBase = new BusBase();

		// 当前广告喷涂业务对应的公交车
		BusVehicleBase currentBus = new BusVehicleBase();

		Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(true);

		if (!lsh.equals("")) {
			Object obj = defaultDao.getRepository(lsh, BusGgjgclsb.class);
			if (obj != null) {
				busGgjgclsb = (BusGgjgclsb) obj;
				busGgjgdlrs = getBusGgjgdlrs(busGgjgclsb.getGgjgid(), "T");
				busVehicleBases = getBusVehicleBase(busGgjgclsb.getGjgsid(), null, null, "1");

				Object tempBusBase = defaultDao.getRepository(busGgjgclsb.getGjgsid(), BusBase.class);
				if (tempBusBase != null) {
					busBase = (BusBase) tempBusBase;
				}

				// 查询当前广告喷涂业务对应的公交车信息
				for (BusVehicleBase bus : busVehicleBases) {
					String hphm = checkStrNullAndReturn(bus.getHphm());
					String hpzl = checkStrNullAndReturn(bus.getHpzl());

					if (hphm.equals(checkStrNullAndReturn(busGgjgclsb.getHphm())) && hpzl.equals(checkStrNullAndReturn(busGgjgclsb.getHpzl()))) {
						currentBus = bus;
						if (hpzlMap != null) {
							// 翻译号牌种类
							currentBus.setHpzl(checkStrNullAndReturn(hpzlMap.get(currentBus.getHpzl())));
						}

						break;
					}
				}
			}
		}

		Map<String, String> cllxMap = gjbaxxspService.getCllxMap(true);
		// 退办原因
		Map<String, String> tbyyMap = getBusSzjdMap(null, "TTBY", null);

		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("cllxMap", cllxMap);
		request.setAttribute("tbyyMap", tbyyMap);
		request.setAttribute("busGgjgdlrs", busGgjgdlrs);
		request.setAttribute("busVehicleBases", busVehicleBases);
		request.setAttribute("currentBus", currentBus);
		request.setAttribute("busBase", busBase);
		request.setAttribute("busGgjgclsb", busGgjgclsb);
	}

	public List<BusGgjgdlr> getBusGgjgdlrs(String ggjgid, String zt) throws Exception {

		List<BusGgjgdlr> busGgjgdlrs = new ArrayList<BusGgjgdlr>();

		if (ggjgid != null && !ggjgid.trim().equals("")) {
			String hql = " from BusGgjgdlr a where a.ggjgid='" + ggjgid + "' ";
			if (!zt.equals("")) {
				hql += " and a.zt='" + zt + "' ";
			}
			List<Object> tempList = defaultDao.getRepositories(hql);
			for (Object obj : tempList) {
				if (obj != null) {
					BusGgjgdlr dlr = (BusGgjgdlr) obj;
					busGgjgdlrs.add(dlr);
				}
			}
		}
		return busGgjgdlrs;
	}

	public List<BusVehicleBase> getBusVehicleBase(String gjgsid, String hphm, String hpzl, String shzt) throws Exception {

		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		if (gjgsid != null && !gjgsid.trim().equals("")) {
			String hql = " from BusVehicleBase a where a.gjgsid='" + gjgsid + "' ";
			if (!checkStrNullAndReturn(hphm).equals("")) {
				hql += "  and a.hphm='" + hphm + "' ";
			}
			if (!checkStrNullAndReturn(hpzl).equals("")) {
				hql += "  and a.hpzl='" + hpzl + "' ";
			}
			// A正常 0未审核 1审核不通过 2互联网修改未审核
			if (!checkStrNullAndReturn(shzt).equals("")) {
				hql += "  and a.shzt='" + shzt + "' ";
			}
			List<Object> tempList = defaultDao.getRepositories(hql);
			for (Object obj : tempList) {
				if (obj != null) {
					BusVehicleBase bus = (BusVehicleBase) obj;
					busVehicleBases.add(bus);
				}
			}
		}
		return busVehicleBases;
	}

	/**
	 * 修改广告喷涂业务
	 * 
	 * @param request
	 * @param zqFile
	 * @param yhFile
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgclsb(HttpServletRequest request, BusGgjgclsb busGgjgclsb) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (busGgjgclsb != null && busGgjgclsb.getLsh() != null && !busGgjgclsb.getLsh().trim().equals("")) {
				String lsh = checkStrNullAndReturn(busGgjgclsb.getLsh());
				Object obj = defaultDao.getRepository(lsh, BusGgjgclsb.class);
				if (obj != null) {
					BusGgjgclsb tempBusGgjgclsb = (BusGgjgclsb) obj;

					String shzt = checkStrNullAndReturn(busGgjgclsb.getZt());
					String mac = checkStrNullAndReturn(request.getParameter("mac"));
					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

					String tbyy = checkStrNullAndReturn(request.getParameter("tbyy"));
					String otherTbyy = checkStrNullAndReturn(request.getParameter("otherTbyy"));
					String realTbyyStr = "";

					otherTbyy = URLDecoder.decode(otherTbyy, "UTF-8");

					if (shzt == null || shzt.equals("")) {
						result = "-操作失败:审核结果为空";
					} else {
						// 退办的
						if (shzt.equals("CQ")) {

							Map<String, String> tbyyMap = getBusSzjdMap(null, "TTBY", null);

							if (tbyy.equals("A000")) {
								if (otherTbyy.equals("")) {
									result = "-操作失败:退办其他原因为空";
								}
							} else {
								if (tbyy.equals("")) {
									result = "-操作失败:退办原因为空";
								} else {
									String[] tempTbyy = tbyy.split("\\*");

									for (int i = 0; i < tempTbyy.length; i++) {
										String str = checkStrNullAndReturn(tempTbyy[i]);
										if (str != null && !str.equals("")) {
											if (i < tempTbyy.length - 1) {
												realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str)) + ";";
											} else {
												realTbyyStr += checkStrNullAndReturn(tbyyMap.get(str));
											}

										}
									}
								}
							}
						} else {

						}
					}

					if (result.indexOf("-") != 0) {

						tempBusGgjgclsb.setZt(shzt);

						if (shzt.equals("CQ")) {
							// 验车结果1合格，2不合格
							tempBusGgjgclsb.setYcjg("2");

							if (!tbyy.equals("A000")) {
								tempBusGgjgclsb.setYcfpms(realTbyyStr);
								tempBusGgjgclsb.setYcjgYy(realTbyyStr);
							} else {
								tempBusGgjgclsb.setYcfpms(otherTbyy);
								tempBusGgjgclsb.setYcjgYy(otherTbyy);
							}
						} else {
							// 验车结果1合格，2不合格
							tempBusGgjgclsb.setYcjg("1");
						}

						Date currentDate = new Date();

						String[] currentUserKjBm = getCurrentUserKjBm(user.getBmid());
						// 审核民警信息
						tempBusGgjgclsb.setYcmjcode(user.getName());
						tempBusGgjgclsb.setYcmjxm(user.getYgxm());
						// 审核部门
						tempBusGgjgclsb.setYcmjbm(user.getBmid());
						// 审核部门(科级)
						tempBusGgjgclsb.setYcmjbmKj(checkStrNullAndReturn(currentUserKjBm[0]));
						tempBusGgjgclsb.setYcmjrq(currentDate);

						tempBusGgjgclsb.setSynFlag("UW");
						tempBusGgjgclsb.setTranDate(null);
						tempBusGgjgclsb.setTranFlag(null);

						defaultDao.updateRepository(tempBusGgjgclsb);
						BusGgjgclsbLog log = new BusGgjgclsbLog();
						log.setCzr(user.getName());
						log.setCzrxm(user.getYgxm());
						log.setCzrbm(user.getBmid());
						log.setCzsj(currentDate);
						log.setCzip(ip);
						log.setCzmac(mac);
						log.setCznr("审核广告业务申报数据");
						List<Object> list = new ArrayList<Object>();
						list.add("yyshsl");
						list.add("ztMc");
						defaultDao.addRepositoryLog(log, tempBusGgjgclsb, list);
					}

				} else {
					result = "-操作失败:查询不到流水号(" + lsh + ")对应的广告喷涂业务数据";
				}
			} else {
				result = "-操作失败:要修改的广告喷涂业务数据为空";
			}

		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 根据流水号查询广告喷涂业务申报信息对应的图片信息
	 * 
	 * @param lsh
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public Blob getYcPic(String lsh, String position) throws Exception {
		if (lsh != null && !lsh.equals("")) {
			Object obj = defaultDao.getRepository(lsh, BusGgjgclsbPhoto.class);
			if (obj != null) {
				BusGgjgclsbPhoto busGgjgclsbPhoto = (BusGgjgclsbPhoto) obj;
				Blob image = null;
				if (position.equals("left")) {
					image = busGgjgclsbPhoto.getLPic();
				} else if (position.equals("right")) {
					image = busGgjgclsbPhoto.getRPic();
				}
				return image;
			}
		}
		return null;
	}

	public String checkStrNullAndReturn(Object str) {
		String returnStr = "";
		try {
			returnStr = str == null ? "" : str.toString().trim();
		} catch (Exception e) {
			return "";
		}
		return returnStr;
	}

	public DefaultDao getDefaultDao() {
		return defaultDao;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public GjbaxxspService getGjbaxxspService() {
		return gjbaxxspService;
	}

	public void setGjbaxxspService(GjbaxxspService gjbaxxspService) {
		this.gjbaxxspService = gjbaxxspService;
	}

}
