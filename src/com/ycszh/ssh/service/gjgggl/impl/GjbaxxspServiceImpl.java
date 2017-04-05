package com.ycszh.ssh.service.gjgggl.impl;

import java.net.URLDecoder;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.DefaultDao;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgBase;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgclsb;
import com.ycszh.ssh.hbm.gjgggl.BusGgjgdlr;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBase;
import com.ycszh.ssh.hbm.gjgggl.BusVehicleBaseLog;
import com.ycszh.ssh.service.gjgggl.GjbaxxspService;
import com.ycszh.ssh.service.gjgggl.GjclycxxglService;
import com.ycszh.util.ToolsUtil;

public class GjbaxxspServiceImpl implements GjbaxxspService {

	private DefaultDao defaultDao;

	/**
	 * 查询备案公交车辆集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BusVehicleBase> getBusVehicleBaseList(HttpServletRequest request) throws Exception {

		String hphm = checkStrNullAndReturn(request.getParameter("hphm"));
		String hpzl = checkStrNullAndReturn(request.getParameter("hpzl"));
		// 审核状态(0未审核 1审核通过 2审核不通过)
		String shzt = checkStrNullAndReturn(request.getParameter("shzt"));
		// 首次进入为true
		String isFirstQuery = checkStrNullAndReturn(request.getParameter("isFirstQuery"));

		Map<String, String> hpzlMap = getHpzlMap(false);

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusVehicleBase a where 1=1 ";

		if (!hphm.equals("")) {
			hql += " and a.hphm like '%" + hphm + "%' ";
		}

		if (!hpzl.equals("")) {
			hql += " and a.hpzl='" + hpzl + "' ";
		}

		// 首次进入查询,默认查询待审数据
		if (isFirstQuery.equals("true")) {
			hql += " and a.shzt='0' ";
			shzt = "0";
		} else {
			if (!shzt.equals("")) {
				hql += " and a.shzt='" + shzt + "' ";
			}
		}

		hql += " order by a.lrrq desc ";

		List<Object> tempResultList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		if (tempResultList != null && tempResultList.size() > 0) {
			for (Object obj : tempResultList) {
				if (obj != null) {
					BusVehicleBase bus = (BusVehicleBase) obj;
					// 翻译车牌种类
					bus.setHpzl(checkStrNullAndReturn(hpzlMap.get(checkStrNullAndReturn(bus.getHpzl()))));
					busVehicleBases.add(bus);
				}
			}
		}

		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("hphm", hphm);
		request.setAttribute("hpzl", hpzl);
		request.setAttribute("shzt", shzt);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		return busVehicleBases;
	}

	public List<BusVehicleBase> getBusVehicleBaseList(String hphm, String hpzl, String shzt) throws Exception {

		List<BusVehicleBase> busVehicleBases = new ArrayList<BusVehicleBase>();

		String hql = " from BusVehicleBase a where 1=1 ";

		if (hphm != null && !hphm.equals("")) {
			hql += " and a.hphm='" + hphm + "' ";
		}

		if (hpzl != null && !hpzl.equals("")) {
			hql += " and a.hpzl='" + hpzl + "' ";
		}

		if (shzt != null && !shzt.equals("")) {
			hql += " and a.shzt='" + shzt + "' ";
		}
		List<Object> tempList = defaultDao.getRepositories(hql);
		for (Object obj : tempList) {
			if (obj != null) {
				BusVehicleBase bus = (BusVehicleBase) obj;
				busVehicleBases.add(bus);
			}
		}

		return busVehicleBases;
	}

	/**
	 * 根据xh查询车辆信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public BusVehicleBase getBusVehicleBase(HttpServletRequest request) throws Exception {

		String xh = checkStrNullAndReturn(request.getParameter("xh"));
		// 操作类型
		String czlx = checkStrNullAndReturn(request.getParameter("czlx"));

		// 验证车档结果(详细)
		Map<String, String> result = new HashMap<String, String>();
		// 保存正确的车档资料
		Map<String, String> resultMap_ = new HashMap<String, String>();

		Map<String, String> hpzlMap = getHpzlMap(true);
		Map<String, String> cllxMap = getCllxMap(true);

		BusVehicleBase busVehicleBase = null;
		if (xh != null && !xh.equals("")) {
			Object obj = defaultDao.getRepository(xh, BusVehicleBase.class);
			if (obj != null) {
				busVehicleBase = (BusVehicleBase) obj;

			}
		}

		// 查看操作的时候不核对车档信息
		if (!czlx.equals("show")) {
			result = checkCd(busVehicleBase);
			boolean hasDifference = false;
			String nodata = checkStrNullAndReturn(result.get("nodata"));
			if (!nodata.equals("true")) {
				String cllxStr = checkStrNullAndReturn(result.get("cllx"));
				String clsbdhStr = checkStrNullAndReturn(result.get("clsbdh"));
				String clxhStr = checkStrNullAndReturn(result.get("clxh"));
				String fdjxhStr = checkStrNullAndReturn(result.get("fdjxh"));
				String fdjhStr = checkStrNullAndReturn(result.get("fdjh"));

				String cllx = checkStrNullAndReturn(busVehicleBase.getCllx());
				String clsbdh = checkStrNullAndReturn(busVehicleBase.getClsbdh());
				String clxh = checkStrNullAndReturn(busVehicleBase.getClxh());
				String fdjxh = checkStrNullAndReturn(busVehicleBase.getFdjxh());
				String fdjh = checkStrNullAndReturn(busVehicleBase.getFdjh());
				String jdcsyr = checkStrNullAndReturn(busVehicleBase.getJdcsyr());

				resultMap_.put("cllx", cllxStr);
				resultMap_.put("clsbdh", clsbdhStr);
				resultMap_.put("clxh", clxhStr);
				resultMap_.put("fdjxh", fdjxhStr);
				resultMap_.put("fdjh", fdjhStr);

				if (!cllx.equals(cllxStr)) {
					result.put("cllx", "车辆类型不匹配!车档车辆类型为：" + cllxMap.get(cllxStr) + ";");
					hasDifference = true;
				} else {
					result.remove("cllx");
				}
				if (!clsbdh.equals(clsbdhStr)) {
					result.put("clsbdh", "车架号不匹配!车档车架号为：" + clsbdhStr + ";");
					hasDifference = true;
				} else {
					result.remove("clsbdh");
				}
				// if (!clxh.equals(clxhStr)) {
				// result.put("clxh", "车辆型号不匹配!车档车辆型号为：" + clxhStr + ";");
				// hasDifference = true;
				// } else {
				result.remove("clxh");
				// }
				// if (!fdjxh.equals(fdjxhStr)) {
				// result.put("fdjxh", "发动机型号不匹配!车档发动机型号为：" + fdjxhStr + ";");
				// hasDifference = true;
				// } else {
				result.remove("fdjxh");
				// }
				// if (!fdjh.equals(fdjhStr)) {
				// result.put("fdjh", "发动机号不匹配!车档发动机号为：" + fdjhStr + ";");
				// hasDifference = true;
				// } else {
				result.remove("fdjh");
				// }

			}

			if (hasDifference) {
				result.put("hasDifference", "true");
			}

			request.setAttribute("resultMap", result);
			request.setAttribute("resultMap_", resultMap_);
		}

		request.setAttribute("czlx", czlx);
		request.setAttribute("hpzlMap", hpzlMap);
		request.setAttribute("cllxMap", cllxMap);

		return busVehicleBase;
	}

	/**
	 * 根据xh查询车辆信息
	 * 
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public Blob getXszPic(String xh) throws Exception {

		if (xh != null && !xh.equals("")) {
			Object obj = defaultDao.getRepository(xh, BusVehicleBase.class);
			if (obj != null) {
				BusVehicleBase bus = (BusVehicleBase) obj;
				Blob image = bus.getXszzp();
				return image;
			}
		}
		return null;
	}

	/**
	 * 修改车辆信息
	 * 
	 * @param bus
	 * @return
	 * @throws Exception
	 */
	public String updateBusVehicleBase(BusVehicleBase bus, HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			if (bus != null && bus.getXh() != null && !bus.getXh().equals("")) {

				Object obj = defaultDao.getRepository(bus.getXh(), BusVehicleBase.class);
				if (obj != null) {

					String mac = checkStrNullAndReturn(request.getParameter("mac"));
					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

					BusVehicleBase tempBus = (BusVehicleBase) obj;

					tempBus.setShzt(bus.getShzt());
					// tempBus.setBusLine(checkStrNullAndReturn(bus.getBusLine()));
					tempBus.setCllx(checkStrNullAndReturn(bus.getCllx()));
					tempBus.setClsbdh(checkStrNullAndReturn(bus.getClsbdh()));
					// tempBus.setClxh(checkStrNullAndReturn(bus.getClxh()));
					// tempBus.setFdjxh(checkStrNullAndReturn(bus.getFdjxh()));
					// tempBus.setFdjh(checkStrNullAndReturn(bus.getFdjh()));
					String shzt = bus.getShzt();

					// 审核状态(0未审核 1审核通过 2审核不通过)
					if (shzt.equals("2")) {
						String tbyy = checkStrNullAndReturn(bus.getTbyy());
						tbyy = URLDecoder.decode(tbyy, "UTf-8");
						tempBus.setTbyy(tbyy);
					} else {
						tempBus.setTbyy("");
					}

					tempBus.setShrdm(user.getName());
					tempBus.setShrxm(user.getYgxm());
					tempBus.setShbm(user.getBmid());
					tempBus.setShbmKj(user.getBmid());
					tempBus.setShsj(new Date());
					tempBus.setShip(ip);
					tempBus.setShmac(mac);

					// 修改同步标志
					tempBus.setSynFlag("UW");
					tempBus.setTranDate(null);
					tempBus.setTranFlag(null);

					Object t = defaultDao.updateRepository(tempBus);
					if (t != null) {
						// 操作日志
						BusVehicleBaseLog bus_log = new BusVehicleBaseLog();
						bus_log.setCzr(user.getName());
						bus_log.setCzrxm(user.getYgxm());
						bus_log.setCzrbm(user.getBmid());
						bus_log.setCzsj(new Date());
						bus_log.setCzip(ip);
						bus_log.setCznr("审核备案车辆");
						bus_log.setCzmac(mac);
						defaultDao.addRepositoryLog(bus_log, tempBus, null);
					} else {
						result = "-操作失败";
					}

				} else {
					result = "-操作失败:要修改的车辆信息数据为空,无法修改";
				}

			} else {
				result = "-操作失败:要修改的车辆信息数据为空,无法修改";
			}
		} else {
			result = "-操作失败:用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 退办申报的车辆数据
	 * 
	 * @param bus
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String cancelBusVehicleBase(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String xh = checkStrNullAndReturn(request.getParameter("xh"));
			if (!xh.equals("")) {
				Object obj = defaultDao.getRepository(xh, BusVehicleBase.class);
				if (obj != null) {
					BusVehicleBase tempBus = (BusVehicleBase) obj;

					String mac = checkStrNullAndReturn(request.getParameter("mac"));
					String ip = checkStrNullAndReturn(ToolsUtil.getIpAddr(request));

					// 审核状态(0未审核 1审核通过 2审核不通过)
					tempBus.setShzt("2");
					tempBus.setShrdm(user.getName());
					tempBus.setShrxm(user.getYgxm());
					tempBus.setShbm(user.getBmid());
					tempBus.setShbmKj(user.getBmid());
					tempBus.setShsj(new Date());
					tempBus.setShip(ip);
					tempBus.setShmac(mac);

					// 修改同步标志
					tempBus.setSynFlag("UW");
					tempBus.setTranDate(null);
					tempBus.setTranFlag(null);

					Object t = defaultDao.updateRepository(tempBus);
					if (t != null) {
						// 操作日志
						BusVehicleBaseLog bus_log = new BusVehicleBaseLog();
						bus_log.setCzr(user.getName());
						bus_log.setCzrxm(user.getYgxm());
						bus_log.setCzrbm(user.getBmid());
						bus_log.setCzsj(new Date());
						bus_log.setCzip(ip);
						bus_log.setCznr("退办备案车辆");
						bus_log.setCzmac(mac);
						defaultDao.addRepositoryLog(bus_log, tempBus, null);
					} else {
						result = "-操作失败:删除失败";
					}

				}
			} else {
				result = "-操作失败:要删除的数据为空(无法获取删除数据的xh)";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}
		return result;
	}

	/**
	 * 查询号牌种类
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getHpzlMap(boolean hasLine) throws Exception {

		Map<String, String> hashMap = new TreeMap<String, String>();

		String sql = "select a.dmz,a.dmsm1 from es_veh_code a where a.dmlb='07'  ";

		List<Object> hpzlList = defaultDao.findSQL(sql);
		for (Object obj : hpzlList) {
			Object[] tempObj = (Object[]) obj;
			String tempHpzl = checkStrNullAndReturn(tempObj[0]);
			String tempHpzlMs = checkStrNullAndReturn(tempObj[1]);
			if (!tempHpzl.equals("") && !tempHpzlMs.equals("")) {
				if (hasLine) {
					hashMap.put(tempHpzl, tempHpzl + "-" + tempHpzlMs);
				} else {
					hashMap.put(tempHpzl, tempHpzlMs);
				}

			}
		}

		return hashMap;
	}

	/**
	 * 获取广告公司map
	 * 
	 * @param hasLine
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getGggsMap(boolean hasLine) throws Exception {

		Map<String, String> gggsMap = new TreeMap<String, String>();

		String hql = " from BusGgjgBase a ";

		List<Object> tempList = defaultDao.getRepositories(hql);
		for (Object obj : tempList) {
			BusGgjgBase busGgjgBase = (BusGgjgBase) obj;
			String tempGgjdid = checkStrNullAndReturn(busGgjgBase.getGgjgid());
			String tempGgjgmc = checkStrNullAndReturn(busGgjgBase.getGgjgmc());
			if (!tempGgjdid.equals("") && !tempGgjgmc.equals("")) {
				if (hasLine) {
					gggsMap.put(tempGgjdid, tempGgjdid + "-" + tempGgjgmc);
				} else {
					gggsMap.put(tempGgjdid, tempGgjgmc);
				}

			}
		}

		return gggsMap;
	}

	public Map<String, String> getCllxMap(boolean hasLine) throws Exception {

		Map<String, String> hashMap = new TreeMap<String, String>();
		String sql = " select a.dmz,a.dmms1 from BUS_SJZD a where a.dmlb='CLLX' order by a.dmlb desc,a.dmz asc ";
		// String sql =
		// "select a.dmz,a.dmsm1 from es_veh_code a where a.dmlb='4' order by a.dmz asc  ";

		List<Object> hpzlList = defaultDao.findSQL(sql);
		for (Object obj : hpzlList) {
			Object[] tempObj = (Object[]) obj;
			String tempDmz = checkStrNullAndReturn(tempObj[0]);
			String tempMs = checkStrNullAndReturn(tempObj[1]);
			if (!tempDmz.equals("") && !tempMs.equals("")) {
				if (hasLine) {
					hashMap.put(tempDmz, tempDmz + "-" + tempMs);
				} else {
					hashMap.put(tempDmz, tempMs);
				}
			}
		}

		return hashMap;
	}

	/**
	 * 验证车档(查镜像库)
	 * 
	 * @param bus
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> checkCd(BusVehicleBase bus) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		if (bus != null) {
			String hphm = checkStrNullAndReturn(bus.getHphm());
			String hpzl = checkStrNullAndReturn(bus.getHpzl());
			String hphmStr = "";
			if (!hphm.equals("") && hphm.length() > 5) {
				hphmStr = hphm.substring(1);
			}
			String sql = "select a.hphm,a.hpzl,a.clsbdh,cast(a.cllx as varchar2(20)),a.xh as clxh,a.fdjxh,a.fdjh,a.czmc as jdcsyr from es_vehicle a where a.hphm='" + hphmStr + "' and a.hpzl='" + hpzl
					+ "' ";

			List<Object> tempList = defaultDao.findSQL(sql);

			if (tempList != null && tempList.size() > 0) {
				Object obj = tempList.get(0);
				if (obj != null) {
					Object[] result = (Object[]) obj;
					String cllxStr = checkStrNullAndReturn(result[3]).toUpperCase();
					String clsbdhStr = checkStrNullAndReturn(result[2]).toUpperCase();
					String clxhStr = checkStrNullAndReturn(result[4]).toUpperCase();
					String fdjxhStr = checkStrNullAndReturn(result[5]).toUpperCase();
					String fdjhStr = checkStrNullAndReturn(result[6]).toUpperCase();
					String jdcsyrStr = checkStrNullAndReturn(result[7]).toUpperCase();

					resultMap.put("cllx", cllxStr);
					resultMap.put("clsbdh", clsbdhStr);
					resultMap.put("clxh", clxhStr);
					resultMap.put("fdjxh", fdjxhStr);
					resultMap.put("fdjh", fdjhStr);
					resultMap.put("jdcsyr", jdcsyrStr);

				}
			} else {
				// 查车档无数据
				resultMap.put("nodata", "true");
			}
		}
		return resultMap;
	}

	/**
	 * 查询广告机构数据集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgBase> getBusGgjgBaseList(HttpServletRequest request) throws Exception {

		// 组织机构代码
		String zzjgdm = checkStrNullAndReturn(request.getParameter("zzjgdm"));
		// 广告机构名称
		String ggjgmc = checkStrNullAndReturn(request.getParameter("ggjgmc"));
		// 审核状态(0未审核 1审核通过 2审核不通过)
		String shzt = checkStrNullAndReturn(request.getParameter("shzt"));

		// 首次进入为true
		String isFirstQuery = checkStrNullAndReturn(request.getParameter("isFirstQuery"));

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusGgjgBase a where 1=1 ";

		if (!zzjgdm.equals("")) {
			hql += " and a.zzjgdm like '%" + zzjgdm + "%' ";
		}

		if (!ggjgmc.equals("")) {
			hql += " and a.ggjgmc like '%" + ggjgmc + "%' ";
		}

		// 首次进入查询,默认查询未审核数据
		if (isFirstQuery.equals("true")) {
			hql += " and a.shzt='0' ";
			shzt = "0";
		} else {
			if (!shzt.equals("")) {
				hql += " and a.shzt='" + shzt + "' ";
			}
		}

		hql += " order by a.lrsj desc ";

		List<Object> tempResultList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		List<BusGgjgBase> busGgjgBases = new ArrayList<BusGgjgBase>();

		if (tempResultList != null && tempResultList.size() > 0) {
			Map<String, String> gjgsMap = getBusGjgsMap(null);
			for (Object obj : tempResultList) {
				if (obj != null) {
					BusGgjgBase bus = (BusGgjgBase) obj;
					// 翻译公交公司名称
					bus.setGjgsmc(checkStrNullAndReturn(gjgsMap.get(bus.getGjgsid())));
					busGgjgBases.add(bus);
				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		request.setAttribute("zzjgdm", zzjgdm);
		request.setAttribute("ggjgmc", ggjgmc);
		request.setAttribute("shzt", shzt);

		return busGgjgBases;
	}

	/**
	 * 查询公交公司名称集合
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGjgsMap(String name) throws Exception {

		Map<String, String> resultMap = new HashMap<String, String>();

		String sql = " select a.gjgsid,a.gjgsmc from BUS_BASE a where 1=1 ";

		if (name != null && !name.equals("")) {
			sql += " and a.gjgsmc like '%" + name + "%' ";
		}

		List<Object> tempResultList = defaultDao.findSQL(sql);

		if (tempResultList != null && tempResultList.size() > 0) {
			for (Object obj : tempResultList) {
				if (obj != null) {
					Object[] tempObj = (Object[]) obj;
					resultMap.put(checkStrNullAndReturn(tempObj[0]), checkStrNullAndReturn(tempObj[1]));
				}
			}
		}

		return resultMap;

	}

	/**
	 * 查询广告机构名称集合
	 * 
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getBusGjjgMap(String name) throws Exception {

		Map<String, String> resultMap = new HashMap<String, String>();

		String sql = " select a.ggjgid,a.ggjgmc from BUS_GGJG_BASE a where 1=1 ";

		if (name != null && !name.equals("")) {
			sql += " and a.ggjgmc like '%" + name + "%' ";
		}

		List<Object> tempResultList = defaultDao.findSQL(sql);

		if (tempResultList != null && tempResultList.size() > 0) {
			for (Object obj : tempResultList) {
				if (obj != null) {
					Object[] tempObj = (Object[]) obj;
					resultMap.put(checkStrNullAndReturn(tempObj[0]), checkStrNullAndReturn(tempObj[1]));
				}
			}
		}

		return resultMap;

	}

	/**
	 * 更新广告机构
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgBase(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String ggjgid = checkStrNullAndReturn(request.getParameter("ggjgid"));
			String shzt = checkStrNullAndReturn(request.getParameter("shzt"));
			String bz = checkStrNullAndReturn(request.getParameter("bz"));
			String mac = checkStrNullAndReturn(request.getParameter("mac"));

			bz = URLDecoder.decode(bz, "UTF-8");

			if (!ggjgid.equals("")) {
				Object obj = defaultDao.getRepository(ggjgid, BusGgjgBase.class);
				if (obj != null) {
					BusGgjgBase ggjgBase = (BusGgjgBase) obj;

					// 广告机构密码(组织机构代码+4为随机数字)
					// String ggjgmm = ggjgBase.getZzjgdm() +
					// Math.round(Math.random() * 10000);
					String ggjgmm = ggjgBase.getZzjgdm();
					String pwd = getPwd(ggjgmm, "BF42");
					ggjgBase.setGgjgmm(pwd);

					// shzt (0未审核 1审核通过 2审核不通过)
					ggjgBase.setShzt(shzt);

					if (shzt.equals("1")) {
						ggjgBase.setZt("T");
					} else {
						ggjgBase.setZt("F");
					}

					ggjgBase.setBz(bz);

					ggjgBase.setShdm(user.getName());
					ggjgBase.setShxm(user.getYgxm());
					ggjgBase.setShbm(user.getBmid());
					ggjgBase.setShbmKj(user.getBmid());
					ggjgBase.setShsj(new Date());
					ggjgBase.setShip(ToolsUtil.getIpAddr(request));
					ggjgBase.setShmac(mac);

					// 修改同步标志
					ggjgBase.setSynFlag("UW");
					ggjgBase.setTranDate(null);
					ggjgBase.setTranFlag(null);

					defaultDao.updateRepository(ggjgBase);

				} else {
					result = "-操作失败:查询不到要操作的广告机构";
				}

			} else {
				result = "-操作失败:无法获取要操作的广告机构id";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 初始化广告机构登录密码
	 * 
	 * @param ggjgid
	 * @return
	 * @throws Exception
	 */
	public String updateBusGgjgBaseByCzlx(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String ggjgid = checkStrNullAndReturn(request.getParameter("ggjgid"));
			String czlx = checkStrNullAndReturn(request.getParameter("czlx"));
			String zhzt = checkStrNullAndReturn(request.getParameter("zhzt"));
			String bz = checkStrNullAndReturn(request.getParameter("bz"));
			bz = URLDecoder.decode(bz, "UTF-8");

			if (!ggjgid.equals("")) {
				Object obj = defaultDao.getRepository(ggjgid, BusGgjgBase.class);
				if (obj != null) {
					BusGgjgBase ggjgBase = (BusGgjgBase) obj;

					// 初始化密码
					if (czlx.equals("initalPwd")) {
						// 广告机构密码(组织机构代码+4为随机数字)
						// String ggjgmm = ggjgBase.getZzjgdm() +
						// Math.round(Math.random() * 10000);
						String ggjgmm = ggjgBase.getZzjgdm();
						String pwd = getPwd(ggjgmm, "BF42");
						ggjgBase.setGgjgmm(pwd);

						result = pwd;

						// 停用广告机构账号
					} else if (czlx.equals("stop")) {
						ggjgBase.setZt(zhzt);
						ggjgBase.setBz(bz);
					}

					ggjgBase.setSynFlag("UW");
					ggjgBase.setTranDate(null);
					ggjgBase.setTranFlag(null);

					defaultDao.updateRepository(ggjgBase);

				} else {
					result = "-操作失败:查询不到要操作的广告机构";
				}
			} else {
				result = "-操作失败:无法获取要操作的广告机构id";
			}
		} else {
			result = "-用户未登录或登录已过期";
		}

		return result;
	}

	/**
	 * 获取加密密码
	 * 
	 * @param str
	 * @param key
	 *            (密钥)
	 * @return
	 * @throws Exception
	 */
	public String getPwd(String str, String key) throws Exception {

		String result = "";
		str = checkStrNullAndReturn(str);
		key = checkStrNullAndReturn(key);
		if (!str.equals("") && !key.equals("")) {
			String sql = " select pkg_encrypt_decrypt.ENCRYPT_3KEY_MODE('" + str + "','" + key + "') from dual ";
			List<Object> tempList = defaultDao.findSQL(sql);
			if (tempList != null && tempList.size() > 0) {
				Object obj = tempList.get(0);
				if (obj != null) {
					result = checkStrNullAndReturn(obj);
				}
			}
		}

		return result;
	}

	/**
	 * 获取广告机构代理人集合
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<BusGgjgdlr> ggjgDbrList(HttpServletRequest request) throws Exception {

		String xm = checkStrNullAndReturn(request.getParameter("xm"));
		String sfzmhm = checkStrNullAndReturn(request.getParameter("sfzmhm"));
		String shzt = checkStrNullAndReturn(request.getParameter("shzt"));

		String ggjgid = request.getParameter("ggjgid") == null ? "" : request.getParameter("ggjgid").trim();

		// 分页参数
		int currentpage = new Integer(request.getParameter("currentpage") == null ? "1" : request.getParameter("currentpage"));
		String uri = request.getRequestURI();
		int pagesize = SysConst.PAGESIZE;
		int offset = pagesize * (currentpage - 1);
		int rscount = 0;

		String hql = " from BusGgjgdlr a where 1=1 ";

		if (!xm.equals("")) {
			hql += " and a.xm like '%" + xm + "%' ";
		}

		if (!sfzmhm.equals("")) {
			hql += " and a.sfzmhm like '%" + sfzmhm + "%' ";
		}

		// 未审核
		/*
		 * if (shzt.equals("0")) { hql += " and a.shzt='" + shzt + "' "; //
		 * 审核通过或审核不通过 } else if (shzt.equals("1") || shzt.equals("2")) { if
		 * (shzt.equals("1")) { hql += " and a.zt='T' "; } else if
		 * (shzt.equals("2")) { hql += " and a.zt='F' "; } }
		 */

		if (!shzt.equals("")) {
			hql += " and a.shzt='" + shzt + "' ";
		}

		if (!ggjgid.equals("")) {
			hql += " and a.ggjgid='" + ggjgid + "' ";
		}

		hql += " order by a.lrsj desc ";

		List<Object> tempResultList = defaultDao.findHQLByPage(hql, offset, pagesize);
		rscount = defaultDao.getRepositoryByHQLListSize(" select count(*) " + hql);

		List<BusGgjgdlr> busGgjgdlrs = new ArrayList<BusGgjgdlr>();

		if (tempResultList != null && tempResultList.size() > 0) {
			Map<String, String> busGgjgMap = getBusGjjgMap(null);
			for (Object obj : tempResultList) {
				if (obj != null) {
					BusGgjgdlr dlr = (BusGgjgdlr) obj;
					// 翻译广告机构名称
					dlr.setGgjgmc(checkStrNullAndReturn(busGgjgMap.get(dlr.getGgjgid())));
					busGgjgdlrs.add(dlr);
				}
			}
		}

		request.setAttribute("xm", xm);
		request.setAttribute("sfzmhm", sfzmhm);
		request.setAttribute("shzt", shzt);
		request.setAttribute("ggjgid", ggjgid);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("currentpage", currentpage);
		map.put("uri", uri);
		map.put("pagesize", pagesize);
		map.put("rscount", rscount);
		request.setAttribute("map", map);

		return busGgjgdlrs;
	}

	public List<BusGgjgBase> getBusGgjgBases(String ggjgid, String ggjgmc, String zzjgdm) throws Exception {

		List<BusGgjgBase> busGgjgBases = new ArrayList<BusGgjgBase>();

		String hql = " from BusGgjgBase a where 1=1 ";

		if (ggjgid != null && !ggjgid.trim().equals("")) {
			hql += " and a.ggjgid like '%" + ggjgid + "%' ";
		}

		if (ggjgmc != null && !ggjgmc.trim().equals("")) {
			hql += " and a.ggjgmc like '%" + ggjgmc + "%' ";
		}

		if (zzjgdm != null && !zzjgdm.trim().equals("")) {
			hql += " and a.zzjgdm like '%" + zzjgdm + "%' ";
		}

		List<Object> tempList = defaultDao.getRepositories(hql);

		for (Object obj : tempList) {
			BusGgjgBase busGgjgBase = (BusGgjgBase) obj;
			busGgjgBases.add(busGgjgBase);
		}

		return busGgjgBases;

	}

	public String updateGgjgDbryByCzlx(HttpServletRequest request) throws Exception {
		String result = "";

		HttpSession session = request.getSession();
		User user = session.getAttribute(SysConst.USERBEAN) == null ? null : (User) session.getAttribute(SysConst.USERBEAN);
		if (user != null) {
			String xh = checkStrNullAndReturn(request.getParameter("xh"));
			String czlx = checkStrNullAndReturn(request.getParameter("czlx"));
			String shzt = checkStrNullAndReturn(request.getParameter("shzt"));
			String zhzt = checkStrNullAndReturn(request.getParameter("zhzt"));
			String bz = checkStrNullAndReturn(request.getParameter("bz"));
			String mac = checkStrNullAndReturn(request.getParameter("mac"));

			bz = URLDecoder.decode(bz, "UTF-8");

			if (!xh.equals("")) {
				Object obj = defaultDao.getRepository(xh, BusGgjgdlr.class);
				if (obj != null) {
					BusGgjgdlr busGgjgdlr = (BusGgjgdlr) obj;

					if (czlx.equals("sh")) {
						// 审核状态(0未审核 1审核通过 2审核不通过 )
						busGgjgdlr.setShzt(shzt);
						if (shzt.equals("2")) {
							busGgjgdlr.setZt("F");
						} else {
							busGgjgdlr.setZt("T");
						}
						busGgjgdlr.setBz(bz);
					} else if (czlx.equals("qy") || czlx.equals("ty")) {
						busGgjgdlr.setZt(zhzt);
						busGgjgdlr.setBz(bz);
					}

					busGgjgdlr.setShrdm(user.getName());
					busGgjgdlr.setShrxm(user.getYgxm());
					busGgjgdlr.setShbm(user.getBmid());
					busGgjgdlr.setShbmKj(user.getBmid());
					busGgjgdlr.setShsj(new Date());
					busGgjgdlr.setShip(ToolsUtil.getIpAddr(request));
					busGgjgdlr.setShmac(mac);

					// 修改同步标志
					busGgjgdlr.setSynFlag("UW");
					busGgjgdlr.setTranDate(null);
					busGgjgdlr.setTranFlag(null);

					defaultDao.updateRepository(busGgjgdlr);

				} else {
					result = "-操作失败:查询不到要操作的代办人员";
				}

			} else {
				result = "-操作失败:无法获取要操作的代办人员xh";
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

	public List<BusGgjgclsb> getBusGgjgclsbList(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
