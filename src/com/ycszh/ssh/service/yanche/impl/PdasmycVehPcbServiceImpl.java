package com.ycszh.ssh.service.yanche.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.yanche.BJdccyxxDao;
import com.ycszh.ssh.dao.yanche.PdasmycPoliceSettingDao;
import com.ycszh.ssh.dao.yanche.PdasmycPoliceSettingLogDao;
import com.ycszh.ssh.dao.yanche.PdasmycTempMidOutNDao;
import com.ycszh.ssh.dao.yanche.PdasmycVehPcbDao;
import com.ycszh.ssh.dao.yanche.PdasmycVehPcbLogDao;
import com.ycszh.ssh.dao.yanche.PdasmycVehicleTsspDao;
import com.ycszh.ssh.dao.yanche.VDataCheckDao;
import com.ycszh.ssh.dao.yanche.VDataCheckLogDao;
import com.ycszh.ssh.dao.yanche.VVehOrgYcsDao;
import com.ycszh.ssh.dao.yanche.VVehUserYcsDao;
import com.ycszh.ssh.dao.yanche.VehicleTempMidOutDao;
import com.ycszh.ssh.hbm.yanche.BJdccyxx;
import com.ycszh.ssh.hbm.yanche.PdasmycPoliceSetting;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcbLog;
import com.ycszh.ssh.hbm.yanche.PdasmycVehicleTssp;
import com.ycszh.ssh.hbm.yanche.VDataCheck;
import com.ycszh.ssh.hbm.yanche.VDataCheckLog;
import com.ycszh.ssh.hbm.yanche.VVehOrgYcs;
import com.ycszh.ssh.hbm.yanche.VVehUserYcs;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.hbm.yanche.YwlsglVehSjzd;
import com.ycszh.ssh.service.yanche.PdasmycVehPcbService;
import com.ycszh.util.DateUtil;
import com.ycszh.util.barcode.BarCodeServlet;

/**
 * @包名:com.ycszh.ssh.service.yanche.impl
 * @文件名:PdasmycVehPcbServiceImpl.java
 * @作者:dengsiqi E-mail:dengsiqi@vip.qq.com
 * @创建日期:2013-4-23
 * @描述:
 * @版本:V 1.0
 */
public class PdasmycVehPcbServiceImpl implements PdasmycVehPcbService {
	private PdasmycVehPcbDao pdasmycVehPcbDao;
	private PdasmycVehPcbLogDao pdasmycVehPcbLogDao;
	private PdasmycPoliceSettingDao pdasmycPoliceSettingDao;
	private PdasmycPoliceSettingLogDao pdasmycPoliceSettingLogDao;
	private PdasmycTempMidOutNDao pdasmycTempMidOutNDao;
	private VVehUserYcsDao vVehUserYcsDao;
	private VVehOrgYcsDao vVehOrgYcsDao;
	private VDataCheckDao vDataCheckDao;
	private VDataCheckLogDao vDataCheckLogDao;
	private VehicleTempMidOutDao vehicleTempMidOutDao;
	private BJdccyxxDao bJdccyxxDao;
	private PdasmycVehicleTsspDao pdasmycVehicleTsspDao;
	private BarCodeServlet barServlet;
	private final static Logger log = Logger.getLogger(PdasmycVehPcbServiceImpl.class);

	public Integer insertOrUpdatePdasmycVehPcb(PdasmycVehPcb pdasmycVehPcb,HttpServletRequest request) throws Exception {
		if (pdasmycVehPcb != null) {
			String id = pdasmycVehPcb.getLsh();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			if(null == id || "".equals(id)){
				try {
					PdasmycVehPcb pvp = pdasmycVehPcbDao.addRepository(pdasmycVehPcb);
					PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pvp);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("I");
					pclog.setCzmac(userMac);
					pdasmycVehPcbLogDao.addRepository(pclog);
					log.info("method:insertOrUpdatePdasmycVehPcb|param:pdasmycVehPcb="+pdasmycVehPcb);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					throw e;
				}
			}else{
				try {
					PdasmycVehPcb pvp = pdasmycVehPcbDao.updateRepository(pdasmycVehPcb);
					PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pvp);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("U");
					pclog.setCzmac(userMac);
					pdasmycVehPcbLogDao.addRepository(pclog);
					log.info("method:insertOrUpdatePdasmycVehPcb|param:pdasmycVehPcb="+pdasmycVehPcb);
					return 0;
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e);
					return 1;
				}
			}
		} else {
			return 1;
		}
	}

	public Integer deletePdasmycVehPcb(String id) throws Exception {
		try {
			PdasmycVehPcb pdasmycVehPcb = pdasmycVehPcbDao.getRepository(id);
			if (pdasmycVehPcb != null) {
				pdasmycVehPcbDao.deleteRepository(pdasmycVehPcb);
				HttpServletRequest request = ServletActionContext.getRequest();
				User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
				PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pdasmycVehPcb);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCzmac("");
				pclog.setCznr("D");
				pdasmycVehPcbLogDao.addRepository(pclog);
				log.info("method:deletePdasmycVehPcb|param:id="+id);
				return 0;
			} else {
				return 1;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	public Integer deletePdasmycVehPcbList(String[] ids) throws Exception {
		try {
			Collection<PdasmycVehPcb> pdasmycVehPcblist = new ArrayList<PdasmycVehPcb>();
			if (null != ids) {
				for (String id : ids) {
					if(null != id && !"".equals(id)){
						PdasmycVehPcb pdasmycVehPcb = pdasmycVehPcbDao.getRepository(id);
						if (pdasmycVehPcb != null) {
							pdasmycVehPcblist.add(pdasmycVehPcb);
						}
					}
				}
			}
			if (pdasmycVehPcblist != null && pdasmycVehPcblist.size() > 0) {
				pdasmycVehPcbDao.deleteRepositoryList(pdasmycVehPcblist);
				log.info("method:deletePdasmycVehPcbList|param:ids="+ids);
				return 0;
			} else {
				return 1;
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
			log.error(e);
			throw e;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public List<PdasmycVehPcb> getPdasmycVehPcbList(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("select distinct t.smycyypch,t.smycyypchsl,t.chmc,t.yycgbm from pdasmyc_veh_pcb t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(distinct t.smycyypch) from pdasmyc_veh_pcb t where 1=1");
		String dbjg = request.getParameter("dbjg");
		String pch = request.getParameter("pch");
		String zttemp = request.getParameter("zt");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List pdasmycVehPcbList = new ArrayList();
		
		
		DateUtil du = new DateUtil();
		
		//所属部门
		String deptid = getDeptUpid(user.getBmid());
		if(null != deptid && !"".equals(deptid)){
			hql.append(" and t.yycgbm = '" + deptid + "' ");
			sql.append(" and t.yycgbm = '" + deptid + "' ");
			request.setAttribute("deptid", deptid);
		}

		// 代办机构
		if (dbjg != null && !dbjg.equals("")) {
			hql.append(" and t.chmc = '" + dbjg + "' ");
			sql.append(" and t.chmc = '" + dbjg + "' ");
			request.setAttribute("dbjg", dbjg);
		}
		
		// 批次号
		if (pch != null && !pch.equals("")) {
			hql.append(" and t.smycyypch = '" + pch + "' ");
			sql.append(" and t.smycyypch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		if (zttemp == null || zttemp.equals("")){
			zttemp = "0";
		}
		//状态
		if (zttemp != null && !zttemp.equals("") && zttemp.equals("0")) {
			hql.append(" and t.ycpch is null");
			sql.append(" and t.ycpch is null");
			request.setAttribute("zt", zttemp);
		}else if(zttemp != null && !zttemp.equals("") && zttemp.equals("1")){
			hql.append(" and t.ycpch is not null");
			sql.append(" and t.ycpch is not null");
			request.setAttribute("zt", zttemp);
		}
		
		//预约日期
		if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !s_date.equals("") && (e_date == null || e_date.equals(""))) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !e_date.equals("") && (s_date == null || s_date.equals(""))) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
				

		int size = pdasmycVehPcbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			List templist = pdasmycVehPcbDao.findSQLByPage(hql.toString(),offset,pageSize);
			for(int i = 0; i < templist.size(); i ++){
				Object[] obj = new Object[8];
				Object[] tempobj = (Object[]) templist.get(i);
				obj[0] = tempobj[0];
				obj[1] = tempobj[1];
				obj[2] = tempobj[2];
				obj[3] = getDeptName(String.valueOf(tempobj[3]));
				
				Integer yni = 0;
				Integer fenpeishu = 0;
				Integer yuyueshu = Integer.parseInt(obj[1].toString());
				String mshql = "from PdasmycVehPcb where smycyypch='"+obj[0]+"'";
				List<PdasmycVehPcb> mslist = pdasmycVehPcbDao.getRepositories(mshql);
				if(null != mslist && mslist.size() > 0){
					for(PdasmycVehPcb pv : mslist){
						if(null != pv && null != pv.getYcpch() && !"".equals(pv.getYcpch())){
							fenpeishu ++;
						}
						if(null != pv && null != pv.getLsh() && !"".equals(pv.getLsh())){
							String sql2 = "select count(0) from pdasmyc_temp_mid_out_n t where t.lsh='" + pv.getLsh() + "'";
							List sql2list = pdasmycVehPcbDao.findSQL(sql2);
							if(null != sql2list && sql2list.size() > 0 && !"0".equals(sql2list.get(0).toString())){
								yni ++;
							}
						}
					}
					if(yni < Integer.parseInt(obj[1].toString())){
						yni = Integer.parseInt(obj[1].toString()) - yni;
					}else{
						yni = 0;
					}
				}
				obj[4] = fenpeishu + "/" + obj[1];
				String zt = "";
				if(fenpeishu < yuyueshu){
					zt = "未分配验车";
				}else{
					zt = "已分配验车";
				}
				obj[5] = zt;
				obj[6] = fenpeishu;
				obj[7] = yni;
				pdasmycVehPcbList.add(obj);
			}
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
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getPdasmycVehPcbList|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes", "static-access" })
	public List<PdasmycVehPcb> getPdasmycVehPcbList2(HttpServletRequest request,int currentPage) throws Exception {
		StringBuffer hql = new StringBuffer("select distinct t.smycyypch,t.smycyypchsl,t.chmc,t.yycgbm from pdasmyc_veh_pcb t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(distinct t.smycyypch) from pdasmyc_veh_pcb t where 1=1");
		String dbjg = request.getParameter("dbjg");
		String pch = request.getParameter("pch");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		
		//User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List pdasmycVehPcbList = new ArrayList();
		
		
		DateUtil du = new DateUtil();
		
		//所属部门
		/*String deptid = getDeptUpid(user.getBmid());
		if(null != deptid && !"".equals(deptid)){
			hql.append(" and t.yycgbm = '" + deptid + "' ");
			sql.append(" and t.yycgbm = '" + deptid + "' ");
			request.setAttribute("deptid", deptid);
		}*/

		// 代办机构
		if (dbjg != null && !dbjg.equals("")) {
			hql.append(" and t.chmc = '" + dbjg + "' ");
			sql.append(" and t.chmc = '" + dbjg + "' ");
			request.setAttribute("dbjg", dbjg);
		}
		
		// 批次号
		if (pch != null && !pch.equals("")) {
			hql.append(" and t.smycyypch = '" + pch + "' ");
			sql.append(" and t.smycyypch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		//预约日期
		if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !s_date.equals("") && (e_date == null || e_date.equals(""))) {
			e_date = du.date2String(new Date());
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !e_date.equals("") && (s_date == null || s_date.equals(""))) {
			Date d = du.getAppointDate(730);
			s_date = du.date2String(d);
			hql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			sql.append(" and (t.chrq between to_date('" + s_date
					+ "','yyyy-MM-dd') and to_date('" + e_date
					+ "','yyyy-MM-dd')+1 )");
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
				

		int size = pdasmycVehPcbDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			List templist = pdasmycVehPcbDao.findSQLByPage(hql.toString(),offset,pageSize);
			for(int i = 0; i < templist.size(); i ++){
				Object[] obj = new Object[7];
				Object[] tempobj = (Object[]) templist.get(i);
				obj[0] = tempobj[0];
				obj[1] = tempobj[1];
				obj[2] = tempobj[2];
				obj[3] = getDeptName(String.valueOf(tempobj[3]));
				
				Integer fenpeishu = 0;
				Integer yuyueshu = Integer.parseInt(obj[1].toString());
				String mshql = "from PdasmycVehPcb where smycyypch='"+obj[0]+"'";
				List<PdasmycVehPcb> mslist = pdasmycVehPcbDao.getRepositories(mshql);
				if(null != mslist && mslist.size() > 0){
					for(PdasmycVehPcb pv : mslist){
						if(null != pv && null != pv.getYcpch() && !"".equals(pv.getYcpch())){
							fenpeishu ++;
						}
					}
				}
				obj[4] = fenpeishu + "/" + obj[1];
				String zt = "";
//				if(fenpeishu < yuyueshu){
//					zt = "未分配验车";
//				}else{
//					zt = "已分配验车";
//				}
				if(fenpeishu == 0){
					zt = "未分配验车";
				}else if(fenpeishu < yuyueshu){
					zt = "部分已分配验车";
				}else if(fenpeishu < yuyueshu){
					zt = "已分配验车";
				}
				obj[5] = zt;
				obj[6] = fenpeishu;
				pdasmycVehPcbList.add(obj);
			}
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
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getPdasmycVehPcbList|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}

	@SuppressWarnings("unchecked")
	public List<PdasmycVehPcb> getPdasmycVehPcbList(HttpServletRequest request) throws Exception {
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb as t where 1=1 ");
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		pdasmycVehPcbList = pdasmycVehPcbDao.getRepositories(hql.toString());
		log.info("method:getPdasmycVehPcbList|param:request="+request);
		return pdasmycVehPcbList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PdasmycVehPcb> getPdasmycVehPcbViewList(HttpServletRequest request) throws Exception{
		String pch = request.getParameter("pch");
		String type = request.getParameter("type");
		request.setAttribute("type", type);
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb as t where 1=1 ");
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		if(null != pch && !"".equals(pch)){
			hql.append(" and smycyypch='" + pch + "'");
		}
		pdasmycVehPcbList = pdasmycVehPcbDao.getRepositories(hql.toString());
		if(null != pdasmycVehPcbList && pdasmycVehPcbList.size() > 0){
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp && null != pvp.getLsh()){
					if("A".equals(pvp.getYcfpqk())){
						if(null != pvp.getYcpch() && !"".equals(pvp.getYcpch())){
							if(null != pvp.getYcmjcode() && !"".equals(pvp.getYcmjcode())){
								if(null != pvp.getQrjg() && !"".equals(pvp.getQrjg())){
									if("1".equals(pvp.getQrjg())){
										pvp.setYcpch("确认合格");
									}else if("2".equals(pvp.getQrjg())){
										pvp.setYcpch("确认不合格");
									}
								}else{
									pvp.setYcpch("已验车");
								}
							}else{
								pvp.setYcpch("已分配验车");
							}
						}else{
							pvp.setYcpch("未分配验车");
						}
					}else if("B".equals(pvp.getYcfpqk())){
						pvp.setYcpch("已退办");
					}else{
						pvp.setYcpch("未分配验车");
					}
					String hql2 = "from PdasmycTempMidOutN t where t.lsh = '" + pvp.getLsh() + "'";
					List<PdasmycTempMidOutN> ptmonlist = pdasmycTempMidOutNDao.getRepositories(hql2);
					if(null != ptmonlist && ptmonlist.size() > 0){
						PdasmycTempMidOutN ptmon = ptmonlist.get(0);
						pvp.setYcfpqkMs(ptmon.getClsbdh());
						pvp.setYcfpcode(ptmon.getClxh());
					}else{
						pvp.setYcfpqkMs("");
						pvp.setYcfpcode("");
					}
				}
			}
		}
		log.info("method:getPdasmycVehPcbList|param:request="+request);
		return pdasmycVehPcbList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycVehPcb> getPdasmycVehPcbViewList2(HttpServletRequest request,int currentPage) throws Exception{
		String pch = request.getParameter("pch");
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb as t where 1=1 ");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		
		//DateUtil du = new DateUtil();
		//String strdate = "";
		//验车批次号
		if(null != pch && !"".equals(pch)){
			hql.append(" and t.smycyypch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		hql.append(" order by t.ycfprq desc");
		int size = pdasmycVehPcbDao.getSize(hql.toString());
		if (size > 0) {
			pdasmycVehPcbList = pdasmycVehPcbDao.findHQLByPage(hql.toString(),offset,pageSize);
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp){
					if(null != pvp && null != pvp.getLsh()){
						String hql2 = "from PdasmycTempMidOutN t where t.lsh = '" + pvp.getLsh() + "'";
						List<PdasmycTempMidOutN> ptmonlist = pdasmycTempMidOutNDao.getRepositories(hql2);
						if(null != ptmonlist && ptmonlist.size() > 0){
							PdasmycTempMidOutN ptmon = ptmonlist.get(0);
							pvp.setChid(ptmon.getClsbdh());
						}else{
							pvp.setChid("");
						}
					}
				}
			}
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
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	
	@SuppressWarnings({ "static-access", "unchecked" })
	public Integer fenpeiycmj(String[] ids,String szmj, String yjsj,String fpms,HttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			DateUtil du = new DateUtil();
			String seqsql = "select SEQ_PDASMYC_VEH_PCB.Nextval from dual";
			List<PdasmycVehPcb> seqlist = pdasmycVehPcbDao.findSQL(seqsql);
			String seqstr = "";
			if(null != seqlist && seqlist.size() > 0){
				seqstr = String.valueOf(seqlist.get(0));
			}
			if (null != ids) {
				for (String id : ids) {
					if(null != id && !"".equals(id)){
						PdasmycVehPcb pdasmycVehPcb = pdasmycVehPcbDao.getRepository(id);
						if (pdasmycVehPcb != null) {
							//String cllx = pdasmycVehPcb.getYclx();
							// 小薇要求更改查询表.
							String hql2 = "from VehicleTempMidOut t where t.lsh = '" + pdasmycVehPcb.getLsh() + "'";
							List<VehicleTempMidOut> ptmonlist = vehicleTempMidOutDao.getRepositories(hql2);
							if(null != ptmonlist && ptmonlist.size() > 0){
								VehicleTempMidOut ptmon = ptmonlist.get(0);
								String syxz = ptmon.getSyxz();
								String cllx = ptmon.getCllx();
								//String hql3 = "from PdasmycPoliceSetting t where t.policeCode = '" + szmj + "'";
								//List<PdasmycPoliceSetting> ptmnlist = pdasmycPoliceSettingDao.getRepositories(hql3);
								//if(null != ptmnlist && ptmnlist.size() > 0){
									//PdasmycPoliceSetting pps = ptmnlist.get(0);
									//if(null != pps && cllx.equals(pps.getKzCllx()) && syxz.equals(pps.getKzSyxz())){
										pdasmycVehPcb.setYcfpqk("A");
										pdasmycVehPcb.setYcfpqkMs(fpms);
										pdasmycVehPcb.setYcpch(seqstr);
										pdasmycVehPcb.setYcfpcode(user.getName());
										pdasmycVehPcb.setYcfpxm(user.getYgxm());
										pdasmycVehPcb.setYcfpbm(user.getBmid());
										pdasmycVehPcb.setYcfpbmKj(getDeptUpid(user.getBmid()));
										pdasmycVehPcb.setYcfprq(new Date());
										pdasmycVehPcb.setYjsmsj(du.string2Date(yjsj));
										if(null != szmj && !"".equals(szmj)){
											VVehUserYcs vuy = getUserInfo(szmj);
											if(null != vuy){
												pdasmycVehPcb.setFpYcmjcode(vuy.getLoginId());
												pdasmycVehPcb.setFpYcmjxm(vuy.getUserName());
												pdasmycVehPcb.setFpYcmjbm(vuy.getOrgId());
												pdasmycVehPcb.setFpYcmjbm2(getDeptUpid(vuy.getOrgId()));
												pdasmycVehPcb.setSyn_flag("UW");
												pdasmycVehPcbDao.updateRepository(pdasmycVehPcb);
												PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pdasmycVehPcb);
												pclog.setCzr(user.getName());
												pclog.setCzrxm(user.getYgxm());
												pclog.setCzrbm(user.getBmid());
												pclog.setCzsj(new Date());
												pclog.setCzip(getLoginIp(request));
												pclog.setCznr("U");
												pclog.setCzmac(userMac);
												pdasmycVehPcbLogDao.addRepository(pclog);
											}else{
												Exception e = new Exception("警号没有查到人员信息!");
												throw e;
											}
										}
									//}else{
									//	return 2;
									//}
								//}else{
								//	return 3;
								//}
							}
						}
					}
				}
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}

	public Integer fenpeitb(String[] ids, String tbyyms,HttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String userMac = request.getParameter("userMac");
			if (null != ids) {
				for (String id : ids) {
					if(null != id && !"".equals(id)){
						PdasmycVehPcb pdasmycVehPcb = pdasmycVehPcbDao.getRepository(id);
						if (pdasmycVehPcb != null) {
							pdasmycVehPcb.setYcfpqk("B");
							pdasmycVehPcb.setSyn_flag("UW");
							pdasmycVehPcbDao.updateRepository(pdasmycVehPcb);
							PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pdasmycVehPcb);
							pclog.setCzr(user.getName());
							pclog.setCzrxm(user.getYgxm());
							pclog.setCzrbm(user.getBmid());
							pclog.setCzsj(new Date());
							pclog.setCzip(getLoginIp(request));
							pclog.setCznr("U");
							pclog.setCzmac(userMac);
							pdasmycVehPcbLogDao.addRepository(pclog);
						}
					}
				}
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	@SuppressWarnings("unchecked")
	public List<VVehUserYcs> getVVehUserYcsList(HttpServletRequest request) throws Exception {
		List<VVehUserYcs> vVehUserYcsList = new ArrayList<VVehUserYcs>();
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String deptid = user.getBmid();
		if(null != deptid && !"".equals(deptid)){
			deptid = getDeptList(deptid);
			String hql = "from VVehUserYcs t where t.orgId in(" + deptid + ") and t.loginId not like 'old_%'";
			vVehUserYcsList = vVehUserYcsDao.getRepositories(hql);
		}
		request.setAttribute("vVehUserYcsList", vVehUserYcsList);
		return vVehUserYcsList;
	}

	public PdasmycVehPcb getPdasmycVehPcb(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			log.info("method:getPdasmycVehPcb|param:id="+id);
			return pdasmycVehPcbDao.getRepository(id);
		}
		return null;
	}
	
	public PdasmycTempMidOutN getPdasmycTempMidOutN(String lsh) throws Exception {
		if(null != lsh && !"".equals(lsh)){
			log.info("method:getPdasmycTempMidOutN|lsh="+lsh);
			return pdasmycTempMidOutNDao.getRepository(lsh);
		}
		return null;
	}

	public PdasmycVehPcb getPdasmycVehPcb(String id) throws Exception {
		log.info("method:getPdasmycVehPcb|param:id="+id);
		return pdasmycVehPcbDao.getRepository(id);
	}
	
	
	public String getLoginIp(HttpServletRequest request) throws Exception{
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
	
	public PdasmycVehPcbLog insertPdasmycVehPcbLog(PdasmycVehPcb pdasmycVehPcb) throws Exception{
		PdasmycVehPcbLog pdasmycVehPcblog = new PdasmycVehPcbLog();
		try {
			Field[] fields = pdasmycVehPcb.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = pdasmycVehPcblog.getClass().getDeclaredField(name);
				field.set(pdasmycVehPcblog, fields[i].get(pdasmycVehPcb));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return pdasmycVehPcblog;
	}
	
	public VDataCheckLog insertVDataCheckLog(VDataCheck vDataCheck) throws Exception{
		VDataCheckLog vDataCheckLog = new VDataCheckLog();
		try {
			Field[] fields = vDataCheck.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = vDataCheckLog.getClass().getDeclaredField(name);
				field.set(vDataCheckLog, fields[i].get(vDataCheck));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return vDataCheckLog;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<YwlsglVehSjzd> getYwlsglVehSjzdList(String dmlb) throws Exception {
		List<YwlsglVehSjzd> ywlsglVehSjzdList = new ArrayList<YwlsglVehSjzd>();
		String hql = "from YwlsglVehSjzd ";
		if(null != dmlb && !"".equals(dmlb)){
			hql += "where dmlb='" + dmlb + "'";
		}
		ywlsglVehSjzdList = pdasmycVehPcbDao.getRepositories(hql);
		return ywlsglVehSjzdList;
	}
	
	@SuppressWarnings("rawtypes")
	public String getDeptName(String deptid) throws Exception{
		String deptsql = "select org_name from v_veh_org_ycs t where t.org_id='" + deptid + "'";
		List deptlist = pdasmycVehPcbDao.findSQL(deptsql);
		String deptname = "";
		if(null != deptlist && deptlist.size() > 0){
			deptname = deptlist.get(0).toString();
		}
		return deptname;
	}
	
	@SuppressWarnings("rawtypes")
	public String getDeptUpid(String deptid) throws Exception{
		String deptsql = "select org_id from (select t.* from v_veh_org_ycs t start with " +
					"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
					"where up_org = 'C34702A8FED97CBFE040007F0100339B'";
		List deptidlist = pdasmycVehPcbDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	@SuppressWarnings("rawtypes")
	public String getDeptList(String deptid) throws Exception{
		String deptsql = "select org_id from v_veh_org_ycs start with org_id = (" +
				"select org_id from (select t.* from v_veh_org_ycs t start with " +
				"org_id = '" + deptid + "' connect by prior up_org = org_id) " +
				"where up_org = 'C34702A8FED97CBFE040007F0100339B') connect by prior org_id = up_org";
		List deptidlist = pdasmycVehPcbDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			for(int i = 0; i < deptidlist.size(); i ++){
				String temp = deptidlist.get(i).toString();
				deptids += "'" + temp + "'";
				if(i != (deptidlist.size() - 1)){
					deptids += ",";
				}
			}
		}
		return deptids;
	}
	
	@SuppressWarnings("unchecked")
	public List<VVehOrgYcs> getOrgList() throws Exception{
		String hql = " from VVehOrgYcs t where t.upOrg='C34702A8FED97CBFE040007F0100339B'";
		List<VVehOrgYcs> vvoylist = vVehOrgYcsDao.getRepositories(hql);
		return vvoylist;
	}
	
	@SuppressWarnings("unchecked")
	public VVehUserYcs getUserInfo(String userid) throws Exception{
		String hql = "from VVehUserYcs t where t.loginId='" + userid + "'";
		List<VVehUserYcs> userList = vVehUserYcsDao.getRepositories(hql);
		if(null != userList && userList.size() > 0){
			VVehUserYcs vuy = userList.get(0);
			return vuy;
		}
		return null;
	}
	
	@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
	public List<VVehUserYcs> getYcrwList(HttpServletRequest request,int currentPage) throws Exception{
		String username = request.getParameter("username");
		String s_date = request.getParameter("s_date");
		String e_date = request.getParameter("e_date");
		StringBuffer hql = new StringBuffer("from VVehUserYcs t where 1=1 ");
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<VVehUserYcs> vVehUserYcsList = new ArrayList<VVehUserYcs>();
		DateUtil du = new DateUtil();
		String strdate = "";
		
		String deptidlist = getDeptList(user.getBmid());
		
		if(null != deptidlist && !"".equals(deptidlist)){
			hql.append(" and t.orgId in(" + deptidlist + ") ");
		}
		
		//验车民警
		if(null != username && !"".equals(username)){
			hql.append(" and t.userName = '" + username + "' ");
			request.setAttribute("username", username);
		}
		
		hql.append(" and t.loginId not like 'old_%' ");
		
		//分配时间
		if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			strdate = " and (t.ycfprq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !s_date.equals("") && (e_date == null || e_date.equals(""))) {
			e_date = du.date2String(new Date());
			strdate = " and (t.ycfprq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !e_date.equals("") && (s_date == null || s_date.equals(""))) {
			Date d = du.getAppointDate(-30);
			s_date = du.date2String(d);
			strdate = " and (t.ycfprq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}
		int size = vVehUserYcsDao.getSize(hql.toString());
		if (size > 0) {
			vVehUserYcsList = vVehUserYcsDao.findHQLByPage(hql.toString(),offset,pageSize);
			for(VVehUserYcs vvuy : vVehUserYcsList){
				String zcount = "0";
				Integer yycounti = 0;
				String sqltemp = "select count(distinct t.ycpch) from pdasmyc_veh_pcb t where t.ycfpcode='" + vvuy.getLoginId() + "'" + strdate;
				List templist = vVehUserYcsDao.findSQL(sqltemp);
				if(null != templist && templist.size() > 0){
					zcount = templist.get(0).toString();
				}
				String sqltemp2 = "select distinct t.ycpch from pdasmyc_veh_pcb t where t.ycfpcode='" + vvuy.getLoginId() + "'" + strdate;
				List templist2 = vVehUserYcsDao.findSQL(sqltemp2);
				if(null != templist2 && templist2.size() > 0){
					for(int i = 0; i < templist2.size(); i ++){
						String countstr = "";
						if(null != templist2.get(0) && !"null".equals(templist2.get(0)))countstr = templist2.get(i).toString();
						String sqltemp3 = "select count(1) from pdasmyc_veh_pcb t where t.ycfpcode='" + vvuy.getLoginId() + 
								"' and t.ycjg is null and t.ycpch='" + countstr + "'" + strdate;
						List templist3 = vVehUserYcsDao.findSQL(sqltemp3);
						if(null != templist3 && templist3.size() > 0)if("0".equals(templist3.get(0).toString()))yycounti ++;
					}
				}
				vvuy.setAreaname(yycounti + "/" + zcount);
			}
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
		request.setAttribute("vVehUserYcsList", vVehUserYcsList);
		log.info("method:getYcrwList|param:request="+request+",currentPage="+currentPage);
		return vVehUserYcsList;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycVehPcb> getYcrwList2(HttpServletRequest request,int currentPage) throws Exception{
		String userid = request.getParameter("userid");
		String pch = request.getParameter("pch");
//		String s_date = request.getParameter("s_date");
//		String e_date = request.getParameter("e_date");
		StringBuffer hql = new StringBuffer("select new PdasmycVehPcb(t.ycpch,t.fpYcmjxm,t.ycfprq) from PdasmycVehPcb t where 1=1 ");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		
		//DateUtil du = new DateUtil();
		String strdate = "";
		
		//验车民警
		if(null != userid && !"".equals(userid)){
			hql.append(" and t.ycfpcode = '" + userid + "' ");
			request.setAttribute("userid", userid);
		}
		//验车批次号
		if(null != pch && !"".equals(pch)){
			hql.append(" and t.ycpch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		//验车时间
		/*if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !s_date.equals("") && (e_date == null || e_date.equals(""))) {
			e_date = du.date2String(new Date());
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !e_date.equals("") && (s_date == null || s_date.equals(""))) {
			Date d = du.getAppointDate(-30);
			s_date = du.date2String(d);
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}*/
		
		hql.append(" group by t.ycpch,t.fpYcmjxm,t.ycfprq");
		int size = pdasmycVehPcbDao.getSize(hql.toString());
		if (size > 0) {
			pdasmycVehPcbList = pdasmycVehPcbDao.findHQLByPage(hql.toString(),offset,pageSize);
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp){
					String ycpch = pvp.getYcpch();
					String zcount = "0";
					String yycount = "0";
					String sql = "select wm_concat(distinct t.smycyypch) from pdasmyc_veh_pcb t where t.ycpch='" + ycpch + "'" + strdate;
					List templist = pdasmycVehPcbDao.findSQL(sql);
					if(null != templist && templist.size() > 0){
						if(null != templist.get(0) && !"null".equals(templist.get(0)))pvp.setSmycyypch(String.valueOf(templist.get(0)));
					}
					String sqltemp2 = "select count(1) from pdasmyc_veh_pcb t where t.ycfpcode='" + userid + "' and t.ycpch='" + ycpch + "'" + strdate;
					List templist2 = pdasmycVehPcbDao.findSQL(sqltemp2);
					if(null != templist2 && templist2.size() > 0){
						zcount = templist2.get(0).toString();
						String sqltemp3 = "select count(1) from pdasmyc_veh_pcb t where t.ycfpcode='" + userid + 
								"' and t.ycjg is not null and t.ycpch='" + ycpch + "'" + strdate;
						List templist3 = pdasmycVehPcbDao.findSQL(sqltemp3);
						if(null != templist3 && templist3.size() > 0)yycount = templist3.get(0).toString();
						pvp.setYcjg(yycount + "/" + zcount);
					}
				}
			}
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
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	
	@SuppressWarnings({"unchecked"})
	public List<PdasmycVehPcb> getYcrwList3(HttpServletRequest request,int currentPage) throws Exception{
		String userid = request.getParameter("userid");
		String pch = request.getParameter("pch");
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb t where 1=1 ");
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		
		//DateUtil du = new DateUtil();
		//String strdate = "";
		
		//验车民警
		if(null != userid && !"".equals(userid)){
			hql.append(" and t.ycfpcode = '" + userid + "' ");
			request.setAttribute("userid", userid);
		}
		//验车批次号
		if(null != pch && !"".equals(pch)){
			hql.append(" and t.ycpch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		hql.append(" order by t.ycfprq desc");
		int size = pdasmycVehPcbDao.getSize(hql.toString());
		if (size > 0) {
			pdasmycVehPcbList = pdasmycVehPcbDao.getRepositories(hql.toString());
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp){
					if(null != pvp && null != pvp.getLsh()){
						String hql2 = "from PdasmycTempMidOutN t where t.lsh = '" + pvp.getLsh() + "'";
						List<PdasmycTempMidOutN> ptmonlist = pdasmycTempMidOutNDao.getRepositories(hql2);
						if(null != ptmonlist && ptmonlist.size() > 0){
							PdasmycTempMidOutN ptmon = ptmonlist.get(0);
							pvp.setChid(ptmon.getClsbdh());
						}else{
							pvp.setChid("");
						}
					}
				}
			}
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		request.setAttribute("rscount", size);
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public List<PdasmycVehPcb> getYcrwList4(HttpServletRequest request,int currentPage) throws Exception{
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String userid = user.getName();
		String pch = request.getParameter("pch");
//		String s_date = request.getParameter("s_date");
//		String e_date = request.getParameter("e_date");
		StringBuffer hql = new StringBuffer("select new PdasmycVehPcb(t.ycpch,t.fpYcmjxm,t.ycfprq) from PdasmycVehPcb t where 1=1 ");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		
		//DateUtil du = new DateUtil();
		String strdate = "";
		
		//验车民警
		if(null != userid && !"".equals(userid)){
			hql.append(" and t.ycfpcode = '" + userid + "' ");
			request.setAttribute("userid", userid);
		}
		//验车批次号
		if(null != pch && !"".equals(pch)){
			hql.append(" and t.ycpch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		//验车时间
		/*if (s_date != null && e_date != null && !s_date.equals("") && !e_date.equals("")) {
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (s_date != null && !s_date.equals("") && (e_date == null || e_date.equals(""))) {
			e_date = du.date2String(new Date());
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		} else if (e_date != null && !e_date.equals("") && (s_date == null || s_date.equals(""))) {
			Date d = du.getAppointDate(-30);
			s_date = du.date2String(d);
			strdate = " and (t.ycmjrq between to_date('" + s_date
					+ "','yyyy-MM-dd HH24:mi:ss') and to_date('" + e_date
					+ "','yyyy-MM-dd HH24:mi:ss'))";
			request.setAttribute("s_date", s_date);
			request.setAttribute("e_date", e_date);
		}*/
		
		hql.append(" group by t.ycpch,t.fpYcmjxm,t.ycfprq");
		int size = pdasmycVehPcbDao.getSize(hql.toString());
		if (size > 0) {
			pdasmycVehPcbList = pdasmycVehPcbDao.findHQLByPage(hql.toString(),offset,pageSize);
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp){
					String ycpch = pvp.getYcpch();
					String zcount = "0";
					String yycount = "0";
					String sql = "select wm_concat(distinct t.smycyypch) from pdasmyc_veh_pcb t where t.ycpch='" + ycpch + "'" + strdate;
					List templist = pdasmycVehPcbDao.findSQL(sql);
					if(null != templist && templist.size() > 0){
						if(null != templist.get(0) && !"null".equals(templist.get(0)))pvp.setSmycyypch(String.valueOf(templist.get(0)));
					}
					String sqltemp2 = "select count(1) from pdasmyc_veh_pcb t where t.ycfpcode='" + userid + "' and t.ycpch='" + ycpch + "'" + strdate;
					List templist2 = pdasmycVehPcbDao.findSQL(sqltemp2);
					if(null != templist2 && templist2.size() > 0){
						zcount = templist2.get(0).toString();
						String sqltemp3 = "select count(1) from pdasmyc_veh_pcb t where t.ycfpcode='" + userid + 
								"' and t.ycjg is not null and t.ycpch='" + ycpch + "'" + strdate;
						List templist3 = pdasmycVehPcbDao.findSQL(sqltemp3);
						if(null != templist3 && templist3.size() > 0)yycount = templist3.get(0).toString();
						pvp.setYcjg(yycount + "/" + zcount);
					}
				}
			}
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
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	
	@SuppressWarnings({"unchecked"})
	public List<PdasmycVehPcb> getYcrwList5(HttpServletRequest request,HttpServletResponse response,int currentPage) throws Exception{
		String userid = request.getParameter("userid");
		String pch = request.getParameter("pch");
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb t where 1=1 ");
		List<PdasmycVehPcb> pdasmycVehPcbList = new ArrayList<PdasmycVehPcb>();
		
		//DateUtil du = new DateUtil();
		//String strdate = "";
		
		//验车民警
		if(null != userid && !"".equals(userid)){
			hql.append(" and t.ycfpcode = '" + userid + "' ");
			request.setAttribute("userid", userid);
		}
		//验车批次号
		if(null != pch && !"".equals(pch)){
			hql.append(" and t.ycpch = '" + pch + "' ");
			request.setAttribute("pch", pch);
		}
		
		hql.append(" order by t.ycfprq desc");
		int size = pdasmycVehPcbDao.getSize(hql.toString());
		if (size > 0) {
			pdasmycVehPcbList = pdasmycVehPcbDao.getRepositories(hql.toString());
			Map<String, String> barCodeParam = new HashMap<String, String>();
			barCodeParam.put("barType", "CODE39");
			barCodeParam.put("checkCharacter", "n");
			barCodeParam.put("checkCharacterInText", "n");
			for(PdasmycVehPcb pvp : pdasmycVehPcbList){
				if(null != pvp){
					if(barServlet == null){
						barServlet = new BarCodeServlet();
					}
					barCodeParam.put("code", pvp.getLsh());
					File f = new File("C:/barcode4j/");
			        // 创建文件夹
			        if (!f.exists()) {
			            f.mkdirs();
			        }
					barServlet.createPicture(request, response, barCodeParam, new File("C:/barcode4j/"+pvp.getLsh()+".png"), "png");
					pvp.setChip("C:/barcode4j/"+pvp.getLsh()+".png");
					if(null != pvp && null != pvp.getLsh()){
						String hql2 = "from PdasmycTempMidOutN t where t.lsh = '" + pvp.getLsh() + "'";
						List<PdasmycTempMidOutN> ptmonlist = pdasmycTempMidOutNDao.getRepositories(hql2);
						if(null != ptmonlist && ptmonlist.size() > 0){
							PdasmycTempMidOutN ptmon = ptmonlist.get(0);
							pvp.setChid(ptmon.getClsbdh());
						}else{
							pvp.setChid("");
						}
					}
					if("1".equals(pvp.getYcjg())){
						pvp.setYclx("已验车");
						pvp.setYcjg("合格");
					}else if("2".equals(pvp.getYcjg())){
						pvp.setYclx("已验车");
						pvp.setYcjg("不合格");
					}else{
						pvp.setYclx("未验车");
					}
				}
			}
			request.setAttribute("rscount", size);
		} else {
			request.setAttribute("rscount", 0);
		}
		request.setAttribute("rscount", size);
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PdasmycVehPcb getPdasmycVehPcb(String lsh,String clsbdh) throws Exception{
		String hql = "from PdasmycVehPcb t where t.lsh = '" + lsh + "'";
		String hql2 = "from PdasmycTempMidOutN t where t.clsbdh = '" + clsbdh + "'";
		String sql = "select count(0) from pdaphoto where lsh='" + lsh + "'";
		PdasmycVehPcb pvp = null;
		if(null != lsh && !"".equals(lsh)){
			List<PdasmycVehPcb> list = pdasmycVehPcbDao.getRepositories(hql);
			if(null != list && list.size() > 0){
				pvp = list.get(0);
				if(null != pvp.getYclx() && !"".equals(pvp.getYclx())){
					if("AA".equals(pvp.getYclx()))pvp.setYclx("未售出新车");
					else if("AB".equals(pvp.getYclx()))pvp.setYclx("已售出新车");
					else if("BB".equals(pvp.getYclx()))pvp.setYclx("未售出在用车");
					else if("BC".equals(pvp.getYclx()))pvp.setYclx("已售出在用车");
				}
				if(null != pvp.getYycgbm() && !"".equals(pvp.getYycgbm())){
					pvp.setYycgbm(getDeptName(pvp.getYycgbm()));
				}
				if(null != pvp.getYcfpbm() && !"".equals(pvp.getYcfpbm())){
					pvp.setYcfpbm(getDeptName(pvp.getYcfpbm()));
				}
				if(null != pvp.getFpYcmjbm() && !"".equals(pvp.getFpYcmjbm())){
					pvp.setFpYcmjbm(getDeptName(pvp.getFpYcmjbm()));
				}
				if(null != pvp.getYcjg() && !"".equals(pvp.getYcjg())){
					if("1".equals(pvp.getYcjg()))pvp.setYcjg("合格");
					else if("2".equals(pvp.getYcjg()))pvp.setYcjg("不合格");
				}
				if(null != pvp.getQrjg() && !"".equals(pvp.getQrjg())){
					if("1".equals(pvp.getQrjg()))pvp.setQrjg("合格");
					else if("2".equals(pvp.getQrjg()))pvp.setQrjg("不合格");
				}
				if(null != pvp.getYcmjbm() && !"".equals(pvp.getYcmjbm())){
					pvp.setYcmjbm(getDeptName(pvp.getYcmjbm()));
				}
				if(null != pvp.getQrmjbm() && !"".equals(pvp.getQrmjbm())){
					pvp.setQrmjbm(getDeptName(pvp.getQrmjbm()));
				}
				List listpzzt = pdasmycVehPcbDao.findSQL(sql);
				if(null != listpzzt && listpzzt.size() > 0){
					String pzzt = listpzzt.toString();
					if("0".equals(pzzt)){
						pvp.setPzzt("否");
					}
					else{
						pvp.setPzzt("是");
					}
				}
			}
		}else if(null != clsbdh && !"".equals(clsbdh)){
			List<PdasmycTempMidOutN> list = pdasmycTempMidOutNDao.getRepositories(hql2);
			if(null != list && list.size() > 0){
				PdasmycTempMidOutN ptmon = list.get(0);
				if(null != ptmon){
					String hql3 = "from PdasmycVehPcb t where t.lsh = '" + ptmon.getLsh() + "'";
					List<PdasmycVehPcb> list2 = pdasmycVehPcbDao.getRepositories(hql3);
					if(null != list2 && list2.size() > 0){
						pvp = list2.get(0);
						if(null != pvp.getYclx() && !"".equals(pvp.getYclx())){
							if("AA".equals(pvp.getYclx()))pvp.setYclx("未售出新车");
							else if("AB".equals(pvp.getYclx()))pvp.setYclx("已售出新车");
							else if("BB".equals(pvp.getYclx()))pvp.setYclx("未售出在用车");
							else if("BC".equals(pvp.getYclx()))pvp.setYclx("已售出在用车");
						}
						if(null != pvp.getYycgbm() && !"".equals(pvp.getYycgbm())){
							pvp.setYycgbm(getDeptName(pvp.getYycgbm()));
						}
						if(null != pvp.getYcfpbm() && !"".equals(pvp.getYcfpbm())){
							pvp.setYcfpbm(getDeptName(pvp.getYcfpbm()));
						}
						if(null != pvp.getFpYcmjbm() && !"".equals(pvp.getFpYcmjbm())){
							pvp.setFpYcmjbm(getDeptName(pvp.getFpYcmjbm()));
						}
						if(null != pvp.getYcjg() && !"".equals(pvp.getYcjg())){
							if("1".equals(pvp.getYcjg()))pvp.setYcjg("合格");
							else if("2".equals(pvp.getYcjg()))pvp.setYcjg("不合格");
						}
						if(null != pvp.getYcmjbm() && !"".equals(pvp.getYcmjbm())){
							pvp.setYcmjbm(getDeptName(pvp.getYcmjbm()));
						}
						if(null != pvp.getQrjg() && !"".equals(pvp.getQrjg())){
							if("1".equals(pvp.getQrjg()))pvp.setQrjg("合格");
							else if("2".equals(pvp.getQrjg()))pvp.setQrjg("不合格");
						}
						if(null != pvp.getQrmjbm() && !"".equals(pvp.getQrmjbm())){
							pvp.setQrmjbm(getDeptName(pvp.getQrmjbm()));
						}
						List listpzzt = pdasmycVehPcbDao.findSQL(sql);
						if(null != listpzzt && listpzzt.size() > 0){
							String pzzt = listpzzt.toString();
							if("0".equals(pzzt)){
								pvp.setPzzt("否");
							}
							else{
								pvp.setPzzt("是");
							}
						}
					}
				}
			}
		}
		return pvp;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PdasmycVehPcb getPdasmycVehPcb2(String lsh) throws Exception{
		String hql = "from PdasmycVehPcb t where t.lsh = '" + lsh + "'";
		String sql = "select count(0) from pdaphoto where lsh='" + lsh + "'";
		PdasmycVehPcb pvp = null;
		if(null != lsh && !"".equals(lsh)){
			List<PdasmycVehPcb> list = pdasmycVehPcbDao.getRepositories(hql);
			if(null != list && list.size() > 0){
				pvp = list.get(0);
				if(null != pvp.getYclx() && !"".equals(pvp.getYclx())){
					if("AA".equals(pvp.getYclx()))pvp.setYclx("未售出新车");
					else if("AB".equals(pvp.getYclx()))pvp.setYclx("已售出新车");
					else if("BB".equals(pvp.getYclx()))pvp.setYclx("未售出在用车");
					else if("BC".equals(pvp.getYclx()))pvp.setYclx("已售出在用车");
				}
				if(null != pvp.getYclxSj() && !"".equals(pvp.getYclxSj())){
					if("0".equals(pvp.getYclxSj()))pvp.setYclxSj("上门验车");
					else if("1".equals(pvp.getYclxSj()))pvp.setYclxSj("现场验车");
				}
				if(null != pvp.getYycgbm() && !"".equals(pvp.getYycgbm())){
					pvp.setYycgbm(getDeptName(pvp.getYycgbm()));
				}
				if(null != pvp.getYcfpbm() && !"".equals(pvp.getYcfpbm())){
					pvp.setYcfpbm(getDeptName(pvp.getYcfpbm()));
				}
				if(null != pvp.getYcfpbmKj() && !"".equals(pvp.getYcfpbmKj())){
					pvp.setYcfpbmKj(getDeptName(pvp.getYcfpbmKj()));
				}
				if(null != pvp.getFpYcmjbm() && !"".equals(pvp.getFpYcmjbm())){
					pvp.setFpYcmjbm(getDeptName(pvp.getFpYcmjbm()));
				}
				if(null != pvp.getFpYcmjbm2() && !"".equals(pvp.getFpYcmjbm2())){
					pvp.setFpYcmjbm2(getDeptName(pvp.getFpYcmjbm2()));
				}
				if(null != pvp.getYcjg() && !"".equals(pvp.getYcjg())){
					if("1".equals(pvp.getYcjg()))pvp.setYcjg("合格");
					else if("2".equals(pvp.getYcjg()))pvp.setYcjg("不合格");
				}
				if(null != pvp.getQrjg() && !"".equals(pvp.getQrjg())){
					if("1".equals(pvp.getQrjg()))pvp.setQrjg("合格");
					else if("2".equals(pvp.getQrjg()))pvp.setQrjg("不合格");
				}
				if(null != pvp.getYcmjbm() && !"".equals(pvp.getYcmjbm())){
					pvp.setYcmjbm(getDeptName(pvp.getYcmjbm()));
				}
				if(null != pvp.getQrmjbm() && !"".equals(pvp.getQrmjbm())){
					pvp.setQrmjbm(getDeptName(pvp.getQrmjbm()));
				}
				List listpzzt = pdasmycVehPcbDao.findSQL(sql);
				if(null != listpzzt && listpzzt.size() > 0){
					String pzzt = listpzzt.toString();
					if("0".equals(pzzt)){
						pvp.setPzzt("否");
					}
					else{
						pvp.setPzzt("是");
					}
				}
				if(null != pvp.getYcfpqk() && !"".equals(pvp.getYcfpqk())){
					if("A".equals(pvp.getYcfpqk()))pvp.setYcfpqk("正常");
					else if("B".equals(pvp.getYcfpqk()))pvp.setYcfpqk("退办");
				}
			}
		}
		return pvp;
	}
	
	
	@SuppressWarnings({"unchecked", "rawtypes" })
	public List<VDataCheck> getVDataCheckList(HttpServletRequest request,int currentPage) throws Exception{
		String slsh = request.getParameter("slsh");
		String sclsbdh = request.getParameter("sclsbdh");
		String shphm = request.getParameter("shphm");
		String shpzl = request.getParameter("shpzl");
		StringBuffer hql = new StringBuffer("from VDataCheck t where 1=1 ");
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<VDataCheck> vDataCheckList = new ArrayList<VDataCheck>();
		
		//本人只能看到本人的
		hql.append(" and t.checkYh = '" + user.getName() + "' and t.ycLx='1'");
		
		//流水号
		if(null != slsh && !"".equals(slsh)){
			hql.append(" and t.lsh = '" + slsh + "' ");
			request.setAttribute("slsh", slsh);
		}
		//车辆识别代号
		if(null != sclsbdh && !"".equals(sclsbdh)){
			hql.append(" and t.clsbdh = '" + sclsbdh + "' ");
			request.setAttribute("sclsbdh", sclsbdh);
		}
		//号牌号码
		if(null != shphm && !"".equals(shphm)){
			hql.append(" and t.hphm = '" + shphm + "' ");
			request.setAttribute("shphm", shphm);
		}
		//号牌种类
		if(null != shpzl && !"".equals(shpzl)){
			hql.append(" and t.hpzl = '" + shpzl + "' ");
			request.setAttribute("shpzl", shpzl);
		}
		
		int size = vDataCheckDao.getSize(hql.toString());
		if (size > 0) {
			vDataCheckList = vDataCheckDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		request.setAttribute("vDataCheckList", vDataCheckList);
		log.info("method:getYcrwList|param:request="+request+",currentPage="+currentPage);
		return vDataCheckList;
	}
	
	public Integer updateVDataCheck(HttpServletRequest request) throws Exception{
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String lshs = request.getParameter("lsh");
			String ycLx = request.getParameter("ycLx");
			String check = request.getParameter("check");
			String yuanyin = request.getParameter("yuanyin");
			/*if(null != yuanyin && !"".equals(yuanyin)){
				yuanyin = URLDecoder.decode(yuanyin,"UTF-8");
			}*/
			String beizhu = request.getParameter("beizhu");
			if(null != beizhu && !"".equals(beizhu)){
				beizhu = URLDecoder.decode(beizhu,"UTF-8");
			}
			List list = vDataCheckDao.getRepositories(" from VDataCheck where lsh='" + lshs + "' and ycLx='" + ycLx + "'");
			if(null != list && list.size() > 0){
				VDataCheck vdc = (VDataCheck)list.get(0);
				vDataCheckDao.updateRepositoryBySql("update V_DATA_CHECK set CHECK_FALG='"+check+"', CHECK_MSG='"+yuanyin+"', CHECK_BZ='"+beizhu+"' where lsh='"+lshs+"' and yc_lx='"+ycLx+"'");
				String lsh = vdc.getLsh();
				VDataCheckLog vdcl = insertVDataCheckLog(vdc);
				vdcl.setCzr(user.getName());
				vdcl.setCzrxm(user.getYgxm());
				vdcl.setCzrbm(user.getBmid());
				vdcl.setCzsj(new Date());
				vdcl.setCzip(getLoginIp(request));
				vdcl.setCznr("U");
				vdcl.setCzmac("");
				vDataCheckLogDao.addRepository(vdcl);
				PdasmycVehPcb pvb = pdasmycVehPcbDao.getRepository(lsh);
				if(null != pvb){
					pvb.setYcjg(check);
					pvb.setSyn_flag("UW");
					pdasmycVehPcbDao.updateRepository(pvb);
					PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pvb);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("U");
					pclog.setCzmac("");
					pdasmycVehPcbLogDao.addRepository(pclog);
				}
				BJdccyxx bj = bJdccyxxDao.getRepository(lsh);
				if(null != bj){
					if("1".equals(check)){
						bj.setCyjg("0");
					}else{
						bj.setCyjg("1");
					}
					bJdccyxxDao.updateRepository(bj);
					String tempsql = "insert into b_jdccylog values(CGSKCB.SEQ_JDCCYLOG_XH.Nextval,'"+lsh+"','"+yuanyin+"','"+beizhu+"','"+user.getName()+"',sysdate,'')";
					bJdccyxxDao.updateRepositoryBySql(tempsql);
				}
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	@SuppressWarnings("rawtypes")
	public List getBhgyyList() throws Exception{
		List list = new ArrayList();
		try {
			String sql = "select dmz,dmsm1 from cgskcb.kcb_Code  where dmlb = '103'";
			list = bJdccyxxDao.findSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Integer updateYybm(HttpServletRequest request) throws Exception{
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String pch = request.getParameter("pch");
			String xbm = request.getParameter("xbm");
			String hql = "from PdasmycVehPcb where smycyypch='"+pch+"'";
			List<PdasmycVehPcb> pcblist = pdasmycVehPcbDao.getRepositories(hql);
			if(null != pcblist && pcblist.size() > 0){
				for(PdasmycVehPcb pvb : pcblist){
					pvb.setYycgbm(xbm);
					pvb.setSyn_flag("UW");
					pdasmycVehPcbDao.updateRepository(pvb);
					PdasmycVehPcbLog pclog = insertPdasmycVehPcbLog(pvb);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("U");
					pclog.setCzmac("");
					pdasmycVehPcbLogDao.addRepository(pclog);
				}
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycVehPcb> getPdasmycVehPcbList3(HttpServletRequest request,int currentPage) throws Exception{
		String slsh = request.getParameter("slsh");
		String sclsbdh = request.getParameter("sclsbdh");
		String shphm = request.getParameter("shphm");
		String shpzl = request.getParameter("shpzl");
		
		String sqlss = "select t.lsh from pdasmyc_veh_pcb t where t.lsh not in(select lsh from pdaphoto)";
		List sslist = pdasmycVehPcbDao.findSQL(sqlss);
		String sstemp = "";
		if(null != sslist && sslist.size() > 0){
			for(Object o : sslist){
				if(null == sstemp || "".equals(sstemp)){
					sstemp = "'" + o.toString() + "'";
				}else{
					sstemp += ",'" + o.toString() + "'";
				}
			}
		}
		
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb as t,VehicleTempMidOut as t1 where t.lsh=t1.lsh and t1.lsh in("+sstemp+") and t.ycjg='1' ");
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List pdasmycVehPcbList = new ArrayList();
		//所属部门
		String deptid = getDeptUpid(user.getBmid());
		if(null != deptid && !"".equals(deptid)){
			hql.append(" and t.ycmjbmKj = '" + deptid + "' ");
			request.setAttribute("deptid", deptid);
		}
		
		//流水号
		if(null != slsh && !"".equals(slsh)){
			hql.append(" and t.lsh = '" + slsh + "' ");
			request.setAttribute("slsh", slsh);
		}
		//车辆识别代号
		if(null != sclsbdh && !"".equals(sclsbdh)){
			hql.append(" and t1.clsbdh = '" + sclsbdh + "' ");
			request.setAttribute("sclsbdh", sclsbdh);
		}
		//号牌号码
		if(null != shphm && !"".equals(shphm)){
			hql.append(" and t1.hphm = '" + shphm + "' ");
			request.setAttribute("shphm", shphm);
		}
		//号牌种类
		if(null != shpzl && !"".equals(shpzl)){
			hql.append(" and t1.hpzl = '" + shpzl + "' ");
			request.setAttribute("shpzl", shpzl);
		}
		
		String sql = "select dmz from ywlsgl_veh_sjzd t where t.dmlb='SMYCPZSX'";
		String dmz = "";
		List templist = pdasmycVehPcbDao.findSQL(sql);
		if(null != templist && templist.size() > 0)dmz = templist.get(0).toString();
		
		int size = 0;
		if("1".equals(dmz)){
			String sql2 = "select dmz from ywlsgl_veh_sjzd t where t.dmlb='SMYCPZSJ'";
			String dmz2 = "";
			List templist2 = pdasmycVehPcbDao.findSQL(sql2);
			if(null != templist && templist.size() > 0)dmz2 = templist2.get(0).toString();
			hql.append(" and (t.ycmjrq + "+dmz2+"/24) < sysdate");
			size = pdasmycVehPcbDao.getSize(hql.toString());
			if(size > 0){
				pdasmycVehPcbList = pdasmycVehPcbDao.findHQLByPage(hql.toString(), offset, pageSize);
				if(null != pdasmycVehPcbList && pdasmycVehPcbList.size() > 0){
					for(int i = 0; i < pdasmycVehPcbList.size(); i ++){
						Object[] obj = (Object[]) pdasmycVehPcbList.get(i);
						PdasmycVehPcb pvb = (PdasmycVehPcb) obj[0];
						String sql3 = "select count(0) from pdasmyc_vehicle_tssp t where t.ywlsh='"+pvb.getLsh()+"'";
						List sql3list = pdasmycVehPcbDao.findSQL(sql3);
						if(null != sql3list && sql3list.size() > 0 && "0".equals(sql3list.get(0).toString())){
							pvb.setChmc("0");
						}else{
							pvb.setChmc("1");
						}
					}
				}
			}
		}
		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycVehPcb> getPdasmycVehPcbList4(HttpServletRequest request,int currentPage) throws Exception{
		String slsh = request.getParameter("slsh");
		String sclsbdh = request.getParameter("sclsbdh");
		String shphm = request.getParameter("shphm");
		String shpzl = request.getParameter("shpzl");
		
		String sqlss = "select t.lsh from pdasmyc_veh_pcb t where t.lsh not in(select lsh from pdaphoto)";
		List sslist = pdasmycVehPcbDao.findSQL(sqlss);
		String sstemp = "";
		if(null != sslist && sslist.size() > 0){
			for(Object o : sslist){
				if(null == sstemp || "".equals(sstemp)){
					sstemp = "'" + o.toString() + "'";
				}else{
					sstemp += ",'" + o.toString() + "'";
				}
			}
		}
		
		
		StringBuffer hql = new StringBuffer("from PdasmycVehPcb as t,VehicleTempMidOut as t1 where t.lsh=t1.lsh and t1.lsh in("+sstemp+") and t.ycjg='1' ");
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List pdasmycVehPcbList = new ArrayList();
		//流水号
		if(null != slsh && !"".equals(slsh)){
			hql.append(" and t.lsh = '" + slsh + "' ");
			request.setAttribute("slsh", slsh);
		}
		//车辆识别代号
		if(null != sclsbdh && !"".equals(sclsbdh)){
			hql.append(" and t1.clsbdh = '" + sclsbdh + "' ");
			request.setAttribute("sclsbdh", sclsbdh);
		}
		//号牌号码
		if(null != shphm && !"".equals(shphm)){
			hql.append(" and t1.hphm = '" + shphm + "' ");
			request.setAttribute("shphm", shphm);
		}
		//号牌种类
		if(null != shpzl && !"".equals(shpzl)){
			hql.append(" and t1.hpzl = '" + shpzl + "' ");
			request.setAttribute("shpzl", shpzl);
		}
		
		String sql = "select dmz from ywlsgl_veh_sjzd t where t.dmlb='SMYCPZSX'";
		String dmz = "";
		List templist = pdasmycVehPcbDao.findSQL(sql);
		if(null != templist && templist.size() > 0)dmz = templist.get(0).toString();
		
		int size = 0;
		if("1".equals(dmz) && null != sstemp && !"".equals(sstemp)){
			String sql2 = "select dmz from ywlsgl_veh_sjzd t where t.dmlb='SMYCPZSJ'";
			String dmz2 = "";
			List templist2 = pdasmycVehPcbDao.findSQL(sql2);
			if(null != templist && templist.size() > 0)dmz2 = templist2.get(0).toString();
			hql.append(" and (t.ycmjrq + "+dmz2+"/24) < sysdate");
			size = pdasmycVehPcbDao.getSize(hql.toString());
			if(size > 0){
				pdasmycVehPcbList = pdasmycVehPcbDao.findHQLByPage(hql.toString(), offset, pageSize);
				if(null != pdasmycVehPcbList && pdasmycVehPcbList.size() > 0){
					for(int i = 0; i < pdasmycVehPcbList.size(); i ++){
						Object[] obj = (Object[]) pdasmycVehPcbList.get(i);
						PdasmycVehPcb pvb = (PdasmycVehPcb) obj[0];
						String sql3 = "select count(0) from pdasmyc_vehicle_tssp t where t.ywlsh='"+pvb.getLsh()+"'";
						List sql3list = pdasmycVehPcbDao.findSQL(sql3);
						if(null != sql3list && sql3list.size() > 0 && "0".equals(sql3list.get(0).toString())){
							pvb.setChmc("0");
						}else{
							pvb.setChmc("1");
						}
					}
				}
			}
		}
		
		Map map=new HashMap();
		map.put("uri", curi);
		map.put("pagesize", pageSize);
		map.put("rscount", size);
		map.put("currentpage", currentPage);
		request.setAttribute("rscount", size);
		request.setAttribute("map", map);
		request.setAttribute("pdasmycVehPcbList", pdasmycVehPcbList);
		log.info("method:getYcrwList2|param:request="+request+",currentPage="+currentPage);
		return pdasmycVehPcbList;
	}
	
	public Integer updateShenPi(HttpServletRequest request) throws Exception{
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String lsh = request.getParameter("lsh");
			String yuanyin = request.getParameter("yuanyin");
			if(null != yuanyin && !"".equals(yuanyin)){
				yuanyin = URLDecoder.decode(yuanyin,"UTF-8");
			}
			String beizhu = request.getParameter("beizhu");
			if(null != beizhu && !"".equals(beizhu)){
				beizhu = URLDecoder.decode(beizhu,"UTF-8");
			}
			PdasmycVehicleTssp pvt = new PdasmycVehicleTssp();
			pvt.setYwlsh(lsh);
			pvt.setTssplx(yuanyin);
			pvt.setBz(beizhu);
			pvt.setShr(user.getName());
			pvt.setShrxm(user.getYgxm());
			pvt.setShbm(user.getBmid());
			pvt.setShbm2(getDeptUpid(user.getBmid()));
			pvt.setShsj(new Date());
			pvt.setShip(getLoginIp(request));
			pdasmycVehicleTsspDao.addRepository(pvt);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	

	/**
	 * @return the pdasmycVehPcbDao
	 */
	public PdasmycVehPcbDao getPdasmycVehPcbDao() {
		return pdasmycVehPcbDao;
	}

	/**
	 * @param pdasmycVehPcbDao the pdasmycVehPcbDao to set
	 */
	public void setPdasmycVehPcbDao(PdasmycVehPcbDao pdasmycVehPcbDao) {
		this.pdasmycVehPcbDao = pdasmycVehPcbDao;
	}

	/**
	 * @return the pdasmycVehPcbLogDao
	 */
	public PdasmycVehPcbLogDao getPdasmycVehPcbLogDao() {
		return pdasmycVehPcbLogDao;
	}

	/**
	 * @param pdasmycVehPcbLogDao the pdasmycVehPcbLogDao to set
	 */
	public void setPdasmycVehPcbLogDao(PdasmycVehPcbLogDao pdasmycVehPcbLogDao) {
		this.pdasmycVehPcbLogDao = pdasmycVehPcbLogDao;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @return the pdasmycPoliceSettingDao
	 */
	public PdasmycPoliceSettingDao getPdasmycPoliceSettingDao() {
		return pdasmycPoliceSettingDao;
	}

	/**
	 * @param pdasmycPoliceSettingDao the pdasmycPoliceSettingDao to set
	 */
	public void setPdasmycPoliceSettingDao(
			PdasmycPoliceSettingDao pdasmycPoliceSettingDao) {
		this.pdasmycPoliceSettingDao = pdasmycPoliceSettingDao;
	}

	/**
	 * @return the pdasmycPoliceSettingLogDao
	 */
	public PdasmycPoliceSettingLogDao getPdasmycPoliceSettingLogDao() {
		return pdasmycPoliceSettingLogDao;
	}

	/**
	 * @param pdasmycPoliceSettingLogDao the pdasmycPoliceSettingLogDao to set
	 */
	public void setPdasmycPoliceSettingLogDao(
			PdasmycPoliceSettingLogDao pdasmycPoliceSettingLogDao) {
		this.pdasmycPoliceSettingLogDao = pdasmycPoliceSettingLogDao;
	}

	/**
	 * @return the pdasmycTempMidOutNDao
	 */
	public PdasmycTempMidOutNDao getPdasmycTempMidOutNDao() {
		return pdasmycTempMidOutNDao;
	}

	/**
	 * @param pdasmycTempMidOutNDao the pdasmycTempMidOutNDao to set
	 */
	public void setPdasmycTempMidOutNDao(PdasmycTempMidOutNDao pdasmycTempMidOutNDao) {
		this.pdasmycTempMidOutNDao = pdasmycTempMidOutNDao;
	}

	/**
	 * @return the vVehUserYcsDao
	 */
	public VVehUserYcsDao getvVehUserYcsDao() {
		return vVehUserYcsDao;
	}

	/**
	 * @param vVehUserYcsDao the vVehUserYcsDao to set
	 */
	public void setvVehUserYcsDao(VVehUserYcsDao vVehUserYcsDao) {
		this.vVehUserYcsDao = vVehUserYcsDao;
	}

	/**
	 * @return the vVehOrgYcsDao
	 */
	public VVehOrgYcsDao getvVehOrgYcsDao() {
		return vVehOrgYcsDao;
	}

	/**
	 * @param vVehOrgYcsDao the vVehOrgYcsDao to set
	 */
	public void setvVehOrgYcsDao(VVehOrgYcsDao vVehOrgYcsDao) {
		this.vVehOrgYcsDao = vVehOrgYcsDao;
	}

	/**
	 * @return the vDataCheckDao
	 */
	public VDataCheckDao getvDataCheckDao() {
		return vDataCheckDao;
	}

	/**
	 * @param vDataCheckDao the vDataCheckDao to set
	 */
	public void setvDataCheckDao(VDataCheckDao vDataCheckDao) {
		this.vDataCheckDao = vDataCheckDao;
	}

	/**
	 * @return the vDataCheckLogDao
	 */
	public VDataCheckLogDao getvDataCheckLogDao() {
		return vDataCheckLogDao;
	}

	/**
	 * @param vDataCheckLogDao the vDataCheckLogDao to set
	 */
	public void setvDataCheckLogDao(VDataCheckLogDao vDataCheckLogDao) {
		this.vDataCheckLogDao = vDataCheckLogDao;
	}

	/**
	 * @return the bJdccyxxDao
	 */
	public BJdccyxxDao getbJdccyxxDao() {
		return bJdccyxxDao;
	}

	/**
	 * @param bJdccyxxDao the bJdccyxxDao to set
	 */
	public void setbJdccyxxDao(BJdccyxxDao bJdccyxxDao) {
		this.bJdccyxxDao = bJdccyxxDao;
	}

	/**
	 * @return the pdasmycVehicleTsspDao
	 */
	public PdasmycVehicleTsspDao getPdasmycVehicleTsspDao() {
		return pdasmycVehicleTsspDao;
	}

	/**
	 * @param pdasmycVehicleTsspDao the pdasmycVehicleTsspDao to set
	 */
	public void setPdasmycVehicleTsspDao(PdasmycVehicleTsspDao pdasmycVehicleTsspDao) {
		this.pdasmycVehicleTsspDao = pdasmycVehicleTsspDao;
	}

	/**
	 * @return the barServlet
	 */
	public BarCodeServlet getBarServlet() {
		return barServlet;
	}

	/**
	 * @param barServlet the barServlet to set
	 */
	public void setBarServlet(BarCodeServlet barServlet) {
		this.barServlet = barServlet;
	}

	public VehicleTempMidOutDao getVehicleTempMidOutDao() {
		return vehicleTempMidOutDao;
	}

	public void setVehicleTempMidOutDao(VehicleTempMidOutDao vehicleTempMidOutDao) {
		this.vehicleTempMidOutDao = vehicleTempMidOutDao;
	}



	

	
}
