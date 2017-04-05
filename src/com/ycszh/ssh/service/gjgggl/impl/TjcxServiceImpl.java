package com.ycszh.ssh.service.gjgggl.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.gjgggl.BusBase;
import com.ycszh.ssh.hbm.gjgggl.BusCertifyCzcd;
import com.ycszh.ssh.hbm.gjgggl.BusCertifyInfo;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.service.gjgggl.GgzxxglService;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import com.ycszh.ssh.service.gjgggl.GjclycxxglService;
import com.ycszh.ssh.service.gjgggl.TjcxService;
import com.ycszh.util.DateUtil;

public class TjcxServiceImpl implements TjcxService {

	private DefaultDao defaultDao;
	private GjbaxxspService gjbaxxspService;
	private GgzxxglService ggzxxglService;
	private GjclycxxglService gjclycxxglService;

	public void getLscx(HttpServletRequest request) throws Exception {

		String currentDateStr = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		Date currentDate = DateUtil.string2Date(currentDateStr, "yyyy-MM-dd");

		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
		String lszt = checkStrNullAndReturn(request.getParameter("lszt"));
		String hphm = checkStrNullAndReturn(request.getParameter("hphm"));
		String hpzl = checkStrNullAndReturn(request.getParameter("hpzl"));
		// 公交公司id
		String gjgs = checkStrNullAndReturn(request.getParameter("gjgs"));
		// 广告公司id
		String gggs = checkStrNullAndReturn(request.getParameter("gggs"));

		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		if (startTime.equals("") || endTime.equals("")) {

			// 查询30天之内的数据
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String realEndTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
			calendar.add(Calendar.DATE, -30);
			String realStartTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

			// 默认第一笔申报的录入时间到现在的数据
			String tempMinTime = getFirstSbTime();

			if (!tempMinTime.equals("")) {

				Date tempMinTime_date = DateUtil.string2Date(tempMinTime, "yyyy-MM-dd HH:mm:ss");
				calendar.setTime(tempMinTime_date);
				calendar.add(Calendar.DATE, -1);
				
				String tempMinTime_=DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

				String realStartTime_ = tempMinTime_;
				String realEndTime_ = DateUtil.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");

				startTime = realStartTime_;
				endTime = realEndTime_;
			} else {
				startTime = realStartTime;
				endTime = realEndTime;
			}
		}

		// 发起请求的来源网页
		String comefrom = checkStrNullAndReturn(request.getParameter("comefrom"));
		List<BusGgjgclsb> busGgjgclsbs = new ArrayList<BusGgjgclsb>();

		// 流水状态
		Map<String, String> lsztMap = new HashMap<String, String>();
		Map<String, String> hpzlMap = new HashMap<String, String>();
		// 公交公司
		Map<String, String> gjgsMap = new HashMap<String, String>();
		// 广告机构
		Map<String, String> gggsMap = new HashMap<String, String>();

		// 验车部门
		Map<String, String> bmMap = new HashMap<String, String>();

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusGgjgclsb a where 1=1 ";
		String hql_ggz = " from BusCertifyInfo a where 1=1 ";

		if (!lsh.equals("")) {
			hql += " and a.lsh = '" + lsh + "' ";
			hql_ggz += " and a.lsh = '" + lsh + "' ";
			startTime = "";
			endTime = "";
		} else {
			if (!startTime.equals("") && !endTime.equals("")) {
				hql += " and a.lrsj between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') ";
			}
		}

		if (!lszt.equals("")) {
			hql += " and a.zt = '" + lszt + "' ";
		}
		if (!hphm.equals("")) {
			hql += " and a.hphm like '%" + hphm + "%' ";
			hql_ggz += " and a.hphm like '%" + hphm + "%' ";
		}
		if (!hpzl.equals("")) {
			hql += " and a.hpzl='" + hpzl + "' ";
			hql_ggz += " and a.hpzl='" + hpzl + "' ";
		}
		if (!gjgs.equals("")) {
			hql += " and a.gjgsid='" + gjgs + "' ";
			hql_ggz += " and a.gjgsid='" + gjgs + "' ";
		}
		if (!gggs.equals("")) {
			hql += " and a.ggjgid='" + gggs + "' ";
			hql_ggz += " and a.ggjgid='" + gggs + "' ";
		}

		hql += " order by a.lsh desc ";

		List<Object> tempList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		List<Object> tempList_ggz = defaultDao.getRepositories(hql_ggz);
		List<BusCertifyInfo> busCertifyInfos = new ArrayList<BusCertifyInfo>();

		for (Object object : tempList_ggz) {
			BusCertifyInfo tempBusCertifyInfo = (BusCertifyInfo) object;
			busCertifyInfos.add(tempBusCertifyInfo);
		}

		lsztMap = gjclycxxglService.getBusSzjdMap(null, "LSZT", null);
		hpzlMap = gjbaxxspService.getHpzlMap(false);
		gjgsMap = ggzxxglService.getBusBases(null, null);
		gggsMap = gjbaxxspService.getGggsMap(false);

		if (tempList != null && tempList.size() > 0) {

			bmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", null);
			// bmMap = gjclycxxglService.getBmMap(null, null, false);

			for (Object obj : tempList) {
				BusGgjgclsb tempBusGgjgclsb = (BusGgjgclsb) obj;
				String tempLsh = checkStrNullAndReturn(tempBusGgjgclsb.getLsh());
				// int bgyxts = Integer.parseInt(tempBusGgjgclsb.getBgyxts() +
				// "");

				// 判断广告证是否过期
				/*
				 * if (!tempLsh.equals("")) { for (BusCertifyInfo c :
				 * busCertifyInfos) { if (c.getLsh().equals(tempLsh)) { Date
				 * lrsj = DateUtil.string2Date(DateUtil.date2String(c.getZzrq(),
				 * "yyyy-MM-dd"), "yyyy-MM-dd"); Calendar ca =
				 * Calendar.getInstance(); ca.setTime(lrsj);
				 * ca.add(Calendar.DATE, bgyxts); if
				 * (currentDate.compareTo(ca.getTime()) > 0) { //
				 * 暂用lrmac字段存储广告证是否过期的状态 tempBusGgjgclsb.setLrmac("true"); }
				 * break; } } }
				 */

				// 翻译号牌种类
				tempBusGgjgclsb.setHpzl(hpzlMap.get(checkStrNullAndReturn(tempBusGgjgclsb.getHpzl())));
				// 翻译公交公司名称
				tempBusGgjgclsb.setGjgsid(gjgsMap.get(checkStrNullAndReturn(tempBusGgjgclsb.getGjgsid())));
				// 翻译验车部门
				tempBusGgjgclsb.setYcmjbmKj(bmMap.get(checkStrNullAndReturn(tempBusGgjgclsb.getYcmjbmKj())));
				String tempBm = checkStrNullAndReturn(tempBusGgjgclsb.getFpYcmjbm());
				if (!tempBm.equals("")) {
					// 翻译分配验车部门
					tempBusGgjgclsb.setFpYcmjbm(bmMap.get(tempBm));
				}
				// 翻译状态
				if (lsztMap != null) {
					tempBusGgjgclsb.setZtMc(checkStrNullAndReturn(lsztMap.get(tempBusGgjgclsb.getZt())));
				}

				busGgjgclsbs.add(tempBusGgjgclsb);
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("currentDate", currentDate);
		request.setAttribute("lsh", lsh);
		request.setAttribute("lszt", lszt);
		request.setAttribute("hphm", hphm);
		request.setAttribute("hpzl", hpzl);
		request.setAttribute("gjgs", gjgs);
		request.setAttribute("gggs", gggs);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("comefrom", comefrom);

		request.setAttribute("lsztMap", lsztMap);
		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("gjgsMap", gjgsMap);
		request.setAttribute("gggsMap", gggsMap);

		request.setAttribute("busGgjgclsbs", busGgjgclsbs);

	}

	/**
	 * 查询流水详细
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getLscx_shwoDetail(HttpServletRequest request) throws Exception {

		BusGgjgclsb busGgjgclsb = null;
		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
		// 公交车
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		// 所属公交公司
		BusBase busBase = new BusBase();

		// 当前广告喷涂业务对应的公交车
		BusVehicleBase currentBus = null;
		// 广告证
		BusCertifyInfo busCertifyInfo = null;

		if (!lsh.equals("")) {

			Object obj = defaultDao.getRepository(lsh, BusGgjgclsb.class);
			if (obj != null) {

				busGgjgclsb = (BusGgjgclsb) obj;

				Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(true);
				Map<String, String> cllxMap = gjbaxxspService.getCllxMap(true);
				Map<String, String> lsztMap = gjclycxxglService.getBusSzjdMap(null, "LSZT", null);
				List<BusGgjgdlr> busGgjgdlrs = gjclycxxglService.getBusGgjgdlrs(busGgjgclsb.getGgjgid(), "T");

				busVehicleBases = gjclycxxglService.getBusVehicleBase(busGgjgclsb.getGjgsid(), null, null, "1");

				// 查询当前广告喷涂业务对应的公交车信息
				for (BusVehicleBase bus : busVehicleBases) {
					String hphm = checkStrNullAndReturn(bus.getHphm());
					String hpzl = checkStrNullAndReturn(bus.getHpzl());

					if (hphm.equals(checkStrNullAndReturn(busGgjgclsb.getHphm())) && hpzl.equals(checkStrNullAndReturn(busGgjgclsb.getHpzl()))) {
						currentBus = bus;
						break;
					}
				}
				if (busGgjgclsb != null) {

					Object tempBusBase = defaultDao.getRepository(busGgjgclsb.getGjgsid(), BusBase.class);
					if (tempBusBase != null) {
						busBase = (BusBase) tempBusBase;
					}

					Object tempObject = defaultDao.getRepository(busGgjgclsb.getLsh(), BusCertifyInfo.class);
					if (tempObject != null) {
						busCertifyInfo = (BusCertifyInfo) tempObject;
					}
				}

				// 翻译号牌种类
				if (busGgjgclsb != null && hpzlMap != null) {
					busGgjgclsb.setHpzl(checkStrNullAndReturn(hpzlMap.get(busGgjgclsb.getHpzl())));
				}
				// 翻译车辆类型
				if (currentBus != null && cllxMap != null) {
					currentBus.setCllx(checkStrNullAndReturn(cllxMap.get(currentBus.getCllx())));
				}

				// 翻译代办员
				if (busGgjgclsb != null && busGgjgdlrs != null) {
					for (BusGgjgdlr busGgjgdlr : busGgjgdlrs) {
						if (busGgjgdlr.getXh().equals(busGgjgclsb.getDlrxh())) {
							busGgjgclsb.setDlrxh(checkStrNullAndReturn(busGgjgdlr.getXm()));
							break;
						}
					}

				}

				// 翻译流水状态
				if (busGgjgclsb != null && lsztMap != null) {
					busGgjgclsb.setZtMc(checkStrNullAndReturn(lsztMap.get(busGgjgclsb.getZt())));
				}

			}
		}

		request.setAttribute("busVehicleBases", busVehicleBases);
		request.setAttribute("currentBus", currentBus);
		request.setAttribute("busBase", busBase);
		request.setAttribute("busGgjgclsb", busGgjgclsb);
		request.setAttribute("busCertifyInfo", busCertifyInfo);
	}

	/**
	 * 根据流水号查询广告证
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getBusGgjgclsbByLsh(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		BusGgjgclsb busGgjgclsb = new BusGgjgclsb();
		BusVehicleBase busVehicleBase = new BusVehicleBase();
		// 广告证
		BusCertifyInfo busCertifyInfo = new BusCertifyInfo();

		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));

		Map<String, String> hpzlMap = new HashMap<String, String>();
		Map<String, String> cllxMap = new HashMap<String, String>();

		// 公交公司名称
		String gjgsmc = "";

		if (user != null) {

			if (!lsh.equals("")) {

				Object obj_BusGgjgclsb = defaultDao.getRepository(lsh, BusGgjgclsb.class);
				if (obj_BusGgjgclsb != null) {
					busGgjgclsb = (BusGgjgclsb) obj_BusGgjgclsb;

					Object obj_BusCertifyInfo = defaultDao.getRepository(lsh, BusCertifyInfo.class);
					if (obj_BusCertifyInfo != null) {
						busCertifyInfo = (BusCertifyInfo) obj_BusCertifyInfo;
					}

					String hphm = checkStrNullAndReturn(busGgjgclsb.getHphm());
					String hpzl = checkStrNullAndReturn(busGgjgclsb.getHpzl());
					List<Object> tempBusList = defaultDao.getRepositories(" from BusVehicleBase a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ");
					if (tempBusList != null && tempBusList.size() > 0) {
						busVehicleBase = (BusVehicleBase) tempBusList.get(0);

						hpzlMap = gjbaxxspService.getHpzlMap(true);
						cllxMap = gjbaxxspService.getCllxMap(true);

					}

					String gjgsid = checkStrNullAndReturn(busGgjgclsb.getGjgsid());
					Map<String, String> busBaseMap = ggzxxglService.getBusBases(gjgsid, null);
					gjgsmc = busBaseMap.get(gjgsid);
				}

			}

		}

		request.setAttribute("busGgjgclsb", busGgjgclsb);
		request.setAttribute("busVehicleBase", busVehicleBase);
		request.setAttribute("busCertifyInfo", busCertifyInfo);

		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("cllxMap", cllxMap);

		request.setAttribute("gjgsmc", gjgsmc);
	}

	/**
	 * 广告证档案查询
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getGgzdacx(HttpServletRequest request) throws Exception {

		List<BusCertifyInfo> busCertifyInfos = new ArrayList<BusCertifyInfo>();

		Map<String, String> hpzlMap = new HashMap<String, String>();
		// 公交公司
		Map<String, String> gjgsMap = new HashMap<String, String>();
		// 广告机构
		Map<String, String> gggsMap = new HashMap<String, String>();
		// 广告机构
		List<BusGgjgBase> ggjgList = new ArrayList<BusGgjgBase>();

		Map<String, String> bmMap = new HashMap<String, String>();

		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
		String hphm = checkStrNullAndReturn(request.getParameter("hphm"));
		String hpzl = checkStrNullAndReturn(request.getParameter("hpzl"));
		// 公交公司id或公司公司名称
		String gjgs = checkStrNullAndReturn(request.getParameter("gjgs"));
		// 广告公司组织机构代码或广告公司名称
		String gggs = checkStrNullAndReturn(request.getParameter("gggs"));

		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		// 默认查询30天之内的数据
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		String realEndTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
		calendar.add(Calendar.DATE, -30);
		String realStartTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

		if (startTime.equals("") || endTime.equals("")) {
			startTime = realStartTime;
			endTime = realEndTime;
		}

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusCertifyInfo a where 1=1 ";

		if (!lsh.equals("")) {
			hql += " and a.lsh='" + lsh + "' ";
		}

		if (!hphm.equals("")) {
			hql += " and a.hphm='" + hphm + "' ";
		}

		if (!hpzl.equals("")) {
			hql += " and a.hpzl ='" + hpzl + "' ";
		}

		if (!gjgs.equals("")) {
			hql += " and a.gjgsid='" + gjgs + "' ";
		}

		if (!gggs.equals("")) {
			hql += " and a.ggjgid='" + gggs + "' ";
		}

		if (!startTime.equals("") && !endTime.equals("")) {
			hql += " and a.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') ";
		}

		gjgsMap = ggzxxglService.getBusBases(null, null);
		gggsMap = gjbaxxspService.getGggsMap(false);

		if (!gjgs.equals("")) {

			StringBuffer tempStr = new StringBuffer();
			tempStr.append("(");

			List<String> tempList = new ArrayList<String>();

			for (Entry<String, String> tempEntry : gjgsMap.entrySet()) {
				String gjgsid = checkStrNullAndReturn(tempEntry.getKey());
				String gjgsmc = checkStrNullAndReturn(tempEntry.getValue());
				if (gjgsid.contains(gjgs) || gjgsmc.contains(gjgs)) {
					tempList.add(gjgsid);
				}
			}

			for (int i = 0; i < tempList.size(); i++) {
				if (i < tempList.size() - 1) {
					tempStr.append(" '" + tempList.get(i) + "', ");
				} else {
					tempStr.append(" '" + tempList.get(i) + "' ");
				}
			}

			if (!tempStr.toString().equals("(") && !tempStr.toString().equals("")) {
				tempStr.append(" ) ");
				hql += " and a.gjgsid in  " + tempStr;

			}
		}

		if (!gggs.equals("")) {

			StringBuffer tempStr = new StringBuffer();

			tempStr.append("(");

			ggjgList = gjbaxxspService.getBusGgjgBases(null, null, null);

			for (int i = 0; i < ggjgList.size(); i++) {

				String tempGgjgid = checkStrNullAndReturn(ggjgList.get(i).getGgjgid());
				String tempZzjgdh = checkStrNullAndReturn(ggjgList.get(i).getZzjgdm());
				String tempGgjgmc = checkStrNullAndReturn(ggjgList.get(i).getGgjgmc());

				if (tempZzjgdh.contains(gggs) || tempGgjgmc.contains(gggs)) {
					if (i < ggjgList.size() - 1) {
						tempStr.append(" '" + tempGgjgid + "', ");
					} else {
						tempStr.append(" '" + tempGgjgid + "' ");
					}
				}

			}

			if (!tempStr.toString().equals("(") && !tempStr.toString().equals("")) {
				tempStr.append(" ) ");
				hql += " and a.ggjgid in  " + tempStr;
			}

		}

		hql += " order by a.lsh desc ";

		hpzlMap = gjbaxxspService.getHpzlMap(false);

		List<Object> tempList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		if (tempList != null && tempList.size() > 0) {

			bmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", null);
			// bmMap = gjclycxxglService.getBmMap(null, null, false);

			for (Object obj : tempList) {
				BusCertifyInfo tempBusCertifyInfo = (BusCertifyInfo) obj;

				// 翻译号牌种类
				// tempBusCertifyInfo.setHpzl(hpzlMap.get(checkStrNullAndReturn(tempBusCertifyInfo.getHpzl())));
				// 翻译制证部门
				tempBusCertifyInfo.setZzmjbmKj(bmMap.get(checkStrNullAndReturn(tempBusCertifyInfo.getZzmjbmKj())));

				busCertifyInfos.add(tempBusCertifyInfo);
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("lsh", lsh);
		request.setAttribute("hphm", hphm);
		request.setAttribute("hpzl", hpzl);
		request.setAttribute("gjgs", gjgs);
		request.setAttribute("gggs", gggs);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("gjgsMap", gjgsMap);
		request.setAttribute("gggsMap", gggsMap);

		request.setAttribute("busCertifyInfos", busCertifyInfos);

	}

	/**
	 * 审核情况统计
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getShqktj(HttpServletRequest request) throws Exception {

		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		// 0未审核 1审核通过 E办结 Q退办
		String zt = checkStrNullAndReturn(request.getParameter("zt"));
		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		// 总体统计数据
		List<Object> tjList_total = new ArrayList<Object>();
		// 页面展示的数据集合
		List<Object> real_tjList_total = new ArrayList<Object>();
		// 审核情况统计
		List<Object> tjList = new ArrayList<Object>();
		// 详细申报流水数据
		List<BusGgjgclsb> sbList = new ArrayList<BusGgjgclsb>();

		Map<String, String> hpzlMap = new HashMap<String, String>();
		// 预约验车部门
		Map<String, String> yyycbmMap = new HashMap<String, String>();
		// 所有部门
		Map<String, String> allBmMap = new HashMap<String, String>();

		// 状态(包括：0未审核 1审核通过 E办结，已发证 Q退办)
		Map<String, String> ztMap = gjclycxxglService.getBusSzjdMap(null, "LSZT", null);

		// 车辆类型
		Map<String, String> cllxMap = new HashMap<String, String>();

		// 车辆信息
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		if (user != null) {

			// 部门申报情况统计(申报数量 审核数量 退办数量 发证数量)
			StringBuffer sql_total = new StringBuffer();
			StringBuffer sql = new StringBuffer();

			if (qx.equals("kj")) {
				String[] currentUserkjBm = gjclycxxglService.getCurrentUserKjBm(checkStrNullAndReturn(user.getBmid()));
				yyycbm = currentUserkjBm[0];
				yyycbmMap = gjclycxxglService.getBusSzjdMap(yyycbm, "YYBM", null);
				// yyycbmMap = gjclycxxglService.getBmMap(yyycbm, null, false);
			} else {
				yyycbmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", null);
				// yyycbmMap = gjclycxxglService.getBmMap(null, null, false);
			}

			// 所有部门
			allBmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", null);
			// allBmMap = gjclycxxglService.getBmMap(null, null, true);

			// 默认查询30天之内的数据
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String realEndTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
			calendar.add(Calendar.DATE, -30);
			String realStartTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

			if (startTime.equals("") || endTime.equals("")) {
				startTime = realStartTime;
				endTime = realEndTime;
			}

			if (!yyycbm.equals("")) {
				if (qx.equals("kj")) {
					sql_total.append(" select a.ycmjbm_kj, '' as ycmjbm_kj_mc, 0 as sb, 0 as sh, 0 as tb,0 as bj,0 as fzsl,a.ycmjcode,a.ycmjxm from bus_ggjgclsb a where  1=1  ");
					sql_total.append(" and a.ycmjbm_kj='" + yyycbm + "' ");
					sql_total.append(" and a.ycmjcode is not null ");
					sql
							.append(" select b.ycmjbm_kj,b.sh,b.tb,(select count(1) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as bj, (select sum(c.dzcs) from BUS_CERTIFY_INFO c where c.zzmjbm = b.ycmjbm_kj) as zzsl,b.ycmjcode,b.ycmjxm from ( ");
					sql.append(" select a.ycmjbm_kj, count(1) as sh, sum(decode(a.ycjg, '2', 1, 0)) as tb, a.ycmjcode,a.ycmjxm from bus_ggjgclsb a where 1 = 1 ");
				} else {

					sql_total.append(" select a.ycmjbm_kj, '' as ycmjbm_kj_mc, 0 as sb, 0 as sh, 0 as tb,0 as bj,0 as fzsl from bus_ggjgclsb a where  1=1  ");
					sql_total.append(" and a.ycmjbm_kj='" + yyycbm + "' ");

					sql
							.append(" select b.ycmjbm_kj,b.sh,b.tb,(select count(1) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as bj, (select sum(c.dzcs) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as zzsl from ( ");
					sql.append(" select a.ycmjbm_kj, count(1) as sh, sum(decode(a.ycjg, '2', 1, 0)) as tb from bus_ggjgclsb a where 1 = 1 ");
				}

				sql.append(" and a.ycmjbm_kj='" + yyycbm + "' ");

				if (!zt.equals("")) {
					sql_total.append(" and a.zt='" + zt + "' ");
					sql.append(" and a.zt='" + zt + "' ");
				} else {
					sql_total.append(" and a.zt in ('2','E','CQ') ");
					sql.append(" and a.zt in ('2','E','CQ') ");
				}

			} else {
				sql_total.append(" select a.ycmjbm_kj, '' as ycmjbm_kj_mc, 0 as sb, 0 as sh, 0 as tb,0 as bj,0 as fzsl from bus_ggjgclsb a where  1=1  ");

				if (qx.equals("kj")) {
					sql
							.append(" select b.ycmjbm_kj,b.sh,b.tb,(select count(1) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as bj, (select sum(c.dzcs) from BUS_CERTIFY_INFO c where c.zzmjbm = b.ycmjbm_kj) as zzsl from ( ");
					sql.append(" select a.ycmjbm_kj, count(1) as sh, sum(decode(a.ycjg, '2', 1, 0)) as tb, a.ycmjcode,a.ycmjxm from bus_ggjgclsb a where 1 = 1 ");
				} else {
					sql
							.append(" select b.ycmjbm_kj,b.sh,b.tb,(select count(1) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as bj, (select sum(c.dzcs) from BUS_CERTIFY_INFO c where c.zzmjbm_kj = b.ycmjbm_kj) as zzsl from ( ");
					sql.append(" select a.ycmjbm_kj, count(1) as sh, sum(decode(a.ycjg, '2', 1, 0)) as tb from bus_ggjgclsb a where 1 = 1 ");
				}

				if (!zt.equals("")) {
					sql_total.append(" and a.zt='" + zt + "' ");
					sql.append(" and a.zt='" + zt + "' ");
				} else {
					sql_total.append(" and a.zt in ('2','E','CQ') ");
					sql.append(" and a.zt in ('2','E','CQ') ");
				}

			}

			// 申报时间
			sql_total.append(" and a.lrsj  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss')");
			// 申报时间
			sql.append(" and a.lrsj  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss')");

			if (qx.equals("kj")) {
				sql_total.append(" group by a.ycmjbm_kj, a.ycmjcode, a.ycmjxm ");
				sql.append(" group by a.ycmjbm_kj,a.ycmjcode,a.ycmjxm ) b ");
			} else {
				sql_total.append(" group by a.ycmjbm_kj ");
				sql.append(" group by a.ycmjbm_kj ) b ");
			}

			tjList_total = defaultDao.findSQL(sql_total.toString());

			if (tjList_total != null && tjList_total.size() > 0) {

				for (Object tempObj : tjList_total) {
					if (tempObj != null) {
						Object[] temp = (Object[]) tempObj;
						temp[1] = checkStrNullAndReturn(allBmMap.get(temp[0]));
					}
				}
			}

			if (tjList_total != null && tjList_total.size() > 0) {

				tjList = defaultDao.findSQL(sql.toString());

				for (int i = 0; i < tjList_total.size(); i++) {
					Object[] tempTotal = (Object[]) tjList_total.get(i);
					int sh = 0;
					int tb = 0;
					int bj = 0;
					int fz = 0;
					// 验车部门
					String bm = checkStrNullAndReturn(tempTotal[0]);
					for (int j = 0; j < tjList.size(); j++) {
						Object[] tempObj = (Object[]) tjList.get(j);
						if (bm.equals(checkStrNullAndReturn(tempObj[0]))) {
							// 审核
							int tempSh = Integer.parseInt(tempObj[1] == null ? "0" : tempObj[1].toString());
							// 退办
							int tempTb = Integer.parseInt(tempObj[2] == null ? "0" : tempObj[2].toString());
							// 办结
							int tempBj = Integer.parseInt(tempObj[3] == null ? "0" : tempObj[3].toString());
							// 发证数量
							int tempFz = Integer.parseInt(tempObj[4] == null ? "0" : tempObj[4].toString());

							sh += tempSh;
							tb += tempTb;
							bj += tempBj;
							fz += tempFz;
						}
					}

					tempTotal[3] = sh;
					tempTotal[4] = tb;
					tempTotal[5] = bj;
					tempTotal[6] = fz;

				}

				real_tjList_total.addAll(tjList_total);

				if (yyycbm.equals("")) {
					// 处理数据库中没有参与审核的部门数据
					for (Entry<String, String> tempBm : yyycbmMap.entrySet()) {
						boolean hasBm = false;
						String tempOutBm = checkStrNullAndReturn(tempBm.getKey());
						String tempOutBmmc = checkStrNullAndReturn(tempBm.getValue());
						for (Object temp : tjList_total) {
							if (temp != null) {
								Object[] tempObj = (Object[]) temp;
								String tempInBm = checkStrNullAndReturn(tempObj[0]);
								if (tempInBm.equals(tempOutBm)) {
									hasBm = true;
									break;
								}
							}
						}
						// 如果在查处的集合中不存在该部门,则把该部门补全到当前集合中
						if (!hasBm) {
							Object[] t = { "", tempOutBmmc, "--", "--", "--", "--", "--", "--", "--" };
							real_tjList_total.add(t);
						}
					}
				}

				hpzlMap = gjbaxxspService.getHpzlMap(false);
				cllxMap = gjbaxxspService.getCllxMap(false);
				busVehicleBases = gjbaxxspService.getBusVehicleBaseList(null, null, null);

				// 申报的广告
				String hql = " from BusGgjgclsb a where 1=1 ";

				if (!yyycbm.equals("")) {
					hql += " and a.yyycbm='" + yyycbm + "' ";
				}

				if (!zt.equals("")) {
					hql += " and a.zt='" + zt + "' ";
				}

				hql += " and a.lrsj  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";

				List<Object> tempList = defaultDao.getRepositories(hql);

				for (Object obj : tempList) {

					BusGgjgclsb tempBusGgjgclsb = (BusGgjgclsb) obj;

					String hphm = checkStrNullAndReturn(tempBusGgjgclsb.getHphm());
					String hpzl = checkStrNullAndReturn(tempBusGgjgclsb.getHpzl());

					// 翻译号牌种类
					tempBusGgjgclsb.setHpzl(hpzlMap.get(checkStrNullAndReturn(hpzl)));

					String tempZt = checkStrNullAndReturn(tempBusGgjgclsb.getZt());
					if (tempZt.equals("0")) {
						tempBusGgjgclsb.setZt("未审核");
					} else if (tempZt.equals("1")) {
						tempBusGgjgclsb.setZt("审核通过");
					} else if (tempZt.equals("2")) {
						tempBusGgjgclsb.setZt("内网审核成功");
					} else if (tempZt.equals("E")) {
						tempBusGgjgclsb.setZt("已办结");
					} else if (tempZt.equals("CQ")) {
						tempBusGgjgclsb.setZt("退办");
					}

					for (BusVehicleBase tempBusVehicleBase : busVehicleBases) {
						if (tempBusVehicleBase.getHphm().equals(hphm) && tempBusVehicleBase.getHpzl().equals(hpzl)) {
							String cllx = checkStrNullAndReturn(tempBusVehicleBase.getCllx());
							// 暂用lrmac字段存在翻译后的车辆类型(页面显示用)
							tempBusGgjgclsb.setLrmac(cllxMap.get(cllx));
						}
					}

					sbList.add(tempBusGgjgclsb);

				}

			}
		}

		request.setAttribute("qx", qx);
		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("zt", zt);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("yyycbmMap", yyycbmMap);
		request.setAttribute("ztMap", ztMap);

		request.setAttribute("tjList_total", real_tjList_total);
		request.setAttribute("tjList", tjList);
		request.setAttribute("sbList", sbList);

	}

	public void getShqktj_detail(HttpServletRequest request) throws Exception {

		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 审核部门科级
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		String yyycbmmc = "";
		// 验车民警
		String ycmjcode = checkStrNullAndReturn(request.getParameter("ycmjcode"));
		// 0未审核 1审核通过 E办结 Q退办
		String zt = checkStrNullAndReturn(request.getParameter("zt"));
		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		// 详细申报流水数据
		List<BusGgjgclsb> sbList = new ArrayList<BusGgjgclsb>();

		Map<String, String> hpzlMap = new HashMap<String, String>();

		// 车辆类型
		Map<String, String> cllxMap = new HashMap<String, String>();

		// 车辆信息
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			hpzlMap = gjbaxxspService.getHpzlMap(false);
			cllxMap = gjbaxxspService.getCllxMap(false);
			busVehicleBases = gjbaxxspService.getBusVehicleBaseList(null, null, null);

			// 申报的广告
			String hql = " from BusGgjgclsb a where 1=1 ";

			if (!yyycbm.equals("")) {
				hql += " and a.ycmjbmKj='" + yyycbm + "' ";
				Map<String, String> yyycbmMap = gjclycxxglService.getBusSzjdMap(yyycbm, "YYBM", null);
				// Map<String, String> yyycbmMap =
				// gjclycxxglService.getBmMap(yyycbm, null, false);
				yyycbmmc = checkStrNullAndReturn(yyycbmMap.get(yyycbm));
			}

			if (!ycmjcode.equals("")) {
				hql += " and a.ycmjcode='" + ycmjcode + "' ";
			}

			if (!zt.equals("")) {
				hql += " and a.zt='" + zt + "' ";
			}

			hql += " and a.lrsj  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";

			List<Object> tempList = defaultDao.findHQLByPage(hql, offset, pagesize);
			rscount = defaultDao.getRepositoryByHQLListSize(" select count(*)  " + hql);

			for (Object obj : tempList) {

				BusGgjgclsb tempBusGgjgclsb = (BusGgjgclsb) obj;

				String hphm = checkStrNullAndReturn(tempBusGgjgclsb.getHphm());
				String hpzl = checkStrNullAndReturn(tempBusGgjgclsb.getHpzl());

				// 翻译号牌种类
				tempBusGgjgclsb.setHpzl(hpzlMap.get(checkStrNullAndReturn(hpzl)));

				String tempZt = checkStrNullAndReturn(tempBusGgjgclsb.getZt());
				if (tempZt.equals("0")) {
					tempBusGgjgclsb.setZt("未审核");
				} else if (tempZt.equals("1")) {
					tempBusGgjgclsb.setZt("审核通过");
				} else if (tempZt.equals("2")) {
					tempBusGgjgclsb.setZt("内网审核成功");
				} else if (tempZt.equals("E")) {
					tempBusGgjgclsb.setZt("已办结");
				} else if (tempZt.equals("Q")) {
					tempBusGgjgclsb.setZt("退办");
				}

				for (BusVehicleBase tempBusVehicleBase : busVehicleBases) {
					if (tempBusVehicleBase.getHphm().equals(hphm) && tempBusVehicleBase.getHpzl().equals(hpzl)) {
						String cllx = checkStrNullAndReturn(tempBusVehicleBase.getCllx());
						// 暂用lrmac字段存在翻译后的车辆类型(页面显示用)
						tempBusGgjgclsb.setLrmac(cllxMap.get(cllx));
					}
				}

				sbList.add(tempBusGgjgclsb);

			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("yyycbmmc", yyycbmmc);
		request.setAttribute("ycmjcode", ycmjcode);
		request.setAttribute("zt", zt);
		request.setAttribute("qx", qx);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("sbList", sbList);

	}

	/**
	 * 证件发放情况统计
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getZjffqktj(HttpServletRequest request) throws Exception {

		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		// 总体统计数据
		List<Object> tjList = new ArrayList<Object>();
		// 页面显示数据
		List<Object> real_tjList = new ArrayList<Object>();

		Map<String, String> hpzlMap = new HashMap<String, String>();
		// 预约验车部门
		Map<String, String> yyycbmMap = new HashMap<String, String>();

		// 车辆类型
		Map<String, String> cllxMap = new HashMap<String, String>();

		// 车辆信息
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			if (qx.equals("kj")) {
				String[] currentUserKjBm = gjclycxxglService.getCurrentUserKjBm(checkStrNullAndReturn(user.getBmid()));

				yyycbm = currentUserKjBm[0];
				yyycbmMap = gjclycxxglService.getBusSzjdMap(yyycbm, "YYBM", null);
				// yyycbmMap = gjclycxxglService.getBmMap(yyycbm, null, false);
			} else {
				yyycbmMap = gjclycxxglService.getBusSzjdMap(null, "YYBM", null);
				// yyycbmMap = gjclycxxglService.getBmMap(null, null, false);
			}

			// 默认查询30天之内的数据
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			String realEndTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
			calendar.add(Calendar.DATE, -30);
			String realStartTime = DateUtil.date2String(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");

			if (startTime.equals("") || endTime.equals("")) {
				startTime = realStartTime;
				endTime = realEndTime;
			}

			StringBuffer sql = new StringBuffer();

			if (!yyycbm.equals("")) {

				if (qx.equals("kj")) {
					sql.append(" select c.zzmjbm_kj, d.Org_Name, count(1) as zs,sum(decode(c.t, 'false', 1, 0)) as cz,c.zzmjcode,c.zzmjxm from ");
					sql.append(" ( ");
					sql.append(" select a.zzmjbm_kj,a.zzmjcode,a.zzmjxm, 'true' as t from BUS_CERTIFY_INFO a where a.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('"
							+ endTime + "','yyyy-MM-dd HH24:mi:ss') and a.zzmjbm='" + yyycbm + "'  ");
				} else {
					sql.append(" select c.zzmjbm_kj, d.Org_Name, count(1) as zs,sum(decode(c.t, 'false', 1, 0)) as cz from ");
					sql.append(" ( ");
					sql.append(" select a.zzmjbm_kj, 'true' as t from BUS_CERTIFY_INFO a where a.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
							+ "','yyyy-MM-dd HH24:mi:ss') and a.zzmjbm_kj='" + yyycbm + "'  ");
				}

			} else {
				if (qx.equals("kj")) {
					sql.append(" select c.zzmjbm_kj, d.Org_Name, count(1) as zs,sum(decode(c.t, 'false', 1, 0)) as cz,c.zzmjcode,c.zzmjxm from ");
					sql.append(" ( ");
					sql.append(" select a.zzmjbm_kj,a.zzmjcode,a.zzmjxm, 'true' as t from BUS_CERTIFY_INFO a where a.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('"
							+ endTime + "','yyyy-MM-dd HH24:mi:ss')  ");
				} else {
					sql.append(" select c.zzmjbm_kj, d.Org_Name, count(1) as zs,sum(decode(c.t, 'false', 1, 0)) as cz from ");
					sql.append(" ( ");
					sql.append(" select a.zzmjbm_kj, 'true' as t from BUS_CERTIFY_INFO a where a.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
							+ "','yyyy-MM-dd HH24:mi:ss')  ");
				}

			}

			sql.append(" union all ");

			if (!yyycbm.equals("")) {
				if (qx.equals("kj")) {
					sql.append(" select b.zzmjbm_kj,b.zzmjcode,b.zzmjxm, 'false' as from BUS_CERTIFY_CZCD b where b.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('"
							+ endTime + "','yyyy-MM-dd HH24:mi:ss') and b.zzmjbm_kj='" + yyycbm + "' ");
				} else {
					sql.append(" select b.zzmjbm_kj, 'false' as from BUS_CERTIFY_CZCD b where b.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
							+ "','yyyy-MM-dd HH24:mi:ss') and b.zzmjbm_kj='" + yyycbm + "' ");
				}

			} else {
				if (qx.equals("kj")) {
					sql.append(" select b.zzmjbm_kj,b.zzmjcode,b.zzmjxm, 'false' as from BUS_CERTIFY_CZCD b where b.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('"
							+ endTime + "','yyyy-MM-dd HH24:mi:ss') ");
				} else {
					sql.append(" select b.zzmjbm_kj, 'false' as from BUS_CERTIFY_CZCD b where b.zzrq between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime
							+ "','yyyy-MM-dd HH24:mi:ss') ");
				}

			}

			sql.append(" ) c ");
			sql.append(" left join v_veh_org_ycs d on c.zzmjbm_kj = d.ORG_Id ");

			if (qx.equals("kj")) {
				sql.append("  group by c.zzmjbm_kj, d.Org_Name,c.zzmjcode,c.zzmjxm ");
			} else {
				sql.append("  group by c.zzmjbm_kj, d.Org_Name ");
			}

			tjList = defaultDao.findSQLByPage(sql.toString(), offset, pagesize);
			String sql_count = " select count(1) from ( " + sql + " ) ";
			rscount = defaultDao.getRepositoryBySQLListSize(sql_count);

			if (tjList != null && tjList.size() > 0) {

				real_tjList.addAll(tjList);

				if (yyycbm.equals("")) {
					// 处理数据库中没有参与审核的部门数据
					for (Entry<String, String> tempBm : yyycbmMap.entrySet()) {
						boolean hasBm = false;
						String tempOutBm = checkStrNullAndReturn(tempBm.getKey());
						String tempOutBmmc = checkStrNullAndReturn(tempBm.getValue());
						for (Object temp : tjList) {
							if (temp != null) {
								Object[] tempObj = (Object[]) temp;
								String tempInBm = checkStrNullAndReturn(tempObj[0]);
								if (tempInBm.equals(tempOutBm)) {
									hasBm = true;
									break;
								}
							}
						}
						// 如果在查处的集合中不存在该部门,则把该部门补全到当前集合中
						if (!hasBm) {
							Object[] t = { "", tempOutBmmc, "--", "--", "--", "--" };
							real_tjList.add(t);
						}
					}
				}

				hpzlMap = gjbaxxspService.getHpzlMap(false);
				cllxMap = gjbaxxspService.getCllxMap(false);
				busVehicleBases = gjbaxxspService.getBusVehicleBaseList(null, null, null);

				String hql_info = " from BusCertifyInfo a where 1=1 ";
				String hql_czcd = " from BusCertifyCzcd a where 1=1 ";

				if (!yyycbm.equals("")) {
					hql_info += " and a.zzmjbm='" + yyycbm + "' ";
					hql_czcd += " and a.zzmjbm='" + yyycbm + "' ";
				}

				hql_info += " and a.zzrq  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";
				hql_czcd += " and a.zzrq  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";

			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("qx", qx);
		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("yyycbmMap", yyycbmMap);

		request.setAttribute("tjList", real_tjList);

	}

	public void getZjffqktj_detail(HttpServletRequest request) throws Exception {

		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 预约验车部门
		String yyycbm = checkStrNullAndReturn(request.getParameter("yyycbm"));
		// 预约验车部门名称
		String yyycbmmc = checkStrNullAndReturn(request.getParameter("yyycbm"));
		// 发证民警
		String zzmjcode = checkStrNullAndReturn(request.getParameter("zzmjcode"));

		String startTime = checkStrNullAndReturn(request.getParameter("startTime"));
		String endTime = checkStrNullAndReturn(request.getParameter("endTime"));

		// 详细广告证信息
		List<BusCertifyInfo> sbList_info = new ArrayList<BusCertifyInfo>();
		// 详细广告证信息(错证)
		List<BusCertifyCzcd> sbList_czcd = new ArrayList<BusCertifyCzcd>();

		Map<String, String> hpzlMap = new HashMap<String, String>();
		// 预约验车部门
		Map<String, String> yyycbmMap = new HashMap<String, String>();

		// 车辆类型
		Map<String, String> cllxMap = new HashMap<String, String>();

		// 车辆信息
		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			hpzlMap = gjbaxxspService.getHpzlMap(false);
			cllxMap = gjbaxxspService.getCllxMap(false);

			busVehicleBases = gjbaxxspService.getBusVehicleBaseList(null, null, null);

			String hql_info = " from BusCertifyInfo a where 1=1 ";
			String hql_czcd = " from BusCertifyCzcd a where 1=1 ";

			if (!yyycbm.equals("")) {
				hql_info += " and a.zzmjbmKj='" + yyycbm + "' ";
				hql_czcd += " and a.zzmjbmKj='" + yyycbm + "' ";

				yyycbmMap = gjclycxxglService.getBusSzjdMap(yyycbm, "YYBM", null);
				// yyycbmMap = gjclycxxglService.getBmMap(yyycbm, null, false);

				yyycbmmc = checkStrNullAndReturn(yyycbmMap.get(yyycbm));
			}

			hql_info += " and a.zzrq  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";
			hql_czcd += " and a.zzrq  between to_date('" + startTime + "','yyyy-MM-dd HH24:mi:ss') and to_date('" + endTime + "','yyyy-MM-dd HH24:mi:ss') order by a.lsh desc ";

			// 正常发证
			List<Object> tempList_info = defaultDao.findHQLByPage(hql_info, offset, pagesize);
			rscount = defaultDao.getRepositoryByHQLListSize(" select count(*)  " + hql_info);

			for (Object obj : tempList_info) {

				if (obj != null) {
					BusCertifyInfo tempBusCertifyInfo = (BusCertifyInfo) obj;

					// String hphm =
					// checkStrNullAndReturn(tempBusCertifyInfo.getHphm());
					// String hpzl =
					// checkStrNullAndReturn(tempBusCertifyInfo.getHpzl());

					// 翻译车辆类型
					// for (BusVehicleBase tempBusVehicleBase : busVehicleBases)
					// {
					// if (tempBusVehicleBase.getHphm().equals(hphm) &&
					// tempBusVehicleBase.getHpzl().equals(hpzl)) {
					// String cllx =
					// checkStrNullAndReturn(tempBusVehicleBase.getCllx());
					// tempBusCertifyInfo.setCllx(cllxMap.get(cllx));
					// // 用ZzmjbmKj暂存fdjh发动机号(页面显示)
					// tempBusCertifyInfo.setZzmjbmKj(tempBusVehicleBase.getFdjh());
					// }
					// }

					// 翻译号牌种类
					// tempBusCertifyInfo.setHpzl(hpzlMap.get(checkStrNullAndReturn(hpzl)));

					sbList_info.add(tempBusCertifyInfo);
				}

			}

			/*
			 * // 错证 List<Object> tempList_czcd =
			 * defaultDao.findHQLByPage(hql_czcd, offset, pagesize); //rscount
			 * += defaultDao.getRepositoryByHQLListSize(" select count(*)  " +
			 * hql_czcd);
			 * 
			 * for (Object obj : tempList_czcd) {
			 * 
			 * if (obj != null) { BusCertifyCzcd tempBusCertifyCzcd =
			 * (BusCertifyCzcd) obj;
			 * 
			 * String hphm =
			 * checkStrNullAndReturn(tempBusCertifyCzcd.getHphm()); String hpzl
			 * = checkStrNullAndReturn(tempBusCertifyCzcd.getHpzl());
			 * 
			 * // 翻译号牌种类
			 * tempBusCertifyCzcd.setHpzl(hpzlMap.get(checkStrNullAndReturn
			 * (hpzl)));
			 * 
			 * // 翻译车辆类型 for (BusVehicleBase tempBusVehicleBase :
			 * busVehicleBases) { if (tempBusVehicleBase.getHphm().equals(hphm)
			 * && tempBusVehicleBase.getHpzl().equals(hpzl)) { String cllx =
			 * checkStrNullAndReturn(tempBusVehicleBase.getCllx());
			 * tempBusCertifyCzcd.setCllx(cllxMap.get(cllx)); //
			 * 用ZzmjbmKj暂存fdjh发动机号(页面显示)
			 * tempBusCertifyCzcd.setZzmjbmKj(tempBusVehicleBase.getFdjh()); } }
			 * 
			 * sbList_czcd.add(tempBusCertifyCzcd); }
			 * 
			 * }
			 */

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("qx", qx);
		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("yyycbmmc", yyycbmmc);
		request.setAttribute("zzmjcode", zzmjcode);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);

		request.setAttribute("sbList_info", sbList_info);
		request.setAttribute("sbList_czcd", sbList_czcd);

	}

	/**
	 * 获取第一笔申报的时间
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getFirstSbTime() throws Exception {

		String result = "";

		String sql = " select to_char(min(a.lrsj),'yyyy-MM-dd HH24:mi:ss') from BUS_GGJGCLSB a ";
		List<Object> temList = defaultDao.findSQL(sql);
		if (temList != null && temList.size() > 0) {
			Object minTime = temList.get(0);
			result = checkStrNullAndReturn(minTime);
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

	public GjbaxxspService getGjbaxxspService() {
		return gjbaxxspService;
	}

	public void setGjbaxxspService(GjbaxxspService gjbaxxspService) {
		this.gjbaxxspService = gjbaxxspService;
	}

	public GgzxxglService getGgzxxglService() {
		return ggzxxglService;
	}

	public void setGgzxxglService(GgzxxglService ggzxxglService) {
		this.ggzxxglService = ggzxxglService;
	}

	public void setDefaultDao(DefaultDao defaultDao) {
		this.defaultDao = defaultDao;
	}

	public GjclycxxglService getGjclycxxglService() {
		return gjclycxxglService;
	}

	public void setGjclycxxglService(GjclycxxglService gjclycxxglService) {
		this.gjclycxxglService = gjclycxxglService;
	}

}
