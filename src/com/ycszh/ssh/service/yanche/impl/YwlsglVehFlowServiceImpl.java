package com.ycszh.ssh.service.yanche.impl;

import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ycszh.global.SysConst;
import com.ycszh.global.User;
import com.ycszh.ssh.dao.yanche.PdasmycSjzdDao;
import com.ycszh.ssh.dao.yanche.PdasmycTempMidOutNDao;
import com.ycszh.ssh.dao.yanche.PdasmycVehPcbDao;
import com.ycszh.ssh.dao.yanche.VehicleTempMidInDao;
import com.ycszh.ssh.dao.yanche.VehicleTempMidOutDao;
import com.ycszh.ssh.dao.yanche.YwlsglVehFieldglDao;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowDao;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowLogDao;
import com.ycszh.ssh.dao.yanche.YwlsglVehFlowModifyDao;
import com.ycszh.ssh.hbm.yanche.PdasmycSjzd;
import com.ycszh.ssh.hbm.yanche.PdasmycTempMidOutN;
import com.ycszh.ssh.hbm.yanche.PdasmycVehPcb;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidIn;
import com.ycszh.ssh.hbm.yanche.VehicleTempMidOut;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFieldgl;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlow;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlowLog;
import com.ycszh.ssh.hbm.yanche.YwlsglVehFlowModify;
import com.ycszh.ssh.service.yanche.YwlsglVehFlowService;

public class YwlsglVehFlowServiceImpl implements YwlsglVehFlowService {
	
	private YwlsglVehFlowDao ywlsglVehFlowDao;
	private YwlsglVehFlowLogDao ywlsglVehFlowLogDao;
	private YwlsglVehFlowModifyDao ywlsglVehFlowModifyDao;
	private VehicleTempMidOutDao vehicleTempMidOutDao;
	private PdasmycVehPcbDao pdasmycVehPcbDao;
	private YwlsglVehFieldglDao	ywlsglVehFieldglDao;
	private PdasmycSjzdDao pdasmycSjzdDao;
	private PdasmycTempMidOutNDao pdasmycTempMidOutNDao;
	private VehicleTempMidInDao vehicleTempMidInDao;
	private final static Logger log = Logger.getLogger(YwlsglVehFlowServiceImpl.class);

	public Integer insertOrUpdateVehicleTempMidOut(VehicleTempMidOut vehicleTempMidOut,
			HttpServletRequest request) throws Exception {
		if (vehicleTempMidOut != null) {
			String lsh = vehicleTempMidOut.getLsh();
			if(null != vehicleTempMidOut.getCsys() && !"".equals(vehicleTempMidOut.getCsys()))vehicleTempMidOut.setCsys(vehicleTempMidOut.getCsys().replaceAll(" ", ""));
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String kbmid = getDeptUpid(user.getBmid());
			String gw = request.getParameter("gw");
			HttpSession session = request.getSession();
			VehicleTempMidOut ptdo = (VehicleTempMidOut) session.getAttribute("ptdo");
			try {
				YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
				VehicleTempMidIn vehicleTempMidIn = vehicleTempMidInDao.getRepository(lsh);
				Field[] fields = ptdo.getClass().getDeclaredFields();
				Field[] fields2 = vehicleTempMidOut.getClass().getDeclaredFields();
				for (int i = 0; i < 151; i++) {
					if(fields2[i].get(vehicleTempMidOut) != null && !fields2[i].get(vehicleTempMidOut).equals(fields[i].get(ptdo))){
						if(null != fields2[i].get(vehicleTempMidOut) && !"".equals(fields2[i].get(vehicleTempMidOut)) && null != fields[i].get(ptdo)){
							YwlsglVehFlowModify yvfm = new YwlsglVehFlowModify();
							yvfm.setLsh(lsh);
							yvfm.setZdmc(fields2[i].getName().toUpperCase());
							if(fields[i].get(ptdo) != null){
								String tempstr = fields[i].get(ptdo).toString();
								yvfm.setZdOld(tempstr);
							}else{
								yvfm.setZdOld(null);
							}
							yvfm.setZdNew(fields2[i].get(vehicleTempMidOut).toString());
							yvfm.setCzr(user.getName());
							yvfm.setCzrxm(user.getYgxm());
							yvfm.setCzrbm(user.getBmid());
							yvfm.setCzrbmKj(kbmid);
							yvfm.setCzsj(new Date());
							yvfm.setCzip(getLoginIp(request));
							yvfm.setCznr("I");
							yvfm.setCzmac("");
							ywlsglVehFlowModifyDao.addRepository(yvfm);
						}
					}
				}
				if("2001".equals(gw) || "3".equals(ywlsglVehFlow.getLszt())){
					if(null != vehicleTempMidIn){
						for (int i = 0; i < 151; i++) {
							String name = fields2[i].getName();
							Field field = vehicleTempMidIn.getClass().getDeclaredField(name);
							field.set(vehicleTempMidIn, fields2[i].get(vehicleTempMidOut));
						}
						vehicleTempMidInDao.updateRepository(vehicleTempMidIn);
					}
				}
				vehicleTempMidOutDao.updateRepository(vehicleTempMidOut);
				if("3".equals(ywlsglVehFlow.getLszt())){
					ywlsglVehFlow.setLszt("1");
					ywlsglVehFlow.setLsdqgw("2001");
					ywlsglVehFlow.setLsczgw("1001");
				}else{
					ywlsglVehFlow.setLszt("4");
				}
				ywlsglVehFlowDao.updateRepository(ywlsglVehFlow);
				YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCznr("U");
				pclog.setCzmac("");
				ywlsglVehFlowLogDao.addRepository(pclog);
				log.info("method:insertOrUpdateVehicleTempMidOut|param:vehicleTempMidOut="+vehicleTempMidOut);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				throw e;
			}
		} else {
			return 1;
		}
	}
	
	public Integer insertOrUpdateVehicleTempMidIn(VehicleTempMidIn vehicleTempMidIn,
			HttpServletRequest request) throws Exception {
		if (vehicleTempMidIn != null) {
			String lsh = vehicleTempMidIn.getLsh();
			if(null != vehicleTempMidIn.getCsys() && !"".equals(vehicleTempMidIn.getCsys()))vehicleTempMidIn.setCsys(vehicleTempMidIn.getCsys().replaceAll(" ", ""));
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String kbmid = getDeptUpid(user.getBmid());
			HttpSession session = request.getSession();
			VehicleTempMidIn ptdoi = (VehicleTempMidIn) session.getAttribute("ptdoi");
			try {
				vehicleTempMidInDao.updateRepositoryBySql("update vehicle_temp_mid_in t set t.fdjxh='"+vehicleTempMidIn.getFdjxh()+"',t.syr='"+vehicleTempMidIn.getSyr()+"' where lsh='"+lsh+"'");
				YwlsglVehFlowModify yvfm = new YwlsglVehFlowModify();
				yvfm.setLsh(lsh);
				yvfm.setZdmc("fdjxh");
				yvfm.setZdOld(ptdoi.getFdjxh().toString());
				yvfm.setZdNew(vehicleTempMidIn.getFdjxh().toString());
				yvfm.setCzr(user.getName());
				yvfm.setCzrxm(user.getYgxm());
				yvfm.setCzrbm(user.getBmid());
				yvfm.setCzrbmKj(kbmid);
				yvfm.setCzsj(new Date());
				yvfm.setCzip(getLoginIp(request));
				yvfm.setCznr("I");
				yvfm.setCzmac("");
				ywlsglVehFlowModifyDao.addRepository(yvfm);
				YwlsglVehFlowModify yvfm2 = new YwlsglVehFlowModify();
				yvfm2.setLsh(lsh);
				yvfm2.setZdmc("syr");
				yvfm2.setZdOld(ptdoi.getSyr().toString());
				yvfm2.setZdNew(vehicleTempMidIn.getSyr().toString());
				yvfm2.setCzr(user.getName());
				yvfm2.setCzrxm(user.getYgxm());
				yvfm2.setCzrbm(user.getBmid());
				yvfm2.setCzrbmKj(kbmid);
				yvfm2.setCzsj(new Date());
				yvfm2.setCzip(getLoginIp(request));
				yvfm2.setCznr("I");
				yvfm2.setCzmac("");
				ywlsglVehFlowModifyDao.addRepository(yvfm2);
				log.info("method:insertOrUpdateVehicleTempMidIn|param:vehicleTempMidIn="+vehicleTempMidIn);
				return 0;
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e);
				throw e;
			}
		} else {
			return 1;
		}
	}

	public Integer deleteYwlsglVehFlow(String lsh) throws Exception {
		try {
			YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
			if (ywlsglVehFlow != null) {
				ywlsglVehFlowDao.deleteRepository(ywlsglVehFlow);
				log.info("method:deleteYwlsglVehFlow|param:lsh="+lsh);
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

	public Integer deleteYwlsglVehFlowList(String[] lshs) throws Exception {
		try {
			Collection<YwlsglVehFlow> ywlsglVehFlowlist = new ArrayList<YwlsglVehFlow>();
			if (null != lshs) {
				for (String id : lshs) {
					if(null != id && !"".equals(id)){
						YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(id);
						if (ywlsglVehFlow != null) {
							ywlsglVehFlowlist.add(ywlsglVehFlow);
						}
					}
				}
			}
			if (ywlsglVehFlowlist != null && ywlsglVehFlowlist.size() > 0) {
				ywlsglVehFlowDao.deleteRepositoryList(ywlsglVehFlowlist);
				log.info("method:deleteYwlsglVehFlowList|param:lshs="+lshs);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<YwlsglVehFlow> getYwlsglVehFlowList(HttpServletRequest request,int currentPage) throws Exception {
		List<YwlsglVehFlow> ywlsglVehFlowList;
		try {
			StringBuffer hql = new StringBuffer("from YwlsglVehFlow as t where 1=1 ");
			StringBuffer sql = new StringBuffer("select count(0) from YWLSGL_VEH_FLOW t where 1=1 ");
			String lsh = request.getParameter("lsh");
			String syr = request.getParameter("syr");
			String hphm = request.getParameter("hphm");
			String gw = request.getParameter("gw");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String kbmid = getDeptUpid(user.getBmid());
			
			int pageSize = SysConst.PAGESIZE;
			int offset = SysConst.PAGESIZE*(currentPage-1);
			String curi = request.getRequestURI();
			ywlsglVehFlowList = new ArrayList<YwlsglVehFlow>();
			
			//根据不同岗位搜索
			if (gw != null && !"".equals(gw)) {
				if("1001".equals(gw)){
					hql.append(" and t.cygbm = '" + kbmid + "' ");
					sql.append(" and t.cygbm = '" + kbmid + "' ");
				}else if("2001".equals(gw)){
					hql.append(" and t.slgbm = '" + kbmid + "' ");
					sql.append(" and t.slgbm = '" + kbmid + "' ");
				}
				hql.append(" and t.lsdqgw = '" + gw + "' ");
				sql.append(" and t.lsdqgw = '" + gw + "' ");
				request.setAttribute("gw", gw);
			}

			// 流水号
			if (lsh != null && !"".equals(lsh)) {
				lsh = lsh.replaceAll(" ", "");
				lsh = lsh.replaceAll("'", "");
				lsh = lsh.replaceAll("\"", "");
				lsh = lsh.replaceAll("，", ",");
				hql.append(" and t.lsh = '" + lsh + "' ");
				sql.append(" and t.lsh = '" + lsh + "' ");
				request.setAttribute("lsh", lsh);
			}
			
			// 所有人
			if (syr != null && !"".equals(syr)) {
				syr = syr.replaceAll(" ", "");
				syr = syr.replaceAll("'", "");
				syr = syr.replaceAll("\"", "");
				syr = syr.replaceAll("，", ",");
				hql.append(" and t.syr = '" + syr + "' ");
				sql.append(" and t.syr = '" + syr + "' ");
				request.setAttribute("syr", syr);
			}
			
			// 号牌号码
			if (hphm != null && !"".equals(hphm)) {
				hphm = hphm.replaceAll(" ", "");
				hphm = hphm.replaceAll("'", "");
				hphm = hphm.replaceAll("\"", "");
				hphm = hphm.replaceAll("，", ",");
				hql.append(" and t.hphm = '" + hphm + "' ");
				sql.append(" and t.hphm = '" + hphm + "' ");
				request.setAttribute("hphm", hphm);
			}

			if((lsh == null || "".equals(lsh)) && (syr == null || "".equals(syr)) && (hphm == null || "".equals(hphm))){
				hql.append(" and 1=2");
				sql.append(" and 1=2");
			}
			
			hql.append(" and t.lszt !='3'");
			sql.append(" and t.lszt !='3'");
			
			int size = ywlsglVehFlowDao.getRepositoryBySQLListSize(sql.toString());
			if (size > 0) {
				ywlsglVehFlowList = ywlsglVehFlowDao.findHQLByPage(hql.toString(),offset,pageSize);
				if(ywlsglVehFlowList.size() > 0){
					for(YwlsglVehFlow flow : ywlsglVehFlowList){
						if(null != flow){
							List<PdasmycSjzd> lsztlist = pdasmycSjzdDao.getRepositories("from PdasmycSjzd where dmlb = 'LSZT' and dmz='" + flow.getLszt() +"'");
							if(lsztlist.size() > 0){
								PdasmycSjzd ps = lsztlist.get(0);
								flow.setLszt(ps.getDmsm1());
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
			request.setAttribute("ywlsglVehFlowList", ywlsglVehFlowList);
			log.info("method:getYwlsglVehFlowList|param:request="+request+",currentPage="+currentPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return ywlsglVehFlowList;
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<VehicleTempMidIn> getYwlsglVehFlowListt(HttpServletRequest request,int currentPage) throws Exception {
		List<VehicleTempMidIn> vehicleTempMidInList;
		try {
			StringBuffer hql = new StringBuffer("from VehicleTempMidIn as t where 1=1 ");
			StringBuffer sql = new StringBuffer("select count(0) from VEHICLE_TEMP_MID_IN t where 1=1 ");
			String lsh = request.getParameter("lsh");
			int pageSize = SysConst.PAGESIZE;
			int offset = SysConst.PAGESIZE*(currentPage-1);
			String curi = request.getRequestURI();
			vehicleTempMidInList = new ArrayList<VehicleTempMidIn>();
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String kbmid = getDeptUpid(user.getBmid());
			//C34702A8FFEB7CBFE040007F0100339B机管科
			//C34702A8FFED7CBFE040007F0100339B档案科
			//C34702A8FEF07CBFE040007F0100339B审验科
			
			String bmpd = "0";//部门判断正常
			String inbmid = "";
			String kinbmid = "";
			String bmsql = "select org_id from v_veh_user_ycs t where exists (select b.check_yh from v_data_check b  where b.lsh='"+lsh+"' and b.Yc_Lx<>'1' and t.login_id=b.check_yh and rownum=1 )";
			List bmsqllist = vehicleTempMidInDao.findSQL(bmsql);
			if(null != bmsqllist && bmsqllist.size() > 0){
				inbmid = bmsqllist.get(0).toString();
				kinbmid = getDeptUpid(inbmid);
			}
			
			if("C34702A8FFEB7CBFE040007F0100339B".equals(kbmid) || "C34702A8FFED7CBFE040007F0100339B".equals(kbmid)){
				if(null != kinbmid && !"".equals(kinbmid) && !"C34702A8FEF07CBFE040007F0100339B".equals(kinbmid)){
					bmpd = "1";//机管科或档案科则只能修改审验科验车的数据
				}
			}else{
				if(null != kinbmid && !"".equals(kinbmid) && !kbmid.equals(kinbmid)){
					bmpd = "2";//非机管科或档案科的修改用户，只能修改本部门的验车数据
				}
			}
			int size = 0;

			// 流水号
			if (lsh != null && !"".equals(lsh)) {
				lsh = lsh.replaceAll(" ", "");
				lsh = lsh.replaceAll("'", "");
				lsh = lsh.replaceAll("\"", "");
				lsh = lsh.replaceAll("，", ",");
				hql.append(" and t.lsh = '" + lsh + "' ");
				sql.append(" and t.lsh = '" + lsh + "' ");
				request.setAttribute("lsh", lsh);
				size = ywlsglVehFlowDao.getRepositoryBySQLListSize(sql.toString());
			}
			if (size > 0) {
				vehicleTempMidInList = vehicleTempMidInDao.findHQLByPage(hql.toString(),offset,pageSize);
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
			request.setAttribute("bmpd", bmpd);
			request.setAttribute("vehicleTempMidInList", vehicleTempMidInList);
			log.info("method:getYwlsglVehFlowListt|param:request="+request+",currentPage="+currentPage);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return vehicleTempMidInList;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<PdasmycTempMidOutN> getPdasmycTempMidOutNList(HttpServletRequest request,int currentPage) throws Exception{
		StringBuffer hql = new StringBuffer("from PdasmycTempMidOutN as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from PDASMYC_TEMP_MID_OUT_N t where 1=1 ");
		String lsh = request.getParameter("lsh");
		String syr = request.getParameter("syr");
		String hphm = request.getParameter("hphm");
		
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<PdasmycTempMidOutN> pdasmycTempMidOutNList = new ArrayList<PdasmycTempMidOutN>();
		

		// 流水号
		if (lsh != null && !"".equals(lsh)) {
			hql.append(" and t.lsh = '" + lsh + "' ");
			sql.append(" and t.lsh = '" + lsh + "' ");
			request.setAttribute("lsh", lsh);
		}
		
		// 所有人
		if (syr != null && !"".equals(syr)) {
			hql.append(" and t.syr = '" + syr + "' ");
			sql.append(" and t.syr = '" + syr + "' ");
			request.setAttribute("syr", syr);
		}
		
		// 号牌号码
		if (hphm != null && !"".equals(hphm)) {
			hql.append(" and t.hphm = '" + hphm + "' ");
			sql.append(" and t.hphm = '" + hphm + "' ");
			request.setAttribute("hphm", hphm);
		}
		
		int size = pdasmycTempMidOutNDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			pdasmycTempMidOutNList = pdasmycTempMidOutNDao.findHQLByPage(hql.toString(),offset,pageSize);
			if(pdasmycTempMidOutNList.size() > 0){
				for(PdasmycTempMidOutN ptdo : pdasmycTempMidOutNList){
					YwlsglVehFlow flow = ywlsglVehFlowDao.getRepository(ptdo.getLsh());
					if(null != flow){
						List<PdasmycSjzd> lsztlist = pdasmycSjzdDao.getRepositories("from PdasmycSjzd where dmlb = 'LSZT' and dmz='" + flow.getLszt() +"'");
						if(lsztlist.size() > 0){
							PdasmycSjzd ps = lsztlist.get(0);
							ptdo.setBh(ps.getDmsm1());
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
		request.setAttribute("pdasmycTempMidOutNList", pdasmycTempMidOutNList);
		log.info("method:getPdasmycTempMidOutNList|param:request="+request+",currentPage="+currentPage);
		return pdasmycTempMidOutNList;
	}
	
	public VehicleTempMidOut getVehicleTempMidOut(String lsh) throws Exception{
		return vehicleTempMidOutDao.getRepository(lsh);
	}
	
	public VehicleTempMidIn getVehicleTempMidIn(String lsh) throws Exception{
		return vehicleTempMidInDao.getRepository(lsh);
	}
	

	@SuppressWarnings("unchecked")
	public List<YwlsglVehFlow> getYwlsglVehFlowList(HttpServletRequest request)
			throws Exception {
		StringBuffer hql = new StringBuffer("from YwlsglVehFlow as t where 1=1 ");
		List<YwlsglVehFlow> ywlsglVehFlowList = new ArrayList<YwlsglVehFlow>();
		ywlsglVehFlowList = ywlsglVehFlowDao.getRepositories(hql.toString());
		log.info("method:getYwlsglVehFlowList|param:request="+request);
		return ywlsglVehFlowList;
	}

	public YwlsglVehFlow getYwlsglVehFlow(HttpServletRequest request)
			throws Exception {
		String id = request.getParameter("id");
		if(null != id && !"".equals(id)){
			log.info("method:getYwlsglVehFlow|param:id="+id);
			return ywlsglVehFlowDao.getRepository(id);
		}
		return null;
	}

	public YwlsglVehFlow getYwlsglVehFlow(String lsh) throws Exception {
		log.info("method:getYwlsglVehFlow|param:lsh="+lsh);
		return ywlsglVehFlowDao.getRepository(lsh);
	}
	
	@SuppressWarnings("rawtypes")
	public YwlsglVehFlow getYwlsglVehFlow2(String lsh) throws Exception {
		YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
		if(null != ywlsglVehFlow){
			String lsztsm = ywlsglVehFlow.getLsztSm();
			String lsztsmtemp = "";
			if(null != lsztsm && !"".equals(lsztsm)){
				if(lsztsm.indexOf(",") > 0){
					String[] lsztsms = lsztsm.split(",");
					for(String lsztsmtemp2 : lsztsms){
						if("2".equals(ywlsglVehFlow.getLszt())){
							String sql = "select dmms1 from ycs_xyc_code t where t.DMZ='" + lsztsmtemp2 + "'";
							List list = ywlsglVehFlowDao.findSQL(sql);
							if(null != list && list.size() > 0){
								if("".equals(lsztsmtemp)){
									lsztsmtemp += list.get(0).toString();
								}else{
									lsztsmtemp += "," + list.get(0).toString();
								}
							}
						}else if("3".equals(ywlsglVehFlow.getLszt())){
							String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='2001_3' and dmz='" + lsztsmtemp2 + "'";
							List list = ywlsglVehFlowDao.findSQL(sql);
							if(null != list && list.size() > 0){
								if("".equals(lsztsmtemp)){
									lsztsmtemp += list.get(0).toString();
								}else{
									lsztsmtemp += "," + list.get(0).toString();
								}
							}
						}else if("5".equals(ywlsglVehFlow.getLszt())){
							String sql = "select dmsm1 from v_kcb_code t where t.DMLB=103 and t.dmz='" + lsztsmtemp2 + "'";
							List list = ywlsglVehFlowDao.findSQL(sql);
							if(null != list && list.size() > 0){
								if("".equals(lsztsmtemp)){
									lsztsmtemp += list.get(0).toString();
								}else{
									lsztsmtemp += "," + list.get(0).toString();
								}
							}
						}else if("Q".equals(ywlsglVehFlow.getLszt())){
							if(lsztsmtemp2.indexOf("A") >= 0){
								String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='1001_Q' and dmz='" + lsztsmtemp2 + "'";
								List list = ywlsglVehFlowDao.findSQL(sql);
								if(null != list && list.size() > 0){
									if("".equals(lsztsmtemp)){
										lsztsmtemp += list.get(0).toString();
									}else{
										lsztsmtemp += "," + list.get(0).toString();
									}
								}
							}else{
								String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='2001_Q' and dmz='" + lsztsmtemp2 + "'";
								List list = ywlsglVehFlowDao.findSQL(sql);
								if(null != list && list.size() > 0){
									if("".equals(lsztsmtemp)){
										lsztsmtemp += list.get(0).toString();
									}else{
										lsztsmtemp += "," + list.get(0).toString();
									}
								}
							}
						}
					}
				}else{
					if("2".equals(ywlsglVehFlow.getLszt())){
						String sql = "select dmms1 from ycs_xyc_code t where t.DMZ='" + lsztsm + "'";
						List list = ywlsglVehFlowDao.findSQL(sql);
						if(null != list && list.size() > 0){
							lsztsmtemp = list.get(0).toString();
						}
					}else if("3".equals(ywlsglVehFlow.getLszt())){
						String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='2001_3' and dmz='" + lsztsm + "'";
						List list = ywlsglVehFlowDao.findSQL(sql);
						if(null != list && list.size() > 0){
							lsztsmtemp = list.get(0).toString();
						}
					}else if("5".equals(ywlsglVehFlow.getLszt())){
						String sql = "select dmsm1 from v_kcb_code t where t.DMLB=103 and t.dmz='" + lsztsm + "'";
						List list = ywlsglVehFlowDao.findSQL(sql);
						if(null != list && list.size() > 0){
							lsztsmtemp = list.get(0).toString();
						}
					}else if("Q".equals(ywlsglVehFlow.getLszt())){
						if(lsztsm.indexOf("A") > 0){
							String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='1001_Q' and dmz='" + lsztsm + "'";
							List list = ywlsglVehFlowDao.findSQL(sql);
							if(null != list && list.size() > 0){
								lsztsmtemp = list.get(0).toString();
							}
						}else{
							String sql = "select dmsm1 from pdasmyc_sjzd t where t.dmlb='2001_Q' and dmz='" + lsztsm + "'";
							List list = ywlsglVehFlowDao.findSQL(sql);
							if(null != list && list.size() > 0){
								lsztsmtemp = list.get(0).toString();
							}
						}
					}
				}
			}
			ywlsglVehFlow.setLsztSm(lsztsmtemp);
			ywlsglVehFlow.setXh(ywlsglVehFlow.getLszt());
			ywlsglVehFlow.setLszt(getDmmsBySzjd(ywlsglVehFlow.getLszt(),"LSZT"));
			return ywlsglVehFlow;
		}
		log.info("method:getYwlsglVehFlow|param:lsh="+lsh);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public PdasmycTempMidOutN getPdasmycTempMidOutN(String lsh) throws Exception{
		List<PdasmycTempMidOutN> list = pdasmycTempMidOutNDao.getRepositories("from PdasmycTempMidOutN as t where t.lsh='" + lsh + "'");
		if(null != list && list.size() > 0){
			PdasmycTempMidOutN ptmo = list.get(0);
			if(null != ptmo){
				return ptmo;
			}
		}
		log.info("method:getPdasmycTempMidOutN|param:lsh="+lsh);
		return null;
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
	
	@SuppressWarnings("rawtypes")
	public String getDeptName(String deptid) throws Exception{
		String deptsql = "select org_name from v_veh_org_ycs t where t.org_id='" + deptid + "'";
		List deptlist = ywlsglVehFlowDao.findSQL(deptsql);
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
		List deptidlist = ywlsglVehFlowDao.findSQL(deptsql);
		String deptids = "";
		if(null != deptidlist && deptidlist.size() > 0){
			deptids = deptidlist.get(0).toString();
		}
		return deptids;
	}
	
	public YwlsglVehFlowLog insertYwlsglVehFlowLog(YwlsglVehFlow ywlsglVehFlow) throws Exception{
		YwlsglVehFlowLog ywlsglVehFlowLog = new YwlsglVehFlowLog();
		try {
			Field[] fields = ywlsglVehFlow.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				String name = fields[i].getName();
				Field field = ywlsglVehFlowLog.getClass().getDeclaredField(name);
				field.set(ywlsglVehFlowLog, fields[i].get(ywlsglVehFlow));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		return ywlsglVehFlowLog;
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public YwlsglVehFlow selectFlowInfo_thyj(String lsh, HttpServletRequest request) throws Exception {
		try {
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			String kbmid = getDeptUpid(user.getBmid());
			String hql = "";
			YwlsglVehFlow ywlsglVehFlow = null;
			hql = "from YwlsglVehFlow as t where t.lsdqgw='2001' and t.cygbm = '" + kbmid + "' and lsh = '" + lsh + "'";
			List<YwlsglVehFlow> list = ywlsglVehFlowDao.getRepositories(hql);
			if(null != list && list.size() > 0){
				ywlsglVehFlow = list.get(0);
			}
			if(null != ywlsglVehFlow && null != ywlsglVehFlow.getLsh()){
				return ywlsglVehFlow;
			}else{
				YwlsglVehFlow tempflow = ywlsglVehFlowDao.getRepository(lsh);
				if(null == tempflow){
					VehicleTempMidOut vehicleTempMidOut = vehicleTempMidOutDao.getRepository(lsh);
					PdasmycVehPcb pdasmycVehPcb = null;
					String pcbhql = "from PdasmycVehPcb as t where t.ycmjbmKj='" + kbmid + "' and t.lsh='" + lsh + "'";
					List<PdasmycVehPcb> pcblist = pdasmycVehPcbDao.getRepositories(pcbhql);
					if(null != pcblist && pcblist.size() > 0){
						pdasmycVehPcb = pcblist.get(0);
					}
					if(null != vehicleTempMidOut && null != pdasmycVehPcb){
						ywlsglVehFlow = new YwlsglVehFlow();
						ywlsglVehFlow.setLsh(vehicleTempMidOut.getLsh());
						ywlsglVehFlow.setXh(vehicleTempMidOut.getXh());
						ywlsglVehFlow.setYwlx(vehicleTempMidOut.getYwlx());
						ywlsglVehFlow.setYwyy(vehicleTempMidOut.getYwyy());
						ywlsglVehFlow.setSyr(vehicleTempMidOut.getSyr());
						ywlsglVehFlow.setHphm(vehicleTempMidOut.getHphm());
						ywlsglVehFlow.setHpzl(vehicleTempMidOut.getHpzl());
						ywlsglVehFlow.setClpp1(vehicleTempMidOut.getClpp1());
						ywlsglVehFlow.setClxh(vehicleTempMidOut.getClxh());
						ywlsglVehFlow.setCllx(vehicleTempMidOut.getCllx());
						ywlsglVehFlow.setClsbdh(vehicleTempMidOut.getClsbdh());
						ywlsglVehFlow.setKsrq(pdasmycVehPcb.getChrq());
						ywlsglVehFlow.setBjrq(null);
						ywlsglVehFlow.setCygbm(kbmid);
						ywlsglVehFlow.setYwlc("2001");
						ywlsglVehFlow.setLsdqgw("2001");
						ywlsglVehFlow.setLszt("1");
						ywlsglVehFlow.setCzr(user.getName());
						ywlsglVehFlow.setCzrxm(user.getYgxm());
						ywlsglVehFlow.setCzrbm(user.getBmid());
						ywlsglVehFlow.setCzrbmKj(kbmid);
						ywlsglVehFlow.setCzip(getLoginIp(request));
						ywlsglVehFlow.setCzmac("");
						ywlsglVehFlow.setCznr("I");
						ywlsglVehFlow.setCzsj(new Date());
						ywlsglVehFlowDao.addRepository(ywlsglVehFlow);
						YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
						pclog.setCzr(user.getName());
						pclog.setCzrxm(user.getYgxm());
						pclog.setCzrbm(user.getBmid());
						pclog.setCzsj(new Date());
						pclog.setCzip(getLoginIp(request));
						pclog.setCznr("I");
						pclog.setCzmac("");
						ywlsglVehFlowLogDao.addRepository(pclog);
						return ywlsglVehFlow;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	public Integer lstb(String lsh,HttpServletRequest request) throws Exception{
		try {
			YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
			String tbyy = request.getParameter("tbyy");
			String tbsm = request.getParameter("tbsm");
			if(null != tbsm && !"".equals(tbsm))tbsm = URLDecoder.decode(tbsm,"UTF-8");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if(null != ywlsglVehFlow){
				ywlsglVehFlow.setLszt("Q");
				ywlsglVehFlow.setLsztSm(tbyy);
				ywlsglVehFlow.setBz(tbsm);
				ywlsglVehFlowDao.updateRepository(ywlsglVehFlow);
				YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCznr("U");
				pclog.setCzmac("");
				ywlsglVehFlowLogDao.addRepository(pclog);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	public Integer lsthyj(String lsh,HttpServletRequest request) throws Exception{
		try {
			YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
			String tcygyy = request.getParameter("tcygyy");
			String tcygsm = request.getParameter("tcygsm");
			if(null != tcygsm && !"".equals(tcygsm))tcygsm = URLDecoder.decode(tcygsm,"UTF-8");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if(null != ywlsglVehFlow){
				ywlsglVehFlow.setLszt("3");
				ywlsglVehFlow.setLsdqgw("1001");
				ywlsglVehFlow.setLsczgw("2001");
				ywlsglVehFlow.setLsztSm(tcygyy);
				ywlsglVehFlow.setBz(tcygsm);
				ywlsglVehFlowDao.updateRepository(ywlsglVehFlow);
				YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCznr("U");
				pclog.setCzmac("");
				ywlsglVehFlowLogDao.addRepository(pclog);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	public Integer lszxyc(String lsh,HttpServletRequest request) throws Exception{
		try {
			YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
			VehicleTempMidOut vehicleTempMidOut = vehicleTempMidOutDao.getRepository(lsh);
			String zxykyy = request.getParameter("zxykyy");
			String zxyksm = request.getParameter("zxyksm");
			if(null != zxyksm && !"".equals(zxyksm))zxyksm = URLDecoder.decode(zxyksm,"UTF-8");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if(null != ywlsglVehFlow && null != vehicleTempMidOut){
				String returnstr = vehicleTempMidOutDao.getPro(vehicleTempMidOut,zxykyy,user);
				if("0".equals(returnstr)){
					ywlsglVehFlow.setLszt("2");
					ywlsglVehFlow.setLsztSm(zxykyy);
					ywlsglVehFlow.setBz(zxyksm);
					ywlsglVehFlowDao.updateRepository(ywlsglVehFlow);
					YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
					pclog.setCzr(user.getName());
					pclog.setCzrxm(user.getYgxm());
					pclog.setCzrbm(user.getBmid());
					pclog.setCzsj(new Date());
					pclog.setCzip(getLoginIp(request));
					pclog.setCznr("U");
					pclog.setCzmac("");
					ywlsglVehFlowLogDao.addRepository(pclog);
					return 0;
				}else{
					Exception etemp = new Exception(returnstr);
					throw etemp;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	public Integer lszthf(String lsh,HttpServletRequest request) throws Exception{
		try {
			YwlsglVehFlow ywlsglVehFlow = ywlsglVehFlowDao.getRepository(lsh);
			String zthfyy = request.getParameter("zthfyy");
			String zthfsm = request.getParameter("zthfsm");
			if(null != zthfsm && !"".equals(zthfsm))zthfsm = URLDecoder.decode(zthfsm,"UTF-8");
			User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
			if(null != ywlsglVehFlow){
				if("1".equals(ywlsglVehFlow.getLszt())){
					ywlsglVehFlow.setLszt("5");
				}else if("5".equals(ywlsglVehFlow.getLszt())){
					ywlsglVehFlow.setLszt("1");
				}
				ywlsglVehFlow.setLsztSm(zthfyy);
				ywlsglVehFlow.setBz(zthfsm);
				ywlsglVehFlowDao.updateRepository(ywlsglVehFlow);
				YwlsglVehFlowLog pclog = insertYwlsglVehFlowLog(ywlsglVehFlow);
				pclog.setCzr(user.getName());
				pclog.setCzrxm(user.getYgxm());
				pclog.setCzrbm(user.getBmid());
				pclog.setCzsj(new Date());
				pclog.setCzip(getLoginIp(request));
				pclog.setCznr("U");
				pclog.setCzmac("");
				ywlsglVehFlowLogDao.addRepository(pclog);
				return 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return 1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<YwlsglVehFlow> getYwlsglVehFlowListByYj(HttpServletRequest request,int currentPage) throws Exception{
		StringBuffer hql = new StringBuffer("from YwlsglVehFlow as t where 1=1 ");
		StringBuffer sql = new StringBuffer("select count(0) from ywlsgl_veh_flow t where 1=1 ");
		User user = (User) request.getSession().getAttribute(SysConst.USERBEAN);
		String kbmid = getDeptUpid(user.getBmid());
		int pageSize = SysConst.PAGESIZE;
		int offset = SysConst.PAGESIZE*(currentPage-1);
		String curi = request.getRequestURI();
		List<YwlsglVehFlow> ywlsglVehFlowList = new ArrayList<YwlsglVehFlow>();
		
		String lsh = request.getParameter("lsh");
		
		// 流水号
		if (lsh != null && !lsh.equals("")) {
			hql.append(" and t.lsh = '" + lsh + "' ");
			sql.append(" and t.lsh = '" + lsh + "' ");
			request.setAttribute("lsh", lsh);
		}
		
		// 科级部门
		if (kbmid != null && !kbmid.equals("")) {
			hql.append(" and t.cygbm = '" + kbmid + "' ");
			sql.append(" and t.cygbm = '" + kbmid + "' ");
			request.setAttribute("kbmid", kbmid);
		}
		hql.append(" and t.lszt = '3' and t.lsdqgw='1001'");
		sql.append(" and t.lszt = '3' and t.lsdqgw='1001'");
		int size = ywlsglVehFlowDao.getRepositoryBySQLListSize(sql.toString());
		if (size > 0) {
			hql.append(" order by t.czsj desc");
			ywlsglVehFlowList = ywlsglVehFlowDao.findHQLByPage(hql.toString(),offset,pageSize);
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
		request.setAttribute("ywlsglVehFlowList", ywlsglVehFlowList);
		log.info("method:getYwlsglVehFlowList|param:request="+request+",currentPage="+currentPage);
		return ywlsglVehFlowList;
	}
	
	@SuppressWarnings("unchecked")
	public List<PdasmycSjzd> getPdasmycSjzdListByHql(String hql) throws Exception{
		List<PdasmycSjzd> list = pdasmycSjzdDao.getRepositories(hql);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<YwlsglVehFieldgl> getYwlsglVehFieldglList(String ld,String gw) throws Exception{
		try {
			String hql = "from YwlsglVehFieldgl t";
			List<YwlsglVehFieldgl> list = null;
			if("0".equals(ld)){
				hql = "from YwlsglVehFieldgl t where t.gwkxgPtg like '%"+gw+"%'";
				list = ywlsglVehFieldglDao.getRepositories(hql);
			}else if("1".equals(ld)){
				hql = "from YwlsglVehFieldgl t where t.gwkxgLdg like '%"+gw+"%'";
				list = ywlsglVehFieldglDao.getRepositories(hql);
			}
			if(null != list && list.size() > 0){
				for(YwlsglVehFieldgl ywlsglVehFieldgl : list){
					ywlsglVehFieldgl.setZdmc(ywlsglVehFieldgl.getZdmc().toLowerCase());
				}
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public List getXycyyList() throws Exception{
		List list = new ArrayList();
		try {
			String sql = "select dmz,dmms1 from ycs_xyc_code";
			list = ywlsglVehFieldglDao.findSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getZtyyList() throws Exception{
		List list = new ArrayList();
		try {
			String sql = "select dmz,dmsm1 from v_kcb_code t where t.DMLB=103";
			list = ywlsglVehFieldglDao.findSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getTbyyList(String gw) throws Exception{
		List list = new ArrayList();
		try {
			String sql = "";
			if("1001".equals(gw)){
				sql = "select dmz,dmsm1 from pdasmyc_sjzd t where t.DMLB='1001_Q'";
			}else if("2001".equals(gw)){
				sql = "select dmz,dmsm1 from pdasmyc_sjzd t where t.DMLB='2001_Q'";
			}
			if(!"".equals(sql)){
				list = ywlsglVehFieldglDao.findSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public List getTcygyyList() throws Exception{
		List list = new ArrayList();
		try {
			String sql = "select dmz,dmsm1 from pdasmyc_sjzd t where t.DMLB='2001_3'";
			list = ywlsglVehFieldglDao.findSQL(sql);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public String getDmmsBySzjd(String dmz,String dmlb) throws Exception{
		String dmms = "";
		try {
			String sql = "select dmsm1 from pdasmyc_sjzd t where t.DMLB='"+dmlb+"' and t.dmz='"+dmz+"'";
			List list = ywlsglVehFieldglDao.findSQL(sql);
			if(null != list && list.size() > 0){
				dmms = list.get(0).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return dmms;
	}
	
	@SuppressWarnings("rawtypes")
	public List getPTMOListBySql(String sql) throws Exception{
		List list = new ArrayList();
		list = ywlsglVehFieldglDao.findSQL(sql);
		log.info("method:findSQLByPage|param:sql="+sql);
		return list;
	}

	/**
	 * @return the ywlsglVehFlowDao
	 */
	public YwlsglVehFlowDao getYwlsglVehFlowDao() {
		return ywlsglVehFlowDao;
	}

	/**
	 * @param ywlsglVehFlowDao the ywlsglVehFlowDao to set
	 */
	public void setYwlsglVehFlowDao(YwlsglVehFlowDao ywlsglVehFlowDao) {
		this.ywlsglVehFlowDao = ywlsglVehFlowDao;
	}

	/**
	 * @return the ywlsglVehFlowLogDao
	 */
	public YwlsglVehFlowLogDao getYwlsglVehFlowLogDao() {
		return ywlsglVehFlowLogDao;
	}

	/**
	 * @param ywlsglVehFlowLogDao the ywlsglVehFlowLogDao to set
	 */
	public void setYwlsglVehFlowLogDao(YwlsglVehFlowLogDao ywlsglVehFlowLogDao) {
		this.ywlsglVehFlowLogDao = ywlsglVehFlowLogDao;
	}

	/**
	 * @return the ywlsglVehFlowModifyDao
	 */
	public YwlsglVehFlowModifyDao getYwlsglVehFlowModifyDao() {
		return ywlsglVehFlowModifyDao;
	}

	/**
	 * @param ywlsglVehFlowModifyDao the ywlsglVehFlowModifyDao to set
	 */
	public void setYwlsglVehFlowModifyDao(
			YwlsglVehFlowModifyDao ywlsglVehFlowModifyDao) {
		this.ywlsglVehFlowModifyDao = ywlsglVehFlowModifyDao;
	}

	/**
	 * @return the log
	 */
	public static Logger getLog() {
		return log;
	}

	/**
	 * @return the vehicleTempMidOutDao
	 */
	public VehicleTempMidOutDao getVehicleTempMidOutDao() {
		return vehicleTempMidOutDao;
	}

	/**
	 * @param vehicleTempMidOutDao the vehicleTempMidOutDao to set
	 */
	public void setVehicleTempMidOutDao(VehicleTempMidOutDao vehicleTempMidOutDao) {
		this.vehicleTempMidOutDao = vehicleTempMidOutDao;
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
	 * @return the ywlsglVehFieldglDao
	 */
	public YwlsglVehFieldglDao getYwlsglVehFieldglDao() {
		return ywlsglVehFieldglDao;
	}

	/**
	 * @param ywlsglVehFieldglDao the ywlsglVehFieldglDao to set
	 */
	public void setYwlsglVehFieldglDao(YwlsglVehFieldglDao ywlsglVehFieldglDao) {
		this.ywlsglVehFieldglDao = ywlsglVehFieldglDao;
	}

	/**
	 * @return the pdasmycSjzdDao
	 */
	public PdasmycSjzdDao getPdasmycSjzdDao() {
		return pdasmycSjzdDao;
	}

	/**
	 * @param pdasmycSjzdDao the pdasmycSjzdDao to set
	 */
	public void setPdasmycSjzdDao(PdasmycSjzdDao pdasmycSjzdDao) {
		this.pdasmycSjzdDao = pdasmycSjzdDao;
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
	 * @return the vehicleTempMidInDao
	 */
	public VehicleTempMidInDao getVehicleTempMidInDao() {
		return vehicleTempMidInDao;
	}

	/**
	 * @param vehicleTempMidInDao the vehicleTempMidInDao to set
	 */
	public void setVehicleTempMidInDao(VehicleTempMidInDao vehicleTempMidInDao) {
		this.vehicleTempMidInDao = vehicleTempMidInDao;
	}

	

}
