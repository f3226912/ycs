package com.ycszh.ssh.service.gjgggl.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.gjgggl.BusBase;
import com.ycszh.ssh.hbm.gjgggl.BusCertifyCzcd;
import com.ycszh.ssh.hbm.gjgggl.BusCertifyInfo;
import com.ycszh.ssh.hbm.gjgggl.BusCertifyInfoLog;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsbLog;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsbPhoto;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.service.gjgggl.GgzxxglService;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import com.ycszh.ssh.service.gjgggl.GjclycxxglService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.ToolsUtil;
import com.ycszh.util.gjgggl.GetBarCode;

public class GgzxxglServiceImpl implements GgzxxglService {

	private DefaultDao defaultDao;
	private GjbaxxspService gjbaxxspService;
	private GjclycxxglService gjclycxxglService;

	public void getBusGgjgclsb(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		BusGgjgclsb busGgjgclsb = new BusGgjgclsb();
		BusVehicleBase busVehicleBase = new BusVehicleBase();
		BusCertifyInfo busCertifyInfo = new BusCertifyInfo();

		String t = new Date().getTime() + "";

		String yyycbm = "";
		String lsh = checkStrNullAndReturn(request.getParameter("lshStr"));
		// cj处级 kj科级
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		// 是否首次查询
		String isFirst = checkStrNullAndReturn(request.getParameter("isFirst"));
		String queryResult = "";

		Map<String, String> hpzlMap = new HashMap<String, String>();
		Map<String, String> cllxMap = new HashMap<String, String>();

		String hpzlStr = "";
		String cllxStr = "";

		// 公交公司名称
		String gjgsmc = "";

		if (user != null) {

			String hql = " from BusGgjgclsb a where 1=1 ";

			qx = checkStrNullAndReturn(request.getParameter("qx"));
			/*
			 * if (qx.equals("kj")) { String[] currentUserKjBm =
			 * gjclycxxglService.getCurrentUserKjBm(user.getBmid()); yyycbm =
			 * checkStrNullAndReturn(currentUserKjBm[0]); hql +=
			 * " and a.yyycbm='" + yyycbm + "' "; }
			 */

			String[] currentUserUpBm = gjclycxxglService.getCurrentBmInClglcbm(user.getBmid());
			yyycbm = checkStrNullAndReturn(currentUserUpBm[0]);

			hql += " and a.yyycbm='" + yyycbm + "' ";

			if (!lsh.equals("")) {

				hql += " and  a.zt in ('2','E') and a.lsh='" + lsh + "' ";

				List<Object> tempList = defaultDao.getRepositories(hql);
				if (tempList != null && tempList.size() > 0) {
					busGgjgclsb = (BusGgjgclsb) tempList.get(0);

					// 0未审核 1已审核 2审核成功 E办结 CQ退办
					String zt = checkStrNullAndReturn(busGgjgclsb.getZt());
					if (zt.equals("2") || zt.equals("E")) {
						Object obj_BusCertifyInfo = defaultDao.getRepository(lsh, BusCertifyInfo.class);
						if (obj_BusCertifyInfo != null) {
							busCertifyInfo = (BusCertifyInfo) obj_BusCertifyInfo;
							zt = checkStrNullAndReturn(busCertifyInfo.getZt());
							// 状态(A正常 R注销 F超期注销)
							if (zt.equals("A")) {
								queryResult = "该流水对应的广告证已正常打印。如要重打,请到错证重打功能处打印";
							} else if (zt.equals("R")) {
								queryResult = "该流水对应的广告证已注销，无法打印广告证";
							} else if (zt.equals("F")) {
								queryResult = "该流水对应的广告证已超期注销，无法打印广告证";
							}
						}

						String hphm = checkStrNullAndReturn(busGgjgclsb.getHphm());
						String hpzl = checkStrNullAndReturn(busGgjgclsb.getHpzl());

						List<Object> tempBusList = defaultDao.getRepositories(" from BusVehicleBase a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ");
						if (tempBusList != null && tempBusList.size() > 0) {
							busVehicleBase = (BusVehicleBase) tempBusList.get(0);

							String cllx = checkStrNullAndReturn(busVehicleBase.getCllx());

							hpzlMap = gjbaxxspService.getHpzlMap(true);
							cllxMap = gjbaxxspService.getCllxMap(true);

							hpzlStr = checkStrNullAndReturn(hpzlMap.get(hpzl));
							cllxStr = checkStrNullAndReturn(cllxMap.get(cllx));

							String gjgsid = checkStrNullAndReturn(busGgjgclsb.getGjgsid());
							Map<String, String> busBaseMap = getBusBases(gjgsid, null);
							gjgsmc = busBaseMap.get(gjgsid);
						}
						isFirst = "true";
					}

				}

			}

		}

		request.setAttribute("yyycbm", yyycbm);
		request.setAttribute("lsh", lsh);
		request.setAttribute("qx", qx);
		request.setAttribute("isFirst", isFirst);
		request.setAttribute("queryResult", queryResult);

		request.setAttribute("busGgjgclsb", busGgjgclsb);
		request.setAttribute("busVehicleBase", busVehicleBase);

		request.setAttribute("hpzlStr", hpzlStr);
		request.setAttribute("cllxStr", cllxStr);
		request.setAttribute("gjgsmc", gjgsmc);
		request.setAttribute("t", t);

	}

	/**
	 * 广告证错证重打
	 */
	public void getBusGgjgclsb_cd(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		BusGgjgclsb busGgjgclsb = new BusGgjgclsb();
		BusCertifyInfo busCertifyInfo = new BusCertifyInfo();
		BusVehicleBase busVehicleBase = new BusVehicleBase();

		String lsh = checkStrNullAndReturn(request.getParameter("lshStr"));
		// 是否首次查询
		String isFirst = checkStrNullAndReturn(request.getParameter("isFirst"));
		String queryResult = "";

		Map<String, String> hpzlMap = new HashMap<String, String>();
		Map<String, String> cllxMap = new HashMap<String, String>();

		String hpzlStr = "";
		String cllxStr = "";

		// 公交公司名称
		String gjgsmc = "";

		if (user != null) {

			String hql = " from BusGgjgclsb a where 1=1 ";

			String[] currentUserUpBm = gjclycxxglService.getCurrentBmInClglcbm(user.getBmid());
			String yyycbm = checkStrNullAndReturn(currentUserUpBm[0]);

			hql += " and a.yyycbm='" + yyycbm + "' ";

			if (!lsh.equals("")) {

				hql += " and  a.zt in ('2','E') and a.lsh='" + lsh + "' ";

				List<Object> tempList = defaultDao.getRepositories(hql);

				if (tempList != null && tempList.size() > 0) {
					Object obj_BusGgjgclsb = tempList.get(0);
					busGgjgclsb = (BusGgjgclsb) obj_BusGgjgclsb;

					Object obj_BusCertifyInfo = defaultDao.getRepository(lsh, BusCertifyInfo.class);
					if (obj_BusCertifyInfo != null) {
						busCertifyInfo = (BusCertifyInfo) obj_BusCertifyInfo;
						String zt = checkStrNullAndReturn(busCertifyInfo.getZt());
						// 状态(A正常 R注销 F超期注销)
						if (zt.equals("R")) {
							queryResult = "该流水对应的广告证已注销，无法打印广告证";
						} else if (zt.equals("F")) {
							queryResult = "该流水对应的广告证已超期注销，无法打印广告证";
						}
					} else {
						queryResult = "该流水尚未打印过广告证，请使用正常的广告证打印功能";
					}

					String hphm = checkStrNullAndReturn(busGgjgclsb.getHphm());
					String hpzl = checkStrNullAndReturn(busGgjgclsb.getHpzl());
					List<Object> tempBusList = defaultDao.getRepositories(" from BusVehicleBase a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ");
					if (tempBusList != null && tempBusList.size() > 0) {
						busVehicleBase = (BusVehicleBase) tempBusList.get(0);
						hpzlMap = gjbaxxspService.getHpzlMap(true);
						cllxMap = gjbaxxspService.getCllxMap(true);

						hpzlStr = checkStrNullAndReturn(hpzlMap.get(hpzl));
						String cllx = busVehicleBase.getCllx();
						cllxStr = checkStrNullAndReturn(cllxMap.get(cllx));

					}

					String gjgsid = checkStrNullAndReturn(busGgjgclsb.getGjgsid());
					Map<String, String> busBaseMap = getBusBases(gjgsid, null);
					gjgsmc = busBaseMap.get(gjgsid);

					isFirst = "true";
				}

			}

		}

		request.setAttribute("lsh", lsh);
		request.setAttribute("isFirst", isFirst);
		request.setAttribute("queryResult", queryResult);

		request.setAttribute("busGgjgclsb", busGgjgclsb);
		request.setAttribute("busVehicleBase", busVehicleBase);

		request.setAttribute("hpzlStr", hpzlStr);
		request.setAttribute("cllxStr", cllxStr);

		request.setAttribute("gjgsmc", gjgsmc);

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
					Map<String, String> busBaseMap = getBusBases(gjgsid, null);
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
	 * 添加打印记录信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyInfo(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
			if (!lsh.equals("")) {
				Object obj_CertifyInfo = defaultDao.getRepository(lsh, BusCertifyInfo.class);
				if (obj_CertifyInfo != null) {
					result = "-操作失败：已打印过该广告证，请到错证重打功能处重打广告证";
				} else {

					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));
					String mac = checkStrNullAndReturn(request.getParameter("mac"));

					Object obj_busGgjgclsb = defaultDao.getRepository(lsh, BusGgjgclsb.class);
					if (obj_busGgjgclsb != null) {

						BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj_busGgjgclsb;
						// 办结
						busGgjgclsb.setZt("E");

						busGgjgclsb.setSynFlag("UW");
						busGgjgclsb.setTranDate(null);
						busGgjgclsb.setTranFlag(null);

						defaultDao.updateRepository(busGgjgclsb);

						// 记录广告喷涂业务操作日志
						BusGgjgclsbLog busGgjgclsbLog = new BusGgjgclsbLog();
						busGgjgclsbLog.setCzr(user.getName());
						busGgjgclsbLog.setCzrxm(user.getYgxm());
						busGgjgclsbLog.setCzrbm(user.getBmid());
						busGgjgclsbLog.setCzsj(new Date());
						busGgjgclsbLog.setCzip(ip);
						busGgjgclsbLog.setCzmac(mac);
						busGgjgclsbLog.setCznr("正常打印广告证");
						List<Object> list = new ArrayList<Object>();
						list.add("yyshsl");
						list.add("ztMc");
						defaultDao.addRepositoryLog(busGgjgclsbLog, busGgjgclsb, list);

						Map<String, Object> bazMap = initalBazData(request, lsh);
						if (bazMap != null && bazMap.size() > 0) {
							result = checkStrNullAndReturn(bazMap.get("result"));

							if (result.equals("")) {

								String hphm = checkStrNullAndReturn(bazMap.get("hphm"));
								String hpzl = checkStrNullAndReturn(bazMap.get("hpzl"));
								String cllx = checkStrNullAndReturn(bazMap.get("cllx"));
								String clxh = checkStrNullAndReturn(bazMap.get("clxh"));
								String fdjh = checkStrNullAndReturn(bazMap.get("fdjh"));
								String jdcsyr = checkStrNullAndReturn(bazMap.get("jdcsyr"));
								String clsbdh = checkStrNullAndReturn(bazMap.get("clsbdh"));
								String yxq = checkStrNullAndReturn(bazMap.get("yxq"));
								String gjgsid = checkStrNullAndReturn(bazMap.get("gjgsid"));
								String ggjgid = checkStrNullAndReturn(bazMap.get("ggjgid"));
								String sbsj = checkStrNullAndReturn(bazMap.get("sbsj"));

								BusCertifyInfo busCertifyInfo = new BusCertifyInfo();

								busCertifyInfo.setLsh(lsh);
								busCertifyInfo.setHphm(hphm);
								busCertifyInfo.setHpzl(hpzl);
								busCertifyInfo.setCllx(cllx);
								busCertifyInfo.setClxh(clxh);
								busCertifyInfo.setFdjhm(fdjh);
								busCertifyInfo.setJdcsyr(jdcsyr);
								busCertifyInfo.setClsbdh(clsbdh);
								busCertifyInfo.setYxq(DateUtil.string2Date(yxq, "yyyy-MM-dd"));
								// 状态(A正常 R注销 F超期注销)
								busCertifyInfo.setZt("A");
								busCertifyInfo.setGjgsid(gjgsid);
								busCertifyInfo.setGgjgid(ggjgid);
								busCertifyInfo.setSbrq(DateUtil.string2Date(sbsj, "yyyy-MM-dd"));

								// 制证次数
								busCertifyInfo.setDzcs(new BigDecimal(1));
								// 制证日期
								busCertifyInfo.setZzrq(new Date());
								// 制证民警代码
								busCertifyInfo.setZzmjcode(user.getName());
								// 制证民警姓名
								busCertifyInfo.setZzmjxm(user.getYgxm());

								String[] currentUserKjBm = gjclycxxglService.getCurrentUserKjBm(user.getBmid());
								// 制证民警部门
								busCertifyInfo.setZzmjbm(user.getBmid());
								// 制证民警部门(科级)
								busCertifyInfo.setZzmjbmKj(currentUserKjBm[0]);

								defaultDao.addRepository(busCertifyInfo);

								// 记录打印广告证操作日志
								BusCertifyInfoLog busCertifyInfoLog = new BusCertifyInfoLog();
								busCertifyInfoLog.setCzr(user.getName());
								busCertifyInfoLog.setCzrxm(user.getYgxm());
								busCertifyInfoLog.setCzrbm(user.getBmid());
								busCertifyInfoLog.setCzsj(new Date());
								busCertifyInfoLog.setCzip(ip);
								busCertifyInfoLog.setCzmac(mac);
								busCertifyInfoLog.setCznr("正常打印广告证");

								defaultDao.addRepositoryLog(busCertifyInfoLog, busCertifyInfo, null);

							}

						} else {
							result = "-操作失败：获取广告备案证数据异常";
						}
					} else {
						result = "-操作失败：获取不到流水号" + lsh + "对应的广告数据";
					}

				}
			} else {
				result = "-操作失败：流水号为空";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 错证重打
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addBusCertifyCzcd(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
			if (!lsh.equals("")) {
				Object obj_CertifyInfo = defaultDao.getRepository(lsh, BusCertifyInfo.class);
				if (obj_CertifyInfo != null) {

					BusCertifyInfo busCertifyInfo = (BusCertifyInfo) obj_CertifyInfo;

					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));
					String mac = checkStrNullAndReturn(request.getParameter("mac"));

					if (result.equals("")) {

						Date czrq = new Date();

						// 制证次数
						BigDecimal dzcs = busCertifyInfo.getDzcs().add(new BigDecimal(1));
						busCertifyInfo.setDzcs(dzcs);
						// 制证日期
						// busCertifyInfo.setZzrq(czrq);
						// 制证民警代码
						// busCertifyInfo.setZzmjcode(user.getName());
						// 制证民警姓名
						// busCertifyInfo.setZzmjxm(user.getYgxm());
						// 制证民警部门
						// busCertifyInfo.setZzmjbm(user.getBmid());

						defaultDao.updateRepository(busCertifyInfo);

						// 错证重打
						BusCertifyCzcd busCertifyCzcd = new BusCertifyCzcd();
						busCertifyCzcd.setLsh(busCertifyInfo.getLsh());
						busCertifyCzcd.setHphm(busCertifyInfo.getHphm());
						busCertifyCzcd.setHpzl(busCertifyInfo.getHpzl());
						busCertifyCzcd.setCllx(busCertifyInfo.getCllx());
						busCertifyCzcd.setClxh(busCertifyInfo.getClxh());
						busCertifyCzcd.setFdjhm(busCertifyInfo.getFdjhm());
						busCertifyCzcd.setJdcsyr(busCertifyInfo.getJdcsyr());
						busCertifyCzcd.setClsbdh(busCertifyInfo.getClsbdh());
						busCertifyCzcd.setYxq(busCertifyInfo.getYxq());
						busCertifyCzcd.setGjgsid(busCertifyInfo.getGjgsid());
						busCertifyCzcd.setGgjgid(busCertifyInfo.getGgjgid());
						busCertifyCzcd.setSbrq(busCertifyInfo.getSbrq());

						// 制证日期
						busCertifyCzcd.setZzrq(czrq);
						// 制证民警代码
						busCertifyCzcd.setZzmjcode(user.getName());
						// 制证民警姓名
						busCertifyCzcd.setZzmjxm(user.getYgxm());

						String[] currentUserKjBm = gjclycxxglService.getCurrentUserKjBm(user.getBmid());
						// 制证民警部门
						busCertifyCzcd.setZzmjbm(user.getBmid());
						// 制证民警部门(科级)
						busCertifyCzcd.setZzmjbmKj(currentUserKjBm[0]);

						defaultDao.addRepository(busCertifyCzcd);

						// 记录打印广告证操作日志
						BusCertifyInfoLog log = new BusCertifyInfoLog();
						log.setCzr(user.getName());
						log.setCzrxm(user.getYgxm());
						log.setCzrbm(user.getBmid());
						log.setCzsj(czrq);
						log.setCzip(ip);
						log.setCzmac(mac);
						log.setCznr("广告证重打");

						defaultDao.addRepositoryLog(log, busCertifyInfo, null);

					}

				}
			} else {
				result = "-操作失败：流水号为空";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 获取要注销的广告证数据集合
	 * 
	 * @param request
	 * @throws Exception
	 */
	public void getZxData(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);

		List<BusCertifyInfo> busCertifyInfos = new ArrayList<BusCertifyInfo>();
		// 权限
		String qx = checkStrNullAndReturn(request.getParameter("qx"));
		String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
		String currentDateStr = DateUtil.date2String(new Date(), "yyyy-MM-dd");
		Date currentDate = DateUtil.string2Date(currentDateStr, "yyyy-MM-dd");

		Map<String, String> hpzlMap = new HashMap<String, String>();
		Map<String, String> cllxMap = new HashMap<String, String>();
		Map<String, String> gjgsMap = new HashMap<String, String>();

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		if (user != null) {

			String hql = " from BusGgjgclsb a where 1=1 ";

			if (qx.equals("kj")) {
				// 获取用户所在科级部门
				String[] currentKjBm = gjclycxxglService.getCurrentUserKjBm(checkStrNullAndReturn(user.getBmid()));
				String yyycbm = currentKjBm[0];
				hql += " and a.yyycbm ='" + yyycbm + "' ";
			}

			if (!lsh.equals("")) {
				hql += " and a.lsh='" + lsh + "' ";
			}
			// 检查是否有申报数据
			List<Object> tempList = defaultDao.getRepositories(hql);

			if (tempList != null && tempList.size() > 0) {

				hql = " from BusCertifyInfo a where 1=1 ";

				if (!lsh.equals("")) {
					hql += " and a.lsh='" + lsh + "' ";
				} else {
					StringBuffer tempLsh = new StringBuffer();
					tempLsh.append(" ( ");
					for (int i = 0; i < tempList.size(); i++) {
						BusGgjgclsb tempBusGgjgclsb = (BusGgjgclsb) tempList.get(i);
						if (i < tempList.size() - 1) {
							tempLsh.append(" '" + tempBusGgjgclsb.getLsh() + "', ");
						} else {
							tempLsh.append(" '" + tempBusGgjgclsb.getLsh() + "' ");
						}
					}
					tempLsh.append(" ) ");

					hql += " and a.lsh in " + tempLsh.toString();
				}

				hql += " order by a.lsh desc ";

				List<Object> templList = defaultDao.findHQLByPage(hql, offset, pagesize);
				rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

				if (templList != null && templList.size() > 0) {

					hpzlMap = gjbaxxspService.getHpzlMap(false);
					cllxMap = gjbaxxspService.getCllxMap(false);
					gjgsMap = getBusBases(null, null);

					for (Object obj : templList) {

						BusCertifyInfo busCertifyInfo = (BusCertifyInfo) obj;

						// String tempHpzl =
						// checkStrNullAndReturn(busCertifyInfo.getHpzl());
						// String tempCllx =
						// checkStrNullAndReturn(busCertifyInfo.getCllx());
						// 翻译号牌号码
						// busCertifyInfo.setHpzl(checkStrNullAndReturn(hpzlMap.get(tempHpzl)));
						// 翻译号牌种类
						// busCertifyInfo.setCllx(checkStrNullAndReturn(cllxMap.get(tempCllx)));
						// 翻译公交公司名称
						busCertifyInfo.setGjgsid(gjgsMap.get(busCertifyInfo.getGjgsid()));

						busCertifyInfos.add(busCertifyInfo);
					}

				}
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("currentDate", currentDate);
		request.setAttribute("qx", qx);
		request.setAttribute("lsh", lsh);
		request.setAttribute("busCertifyInfos", busCertifyInfos);

	}

	public Map<String, Object> initalBazData(HttpServletRequest request, String lsh) throws Exception {

		lsh = checkStrNullAndReturn(lsh);
		Map<String, Object> bazMap = new HashMap<String, Object>();
		bazMap.put("result", "");

		Object obj = defaultDao.getRepository(lsh, BusGgjgclsb.class);
		if (obj != null) {
			BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;

			Object obj_photo = defaultDao.getRepository(lsh, BusGgjgclsbPhoto.class);
			BusGgjgclsbPhoto ggjgclsbPhoto = null;
			if (obj_photo != null) {
				ggjgclsbPhoto = (BusGgjgclsbPhoto) obj_photo;
			}

			String hphm = checkStrNullAndReturn(busGgjgclsb.getHphm());
			String hpzl = checkStrNullAndReturn(busGgjgclsb.getHpzl());
			// String bgyxts = checkStrNullAndReturn(busGgjgclsb.getBgyxts() +
			// "");

			if (!hphm.equals("") && !hpzl.equals("")) {

				String hql = " from BusVehicleBase a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ";
				List<Object> tempList = defaultDao.getRepositories(hql);
				if (tempList != null && tempList.size() > 0) {
					Object tmpBus = tempList.get(0);
					if (obj != null) {

						BusVehicleBase bus = (BusVehicleBase) tmpBus;

						// 翻译号牌种类
						Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);
						String hpzlStr = hpzlMap.get(hpzl);

						// 翻译车辆类型
						Map<String, String> cllxMap = gjbaxxspService.getCllxMap(false);
						String cllxStr = cllxMap.get(bus.getCllx());

						String clxhStr = checkStrNullAndReturn(bus.getClxh());

						String clsbdh = checkStrNullAndReturn(bus.getClsbdh());

						String fdjhStr = checkStrNullAndReturn(bus.getFdjh());

						String jdcsyrStr = checkStrNullAndReturn(bus.getJdcsyr());

						String gjgsidStr = checkStrNullAndReturn(bus.getGjgsid());
						String ggjgidStr = checkStrNullAndReturn(busGgjgclsb.getGgjgid());

						/*
						 * Calendar calendar = Calendar.getInstance();
						 * calendar.setTime(new Date());
						 * calendar.add(Calendar.DATE,
						 * Integer.parseInt(bgyxts));
						 */

						// 有效期
						// String yxq =
						// checkStrNullAndReturn(DateUtil.date2String(calendar.getTime(),
						// "yyyy-MM-dd"));

						// 有效期是第一次打印的时候生成的时间
						Object tempObject = defaultDao.getRepository(lsh, BusCertifyInfo.class);
						if (tempObject != null) {
							BusCertifyInfo busCertifyInfo = (BusCertifyInfo) tempObject;
							// yxq =
							// checkStrNullAndReturn(DateUtil.date2String(busCertifyInfo.getYxq(),
							// "yyyy-MM-dd"));
						}

						// 申报时间
						String sbsj = checkStrNullAndReturn(DateUtil.date2String(busGgjgclsb.getLrsj(), "yyyy-MM-dd"));

						String sparator = File.separator;

						String path = request.getSession().getServletContext().getRealPath("/");
						// 样板图片地址
						// String sampleFilePath = path +
						// "\\pages\\gjgggl\\ggzxxgl\\pic\\sample.jpg";
						String sampleFilePath = path + sparator + "pages" + sparator + "gjgggl" + sparator + "ggzxxgl" + sparator + "pic" + sparator + "sample.jpg";
						// System.out.println("filepath:" + sampleFilePath);

						bazMap.put("lsh", lsh);
						bazMap.put("hphm", hphm);
						bazMap.put("hpzl", hpzlStr);
						bazMap.put("cllx", cllxStr);
						bazMap.put("clxh", clxhStr);
						bazMap.put("fdjh", fdjhStr);
						bazMap.put("jdcsyr", jdcsyrStr);
						bazMap.put("clsbdh", clsbdh);
						// bazMap.put("yxq", yxq);
						bazMap.put("gjgsid", gjgsidStr);
						bazMap.put("ggjgid", ggjgidStr);
						bazMap.put("sbsj", sbsj);
						if (sampleFilePath != null && !sampleFilePath.trim().equals("")) {
							bazMap.put("sampleFilePath", checkStrNullAndReturn(sampleFilePath));
						}

						if (ggjgclsbPhoto != null) {
							// 左前图片
							bazMap.put("zqPic", ggjgclsbPhoto.getLPic());
							// 右后图片
							bazMap.put("yhPic", ggjgclsbPhoto.getRPic());
						}

					} else {
						bazMap.put("result", "-操作失败:查无对应号牌号码号牌种类的公交信息数据");
					}

				}
			} else {
				bazMap.put("result", "-操作失败:该流水号" + lsh + "对应的广告申报数据无号牌号码,号牌种类或有效天数为空");
			}
		} else {
			bazMap.put("result", "-操作失败:查无该流水号" + lsh + "对应的广告申报数据");
		}
		return bazMap;
	}

	/**
	 * 获取左前和右前图片
	 * 
	 * @param lsh
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public byte[] getImage(String lsh, String position) throws Exception {
		byte[] result = null;
		lsh = checkStrNullAndReturn(lsh);
		position = checkStrNullAndReturn(position);
		try {
			if (!lsh.equals("") && !position.equals("")) {

				Object tempObject = defaultDao.getRepository(lsh, BusGgjgclsbPhoto.class);
				if (tempObject != null) {
					BusGgjgclsbPhoto ggjgclsbPhoto = (BusGgjgclsbPhoto) tempObject;

					Blob image = null;

					if (position.equals("left")) {
						image = (Blob) ggjgclsbPhoto.getLPic();
					} else {
						image = (Blob) ggjgclsbPhoto.getRPic();
					}

					if (image != null && image.length() > 0) {
						int length = (int) image.length();
						result = image.getBytes(1, length);
					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 初始化备案证数据
	 * 
	 * @param request
	 * @param lsh
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> initalBazData_(HttpServletRequest request, String lsh) throws Exception {

		String path = request.getSession().getServletContext().getRealPath("/");
		String sampleFilePath = path + "\\pages\\gjgggl\\ggzxxgl\\pic\\sample.jpg";

		lsh = checkStrNullAndReturn(lsh);
		Map<String, Object> bazMap = new HashMap<String, Object>();

		if (!lsh.equals("")) {
			Object obj = defaultDao.getRepository(lsh, BusGgjgclsb.class);
			if (obj != null) {
				BusGgjgclsb busGgjgclsb = (BusGgjgclsb) obj;
				String hphm = checkStrNullAndReturn(busGgjgclsb.getHphm());
				String hpzl = checkStrNullAndReturn(busGgjgclsb.getHpzl());
				if (!hphm.equals("") && !hpzl.equals("")) {

					String hql = " from BusVehicleBase a where a.hphm='" + hphm + "' and a.hpzl='" + hpzl + "' ";
					List<Object> tempList = defaultDao.getRepositories(hql);
					if (tempList != null && tempList.size() > 0) {
						Object tmpBus = tempList.get(0);
						if (obj != null) {

							Object obj_phot = defaultDao.getRepository(lsh, BusGgjgclsbPhoto.class);
							if (obj_phot != null) {
								BusGgjgclsbPhoto photo = (BusGgjgclsbPhoto) obj_phot;

								BusVehicleBase bus = (BusVehicleBase) tmpBus;
								Map<String, String> hpzlMap = gjbaxxspService.getHpzlMap(false);
								String hpzlStr = checkStrNullAndReturn(hpzlMap.get(hpzl));
								String fdjhStr = checkStrNullAndReturn(bus.getFdjh());
								String clsbdhStr = checkStrNullAndReturn(bus.getClsbdh());
								String jdcsyrStr = checkStrNullAndReturn(bus.getJdcsyr());
								// String bgyxts =
								// checkStrNullAndReturn(busGgjgclsb.getBgyxts()
								// + "");

								/*
								 * Calendar calendar = Calendar.getInstance();
								 * calendar.setTime(new Date());
								 * calendar.add(Calendar.DATE,
								 * Integer.parseInt(bgyxts)); String yxq =
								 * checkStrNullAndReturn
								 * (DateUtil.date2String(calendar.getTime(),
								 * "yyyy-MM-dd"));
								 */

								bazMap.put("hphm", hphm);
								bazMap.put("hpzl", hpzlStr);
								bazMap.put("fdjh", fdjhStr);
								bazMap.put("clsbdh", clsbdhStr);
								bazMap.put("jdcsyr", jdcsyrStr);
								// bazMap.put("yxq", yxq);
								bazMap.put("lsh", lsh);

								// 左前图片
								bazMap.put("zqPic", photo.getLPic());
								// 右后图片
								bazMap.put("yhPic", photo.getRPic());
								// 备案证模板图片路径
								bazMap.put("sampleFilePath", sampleFilePath);

							}

						}

					}
				}
			}
		}
		return bazMap;
	}

	/**
	 * 生成备案证图片
	 * 
	 * @param bazMap
	 * @return
	 * @throws Exception
	 */
	public byte[] createBazPic(Map<String, Object> bazMap) throws Exception {

		byte[] result = null;

		try {
			if (bazMap != null && bazMap.size() > 0) {
				String hphm = checkStrNullAndReturn(bazMap.get("hphm"));
				String hpzl = checkStrNullAndReturn(bazMap.get("hpzl"));
				String cllx = checkStrNullAndReturn(bazMap.get("cllx"));
				String fdjh = checkStrNullAndReturn(bazMap.get("fdjh"));
				String clsbdh = checkStrNullAndReturn(bazMap.get("clsbdh"));
				String jdcsyr = checkStrNullAndReturn(bazMap.get("jdcsyr"));
				String yxq = checkStrNullAndReturn(bazMap.get("yxq"));
				String lsh = checkStrNullAndReturn(bazMap.get("lsh"));
				// 备案证模板图片路径
				String sampleFilePath = checkStrNullAndReturn(bazMap.get("sampleFilePath"));
				// 左前图片
				Object tempZqPic = bazMap.get("zqPic");
				// 右后图片
				Object tempYhPic = bazMap.get("yhPic");

				if (!hphm.equals("") && !hpzl.equals("") && !lsh.equals("") && !sampleFilePath.equals("") && tempZqPic != null && tempYhPic != null) {
					BufferedImage buffImage = ImageIO.read(new File(sampleFilePath));
					if (buffImage != null) {
						Graphics g = buffImage.getGraphics();
						Font font = new Font("宋体", Font.BOLD, 20);
						g.setFont(font);
						Color c = new Color(0, 0, 0);
						g.setColor(c);
						// 号牌号码
						g.drawString(hphm, 140, 190);
						// 号牌种类
						g.drawString(hpzl, 420, 190);
						// 车辆类型
						g.drawString(cllx, 140, 240);
						// 车架号
						g.drawString(clsbdh, 390, 240);
						// 机动车所有人
						g.drawString(jdcsyr, 170, 290);
						// 有效期
						// g.drawString(yxq, 140, 335);
						// 流水号
						// g.drawString(lsh, 100, 387);

						Blob zqPic = (Blob) tempZqPic;
						Blob yhPic = (Blob) tempYhPic;

						// 左前图片
						Image img_zq = null;
						// 右后图片
						Image img_yh = null;

						// 流水号条形码
						Image img_txm = null;

						try {
							// 左前图片
							img_zq = ImageIO.read(zqPic.getBinaryStream());
							// 右后图片
							img_yh = ImageIO.read(yhPic.getBinaryStream());

							// code=${tempSbSingle.lsh}&barType=CODE39&checkCharacter=n&checkCharacterInText=n
							// 条形码
							// img_txm = new
							// GetBarCode().createImage("D1412201234", "CODE39",
							// "200", "50");
							img_txm = new GetBarCode().createImage(lsh, "CODE39", "215", "63");

						} catch (Throwable e) {
							e.printStackTrace();
						}

						g.drawImage(img_zq, 50, 575, 278, 192, null);
						g.drawImage(img_yh, 340, 575, 278, 192, null);
						g.drawImage(img_txm, 100, 310, 215, 63, null);

						ByteArrayOutputStream outImg = new ByteArrayOutputStream();
						ImageIO.write(buffImage, "jpg", outImg);

						result = outImg.toByteArray();

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 注销或恢复广告证
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String zxOrfhBusCertifyInfo(HttpServletRequest request) throws Exception {
		String result = "";
		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String lsh = checkStrNullAndReturn(request.getParameter("lsh"));
			String czlx = checkStrNullAndReturn(request.getParameter("czlx"));
			String mac = checkStrNullAndReturn(request.getParameter("mac"));

			if (!lsh.equals("") && !czlx.equals("")) {
				Object obj = defaultDao.getRepository(lsh, BusCertifyInfo.class);
				if (obj != null) {
					BusCertifyInfo busCertifyInfo = (BusCertifyInfo) obj;
					// 状态(A正常 R注销 F超期注销)
					String zt = checkStrNullAndReturn(busCertifyInfo.getZt());
					String cznr = "";

					// 是否更新数据
					boolean isUpdate = false;
					// 注销
					if (czlx.equals("zx")) {
						busCertifyInfo.setZt("R");
						cznr = "注销广告证";
						isUpdate = true;
						// 恢复
					} else if (czlx.equals("hf")) {
						// 不等于超期注销的才可以进行恢复
						if (!zt.equals("F")) {
							busCertifyInfo.setZt("A");
							cznr = "恢复广告证";
							isUpdate = true;
						} else {
							result = "-操作失败:超期注销的不允许恢复";
						}

					}

					if (isUpdate) {

						defaultDao.updateRepository(busCertifyInfo);

						// 记录打印广告证操作日志
						BusCertifyInfoLog log = new BusCertifyInfoLog();
						log.setCzr(user.getName());
						log.setCzrxm(user.getYgxm());
						log.setCzrbm(user.getBmid());
						log.setCzsj(new Date());
						log.setCzip(checkStrNullAndReturn(ToolsUtil.getIpAddr(request)));
						log.setCzmac(mac);
						log.setCznr(cznr);

						defaultDao.addRepositoryLog(log, busCertifyInfo, null);

					}

				} else {
					result = "-操作失败:查询不到流水号(" + lsh + ")对应的广告证数据";
				}
			} else {
				result = "-操作失败:要操作的流水号为空";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	public Map<String, String> getBusBases(String gjgsid, String gjgsName) throws Exception {
		Map<String, String> busBaseMap = new HashMap<String, String>();
		String hql = " from BusBase a where 1=1 ";
		if (gjgsid != null && !gjgsid.equals("")) {
			hql += " and  a.gjgsid='" + gjgsid + "' ";
		} else {
			if (gjgsName != null && !gjgsName.equals("")) {
				hql += " and  a.gjgsmc like '%" + gjgsid + "%' ";
			}
		}
		List<Object> tempList = defaultDao.getRepositories(hql);
		for (Object obj : tempList) {
			BusBase busBase = (BusBase) obj;
			busBaseMap.put(busBase.getGjgsid(), busBase.getGjgsmc());
		}
		return busBaseMap;
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

	public GjclycxxglService getGjclycxxglService() {
		return gjclycxxglService;
	}

	public void setGjclycxxglService(GjclycxxglService gjclycxxglService) {
		this.gjclycxxglService = gjclycxxglService;
	}

}
