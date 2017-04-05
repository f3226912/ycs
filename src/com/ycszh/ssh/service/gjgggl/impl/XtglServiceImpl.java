package com.ycszh.ssh.service.gjgggl.impl;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.gjgggl.BusBase;
import com.ycszh.ssh.hbm.gjgggl.BusSjzd;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import com.ycszh.ssh.service.gjgggl.XtglService;
import com.ycszh.util.ToolsUtil;

public class XtglServiceImpl implements XtglService {

	private DefaultDao defaultDao;
	private GjbaxxspService gjbaxxspService;

	public void getBusBaseInital(HttpServletRequest request) throws Exception {

		List<BusBase> busBases = new ArrayList<BusBase>();

		// 公交公司id
		String gjgsid = checkStrNullAndReturn(request.getParameter("gjgsid"));
		// 公交公司名称
		String gjgsmc = checkStrNullAndReturn(request.getParameter("gjgsmc"));
		// 公交公司组织机构代码
		String zzjgdm = checkStrNullAndReturn(request.getParameter("zzjgdm"));

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusBase a where 1=1 ";
		if (!gjgsid.equals("")) {
			hql += " and  a.gjgsid='" + gjgsid + "'  ";
		}
		if (!gjgsmc.equals("")) {
			hql += " and  a.gjgsmc like '%" + gjgsmc + "%'  ";
		}
		if (!zzjgdm.equals("")) {
			hql += " and  a.zzjgdm like '%" + zzjgdm + "%'  ";
		}

		hql += " order by a.gjgsid desc ";

		List<Object> tempList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		for (Object obj : tempList) {
			BusBase busBase = (BusBase) obj;
			busBases.add(busBase);
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("gjgsid", gjgsid);
		request.setAttribute("gjgsmc", gjgsmc);
		request.setAttribute("zzjgdm", zzjgdm);

		request.setAttribute("busBases", busBases);

	}

	/**
	 * 初始化公交公司登录密码
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusBasePwd(HttpServletRequest request) throws Exception {
		String result = "";

		String gjgsid = checkStrNullAndReturn(request.getParameter("gjgsid"));

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (gjgsid != null && !gjgsid.trim().equals("")) {
				Object obj = defaultDao.getRepository(gjgsid, BusBase.class);
				if (obj != null) {
					BusBase busBase = (BusBase) obj;
					// String newPwd = busBase.getZzjgdm() +
					// Math.round(Math.random() * 10000);
					String newPwd = busBase.getZzjgdm();
					String pwd = gjbaxxspService.getPwd(newPwd, "BF42");
					busBase.setPassword(pwd);

					busBase.setSynFlag("UW");
					busBase.setTranDate(null);
					busBase.setTranFlag(null);

					defaultDao.updateRepository(busBase);

					result = pwd;

				} else {
					result = "-操作失败:查询不到公交公司id(" + gjgsid + ")对应的公交公司数据,无法初始化公交公司密码";
				}
			} else {
				result = "-操作失败:公交公司id为空,无法初始化公交公司密码";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 添加公交公公司数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusBase(HttpServletRequest request, BusBase busBase) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (busBase != null) {

				String zjjgdmStr = checkStrNullAndReturn(busBase.getZzjgdm());
				String lxdhStr = checkStrNullAndReturn(busBase.getLxdh());
				String mac = checkStrNullAndReturn(request.getParameter("mac"));

				String gjgsmcStr = checkStrNullAndReturn(busBase.getGjgsmc());
				String gjgsdzStr = checkStrNullAndReturn(busBase.getGjgsdz());
				String zrrStr = checkStrNullAndReturn(busBase.getZzr());
				String gsgsjcStr = checkStrNullAndReturn(busBase.getGsgsjc());

				boolean isContinue = true;
				if (gjgsmcStr.equals("")) {
					result = "-操作失败:公交公司名称为空";
					isContinue = false;
				}
				if (zjjgdmStr.equals("")) {
					result = "-操作失败:公交公司组织机构代码为空";
					isContinue = false;
				}
				if (lxdhStr.equals("")) {
					result = "-操作失败:公交公司联系电话为空";
					isContinue = false;
				}
				if (gsgsjcStr.equals("")) {
					result = "-操作失败:公交公司简称为空";
					isContinue = false;
				}

				if (isContinue) {
					// String newPwd = busBase.getZzjgdm() +
					// Math.round(Math.random() * 10000);
					String newPwd = busBase.getZzjgdm();
					String pwd = gjbaxxspService.getPwd(newPwd, "BF42");

					busBase.setGjgsmc(URLDecoder.decode(gjgsmcStr, "UTF-8"));
					busBase.setGjgsdz(URLDecoder.decode(gjgsdzStr, "UTF-8"));
					busBase.setZzr(URLDecoder.decode(zrrStr, "UTF-8"));
					busBase.setGsgsjc(URLDecoder.decode(gsgsjcStr, "UTF-8"));

					busBase.setPassword(pwd);

					busBase.setLrdm(user.getName());
					busBase.setLrxm(user.getYgxm());
					busBase.setLrbm(user.getBmid());
					busBase.setLrsj(new Date());
					busBase.setLrip(checkStrNullAndReturn(ToolsUtil.getIpAddr(request)));
					busBase.setLrmac(mac);

					busBase.setSynFlag("UW");
					busBase.setTranDate(null);
					busBase.setTranFlag(null);

					defaultDao.addRepository(busBase);

					result = pwd;
				}

			} else {
				result = "-操作失败:要添加的公交公司数据为空";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	public void getSzzdInital(HttpServletRequest request) throws Exception {

		List<String[]> szzdList = new ArrayList<String[]>();

		String dmz = checkStrNullAndReturn(request.getParameter("dmz"));
		String dmlb = checkStrNullAndReturn(request.getParameter("dmlb"));
		String dmms1 = checkStrNullAndReturn(request.getParameter("dmms1"));

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " select a.xh,a.dmlb,a.dmz,a.dmms1 from BUS_SJZD a where 1=1 ";
		if (!dmz.equals("")) {
			hql += " and  a.dmz like '%" + dmz + "%'  ";
		}
		if (!dmlb.equals("")) {
			hql += " and  a.dmlb like '%" + dmlb + "%'  ";
		}
		if (!dmms1.equals("")) {
			hql += " and  a.dmms1 like '%" + dmms1 + "%'  ";
		}

		hql += " order by a.dmlb asc,a.dmms1 asc ";

		List<Object> tempList = defaultDao.findSQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryBySQLListSize(" select count(*) from ( " + hql + " ) ");

		for (Object obj : tempList) {
			if (obj != null) {
				Object[] tempObj = (Object[]) obj;
				String[] tempSzzd = new String[4];
				String tempXh = checkStrNullAndReturn(tempObj[0]);
				String tempDmz = checkStrNullAndReturn(tempObj[1]);
				String tempDmlb = checkStrNullAndReturn(tempObj[2]);
				String tempDmms1 = checkStrNullAndReturn(tempObj[3]);
				tempSzzd[0] = tempXh;
				tempSzzd[1] = tempDmz;
				tempSzzd[2] = tempDmlb;
				tempSzzd[3] = tempDmms1;
				szzdList.add(tempSzzd);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("dmz", dmz);
		request.setAttribute("dmlb", dmlb);
		request.setAttribute("dmms1", dmms1);

		request.setAttribute("szzdList", szzdList);

	}

	/**
	 * 更新数字字典
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateSzzd(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String xh = checkStrNullAndReturn(request.getParameter("xh"));
			if (!xh.equals("")) {
				String dmz = checkStrNullAndReturn(request.getParameter("dmz"));
				String dmlb = checkStrNullAndReturn(request.getParameter("dmlb"));
				String dmms = checkStrNullAndReturn(request.getParameter("dmms"));
				dmms = URLDecoder.decode(dmms, "UTF-8");

				Object obj = defaultDao.getRepository(xh, BusSjzd.class);

				if (obj != null) {

					BusSjzd busSjzd = (BusSjzd) obj;

					boolean isChange = false;
					if (!dmz.equals("")) {
						busSjzd.setDmz(dmz);
						isChange = true;
					}
					if (!dmlb.equals("")) {
						busSjzd.setDmlb(dmlb);
						isChange = true;
					}
					if (!dmms.equals("")) {
						busSjzd.setDmms1(dmms);
						isChange = true;
					}

					if (isChange) {
						defaultDao.updateRepository(busSjzd);
					}

				}

			} else {
				result = "-字典序号为空,无法操作";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
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
